package org.djunits.quantity;

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
 * Frequency encodes the number of events per unit of duration.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Frequency extends Quantity.Relative<Frequency, Frequency.Unit>
{
    /** Constant with value zero. */
    public static final Frequency ZERO = Frequency.ofSi(0.0);

    /** Constant with value one. */
    public static final Frequency ONE = Frequency.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Frequency NaN = Frequency.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Frequency POSITIVE_INFINITY = Frequency.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Frequency NEGATIVE_INFINITY = Frequency.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Frequency POS_MAXVALUE = Frequency.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Frequency NEG_MAXVALUE = Frequency.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Frequency quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Frequency(final double value, final Frequency.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Frequency quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Frequency(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Frequency.Unit.class, abbreviation));
    }

    /**
     * Construct Frequency quantity.
     * @param value Scalar from which to construct this instance
     */
    public Frequency(final Frequency value)
    {
        super(value.si(), Frequency.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Frequency instance based on an SI value.
     * @param si the si value
     * @return the Frequency instance based on an SI value
     */
    public static Frequency ofSi(final double si)
    {
        return new Frequency(si, Frequency.Unit.SI);
    }

    @Override
    public Frequency instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Frequency.Unit.SI_UNIT;
    }

    /**
     * Returns a Frequency representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Frequency
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Frequency valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Frequency: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Frequency: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            Frequency.Unit unit = Units.resolve(Frequency.Unit.class, unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Frequency", unitString);
            return new Frequency(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Frequency from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Frequency based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Frequency of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Frequency: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Frequency: empty unitString");
        Frequency.Unit unit = Units.resolve(Frequency.Unit.class, unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Frequency with unit %s", unitString);
        return new Frequency(value, unit);
    }

    /**
     * Calculate the division of Frequency and Frequency, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Frequency and Frequency
     */
    public final Dimensionless divide(final Frequency v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Frequency and Duration, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of Frequency and Duration
     */
    public final Dimensionless times(final Duration v)
    {
        return new Dimensionless(this.si() * v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Frequency and Length, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a multiplication of Frequency and Length
     */
    public final Speed times(final Length v)
    {
        return new Speed(this.si() * v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and Speed, which results in a Acceleration scalar.
     * @param v scalar
     * @return scalar as a multiplication of Frequency and Speed
     */
    public final Acceleration times(final Speed v)
    {
        return new Acceleration(this.si() * v.si(), Acceleration.Unit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and Energy, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Frequency and Energy
     */
    public final Power times(final Energy v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and Angle, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of Frequency and Angle
     */
    public final AngularVelocity times(final Angle v)
    {
        return new AngularVelocity(this.si() * v.si(), AngularVelocity.Unit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and AngularVelocity, which results in a AngularAcceleration scalar.
     * @param v scalar
     * @return scalar as a multiplication of Frequency and AngularVelocity
     */
    public final AngularAcceleration times(final AngularVelocity v)
    {
        return new AngularAcceleration(this.si() * v.si(), AngularAcceleration.Unit.SI);
    }

    @Override
    public Duration reciprocal()
    {
        return Duration.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Frequency.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Frequency.Unit, Frequency>
    {
        /** The dimensions of frequency: /s. */
        public static final SIUnit SI_UNIT = SIUnit.of("/s");

        /** hertz. */
        public static final Frequency.Unit HERTZ = new Frequency.Unit("Hz", "hertz", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Frequency.Unit SI = HERTZ.generateSiPrefixes(false, false);

        /** kiloHertz. */
        public static final Frequency.Unit KILOHERTZ = Units.resolve(Frequency.Unit.class, "kHz");

        /** megaHertz. */
        public static final Frequency.Unit MEGAHERTZ = Units.resolve(Frequency.Unit.class, "MHz");

        /** gigaHertz. */
        public static final Frequency.Unit GIGAHERTZ = Units.resolve(Frequency.Unit.class, "GHz");

        /** teraHertz. */
        public static final Frequency.Unit TERAHERTZ = Units.resolve(Frequency.Unit.class, "THz");

        /** Revolutions per minute = 1/60 Hz. */
        public static final Frequency.Unit RPM =
                HERTZ.deriveUnit("rpm", "revolutions per minute", 1.0 / 60.0, UnitSystem.OTHER);

        /**
         * Create a new Frequency unit.
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
        public Frequency ofSi(final double si)
        {
            return Frequency.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Frequency.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
