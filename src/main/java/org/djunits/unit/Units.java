package org.djunits.unit;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.djunits.quantity.AbsorbedDose;
import org.djunits.quantity.Acceleration;
import org.djunits.quantity.AmountOfSubstance;
import org.djunits.quantity.Angle;
import org.djunits.quantity.AngularAcceleration;
import org.djunits.quantity.AngularVelocity;
import org.djunits.quantity.Area;
import org.djunits.quantity.ArealObjectDensity;
import org.djunits.quantity.CatalyticActivity;
import org.djunits.quantity.Density;
import org.djunits.quantity.Duration;
import org.djunits.quantity.ElectricCharge;
import org.djunits.quantity.ElectricCurrent;
import org.djunits.quantity.ElectricPotential;
import org.djunits.quantity.ElectricalCapacitance;
import org.djunits.quantity.ElectricalConductance;
import org.djunits.quantity.ElectricalInductance;
import org.djunits.quantity.ElectricalResistance;
import org.djunits.quantity.Energy;
import org.djunits.quantity.EquivalentDose;
import org.djunits.quantity.FlowMass;
import org.djunits.quantity.FlowVolume;
import org.djunits.quantity.Force;
import org.djunits.quantity.Frequency;
import org.djunits.quantity.Illuminance;
import org.djunits.quantity.Length;
import org.djunits.quantity.LinearDensity;
import org.djunits.quantity.LinearObjectDensity;
import org.djunits.quantity.LuminousFlux;
import org.djunits.quantity.LuminousIntensity;
import org.djunits.quantity.MagneticFlux;
import org.djunits.quantity.MagneticFluxDensity;
import org.djunits.quantity.Mass;
import org.djunits.quantity.Momentum;
import org.djunits.quantity.Power;
import org.djunits.quantity.Pressure;
import org.djunits.quantity.RadioActivity;
import org.djunits.quantity.SolidAngle;
import org.djunits.quantity.Speed;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.Torque;
import org.djunits.quantity.Volume;
import org.djunits.quantity.VolumetricObjectDensity;
import org.djutils.exceptions.Throw;
import org.djutils.logger.CategoryLogger;

/**
 * Units is a static class that can register and resolve string representations of units, possibly using a locale. The Units
 * class is responsible for maintaining a registry of all units based on their textual abbreviations. It allows for a unit to
 * register itself, and for code to retrieve a unit based on a textual abbreviation. The Units class also takes care of
 * localization of the unit representations. If the Locale is not US, it will look for a resource bundle of the active Locale to
 * see if localized textual abbreviations are registered, and it will use these when resolving the unit. When no localized
 * matches can be found, it will test the (default) US Locale abbreviations as well.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class Units
{
    /** Map with all units per quantity type. */
    private static final Map<String, Map<String, UnitInterface<?, ?>>> UNIT_MAP = new LinkedHashMap<>();

    /** Current map locale. */
    private static Locale currentLocale = Locale.US;

    /** Current resource bundle. */
    private static ResourceBundle resourceBundle = null;

    /** Localized map to translate textual unit abbreviation to US id. */
    private static Map<String, Map<String, String>> localizedUnitTranslateMap = new LinkedHashMap<>();

    /** Prefix for quantity keys in the resource bundle. */
    private static final String QUANTITY_PREFIX = "quantity.";

    /** Prefix for unit keys in the resource bundle. */
    private static final String UNIT_PREFIX = "unit.";

    /** Suffix used for textual abbreviations (pipe separated). */
    private static final String ABBR_SUFFIX = ".abbr";

    /** Suffix used for name. */
    private static final String NAME_SUFFIX = ".name";

    /** Suffix used for display abbreviations or symbols. */
    private static final String DISPLAY_SUFFIX = ".display";

    /** */
    private Units()
    {
        // static class.
    }

    static
    {
        registerStandardUnits();
    }

    /**
     * Register a unit so it can be found based on its textual abbreviations.
     * @param unit the unit to register
     * @throws NullPointerException when unit is null
     */
    public static void register(final UnitInterface<?, ?> unit)
    {
        Throw.whenNull(unit, "unit");
        var subMap =
                UNIT_MAP.computeIfAbsent(quantityName(unit.getClass()), k -> new LinkedHashMap<String, UnitInterface<?, ?>>());
        subMap.put(unit.getStoredTextualAbbreviation(), unit);
    }

    /**
     * Look up a unit in the registry, based on its textual abbreviation.
     * @param unitClass the unit class for which the abbreviation has to be looked up
     * @param abbreviation the abbreviation to look up in the unit registry
     * @return the unit belonging to the abbreviation (if it exists)
     * @throws NullPointerException when unitClass or abbreviation is null
     * @throws UnitRuntimeException when the unit did not exist, or the abbreviation was not registered
     * @param <U> the unit type
     */
    public static <U extends UnitInterface<U, ?>> U resolve(final Class<U> unitClass, final String abbreviation)
            throws UnitRuntimeException
    {
        Throw.whenNull(unitClass, "unitClass");
        Throw.whenNull(abbreviation, "abbreviation");
        String quantityName = quantityName(unitClass);
        Throw.when(!UNIT_MAP.containsKey(quantityName), UnitRuntimeException.class,
                "Error resolving unit class %s (abbreviation '%s')", abbreviation, unitClass.getSimpleName());
        readTranslateMap();
        String unitKey = abbreviation;
        if (localizedUnitTranslateMap.containsKey(quantityName)
                && localizedUnitTranslateMap.get(quantityName).containsKey(abbreviation))
        {
            unitKey = localizedUnitTranslateMap.get(quantityName).get(abbreviation);
        }
        @SuppressWarnings("unchecked")
        U result = (U) UNIT_MAP.get(quantityName).get(unitKey);
        Throw.when(result == null, UnitRuntimeException.class, "Error resolving abbreviation '%s' for unit class %s",
                abbreviation, unitClass.getSimpleName());
        return result;
    }

    /**
     * Return a safe copy of the registered units, e.g. to build pick lists in a user interface.
     * @return a safe copy of the registered units
     */
    public static Map<String, Map<String, UnitInterface<?, ?>>> registeredUnits()
    {
        return new LinkedHashMap<String, Map<String, UnitInterface<?, ?>>>(UNIT_MAP);
    }

    /**
     * Return the quantity name based on a unit class.
     * @param unitClass the unit class
     * @return the quantity name based on the unit class
     */
    private static String quantityName(final Class<?> unitClass)
    {
        String name = unitClassName(unitClass);
        if (name.endsWith(".Unit"))
        {
            name = name.substring(0, name.length() - 5);
        }
        return name;
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

    /** The base of the resource bundle name, will expand to unit.properties, unit_nl.properties, etc. */
    private static final String BUNDLE_BASE = "unit";

    /** UTF-8 loader for .properties ResourceBundles. */
    public static final class Utf8Control extends ResourceBundle.Control
    {
        @Override
        public ResourceBundle newBundle(final String baseName, final Locale locale, final String format,
                final ClassLoader loader, final boolean reload)
                throws IllegalAccessException, InstantiationException, IOException
        {
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName("locale/" + bundleName, "properties");
            URL url = loader.getResource(resourceName);
            if (url == null)
            {
                resourceName = toResourceName("resources/locale/" + bundleName, "properties");
                url = loader.getResource(resourceName);
            }
            if (url == null)
            {
                return null;
            }
            try (var is = loader.getResourceAsStream(resourceName))
            {
                if (is == null)
                {
                    return null;
                }
                try (var reader = new InputStreamReader(is, StandardCharsets.UTF_8))
                {
                    return new PropertyResourceBundle(reader);
                }
            }
        }
    }

    /**
     * Return a resource bundle for the Locale.
     * @param locale the locale to search for
     * @return The resource budle belonging to the given locale
     */
    public static ResourceBundle bundle(final Locale locale)
    {
        return ResourceBundle.getBundle(BUNDLE_BASE, locale, new Utf8Control());
    }

    /**
     * Reads a string value from the bundle returning {@code null} if the key is missing.
     * @param bundle the resource bundle.
     * @param key the key to read.
     * @return the string value or {@code null}.
     */
    private static String getStringSafe(final ResourceBundle bundle, final String key)
    {
        try
        {
            return bundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            return null;
        }
    }

    /**
     * Read the data from the resource bundle.
     */
    public static synchronized void readTranslateMap()
    {
        if (Locale.getDefault().equals(currentLocale))
        {
            return;
        }
        localizedUnitTranslateMap.clear();
        if (Locale.getDefault().equals(Locale.US))
        {
            currentLocale = Locale.US;
            return;
        }
        currentLocale = Locale.getDefault();
        resourceBundle = bundle(currentLocale);
        if (resourceBundle != null)
        {
            for (String key : resourceBundle.keySet())
            {
                if (!key.startsWith(UNIT_PREFIX) || !key.endsWith(ABBR_SUFFIX))
                {
                    continue;
                }
                String quantity = key.substring(UNIT_PREFIX.length(), key.indexOf('.', UNIT_PREFIX.length()));
                if (!UNIT_MAP.containsKey(quantity))
                {
                    CategoryLogger.always().info("djunits localization. Quantity {} from locale file {} unknown", quantity,
                            currentLocale);
                    continue;
                }
                var quantityMap = localizedUnitTranslateMap.computeIfAbsent(quantity, k -> new LinkedHashMap<String, String>());
                String unitId = key.substring(key.indexOf('.', UNIT_PREFIX.length()));
                unitId = unitId.substring(1, unitId.length() - ABBR_SUFFIX.length());
                if (!UNIT_MAP.get(quantity).containsKey(unitId))
                {
                    CategoryLogger.always().info("djunits localization. Unit {} for quantity {} from locale file {} unknown",
                            unitId, quantity, currentLocale);
                    continue;
                }
                String token = getStringSafe(resourceBundle, key).strip();
                if (token == null || token.isBlank())
                {
                    continue;
                }
                quantityMap.put(token, unitId);
            }
        }
    }

    /**
     * Lookup a quantity name for a given locale.
     * @param locale the locale
     * @param quantityName the simple class name of the quantity
     * @return the localized name of the quantity
     */
    public static String localizedQuantityName(final Locale locale, final String quantityName)
    {
        return getLocalizedOrFallback(locale, QUANTITY_PREFIX + quantityName + NAME_SUFFIX, quantityName);
    }

    /**
     * Get the localized unit name for a unit class.
     * @param unitClass the class of the unit to lookup
     * @return the localized name of the quantity belonging to that unit
     */
    public static String localizedQuantityName(final Class<? extends UnitInterface<?, ?>> unitClass)
    {
        return localizedQuantityName(Locale.getDefault(), quantityName(unitClass));
    }

    /**
     * Return the unit based on a quantity name and unit key. Give a warning when either of them cannot be found. In that case,
     * return null.
     * @param quantityName the simple class name of the quantity
     * @param unitKey the key of the unit
     * @return the unit identified by unitKey for the quantity, or null when either cannot be found
     */
    private static UnitInterface<?, ?> getUnit(final String quantityName, final String unitKey)
    {
        var subMap = UNIT_MAP.get(quantityName);
        if (subMap == null)
        {
            CategoryLogger.always().info("djunits localization. Quantity {} unknown", quantityName);
            return null;
        }
        UnitInterface<?, ?> unit = UNIT_MAP.get(quantityName).get(unitKey);
        if (unit == null)
        {
            CategoryLogger.always().info("djunits localization. Unit {} for quantity {} could not be found", unitKey,
                    quantityName);
            return null;
        }
        return unit;
    }

    /**
     * Lookup a display abbreviation for a given unit key. If it cannot be found, use the stored US unit as a fallback. If the
     * US-based unit cannot be found on the basis of the unit key, return the unit key itself as the display abbreviation.
     * @param locale the locale
     * @param quantityName the simple class name of the quantity
     * @param unitKey the key of the unit
     * @return the localized display abbreviation of the unit
     */
    public static String localizedUnitDisplayAbbr(final Locale locale, final String quantityName, final String unitKey)
    {
        String abbr = getLocalized(locale, UNIT_PREFIX + quantityName + "." + unitKey + DISPLAY_SUFFIX, false);
        if (abbr == null)
        {
            abbr = getLocalized(locale, UNIT_PREFIX + quantityName + "." + unitKey + ABBR_SUFFIX, false);
        }
        if (abbr != null)
        {
            return abbr;
        }
        var unit = getUnit(quantityName, unitKey);
        return unit == null ? unitKey : unit.getStoredDisplayAbbreviation();
    }

    /**
     * Lookup a display abbreviation for a given unit key. If it cannot be found, use the stored US unit as a fallback. If the
     * US-based unit cannot be found on the basis of the unit key, return the unit key itself as the display abbreviation.
     * @param unitClass the class of the unit to lookup
     * @param unitKey the key of the unit
     * @return the localized display abbreviation of the unit
     */
    public static String localizedUnitDisplayAbbr(final Class<?> unitClass, final String unitKey)
    {
        return localizedUnitDisplayAbbr(Locale.getDefault(), quantityName(unitClass), unitKey);
    }

    /**
     * Lookup a display Name for a given unit key. If it cannot be found, use the stored US unit as a fallback. If the US-based
     * unit cannot be found on the basis of the unit key, return the unit key itself as the display name.
     * @param locale the locale
     * @param quantityName the simple class name of the quantity
     * @param unitKey the key of the unit
     * @return the localized display name of the unit
     */
    public static String localizedUnitName(final Locale locale, final String quantityName, final String unitKey)
    {
        String name = getLocalized(locale, UNIT_PREFIX + quantityName + "." + unitKey + NAME_SUFFIX, false);
        if (name != null)
        {
            return name;
        }
        var unit = getUnit(quantityName, unitKey);
        return unit == null ? unitKey : unit.getStoredName();
    }

    /**
     * Lookup a display name for a given unit key. If it cannot be found, use the stored US unit as a fallback. If the US-based
     * unit cannot be found on the basis of the unit key, return the unit key itself as the display name.
     * @param unitClass the class of the unit to lookup
     * @param unitKey the key of the unit
     * @return the localized display name of the unit
     */
    public static String localizedUnitName(final Class<?> unitClass, final String unitKey)
    {
        return localizedUnitName(Locale.getDefault(), quantityName(unitClass), unitKey);
    }

    /**
     * Lookup a textual abbreviation for a given unit key. If it cannot be found, use the stored US unit as a fallback. If the
     * US-based unit cannot be found on the basis of the unit key, return the unit key itself as the textual abbreviation.
     * @param locale the locale
     * @param quantityName the simple class name of the quantity
     * @param unitKey the key of the unit
     * @return the localized textual abbreviation of the unit
     */
    public static String localizedUnitTextualAbbr(final Locale locale, final String quantityName, final String unitKey)
    {
        String abbr = getLocalized(locale, UNIT_PREFIX + quantityName + "." + unitKey + ABBR_SUFFIX, false);
        if (abbr != null)
        {
            return abbr;
        }
        var unit = getUnit(quantityName, unitKey);
        return unit == null ? unitKey : unit.getStoredTextualAbbreviation();
    }

    /**
     * Lookup a textual abbreviation (key) for a given unit key. If it cannot be found, use the stored US unit as a fallback. If
     * the US-based unit cannot be found on the basis of the unit key, return the unit key itself as the textual abbreviation.
     * @param unitClass the class of the unit to lookup
     * @param unitKey the key of the unit
     * @return the localized textual abbreviation of the unit
     */
    public static String localizedUnitTextualAbbr(final Class<?> unitClass, final String unitKey)
    {
        return localizedUnitTextualAbbr(Locale.getDefault(), quantityName(unitClass), unitKey);
    }

    /**
     * Return the value of a key for the given locale.
     * @param locale the locale to use
     * @param key the key to search for
     * @param fallback a fallback string
     * @return the value of the key for the given locale
     */
    private static String getLocalizedOrFallback(final Locale locale, final String key, final String fallback)
    {
        String v = getLocalized(locale, key, false);
        return (v != null) ? v : fallback;
    }

    /**
     * Return the value of a key for the given locale.
     * @param locale the locale to use
     * @param key the key to search for
     * @param required whether the key should be present in the bundle
     * @return the value of the key for the given locale
     */
    private static String getLocalized(final Locale locale, final String key, final boolean required)
    {
        ResourceBundle b = bundle(locale);
        try
        {
            return b.getString(key);
        }
        catch (MissingResourceException e)
        {
            if (required)
            {
                throw e;
            }
            return null;
        }
    }

    /**
     * Force all standard units to register themselves in the unit map, e.g. to make user interface picklists.
     */
    public static void registerStandardUnits()
    {
        AbsorbedDose.Unit.SI_UNIT.isFractional();
        Acceleration.Unit.SI_UNIT.isFractional();
        AmountOfSubstance.Unit.SI_UNIT.isFractional();
        Angle.Unit.SI_UNIT.isFractional();
        AngularAcceleration.Unit.SI_UNIT.isFractional();
        AngularVelocity.Unit.SI_UNIT.isFractional();
        Area.Unit.SI_UNIT.isFractional();
        ArealObjectDensity.Unit.SI_UNIT.isFractional();
        CatalyticActivity.Unit.SI_UNIT.isFractional();
        Density.Unit.SI_UNIT.isFractional();
        Unitless.BASE.getBaseUnit().getScale().isBaseScale();
        Duration.Unit.SI_UNIT.isFractional();
        ElectricalCapacitance.Unit.SI_UNIT.isFractional();
        ElectricalConductance.Unit.SI_UNIT.isFractional();
        ElectricalInductance.Unit.SI_UNIT.isFractional();
        ElectricalResistance.Unit.SI_UNIT.isFractional();
        ElectricCharge.Unit.SI_UNIT.isFractional();
        ElectricCurrent.Unit.SI_UNIT.isFractional();
        ElectricPotential.Unit.SI_UNIT.isFractional();
        Energy.Unit.SI_UNIT.isFractional();
        EquivalentDose.Unit.SI_UNIT.isFractional();
        FlowMass.Unit.SI_UNIT.isFractional();
        FlowVolume.Unit.SI_UNIT.isFractional();
        Force.Unit.SI_UNIT.isFractional();
        Frequency.Unit.SI_UNIT.isFractional();
        Illuminance.Unit.SI_UNIT.isFractional();
        Length.Unit.SI_UNIT.isFractional();
        LinearDensity.Unit.SI_UNIT.isFractional();
        LinearObjectDensity.Unit.SI_UNIT.isFractional();
        LuminousFlux.Unit.SI_UNIT.isFractional();
        LuminousIntensity.Unit.SI_UNIT.isFractional();
        MagneticFlux.Unit.SI_UNIT.isFractional();
        MagneticFluxDensity.Unit.SI_UNIT.isFractional();
        Mass.Unit.SI_UNIT.isFractional();
        Momentum.Unit.SI_UNIT.isFractional();
        Power.Unit.SI_UNIT.isFractional();
        Pressure.Unit.SI_UNIT.isFractional();
        RadioActivity.Unit.SI_UNIT.isFractional();
        SolidAngle.Unit.SI_UNIT.isFractional();
        Speed.Unit.SI_UNIT.isFractional();
        Temperature.Unit.SI_UNIT.isFractional();
        Torque.Unit.SI_UNIT.isFractional();
        Volume.Unit.SI_UNIT.isFractional();
        VolumetricObjectDensity.Unit.SI_UNIT.isFractional();
    }

}
