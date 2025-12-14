package org.djunits.old.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.old.unit.AngleUnit;
import org.djunits.old.unit.AngularAccelerationUnit;
import org.djunits.old.unit.AngularVelocityUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.DurationUnit;
import org.djunits.old.unit.FrequencyUnit;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatAngularVelocity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatAngularVelocity extends FloatScalarRel<AngularVelocityUnit, FloatAngularVelocity>
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
     * Construct FloatAngularVelocity scalar with a unit.
     * @param value the float value, expressed in the given unit
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
     * Construct FloatAngularVelocity scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatAngularVelocity(final double value, final AngularVelocityUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatAngularVelocity instantiateRel(final float value, final AngularVelocityUnit unit)
    {
        return new FloatAngularVelocity(value, unit);
    }

    /**
     * Construct FloatAngularVelocity scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAngularVelocity ofSI(final float value)
    {
        return new FloatAngularVelocity(value, AngularVelocityUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatAngularVelocity at the given ratio between 0 and 1
     */
    public static FloatAngularVelocity interpolate(final FloatAngularVelocity zero, final FloatAngularVelocity one,
            final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
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
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatAngularVelocity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAngularVelocity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAngularVelocity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAngularVelocity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AngularVelocityUnit unit = AngularVelocityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity AngularVelocity",
                    unitString);
            return new FloatAngularVelocity(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatAngularVelocity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatAngularVelocity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAngularVelocity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAngularVelocity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAngularVelocity: empty unitString");
        AngularVelocityUnit unit = AngularVelocityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatAngularVelocity with unit %s", unitString);
        return new FloatAngularVelocity(value, unit);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatAngularVelocity, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngularVelocity and FloatAngularVelocity
     */
    public final FloatDimensionless divide(final FloatAngularVelocity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatAngle, which results in a FloatFrequency scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngularVelocity and FloatAngle
     */
    public final FloatFrequency divide(final FloatAngle v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatFrequency, which results in a FloatAngle scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngularVelocity and FloatFrequency
     */
    public final FloatAngle divide(final FloatFrequency v)
    {
        return new FloatAngle(this.si / v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAngularVelocity and FloatDuration, which results in a FloatAngle scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatAngularVelocity and FloatDuration
     */
    public final FloatAngle times(final FloatDuration v)
    {
        return new FloatAngle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatDuration, which results in a FloatAngularAcceleration scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngularVelocity and FloatDuration
     */
    public final FloatAngularAcceleration divide(final FloatDuration v)
    {
        return new FloatAngularAcceleration(this.si / v.si, AngularAccelerationUnit.SI);
    }

    /**
     * Calculate the division of FloatAngularVelocity and FloatAngularAcceleration, which results in a FloatDuration scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngularVelocity and FloatAngularAcceleration
     */
    public final FloatDuration divide(final FloatAngularAcceleration v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAngularVelocity and FloatFrequency, which results in a FloatAngularAcceleration
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatAngularVelocity and FloatFrequency
     */
    public final FloatAngularAcceleration times(final FloatFrequency v)
    {
        return new FloatAngularAcceleration(this.si * v.si, AngularAccelerationUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatAngularVelocity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatAngularVelocity
     */
    public static FloatAngularVelocity multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(AngularVelocityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatAngularVelocity",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatAngularVelocity(scalar1.si * scalar2.si, AngularVelocityUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatAngularVelocity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatAngularVelocity
     */
    public static FloatAngularVelocity divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(AngularVelocityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatAngularVelocity",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatAngularVelocity(scalar1.si / scalar2.si, AngularVelocityUnit.SI);
    }

}
