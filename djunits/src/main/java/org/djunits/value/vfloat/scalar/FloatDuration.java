package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
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
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRelWithAbs;

/**
 * Easy access methods for the FloatDuration FloatScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDuration extends AbstractFloatScalarRelWithAbs<TimeUnit, FloatTime, DurationUnit, FloatDuration>
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
     * Construct FloatDuration scalar.
     * @param value float; the float value
     * @param unit DurationUnit; unit for the float value
     */
    public FloatDuration(final float value, final DurationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatDuration scalar.
     * @param value FloatDuration; Scalar from which to construct this instance
     */
    public FloatDuration(final FloatDuration value)
    {
        super(value);
    }

    /**
     * Construct FloatDuration scalar using a double value.
     * @param value double; the double value
     * @param unit DurationUnit; unit for the resulting float value
     */
    public FloatDuration(final double value, final DurationUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDuration instantiateRel(final float value, final DurationUnit unit)
    {
        return new FloatDuration(value, unit);
    }

    /**
     * Construct FloatDuration scalar.
     * @param value float; the float value in SI units
     * @return FloatDuration; the new scalar with the SI value
     */
    public static final FloatDuration instantiateSI(final float value)
    {
        return new FloatDuration(value, DurationUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatTime instantiateAbs(final float value, final TimeUnit unit)
    {
        return new FloatTime(value, unit);
    }

    /**
     * Interpolate between two values.
     * @param zero FloatDuration; the low value
     * @param one FloatDuration; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return FloatDuration; a Scalar at the ratio between
     */
    public static FloatDuration interpolate(final FloatDuration zero, final FloatDuration one, final float ratio)
    {
        return new FloatDuration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 FloatDuration; the first scalar
     * @param r2 FloatDuration; the second scalar
     * @return FloatDuration; the maximum value of two relative scalars
     */
    public static FloatDuration max(final FloatDuration r1, final FloatDuration r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 FloatDuration; the first scalar
     * @param r2 FloatDuration; the second scalar
     * @param rn FloatDuration...; the other scalars
     * @return FloatDuration; the maximum value of more than two relative scalars
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
     * @param r1 FloatDuration; the first scalar
     * @param r2 FloatDuration; the second scalar
     * @return FloatDuration; the minimum value of two relative scalars
     */
    public static FloatDuration min(final FloatDuration r1, final FloatDuration r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 FloatDuration; the first scalar
     * @param r2 FloatDuration; the second scalar
     * @param rn FloatDuration...; the other scalars
     * @return FloatDuration; the minimum value of more than two relative scalars
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
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatDuration
     * @return FloatDuration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatDuration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatDuration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatDuration: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            DurationUnit unit = DurationUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatDuration(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatDuration from " + text);
    }

    /**
     * Returns a FloatDuration based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatDuration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatDuration of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatDuration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatDuration: empty unitString");
        DurationUnit unit = DurationUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatDuration(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatDuration with unit " + unitString);
    }

    /**
     * Calculate the division of FloatDuration and FloatDuration, which results in a FloatDimensionless scalar.
     * @param v FloatDuration; scalar
     * @return FloatDimensionless; scalar as a division of FloatDuration and FloatDuration
     */
    public final FloatDimensionless divide(final FloatDuration v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatFrequency, which results in a FloatDimensionless scalar.
     * @param v FloatDuration; scalar
     * @return FloatDimensionless; scalar as a multiplication of FloatDuration and FloatFrequency
     */
    public final FloatDimensionless times(final FloatFrequency v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatElectricalCurrent, which results in a FloatElectricalCharge
     * scalar.
     * @param v FloatDuration; scalar
     * @return FloatElectricalCharge; scalar as a multiplication of FloatDuration and FloatElectricalCurrent
     */
    public final FloatElectricalCharge times(final FloatElectricalCurrent v)
    {
        return new FloatElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatFlowMass, which results in a FloatMass scalar.
     * @param v FloatDuration; scalar
     * @return FloatMass; scalar as a multiplication of FloatDuration and FloatFlowMass
     */
    public final FloatMass times(final FloatFlowMass v)
    {
        return new FloatMass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatFlowVolume, which results in a FloatVolume scalar.
     * @param v FloatDuration; scalar
     * @return FloatVolume; scalar as a multiplication of FloatDuration and FloatFlowVolume
     */
    public final FloatVolume times(final FloatFlowVolume v)
    {
        return new FloatVolume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatAcceleration, which results in a FloatSpeed scalar.
     * @param v FloatDuration; scalar
     * @return FloatSpeed; scalar as a multiplication of FloatDuration and FloatAcceleration
     */
    public final FloatSpeed times(final FloatAcceleration v)
    {
        return new FloatSpeed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatPower, which results in a FloatEnergy scalar.
     * @param v FloatDuration; scalar
     * @return FloatEnergy; scalar as a multiplication of FloatDuration and FloatPower
     */
    public final FloatEnergy times(final FloatPower v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatSpeed, which results in a FloatLength scalar.
     * @param v FloatDuration; scalar
     * @return FloatLength; scalar as a multiplication of FloatDuration and FloatSpeed
     */
    public final FloatLength times(final FloatSpeed v)
    {
        return new FloatLength(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatElectricalPotential, which results in a FloatMagneticFlux scalar.
     * @param v FloatDuration; scalar
     * @return FloatMagneticFlux; scalar as a multiplication of FloatDuration and FloatElectricalPotential
     */
    public final FloatMagneticFlux times(final FloatElectricalPotential v)
    {
        return new FloatMagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatElectricalResistance, which results in a FloatElectricalInductance
     * scalar.
     * @param v FloatDuration; scalar
     * @return FloatElectricalInductance; scalar as a multiplication of FloatDuration and FloatElectricalResistance
     */
    public final FloatElectricalInductance times(final FloatElectricalResistance v)
    {
        return new FloatElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatElectricalConductance, which results in a
     * FloatElectricalCapacitance scalar.
     * @param v FloatDuration; scalar
     * @return FloatElectricalCapacitance; scalar as a multiplication of FloatDuration and FloatElectricalConductance
     */
    public final FloatElectricalCapacitance times(final FloatElectricalConductance v)
    {
        return new FloatElectricalCapacitance(this.si * v.si, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatAngularVelocity, which results in a FloatAngle scalar.
     * @param v FloatDuration; scalar
     * @return FloatAngle; scalar as a multiplication of FloatDuration and FloatAngularVelocity
     */
    public final FloatAngle times(final FloatAngularVelocity v)
    {
        return new FloatAngle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDuration and FloatAngularAcceleration, which results in a FloatAngularVelocity
     * scalar.
     * @param v FloatDuration; scalar
     * @return FloatAngularVelocity; scalar as a multiplication of FloatDuration and FloatAngularAcceleration
     */
    public final FloatAngularVelocity times(final FloatAngularAcceleration v)
    {
        return new FloatAngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFrequency reciprocal()
    {
        return FloatFrequency.instantiateSI(1.0f / this.si);
    }

}
