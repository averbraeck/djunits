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
 * IlluminanceTest tests the Illuminance quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class IlluminanceTest
{
    /**
     * Use standard locale for tests.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, constants, parsing, SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testIlluminanceBasics()
    {
        // Construct with unit
        Illuminance e0 = new Illuminance(0.0, Illuminance.Unit.lx);
        assertEquals(Illuminance.ZERO, e0);
        assertEquals(0.0, Illuminance.ZERO.si(), 1E-12);

        Illuminance e1 = new Illuminance(1.0, Illuminance.Unit.lx);
        assertEquals(Illuminance.ONE, e1);
        assertEquals(1.0, Illuminance.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Illuminance.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Illuminance.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Illuminance.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Illuminance.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Illuminance.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Illuminance kilo = new Illuminance(2.0, Illuminance.Unit.klx); // 2 klx = 2000 lx
        Illuminance copy = new Illuminance(kilo);
        assertEquals(kilo.si(), copy.si(), 1E-9);
        assertEquals(kilo.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Illuminance eStr = new Illuminance(1.5, "lx");
        assertEquals(1.5, eStr.si(), 1E-12);

        // SI prefixes via resolved/derived units
        assertEquals(1E-3, new Illuminance(1.0, Illuminance.Unit.mlx).si(), 1E-15); // millilux
        assertEquals(1E-6, new Illuminance(1.0, Illuminance.Unit.mulx).si(), 1E-18); // microlux
        assertEquals(1E3, new Illuminance(1.0, Illuminance.Unit.klx).si(), 1E-9); // kilolux

        // phot: 1 ph = 10_000 lx
        assertEquals(1.0E4, new Illuminance(1.0, Illuminance.Unit.ph).si(), 1E-9);

        // nox: equals millilux; here: 1 nx = 1 mlx = 1E-3 lx
        assertEquals(1E-3, new Illuminance(1.0, Illuminance.Unit.nx).si(), 1E-15);

        // Parsing valueOf and of(value, unitString)
        Illuminance p1 = Illuminance.valueOf("2 lx");
        assertEquals(2.0, p1.si(), 1E-12);

        Illuminance p2 = Illuminance.valueOf("2 klx"); // 2000 lx
        assertEquals(2000.0, p2.si(), 1E-9);

        Illuminance p3 = Illuminance.of(500.0, "mlx"); // 0.5 lx
        assertEquals(0.5, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, eStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("srcd/m2", eStr.siUnit().toString(true, false));

        // ofSi
        Illuminance neg = Illuminance.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testIlluminanceOperations()
    {
        // Divide by Illuminance -> Dimensionless
        var d1 = Illuminance.ONE.divide(Illuminance.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Illuminance.ofSi(1.0).divide(Illuminance.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Illuminance * Area -> LuminousFlux (lm = lx * m^2)
        var phi = Illuminance.ofSi(2.0).multiply(Area.ofSi(3.0));
        assertTrue(phi instanceof LuminousFlux);
        assertEquals(6.0, phi.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Illuminance.Unit.SI_UNIT, Illuminance.Unit.lx.siUnit());
        assertEquals(Illuminance.Unit.SI, Illuminance.Unit.lx.getBaseUnit());

        // Unit.ofSi delegates to Illuminance.ofSi
        Illuminance fromUnit = Illuminance.Unit.lx.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (lx) -> should succeed
        Illuminance.Unit twoLx = Illuminance.Unit.lx.deriveUnit("2lx", "two lux", 2.0, UnitSystem.OTHER);
        Illuminance x = new Illuminance(1.0, twoLx); // 1 * 2 lx == 2 lx
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Illuminance.Unit nonLinear =
                new Illuminance.Unit("glx", "glx", "grade-like lux (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2lx", "nonlinear derived", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
