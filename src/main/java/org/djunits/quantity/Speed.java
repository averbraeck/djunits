package org.djunits.quantity;

import java.util.List;
import java.util.Locale;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * According to <a href="http://en.wikipedia.org/wiki/Velocity">Wikipedia</a>: Speed describes only how fast an object is
 * moving, whereas velocity gives both how fast and in what direction the object is moving.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Speed extends Quantity.Relative<Speed, Speed.Unit>
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
    private static final long serialVersionUID = 500L;

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
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Speed and Area, which results in a FlowVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Area
     */
    public final FlowVolume times(final Area v)
    {
        return new FlowVolume(this.si() * v.si(), FlowVolume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Force, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Force
     */
    public final Power times(final Force v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Frequency, which results in a Acceleration scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Frequency
     */
    public final Acceleration times(final Frequency v)
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
     * Calculate the multiplication of Speed and LinearDensity, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and LinearDensity
     */
    public final Frequency times(final LinearDensity v)
    {
        return new Frequency(this.si() * v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Duration, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Duration
     */
    public final Length times(final Duration v)
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
    public final Force times(final FlowMass v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Mass, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Mass
     */
    public final Momentum times(final Mass v)
    {
        return new Momentum(this.si() * v.si(), Momentum.Unit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Momentum, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Speed and Momentum
     */
    public final Energy times(final Momentum v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Speed.Unit encodes the units of speed.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Speed.Unit>
    {
        /** The dimensions of Speed: m/s [rad, sr, kg, m, s, A, K, mol, cd]. */
        public static final SIUnit SI_UNIT = new SIUnit(new byte[] {0, 0, 0, 1, -1, 0, 0, 0, 0});

        /** The SI or BASE unit. */
        public static final Speed.Unit SI = Units.meter_per_second;

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
                return new Speed.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
