package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Mass is the amount of matter in an object, measured in kilograms (kg).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Mass extends Quantity.Relative<Mass, Mass.Unit>
{
    /** Constant with value zero. */
    public static final Mass ZERO = Mass.ofSi(0.0);

    /** Constant with value one. */
    public static final Mass ONE = Mass.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Mass NaN = Mass.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Mass POSITIVE_INFINITY = Mass.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Mass NEGATIVE_INFINITY = Mass.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Mass POS_MAXVALUE = Mass.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Mass NEG_MAXVALUE = Mass.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Mass quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Mass(final double value, final Mass.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Mass quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Mass(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Mass.Unit.class, abbreviation));
    }

    /**
     * Construct Mass quantity.
     * @param value Scalar from which to construct this instance
     */
    public Mass(final Mass value)
    {
        super(value.si(), Mass.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Mass instance based on an SI value.
     * @param si the si value
     * @return the Mass instance based on an SI value
     */
    public static Mass ofSi(final double si)
    {
        return new Mass(si, Mass.Unit.SI);
    }

    @Override
    public Mass instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Mass.Unit.SI_UNIT;
    }

    /**
     * Returns a Mass representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Mass
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Mass valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Mass based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Mass of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Mass and Mass, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Mass and Mass
     */
    public final Dimensionless divide(final Mass v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of Mass and FlowMass, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Mass and FlowMass
     */
    public final Duration divide(final FlowMass v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of Mass and Duration, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Duration
     */
    public final FlowMass divide(final Duration v)
    {
        return new FlowMass(this.si() / v.si(), FlowMass.Unit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Acceleration, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Acceleration
     */
    public final Force multiply(final Acceleration v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Frequency, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Frequency
     */
    public final FlowMass multiply(final Frequency v)
    {
        return new FlowMass(this.si() * v.si(), FlowMass.Unit.SI);
    }

    /**
     * Calculate the division of Mass and Density, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Density
     */
    public final Volume divide(final Density v)
    {
        return new Volume(this.si() / v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the division of Mass and Volume, which results in a Density scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Volume
     */
    public final Density divide(final Volume v)
    {
        return new Density(this.si() / v.si(), Density.Unit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Speed, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Speed
     */
    public final Momentum multiply(final Speed v)
    {
        return new Momentum(this.si() * v.si(), Momentum.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Mass.Unit encodes the unit of the amount of matter in an object.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Mass.Unit, Mass>
    {
        /** Constant for pound (lb). */
        public static final double CONST_LB = 0.45359237;

        /** Constant for ounce. */
        public static final double CONST_OUNCE = CONST_LB / 16.0;

        /** Constant for short ton. */
        public static final double CONST_TON_SHORT = 2000.0 * CONST_LB;

        /** Constant for long ton. */
        public static final double CONST_TON_LONG = 2240.0 * CONST_LB;

        /** The dimensions of mass: kg. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg");

        /** kilogram. */
        public static final Mass.Unit KILOGRAM = new Mass.Unit("kg", "kilogram", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Mass.Unit SI = KILOGRAM.generateSiPrefixes(true, false);

        /** gram. */
        public static final Mass.Unit GRAM = Units.resolve(Mass.Unit.class, "g");

        /** microgram. */
        public static final Mass.Unit MICROGRAM = Units.resolve(Mass.Unit.class, "mug");

        /** milligram. */
        public static final Mass.Unit MILLIGRAM = Units.resolve(Mass.Unit.class, "mg");

        /** pound. */
        public static final Mass.Unit POUND = KILOGRAM.deriveUnit("lb", "pound", CONST_LB, UnitSystem.IMPERIAL);

        /** pound. */
        public static final Mass.Unit OUNCE = KILOGRAM.deriveUnit("oz", "ounce", CONST_OUNCE, UnitSystem.IMPERIAL);

        /** long ton = 2240 lb. */
        public static final Mass.Unit TON_LONG =
                KILOGRAM.deriveUnit("long tn", "long ton", CONST_TON_LONG, UnitSystem.IMPERIAL);

        /** short ton = 2000 lb. */
        public static final Mass.Unit TON_SHORT =
                KILOGRAM.deriveUnit("sh tn", "short ton", CONST_TON_SHORT, UnitSystem.US_CUSTOMARY);

        /** metric ton = 1000 kg. */
        public static final Mass.Unit TON_METRIC = KILOGRAM.deriveUnit("t", "metric tonne", 1000.0, UnitSystem.SI_ACCEPTED);

        /** metric ton = 1000 kg. */
        public static final Mass.Unit TONNE = KILOGRAM.deriveUnit("t(mts)", "tonne", 1000.0, UnitSystem.MTS);

        /** dalton. */
        public static final Mass.Unit DALTON = KILOGRAM.deriveUnit("Da", "Dalton", 1.6605388628E-27, UnitSystem.SI_ACCEPTED);

        /** electronvolt = 1.782661907E-36 kg. See http://physics.nist.gov/cuu/Constants/Table/allascii.txt. */
        public static final Mass.Unit ELECTRONVOLT =
                KILOGRAM.deriveUnit("eV", "electronvolt", 1.782661907E-36, UnitSystem.OTHER);

        /** microelectronvolt. */
        public static final Mass.Unit MICROELECTRONVOLT =
                ELECTRONVOLT.deriveUnit("mueV", "\u03BCeV", "microelectronvolt", 1E-6, UnitSystem.OTHER);

        /** millielectronvolt (note, no dash between milli and electron; the SI style guide forbids spaces or hyphens). */
        public static final Mass.Unit MILLIELECTRONVOLT =
                ELECTRONVOLT.deriveUnit("meV", "millielectronvolt", 1E-3, UnitSystem.OTHER);

        /** kiloelectronvolt. */
        public static final Mass.Unit KILOELECTRONVOLT =
                ELECTRONVOLT.deriveUnit("keV", "kiloelectronvolt", 1E3, UnitSystem.OTHER);

        /** megaelectronvolt. */
        public static final Mass.Unit MEGAELECTRONVOLT =
                ELECTRONVOLT.deriveUnit("MeV", "megaelectronvolt", 1E6, UnitSystem.OTHER);

        /** gigaelectronvolt. */
        public static final Mass.Unit GIGAELECTRONVOLT =
                ELECTRONVOLT.deriveUnit("GeV", "gigaelectronvolt", 1E9, UnitSystem.OTHER);

        /**
         * Create a new Mass unit.
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
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
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
        public Mass ofSi(final double si)
        {
            return Mass.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Mass.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
