package org.djunits.value.vdouble.matrix;

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
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.vector.SIVector;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Easy access methods for the generic Relative SI DoubleMatrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class SIMatrix extends AbstractDoubleMatrixRel<SIUnit, SIScalar, SIVector, SIMatrix>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct a new Relative Double SIMatrix.
     * @param values double[][]; the values of the entries in the new Relative Double SIMatrix
     * @param unit SIUnit; the unit of the new Relative Double SIMatrix
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @return SIMatrix; the SIMatrix of the given unit
     * @throws ValueRuntimeException when values is null
     */
    public static SIMatrix instantiate(final double[][] values, final SIUnit unit, final StorageType storageType)
            throws ValueRuntimeException
    {
        return new SIMatrix(DoubleMatrixData.instantiate(values, unit.getScale(), storageType), unit);
    }

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit SIUnit; the unit
     */
    public SIMatrix(final DoubleMatrixData data, final SIUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<SIScalar> getScalarClass()
    {
        return SIScalar.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<SIVector> getVectorClass()
    {
        return SIVector.class;
    }

    /**
     * Returns an SIMatrix based on an array of values and the textual representation of the unit.
     * @param values double[][]; the values to use
     * @param unitString String; the textual representation of the unit
     * @param storageType StorageType; the storage type to use
     * @return SIMatrix; the matrix representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIMatrix of(final double[][] values, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(values, "Error parsing SIMatrix: value is null");
        Throw.whenNull(unitString, "Error parsing SIMatrix: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SIMatrix: empty unitString");
        Throw.whenNull(storageType, "Error parsing SIMatrix: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return SIMatrix.instantiate(values, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIMatrix with unit " + unitString);
    }

    /** {@inheritDoc} */
    @Override
    public SIMatrix instantiateMatrix(final DoubleMatrixData dmd, final SIUnit unit)
    {
        return new SIMatrix(dmd, unit);
    }

    /** {@inheritDoc} */
    @Override
    public SIVector instantiateVector(final DoubleVectorData dvd, final SIUnit unit)
    {
        return new SIVector(dvd, unit);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar instantiateScalarSI(final double valueSI, final SIUnit unit)
    {
        return new SIScalar(valueSI, unit);
    }

    /**********************************************************************************/
    /******************************** 'CAST AS' METHODS *******************************/
    /**********************************************************************************/

    /**
     * Return the current matrix transformed to a matrix in the given unit. Of course the SI dimensionality has to match,
     * otherwise the matrix cannot be transformed. The compiler will check the alignment between the return value and the unit.
     * @param displayUnit KU; the unit in which the matrix needs to be expressed
     * @return M; the matrix that has been transformed into the right matrix type and unit
     * @param <U> the unit type
     * @param <S> the scalar type
     * @param <V> the vector type
     * @param <M> the matrix type
     */
    public final <U extends Unit<U>, S extends AbstractDoubleScalarRel<U, S>, V extends AbstractDoubleVectorRel<U, S, V>,
            M extends AbstractDoubleMatrixRel<U, S, V, M>> M as(final U displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(displayUnit.getQuantity().getSiDimensions())),
                UnitRuntimeException.class, "SIMatrix with unit %s cannot be converted to a matrix with unit %s",
                getDisplayUnit(), displayUnit);
        M result = DoubleMatrix.instantiate(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a absorbeddose matrix.
     * @return AbsorbedDoseMatrix; the current matrix as a absorbeddose matrix
     */
    public final AbsorbedDoseMatrix asAbsorbedDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AbsorbedDoseMatrix", this.toString());
        return new AbsorbedDoseMatrix(this.data, AbsorbedDoseUnit.SI);
    }

    /**
     * Return the current matrix as a absorbeddose matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AbsorbedDoseMatrix; the current matrix as a absorbeddose matrix
     */
    public final AbsorbedDoseMatrix asAbsorbedDose(final AbsorbedDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AbsorbedDoseMatrix", this.toString());
        AbsorbedDoseMatrix result = new AbsorbedDoseMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a acceleration matrix.
     * @return AccelerationMatrix; the current matrix as a acceleration matrix
     */
    public final AccelerationMatrix asAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AccelerationMatrix", this.toString());
        return new AccelerationMatrix(this.data, AccelerationUnit.SI);
    }

    /**
     * Return the current matrix as a acceleration matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AccelerationMatrix; the current matrix as a acceleration matrix
     */
    public final AccelerationMatrix asAcceleration(final AccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AccelerationMatrix", this.toString());
        AccelerationMatrix result = new AccelerationMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a amountofsubstance matrix.
     * @return AmountOfSubstanceMatrix; the current matrix as a amountofsubstance matrix
     */
    public final AmountOfSubstanceMatrix asAmountOfSubstance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AmountOfSubstanceMatrix", this.toString());
        return new AmountOfSubstanceMatrix(this.data, AmountOfSubstanceUnit.SI);
    }

    /**
     * Return the current matrix as a amountofsubstance matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AmountOfSubstanceMatrix; the current matrix as a amountofsubstance matrix
     */
    public final AmountOfSubstanceMatrix asAmountOfSubstance(final AmountOfSubstanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AmountOfSubstanceMatrix", this.toString());
        AmountOfSubstanceMatrix result = new AmountOfSubstanceMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a angularacceleration matrix.
     * @return AngularAccelerationMatrix; the current matrix as a angularacceleration matrix
     */
    public final AngularAccelerationMatrix asAngularAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularAccelerationMatrix", this.toString());
        return new AngularAccelerationMatrix(this.data, AngularAccelerationUnit.SI);
    }

    /**
     * Return the current matrix as a angularacceleration matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AngularAccelerationMatrix; the current matrix as a angularacceleration matrix
     */
    public final AngularAccelerationMatrix asAngularAcceleration(final AngularAccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularAccelerationMatrix", this.toString());
        AngularAccelerationMatrix result = new AngularAccelerationMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a angularvelocity matrix.
     * @return AngularVelocityMatrix; the current matrix as a angularvelocity matrix
     */
    public final AngularVelocityMatrix asAngularVelocity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularVelocityMatrix", this.toString());
        return new AngularVelocityMatrix(this.data, AngularVelocityUnit.SI);
    }

    /**
     * Return the current matrix as a angularvelocity matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AngularVelocityMatrix; the current matrix as a angularvelocity matrix
     */
    public final AngularVelocityMatrix asAngularVelocity(final AngularVelocityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularVelocityMatrix", this.toString());
        AngularVelocityMatrix result = new AngularVelocityMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a area matrix.
     * @return AreaMatrix; the current matrix as a area matrix
     */
    public final AreaMatrix asArea()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AreaMatrix", this.toString());
        return new AreaMatrix(this.data, AreaUnit.SI);
    }

    /**
     * Return the current matrix as a area matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AreaMatrix; the current matrix as a area matrix
     */
    public final AreaMatrix asArea(final AreaUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AreaMatrix", this.toString());
        AreaMatrix result = new AreaMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a catalyticactivity matrix.
     * @return CatalyticActivityMatrix; the current matrix as a catalyticactivity matrix
     */
    public final CatalyticActivityMatrix asCatalyticActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to CatalyticActivityMatrix", this.toString());
        return new CatalyticActivityMatrix(this.data, CatalyticActivityUnit.SI);
    }

    /**
     * Return the current matrix as a catalyticactivity matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return CatalyticActivityMatrix; the current matrix as a catalyticactivity matrix
     */
    public final CatalyticActivityMatrix asCatalyticActivity(final CatalyticActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to CatalyticActivityMatrix", this.toString());
        CatalyticActivityMatrix result = new CatalyticActivityMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a density matrix.
     * @return DensityMatrix; the current matrix as a density matrix
     */
    public final DensityMatrix asDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DensityMatrix", this.toString());
        return new DensityMatrix(this.data, DensityUnit.SI);
    }

    /**
     * Return the current matrix as a density matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return DensityMatrix; the current matrix as a density matrix
     */
    public final DensityMatrix asDensity(final DensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DensityMatrix", this.toString());
        DensityMatrix result = new DensityMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a dimensionless matrix.
     * @return DimensionlessMatrix; the current matrix as a dimensionless matrix
     */
    public final DimensionlessMatrix asDimensionless()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DimensionlessMatrix", this.toString());
        return new DimensionlessMatrix(this.data, DimensionlessUnit.SI);
    }

    /**
     * Return the current matrix as a dimensionless matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return DimensionlessMatrix; the current matrix as a dimensionless matrix
     */
    public final DimensionlessMatrix asDimensionless(final DimensionlessUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DimensionlessMatrix", this.toString());
        DimensionlessMatrix result = new DimensionlessMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a electricalcapacitance matrix.
     * @return ElectricalCapacitanceMatrix; the current matrix as a electricalcapacitance matrix
     */
    public final ElectricalCapacitanceMatrix asElectricalCapacitance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCapacitanceMatrix", this.toString());
        return new ElectricalCapacitanceMatrix(this.data, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Return the current matrix as a electricalcapacitance matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalCapacitanceMatrix; the current matrix as a electricalcapacitance matrix
     */
    public final ElectricalCapacitanceMatrix asElectricalCapacitance(final ElectricalCapacitanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCapacitanceMatrix", this.toString());
        ElectricalCapacitanceMatrix result = new ElectricalCapacitanceMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a electricalcharge matrix.
     * @return ElectricalChargeMatrix; the current matrix as a electricalcharge matrix
     */
    public final ElectricalChargeMatrix asElectricalCharge()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalChargeMatrix", this.toString());
        return new ElectricalChargeMatrix(this.data, ElectricalChargeUnit.SI);
    }

    /**
     * Return the current matrix as a electricalcharge matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalChargeMatrix; the current matrix as a electricalcharge matrix
     */
    public final ElectricalChargeMatrix asElectricalCharge(final ElectricalChargeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalChargeMatrix", this.toString());
        ElectricalChargeMatrix result = new ElectricalChargeMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a electricalconductance matrix.
     * @return ElectricalConductanceMatrix; the current matrix as a electricalconductance matrix
     */
    public final ElectricalConductanceMatrix asElectricalConductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalConductanceMatrix", this.toString());
        return new ElectricalConductanceMatrix(this.data, ElectricalConductanceUnit.SI);
    }

    /**
     * Return the current matrix as a electricalconductance matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalConductanceMatrix; the current matrix as a electricalconductance matrix
     */
    public final ElectricalConductanceMatrix asElectricalConductance(final ElectricalConductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalConductanceMatrix", this.toString());
        ElectricalConductanceMatrix result = new ElectricalConductanceMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a electricalcurrent matrix.
     * @return ElectricalCurrentMatrix; the current matrix as a electricalcurrent matrix
     */
    public final ElectricalCurrentMatrix asElectricalCurrent()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCurrentMatrix", this.toString());
        return new ElectricalCurrentMatrix(this.data, ElectricalCurrentUnit.SI);
    }

    /**
     * Return the current matrix as a electricalcurrent matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalCurrentMatrix; the current matrix as a electricalcurrent matrix
     */
    public final ElectricalCurrentMatrix asElectricalCurrent(final ElectricalCurrentUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCurrentMatrix", this.toString());
        ElectricalCurrentMatrix result = new ElectricalCurrentMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a electricalinductance matrix.
     * @return ElectricalInductanceMatrix; the current matrix as a electricalinductance matrix
     */
    public final ElectricalInductanceMatrix asElectricalInductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalInductanceMatrix", this.toString());
        return new ElectricalInductanceMatrix(this.data, ElectricalInductanceUnit.SI);
    }

    /**
     * Return the current matrix as a electricalinductance matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalInductanceMatrix; the current matrix as a electricalinductance matrix
     */
    public final ElectricalInductanceMatrix asElectricalInductance(final ElectricalInductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalInductanceMatrix", this.toString());
        ElectricalInductanceMatrix result = new ElectricalInductanceMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a electricalpotential matrix.
     * @return ElectricalPotentialMatrix; the current matrix as a electricalpotential matrix
     */
    public final ElectricalPotentialMatrix asElectricalPotential()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalPotentialMatrix", this.toString());
        return new ElectricalPotentialMatrix(this.data, ElectricalPotentialUnit.SI);
    }

    /**
     * Return the current matrix as a electricalpotential matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalPotentialMatrix; the current matrix as a electricalpotential matrix
     */
    public final ElectricalPotentialMatrix asElectricalPotential(final ElectricalPotentialUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalPotentialMatrix", this.toString());
        ElectricalPotentialMatrix result = new ElectricalPotentialMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a electricalresistance matrix.
     * @return ElectricalResistanceMatrix; the current matrix as a electricalresistance matrix
     */
    public final ElectricalResistanceMatrix asElectricalResistance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalResistanceMatrix", this.toString());
        return new ElectricalResistanceMatrix(this.data, ElectricalResistanceUnit.SI);
    }

    /**
     * Return the current matrix as a electricalresistance matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ElectricalResistanceMatrix; the current matrix as a electricalresistance matrix
     */
    public final ElectricalResistanceMatrix asElectricalResistance(final ElectricalResistanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalResistanceMatrix", this.toString());
        ElectricalResistanceMatrix result = new ElectricalResistanceMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a energy matrix.
     * @return EnergyMatrix; the current matrix as a energy matrix
     */
    public final EnergyMatrix asEnergy()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EnergyMatrix", this.toString());
        return new EnergyMatrix(this.data, EnergyUnit.SI);
    }

    /**
     * Return the current matrix as a energy matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return EnergyMatrix; the current matrix as a energy matrix
     */
    public final EnergyMatrix asEnergy(final EnergyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EnergyMatrix", this.toString());
        EnergyMatrix result = new EnergyMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a equivalentdose matrix.
     * @return EquivalentDoseMatrix; the current matrix as a equivalentdose matrix
     */
    public final EquivalentDoseMatrix asEquivalentDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EquivalentDoseMatrix", this.toString());
        return new EquivalentDoseMatrix(this.data, EquivalentDoseUnit.SI);
    }

    /**
     * Return the current matrix as a equivalentdose matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return EquivalentDoseMatrix; the current matrix as a equivalentdose matrix
     */
    public final EquivalentDoseMatrix asEquivalentDose(final EquivalentDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EquivalentDoseMatrix", this.toString());
        EquivalentDoseMatrix result = new EquivalentDoseMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a flowmass matrix.
     * @return FlowMassMatrix; the current matrix as a flowmass matrix
     */
    public final FlowMassMatrix asFlowMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowMassMatrix", this.toString());
        return new FlowMassMatrix(this.data, FlowMassUnit.SI);
    }

    /**
     * Return the current matrix as a flowmass matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FlowMassMatrix; the current matrix as a flowmass matrix
     */
    public final FlowMassMatrix asFlowMass(final FlowMassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowMassMatrix", this.toString());
        FlowMassMatrix result = new FlowMassMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a flowvolume matrix.
     * @return FlowVolumeMatrix; the current matrix as a flowvolume matrix
     */
    public final FlowVolumeMatrix asFlowVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowVolumeMatrix", this.toString());
        return new FlowVolumeMatrix(this.data, FlowVolumeUnit.SI);
    }

    /**
     * Return the current matrix as a flowvolume matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FlowVolumeMatrix; the current matrix as a flowvolume matrix
     */
    public final FlowVolumeMatrix asFlowVolume(final FlowVolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowVolumeMatrix", this.toString());
        FlowVolumeMatrix result = new FlowVolumeMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a force matrix.
     * @return ForceMatrix; the current matrix as a force matrix
     */
    public final ForceMatrix asForce()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ForceMatrix", this.toString());
        return new ForceMatrix(this.data, ForceUnit.SI);
    }

    /**
     * Return the current matrix as a force matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return ForceMatrix; the current matrix as a force matrix
     */
    public final ForceMatrix asForce(final ForceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ForceMatrix", this.toString());
        ForceMatrix result = new ForceMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a frequency matrix.
     * @return FrequencyMatrix; the current matrix as a frequency matrix
     */
    public final FrequencyMatrix asFrequency()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FrequencyMatrix", this.toString());
        return new FrequencyMatrix(this.data, FrequencyUnit.SI);
    }

    /**
     * Return the current matrix as a frequency matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FrequencyMatrix; the current matrix as a frequency matrix
     */
    public final FrequencyMatrix asFrequency(final FrequencyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FrequencyMatrix", this.toString());
        FrequencyMatrix result = new FrequencyMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a illuminance matrix.
     * @return IlluminanceMatrix; the current matrix as a illuminance matrix
     */
    public final IlluminanceMatrix asIlluminance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to IlluminanceMatrix", this.toString());
        return new IlluminanceMatrix(this.data, IlluminanceUnit.SI);
    }

    /**
     * Return the current matrix as a illuminance matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return IlluminanceMatrix; the current matrix as a illuminance matrix
     */
    public final IlluminanceMatrix asIlluminance(final IlluminanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to IlluminanceMatrix", this.toString());
        IlluminanceMatrix result = new IlluminanceMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a lineardensity matrix.
     * @return LinearDensityMatrix; the current matrix as a lineardensity matrix
     */
    public final LinearDensityMatrix asLinearDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LinearDensityMatrix", this.toString());
        return new LinearDensityMatrix(this.data, LinearDensityUnit.SI);
    }

    /**
     * Return the current matrix as a lineardensity matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return LinearDensityMatrix; the current matrix as a lineardensity matrix
     */
    public final LinearDensityMatrix asLinearDensity(final LinearDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LinearDensityMatrix", this.toString());
        LinearDensityMatrix result = new LinearDensityMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a luminousflux matrix.
     * @return LuminousFluxMatrix; the current matrix as a luminousflux matrix
     */
    public final LuminousFluxMatrix asLuminousFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousFluxMatrix", this.toString());
        return new LuminousFluxMatrix(this.data, LuminousFluxUnit.SI);
    }

    /**
     * Return the current matrix as a luminousflux matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return LuminousFluxMatrix; the current matrix as a luminousflux matrix
     */
    public final LuminousFluxMatrix asLuminousFlux(final LuminousFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousFluxMatrix", this.toString());
        LuminousFluxMatrix result = new LuminousFluxMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a luminousintensity matrix.
     * @return LuminousIntensityMatrix; the current matrix as a luminousintensity matrix
     */
    public final LuminousIntensityMatrix asLuminousIntensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousIntensityMatrix", this.toString());
        return new LuminousIntensityMatrix(this.data, LuminousIntensityUnit.SI);
    }

    /**
     * Return the current matrix as a luminousintensity matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return LuminousIntensityMatrix; the current matrix as a luminousintensity matrix
     */
    public final LuminousIntensityMatrix asLuminousIntensity(final LuminousIntensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousIntensityMatrix", this.toString());
        LuminousIntensityMatrix result = new LuminousIntensityMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a magneticfluxdensity matrix.
     * @return MagneticFluxDensityMatrix; the current matrix as a magneticfluxdensity matrix
     */
    public final MagneticFluxDensityMatrix asMagneticFluxDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxDensityMatrix", this.toString());
        return new MagneticFluxDensityMatrix(this.data, MagneticFluxDensityUnit.SI);
    }

    /**
     * Return the current matrix as a magneticfluxdensity matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return MagneticFluxDensityMatrix; the current matrix as a magneticfluxdensity matrix
     */
    public final MagneticFluxDensityMatrix asMagneticFluxDensity(final MagneticFluxDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxDensityMatrix", this.toString());
        MagneticFluxDensityMatrix result = new MagneticFluxDensityMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a magneticflux matrix.
     * @return MagneticFluxMatrix; the current matrix as a magneticflux matrix
     */
    public final MagneticFluxMatrix asMagneticFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxMatrix", this.toString());
        return new MagneticFluxMatrix(this.data, MagneticFluxUnit.SI);
    }

    /**
     * Return the current matrix as a magneticflux matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return MagneticFluxMatrix; the current matrix as a magneticflux matrix
     */
    public final MagneticFluxMatrix asMagneticFlux(final MagneticFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxMatrix", this.toString());
        MagneticFluxMatrix result = new MagneticFluxMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a mass matrix.
     * @return MassMatrix; the current matrix as a mass matrix
     */
    public final MassMatrix asMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MassMatrix", this.toString());
        return new MassMatrix(this.data, MassUnit.SI);
    }

    /**
     * Return the current matrix as a mass matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return MassMatrix; the current matrix as a mass matrix
     */
    public final MassMatrix asMass(final MassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MassMatrix", this.toString());
        MassMatrix result = new MassMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a momentum matrix.
     * @return MomentumMatrix; the current matrix as a momentum matrix
     */
    public final MomentumMatrix asMomentum()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MomentumMatrix", this.toString());
        return new MomentumMatrix(this.data, MomentumUnit.SI);
    }

    /**
     * Return the current matrix as a momentum matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return MomentumMatrix; the current matrix as a momentum matrix
     */
    public final MomentumMatrix asMomentum(final MomentumUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MomentumMatrix", this.toString());
        MomentumMatrix result = new MomentumMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a power matrix.
     * @return PowerMatrix; the current matrix as a power matrix
     */
    public final PowerMatrix asPower()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to PowerMatrix", this.toString());
        return new PowerMatrix(this.data, PowerUnit.SI);
    }

    /**
     * Return the current matrix as a power matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return PowerMatrix; the current matrix as a power matrix
     */
    public final PowerMatrix asPower(final PowerUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to PowerMatrix", this.toString());
        PowerMatrix result = new PowerMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a pressure matrix.
     * @return PressureMatrix; the current matrix as a pressure matrix
     */
    public final PressureMatrix asPressure()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to PressureMatrix", this.toString());
        return new PressureMatrix(this.data, PressureUnit.SI);
    }

    /**
     * Return the current matrix as a pressure matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return PressureMatrix; the current matrix as a pressure matrix
     */
    public final PressureMatrix asPressure(final PressureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to PressureMatrix", this.toString());
        PressureMatrix result = new PressureMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a radioactivity matrix.
     * @return RadioActivityMatrix; the current matrix as a radioactivity matrix
     */
    public final RadioActivityMatrix asRadioActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to RadioActivityMatrix", this.toString());
        return new RadioActivityMatrix(this.data, RadioActivityUnit.SI);
    }

    /**
     * Return the current matrix as a radioactivity matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return RadioActivityMatrix; the current matrix as a radioactivity matrix
     */
    public final RadioActivityMatrix asRadioActivity(final RadioActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to RadioActivityMatrix", this.toString());
        RadioActivityMatrix result = new RadioActivityMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a solidangle matrix.
     * @return SolidAngleMatrix; the current matrix as a solidangle matrix
     */
    public final SolidAngleMatrix asSolidAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SolidAngleMatrix", this.toString());
        return new SolidAngleMatrix(this.data, SolidAngleUnit.SI);
    }

    /**
     * Return the current matrix as a solidangle matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return SolidAngleMatrix; the current matrix as a solidangle matrix
     */
    public final SolidAngleMatrix asSolidAngle(final SolidAngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SolidAngleMatrix", this.toString());
        SolidAngleMatrix result = new SolidAngleMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a speed matrix.
     * @return SpeedMatrix; the current matrix as a speed matrix
     */
    public final SpeedMatrix asSpeed()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SpeedMatrix", this.toString());
        return new SpeedMatrix(this.data, SpeedUnit.SI);
    }

    /**
     * Return the current matrix as a speed matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return SpeedMatrix; the current matrix as a speed matrix
     */
    public final SpeedMatrix asSpeed(final SpeedUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SpeedMatrix", this.toString());
        SpeedMatrix result = new SpeedMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a torque matrix.
     * @return TorqueMatrix; the current matrix as a torque matrix
     */
    public final TorqueMatrix asTorque()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to TorqueMatrix", this.toString());
        return new TorqueMatrix(this.data, TorqueUnit.SI);
    }

    /**
     * Return the current matrix as a torque matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return TorqueMatrix; the current matrix as a torque matrix
     */
    public final TorqueMatrix asTorque(final TorqueUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to TorqueMatrix", this.toString());
        TorqueMatrix result = new TorqueMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a volume matrix.
     * @return VolumeMatrix; the current matrix as a volume matrix
     */
    public final VolumeMatrix asVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to VolumeMatrix", this.toString());
        return new VolumeMatrix(this.data, VolumeUnit.SI);
    }

    /**
     * Return the current matrix as a volume matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return VolumeMatrix; the current matrix as a volume matrix
     */
    public final VolumeMatrix asVolume(final VolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to VolumeMatrix", this.toString());
        VolumeMatrix result = new VolumeMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a angle matrix.
     * @return AngleMatrix; the current matrix as a angle matrix
     */
    public final AngleMatrix asAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngleMatrix", this.toString());
        return new AngleMatrix(this.data, AngleUnit.SI);
    }

    /**
     * Return the current matrix as a angle matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return AngleMatrix; the current matrix as a angle matrix
     */
    public final AngleMatrix asAngle(final AngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngleMatrix", this.toString());
        AngleMatrix result = new AngleMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a length matrix.
     * @return LengthMatrix; the current matrix as a length matrix
     */
    public final LengthMatrix asLength()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LengthMatrix", this.toString());
        return new LengthMatrix(this.data, LengthUnit.SI);
    }

    /**
     * Return the current matrix as a length matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return LengthMatrix; the current matrix as a length matrix
     */
    public final LengthMatrix asLength(final LengthUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LengthMatrix", this.toString());
        LengthMatrix result = new LengthMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a temperature matrix.
     * @return TemperatureMatrix; the current matrix as a temperature matrix
     */
    public final TemperatureMatrix asTemperature()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to TemperatureMatrix", this.toString());
        return new TemperatureMatrix(this.data, TemperatureUnit.SI);
    }

    /**
     * Return the current matrix as a temperature matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return TemperatureMatrix; the current matrix as a temperature matrix
     */
    public final TemperatureMatrix asTemperature(final TemperatureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to TemperatureMatrix", this.toString());
        TemperatureMatrix result = new TemperatureMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current matrix as a duration matrix.
     * @return DurationMatrix; the current matrix as a duration matrix
     */
    public final DurationMatrix asDuration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DurationMatrix", this.toString());
        return new DurationMatrix(this.data, DurationUnit.SI);
    }

    /**
     * Return the current matrix as a duration matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return DurationMatrix; the current matrix as a duration matrix
     */
    public final DurationMatrix asDuration(final DurationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DurationMatrix", this.toString());
        DurationMatrix result = new DurationMatrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
