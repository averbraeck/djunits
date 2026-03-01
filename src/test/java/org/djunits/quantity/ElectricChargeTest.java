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
 * ElectricChargeTest tests the ElectricCharge quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class ElectricChargeTest
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
    void testElectricChargeBasics()
    {
        // Construct with unit
        ElectricCharge q0 = new ElectricCharge(0.0, ElectricCharge.Unit.C);
        assertEquals(ElectricCharge.ZERO, q0);
        assertEquals(0.0, ElectricCharge.ZERO.si(), 1E-12);

        ElectricCharge q1 = new ElectricCharge(1.0, ElectricCharge.Unit.C);
        assertEquals(ElectricCharge.ONE, q1);
        assertEquals(1.0, ElectricCharge.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(ElectricCharge.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, ElectricCharge.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, ElectricCharge.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, ElectricCharge.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, ElectricCharge.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        ElectricCharge mAh = new ElectricCharge(2.0, ElectricCharge.Unit.mAh); // 2 mAh = 7.2 C
        ElectricCharge copy = new ElectricCharge(mAh);
        assertEquals(mAh.si(), copy.si(), 1E-12);
        assertEquals(mAh.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        ElectricCharge qStr = new ElectricCharge(1.5, "C");
        assertEquals(1.5, qStr.si(), 1E-12);

        // SI prefixes via generated/resolved units
        assertEquals(1E-3, new ElectricCharge(1.0, ElectricCharge.Unit.mC).si(), 1E-15);
        assertEquals(1E-6, new ElectricCharge(1.0, ElectricCharge.Unit.muC).si(), 1E-18);

        // Derived units
        // 1 Ah = 3600 C
        assertEquals(3600.0, new ElectricCharge(1.0, ElectricCharge.Unit.Ah).si(), 1E-9);
        // 1 mAh = 3.6 C
        assertEquals(3.6, new ElectricCharge(1.0, ElectricCharge.Unit.mAh).si(), 1E-12);
        // 1 mAs = 1e-3 C
        assertEquals(1e-3, new ElectricCharge(1.0, ElectricCharge.Unit.mAs).si(), 1E-15);
        // 1 kAh = 3.6e6 C
        assertEquals(3.6e6, new ElectricCharge(1.0, ElectricCharge.Unit.kAh).si(), 1E-3);
        // 1 MAh = 3.6e9 C
        assertEquals(3.6e9, new ElectricCharge(1.0, ElectricCharge.Unit.MAh).si(), 1E2);

        // Faraday, elementary charge, CGS ESU/EMU
        assertEquals(96485.3383, new ElectricCharge(1.0, ElectricCharge.Unit.F).si(), 1E-7);
        assertEquals(1.602176634E-19, new ElectricCharge(1.0, ElectricCharge.Unit.e).si(), 1E-30);
        assertEquals(3.335641E-10, new ElectricCharge(1.0, ElectricCharge.Unit.statC).si(), 1E-16);
        assertEquals(3.335641E-10, new ElectricCharge(1.0, ElectricCharge.Unit.Fr).si(), 1E-16); // same as statC
        assertEquals(3.335641E-10, new ElectricCharge(1.0, ElectricCharge.Unit.esu).si(), 1E-16); // same as statC
        assertEquals(10.0, new ElectricCharge(1.0, ElectricCharge.Unit.abC).si(), 1E-12);
        assertEquals(10.0, new ElectricCharge(1.0, ElectricCharge.Unit.emu).si(), 1E-12); // same as abC

        // Parsing valueOf and of(value, unitString)
        ElectricCharge p1 = ElectricCharge.valueOf("2 C");
        assertEquals(2.0, p1.si(), 1E-12);

        ElectricCharge p2 = ElectricCharge.valueOf("2 mC"); // 0.002 C
        assertEquals(0.002, p2.si(), 1E-12);

        ElectricCharge p3 = ElectricCharge.of(500.0, "statC"); // 500 * 3.335641e-10 C
        assertEquals(500.0 * 3.335641E-10, p3.si(), 1E-16);

        // instantiate
        assertEquals(-10.1, qStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (A·s)
        assertEquals("sA", qStr.siUnit().toString(true, false));

        // ofSi
        ElectricCharge neg = ElectricCharge.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide variants) behavior.
     */
    @Test
    void testElectricChargeOperations()
    {
        // Divide by ElectricCharge -> Dimensionless
        var d1 = ElectricCharge.ONE.divide(ElectricCharge.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = ElectricCharge.ofSi(1.0).divide(ElectricCharge.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // ElectricCharge / Duration -> ElectricCurrent
        var i = ElectricCharge.ofSi(6.0).divide(Duration.ofSi(2.0));
        assertTrue(i instanceof ElectricCurrent);
        assertEquals(3.0, i.si(), 1E-12);

        // ElectricCharge / ElectricCurrent -> Duration
        var t = ElectricCharge.ofSi(6.0).divide(ElectricCurrent.ofSi(2.0));
        assertTrue(t instanceof Duration);
        assertEquals(3.0, t.si(), 1E-12);

        // ElectricCharge / ElectricPotential -> ElectricalCapacitance
        var c = ElectricCharge.ofSi(6.0).divide(ElectricPotential.ofSi(2.0));
        assertTrue(c instanceof ElectricalCapacitance);
        assertEquals(3.0, c.si(), 1E-12);

        // ElectricCharge / ElectricalCapacitance -> ElectricPotential
        var v = ElectricCharge.ofSi(6.0).divide(ElectricalCapacitance.ofSi(2.0));
        assertTrue(v instanceof ElectricPotential);
        assertEquals(3.0, v.si(), 1E-12);
    }

    /**
     * Test the ElectricCharge.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(ElectricCharge.Unit.SI_UNIT, ElectricCharge.Unit.C.siUnit());
        assertEquals(ElectricCharge.Unit.SI, ElectricCharge.Unit.C.getBaseUnit());

        // Unit.ofSi delegates to ElectricCharge.ofSi
        ElectricCharge fromUnit = ElectricCharge.Unit.C.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (C) -> should succeed
        ElectricCharge.Unit twoC = ElectricCharge.Unit.C.deriveUnit("2C", "two coulomb", 2.0, UnitSystem.OTHER);
        ElectricCharge x = new ElectricCharge(1.0, twoC); // 1 * 2 C == 2 C
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        ElectricCharge.Unit nonLinear =
                new ElectricCharge.Unit("gC", "gC", "grade-like coulomb (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2C", "g2C", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
