package org.djunits.quantity;

import java.util.GregorianCalendar;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Time is the absolute equivalent of Duration, and can, e.g., represent a calendar date with a zero.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Time extends Quantity.Relative<Time, Time.Unit>
{
    /** Constant with value zero. */
    public static final Time ZERO = Time.ofSi(0.0);

    /** Constant with value one. */
    public static final Time ONE = Time.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Time NaN = Time.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Time POSITIVE_INFINITY = Time.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Time NEGATIVE_INFINITY = Time.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Time POS_MAXVALUE = Time.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Time NEG_MAXVALUE = Time.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Time quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Time(final double value, final Time.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Time quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Time(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Time.Unit.class, abbreviation));
    }

    /**
     * Construct Time quantity.
     * @param value Scalar from which to construct this instance
     */
    public Time(final Time value)
    {
        super(value.si(), Time.Unit.BASE);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Time instance based on an SI value.
     * @param si the si value
     * @return the Time instance based on an SI value
     */
    public static Time ofSi(final double si)
    {
        return new Time(si, Time.Unit.BASE);
    }

    @Override
    public Time instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Time.Unit.SI_UNIT;
    }

    /**
     * Returns a Time representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Time
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Time valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Time based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Time of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Time and Time, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Time and Time
     */
    public final Dimensionless divide(final Time v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Time.Unit encodes the absolute unit of time.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Time.Unit, Time>
    {
        /** The dimensions of time: s. */
        public static final SIUnit SI_UNIT = SIUnit.of("s");

        /** second. */
        public static final Time.Unit BASE_SECOND = new Time.Unit("s", "second", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Time.Unit BASE = BASE_SECOND;

        /** The base unit for time with an arbitrary "zero" point with a calculation in microseconds. */
        public static final Time.Unit BASE_MICROSECOND =
                BASE_SECOND.deriveUnit("mus", "\u03BCs", "microsecond", 1.0E-6, UnitSystem.SI_BASE);

        /** The base unit for time with an arbitrary "zero" point with a calculation in milliseconds. */
        public static final Time.Unit BASE_MILLISECOND =
                BASE_SECOND.deriveUnit("ms", "millisecond", 1.0E-3, UnitSystem.SI_BASE);

        /** The base unit for time with an arbitrary "zero" point with a calculation in minutes. */
        public static final Time.Unit BASE_MINUTE = BASE_SECOND.deriveUnit("min", "minute", 60.0, UnitSystem.SI_DERIVED);

        /** The base unit for time with an arbitrary "zero" point with a calculation in hours. */
        public static final Time.Unit BASE_HOUR = BASE_SECOND.deriveUnit("h", "hour", 3600.0, UnitSystem.SI_DERIVED);

        /** The base unit for time with an arbitrary "zero" point with a calculation in days. */
        public static final Time.Unit BASE_DAY = BASE_SECOND.deriveUnit("day", "day", 24.0 * 3600.0, UnitSystem.SI_DERIVED);

        /** The base unit for time with an arbitrary "zero" point with a calculation in weeks. */
        public static final Time.Unit BASE_WEEK =
                BASE_SECOND.deriveUnit("wk", "week", 7.0 * 24.0 * 3600.0, UnitSystem.SI_DERIVED);

        /**
         * The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in seconds. The base should be taken
         * in such a way that a resolution of a millisecond is still 'visible' on a date in, say, 2020. When 1-1-1970 is used as
         * the origin, 1-1-2021 has a value of 1,577,836,800,000 milliseconds = 1.6E12 ms. If we want to be precise on the ms
         * level, we need 12 significant digits. A float has around 7 significant digits (23 bit mantissa), whereas a double has
         * around 16 significant digits (52 bit mantissa). This means that a float time with an offset of 1-1-1970 is at best
         * precise to a minute level. A double time is precise to microseconds. Therefore, avoid using float times that use the
         * EPOCH.
         */
        public static final Time.Unit EPOCH_SECOND =
                BASE_SECOND.deriveUnit("s(Y1970)", "seconds since 1/1/70", 1.0, UnitSystem.OTHER);

        /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in microseconds. */
        public static final Time.Unit EPOCH_MICROSECOND =
                BASE_SECOND.deriveUnit("mus(Y1970)", "\u03BCs(Y1970)", "microseconds since 1/1/70", 1.0E-6, UnitSystem.SI_BASE);

        /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in milliseconds. */
        public static final Time.Unit EPOCH_MILLISECOND =
                BASE_SECOND.deriveUnit("ms(Y1970)", "milliseconds since 1/1/70", 1.0E-3, UnitSystem.OTHER);

        /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in minutes. */
        public static final Time.Unit EPOCH_MINUTE =
                BASE_SECOND.deriveUnit("min(Y1970)", "minutes since 1/1/70", 60.0, UnitSystem.OTHER);

        /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in hours. */
        public static final Time.Unit EPOCH_HOUR =
                BASE_SECOND.deriveUnit("h(Y1970)", "hours since 1/1/70", 3600.0, UnitSystem.OTHER);

        /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in days. */
        public static final Time.Unit EPOCH_DAY =
                BASE_SECOND.deriveUnit("day(Y1970)", "days since 1/1/70", 24.0 * 3600.0, UnitSystem.OTHER);

        /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in weeks. */
        public static final Time.Unit EPOCH_WEEK =
                BASE_SECOND.deriveUnit("wk(Y1970)", "weeks since 1/1/70", 7.0 * 24.0 * 3600.0, UnitSystem.OTHER);

        /**
         * The Epoch with 0001-01-01 AD at 00:00 as the origin with a calculation in seconds. When 1-1-0001 is used as the
         * origin, 1-1-2021 has a value of around 6.4E13 ms. If we want to be precise on the ms level, we need 13 significant
         * digits. A float has around 7 significant digits (23 bit mantissa), whereas a double has around 16 significant digits
         * (52 bit mantissa). This means that a float time with an offset of 1-1-0001 is at best precise to an hour level. A
         * double time is precise to microseconds. Therefore, avoid using float times that use the EPOCH_YEAR1_SECOND.
         */
        public static final Time.Unit EPOCH_YEAR1_SECOND = new Time.Unit("s(Y1)", "s(Y1)", "seconds since 1-1-0001 00:00",
                new OffsetLinearScale(1.0, new GregorianCalendar(1, 0, 1, 0, 0, 0).getTimeInMillis() / 1000.0),
                UnitSystem.OTHER);

        /**
         * The Epoch with J2000.0 as the origin, which is The Gregorian date January 1, 2000 at 12:00 GMT (noon) with a
         * calculation in seconds. When 1-1-2000 is used as the origin, 1-1-2021 has a value of around 6.3E11 ms. If we want to
         * be precise on the ms level, we need 11 significant digits. A float has around 7 significant digits (23 bit mantissa),
         * whereas a double has around 16 significant digits (52 bit mantissa). This means that a float time with an offset of
         * 1-1-2000 is at best precise to a minute level. A double time is precise to fractions of microseconds. Therefore,
         * avoid using float times that use the EPOCH_J2000_SECOND.
         */
        public static final Time.Unit EPOCH_J2000_SECOND =
                new Time.Unit("s(Y2000)", "s(Y2000)", "seconds since 1-1-2000 12:00 GMT",
                        new OffsetLinearScale(1.0, new GregorianCalendar(2000, 0, 1, 12, 0, 0).getTimeInMillis() / 1000.0),
                        UnitSystem.OTHER);

        /**
         * Create a new Time unit.
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
            return BASE;
        }

        @Override
        public Time ofSi(final double si)
        {
            return Time.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Time.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
