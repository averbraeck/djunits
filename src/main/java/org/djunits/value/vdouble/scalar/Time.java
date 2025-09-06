package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Absolute Time DoubleScalar.
 * <p>
 * Note that when the offset of a stored absolute Time becomes large, precision of a double might not be enough for the required
 * resolution of a Time. A double has around 16 significant digits (52 bit mantissa). This means that when we need to have a
 * double Time with TimeUnit.BASE as its unit, the largest value where the ms precision is reached is 2^51 = 2.3E15, which is
 * around 71000 years. This is sufficient to store a date in the 21st Century with a BASE or an Epoch offset precise to a
 * microsecond.
 * </p>
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Time extends DoubleScalarAbs<TimeUnit, Time, DurationUnit, Duration>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Time ZERO = new Time(0.0, TimeUnit.DEFAULT);

    /**
     * Construct Time scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Time(final double value, final TimeUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Time scalar.
     * @param value Scalar from which to construct this instance
     */
    public Time(final Time value)
    {
        super(value);
    }

    @Override
    public final Time instantiateAbs(final double value, final TimeUnit unit)
    {
        return new Time(value, unit);
    }

    @Override
    public final Duration instantiateRel(final double value, final DurationUnit unit)
    {
        return new Duration(value, unit);
    }

    /**
     * Construct Time scalar based on a BASE unit value.
     * @param value value in BASE units
     * @return the new scalar with the BASE unit value
     */
    public static final Time ofSI(final double value)
    {
        return new Time(value, TimeUnit.DEFAULT);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Time at the given ratio between 0 and 1
     */
    public static Time interpolate(final Time zero, final Time one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Time(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @return the maximum value of two absolute scalars
     */
    public static Time max(final Time a1, final Time a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @param an the other scalars
     * @return the maximum value of more than two absolute scalars
     */
    public static Time max(final Time a1, final Time a2, final Time... an)
    {
        Time maxa = a1.gt(a2) ? a1 : a2;
        for (Time a : an)
        {
            if (a.gt(maxa))
            {
                maxa = a;
            }
        }
        return maxa;
    }

    /**
     * Return the minimum value of two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @return the minimum value of two absolute scalars
     */
    public static Time min(final Time a1, final Time a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @param an the other scalars
     * @return the minimum value of more than two absolute scalars
     */
    public static Time min(final Time a1, final Time a2, final Time... an)
    {
        Time mina = a1.lt(a2) ? a1 : a2;
        for (Time a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
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
        Throw.whenNull(text, "Error parsing Time: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Time: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            TimeUnit unit = TimeUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Time", unitString);
            return new Time(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Time from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
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
        Throw.whenNull(unitString, "Error parsing Time: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Time: empty unitString");
        TimeUnit unit = TimeUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Time with unit %s", unitString);
        return new Time(value, unit);
    }

}
