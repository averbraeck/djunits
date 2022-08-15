package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarAbs;

/**
 * Easy access methods for the FloatPosition FloatScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatPosition extends AbstractFloatScalarAbs<PositionUnit, FloatPosition, LengthUnit, FloatLength>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatPosition ZERO = new FloatPosition(0.0f, PositionUnit.DEFAULT);

    /**
     * Construct FloatPosition scalar.
     * @param value float; the float value
     * @param unit PositionUnit; unit for the float value
     */
    public FloatPosition(final float value, final PositionUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatPosition scalar using a double value.
     * @param value double; the double value
     * @param unit PositionUnit; unit for the resulting float value
     */
    public FloatPosition(final double value, final PositionUnit unit)
    {
        super((float) value, unit);
    }

    /**
     * Construct FloatPosition scalar.
     * @param value FloatPosition; Scalar from which to construct this instance
     */
    public FloatPosition(final FloatPosition value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatPosition instantiateAbs(final float value, final PositionUnit unit)
    {
        return new FloatPosition(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatLength instantiateRel(final float value, final LengthUnit unit)
    {
        return new FloatLength(value, unit);
    }

    /**
     * Construct FloatPosition scalar.
     * @param value float; the float value in BASE units
     * @return FloatPosition; the new scalar with the BASE value
     */
    public static final FloatPosition instantiateSI(final float value)
    {
        return new FloatPosition(value, PositionUnit.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero FloatPosition; the low value
     * @param one FloatPosition; the high value
     * @param ratio float; the ratio between 0 and 1, inclusive
     * @return FloatPosition; a Scalar at the ratio between
     */
    public static FloatPosition interpolate(final FloatPosition zero, final FloatPosition one, final float ratio)
    {
        return new FloatPosition(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 FloatPosition; the first scalar
     * @param a2 FloatPosition; the second scalar
     * @return FloatPosition; the maximum value of two absolute scalars
     */
    public static FloatPosition max(final FloatPosition a1, final FloatPosition a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 FloatPosition; the first scalar
     * @param a2 FloatPosition; the second scalar
     * @param an FloatPosition...; the other scalars
     * @return FloatPosition; the maximum value of more than two absolute scalars
     */
    public static FloatPosition max(final FloatPosition a1, final FloatPosition a2, final FloatPosition... an)
    {
        FloatPosition maxa = a1.gt(a2) ? a1 : a2;
        for (FloatPosition a : an)
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
     * @param a1 FloatPosition; the first scalar
     * @param a2 FloatPosition; the second scalar
     * @return FloatPosition; the minimum value of two absolute scalars
     */
    public static FloatPosition min(final FloatPosition a1, final FloatPosition a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 FloatPosition; the first scalar
     * @param a2 FloatPosition; the second scalar
     * @param an FloatPosition...; the other scalars
     * @return FloatPosition; the minimum value of more than two absolute scalars
     */
    public static FloatPosition min(final FloatPosition a1, final FloatPosition a2, final FloatPosition... an)
    {
        FloatPosition mina = a1.lt(a2) ? a1 : a2;
        for (FloatPosition a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a FloatPosition representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatPosition
     * @return FloatPosition; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatPosition valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatPosition: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatPosition: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            PositionUnit unit = PositionUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatPosition(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatPosition from " + text);
    }

    /**
     * Returns a FloatPosition based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatPosition; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatPosition of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatPosition: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatPosition: empty unitString");
        PositionUnit unit = PositionUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatPosition(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatPosition with unit " + unitString);
    }

}
