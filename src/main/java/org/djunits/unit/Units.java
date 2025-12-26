package org.djunits.unit;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.djunits.quantity.AbsoluteTemperature;
import org.djunits.quantity.AbsorbedDose;
import org.djunits.quantity.Acceleration;
import org.djunits.quantity.AmountOfSubstance;
import org.djunits.quantity.Angle;
import org.djunits.quantity.AngularAcceleration;
import org.djunits.quantity.AngularVelocity;
import org.djunits.quantity.CatalyticActivity;
import org.djunits.quantity.Density;
import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.Direction;
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
import org.djunits.quantity.LuminousFlux;
import org.djunits.quantity.LuminousIntensity;
import org.djunits.quantity.MagneticFlux;
import org.djunits.quantity.MagneticFluxDensity;
import org.djunits.quantity.Mass;
import org.djunits.quantity.Momentum;
import org.djunits.quantity.Position;
import org.djunits.quantity.Power;
import org.djunits.quantity.Pressure;
import org.djunits.quantity.RadioActivity;
import org.djunits.quantity.SolidAngle;
import org.djunits.quantity.Speed;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.Time;
import org.djunits.quantity.Torque;
import org.djunits.quantity.Volume;
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
public final class Units
{
    /** Current map locale. */
    private static Locale currentLocale = Locale.US;

    /** Localized map to translate international id to US id. */
    private static Map<String, String> localeTranslateMap = new LinkedHashMap<>();

    /** Map with all units per unit type. */
    private static final Map<Class<?>, Map<String, UnitInterface<?>>> UNITMAP = new LinkedHashMap<>();

    /** Constant for the foot. */
    public static final double CONST_FT = 0.3048;

    /** Constant for the yard. */
    public static final double CONST_YD = 3.0 * CONST_FT;

    /** Constant for the inch. */
    public static final double CONST_IN = CONST_FT / 12.0;

    /** Constant for the mile. */
    public static final double CONST_MI = 5280.0 * CONST_FT;

    /** Constant for the nautical mile. */
    public static final double CONST_NM = 1852.0;

    /** */
    private Units()
    {
    }

    static
    {
        registerStandardUnits();
    }

    /**
     * Register a unit so it can be found based on its textual abbreviations.
     * @param unit the unit to register
     */
    public static void register(final UnitInterface<?> unit)
    {
        Throw.whenNull(unit, "unit");
        var subMap = UNITMAP.computeIfAbsent(unit.getClass(), k -> new LinkedHashMap<String, UnitInterface<?>>());
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
        Throw.when(!UNITMAP.containsKey(unitClass), UnitRuntimeException.class,
                "Error resolving unit class %s (abbreviation '%s')", abbreviation, unitClass.getSimpleName());
        @SuppressWarnings("unchecked")
        U result = (U) UNITMAP.get(unitClass).get(abbreviation);
        Throw.when(result == null, UnitRuntimeException.class, "Error resolving abbreviation '%s' for unit class %s",
                abbreviation, unitClass.getSimpleName());
        return result;
    }

    /**
     * Return a safe copy of the registered units, e.g. to build pick lists in a user interface. The map will be sorted on class
     * and unit.
     * @return a safe copy of the registered units
     */
    public static Map<Class<?>, Map<String, UnitInterface<?>>> registeredUnits()
    {
        return new LinkedHashMap<Class<?>, Map<String, UnitInterface<?>>>(UNITMAP);
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

    /**
     * Register all standard units in the unit map, e.g. to make user interface picklists.
     */
    public static void registerStandardUnits()
    {
        AbsoluteTemperature.Unit.SI_UNIT.isFractional();
        AbsorbedDose.Unit.SI_UNIT.isFractional();
        Acceleration.Unit.SI_UNIT.isFractional();
        AmountOfSubstance.Unit.SI_UNIT.isFractional();
        Angle.Unit.SI_UNIT.isFractional();
        AngularAcceleration.Unit.SI_UNIT.isFractional();
        AngularVelocity.Unit.SI_UNIT.isFractional();
        CatalyticActivity.Unit.SI_UNIT.isFractional();
        Density.Unit.SI_UNIT.isFractional();
        Dimensionless.Unit.BASE.getBaseUnit().getScale().isBaseScale();
        Direction.Unit.DEFAULT.getBaseUnit().getScale().isBaseScale();
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
        LuminousFlux.Unit.SI_UNIT.isFractional();
        LuminousIntensity.Unit.SI_UNIT.isFractional();
        MagneticFlux.Unit.SI_UNIT.isFractional();
        MagneticFluxDensity.Unit.SI_UNIT.isFractional();
        Mass.Unit.SI_UNIT.isFractional();
        Momentum.Unit.SI_UNIT.isFractional();
        Position.Unit.SI_UNIT.isFractional();
        Power.Unit.SI_UNIT.isFractional();
        Pressure.Unit.SI_UNIT.isFractional();
        RadioActivity.Unit.SI_UNIT.isFractional();
        SolidAngle.Unit.SI_UNIT.isFractional();
        Speed.Unit.SI_UNIT.isFractional();
        Temperature.Unit.SI_UNIT.isFractional();
        Time.Unit.SI_UNIT.isFractional();
        Torque.Unit.SI_UNIT.isFractional();
        Volume.Unit.SI_UNIT.isFractional();
    }

}
