package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;

/**
 * Easy access methods for the FloatFrequency FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatFrequency extends AbstractFloatScalarRel<FrequencyUnit, FloatFrequency>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatFrequency ZERO = new FloatFrequency(0.0f, FrequencyUnit.SI);

    /** Constant with value one. */
    public static final FloatFrequency ONE = new FloatFrequency(1.0f, FrequencyUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatFrequency NaN = new FloatFrequency(Float.NaN, FrequencyUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatFrequency POSITIVE_INFINITY = new FloatFrequency(Float.POSITIVE_INFINITY, FrequencyUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatFrequency NEGATIVE_INFINITY = new FloatFrequency(Float.NEGATIVE_INFINITY, FrequencyUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatFrequency POS_MAXVALUE = new FloatFrequency(Float.MAX_VALUE, FrequencyUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatFrequency NEG_MAXVALUE = new FloatFrequency(-Float.MAX_VALUE, FrequencyUnit.SI);

    /**
     * Construct FloatFrequency scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatFrequency(final float value, final FrequencyUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatFrequency scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatFrequency(final FloatFrequency value)
    {
        super(value);
    }

    /**
     * Construct FloatFrequency scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatFrequency(final double value, final FrequencyUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatFrequency instantiateRel(final float value, final FrequencyUnit unit)
    {
        return new FloatFrequency(value, unit);
    }

    /**
     * Construct FloatFrequency scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatFrequency instantiateSI(final float value)
    {
        return new FloatFrequency(value, FrequencyUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatFrequency interpolate(final FloatFrequency zero, final FloatFrequency one, final float ratio)
    {
        return new FloatFrequency(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatFrequency max(final FloatFrequency r1, final FloatFrequency r2)
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
    public static FloatFrequency max(final FloatFrequency r1, final FloatFrequency r2, final FloatFrequency... rn)
    {
        FloatFrequency maxr = r1.gt(r2) ? r1 : r2;
        for (FloatFrequency r : rn)
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
    public static FloatFrequency min(final FloatFrequency r1, final FloatFrequency r2)
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
    public static FloatFrequency min(final FloatFrequency r1, final FloatFrequency r2, final FloatFrequency... rn)
    {
        FloatFrequency minr = r1.lt(r2) ? r1 : r2;
        for (FloatFrequency r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatFrequency representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatFrequency
     * @return FloatFrequency; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatFrequency valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatFrequency: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatFrequency: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            FrequencyUnit unit = FrequencyUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatFrequency(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatFrequency from " + text);
    }

    /**
     * Returns a FloatFrequency based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatFrequency; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatFrequency of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatFrequency: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatFrequency: empty unitString");
        FrequencyUnit unit = FrequencyUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatFrequency(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatFrequency with unit " + unitString);
    }

    /**
     * Calculate the division of FloatFrequency and FloatFrequency, which results in a FloatDimensionless scalar.
     * @param v FloatFrequency; scalar
     * @return FloatDimensionless; scalar as a division of FloatFrequency and FloatFrequency
     */
    public final FloatDimensionless divide(final FloatFrequency v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatDuration, which results in a FloatDimensionless scalar.
     * @param v FloatFrequency; scalar
     * @return FloatDimensionless; scalar as a multiplication of FloatFrequency and FloatDuration
     */
    public final FloatDimensionless times(final FloatDuration v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatLength, which results in a FloatSpeed scalar.
     * @param v FloatFrequency; scalar
     * @return FloatSpeed; scalar as a multiplication of FloatFrequency and FloatLength
     */
    public final FloatSpeed times(final FloatLength v)
    {
        return new FloatSpeed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatSpeed, which results in a FloatAcceleration scalar.
     * @param v FloatFrequency; scalar
     * @return FloatAcceleration; scalar as a multiplication of FloatFrequency and FloatSpeed
     */
    public final FloatAcceleration times(final FloatSpeed v)
    {
        return new FloatAcceleration(this.si * v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatEnergy, which results in a FloatPower scalar.
     * @param v FloatFrequency; scalar
     * @return FloatPower; scalar as a multiplication of FloatFrequency and FloatEnergy
     */
    public final FloatPower times(final FloatEnergy v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatAngle, which results in a FloatAngularVelocity scalar.
     * @param v FloatFrequency; scalar
     * @return FloatAngularVelocity; scalar as a multiplication of FloatFrequency and FloatAngle
     */
    public final FloatAngularVelocity times(final FloatAngle v)
    {
        return new FloatAngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatAngularVelocity, which results in a FloatAngularAcceleration
     * scalar.
     * @param v FloatFrequency; scalar
     * @return FloatAngularAcceleration; scalar as a multiplication of FloatFrequency and FloatAngularVelocity
     */
    public final FloatAngularAcceleration times(final FloatAngularVelocity v)
    {
        return new FloatAngularAcceleration(this.si * v.si, AngularAccelerationUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDuration reciprocal()
    {
        return FloatDuration.instantiateSI(1.0f / this.si);
    }

}
