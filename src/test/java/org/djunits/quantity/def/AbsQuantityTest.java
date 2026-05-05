package org.djunits.quantity.def;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.formatter.QuantityFormat;
import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.djunits.quantity.Length;
import org.djunits.quantity.Position;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the abstract {@link AbsQuantity} API using {@link Position} and its nested
 * {@link org.djunits.quantity.Position.Reference}.
 * <p>
 * <strong>Goals and coverage:</strong>
 * <ul>
 * <li>Reference-aware comparisons and {@link Comparable} rules</li>
 * <li>Reference transformations via {@link AbsQuantity#relativeTo(Reference)}</li>
 * <li>Parsing helpers: {@link AbsQuantity#valueOf(String, AbsQuantity, Reference)} and
 * {@link AbsQuantity#of(double, String, AbsQuantity, Reference)}</li>
 * <li>Stringification and SI-prefix formatting</li>
 * <li>Static helpers: {@link AbsQuantity#interpolate(AbsQuantity, AbsQuantity, double)},
 * {@link AbsQuantity#max(AbsQuantity, AbsQuantity[])}, {@link AbsQuantity#min(AbsQuantity, AbsQuantity[])},
 * {@link AbsQuantity#sum(AbsQuantity, AbsQuantity[])}, {@link AbsQuantity#mean(AbsQuantity, AbsQuantity[])}</li>
 * <li>Arithmetic with relative quantities: {@link AbsQuantity#add(Quantity)} and {@link AbsQuantity#subtract(Quantity)} and
 * {@link AbsQuantity#subtract(AbsQuantity)}</li>
 * <li>{@code equals}/{@code hashCode} contract</li>
 * </ul>
 * <p>
 * <strong>Deliberately out of scope:</strong> We do not re-test domain specifics of {@link Position}/{@link Length}. Those are
 * covered by their dedicated tests; here, {@code Position} provides a concrete vehicle to exercise the abstract API.
 * <p>
 * <strong>Locale pinning:</strong> The suite pins {@code Locale.Category.FORMAT} to {@code Locale.US} for deterministic
 * formatting/parsing behavior; the original locale is restored afterwards.
 * <p>
 * <strong>Reference registry hygiene:</strong> Each created {@link org.djunits.quantity.Position.Reference} is
 * {@link Reference#unregister() unregistered} in {@link #cleanup()} to avoid cross-test pollution of the static per-class
 * registry. Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights
 * reserved. See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS
 * project is distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style
 * license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class AbsQuantityTest
{
    /** Saved default locale (FORMAT category) to restore after the test run. */
    private static Locale savedLocale;

    /** A base/reference origin "A" (root; offset 0). */
    private Position.Reference refA;

    /** A reference "B" that is offset by +2 m relative to {@link #refA}. */
    private Position.Reference refB;

    /** A reference "C" that is offset by +1 m relative to {@link #refB} (thus +3 m relative to {@link #refA}). */
    private Position.Reference refC;

    /** An unrelated reference "X" serving as a distinct root (no path to {@link #refA}/{@link #refB}/{@link #refC}). */
    private Position.Reference refX;

    /** An unrelated reference "Y" serving as a distinct root (no DIRECT path to {@link #refA}/{@link #refB}/{@link #refC}). */
    private Position.Reference refY;

    /** An unrelated reference "Z" serving as a distinct root (no path to {@link #refA}/{@link #refB}/{@link #refC}). */
    private Position.Reference refZ;

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
     * Establishes a small reference graph for tests.
     * <ul>
     * <li>{@code A}: root</li>
     * <li>{@code B}: A + 2 m</li>
     * <li>{@code C}: B + 1 m (i.e., A + 3 m)</li>
     * <li>{@code X}: unrelated root</li>
     * <li>{@code Y}: related indirect root</li>
     * <li>{@code Z}: unrelated indirect root</li>
     * </ul>
     */
    @BeforeEach
    void setup()
    {
        this.refA = new Position.Reference("A", "origin A");
        this.refB = new Position.Reference("B", "origin B", Length.ofSi(2), this.refA);
        this.refC = new Position.Reference("C", "origin C", Length.ofSi(3), this.refA);
        this.refX = new Position.Reference("X", "origin X");
        this.refY = new Position.Reference("Y", "origin Y", Length.ofSi(1), this.refC);
        this.refZ = new Position.Reference("Z", "origin Z", Length.ofSi(1), this.refX);
    }

    /**
     * Unregisters created references from the per-class registry to avoid cross-test pollution.
     */
    @AfterEach
    void cleanup()
    {
        this.refC.unregister();
        this.refB.unregister();
        this.refA.unregister();
        this.refX.unregister();
        this.refY.unregister();
        this.refZ.unregister();
    }

    /**
     * Convenience factory to create a {@link Position} with a given SI value and reference, with a specific display unit.
     * @param si the SI value (meters)
     * @param ref the {@link org.djunits.quantity.Position.Reference} to bind to
     * @param unit the display unit to set on the inner {@link Length}
     * @return the constructed {@link Position}
     */
    private static Position pos(final double si, final Position.Reference ref, final Length.Unit unit)
    {
        return new Position(si, unit, ref);
    }

    // ----------------------------------------------------------------------
    // Construction and unit plumbing
    // ----------------------------------------------------------------------

    /**
     * Verifies that the constructor of {@link Position} (as a representative of {@link AbsQuantity}) rejects {@code null} for
     * either the relative quantity or the reference.
     */
    @Test
    void constructorNulls()
    {
        assertThrows(NullPointerException.class, () -> new Position(null, this.refA));
        assertThrows(NullPointerException.class, () -> new Position(Length.ofSi(0), null));
    }

    /**
     * Verifies that {@link AbsQuantity#getDisplayUnit()} and {@link AbsQuantity#setDisplayUnit(org.djunits.unit.Unit)} delegate
     * correctly to the inner relative quantity and that the setter is fluent.
     */
    @Test
    void displayUnitAccessors()
    {
        Position p = pos(5, this.refA, Length.Unit.m);
        assertSame(Length.Unit.m, p.getDisplayUnit());
        Position same = p.setDisplayUnit(Length.Unit.km);
        assertSame(p, same);
        assertSame(Length.Unit.km, p.getDisplayUnit());
    }

    /**
     * Verifies the accessors {@link AbsQuantity#getQuantity()} and {@link AbsQuantity#getReference()}.
     */
    @Test
    void quantityAndReferenceAccessors()
    {
        Position p = pos(42, this.refB, Length.Unit.m);
        assertEquals(42.0, p.getQuantity().si(), 1e-12);
        assertSame(this.refB, p.getReference());
    }

    // ----------------------------------------------------------------------
    // In-unit retrieval and Number overrides
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link AbsQuantity#getInUnit()} and {@link AbsQuantity#getInUnit(org.djunits.unit.Unit)}.
     */
    @Test
    void getInUnitVariants()
    {
        Position p = pos(1500, this.refA, Length.Unit.m);
        assertEquals(1500.0, p.getInUnit(), 1e-12);
        assertEquals(1.5, p.getInUnit(Length.Unit.km), 1e-12);
        var psiu = pos(1.234, this.refA, Length.Unit.km).siUnit();
        assertEquals("m", psiu.toString());
    }

    // ----------------------------------------------------------------------
    // Comparisons and zero helpers (with reference equality checks)
    // ----------------------------------------------------------------------

    /**
     * Verifies that comparisons (lt/le/gt/ge) and {@link Comparable#compareTo(Object)} require identical references, otherwise
     * an {@link IllegalArgumentException} is thrown.
     * <p>
     * Also verifies eq/ne logic (which includes reference equality for absolute quantities).
     */
    @Test
    void comparisonsAndReferenceConstraints()
    {
        Position aA = pos(1, this.refA, Length.Unit.m);
        Position bA = pos(3, this.refA, Length.Unit.m);

        assertTrue(aA.lt(bA));
        assertTrue(aA.le(bA));
        assertFalse(aA.gt(bA));
        assertFalse(aA.ge(bA));
        assertFalse(aA.eq(bA));
        assertTrue(aA.ne(bA));

        assertFalse(bA.lt(aA));
        assertFalse(bA.le(aA));
        assertTrue(bA.gt(aA));
        assertTrue(bA.ge(aA));
        assertTrue(aA.eq(aA));
        assertFalse(aA.ne(aA));

        assertEquals(0, aA.compareTo(pos(1, this.refA, Length.Unit.m)));

        Position aB = pos(1, this.refB, Length.Unit.m);
        assertTrue(aA.ne(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.lt(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.le(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.gt(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.ge(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.compareTo(aB));
    }

    /**
     * Verifies zero-comparison helpers (lt0/le0/gt0/ge0/eq0/ne0) for {@link AbsQuantity}.
     */
    @Test
    void zeroComparisons()
    {
        assertTrue(pos(-1, this.refA, Length.Unit.m).lt0());
        assertTrue(pos(0, this.refA, Length.Unit.m).le0());
        assertTrue(pos(1, this.refA, Length.Unit.m).gt0());
        assertTrue(pos(0, this.refA, Length.Unit.m).ge0());
        assertTrue(pos(0, this.refA, Length.Unit.m).eq0());
        assertTrue(pos(2, this.refA, Length.Unit.m).ne0());

        assertFalse(pos(1, this.refA, Length.Unit.m).lt0());
        assertFalse(pos(1, this.refA, Length.Unit.m).le0());
        assertFalse(pos(-1, this.refA, Length.Unit.m).gt0());
        assertFalse(pos(-1, this.refA, Length.Unit.m).ge0());
        assertFalse(pos(1, this.refA, Length.Unit.m).eq0());
        assertFalse(pos(0, this.refA, Length.Unit.m).ne0());
    }

    // ----------------------------------------------------------------------
    // Parsing helpers
    // ----------------------------------------------------------------------

    /**
     * Verifies successful parsing via {@link AbsQuantity#valueOf(String, AbsQuantity, Reference)} and error branches for
     * {@code null} parameters, empty text, and unknown units.
     */
    @Test
    void valueOfParses()
    {
        Position ex = pos(0, this.refA, Length.Unit.m); // example
        Position p = AbsQuantity.valueOf("12.5 m", ex, this.refA);
        assertEquals(12.5, p.si(), 1e-12);
        assertSame(this.refA, p.getReference());

        assertThrows(NullPointerException.class, () -> AbsQuantity.valueOf(null, ex, this.refA));
        assertThrows(NullPointerException.class, () -> AbsQuantity.valueOf("1 m", null, this.refA));
        assertThrows(NullPointerException.class, () -> AbsQuantity.valueOf("1 m", ex, null));
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.valueOf("", ex, this.refA));
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.valueOf("5 ???", ex, this.refA));
    }

    /**
     * Verifies successful parsing via {@link AbsQuantity#of(double, String, AbsQuantity, Reference)} and error branches for
     * {@code null} parameters, empty unit string, and unknown units.
     */
    @Test
    void ofParses()
    {
        Position ex = pos(0, this.refA, Length.Unit.m);
        Position p = AbsQuantity.of(2.0, "km", ex, this.refA);
        assertEquals(2000.0, p.si(), 1e-12);
        assertSame(this.refA, p.getReference());

        assertThrows(NullPointerException.class, () -> AbsQuantity.of(1.0, null, ex, this.refA));
        assertThrows(NullPointerException.class, () -> AbsQuantity.of(1.0, "m", null, this.refA));
        assertThrows(NullPointerException.class, () -> AbsQuantity.of(1.0, "m", ex, null));
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.of(1.0, "", ex, this.refA));
        assertThrows(UnitRuntimeException.class, () -> AbsQuantity.of(1.0, "???", ex, this.refA));
    }

    // ----------------------------------------------------------------------
    // relativeTo reference transformations
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link AbsQuantity#relativeTo(Reference)} for:
     * <ul>
     * <li>identity transform (same reference)</li>
     * <li>direct parent/child transform (A ⇄ B)</li>
     * <li>multi-level transform (C ⇄ A via B)</li>
     * <li>unrelated roots (should throw)</li>
     * </ul>
     * Graph (SI offsets): A=0, B=A+2, C=B+1 (=A+3).
     */
    @Test
    void relativeToVariants()
    {
        // Identity
        Position pA = pos(10, this.refA, Length.Unit.m);
        assertSame(pA, pA.relativeTo(this.refA));

        // A -> B: B is A+2; the same absolute point reads (p - 2) at B
        Position pAtoB = pA.relativeTo(this.refB);
        assertEquals(8.0, pAtoB.getQuantity().si(), 1e-12);
        assertSame(this.refB, pAtoB.getReference());

        // B -> A: add B's offset (+2)
        Position pB = pos(8, this.refB, Length.Unit.m); // same absolute as pA
        Position pBtoA = pB.relativeTo(this.refA);
        assertEquals(10.0, pBtoA.getQuantity().si(), 1e-12);
        assertSame(this.refA, pBtoA.getReference());

        // C -> A: add B offset (+2) and C offset (+1)
        Position pC = pos(7, this.refC, Length.Unit.m); // same absolute as pA
        Position pCtoA = pC.relativeTo(this.refA);
        assertEquals(10.0, pCtoA.getQuantity().si(), 1e-12);
        assertSame(this.refA, pCtoA.getReference());

        // A -> C: subtract total offset 3
        Position pAtoC = pA.relativeTo(this.refC);
        assertEquals(7.0, pAtoC.getQuantity().si(), 1e-12);
        assertSame(this.refC, pAtoC.getReference());

        // B -> C: same offset B = +2, C = +3 relative to A; pB = 8 m -> 7 m
        Position pBtoC = pB.relativeTo(this.refC);
        assertEquals(7.0, pBtoC.getQuantity().si(), 1e-12);
        assertSame(this.refC, pBtoC.getReference());

        // Unrelated roots should fail
        assertThrows(IllegalArgumentException.class, () -> pA.relativeTo(this.refX));

        // Unrelated roots should fail
        assertThrows(IllegalArgumentException.class, () -> pA.relativeTo(this.refY));

        // Unrelated roots should fail
        assertThrows(IllegalArgumentException.class, () -> pA.relativeTo(this.refZ));

        // Branch where current reference has no offsetReference and other is not reachable:
        Position pX = pos(1, this.refX, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> pX.relativeTo(this.refA));
        
        Position pY = pos(1, this.refY, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> pY.relativeTo(this.refA));

        Position pZ = pos(1, this.refZ, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> pZ.relativeTo(this.refA));
    }

    // ----------------------------------------------------------------------
    // Formatting and stringification
    // ----------------------------------------------------------------------

    /**
     * Test default {@code toString()} for a simple direction.
     */
    @Test
    @DisplayName("Default toString() uses default number and display unit")
    public void testDefaultToString()
    {
        Direction d = new Direction(12.34567, Angle.Unit.deg, Direction.Reference.NORTH);
        assertEquals("    12.346 deg", d.format(QuantityFormat.defaults().setFixedWithSciFallback().setTextual()));
    }

    /**
     * Test {@code toString(Unit)} converts the unit for display.
     */
    @Test
    @DisplayName("toString(Unit) converts value and displays target unit")
    public void testToStringWithTargetUnit()
    {
        Direction d = new Direction(Math.PI, Angle.Unit.rad, Direction.Reference.EAST);
        assertEquals("     3.142 rad", d.format(QuantityFormat.defaults().setTextual().setFixedWithSciFallback()));
        assertEquals("   180.000 deg",
                d.format(QuantityFormat.defaults().setDisplayUnit(Angle.Unit.deg).setTextual().setFixedWithSciFallback()));

        Direction d2 = new Direction(180.0, Angle.Unit.deg, Direction.Reference.EAST);
        assertTrue(d2.format(Angle.Unit.rad).contains("3.14159"));
    }

    /**
     * Test magnitude change with decimals and width.
     */
    @Test
    @DisplayName("Large magnitude with explicit number format")
    public void testLargeMagnitudeFixed()
    {
        Position.Reference pref = new Position.Reference("TEST", "TEST REFERENCE");
        try
        {
            Position p = new Position(12_345_678.9, Length.Unit.m, pref);
            String s1 =
                    p.format(QuantityFormat.defaults().setFixedFloat().setDecimals(1).setWidth(12).setGroupingSeparator(true));
            assertEquals("12,345,678.9 m", s1);
            String s2 =
                    p.format(QuantityFormat.defaults().setFixedFloat().setDecimals(1).setWidth(12).setGroupingSeparator(false));
            assertEquals("  12345678.9 m", s2);
            String s3 =
                    p.format(QuantityFormat.defaults().setFixedFloat().setDecimals(2).setWidth(12).setGroupingSeparator(false));
            assertEquals(" 12345678.90 m", s3);
        }
        finally
        {
            pref.unregister();
        }
    }

    /**
     * Test multiple format settings combined.
     */
    @Test
    @DisplayName("Combined number format, unit format and quantity format")
    public void testCombinedFormats()
    {
        Position.Reference pref = new Position.Reference("TEST", "TEST REFERENCE");
        try
        {
            Position pos = new Position(20400.0, Length.Unit.m, pref);
            String s1 = pos.format(
                    QuantityFormat.defaults().setFixedWithSciFallback().setScaleSiPrefixes().setDecimals(3).setTextual());
            assertEquals("    20.400 km", s1);

            String s2 = pos.format(QuantityFormat.defaults().setFixedWithSciFallback().setScaleSiPrefixes().setDecimals(3)
                    .setTextual().setPrintReference().setReferencePrefix(" (").setReferencePostfix(")"));
            assertEquals("    20.400 km (TEST)", s2);
        }
        finally
        {
            pref.unregister();
        }
    }

    /**
     * Test locale influence on decimal separator.
     */
    @Test
    @DisplayName("Locale format affects decimal separator")
    public void testLocaleFormat()
    {
        Position.Reference pref = new Position.Reference("TEST", "TEST REFERENCE");
        try
        {
            Position pos = new Position(20400.0, Length.Unit.m, pref);
            String s = pos.format(QuantityFormat.defaults().setFixedWithSciFallback().setLocale(Locale.GERMANY));
            assertEquals(" 20400,000 m", s);
        }
        finally
        {
            pref.unregister();
        }
    }

    // ----------------------------------------------------------------------
    // Arithmetic with relative quantity
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link AbsQuantity#subtract(AbsQuantity)} aligns references and returns the relative difference.
     * <p>
     * <strong>Case:</strong> {@code pA = 10@A}, {@code pB = 8@B} represent the same absolute point; the difference is 0.
     */
    @Test
    void subtractAbsolute()
    {
        Position pA = pos(10, this.refA, Length.Unit.m);
        Position pB = pos(8, this.refB, Length.Unit.m);
        Length delta = pA.subtract(pB);
        assertEquals(0.0, delta.si(), 1e-12);
        assertSame(pA.getDisplayUnit(), delta.getDisplayUnit());
    }

    /**
     * Verifies {@link AbsQuantity#add(Quantity)} and {@link AbsQuantity#subtract(Quantity)} adjust the inner relative quantity
     * while preserving the absolute reference and the display unit.
     */
    @Test
    void addSubtractRelative()
    {
        Position p = pos(10, this.refA, Length.Unit.km);
        Position pPlus = p.add(Length.ofSi(500)); // +0.5 km
        assertEquals(10.5, pPlus.getInUnit(Length.Unit.km), 1e-12);
        assertSame(this.refA, pPlus.getReference());
        assertSame(Length.Unit.km, pPlus.getDisplayUnit());

        Position pMinus = p.subtract(Length.ofSi(500));
        assertEquals(9.5, pMinus.getInUnit(Length.Unit.km), 1e-12);
        assertSame(this.refA, pMinus.getReference());
        assertSame(Length.Unit.km, pMinus.getDisplayUnit());
    }

    // ----------------------------------------------------------------------
    // Static helpers: interpolate, max/min/sum/mean
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link AbsQuantity#interpolate(AbsQuantity, AbsQuantity, double)}. For:
     * <ul>
     * <li>valid ratio in [0, 1]</li>
     * <li>ratio out of bounds raising {@link IllegalArgumentException}</li>
     * <li>reference mismatch raising {@link IllegalArgumentException}</li>
     * </ul>
     */
    @Test
    void interpolateAbsolute()
    {
        Position z = pos(0, this.refA, Length.Unit.m);
        Position o = pos(1000, this.refA, Length.Unit.m);
        Position mid = AbsQuantity.interpolate(z, o, 0.5);
        assertEquals(500.0, mid.si(), 1e-12);
        assertSame(this.refA, mid.getReference());
        assertSame(z.getDisplayUnit(), mid.getDisplayUnit());

        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.interpolate(z, o, -0.01));
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.interpolate(z, o, 1.01));

        Position oB = pos(1000, this.refB, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.interpolate(z, oB, 0.5));
    }

    /**
     * Verifies {@link AbsQuantity#max(AbsQuantity, AbsQuantity[])} and {@link AbsQuantity#min(AbsQuantity, AbsQuantity[])} for
     * identical references and that mixing references raises an {@link IllegalArgumentException}.
     */
    @Test
    void maxMin()
    {
        Position a = pos(1, this.refA, Length.Unit.m);
        Position b = pos(3, this.refA, Length.Unit.m);
        Position c = pos(-2, this.refA, Length.Unit.m);
        assertSame(b, AbsQuantity.max(a, b, c));
        assertSame(c, AbsQuantity.min(a, b, c));

        Position bB = pos(3, this.refB, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.max(a, bB));
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.min(a, bB));
    }

    /**
     * Verifies {@link AbsQuantity#sum(AbsQuantity, AbsQuantity[])} and {@link AbsQuantity#mean(AbsQuantity, AbsQuantity[])} for
     * identical references, and ensures that the reference and display unit of the first argument are preserved. Mixing
     * references must throw.
     */
    @Test
    void sumMean()
    {
        Position a = pos(1, this.refA, Length.Unit.cm);
        Position b = pos(2, this.refA, Length.Unit.m);
        Position c = pos(3, this.refA, Length.Unit.km);
        Position sum = AbsQuantity.sum(a, b, c); // SI: 1 + 2 + 3 = 6
        assertEquals(3002.01, sum.si(), 1e-12);
        assertSame(this.refA, sum.getReference());
        assertSame(a.getDisplayUnit(), sum.getDisplayUnit());

        Position ma = pos(1, this.refA, Length.Unit.m);
        Position mb = pos(2, this.refA, Length.Unit.m);
        Position mc = pos(3, this.refA, Length.Unit.m);
        Position mean = AbsQuantity.mean(ma, mb, mc); // 6/3 = 2
        assertEquals(2.0, mean.si(), 1e-12);
        assertSame(this.refA, mean.getReference());
        assertSame(ma.getDisplayUnit(), mean.getDisplayUnit());

        Position bB = pos(2, this.refB, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.sum(a, bB));
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.mean(a, bB));
    }

    /**
     * Verifies correct working of {@link AbsQuantity#siUnit()}.
     */
    @Test
    void siUnit()
    {
        Position c = pos(3, this.refA, Length.Unit.km);
        assertEquals("m", c.siUnit().toString());
    }
    
    // ----------------------------------------------------------------------
    // equals / hashCode
    // ----------------------------------------------------------------------

    /**
     * Verifies {@code equals}/{@code hashCode} consider both the inner relative quantity and the reference identity.
     */
    @Test
    void equalsHashCode()
    {
        Position p1 = pos(10, this.refA, Length.Unit.m);
        Position p2 = pos(10, this.refA, Length.Unit.m);
        Position p3 = pos(10, this.refB, Length.Unit.m); // different reference
        Position p4 = pos(20, this.refA, Length.Unit.m); // same reference, different position

        assertEquals(p1, p1);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
        assertNotEquals(p1, null);
        assertNotEquals(p1, "");
        assertNotEquals(p1, p4);

        assertTrue(p1.eq(p2));
        assertFalse(p1.eq(p3));
        assertFalse(p1.eq(p4));
        assertFalse(p1.ne(p2));
    }

    /**
     * Tests {@link AbsQuantity#getName()} using {@link Position} as the representative absolute quantity.
     * <p>
     * The "pretty name" algorithm mirrors {@link Quantity#getName()} and:
     * <ol>
     * <li>Keeps the first character of the localized simple name exactly as-is;</li>
     * <li>For each subsequent uppercase character, inserts a space <em>if</em> the previous character is not a space, and
     * appends the lower-cased version of that uppercase character;</li>
     * <li>Otherwise, appends the character unchanged.</li>
     * </ol>
     * <p>
     * <strong>Coverage note:</strong> The class name {@code Position} contains only an initial uppercase, so this test covers
     * the "no internal uppercase" path. If you want to also cover the branch that inserts spaces for internal capitals for
     * absolute quantities, add a trivial test-only subclass, e.g., {@code TestPositionAbsolute extends Position}, and assert
     * that {@code new TestPositionAbsolute(...).getName()} becomes {@code "Test position absolute"}.
     * <p>
     * <strong>Expected:</strong> {@code new Position(...).getName()} yields {@code "Position"}.
     */
    @Test
    void getNameFormatsPrettyForAbsoluteQuantity()
    {
        // Any instance suffices; the value/reference do not influence getName().
        Position.Reference pr = new Position.Reference("pr", "position reference");
        Position pos = new Position(Length.ONE, pr);

        // For "Position", no spaces should be inserted.
        assertEquals("Position", pos.getName(), "Position should remain Position");

        // AbsQuantity name with capitals
        assertNull(AbstractReference.get(AbsoluteExampleQuantityAQxyz.Reference.class, "xyz"));
        assertFalse(AbstractReference.containsId(RelativeExampleQuantityAQxyz.class, "xyz"));
        assertEquals(0, AbstractReference.snapshotMap(AbsoluteExampleQuantityAQxyz.Reference.class).size());
        AbsoluteExampleQuantityAQxyz.Reference ref =
                new AbsoluteExampleQuantityAQxyz.Reference("R0", "ref 0", RelativeExampleQuantityAQxyz.ZERO, null);
        var aq = new AbsoluteExampleQuantityAQxyz(RelativeExampleQuantityAQxyz.ONE, ref);
        assertEquals("Absolute example quantity a qxyz", aq.getName(), "Quantity name: camel case should result in spaces");

        // Clean up the test-created reference registry entry to avoid cross-test pollution.
        assertTrue(pr.unregister(), "Reference pr should be unregistered to keep the registry clean for other tests");
        assertTrue(ref.unregister(), "Reference ref should be unregistered to keep the registry clean for other tests");
        assertFalse(ref.unregister(), "Reference ref was unregistered already");
    }

    /**
     * Relative quantity class for test.
     */
    static class RelativeExampleQuantityAQxyz extends Quantity<RelativeExampleQuantityAQxyz>
    {
        /** */
        private static final long serialVersionUID = 1L;

        /** ZERO. */
        static final RelativeExampleQuantityAQxyz ZERO = new RelativeExampleQuantityAQxyz(0.0, Unit.BASE);

        /** ONE. */
        static final RelativeExampleQuantityAQxyz ONE = new RelativeExampleQuantityAQxyz(1.0, Unit.BASE);

        /**
         * @param value the value
         * @param displayUnit the unit
         */
        RelativeExampleQuantityAQxyz(final double value, final RelativeExampleQuantityAQxyz.Unit displayUnit)
        {
            super(value, displayUnit);
        }

        @Override
        public RelativeExampleQuantityAQxyz instantiateSi(final double siValue)
        {
            return null;
        }

        /** The unit. */
        static class Unit extends AbstractUnit<Unit, RelativeExampleQuantityAQxyz>
        {
            /** BASE. */
            static final Unit BASE = new Unit("BASE", "BASE", IdentityScale.SCALE, UnitSystem.OTHER);

            /**
             * @param textualAbbreviation abb
             * @param name name
             * @param scale scale
             * @param unitSystem system
             */
            Unit(final String textualAbbreviation, final String name, final Scale scale, final UnitSystem unitSystem)
            {
                super(textualAbbreviation, name, scale, unitSystem);
            }

            @Override
            public SIUnit siUnit()
            {
                return null;
            }

            @Override
            public Unit getBaseUnit()
            {
                return null;
            }

            @Override
            public RelativeExampleQuantityAQxyz ofSi(final double si)
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

    /**
     * Absolute quantity class for test.
     */
    static class AbsoluteExampleQuantityAQxyz extends
            AbsQuantity<AbsoluteExampleQuantityAQxyz, RelativeExampleQuantityAQxyz, AbsoluteExampleQuantityAQxyz.Reference>
    {
        /** */
        private static final long serialVersionUID = 1L;

        /**
         * @param quantity length
         * @param reference position reference
         */
        AbsoluteExampleQuantityAQxyz(final RelativeExampleQuantityAQxyz quantity, final Reference reference)
        {
            super(quantity, reference);
        }

        @Override
        public AbsoluteExampleQuantityAQxyz instantiate(final RelativeExampleQuantityAQxyz quantity, final Reference reference)
        {
            return null;
        }

        @Override
        public RelativeExampleQuantityAQxyz subtract(final AbsoluteExampleQuantityAQxyz other)
        {
            return null;
        }

        @Override
        public AbsoluteExampleQuantityAQxyz add(final RelativeExampleQuantityAQxyz other)
        {
            return null;
        }

        @Override
        public AbsoluteExampleQuantityAQxyz subtract(final RelativeExampleQuantityAQxyz other)
        {
            return null;
        }

        /** The reference class. */
        static class Reference extends AbstractReference<Reference, AbsoluteExampleQuantityAQxyz, RelativeExampleQuantityAQxyz>
        {
            /** REF. */
            static final Reference REF = new Reference("REF", "REF", RelativeExampleQuantityAQxyz.ZERO, null);

            /**
             * @param id id
             * @param name name
             * @param offset offset
             * @param offsetReference ref
             */
            Reference(final String id, final String name, final RelativeExampleQuantityAQxyz offset,
                    final Reference offsetReference)
            {
                super(id, name, offset, offsetReference);
            }

            @Override
            public AbsoluteExampleQuantityAQxyz instantiate(final RelativeExampleQuantityAQxyz quantity)
            {
                return new AbsoluteExampleQuantityAQxyz(quantity, this);
            }

        }
    }
}
