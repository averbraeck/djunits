package org.djunits.quantity;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;
import org.djutils.exceptions.Throw;

/**
 * Time is the absolute equivalent of Duration, and can, e.g., represent a calendar date with a zero.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Time extends AbsoluteQuantity<Time, Duration, Duration.Unit, Time.TimeReference>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Time quantity with a unit and a reference point.
     * @param value the duration value, expressed in a duration unit
     * @param unit the duration unit in which the value is expressed, relative to the reference point
     * @param reference the reference point of this time
     */
    public Time(final double value, final Duration.Unit unit, final TimeReference reference)
    {
        super(new Duration(value, unit), reference);
    }

    /**
     * Instantiate a Time quantity with a unit, expressed as a String, and a reference point.
     * @param value the duration value, expressed in the unit, relative to the reference point
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     * @param reference the reference point of this time
     */
    public Time(final double value, final String abbreviation, final TimeReference reference)
    {
        this(value, Units.resolve(Duration.Unit.class, abbreviation), reference);
    }

    /**
     * Instantiate a Time instance based on an duration and a reference point.
     * @param duration the duration, relative to the reference point
     * @param reference the reference point of this time
     */
    public Time(final Duration duration, final TimeReference reference)
    {
        super(duration, reference);
    }

    /**
     * Return a Time instance based on an SI value and a reference point.
     * @param si the duration si value, relative to the reference point
     * @param reference the reference point of this time
     * @return the Time instance based on an SI value
     */
    public static Time ofSi(final double si, final TimeReference reference)
    {
        return new Time(si, Duration.Unit.SI, reference);
    }

    @Override
    public Time instantiate(final Duration duration, final TimeReference reference)
    {
        return new Time(duration, reference);
    }

    @Override
    public SIUnit siUnit()
    {
        return Duration.Unit.SI_UNIT;
    }

    /**
     * Returns a Time representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Time
     * @param reference the reference point of this time
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Time valueOf(final String text, final TimeReference reference)
    {
        return new Time(Quantity.valueOf(text, Duration.ZERO), reference);
    }

    /**
     * Returns a Time based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @param reference the reference point of this time
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Time of(final double value, final String unitString, final TimeReference reference)
    {
        return new Time(Quantity.of(value, unitString, Duration.ZERO), reference);
    }

    @Override
    public Duration subtract(final Time other)
    {
        Throw.when(!getReference().equals(other.getReference()), IllegalArgumentException.class,
                "cannot subtract two absolute quantities with a different reference: %s <> %s", getReference().getId(),
                other.getReference().getId());
        return Duration.ofSi(si() - other.si()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Time add(final Duration other)
    {
        return new Time(Duration.ofSi(si() + other.si()).setDisplayUnit(getDisplayUnit()), getReference());
    }

    @Override
    public Time subtract(final Duration other)
    {
        return new Time(Duration.ofSi(si() - other.si()).setDisplayUnit(getDisplayUnit()), getReference());
    }

    /**
     * The reference class to define a reference point for the time.
     */
    public static final class TimeReference extends Reference<TimeReference, Duration>
    {
        /** Gregorian. */
        public static final TimeReference GREGORIAN = new TimeReference("GREGORIAN", "Gregorian time origin (1-1-0000)");

        /** Unix. */
        public static final TimeReference UNIX = new TimeReference("UNIX", "UNIX epoch, 1-1-1970, 00:00 GMT");

        /** GPS. */
        public static final TimeReference GPS = new TimeReference("GPS", "GPS epoch, 6-1-1980");

        /**
         * Define a new reference point for the time, with an offset value to another reference.
         * @param id the id
         * @param name the name or explanation
         * @param offset the offset w.r.t. offsetReference
         * @param offsetReference the reference to which the offset is relative
         */
        public TimeReference(final String id, final String name, final Duration offset, final TimeReference offsetReference)
        {
            super(id, name, offset, offsetReference);
        }

        /**
         * Define a new reference point for the time without an offset to a base reference.
         * @param id the id
         * @param name the name or explanation
         */
        public TimeReference(final String id, final String name)
        {
            super(id, name, Duration.ZERO, null);
        }

        /**
         * Define a new reference point for the time, with an offset value to another reference.
         * @param id the id
         * @param name the name or explanation
         * @param offset the offset w.r.t. offsetReference
         * @param offsetReference the reference to which the offset is relative
         */
        public static void add(final String id, final String name, final Duration offset, final TimeReference offsetReference)
        {
            new TimeReference(id, name, offset, offsetReference);
        }

        /**
         * Define a new reference point for the time without an offset to a base reference.
         * @param id the id
         * @param name the name or explanation
         */
        public static void add(final String id, final String name)
        {
            new TimeReference(id, name);
        }

        /**
         * Get a reference point for the time, based on its id. Return null when the id could not be found.
         * @param id the id
         * @return the TimeReference object
         */
        public static TimeReference get(final String id)
        {
            return (TimeReference) referenceMap.get(id);
        }
    }
}
