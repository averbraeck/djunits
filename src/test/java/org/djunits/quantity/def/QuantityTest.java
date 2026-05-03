package org.djunits.quantity.def;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.formatter.QuantityFormat;
import org.djunits.quantity.Area;
import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.Volume;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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
 * <li>Conversion to a known quantity via {@link Quantity#as(org.djunits.unit.Unit)}</li>
 * <li>{@code equals}/{@code hashCode} contract</li>
 * <li>Special branch in {@link Quantity#valueOf(String, Quantity)} for {@link Unitless} (empty unit string accepted), exercised
 * using {@link Dimensionless}</li>
 * </ul>
 * <p>
 * <strong>Deliberately out of scope:</strong> We do not re-test domain specifics of {@link Length} or any other derived type;
 * they have their own dedicated unit tests. Here, {@code Length} merely provides a concrete vehicle for the abstract API.
 * <p>
 * <strong>Locale pinning:</strong> The suite pins {@code Locale.Category.FORMAT} to {@code Locale.US} to ensure deterministic
 * behavior of number formatting and parsing; the original locale is restored afterwards. Copyright (c) 2025-2026 Delft
 * University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See for project information
 * <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is distributed under a
 * <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
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
     * Verifies that {@link Quantity#getDisplayUnit()} and {@link Quantity#setDisplayUnit(org.djunits.unit.Unit)} round-trip
     * correctly and that the setter is fluent (returns {@code this}).
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
        assertClose(1500.0, l.si);
        l.setDisplayUnit(Length.Unit.m);
        assertClose(1500.0, l.si());
        assertClose(1500.0, l.si);
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
        l.setDisplayUnit("hm");
        assertClose(25.0, l.getInUnit());
        assertTrue(l.isRelative());
        assertFalse(l.isAbsolute());

        Temperature t = new Temperature(23.0, "degC");
        assertFalse(t.isRelative());
        assertTrue(t.isAbsolute());
    }

    /**
     * Verifies {@link Quantity#getInUnit(org.djunits.unit.Unit)} for explicit target units.
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

        assertFalse(b.lt(a));
        assertFalse(b.le(a));
        assertTrue(b.gt(a));
        assertTrue(b.ge(a));
        assertTrue(a.eq(a));
        assertFalse(b.ne(b));

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

        assertFalse(m(1).lt0());
        assertFalse(m(1).le0());
        assertFalse(m(-1).gt0());
        assertFalse(m(-1).ge0());
        assertFalse(m(1).eq0());
        assertFalse(m(0).ne0());
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
        assertClose(2000.0, a.si);
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
        assertEquals(2.5, a.si, 1e-12);
        assertTrue(a.getDisplayUnit() instanceof Unitless);

        // Whitespace-only suffix -> accepted for Unitless
        Dimensionless b = Quantity.valueOf("3.75   \t", example);
        assertEquals(3.75, b.si(), 1e-12);
        assertEquals(3.75, b.si, 1e-12);
        assertTrue(b.getDisplayUnit() instanceof Unitless);

        // Unknown, non-empty unit -> still fails
        assertThrows(IllegalArgumentException.class, () -> Quantity.valueOf("1.0 xyz", example));
    }

    // ----------------------------------------------------------------------
    // Formatting and stringification
    // ----------------------------------------------------------------------

    /**
     * Test default {@code toString()} for a simple length.
     */
    @Test
    @DisplayName("Default toString() uses default number and display unit")
    public void testDefaultToString()
    {
        Length l = new Length(12.34567, Length.Unit.m);
        assertEquals("    12.346 m", l.toString());
    }

    /**
     * Test {@code toString(Unit)} converts the unit for display.
     */
    @Test
    @DisplayName("toString(Unit) converts value and displays target unit")
    public void testToStringWithTargetUnit()
    {
        Length l = new Length(1234.0, Length.Unit.m);
        assertEquals("     1.234 km", l.toString(Length.Unit.km));
    }

    /**
     * Test negative quantity formatting.
     */
    @Test
    @DisplayName("Negative quantities keep sign and formatting")
    public void testNegativeQuantity()
    {
        Duration d = new Duration(-2.5, Duration.Unit.s);
        assertEquals("    -2.500 s", d.toString());
    }

    /**
     * Test magnitude change with decimals and width.
     */
    @Test
    @DisplayName("Large magnitude with explicit number format")
    public void testLargeMagnitudeFixed()
    {
        Area a = new Area(12_345_678.9, Area.Unit.m2);
        String s1 = a.toString(QuantityFormat.defaults().fixedFloat().setDecimals(1).setWidth(12).setGroupingSeparator(true));
        assertEquals("12,345,678.9 m2", s1);
        String s2 = a.toString(QuantityFormat.defaults().fixedFloat().setDecimals(1).setWidth(12).setGroupingSeparator(false));
        assertEquals("  12345678.9 m2", s2);
        String s3 = a.toString(QuantityFormat.defaults().fixedFloat().setDecimals(2).setWidth(12).setGroupingSeparator(false));
        assertEquals(" 12345678.90 m2", s3);
    }

    /**
     * Test multiple format settings combined.
     */
    @Test
    @DisplayName("Combined settings for number, unit and quantity")
    public void testCombinedFormat()
    {
        Length l = new Length(20400.0, Length.Unit.m);
        String s = l.toString(QuantityFormat.defaults().scaleSiPrefixes().setDecimals(3).textual());
        assertEquals("    20.400 km", s);
    }

    /**
     * Test locale influence on decimal separator.
     */
    @Test
    @DisplayName("Locale format affects decimal separator")
    public void testLocaleFormat()
    {
        Length l = new Length(1.5, Length.Unit.m);
        String s = l.toString(QuantityFormat.defaults().setLocale(Locale.GERMANY));
        assertEquals("     1,500 m", s);
    }

    // ----------------------------------------------------------------------
    // Static helpers: interpolate, min/max/sum/mean/etc.
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
        assertClose(250.0, mid.si);
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
        assertClose(6.0, sum.si);
        assertSame(a.getDisplayUnit(), sum.getDisplayUnit());

        Length mean = Quantity.mean(a, b, c);
        assertClose(2.0, mean.si());
        assertClose(2.0, mean.si);
        assertSame(a.getDisplayUnit(), mean.getDisplayUnit());
    }

    /**
     * Verifies abs and negate.
     */
    @Test
    void absNegate()
    {
        Length a = m(-1).setDisplayUnit(Length.Unit.cm);
        Length aa = a.abs();
        assertClose(1.0, aa.si());
        assertClose(1.0, aa.si);
        assertSame(a.getDisplayUnit(), aa.getDisplayUnit());

        Length one = m(1).setDisplayUnit(Length.Unit.cm);
        Length none = one.negate();
        Length na = a.negate();
        assertClose(1.0, na.si());
        assertClose(1.0, na.si);
        assertClose(-1.0, none.si());
        assertClose(-1.0, none.si);
        assertSame(one.getDisplayUnit(), none.getDisplayUnit());
        assertSame(a.getDisplayUnit(), na.getDisplayUnit());
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
        assertClose(12.0, prod.si);

        SIQuantity prod2 = Quantity.product(a, b);
        assertClose(12.0, prod2.si());
        assertClose(12.0, prod2.si);
        assertEquals("m2", prod2.siUnit().toString(true, false));

        SIQuantity prod3 = Quantity.product(a, b, a);
        assertClose(36.0, prod3.si());
        assertClose(36.0, prod3.si);
        assertEquals("m3", prod3.siUnit().toString(true, false));
        Volume v3 = prod3.as(Volume.Unit.m3);
        assertEquals(36.0, v3.si());
        assertEquals(36.0, v3.si);
        assertThrows(IllegalArgumentException.class, () -> prod3.as(Area.Unit.m2));

        Dimensionless quot = a.divide(b);
        assertClose(0.75, quot.si());
        assertClose(0.75, quot.si);

        var inv = a.reciprocal(); // LinearObjectDensity
        assertClose(1.0 / 3.0, inv.si());
        assertClose(1.0 / 3.0, inv.si);
    }

    // ----------------------------------------------------------------------
    // as(TU) conversion
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link Quantity#as(org.djunits.unit.Unit)} converts to a correctly typed quantity while preserving the SI value
     * and replacing the display unit with the requested unit.
     */
    @Test
    void asKnownQuantity()
    {
        Length l = m(1234.0);
        Length km = l.as(Length.Unit.km);
        assertClose(1234.0, km.si());
        assertClose(1234.0, km.si);
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
        assertEquals(a, a);
        assertNotEquals(a, null);
        assertNotEquals(a, "");
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

        // AbsQuantity name with capitals
        var q = new ExampleQuantityAQxyz(3.0, ExampleQuantityAQxyz.Unit.DEFAULT);
        assertEquals("Example quantity a qxyz", q.getName(), "Quantity name: camel case should result in spaces");
        assertEquals("m", q.siUnit().toString(true, false));
        Units.unregister(ExampleQuantityAQxyz.Unit.DEFAULT);

        // Internal-uppercase case: "SIQuantity" -> "S i quantity"
        // Construct an SIQuantity with a length SI unit to reuse an available SIUnit.
        SIQuantity siq = new SIQuantity(1.0, Length.Unit.SI_UNIT);
        assertEquals("S i quantity", siq.getName(),
                "Camel-cased 'SIQuantity' should become 'S i quantity' (spaces before internal capitals)");
    }

    /**
     * Quantity class for test.
     */
    static class ExampleQuantityAQxyz extends Quantity<ExampleQuantityAQxyz>
    {
        /** */
        private static final long serialVersionUID = 1L;

        /**
         * @param value v
         * @param displayUnit du
         */
        ExampleQuantityAQxyz(final double value, final Unit displayUnit)
        {
            super(value, displayUnit);
        }

        @Override
        public ExampleQuantityAQxyz instantiateSi(final double siValue)
        {
            return null;
        }

        /** The unit for ExampleQuantityAQxyz. */
        static class Unit extends AbstractUnit<Unit, ExampleQuantityAQxyz>
        {
            /** */
            static final Unit DEFAULT = new Unit("u", "u", IdentityScale.SCALE, UnitSystem.OTHER);

            /**
             * @param textualAbbreviation x
             * @param name x
             * @param scale x
             * @param unitSystem x
             */
            Unit(final String textualAbbreviation, final String name, final Scale scale, final UnitSystem unitSystem)
            {
                super(textualAbbreviation, name, scale, unitSystem);
            }

            @Override
            public SIUnit siUnit()
            {
                return SIUnit.of("m");
            }

            @Override
            public Unit getBaseUnit()
            {
                return null;
            }

            @Override
            public ExampleQuantityAQxyz ofSi(final double si)
            {
                return null;
            }

            @Override
            public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                    final double scaleFactor, final UnitSystem unitSystem)
            {
                return null;
            }
        }
    }

}
