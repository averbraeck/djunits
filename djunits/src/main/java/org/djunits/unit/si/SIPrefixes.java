package org.djunits.unit.si;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Useful sets of SI prefixes.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public enum SIPrefixes
{
    /** No SI prefixes allowed. E.g., for the "inch". */
    NONE,

    /** All standard SI prefixes allowed. E.g., for the "meter". */
    UNIT,

    /** All SI prefixes indicating larger than 1. E.g., for the electronVolt to avoid underflow with float values. */
    UNIT_POS,

    /** All standard SI prefixes allowed for "per unit". E.g., for the "per second". */
    PER_UNIT,

    /** SI prefixes allowed, but default starts with "kilo" / "k", e.g., for the "kilogram". */
    KILO;

    /** The SI prefixes and their values for the "UNIT" settings. */
    public static final Map<String, SIPrefix> UNIT_PREFIXES;

    /** The SI prefixes and their values for the "PER_UNIT" settings. */
    public static final Map<String, SIPrefix> PER_UNIT_PREFIXES;

    /** The larger than 1 SI prefixes and their values for the "UNIT_POS" settings. */
    public static final Map<String, SIPrefix> UNIT_POS_PREFIXES;

    /** The SI prefixes and their values for the "KILO" settings. */
    public static final Map<String, SIPrefix> KILO_PREFIXES;

    /** The map that translates an SI prefix power to the SI Prefix. */
    public static final Map<Integer, SIPrefix> FACTORS;

    static
    {
        Map<String, SIPrefix> unitPrefixes = new LinkedHashMap<>();
        unitPrefixes.put("y", new SIPrefix("y", "yocto", 1.0E-24));
        unitPrefixes.put("z", new SIPrefix("z", "zepto", 1.0E-21));
        unitPrefixes.put("a", new SIPrefix("a", "atto", 1.0E-18));
        unitPrefixes.put("f", new SIPrefix("f", "femto", 1.0E-15));
        unitPrefixes.put("p", new SIPrefix("p", "pico", 1.0E-12));
        unitPrefixes.put("n", new SIPrefix("n", "nano", 1.0E-9));
        unitPrefixes.put("mu", new SIPrefix("mu", "micro", 1.0E-6, "\u03BC"));
        unitPrefixes.put("m", new SIPrefix("m", "milli", 1.0E-3));
        unitPrefixes.put("c", new SIPrefix("c", "centi", 1.0E-2));
        unitPrefixes.put("d", new SIPrefix("d", "deci", 1.0E-1));

        unitPrefixes.put("da", new SIPrefix("da", "deca", 1.0E1));
        unitPrefixes.put("h", new SIPrefix("h", "hecto", 1.0E2));
        unitPrefixes.put("k", new SIPrefix("k", "kilo", 1.0E3));
        unitPrefixes.put("M", new SIPrefix("M", "mega", 1.0E6));
        unitPrefixes.put("G", new SIPrefix("G", "giga", 1.0E9));
        unitPrefixes.put("T", new SIPrefix("T", "tera", 1.0E12));
        unitPrefixes.put("P", new SIPrefix("P", "peta", 1.0E15));
        unitPrefixes.put("E", new SIPrefix("E", "exa", 1.0E18));
        unitPrefixes.put("Z", new SIPrefix("Z", "zetta", 1.0E21));
        unitPrefixes.put("Y", new SIPrefix("Y", "yotta", 1.0E24));
        UNIT_PREFIXES = Collections.unmodifiableMap(unitPrefixes);

        Map<Integer, SIPrefix> factorMap = new LinkedHashMap<>();
        for (SIPrefix siPrefix : UNIT_PREFIXES.values())
        {
            String s = String.format("%e", siPrefix.getFactor());
            // System.out.println(String.format("%s gets formatted as %s", siPrefix.getFactor(), s));
            Integer key = Integer.parseInt(s.substring(s.indexOf("e") + 1));
            factorMap.put(key, siPrefix);
        }
        factorMap.put(0, new SIPrefix("", "", 1.0));
        FACTORS = factorMap;

        Map<String, SIPrefix> perUnitPrefixes = new LinkedHashMap<>();
        perUnitPrefixes.put("/y", new SIPrefix("/y", "per yocto", 1.0E24));
        perUnitPrefixes.put("/z", new SIPrefix("/z", "per zepto", 1.0E21));
        perUnitPrefixes.put("/a", new SIPrefix("/a", "per atto", 1.0E18));
        perUnitPrefixes.put("/f", new SIPrefix("/f", "per femto", 1.0E15));
        perUnitPrefixes.put("/p", new SIPrefix("/p", "per pico", 1.0E12));
        perUnitPrefixes.put("/n", new SIPrefix("/n", "per nano", 1.0E9));
        perUnitPrefixes.put("/mu", new SIPrefix("/mu", "per micro", 1.0E6, "/\u03BC"));
        perUnitPrefixes.put("/m", new SIPrefix("/m", "per milli", 1.0E3));
        perUnitPrefixes.put("/c", new SIPrefix("/c", "per centi", 1.0E2));
        perUnitPrefixes.put("/d", new SIPrefix("/d", "per deci", 1.0E1));

        perUnitPrefixes.put("/da", new SIPrefix("/da", "per deca", 1.0E-1));
        perUnitPrefixes.put("/h", new SIPrefix("/h", "per hecto", 1.0E-2));
        perUnitPrefixes.put("/k", new SIPrefix("/k", "per kilo", 1.0E-3));
        perUnitPrefixes.put("/M", new SIPrefix("/M", "per mega", 1.0E-6));
        perUnitPrefixes.put("/G", new SIPrefix("/G", "per giga", 1.0E-9));
        perUnitPrefixes.put("/T", new SIPrefix("/T", "per tera", 1.0E-12));
        perUnitPrefixes.put("/P", new SIPrefix("/P", "per peta", 1.0E-15));
        perUnitPrefixes.put("/E", new SIPrefix("/E", "per exa", 1.0E-18));
        perUnitPrefixes.put("/Z", new SIPrefix("/Z", "per zetta", 1.0E-21));
        perUnitPrefixes.put("/Y", new SIPrefix("/Y", "per yotta", 1.0E-24));
        PER_UNIT_PREFIXES = Collections.unmodifiableMap(perUnitPrefixes);

        Map<String, SIPrefix> unitPosPrefixes = new LinkedHashMap<>();
        unitPosPrefixes.put("da", new SIPrefix("da", "deca", 1.0E1));
        unitPosPrefixes.put("h", new SIPrefix("h", "hecto", 1.0E2));
        unitPosPrefixes.put("k", new SIPrefix("k", "kilo", 1.0E3));
        unitPosPrefixes.put("M", new SIPrefix("M", "mega", 1.0E6));
        unitPosPrefixes.put("G", new SIPrefix("G", "giga", 1.0E9));
        unitPosPrefixes.put("T", new SIPrefix("T", "tera", 1.0E12));
        unitPosPrefixes.put("P", new SIPrefix("P", "peta", 1.0E15));
        unitPosPrefixes.put("E", new SIPrefix("E", "exa", 1.0E18));
        unitPosPrefixes.put("Z", new SIPrefix("Z", "zetta", 1.0E21));
        unitPosPrefixes.put("Y", new SIPrefix("Y", "yotta", 1.0E24));
        UNIT_POS_PREFIXES = Collections.unmodifiableMap(unitPosPrefixes);

        Map<String, SIPrefix> kiloPrefixes = new LinkedHashMap<>();
        kiloPrefixes.put("y", new SIPrefix("y", "yocto", 1.0E-27));
        kiloPrefixes.put("z", new SIPrefix("z", "zepto", 1.0E-24));
        kiloPrefixes.put("a", new SIPrefix("a", "atto", 1.0E-21));
        kiloPrefixes.put("f", new SIPrefix("f", "femto", 1.0E-18));
        kiloPrefixes.put("p", new SIPrefix("p", "pico", 1.0E-15));
        kiloPrefixes.put("n", new SIPrefix("n", "nano", 1.0E-12));
        kiloPrefixes.put("mu", new SIPrefix("mu", "micro", 1.0E-9, "\u03BC"));
        kiloPrefixes.put("m", new SIPrefix("m", "milli", 1.0E-6));
        kiloPrefixes.put("c", new SIPrefix("c", "centi", 1.0E-5));
        kiloPrefixes.put("d", new SIPrefix("d", "deci", 1.0E-4));
        kiloPrefixes.put("", new SIPrefix("", "", 1.0E-3));
        kiloPrefixes.put("da", new SIPrefix("da", "deca", 1.0E-2));
        kiloPrefixes.put("h", new SIPrefix("h", "hecto", 1.0E-1));

        kiloPrefixes.put("M", new SIPrefix("M", "mega", 1.0E3));
        kiloPrefixes.put("G", new SIPrefix("G", "giga", 1.0E6));
        kiloPrefixes.put("T", new SIPrefix("T", "tera", 1.0E9));
        kiloPrefixes.put("P", new SIPrefix("P", "peta", 1.0E12));
        kiloPrefixes.put("E", new SIPrefix("E", "exa", 1.0E15));
        kiloPrefixes.put("Z", new SIPrefix("Z", "zetta", 1.0E18));
        kiloPrefixes.put("Y", new SIPrefix("Y", "yotta", 1.0E21));
        KILO_PREFIXES = Collections.unmodifiableMap(kiloPrefixes);
    }

    /**
     * Look up and return the prefix information for the given prefix key (e.g., "G" for "giga").
     * @param prefixKey String; the prefix key, e.g., "G" for "giga"
     * @return SIPrefix; the SIPrefix information, or null if the <code>prefixKey</code> does not exist
     */
    public static SIPrefix getUnit(final String prefixKey)
    {
        return UNIT_PREFIXES.get(prefixKey);
    }

    /**
     * Look up and return the prefix information for the given prefix key (e.g., "/n" for "per nano").
     * @param prefixKey String; the prefix key, e.g., "/n" for "per nano"
     * @return SIPrefix; the SIPrefix information, or null if the <code>prefixKey</code> does not exist
     */
    public static SIPrefix getPerUnit(final String prefixKey)
    {
        return PER_UNIT_PREFIXES.get(prefixKey);
    }

    /**
     * Return the prefix information for the given prefix key (e.g., "G" for "giga"), with an offset of a factor 1000 for units
     * that have "kilo" as the default.
     * @param prefixKey String; the prefix key, e.g., "G" for "giga"
     * @return SIPrefix; the SIPrefix information, with an offset of 1000. So "k" will return 1, and "" will return 1.0E-3.
     */
    public static SIPrefix getKiloUnit(final String prefixKey)
    {
        return KILO_PREFIXES.get(prefixKey);
    }

}
