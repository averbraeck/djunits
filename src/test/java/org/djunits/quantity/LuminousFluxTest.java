package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * LuminousFluxTest tests the LuminousFlux quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class LuminousFluxTest
{
    /** Standard locale for the tests. */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, constants, parsing, SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testLuminousFluxBasics()
    {
        // Construct with unit
        LuminousFlux lf0 = new LuminousFlux(0.0, LuminousFlux.Unit.lm);
        assertEquals(LuminousFlux.ZERO, lf0);
        assertEquals(0.0, LuminousFlux.ZERO.si(), 1E-12);

        LuminousFlux lf1 = new LuminousFlux(1.0, LuminousFlux.Unit.lm);
        assertEquals(LuminousFlux.ONE, lf1);
        assertEquals(1.0, LuminousFlux.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(LuminousFlux.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, LuminousFlux.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, LuminousFlux.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, LuminousFlux.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, LuminousFlux.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        LuminousFlux two = new LuminousFlux(2.0, LuminousFlux.Unit.lm);
        LuminousFlux copy = new LuminousFlux(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        LuminousFlux fromStr = new LuminousFlux(1.5, "lm");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        LuminousFlux p1 = LuminousFlux.valueOf("2 lm");
        assertEquals(2.0, p1.si(), 1E-12);

        LuminousFlux p2 = LuminousFlux.of(500.0, "lm");
        assertEquals(500.0, p2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of string used in Unit ("cdsr")
        assertEquals("srcd", fromStr.siUnit().toString(true, false));

        // ofSi
        LuminousFlux neg = LuminousFlux.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> LuminousFlux.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> LuminousFlux.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> LuminousFlux.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> LuminousFlux.valueOf("not-a-number lm"));

        // of null unit
        assertThrows(NullPointerException.class, () -> LuminousFlux.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> LuminousFlux.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> LuminousFlux.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testLuminousFluxOperations()
    {
        // lm / lm -> Dimensionless
        var d1 = LuminousFlux.ONE.divide(LuminousFlux.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = LuminousFlux.ofSi(1.0).divide(LuminousFlux.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = LuminousFlux.ofSi(1.0).divide(LuminousFlux.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // lm / m^2 -> lux (Illuminance)
        var e = LuminousFlux.ofSi(12.0).divide(Area.ofSi(3.0));
        assertTrue(e instanceof Illuminance);
        assertEquals(4.0, e.si(), 1E-12);

        // lm / lux -> m^2 (Area)
        var a = LuminousFlux.ofSi(10.0).divide(Illuminance.ofSi(2.0));
        assertTrue(a instanceof Area);
        assertEquals(5.0, a.si(), 1E-12);

        // lm / cd -> sr (SolidAngle)
        var omega = LuminousFlux.ofSi(9.0).divide(LuminousIntensity.ofSi(3.0));
        assertTrue(omega instanceof SolidAngle);
        assertEquals(3.0, omega.si(), 1E-12);

        // lm / sr -> cd (LuminousIntensity)
        var i = LuminousFlux.ofSi(15.0).divide(SolidAngle.ofSi(5.0));
        assertTrue(i instanceof LuminousIntensity);
        assertEquals(3.0, i.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, and non-linear derivation exception
     * branch.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(LuminousFlux.Unit.SI_UNIT, LuminousFlux.Unit.lm.siUnit());
        assertEquals(LuminousFlux.Unit.SI, LuminousFlux.Unit.lm.getBaseUnit());

        // Unit.ofSi delegates to LuminousFlux.ofSi
        LuminousFlux fromUnit = LuminousFlux.Unit.lm.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (lm) -> klm (scale 1000)
        LuminousFlux.Unit klm = LuminousFlux.Unit.lm.deriveUnit("klm", "klm", "kilolumen", 1000.0, UnitSystem.SI_DERIVED);
        LuminousFlux x = new LuminousFlux(1.0, klm); // 1 klm == 1000 lm
        assertEquals(1000.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        LuminousFlux.Unit nonLinear =
                new LuminousFlux.Unit("gLm", "gLm", "grade-like lumen (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Unit resolution sanity for base abbreviation
        LuminousFlux.Unit resolved = Units.resolve(LuminousFlux.Unit.class, "lm");
        assertEquals(LuminousFlux.Unit.lm, resolved);
    }
}
