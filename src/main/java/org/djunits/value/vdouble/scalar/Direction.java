package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Absolute Direction DoubleScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Direction extends DoubleScalarAbs<DirectionUnit, Direction, AngleUnit, Angle>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Direction ZERO = new Direction(0.0, DirectionUnit.DEFAULT);

    /**
     * Construct Direction scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Direction(final double value, final DirectionUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Direction scalar.
     * @param value Scalar from which to construct this instance
     */
    public Direction(final Direction value)
    {
        super(value);
    }

    @Override
    public final Direction instantiateAbs(final double value, final DirectionUnit unit)
    {
        return new Direction(value, unit);
    }

    @Override
    public final Angle instantiateRel(final double value, final AngleUnit unit)
    {
        return new Angle(value, unit);
    }

    /**
     * Construct Direction scalar based on a BASE unit value.
     * @param value value in BASE units
     * @return the new scalar with the BASE unit value
     */
    public static final Direction ofSI(final double value)
    {
        return new Direction(value, DirectionUnit.DEFAULT);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Direction at the given ratio between 0 and 1
     */
    public static Direction interpolate(final Direction zero, final Direction one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Direction(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @return the maximum value of two absolute scalars
     */
    public static Direction max(final Direction a1, final Direction a2)
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
    public static Direction max(final Direction a1, final Direction a2, final Direction... an)
    {
        Direction maxa = a1.gt(a2) ? a1 : a2;
        for (Direction a : an)
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
    public static Direction min(final Direction a1, final Direction a2)
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
    public static Direction min(final Direction a1, final Direction a2, final Direction... an)
    {
        Direction mina = a1.lt(a2) ? a1 : a2;
        for (Direction a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a Direction representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Direction
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Direction valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Direction: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Direction: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            DirectionUnit unit = DirectionUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Direction", unitString);
            return new Direction(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Direction from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Direction based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Direction of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Direction: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Direction: empty unitString");
        DirectionUnit unit = DirectionUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Direction with unit %s", unitString);
        return new Direction(value, unit);
    }

}
