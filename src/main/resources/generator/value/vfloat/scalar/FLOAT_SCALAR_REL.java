package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import org.djunits.value.Relative;
import org.djunits.value.util.ValueUtil;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.*;

/**
 * Easy access methods for the Float%Type% FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class Float%Type% extends AbstractFloatScalarRel<%Type%Unit, Float%Type%> %DIMLESS%
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Float%Type% ZERO = new Float%Type%(0.0f, %Type%Unit.SI);

    /** Constant with value one. */
    public static final Float%Type% ONE = new Float%Type%(1.0f, %Type%Unit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Float%Type% NaN = new Float%Type%(Float.NaN, %Type%Unit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Float%Type% POSITIVE_INFINITY = new Float%Type%(Float.POSITIVE_INFINITY, %Type%Unit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Float%Type% NEGATIVE_INFINITY = new Float%Type%(Float.NEGATIVE_INFINITY, %Type%Unit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Float%Type% POS_MAXVALUE = new Float%Type%(Float.MAX_VALUE, %Type%Unit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Float%Type% NEG_MAXVALUE = new Float%Type%(-Float.MAX_VALUE, %Type%Unit.SI);

    /**
     * Construct Float%Type% scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public Float%Type%(final float value, final %Type%Unit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Float%Type% scalar.
     * @param value Scalar from which to construct this instance
     */
    public Float%Type%(final Float%Type% value)
    {
        super(value);
    }

    /**
     * Construct Float%Type% scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public Float%Type%(final double value, final %Type%Unit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final Float%Type% instantiateRel(final float value, final %Type%Unit unit)
    {
        return new Float%Type%(value, unit);
    }

    /**
     * Construct Float%Type% scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final Float%Type% instantiateSI(final float value)
    {
        return new Float%Type%(value, %Type%Unit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static Float%Type% interpolate(final Float%Type% zero, final Float%Type% one, final float ratio)
    {
        return new Float%Type%(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero
            .getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Float%Type% max(final Float%Type% r1, final Float%Type% r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @param rn the other scalars
     * @return the maximum value of more than two relative scalars
     */
    public static Float%Type% max(final Float%Type% r1, final Float%Type% r2, final Float%Type%... rn)
    {
        Float%Type% maxr = r1.gt(r2) ? r1 : r2;
        for (Float%Type% r : rn)
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
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the minimum value of two relative scalars
     */
    public static Float%Type% min(final Float%Type% r1, final Float%Type% r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @param rn the other scalars
     * @return the minimum value of more than two relative scalars
     */
    public static Float%Type% min(final Float%Type% r1, final Float%Type% r2, final Float%Type%... rn)
    {
        Float%Type% minr = r1.lt(r2) ? r1 : r2;
        for (Float%Type% r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Float%Type% representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Float%Type%
     * @return Float%Type%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Float%Type% valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Float%Type%: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Float%Type%: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            %Type%Unit unit = %Type%Unit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new Float%Type%(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Float%Type% from " + text);
    }

    /**
     * Returns a Float%Type% based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Float%Type%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Float%Type% of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Float%Type%: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Float%Type%: empty unitString");
        %Type%Unit unit = %Type%Unit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Float%Type%(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Float%Type% with unit " + unitString);
    }

##FLOATMASS##

%FORMULAS%%Type%%
}


