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
 * ElectricCurrentTest tests the ElectricCurrent quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class ElectricCurrentTest
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
    void testElectricCurrentBasics()
    {
        // Construct with unit
        ElectricCurrent i0 = new ElectricCurrent(0.0, ElectricCurrent.Unit.A);
        assertEquals(ElectricCurrent.ZERO, i0);
        assertEquals(0.0, ElectricCurrent.ZERO.si(), 1E-12);

        ElectricCurrent i1 = new ElectricCurrent(1.0, ElectricCurrent.Unit.A);
        assertEquals(ElectricCurrent.ONE, i1);
        assertEquals(1.0, ElectricCurrent.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(ElectricCurrent.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, ElectricCurrent.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, ElectricCurrent.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, ElectricCurrent.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, ElectricCurrent.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        ElectricCurrent milli = new ElectricCurrent(2.0, ElectricCurrent.Unit.mA); // 2 mA = 0.002 A
        ElectricCurrent copy = new ElectricCurrent(milli);
        assertEquals(milli.si(), copy.si(), 1E-12);
        assertEquals(milli.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        ElectricCurrent iStr = new ElectricCurrent(1.5, "A");
        assertEquals(1.5, iStr.si(), 1E-12);

        // SI prefixes via generated/resolved units
        assertEquals(1E-6, new ElectricCurrent(1.0, ElectricCurrent.Unit.muA).si(), 1E-15);
        assertEquals(1E-3, new ElectricCurrent(1.0, ElectricCurrent.Unit.mA).si(), 1E-12);
        assertEquals(1E3, new ElectricCurrent(1.0, ElectricCurrent.Unit.kA).si(), 1E-9);
        assertEquals(1E6, new ElectricCurrent(1.0, ElectricCurrent.Unit.MA).si(), 1E-6);

        // CGS ESU / EMU derived units
        assertEquals(3.335641E-10, new ElectricCurrent(1.0, ElectricCurrent.Unit.statA).si(), 1E-16);
        assertEquals(10.0, new ElectricCurrent(1.0, ElectricCurrent.Unit.abA).si(), 1E-12);

        // Parsing valueOf and of(value, unitString)
        ElectricCurrent p1 = ElectricCurrent.valueOf("2 A");
        assertEquals(2.0, p1.si(), 1E-12);

        ElectricCurrent p2 = ElectricCurrent.valueOf("2000 mA"); // 2 A
        assertEquals(2.0, p2.si(), 1E-9);

        ElectricCurrent p3 = ElectricCurrent.of(500.0, "muA"); // 500e-6 A
        assertEquals(5e-4, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, iStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (symbol)
        assertEquals("A", iStr.siUnit().toString(true, false));

        // ofSi
        ElectricCurrent neg = ElectricCurrent.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide, multiply) behavior.
     */
    @Test
    void testElectricCurrentOperations()
    {
        // Divide by ElectricCurrent -> Dimensionless
        var d1 = ElectricCurrent.ONE.divide(ElectricCurrent.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = ElectricCurrent.ofSi(1.0).divide(ElectricCurrent.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // ElectricCurrent * ElectricPotential -> Power
        var p = ElectricCurrent.ofSi(2.0).multiply(ElectricPotential.ofSi(3.0));
        assertTrue(p instanceof Power);
        assertEquals(6.0, p.si(), 1E-12);

        // ElectricCurrent * Duration -> ElectricCharge
        var q = ElectricCurrent.ofSi(2.0).multiply(Duration.ofSi(3.0));
        assertTrue(q instanceof ElectricCharge);
        assertEquals(6.0, q.si(), 1E-12);

        // ElectricCurrent * ElectricalResistance -> ElectricPotential
        var v1 = ElectricCurrent.ofSi(2.0).multiply(ElectricalResistance.ofSi(3.0));
        assertTrue(v1 instanceof ElectricPotential);
        assertEquals(6.0, v1.si(), 1E-12);

        // ElectricCurrent / ElectricPotential -> ElectricalConductance
        var g = ElectricCurrent.ofSi(2.0).divide(ElectricPotential.ofSi(4.0));
        assertTrue(g instanceof ElectricalConductance);
        assertEquals(0.5, g.si(), 1E-12);

        // ElectricCurrent / ElectricalConductance -> ElectricPotential
        var v2 = ElectricCurrent.ofSi(2.0).divide(ElectricalConductance.ofSi(4.0));
        assertTrue(v2 instanceof ElectricPotential);
        assertEquals(0.5, v2.si(), 1E-12);
    }

    /**
     * Test the ElectricCurrent.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(ElectricCurrent.Unit.SI_UNIT, ElectricCurrent.Unit.A.siUnit());
        assertEquals(ElectricCurrent.Unit.SI, ElectricCurrent.Unit.A.getBaseUnit());

        // Unit.ofSi delegates to ElectricCurrent.ofSi
        ElectricCurrent fromUnit = ElectricCurrent.Unit.A.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (A) -> should succeed
        ElectricCurrent.Unit twoA = ElectricCurrent.Unit.A.deriveUnit("2A", "2A", "two ampere", 2.0, UnitSystem.OTHER);
        ElectricCurrent x = new ElectricCurrent(1.0, twoA); // 1 * 2 A == 2 A
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        ElectricCurrent.Unit nonLinear =
                new ElectricCurrent.Unit("gA", "gA", "grade-like ampere (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2A", "g2A", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
