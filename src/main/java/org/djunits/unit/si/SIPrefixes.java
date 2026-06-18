package org.djunits.unit.si;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.djutils.exceptions.Throw;

/**
 * Useful sets of SI prefixes.
 * <p>
 * Copyright (c) 2019-2026 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * @author Alexander Verbraeck
 */
public enum SIPrefixes
{
    /** No SI prefixes allowed. E.g., for the "inch". */
    NONE,

    /** All standard SI prefixes allowed. E.g., for the "meter". */
    UNIT,

    /** All standard SI prefixes allowed for "per unit". E.g., for the "per second". */
    PER_UNIT,

    /** SI prefixes allowed, but default starts with "kilo" / "k", e.g., for the "kilogram". */
    KILO;

    /** The SI prefixes and their values for the "UNIT" settings. */
    public static final Map<String, SIPrefix> UNIT_PREFIXES;

    /** The SI prefixes and their values for the "PER_UNIT" settings. */
    public static final Map<String, SIPrefix> PER_UNIT_PREFIXES;

    /** The SI prefixes and their values for the "KILO" settings. */
    public static final Map<String, SIPrefix> KILO_PREFIXES;

    /** The SI prefixes and their values for the "PER_KILO" settings. */
    public static final Map<String, SIPrefix> PER_KILO_PREFIXES;

    /** The map that translates an SI prefix power to the SI Prefix. */
    public static final Map<Integer, SIPrefix> FACTORS;

    static
    {
        Map<String, SIPrefix> unitPrefixes = new LinkedHashMap<>();
        unitPrefixes.put("q", new SIPrefix("q", "quecto", -30, PrefixType.UNIT));
        unitPrefixes.put("r", new SIPrefix("r", "ronto", -27, PrefixType.UNIT));
        unitPrefixes.put("y", new SIPrefix("y", "yocto", -24, PrefixType.UNIT));
        unitPrefixes.put("z", new SIPrefix("z", "zepto", -21, PrefixType.UNIT));
        unitPrefixes.put("a", new SIPrefix("a", "atto", -18, PrefixType.UNIT));
        unitPrefixes.put("f", new SIPrefix("f", "femto", -15, PrefixType.UNIT));
        unitPrefixes.put("p", new SIPrefix("p", "pico", -12, PrefixType.UNIT));
        unitPrefixes.put("n", new SIPrefix("n", "nano", -9, PrefixType.UNIT));
        unitPrefixes.put("mu", new SIPrefix("mu", "micro", -6, "\u03BC", PrefixType.UNIT));
        unitPrefixes.put("m", new SIPrefix("m", "milli", -3, PrefixType.UNIT));
        unitPrefixes.put("c", new SIPrefix("c", "centi", -2, PrefixType.UNIT));
        unitPrefixes.put("d", new SIPrefix("d", "deci", -1, PrefixType.UNIT));

        unitPrefixes.put("", new SIPrefix("", "", 0, PrefixType.UNIT));

        unitPrefixes.put("da", new SIPrefix("da", "deca", 1, PrefixType.UNIT));
        unitPrefixes.put("h", new SIPrefix("h", "hecto", 2, PrefixType.UNIT));
        unitPrefixes.put("k", new SIPrefix("k", "kilo", 3, PrefixType.UNIT));
        unitPrefixes.put("M", new SIPrefix("M", "mega", 6, PrefixType.UNIT));
        unitPrefixes.put("G", new SIPrefix("G", "giga", 9, PrefixType.UNIT));
        unitPrefixes.put("T", new SIPrefix("T", "tera", 12, PrefixType.UNIT));
        unitPrefixes.put("P", new SIPrefix("P", "peta", 15, PrefixType.UNIT));
        unitPrefixes.put("E", new SIPrefix("E", "exa", 18, PrefixType.UNIT));
        unitPrefixes.put("Z", new SIPrefix("Z", "zetta", 21, PrefixType.UNIT));
        unitPrefixes.put("Y", new SIPrefix("Y", "yotta", 24, PrefixType.UNIT));
        unitPrefixes.put("R", new SIPrefix("R", "ronna", 27, PrefixType.UNIT));
        unitPrefixes.put("Q", new SIPrefix("Q", "quetta", 30, PrefixType.UNIT));
        UNIT_PREFIXES = Collections.unmodifiableMap(unitPrefixes);

        Map<Integer, SIPrefix> factorMap = new LinkedHashMap<>();
        for (SIPrefix siPrefix : UNIT_PREFIXES.values())
        {
            String s = String.format("%e", siPrefix.getFactor());
            Integer key = Integer.parseInt(s.substring(s.indexOf("e") + 1));
            factorMap.put(key, siPrefix);
        }
        factorMap.put(0, new SIPrefix("", "", 0, PrefixType.UNIT));
        FACTORS = factorMap;

        Map<String, SIPrefix> perUnitPrefixes = new LinkedHashMap<>();
        perUnitPrefixes.put("/q", new SIPrefix("/q", "per quecto", 30, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/r", new SIPrefix("/r", "per ronto", 27, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/y", new SIPrefix("/y", "per yocto", 24, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/z", new SIPrefix("/z", "per zepto", 21, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/a", new SIPrefix("/a", "per atto", 18, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/f", new SIPrefix("/f", "per femto", 15, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/p", new SIPrefix("/p", "per pico", 12, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/n", new SIPrefix("/n", "per nano", 9, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/mu", new SIPrefix("/mu", "per micro", 6, "/\u03BC", PrefixType.PER_UNIT));
        perUnitPrefixes.put("/m", new SIPrefix("/m", "per milli", 3, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/c", new SIPrefix("/c", "per centi", 2, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/d", new SIPrefix("/d", "per deci", 1, PrefixType.PER_UNIT));

        perUnitPrefixes.put("/", new SIPrefix("/", "per", 0, PrefixType.PER_UNIT));

        perUnitPrefixes.put("/da", new SIPrefix("/da", "per deca", -1, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/h", new SIPrefix("/h", "per hecto", -2, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/k", new SIPrefix("/k", "per kilo", -3, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/M", new SIPrefix("/M", "per mega", -6, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/G", new SIPrefix("/G", "per giga", -9, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/T", new SIPrefix("/T", "per tera", -12, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/P", new SIPrefix("/P", "per peta", -15, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/E", new SIPrefix("/E", "per exa", -18, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/Z", new SIPrefix("/Z", "per zetta", -21, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/Y", new SIPrefix("/Y", "per yotta", -24, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/R", new SIPrefix("/R", "per ronna", -27, PrefixType.PER_UNIT));
        perUnitPrefixes.put("/Q", new SIPrefix("/Q", "per quetta", -30, PrefixType.PER_UNIT));
        PER_UNIT_PREFIXES = Collections.unmodifiableMap(perUnitPrefixes);

        Map<String, SIPrefix> kiloPrefixes = new LinkedHashMap<>();
        kiloPrefixes.put("q", new SIPrefix("q", "quecto", -33, PrefixType.KILO));
        kiloPrefixes.put("r", new SIPrefix("r", "ronto", -30, PrefixType.KILO));
        kiloPrefixes.put("y", new SIPrefix("y", "yocto", -27, PrefixType.KILO));
        kiloPrefixes.put("z", new SIPrefix("z", "zepto", -24, PrefixType.KILO));
        kiloPrefixes.put("a", new SIPrefix("a", "atto", -21, PrefixType.KILO));
        kiloPrefixes.put("f", new SIPrefix("f", "femto", -18, PrefixType.KILO));
        kiloPrefixes.put("p", new SIPrefix("p", "pico", -15, PrefixType.KILO));
        kiloPrefixes.put("n", new SIPrefix("n", "nano", -12, PrefixType.KILO));
        kiloPrefixes.put("mu", new SIPrefix("mu", "micro", -9, "\u03BC", PrefixType.KILO));
        kiloPrefixes.put("m", new SIPrefix("m", "milli", -6, PrefixType.KILO));
        kiloPrefixes.put("c", new SIPrefix("c", "centi", -5, PrefixType.KILO));
        kiloPrefixes.put("d", new SIPrefix("d", "deci", -4, PrefixType.KILO));
        kiloPrefixes.put("", new SIPrefix("", "", -3, PrefixType.KILO));
        kiloPrefixes.put("da", new SIPrefix("da", "deca", -2, PrefixType.KILO));
        kiloPrefixes.put("h", new SIPrefix("h", "hecto", -1, PrefixType.KILO));

        kiloPrefixes.put("k", new SIPrefix("k", "kilo", 0, PrefixType.KILO));

        kiloPrefixes.put("M", new SIPrefix("M", "mega", 3, PrefixType.KILO));
        kiloPrefixes.put("G", new SIPrefix("G", "giga", 6, PrefixType.KILO));
        kiloPrefixes.put("T", new SIPrefix("T", "tera", 9, PrefixType.KILO));
        kiloPrefixes.put("P", new SIPrefix("P", "peta", 12, PrefixType.KILO));
        kiloPrefixes.put("E", new SIPrefix("E", "exa", 15, PrefixType.KILO));
        kiloPrefixes.put("Z", new SIPrefix("Z", "zetta", 18, PrefixType.KILO));
        kiloPrefixes.put("Y", new SIPrefix("Y", "yotta", 21, PrefixType.KILO));
        kiloPrefixes.put("R", new SIPrefix("R", "ronna", 24, PrefixType.KILO));
        kiloPrefixes.put("Q", new SIPrefix("Q", "quetta", 27, PrefixType.KILO));
        KILO_PREFIXES = Collections.unmodifiableMap(kiloPrefixes);

        Map<String, SIPrefix> perKiloPrefixes = new LinkedHashMap<>();
        perKiloPrefixes.put("/q", new SIPrefix("/q", "per quecto", 33, PrefixType.PER_KILO));
        perKiloPrefixes.put("/r", new SIPrefix("/r", "per ronto", 30, PrefixType.PER_KILO));
        perKiloPrefixes.put("/y", new SIPrefix("/y", "per yocto", 27, PrefixType.PER_KILO));
        perKiloPrefixes.put("/z", new SIPrefix("/z", "per zepto", 24, PrefixType.PER_KILO));
        perKiloPrefixes.put("/a", new SIPrefix("/a", "per atto", 21, PrefixType.PER_KILO));
        perKiloPrefixes.put("/f", new SIPrefix("/f", "per femto", 18, PrefixType.PER_KILO));
        perKiloPrefixes.put("/p", new SIPrefix("/p", "per pico", 15, PrefixType.PER_KILO));
        perKiloPrefixes.put("/n", new SIPrefix("/n", "per nano", 12, PrefixType.PER_KILO));
        perKiloPrefixes.put("/mu", new SIPrefix("/mu", "per micro", 9, "/\u03BC", PrefixType.PER_KILO));
        perKiloPrefixes.put("/m", new SIPrefix("/m", "per milli", 6, PrefixType.PER_KILO));
        perKiloPrefixes.put("/c", new SIPrefix("/c", "per centi", 5, PrefixType.PER_KILO));
        perKiloPrefixes.put("/d", new SIPrefix("/d", "per deci", 4, PrefixType.PER_KILO));
        perKiloPrefixes.put("/", new SIPrefix("/", "per ", 3, PrefixType.PER_KILO));
        perKiloPrefixes.put("/da", new SIPrefix("/da", "per deca", 2, PrefixType.PER_KILO));
        perKiloPrefixes.put("/h", new SIPrefix("/h", "per hecto", 1, PrefixType.PER_KILO));

        perKiloPrefixes.put("/k", new SIPrefix("/k", "per kilo", 0, PrefixType.PER_KILO));
        perKiloPrefixes.put("/M", new SIPrefix("/M", "per mega", -3, PrefixType.PER_KILO));
        perKiloPrefixes.put("/G", new SIPrefix("/G", "per giga", -6, PrefixType.PER_KILO));
        perKiloPrefixes.put("/T", new SIPrefix("/T", "per tera", -9, PrefixType.PER_KILO));
        perKiloPrefixes.put("/P", new SIPrefix("/P", "per peta", -12, PrefixType.PER_KILO));
        perKiloPrefixes.put("/E", new SIPrefix("/E", "per exa", -15, PrefixType.PER_KILO));
        perKiloPrefixes.put("/Z", new SIPrefix("/Z", "per zetta", -18, PrefixType.PER_KILO));
        perKiloPrefixes.put("/Y", new SIPrefix("/Y", "per yotta", -21, PrefixType.PER_KILO));
        perKiloPrefixes.put("/R", new SIPrefix("/R", "per ronna", -24, PrefixType.PER_KILO));
        perKiloPrefixes.put("/Q", new SIPrefix("/Q", "per quetta", -27, PrefixType.PER_KILO));
        PER_KILO_PREFIXES = Collections.unmodifiableMap(perKiloPrefixes);
    }

    /**
     * Look up and return the prefix information for the given prefix key (e.g., "G" for "giga").
     * @param prefixKey the prefix key, e.g., "G" for "giga"
     * @return the SIPrefix information
     * @throws IllegalArgumentException when the <code>prefixKey</code> does not exist
     */
    public static SIPrefix getSiPrefix(final String prefixKey)
    {
        Throw.whenNull(prefixKey, "prefixKey cannot be null");
        SIPrefix p = UNIT_PREFIXES.get(prefixKey);
        Throw.when(p == null, IllegalArgumentException.class, "Unknown SI prefix: %s", prefixKey);
        return p;
    }

    /**
     * Look up and return the prefix information for the given prefix key (e.g., "/n" for "per nano").
     * @param prefixKey the prefix key, e.g., "/n" for "per nano"
     * @return the SIPrefix information
     * @throws IllegalArgumentException when the <code>prefixKey</code> does not exist
     */
    public static SIPrefix getSiPrefixPer(final String prefixKey)
    {
        Throw.whenNull(prefixKey, "prefixKey cannot be null");
        SIPrefix p = PER_UNIT_PREFIXES.get(prefixKey);
        Throw.when(p == null, IllegalArgumentException.class, "Unknown SI prefix for 'per': %s", prefixKey);
        return p;
    }

    /**
     * Return the prefix information for the given prefix key (e.g., "G" for "giga"), with an offset of a factor 1000 for units
     * that have "kilo" as the default.
     * @param prefixKey the prefix key, e.g., "G" for "giga"
     * @return the SIPrefix information, with an offset of 1000. So "k" will return 1, and "" will return 1.0E-3.
     * @throws IllegalArgumentException when the <code>prefixKey</code> does not exist
     */
    public static SIPrefix getSiPrefixKilo(final String prefixKey)
    {
        Throw.whenNull(prefixKey, "prefixKey cannot be null");
        SIPrefix p = KILO_PREFIXES.get(prefixKey);
        Throw.when(p == null, IllegalArgumentException.class, "Unknown SI prefix for 'kilo': %s", prefixKey);
        return p;
    }

    /**
     * Return the prefix information for the given prefix key (e.g., "/G" for "per giga"), with an offset of a factor 1000 for
     * units that have "per kilo" as the default.
     * @param prefixKey the prefix key, e.g., "/G" for "per giga"
     * @return the SIPrefix information, with an offset of 1000. So "/k" will return 1, and "" will return 1.0E3.
     * @throws IllegalArgumentException when the <code>prefixKey</code> does not exist
     */
    public static SIPrefix getSiPrefixPerKilo(final String prefixKey)
    {
        Throw.whenNull(prefixKey, "prefixKey cannot be null");
        SIPrefix p = PER_KILO_PREFIXES.get(prefixKey);
        Throw.when(p == null, IllegalArgumentException.class, "Unknown SI prefix for 'per kilo': %s", prefixKey);
        return p;
    }

}
