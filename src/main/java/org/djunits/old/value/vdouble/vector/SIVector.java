package org.djunits.old.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.old.unit.AbsorbedDoseUnit;
import org.djunits.old.unit.AccelerationUnit;
import org.djunits.old.unit.AmountOfSubstanceUnit;
import org.djunits.old.unit.AngleUnit;
import org.djunits.old.unit.AngularAccelerationUnit;
import org.djunits.old.unit.AngularVelocityUnit;
import org.djunits.old.unit.AreaUnit;
import org.djunits.old.unit.CatalyticActivityUnit;
import org.djunits.old.unit.DensityUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.DurationUnit;
import org.djunits.old.unit.ElectricalCapacitanceUnit;
import org.djunits.old.unit.ElectricalChargeUnit;
import org.djunits.old.unit.ElectricalConductanceUnit;
import org.djunits.old.unit.ElectricalCurrentUnit;
import org.djunits.old.unit.ElectricalInductanceUnit;
import org.djunits.old.unit.ElectricalPotentialUnit;
import org.djunits.old.unit.ElectricalResistanceUnit;
import org.djunits.old.unit.EnergyUnit;
import org.djunits.old.unit.EquivalentDoseUnit;
import org.djunits.old.unit.FlowMassUnit;
import org.djunits.old.unit.FlowVolumeUnit;
import org.djunits.old.unit.ForceUnit;
import org.djunits.old.unit.FrequencyUnit;
import org.djunits.old.unit.IlluminanceUnit;
import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.LinearDensityUnit;
import org.djunits.old.unit.LuminousFluxUnit;
import org.djunits.old.unit.LuminousIntensityUnit;
import org.djunits.old.unit.MagneticFluxDensityUnit;
import org.djunits.old.unit.MagneticFluxUnit;
import org.djunits.old.unit.MassUnit;
import org.djunits.old.unit.MomentumUnit;
import org.djunits.old.unit.PowerUnit;
import org.djunits.old.unit.PressureUnit;
import org.djunits.old.unit.RadioActivityUnit;
import org.djunits.old.unit.SIUnit;
import org.djunits.old.unit.SolidAngleUnit;
import org.djunits.old.unit.SpeedUnit;
import org.djunits.old.unit.TemperatureUnit;
import org.djunits.old.unit.TorqueUnit;
import org.djunits.old.unit.Unit;
import org.djunits.old.unit.VolumeUnit;
import org.djunits.old.unit.scale.IdentityScale;
import org.djunits.old.unit.si.SIDimensions;
import org.djunits.old.unit.util.UnitRuntimeException;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vdouble.scalar.SIScalar;
import org.djunits.old.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.old.value.vdouble.vector.data.DoubleVectorData;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the generic Relative SI DoubleVector.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class SIVector extends DoubleVectorRel<SIUnit, SIScalar, SIVector>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct an SIVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public SIVector(final DoubleVectorData data, final SIUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* ****************************** CONSTRUCTORS WITH double[] ****************************** */

    /**
     * Construct an SIVector from a double[] object. The double values are expressed in the displayUnit, and will be printed
     * using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public SIVector(final double[] data, final SIUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an SIVector from a double[] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public SIVector(final double[] data, final SIUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /* ****************************** CONSTRUCTORS WITH SIScalar[] ****************************** */

    /**
     * Construct an SIVector from an array of SIScalar objects. The SIScalar values are each expressed in their own unit, but
     * will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public SIVector(final SIScalar[] data, final SIUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an SIVector from an array of SIScalar objects. The SIScalar values are each expressed in their own unit, but
     * will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the StorageType is
     * DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public SIVector(final SIScalar[] data, final SIUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /* ****************************** CONSTRUCTORS WITH List<Double> or List<SIScalar> ****************************** */

    /**
     * Construct an SIVector from a list of Number objects or a list of SIScalar objects. Note that the displayUnit has a
     * different meaning depending on whether the list contains Number objects (e.g., Double objects) or SIScalar objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains SIScalar objects, each SIScalar has its own
     * unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public SIVector(final List<? extends Number> data, final SIUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof SIScalar ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an SIVector from a list of Number objects or a list of SIScalar objects. Note that the displayUnit has a
     * different meaning depending on whether the list contains Number objects (e.g., Double objects) or SIScalar objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains SIScalar objects, each SIScalar has its own
     * unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we offer
     * the data as a List.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public SIVector(final List<? extends Number> data, final SIUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /* ******************** CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, SIScalar> ******************** */

    /**
     * Construct an SIVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * SIScalar objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of
     * the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different
     * meaning depending on whether the map contains Number objects (e.g., Double objects) or SIScalar objects. In case the map
     * contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the
     * unit in which they will be printed. In case the map contains SIScalar objects, each SIScalar has its own unit, and the
     * displayUnit is just used for printing. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public SIVector(final Map<Integer, ? extends Number> data, final int size, final SIUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof SIScalar
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an SIVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * SIScalar objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of
     * the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different
     * meaning depending on whether the map contains Number objects (e.g., Double objects) or SIScalar objects. In case the map
     * contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the
     * unit in which they will be printed. In case the map contains SIScalar objects, each SIScalar has its own unit, and the
     * displayUnit is just used for printing. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as
     * a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public SIVector(final Map<Integer, ? extends Number> data, final int size, final SIUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<SIScalar> getScalarClass()
    {
        return SIScalar.class;
    }

    /**
     * Returns an SIVector based on an array of values and the textual representation of the unit.
     * @param value the values to use
     * @param unitString the textual representation of the unit
     * @param storageType the storage type to use
     * @return the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIVector of(final double[] value, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(value, "Error parsing SIVector: value is null");
        Throw.whenNull(unitString, "Error parsing SIVector: unitString is null");
        Throw.whenNull(storageType, "Error parsing SIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new SIVector(value, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIVector with unit " + unitString);
    }

    /**
     * Returns an SIVector based on an array of values and the textual representation of the unit.
     * @param valueList the values to use
     * @param unitString the textual representation of the unit
     * @param storageType the storage type to use
     * @return the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIVector of(final List<Double> valueList, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(valueList, "Error parsing SIVector: valueList is null");
        Throw.whenNull(unitString, "Error parsing SIVector: unitString is null");
        Throw.whenNull(storageType, "Error parsing SIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new SIVector(valueList, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIVector with unit " + unitString);
    }

    /**
     * Returns an SIVector based on a (sparse) map of values and the textual representation of the unit.
     * @param valueMap the values to use
     * @param unitString the textual representation of the unit
     * @param length the size of the vector
     * @param storageType the storage type to use
     * @return the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIVector of(final Map<Integer, Double> valueMap, final String unitString, final int length,
            final StorageType storageType)
    {
        Throw.whenNull(valueMap, "Error parsing SIVector: valueMap is null");
        Throw.whenNull(unitString, "Error parsing SIVector: unitString is null");
        Throw.whenNull(storageType, "Error parsing SIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new SIVector(valueMap, length, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIVector with unit " + unitString);
    }

    @Override
    public SIVector instantiateVector(final DoubleVectorData dvd, final SIUnit unit)
    {
        return new SIVector(dvd, unit);
    }

    @Override
    public SIScalar instantiateScalarSI(final double valueSI, final SIUnit unit)
    {
        return new SIScalar(valueSI, unit);
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
     * Return the current vector as a absorbeddose vector.
     * @return the current vector as a absorbeddose vector
     */
    public final AbsorbedDoseVector asAbsorbedDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AbsorbedDoseVector", this.toString());
        return new AbsorbedDoseVector(this.data, AbsorbedDoseUnit.SI);
    }

    /**
     * Return the current vector as a absorbeddose vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a absorbeddose vector
     */
    public final AbsorbedDoseVector asAbsorbedDose(final AbsorbedDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AbsorbedDoseVector", this.toString());
        AbsorbedDoseVector result = new AbsorbedDoseVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a acceleration vector.
     * @return the current vector as a acceleration vector
     */
    public final AccelerationVector asAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AccelerationVector", this.toString());
        return new AccelerationVector(this.data, AccelerationUnit.SI);
    }

    /**
     * Return the current vector as a acceleration vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a acceleration vector
     */
    public final AccelerationVector asAcceleration(final AccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AccelerationVector", this.toString());
        AccelerationVector result = new AccelerationVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a amountofsubstance vector.
     * @return the current vector as a amountofsubstance vector
     */
    public final AmountOfSubstanceVector asAmountOfSubstance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AmountOfSubstanceVector", this.toString());
        return new AmountOfSubstanceVector(this.data, AmountOfSubstanceUnit.SI);
    }

    /**
     * Return the current vector as a amountofsubstance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a amountofsubstance vector
     */
    public final AmountOfSubstanceVector asAmountOfSubstance(final AmountOfSubstanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AmountOfSubstanceVector", this.toString());
        AmountOfSubstanceVector result = new AmountOfSubstanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a angularacceleration vector.
     * @return the current vector as a angularacceleration vector
     */
    public final AngularAccelerationVector asAngularAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularAccelerationVector", this.toString());
        return new AngularAccelerationVector(this.data, AngularAccelerationUnit.SI);
    }

    /**
     * Return the current vector as a angularacceleration vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a angularacceleration vector
     */
    public final AngularAccelerationVector asAngularAcceleration(final AngularAccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularAccelerationVector", this.toString());
        AngularAccelerationVector result = new AngularAccelerationVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a angularvelocity vector.
     * @return the current vector as a angularvelocity vector
     */
    public final AngularVelocityVector asAngularVelocity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularVelocityVector", this.toString());
        return new AngularVelocityVector(this.data, AngularVelocityUnit.SI);
    }

    /**
     * Return the current vector as a angularvelocity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a angularvelocity vector
     */
    public final AngularVelocityVector asAngularVelocity(final AngularVelocityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngularVelocityVector", this.toString());
        AngularVelocityVector result = new AngularVelocityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a area vector.
     * @return the current vector as a area vector
     */
    public final AreaVector asArea()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AreaVector", this.toString());
        return new AreaVector(this.data, AreaUnit.SI);
    }

    /**
     * Return the current vector as a area vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a area vector
     */
    public final AreaVector asArea(final AreaUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AreaVector", this.toString());
        AreaVector result = new AreaVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a catalyticactivity vector.
     * @return the current vector as a catalyticactivity vector
     */
    public final CatalyticActivityVector asCatalyticActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to CatalyticActivityVector", this.toString());
        return new CatalyticActivityVector(this.data, CatalyticActivityUnit.SI);
    }

    /**
     * Return the current vector as a catalyticactivity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a catalyticactivity vector
     */
    public final CatalyticActivityVector asCatalyticActivity(final CatalyticActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to CatalyticActivityVector", this.toString());
        CatalyticActivityVector result = new CatalyticActivityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a density vector.
     * @return the current vector as a density vector
     */
    public final DensityVector asDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DensityVector", this.toString());
        return new DensityVector(this.data, DensityUnit.SI);
    }

    /**
     * Return the current vector as a density vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a density vector
     */
    public final DensityVector asDensity(final DensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DensityVector", this.toString());
        DensityVector result = new DensityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a dimensionless vector.
     * @return the current vector as a dimensionless vector
     */
    public final DimensionlessVector asDimensionless()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DimensionlessVector", this.toString());
        return new DimensionlessVector(this.data, DimensionlessUnit.SI);
    }

    /**
     * Return the current vector as a dimensionless vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a dimensionless vector
     */
    public final DimensionlessVector asDimensionless(final DimensionlessUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DimensionlessVector", this.toString());
        DimensionlessVector result = new DimensionlessVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalcapacitance vector.
     * @return the current vector as a electricalcapacitance vector
     */
    public final ElectricalCapacitanceVector asElectricalCapacitance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCapacitanceVector", this.toString());
        return new ElectricalCapacitanceVector(this.data, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Return the current vector as a electricalcapacitance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a electricalcapacitance vector
     */
    public final ElectricalCapacitanceVector asElectricalCapacitance(final ElectricalCapacitanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCapacitanceVector", this.toString());
        ElectricalCapacitanceVector result = new ElectricalCapacitanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalcharge vector.
     * @return the current vector as a electricalcharge vector
     */
    public final ElectricalChargeVector asElectricalCharge()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalChargeVector", this.toString());
        return new ElectricalChargeVector(this.data, ElectricalChargeUnit.SI);
    }

    /**
     * Return the current vector as a electricalcharge vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a electricalcharge vector
     */
    public final ElectricalChargeVector asElectricalCharge(final ElectricalChargeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalChargeVector", this.toString());
        ElectricalChargeVector result = new ElectricalChargeVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalconductance vector.
     * @return the current vector as a electricalconductance vector
     */
    public final ElectricalConductanceVector asElectricalConductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalConductanceVector", this.toString());
        return new ElectricalConductanceVector(this.data, ElectricalConductanceUnit.SI);
    }

    /**
     * Return the current vector as a electricalconductance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a electricalconductance vector
     */
    public final ElectricalConductanceVector asElectricalConductance(final ElectricalConductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalConductanceVector", this.toString());
        ElectricalConductanceVector result = new ElectricalConductanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalcurrent vector.
     * @return the current vector as a electricalcurrent vector
     */
    public final ElectricalCurrentVector asElectricalCurrent()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCurrentVector", this.toString());
        return new ElectricalCurrentVector(this.data, ElectricalCurrentUnit.SI);
    }

    /**
     * Return the current vector as a electricalcurrent vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a electricalcurrent vector
     */
    public final ElectricalCurrentVector asElectricalCurrent(final ElectricalCurrentUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalCurrentVector", this.toString());
        ElectricalCurrentVector result = new ElectricalCurrentVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalinductance vector.
     * @return the current vector as a electricalinductance vector
     */
    public final ElectricalInductanceVector asElectricalInductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalInductanceVector", this.toString());
        return new ElectricalInductanceVector(this.data, ElectricalInductanceUnit.SI);
    }

    /**
     * Return the current vector as a electricalinductance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a electricalinductance vector
     */
    public final ElectricalInductanceVector asElectricalInductance(final ElectricalInductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalInductanceVector", this.toString());
        ElectricalInductanceVector result = new ElectricalInductanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalpotential vector.
     * @return the current vector as a electricalpotential vector
     */
    public final ElectricalPotentialVector asElectricalPotential()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalPotentialVector", this.toString());
        return new ElectricalPotentialVector(this.data, ElectricalPotentialUnit.SI);
    }

    /**
     * Return the current vector as a electricalpotential vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a electricalpotential vector
     */
    public final ElectricalPotentialVector asElectricalPotential(final ElectricalPotentialUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalPotentialVector", this.toString());
        ElectricalPotentialVector result = new ElectricalPotentialVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalresistance vector.
     * @return the current vector as a electricalresistance vector
     */
    public final ElectricalResistanceVector asElectricalResistance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalResistanceVector", this.toString());
        return new ElectricalResistanceVector(this.data, ElectricalResistanceUnit.SI);
    }

    /**
     * Return the current vector as a electricalresistance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a electricalresistance vector
     */
    public final ElectricalResistanceVector asElectricalResistance(final ElectricalResistanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ElectricalResistanceVector", this.toString());
        ElectricalResistanceVector result = new ElectricalResistanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a energy vector.
     * @return the current vector as a energy vector
     */
    public final EnergyVector asEnergy()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EnergyVector", this.toString());
        return new EnergyVector(this.data, EnergyUnit.SI);
    }

    /**
     * Return the current vector as a energy vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a energy vector
     */
    public final EnergyVector asEnergy(final EnergyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EnergyVector", this.toString());
        EnergyVector result = new EnergyVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a equivalentdose vector.
     * @return the current vector as a equivalentdose vector
     */
    public final EquivalentDoseVector asEquivalentDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EquivalentDoseVector", this.toString());
        return new EquivalentDoseVector(this.data, EquivalentDoseUnit.SI);
    }

    /**
     * Return the current vector as a equivalentdose vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a equivalentdose vector
     */
    public final EquivalentDoseVector asEquivalentDose(final EquivalentDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to EquivalentDoseVector", this.toString());
        EquivalentDoseVector result = new EquivalentDoseVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a flowmass vector.
     * @return the current vector as a flowmass vector
     */
    public final FlowMassVector asFlowMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowMassVector", this.toString());
        return new FlowMassVector(this.data, FlowMassUnit.SI);
    }

    /**
     * Return the current vector as a flowmass vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a flowmass vector
     */
    public final FlowMassVector asFlowMass(final FlowMassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowMassVector", this.toString());
        FlowMassVector result = new FlowMassVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a flowvolume vector.
     * @return the current vector as a flowvolume vector
     */
    public final FlowVolumeVector asFlowVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowVolumeVector", this.toString());
        return new FlowVolumeVector(this.data, FlowVolumeUnit.SI);
    }

    /**
     * Return the current vector as a flowvolume vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a flowvolume vector
     */
    public final FlowVolumeVector asFlowVolume(final FlowVolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FlowVolumeVector", this.toString());
        FlowVolumeVector result = new FlowVolumeVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a force vector.
     * @return the current vector as a force vector
     */
    public final ForceVector asForce()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ForceVector", this.toString());
        return new ForceVector(this.data, ForceUnit.SI);
    }

    /**
     * Return the current vector as a force vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a force vector
     */
    public final ForceVector asForce(final ForceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to ForceVector", this.toString());
        ForceVector result = new ForceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a frequency vector.
     * @return the current vector as a frequency vector
     */
    public final FrequencyVector asFrequency()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FrequencyVector", this.toString());
        return new FrequencyVector(this.data, FrequencyUnit.SI);
    }

    /**
     * Return the current vector as a frequency vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a frequency vector
     */
    public final FrequencyVector asFrequency(final FrequencyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FrequencyVector", this.toString());
        FrequencyVector result = new FrequencyVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a illuminance vector.
     * @return the current vector as a illuminance vector
     */
    public final IlluminanceVector asIlluminance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to IlluminanceVector", this.toString());
        return new IlluminanceVector(this.data, IlluminanceUnit.SI);
    }

    /**
     * Return the current vector as a illuminance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a illuminance vector
     */
    public final IlluminanceVector asIlluminance(final IlluminanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to IlluminanceVector", this.toString());
        IlluminanceVector result = new IlluminanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a lineardensity vector.
     * @return the current vector as a lineardensity vector
     */
    public final LinearDensityVector asLinearDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LinearDensityVector", this.toString());
        return new LinearDensityVector(this.data, LinearDensityUnit.SI);
    }

    /**
     * Return the current vector as a lineardensity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a lineardensity vector
     */
    public final LinearDensityVector asLinearDensity(final LinearDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LinearDensityVector", this.toString());
        LinearDensityVector result = new LinearDensityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a luminousflux vector.
     * @return the current vector as a luminousflux vector
     */
    public final LuminousFluxVector asLuminousFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousFluxVector", this.toString());
        return new LuminousFluxVector(this.data, LuminousFluxUnit.SI);
    }

    /**
     * Return the current vector as a luminousflux vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a luminousflux vector
     */
    public final LuminousFluxVector asLuminousFlux(final LuminousFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousFluxVector", this.toString());
        LuminousFluxVector result = new LuminousFluxVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a luminousintensity vector.
     * @return the current vector as a luminousintensity vector
     */
    public final LuminousIntensityVector asLuminousIntensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousIntensityVector", this.toString());
        return new LuminousIntensityVector(this.data, LuminousIntensityUnit.SI);
    }

    /**
     * Return the current vector as a luminousintensity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a luminousintensity vector
     */
    public final LuminousIntensityVector asLuminousIntensity(final LuminousIntensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LuminousIntensityVector", this.toString());
        LuminousIntensityVector result = new LuminousIntensityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a magneticfluxdensity vector.
     * @return the current vector as a magneticfluxdensity vector
     */
    public final MagneticFluxDensityVector asMagneticFluxDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxDensityVector", this.toString());
        return new MagneticFluxDensityVector(this.data, MagneticFluxDensityUnit.SI);
    }

    /**
     * Return the current vector as a magneticfluxdensity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a magneticfluxdensity vector
     */
    public final MagneticFluxDensityVector asMagneticFluxDensity(final MagneticFluxDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxDensityVector", this.toString());
        MagneticFluxDensityVector result = new MagneticFluxDensityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a magneticflux vector.
     * @return the current vector as a magneticflux vector
     */
    public final MagneticFluxVector asMagneticFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxVector", this.toString());
        return new MagneticFluxVector(this.data, MagneticFluxUnit.SI);
    }

    /**
     * Return the current vector as a magneticflux vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a magneticflux vector
     */
    public final MagneticFluxVector asMagneticFlux(final MagneticFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MagneticFluxVector", this.toString());
        MagneticFluxVector result = new MagneticFluxVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a mass vector.
     * @return the current vector as a mass vector
     */
    public final MassVector asMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MassVector", this.toString());
        return new MassVector(this.data, MassUnit.SI);
    }

    /**
     * Return the current vector as a mass vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a mass vector
     */
    public final MassVector asMass(final MassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MassVector", this.toString());
        MassVector result = new MassVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a momentum vector.
     * @return the current vector as a momentum vector
     */
    public final MomentumVector asMomentum()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MomentumVector", this.toString());
        return new MomentumVector(this.data, MomentumUnit.SI);
    }

    /**
     * Return the current vector as a momentum vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a momentum vector
     */
    public final MomentumVector asMomentum(final MomentumUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to MomentumVector", this.toString());
        MomentumVector result = new MomentumVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a power vector.
     * @return the current vector as a power vector
     */
    public final PowerVector asPower()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to PowerVector", this.toString());
        return new PowerVector(this.data, PowerUnit.SI);
    }

    /**
     * Return the current vector as a power vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a power vector
     */
    public final PowerVector asPower(final PowerUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to PowerVector", this.toString());
        PowerVector result = new PowerVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a pressure vector.
     * @return the current vector as a pressure vector
     */
    public final PressureVector asPressure()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to PressureVector", this.toString());
        return new PressureVector(this.data, PressureUnit.SI);
    }

    /**
     * Return the current vector as a pressure vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a pressure vector
     */
    public final PressureVector asPressure(final PressureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to PressureVector", this.toString());
        PressureVector result = new PressureVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a radioactivity vector.
     * @return the current vector as a radioactivity vector
     */
    public final RadioActivityVector asRadioActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to RadioActivityVector", this.toString());
        return new RadioActivityVector(this.data, RadioActivityUnit.SI);
    }

    /**
     * Return the current vector as a radioactivity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a radioactivity vector
     */
    public final RadioActivityVector asRadioActivity(final RadioActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to RadioActivityVector", this.toString());
        RadioActivityVector result = new RadioActivityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a solidangle vector.
     * @return the current vector as a solidangle vector
     */
    public final SolidAngleVector asSolidAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SolidAngleVector", this.toString());
        return new SolidAngleVector(this.data, SolidAngleUnit.SI);
    }

    /**
     * Return the current vector as a solidangle vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a solidangle vector
     */
    public final SolidAngleVector asSolidAngle(final SolidAngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SolidAngleVector", this.toString());
        SolidAngleVector result = new SolidAngleVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a speed vector.
     * @return the current vector as a speed vector
     */
    public final SpeedVector asSpeed()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SpeedVector", this.toString());
        return new SpeedVector(this.data, SpeedUnit.SI);
    }

    /**
     * Return the current vector as a speed vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a speed vector
     */
    public final SpeedVector asSpeed(final SpeedUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to SpeedVector", this.toString());
        SpeedVector result = new SpeedVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a torque vector.
     * @return the current vector as a torque vector
     */
    public final TorqueVector asTorque()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to TorqueVector", this.toString());
        return new TorqueVector(this.data, TorqueUnit.SI);
    }

    /**
     * Return the current vector as a torque vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a torque vector
     */
    public final TorqueVector asTorque(final TorqueUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to TorqueVector", this.toString());
        TorqueVector result = new TorqueVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a volume vector.
     * @return the current vector as a volume vector
     */
    public final VolumeVector asVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to VolumeVector", this.toString());
        return new VolumeVector(this.data, VolumeUnit.SI);
    }

    /**
     * Return the current vector as a volume vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a volume vector
     */
    public final VolumeVector asVolume(final VolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to VolumeVector", this.toString());
        VolumeVector result = new VolumeVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a angle vector.
     * @return the current vector as a angle vector
     */
    public final AngleVector asAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngleVector", this.toString());
        return new AngleVector(this.data, AngleUnit.SI);
    }

    /**
     * Return the current vector as a angle vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a angle vector
     */
    public final AngleVector asAngle(final AngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to AngleVector", this.toString());
        AngleVector result = new AngleVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a length vector.
     * @return the current vector as a length vector
     */
    public final LengthVector asLength()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LengthVector", this.toString());
        return new LengthVector(this.data, LengthUnit.SI);
    }

    /**
     * Return the current vector as a length vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a length vector
     */
    public final LengthVector asLength(final LengthUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to LengthVector", this.toString());
        LengthVector result = new LengthVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a temperature vector.
     * @return the current vector as a temperature vector
     */
    public final TemperatureVector asTemperature()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to TemperatureVector", this.toString());
        return new TemperatureVector(this.data, TemperatureUnit.SI);
    }

    /**
     * Return the current vector as a temperature vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a temperature vector
     */
    public final TemperatureVector asTemperature(final TemperatureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to TemperatureVector", this.toString());
        TemperatureVector result = new TemperatureVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a duration vector.
     * @return the current vector as a duration vector
     */
    public final DurationVector asDuration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DurationVector", this.toString());
        return new DurationVector(this.data, DurationUnit.SI);
    }

    /**
     * Return the current vector as a duration vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return the current vector as a duration vector
     */
    public final DurationVector asDuration(final DurationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to DurationVector", this.toString());
        DurationVector result = new DurationVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
