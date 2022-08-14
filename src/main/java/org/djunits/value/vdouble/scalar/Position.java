package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarAbs;

/**
 * Easy access methods for the Absolute Position DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Position extends AbstractDoubleScalarAbs<PositionUnit, Position, LengthUnit, Length>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Position ZERO = new Position(0.0, PositionUnit.DEFAULT);

    /**
     * Construct Position scalar.
     * @param value double; value
     * @param unit PositionUnit; unit for the double value
     */
    public Position(final double value, final PositionUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Position scalar.
     * @param value Position; Scalar from which to construct this instance
     */
    public Position(final Position value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Position instantiateAbs(final double value, final PositionUnit unit)
    {
        return new Position(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final Length instantiateRel(final double value, final LengthUnit unit)
    {
        return new Length(value, unit);
    }

    /**
     * Construct Position scalar.
     * @param value double; value in SI units
     * @return Position; the new scalar with the SI value
     */
    public static final Position instantiateSI(final double value)
    {
        return new Position(value, PositionUnit.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero Position; the low value
     * @param one Position; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Position; a Scalar at the ratio between
     */
    public static Position interpolate(final Position zero, final Position one, final double ratio)
    {
        return new Position(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 Position; the first scalar
     * @param a2 Position; the second scalar
     * @return Position; the maximum value of two absolute scalars
     */
    public static Position max(final Position a1, final Position a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 Position; the first scalar
     * @param a2 Position; the second scalar
     * @param an Position...; the other scalars
     * @return the maximum value of more than two absolute scalars
     */
    public static Position max(final Position a1, final Position a2, final Position... an)
    {
        Position maxa = a1.gt(a2) ? a1 : a2;
        for (Position a : an)
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
     * @param a1 Position; the first scalar
     * @param a2 Position; the second scalar
     * @return the minimum value of two absolute scalars
     */
    public static Position min(final Position a1, final Position a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 Position; the first scalar
     * @param a2 Position; the second scalar
     * @param an Position...; the other scalars
     * @return the minimum value of more than two absolute scalars
     */
    public static Position min(final Position a1, final Position a2, final Position... an)
    {
        Position mina = a1.lt(a2) ? a1 : a2;
        for (Position a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a Position representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Position
     * @return Position; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Position valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Position: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Position: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            PositionUnit unit = PositionUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Position(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Position from " + text);
    }

    /**
     * Returns a Position based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Position; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Position of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Position: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Position: empty unitString");
        PositionUnit unit = PositionUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Position(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Position with unit " + unitString);
    }

}
