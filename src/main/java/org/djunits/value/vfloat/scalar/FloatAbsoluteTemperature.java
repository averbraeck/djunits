package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarAbs;

/**
 * Easy access methods for the FloatAbsoluteTemperature FloatScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAbsoluteTemperature
        extends AbstractFloatScalarAbs<AbsoluteTemperatureUnit, FloatAbsoluteTemperature, TemperatureUnit, FloatTemperature>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAbsoluteTemperature ZERO = new FloatAbsoluteTemperature(0.0f, AbsoluteTemperatureUnit.DEFAULT);

    /**
     * Construct FloatAbsoluteTemperature scalar.
     * @param value float; the float value
     * @param unit AbsoluteTemperatureUnit; unit for the float value
     */
    public FloatAbsoluteTemperature(final float value, final AbsoluteTemperatureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAbsoluteTemperature scalar using a double value.
     * @param value double; the double value
     * @param unit AbsoluteTemperatureUnit; unit for the resulting float value
     */
    public FloatAbsoluteTemperature(final double value, final AbsoluteTemperatureUnit unit)
    {
        super((float) value, unit);
    }

    /**
     * Construct FloatAbsoluteTemperature scalar.
     * @param value FloatAbsoluteTemperature; Scalar from which to construct this instance
     */
    public FloatAbsoluteTemperature(final FloatAbsoluteTemperature value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatAbsoluteTemperature instantiateAbs(final float value, final AbsoluteTemperatureUnit unit)
    {
        return new FloatAbsoluteTemperature(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatTemperature instantiateRel(final float value, final TemperatureUnit unit)
    {
        return new FloatTemperature(value, unit);
    }

    /**
     * Construct FloatAbsoluteTemperature scalar.
     * @param value float; the float value in BASE units
     * @return FloatAbsoluteTemperature; the new scalar with the BASE value
     */
    public static final FloatAbsoluteTemperature instantiateSI(final float value)
    {
        return new FloatAbsoluteTemperature(value, AbsoluteTemperatureUnit.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero FloatAbsoluteTemperature; the low value
     * @param one FloatAbsoluteTemperature; the high value
     * @param ratio float; the ratio between 0 and 1, inclusive
     * @return FloatAbsoluteTemperature; a Scalar at the ratio between
     */
    public static FloatAbsoluteTemperature interpolate(final FloatAbsoluteTemperature zero, final FloatAbsoluteTemperature one,
            final float ratio)
    {
        return new FloatAbsoluteTemperature(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 FloatAbsoluteTemperature; the first scalar
     * @param a2 FloatAbsoluteTemperature; the second scalar
     * @return FloatAbsoluteTemperature; the maximum value of two absolute scalars
     */
    public static FloatAbsoluteTemperature max(final FloatAbsoluteTemperature a1, final FloatAbsoluteTemperature a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 FloatAbsoluteTemperature; the first scalar
     * @param a2 FloatAbsoluteTemperature; the second scalar
     * @param an FloatAbsoluteTemperature...; the other scalars
     * @return FloatAbsoluteTemperature; the maximum value of more than two absolute scalars
     */
    public static FloatAbsoluteTemperature max(final FloatAbsoluteTemperature a1, final FloatAbsoluteTemperature a2,
            final FloatAbsoluteTemperature... an)
    {
        FloatAbsoluteTemperature maxa = a1.gt(a2) ? a1 : a2;
        for (FloatAbsoluteTemperature a : an)
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
     * @param a1 FloatAbsoluteTemperature; the first scalar
     * @param a2 FloatAbsoluteTemperature; the second scalar
     * @return FloatAbsoluteTemperature; the minimum value of two absolute scalars
     */
    public static FloatAbsoluteTemperature min(final FloatAbsoluteTemperature a1, final FloatAbsoluteTemperature a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 FloatAbsoluteTemperature; the first scalar
     * @param a2 FloatAbsoluteTemperature; the second scalar
     * @param an FloatAbsoluteTemperature...; the other scalars
     * @return FloatAbsoluteTemperature; the minimum value of more than two absolute scalars
     */
    public static FloatAbsoluteTemperature min(final FloatAbsoluteTemperature a1, final FloatAbsoluteTemperature a2,
            final FloatAbsoluteTemperature... an)
    {
        FloatAbsoluteTemperature mina = a1.lt(a2) ? a1 : a2;
        for (FloatAbsoluteTemperature a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a FloatAbsoluteTemperature representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatAbsoluteTemperature
     * @return FloatAbsoluteTemperature; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAbsoluteTemperature valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAbsoluteTemperature: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAbsoluteTemperature: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AbsoluteTemperatureUnit unit = AbsoluteTemperatureUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatAbsoluteTemperature(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatAbsoluteTemperature from " + text);
    }

    /**
     * Returns a FloatAbsoluteTemperature based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatAbsoluteTemperature; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAbsoluteTemperature of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAbsoluteTemperature: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAbsoluteTemperature: empty unitString");
        AbsoluteTemperatureUnit unit = AbsoluteTemperatureUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatAbsoluteTemperature(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatAbsoluteTemperature with unit " + unitString);
    }

}
