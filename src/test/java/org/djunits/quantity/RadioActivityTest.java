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
 * RadioActivityTest tests the RadioActivity quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class RadioActivityTest
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
    void testRadioActivityBasics()
    {
        // Construct with unit
        RadioActivity a0 = new RadioActivity(0.0, RadioActivity.Unit.Bq);
        assertEquals(RadioActivity.ZERO, a0);
        assertEquals(0.0, RadioActivity.ZERO.si(), 1E-12);

        RadioActivity a1 = new RadioActivity(1.0, RadioActivity.Unit.Bq);
        assertEquals(RadioActivity.ONE, a1);
        assertEquals(1.0, RadioActivity.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(RadioActivity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, RadioActivity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, RadioActivity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, RadioActivity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, RadioActivity.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        RadioActivity two = new RadioActivity(2.0, RadioActivity.Unit.Bq);
        RadioActivity copy = new RadioActivity(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        RadioActivity fromStr = new RadioActivity(1.5, "Bq");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        RadioActivity p1 = RadioActivity.valueOf("2 Bq");
        assertEquals(2.0, p1.si(), 1E-12);

        RadioActivity p2 = RadioActivity.of(500.0, "Bq");
        assertEquals(500.0, p2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of string used in Unit ("/s")
        assertEquals("1/s", fromStr.siUnit().toString(true, false));

        // ofSi
        RadioActivity neg = RadioActivity.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test SI prefixes and non-SI units: Curie family and Rutherford.
     */
    @Test
    @SuppressWarnings("checkstyle:localvariablename")
    void testUnitsAndPrefixes()
    {
        // SI prefixes that are explicitly exposed
        RadioActivity.Unit kBq = RadioActivity.Unit.kBq;
        RadioActivity.Unit MBq = RadioActivity.Unit.MBq;
        RadioActivity.Unit GBq = RadioActivity.Unit.GBq;
        RadioActivity.Unit TBq = RadioActivity.Unit.TBq;

        assertEquals(1e3, new RadioActivity(1.0, kBq).si(), 1e-9);
        assertEquals(1e6, new RadioActivity(1.0, MBq).si(), 1e-6);
        assertEquals(1e9, new RadioActivity(1.0, GBq).si(), 1e-3);
        assertEquals(1e12, new RadioActivity(1.0, TBq).si(), 1e0);

        // Curie family: 1 Ci = 3.7e10 Bq (by definition here)
        RadioActivity.Unit Ci = RadioActivity.Unit.Ci;
        RadioActivity.Unit mCi = RadioActivity.Unit.mCi;
        RadioActivity.Unit muCi = RadioActivity.Unit.muCi; // microcurie
        RadioActivity.Unit nCi = RadioActivity.Unit.nCi;

        assertEquals(3.7e10, new RadioActivity(1.0, Ci).si(), 1e2); // tolerance accounts for double
        assertEquals(3.7e7, new RadioActivity(1.0, mCi).si(), 1e-1);
        assertEquals(3.7e4, new RadioActivity(1.0, muCi).si(), 1e-4);
        assertEquals(3.7e1, new RadioActivity(1.0, nCi).si(), 1e-7);

        // Rutherford: 1 Rd = 1e6 Bq
        RadioActivity.Unit Rd = RadioActivity.Unit.Rd;
        assertEquals(1e6, new RadioActivity(1.0, Rd).si(), 1e-6);

        // Registry sanity
        assertEquals(RadioActivity.Unit.Bq, Units.resolve(RadioActivity.Unit.class, "Bq"));
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> RadioActivity.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> RadioActivity.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> RadioActivity.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> RadioActivity.valueOf("not-a-number Bq"));

        // of null unit
        assertThrows(NullPointerException.class, () -> RadioActivity.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> RadioActivity.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> RadioActivity.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testRadioActivityOperations()
    {
        // (1/s) / (1/s) -> Dimensionless
        var d1 = RadioActivity.ONE.divide(RadioActivity.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = RadioActivity.ofSi(1.0).divide(RadioActivity.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = RadioActivity.ofSi(1.0).divide(RadioActivity.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, and non-linear derivation exception
     * branch.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(RadioActivity.Unit.SI_UNIT, RadioActivity.Unit.Bq.siUnit());
        assertEquals(RadioActivity.Unit.SI, RadioActivity.Unit.Bq.getBaseUnit());

        // Unit.ofSi delegates to RadioActivity.ofSi
        RadioActivity fromUnit = RadioActivity.Unit.Bq.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (Bq) -> e.g., "2Bq" (scale 2.0)
        RadioActivity.Unit twoBq = RadioActivity.Unit.Bq.deriveUnit("2Bq", "2Bq", "two becquerel", 2.0, UnitSystem.OTHER);
        RadioActivity x = new RadioActivity(1.0, twoBq); // 1 * 2 Bq == 2 Bq
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        RadioActivity.Unit nonLinear = new RadioActivity.Unit("gBq", "gBq", "grade-like becquerel (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
