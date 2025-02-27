package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
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
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.unit.RadioActivityUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.TorqueUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.VolumeUnit;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the generic Relative SI FloatScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatSIScalar extends FloatScalarRel<SIUnit, FloatSIScalar>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct SI scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatSIScalar(final float value, final SIUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct SI scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatSIScalar(final FloatSIScalar value)
    {
        super(value);
    }

    @Override
    public final FloatSIScalar instantiateRel(final float value, final SIUnit unit)
    {
        return new FloatSIScalar(value, unit);
    }

    /**
     * Construct SI scalar.
     * @param value the float value in SI units
     * @param unit the unit to use for the SI scalar
     * @return the new scalar with the SI value
     */
    public static final FloatSIScalar instantiateSI(final float value, final SIUnit unit)
    {
        return new FloatSIScalar(value, unit);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatSIScalar interpolate(final FloatSIScalar zero, final FloatSIScalar one, final float ratio)
    {
        return new FloatSIScalar(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatSIScalar max(final FloatSIScalar r1, final FloatSIScalar r2)
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
    public static FloatSIScalar max(final FloatSIScalar r1, final FloatSIScalar r2, final FloatSIScalar... rn)
    {
        FloatSIScalar maxr = r1.gt(r2) ? r1 : r2;
        for (FloatSIScalar r : rn)
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
    public static FloatSIScalar min(final FloatSIScalar r1, final FloatSIScalar r2)
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
    public static FloatSIScalar min(final FloatSIScalar r1, final FloatSIScalar r2, final FloatSIScalar... rn)
    {
        FloatSIScalar minr = r1.lt(r2) ? r1 : r2;
        for (FloatSIScalar r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Returns an FloatSIScalar representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the float value in the unit, followed by the official abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatSIScalar
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatSIScalar valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing SIScalar: unitString is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing SIScalar: empty unitString");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " for FloatSIScalar not found");
            return new FloatSIScalar(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatSIScalar from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns an FloatSIScalar based on a value and the textual representation of the unit.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatSIScalar of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatSIScalar: unitString is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new FloatSIScalar(value, unit);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing FloatSIScalar with unit " + unitString);
    }

    @Override
    public String toString(final SIUnit displayUnit, final boolean verbose, final boolean withUnit)
    {
        return super.toString(displayUnit, verbose, withUnit).replaceAll("!", "");
    }

    /**********************************************************************************/
    /******************************** 'CAST AS' METHODS *******************************/
    /**********************************************************************************/

    /**
     * Return the current scalar transformed to a scalar in the given unit. Of course the SI dimensionality has to match,
     * otherwise the scalar cannot be transformed. The compiler will check the alignment between the return value and the unit.
     * @param displayUnit the unit in which the scalar needs to be expressed
     * @return the scalar that has been transformed into the right scalar type and unit
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    public final <U extends Unit<U>, S extends FloatScalarRel<U, S>> S as(final U displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(displayUnit.getQuantity().getSiDimensions())),
                UnitRuntimeException.class, "FloatSIScalar with unit %s cannot be converted to a scalar with unit %s",
                getDisplayUnit(), displayUnit);
        S result = FloatScalar.instantiate(this.si, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a absorbeddose.
     * @return the current scalar as a absorbeddose
     */
    public final FloatAbsorbedDose asAbsorbedDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAbsorbedDose", this.toString());
        return new FloatAbsorbedDose(getSI(), AbsorbedDoseUnit.SI);
    }

    /**
     * Return the current scalar as a absorbeddose, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a absorbeddose
     */
    public final FloatAbsorbedDose asAbsorbedDose(final AbsorbedDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAbsorbedDose", this.toString());
        FloatAbsorbedDose result = new FloatAbsorbedDose(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a acceleration.
     * @return the current scalar as a acceleration
     */
    public final FloatAcceleration asAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAcceleration", this.toString());
        return new FloatAcceleration(getSI(), AccelerationUnit.SI);
    }

    /**
     * Return the current scalar as a acceleration, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a acceleration
     */
    public final FloatAcceleration asAcceleration(final AccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAcceleration", this.toString());
        FloatAcceleration result = new FloatAcceleration(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a amountofsubstance.
     * @return the current scalar as a amountofsubstance
     */
    public final FloatAmountOfSubstance asAmountOfSubstance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAmountOfSubstance", this.toString());
        return new FloatAmountOfSubstance(getSI(), AmountOfSubstanceUnit.SI);
    }

    /**
     * Return the current scalar as a amountofsubstance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a amountofsubstance
     */
    public final FloatAmountOfSubstance asAmountOfSubstance(final AmountOfSubstanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAmountOfSubstance", this.toString());
        FloatAmountOfSubstance result = new FloatAmountOfSubstance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a angularacceleration.
     * @return the current scalar as a angularacceleration
     */
    public final FloatAngularAcceleration asAngularAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngularAcceleration", this.toString());
        return new FloatAngularAcceleration(getSI(), AngularAccelerationUnit.SI);
    }

    /**
     * Return the current scalar as a angularacceleration, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a angularacceleration
     */
    public final FloatAngularAcceleration asAngularAcceleration(final AngularAccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngularAcceleration", this.toString());
        FloatAngularAcceleration result = new FloatAngularAcceleration(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a angularvelocity.
     * @return the current scalar as a angularvelocity
     */
    public final FloatAngularVelocity asAngularVelocity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngularVelocity", this.toString());
        return new FloatAngularVelocity(getSI(), AngularVelocityUnit.SI);
    }

    /**
     * Return the current scalar as a angularvelocity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a angularvelocity
     */
    public final FloatAngularVelocity asAngularVelocity(final AngularVelocityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngularVelocity", this.toString());
        FloatAngularVelocity result = new FloatAngularVelocity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a area.
     * @return the current scalar as a area
     */
    public final FloatArea asArea()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatArea", this.toString());
        return new FloatArea(getSI(), AreaUnit.SI);
    }

    /**
     * Return the current scalar as a area, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a area
     */
    public final FloatArea asArea(final AreaUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatArea", this.toString());
        FloatArea result = new FloatArea(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a catalyticactivity.
     * @return the current scalar as a catalyticactivity
     */
    public final FloatCatalyticActivity asCatalyticActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatCatalyticActivity", this.toString());
        return new FloatCatalyticActivity(getSI(), CatalyticActivityUnit.SI);
    }

    /**
     * Return the current scalar as a catalyticactivity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a catalyticactivity
     */
    public final FloatCatalyticActivity asCatalyticActivity(final CatalyticActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatCatalyticActivity", this.toString());
        FloatCatalyticActivity result = new FloatCatalyticActivity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a density.
     * @return the current scalar as a density
     */
    public final FloatDensity asDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDensity", this.toString());
        return new FloatDensity(getSI(), DensityUnit.SI);
    }

    /**
     * Return the current scalar as a density, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a density
     */
    public final FloatDensity asDensity(final DensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDensity", this.toString());
        FloatDensity result = new FloatDensity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a dimensionless.
     * @return the current scalar as a dimensionless
     */
    public final FloatDimensionless asDimensionless()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDimensionless", this.toString());
        return new FloatDimensionless(getSI(), DimensionlessUnit.SI);
    }

    /**
     * Return the current scalar as a dimensionless, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a dimensionless
     */
    public final FloatDimensionless asDimensionless(final DimensionlessUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDimensionless", this.toString());
        FloatDimensionless result = new FloatDimensionless(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalcapacitance.
     * @return the current scalar as a electricalcapacitance
     */
    public final FloatElectricalCapacitance asElectricalCapacitance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCapacitance", this.toString());
        return new FloatElectricalCapacitance(getSI(), ElectricalCapacitanceUnit.SI);
    }

    /**
     * Return the current scalar as a electricalcapacitance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a electricalcapacitance
     */
    public final FloatElectricalCapacitance asElectricalCapacitance(final ElectricalCapacitanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCapacitance", this.toString());
        FloatElectricalCapacitance result = new FloatElectricalCapacitance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalcharge.
     * @return the current scalar as a electricalcharge
     */
    public final FloatElectricalCharge asElectricalCharge()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCharge", this.toString());
        return new FloatElectricalCharge(getSI(), ElectricalChargeUnit.SI);
    }

    /**
     * Return the current scalar as a electricalcharge, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a electricalcharge
     */
    public final FloatElectricalCharge asElectricalCharge(final ElectricalChargeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCharge", this.toString());
        FloatElectricalCharge result = new FloatElectricalCharge(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalconductance.
     * @return the current scalar as a electricalconductance
     */
    public final FloatElectricalConductance asElectricalConductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalConductance", this.toString());
        return new FloatElectricalConductance(getSI(), ElectricalConductanceUnit.SI);
    }

    /**
     * Return the current scalar as a electricalconductance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a electricalconductance
     */
    public final FloatElectricalConductance asElectricalConductance(final ElectricalConductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalConductance", this.toString());
        FloatElectricalConductance result = new FloatElectricalConductance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalcurrent.
     * @return the current scalar as a electricalcurrent
     */
    public final FloatElectricalCurrent asElectricalCurrent()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCurrent", this.toString());
        return new FloatElectricalCurrent(getSI(), ElectricalCurrentUnit.SI);
    }

    /**
     * Return the current scalar as a electricalcurrent, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a electricalcurrent
     */
    public final FloatElectricalCurrent asElectricalCurrent(final ElectricalCurrentUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCurrent", this.toString());
        FloatElectricalCurrent result = new FloatElectricalCurrent(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalinductance.
     * @return the current scalar as a electricalinductance
     */
    public final FloatElectricalInductance asElectricalInductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalInductance", this.toString());
        return new FloatElectricalInductance(getSI(), ElectricalInductanceUnit.SI);
    }

    /**
     * Return the current scalar as a electricalinductance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a electricalinductance
     */
    public final FloatElectricalInductance asElectricalInductance(final ElectricalInductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalInductance", this.toString());
        FloatElectricalInductance result = new FloatElectricalInductance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalpotential.
     * @return the current scalar as a electricalpotential
     */
    public final FloatElectricalPotential asElectricalPotential()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalPotential", this.toString());
        return new FloatElectricalPotential(getSI(), ElectricalPotentialUnit.SI);
    }

    /**
     * Return the current scalar as a electricalpotential, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a electricalpotential
     */
    public final FloatElectricalPotential asElectricalPotential(final ElectricalPotentialUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalPotential", this.toString());
        FloatElectricalPotential result = new FloatElectricalPotential(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalresistance.
     * @return the current scalar as a electricalresistance
     */
    public final FloatElectricalResistance asElectricalResistance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalResistance", this.toString());
        return new FloatElectricalResistance(getSI(), ElectricalResistanceUnit.SI);
    }

    /**
     * Return the current scalar as a electricalresistance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a electricalresistance
     */
    public final FloatElectricalResistance asElectricalResistance(final ElectricalResistanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalResistance", this.toString());
        FloatElectricalResistance result = new FloatElectricalResistance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a energy.
     * @return the current scalar as a energy
     */
    public final FloatEnergy asEnergy()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatEnergy", this.toString());
        return new FloatEnergy(getSI(), EnergyUnit.SI);
    }

    /**
     * Return the current scalar as a energy, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a energy
     */
    public final FloatEnergy asEnergy(final EnergyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatEnergy", this.toString());
        FloatEnergy result = new FloatEnergy(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a equivalentdose.
     * @return the current scalar as a equivalentdose
     */
    public final FloatEquivalentDose asEquivalentDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatEquivalentDose", this.toString());
        return new FloatEquivalentDose(getSI(), EquivalentDoseUnit.SI);
    }

    /**
     * Return the current scalar as a equivalentdose, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a equivalentdose
     */
    public final FloatEquivalentDose asEquivalentDose(final EquivalentDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatEquivalentDose", this.toString());
        FloatEquivalentDose result = new FloatEquivalentDose(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a flowmass.
     * @return the current scalar as a flowmass
     */
    public final FloatFlowMass asFlowMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFlowMass", this.toString());
        return new FloatFlowMass(getSI(), FlowMassUnit.SI);
    }

    /**
     * Return the current scalar as a flowmass, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a flowmass
     */
    public final FloatFlowMass asFlowMass(final FlowMassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFlowMass", this.toString());
        FloatFlowMass result = new FloatFlowMass(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a flowvolume.
     * @return the current scalar as a flowvolume
     */
    public final FloatFlowVolume asFlowVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFlowVolume", this.toString());
        return new FloatFlowVolume(getSI(), FlowVolumeUnit.SI);
    }

    /**
     * Return the current scalar as a flowvolume, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a flowvolume
     */
    public final FloatFlowVolume asFlowVolume(final FlowVolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFlowVolume", this.toString());
        FloatFlowVolume result = new FloatFlowVolume(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a force.
     * @return the current scalar as a force
     */
    public final FloatForce asForce()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatForce", this.toString());
        return new FloatForce(getSI(), ForceUnit.SI);
    }

    /**
     * Return the current scalar as a force, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a force
     */
    public final FloatForce asForce(final ForceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatForce", this.toString());
        FloatForce result = new FloatForce(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a frequency.
     * @return the current scalar as a frequency
     */
    public final FloatFrequency asFrequency()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFrequency", this.toString());
        return new FloatFrequency(getSI(), FrequencyUnit.SI);
    }

    /**
     * Return the current scalar as a frequency, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a frequency
     */
    public final FloatFrequency asFrequency(final FrequencyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFrequency", this.toString());
        FloatFrequency result = new FloatFrequency(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a illuminance.
     * @return the current scalar as a illuminance
     */
    public final FloatIlluminance asIlluminance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatIlluminance", this.toString());
        return new FloatIlluminance(getSI(), IlluminanceUnit.SI);
    }

    /**
     * Return the current scalar as a illuminance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a illuminance
     */
    public final FloatIlluminance asIlluminance(final IlluminanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatIlluminance", this.toString());
        FloatIlluminance result = new FloatIlluminance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a lineardensity.
     * @return the current scalar as a lineardensity
     */
    public final FloatLinearDensity asLinearDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLinearDensity", this.toString());
        return new FloatLinearDensity(getSI(), LinearDensityUnit.SI);
    }

    /**
     * Return the current scalar as a lineardensity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a lineardensity
     */
    public final FloatLinearDensity asLinearDensity(final LinearDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLinearDensity", this.toString());
        FloatLinearDensity result = new FloatLinearDensity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a luminousflux.
     * @return the current scalar as a luminousflux
     */
    public final FloatLuminousFlux asLuminousFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLuminousFlux", this.toString());
        return new FloatLuminousFlux(getSI(), LuminousFluxUnit.SI);
    }

    /**
     * Return the current scalar as a luminousflux, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a luminousflux
     */
    public final FloatLuminousFlux asLuminousFlux(final LuminousFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLuminousFlux", this.toString());
        FloatLuminousFlux result = new FloatLuminousFlux(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a luminousintensity.
     * @return the current scalar as a luminousintensity
     */
    public final FloatLuminousIntensity asLuminousIntensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLuminousIntensity", this.toString());
        return new FloatLuminousIntensity(getSI(), LuminousIntensityUnit.SI);
    }

    /**
     * Return the current scalar as a luminousintensity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a luminousintensity
     */
    public final FloatLuminousIntensity asLuminousIntensity(final LuminousIntensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLuminousIntensity", this.toString());
        FloatLuminousIntensity result = new FloatLuminousIntensity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a magneticfluxdensity.
     * @return the current scalar as a magneticfluxdensity
     */
    public final FloatMagneticFluxDensity asMagneticFluxDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMagneticFluxDensity", this.toString());
        return new FloatMagneticFluxDensity(getSI(), MagneticFluxDensityUnit.SI);
    }

    /**
     * Return the current scalar as a magneticfluxdensity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a magneticfluxdensity
     */
    public final FloatMagneticFluxDensity asMagneticFluxDensity(final MagneticFluxDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMagneticFluxDensity", this.toString());
        FloatMagneticFluxDensity result = new FloatMagneticFluxDensity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a magneticflux.
     * @return the current scalar as a magneticflux
     */
    public final FloatMagneticFlux asMagneticFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMagneticFlux", this.toString());
        return new FloatMagneticFlux(getSI(), MagneticFluxUnit.SI);
    }

    /**
     * Return the current scalar as a magneticflux, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a magneticflux
     */
    public final FloatMagneticFlux asMagneticFlux(final MagneticFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMagneticFlux", this.toString());
        FloatMagneticFlux result = new FloatMagneticFlux(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a mass.
     * @return the current scalar as a mass
     */
    public final FloatMass asMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMass", this.toString());
        return new FloatMass(getSI(), MassUnit.SI);
    }

    /**
     * Return the current scalar as a mass, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a mass
     */
    public final FloatMass asMass(final MassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMass", this.toString());
        FloatMass result = new FloatMass(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a momentum.
     * @return the current scalar as a momentum
     */
    public final FloatMomentum asMomentum()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMomentum", this.toString());
        return new FloatMomentum(getSI(), MomentumUnit.SI);
    }

    /**
     * Return the current scalar as a momentum, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a momentum
     */
    public final FloatMomentum asMomentum(final MomentumUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMomentum", this.toString());
        FloatMomentum result = new FloatMomentum(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a power.
     * @return the current scalar as a power
     */
    public final FloatPower asPower()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatPower", this.toString());
        return new FloatPower(getSI(), PowerUnit.SI);
    }

    /**
     * Return the current scalar as a power, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a power
     */
    public final FloatPower asPower(final PowerUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatPower", this.toString());
        FloatPower result = new FloatPower(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a pressure.
     * @return the current scalar as a pressure
     */
    public final FloatPressure asPressure()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatPressure", this.toString());
        return new FloatPressure(getSI(), PressureUnit.SI);
    }

    /**
     * Return the current scalar as a pressure, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a pressure
     */
    public final FloatPressure asPressure(final PressureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatPressure", this.toString());
        FloatPressure result = new FloatPressure(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a radioactivity.
     * @return the current scalar as a radioactivity
     */
    public final FloatRadioActivity asRadioActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatRadioActivity", this.toString());
        return new FloatRadioActivity(getSI(), RadioActivityUnit.SI);
    }

    /**
     * Return the current scalar as a radioactivity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a radioactivity
     */
    public final FloatRadioActivity asRadioActivity(final RadioActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatRadioActivity", this.toString());
        FloatRadioActivity result = new FloatRadioActivity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a solidangle.
     * @return the current scalar as a solidangle
     */
    public final FloatSolidAngle asSolidAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatSolidAngle", this.toString());
        return new FloatSolidAngle(getSI(), SolidAngleUnit.SI);
    }

    /**
     * Return the current scalar as a solidangle, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a solidangle
     */
    public final FloatSolidAngle asSolidAngle(final SolidAngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatSolidAngle", this.toString());
        FloatSolidAngle result = new FloatSolidAngle(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a speed.
     * @return the current scalar as a speed
     */
    public final FloatSpeed asSpeed()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatSpeed", this.toString());
        return new FloatSpeed(getSI(), SpeedUnit.SI);
    }

    /**
     * Return the current scalar as a speed, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a speed
     */
    public final FloatSpeed asSpeed(final SpeedUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatSpeed", this.toString());
        FloatSpeed result = new FloatSpeed(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a torque.
     * @return the current scalar as a torque
     */
    public final FloatTorque asTorque()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatTorque", this.toString());
        return new FloatTorque(getSI(), TorqueUnit.SI);
    }

    /**
     * Return the current scalar as a torque, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a torque
     */
    public final FloatTorque asTorque(final TorqueUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatTorque", this.toString());
        FloatTorque result = new FloatTorque(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a volume.
     * @return the current scalar as a volume
     */
    public final FloatVolume asVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatVolume", this.toString());
        return new FloatVolume(getSI(), VolumeUnit.SI);
    }

    /**
     * Return the current scalar as a volume, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a volume
     */
    public final FloatVolume asVolume(final VolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatVolume", this.toString());
        FloatVolume result = new FloatVolume(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a angle.
     * @return the current scalar as a angle
     */
    public final FloatAngle asAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngle", this.toString());
        return new FloatAngle(getSI(), AngleUnit.SI);
    }

    /**
     * Return the current scalar as a angle, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a angle
     */
    public final FloatAngle asAngle(final AngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngle", this.toString());
        FloatAngle result = new FloatAngle(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a length.
     * @return the current scalar as a length
     */
    public final FloatLength asLength()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLength", this.toString());
        return new FloatLength(getSI(), LengthUnit.SI);
    }

    /**
     * Return the current scalar as a length, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a length
     */
    public final FloatLength asLength(final LengthUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLength", this.toString());
        FloatLength result = new FloatLength(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a temperature.
     * @return the current scalar as a temperature
     */
    public final FloatTemperature asTemperature()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatTemperature", this.toString());
        return new FloatTemperature(getSI(), TemperatureUnit.SI);
    }

    /**
     * Return the current scalar as a temperature, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a temperature
     */
    public final FloatTemperature asTemperature(final TemperatureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatTemperature", this.toString());
        FloatTemperature result = new FloatTemperature(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a duration.
     * @return the current scalar as a duration
     */
    public final FloatDuration asDuration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDuration", this.toString());
        return new FloatDuration(getSI(), DurationUnit.SI);
    }

    /**
     * Return the current scalar as a duration, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current scalar as a duration
     */
    public final FloatDuration asDuration(final DurationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDuration", this.toString());
        FloatDuration result = new FloatDuration(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
