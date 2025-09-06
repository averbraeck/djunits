package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatSpeed FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatSpeed extends FloatScalarRel<SpeedUnit, FloatSpeed>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatSpeed ZERO = new FloatSpeed(0.0f, SpeedUnit.SI);

    /** Constant with value one. */
    public static final FloatSpeed ONE = new FloatSpeed(1.0f, SpeedUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatSpeed NaN = new FloatSpeed(Float.NaN, SpeedUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatSpeed POSITIVE_INFINITY = new FloatSpeed(Float.POSITIVE_INFINITY, SpeedUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatSpeed NEGATIVE_INFINITY = new FloatSpeed(Float.NEGATIVE_INFINITY, SpeedUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatSpeed POS_MAXVALUE = new FloatSpeed(Float.MAX_VALUE, SpeedUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatSpeed NEG_MAXVALUE = new FloatSpeed(-Float.MAX_VALUE, SpeedUnit.SI);

    /**
     * Construct FloatSpeed scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatSpeed(final float value, final SpeedUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatSpeed scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatSpeed(final FloatSpeed value)
    {
        super(value);
    }

    /**
     * Construct FloatSpeed scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatSpeed(final double value, final SpeedUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatSpeed instantiateRel(final float value, final SpeedUnit unit)
    {
        return new FloatSpeed(value, unit);
    }

    /**
     * Construct FloatSpeed scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatSpeed ofSI(final float value)
    {
        return new FloatSpeed(value, SpeedUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatSpeed at the given ratio between 0 and 1
     */
    public static FloatSpeed interpolate(final FloatSpeed zero, final FloatSpeed one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatSpeed(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatSpeed max(final FloatSpeed r1, final FloatSpeed r2)
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
    public static FloatSpeed max(final FloatSpeed r1, final FloatSpeed r2, final FloatSpeed... rn)
    {
        FloatSpeed maxr = r1.gt(r2) ? r1 : r2;
        for (FloatSpeed r : rn)
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
    public static FloatSpeed min(final FloatSpeed r1, final FloatSpeed r2)
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
    public static FloatSpeed min(final FloatSpeed r1, final FloatSpeed r2, final FloatSpeed... rn)
    {
        FloatSpeed minr = r1.lt(r2) ? r1 : r2;
        for (FloatSpeed r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatSpeed representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatSpeed
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatSpeed valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatSpeed: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatSpeed: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            SpeedUnit unit = SpeedUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Speed", unitString);
            return new FloatSpeed(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatSpeed from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatSpeed based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatSpeed of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatSpeed: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatSpeed: empty unitString");
        SpeedUnit unit = SpeedUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatSpeed with unit %s", unitString);
        return new FloatSpeed(value, unit);
    }

    /**
     * Calculate the division of FloatSpeed and FloatSpeed, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatSpeed and FloatSpeed
     */
    public final FloatDimensionless divide(final FloatSpeed v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSpeed and FloatArea, which results in a FloatFlowVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatSpeed and FloatArea
     */
    public final FloatFlowVolume times(final FloatArea v)
    {
        return new FloatFlowVolume(this.si * v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSpeed and FloatForce, which results in a FloatPower scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatSpeed and FloatForce
     */
    public final FloatPower times(final FloatForce v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSpeed and FloatFrequency, which results in a FloatAcceleration scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatSpeed and FloatFrequency
     */
    public final FloatAcceleration times(final FloatFrequency v)
    {
        return new FloatAcceleration(this.si * v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the division of FloatSpeed and FloatLength, which results in a FloatFrequency scalar.
     * @param v scalar
     * @return scalar as a division of FloatSpeed and FloatLength
     */
    public final FloatFrequency divide(final FloatLength v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of FloatSpeed and FloatFrequency, which results in a FloatLength scalar.
     * @param v scalar
     * @return scalar as a division of FloatSpeed and FloatFrequency
     */
    public final FloatLength divide(final FloatFrequency v)
    {
        return new FloatLength(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSpeed and FloatLinearDensity, which results in a FloatFrequency scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatSpeed and FloatLinearDensity
     */
    public final FloatFrequency times(final FloatLinearDensity v)
    {
        return new FloatFrequency(this.si * v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSpeed and FloatDuration, which results in a FloatLength scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatSpeed and FloatDuration
     */
    public final FloatLength times(final FloatDuration v)
    {
        return new FloatLength(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the division of FloatSpeed and FloatDuration, which results in a FloatAcceleration scalar.
     * @param v scalar
     * @return scalar as a division of FloatSpeed and FloatDuration
     */
    public final FloatAcceleration divide(final FloatDuration v)
    {
        return new FloatAcceleration(this.si / v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the division of FloatSpeed and FloatAcceleration, which results in a FloatDuration scalar.
     * @param v scalar
     * @return scalar as a division of FloatSpeed and FloatAcceleration
     */
    public final FloatDuration divide(final FloatAcceleration v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSpeed and FloatFlowMass, which results in a FloatForce scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatSpeed and FloatFlowMass
     */
    public final FloatForce times(final FloatFlowMass v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSpeed and FloatMass, which results in a FloatMomentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatSpeed and FloatMass
     */
    public final FloatMomentum times(final FloatMass v)
    {
        return new FloatMomentum(this.si * v.si, MomentumUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSpeed and FloatMomentum, which results in a FloatEnergy scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatSpeed and FloatMomentum
     */
    public final FloatEnergy times(final FloatMomentum v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatSpeed.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatSpeed
     */
    public static FloatSpeed multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(SpeedUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatSpeed",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatSpeed(scalar1.si * scalar2.si, SpeedUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatSpeed.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatSpeed
     */
    public static FloatSpeed divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(SpeedUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatSpeed",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatSpeed(scalar1.si / scalar2.si, SpeedUnit.SI);
    }

}
