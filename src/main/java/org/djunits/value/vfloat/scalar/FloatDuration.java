package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalarRelWithAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatDuration FloatScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatDuration extends FloatScalarRelWithAbs<TimeUnit, FloatTime, DurationUnit, FloatDuration>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatDuration ZERO = new FloatDuration(0.0f, DurationUnit.SI);

    /** Constant with value one. */
    public static final FloatDuration ONE = new FloatDuration(1.0f, DurationUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatDuration NaN = new FloatDuration(Float.NaN, DurationUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatDuration POSITIVE_INFINITY = new FloatDuration(Float.POSITIVE_INFINITY, DurationUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatDuration NEGATIVE_INFINITY = new FloatDuration(Float.NEGATIVE_INFINITY, DurationUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatDuration POS_MAXVALUE = new FloatDuration(Float.MAX_VALUE, DurationUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatDuration NEG_MAXVALUE = new FloatDuration(-Float.MAX_VALUE, DurationUnit.SI);

    /**
     * Construct FloatDuration scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatDuration(final float value, final DurationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatDuration scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatDuration(final FloatDuration value)
    {
        super(value);
    }

    /**
     * Construct FloatDuration scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatDuration(final double value, final DurationUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatDuration instantiateRel(final float value, final DurationUnit unit)
    {
        return new FloatDuration(value, unit);
    }

    /**
     * Construct FloatDuration scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatDuration ofSI(final float value)
    {
        return new FloatDuration(value, DurationUnit.SI);
    }

    @Override
    public final FloatTime instantiateAbs(final float value, final TimeUnit unit)
    {
        return new FloatTime(value, unit);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatDuration at the given ratio between 0 and 1
     */
    public static FloatDuration interpolate(final FloatDuration zero, final FloatDuration one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatDuration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatDuration max(final FloatDuration r1, final FloatDuration r2)
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
    public static FloatDuration max(final FloatDuration r1, final FloatDuration r2, final FloatDuration... rn)
    {
        FloatDuration maxr = r1.gt(r2) ? r1 : r2;
        for (FloatDuration r : rn)
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
    public static FloatDuration min(final FloatDuration r1, final FloatDuration r2)
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
    public static FloatDuration min(final FloatDuration r1, final FloatDuration r2, final FloatDuration... rn)
    {
        FloatDuration minr = r1.lt(r2) ? r1 : r2;
        for (FloatDuration r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatDuration representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatDuration
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatDuration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatDuration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatDuration: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            DurationUnit unit = DurationUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Duration", unitString);
            return new FloatDuration(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatDuration from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatDuration based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatDuration of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatDuration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatDuration: empty unitString");
        DurationUnit unit = DurationUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatDuration with unit %s", unitString);
        return new FloatDuration(value, unit);
    }

    /**
     * Calculate the division of FloatDuration and FloatDuration, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatDuration and FloatDuration
     */
    public final FloatDimensionless divide(final FloatDuration v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatFrequency, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatFrequency
     */
    public final FloatDimensionless times(final FloatFrequency v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatElectricalCurrent, which results in a FloatElectricalCharge
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatElectricalCurrent
     */
    public final FloatElectricalCharge times(final FloatElectricalCurrent v)
    {
        return new FloatElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatFlowMass, which results in a FloatMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatFlowMass
     */
    public final FloatMass times(final FloatFlowMass v)
    {
        return new FloatMass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatFlowVolume, which results in a FloatVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatFlowVolume
     */
    public final FloatVolume times(final FloatFlowVolume v)
    {
        return new FloatVolume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatAcceleration, which results in a FloatSpeed scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatAcceleration
     */
    public final FloatSpeed times(final FloatAcceleration v)
    {
        return new FloatSpeed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatPower, which results in a FloatEnergy scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatPower
     */
    public final FloatEnergy times(final FloatPower v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatSpeed, which results in a FloatLength scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatSpeed
     */
    public final FloatLength times(final FloatSpeed v)
    {
        return new FloatLength(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatElectricalPotential, which results in a FloatMagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatElectricalPotential
     */
    public final FloatMagneticFlux times(final FloatElectricalPotential v)
    {
        return new FloatMagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatElectricalResistance, which results in a FloatElectricalInductance
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatElectricalResistance
     */
    public final FloatElectricalInductance times(final FloatElectricalResistance v)
    {
        return new FloatElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatElectricalConductance, which results in a
     * FloatElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatElectricalConductance
     */
    public final FloatElectricalCapacitance times(final FloatElectricalConductance v)
    {
        return new FloatElectricalCapacitance(this.si * v.si, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatAngularVelocity, which results in a FloatAngle scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatAngularVelocity
     */
    public final FloatAngle times(final FloatAngularVelocity v)
    {
        return new FloatAngle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatAngularAcceleration, which results in a FloatAngularVelocity
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatDuration and FloatAngularAcceleration
     */
    public final FloatAngularVelocity times(final FloatAngularAcceleration v)
    {
        return new FloatAngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    @Override
    public FloatFrequency reciprocal()
    {
        return FloatFrequency.ofSI(1.0f / this.si);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatDuration.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatDuration
     */
    public static FloatDuration multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(DurationUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatDuration",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatDuration(scalar1.si * scalar2.si, DurationUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatDuration.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatDuration
     */
    public static FloatDuration divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(DurationUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatDuration",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatDuration(scalar1.si / scalar2.si, DurationUnit.SI);
    }

}
