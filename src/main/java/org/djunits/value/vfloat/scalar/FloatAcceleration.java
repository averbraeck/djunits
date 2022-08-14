package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatAcceleration FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAcceleration extends AbstractFloatScalarRel<AccelerationUnit, FloatAcceleration>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAcceleration ZERO = new FloatAcceleration(0.0f, AccelerationUnit.SI);

    /** Constant with value one. */
    public static final FloatAcceleration ONE = new FloatAcceleration(1.0f, AccelerationUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatAcceleration NaN = new FloatAcceleration(Float.NaN, AccelerationUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatAcceleration POSITIVE_INFINITY =
            new FloatAcceleration(Float.POSITIVE_INFINITY, AccelerationUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatAcceleration NEGATIVE_INFINITY =
            new FloatAcceleration(Float.NEGATIVE_INFINITY, AccelerationUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatAcceleration POS_MAXVALUE = new FloatAcceleration(Float.MAX_VALUE, AccelerationUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatAcceleration NEG_MAXVALUE = new FloatAcceleration(-Float.MAX_VALUE, AccelerationUnit.SI);

    /**
     * Construct FloatAcceleration scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatAcceleration(final float value, final AccelerationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAcceleration scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatAcceleration(final FloatAcceleration value)
    {
        super(value);
    }

    /**
     * Construct FloatAcceleration scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatAcceleration(final double value, final AccelerationUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatAcceleration instantiateRel(final float value, final AccelerationUnit unit)
    {
        return new FloatAcceleration(value, unit);
    }

    /**
     * Construct FloatAcceleration scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAcceleration instantiateSI(final float value)
    {
        return new FloatAcceleration(value, AccelerationUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatAcceleration interpolate(final FloatAcceleration zero, final FloatAcceleration one, final float ratio)
    {
        return new FloatAcceleration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatAcceleration max(final FloatAcceleration r1, final FloatAcceleration r2)
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
    public static FloatAcceleration max(final FloatAcceleration r1, final FloatAcceleration r2, final FloatAcceleration... rn)
    {
        FloatAcceleration maxr = r1.gt(r2) ? r1 : r2;
        for (FloatAcceleration r : rn)
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
    public static FloatAcceleration min(final FloatAcceleration r1, final FloatAcceleration r2)
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
    public static FloatAcceleration min(final FloatAcceleration r1, final FloatAcceleration r2, final FloatAcceleration... rn)
    {
        FloatAcceleration minr = r1.lt(r2) ? r1 : r2;
        for (FloatAcceleration r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatAcceleration representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatAcceleration
     * @return FloatAcceleration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAcceleration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAcceleration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatAcceleration: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AccelerationUnit unit = AccelerationUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatAcceleration(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatAcceleration from " + text);
    }

    /**
     * Returns a FloatAcceleration based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatAcceleration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAcceleration of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAcceleration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAcceleration: empty unitString");
        AccelerationUnit unit = AccelerationUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatAcceleration(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatAcceleration with unit " + unitString);
    }

    /**
     * Calculate the division of FloatAcceleration and FloatAcceleration, which results in a FloatDimensionless scalar.
     * @param v FloatAcceleration; scalar
     * @return FloatDimensionless; scalar as a division of FloatAcceleration and FloatAcceleration
     */
    public final FloatDimensionless divide(final FloatAcceleration v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAcceleration and FloatMass, which results in a FloatForce scalar.
     * @param v FloatAcceleration; scalar
     * @return FloatForce; scalar as a multiplication of FloatAcceleration and FloatMass
     */
    public final FloatForce times(final FloatMass v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAcceleration and FloatDuration, which results in a FloatSpeed scalar.
     * @param v FloatAcceleration; scalar
     * @return FloatSpeed; scalar as a multiplication of FloatAcceleration and FloatDuration
     */
    public final FloatSpeed times(final FloatDuration v)
    {
        return new FloatSpeed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatAcceleration and FloatFrequency, which results in a FloatSpeed scalar.
     * @param v FloatAcceleration; scalar
     * @return FloatSpeed; scalar as a division of FloatAcceleration and FloatFrequency
     */
    public final FloatSpeed divide(final FloatFrequency v)
    {
        return new FloatSpeed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatAcceleration and FloatSpeed, which results in a FloatFrequency scalar.
     * @param v FloatAcceleration; scalar
     * @return FloatFrequency; scalar as a division of FloatAcceleration and FloatSpeed
     */
    public final FloatFrequency divide(final FloatSpeed v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAcceleration and FloatMomentum, which results in a FloatPower scalar.
     * @param v FloatAcceleration; scalar
     * @return FloatPower; scalar as a multiplication of FloatAcceleration and FloatMomentum
     */
    public final FloatPower times(final FloatMomentum v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
