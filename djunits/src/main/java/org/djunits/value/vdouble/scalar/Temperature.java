package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRelWithAbs;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Relative Temperature DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Temperature
        extends AbstractDoubleScalarRelWithAbs<AbsoluteTemperatureUnit, AbsoluteTemperature, TemperatureUnit, Temperature>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Temperature ZERO = new Temperature(0.0, TemperatureUnit.SI);

    /** Constant with value one. */
    public static final Temperature ONE = new Temperature(1.0, TemperatureUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Temperature NaN = new Temperature(Double.NaN, TemperatureUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Temperature POSITIVE_INFINITY = new Temperature(Double.POSITIVE_INFINITY, TemperatureUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Temperature NEGATIVE_INFINITY = new Temperature(Double.NEGATIVE_INFINITY, TemperatureUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Temperature POS_MAXVALUE = new Temperature(Double.MAX_VALUE, TemperatureUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Temperature NEG_MAXVALUE = new Temperature(-Double.MAX_VALUE, TemperatureUnit.SI);

    /**
     * Construct Temperature scalar.
     * @param value double; double value
     * @param unit TemperatureUnit; unit for the double value
     */
    public Temperature(final double value, final TemperatureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Temperature scalar.
     * @param value Temperature; Scalar from which to construct this instance
     */
    public Temperature(final Temperature value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Temperature instantiateRel(final double value, final TemperatureUnit unit)
    {
        return new Temperature(value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final AbsoluteTemperature instantiateAbs(final double value, final AbsoluteTemperatureUnit unit)
    {
        return new AbsoluteTemperature(value, unit);
    }

    /**
     * Construct Temperature scalar.
     * @param value double; the double value in SI units
     * @return Temperature; the new scalar with the SI value
     */
    public static final Temperature instantiateSI(final double value)
    {
        return new Temperature(value, TemperatureUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Temperature; the low value
     * @param one Temperature; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Temperature; a Scalar at the ratio between
     */
    public static Temperature interpolate(final Temperature zero, final Temperature one, final double ratio)
    {
        return new Temperature(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Temperature; the first scalar
     * @param r2 Temperature; the second scalar
     * @return Temperature; the maximum value of two relative scalars
     */
    public static Temperature max(final Temperature r1, final Temperature r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Temperature; the first scalar
     * @param r2 Temperature; the second scalar
     * @param rn Temperature...; the other scalars
     * @return Temperature; the maximum value of more than two relative scalars
     */
    public static Temperature max(final Temperature r1, final Temperature r2, final Temperature... rn)
    {
        Temperature maxr = r1.gt(r2) ? r1 : r2;
        for (Temperature r : rn)
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
     * @param r1 Temperature; the first scalar
     * @param r2 Temperature; the second scalar
     * @return Temperature; the minimum value of two relative scalars
     */
    public static Temperature min(final Temperature r1, final Temperature r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Temperature; the first scalar
     * @param r2 Temperature; the second scalar
     * @param rn Temperature...; the other scalars
     * @return Temperature; the minimum value of more than two relative scalars
     */
    public static Temperature min(final Temperature r1, final Temperature r2, final Temperature... rn)
    {
        Temperature minr = r1.lt(r2) ? r1 : r2;
        for (Temperature r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Temperature representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a Temperature
     * @return Temperature; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Temperature valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Temperature: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Temperature: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            TemperatureUnit unit = TemperatureUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Temperature(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Temperature from " + text);
    }

    /**
     * Returns a Temperature based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Temperature; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Temperature of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Temperature: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Temperature: empty unitString");
        TemperatureUnit unit = TemperatureUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Temperature(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Temperature with unit " + unitString);
    }

    /**
     * Calculate the division of Temperature and Temperature, which results in a Dimensionless scalar.
     * @param v Temperature; scalar
     * @return Dimensionless; scalar as a division of Temperature and Temperature
     */
    public final Dimensionless divide(final Temperature v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
