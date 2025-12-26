package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Energy is a physical quantity representing the capacity to do work, measured in joules (J).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Energy extends Quantity.Relative<Energy, Energy.Unit>
{
    /** Constant with value zero. */
    public static final Energy ZERO = Energy.ofSi(0.0);

    /** Constant with value one. */
    public static final Energy ONE = Energy.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Energy NaN = Energy.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Energy POSITIVE_INFINITY = Energy.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Energy NEGATIVE_INFINITY = Energy.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Energy POS_MAXVALUE = Energy.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Energy NEG_MAXVALUE = Energy.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Energy quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Energy(final double value, final Energy.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Energy quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Energy(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Energy.Unit.class, abbreviation));
    }

    /**
     * Construct Energy quantity.
     * @param value Scalar from which to construct this instance
     */
    public Energy(final Energy value)
    {
        super(value.si(), Energy.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Energy instance based on an SI value.
     * @param si the si value
     * @return the Energy instance based on an SI value
     */
    public static Energy ofSi(final double si)
    {
        return new Energy(si, Energy.Unit.SI);
    }

    @Override
    public Energy instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Energy.Unit.SI_UNIT;
    }

    /**
     * Returns a Energy representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Energy
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Energy valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Energy based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Energy of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Energy and Energy, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Energy and Energy
     */
    public final Dimensionless divide(final Energy v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the division of Energy and Force, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Force
     */
    public final Length divide(final Force v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the division of Energy and Length, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Length
     */
    public final Force divide(final Length v)
    {
        return new Force(this.si() / v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Energy and LinearDensity, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Energy and LinearDensity
     */
    public final Force times(final LinearDensity v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the division of Energy and Duration, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Duration
     */
    public final Power divide(final Duration v)
    {
        return new Power(this.si() / v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of Energy and Power, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Power
     */
    public final Duration divide(final Power v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of Energy and Volume, which results in a Pressure scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Volume
     */
    public final Pressure divide(final Volume v)
    {
        return new Pressure(this.si() / v.si(), Pressure.Unit.SI);
    }

    /**
     * Calculate the division of Energy and Pressure, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Pressure
     */
    public final Volume divide(final Pressure v)
    {
        return new Volume(this.si() / v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Energy and Frequency, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Energy and Frequency
     */
    public final Power times(final Frequency v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of Energy and Speed, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Speed
     */
    public final Momentum divide(final Speed v)
    {
        return new Momentum(this.si() / v.si(), Momentum.Unit.SI);
    }

    /**
     * Calculate the division of Energy and Momentum, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Momentum
     */
    public final Speed divide(final Momentum v)
    {
        return new Speed(this.si() / v.si(), Speed.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Energy.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Energy.Unit>
    {
        /** The dimensions of energy: kgm2/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s2");

        /** Joule. */
        public static final Energy.Unit JOULE = new Energy.Unit("J", "joule", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Energy.Unit SI = JOULE.generateSiPrefixes(false, false);

        /** microjoule. */
        public static final Energy.Unit MICROJOULE = Units.resolve(Energy.Unit.class, "muJ");

        /** millijoule. */
        public static final Energy.Unit MILLIJOULE = Units.resolve(Energy.Unit.class, "mJ");

        /** kilojoule. */
        public static final Energy.Unit KILOJOULE = Units.resolve(Energy.Unit.class, "kJ");

        /** megajoule. */
        public static final Energy.Unit MEGAJOULE = Units.resolve(Energy.Unit.class, "MJ");

        /** gigajoule. */
        public static final Energy.Unit GIGAJOULE = Units.resolve(Energy.Unit.class, "GJ");

        /** terajoule. */
        public static final Energy.Unit TERAJOULE = Units.resolve(Energy.Unit.class, "TJ");

        /** petajoule. */
        public static final Energy.Unit PETAJOULE = Units.resolve(Energy.Unit.class, "PJ");

        /** foot-pound force. */
        public static final Energy.Unit FOOT_POUND_FORCE = JOULE.deriveUnit("ft.lbf", "foot pound-force",
                Units.CONST_FT * Units.CONST_LB * Units.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** inch-pound force. */
        public static final Energy.Unit INCH_POUND_FORCE = JOULE.deriveUnit("in.lbf", "inch pound-force",
                Units.CONST_IN * Units.CONST_LB * Units.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** British thermal unit (ISO). */
        public static final Energy.Unit BTU_ISO =
                JOULE.deriveUnit("BTU(ISO)", "British thermal unit (ISO)", 1.0545E3, UnitSystem.IMPERIAL);

        /** British thermal unit (International Table). */
        public static final Energy.Unit BTU_IT =
                JOULE.deriveUnit("BTU(IT)", "British thermal unit (Int. Table)", 1.05505585262E3, UnitSystem.IMPERIAL);

        /** calorie (International Table). */
        public static final Energy.Unit CALORIE_IT =
                JOULE.deriveUnit("cal(IT)", "calorie (Int. Table)", 4.1868, UnitSystem.IMPERIAL);

        /** calorie. */
        public static final Energy.Unit CALORIE = JOULE.deriveUnit("cal", "calorie", 4.184, UnitSystem.OTHER);

        /** kilocalorie. */
        public static final Energy.Unit KILOCALORIE = CALORIE.deriveUnit("kcal", "kilocalorie", 1000.0, UnitSystem.OTHER);

        /** watt-hour. */
        public static final Energy.Unit WATT_HOUR = new Energy.Unit("Wh", "watt-hour", 3600.0, UnitSystem.SI_DERIVED);

        /** microwatt-hour. */
        public static final Energy.Unit MICROWATT_HOUR =
                WATT_HOUR.deriveUnit("muWh", "microwatt-hour", 1E-6, UnitSystem.SI_DERIVED);

        /** milliwatt-hour. */
        public static final Energy.Unit MILLIWATT_HOUR =
                WATT_HOUR.deriveUnit("mWh", "milliwatt-hour", 1E-3, UnitSystem.SI_DERIVED);

        /** kilowatt-hour. */
        public static final Energy.Unit KILOWATT_HOUR =
                WATT_HOUR.deriveUnit("kWh", "kilowatt-hour", 1E3, UnitSystem.SI_DERIVED);

        /** megawatt-hour. */
        public static final Energy.Unit MEGAWATT_HOUR =
                WATT_HOUR.deriveUnit("MWh", "megawatt-hour", 1E6, UnitSystem.SI_DERIVED);

        /** gigawatt-hour. */
        public static final Energy.Unit GIGAWATT_HOUR =
                WATT_HOUR.deriveUnit("MWh", "megawatt-hour", 1E9, UnitSystem.SI_DERIVED);

        /** terawatt-hour. */
        public static final Energy.Unit TERAWATT_HOUR =
                WATT_HOUR.deriveUnit("TWh", "terawatt-hour", 1E12, UnitSystem.SI_DERIVED);

        /** petawatt-hour. */
        public static final Energy.Unit PETAWATT_HOUR =
                WATT_HOUR.deriveUnit("PWh", "petawatt-hour", 1E15, UnitSystem.SI_DERIVED);

        /** electronvolt. */
        public static final Energy.Unit ELECTRONVOLT =
                new Energy.Unit("eV", "electronvolt", 1.602176634E-19, UnitSystem.SI_ACCEPTED);

        /** kilo-electronvolt. */
        public static final Energy.Unit KILOELECTRONVOLT =
                ELECTRONVOLT.deriveUnit("keV", "kilo-electronvolt", 1E3, UnitSystem.SI_ACCEPTED);

        /** mega-electronvolt. */
        public static final Energy.Unit MEGAELECTRONVOLT =
                ELECTRONVOLT.deriveUnit("MeV", "mega-electronvolt", 1E6, UnitSystem.SI_ACCEPTED);

        /** giga-electronvolt. */
        public static final Energy.Unit GIGAELECTRONVOLT =
                ELECTRONVOLT.deriveUnit("GeV", "giga-electronvolt", 1E9, UnitSystem.SI_ACCEPTED);

        /** sthene-meter (mts). */
        public static final Energy.Unit STHENE_METER = JOULE.deriveUnit("sn.m", "sthene meter", 1000.0, UnitSystem.MTS);

        /** erg (cgs). */
        public static final Energy.Unit ERG = JOULE.deriveUnit("erg", "erg", 1.0E-7, UnitSystem.CGS);

        /**
         * Create a new Energy unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, new LinearScale(scaleFactorToBaseUnit), unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviations the textual abbreviations of the unit, where the first one in the list is the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            super(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
        }

        @Override
        public SIUnit siUnit()
        {
            return SI_UNIT;
        }

        @Override
        public Unit getBaseUnit()
        {
            return SI;
        }

        @Override
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Energy.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
