package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarAbs;

/**
 * Easy access methods for the Float%TypeAbs% FloatScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
##FLOATTIME##
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class Float%TypeAbs% extends AbstractFloatScalarAbs<%TypeAbsUnit%, Float%TypeAbs%, %TypeRelUnit%, Float%TypeRel%>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Float%TypeAbs% ZERO = new Float%TypeAbs%(0.0f, %TypeAbsUnit%.DEFAULT);

    /**
     * Construct Float%TypeAbs% scalar.
     * @param value float; the float value
     * @param unit %TypeAbsUnit%; unit for the float value
     */
    public Float%TypeAbs%(final float value, final %TypeAbsUnit% unit)
    {
        super(value, unit);
    }

    /**
     * Construct Float%TypeAbs% scalar using a double value.
     * @param value double; the double value
     * @param unit %TypeAbsUnit%; unit for the resulting float value
     */
    public Float%TypeAbs%(final double value, final %TypeAbsUnit% unit)
    {
        super((float) value, unit);
    }

    /**
     * Construct Float%TypeAbs% scalar.
     * @param value Float%TypeAbs%; Scalar from which to construct this instance
     */
    public Float%TypeAbs%(final Float%TypeAbs% value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Float%TypeAbs% instantiateAbs(final float value, final %TypeAbsUnit% unit)
    {
        return new Float%TypeAbs%(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final Float%TypeRel% instantiateRel(final float value, final %TypeRelUnit% unit)
    {
        return new Float%TypeRel%(value, unit);
    }

    /**
     * Construct Float%TypeAbs% scalar.
     * @param value float; the float value in BASE units
     * @return Float%TypeAbs%; the new scalar with the BASE value
     */
    public static final Float%TypeAbs% instantiateSI(final float value)
    {
        return new Float%TypeAbs%(value, %TypeAbsUnit%.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero Float%TypeAbs%; the low value
     * @param one Float%TypeAbs%; the high value
     * @param ratio float; the ratio between 0 and 1, inclusive
     * @return Float%TypeAbs%; a Scalar at the ratio between
     */
    public static Float%TypeAbs% interpolate(final Float%TypeAbs% zero, final Float%TypeAbs% one, final float ratio)
    {
        return new Float%TypeAbs%(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero
            .getDisplayUnit());
    }
    
    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 Float%TypeAbs%; the first scalar
     * @param a2 Float%TypeAbs%; the second scalar
     * @return Float%TypeAbs%; the maximum value of two absolute scalars
     */
    public static Float%TypeAbs% max(final Float%TypeAbs% a1, final Float%TypeAbs% a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 Float%TypeAbs%; the first scalar
     * @param a2 Float%TypeAbs%; the second scalar
     * @param an Float%TypeAbs%...; the other scalars
     * @return Float%TypeAbs%; the maximum value of more than two absolute scalars
     */
    public static Float%TypeAbs% max(final Float%TypeAbs% a1, final Float%TypeAbs% a2, final Float%TypeAbs%... an)
    {
        Float%TypeAbs% maxa = a1.gt(a2) ? a1 : a2;
        for (Float%TypeAbs% a : an)
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
     * @param a1 Float%TypeAbs%; the first scalar
     * @param a2 Float%TypeAbs%; the second scalar
     * @return Float%TypeAbs%; the minimum value of two absolute scalars
     */
    public static Float%TypeAbs% min(final Float%TypeAbs% a1, final Float%TypeAbs% a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 Float%TypeAbs%; the first scalar
     * @param a2 Float%TypeAbs%; the second scalar
     * @param an Float%TypeAbs%...; the other scalars
     * @return Float%TypeAbs%; the minimum value of more than two absolute scalars
     */
    public static Float%TypeAbs% min(final Float%TypeAbs% a1, final Float%TypeAbs% a2, final Float%TypeAbs%... an)
    {
        Float%TypeAbs% mina = a1.lt(a2) ? a1 : a2;
        for (Float%TypeAbs% a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }
    
    /**
     * Returns a Float%TypeAbs% representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Float%TypeAbs%
     * @return Float%TypeAbs%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Float%TypeAbs% valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Float%TypeAbs%: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Float%TypeAbs%: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            %TypeAbsUnit% unit = %TypeAbsUnit%.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new Float%TypeAbs%(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Float%TypeAbs% from " + text);
    }

    /**
     * Returns a Float%TypeAbs% based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Float%TypeAbs%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Float%TypeAbs% of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Float%TypeAbs%: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Float%TypeAbs%: empty unitString");
        %TypeAbsUnit% unit = %TypeAbsUnit%.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Float%TypeAbs%(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Float%TypeAbs% with unit " + unitString);
    }


%FORMULAS%%TypeAbs%%
}

