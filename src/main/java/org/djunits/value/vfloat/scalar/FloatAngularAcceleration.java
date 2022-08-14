package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatAngularAcceleration FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAngularAcceleration extends AbstractFloatScalarRel<AngularAccelerationUnit, FloatAngularAcceleration>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAngularAcceleration ZERO = new FloatAngularAcceleration(0.0f, AngularAccelerationUnit.SI);

    /** Constant with value one. */
    public static final FloatAngularAcceleration ONE = new FloatAngularAcceleration(1.0f, AngularAccelerationUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatAngularAcceleration NaN = new FloatAngularAcceleration(Float.NaN, AngularAccelerationUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatAngularAcceleration POSITIVE_INFINITY =
            new FloatAngularAcceleration(Float.POSITIVE_INFINITY, AngularAccelerationUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatAngularAcceleration NEGATIVE_INFINITY =
            new FloatAngularAcceleration(Float.NEGATIVE_INFINITY, AngularAccelerationUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatAngularAcceleration POS_MAXVALUE =
            new FloatAngularAcceleration(Float.MAX_VALUE, AngularAccelerationUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatAngularAcceleration NEG_MAXVALUE =
            new FloatAngularAcceleration(-Float.MAX_VALUE, AngularAccelerationUnit.SI);

    /**
     * Construct FloatAngularAcceleration scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatAngularAcceleration(final float value, final AngularAccelerationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAngularAcceleration scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatAngularAcceleration(final FloatAngularAcceleration value)
    {
        super(value);
    }

    /**
     * Construct FloatAngularAcceleration scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatAngularAcceleration(final double value, final AngularAccelerationUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatAngularAcceleration instantiateRel(final float value, final AngularAccelerationUnit unit)
    {
        return new FloatAngularAcceleration(value, unit);
    }

    /**
     * Construct FloatAngularAcceleration scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAngularAcceleration instantiateSI(final float value)
    {
        return new FloatAngularAcceleration(value, AngularAccelerationUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatAngularAcceleration interpolate(final FloatAngularAcceleration zero, final FloatAngularAcceleration one,
            final float ratio)
    {
        return new FloatAngularAcceleration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatAngularAcceleration max(final FloatAngularAcceleration r1, final FloatAngularAcceleration r2)
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
    public static FloatAngularAcceleration max(final FloatAngularAcceleration r1, final FloatAngularAcceleration r2,
            final FloatAngularAcceleration... rn)
    {
        FloatAngularAcceleration maxr = r1.gt(r2) ? r1 : r2;
        for (FloatAngularAcceleration r : rn)
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
    public static FloatAngularAcceleration min(final FloatAngularAcceleration r1, final FloatAngularAcceleration r2)
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
    public static FloatAngularAcceleration min(final FloatAngularAcceleration r1, final FloatAngularAcceleration r2,
            final FloatAngularAcceleration... rn)
    {
        FloatAngularAcceleration minr = r1.lt(r2) ? r1 : r2;
        for (FloatAngularAcceleration r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatAngularAcceleration representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatAngularAcceleration
     * @return FloatAngularAcceleration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAngularAcceleration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAngularAcceleration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAngularAcceleration: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AngularAccelerationUnit unit = AngularAccelerationUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatAngularAcceleration(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatAngularAcceleration from " + text);
    }

    /**
     * Returns a FloatAngularAcceleration based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatAngularAcceleration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAngularAcceleration of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAngularAcceleration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAngularAcceleration: empty unitString");
        AngularAccelerationUnit unit = AngularAccelerationUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatAngularAcceleration(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatAngularAcceleration with unit " + unitString);
    }

    /**
     * Calculate the division of FloatAngularAcceleration and FloatAngularAcceleration, which results in a FloatDimensionless
     * scalar.
     * @param v FloatAngularAcceleration; scalar
     * @return FloatDimensionless; scalar as a division of FloatAngularAcceleration and FloatAngularAcceleration
     */
    public final FloatDimensionless divide(final FloatAngularAcceleration v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAngularAcceleration and FloatDuration, which results in a FloatAngularVelocity
     * scalar.
     * @param v FloatAngularAcceleration; scalar
     * @return FloatAngularVelocity; scalar as a multiplication of FloatAngularAcceleration and FloatDuration
     */
    public final FloatAngularVelocity times(final FloatDuration v)
    {
        return new FloatAngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularAcceleration and FloatFrequency, which results in a FloatAngularVelocity scalar.
     * @param v FloatAngularAcceleration; scalar
     * @return FloatAngularVelocity; scalar as a division of FloatAngularAcceleration and FloatFrequency
     */
    public final FloatAngularVelocity divide(final FloatFrequency v)
    {
        return new FloatAngularVelocity(this.si / v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularAcceleration and FloatAngularVelocity, which results in a FloatFrequency scalar.
     * @param v FloatAngularAcceleration; scalar
     * @return FloatFrequency; scalar as a division of FloatAngularAcceleration and FloatAngularVelocity
     */
    public final FloatFrequency divide(final FloatAngularVelocity v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
