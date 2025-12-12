package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatFrequency FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatFrequency extends FloatScalarRel<FrequencyUnit, FloatFrequency>
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
     * Construct FloatFrequency scalar with a unit.
     * @param value the float value, expressed in the given unit
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
     * Construct FloatFrequency scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatFrequency(final double value, final FrequencyUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatFrequency instantiateRel(final float value, final FrequencyUnit unit)
    {
        return new FloatFrequency(value, unit);
    }

    /**
     * Construct FloatFrequency scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatFrequency ofSI(final float value)
    {
        return new FloatFrequency(value, FrequencyUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatFrequency at the given ratio between 0 and 1
     */
    public static FloatFrequency interpolate(final FloatFrequency zero, final FloatFrequency one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
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
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatFrequency
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatFrequency valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatFrequency: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatFrequency: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            FrequencyUnit unit = FrequencyUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Frequency", unitString);
            return new FloatFrequency(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatFrequency from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatFrequency based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatFrequency of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatFrequency: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatFrequency: empty unitString");
        FrequencyUnit unit = FrequencyUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatFrequency with unit %s", unitString);
        return new FloatFrequency(value, unit);
    }

    /**
     * Calculate the division of FloatFrequency and FloatFrequency, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatFrequency and FloatFrequency
     */
    public final FloatDimensionless divide(final FloatFrequency v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatDuration, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFrequency and FloatDuration
     */
    public final FloatDimensionless times(final FloatDuration v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatLength, which results in a FloatSpeed scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFrequency and FloatLength
     */
    public final FloatSpeed times(final FloatLength v)
    {
        return new FloatSpeed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatSpeed, which results in a FloatAcceleration scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFrequency and FloatSpeed
     */
    public final FloatAcceleration times(final FloatSpeed v)
    {
        return new FloatAcceleration(this.si * v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatEnergy, which results in a FloatPower scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFrequency and FloatEnergy
     */
    public final FloatPower times(final FloatEnergy v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatAngle, which results in a FloatAngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFrequency and FloatAngle
     */
    public final FloatAngularVelocity times(final FloatAngle v)
    {
        return new FloatAngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFrequency and FloatAngularVelocity, which results in a FloatAngularAcceleration
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFrequency and FloatAngularVelocity
     */
    public final FloatAngularAcceleration times(final FloatAngularVelocity v)
    {
        return new FloatAngularAcceleration(this.si * v.si, AngularAccelerationUnit.SI);
    }

    @Override
    public FloatDuration reciprocal()
    {
        return FloatDuration.ofSI(1.0f / this.si);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatFrequency.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatFrequency
     */
    public static FloatFrequency multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(FrequencyUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatFrequency",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatFrequency(scalar1.si * scalar2.si, FrequencyUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatFrequency.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatFrequency
     */
    public static FloatFrequency divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(FrequencyUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatFrequency",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatFrequency(scalar1.si / scalar2.si, FrequencyUnit.SI);
    }

}
