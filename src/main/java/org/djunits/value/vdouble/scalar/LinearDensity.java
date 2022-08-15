package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;

/**
 * Easy access methods for the LinearDensity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class LinearDensity extends AbstractDoubleScalarRel<LinearDensityUnit, LinearDensity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final LinearDensity ZERO = new LinearDensity(0.0, LinearDensityUnit.SI);

    /** Constant with value one. */
    public static final LinearDensity ONE = new LinearDensity(1.0, LinearDensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LinearDensity NaN = new LinearDensity(Double.NaN, LinearDensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LinearDensity POSITIVE_INFINITY = new LinearDensity(Double.POSITIVE_INFINITY, LinearDensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LinearDensity NEGATIVE_INFINITY = new LinearDensity(Double.NEGATIVE_INFINITY, LinearDensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final LinearDensity POS_MAXVALUE = new LinearDensity(Double.MAX_VALUE, LinearDensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final LinearDensity NEG_MAXVALUE = new LinearDensity(-Double.MAX_VALUE, LinearDensityUnit.SI);

    /**
     * Construct LinearDensity scalar.
     * @param value double; the double value
     * @param unit LinearDensityUnit; unit for the double value
     */
    public LinearDensity(final double value, final LinearDensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct LinearDensity scalar.
     * @param value LinearDensity; Scalar from which to construct this instance
     */
    public LinearDensity(final LinearDensity value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final LinearDensity instantiateRel(final double value, final LinearDensityUnit unit)
    {
        return new LinearDensity(value, unit);
    }

    /**
     * Construct LinearDensity scalar.
     * @param value double; the double value in SI units
     * @return LinearDensity; the new scalar with the SI value
     */
    public static final LinearDensity instantiateSI(final double value)
    {
        return new LinearDensity(value, LinearDensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero LinearDensity; the low value
     * @param one LinearDensity; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return LinearDensity; a Scalar at the ratio between
     */
    public static LinearDensity interpolate(final LinearDensity zero, final LinearDensity one, final double ratio)
    {
        return new LinearDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 LinearDensity; the first scalar
     * @param r2 LinearDensity; the second scalar
     * @return LinearDensity; the maximum value of two relative scalars
     */
    public static LinearDensity max(final LinearDensity r1, final LinearDensity r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 LinearDensity; the first scalar
     * @param r2 LinearDensity; the second scalar
     * @param rn LinearDensity...; the other scalars
     * @return LinearDensity; the maximum value of more than two relative scalars
     */
    public static LinearDensity max(final LinearDensity r1, final LinearDensity r2, final LinearDensity... rn)
    {
        LinearDensity maxr = r1.gt(r2) ? r1 : r2;
        for (LinearDensity r : rn)
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
     * @param r1 LinearDensity; the first scalar
     * @param r2 LinearDensity; the second scalar
     * @return LinearDensity; the minimum value of two relative scalars
     */
    public static LinearDensity min(final LinearDensity r1, final LinearDensity r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 LinearDensity; the first scalar
     * @param r2 LinearDensity; the second scalar
     * @param rn LinearDensity...; the other scalars
     * @return LinearDensity; the minimum value of more than two relative scalars
     */
    public static LinearDensity min(final LinearDensity r1, final LinearDensity r2, final LinearDensity... rn)
    {
        LinearDensity minr = r1.lt(r2) ? r1 : r2;
        for (LinearDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a LinearDensity representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a LinearDensity
     * @return LinearDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LinearDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing LinearDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing LinearDensity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            LinearDensityUnit unit = LinearDensityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new LinearDensity(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing LinearDensity from " + text);
    }

    /**
     * Returns a LinearDensity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return LinearDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LinearDensity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing LinearDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing LinearDensity: empty unitString");
        LinearDensityUnit unit = LinearDensityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new LinearDensity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing LinearDensity with unit " + unitString);
    }

    /**
     * Calculate the division of LinearDensity and LinearDensity, which results in a Dimensionless scalar.
     * @param v LinearDensity; scalar
     * @return Dimensionless; scalar as a division of LinearDensity and LinearDensity
     */
    public final Dimensionless divide(final LinearDensity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of LinearDensity and Length, which results in a Dimensionless scalar.
     * @param v LinearDensity; scalar
     * @return Dimensionless; scalar as a multiplication of LinearDensity and Length
     */
    public final Dimensionless times(final Length v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of LinearDensity and Area, which results in a Length scalar.
     * @param v LinearDensity; scalar
     * @return Length; scalar as a multiplication of LinearDensity and Area
     */
    public final Length times(final Area v)
    {
        return new Length(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of LinearDensity and Energy, which results in a Force scalar.
     * @param v LinearDensity; scalar
     * @return Force; scalar as a multiplication of LinearDensity and Energy
     */
    public final Force times(final Energy v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of LinearDensity and Speed, which results in a Frequency scalar.
     * @param v LinearDensity; scalar
     * @return Frequency; scalar as a multiplication of LinearDensity and Speed
     */
    public final Frequency times(final Speed v)
    {
        return new Frequency(this.si * v.si, FrequencyUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public Length reciprocal()
    {
        return Length.instantiateSI(1.0 / this.si);
    }

}
