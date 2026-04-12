package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TemperatureDifferenceTest tests the TemperatureDifference quantity class.<p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
class TemperatureDifferenceTest
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
    void testTemperatureDifferenceBasics()
    {
        // Construct with unit (Kelvin)
        TemperatureDifference d0 = new TemperatureDifference(0.0, Temperature.Unit.SI);
        assertEquals(TemperatureDifference.ZERO, d0);
        assertEquals(0.0, TemperatureDifference.ZERO.si(), 1E-12);

        TemperatureDifference d1 = new TemperatureDifference(1.0, Temperature.Unit.SI);
        assertEquals(TemperatureDifference.ONE, d1);
        assertEquals(1.0, TemperatureDifference.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(TemperatureDifference.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, TemperatureDifference.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, TemperatureDifference.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, TemperatureDifference.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, TemperatureDifference.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        TemperatureDifference two = new TemperatureDifference(2.0, Temperature.Unit.SI);
        TemperatureDifference copy = new TemperatureDifference(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string ("K")
        TemperatureDifference fromStr = new TemperatureDifference(1.5, "K");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        TemperatureDifference p1 = TemperatureDifference.valueOf("2 K");
        assertEquals(2.0, p1.si(), 1E-12);

        TemperatureDifference p2 = TemperatureDifference.of(500.0, "K");
        assertEquals(500.0, p2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiateSi(-10.1).si(), 1E-12);

        // siUnit textual representation must match Temperature.Unit.SI_UNIT ("K")
        assertEquals("K", fromStr.siUnit().toString(true, false));

        // ofSi
        TemperatureDifference neg = TemperatureDifference.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> TemperatureDifference.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> TemperatureDifference.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> TemperatureDifference.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser underneath will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> TemperatureDifference.valueOf("not-a-number K"));

        // of null unit
        assertThrows(NullPointerException.class, () -> TemperatureDifference.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> TemperatureDifference.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> TemperatureDifference.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations and add(Temperature).
     */
    @Test
    void testTemperatureDifferenceOperations()
    {
        // ΔT / ΔT -> Dimensionless
        var d1 = TemperatureDifference.ONE.divide(TemperatureDifference.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = TemperatureDifference.ofSi(1.0).divide(TemperatureDifference.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = TemperatureDifference.ofSi(1.0).divide(TemperatureDifference.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // add(Temperature): absolute + relative -> absolute
        // Create an absolute temperature T_abs = 300 K
        Temperature tAbs = Temperature.ofSi(300.0);
        TemperatureDifference delta = TemperatureDifference.ofSi(5.0); // +5 K

        // tAbs + delta, but using delta.add(tAbs) per method signature
        Temperature tNew = delta.add(tAbs);
        assertEquals(305.0, tNew.si(), 1E-12);

        // The display unit of the result is set to the display unit of the difference
        assertEquals(delta.getDisplayUnit(), tNew.getDisplayUnit());

        // Negative delta
        Temperature tNew2 = TemperatureDifference.ofSi(-10.0).add(tAbs);
        assertEquals(290.0, tNew2.si(), 1E-12);
    }

    /**
     * Unit resolution sanity (relies on Temperature.Unit registration).
     */
    @Test
    void testUnitResolution()
    {
        // "K" should resolve for a temperature difference (backed by Temperature.Unit)
        assertEquals(Temperature.Unit.SI, Units.resolve(Temperature.Unit.class, "K"));
    }
}
