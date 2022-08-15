package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.unit.RadioActivityUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.TorqueUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;

/**
 * Easy access methods for the FloatDimensionless FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDimensionless extends AbstractFloatScalarRel<DimensionlessUnit, FloatDimensionless>
        implements DimensionlessFunctions<DimensionlessUnit, FloatDimensionless>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatDimensionless ZERO = new FloatDimensionless(0.0f, DimensionlessUnit.SI);

    /** Constant with value one. */
    public static final FloatDimensionless ONE = new FloatDimensionless(1.0f, DimensionlessUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatDimensionless NaN = new FloatDimensionless(Float.NaN, DimensionlessUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatDimensionless POSITIVE_INFINITY =
            new FloatDimensionless(Float.POSITIVE_INFINITY, DimensionlessUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatDimensionless NEGATIVE_INFINITY =
            new FloatDimensionless(Float.NEGATIVE_INFINITY, DimensionlessUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatDimensionless POS_MAXVALUE = new FloatDimensionless(Float.MAX_VALUE, DimensionlessUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatDimensionless NEG_MAXVALUE = new FloatDimensionless(-Float.MAX_VALUE, DimensionlessUnit.SI);

    /**
     * Construct FloatDimensionless scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatDimensionless(final float value, final DimensionlessUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatDimensionless scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatDimensionless(final FloatDimensionless value)
    {
        super(value);
    }

    /**
     * Construct FloatDimensionless scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatDimensionless(final double value, final DimensionlessUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless instantiateRel(final float value, final DimensionlessUnit unit)
    {
        return new FloatDimensionless(value, unit);
    }

    /**
     * Construct FloatDimensionless scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatDimensionless instantiateSI(final float value)
    {
        return new FloatDimensionless(value, DimensionlessUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatDimensionless interpolate(final FloatDimensionless zero, final FloatDimensionless one, final float ratio)
    {
        return new FloatDimensionless(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatDimensionless max(final FloatDimensionless r1, final FloatDimensionless r2)
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
    public static FloatDimensionless max(final FloatDimensionless r1, final FloatDimensionless r2,
            final FloatDimensionless... rn)
    {
        FloatDimensionless maxr = r1.gt(r2) ? r1 : r2;
        for (FloatDimensionless r : rn)
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
    public static FloatDimensionless min(final FloatDimensionless r1, final FloatDimensionless r2)
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
    public static FloatDimensionless min(final FloatDimensionless r1, final FloatDimensionless r2,
            final FloatDimensionless... rn)
    {
        FloatDimensionless minr = r1.lt(r2) ? r1 : r2;
        for (FloatDimensionless r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatDimensionless representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatDimensionless
     * @return FloatDimensionless; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatDimensionless valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatDimensionless: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatDimensionless: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            DimensionlessUnit unit = DimensionlessUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatDimensionless(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatDimensionless from " + text);
    }

    /**
     * Returns a FloatDimensionless based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatDimensionless; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatDimensionless of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatDimensionless: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatDimensionless: empty unitString");
        DimensionlessUnit unit = DimensionlessUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatDimensionless(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatDimensionless with unit " + unitString);
    }

    /** {@inheritDoc} */
    @Override
    public String toStringSIPrefixed(final int smallestPower, final int biggestPower)
    {
        return toString();
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless acos()
    {
        return instantiateRel((float) Math.acos(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless asin()
    {
        return instantiateRel((float) Math.asin(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless atan()
    {
        return instantiateRel((float) Math.atan(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless cbrt()
    {
        return instantiateRel((float) Math.cbrt(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless cos()
    {
        return instantiateRel((float) Math.cos(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless cosh()
    {
        return instantiateRel((float) Math.cosh(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless exp()
    {
        return instantiateRel((float) Math.exp(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless expm1()
    {
        return instantiateRel((float) Math.expm1(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless log()
    {
        return instantiateRel((float) Math.log(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless log10()
    {
        return instantiateRel((float) Math.log10(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless log1p()
    {
        return instantiateRel((float) Math.log1p(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless pow(final double x)
    {
        return instantiateRel((float) Math.pow(getInUnit(), x), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless signum()
    {
        return instantiateRel(Math.signum(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless sin()
    {
        return instantiateRel((float) Math.sin(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless sinh()
    {
        return instantiateRel((float) Math.sinh(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless sqrt()
    {
        return instantiateRel((float) Math.sqrt(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless tan()
    {
        return instantiateRel((float) Math.tan(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless tanh()
    {
        return instantiateRel((float) Math.tanh(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionless inv()
    {
        return instantiateRel(1.0f / getInUnit(), getDisplayUnit());
    }

    /**
     * Calculate the division of FloatDimensionless and FloatDimensionless, which results in a FloatDimensionless scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatDimensionless; scalar as a division of FloatDimensionless and FloatDimensionless
     */
    public final FloatDimensionless divide(final FloatDimensionless v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatAbsorbedDose, which results in a FloatAbsorbedDose scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatAbsorbedDose; scalar as a multiplication of FloatDimensionless and FloatAbsorbedDose
     */
    public final FloatAbsorbedDose times(final FloatAbsorbedDose v)
    {
        return new FloatAbsorbedDose(this.si * v.si, AbsorbedDoseUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatAcceleration, which results in a FloatAcceleration scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatAcceleration; scalar as a multiplication of FloatDimensionless and FloatAcceleration
     */
    public final FloatAcceleration times(final FloatAcceleration v)
    {
        return new FloatAcceleration(this.si * v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatAmountOfSubstance, which results in a FloatAmountOfSubstance
     * scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatAmountOfSubstance; scalar as a multiplication of FloatDimensionless and FloatAmountOfSubstance
     */
    public final FloatAmountOfSubstance times(final FloatAmountOfSubstance v)
    {
        return new FloatAmountOfSubstance(this.si * v.si, AmountOfSubstanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatAngle, which results in a FloatAngle scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatAngle; scalar as a multiplication of FloatDimensionless and FloatAngle
     */
    public final FloatAngle times(final FloatAngle v)
    {
        return new FloatAngle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatSolidAngle, which results in a FloatSolidAngle scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatSolidAngle; scalar as a multiplication of FloatDimensionless and FloatSolidAngle
     */
    public final FloatSolidAngle times(final FloatSolidAngle v)
    {
        return new FloatSolidAngle(this.si * v.si, SolidAngleUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatArea, which results in a FloatArea scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatArea; scalar as a multiplication of FloatDimensionless and FloatArea
     */
    public final FloatArea times(final FloatArea v)
    {
        return new FloatArea(this.si * v.si, AreaUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatCatalyticActivity, which results in a FloatCatalyticActivity
     * scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatCatalyticActivity; scalar as a multiplication of FloatDimensionless and FloatCatalyticActivity
     */
    public final FloatCatalyticActivity times(final FloatCatalyticActivity v)
    {
        return new FloatCatalyticActivity(this.si * v.si, CatalyticActivityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatDensity, which results in a FloatDensity scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatDensity; scalar as a multiplication of FloatDimensionless and FloatDensity
     */
    public final FloatDensity times(final FloatDensity v)
    {
        return new FloatDensity(this.si * v.si, DensityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatDimensionless, which results in a FloatDimensionless scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatDimensionless; scalar as a multiplication of FloatDimensionless and FloatDimensionless
     */
    public final FloatDimensionless times(final FloatDimensionless v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatElectricalCapacitance, which results in a
     * FloatElectricalCapacitance scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalCapacitance; scalar as a multiplication of FloatDimensionless and FloatElectricalCapacitance
     */
    public final FloatElectricalCapacitance times(final FloatElectricalCapacitance v)
    {
        return new FloatElectricalCapacitance(this.si * v.si, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatElectricalCharge, which results in a FloatElectricalCharge
     * scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalCharge; scalar as a multiplication of FloatDimensionless and FloatElectricalCharge
     */
    public final FloatElectricalCharge times(final FloatElectricalCharge v)
    {
        return new FloatElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatElectricalConductance, which results in a
     * FloatElectricalConductance scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalConductance; scalar as a multiplication of FloatDimensionless and FloatElectricalConductance
     */
    public final FloatElectricalConductance times(final FloatElectricalConductance v)
    {
        return new FloatElectricalConductance(this.si * v.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatElectricalCurrent, which results in a FloatElectricalCurrent
     * scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalCurrent; scalar as a multiplication of FloatDimensionless and FloatElectricalCurrent
     */
    public final FloatElectricalCurrent times(final FloatElectricalCurrent v)
    {
        return new FloatElectricalCurrent(this.si * v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatElectricalInductance, which results in a
     * FloatElectricalInductance scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalInductance; scalar as a multiplication of FloatDimensionless and FloatElectricalInductance
     */
    public final FloatElectricalInductance times(final FloatElectricalInductance v)
    {
        return new FloatElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatElectricalPotential, which results in a
     * FloatElectricalPotential scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalPotential; scalar as a multiplication of FloatDimensionless and FloatElectricalPotential
     */
    public final FloatElectricalPotential times(final FloatElectricalPotential v)
    {
        return new FloatElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatElectricalResistance, which results in a
     * FloatElectricalResistance scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalResistance; scalar as a multiplication of FloatDimensionless and FloatElectricalResistance
     */
    public final FloatElectricalResistance times(final FloatElectricalResistance v)
    {
        return new FloatElectricalResistance(this.si * v.si, ElectricalResistanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatEnergy, which results in a FloatEnergy scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatEnergy; scalar as a multiplication of FloatDimensionless and FloatEnergy
     */
    public final FloatEnergy times(final FloatEnergy v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatEquivalentDose, which results in a FloatEquivalentDose
     * scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatEquivalentDose; scalar as a multiplication of FloatDimensionless and FloatEquivalentDose
     */
    public final FloatEquivalentDose times(final FloatEquivalentDose v)
    {
        return new FloatEquivalentDose(this.si * v.si, EquivalentDoseUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatFlowMass, which results in a FloatFlowMass scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatFlowMass; scalar as a multiplication of FloatDimensionless and FloatFlowMass
     */
    public final FloatFlowMass times(final FloatFlowMass v)
    {
        return new FloatFlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatFlowVolume, which results in a FloatFlowVolume scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatFlowVolume; scalar as a multiplication of FloatDimensionless and FloatFlowVolume
     */
    public final FloatFlowVolume times(final FloatFlowVolume v)
    {
        return new FloatFlowVolume(this.si * v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatForce, which results in a FloatForce scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatForce; scalar as a multiplication of FloatDimensionless and FloatForce
     */
    public final FloatForce times(final FloatForce v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatFrequency, which results in a FloatFrequency scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatFrequency; scalar as a multiplication of FloatDimensionless and FloatFrequency
     */
    public final FloatFrequency times(final FloatFrequency v)
    {
        return new FloatFrequency(this.si * v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatIlluminance, which results in a FloatIlluminance scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatIlluminance; scalar as a multiplication of FloatDimensionless and FloatIlluminance
     */
    public final FloatIlluminance times(final FloatIlluminance v)
    {
        return new FloatIlluminance(this.si * v.si, IlluminanceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatLength, which results in a FloatLength scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatLength; scalar as a multiplication of FloatDimensionless and FloatLength
     */
    public final FloatLength times(final FloatLength v)
    {
        return new FloatLength(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatLinearDensity, which results in a FloatLinearDensity scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatLinearDensity; scalar as a multiplication of FloatDimensionless and FloatLinearDensity
     */
    public final FloatLinearDensity times(final FloatLinearDensity v)
    {
        return new FloatLinearDensity(this.si * v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatLuminousFlux, which results in a FloatLuminousFlux scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatLuminousFlux; scalar as a multiplication of FloatDimensionless and FloatLuminousFlux
     */
    public final FloatLuminousFlux times(final FloatLuminousFlux v)
    {
        return new FloatLuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatLuminousIntensity, which results in a FloatLuminousIntensity
     * scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatLuminousIntensity; scalar as a multiplication of FloatDimensionless and FloatLuminousIntensity
     */
    public final FloatLuminousIntensity times(final FloatLuminousIntensity v)
    {
        return new FloatLuminousIntensity(this.si * v.si, LuminousIntensityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatMagneticFluxDensity, which results in a
     * FloatMagneticFluxDensity scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatMagneticFluxDensity; scalar as a multiplication of FloatDimensionless and FloatMagneticFluxDensity
     */
    public final FloatMagneticFluxDensity times(final FloatMagneticFluxDensity v)
    {
        return new FloatMagneticFluxDensity(this.si * v.si, MagneticFluxDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatMagneticFlux, which results in a FloatMagneticFlux scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatMagneticFlux; scalar as a multiplication of FloatDimensionless and FloatMagneticFlux
     */
    public final FloatMagneticFlux times(final FloatMagneticFlux v)
    {
        return new FloatMagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatMass, which results in a FloatMass scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatMass; scalar as a multiplication of FloatDimensionless and FloatMass
     */
    public final FloatMass times(final FloatMass v)
    {
        return new FloatMass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatPower, which results in a FloatPower scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatPower; scalar as a multiplication of FloatDimensionless and FloatPower
     */
    public final FloatPower times(final FloatPower v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatPressure, which results in a FloatPressure scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatPressure; scalar as a multiplication of FloatDimensionless and FloatPressure
     */
    public final FloatPressure times(final FloatPressure v)
    {
        return new FloatPressure(this.si * v.si, PressureUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatRadioActivity, which results in a FloatRadioActivity scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatRadioActivity; scalar as a multiplication of FloatDimensionless and FloatRadioActivity
     */
    public final FloatRadioActivity times(final FloatRadioActivity v)
    {
        return new FloatRadioActivity(this.si * v.si, RadioActivityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatSpeed, which results in a FloatSpeed scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatSpeed; scalar as a multiplication of FloatDimensionless and FloatSpeed
     */
    public final FloatSpeed times(final FloatSpeed v)
    {
        return new FloatSpeed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatTemperature, which results in a FloatTemperature scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatTemperature; scalar as a multiplication of FloatDimensionless and FloatTemperature
     */
    public final FloatTemperature times(final FloatTemperature v)
    {
        return new FloatTemperature(this.si * v.si, TemperatureUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatDuration, which results in a FloatDuration scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatDuration; scalar as a multiplication of FloatDimensionless and FloatDuration
     */
    public final FloatDuration times(final FloatDuration v)
    {
        return new FloatDuration(this.si * v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatTorque, which results in a FloatTorque scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatTorque; scalar as a multiplication of FloatDimensionless and FloatTorque
     */
    public final FloatTorque times(final FloatTorque v)
    {
        return new FloatTorque(this.si * v.si, TorqueUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDimensionless and FloatVolume, which results in a FloatVolume scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatVolume; scalar as a multiplication of FloatDimensionless and FloatVolume
     */
    public final FloatVolume times(final FloatVolume v)
    {
        return new FloatVolume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FloatDimensionless and FloatLength, which results in a FloatLinearDensity scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatLinearDensity; scalar as a division of FloatDimensionless and FloatLength
     */
    public final FloatLinearDensity divide(final FloatLength v)
    {
        return new FloatLinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the division of FloatDimensionless and FloatLinearDensity, which results in a FloatLength scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatLength; scalar as a division of FloatDimensionless and FloatLinearDensity
     */
    public final FloatLength divide(final FloatLinearDensity v)
    {
        return new FloatLength(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the division of FloatDimensionless and FloatDuration, which results in a FloatFrequency scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatFrequency; scalar as a division of FloatDimensionless and FloatDuration
     */
    public final FloatFrequency divide(final FloatDuration v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of FloatDimensionless and FloatFrequency, which results in a FloatDuration scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatDuration; scalar as a division of FloatDimensionless and FloatFrequency
     */
    public final FloatDuration divide(final FloatFrequency v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of FloatDimensionless and FloatElectricalConductance, which results in a FloatElectricalResistance
     * scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalResistance; scalar as a division of FloatDimensionless and FloatElectricalConductance
     */
    public final FloatElectricalResistance divide(final FloatElectricalConductance v)
    {
        return new FloatElectricalResistance(this.si / v.si, ElectricalResistanceUnit.SI);
    }

    /**
     * Calculate the division of FloatDimensionless and FloatElectricalResistance, which results in a FloatElectricalConductance
     * scalar.
     * @param v FloatDimensionless; scalar
     * @return FloatElectricalConductance; scalar as a division of FloatDimensionless and FloatElectricalResistance
     */
    public final FloatElectricalConductance divide(final FloatElectricalResistance v)
    {
        return new FloatElectricalConductance(this.si / v.si, ElectricalConductanceUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDimensionless reciprocal()
    {
        return FloatDimensionless.instantiateSI(1.0f / this.si);
    }

}
