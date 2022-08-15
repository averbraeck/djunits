package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarAbs;

/**
 * Easy access methods for the Absolute %TypeAbs% DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
##TIME##
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class %TypeAbs% extends AbstractDoubleScalarAbs<%TypeAbsUnit%, %TypeAbs%, %TypeRelUnit%, %TypeRel%>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final %TypeAbs% ZERO = new %TypeAbs%(0.0, %TypeAbsUnit%.DEFAULT);

    /**
     * Construct %TypeAbs% scalar.
     * @param value double; value
     * @param unit %TypeAbsUnit%; unit for the double value
     */
    public %TypeAbs%(final double value, final %TypeAbsUnit% unit)
    {
        super(value, unit);
    }

    /**
     * Construct %TypeAbs% scalar.
     * @param value %TypeAbs%; Scalar from which to construct this instance
     */
    public %TypeAbs%(final %TypeAbs% value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final %TypeAbs% instantiateAbs(final double value, final %TypeAbsUnit% unit)
    {
        return new %TypeAbs%(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final %TypeRel% instantiateRel(final double value, final %TypeRelUnit% unit)
    {
        return new %TypeRel%(value, unit);
    }

    /**
     * Construct %TypeAbs% scalar.
     * @param value double; value in SI units
     * @return %TypeAbs%; the new scalar with the SI value
     */
    public static final %TypeAbs% instantiateSI(final double value)
    {
        return new %TypeAbs%(value, %TypeAbsUnit%.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero %TypeAbs%; the low value
     * @param one %TypeAbs%; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return %TypeAbs%; a Scalar at the ratio between
     */
    public static %TypeAbs% interpolate(final %TypeAbs% zero, final %TypeAbs% one, final double ratio)
    {
        return new %TypeAbs%(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero
            .getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 %TypeAbs%; the first scalar
     * @param a2 %TypeAbs%; the second scalar
     * @return %TypeAbs%; the maximum value of two absolute scalars
     */
    public static %TypeAbs% max(final %TypeAbs% a1, final %TypeAbs% a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 %TypeAbs%; the first scalar
     * @param a2 %TypeAbs%; the second scalar
     * @param an %TypeAbs%...; the other scalars
     * @return the maximum value of more than two absolute scalars
     */
    public static %TypeAbs% max(final %TypeAbs% a1, final %TypeAbs% a2, final %TypeAbs%... an)
    {
        %TypeAbs% maxa = a1.gt(a2) ? a1 : a2;
        for (%TypeAbs% a : an)
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
     * @param a1 %TypeAbs%; the first scalar
     * @param a2 %TypeAbs%; the second scalar
     * @return the minimum value of two absolute scalars
     */
    public static %TypeAbs% min(final %TypeAbs% a1, final %TypeAbs% a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 %TypeAbs%; the first scalar
     * @param a2 %TypeAbs%; the second scalar
     * @param an %TypeAbs%...; the other scalars
     * @return the minimum value of more than two absolute scalars
     */
    public static %TypeAbs% min(final %TypeAbs% a1, final %TypeAbs% a2, final %TypeAbs%... an)
    {
        %TypeAbs% mina = a1.lt(a2) ? a1 : a2;
        for (%TypeAbs% a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a %TypeAbs% representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a %TypeAbs%
     * @return %TypeAbs%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static %TypeAbs% valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing %TypeAbs%: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing %TypeAbs%: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            %TypeAbsUnit% unit = %TypeAbsUnit%.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new %TypeAbs%(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing %TypeAbs% from " + text);
    }

    /**
     * Returns a %TypeAbs% based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return %TypeAbs%; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static %TypeAbs% of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing %TypeAbs%: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing %TypeAbs%: empty unitString");
        %TypeAbsUnit% unit = %TypeAbsUnit%.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new %TypeAbs%(value, unit);
        }
        throw new IllegalArgumentException("Error parsing %TypeAbs% with unit " + unitString);
    }

    
%FORMULAS%%TypeAbs%%
}

