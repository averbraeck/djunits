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
 * ElectricalCapacitanceTest tests the ElectricalCapacitance quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class ElectricalCapacitanceTest
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
    void testElectricalCapacitanceBasics()
    {
        // Construct with unit
        ElectricalCapacitance c0 = new ElectricalCapacitance(0.0, ElectricalCapacitance.Unit.F);
        assertEquals(ElectricalCapacitance.ZERO, c0);
        assertEquals(0.0, ElectricalCapacitance.ZERO.si(), 1E-12);

        ElectricalCapacitance c1 = new ElectricalCapacitance(1.0, ElectricalCapacitance.Unit.F);
        assertEquals(ElectricalCapacitance.ONE, c1);
        assertEquals(1.0, ElectricalCapacitance.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(ElectricalCapacitance.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, ElectricalCapacitance.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, ElectricalCapacitance.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, ElectricalCapacitance.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, ElectricalCapacitance.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        ElectricalCapacitance milli = new ElectricalCapacitance(2.0, ElectricalCapacitance.Unit.mF); // 2 mF = 0.002 F
        ElectricalCapacitance copy = new ElectricalCapacitance(milli);
        assertEquals(milli.si(), copy.si(), 1E-12);
        assertEquals(milli.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        ElectricalCapacitance cStr = new ElectricalCapacitance(1.5, "F");
        assertEquals(1.5, cStr.si(), 1E-12);

        // SI prefixes via generated / resolved units
        assertEquals(1E-3, new ElectricalCapacitance(1.0, ElectricalCapacitance.Unit.mF).si(), 1E-15);
        assertEquals(1E-6, new ElectricalCapacitance(1.0, ElectricalCapacitance.Unit.muF).si(), 1E-18);
        assertEquals(1E-9, new ElectricalCapacitance(1.0, ElectricalCapacitance.Unit.nF).si(), 1E-21);
        assertEquals(1E-12, new ElectricalCapacitance(1.0, ElectricalCapacitance.Unit.pF).si(), 1E-24);

        // Parsing valueOf and of(value, unitString)
        ElectricalCapacitance p1 = ElectricalCapacitance.valueOf("2 F");
        assertEquals(2.0, p1.si(), 1E-12);

        ElectricalCapacitance p2 = ElectricalCapacitance.valueOf("2 mF"); // 0.002 F
        assertEquals(0.002, p2.si(), 1E-12);

        ElectricalCapacitance p3 = ElectricalCapacitance.of(500.0, "nF"); // 500e-9 F
        assertEquals(5e-7, p3.si(), 1E-18);

        // instantiate
        assertEquals(-10.1, cStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("s4A2/kgm2", cStr.siUnit().toString(true, false));

        // ofSi
        ElectricalCapacitance neg = ElectricalCapacitance.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide, multiply) behavior.
     */
    @Test
    void testElectricalCapacitanceOperations()
    {
        // Divide by ElectricalCapacitance -> Dimensionless
        var r1 = ElectricalCapacitance.ONE.divide(ElectricalCapacitance.ONE);
        assertTrue(r1 instanceof Dimensionless);
        assertEquals(1.0, r1.si(), 1E-12);

        var r2 = ElectricalCapacitance.ofSi(1.0).divide(ElectricalCapacitance.ofSi(2.0));
        assertEquals(0.5, r2.si(), 1E-12);

        // ElectricalCapacitance * ElectricPotential -> ElectricCharge (Q = C * V)
        var q = ElectricalCapacitance.ofSi(2.0).multiply(ElectricPotential.ofSi(3.0));
        assertTrue(q instanceof ElectricCharge);
        assertEquals(6.0, q.si(), 1E-12);

        // ElectricalCapacitance / Duration -> ElectricalConductance (S = F / s)
        var g = ElectricalCapacitance.ofSi(2.0).divide(Duration.ofSi(4.0));
        assertTrue(g instanceof ElectricalConductance);
        assertEquals(0.5, g.si(), 1E-12);

        // ElectricalCapacitance / ElectricalConductance -> Duration (s = F / S)
        var t = ElectricalCapacitance.ofSi(2.0).divide(ElectricalConductance.ofSi(4.0));
        assertTrue(t instanceof Duration);
        assertEquals(0.5, t.si(), 1E-12);
    }

    /**
     * Test the ElectricalCapacitance.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear
     * scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(ElectricalCapacitance.Unit.SI_UNIT, ElectricalCapacitance.Unit.F.siUnit());
        assertEquals(ElectricalCapacitance.Unit.SI, ElectricalCapacitance.Unit.F.getBaseUnit());

        // Unit.ofSi delegates to ElectricalCapacitance.ofSi
        ElectricalCapacitance fromUnit = ElectricalCapacitance.Unit.F.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (F) -> should succeed
        ElectricalCapacitance.Unit twoF =
                ElectricalCapacitance.Unit.F.deriveUnit("2F", "2F", "two farads", 2.0, UnitSystem.OTHER);
        ElectricalCapacitance x = new ElectricalCapacitance(1.0, twoF); // 1 * 2 F == 2 F
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        ElectricalCapacitance.Unit nonLinear = new ElectricalCapacitance.Unit("gF", "gF", "grade-like farad (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2F", "g2F", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
