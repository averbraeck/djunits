package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarAbs;

/**
 * Easy access methods for the Absolute AbsoluteTemperature DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AbsoluteTemperature
        extends AbstractDoubleScalarAbs<AbsoluteTemperatureUnit, AbsoluteTemperature, TemperatureUnit, Temperature>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final AbsoluteTemperature ZERO = new AbsoluteTemperature(0.0, AbsoluteTemperatureUnit.DEFAULT);

    /**
     * Construct AbsoluteTemperature scalar.
     * @param value double; value
     * @param unit AbsoluteTemperatureUnit; unit for the double value
     */
    public AbsoluteTemperature(final double value, final AbsoluteTemperatureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct AbsoluteTemperature scalar.
     * @param value AbsoluteTemperature; Scalar from which to construct this instance
     */
    public AbsoluteTemperature(final AbsoluteTemperature value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final AbsoluteTemperature instantiateAbs(final double value, final AbsoluteTemperatureUnit unit)
    {
        return new AbsoluteTemperature(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final Temperature instantiateRel(final double value, final TemperatureUnit unit)
    {
        return new Temperature(value, unit);
    }

    /**
     * Construct AbsoluteTemperature scalar.
     * @param value double; value in SI units
     * @return AbsoluteTemperature; the new scalar with the SI value
     */
    public static final AbsoluteTemperature instantiateSI(final double value)
    {
        return new AbsoluteTemperature(value, AbsoluteTemperatureUnit.DEFAULT);
    }

    /**
     * Interpolate between two values.
     * @param zero AbsoluteTemperature; the low value
     * @param one AbsoluteTemperature; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return AbsoluteTemperature; a Scalar at the ratio between
     */
    public static AbsoluteTemperature interpolate(final AbsoluteTemperature zero, final AbsoluteTemperature one,
            final double ratio)
    {
        return new AbsoluteTemperature(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 AbsoluteTemperature; the first scalar
     * @param a2 AbsoluteTemperature; the second scalar
     * @return AbsoluteTemperature; the maximum value of two absolute scalars
     */
    public static AbsoluteTemperature max(final AbsoluteTemperature a1, final AbsoluteTemperature a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 AbsoluteTemperature; the first scalar
     * @param a2 AbsoluteTemperature; the second scalar
     * @param an AbsoluteTemperature...; the other scalars
     * @return the maximum value of more than two absolute scalars
     */
    public static AbsoluteTemperature max(final AbsoluteTemperature a1, final AbsoluteTemperature a2,
            final AbsoluteTemperature... an)
    {
        AbsoluteTemperature maxa = a1.gt(a2) ? a1 : a2;
        for (AbsoluteTemperature a : an)
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
     * @param a1 AbsoluteTemperature; the first scalar
     * @param a2 AbsoluteTemperature; the second scalar
     * @return the minimum value of two absolute scalars
     */
    public static AbsoluteTemperature min(final AbsoluteTemperature a1, final AbsoluteTemperature a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 AbsoluteTemperature; the first scalar
     * @param a2 AbsoluteTemperature; the second scalar
     * @param an AbsoluteTemperature...; the other scalars
     * @return the minimum value of more than two absolute scalars
     */
    public static AbsoluteTemperature min(final AbsoluteTemperature a1, final AbsoluteTemperature a2,
            final AbsoluteTemperature... an)
    {
        AbsoluteTemperature mina = a1.lt(a2) ? a1 : a2;
        for (AbsoluteTemperature a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a AbsoluteTemperature representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a AbsoluteTemperature
     * @return AbsoluteTemperature; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AbsoluteTemperature valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing AbsoluteTemperature: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing AbsoluteTemperature: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AbsoluteTemperatureUnit unit = AbsoluteTemperatureUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new AbsoluteTemperature(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing AbsoluteTemperature from " + text);
    }

    /**
     * Returns a AbsoluteTemperature based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return AbsoluteTemperature; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AbsoluteTemperature of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing AbsoluteTemperature: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing AbsoluteTemperature: empty unitString");
        AbsoluteTemperatureUnit unit = AbsoluteTemperatureUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new AbsoluteTemperature(value, unit);
        }
        throw new IllegalArgumentException("Error parsing AbsoluteTemperature with unit " + unitString);
    }

}
