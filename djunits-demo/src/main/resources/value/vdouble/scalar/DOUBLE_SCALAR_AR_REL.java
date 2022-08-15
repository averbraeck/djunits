package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRelWithAbs;

/**
 * Easy access methods for the Relative %TypeRel% DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class %TypeRel% extends AbstractDoubleScalarRelWithAbs<%TypeAbsUnit%, %TypeAbs%, %TypeRelUnit%, %TypeRel%>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final %TypeRel% ZERO = new %TypeRel%(0.0, %TypeRelUnit%.SI);

    /** Constant with value one. */
    public static final %TypeRel% ONE = new %TypeRel%(1.0, %TypeRelUnit%.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final %TypeRel% NaN = new %TypeRel%(Double.NaN, %TypeRelUnit%.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final %TypeRel% POSITIVE_INFINITY = new %TypeRel%(Double.POSITIVE_INFINITY, %TypeRelUnit%.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final %TypeRel% NEGATIVE_INFINITY = new %TypeRel%(Double.NEGATIVE_INFINITY, %TypeRelUnit%.SI);

    /** Constant with value MAX_VALUE. */
    public static final %TypeRel% POS_MAXVALUE = new %TypeRel%(Double.MAX_VALUE, %TypeRelUnit%.SI);

    /** Constant with value -MAX_VALUE. */
    public static final %TypeRel% NEG_MAXVALUE = new %TypeRel%(-Double.MAX_VALUE, %TypeRelUnit%.SI);

    /**
     * Construct %TypeRel% scalar.
     * @param value double; double value
     * @param unit %TypeRelUnit%; unit for the double value
     */
    public %TypeRel%(final double value, final %TypeRelUnit% unit)
    {
        super(value, unit);
    }

    /**
     * Construct %TypeRel% scalar.
     * @param value %TypeRel%; Scalar from which to construct this instance
     */
    public %TypeRel%(final %TypeRel% value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final %TypeRel% instantiateRel(final double value, final %TypeRelUnit% unit)
    {
        return new %TypeRel%(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final %TypeAbs% instantiateAbs(final double value, final %TypeAbsUnit% unit)
    {
        return new %TypeAbs%(value, unit);
    }

    /**
     * Construct %TypeRel% scalar.
     * @param value double; the double value in SI units
     * @return %TypeRel%; the new scalar with the SI value
     */
    public static final %TypeRel% instantiateSI(final double value)
    {
        return new %TypeRel%(value, %TypeRelUnit%.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero %TypeRel%; the low value
     * @param one %TypeRel%; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return %TypeRel%; a Scalar at the ratio between
     */
    public static %TypeRel% interpolate(final %TypeRel% zero, final %TypeRel% one, final double ratio)
    {
        return new %TypeRel%(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero
            .getDisplayUnit());
    }
    
    /**
     * Return the maximum value of two relative scalars.
     * @param r1 %TypeRel%; the first scalar
     * @param r2 %TypeRel%; the second scalar
     * @return %TypeRel%; the maximum value of two relative scalars
     */
    public static %TypeRel% max(final %TypeRel% r1, final %TypeRel% r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 %TypeRel%; the first scalar
     * @param r2 %TypeRel%; the second scalar
     * @param rn %TypeRel%...; the other scalars
     * @return %TypeRel%; the maximum value of more than two relative scalars
     */
    public static %TypeRel% max(final %TypeRel% r1, final %TypeRel% r2, final %TypeRel%... rn)
    {
        %TypeRel% maxr = r1.gt(r2) ? r1 : r2;
        for (%TypeRel% r : rn)
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
     * @param r1 %TypeRel%; the first scalar
     * @param r2 %TypeRel%; the second scalar
     * @return %TypeRel%; the minimum value of two relative scalars
     */
    public static %TypeRel% min(final %TypeRel% r1, final %TypeRel% r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 %TypeRel%; the first scalar
     * @param r2 %TypeRel%; the second scalar
     * @param rn %TypeRel%...; the other scalars
     * @return %TypeRel%; the minimum value of more than two relative scalars
     */
    public static %TypeRel% min(final %TypeRel% r1, final %TypeRel% r2, final %TypeRel%... rn)
    {
        %TypeRel% minr = r1.lt(r2) ? r1 : r2;
        for (%TypeRel% r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }
    
    /**
     * Returns a %TypeRel% representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a %TypeRel%
     * @return %TypeRel%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static %TypeRel% valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing %TypeRel%: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing %TypeRel%: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            %TypeRelUnit% unit = %TypeRelUnit%.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new %TypeRel%(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing %TypeRel% from " + text);
    }

    /**
     * Returns a %TypeRel% based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return %TypeRel%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static %TypeRel% of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing %TypeRel%: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing %TypeRel%: empty unitString");
        %TypeRelUnit% unit = %TypeRelUnit%.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new %TypeRel%(value, unit);
        }
        throw new IllegalArgumentException("Error parsing %TypeRel% with unit " + unitString);
    }


%FORMULAS%%TypeRel%%
    
}

