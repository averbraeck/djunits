package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;

/**
 * Easy access methods for the FloatLinearDensity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatLinearDensity extends AbstractFloatScalarRel<LinearDensityUnit, FloatLinearDensity>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatLinearDensity ZERO = new FloatLinearDensity(0.0f, LinearDensityUnit.SI);

    /** Constant with value one. */
    public static final FloatLinearDensity ONE = new FloatLinearDensity(1.0f, LinearDensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatLinearDensity NaN = new FloatLinearDensity(Float.NaN, LinearDensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatLinearDensity POSITIVE_INFINITY =
            new FloatLinearDensity(Float.POSITIVE_INFINITY, LinearDensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatLinearDensity NEGATIVE_INFINITY =
            new FloatLinearDensity(Float.NEGATIVE_INFINITY, LinearDensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatLinearDensity POS_MAXVALUE = new FloatLinearDensity(Float.MAX_VALUE, LinearDensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatLinearDensity NEG_MAXVALUE = new FloatLinearDensity(-Float.MAX_VALUE, LinearDensityUnit.SI);

    /**
     * Construct FloatLinearDensity scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatLinearDensity(final float value, final LinearDensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatLinearDensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatLinearDensity(final FloatLinearDensity value)
    {
        super(value);
    }

    /**
     * Construct FloatLinearDensity scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatLinearDensity(final double value, final LinearDensityUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatLinearDensity instantiateRel(final float value, final LinearDensityUnit unit)
    {
        return new FloatLinearDensity(value, unit);
    }

    /**
     * Construct FloatLinearDensity scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatLinearDensity instantiateSI(final float value)
    {
        return new FloatLinearDensity(value, LinearDensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatLinearDensity interpolate(final FloatLinearDensity zero, final FloatLinearDensity one, final float ratio)
    {
        return new FloatLinearDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatLinearDensity max(final FloatLinearDensity r1, final FloatLinearDensity r2)
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
    public static FloatLinearDensity max(final FloatLinearDensity r1, final FloatLinearDensity r2,
            final FloatLinearDensity... rn)
    {
        FloatLinearDensity maxr = r1.gt(r2) ? r1 : r2;
        for (FloatLinearDensity r : rn)
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
    public static FloatLinearDensity min(final FloatLinearDensity r1, final FloatLinearDensity r2)
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
    public static FloatLinearDensity min(final FloatLinearDensity r1, final FloatLinearDensity r2,
            final FloatLinearDensity... rn)
    {
        FloatLinearDensity minr = r1.lt(r2) ? r1 : r2;
        for (FloatLinearDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatLinearDensity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatLinearDensity
     * @return FloatLinearDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatLinearDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatLinearDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatLinearDensity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            LinearDensityUnit unit = LinearDensityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatLinearDensity(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatLinearDensity from " + text);
    }

    /**
     * Returns a FloatLinearDensity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatLinearDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatLinearDensity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatLinearDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatLinearDensity: empty unitString");
        LinearDensityUnit unit = LinearDensityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatLinearDensity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatLinearDensity with unit " + unitString);
    }

    /**
     * Calculate the division of FloatLinearDensity and FloatLinearDensity, which results in a FloatDimensionless scalar.
     * @param v FloatLinearDensity; scalar
     * @return FloatDimensionless; scalar as a division of FloatLinearDensity and FloatLinearDensity
     */
    public final FloatDimensionless divide(final FloatLinearDensity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLinearDensity and FloatLength, which results in a FloatDimensionless scalar.
     * @param v FloatLinearDensity; scalar
     * @return FloatDimensionless; scalar as a multiplication of FloatLinearDensity and FloatLength
     */
    public final FloatDimensionless times(final FloatLength v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLinearDensity and FloatArea, which results in a FloatLength scalar.
     * @param v FloatLinearDensity; scalar
     * @return FloatLength; scalar as a multiplication of FloatLinearDensity and FloatArea
     */
    public final FloatLength times(final FloatArea v)
    {
        return new FloatLength(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLinearDensity and FloatEnergy, which results in a FloatForce scalar.
     * @param v FloatLinearDensity; scalar
     * @return FloatForce; scalar as a multiplication of FloatLinearDensity and FloatEnergy
     */
    public final FloatForce times(final FloatEnergy v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLinearDensity and FloatSpeed, which results in a FloatFrequency scalar.
     * @param v FloatLinearDensity; scalar
     * @return FloatFrequency; scalar as a multiplication of FloatLinearDensity and FloatSpeed
     */
    public final FloatFrequency times(final FloatSpeed v)
    {
        return new FloatFrequency(this.si * v.si, FrequencyUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLength reciprocal()
    {
        return FloatLength.instantiateSI(1.0f / this.si);
    }

}
