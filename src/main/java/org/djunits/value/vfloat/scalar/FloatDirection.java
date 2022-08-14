package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarAbs;

/**
 * Easy access methods for the FloatDirection FloatScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDirection extends AbstractFloatScalarAbs<DirectionUnit, FloatDirection, AngleUnit, FloatAngle>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatDirection ZERO = new FloatDirection(0.0f, DirectionUnit.DEFAULT);

    /**
     * Construct FloatDirection scalar.
     * @param value float; the float value
     * @param unit DirectionUnit; unit for the float value
     */
    public FloatDirection(final float value, final DirectionUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatDirection scalar using a double value.
     * @param value double; the double value
     * @param unit DirectionUnit; unit for the resulting float value
     */
    public FloatDirection(final double value, final DirectionUnit unit)
    {
        super((float) value, unit);
    }

    /**
     * Construct FloatDirection scalar.
     * @param value FloatDirection; Scalar from which to construct this instance
     */
    public FloatDirection(final FloatDirection value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDirection instantiateAbs(final float value, final DirectionUnit unit)
    {
        return new FloatDirection(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatAngle instantiateRel(final float value, final AngleUnit unit)
    {
        return new FloatAngle(value, unit);
    }

    /**
     * Construct FloatDirection scalar.
     * @param value float; the float value in BASE units
     * @return FloatDirection; the new scalar with the BASE value
     */
    public static final FloatDirection instantiateSI(final float value)
    {
        return new FloatDirection(value, DirectionUnit.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero FloatDirection; the low value
     * @param one FloatDirection; the high value
     * @param ratio float; the ratio between 0 and 1, inclusive
     * @return FloatDirection; a Scalar at the ratio between
     */
    public static FloatDirection interpolate(final FloatDirection zero, final FloatDirection one, final float ratio)
    {
        return new FloatDirection(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 FloatDirection; the first scalar
     * @param a2 FloatDirection; the second scalar
     * @return FloatDirection; the maximum value of two absolute scalars
     */
    public static FloatDirection max(final FloatDirection a1, final FloatDirection a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 FloatDirection; the first scalar
     * @param a2 FloatDirection; the second scalar
     * @param an FloatDirection...; the other scalars
     * @return FloatDirection; the maximum value of more than two absolute scalars
     */
    public static FloatDirection max(final FloatDirection a1, final FloatDirection a2, final FloatDirection... an)
    {
        FloatDirection maxa = a1.gt(a2) ? a1 : a2;
        for (FloatDirection a : an)
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
     * @param a1 FloatDirection; the first scalar
     * @param a2 FloatDirection; the second scalar
     * @return FloatDirection; the minimum value of two absolute scalars
     */
    public static FloatDirection min(final FloatDirection a1, final FloatDirection a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 FloatDirection; the first scalar
     * @param a2 FloatDirection; the second scalar
     * @param an FloatDirection...; the other scalars
     * @return FloatDirection; the minimum value of more than two absolute scalars
     */
    public static FloatDirection min(final FloatDirection a1, final FloatDirection a2, final FloatDirection... an)
    {
        FloatDirection mina = a1.lt(a2) ? a1 : a2;
        for (FloatDirection a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a FloatDirection representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatDirection
     * @return FloatDirection; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatDirection valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatDirection: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatDirection: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            DirectionUnit unit = DirectionUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatDirection(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatDirection from " + text);
    }

    /**
     * Returns a FloatDirection based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatDirection; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatDirection of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatDirection: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatDirection: empty unitString");
        DirectionUnit unit = DirectionUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatDirection(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatDirection with unit " + unitString);
    }

}
