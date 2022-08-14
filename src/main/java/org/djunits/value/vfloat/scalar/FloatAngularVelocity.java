package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatAngularVelocity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAngularVelocity extends AbstractFloatScalarRel<AngularVelocityUnit, FloatAngularVelocity>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAngularVelocity ZERO = new FloatAngularVelocity(0.0f, AngularVelocityUnit.SI);

    /** Constant with value one. */
    public static final FloatAngularVelocity ONE = new FloatAngularVelocity(1.0f, AngularVelocityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatAngularVelocity NaN = new FloatAngularVelocity(Float.NaN, AngularVelocityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatAngularVelocity POSITIVE_INFINITY =
            new FloatAngularVelocity(Float.POSITIVE_INFINITY, AngularVelocityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatAngularVelocity NEGATIVE_INFINITY =
            new FloatAngularVelocity(Float.NEGATIVE_INFINITY, AngularVelocityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatAngularVelocity POS_MAXVALUE = new FloatAngularVelocity(Float.MAX_VALUE, AngularVelocityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatAngularVelocity NEG_MAXVALUE = new FloatAngularVelocity(-Float.MAX_VALUE, AngularVelocityUnit.SI);

    /**
     * Construct FloatAngularVelocity scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatAngularVelocity(final float value, final AngularVelocityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAngularVelocity scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatAngularVelocity(final FloatAngularVelocity value)
    {
        super(value);
    }

    /**
     * Construct FloatAngularVelocity scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatAngularVelocity(final double value, final AngularVelocityUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatAngularVelocity instantiateRel(final float value, final AngularVelocityUnit unit)
    {
        return new FloatAngularVelocity(value, unit);
    }

    /**
     * Construct FloatAngularVelocity scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAngularVelocity instantiateSI(final float value)
    {
        return new FloatAngularVelocity(value, AngularVelocityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatAngularVelocity interpolate(final FloatAngularVelocity zero, final FloatAngularVelocity one,
            final float ratio)
    {
        return new FloatAngularVelocity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatAngularVelocity max(final FloatAngularVelocity r1, final FloatAngularVelocity r2)
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
    public static FloatAngularVelocity max(final FloatAngularVelocity r1, final FloatAngularVelocity r2,
            final FloatAngularVelocity... rn)
    {
        FloatAngularVelocity maxr = r1.gt(r2) ? r1 : r2;
        for (FloatAngularVelocity r : rn)
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
    public static FloatAngularVelocity min(final FloatAngularVelocity r1, final FloatAngularVelocity r2)
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
    public static FloatAngularVelocity min(final FloatAngularVelocity r1, final FloatAngularVelocity r2,
            final FloatAngularVelocity... rn)
    {
        FloatAngularVelocity minr = r1.lt(r2) ? r1 : r2;
        for (FloatAngularVelocity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatAngularVelocity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatAngularVelocity
     * @return FloatAngularVelocity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAngularVelocity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAngularVelocity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAngularVelocity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AngularVelocityUnit unit = AngularVelocityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatAngularVelocity(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatAngularVelocity from " + text);
    }

    /**
     * Returns a FloatAngularVelocity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatAngularVelocity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAngularVelocity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAngularVelocity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAngularVelocity: empty unitString");
        AngularVelocityUnit unit = AngularVelocityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatAngularVelocity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatAngularVelocity with unit " + unitString);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatAngularVelocity, which results in a FloatDimensionless scalar.
     * @param v FloatAngularVelocity; scalar
     * @return FloatDimensionless; scalar as a division of FloatAngularVelocity and FloatAngularVelocity
     */
    public final FloatDimensionless divide(final FloatAngularVelocity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatAngle, which results in a FloatFrequency scalar.
     * @param v FloatAngularVelocity; scalar
     * @return FloatFrequency; scalar as a division of FloatAngularVelocity and FloatAngle
     */
    public final FloatFrequency divide(final FloatAngle v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatFrequency, which results in a FloatAngle scalar.
     * @param v FloatAngularVelocity; scalar
     * @return FloatAngle; scalar as a division of FloatAngularVelocity and FloatFrequency
     */
    public final FloatAngle divide(final FloatFrequency v)
    {
        return new FloatAngle(this.si / v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAngularVelocity and FloatDuration, which results in a FloatAngle scalar.
     * @param v FloatAngularVelocity; scalar
     * @return FloatAngle; scalar as a multiplication of FloatAngularVelocity and FloatDuration
     */
    public final FloatAngle times(final FloatDuration v)
    {
        return new FloatAngle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatDuration, which results in a FloatAngularAcceleration scalar.
     * @param v FloatAngularVelocity; scalar
     * @return FloatAngularAcceleration; scalar as a division of FloatAngularVelocity and FloatDuration
     */
    public final FloatAngularAcceleration divide(final FloatDuration v)
    {
        return new FloatAngularAcceleration(this.si / v.si, AngularAccelerationUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatAngularAcceleration, which results in a FloatDuration scalar.
     * @param v FloatAngularVelocity; scalar
     * @return FloatDuration; scalar as a division of FloatAngularVelocity and FloatAngularAcceleration
     */
    public final FloatDuration divide(final FloatAngularAcceleration v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAngularVelocity and FloatFrequency, which results in a FloatAngularAcceleration
     * scalar.
     * @param v FloatAngularVelocity; scalar
     * @return FloatAngularAcceleration; scalar as a multiplication of FloatAngularVelocity and FloatFrequency
     */
    public final FloatAngularAcceleration times(final FloatFrequency v)
    {
        return new FloatAngularAcceleration(this.si * v.si, AngularAccelerationUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
