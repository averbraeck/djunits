package org.djunits.quantity;

import java.util.Locale;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * Speed is the rate of change of position over time.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Speed extends Quantity<Speed, Speed.Unit>
{
    /** Constant with value zero. */
    public static final Speed ZERO = Speed.ofSi(0.0);

    /** Constant with value one. */
    public static final Speed ONE = Speed.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Speed NaN = Speed.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Speed POSITIVE_INFINITY = Speed.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Speed NEGATIVE_INFINITY = Speed.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Speed POS_MAXVALUE = Speed.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Speed NEG_MAXVALUE = Speed.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Speed quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Speed(final double value, final Speed.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Speed quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Speed(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Speed.Unit.class, abbreviation));
    }

    /**
     * Construct Speed quantity.
     * @param value Scalar from which to construct this instance
     */
    public Speed(final Speed value)
    {
        super(value.si(), Speed.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Speed instance based on an SI value.
     * @param si the si value
     * @return the Speed instance based on an SI value
     */
    public static Speed ofSi(final double si)
    {
        return new Speed(si, Speed.Unit.SI);
    }

    @Override
    public Speed instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Speed.Unit.SI_UNIT;
    }

    /**
     * Returns a Speed representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Speed
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Speed valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Speed: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Speed: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            Speed.Unit unit = Units.resolve(Speed.Unit.class, unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Speed", unitString);
            return new Speed(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Speed from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Speed based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Speed of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Speed: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Speed: empty unitString");
        Speed.Unit unit = Units.resolve(Speed.Unit.class, unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Speed with unit %s", unitString);
        return new Speed(value, unit);
    }

    /**
     * Calculate the division of Speed and Speed, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Speed and Speed
     */
    public final Dimensionless divide(final Speed v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Speed and Area, which results in a FlowVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Area
     */
    public final FlowVolume multiply(final Area v)
    {
        return new FlowVolume(this.si() * v.si(), FlowVolume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Force, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Force
     */
    public final Power multiply(final Force v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Frequency, which results in a Acceleration scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Frequency
     */
    public final Acceleration multiply(final Frequency v)
    {
        return new Acceleration(this.si() * v.si(), Acceleration.Unit.SI);
    }

    /**
     * Calculate the division of Speed and Length, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of Speed and Length
     */
    public final Frequency divide(final Length v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the division of Speed and Frequency, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Speed and Frequency
     */
    public final Length divide(final Frequency v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and LinearObjectDensity, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and LinearObjectDensity
     */
    public final Frequency multiply(final LinearObjectDensity v)
    {
        return new Frequency(this.si() * v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Duration, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Duration
     */
    public final Length multiply(final Duration v)
    {
        return new Length(this.si() * v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the division of Speed and Duration, which results in a Acceleration scalar.
     * @param v scalar
     * @return scalar as a division of Speed and Duration
     */
    public final Acceleration divide(final Duration v)
    {
        return new Acceleration(this.si() / v.si(), Acceleration.Unit.SI);
    }

    /**
     * Calculate the division of Speed and Acceleration, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Speed and Acceleration
     */
    public final Duration divide(final Acceleration v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and FlowMass, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and FlowMass
     */
    public final Force multiply(final FlowMass v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Mass, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Mass
     */
    public final Momentum multiply(final Mass v)
    {
        return new Momentum(this.si() * v.si(), Momentum.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Momentum, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Momentum
     */
    public final Energy multiply(final Momentum v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Speed.Unit encodes the units of the rate of change of a position over time.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Speed.Unit, Speed>
    {
        /** The dimensions of Speed: m/s. */
        public static final SIUnit SI_UNIT = SIUnit.of("m/s");

        /** Meter per second. */
        public static final Speed.Unit m_s =
                new Speed.Unit("m/s", "m/s", "meter per second", IdentityScale.SCALE, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Speed.Unit SI = m_s;

        /** m/h. */
        public static final Speed.Unit m_h =
                new Speed.Unit("m/h", "m/h", "meter per hour", new LinearScale(1.0, 3600.0), UnitSystem.SI_ACCEPTED);

        /** km/s. */
        public static final Speed.Unit km_s =
                new Speed.Unit("km/s", "km/s", "kilometer per second", new LinearScale(1000.0), UnitSystem.SI_ACCEPTED);

        /** km/h. */
        public static final Speed.Unit km_h =
                new Speed.Unit("km/h", "km/h", "kilometer per hour", new LinearScale(1000.0, 3600.0), UnitSystem.SI_ACCEPTED);

        /** in/s. */
        public static final Speed.Unit in_s =
                new Speed.Unit("in/s", "in/s", "inch per second", new LinearScale(Length.Unit.CONST_IN), UnitSystem.IMPERIAL);

        /** in/min. */
        public static final Speed.Unit in_min = new Speed.Unit("in/min", "in/min", "inch per minute",
                new LinearScale(Length.Unit.CONST_IN, 60.0), UnitSystem.IMPERIAL);

        /** in/h. */
        public static final Speed.Unit in_h = new Speed.Unit("in/h", "in/h", "inch per hour",
                new LinearScale(Length.Unit.CONST_IN, 3600.0), UnitSystem.IMPERIAL);

        /** ft/s. */
        public static final Speed.Unit ft_s =
                new Speed.Unit("ft/s", "ft/s", "foot per second", new LinearScale(Length.Unit.CONST_FT), UnitSystem.IMPERIAL);

        /** ft/min. */
        public static final Speed.Unit ft_min = new Speed.Unit("ft/min", "ft/min", "foot per minute",
                new LinearScale(Length.Unit.CONST_FT, 60.0), UnitSystem.IMPERIAL);

        /** ft/h. */
        public static final Speed.Unit ft_h = new Speed.Unit("ft/h", "ft/h", "foot per hour",
                new LinearScale(Length.Unit.CONST_FT, 3600.0), UnitSystem.IMPERIAL);

        /** mi/s. */
        public static final Speed.Unit mi_s =
                new Speed.Unit("mi/s", "mi/s", "mile per second", new LinearScale(Length.Unit.CONST_MI), UnitSystem.IMPERIAL);

        /** mi/min. */
        public static final Speed.Unit mi_min = new Speed.Unit("mi/min", "mi/min", "mile per minute",
                new LinearScale(Length.Unit.CONST_MI, 60.0), UnitSystem.IMPERIAL);

        /** mi/h. */
        public static final Speed.Unit mi_h = new Speed.Unit("mi/h", "mi/h", "mile per hour",
                new LinearScale(Length.Unit.CONST_MI, 3600.0), UnitSystem.IMPERIAL);

        /** knot = Nautical Mile per hour. */
        public static final Speed.Unit kt = new Speed.Unit("kt", "knot", Length.Unit.CONST_NM / 3600.0, UnitSystem.OTHER);

        /**
         * Create a new Speed unit.
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
        public Speed ofSi(final double si)
        {
            return Speed.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Speed.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
