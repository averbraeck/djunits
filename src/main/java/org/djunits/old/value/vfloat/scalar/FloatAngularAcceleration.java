package org.djunits.old.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.old.unit.AngularAccelerationUnit;
import org.djunits.old.unit.AngularVelocityUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.FrequencyUnit;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatAngularAcceleration FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatAngularAcceleration extends FloatScalarRel<AngularAccelerationUnit, FloatAngularAcceleration>
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
     * Construct FloatAngularAcceleration scalar with a unit.
     * @param value the float value, expressed in the given unit
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
     * Construct FloatAngularAcceleration scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatAngularAcceleration(final double value, final AngularAccelerationUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatAngularAcceleration instantiateRel(final float value, final AngularAccelerationUnit unit)
    {
        return new FloatAngularAcceleration(value, unit);
    }

    /**
     * Construct FloatAngularAcceleration scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAngularAcceleration ofSI(final float value)
    {
        return new FloatAngularAcceleration(value, AngularAccelerationUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatAngularAcceleration at the given ratio between 0 and 1
     */
    public static FloatAngularAcceleration interpolate(final FloatAngularAcceleration zero, final FloatAngularAcceleration one,
            final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
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
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatAngularAcceleration
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAngularAcceleration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAngularAcceleration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAngularAcceleration: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AngularAccelerationUnit unit = AngularAccelerationUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity AngularAcceleration",
                    unitString);
            return new FloatAngularAcceleration(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatAngularAcceleration from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatAngularAcceleration based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAngularAcceleration of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAngularAcceleration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAngularAcceleration: empty unitString");
        AngularAccelerationUnit unit = AngularAccelerationUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatAngularAcceleration with unit %s",
                unitString);
        return new FloatAngularAcceleration(value, unit);
    }

    /**
     * Calculate the division of FloatAngularAcceleration and FloatAngularAcceleration, which results in a FloatDimensionless
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngularAcceleration and FloatAngularAcceleration
     */
    public final FloatDimensionless divide(final FloatAngularAcceleration v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAngularAcceleration and FloatDuration, which results in a FloatAngularVelocity
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatAngularAcceleration and FloatDuration
     */
    public final FloatAngularVelocity times(final FloatDuration v)
    {
        return new FloatAngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularAcceleration and FloatFrequency, which results in a FloatAngularVelocity scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngularAcceleration and FloatFrequency
     */
    public final FloatAngularVelocity divide(final FloatFrequency v)
    {
        return new FloatAngularVelocity(this.si / v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularAcceleration and FloatAngularVelocity, which results in a FloatFrequency scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngularAcceleration and FloatAngularVelocity
     */
    public final FloatFrequency divide(final FloatAngularVelocity v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatAngularAcceleration.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatAngularAcceleration
     */
    public static FloatAngularAcceleration multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(AngularAccelerationUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Multiplying %s by %s does not result in instance of type FloatAngularAcceleration", scalar1.toDisplayString(),
                scalar2.toDisplayString());
        return new FloatAngularAcceleration(scalar1.si * scalar2.si, AngularAccelerationUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatAngularAcceleration.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatAngularAcceleration
     */
    public static FloatAngularAcceleration divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(AngularAccelerationUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Dividing %s by %s does not result in an instance of type FloatAngularAcceleration", scalar1.toDisplayString(),
                scalar2.toDisplayString());
        return new FloatAngularAcceleration(scalar1.si / scalar2.si, AngularAccelerationUnit.SI);
    }

}
