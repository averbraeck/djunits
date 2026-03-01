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
 * EquivalentDoseTest tests the EquivalentDose quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class EquivalentDoseTest
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
    void testEquivalentDoseBasics()
    {
        // Construct with unit
        EquivalentDose d0 = new EquivalentDose(0.0, EquivalentDose.Unit.Sv);
        assertEquals(EquivalentDose.ZERO, d0);
        assertEquals(0.0, EquivalentDose.ZERO.si(), 1E-12);

        EquivalentDose d1 = new EquivalentDose(1.0, EquivalentDose.Unit.Sv);
        assertEquals(EquivalentDose.ONE, d1);
        assertEquals(1.0, EquivalentDose.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(EquivalentDose.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, EquivalentDose.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, EquivalentDose.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, EquivalentDose.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, EquivalentDose.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        EquivalentDose milli = new EquivalentDose(2.0, EquivalentDose.Unit.mSv); // 2 mSv = 0.002 Sv
        EquivalentDose copy = new EquivalentDose(milli);
        assertEquals(milli.si(), copy.si(), 1E-12);
        assertEquals(milli.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        EquivalentDose dStr = new EquivalentDose(1.5, "Sv");
        assertEquals(1.5, dStr.si(), 1E-12);

        // SI prefixes via resolved units
        assertEquals(1E-3, new EquivalentDose(1.0, EquivalentDose.Unit.mSv).si(), 1E-15);
        assertEquals(1E-6, new EquivalentDose(1.0, EquivalentDose.Unit.muSv).si(), 1E-18);

        // Non-SI derived unit: rem = 0.01 Sv
        assertEquals(0.01, new EquivalentDose(1.0, EquivalentDose.Unit.rem).si(), 1E-12);

        // Parsing valueOf and of(value, unitString)
        EquivalentDose p1 = EquivalentDose.valueOf("2 Sv");
        assertEquals(2.0, p1.si(), 1E-12);

        EquivalentDose p2 = EquivalentDose.valueOf("2 mSv"); // 0.002 Sv
        assertEquals(0.002, p2.si(), 1E-12);

        EquivalentDose p3 = EquivalentDose.of(500.0, "muSv"); // 500e-6 Sv
        assertEquals(5e-4, p3.si(), 1E-12);

        // instantiate creates new instance with same SI value
        assertEquals(-10.1, dStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("m2/s2", dStr.siUnit().toString(true, false));

        // ofSi
        EquivalentDose neg = EquivalentDose.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide) behavior.
     */
    @Test
    void testEquivalentDoseOperations()
    {
        // Divide by EquivalentDose -> Dimensionless
        var r1 = EquivalentDose.ONE.divide(EquivalentDose.ONE);
        assertTrue(r1 instanceof Dimensionless);
        assertEquals(1.0, r1.si(), 1E-12);

        var r2 = EquivalentDose.ofSi(1.0).divide(EquivalentDose.ofSi(2.0));
        assertEquals(0.5, r2.si(), 1E-12);
    }

    /**
     * Test the EquivalentDose.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(EquivalentDose.Unit.SI_UNIT, EquivalentDose.Unit.Sv.siUnit());
        assertEquals(EquivalentDose.Unit.SI, EquivalentDose.Unit.Sv.getBaseUnit());

        // Unit.ofSi delegates to EquivalentDose.ofSi
        EquivalentDose fromUnit = EquivalentDose.Unit.Sv.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (Sv) -> should succeed
        EquivalentDose.Unit twoSv = EquivalentDose.Unit.Sv.deriveUnit("2Sv", "2Sv", "two sievert", 2.0, UnitSystem.OTHER);
        EquivalentDose x = new EquivalentDose(1.0, twoSv); // 1 * 2 Sv == 2 Sv
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        EquivalentDose.Unit nonLinear =
                new EquivalentDose.Unit("gSv", "gSv", "grade-like sievert (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2Sv", "g2Sv", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
