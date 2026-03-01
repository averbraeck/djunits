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
 * ElectricalInductanceTest tests the ElectricalInductance quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license. author Alexander Verbraeck
 */
class ElectricalInductanceTest
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
    void testElectricalInductanceBasics()
    {
        // Construct with unit
        ElectricalInductance l0 = new ElectricalInductance(0.0, ElectricalInductance.Unit.H);
        assertEquals(ElectricalInductance.ZERO, l0);
        assertEquals(0.0, ElectricalInductance.ZERO.si(), 1E-12);

        ElectricalInductance l1 = new ElectricalInductance(1.0, ElectricalInductance.Unit.H);
        assertEquals(ElectricalInductance.ONE, l1);
        assertEquals(1.0, ElectricalInductance.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(ElectricalInductance.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, ElectricalInductance.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, ElectricalInductance.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, ElectricalInductance.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, ElectricalInductance.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        ElectricalInductance milli = new ElectricalInductance(2.0, "mH"); // 2 mH = 0.002 H
        ElectricalInductance copy = new ElectricalInductance(milli);
        assertEquals(milli.si(), copy.si(), 1E-12);
        assertEquals(milli.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        ElectricalInductance lStr = new ElectricalInductance(1.5, "H");
        assertEquals(1.5, lStr.si(), 1E-12);

        // SI prefixes via generated units
        assertEquals(1E-3, new ElectricalInductance(1.0, "mH").si(), 1E-15);
        assertEquals(1E-6, new ElectricalInductance(1.0, "muH").si(), 1E-18);
        assertEquals(1E-9, new ElectricalInductance(1.0, "nH").si(), 1E-21);
        assertEquals(1E-12, new ElectricalInductance(1.0, "pH").si(), 1E-24);

        // Parsing valueOf and of(value, unitString)
        ElectricalInductance p1 = ElectricalInductance.valueOf("2 H");
        assertEquals(2.0, p1.si(), 1E-12);

        ElectricalInductance p2 = ElectricalInductance.valueOf("2 mH"); // 0.002 H
        assertEquals(0.002, p2.si(), 1E-12);

        ElectricalInductance p3 = ElectricalInductance.of(500.0, "nH"); // 500e-9 H
        assertEquals(5e-7, p3.si(), 1E-18);

        // instantiate
        assertEquals(-10.1, lStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("kgm2/s2A2", lStr.siUnit().toString(true, false));

        // ofSi
        ElectricalInductance neg = ElectricalInductance.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide) behavior.
     */
    @Test
    void testElectricalInductanceOperations()
    {
        // Divide by ElectricalInductance -> Dimensionless
        var r1 = ElectricalInductance.ONE.divide(ElectricalInductance.ONE);
        assertTrue(r1 instanceof Dimensionless);
        assertEquals(1.0, r1.si(), 1E-12);

        var r2 = ElectricalInductance.ofSi(1.0).divide(ElectricalInductance.ofSi(2.0));
        assertEquals(0.5, r2.si(), 1E-12);
    }

    /**
     * Test the ElectricalInductance.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear
     * scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(ElectricalInductance.Unit.SI_UNIT, ElectricalInductance.Unit.H.siUnit());
        assertEquals(ElectricalInductance.Unit.SI, ElectricalInductance.Unit.H.getBaseUnit());

        // Unit.ofSi delegates to ElectricalInductance.ofSi
        ElectricalInductance fromUnit = ElectricalInductance.Unit.H.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (H) -> should succeed
        ElectricalInductance.Unit twoH = ElectricalInductance.Unit.H.deriveUnit("2H", "2H", "two henry", 2.0, UnitSystem.OTHER);
        ElectricalInductance x = new ElectricalInductance(1.0, twoH); // 1 * 2 H == 2 H
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        ElectricalInductance.Unit nonLinear = new ElectricalInductance.Unit("gH", "gH", "grade-like henry (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2H", "g2H", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
