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
 * DensityTest tests the Density quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class DensityTest
{
    /**
     * Set the locale to "US" so we know what texts should be retrieved from the resources.
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
    void testDensityBasics()
    {
        // Construct with unit
        Density d0 = new Density(0.0, Density.Unit.kg_m3);
        assertEquals(Density.ZERO, d0);
        assertEquals(0.0, Density.ZERO.si());

        Density d1 = new Density(1.0, Density.Unit.kg_m3);
        assertEquals(Density.ONE, d1);
        assertEquals(1.0, Density.ONE.si());

        // Constants sanity
        assertTrue(Double.isNaN(Density.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Density.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Density.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Density.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Density.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Density gramPerCm3 = new Density(1.0, Density.Unit.g_cm3); // 1 g/cm3 == 1000 kg/m3
        Density copy = new Density(gramPerCm3);
        assertEquals(gramPerCm3.si(), copy.si(), 1E-12);

        // Construct with abbreviation string
        Density dStr = new Density(1000.0, "kg/m3");
        assertEquals(1000.0, dStr.si(), 1E-12);

        // g/cm3 conversion: 1 g/cm3 == 1000 kg/m3
        Density oneGPerCm3 = new Density(1.0, Density.Unit.g_cm3);
        assertEquals(1000.0, oneGPerCm3.si(), 1E-9);

        Density twoPointFiveGPerCm3 = new Density(2.5, "g/cm3");
        assertEquals(2500.0, twoPointFiveGPerCm3.si(), 1E-9);

        // Parsing valueOf and of(value, unitString)
        Density p1 = Density.valueOf("1 g/cm3");
        assertEquals(1000.0, p1.si(), 1E-9);

        Density p2 = Density.valueOf("750 kg/m3");
        assertEquals(750.0, p2.si(), 1E-12);

        Density p3 = Density.of(0.8, "g/cm3");
        assertEquals(800.0, p3.si(), 1E-9);

        // instantiate
        assertEquals(-10.1, dStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation
        assertEquals("kg/m3", dStr.siUnit().toString(true, false));

        // ofSi
        Density neg = Density.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testDensityOperations()
    {
        // Divide by Density -> Dimensionless
        var r1 = Density.ONE.divide(Density.ONE.scaleBy(2.0));
        assertTrue(r1 instanceof Dimensionless);
        assertEquals(0.5, r1.si(), 1E-12);

        // Density * Volume -> Mass
        var m2 = Density.ONE.scaleBy(2.0).multiply(Volume.ONE.scaleBy(3.0));
        assertTrue(m2 instanceof Mass);
        assertEquals(6.0, m2.si(), 1E-12);

        // Density * FlowVolume -> FlowMass
        var fm3 = Density.ONE.scaleBy(2.0).multiply(FlowVolume.ONE.scaleBy(3.0));
        assertTrue(fm3 instanceof FlowMass);
        assertEquals(6.0, fm3.si(), 1E-12);
    }

    /**
     * Test the Density.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Density.Unit.SI_UNIT, Density.Unit.kg_m3.siUnit());
        assertEquals(Density.Unit.SI, Density.Unit.kg_m3.getBaseUnit());

        // Unit.ofSi delegates to Density.ofSi
        Density fromUnit = Density.Unit.kg_m3.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (kg/m3) -> should succeed
        Density.Unit twoKgPerM3 =
                Density.Unit.kg_m3.deriveUnit("2kg/m3", "2kg/m3", "two kilogram per cubic meter", 2.0, UnitSystem.OTHER);
        Density x = new Density(1.0, twoKgPerM3); // 1 * 2 kg/m3 == 2 kg/m3
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Density.Unit nonLinear =
                new Density.Unit("g%/m3", "g%/m3", "grade-like density (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2%/m3", "g2%/m3", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
