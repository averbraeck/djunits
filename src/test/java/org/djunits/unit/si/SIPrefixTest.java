package org.djunits.unit.si;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SIPrefix} and {@link SIPrefixes}.
 * <p>
 * This suite validates:
 * <ul>
 * <li>Public constructor guards on {@link SIPrefix}: nulls and zero factor are rejected.</li>
 * <li>Getter semantics: textual vs display prefix, name, and numeric factor for known prefixes.</li>
 * <li>Public lookups on {@link SIPrefixes}: {@code getUnit}, {@code getPerUnit}, {@code getKiloUnit} accept valid keys, reject
 * null/blank/unknown keys, and return stable results.</li>
 * <li>Maps exposed by {@link SIPrefixes} are unmodifiable.</li>
 * <li>The {@code FACTORS} map resolves typical exponents (e.g., -6 → micro, +3 → kilo, 0 → identity).</li>
 * <li>Enum constants presence and basic invariants.</li>
 * </ul>
 * </p>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class SIPrefixTest
{
    /**
     * Constructor validation for {@link SIPrefix}.
     * <ul>
     * <li>null textual prefix → NPE</li>
     * <li>null name → NPE</li>
     * <li>null display prefix → NPE</li>
     * <li>factor == 0 → IllegalArgumentException or runtime exception (as per implementation)</li>
     * <li>valid construction returns stable getters</li>
     * </ul>
     */
    @Test
    @DisplayName("SIPrefix constructor guards and getters")
    public void testSiprefixConstructorAndGetters()
    {
        // Nulls must fail
        assertThrows(NullPointerException.class, () -> new SIPrefix(null, "mega", 1.0E6, "M"));
        assertThrows(NullPointerException.class, () -> new SIPrefix("M", null, 1.0E6, "M"));
        assertThrows(NullPointerException.class, () -> new SIPrefix("M", "mega", 1.0E6, null));

        // Zero factor must fail
        assertThrows(RuntimeException.class, () -> new SIPrefix("X", "x", 0.0, "X"));

        // Valid construction (textual==display when using 3-arg constructor)
        SIPrefix mega = new SIPrefix("M", "mega", 1.0E6);
        assertEquals("M", mega.getDefaultTextualPrefix());
        assertEquals("M", mega.getDefaultDisplayPrefix());
        assertEquals("mega", mega.getPrefixName());
        assertEquals(1.0E6, mega.getFactor(), 0.0);

        // Micro uses textual "mu" and display "μ"
        SIPrefix micro = SIPrefixes.UNIT_PREFIXES.get("mu");
        assertNotNull(micro, "micro must exist in UNIT_PREFIXES");
        assertEquals("mu", micro.getDefaultTextualPrefix());
        assertEquals("\u03BC", micro.getDefaultDisplayPrefix());
        assertEquals("micro", micro.getPrefixName());
        assertEquals(1.0E-6, micro.getFactor(), 1e-18);
    }

    /**
     * getSiPrefix(String) should return proper results for valid keys, including the empty string "" to indicate "no SI
     * prefix".
     */
    @Test
    @DisplayName("getSiPrefix: valid keys (incl. \"\") return correct prefix")
    public void testGetSiPrefixValidKeys()
    {
        // No prefix -> factor 1.0
        SIPrefix none = SIPrefixes.getSiPrefix("");
        assertNotNull(none, "Empty key should return the no-prefix SIPrefix for UNIT");
        assertEquals(1.0, none.getFactor(), 0.0);
        assertTrue(none.getDefaultTextualPrefix().isEmpty());
        assertTrue(none.getDefaultDisplayPrefix().isEmpty());
        assertNotNull(none.toString());

        // Typical positive prefix
        SIPrefix kilo = SIPrefixes.getSiPrefix("k");
        assertNotNull(kilo);
        assertEquals(1.0E3, kilo.getFactor(), 0.0);
        assertEquals("k", kilo.getDefaultTextualPrefix());
        assertEquals("k", kilo.getDefaultDisplayPrefix());
        assertNotNull(kilo.toString());

        // 2022 extreme positive prefix (quetta)
        SIPrefix quetta = SIPrefixes.getSiPrefix("Q");
        assertNotNull(quetta);
        assertEquals(1.0E30, quetta.getFactor(), 0.0);
        assertEquals("Q", quetta.getDefaultTextualPrefix());
        assertNotNull(quetta.toString());

        // 2022 extreme negative prefix (quecto)
        SIPrefix quecto = SIPrefixes.getSiPrefix("q");
        assertNotNull(quecto);
        assertEquals(1.0E-30, quecto.getFactor(), 0.0);
        assertEquals("q", quecto.getDefaultTextualPrefix());
        assertNotNull(quecto.toString());

        // micro: textual "mu", display "μ"
        SIPrefix micro = SIPrefixes.getSiPrefix("mu");
        assertNotNull(micro);
        assertEquals(1.0E-6, micro.getFactor(), 1e-18);
        assertEquals("mu", micro.getDefaultTextualPrefix());
        assertEquals("\u03BC", micro.getDefaultDisplayPrefix());
        assertNotNull(micro.toString());
    }

    /**
     * getSiPrefixPer(String) should return proper results for valid keys, including the empty string "" to indicate "no SI
     * prefix" in the per-unit set.
     */
    @Test
    @DisplayName("getSiPrefixPer: valid keys (incl. \"\") return correct prefix")
    public void testGetSiPrefixPerValidKeys()
    {
        // No per-prefix -> factor 1.0
        SIPrefix perNone = SIPrefixes.getSiPrefixPer("/");
        assertNotNull(perNone, "Empty key should return the no-prefix SIPrefix for PER_UNIT");
        assertEquals(1.0, perNone.getFactor(), 0.0);
        assertEquals("/", perNone.getDefaultTextualPrefix());
        assertEquals("/", perNone.getDefaultDisplayPrefix());

        // Typical reciprocal: per kilo -> 1e-3
        SIPrefix perKilo = SIPrefixes.getSiPrefixPer("/k");
        assertNotNull(perKilo);
        assertEquals(1.0E-3, perKilo.getFactor(), 0.0);
        assertEquals("/k", perKilo.getDefaultTextualPrefix());
        assertEquals("/k", perKilo.getDefaultDisplayPrefix());

        // 2022 extreme positive reciprocal: /Q -> 1e-30
        SIPrefix perQuetta = SIPrefixes.getSiPrefixPer("/Q");
        assertNotNull(perQuetta);
        assertEquals(1.0E-30, perQuetta.getFactor(), 0.0);

        // micro display form: "/μ"
        SIPrefix perMicro = SIPrefixes.getSiPrefixPer("/mu");
        assertNotNull(perMicro);
        assertEquals(1.0E6, perMicro.getFactor(), 0.0);
        assertEquals("/\u03BC", perMicro.getDefaultDisplayPrefix());
        
        // kilo-prefix
        assertEquals(1.0, SIPrefixes.getSiPrefixKilo("k").getFactor(), 0.0);
    }

    /**
     * Verify presence and numeric semantics for "" in the public maps now that you allow empty-string as the “no SI prefix”
     * marker.
     */
    @Test
    @DisplayName("Empty-string entries exist: UNIT/PER_UNIT/KILO and have expected factors")
    public void testEmptyStringEntriesInMaps()
    {
        // UNIT: "" => 1.0
        SIPrefix unitNone = SIPrefixes.UNIT_PREFIXES.get("");
        assertNotNull(unitNone, "UNIT_PREFIXES must contain \"\"");
        assertEquals(1.0, unitNone.getFactor(), 0.0);

        // PER_UNIT: "" => 1.0
        SIPrefix perUnitNone = SIPrefixes.PER_UNIT_PREFIXES.get("/");
        assertNotNull(perUnitNone, "PER_UNIT_PREFIXES must contain \"/\"");
        assertEquals(1.0, perUnitNone.getFactor(), 0.0);

        // KILO: "" => 1e-3 (offset for kilo-default)
        SIPrefix kiloNone = SIPrefixes.KILO_PREFIXES.get("");
        assertNotNull(kiloNone, "KILO_PREFIXES must contain \"\"");
        assertEquals(1.0E-3, kiloNone.getFactor(), 0.0);
    }

    /**
     * SIPrefixes enum toString() should equal the enum constant name, and valueOf(toString()) should round-trip to the same
     * constant.
     */
    @Test
    @DisplayName("SIPrefixes.toString() equals name() and valueOf(toString()) round-trips")
    public void testSiprefixesEnumToStringAndValueOf()
    {
        // Pick a couple of enum constants
        SIPrefixes u = SIPrefixes.UNIT;
        SIPrefixes pu = SIPrefixes.PER_UNIT;
        SIPrefixes k = SIPrefixes.KILO;

        assertEquals(u.name(), u.toString());
        assertEquals(pu.name(), pu.toString());
        assertEquals(k.name(), k.toString());

        assertSame(u, SIPrefixes.valueOf(u.toString()));
        assertSame(pu, SIPrefixes.valueOf(pu.toString()));
        assertSame(k, SIPrefixes.valueOf(k.toString()));
    }

    /**
     * Basic presence and values in UNIT prefixes map.
     */
    @Test
    @DisplayName("UNIT_PREFIXES: expected keys and representative values")
    public void testUnitPrefixesPresenceAndValues()
    {
        Map<String, SIPrefix> unit = SIPrefixes.UNIT_PREFIXES;
        assertNotNull(unit);
        assertFalse(unit.isEmpty());

        // Presence of common prefixes
        assertTrue(unit.containsKey("k"), "kilo present");
        assertTrue(unit.containsKey("M"), "mega present");
        assertTrue(unit.containsKey("G"), "giga present");
        assertTrue(unit.containsKey("m"), "milli present");
        assertTrue(unit.containsKey("c"), "centi present");
        assertTrue(unit.containsKey("da"), "deca present");
        assertTrue(unit.containsKey("Y"), "yotta present");
        assertTrue(unit.containsKey("y"), "yocto present");

        // Representative numeric values
        assertEquals(1.0E3, unit.get("k").getFactor(), 0.0);
        assertEquals(1.0E6, unit.get("M").getFactor(), 0.0);
        assertEquals(1.0E9, unit.get("G").getFactor(), 0.0);
        assertEquals(1.0E-3, unit.get("m").getFactor(), 0.0);
        assertEquals(1.0E-2, unit.get("c").getFactor(), 0.0);
        assertEquals(1.0E1, unit.get("da").getFactor(), 0.0);
        assertEquals(1.0E24, unit.get("Y").getFactor(), 0.0);
        assertEquals(1.0E-24, unit.get("y").getFactor(), 0.0);
    }

    /**
     * PER_UNIT (reciprocal) prefixes map: presence and representative reciprocal values.
     */
    @Test
    @DisplayName("PER_UNIT_PREFIXES: reciprocal values")
    public void testPerUnitPrefixesValues()
    {
        Map<String, SIPrefix> per = SIPrefixes.PER_UNIT_PREFIXES;
        assertNotNull(per);
        assertFalse(per.isEmpty());

        // Presence of per nano and per kilo
        assertTrue(per.containsKey("/n"), "per nano present");
        assertTrue(per.containsKey("/k"), "per kilo present");

        // 2022 additions (reciprocal)
        assertEquals(1.0E30, per.get("/q").getFactor(), 0.0);
        assertEquals(1.0E27, per.get("/r").getFactor(), 0.0);
        assertEquals(1.0E-27, per.get("/R").getFactor(), 0.0);
        assertEquals(1.0E-30, per.get("/Q").getFactor(), 0.0);

        // Reciprocal values: per nano is 1e9, per kilo is 1e-3
        assertEquals(1.0E9, per.get("/n").getFactor(), 0.0);
        assertEquals(1.0E-3, per.get("/k").getFactor(), 0.0);

        // Micro display reciprocal "/μ"
        SIPrefix perMicro = per.get("/mu");
        assertNotNull(perMicro);
        assertEquals("/\u03BC", perMicro.getDefaultDisplayPrefix());
        assertEquals(1.0E6, perMicro.getFactor(), 0.0);
    }

    /**
     * KILO-default maps: KILO and PER_KILO.
     * <ul>
     * <li>KILO_PREFIXES: "" (empty) is 1e-3 relative to kilo-default; large prefixes scale positively.</li>
     * <li>PER_KILO_PREFIXES: "/" is 1e3; "/k" is 1.0 identity for per‑kilo.</li>
     * <li>getSiPrefixKilo("k") should be identity → factor 1.0 (this currently requires adding "k" to KILO_PREFIXES).</li>
     * </ul>
     */
    @Test
    @DisplayName("KILO & PER_KILO: offset behavior and identity at 'k' / '/k'")
    public void testKiloAndPerKiloBehavior()
    {
        Map<String, SIPrefix> kilo = SIPrefixes.KILO_PREFIXES;
        Map<String, SIPrefix> perKilo = SIPrefixes.PER_KILO_PREFIXES;

        assertNotNull(kilo);
        assertNotNull(perKilo);
        assertFalse(kilo.isEmpty());
        assertFalse(perKilo.isEmpty());

        // "" (no textual prefix) is 1e-3 vs kilo-default
        assertEquals(1.0E-3, kilo.get("").getFactor(), 0.0);

        // Representative positive/negative offsets around kilo
        assertEquals(1.0E3, kilo.get("M").getFactor(), 0.0); // mega relative to kilo
        assertEquals(1.0E-6, kilo.get("m").getFactor(), 0.0); // milli relative to kilo

        // PER_KILO: "/" is 1e3; "/k" is identity
        assertEquals(1.0E3, perKilo.get("/").getFactor(), 0.0);
        assertEquals(1.0, perKilo.get("/k").getFactor(), 0.0);

        // Desired behavior for getSiPrefixKilo("k"): identity (1.0).
        // NOTE: At the moment KILO_PREFIXES does not contain "k", so this will return null and the next line should fail,
        // prompting addition of ("k" -> new SIPrefix("k", "kilo", 1.0)) to KILO_PREFIXES.
        assertEquals(1.0, SIPrefixes.getSiPrefixKilo("k").getFactor(), 0.0);
    }

    /**
     * UNIT_POS prefixes contain only factors > 1 (no milli/centi/...).
     */
    @Test
    @DisplayName("UNIT_POS contains only >1 prefixes")
    public void testUnitPosContainsOnlyPositivePrefixes()
    {
        Map<String, SIPrefix> up = SIPrefixes.UNIT_POS_PREFIXES;
        assertNotNull(up);
        assertFalse(up.isEmpty());

        // Should include larger-than-one prefixes
        assertTrue(up.containsKey("da"));
        assertTrue(up.containsKey("k"));
        assertTrue(up.containsKey("M"));
        assertTrue(up.containsKey("Q"));

        // Should NOT include sub-unit prefixes
        assertFalse(up.containsKey("m"));
        assertFalse(up.containsKey("c"));
        assertFalse(up.containsKey("mu"));
        assertFalse(up.containsKey("n"));
    }

    /**
     * FACTORS map: exponent → prefix.
     * <ul>
     * <li>3 → kilo</li>
     * <li>-6 → micro</li>
     * <li>0 → identity (empty textual/display)</li>
     * </ul>
     */
    @Test
    @DisplayName("FACTORS map: exponent resolution")
    public void testFactorsExponentResolution()
    {

        assertEquals(1.0E30, SIPrefixes.FACTORS.get(30).getFactor(), 0.0); // quetta
        assertEquals(1.0E27, SIPrefixes.FACTORS.get(27).getFactor(), 0.0); // ronna
        assertEquals(1.0, SIPrefixes.FACTORS.get(0).getFactor(), 0.0); // identity
        assertEquals(1.0E-27, SIPrefixes.FACTORS.get(-27).getFactor(), 0.0); // ronto
        assertEquals(1.0E-30, SIPrefixes.FACTORS.get(-30).getFactor(), 0.0); // quecto

        SIPrefix plus3 = SIPrefixes.FACTORS.get(3);
        SIPrefix minus6 = SIPrefixes.FACTORS.get(-6);
        SIPrefix zero = SIPrefixes.FACTORS.get(0);

        assertNotNull(plus3);
        assertEquals(1.0E3, plus3.getFactor(), 0.0);
        assertEquals("k", plus3.getDefaultTextualPrefix());

        assertNotNull(minus6);
        assertEquals(1.0E-6, minus6.getFactor(), 1e-18);
        assertEquals("mu", minus6.getDefaultTextualPrefix());

        assertNotNull(zero);
        assertEquals(1.0, zero.getFactor(), 0.0);
        assertTrue(zero.getDefaultTextualPrefix().isEmpty());
        assertTrue(zero.getDefaultDisplayPrefix().isEmpty());
    }

    /**
     * Public maps should be unmodifiable.
     */
    @Test
    @DisplayName("All public maps are unmodifiable")
    public void testMapsAreUnmodifiable()
    {
        assertThrows(UnsupportedOperationException.class, () -> SIPrefixes.UNIT_PREFIXES.put("X", new SIPrefix("X", "x", 2.0)));
        assertThrows(UnsupportedOperationException.class,
                () -> SIPrefixes.PER_UNIT_PREFIXES.put("/X", new SIPrefix("/X", "per x", 0.5)));
        assertThrows(UnsupportedOperationException.class,
                () -> SIPrefixes.UNIT_POS_PREFIXES.put("X", new SIPrefix("X", "x", 2.0)));
        assertThrows(UnsupportedOperationException.class, () -> SIPrefixes.KILO_PREFIXES.put("X", new SIPrefix("X", "x", 2.0)));
        assertThrows(UnsupportedOperationException.class,
                () -> SIPrefixes.PER_KILO_PREFIXES.put("/X", new SIPrefix("/X", "per x", 2.0)));
    }

    /**
     * Lookup functions: desired public input checking (null/blank/unknown).
     * <p>
     * These assertions encode what should be enforced on the public API:
     * <ul>
     * <li>{@code null} → {@link NullPointerException}</li>
     * <li>blank → {@link IllegalArgumentException}</li>
     * <li>unknown → {@link IllegalArgumentException}</li>
     * <li>blank is valid only for KILO-lookup of "" key (handled in a separate test)</li>
     * </ul>
     * Current implementation returns {@code null} for unknown keys; this test will guide you to add the input checks in
     * {@code getSiPrefix}, {@code getSiPrefixPer}, {@code getSiPrefixKilo}.
     */
    @Test
    @DisplayName("Lookup methods should validate inputs (NPE/IAE)")
    public void testLookupInputValidationDesired()
    {
        // null ⇒ NPE
        assertThrows(NullPointerException.class, () -> SIPrefixes.getSiPrefix(null));
        assertThrows(NullPointerException.class, () -> SIPrefixes.getSiPrefixPer(null));
        assertThrows(NullPointerException.class, () -> SIPrefixes.getSiPrefixKilo(null));

        // unknown ⇒ IAE
        assertThrows(IllegalArgumentException.class, () -> SIPrefixes.getSiPrefix("X"));
        assertThrows(IllegalArgumentException.class, () -> SIPrefixes.getSiPrefixPer("/X"));
        assertThrows(IllegalArgumentException.class, () -> SIPrefixes.getSiPrefixKilo("X"));
    }

    /**
     * Enum constants presence and basic invariants.
     */
    @Test
    @DisplayName("Enum presence: NONE, UNIT, UNIT_POS, PER_UNIT, KILO")
    public void testEnumConstantsPresence()
    {
        SIPrefixes[] all = SIPrefixes.values();
        assertTrue(all.length >= 5);

        // Basic presence checks
        boolean hasNone = false, hasUnit = false, hasUnitPos = false, hasPerUnit = false, hasKilo = false;
        for (SIPrefixes p : all)
        {
            hasNone |= p == SIPrefixes.NONE;
            hasUnit |= p == SIPrefixes.UNIT;
            hasUnitPos |= p == SIPrefixes.UNIT_POS;
            hasPerUnit |= p == SIPrefixes.PER_UNIT;
            hasKilo |= p == SIPrefixes.KILO;
        }
        assertTrue(hasNone && hasUnit && hasUnitPos && hasPerUnit && hasKilo);
    }
}
