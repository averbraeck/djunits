package org.djunits.quantity.def;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Area;
import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the abstract {@link Quantity} API using {@link Length} as a representative concrete type.
 * <p>
 * <strong>Goals and coverage:</strong>
 * <ul>
 * <li>Display unit handling and SI value invariants</li>
 * <li>Formatting and stringification variants</li>
 * <li>Parsing helpers: {@link Quantity#valueOf(String, Quantity)} and {@link Quantity#of(double, String, Quantity)}</li>
 * <li>Comparators and zero-comparison helpers</li>
 * <li>Static helpers: {@link Quantity#interpolate(Quantity, Quantity, double)}, {@link Quantity#max(Quantity, Quantity[])},
 * {@link Quantity#min(Quantity, Quantity[])}, {@link Quantity#sum(Quantity, Quantity[])},
 * {@link Quantity#mean(Quantity, Quantity[])}</li>
 * <li>Arithmetic producing {@link SIQuantity}: {@link Quantity#multiply(Quantity)}, {@link Quantity#divide(Quantity)},
 * {@link Quantity#reciprocal()}</li>
 * <li>Conversion to a known quantity via {@link Quantity#as(org.djunits.unit.UnitInterface)}</li>
 * <li>{@code equals}/{@code hashCode} contract</li>
 * <li>Special branch in {@link Quantity#valueOf(String, Quantity)} for {@link Unitless} (empty unit string accepted), exercised
 * using {@link Dimensionless}</li>
 * </ul>
 * <p>
 * <strong>Deliberately out of scope:</strong> We do not re-test domain specifics of {@link Length} or any other derived type;
 * they have their own dedicated unit tests. Here, {@code Length} merely provides a concrete vehicle for the abstract API.
 * </p>
 * <p>
 * <strong>Locale pinning:</strong> The suite pins {@code Locale.Category.FORMAT} to {@code Locale.US} to ensure deterministic
 * behavior of number formatting and parsing; the original locale is restored afterwards.
 * </p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class QuantityTest
{
    /**
     * Saved default locale (FORMAT category) to restore after the test run.
     */
    private static Locale savedLocale;

    /**
     * Pin the default {@link Locale} for predictable formatting/parsing behavior.
     */
    @BeforeAll
    static void pinLocale()
    {
        savedLocale = Locale.getDefault(Locale.Category.FORMAT);
        Locale.setDefault(Locale.US);
    }

    /**
     * Restore the default {@link Locale} for the FORMAT category.
     */
    @AfterAll
    static void restoreLocale()
    {
        Locale.setDefault(Locale.Category.FORMAT, savedLocale);
    }

    /**
     * Helper to construct a {@link Length} in meters.
     * @param value the SI value in meters
     * @return a {@link Length} with meter as display unit
     */
    private static Length m(final double value)
    {
        return new Length(value, Length.Unit.m);
    }

    /**
     * Helper to compare doubles with a tight but safe tolerance, intended for SI comparisons.
     * @param expected expected SI value
     * @param actual actual SI value
     */
    private static void assertClose(final double expected, final double actual)
    {
        assertEquals(expected, actual, 1e-12);
    }

    // ----------------------------------------------------------------------
    // Construction, display unit, and SI invariants
    // ----------------------------------------------------------------------

    /**
     * Verifies that the {@link Quantity} constructor rejects a {@code null} display unit.
     * <p>
     * <strong>Expected:</strong> {@link NullPointerException}.
     */
    @Test
    void constructorNullDisplayUnitThrows()
    {
        assertThrows(NullPointerException.class, () -> new Length(1.0, (Length.Unit) null));
    }

    /**
     * Verifies that {@link Quantity#getDisplayUnit()} and {@link Quantity#setDisplayUnit(org.djunits.unit.UnitInterface)}
     * round-trip correctly and that the setter is fluent (returns {@code this}).
     */
    @Test
    void displayUnitAccessors()
    {
        Length l = m(12).setDisplayUnit(Length.Unit.km);
        assertSame(Length.Unit.km, l.getDisplayUnit());
        Length same = l.setDisplayUnit(Length.Unit.cm);
        assertSame(l, same);
        assertSame(Length.Unit.cm, l.getDisplayUnit());
    }

    /**
     * Ensures that the SI value returned by {@link Quantity#si()} is invariant under changes to the display unit.
     */
    @Test
    void siValueInvariant()
    {
        Length l = new Length(1.5, Length.Unit.km); // 1500 m
        assertClose(1500.0, l.si());
        l.setDisplayUnit(Length.Unit.m);
        assertClose(1500.0, l.si());
    }

    // ----------------------------------------------------------------------
    // In-unit retrieval
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link Quantity#getInUnit()} using the current display unit.
     * <p>
     * <strong>Case:</strong> switch from meters to kilometers and check numeric conversion.
     */
    @Test
    void getInUnitCurrentDisplay()
    {
        Length l = new Length(2500.0, Length.Unit.m);
        assertClose(2500.0, l.getInUnit());
        l.setDisplayUnit(Length.Unit.km);
        assertClose(2.5, l.getInUnit());
    }

    /**
     * Verifies {@link Quantity#getInUnit(org.djunits.unit.UnitInterface)} for explicit target units.
     */
    @Test
    void getInUnitExplicitTarget()
    {
        Length l = m(123.0);
        assertClose(0.123, l.getInUnit(Length.Unit.km));
        assertClose(123000.0, l.getInUnit(Length.Unit.mm));
    }

    // ----------------------------------------------------------------------
    // Names, SI unit, Number overrides
    // ----------------------------------------------------------------------

    /**
     * Confirms that the pretty/localized name as returned by {@link Quantity#getName()} is non-empty.
     */
    @Test
    void getNameIsNonEmpty()
    {
        assertFalse(m(1).getName().isEmpty(), "Pretty name should be non-empty");
    }

    /**
     * Ensures that {@link Quantity#siUnit()} returns the correct SI unit for the quantity family.
     */
    @Test
    void siUnitMatches()
    {
        assertEquals(Length.Unit.SI_UNIT, m(1).siUnit());
    }

    /**
     * Verifies that {@link Number}-derived conversions reflect the SI value semantics (rounding for int/long).
     */
    @Test
    void numberConversions()
    {
        Length l = new Length(2.49, Length.Unit.m);
        assertClose(2.49, l.doubleValue());
        assertEquals(2, l.intValue());
        assertEquals(2L, l.longValue());
        assertEquals(2.49f, l.floatValue(), 1e-6f);
    }

    // ----------------------------------------------------------------------
    // Comparisons and zero-comparisons
    // ----------------------------------------------------------------------

    /**
     * Verifies pairwise comparisons (lt/le/gt/ge/eq/ne) based on SI values.
     */
    @Test
    void comparisons()
    {
        Length a = m(10);
        Length b = m(20);
        assertTrue(a.lt(b));
        assertTrue(a.le(b));
        assertFalse(a.gt(b));
        assertFalse(a.ge(b));
        assertFalse(a.eq(b));
        assertTrue(a.ne(b));

        Length c = m(10);
        assertTrue(a.le(c));
        assertTrue(a.ge(c));
        assertTrue(a.eq(c));
        assertFalse(a.ne(c));
    }

    /**
     * Verifies zero-comparison helpers (lt0/le0/gt0/ge0/eq0/ne0).
     */
    @Test
    void zeroComparisons()
    {
        assertTrue(m(-1).lt0());
        assertTrue(m(0).le0());
        assertTrue(m(1).gt0());
        assertTrue(m(0).ge0());
        assertTrue(m(0).eq0());
        assertTrue(m(2).ne0());
    }

    /**
     * Verifies the {@link Comparable} contract for {@link Quantity#compareTo(Quantity)}.
     */
    @Test
    void compareToContract()
    {
        Length a = m(5);
        Length b = m(6);
        Length c = m(5);
        assertTrue(a.compareTo(b) < 0);
        assertEquals(0, a.compareTo(c));
        assertTrue(b.compareTo(a) > 0);
    }

    // ----------------------------------------------------------------------
    // Parsing helpers: valueOf / of
    // ----------------------------------------------------------------------

    /**
     * Verifies successful parsing via {@link Quantity#valueOf(String, Quantity)} with and without a space between number and
     * unit.
     */
    @Test
    void valueOfParses()
    {
        Length ex = Length.ZERO; // example
        Length a = Quantity.valueOf("12.5 m", ex);
        assertClose(12.5, a.getInUnit(Length.Unit.m));

        Length b = Quantity.valueOf("12.5m", ex);
        assertClose(12.5, b.getInUnit(Length.Unit.m));
    }

    /**
     * Verifies error handling in {@link Quantity#valueOf(String, Quantity)}.
     * <ul>
     * <li>{@code null} input or example</li>
     * <li>empty string</li>
     * <li>missing unit (non-Unitless type)</li>
     * <li>unknown unit</li>
     * </ul>
     */
    @Test
    void valueOfInvalids()
    {
        Length ex = Length.ZERO;
        assertThrows(NullPointerException.class, () -> Quantity.valueOf(null, ex));
        assertThrows(NullPointerException.class, () -> Quantity.valueOf("1.0 m", null));
        assertThrows(IllegalArgumentException.class, () -> Quantity.valueOf("", ex)); // empty text
        assertThrows(IllegalArgumentException.class, () -> Quantity.valueOf("123", ex)); // no unit (not unitless)
        assertThrows(IllegalArgumentException.class, () -> Quantity.valueOf("1.0 zz", ex)); // unknown unit
    }

    /**
     * Verifies successful parsing via {@link Quantity#of(double, String, Quantity)} and its error branches for {@code null}
     * parameters, empty unit string, and unknown unit.
     */
    @Test
    void ofParses()
    {
        Length ex = Length.ZERO;
        Length a = Quantity.of(2.0, "km", ex);
        assertClose(2000.0, a.si());
        assertThrows(NullPointerException.class, () -> Quantity.of(1.0, null, ex));
        assertThrows(NullPointerException.class, () -> Quantity.of(1.0, "m", null));
        assertThrows(IllegalArgumentException.class, () -> Quantity.of(1.0, "", ex));
        assertThrows(UnitRuntimeException.class, () -> Quantity.of(1.0, "???", ex));
    }

    /**
     * <strong>Unitless special-case coverage:</strong> When the example is {@link Dimensionless}, an empty (or whitespace-only)
     * unit token must be accepted and interpreted as {@link Unitless#BASE}.
     * <p>
     * Also verifies that a non-empty, unknown unit still fails for dimensionless parsing.
     */
    @Test
    void valueOfAcceptsEmptyUnitForDimensionless()
    {
        Dimensionless example = Dimensionless.ZERO;

        // Empty unit token after trimming -> accepted for Unitless
        Dimensionless a = Quantity.valueOf("2.5", example);
        assertEquals(2.5, a.si(), 1e-12);
        assertTrue(a.getDisplayUnit() instanceof Unitless);

        // Whitespace-only suffix -> accepted for Unitless
        Dimensionless b = Quantity.valueOf("3.75   \t", example);
        assertEquals(3.75, b.si(), 1e-12);
        assertTrue(b.getDisplayUnit() instanceof Unitless);

        // Unknown, non-empty unit -> still fails
        assertThrows(IllegalArgumentException.class, () -> Quantity.valueOf("1.0 xyz", example));
    }

    // ----------------------------------------------------------------------
    // Formatting and stringification
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link Quantity#format(double)} for the fixed-format range, E-notation range, and non-finite values.
     */
    @Test
    void formatVariants()
    {
        // %f path
        assertEquals("3.14", Quantity.format(3.140000));
        assertEquals("0.0", Quantity.format(0));
        // %E path
        String s = Quantity.format(1.23e12);
        assertTrue(s.contains("E"));
        // Non-finite values should still yield a non-empty result
        assertFalse(Quantity.format(Double.NaN).isEmpty());
        assertFalse(Quantity.format(Double.POSITIVE_INFINITY).isEmpty());
        assertFalse(Quantity.format(Double.NEGATIVE_INFINITY).isEmpty());
    }

    /**
     * Verifies {@link Quantity#toString()} variants, including verbose/type flags and unit suppression.
     */
    @Test
    void toStringVariants()
    {
        Length d = new Length(1500, Length.Unit.m);
        // default
        String def = d.toString();
        assertTrue(def.endsWith(" m"));
        // explicit unit
        assertTrue(d.toString(Length.Unit.km).endsWith(" km"));
        // verbose flag
        assertTrue(d.toString(true, true).startsWith("Rel "));
        // without unit
        assertFalse(d.toString(false, false).endsWith(" m"));
    }

    /**
     * Verifies {@link Quantity#toStringSIPrefixed()} both for an in-range SI-prefix selection and an out-of-range fallback to
     * E-notation.
     */
    @Test
    void toStringSIPrefixed()
    {
        Length small = m(0.001); // 1 mm
        assertTrue(small.toStringSIPrefixed().endsWith(" mm"));

        Length big = m(12_345); // ~12.345 km
        String pref = big.toStringSIPrefixed();
        assertTrue(pref.contains("km"));

        // Out of SI-prefix range -> E-notation with base unit
        Length huge = m(1e40);
        String hugeS = huge.toStringSIPrefixed();
        assertTrue(hugeS.contains("E"));
        assertTrue(hugeS.endsWith(" m"));
    }

    /**
     * Verifies concise textual and display strings (abbreviation correctness and spacing).
     */
    @Test
    void compactStrings()
    {
        Length l = new Length(1234, Length.Unit.m);
        assertTrue(l.toTextualString().endsWith(" m"));
        assertTrue(l.toDisplayString().endsWith(" m"));
        assertTrue(l.toTextualString(Length.Unit.km).endsWith(" km"));
        assertTrue(l.toDisplayString(Length.Unit.km).endsWith(" km"));
    }

    // ----------------------------------------------------------------------
    // Static helpers: interpolate, min/max/sum/mean
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link Quantity#interpolate(Quantity, Quantity, double)}. For:
     * <ul>
     * <li>a valid ratio in [0, 1] (result SI value and display unit of the first argument)</li>
     * <li>out-of-bounds ratio (&lt; 0 or &gt; 1) raising {@link IllegalArgumentException}</li>
     * </ul>
     */
    @Test
    void interpolateQuantity()
    {
        Length a = m(0).setDisplayUnit(Length.Unit.m);
        Length b = m(1000).setDisplayUnit(Length.Unit.m);
        Length mid = Quantity.interpolate(a, b, 0.25);
        assertClose(250.0, mid.si());
        assertSame(Length.Unit.m, mid.getDisplayUnit());

        assertThrows(IllegalArgumentException.class, () -> Quantity.interpolate(a, b, -0.1));
        assertThrows(IllegalArgumentException.class, () -> Quantity.interpolate(a, b, 1.1));
    }

    /**
     * Verifies {@link Quantity#max(Quantity, Quantity[])} and {@link Quantity#min(Quantity, Quantity[])} selection.
     */
    @Test
    void maxMin()
    {
        Length a = m(1);
        Length b = m(5);
        Length c = m(-2);
        assertSame(b, Quantity.max(a, b, c));
        assertSame(c, Quantity.min(a, b, c));
    }

    /**
     * Verifies {@link Quantity#sum(Quantity, Quantity[])} and {@link Quantity#mean(Quantity, Quantity[])} and that the display
     * unit of the first argument is preserved in the result.
     */
    @Test
    void sumMean()
    {
        Length a = m(1).setDisplayUnit(Length.Unit.cm);
        Length b = m(2);
        Length c = m(3);
        Length sum = Quantity.sum(a, b, c);
        assertClose(6.0, sum.si());
        assertSame(a.getDisplayUnit(), sum.getDisplayUnit());

        Length mean = Quantity.mean(a, b, c);
        assertClose(2.0, mean.si());
        assertSame(a.getDisplayUnit(), mean.getDisplayUnit());
    }

    // ----------------------------------------------------------------------
    // Arithmetic yielding SIQuantity
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link Quantity#multiply(Quantity)}, {@link Quantity#divide(Quantity)}, and {@link Quantity#reciprocal()}
     * produce {@link SIQuantity} with correct SI values (unit algebra correctness is validated in SIQuantity/unit tests).
     */
    @Test
    void siQuantityArithmetic()
    {
        Length a = m(3);
        Length b = m(4);

        Area prod = a.multiply(b);
        assertClose(12.0, prod.si());

        Dimensionless quot = a.divide(b);
        assertClose(0.75, quot.si());

        var inv = a.reciprocal(); // LinearObjectDensity
        assertClose(1.0 / 3.0, inv.si());
    }

    // ----------------------------------------------------------------------
    // as(TU) conversion
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link Quantity#as(org.djunits.unit.UnitInterface)} converts to a correctly typed quantity while preserving the
     * SI value and replacing the display unit with the requested unit.
     */
    @Test
    void asKnownQuantity()
    {
        Length l = m(1234.0);
        Length km = l.as(Length.Unit.km);
        assertClose(1234.0, km.si());
        assertSame(Length.Unit.km, km.getDisplayUnit());
        assertEquals(Length.class, km.getClass());
    }

    // ----------------------------------------------------------------------
    // equals / hashCode
    // ----------------------------------------------------------------------

    /**
     * Verifies {@code equals}/{@code hashCode} consider the SI value (bits) and the display unit.
     */
    @Test
    void equalsHashCode()
    {
        Length a = new Length(1, Length.Unit.km); // 1000 m
        Length b = m(1000).setDisplayUnit(Length.Unit.km);
        Length c = m(1000).setDisplayUnit(Length.Unit.m); // different display unit
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    /**
     * Tests {@link Quantity#getName()} for both the simple case (single leading uppercase, e.g. "Length" -> "Length") and the
     * camel-case/internal-uppercase case using {@code SIQuantity} (e.g. "SIQuantity" -> "S i quantity").
     * <p>
     * <strong>Why SIQuantity?</strong> The algorithm in {@link Quantity#getName()} inserts a space before each uppercase letter
     * <em>after</em> the first character and lower-cases that letter. Using {@code SIQuantity} guarantees we hit that branch:
     * 'S' (kept as-is), then 'I' (→ " i"), then 'Q' (→ " q"), then "uantity".
     * </p>
     * <p>
     * <strong>Expected:</strong>
     * <ul>
     * <li>{@code new Length(...).getName()} yields {@code "Length"} (no spaces added)</li>
     * <li>{@code new SIQuantity(...).getName()} yields {@code "S i quantity"} (spaces and lower-casing applied)</li>
     * </ul>
     */
    @Test
    void getNameFormatsPrettyForQuantityAndSIQuantity()
    {
        // Simple case: only the first character is uppercase; no spaces should be inserted.
        Length length = new Length(1.0, Length.Unit.m);
        assertEquals("Length", length.getName(), "Length should remain 'Length' without extra spaces");

        // Internal-uppercase case: "SIQuantity" -> "S i quantity"
        // Construct an SIQuantity with a length SI unit to reuse an available SIUnit.
        SIQuantity siq = new SIQuantity(1.0, Length.Unit.SI_UNIT);
        assertEquals("S i quantity", siq.getName(),
                "Camel-cased 'SIQuantity' should become 'S i quantity' (spaces before internal capitals)");
    }

}
