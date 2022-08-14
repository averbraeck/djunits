package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.function.DimensionlessFunctions;

/**
 * Easy access methods for the %Type% DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class %Type% extends AbstractDoubleScalarRel<%Type%Unit, %Type%> %DIMLESS%
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final %Type% ZERO = new %Type%(0.0, %Type%Unit.SI);

    /** Constant with value one. */
    public static final %Type% ONE = new %Type%(1.0, %Type%Unit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final %Type% NaN = new %Type%(Double.NaN, %Type%Unit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final %Type% POSITIVE_INFINITY = new %Type%(Double.POSITIVE_INFINITY, %Type%Unit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final %Type% NEGATIVE_INFINITY = new %Type%(Double.NEGATIVE_INFINITY, %Type%Unit.SI);

    /** Constant with value MAX_VALUE. */
    public static final %Type% POS_MAXVALUE = new %Type%(Double.MAX_VALUE, %Type%Unit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final %Type% NEG_MAXVALUE = new %Type%(-Double.MAX_VALUE, %Type%Unit.SI);

    /**
     * Construct %Type% scalar.
     * @param value double; the double value
     * @param unit %Type%Unit; unit for the double value
     */
    public %Type%(final double value, final %Type%Unit unit)
    {
        super(value, unit);
    }

    /**
     * Construct %Type% scalar.
     * @param value %Type%; Scalar from which to construct this instance
     */
    public %Type%(final %Type% value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final %Type% instantiateRel(final double value, final %Type%Unit unit)
    {
        return new %Type%(value, unit);
    }

    /**
     * Construct %Type% scalar.
     * @param value double; the double value in SI units
     * @return %Type%; the new scalar with the SI value
     */
    public static final %Type% instantiateSI(final double value)
    {
        return new %Type%(value, %Type%Unit.SI);
    }
    
    /**
     * Interpolate between two values.
     * @param zero %Type%; the low value
     * @param one %Type%; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return %Type%; a Scalar at the ratio between
     */
    public static %Type% interpolate(final %Type% zero, final %Type% one, final double ratio)
    {
        return new %Type%(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }
    
    /**
     * Return the maximum value of two relative scalars.
     * @param r1 %Type%; the first scalar
     * @param r2 %Type%; the second scalar
     * @return %Type%; the maximum value of two relative scalars
     */
    public static %Type% max(final %Type% r1, final %Type% r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 %Type%; the first scalar
     * @param r2 %Type%; the second scalar
     * @param rn %Type%...; the other scalars
     * @return %Type%; the maximum value of more than two relative scalars
     */
    public static %Type% max(final %Type% r1, final %Type% r2, final %Type%... rn)
    {
        %Type% maxr = r1.gt(r2) ? r1 : r2;
        for (%Type% r : rn)
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
     * @param r1 %Type%; the first scalar
     * @param r2 %Type%; the second scalar
     * @return %Type%; the minimum value of two relative scalars
     */
    public static %Type% min(final %Type% r1, final %Type% r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 %Type%; the first scalar
     * @param r2 %Type%; the second scalar
     * @param rn %Type%...; the other scalars
     * @return %Type%; the minimum value of more than two relative scalars
     */
    public static %Type% min(final %Type% r1, final %Type% r2, final %Type%... rn)
    {
        %Type% minr = r1.lt(r2) ? r1 : r2;
        for (%Type% r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a %Type% representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a %Type%
     * @return %Type%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static %Type% valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing %Type%: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing %Type%: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            %Type%Unit unit = %Type%Unit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new %Type%(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing %Type% from " + text);
    }

    /**
     * Returns a %Type% based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return %Type%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static %Type% of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing %Type%: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing %Type%: empty unitString");
        %Type%Unit unit = %Type%Unit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new %Type%(value, unit);
        }
        throw new IllegalArgumentException("Error parsing %Type% with unit " + unitString);
    }

##MASS##
    
%FORMULAS%%Type%%
}


