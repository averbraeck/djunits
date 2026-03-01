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
 * ElectricalConductanceTest tests the ElectricalConductance quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class ElectricalConductanceTest
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
    void testElectricalConductanceBasics()
    {
        // Construct with unit
        ElectricalConductance g0 = new ElectricalConductance(0.0, ElectricalConductance.Unit.S);
        assertEquals(ElectricalConductance.ZERO, g0);
        assertEquals(0.0, ElectricalConductance.ZERO.si(), 1E-12);

        ElectricalConductance g1 = new ElectricalConductance(1.0, ElectricalConductance.Unit.S);
        assertEquals(ElectricalConductance.ONE, g1);
        assertEquals(1.0, ElectricalConductance.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(ElectricalConductance.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, ElectricalConductance.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, ElectricalConductance.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, ElectricalConductance.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, ElectricalConductance.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        ElectricalConductance milli = new ElectricalConductance(2.0, ElectricalConductance.Unit.mS); // 2 mS = 0.002 S
        ElectricalConductance copy = new ElectricalConductance(milli);
        assertEquals(milli.si(), copy.si(), 1E-12);
        assertEquals(milli.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        ElectricalConductance gStr = new ElectricalConductance(1.5, "S");
        assertEquals(1.5, gStr.si(), 1E-12);

        // SI prefixes via resolved units
        assertEquals(1E-3, new ElectricalConductance(1.0, ElectricalConductance.Unit.mS).si(), 1E-15);
        assertEquals(1E-6, new ElectricalConductance(1.0, ElectricalConductance.Unit.muS).si(), 1E-18);
        assertEquals(1E-9, new ElectricalConductance(1.0, ElectricalConductance.Unit.nS).si(), 1E-21);

        // Parsing valueOf and of(value, unitString)
        ElectricalConductance p1 = ElectricalConductance.valueOf("2 S");
        assertEquals(2.0, p1.si(), 1E-12);

        ElectricalConductance p2 = ElectricalConductance.valueOf("2000 mS"); // 2 S
        assertEquals(2.0, p2.si(), 1E-12);

        ElectricalConductance p3 = ElectricalConductance.of(500.0, "nS"); // 500e-9 S
        assertEquals(5e-7, p3.si(), 1E-18);

        // instantiate
        assertEquals(-10.1, gStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("s3A2/kgm2", gStr.siUnit().toString(true, false));

        // ofSi
        ElectricalConductance neg = ElectricalConductance.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide, multiply, reciprocal) behavior.
     */
    @Test
    void testElectricalConductanceOperations()
    {
        // Divide by ElectricalConductance -> Dimensionless
        var r1 = ElectricalConductance.ONE.divide(ElectricalConductance.ONE);
        assertTrue(r1 instanceof Dimensionless);
        assertEquals(1.0, r1.si(), 1E-12);

        var r2 = ElectricalConductance.ofSi(1.0).divide(ElectricalConductance.ofSi(2.0));
        assertEquals(0.5, r2.si(), 1E-12);

        // ElectricalConductance * ElectricalResistance -> Dimensionless
        var d = ElectricalConductance.ofSi(2.0).multiply(ElectricalResistance.ofSi(3.0));
        assertTrue(d instanceof Dimensionless);
        assertEquals(6.0, d.si(), 1E-12);

        // ElectricalConductance * ElectricPotential -> ElectricCurrent
        var i = ElectricalConductance.ofSi(2.0).multiply(ElectricPotential.ofSi(3.0));
        assertTrue(i instanceof ElectricCurrent);
        assertEquals(6.0, i.si(), 1E-12);

        // ElectricalConductance * Duration -> ElectricalCapacitance
        var c = ElectricalConductance.ofSi(2.0).multiply(Duration.ofSi(3.0));
        assertTrue(c instanceof ElectricalCapacitance);
        assertEquals(6.0, c.si(), 1E-12);

        // reciprocal -> ElectricalResistance
        var r = ElectricalConductance.ofSi(4.0).reciprocal();
        assertTrue(r instanceof ElectricalResistance);
        assertEquals(0.25, r.si(), 1E-12);
    }

    /**
     * Test the ElectricalConductance.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear
     * scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(ElectricalConductance.Unit.SI_UNIT, ElectricalConductance.Unit.S.siUnit());
        assertEquals(ElectricalConductance.Unit.SI, ElectricalConductance.Unit.S.getBaseUnit());

        // Unit.ofSi delegates to ElectricalConductance.ofSi
        ElectricalConductance fromUnit = ElectricalConductance.Unit.S.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (S) -> should succeed
        ElectricalConductance.Unit twoS =
                ElectricalConductance.Unit.S.deriveUnit("2S", "2S", "two siemens", 2.0, UnitSystem.OTHER);
        ElectricalConductance x = new ElectricalConductance(1.0, twoS); // 1 * 2 S == 2 S
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        ElectricalConductance.Unit nonLinear = new ElectricalConductance.Unit("gS", "gS", "grade-like siemens (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2S", "g2S", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
