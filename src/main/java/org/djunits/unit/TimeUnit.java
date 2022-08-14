package org.djunits.unit;

import java.util.GregorianCalendar;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard absolute time units. Note that when the offset of a stored absolute Time becomes large, precision of a float or
 * double might not be enough for the required resolution of a Time. A float has around 7 significant digits (23 bit mantissa),
 * whereas a double has around 16 significant digits (52 bit mantissa). This means that when we need to have a float time that
 * is precise to microseconds, the Time value should not go above 2^22 = 4.0E6. This is <b>not</b> enough to store Epoch values!
 * So feeding System.TimeInMillis() to a FloatTime with TimeUnit.BASE as its unit is not having the required precision. For a
 * (double) Time with TimeUnit.BASE as its unit, the largest value where the ms precision is reached is 2^51 = 2.3E15, which is
 * around 71000 years. This is sufficient to store a date on an Epoch level precise to a ms.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class TimeUnit extends AbsoluteLinearUnit<TimeUnit, DurationUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "m2" as the SI signature. */
    public static final Quantity<TimeUnit> BASE = new Quantity<>("Time", "s");

    /**
     * The base unit for time with an artifical "zero" point with a calculation in seconds. Note that when the offset becomes
     * large, precision of a float or double might not be enough for the required resolution of a Time. A float has around 7
     * significant digits (23 bit mantissa), whereas a double has around 16 significant digits (52 bit mantissa). This means
     * that when we need to have a float time that is precise to microseconds, the Time value should not go above 2^22 = 4.0E6.
     * This is <b>not</b> enough to store Epoch values! So feeding System.TimeInMillis() to a FloatTime with TimeUnit.BASE as
     * its unit is not having the required precision. For a (double) Time with TimeUnit.BASE as its unit, the largest value
     * where the ms precision is reached is 2^51 = 2.3E15, which is around 71000 years. This is sufficient to store a date on an
     * Epoch level precise to a ms.
     */
    public static final TimeUnit BASE_SECOND = new TimeUnit().build(new AbsoluteLinearUnit.Builder<TimeUnit, DurationUnit>()
            .setQuantity(BASE).setId("s").setName("second").setUnitSystem(UnitSystem.SI_DERIVED)
            .setSiPrefixes(SIPrefixes.UNIT, 1.0).setRelativeUnit(DurationUnit.SECOND).setScale(new OffsetLinearScale(1.0, 0.0))
            .setDefaultDisplayAbbreviation("s").setDefaultTextualAbbreviation("s").setAdditionalAbbreviations("sec"));

    /** The default unit for time is BASE_SECOND. */
    public static final TimeUnit DEFAULT = BASE_SECOND;

    /** The base unit for time with an artificial "zero" point with a calculation in microseconds. */
    public static final TimeUnit BASE_MICROSECOND = BASE_SECOND.deriveLinearOffset(1.0E-6, 0.0, DurationUnit.MICROSECOND, "mus",
            "microsecond", UnitSystem.SI_DERIVED, "\u03BCs", "mus", "\u03BCsec", "musec");

    /** The base unit for time with an artificial "zero" point with a calculation in milliseconds. */
    public static final TimeUnit BASE_MILLISECOND = BASE_SECOND.deriveLinearOffset(1.0E-3, 0.0, DurationUnit.MILLISECOND, "ms",
            "millisecond", UnitSystem.SI_DERIVED, "ms", "ms", "msec");

    /** The base unit for time with an artificial "zero" point with a calculation in minutes. */
    public static final TimeUnit BASE_MINUTE = BASE_SECOND.deriveLinearOffset(60.0, 0.0, DurationUnit.MINUTE, "min", "minute",
            UnitSystem.SI_DERIVED, "min", "min");

    /** The base unit for time with an artificial "zero" point with a calculation in hours. */
    public static final TimeUnit BASE_HOUR = BASE_SECOND.deriveLinearOffset(3600.0, 0.0, DurationUnit.HOUR, "h", "hour",
            UnitSystem.SI_DERIVED, "h", "h", "hr", "hour");

    /** The base unit for time with an artificial "zero" point with a calculation in days. */
    public static final TimeUnit BASE_DAY = BASE_SECOND.deriveLinearOffset(24.0 * 3600.0, 0.0, DurationUnit.DAY, "day", "day",
            UnitSystem.SI_DERIVED, "day", "day");

    /** The base unit for time with an artificial "zero" point with a calculation in weeks. */
    public static final TimeUnit BASE_WEEK = BASE_SECOND.deriveLinearOffset(7.0 * 24.0 * 3600.0, 0.0, DurationUnit.WEEK, "wk",
            "week", UnitSystem.SI_DERIVED, "wk", "wk", "week");

    /**
     * The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in seconds. The base should be taken in
     * such a way that a resolution of a millisecond is still 'visible' on a date in, say, 2020. When 1-1-1970 is used as the
     * origin, 1-1-2021 has a value of 1,577,836,800,000 milliseconds = 1.6E12 ms. If we want to be precise on the ms level, we
     * need 12 significant digits. A float has around 7 significant digits (23 bit mantissa), whereas a double has around 16
     * significant digits (52 bit mantissa). This means that a float time with an offset of 1-1-1970 is at best precise to a
     * minute level. A double time is precise to microseconds. Therefore, avoid using float times that use the EPOCH.
     */
    public static final TimeUnit EPOCH_SECOND = BASE_SECOND.deriveLinearOffset(1.0, 0.0, DurationUnit.SECOND, "s(Y1970)",
            "seconds since 1/1/70", UnitSystem.OTHER, "s(Y1970)", "s(Y1970)", "sec(Y1970)");

    /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in microseconds. */
    public static final TimeUnit EPOCH_MICROSECOND =
            BASE_SECOND.deriveLinearOffset(1.0E-6, 0.0, DurationUnit.MICROSECOND, "mus(Y1970)", "microseconds since 1/1/70",
                    UnitSystem.OTHER, "\u03BCs(Y1970)", "mus(Y1970)", "\u03BCsec(Y1970)", "musec(Y1970)");

    /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in milliseconds. */
    public static final TimeUnit EPOCH_MILLISECOND = BASE_SECOND.deriveLinearOffset(1.0E-3, 0.0, DurationUnit.MILLISECOND,
            "ms(Y1970)", "milliseconds since 1/1/70", UnitSystem.OTHER, "ms(Y1970)", "ms(Y1970)", "msec(Y1970)");

    /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in minutes. */
    public static final TimeUnit EPOCH_MINUTE = BASE_SECOND.deriveLinearOffset(60.0, 0.0, DurationUnit.MINUTE, "min(Y1970)",
            "minutes since 1/1/70", UnitSystem.OTHER, "min(Y1970)", "min(Y1970)");

    /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in hours. */
    public static final TimeUnit EPOCH_HOUR = BASE_SECOND.deriveLinearOffset(3600.0, 0.0, DurationUnit.HOUR, "h(Y1970)",
            "hours since 1/1/70", UnitSystem.OTHER, "h(Y1970)", "h(Y1970)", "hour(Y1970)", "hr(Y1970)");

    /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in days. */
    public static final TimeUnit EPOCH_DAY = BASE_SECOND.deriveLinearOffset(24.0 * 3600.0, 0.0, DurationUnit.DAY, "day(Y1970)",
            "days since 1/1/70", UnitSystem.OTHER, "day(Y1970)", "day(Y1970)");

    /** The POSIX and Gregorian Epoch: January 1, 1970 at 00:00 UTC with a calculation in weeks. */
    public static final TimeUnit EPOCH_WEEK = BASE_SECOND.deriveLinearOffset(7.0 * 24.0 * 3600.0, 0.0, DurationUnit.WEEK,
            "wk(Y1970)", "weeks since 1/1/70", UnitSystem.OTHER, "wk(Y1970)", "wk(Y1970)", "week(Y1970)");

    /**
     * The Epoch with 0001-01-01 AD at 00:00 as the origin with a calculation in seconds. When 1-1-0001 is used as the origin,
     * 1-1-2021 has a value of around 6.4E13 ms. If we want to be precise on the ms level, we need 13 significant digits. A
     * float has around 7 significant digits (23 bit mantissa), whereas a double has around 16 significant digits (52 bit
     * mantissa). This means that a float time with an offset of 1-1-0001 is at best precise to an hour level. A double time is
     * precise to microseconds. Therefore, avoid using float times that use the EPOCH_YEAR1_SECOND.
     */
    public static final TimeUnit EPOCH_YEAR1_SECOND = EPOCH_SECOND.deriveLinearOffset(1.0,
            new GregorianCalendar(1, 0, 1, 0, 0, 0).getTimeInMillis() / 1000.0, DurationUnit.SECOND, "s(Y1)",
            "seconds since 1-1-0001 00:00", UnitSystem.OTHER, "s(Y1)", "s(Y1)", "sec(Y1)");

    /**
     * The Epoch with J2000.0 as the origin, which is The Gregorian date January 1, 2000 at 12:00 GMT (noon) with a calculation
     * in seconds. When 1-1-2000 is used as the origin, 1-1-2021 has a value of around 6.3E11 ms. If we want to be precise on
     * the ms level, we need 11 significant digits. A float has around 7 significant digits (23 bit mantissa), whereas a double
     * has around 16 significant digits (52 bit mantissa). This means that a float time with an offset of 1-1-2000 is at best
     * precise to a minute level. A double time is precise to fractions of microseconds. Therefore, avoid using float times that
     * use the EPOCH_J2000_SECOND.
     */
    public static final TimeUnit EPOCH_J2000_SECOND = EPOCH_SECOND.deriveLinearOffset(1.0,
            new GregorianCalendar(2000, 0, 1, 12, 0, 0).getTimeInMillis() / 1000.0, DurationUnit.SECOND, "s(Y2000)",
            "seconds since 1-1-2000 12:00 GMT", UnitSystem.OTHER, "s(Y2000)", "s(Y2000)", "sec(Y2000)");

}
