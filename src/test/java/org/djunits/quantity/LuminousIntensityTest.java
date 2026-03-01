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
 * LuminousIntensityTest tests the LuminousIntensity quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class LuminousIntensityTest
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
    void testLuminousIntensityBasics()
    {
        // Construct with unit
        LuminousIntensity li0 = new LuminousIntensity(0.0, LuminousIntensity.Unit.cd);
        assertEquals(LuminousIntensity.ZERO, li0);
        assertEquals(0.0, LuminousIntensity.ZERO.si(), 1E-12);

        LuminousIntensity li1 = new LuminousIntensity(1.0, LuminousIntensity.Unit.cd);
        assertEquals(LuminousIntensity.ONE, li1);
        assertEquals(1.0, LuminousIntensity.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(LuminousIntensity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, LuminousIntensity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, LuminousIntensity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, LuminousIntensity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, LuminousIntensity.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        LuminousIntensity two = new LuminousIntensity(2.0, LuminousIntensity.Unit.cd);
        LuminousIntensity copy = new LuminousIntensity(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        LuminousIntensity fromStr = new LuminousIntensity(1.5, "cd");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        LuminousIntensity p1 = LuminousIntensity.valueOf("2 cd");
        assertEquals(2.0, p1.si(), 1E-12);

        LuminousIntensity p2 = LuminousIntensity.of(500.0, "cd");
        assertEquals(500.0, p2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of string used in Unit ("cd")
        assertEquals("cd", fromStr.siUnit().toString(true, false));

        // ofSi
        LuminousIntensity neg = LuminousIntensity.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> LuminousIntensity.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> LuminousIntensity.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> LuminousIntensity.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> LuminousIntensity.valueOf("not-a-number cd"));

        // of null unit
        assertThrows(NullPointerException.class, () -> LuminousIntensity.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> LuminousIntensity.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> LuminousIntensity.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testLuminousIntensityOperations()
    {
        // cd / cd -> Dimensionless
        var d1 = LuminousIntensity.ONE.divide(LuminousIntensity.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = LuminousIntensity.ofSi(1.0).divide(LuminousIntensity.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = LuminousIntensity.ofSi(1.0).divide(LuminousIntensity.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // cd * sr -> lm (LuminousFlux)
        var omega = SolidAngle.ofSi(3.0);
        var flux = LuminousIntensity.ofSi(4.0).multiply(omega);
        assertTrue(flux instanceof LuminousFlux);
        assertEquals(12.0, flux.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, and non-linear derivation exception
     * branch.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(LuminousIntensity.Unit.SI_UNIT, LuminousIntensity.Unit.cd.siUnit());
        assertEquals(LuminousIntensity.Unit.SI, LuminousIntensity.Unit.cd.getBaseUnit());

        // Unit.ofSi delegates to LuminousIntensity.ofSi
        LuminousIntensity fromUnit = LuminousIntensity.Unit.cd.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (cd) -> kcd (scale 1000)
        LuminousIntensity.Unit kcd =
                LuminousIntensity.Unit.cd.deriveUnit("kcd", "kcd", "kilocandela", 1000.0, UnitSystem.SI_DERIVED);
        LuminousIntensity x = new LuminousIntensity(1.0, kcd); // 1 kcd == 1000 cd
        assertEquals(1000.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        LuminousIntensity.Unit nonLinear = new LuminousIntensity.Unit("gCd", "gCd", "grade-like candela (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Registry sanity for base abbreviation
        LuminousIntensity.Unit resolved = Units.resolve(LuminousIntensity.Unit.class, "cd");
        assertEquals(LuminousIntensity.Unit.cd, resolved);
    }
}
