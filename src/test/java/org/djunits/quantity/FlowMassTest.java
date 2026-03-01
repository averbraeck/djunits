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
 * FlowMassTest tests the FlowMass quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class FlowMassTest
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
    void testFlowMassBasics()
    {
        // Construct with unit
        FlowMass fm0 = new FlowMass(0.0, FlowMass.Unit.kg_s);
        assertEquals(FlowMass.ZERO, fm0);
        assertEquals(0.0, FlowMass.ZERO.si(), 1E-12);

        FlowMass fm1 = new FlowMass(1.0, FlowMass.Unit.kg_s);
        assertEquals(FlowMass.ONE, fm1);
        assertEquals(1.0, FlowMass.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(FlowMass.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, FlowMass.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, FlowMass.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, FlowMass.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, FlowMass.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        FlowMass poundsPerSec = new FlowMass(2.0, FlowMass.Unit.lb_s); // 2 lb/s = 2 * CONST_LB kg/s
        FlowMass copy = new FlowMass(poundsPerSec);
        assertEquals(poundsPerSec.si(), copy.si(), 1E-12);
        assertEquals(poundsPerSec.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        FlowMass fmStr = new FlowMass(1.5, "kg/s");
        assertEquals(1.5, fmStr.si(), 1E-12);

        // Derived unit conversion: 1 lb/s -> CONST_LB kg/s
        double lbInKg = Mass.Unit.CONST_LB; // 0.45359237...
        assertEquals(lbInKg, new FlowMass(1.0, FlowMass.Unit.lb_s).si(), 1E-12);

        // Parsing valueOf and of(value, unitString)
        FlowMass p1 = FlowMass.valueOf("2 kg/s");
        assertEquals(2.0, p1.si(), 1E-12);

        FlowMass p2 = FlowMass.of(10.0, "lb/s");
        assertEquals(10.0 * lbInKg, p2.si(), 1E-12);

        // instantiate creates new instance with same SI value
        assertEquals(-10.1, fmStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("kg/s", fmStr.siUnit().toString(true, false));

        // ofSi
        FlowMass neg = FlowMass.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic operations with related quantities.
     */
    @Test
    void testFlowMassOperations()
    {
        // Divide by FlowMass -> Dimensionless
        var d1 = FlowMass.ONE.divide(FlowMass.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = FlowMass.ofSi(1.0).divide(FlowMass.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // FlowMass * Duration -> Mass
        var m1 = FlowMass.ofSi(2.0).multiply(Duration.ofSi(3.0));
        assertTrue(m1 instanceof Mass);
        assertEquals(6.0, m1.si(), 1E-12);

        // FlowMass / Frequency -> Mass
        var m2 = FlowMass.ofSi(6.0).divide(Frequency.ofSi(2.0));
        assertTrue(m2 instanceof Mass);
        assertEquals(3.0, m2.si(), 1E-12);

        // FlowMass / Mass -> Frequency
        var f1 = FlowMass.ofSi(6.0).divide(Mass.ofSi(2.0));
        assertTrue(f1 instanceof Frequency);
        assertEquals(3.0, f1.si(), 1E-12);

        // FlowMass * Speed -> Force
        var fo1 = FlowMass.ofSi(2.0).multiply(Speed.ofSi(3.0));
        assertTrue(fo1 instanceof Force);
        assertEquals(6.0, fo1.si(), 1E-12);

        // FlowMass / FlowVolume -> Density
        var rho1 = FlowMass.ofSi(6.0).divide(FlowVolume.ofSi(2.0));
        assertTrue(rho1 instanceof Density);
        assertEquals(3.0, rho1.si(), 1E-12);

        // FlowMass / Density -> FlowVolume
        var qv1 = FlowMass.ofSi(6.0).divide(Density.ofSi(2.0));
        assertTrue(qv1 instanceof FlowVolume);
        assertEquals(3.0, qv1.si(), 1E-12);

        // FlowMass * Length -> Momentum
        var p1 = FlowMass.ofSi(2.0).multiply(Length.ofSi(3.0));
        assertTrue(p1 instanceof Momentum);
        assertEquals(6.0, p1.si(), 1E-12);
    }

    /**
     * Test the FlowMass.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(FlowMass.Unit.SI_UNIT, FlowMass.Unit.kg_s.siUnit());
        assertEquals(FlowMass.Unit.SI, FlowMass.Unit.kg_s.getBaseUnit());

        // Unit.ofSi delegates to FlowMass.ofSi
        FlowMass fromUnit = FlowMass.Unit.kg_s.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (kg/s) -> should succeed
        FlowMass.Unit twoKgPerS = FlowMass.Unit.kg_s.deriveUnit("2kg/s", "2 kilogram per second", 2.0, UnitSystem.OTHER);
        FlowMass x = new FlowMass(1.0, twoKgPerS); // 1 * 2 kg/s == 2 kg/s
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        FlowMass.Unit nonLinear =
                new FlowMass.Unit("gkg/s", "gkg/s", "grade-like kg/s (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2kg/s", "g2 kg/s", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
