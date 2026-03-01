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
 * ElectricPotentialTest tests the ElectricPotential quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class ElectricPotentialTest
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
    void testElectricPotentialBasics()
    {
        // Construct with unit
        ElectricPotential v0 = new ElectricPotential(0.0, ElectricPotential.Unit.V);
        assertEquals(ElectricPotential.ZERO, v0);
        assertEquals(0.0, ElectricPotential.ZERO.si(), 1E-12);

        ElectricPotential v1 = new ElectricPotential(1.0, ElectricPotential.Unit.V);
        assertEquals(ElectricPotential.ONE, v1);
        assertEquals(1.0, ElectricPotential.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(ElectricPotential.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, ElectricPotential.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, ElectricPotential.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, ElectricPotential.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, ElectricPotential.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        ElectricPotential kilo = new ElectricPotential(2.0, ElectricPotential.Unit.kV); // 2 kV = 2000 V
        ElectricPotential copy = new ElectricPotential(kilo);
        assertEquals(kilo.si(), copy.si(), 1E-9);
        assertEquals(kilo.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        ElectricPotential vStr = new ElectricPotential(1.5, "V");
        assertEquals(1.5, vStr.si(), 1E-12);

        // SI prefixes via generated/resolved units
        assertEquals(1E-6, new ElectricPotential(1.0, ElectricPotential.Unit.muV).si(), 1E-15);
        assertEquals(1E-3, new ElectricPotential(1.0, ElectricPotential.Unit.mV).si(), 1E-12);
        assertEquals(1E3, new ElectricPotential(1.0, ElectricPotential.Unit.kV).si(), 1E-9);
        assertEquals(1E6, new ElectricPotential(1.0, ElectricPotential.Unit.MV).si(), 1E-6);
        assertEquals(1E9, new ElectricPotential(1.0, ElectricPotential.Unit.GV).si(), 1E-3);

        // CGS ESU / EMU derived units
        assertEquals(299.792458, new ElectricPotential(1.0, ElectricPotential.Unit.statV).si(), 1E-9);
        assertEquals(1.0E-8, new ElectricPotential(1.0, ElectricPotential.Unit.abV).si(), 1E-20);

        // Parsing valueOf and of(value, unitString)
        ElectricPotential p1 = ElectricPotential.valueOf("2 V");
        assertEquals(2.0, p1.si(), 1E-12);

        ElectricPotential p2 = ElectricPotential.valueOf("2 kV"); // 2000 V
        assertEquals(2000.0, p2.si(), 1E-9);

        ElectricPotential p3 = ElectricPotential.of(500.0, "mV"); // 0.5 V
        assertEquals(0.5, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, vStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("kgm2/s3A", vStr.siUnit().toString(true, false));

        // ofSi
        ElectricPotential neg = ElectricPotential.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide, multiply) behavior.
     */
    @Test
    void testElectricPotentialOperations()
    {
        // Divide by ElectricPotential -> Dimensionless
        var d1 = ElectricPotential.ONE.divide(ElectricPotential.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = ElectricPotential.ofSi(1.0).divide(ElectricPotential.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // ElectricPotential * ElectricCurrent -> Power
        var p = ElectricPotential.ofSi(2.0).multiply(ElectricCurrent.ofSi(3.0));
        assertTrue(p instanceof Power);
        assertEquals(6.0, p.si(), 1E-12);

        // ElectricPotential / ElectricCurrent -> ElectricalResistance
        var r = ElectricPotential.ofSi(6.0).divide(ElectricCurrent.ofSi(2.0));
        assertTrue(r instanceof ElectricalResistance);
        assertEquals(3.0, r.si(), 1E-12);

        // ElectricPotential / ElectricalResistance -> ElectricCurrent
        var i = ElectricPotential.ofSi(6.0).divide(ElectricalResistance.ofSi(2.0));
        assertTrue(i instanceof ElectricCurrent);
        assertEquals(3.0, i.si(), 1E-12);
    }

    /**
     * Test the ElectricPotential.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(ElectricPotential.Unit.SI_UNIT, ElectricPotential.Unit.V.siUnit());
        assertEquals(ElectricPotential.Unit.SI, ElectricPotential.Unit.V.getBaseUnit());

        // Unit.ofSi delegates to ElectricPotential.ofSi
        ElectricPotential fromUnit = ElectricPotential.Unit.V.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (V) -> should succeed
        ElectricPotential.Unit twoV = ElectricPotential.Unit.V.deriveUnit("2V", "2V", "two volt", 2.0, UnitSystem.OTHER);
        ElectricPotential x = new ElectricPotential(1.0, twoV); // 1 * 2 V == 2 V
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        ElectricPotential.Unit nonLinear =
                new ElectricPotential.Unit("gV", "gV", "grade-like volt (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2V", "g2V", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
