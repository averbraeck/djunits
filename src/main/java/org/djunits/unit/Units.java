package org.djunits.unit;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.djunits.quantity.AbsorbedDose;
import org.djunits.quantity.Acceleration;
import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.Length;
import org.djunits.quantity.Speed;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.system.UnitSystem;
import org.djutils.exceptions.Throw;

/**
 * Units is a static class that gives access to strongly typed units, such as <code>Length.km</code>. The Units class is
 * responsible for maintaining a registry of all units based on their textual abbreviations. It allows for a unit to register
 * itself, and for code to retrieve a unit based on a textual abbreviation. The Units class also takes care of localization of
 * the unit representations. If the Locale is not US, it will look for a resource bundle of the active Locale to see if
 * localized textual abbreviations are registered, and it will use these when resolving the unit. When no localized matches can
 * be found, it will test the (default) US Locale abbreviations as well.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@SuppressWarnings({"checkstyle:leftcurly", "checkstyle:rightcurly", "checkstyle:constantname", "checkstyle:whitespacearound",
        "checkstyle:javadocvariable", "checkstyle:linelength"})
public final class Units
{
    /** Current map locale. */
    private static Locale currentLocale = Locale.US;

    /** Localized map to translate international id to US id. */
    private static Map<String, String> localeTranslateMap = new LinkedHashMap<>();

    /** Map with all units per unit type. */
    private static final Map<Class<?>, Map<String, UnitInterface<?>>> unitMap = new LinkedHashMap<>();

    /** Constant for the foot. */
    private static final double const_ft = 0.3048;

    /** Constant for the yard. */
    private static final double const_yd = 3.0 * const_ft;

    /** Constant for the inch. */
    private static final double const_in = const_ft / 12.0;

    /** Constant for the mile. */
    private static final double const_mi = 5280.0 * const_ft;

    /** Constant for the nautical mile. */
    private static final double const_NM = 1852.0;

    // @formatter:off

    /** */ private Units() {}

    /* **************************************************************************************** */
    /* ************************************* ABSORBED DOSE ************************************ */
    /* **************************************************************************************** */

    /** Gray. */
    public static final AbsorbedDose.Unit gray = new AbsorbedDose.Unit("Gy", "gray", 1.0, UnitSystem.SI_DERIVED);

    /** mGy. */
    public static final AbsorbedDose.Unit milligray = 
            new AbsorbedDose.Unit("mGy", "milligray", 1.0E-3, UnitSystem.SI_DERIVED);

    /** &#181;Gy. */
    public static final AbsorbedDose.Unit microgray = 
            new AbsorbedDose.Unit(List.of("muGy"), "\u03BCGy", "microgray", new LinearScale(1.0E-6), UnitSystem.SI_DERIVED);

    /** erg/g. */
    public static final AbsorbedDose.Unit erg_per_gram = 
            new AbsorbedDose.Unit("erg/g", "erg per gram", 1.0E-4, UnitSystem.CGS);

    /** rad. */
    public static final AbsorbedDose.Unit rad = new AbsorbedDose.Unit("rad", "rad", 1.0E-2, UnitSystem.CGS);

    /* **************************************************************************************** */
    /* ************************************* ACCELERATION ************************************* */
    /* **************************************************************************************** */

    /** m/s2. */
    public static final Acceleration.Unit meter_per_second2 = new Acceleration.Unit(List.of("m/s2", "m/s^2", "m/sec2", "m/sec^2"), 
            "m/s2", "meter per second squared", IdentityScale.SCALE, UnitSystem.SI_BASE);

    /** km/h2. */
    public static final Acceleration.Unit km_per_hour2 = new Acceleration.Unit(List.of("km/h2", "km/h^2", "km/hour2", "km/hour^2"), 
            "km/h2", "kilometer per hour squared", new LinearScale(1000.0, 3600.0 * 3600.0), UnitSystem.SI_ACCEPTED);

    /** ft/s2. */
    public static final Acceleration.Unit foot_per_second2 = new Acceleration.Unit(List.of("ft/s2", "ft/s^2", "foot/sec2", "foot/sec^2"), 
            "ft/s2", "foot per second squared", new LinearScale(const_ft), UnitSystem.IMPERIAL);

    /** in/s2. */
    public static final Acceleration.Unit inch_per_second2 = new Acceleration.Unit(List.of("in/s2", "in/s^2", "inch/sec2", "inch/sec^2"), 
            "in/s2", "inch per second squared", new LinearScale(const_in), UnitSystem.IMPERIAL);

    /** mi/h2. */
    public static final Acceleration.Unit mile_per_hour2 = new Acceleration.Unit(List.of("mi/h2", "mi/h^2", "mile/hour2", "mile/hour^2"), 
            "mi/h2", "mile per hour squared", new LinearScale(const_mi, 3600.0 * 3600.0), UnitSystem.IMPERIAL);

    /** mi/s2. */
    public static final Acceleration.Unit mile_per_second2 = new Acceleration.Unit(List.of("mi/s2", "mi/s^2", "mile/sec2", "mile/sec^2"), 
            "mi/s2", "mile per second squared", new LinearScale(const_mi), UnitSystem.IMPERIAL);

    /** kt/s = Nautical Mile / h / s. */
    public static final Acceleration.Unit knot_per_second = new Acceleration.Unit(List.of("kt/s", "kt/sec", "knot/s", "knot/sec"), 
            "kt/s", "knot per second", new LinearScale(const_NM, 3600.0), UnitSystem.OTHER);

    /** mi/h/s. */
    public static final Acceleration.Unit mile_per_hour_per_second = 
            new Acceleration.Unit(List.of("mi/h/s", "mi/hr/sec", "mile/hour/sec"), 
            "mi/h/s", "mile per hour per second", new LinearScale(const_mi, 3600.0), UnitSystem.IMPERIAL);

    /** The standard gravity. */
    public static final Acceleration.Unit standard_gravity = new Acceleration.Unit("g", "standard gravity", 9.80665, UnitSystem.OTHER);

    /** Gal or cm/s. */
    public static final Acceleration.Unit gal = new Acceleration.Unit("Gal", "gal", 0.01, UnitSystem.CGS);

    /* **************************************************************************************** */
    /* ************************************* DIMENSIONLESS ************************************ */
    /* **************************************************************************************** */

    /** The dimensionless unit has a blank unit symbol. */ 
    public static final Dimensionless.Unit one = new Dimensionless.Unit("", "", 1.0, UnitSystem.OTHER); 

    /* **************************************************************************************** */
    /* **************************************** LENGTH **************************************** */
    /* **************************************************************************************** */
    
    /** */ public static final Length.Unit m = new Length.Unit("m", "meter", 1.0, UnitSystem.SI_BASE);
    /** */ public static final Length.Unit meter = m.generateSiPrefixes(false, false);
    /** */ public static final Length.Unit dam = resolve(Length.Unit.class, "dam");
    /** */ public static final Length.Unit hm = resolve(Length.Unit.class, "hm");
    /** */ public static final Length.Unit km = resolve(Length.Unit.class, "km");
    /** */ public static final Length.Unit dm = resolve(Length.Unit.class, "dm");
    /** */ public static final Length.Unit cm = resolve(Length.Unit.class, "cm");
    /** */ public static final Length.Unit mm = resolve(Length.Unit.class, "mm");
    /** */ public static final Length.Unit mum = resolve(Length.Unit.class, "mum");
    /** */ public static final Length.Unit nm = resolve(Length.Unit.class, "nm");
    /** */ public static final Length.Unit pm = resolve(Length.Unit.class, "pm");
    /** */ public static final Length.Unit am = resolve(Length.Unit.class, "am");
    /** */ public static final Length.Unit fm = resolve(Length.Unit.class, "fm");
    
    /** foot (international) = 0.3048 m = 1/3 yd = 12 inches. */
    public static final Length.Unit foot = new Length.Unit(List.of("ft", "foot", "'"), "ft", "foot", 
            new LinearScale(const_ft), UnitSystem.IMPERIAL); 

    /** inch (international) = 2.54 cm = 1/36 yd = 1/12 ft. */
    public static final Length.Unit inch = new Length.Unit(List.of("in", "inch", "\""), "in", "inch", 
            new LinearScale(const_in), UnitSystem.IMPERIAL); 

    /** yard (international) = 0.9144 m = 3 ft = 36 in. */
    public static final Length.Unit yard = new Length.Unit(List.of("yd", "yard"), "yd", "yard", 
            new LinearScale(const_yd), UnitSystem.IMPERIAL); 

    /** mile (international) = 5280 ft = 1760 yd. */
    public static final Length.Unit mile = new Length.Unit(List.of("mi", "mile"), "mi", "mile", 
            new LinearScale(const_mi), UnitSystem.IMPERIAL); 

    /** nautical mile (international) = 1852 m. */
    public static final Length.Unit NM = new Length.Unit("NM", "Nautical Mile", const_NM, UnitSystem.OTHER);

    /** Astronomical Unit = 149,597,870,700 m. */
    public static final Length.Unit AU =
            new Length.Unit("AU", "Astronomical Unit", 149_597_870_700.0, UnitSystem.OTHER);

    /** Lightyear = 9,460,730,472,580,800 m. */
    public static final Length.Unit lightyear = 
            new Length.Unit("ly", "lightyear", 9_460_730_472_580_800.0, UnitSystem.OTHER);

    /** Parsec = 648,000 / PI ly. */
    public static final Length.Unit Parsec = 
            new Length.Unit("Pc", "Parsec", 9_460_730_472_580_800.0 * 648_000.0 / Math.PI, UnitSystem.OTHER);

    /** Angstrom = 10^-10 m. */
    public static final Length.Unit Angstrom = 
            new Length.Unit(List.of("A", "\u212B"), "\u212B", "Angstrom", new LinearScale(1.0E-10), UnitSystem.OTHER);

    /* **************************************************************************************** */
    /* ***************************************** SPEED **************************************** */
    /* **************************************************************************************** */

    /** m/s. */
    public static final Speed.Unit meter_per_second = 
            new Speed.Unit(List.of("m/s", "m/sec"), "m/s", "meter per second", IdentityScale.SCALE, UnitSystem.SI_BASE);

    /** m/h. */
    public static final Speed.Unit meter_per_hour = new Speed.Unit(List.of("m/h", "m/hr", "m/hour"), 
            "m/h", "meter per hour", new LinearScale(1.0, 3600.0), UnitSystem.SI_ACCEPTED);

    /** km/s. */
    public static final Speed.Unit km_per_second = new Speed.Unit(List.of("km/s", "km/sec"), 
            "km/s", "kilometer per second", new LinearScale(1000.0), UnitSystem.SI_ACCEPTED);

    /** km/h. */
    public static final Speed.Unit km_per_hour = new Speed.Unit(List.of("km/h", "km/hr", "km/hour"), 
            "km/h", "kilometer per hour", new LinearScale(1000.0, 3600.0), UnitSystem.SI_ACCEPTED);

    /** in/s. */
    public static final Speed.Unit inch_per_second = new Speed.Unit(List.of("in/s", "in/sec", "inch/s", "inch/sec"), 
            "in/s", "inch per second", new LinearScale(const_in), UnitSystem.IMPERIAL);

    /** in/min. */
    public static final Speed.Unit inch_per_minute = new Speed.Unit(List.of("in/min", "inch/min"), 
            "in/min", "inch per minute", new LinearScale(const_in, 60.0), UnitSystem.IMPERIAL);

    /** in/h. */
    public static final Speed.Unit inch_per_hour = new Speed.Unit(List.of("in/h", "in/hr", "in/hour", "inch/hour"), 
            "in/h", "inch per hour", new LinearScale(const_in, 3600.0), UnitSystem.IMPERIAL);

    /** ft/s. */
    public static final Speed.Unit foot_per_second = new Speed.Unit(List.of("ft/s", "ft/sec", "foot/s", "foot/sec"), 
            "ft/s", "foot per second", new LinearScale(const_ft), UnitSystem.IMPERIAL);

    /** ft/min. */
    public static final Speed.Unit foot_per_minute = new Speed.Unit(List.of("ft/min", "foot/min"), 
            "ft/min", "foot per minute", new LinearScale(const_ft, 60.0), UnitSystem.IMPERIAL);

    /** ft/h. */
    public static final Speed.Unit foot_per_hour = new Speed.Unit(List.of("ft/h", "ft/hr", "ft/hour", "foot/hour"), 
            "ft/h", "foot per hour", new LinearScale(const_ft, 3600.0), UnitSystem.IMPERIAL);
    
    /** mi/s. */
    public static final Speed.Unit mile_per_second = new Speed.Unit(List.of("mi/s", "mi/sec", "mile/s", "mile/sec"), 
            "mi/s", "mile per second", new LinearScale(const_mi), UnitSystem.IMPERIAL);

    /** mi/min. */
    public static final Speed.Unit mile_per_minute = new Speed.Unit(List.of("mi/min", "mile/min"), 
            "mi/min", "mile per minute", new LinearScale(const_mi, 60.0), UnitSystem.IMPERIAL);

    /** mi/h. */
    public static final Speed.Unit mile_per_hour = new Speed.Unit(List.of("mi/h", "mi/hr", "mi/hour", "mile/hour"), 
            "mi/h", "mile per hour", new LinearScale(const_mi, 3600.0), UnitSystem.IMPERIAL);

    /** knot = Nautical Mile per hour. */
    public static final Speed.Unit knot = new Speed.Unit("kt", "knot", const_NM / 3600.0, UnitSystem.OTHER);

    
    
    // @formatter:on

    /**
     * Register a unit so it can be found based on its textual abbreviations.
     * @param unit the unit to register
     */
    public static void register(final UnitInterface<?> unit)
    {
        Throw.whenNull(unit, "unit");
        var subMap = unitMap.computeIfAbsent(unit.getClass(), k -> new LinkedHashMap<String, UnitInterface<?>>());
        for (var key : unit.getTextualAbbreviations())
        {
            subMap.put(key, unit);
        }
    }

    /**
     * Look up a unit in the registry, based on its textual abbreviation.
     * @param unitClass the unit class for which the abbreviation has to be looked up
     * @param abbreviation the abbreviation to look up in the unit registry
     * @return the unit belonging to the abbreviation (if it exists)
     * @throws UnitRuntimeException when the unit did not exist, or the abbreviation was not registered
     * @param <U> the unit type
     */
    public static <U extends UnitInterface<U>> U resolve(final Class<U> unitClass, final String abbreviation)
            throws UnitRuntimeException
    {
        Throw.whenNull(abbreviation, "abbreviation");
        Throw.when(!unitMap.containsKey(unitClass), UnitRuntimeException.class,
                "Error resolving unit class %s (abbreviation '%s')", abbreviation, unitClass.getSimpleName());
        @SuppressWarnings("unchecked")
        U result = (U) unitMap.get(unitClass).get(abbreviation);
        Throw.when(result == null, UnitRuntimeException.class, "Error resolving abbreviation '%s' for unit class %s",
                abbreviation, unitClass.getSimpleName());
        return result;
    }

    /**
     * Return the proper class name for a unit class, also when it is a nested class. This method returns 'Length.Unit' rather
     * than 'Unit' for the inner 'Unit' class of the 'Length' class.
     * @param cls the unit class to return the name for, including the outer class name(s)
     * @return the class name, including the outer class name(s)
     */
    public static String unitClassName(final Class<?> cls)
    {
        return cls.getCanonicalName().substring(cls.getPackageName().isEmpty() ? 0 : cls.getPackageName().length() + 1);
    }

}
