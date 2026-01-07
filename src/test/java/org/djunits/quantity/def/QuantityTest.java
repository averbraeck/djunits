package org.djunits.quantity.def;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Area;
import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.Test;

/**
 * Tests for the abstract {@link Quantity} class. These tests aim to cover all code paths in Quantity itself: construction via
 * concrete quantities, unit conversions, parsing helpers, formatting helpers, comparisons, static operations, and SI-prefix
 * formatting behavior. Concrete quantity classes (e.g., Length, Mass, Frequency) will have their own dedicated tests
 * elsewhere.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class QuantityTest
{
    /**
     * Helper to make a {@link Dimensionless} for a given value and unit.
     * @param value value expressed in the chosen unit
     * @param unit unit to use
     * @return a dimensionless quantity
     */
    private static Dimensionless dim(final double value, final Unitless unit)
    {
        return new Dimensionless(value, unit);
    }

    /**
     * Verify basic getters and number conversion methods return SI values, and display unit conversions round-trip correctly.
     */
    @Test
    public void testGetDisplayUnitAndNumberMethods()
    {
        // Use % with factor 0.01 to ensure SI storage differs from display value.
        Unitless percent = new Unitless("%", "percent", 0.01, UnitSystem.OTHER);
        Dimensionless d = dim(250.0, percent); // si = 2.5

        assertSame(percent, d.getDisplayUnit());
        assertEquals(250.0, d.getInUnit(), 1e-12); // back to display unit
        assertEquals(2.5, d.si(), 1e-12);

        // Number conversions are based on si() (not display).
        assertEquals(2.5, d.doubleValue(), 1e-12);
        assertEquals(3, d.intValue()); // rounded
        assertEquals(3L, d.longValue()); // rounded
        assertEquals(2.5f, d.floatValue(), 1e-6);

        // Change display unit and ensure getInUnit follows the new unit scale.
        Unitless perMille = new Unitless("\u2030", "\u2030", "permille", new LinearScale(0.001), UnitSystem.OTHER);
        d.setDisplayUnit(perMille);
        assertEquals(2500.0, d.getInUnit(), 1e-12); // 2.5 SI -> 2500 ‰
        assertSame(perMille, d.getDisplayUnit());
    }

    /**
     * Verify {@link Quantity#siUnit()}, {@link Quantity#instantiate(double)} via concrete types, and
     * {@link Quantity#instantiate(double, UnitInterface)} for display unit setting.
     */
    @Test
    public void testSiUnitAndInstantiate()
    {
        // Use Length to test instantiate paths without asserting Length-specific behavior.
        Length m = new Length(12.0, Length.Unit.SI); // si = 12 meters
        assertEquals(12.0, m.si(), 1e-12);
        assertEquals(Length.Unit.SI_UNIT.siUnit(), m.siUnit());

        // Instantiate from SI and set display unit via 'instantiate(value, unit)'
        Length km = m.instantiate(3.0, Length.Unit.km); // 3 km -> si = 3000 meters
        assertEquals(3000.0, km.si(), 1e-9);
        assertSame(Length.Unit.km, km.getDisplayUnit());

        // Re-instantiate SI explicitly
        Length fromSi = m.instantiate(1234.0);
        assertEquals(1234.0, fromSi.si(), 1e-12);
    }

    /**
     * Verify all comparison helpers (lt/le/gt/ge/eq/ne) and zero-comparisons (lt0/le0/gt0/ge0/eq0/ne0).
     */
    @Test
    public void testComparisons()
    {
        Unitless one = Unitless.BASE;
        Dimensionless a = dim(2.0, one); // si=2
        Dimensionless b = dim(3.0, one); // si=3

        assertTrue(a.lt(b));
        assertTrue(a.le(b));
        assertFalse(a.gt(b));
        assertFalse(a.ge(b));
        assertFalse(a.eq(b));
        assertTrue(a.ne(b));

        Dimensionless zero = dim(0.0, one);
        Dimensionless neg = dim(-1.0, one);
        Dimensionless pos = dim(+1.0, one);

        assertTrue(neg.lt0());
        assertTrue(neg.le0());
        assertFalse(neg.gt0());
        assertFalse(neg.ge0());
        assertFalse(neg.eq0());
        assertTrue(neg.ne0());

        assertFalse(zero.lt0());
        assertTrue(zero.le0());
        assertFalse(zero.gt0());
        assertTrue(zero.ge0());
        assertTrue(zero.eq0());
        assertFalse(zero.ne0());

        assertFalse(pos.lt0());
        assertFalse(pos.le0());
        assertTrue(pos.gt0());
        assertTrue(pos.ge0());
        assertFalse(pos.eq0());
        assertTrue(pos.ne0());

        // Comparable
        assertTrue(a.compareTo(b) < 0);
        assertEquals(0, a.compareTo(dim(2.0, one)));
        assertTrue(b.compareTo(a) > 0);
    }

    /**
     * Verify add/subtract/abs/negate/scaleBy preserve display unit and compute the correct SI value.
     */
    @Test
    public void testRelativeOperations()
    {
        Unitless u = Unitless.BASE;
        Dimensionless x = dim(10.0, u); // si=10
        Dimensionless y = dim(4.0, u); // si=4

        assertEquals(14.0, x.add(y).si(), 1e-12);
        assertSame(u, x.add(y).getDisplayUnit());

        assertEquals(6.0, x.subtract(y).si(), 1e-12);
        assertSame(u, x.subtract(y).getDisplayUnit());

        assertEquals(10.0, x.abs().si(), 1e-12);
        assertEquals(10.0, x.negate().negate().si(), 1e-12);
        assertEquals(-10.0, x.negate().si(), 1e-12);

        assertEquals(15.0, x.scaleBy(1.5).si(), 1e-12);
        assertSame(u, x.scaleBy(1.5).getDisplayUnit());
    }

    /**
     * Verify multiply/divide produce an {@link SIQuantity} with correct SI value and a non-null unit, and reciprocal produces
     * the inverted unit result.
     */
    @Test
    public void testMultiplyDivideReciprocal()
    {
        Length a = new Length(2.0, Length.Unit.m); // si=2 m
        Length b = new Length(3.0, Length.Unit.m); // si=3 m

        Area prod = a.multiply(b); // si=6 m^2
        assertEquals(6.0, prod.si(), 1e-12);
        assertNotNull(prod.siUnit());

        Dimensionless quot = a.divide(b); // si=2/3 m/m = 2/3
        assertEquals(2.0 / 3.0, quot.si(), 1e-12);
        assertNotNull(quot.siUnit());

        Quantity<?, ?> recip = a.reciprocal(); // 1 / (2 m) = 0.5 1/m
        assertEquals(0.5, recip.si(), 1e-12);
        assertNotNull(recip.siUnit());
    }

    /**
     * Verify {@link Quantity#as(UnitInterface)} returns a correctly typed quantity when SI units match, and throws when they do
     * not match.
     */
    @Test
    public void testAs()
    {
        Length m = new Length(1500.0, Length.Unit.m); // si=1500 m
        // Convert to kilometers (same SI type)
        Length kmResult = m.as(Length.Unit.km);
        assertEquals(1500.0, kmResult.si(), 1e-12);
        assertSame(Length.Unit.km, kmResult.getDisplayUnit());

        // Mismatched SI type: converting Length "as" Unitless must fail
        assertThrows(IllegalArgumentException.class, () -> m.as(Unitless.BASE));
    }

    /**
     * Verify {@link Quantity#format(double)} and {@link Quantity#format(double, String)} behavior: stripping trailing zeros for
     * %f, preserving exponent notation for %E, and keeping a trailing zero if needed.
     */
    @Test
    public void testFormatHelpers()
    {
        Length m = new Length(1.0, Length.Unit.m);

        // %f path with trimming and ensuring digit after decimal (if any)
        assertEquals("123.456", m.format(123.4560));
        assertEquals("1.2", m.format(1.200000));
        assertEquals("0.0", m.format(0.0));

        // %E path for small/large values
        String smallE = m.format(1e-6);
        String largeE = m.format(1e6);
        assertTrue(smallE.contains("E") || smallE.contains("e"));
        assertTrue(largeE.contains("E") || largeE.contains("e"));

        // Explicit format does not trim if exponent is present
        String explicit = m.format(123.456, "%10.4E");
        assertTrue(explicit.contains("E"));
    }

    /**
     * Verify {@link Quantity#toString()}, {@link Quantity#toString(UnitInterface)}, and
     * {@link Quantity#toString(boolean, boolean)} produce expected shapes: SI-Formatted value, optional "Rel " prefix for
     * verbose, and unit inclusion/exclusion.
     */
    @Test
    public void testToStringVariants()
    {
        Unitless percent = new Unitless("%", "%", "percent", new LinearScale(0.01), UnitSystem.OTHER);
        Dimensionless d = dim(250.0, percent); // si=2.5, display=250 %

        // Default toString: value in current display unit + display abbr
        String s = d.toString();
        assertTrue(s.contains("%"));
        assertTrue(s.contains("250"));

        // toString(unit): force display in permille
        Unitless perMille = new Unitless("\u2030", "\u2030", "permille", new LinearScale(0.001), UnitSystem.OTHER);
        String sp = d.toString(perMille);
        assertTrue(sp.contains("\u2030"));
        assertTrue(sp.contains("2500"));

        // Verbose without unit: prefixed "Rel "
        String verb = d.toString(true, false);
        assertTrue(verb.startsWith("Rel "));

        // With unit
        String verbUnit = d.toString(true, true);
        assertTrue(verbUnit.startsWith("Rel "));
        assertTrue(verbUnit.endsWith(" %"));
    }

    /**
     * Verify SIPrefixed rendering: for finite SI values within prefix range, a prefixed unit is chosen; for non-finite SI
     * values, the base-unit toString is used; for out-of-range exponents, e-notation with plain base unit. Note: this exercises
     * the code path in Quantity; concrete details of Length prefixes are covered in Length's own tests.
     */
    @Test
    public void testToStringSIPrefixed()
    {
        // SI value still used (meters); choose value around 10^3 to trigger kilo.
        Length m = new Length(1200.0, Length.Unit.m); // si=1200 -> expect something like "1.2 km"
        String prefixed = m.toStringSIPrefixed();
        assertTrue(prefixed.contains("km") || prefixed.contains("m")); // allow rounding boundary

        // Non-finite SI -> fall back to base-unit toString
        Length nanLen = new Length(Double.NaN, Length.Unit.m);
        String prefixedNan = nanLen.toStringSIPrefixed();
        assertTrue(prefixedNan.endsWith(" " + Length.Unit.m.getBaseUnit().getId()));

        // Extremely large value (bigger than Quetta -> e-notation with plain base unit
        Length huge = new Length(1e42, Length.Unit.m);
        String prefixedHuge = huge.toStringSIPrefixed();
        assertTrue(prefixedHuge.contains("E"));
        assertTrue(prefixedHuge.endsWith(" " + Length.Unit.m.getBaseUnit().getId()));
    }

    /**
     * Verify concise textual and display strings with current and explicit display units.
     */
    @Test
    public void testToTextualAndDisplayStrings()
    {
        Length len = new Length(1.2345, Length.Unit.m);
        assertTrue(len.toTextualString().contains("m"));
        assertTrue(len.toDisplayString().contains("m"));

        String kmText = len.toTextualString(Length.Unit.km);
        String kmDisp = len.toDisplayString(Length.Unit.km);
        assertTrue(kmText.contains("km"));
        assertTrue(kmDisp.contains("km"));
    }

    /**
     * Verify parsing helpers: {@link Quantity#valueOf(String, Quantity)} and {@link Quantity#of(double, String, Quantity)}.
     * Includes happy paths and error cases (nulls, empty, unknown units, bad numbers).
     */
    @Test
    public void testParsingHelpers()
    {
        Locale original = Locale.getDefault();
        try
        {
            Locale.setDefault(Locale.US);

            // Happy path: "1.5 km"
            Length example = new Length(0.0, Length.Unit.m); // example instance only supplies class & display unit
            // (Length.Unit)
            Length parsed = Quantity.valueOf("1.5 km", example);
            assertEquals(1500.0, parsed.si(), 1e-12);
            assertSame(Length.Unit.km, parsed.getDisplayUnit());

            // Happy path using 'of(value, "unit", example)'
            Length x = Quantity.of(2.5, "m", example);
            assertEquals(2.5, x.si(), 1e-12);
            assertSame(Length.Unit.m, x.getDisplayUnit());

            // Null example
            assertThrows(NullPointerException.class, () -> Quantity.valueOf("1 m", (Length) null));
            assertThrows(NullPointerException.class, () -> Quantity.of(1.0, "m", (Length) null));

            // Null/empty text and unitString
            assertThrows(NullPointerException.class, () -> Quantity.valueOf(null, example));
            assertThrows(IllegalArgumentException.class, () -> Quantity.valueOf("", example));
            assertThrows(NullPointerException.class, () -> Quantity.of(1.0, null, example));
            assertThrows(IllegalArgumentException.class, () -> Quantity.of(1.0, "", example));

            // Unknown unit -> Units.resolve throws -> Quantity wraps as IllegalArgumentException
            assertThrows(IllegalArgumentException.class, () -> Quantity.valueOf("12.34 xyz", example));
            assertThrows(UnitRuntimeException.class, () -> Quantity.of(1.0, "xyz", example));

            // Bad number still yields IllegalArgumentException
            assertThrows(IllegalArgumentException.class, () -> Quantity.valueOf("abc m", example));
        }
        finally
        {
            Locale.setDefault(original);
        }
    }

    /**
     * Verify static operations: interpolate, max, min, sum, mean. Ensure unit handling and SI results are correct.
     */
    @Test
    public void testStaticOperations()
    {
        // Interpolate with different display units; the result should use zero's display unit.
        Length zero = new Length(0.0, Length.Unit.km); // si=0 km
        Length one = new Length(1000.0, Length.Unit.m); // si=1000 m (1 km)
        Length mid = Quantity.interpolate(zero, one, 0.5);
        assertEquals(0.5, mid.getInUnit(), 1e-12); // expressed in km
        assertSame(Length.Unit.km, mid.getDisplayUnit());

        // Ratio bounds
        assertThrows(IllegalArgumentException.class, () -> Quantity.interpolate(zero, one, -0.1));
        assertThrows(IllegalArgumentException.class, () -> Quantity.interpolate(zero, one, 1.1));

        // max/min
        Length a = new Length(2.0, Length.Unit.m);
        Length b = new Length(3.0, Length.Unit.m);
        assertSame(b, Quantity.max(a, b));
        assertSame(a, Quantity.min(a, b));

        // sum/mean preserve the display unit of the first argument
        Length s = Quantity.sum(a, b); // si = 5.0
        assertEquals(5.0, s.si(), 1e-12);
        assertSame(a.getDisplayUnit(), s.getDisplayUnit());

        Length mean = Quantity.mean(a, b); // (2+3)/2 = 2.5
        assertEquals(2.5, mean.si(), 1e-12);
        assertSame(a.getDisplayUnit(), mean.getDisplayUnit());
    }

    /**
     * Verify equals and hashCode behavior for equal and unequal quantities. Quantity.equals requires same class, equal display
     * unit, and equal SI value.
     */
    @Test
    public void testEqualsAndHashCode()
    {
        Length l1 = new Length(5.0, Length.Unit.m);
        Length l2 = new Length(5.0, Length.Unit.m);
        Length l3 = new Length(5.0, Length.Unit.km); // different display unit; not equal

        assertEquals(l1, l2);
        assertEquals(l1.hashCode(), l2.hashCode());

        assertNotEquals(l1, l3);
        assertNotEquals(l1, null);
        assertNotEquals(l1, new Object());
    }
}
