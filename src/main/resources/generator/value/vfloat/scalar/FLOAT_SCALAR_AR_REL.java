package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRelWithAbs;

/**
 * Easy access methods for the Float%TypeRel% FloatScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class Float%TypeRel% extends AbstractFloatScalarRelWithAbs<%TypeAbsUnit%, Float%TypeAbs%, %TypeRelUnit%, Float%TypeRel%>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Float%TypeRel% ZERO = new Float%TypeRel%(0.0f, %TypeRelUnit%.SI);

    /** Constant with value one. */
    public static final Float%TypeRel% ONE = new Float%TypeRel%(1.0f, %TypeRelUnit%.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Float%TypeRel% NaN = new Float%TypeRel%(Float.NaN, %TypeRelUnit%.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Float%TypeRel% POSITIVE_INFINITY = new Float%TypeRel%(Float.POSITIVE_INFINITY, %TypeRelUnit%.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Float%TypeRel% NEGATIVE_INFINITY = new Float%TypeRel%(Float.NEGATIVE_INFINITY, %TypeRelUnit%.SI);

    /** Constant with value MAX_VALUE. */
    public static final Float%TypeRel% POS_MAXVALUE = new Float%TypeRel%(Float.MAX_VALUE, %TypeRelUnit%.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Float%TypeRel% NEG_MAXVALUE = new Float%TypeRel%(-Float.MAX_VALUE, %TypeRelUnit%.SI);

    /**
     * Construct Float%TypeRel% scalar.
     * @param value float; the float value
     * @param unit %TypeRelUnit%; unit for the float value
     */
    public Float%TypeRel%(final float value, final %TypeRelUnit% unit)
    {
        super(value, unit);
    }

    /**
     * Construct Float%TypeRel% scalar.
     * @param value Float%TypeRel%; Scalar from which to construct this instance
     */
    public Float%TypeRel%(final Float%TypeRel% value)
    {
        super(value);
    }

    /**
     * Construct Float%TypeRel% scalar using a double value.
     * @param value double; the double value
     * @param unit %TypeRelUnit%; unit for the resulting float value
     */
    public Float%TypeRel%(final double value, final %TypeRelUnit% unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final Float%TypeRel% instantiateRel(final float value, final %TypeRelUnit% unit)
    {
        return new Float%TypeRel%(value, unit);
    }

    /**
     * Construct Float%TypeRel% scalar.
     * @param value float; the float value in SI units
     * @return Float%TypeRel%; the new scalar with the SI value
     */
    public static final Float%TypeRel% instantiateSI(final float value)
    {
        return new Float%TypeRel%(value, %TypeRelUnit%.SI);
    }

    /** {@inheritDoc} */
    @Override
    public final Float%TypeAbs% instantiateAbs(final float value, final %TypeAbsUnit% unit)
    {
        return new Float%TypeAbs%(value, unit);
    }

    /**
     * Interpolate between two values.
     * @param zero Float%TypeRel%; the low value
     * @param one Float%TypeRel%; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Float%TypeRel%; a Scalar at the ratio between
     */
    public static Float%TypeRel% interpolate(final Float%TypeRel% zero, final Float%TypeRel% one, final float ratio)
    {
        return new Float%TypeRel%(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero
            .getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Float%TypeRel%; the first scalar
     * @param r2 Float%TypeRel%; the second scalar
     * @return Float%TypeRel%; the maximum value of two relative scalars
     */
    public static Float%TypeRel% max(final Float%TypeRel% r1, final Float%TypeRel% r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Float%TypeRel%; the first scalar
     * @param r2 Float%TypeRel%; the second scalar
     * @param rn Float%TypeRel%...; the other scalars
     * @return Float%TypeRel%; the maximum value of more than two relative scalars
     */
    public static Float%TypeRel% max(final Float%TypeRel% r1, final Float%TypeRel% r2, final Float%TypeRel%... rn)
    {
        Float%TypeRel% maxr = r1.gt(r2) ? r1 : r2;
        for (Float%TypeRel% r : rn)
        {
            if (r.gt(maxr))
            {
                maxr = r;
            }
        }
        return maxr;
    }

    /**
     * Return the minimum value of two relative scalars.
     * @param r1 Float%TypeRel%; the first scalar
     * @param r2 Float%TypeRel%; the second scalar
     * @return Float%TypeRel%; the minimum value of two relative scalars
     */
    public static Float%TypeRel% min(final Float%TypeRel% r1, final Float%TypeRel% r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Float%TypeRel%; the first scalar
     * @param r2 Float%TypeRel%; the second scalar
     * @param rn Float%TypeRel%...; the other scalars
     * @return Float%TypeRel%; the minimum value of more than two relative scalars
     */
    public static Float%TypeRel% min(final Float%TypeRel% r1, final Float%TypeRel% r2, final Float%TypeRel%... rn)
    {
        Float%TypeRel% minr = r1.lt(r2) ? r1 : r2;
        for (Float%TypeRel% r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Float%TypeRel% representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Float%TypeRel%
     * @return Float%TypeRel%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Float%TypeRel% valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Float%TypeRel%: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Float%TypeRel%: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            %TypeRelUnit% unit = %TypeRelUnit%.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new Float%TypeRel%(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Float%TypeRel% from " + text);
    }

    /**
     * Returns a Float%TypeRel% based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Float%TypeRel%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Float%TypeRel% of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Float%TypeRel%: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Float%TypeRel%: empty unitString");
        %TypeRelUnit% unit = %TypeRelUnit%.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Float%TypeRel%(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Float%TypeRel% with unit " + unitString);
    }


%FORMULAS%%TypeRel%%
}

