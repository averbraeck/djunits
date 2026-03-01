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
 * ElectricalResistanceTest tests the ElectricalResistance quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class ElectricalResistanceTest
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
    void testElectricalResistanceBasics()
    {
        // Construct with unit
        ElectricalResistance r0 = new ElectricalResistance(0.0, ElectricalResistance.Unit.ohm);
        assertEquals(ElectricalResistance.ZERO, r0);
        assertEquals(0.0, ElectricalResistance.ZERO.si(), 1E-12);

        ElectricalResistance r1 = new ElectricalResistance(1.0, ElectricalResistance.Unit.ohm);
        assertEquals(ElectricalResistance.ONE, r1);
        assertEquals(1.0, ElectricalResistance.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(ElectricalResistance.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, ElectricalResistance.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, ElectricalResistance.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, ElectricalResistance.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, ElectricalResistance.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        ElectricalResistance kilo = new ElectricalResistance(2.0, ElectricalResistance.Unit.kohm); // 2 kΩ = 2000 Ω
        ElectricalResistance copy = new ElectricalResistance(kilo);
        assertEquals(kilo.si(), copy.si(), 1E-9);
        assertEquals(kilo.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string (id)
        ElectricalResistance rStr = new ElectricalResistance(1.5, "ohm"); // base Ω
        assertEquals(1.5, rStr.si(), 1E-12);

        // SI prefixes via resolved units
        assertEquals(1E-6, new ElectricalResistance(1.0, ElectricalResistance.Unit.muohm).si(), 1E-15);
        assertEquals(1E-3, new ElectricalResistance(1.0, ElectricalResistance.Unit.mohm).si(), 1E-12);
        assertEquals(1E3, new ElectricalResistance(1.0, ElectricalResistance.Unit.kohm).si(), 1E-9);
        assertEquals(1E6, new ElectricalResistance(1.0, ElectricalResistance.Unit.Mohm).si(), 1E-6);
        assertEquals(1E9, new ElectricalResistance(1.0, ElectricalResistance.Unit.Gohm).si(), 1E-3);

        // Non-SI derived units (from ohm.deriveUnit)
        assertEquals(1E-9, new ElectricalResistance(1.0, ElectricalResistance.Unit.abohm).si(), 1E-18);
        assertEquals(8.987551787E11, new ElectricalResistance(1.0, ElectricalResistance.Unit.statohm).si(), 1E3);

        // Parsing valueOf and of(value, unitString)
        ElectricalResistance p1 = ElectricalResistance.valueOf("2 ohm");
        assertEquals(2.0, p1.si(), 1E-12);

        ElectricalResistance p2 = ElectricalResistance.valueOf("2 kohm"); // 2000 Ω
        assertEquals(2000.0, p2.si(), 1E-9);

        ElectricalResistance p3 = ElectricalResistance.of(500.0, "mohm"); // 0.5 Ω
        assertEquals(0.5, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, rStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("kgm2/s3A2", rStr.siUnit().toString(true, false));

        // ofSi
        ElectricalResistance neg = ElectricalResistance.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide, multiply, reciprocal) behavior.
     */
    @Test
    void testElectricalResistanceOperations()
    {
        // Divide by ElectricalResistance -> Dimensionless
        var d1 = ElectricalResistance.ONE.divide(ElectricalResistance.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = ElectricalResistance.ofSi(1.0).divide(ElectricalResistance.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // ElectricalResistance * ElectricalConductance -> Dimensionless
        var dim = ElectricalResistance.ofSi(2.0).multiply(ElectricalConductance.ofSi(3.0));
        assertTrue(dim instanceof Dimensionless);
        assertEquals(6.0, dim.si(), 1E-12);

        // ElectricalResistance * ElectricCurrent -> ElectricPotential (V = Ω * A)
        var v = ElectricalResistance.ofSi(2.0).multiply(ElectricCurrent.ofSi(3.0));
        assertTrue(v instanceof ElectricPotential);
        assertEquals(6.0, v.si(), 1E-12);

        // ElectricalResistance * Duration -> ElectricalInductance (H = Ω * s)
        var l = ElectricalResistance.ofSi(2.0).multiply(Duration.ofSi(3.0));
        assertTrue(l instanceof ElectricalInductance);
        assertEquals(6.0, l.si(), 1E-12);

        // reciprocal -> ElectricalConductance
        var g = ElectricalResistance.ofSi(4.0).reciprocal();
        assertTrue(g instanceof ElectricalConductance);
        assertEquals(0.25, g.si(), 1E-12);
    }

    /**
     * Test the ElectricalResistance.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear
     * scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(ElectricalResistance.Unit.SI_UNIT, ElectricalResistance.Unit.ohm.siUnit());
        assertEquals(ElectricalResistance.Unit.SI, ElectricalResistance.Unit.ohm.getBaseUnit());

        // Unit.ofSi delegates to ElectricalResistance.ofSi
        ElectricalResistance fromUnit = ElectricalResistance.Unit.ohm.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (ohm) -> should succeed
        ElectricalResistance.Unit twoOhm =
                ElectricalResistance.Unit.ohm.deriveUnit("2ohm", "2\u03A9", "two ohm", 2.0, UnitSystem.OTHER);
        ElectricalResistance x = new ElectricalResistance(1.0, twoOhm); // 1 * 2 Ω == 2 Ω
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        ElectricalResistance.Unit nonLinear = new ElectricalResistance.Unit("gohm", "g\u03A9", "grade-like ohm (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2ohm", "g2\u03A9", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
