package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatAcceleration FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatAcceleration extends FloatScalarRel<AccelerationUnit, FloatAcceleration>
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
     * Construct FloatAcceleration scalar with a unit.
     * @param value the float value, expressed in the given unit
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
     * Construct FloatAcceleration scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatAcceleration(final double value, final AccelerationUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatAcceleration instantiateRel(final float value, final AccelerationUnit unit)
    {
        return new FloatAcceleration(value, unit);
    }

    /**
     * Construct FloatAcceleration scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAcceleration ofSI(final float value)
    {
        return new FloatAcceleration(value, AccelerationUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatAcceleration at the given ratio between 0 and 1
     */
    public static FloatAcceleration interpolate(final FloatAcceleration zero, final FloatAcceleration one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
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
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatAcceleration
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAcceleration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAcceleration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatAcceleration: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AccelerationUnit unit = AccelerationUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Acceleration", unitString);
            return new FloatAcceleration(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatAcceleration from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatAcceleration based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAcceleration of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAcceleration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAcceleration: empty unitString");
        AccelerationUnit unit = AccelerationUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatAcceleration with unit %s", unitString);
        return new FloatAcceleration(value, unit);
    }

    /**
     * Calculate the division of FloatAcceleration and FloatAcceleration, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatAcceleration and FloatAcceleration
     */
    public final FloatDimensionless divide(final FloatAcceleration v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAcceleration and FloatMass, which results in a FloatForce scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatAcceleration and FloatMass
     */
    public final FloatForce times(final FloatMass v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAcceleration and FloatDuration, which results in a FloatSpeed scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatAcceleration and FloatDuration
     */
    public final FloatSpeed times(final FloatDuration v)
    {
        return new FloatSpeed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatAcceleration and FloatFrequency, which results in a FloatSpeed scalar.
     * @param v scalar
     * @return scalar as a division of FloatAcceleration and FloatFrequency
     */
    public final FloatSpeed divide(final FloatFrequency v)
    {
        return new FloatSpeed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatAcceleration and FloatSpeed, which results in a FloatFrequency scalar.
     * @param v scalar
     * @return scalar as a division of FloatAcceleration and FloatSpeed
     */
    public final FloatFrequency divide(final FloatSpeed v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAcceleration and FloatMomentum, which results in a FloatPower scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatAcceleration and FloatMomentum
     */
    public final FloatPower times(final FloatMomentum v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatAcceleration.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatAcceleration
     */
    public static FloatAcceleration multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(AccelerationUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatAcceleration",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatAcceleration(scalar1.si * scalar2.si, AccelerationUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatAcceleration.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatAcceleration
     */
    public static FloatAcceleration divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(AccelerationUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatAcceleration",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatAcceleration(scalar1.si / scalar2.si, AccelerationUnit.SI);
    }

}
