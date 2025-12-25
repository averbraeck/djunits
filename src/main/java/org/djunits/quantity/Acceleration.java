package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Acceleration is the rate of change of velocity over time, measured in meters per second squared (m/s2).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Acceleration extends Quantity.Relative<Acceleration, Acceleration.Unit>
{
    /** Constant with value zero. */
    public static final Acceleration ZERO = Acceleration.ofSi(0.0);

    /** Constant with value one. */
    public static final Acceleration ONE = Acceleration.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Acceleration NaN = Acceleration.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Acceleration POSITIVE_INFINITY = Acceleration.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Acceleration NEGATIVE_INFINITY = Acceleration.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Acceleration POS_MAXVALUE = Acceleration.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Acceleration NEG_MAXVALUE = Acceleration.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Acceleration quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Acceleration(final double value, final Acceleration.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Acceleration quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Acceleration(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Acceleration.Unit.class, abbreviation));
    }

    /**
     * Construct Acceleration quantity.
     * @param value Scalar from which to construct this instance
     */
    public Acceleration(final Acceleration value)
    {
        super(value.si(), Acceleration.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Acceleration instance based on an SI value.
     * @param si the si value
     * @return the Acceleration instance based on an SI value
     */
    public static Acceleration ofSi(final double si)
    {
        return new Acceleration(si, Acceleration.Unit.SI);
    }

    @Override
    public Acceleration instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Acceleration.Unit.SI_UNIT;
    }

    /**
     * Returns a Acceleration representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Acceleration
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Acceleration valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Acceleration based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Acceleration of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Acceleration and Acceleration, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Acceleration and Acceleration
     */
    public final Dimensionless divide(final Acceleration v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Acceleration and Mass, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Acceleration and Mass
     */
    public final Force times(final Mass v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Acceleration and Duration, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a multiplication of Acceleration and Duration
     */
    public final Speed times(final Duration v)
    {
        return new Speed(this.si() * v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the division of Acceleration and Frequency, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Acceleration and Frequency
     */
    public final Speed divide(final Frequency v)
    {
        return new Speed(this.si() / v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the division of Acceleration and Speed, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of Acceleration and Speed
     */
    public final Frequency divide(final Speed v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the multiplication of Acceleration and Momentum, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Acceleration and Momentum
     */
    public final Power times(final Momentum v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Acceleration.Unit encodes the units of acceleration.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Acceleration.Unit>
    {
        /** The dimensions of Acceleration: m/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("m/s2");

        /** m/s2. */
        public static final Acceleration.Unit METER_PER_SECOND2 =
                new Acceleration.Unit(List.of("m/s2", "m/s^2", "m/sec2", "m/sec^2"), "m/s2", "meter per second squared",
                        IdentityScale.SCALE, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Acceleration.Unit SI = METER_PER_SECOND2;

        /** km/h2. */
        public static final Acceleration.Unit KM_PER_HOUR2 =
                new Acceleration.Unit(List.of("km/h2", "km/h^2", "km/hour2", "km/hour^2"), "km/h2",
                        "kilometer per hour squared", new LinearScale(1000.0, 3600.0 * 3600.0), UnitSystem.SI_ACCEPTED);

        /** ft/s2. */
        public static final Acceleration.Unit FOOT_PER_SECOND2 =
                new Acceleration.Unit(List.of("ft/s2", "ft/s^2", "foot/sec2", "foot/sec^2"), "ft/s2", "foot per second squared",
                        new LinearScale(Units.const_ft), UnitSystem.IMPERIAL);

        /** in/s2. */
        public static final Acceleration.Unit INCH_PER_SECOND2 =
                new Acceleration.Unit(List.of("in/s2", "in/s^2", "inch/sec2", "inch/sec^2"), "in/s2", "inch per second squared",
                        new LinearScale(Units.const_in), UnitSystem.IMPERIAL);

        /** mi/h2. */
        public static final Acceleration.Unit MILE_PER_HOUR2 =
                new Acceleration.Unit(List.of("mi/h2", "mi/h^2", "mile/hour2", "mile/hour^2"), "mi/h2", "mile per hour squared",
                        new LinearScale(Units.const_mi, 3600.0 * 3600.0), UnitSystem.IMPERIAL);

        /** mi/s2. */
        public static final Acceleration.Unit MILE_PER_SECOND2 =
                new Acceleration.Unit(List.of("mi/s2", "mi/s^2", "mile/sec2", "mile/sec^2"), "mi/s2", "mile per second squared",
                        new LinearScale(Units.const_mi), UnitSystem.IMPERIAL);

        /** kt/s = Nautical Mile / h / s. */
        public static final Acceleration.Unit KNOT_PER_SECOND =
                new Acceleration.Unit(List.of("kt/s", "kt/sec", "knot/s", "knot/sec"), "kt/s", "knot per second",
                        new LinearScale(Units.const_NM, 3600.0), UnitSystem.OTHER);

        /** mi/h/s. */
        public static final Acceleration.Unit MILE_PER_HOUR_PER_SECOND =
                new Acceleration.Unit(List.of("mi/h/s", "mi/hr/sec", "mile/hour/sec"), "mi/h/s", "mile per hour per second",
                        new LinearScale(Units.const_mi, 3600.0), UnitSystem.IMPERIAL);

        /** The standard gravity. */
        public static final Acceleration.Unit STANDARD_GRAVITY =
                new Acceleration.Unit("g", "standard gravity", 9.80665, UnitSystem.OTHER);

        /** Gal or cm/s. */
        public static final Acceleration.Unit GAL = new Acceleration.Unit("Gal", "gal", 0.01, UnitSystem.CGS);

        /**
         * Create a new Acceleration unit.
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
                return new Acceleration.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
