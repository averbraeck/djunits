package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarAbs;

/**
 * Easy access methods for the Absolute Time DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * Note that when the offset of a stored absolute Time becomes large, precision of a double might not be enough for the required
 * resolution of a Time. A double has around 16 significant digits (52 bit mantissa). This means that when we need to have a
 * double Time with TimeUnit.BASE as its unit, the largest value where the ms precision is reached is 2^51 = 2.3E15, which is
 * around 71000 years. This is sufficient to store a date in the 21st Century with a BASE or an Epoch offset precise to a
 * microsecond.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Time extends AbstractDoubleScalarAbs<TimeUnit, Time, DurationUnit, Duration>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Time ZERO = new Time(0.0, TimeUnit.DEFAULT);

    /**
     * Construct Time scalar.
     * @param value double; value
     * @param unit TimeUnit; unit for the double value
     */
    public Time(final double value, final TimeUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Time scalar.
     * @param value Time; Scalar from which to construct this instance
     */
    public Time(final Time value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Time instantiateAbs(final double value, final TimeUnit unit)
    {
        return new Time(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final Duration instantiateRel(final double value, final DurationUnit unit)
    {
        return new Duration(value, unit);
    }

    /**
     * Construct Time scalar.
     * @param value double; value in SI units
     * @return Time; the new scalar with the SI value
     */
    public static final Time instantiateSI(final double value)
    {
        return new Time(value, TimeUnit.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero Time; the low value
     * @param one Time; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Time; a Scalar at the ratio between
     */
    public static Time interpolate(final Time zero, final Time one, final double ratio)
    {
        return new Time(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 Time; the first scalar
     * @param a2 Time; the second scalar
     * @return Time; the maximum value of two absolute scalars
     */
    public static Time max(final Time a1, final Time a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 Time; the first scalar
     * @param a2 Time; the second scalar
     * @param an Time...; the other scalars
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
     * @param a1 Time; the first scalar
     * @param a2 Time; the second scalar
     * @return the minimum value of two absolute scalars
     */
    public static Time min(final Time a1, final Time a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 Time; the first scalar
     * @param a2 Time; the second scalar
     * @param an Time...; the other scalars
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
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Time
     * @return Time; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Time valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Time: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Time: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            TimeUnit unit = TimeUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Time(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Time from " + text);
    }

    /**
     * Returns a Time based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Time; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Time of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Time: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Time: empty unitString");
        TimeUnit unit = TimeUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Time(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Time with unit " + unitString);
    }

}
