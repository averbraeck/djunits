package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * FlowVolumeTest tests the FlowVolume quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class FlowVolumeTest
{
    /**
     * Setup uniform locale before the tests.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test the basic features: constructors, constants, parsing, SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testFlowVolumeBasics()
    {
        // Construct with unit
        FlowVolume q0 = new FlowVolume(0.0, FlowVolume.Unit.m3_s);
        assertEquals(FlowVolume.ZERO, q0);
        assertEquals(0.0, FlowVolume.ZERO.si(), 1E-12);

        FlowVolume q1 = new FlowVolume(1.0, FlowVolume.Unit.m3_s);
        assertEquals(FlowVolume.ONE, q1);
        assertEquals(1.0, FlowVolume.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(FlowVolume.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, FlowVolume.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, FlowVolume.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, FlowVolume.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, FlowVolume.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        FlowVolume litersPerMin = new FlowVolume(2.0, FlowVolume.Unit.L_min); // 2 L/min = 2e-3/60 m3/s
        FlowVolume copy = new FlowVolume(litersPerMin);
        assertEquals(litersPerMin.si(), copy.si(), 1E-15);
        assertEquals(litersPerMin.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        FlowVolume qStr = new FlowVolume(1.5, "m3/s");
        assertEquals(1.5, qStr.si(), 1E-12);

        // Time-based SI-accepted variants
        assertEquals(1.0 / 60.0, new FlowVolume(1.0, FlowVolume.Unit.m3_min).si(), 1E-15);
        assertEquals(1.0 / 3600.0, new FlowVolume(1.0, FlowVolume.Unit.m3_h).si(), 1E-15);
        assertEquals(1.0 / 86400.0, new FlowVolume(1.0, FlowVolume.Unit.m3_day).si(), 1E-15);

        // Liter-based variants (1 L = 1e-3 m3)
        assertEquals(1E-3, new FlowVolume(1.0, FlowVolume.Unit.L_s).si(), 1E-15);
        assertEquals(1E-3 / 60.0, new FlowVolume(1.0, FlowVolume.Unit.L_min).si(), 1E-15);
        assertEquals(1E-3 / 3600.0, new FlowVolume(1.0, FlowVolume.Unit.L_h).si(), 1E-15);
        assertEquals(1E-3 / 86400.0, new FlowVolume(1.0, FlowVolume.Unit.L_day).si(), 1E-15);

        // Imperial / US customary variants
        assertEquals(Volume.Unit.CONST_CUBIC_FOOT, new FlowVolume(1.0, FlowVolume.Unit.ft3_s).si(), 1E-15);
        assertEquals(Volume.Unit.CONST_CUBIC_FOOT / 60.0, new FlowVolume(1.0, FlowVolume.Unit.ft3_min).si(), 1E-15);
        assertEquals(Volume.Unit.CONST_CUBIC_INCH, new FlowVolume(1.0, FlowVolume.Unit.in3_s).si(), 1E-20);
        assertEquals(Volume.Unit.CONST_CUBIC_INCH / 60.0, new FlowVolume(1.0, FlowVolume.Unit.in3_min).si(), 1E-20);
        assertEquals(Volume.Unit.CONST_GALLON_US, new FlowVolume(1.0, FlowVolume.Unit.gal_US_s).si(), 1E-15);
        assertEquals(Volume.Unit.CONST_GALLON_US / 60.0, new FlowVolume(1.0, FlowVolume.Unit.gal_US_min).si(), 1E-15);
        assertEquals(Volume.Unit.CONST_GALLON_US / 3600.0, new FlowVolume(1.0, FlowVolume.Unit.gal_US_h).si(), 1E-15);
        assertEquals(Volume.Unit.CONST_GALLON_US / 86400.0, new FlowVolume(1.0, FlowVolume.Unit.gal_US_day).si(), 1E-15);

        // Parsing valueOf and of(value, unitString)
        FlowVolume p1 = FlowVolume.valueOf("2 m3/s");
        assertEquals(2.0, p1.si(), 1E-12);

        FlowVolume p2 = FlowVolume.valueOf("120 L/min"); // 120 * 1e-3 / 60 = 0.002 m3/s
        assertEquals(0.002, p2.si(), 1E-12);

        FlowVolume p3 = FlowVolume.of(500.0, "gal(US)/h"); // 500 * gallon_us / 3600
        assertEquals(500.0 * Volume.Unit.CONST_GALLON_US / 3600.0, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, qStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("m3/s", qStr.siUnit().toString(true, false));

        // ofSi
        FlowVolume neg = FlowVolume.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic operations with related quantities.
     */
    @Test
    void testFlowVolumeOperations()
    {
        // Divide by FlowVolume -> Dimensionless
        var d1 = FlowVolume.ONE.divide(FlowVolume.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = FlowVolume.ofSi(1.0).divide(FlowVolume.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // FlowVolume * Duration -> Volume
        var v1 = FlowVolume.ofSi(2.0).multiply(Duration.ofSi(3.0));
        assertTrue(v1 instanceof Volume);
        assertEquals(6.0, v1.si(), 1E-12);

        // FlowVolume / Frequency -> Volume
        var v2 = FlowVolume.ofSi(6.0).divide(Frequency.ofSi(2.0));
        assertTrue(v2 instanceof Volume);
        assertEquals(3.0, v2.si(), 1E-12);

        // FlowVolume / Volume -> Frequency
        var f1 = FlowVolume.ofSi(6.0).divide(Volume.ofSi(2.0));
        assertTrue(f1 instanceof Frequency);
        assertEquals(3.0, f1.si(), 1E-12);

        // FlowVolume / Area -> Speed
        var s1 = FlowVolume.ofSi(6.0).divide(Area.ofSi(2.0));
        assertTrue(s1 instanceof Speed);
        assertEquals(3.0, s1.si(), 1E-12);

        // FlowVolume / Speed -> Area
        var a1 = FlowVolume.ofSi(6.0).divide(Speed.ofSi(2.0));
        assertTrue(a1 instanceof Area);
        assertEquals(3.0, a1.si(), 1E-12);

        // FlowVolume * Density -> FlowMass
        var qm = FlowVolume.ofSi(2.0).multiply(Density.ofSi(3.0));
        assertTrue(qm instanceof FlowMass);
        assertEquals(6.0, qm.si(), 1E-12);
    }

    /**
     * Test the FlowVolume.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(FlowVolume.Unit.SI_UNIT, FlowVolume.Unit.m3_s.siUnit());
        assertEquals(FlowVolume.Unit.SI, FlowVolume.Unit.m3_s.getBaseUnit());

        // Unit.ofSi delegates to FlowVolume.ofSi
        FlowVolume fromUnit = FlowVolume.Unit.m3_s.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (m3/s) -> should succeed
        FlowVolume.Unit twoM3S = FlowVolume.Unit.m3_s.deriveUnit("2m3/s", "two cubic meters per second", 2.0, UnitSystem.OTHER);
        FlowVolume x = new FlowVolume(1.0, twoM3S); // 1 * 2 m3/s == 2 m3/s
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        FlowVolume.Unit nonLinear =
                new FlowVolume.Unit("gm3/s", "gm3/s", "grade-like m3/s (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2m3/s", "g2 m3/s", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
