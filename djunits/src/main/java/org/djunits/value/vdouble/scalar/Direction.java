package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarAbs;

/**
 * Easy access methods for the Absolute Direction DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Direction extends AbstractDoubleScalarAbs<DirectionUnit, Direction, AngleUnit, Angle>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Direction ZERO = new Direction(0.0, DirectionUnit.DEFAULT);

    /**
     * Construct Direction scalar.
     * @param value double; value
     * @param unit DirectionUnit; unit for the double value
     */
    public Direction(final double value, final DirectionUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Direction scalar.
     * @param value Direction; Scalar from which to construct this instance
     */
    public Direction(final Direction value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Direction instantiateAbs(final double value, final DirectionUnit unit)
    {
        return new Direction(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final Angle instantiateRel(final double value, final AngleUnit unit)
    {
        return new Angle(value, unit);
    }

    /**
     * Construct Direction scalar.
     * @param value double; value in SI units
     * @return Direction; the new scalar with the SI value
     */
    public static final Direction instantiateSI(final double value)
    {
        return new Direction(value, DirectionUnit.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero Direction; the low value
     * @param one Direction; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Direction; a Scalar at the ratio between
     */
    public static Direction interpolate(final Direction zero, final Direction one, final double ratio)
    {
        return new Direction(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 Direction; the first scalar
     * @param a2 Direction; the second scalar
     * @return Direction; the maximum value of two absolute scalars
     */
    public static Direction max(final Direction a1, final Direction a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 Direction; the first scalar
     * @param a2 Direction; the second scalar
     * @param an Direction...; the other scalars
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
     * @param a1 Direction; the first scalar
     * @param a2 Direction; the second scalar
     * @return the minimum value of two absolute scalars
     */
    public static Direction min(final Direction a1, final Direction a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 Direction; the first scalar
     * @param a2 Direction; the second scalar
     * @param an Direction...; the other scalars
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
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Direction
     * @return Direction; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Direction valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Direction: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Direction: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            DirectionUnit unit = DirectionUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Direction(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Direction from " + text);
    }

    /**
     * Returns a Direction based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Direction; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Direction of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Direction: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Direction: empty unitString");
        DirectionUnit unit = DirectionUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Direction(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Direction with unit " + unitString);
    }

}
