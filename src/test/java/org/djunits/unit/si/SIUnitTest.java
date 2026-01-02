package org.djunits.unit.si;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.djunits.quantity.SIQuantity;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SIUnit}.
 * <p>
 * These tests validate construction (array and 9-arg), parsing of SI-dimension strings (including tricky cases for {@code sr}
 * vs {@code s} and {@code mol} vs {@code m}), exponent algebra ({@link SIUnit#plus(SIUnit)}, {@link SIUnit#minus(SIUnit)},
 * {@link SIUnit#invert()}, {@link SIUnit#pow(int)}), formatting variants (plain/with separator/with power marker and HTML),
 * immutability around rendering, {@link SIUnit#siAbbreviations()} defensive copies, {@link org.djunits.unit.UnitInterface}
 * getters, and a smoke test for {@link SIUnit#ofSi(double)}. <br>
 * Fractional dimensionality is intentionally <em>not</em> tested here (feature removed / not in production).
 */
public final class SIUnitTest
{
    /**
     * Helper: nine zeros for convenience.
     * @return int[9] array with all zeros
     */
    private static int[] zeros()
    {
        return new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    /**
     * Verifies the {@link SIUnit#DIMLESS} instance and basic renderers.
     * <ul>
     * <li>Dimensionless has all-zero exponents.</li>
     * <li>String renderers produce the documented empty/identity forms.</li>
     * </ul>
     */
    @Test
    @DisplayName("DIMLESS: exponents and rendering")
    public void testDimless()
    {
        SIUnit dimless = SIUnit.DIMLESS;
        assertNotNull(dimless);

        int[] dims = dimless.siDimensions();
        assertEquals(SIUnit.NUMBER_DIMENSIONS, dims.length);
        for (int d : dims)
        {
            assertEquals(0, d, "DIMLESS must have zero exponents");
        }

        // The "divided" textual ID for dimensionless is empty in this implementation
        assertEquals("", dimless.getId());
        assertEquals("", dimless.getTextualAbbreviation());
        assertEquals("", dimless.getDisplayAbbreviation());
        assertEquals("", dimless.getName());
        assertEquals("", dimless.getStoredTextualAbbreviation());
        assertEquals("", dimless.getStoredDisplayAbbreviation());
        assertEquals("", dimless.getStoredName());

        // Plain toString() renders bracketed exponents
        assertEquals("[0, 0, 0, 0, 0, 0, 0, 0, 0]", dimless.toString());

        // HTML rendering for dimensionless is empty
        assertEquals("", dimless.toHTMLString(true, false));
        assertEquals("", dimless.toHTMLString(true, true));
    }

    /**
     * Verifies the int[9]-array constructor:
     * <ul>
     * <li>Null and wrong-length arrays are rejected.</li>
     * <li>Input array is defensively copied.</li>
     * </ul>
     */
    @Test
    @DisplayName("int[9] constructor: validation and immutability")
    public void testArrayConstructor()
    {
        // null → NPE
        assertThrows(NullPointerException.class, () -> new SIUnit((int[]) null));

        // wrong length (8) → SIRuntimeException with "wrong dimensionality"
        final int[] eight = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
        RuntimeException wrongLen8 = assertThrows(RuntimeException.class, () -> new SIUnit(eight));
        assertTrue(wrongLen8.getMessage().toLowerCase().contains("wrong dimensionality"));

        // wrong length (10)
        final int[] ten = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        RuntimeException wrongLen10 = assertThrows(RuntimeException.class, () -> new SIUnit(ten));
        assertTrue(wrongLen10.getMessage().toLowerCase().contains("wrong dimensionality"));

        // happy path + defensive copy
        int[] speed = zeros();
        speed[3] = 1; // m
        speed[4] = -1; // s^-1
        SIUnit u = new SIUnit(speed);
        speed[3] = 2; // mutate original
        int[] dims = u.siDimensions();
        assertEquals(1, dims[3]);
        assertEquals(-1, dims[4]);

        assertEquals("[0, 0, 0, 1, -1, 0, 0, 0, 0]", u.toString());
    }

    /**
     * Verifies the 9-argument convenience constructor stores exponents in the documented order.
     */
    @Test
    @DisplayName("9-arg constructor: exponent order")
    @SuppressWarnings("checkstyle:magicnumber")
    public void testNineArgConstructor()
    {
        // angle, solidAngle, mass, length, time, current, temperature, amount, luminous
        SIUnit u = new SIUnit(0, 0, 1, 1, -2, 0, 0, 0, 0); // Newton (kg·m·s^-2)
        assertEquals("[0, 0, 1, 1, -2, 0, 0, 0, 0]", u.toString());
    }

    /**
     * Parsing: happy-path and edge cases, including disambiguation of {@code sr} vs {@code s} and {@code mol} vs {@code m}.
     */
    @Test
    @DisplayName("Parsing SI strings: happy path + disambiguation + errors")
    public void testParsing()
    {
        // Happy path (acceleration)
        SIUnit a1 = SIUnit.of("m/s2");
        SIUnit a2 = SIUnit.of("m.s-2");
        SIUnit a3 = SIUnit.of(" m / s ^ 2 "); // spaces/slashes/carets are stripped
        assertEquals(a1, a2);
        assertEquals(a1, a3);
        assertEquals("[0, 0, 0, 1, -2, 0, 0, 0, 0]", a1.toString());

        // Disambiguation: sr vs s
        assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0]", SIUnit.of("sr").toString()); // solid angle
        assertEquals("[0, 0, 0, 0, 1, 0, 0, 0, 0]", SIUnit.of("s").toString()); // second

        // Disambiguation: mol vs m
        assertEquals("[0, 0, 0, 0, 0, 0, 0, 1, 0]", SIUnit.of("mol").toString());
        assertEquals("[0, 0, 0, 1, 0, 0, 0, 0, 0]", SIUnit.of("m").toString());

        // Division cancellation example
        assertEquals("[0, 0, 0, 0, 1, 0, 0, 0, 0]", SIUnit.of("kgm2s2/kgm2s").toString());

        // Null string -> NPE
        assertThrows(NullPointerException.class, () -> SIUnit.of((String) null));

        // Illegal inputs
        illegal("kgm/s/s"); // more than one '/'
        illegal("kgkg/s"); // duplicate same unit in numerator
        illegal("abcd"); // unknown units
        illegal("kgm-/s"); // ends with '-'
        illegal("kg-m/s"); // '-' not allowed there
        illegal("kgm/s-"); // trailing '-' after '/'
        illegal("kgm/"); // empty denominator
        illegal("1/"); // empty denominator after '1'
        illegal("/"); // no numerator or denominator

        // Legal corner cases (dimensionless variants)
        assertEquals(SIUnit.DIMLESS, SIUnit.of("1/1"));
        assertEquals(SIUnit.DIMLESS, SIUnit.of("1"));
        assertEquals(SIUnit.DIMLESS, SIUnit.of("/1"));
        assertEquals(SIUnit.DIMLESS, SIUnit.of(""));

        // Permutation robustness of token order
        SIUnit si9 = SIUnit.of("rad1sr2kg3m4s5A6K7mol8cd9");
        SIUnit si9a = SIUnit.of("mol8cd9rad1sr2kg3m4s5A6K7");
        SIUnit si9b = SIUnit.of("cd9mol8K7A6s5m4kg3sr2rad1");
        assertEquals(si9, si9a);
        assertEquals(si9, si9b);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", si9.toString());
    }

    /**
     * Exponent algebra: plus, minus, invert, pow. Also checks pow(0) and that pow(-1) equals invert().
     */
    @Test
    @DisplayName("Exponent algebra: plus/minus/invert/pow")
    @SuppressWarnings("checkstyle:magicnumber")
    public void testExponentAlgebra()
    {
        SIUnit m = SIUnit.of("m");
        SIUnit s = SIUnit.of("s");

        // m + s^-1 == m.s^-1
        SIUnit invS = s.invert();
        SIUnit speed1 = m.plus(invS);
        assertEquals("[0, 0, 0, 1, -1, 0, 0, 0, 0]", speed1.toString());

        // m - s == m.s^-1
        SIUnit speed2 = m.minus(s);
        assertEquals(speed1, speed2);

        // invert twice -> original
        assertEquals(speed1, speed1.invert().invert());

        // pow(2): (m.s^-1)^2 = m^2.s^-2
        SIUnit sq = speed1.pow(2);
        assertEquals("[0, 0, 0, 2, -2, 0, 0, 0, 0]", sq.toString());

        // pow(0): -> DIMLESS
        assertEquals(SIUnit.DIMLESS, speed1.pow(0));

        // pow(-1) equals invert()
        assertEquals(speed1.invert(), speed1.pow(-1));

        // Static helpers add/subtract
        assertEquals(speed1, SIUnit.add(m, invS));
        assertEquals(speed1, SIUnit.subtract(m, s));
    }

    /**
     * Formatting variants: plain (with/without separator), caret powers, and HTML, for two representative units.
     */
    @Test
    @DisplayName("Formatting: plain / caret / HTML")
    @SuppressWarnings("checkstyle:magicnumber")
    public void testFormattingVariants()
    {
        // Newton: kg·m·s^-2
        SIUnit newton = new SIUnit(0, 0, 1, 1, -2, 0, 0, 0, 0);

        // divided=false (compact) and separator toggle
        assertEquals("kgms-2", newton.toString(false, false));
        assertEquals("kg.m.s-2", newton.toString(false, true));

        // divided=true (slash form)
        assertEquals("kgm/s2", newton.toString(true, false));
        assertEquals("kg.m/s2", newton.toString(true, true));

        // caret powers
        assertEquals("kgm/s^2", newton.toString(true, false, true));
        assertEquals("kg.m/s^2", newton.toString(true, true, true));
        assertEquals("kgms^-2", newton.toString(false, false, true));
        assertEquals("kg.m.s^-2", newton.toString(false, true, true));

        // HTML
        assertEquals("kgm/s<sup>2</sup>", newton.toHTMLString(true, false));
        assertEquals("kg.m/s<sup>2</sup>", newton.toHTMLString(true, true));
        assertEquals("kgms<sup>-2</sup>", newton.toHTMLString(false, false));
        assertEquals("kg.m.s<sup>-2</sup>", newton.toHTMLString(false, true));

        // A single negative exponent, s^-3
        SIUnit sNeg3 = SIUnit.of("s-3");
        assertEquals("1/s3", sNeg3.toString(true, false));
        assertEquals("1/s3", sNeg3.toString(true, true));
        assertEquals("s-3", sNeg3.toString(false, false));
        assertEquals("s-3", sNeg3.toString(false, true));

        // caret and HTML for s^-3
        assertEquals("1/s^3", sNeg3.toString(true, false, true));
        assertEquals("1/s^3", sNeg3.toString(true, true, true));
        assertEquals("s^-3", sNeg3.toString(false, false, true));
        assertEquals("s^-3", sNeg3.toString(false, true, true));
        assertEquals("1/s<sup>3</sup>", sNeg3.toHTMLString(true, false));
        assertEquals("1/s<sup>3</sup>", sNeg3.toHTMLString(true, true));
        assertEquals("s<sup>-3</sup>", sNeg3.toHTMLString(false, false));
        assertEquals("s<sup>-3</sup>", sNeg3.toHTMLString(false, true));

        // Mixed example: kg m^2 / (s^3 A)
        SIUnit mixed = SIUnit.of("kgm2/s3A");
        assertEquals("kgm2/s3A", mixed.toString(true, false));
        assertEquals("kg.m2/s3.A", mixed.toString(true, true));
        assertEquals("kgm2s-3A-1", mixed.toString(false, false));
        assertEquals("kg.m2.s-3.A-1", mixed.toString(false, true));
        assertEquals("kgm^2/s^3A", mixed.toString(true, false, true));
        assertEquals("kg.m^2/s^3.A", mixed.toString(true, true, true));
        assertEquals("kgm^2s^-3A^-1", mixed.toString(false, false, true));
        assertEquals("kg.m^2.s^-3.A^-1", mixed.toString(false, true, true));
        assertEquals("kgm<sup>2</sup>/s<sup>3</sup>A", mixed.toHTMLString(true, false));
        assertEquals("kg.m<sup>2</sup>/s<sup>3</sup>.A", mixed.toHTMLString(true, true));
        assertEquals("kgm<sup>2</sup>s<sup>-3</sup>A<sup>-1</sup>", mixed.toHTMLString(false, false));
        assertEquals("kg.m<sup>2</sup>.s<sup>-3</sup>.A<sup>-1</sup>", mixed.toHTMLString(false, true));
    }

    /**
     * Verifies that {@link SIUnit#siAbbreviations()} returns a defensive copy.
     */
    @Test
    @DisplayName("siAbbreviations(): defensive copy")
    public void testSiAbbreviationsDefensiveCopy()
    {
        String[] a1 = SIUnit.DIMLESS.siAbbreviations();
        String[] a2 = SIUnit.DIMLESS.siAbbreviations();
        assertNotSame(a1, a2, "Each call should return a fresh copy");
        assertEquals(a1.length, a2.length);
        assertTrue(a1.length >= 9);

        // Spot-check stable entries
        assertEquals("m", a1[3]);
        assertEquals("mol", a1[7]);

        // Mutate the returned array; future calls must be unaffected
        a1[3] = "X";
        assertEquals("m", SIUnit.DIMLESS.siAbbreviations()[3]);
    }

    /**
     * Verifies {@link org.djunits.unit.UnitInterface} getters exposed by {@link SIUnit}.
     */
    @Test
    @DisplayName("UnitInterface getters: consistent values")
    public void testUnitInterfaceGetters()
    {
        SIUnit speed = SIUnit.of("m/s");

        // Many getters map to toString(true,false) in this implementation:
        assertEquals("m/s", speed.getId());
        assertEquals("m/s", speed.getTextualAbbreviation());
        assertEquals("m/s", speed.getDisplayAbbreviation());
        assertEquals("m/s", speed.getName());
        assertEquals("m/s", speed.getStoredTextualAbbreviation());
        assertEquals("m/s", speed.getStoredDisplayAbbreviation());
        assertEquals("m/s", speed.getStoredName());

        // Scale and UnitSystem
        Scale scale = speed.getScale();
        assertSame(IdentityScale.SCALE, scale);
        assertEquals(UnitSystem.SI_BASE, speed.getUnitSystem());

        // SI/base unit for SIUnit is itself
        assertSame(speed, speed.siUnit());
        assertSame(speed, speed.getBaseUnit());
    }

    /**
     * Smoke test: {@link SIUnit#ofSi(double)} returns an {@link SIQuantity}.
     */
    @Test
    @DisplayName("ofSi(double): creates SIQuantity")
    public void testOfSiCreatesQuantity()
    {
        SIUnit newton = SIUnit.of("kg.m.s-2");
        SIQuantity q = newton.ofSi(12.34);
        assertNotNull(q, "Expected a non-null SIQuantity");
    }

    /**
     * Equality and hashCode contracts across construction paths and negatives.
     */
    @Test
    @DisplayName("equals/hashCode across construction paths")
    @SuppressWarnings("checkstyle:magicnumber")
    public void testEqualsAndHashCode()
    {
        // All "kgm/s2"
        SIUnit si1 = new SIUnit(0, 0, 1, 1, -2, 0, 0, 0, 0);
        SIUnit si2 = new SIUnit(new int[] {0, 0, 1, 1, -2, 0, 0, 0, 0});
        SIUnit si3 = SIUnit.of("kgm/s2");
        SIUnit si4 = SIUnit.of("kgms-2");

        assertEquals(si1, si2, si1 + " != " + si2);
        assertEquals(si2, si3, si2 + " != " + si3);
        assertEquals(si3, si4, si3 + " != " + si4);
        assertEquals(si1.hashCode(), si2.hashCode());

        assertTrue(si1.equals(si2));
        assertTrue(si1.equals(si1));
        assertFalse(si1.equals(new Object()));
        assertFalse(si1.equals(SIUnit.of("kgm2/s2")));
        assertFalse(si1.equals(null));

        assertEquals("[0, 0, 1, 1, -2, 0, 0, 0, 0]", si1.toString());

        // Negative hashCode comparison (not required by contract, but indicative)
        assertNotEquals(si1.hashCode(), SIUnit.of("kg").hashCode());
    }

    /**
     * Immutability: calling the various renderers must not mutate internal state.
     */
    @Test
    @DisplayName("Rendering must not mutate exponents")
    public void testRenderingNoMutation()
    {
        SIUnit u = SIUnit.of("kg.m.s-2");
        int[] before = u.siDimensions().clone();

        // Call a few renderers
        String t1 = u.toString();
        String t2 = u.toString(true, false);
        String t3 = u.toString(false, true);
        String t4 = u.toString(true, true, true);
        String h1 = u.toHTMLString(true, true);

        assertNotNull(t1);
        assertNotNull(t2);
        assertNotNull(t3);
        assertNotNull(t4);
        assertNotNull(h1);

        int[] after = u.siDimensions();
        assertArrayEquals(before, after, "Rendering must not change exponent vector");
    }

    /**
     * Helper that asserts that {@link SIUnit#of(String)} throws a {@link UnitRuntimeException}.
     * @param si the invalid SI dimension string
     */
    private static void illegal(final String si)
    {
        try
        {
            SIUnit.of(si);
            fail("SIUnit.of(" + si + ") should have thrown UnitRuntimeException");
        }
        catch (UnitRuntimeException e)
        {
            // expected
        }
    }
}
