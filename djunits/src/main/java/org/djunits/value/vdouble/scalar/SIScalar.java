package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
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
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the generic Relative SI DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class SIScalar extends AbstractDoubleScalarRel<SIUnit, SIScalar>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct SI scalar.
     * @param value double; the double value
     * @param unit SIUnit; unit for the double value
     */
    public SIScalar(final double value, final SIUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct SI scalar.
     * @param value SIScalar; Scalar from which to construct this instance
     */
    public SIScalar(final SIScalar value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final SIScalar instantiateRel(final double value, final SIUnit unit)
    {
        return new SIScalar(value, unit);
    }

    /**
     * Construct SI scalar.
     * @param value double; the double value in SI units
     * @param unit SIUnit; the unit to use for the SI scalar
     * @return SIScalar; the new scalar with the SI value
     */
    public static final SIScalar instantiateSI(final double value, final SIUnit unit)
    {
        return new SIScalar(value, unit);
    }

    /**
     * Interpolate between two values.
     * @param zero SIScalar; the low value
     * @param one SIScalar; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return SIScalar; a Scalar at the ratio between
     */
    public static SIScalar interpolate(final SIScalar zero, final SIScalar one, final double ratio)
    {
        return new SIScalar(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 SIScalar; the first scalar
     * @param r2 SIScalar; the second scalar
     * @return SIScalar; the maximum value of two relative scalars
     */
    public static SIScalar max(final SIScalar r1, final SIScalar r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 SIScalar; the first scalar
     * @param r2 SIScalar; the second scalar
     * @param rn SIScalar...; the other scalars
     * @return SIScalar; the maximum value of more than two relative scalars
     */
    public static SIScalar max(final SIScalar r1, final SIScalar r2, final SIScalar... rn)
    {
        SIScalar maxr = r1.gt(r2) ? r1 : r2;
        for (SIScalar r : rn)
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
     * @param r1 SIScalar; the first scalar
     * @param r2 SIScalar; the second scalar
     * @return SIScalar; the minimum value of two relative scalars
     */
    public static SIScalar min(final SIScalar r1, final SIScalar r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 SIScalar; the first scalar
     * @param r2 SIScalar; the second scalar
     * @param rn SIScalar...; the other scalars
     * @return SIScalar; the minimum value of more than two relative scalars
     */
    public static SIScalar min(final SIScalar r1, final SIScalar r2, final SIScalar... rn)
    {
        SIScalar minr = r1.lt(r2) ? r1 : r2;
        for (SIScalar r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Returns an SIScalar representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a SIScalar
     * @return SIScalar; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static SIScalar valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing SIScalar: unitString is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing SIScalar: empty unitString");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            try
            {
                String unitString = text.substring(index).trim();
                String valueString = text.substring(0, index).trim();
                SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
                if (unit != null)
                {
                    double d = Double.parseDouble(valueString);
                    return new SIScalar(d, unit);
                }
            }
            catch (Exception exception)
            {
                throw new IllegalArgumentException("Error parsing SIScalar from " + text, exception);
            }
        }
        throw new IllegalArgumentException("Error parsing SIScalar from " + text);
    }

    /**
     * Returns an SIScalar based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return SIScalar; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIScalar of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing SIScalar: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SIScalar: empty unitString");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new SIScalar(value, unit);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIScalar with unit " + unitString);
    }

    /**********************************************************************************/
    /******************************** 'CAST AS' METHODS *******************************/
    /**********************************************************************************/

    /**
     * Return the current scalar transformed to a scalar in the given unit. Of course the SI dimensionality has to match,
     * otherwise the scalar cannot be transformed. The compiler will check the alignment between the return value and the unit.
     * @param displayUnit KU; the unit in which the scalar needs to be expressed
     * @return S; the scalar that has been transformed into the right scalar type and unit
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    public final <U extends Unit<U>, S extends AbstractDoubleScalarRel<U, S>> S as(final U displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(displayUnit.getQuantity().getSiDimensions())),
                UnitRuntimeException.class, "SIScalar with unit %s cannot be converted to a scalar with unit %s",
                getDisplayUnit(), displayUnit);
        S result = DoubleScalar.instantiate(this.si, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a absorbeddose.
     * @return AbsorbedDose; the current scalar as a absorbeddose
     */
    public final AbsorbedDose asAbsorbedDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AbsorbedDose", this.toString());
        return new AbsorbedDose(getSI(), AbsorbedDoseUnit.SI);
    }

    /**
     * Return the current scalar as a absorbeddose, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AbsorbedDose; the current scalar as a absorbeddose
     */
    public final AbsorbedDose asAbsorbedDose(final AbsorbedDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AbsorbedDose", this.toString());
        AbsorbedDose result = new AbsorbedDose(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a acceleration.
     * @return Acceleration; the current scalar as a acceleration
     */
    public final Acceleration asAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Acceleration", this.toString());
        return new Acceleration(getSI(), AccelerationUnit.SI);
    }

    /**
     * Return the current scalar as a acceleration, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Acceleration; the current scalar as a acceleration
     */
    public final Acceleration asAcceleration(final AccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Acceleration", this.toString());
        Acceleration result = new Acceleration(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a amountofsubstance.
     * @return AmountOfSubstance; the current scalar as a amountofsubstance
     */
    public final AmountOfSubstance asAmountOfSubstance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AmountOfSubstance", this.toString());
        return new AmountOfSubstance(getSI(), AmountOfSubstanceUnit.SI);
    }

    /**
     * Return the current scalar as a amountofsubstance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AmountOfSubstance; the current scalar as a amountofsubstance
     */
    public final AmountOfSubstance asAmountOfSubstance(final AmountOfSubstanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AmountOfSubstance", this.toString());
        AmountOfSubstance result = new AmountOfSubstance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a angularacceleration.
     * @return AngularAcceleration; the current scalar as a angularacceleration
     */
    public final AngularAcceleration asAngularAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularAcceleration", this.toString());
        return new AngularAcceleration(getSI(), AngularAccelerationUnit.SI);
    }

    /**
     * Return the current scalar as a angularacceleration, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AngularAcceleration; the current scalar as a angularacceleration
     */
    public final AngularAcceleration asAngularAcceleration(final AngularAccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularAcceleration", this.toString());
        AngularAcceleration result = new AngularAcceleration(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a angularvelocity.
     * @return AngularVelocity; the current scalar as a angularvelocity
     */
    public final AngularVelocity asAngularVelocity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularVelocity", this.toString());
        return new AngularVelocity(getSI(), AngularVelocityUnit.SI);
    }

    /**
     * Return the current scalar as a angularvelocity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AngularVelocity; the current scalar as a angularvelocity
     */
    public final AngularVelocity asAngularVelocity(final AngularVelocityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularVelocity", this.toString());
        AngularVelocity result = new AngularVelocity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a area.
     * @return Area; the current scalar as a area
     */
    public final Area asArea()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Area", this.toString());
        return new Area(getSI(), AreaUnit.SI);
    }

    /**
     * Return the current scalar as a area, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Area; the current scalar as a area
     */
    public final Area asArea(final AreaUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Area", this.toString());
        Area result = new Area(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a catalyticactivity.
     * @return CatalyticActivity; the current scalar as a catalyticactivity
     */
    public final CatalyticActivity asCatalyticActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to CatalyticActivity", this.toString());
        return new CatalyticActivity(getSI(), CatalyticActivityUnit.SI);
    }

    /**
     * Return the current scalar as a catalyticactivity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return CatalyticActivity; the current scalar as a catalyticactivity
     */
    public final CatalyticActivity asCatalyticActivity(final CatalyticActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to CatalyticActivity", this.toString());
        CatalyticActivity result = new CatalyticActivity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a density.
     * @return Density; the current scalar as a density
     */
    public final Density asDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Density", this.toString());
        return new Density(getSI(), DensityUnit.SI);
    }

    /**
     * Return the current scalar as a density, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Density; the current scalar as a density
     */
    public final Density asDensity(final DensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Density", this.toString());
        Density result = new Density(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a dimensionless.
     * @return Dimensionless; the current scalar as a dimensionless
     */
    public final Dimensionless asDimensionless()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Dimensionless", this.toString());
        return new Dimensionless(getSI(), DimensionlessUnit.SI);
    }

    /**
     * Return the current scalar as a dimensionless, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Dimensionless; the current scalar as a dimensionless
     */
    public final Dimensionless asDimensionless(final DimensionlessUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Dimensionless", this.toString());
        Dimensionless result = new Dimensionless(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalcapacitance.
     * @return ElectricalCapacitance; the current scalar as a electricalcapacitance
     */
    public final ElectricalCapacitance asElectricalCapacitance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCapacitance", this.toString());
        return new ElectricalCapacitance(getSI(), ElectricalCapacitanceUnit.SI);
    }

    /**
     * Return the current scalar as a electricalcapacitance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalCapacitance; the current scalar as a electricalcapacitance
     */
    public final ElectricalCapacitance asElectricalCapacitance(final ElectricalCapacitanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCapacitance", this.toString());
        ElectricalCapacitance result = new ElectricalCapacitance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalcharge.
     * @return ElectricalCharge; the current scalar as a electricalcharge
     */
    public final ElectricalCharge asElectricalCharge()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCharge", this.toString());
        return new ElectricalCharge(getSI(), ElectricalChargeUnit.SI);
    }

    /**
     * Return the current scalar as a electricalcharge, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalCharge; the current scalar as a electricalcharge
     */
    public final ElectricalCharge asElectricalCharge(final ElectricalChargeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCharge", this.toString());
        ElectricalCharge result = new ElectricalCharge(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalconductance.
     * @return ElectricalConductance; the current scalar as a electricalconductance
     */
    public final ElectricalConductance asElectricalConductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalConductance", this.toString());
        return new ElectricalConductance(getSI(), ElectricalConductanceUnit.SI);
    }

    /**
     * Return the current scalar as a electricalconductance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalConductance; the current scalar as a electricalconductance
     */
    public final ElectricalConductance asElectricalConductance(final ElectricalConductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalConductance", this.toString());
        ElectricalConductance result = new ElectricalConductance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalcurrent.
     * @return ElectricalCurrent; the current scalar as a electricalcurrent
     */
    public final ElectricalCurrent asElectricalCurrent()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCurrent", this.toString());
        return new ElectricalCurrent(getSI(), ElectricalCurrentUnit.SI);
    }

    /**
     * Return the current scalar as a electricalcurrent, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalCurrent; the current scalar as a electricalcurrent
     */
    public final ElectricalCurrent asElectricalCurrent(final ElectricalCurrentUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCurrent", this.toString());
        ElectricalCurrent result = new ElectricalCurrent(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalinductance.
     * @return ElectricalInductance; the current scalar as a electricalinductance
     */
    public final ElectricalInductance asElectricalInductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalInductance", this.toString());
        return new ElectricalInductance(getSI(), ElectricalInductanceUnit.SI);
    }

    /**
     * Return the current scalar as a electricalinductance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalInductance; the current scalar as a electricalinductance
     */
    public final ElectricalInductance asElectricalInductance(final ElectricalInductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalInductance", this.toString());
        ElectricalInductance result = new ElectricalInductance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalpotential.
     * @return ElectricalPotential; the current scalar as a electricalpotential
     */
    public final ElectricalPotential asElectricalPotential()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalPotential", this.toString());
        return new ElectricalPotential(getSI(), ElectricalPotentialUnit.SI);
    }

    /**
     * Return the current scalar as a electricalpotential, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalPotential; the current scalar as a electricalpotential
     */
    public final ElectricalPotential asElectricalPotential(final ElectricalPotentialUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalPotential", this.toString());
        ElectricalPotential result = new ElectricalPotential(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a electricalresistance.
     * @return ElectricalResistance; the current scalar as a electricalresistance
     */
    public final ElectricalResistance asElectricalResistance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalResistance", this.toString());
        return new ElectricalResistance(getSI(), ElectricalResistanceUnit.SI);
    }

    /**
     * Return the current scalar as a electricalresistance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalResistance; the current scalar as a electricalresistance
     */
    public final ElectricalResistance asElectricalResistance(final ElectricalResistanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalResistance", this.toString());
        ElectricalResistance result = new ElectricalResistance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a energy.
     * @return Energy; the current scalar as a energy
     */
    public final Energy asEnergy()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Energy", this.toString());
        return new Energy(getSI(), EnergyUnit.SI);
    }

    /**
     * Return the current scalar as a energy, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Energy; the current scalar as a energy
     */
    public final Energy asEnergy(final EnergyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Energy", this.toString());
        Energy result = new Energy(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a equivalentdose.
     * @return EquivalentDose; the current scalar as a equivalentdose
     */
    public final EquivalentDose asEquivalentDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EquivalentDose", this.toString());
        return new EquivalentDose(getSI(), EquivalentDoseUnit.SI);
    }

    /**
     * Return the current scalar as a equivalentdose, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return EquivalentDose; the current scalar as a equivalentdose
     */
    public final EquivalentDose asEquivalentDose(final EquivalentDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EquivalentDose", this.toString());
        EquivalentDose result = new EquivalentDose(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a flowmass.
     * @return FlowMass; the current scalar as a flowmass
     */
    public final FlowMass asFlowMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowMass", this.toString());
        return new FlowMass(getSI(), FlowMassUnit.SI);
    }

    /**
     * Return the current scalar as a flowmass, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FlowMass; the current scalar as a flowmass
     */
    public final FlowMass asFlowMass(final FlowMassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowMass", this.toString());
        FlowMass result = new FlowMass(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a flowvolume.
     * @return FlowVolume; the current scalar as a flowvolume
     */
    public final FlowVolume asFlowVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowVolume", this.toString());
        return new FlowVolume(getSI(), FlowVolumeUnit.SI);
    }

    /**
     * Return the current scalar as a flowvolume, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FlowVolume; the current scalar as a flowvolume
     */
    public final FlowVolume asFlowVolume(final FlowVolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowVolume", this.toString());
        FlowVolume result = new FlowVolume(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a force.
     * @return Force; the current scalar as a force
     */
    public final Force asForce()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Force", this.toString());
        return new Force(getSI(), ForceUnit.SI);
    }

    /**
     * Return the current scalar as a force, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Force; the current scalar as a force
     */
    public final Force asForce(final ForceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Force", this.toString());
        Force result = new Force(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a frequency.
     * @return Frequency; the current scalar as a frequency
     */
    public final Frequency asFrequency()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Frequency", this.toString());
        return new Frequency(getSI(), FrequencyUnit.SI);
    }

    /**
     * Return the current scalar as a frequency, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Frequency; the current scalar as a frequency
     */
    public final Frequency asFrequency(final FrequencyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Frequency", this.toString());
        Frequency result = new Frequency(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a illuminance.
     * @return Illuminance; the current scalar as a illuminance
     */
    public final Illuminance asIlluminance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Illuminance", this.toString());
        return new Illuminance(getSI(), IlluminanceUnit.SI);
    }

    /**
     * Return the current scalar as a illuminance, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Illuminance; the current scalar as a illuminance
     */
    public final Illuminance asIlluminance(final IlluminanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Illuminance", this.toString());
        Illuminance result = new Illuminance(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a lineardensity.
     * @return LinearDensity; the current scalar as a lineardensity
     */
    public final LinearDensity asLinearDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LinearDensity", this.toString());
        return new LinearDensity(getSI(), LinearDensityUnit.SI);
    }

    /**
     * Return the current scalar as a lineardensity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return LinearDensity; the current scalar as a lineardensity
     */
    public final LinearDensity asLinearDensity(final LinearDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LinearDensity", this.toString());
        LinearDensity result = new LinearDensity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a luminousflux.
     * @return LuminousFlux; the current scalar as a luminousflux
     */
    public final LuminousFlux asLuminousFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousFlux", this.toString());
        return new LuminousFlux(getSI(), LuminousFluxUnit.SI);
    }

    /**
     * Return the current scalar as a luminousflux, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return LuminousFlux; the current scalar as a luminousflux
     */
    public final LuminousFlux asLuminousFlux(final LuminousFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousFlux", this.toString());
        LuminousFlux result = new LuminousFlux(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a luminousintensity.
     * @return LuminousIntensity; the current scalar as a luminousintensity
     */
    public final LuminousIntensity asLuminousIntensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousIntensity", this.toString());
        return new LuminousIntensity(getSI(), LuminousIntensityUnit.SI);
    }

    /**
     * Return the current scalar as a luminousintensity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return LuminousIntensity; the current scalar as a luminousintensity
     */
    public final LuminousIntensity asLuminousIntensity(final LuminousIntensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousIntensity", this.toString());
        LuminousIntensity result = new LuminousIntensity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a magneticfluxdensity.
     * @return MagneticFluxDensity; the current scalar as a magneticfluxdensity
     */
    public final MagneticFluxDensity asMagneticFluxDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxDensity", this.toString());
        return new MagneticFluxDensity(getSI(), MagneticFluxDensityUnit.SI);
    }

    /**
     * Return the current scalar as a magneticfluxdensity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return MagneticFluxDensity; the current scalar as a magneticfluxdensity
     */
    public final MagneticFluxDensity asMagneticFluxDensity(final MagneticFluxDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxDensity", this.toString());
        MagneticFluxDensity result = new MagneticFluxDensity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a magneticflux.
     * @return MagneticFlux; the current scalar as a magneticflux
     */
    public final MagneticFlux asMagneticFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFlux", this.toString());
        return new MagneticFlux(getSI(), MagneticFluxUnit.SI);
    }

    /**
     * Return the current scalar as a magneticflux, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return MagneticFlux; the current scalar as a magneticflux
     */
    public final MagneticFlux asMagneticFlux(final MagneticFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFlux", this.toString());
        MagneticFlux result = new MagneticFlux(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a mass.
     * @return Mass; the current scalar as a mass
     */
    public final Mass asMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Mass", this.toString());
        return new Mass(getSI(), MassUnit.SI);
    }

    /**
     * Return the current scalar as a mass, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Mass; the current scalar as a mass
     */
    public final Mass asMass(final MassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Mass", this.toString());
        Mass result = new Mass(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a momentum.
     * @return Momentum; the current scalar as a momentum
     */
    public final Momentum asMomentum()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Momentum", this.toString());
        return new Momentum(getSI(), MomentumUnit.SI);
    }

    /**
     * Return the current scalar as a momentum, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Momentum; the current scalar as a momentum
     */
    public final Momentum asMomentum(final MomentumUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Momentum", this.toString());
        Momentum result = new Momentum(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a power.
     * @return Power; the current scalar as a power
     */
    public final Power asPower()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Power", this.toString());
        return new Power(getSI(), PowerUnit.SI);
    }

    /**
     * Return the current scalar as a power, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Power; the current scalar as a power
     */
    public final Power asPower(final PowerUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Power", this.toString());
        Power result = new Power(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a pressure.
     * @return Pressure; the current scalar as a pressure
     */
    public final Pressure asPressure()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Pressure", this.toString());
        return new Pressure(getSI(), PressureUnit.SI);
    }

    /**
     * Return the current scalar as a pressure, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Pressure; the current scalar as a pressure
     */
    public final Pressure asPressure(final PressureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Pressure", this.toString());
        Pressure result = new Pressure(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a radioactivity.
     * @return RadioActivity; the current scalar as a radioactivity
     */
    public final RadioActivity asRadioActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to RadioActivity", this.toString());
        return new RadioActivity(getSI(), RadioActivityUnit.SI);
    }

    /**
     * Return the current scalar as a radioactivity, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return RadioActivity; the current scalar as a radioactivity
     */
    public final RadioActivity asRadioActivity(final RadioActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to RadioActivity", this.toString());
        RadioActivity result = new RadioActivity(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a solidangle.
     * @return SolidAngle; the current scalar as a solidangle
     */
    public final SolidAngle asSolidAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SolidAngle", this.toString());
        return new SolidAngle(getSI(), SolidAngleUnit.SI);
    }

    /**
     * Return the current scalar as a solidangle, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return SolidAngle; the current scalar as a solidangle
     */
    public final SolidAngle asSolidAngle(final SolidAngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SolidAngle", this.toString());
        SolidAngle result = new SolidAngle(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a speed.
     * @return Speed; the current scalar as a speed
     */
    public final Speed asSpeed()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Speed", this.toString());
        return new Speed(getSI(), SpeedUnit.SI);
    }

    /**
     * Return the current scalar as a speed, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Speed; the current scalar as a speed
     */
    public final Speed asSpeed(final SpeedUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Speed", this.toString());
        Speed result = new Speed(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a torque.
     * @return Torque; the current scalar as a torque
     */
    public final Torque asTorque()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Torque", this.toString());
        return new Torque(getSI(), TorqueUnit.SI);
    }

    /**
     * Return the current scalar as a torque, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Torque; the current scalar as a torque
     */
    public final Torque asTorque(final TorqueUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Torque", this.toString());
        Torque result = new Torque(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a volume.
     * @return Volume; the current scalar as a volume
     */
    public final Volume asVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Volume", this.toString());
        return new Volume(getSI(), VolumeUnit.SI);
    }

    /**
     * Return the current scalar as a volume, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Volume; the current scalar as a volume
     */
    public final Volume asVolume(final VolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Volume", this.toString());
        Volume result = new Volume(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a angle.
     * @return Angle; the current scalar as a angle
     */
    public final Angle asAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Angle", this.toString());
        return new Angle(getSI(), AngleUnit.SI);
    }

    /**
     * Return the current scalar as a angle, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Angle; the current scalar as a angle
     */
    public final Angle asAngle(final AngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Angle", this.toString());
        Angle result = new Angle(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a length.
     * @return Length; the current scalar as a length
     */
    public final Length asLength()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Length", this.toString());
        return new Length(getSI(), LengthUnit.SI);
    }

    /**
     * Return the current scalar as a length, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Length; the current scalar as a length
     */
    public final Length asLength(final LengthUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Length", this.toString());
        Length result = new Length(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a temperature.
     * @return Temperature; the current scalar as a temperature
     */
    public final Temperature asTemperature()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Temperature", this.toString());
        return new Temperature(getSI(), TemperatureUnit.SI);
    }

    /**
     * Return the current scalar as a temperature, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Temperature; the current scalar as a temperature
     */
    public final Temperature asTemperature(final TemperatureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Temperature", this.toString());
        Temperature result = new Temperature(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current scalar as a duration.
     * @return Duration; the current scalar as a duration
     */
    public final Duration asDuration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Duration", this.toString());
        return new Duration(getSI(), DurationUnit.SI);
    }

    /**
     * Return the current scalar as a duration, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Duration; the current scalar as a duration
     */
    public final Duration asDuration(final DurationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Duration", this.toString());
        Duration result = new Duration(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
