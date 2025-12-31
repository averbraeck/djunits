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
 * Power is the rate of energy transfer or work done per unit time, measured in watts (W).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Power extends Quantity.Relative<Power, Power.Unit>
{
    /** Constant with value zero. */
    public static final Power ZERO = Power.ofSi(0.0);

    /** Constant with value one. */
    public static final Power ONE = Power.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Power NaN = Power.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Power POSITIVE_INFINITY = Power.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Power NEGATIVE_INFINITY = Power.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Power POS_MAXVALUE = Power.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Power NEG_MAXVALUE = Power.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Power quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Power(final double value, final Power.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Power quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Power(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Power.Unit.class, abbreviation));
    }

    /**
     * Construct Power quantity.
     * @param value Scalar from which to construct this instance
     */
    public Power(final Power value)
    {
        super(value.si(), Power.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Power instance based on an SI value.
     * @param si the si value
     * @return the Power instance based on an SI value
     */
    public static Power ofSi(final double si)
    {
        return new Power(si, Power.Unit.SI);
    }

    @Override
    public Power instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Power.Unit.SI_UNIT;
    }

    /**
     * Returns a Power representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Power
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Power valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Power based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Power of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Power and Power, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Power and Power
     */
    public final Dimensionless divide(final Power v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Power and Duration, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Power and Duration
     */
    public final Energy multiply(final Duration v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the division of Power and Frequency, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a division of Power and Frequency
     */
    public final Energy divide(final Frequency v)
    {
        return new Energy(this.si() / v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the division of Power and Energy, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of Power and Energy
     */
    public final Frequency divide(final Energy v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the division of Power and Speed, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a division of Power and Speed
     */
    public final Force divide(final Speed v)
    {
        return new Force(this.si() / v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the division of Power and Force, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Power and Force
     */
    public final Speed divide(final Force v)
    {
        return new Speed(this.si() / v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the division of Power and ElectricPotential, which results in a ElectricCurrent scalar.
     * @param v scalar
     * @return scalar as a division of Power and ElectricPotential
     */
    public final ElectricCurrent divide(final ElectricPotential v)
    {
        return new ElectricCurrent(this.si() / v.si(), ElectricCurrent.Unit.SI);
    }

    /**
     * Calculate the division of Power and ElectricCurrent, which results in a ElectricPotential scalar.
     * @param v scalar
     * @return scalar as a division of Power and ElectricCurrent
     */
    public final ElectricPotential divide(final ElectricCurrent v)
    {
        return new ElectricPotential(this.si() / v.si(), ElectricPotential.Unit.SI);
    }

    /**
     * Calculate the division of Power and Acceleration, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a division of Power and Acceleration
     */
    public final Momentum divide(final Acceleration v)
    {
        return new Momentum(this.si() / v.si(), Momentum.Unit.SI);
    }

    /**
     * Calculate the division of Power and Momentum, which results in a Acceleration scalar.
     * @param v scalar
     * @return scalar as a division of Power and Momentum
     */
    public final Acceleration divide(final Momentum v)
    {
        return new Acceleration(this.si() / v.si(), Acceleration.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Power.Unit encodes the units for the rate of energy transfer or work done per unit time.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Power.Unit, Power>
    {
        /** The dimensions of power: kgm2/s3. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s3");

        /** Watt. */
        public static final Power.Unit WATT = new Power.Unit("W", "watt", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Power.Unit SI = WATT.generateSiPrefixes(false, false);

        /** microwatt. */
        public static final Power.Unit MICROWATT = Units.resolve(Power.Unit.class, "muW");

        /** milliwatt. */
        public static final Power.Unit MILLIWATT = Units.resolve(Power.Unit.class, "mW");

        /** kiloawatt. */
        public static final Power.Unit KILOWATT = Units.resolve(Power.Unit.class, "kW");

        /** megawatt. */
        public static final Power.Unit MEGAWATT = Units.resolve(Power.Unit.class, "MW");

        /** gigawatt. */
        public static final Power.Unit GIGAWATT = Units.resolve(Power.Unit.class, "GW");

        /** terawatt. */
        public static final Power.Unit TERAWATT = Units.resolve(Power.Unit.class, "TW");

        /** petawatt. */
        public static final Power.Unit PETAWATT = Units.resolve(Power.Unit.class, "PW");

        /** foot-pound-force per hour. */
        public static final Power.Unit FOOT_POUND_FORCE_PER_HOUR = SI.deriveUnit("ft.lbf/h", "foot pound-force per hour",
                Length.Unit.CONST_FT * Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY / 3600.0, UnitSystem.IMPERIAL);

        /** foot-pound-force per minute. */
        public static final Power.Unit FOOT_POUND_FORCE_PER_MINUTE = SI.deriveUnit("ft.lbf/min", "foot pound-force per minute",
                Length.Unit.CONST_FT * Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY / 60.0, UnitSystem.IMPERIAL);

        /** foot-pound-force per second. */
        public static final Power.Unit FOOT_POUND_FORCE_PER_SECOND = SI.deriveUnit("ft.lbf/s", "foot pound-force per second",
                Length.Unit.CONST_FT * Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** horsepower (metric). */
        public static final Power.Unit HORSEPOWER_METRIC =
                WATT.deriveUnit("hp(M)", "horsepower (metric)", 735.49875, UnitSystem.OTHER);

        /** sthene-meter per second. */
        public static final Power.Unit STHENE_METER_PER_SECOND =
                SI.deriveUnit("sn.m/s", "sthene meter per second", 1000.0, UnitSystem.MTS);

        /** erg per second. */
        public static final Power.Unit ERG_PER_SECOND = SI.deriveUnit("erg/s", "erg per second", 1.0E-7, UnitSystem.CGS);

        /**
         * Create a new Power unit.
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
        public Power ofSi(final double si)
        {
            return Power.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Power.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
