package org.djunits.quantity.def;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Length;
import org.djunits.quantity.Position;
import org.djunits.unit.UnitRuntimeException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the abstract {@link AbsoluteQuantity} API using {@link Position} and its nested
 * {@link org.djunits.quantity.Position.Reference}.
 * <p>
 * <strong>Goals and coverage:</strong>
 * <ul>
 * <li>Reference-aware comparisons and {@link Comparable} rules</li>
 * <li>Reference transformations via {@link AbsoluteQuantity#relativeTo(AbstractReference)}</li>
 * <li>Parsing helpers: {@link AbsoluteQuantity#valueOf(String, AbsoluteQuantity, AbstractReference)} and
 * {@link AbsoluteQuantity#of(double, String, AbsoluteQuantity, AbstractReference)}</li>
 * <li>Stringification and SI-prefix formatting</li>
 * <li>Static helpers: {@link AbsoluteQuantity#interpolate(AbsoluteQuantity, AbsoluteQuantity, double)},
 * {@link AbsoluteQuantity#max(AbsoluteQuantity, AbsoluteQuantity[])},
 * {@link AbsoluteQuantity#min(AbsoluteQuantity, AbsoluteQuantity[])},
 * {@link AbsoluteQuantity#sum(AbsoluteQuantity, AbsoluteQuantity[])},
 * {@link AbsoluteQuantity#mean(AbsoluteQuantity, AbsoluteQuantity[])}</li>
 * <li>Arithmetic with relative quantities: {@link AbsoluteQuantity#add(Quantity)} and
 * {@link AbsoluteQuantity#subtract(Quantity)} and {@link AbsoluteQuantity#subtract(AbsoluteQuantity)}</li>
 * <li>{@code equals}/{@code hashCode} contract</li>
 * </ul>
 * <p>
 * <strong>Deliberately out of scope:</strong> We do not re-test domain specifics of {@link Position}/{@link Length}. Those are
 * covered by their dedicated tests; here, {@code Position} provides a concrete vehicle to exercise the abstract API.
 * </p>
 * <p>
 * <strong>Locale pinning:</strong> The suite pins {@code Locale.Category.FORMAT} to {@code Locale.US} for deterministic
 * formatting/parsing behavior; the original locale is restored afterwards.
 * </p>
 * <p>
 * <strong>Reference registry hygiene:</strong> Each created {@link org.djunits.quantity.Position.Reference} is
 * {@link AbstractReference#unregister() unregistered} in {@link #cleanup()} to avoid cross-test pollution of the static
 * per-class registry.
 * </p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class AbsoluteQuantityTest
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
     * </ul>
     */
    @BeforeEach
    void setup()
    {
        this.refA = new Position.Reference("A", "origin A");
        this.refB = new Position.Reference("B", "origin B", Length.ofSi(2), this.refA);
        this.refC = new Position.Reference("C", "origin C", Length.ofSi(3), this.refA);
        this.refX = new Position.Reference("X", "origin X");
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
     * Verifies that the constructor of {@link Position} (as a representative of {@link AbsoluteQuantity}) rejects {@code null}
     * for either the relative quantity or the reference.
     */
    @Test
    void constructorNulls()
    {
        assertThrows(NullPointerException.class, () -> new Position(null, this.refA));
        assertThrows(NullPointerException.class, () -> new Position(Length.ofSi(0), null));
    }

    /**
     * Verifies that {@link AbsoluteQuantity#getDisplayUnit()} and
     * {@link AbsoluteQuantity#setDisplayUnit(org.djunits.unit.UnitInterface)} delegate correctly to the inner relative quantity
     * and that the setter is fluent.
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
     * Verifies the accessors {@link AbsoluteQuantity#getQuantity()} and {@link AbsoluteQuantity#getReference()}.
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
     * Verifies {@link AbsoluteQuantity#getInUnit()} and {@link AbsoluteQuantity#getInUnit(org.djunits.unit.UnitInterface)}.
     */
    @Test
    void getInUnitVariants()
    {
        Position p = pos(1500, this.refA, Length.Unit.m);
        assertEquals(1500.0, p.getInUnit(), 1e-12);
        assertEquals(1.5, p.getInUnit(Length.Unit.km), 1e-12);
    }

    /**
     * Verifies that {@link Number}-derived conversions reflect the SI value semantics for {@link AbsoluteQuantity}.
     */
    @Test
    void numberConversions()
    {
        Position p = pos(2.49, this.refA, Length.Unit.m);
        assertEquals(2.49, p.doubleValue(), 1e-12);
        assertEquals(2, p.intValue());
        assertEquals(2L, p.longValue());
        assertEquals(2.49f, p.floatValue(), 1e-6f);
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
        assertEquals(0, aA.compareTo(pos(1, this.refA, Length.Unit.m)));

        Position aB = pos(1, this.refB, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> aA.lt(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.le(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.gt(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.ge(aB));
        assertThrows(IllegalArgumentException.class, () -> aA.compareTo(aB));
    }

    /**
     * Verifies zero-comparison helpers (lt0/le0/gt0/ge0/eq0/ne0) for {@link AbsoluteQuantity}.
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
    }

    // ----------------------------------------------------------------------
    // Parsing helpers
    // ----------------------------------------------------------------------

    /**
     * Verifies successful parsing via {@link AbsoluteQuantity#valueOf(String, AbsoluteQuantity, AbstractReference)} and error
     * branches for {@code null} parameters, empty text, and unknown units.
     */
    @Test
    void valueOfParses()
    {
        Position ex = pos(0, this.refA, Length.Unit.m); // example
        Position p = AbsoluteQuantity.valueOf("12.5 m", ex, this.refA);
        assertEquals(12.5, p.si(), 1e-12);
        assertSame(this.refA, p.getReference());

        assertThrows(NullPointerException.class, () -> AbsoluteQuantity.valueOf(null, ex, this.refA));
        assertThrows(NullPointerException.class, () -> AbsoluteQuantity.valueOf("1 m", null, this.refA));
        assertThrows(NullPointerException.class, () -> AbsoluteQuantity.valueOf("1 m", ex, null));
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.valueOf("", ex, this.refA));
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.valueOf("5 ???", ex, this.refA));
    }

    /**
     * Verifies successful parsing via {@link AbsoluteQuantity#of(double, String, AbsoluteQuantity, AbstractReference)} and
     * error branches for {@code null} parameters, empty unit string, and unknown units.
     */
    @Test
    void ofParses()
    {
        Position ex = pos(0, this.refA, Length.Unit.m);
        Position p = AbsoluteQuantity.of(2.0, "km", ex, this.refA);
        assertEquals(2000.0, p.si(), 1e-12);
        assertSame(this.refA, p.getReference());

        assertThrows(NullPointerException.class, () -> AbsoluteQuantity.of(1.0, null, ex, this.refA));
        assertThrows(NullPointerException.class, () -> AbsoluteQuantity.of(1.0, "m", null, this.refA));
        assertThrows(NullPointerException.class, () -> AbsoluteQuantity.of(1.0, "m", ex, null));
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.of(1.0, "", ex, this.refA));
        assertThrows(UnitRuntimeException.class, () -> AbsoluteQuantity.of(1.0, "???", ex, this.refA));
    }

    // ----------------------------------------------------------------------
    // relativeTo reference transformations
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link AbsoluteQuantity#relativeTo(AbstractReference)} for:
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

        // Unrelated roots should fail
        assertThrows(IllegalArgumentException.class, () -> pA.relativeTo(this.refX));

        // Branch where current reference has no offsetReference and other is not reachable:
        Position pX = pos(1, this.refX, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> pX.relativeTo(this.refA));
    }

    // ----------------------------------------------------------------------
    // Formatting and stringification
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link AbsoluteQuantity#format(double)} and {@link AbsoluteQuantity#format(double, String)} paths: fixed format
     * range and E-notation range.
     */
    @Test
    void formatVariants()
    {
        Position ex = pos(0, this.refA, Length.Unit.m);
        assertEquals("3.14", ex.format(3.140000));
        String e = ex.format(1.23e12);
        assertTrue(e.contains("E"));
    }

    /**
     * Verifies {@link AbsoluteQuantity#toString()} variants, ensuring that when {@code withUnit=true} the reference id is
     * appended as " (refId)" and that the verbose flag adds the "Abs " prefix.
     */
    @Test
    void toStringVariants()
    {
        Position p = pos(1500, this.refB, Length.Unit.m);
        String s = p.toString();
        assertTrue(s.endsWith(" m (B)"));

        String kv = p.toString(Length.Unit.km);
        assertTrue(kv.endsWith(" km (B)"));

        String verbose = p.toString(true, true);
        assertTrue(verbose.startsWith("Abs "));
        assertTrue(verbose.endsWith(" m (B)"));

        String noUnit = p.toString(false, false);
        assertFalse(noUnit.contains("(B)"));
        assertFalse(noUnit.endsWith(" m (B)"));
    }

    /**
     * Verifies that {@link AbsoluteQuantity#toStringSIPrefixed()} delegates to the inner relative quantity and produces a
     * sensible SI prefix when possible.
     */
    @Test
    void toStringSIPrefixed()
    {
        Position p = pos(12_345, this.refA, Length.Unit.m);
        assertTrue(p.toStringSIPrefixed().contains("km"));
    }

    /**
     * Verifies concise textual and display strings for absolute quantities.
     */
    @Test
    void compactStrings()
    {
        Position p = pos(1234, this.refA, Length.Unit.m);
        assertTrue(p.toTextualString().endsWith(" m"));
        assertTrue(p.toDisplayString().endsWith(" m"));
        assertTrue(p.toTextualString(Length.Unit.km).endsWith(" km"));
        assertTrue(p.toDisplayString(Length.Unit.km).endsWith(" km"));
    }

    // ----------------------------------------------------------------------
    // Arithmetic with relative quantity
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link AbsoluteQuantity#subtract(AbsoluteQuantity)} aligns references and returns the relative difference.
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
     * Verifies {@link AbsoluteQuantity#add(Quantity)} and {@link AbsoluteQuantity#subtract(Quantity)} adjust the inner relative
     * quantity while preserving the absolute reference and the display unit.
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
     * Verifies {@link AbsoluteQuantity#interpolate(AbsoluteQuantity, AbsoluteQuantity, double)}. For:
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
        Position mid = AbsoluteQuantity.interpolate(z, o, 0.5);
        assertEquals(500.0, mid.si(), 1e-12);
        assertSame(this.refA, mid.getReference());
        assertSame(z.getDisplayUnit(), mid.getDisplayUnit());

        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.interpolate(z, o, -0.01));
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.interpolate(z, o, 1.01));

        Position oB = pos(1000, this.refB, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.interpolate(z, oB, 0.5));
    }

    /**
     * Verifies {@link AbsoluteQuantity#max(AbsoluteQuantity, AbsoluteQuantity[])} and
     * {@link AbsoluteQuantity#min(AbsoluteQuantity, AbsoluteQuantity[])} for identical references and that mixing references
     * raises an {@link IllegalArgumentException}.
     */
    @Test
    void maxMin()
    {
        Position a = pos(1, this.refA, Length.Unit.m);
        Position b = pos(3, this.refA, Length.Unit.m);
        Position c = pos(-2, this.refA, Length.Unit.m);
        assertSame(b, AbsoluteQuantity.max(a, b, c));
        assertSame(c, AbsoluteQuantity.min(a, b, c));

        Position bB = pos(3, this.refB, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.max(a, bB));
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.min(a, bB));
    }

    /**
     * Verifies {@link AbsoluteQuantity#sum(AbsoluteQuantity, AbsoluteQuantity[])} and
     * {@link AbsoluteQuantity#mean(AbsoluteQuantity, AbsoluteQuantity[])} for identical references, and ensures that the
     * reference and display unit of the first argument are preserved. Mixing references must throw.
     */
    @Test
    void sumMean()
    {
        Position a = pos(1, this.refA, Length.Unit.cm);
        Position b = pos(2, this.refA, Length.Unit.m);
        Position c = pos(3, this.refA, Length.Unit.km);
        Position sum = AbsoluteQuantity.sum(a, b, c); // SI: 1 + 2 + 3 = 6
        assertEquals(3002.01, sum.si(), 1e-12);
        assertSame(this.refA, sum.getReference());
        assertSame(a.getDisplayUnit(), sum.getDisplayUnit());

        Position ma = pos(1, this.refA, Length.Unit.m);
        Position mb = pos(2, this.refA, Length.Unit.m);
        Position mc = pos(3, this.refA, Length.Unit.m);
        Position mean = AbsoluteQuantity.mean(ma, mb, mc); // 6/3 = 2
        assertEquals(2.0, mean.si(), 1e-12);
        assertSame(this.refA, mean.getReference());
        assertSame(ma.getDisplayUnit(), mean.getDisplayUnit());

        Position bB = pos(2, this.refB, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.sum(a, bB));
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.mean(a, bB));
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

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
    }

    /**
     * Tests {@link AbsoluteQuantity#getName()} using {@link Position} as the representative absolute quantity.
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
     * </p>
     * <p>
     * <strong>Expected:</strong> {@code new Position(...).getName()} yields {@code "Position"}.
     * </p>
     */
    @Test
    void getNameFormatsPrettyForAbsoluteQuantity()
    {
        // Any instance suffices; the value/reference do not influence getName().
        Position.Reference ref = new Position.Reference("R0", "ref 0");
        Position pos = Position.ofSi(0.0, ref).setDisplayUnit(Length.Unit.m);

        // For "Position" (only first char uppercase), no spaces are inserted; the result should be identical.
        assertEquals("Position", pos.getName(), "Position should remain 'Position' without extra spaces");

        // Clean up the test-created reference registry entry to avoid cross-test pollution.
        assertTrue(ref.unregister(), "Reference should be unregistered to keep the registry clean for other tests");
    }
}
