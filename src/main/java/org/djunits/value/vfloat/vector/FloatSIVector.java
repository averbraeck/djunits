package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

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
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the generic Relative SI FloatVector.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatSIVector extends FloatVectorRel<SIUnit, FloatSIScalar, FloatSIVector>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct a FloatSIVector from an internal data object.
     * @param data FloatVectorData; the internal data object for the vector
     * @param displayUnit SIUnit; the display unit of the vector data
     */
    public FloatSIVector(final FloatVectorData data, final SIUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* ****************************** CONSTRUCTORS WITH float[] ****************************** */

    /**
     * Construct a FloatSIVector from a float[] object. The float values are expressed in the displayUnit, and will be printed
     * using the displayUnit.
     * @param data float[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit SIUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSIVector(final float[] data, final SIUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatSIVector from a float[] object. The float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data float[]; the data for the vector
     * @param displayUnit SIUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatSIVector(final float[] data, final SIUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /* ****************************** CONSTRUCTORS WITH FloatSIScalar[] ****************************** */

    /**
     * Construct a FloatSIVector from an array of FloatSIScalar objects. The FloatSIScalar values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatSIScalar[]; the data for the vector
     * @param displayUnit SIUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSIVector(final FloatSIScalar[] data, final SIUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatSIVector from an array of FloatSIScalar objects. The FloatSIScalar values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data FloatSIScalar[]; the data for the vector
     * @param displayUnit SIUnit; the display unit of the values when printing
     */
    public FloatSIVector(final FloatSIScalar[] data, final SIUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /* ****************************** CONSTRUCTORS WITH List<Float> or List<FloatSIScalar> ****************************** */

    /**
     * Construct a FloatSIVector from a list of Number objects or a list of FloatSIScalar objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatSIScalar objects.
     * In case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are
     * expressed, as well as the unit in which they will be printed. In case the list contains FloatSIScalar objects, each
     * FloatSIScalar has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;SIScalar&gt;; the data for the vector
     * @param displayUnit SIUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSIVector(final List<? extends Number> data, final SIUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatSIScalar ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatSIVector from a list of Number objects or a list of FloatSIScalar objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatSIScalar objects.
     * In case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are
     * expressed, as well as the unit in which they will be printed. In case the list contains FloatSIScalar objects, each
     * FloatSIScalar has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;SIScalar&gt;; the data for the vector
     * @param displayUnit SIUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatSIVector(final List<? extends Number> data, final SIUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /* ******************** CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatSIScalar> ******************** */

    /**
     * Construct a FloatSIVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * FloatSIScalar objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatSIScalar objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatSIScalar objects, each FloatSIScalar has
     * its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSIScalar&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit SIUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSIVector(final Map<Integer, ? extends Number> data, final int size, final SIUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatSIScalar
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatSIVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * FloatSIScalar objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatSIScalar objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatSIScalar objects, each FloatSIScalar has
     * its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since
     * we offer the data as a Map.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSIScalar&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit SIUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatSIVector(final Map<Integer, ? extends Number> data, final int size, final SIUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<FloatSIScalar> getScalarClass()
    {
        return FloatSIScalar.class;
    }

    /**
     * Returns a FloatSIVector based on an array of values and the textual representation of the unit.
     * @param value float[]; the values to use
     * @param unitString String; the textual representation of the unit
     * @param storageType StorageType; the storage type to use
     * @return FloatSIVector; the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatSIVector of(final float[] value, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(value, "Error parsing FloatSIVector: value is null");
        Throw.whenNull(unitString, "Error parsing FloatSIVector: unitString is null");
        Throw.whenNull(storageType, "Error parsing FloatSIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new FloatSIVector(value, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing FloatSIVector with unit " + unitString);
    }

    /**
     * Returns a FloatSIVector based on an array of values and the textual representation of the unit.
     * @param valueList List&lt;Float&gt;; the values to use
     * @param unitString String; the textual representation of the unit
     * @param storageType StorageType; the storage type to use
     * @return FloatSIVector; the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatSIVector of(final List<Float> valueList, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(valueList, "Error parsing FloatSIVector: valueList is null");
        Throw.whenNull(unitString, "Error parsing FloatSIVector: unitString is null");
        Throw.whenNull(storageType, "Error parsing FloatSIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new FloatSIVector(valueList, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing FloatSIVector with unit " + unitString);
    }

    /**
     * Returns a FloatSIVector based on a (sparse) map of values and the textual representation of the unit.
     * @param valueMap Map&lt;Integer, Float&gt;; the values to use
     * @param unitString String; the textual representation of the unit
     * @param length int; the size of the vector
     * @param storageType StorageType; the storage type to use
     * @return FloatSIVector; the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatSIVector of(final Map<Integer, Float> valueMap, final String unitString, final int length,
            final StorageType storageType)
    {
        Throw.whenNull(valueMap, "Error parsing FloatSIVector: valueMap is null");
        Throw.whenNull(unitString, "Error parsing FloatSIVector: unitString is null");
        Throw.whenNull(storageType, "Error parsing FloatSIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new FloatSIVector(valueMap, length, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing FloatSIVector with unit " + unitString);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIVector instantiateVector(final FloatVectorData fvd, final SIUnit unit)
    {
        return new FloatSIVector(fvd, unit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar instantiateScalarSI(final float valueSI, final SIUnit unit)
    {
        return new FloatSIScalar(valueSI, unit);
    }

    /** {@inheritDoc} */
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
     * @return FloatAbsorbedDoseVector; the current vector as a absorbeddose vector
     */
    public final FloatAbsorbedDoseVector asAbsorbedDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAbsorbedDoseVector", this.toString());
        return new FloatAbsorbedDoseVector(this.data, AbsorbedDoseUnit.SI);
    }

    /**
     * Return the current vector as a absorbeddose vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatAbsorbedDoseVector; the current vector as a absorbeddose vector
     */
    public final FloatAbsorbedDoseVector asAbsorbedDose(final AbsorbedDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AbsorbedDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAbsorbedDoseVector", this.toString());
        FloatAbsorbedDoseVector result = new FloatAbsorbedDoseVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a acceleration vector.
     * @return FloatAccelerationVector; the current vector as a acceleration vector
     */
    public final FloatAccelerationVector asAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAccelerationVector", this.toString());
        return new FloatAccelerationVector(this.data, AccelerationUnit.SI);
    }

    /**
     * Return the current vector as a acceleration vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatAccelerationVector; the current vector as a acceleration vector
     */
    public final FloatAccelerationVector asAcceleration(final AccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAccelerationVector", this.toString());
        FloatAccelerationVector result = new FloatAccelerationVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a amountofsubstance vector.
     * @return FloatAmountOfSubstanceVector; the current vector as a amountofsubstance vector
     */
    public final FloatAmountOfSubstanceVector asAmountOfSubstance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAmountOfSubstanceVector", this.toString());
        return new FloatAmountOfSubstanceVector(this.data, AmountOfSubstanceUnit.SI);
    }

    /**
     * Return the current vector as a amountofsubstance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatAmountOfSubstanceVector; the current vector as a amountofsubstance vector
     */
    public final FloatAmountOfSubstanceVector asAmountOfSubstance(final AmountOfSubstanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AmountOfSubstanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAmountOfSubstanceVector", this.toString());
        FloatAmountOfSubstanceVector result = new FloatAmountOfSubstanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a angularacceleration vector.
     * @return FloatAngularAccelerationVector; the current vector as a angularacceleration vector
     */
    public final FloatAngularAccelerationVector asAngularAcceleration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngularAccelerationVector", this.toString());
        return new FloatAngularAccelerationVector(this.data, AngularAccelerationUnit.SI);
    }

    /**
     * Return the current vector as a angularacceleration vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatAngularAccelerationVector; the current vector as a angularacceleration vector
     */
    public final FloatAngularAccelerationVector asAngularAcceleration(final AngularAccelerationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularAccelerationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngularAccelerationVector", this.toString());
        FloatAngularAccelerationVector result = new FloatAngularAccelerationVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a angularvelocity vector.
     * @return FloatAngularVelocityVector; the current vector as a angularvelocity vector
     */
    public final FloatAngularVelocityVector asAngularVelocity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngularVelocityVector", this.toString());
        return new FloatAngularVelocityVector(this.data, AngularVelocityUnit.SI);
    }

    /**
     * Return the current vector as a angularvelocity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatAngularVelocityVector; the current vector as a angularvelocity vector
     */
    public final FloatAngularVelocityVector asAngularVelocity(final AngularVelocityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngularVelocityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngularVelocityVector", this.toString());
        FloatAngularVelocityVector result = new FloatAngularVelocityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a area vector.
     * @return FloatAreaVector; the current vector as a area vector
     */
    public final FloatAreaVector asArea()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAreaVector", this.toString());
        return new FloatAreaVector(this.data, AreaUnit.SI);
    }

    /**
     * Return the current vector as a area vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatAreaVector; the current vector as a area vector
     */
    public final FloatAreaVector asArea(final AreaUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AreaUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAreaVector", this.toString());
        FloatAreaVector result = new FloatAreaVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a catalyticactivity vector.
     * @return FloatCatalyticActivityVector; the current vector as a catalyticactivity vector
     */
    public final FloatCatalyticActivityVector asCatalyticActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatCatalyticActivityVector", this.toString());
        return new FloatCatalyticActivityVector(this.data, CatalyticActivityUnit.SI);
    }

    /**
     * Return the current vector as a catalyticactivity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatCatalyticActivityVector; the current vector as a catalyticactivity vector
     */
    public final FloatCatalyticActivityVector asCatalyticActivity(final CatalyticActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(CatalyticActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatCatalyticActivityVector", this.toString());
        FloatCatalyticActivityVector result = new FloatCatalyticActivityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a density vector.
     * @return FloatDensityVector; the current vector as a density vector
     */
    public final FloatDensityVector asDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDensityVector", this.toString());
        return new FloatDensityVector(this.data, DensityUnit.SI);
    }

    /**
     * Return the current vector as a density vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatDensityVector; the current vector as a density vector
     */
    public final FloatDensityVector asDensity(final DensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDensityVector", this.toString());
        FloatDensityVector result = new FloatDensityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a dimensionless vector.
     * @return FloatDimensionlessVector; the current vector as a dimensionless vector
     */
    public final FloatDimensionlessVector asDimensionless()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDimensionlessVector", this.toString());
        return new FloatDimensionlessVector(this.data, DimensionlessUnit.SI);
    }

    /**
     * Return the current vector as a dimensionless vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatDimensionlessVector; the current vector as a dimensionless vector
     */
    public final FloatDimensionlessVector asDimensionless(final DimensionlessUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DimensionlessUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDimensionlessVector", this.toString());
        FloatDimensionlessVector result = new FloatDimensionlessVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalcapacitance vector.
     * @return FloatElectricalCapacitanceVector; the current vector as a electricalcapacitance vector
     */
    public final FloatElectricalCapacitanceVector asElectricalCapacitance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCapacitanceVector", this.toString());
        return new FloatElectricalCapacitanceVector(this.data, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Return the current vector as a electricalcapacitance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatElectricalCapacitanceVector; the current vector as a electricalcapacitance vector
     */
    public final FloatElectricalCapacitanceVector asElectricalCapacitance(final ElectricalCapacitanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCapacitanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCapacitanceVector", this.toString());
        FloatElectricalCapacitanceVector result =
                new FloatElectricalCapacitanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalcharge vector.
     * @return FloatElectricalChargeVector; the current vector as a electricalcharge vector
     */
    public final FloatElectricalChargeVector asElectricalCharge()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalChargeVector", this.toString());
        return new FloatElectricalChargeVector(this.data, ElectricalChargeUnit.SI);
    }

    /**
     * Return the current vector as a electricalcharge vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatElectricalChargeVector; the current vector as a electricalcharge vector
     */
    public final FloatElectricalChargeVector asElectricalCharge(final ElectricalChargeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalChargeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalChargeVector", this.toString());
        FloatElectricalChargeVector result = new FloatElectricalChargeVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalconductance vector.
     * @return FloatElectricalConductanceVector; the current vector as a electricalconductance vector
     */
    public final FloatElectricalConductanceVector asElectricalConductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalConductanceVector", this.toString());
        return new FloatElectricalConductanceVector(this.data, ElectricalConductanceUnit.SI);
    }

    /**
     * Return the current vector as a electricalconductance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatElectricalConductanceVector; the current vector as a electricalconductance vector
     */
    public final FloatElectricalConductanceVector asElectricalConductance(final ElectricalConductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalConductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalConductanceVector", this.toString());
        FloatElectricalConductanceVector result =
                new FloatElectricalConductanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalcurrent vector.
     * @return FloatElectricalCurrentVector; the current vector as a electricalcurrent vector
     */
    public final FloatElectricalCurrentVector asElectricalCurrent()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCurrentVector", this.toString());
        return new FloatElectricalCurrentVector(this.data, ElectricalCurrentUnit.SI);
    }

    /**
     * Return the current vector as a electricalcurrent vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatElectricalCurrentVector; the current vector as a electricalcurrent vector
     */
    public final FloatElectricalCurrentVector asElectricalCurrent(final ElectricalCurrentUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalCurrentUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalCurrentVector", this.toString());
        FloatElectricalCurrentVector result = new FloatElectricalCurrentVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalinductance vector.
     * @return FloatElectricalInductanceVector; the current vector as a electricalinductance vector
     */
    public final FloatElectricalInductanceVector asElectricalInductance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalInductanceVector", this.toString());
        return new FloatElectricalInductanceVector(this.data, ElectricalInductanceUnit.SI);
    }

    /**
     * Return the current vector as a electricalinductance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatElectricalInductanceVector; the current vector as a electricalinductance vector
     */
    public final FloatElectricalInductanceVector asElectricalInductance(final ElectricalInductanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalInductanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalInductanceVector", this.toString());
        FloatElectricalInductanceVector result = new FloatElectricalInductanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalpotential vector.
     * @return FloatElectricalPotentialVector; the current vector as a electricalpotential vector
     */
    public final FloatElectricalPotentialVector asElectricalPotential()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalPotentialVector", this.toString());
        return new FloatElectricalPotentialVector(this.data, ElectricalPotentialUnit.SI);
    }

    /**
     * Return the current vector as a electricalpotential vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatElectricalPotentialVector; the current vector as a electricalpotential vector
     */
    public final FloatElectricalPotentialVector asElectricalPotential(final ElectricalPotentialUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalPotentialUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalPotentialVector", this.toString());
        FloatElectricalPotentialVector result = new FloatElectricalPotentialVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a electricalresistance vector.
     * @return FloatElectricalResistanceVector; the current vector as a electricalresistance vector
     */
    public final FloatElectricalResistanceVector asElectricalResistance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalResistanceVector", this.toString());
        return new FloatElectricalResistanceVector(this.data, ElectricalResistanceUnit.SI);
    }

    /**
     * Return the current vector as a electricalresistance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatElectricalResistanceVector; the current vector as a electricalresistance vector
     */
    public final FloatElectricalResistanceVector asElectricalResistance(final ElectricalResistanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ElectricalResistanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatElectricalResistanceVector", this.toString());
        FloatElectricalResistanceVector result = new FloatElectricalResistanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a energy vector.
     * @return FloatEnergyVector; the current vector as a energy vector
     */
    public final FloatEnergyVector asEnergy()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatEnergyVector", this.toString());
        return new FloatEnergyVector(this.data, EnergyUnit.SI);
    }

    /**
     * Return the current vector as a energy vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatEnergyVector; the current vector as a energy vector
     */
    public final FloatEnergyVector asEnergy(final EnergyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EnergyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatEnergyVector", this.toString());
        FloatEnergyVector result = new FloatEnergyVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a equivalentdose vector.
     * @return FloatEquivalentDoseVector; the current vector as a equivalentdose vector
     */
    public final FloatEquivalentDoseVector asEquivalentDose()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatEquivalentDoseVector", this.toString());
        return new FloatEquivalentDoseVector(this.data, EquivalentDoseUnit.SI);
    }

    /**
     * Return the current vector as a equivalentdose vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatEquivalentDoseVector; the current vector as a equivalentdose vector
     */
    public final FloatEquivalentDoseVector asEquivalentDose(final EquivalentDoseUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(EquivalentDoseUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatEquivalentDoseVector", this.toString());
        FloatEquivalentDoseVector result = new FloatEquivalentDoseVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a flowmass vector.
     * @return FloatFlowMassVector; the current vector as a flowmass vector
     */
    public final FloatFlowMassVector asFlowMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFlowMassVector", this.toString());
        return new FloatFlowMassVector(this.data, FlowMassUnit.SI);
    }

    /**
     * Return the current vector as a flowmass vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatFlowMassVector; the current vector as a flowmass vector
     */
    public final FloatFlowMassVector asFlowMass(final FlowMassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowMassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFlowMassVector", this.toString());
        FloatFlowMassVector result = new FloatFlowMassVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a flowvolume vector.
     * @return FloatFlowVolumeVector; the current vector as a flowvolume vector
     */
    public final FloatFlowVolumeVector asFlowVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFlowVolumeVector", this.toString());
        return new FloatFlowVolumeVector(this.data, FlowVolumeUnit.SI);
    }

    /**
     * Return the current vector as a flowvolume vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatFlowVolumeVector; the current vector as a flowvolume vector
     */
    public final FloatFlowVolumeVector asFlowVolume(final FlowVolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FlowVolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFlowVolumeVector", this.toString());
        FloatFlowVolumeVector result = new FloatFlowVolumeVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a force vector.
     * @return FloatForceVector; the current vector as a force vector
     */
    public final FloatForceVector asForce()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatForceVector", this.toString());
        return new FloatForceVector(this.data, ForceUnit.SI);
    }

    /**
     * Return the current vector as a force vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatForceVector; the current vector as a force vector
     */
    public final FloatForceVector asForce(final ForceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(ForceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatForceVector", this.toString());
        FloatForceVector result = new FloatForceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a frequency vector.
     * @return FloatFrequencyVector; the current vector as a frequency vector
     */
    public final FloatFrequencyVector asFrequency()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFrequencyVector", this.toString());
        return new FloatFrequencyVector(this.data, FrequencyUnit.SI);
    }

    /**
     * Return the current vector as a frequency vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatFrequencyVector; the current vector as a frequency vector
     */
    public final FloatFrequencyVector asFrequency(final FrequencyUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(FrequencyUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatFrequencyVector", this.toString());
        FloatFrequencyVector result = new FloatFrequencyVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a illuminance vector.
     * @return FloatIlluminanceVector; the current vector as a illuminance vector
     */
    public final FloatIlluminanceVector asIlluminance()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatIlluminanceVector", this.toString());
        return new FloatIlluminanceVector(this.data, IlluminanceUnit.SI);
    }

    /**
     * Return the current vector as a illuminance vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatIlluminanceVector; the current vector as a illuminance vector
     */
    public final FloatIlluminanceVector asIlluminance(final IlluminanceUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(IlluminanceUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatIlluminanceVector", this.toString());
        FloatIlluminanceVector result = new FloatIlluminanceVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a lineardensity vector.
     * @return FloatLinearDensityVector; the current vector as a lineardensity vector
     */
    public final FloatLinearDensityVector asLinearDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLinearDensityVector", this.toString());
        return new FloatLinearDensityVector(this.data, LinearDensityUnit.SI);
    }

    /**
     * Return the current vector as a lineardensity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatLinearDensityVector; the current vector as a lineardensity vector
     */
    public final FloatLinearDensityVector asLinearDensity(final LinearDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LinearDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLinearDensityVector", this.toString());
        FloatLinearDensityVector result = new FloatLinearDensityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a luminousflux vector.
     * @return FloatLuminousFluxVector; the current vector as a luminousflux vector
     */
    public final FloatLuminousFluxVector asLuminousFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLuminousFluxVector", this.toString());
        return new FloatLuminousFluxVector(this.data, LuminousFluxUnit.SI);
    }

    /**
     * Return the current vector as a luminousflux vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatLuminousFluxVector; the current vector as a luminousflux vector
     */
    public final FloatLuminousFluxVector asLuminousFlux(final LuminousFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLuminousFluxVector", this.toString());
        FloatLuminousFluxVector result = new FloatLuminousFluxVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a luminousintensity vector.
     * @return FloatLuminousIntensityVector; the current vector as a luminousintensity vector
     */
    public final FloatLuminousIntensityVector asLuminousIntensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLuminousIntensityVector", this.toString());
        return new FloatLuminousIntensityVector(this.data, LuminousIntensityUnit.SI);
    }

    /**
     * Return the current vector as a luminousintensity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatLuminousIntensityVector; the current vector as a luminousintensity vector
     */
    public final FloatLuminousIntensityVector asLuminousIntensity(final LuminousIntensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LuminousIntensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLuminousIntensityVector", this.toString());
        FloatLuminousIntensityVector result = new FloatLuminousIntensityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a magneticfluxdensity vector.
     * @return FloatMagneticFluxDensityVector; the current vector as a magneticfluxdensity vector
     */
    public final FloatMagneticFluxDensityVector asMagneticFluxDensity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMagneticFluxDensityVector", this.toString());
        return new FloatMagneticFluxDensityVector(this.data, MagneticFluxDensityUnit.SI);
    }

    /**
     * Return the current vector as a magneticfluxdensity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatMagneticFluxDensityVector; the current vector as a magneticfluxdensity vector
     */
    public final FloatMagneticFluxDensityVector asMagneticFluxDensity(final MagneticFluxDensityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxDensityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMagneticFluxDensityVector", this.toString());
        FloatMagneticFluxDensityVector result = new FloatMagneticFluxDensityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a magneticflux vector.
     * @return FloatMagneticFluxVector; the current vector as a magneticflux vector
     */
    public final FloatMagneticFluxVector asMagneticFlux()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMagneticFluxVector", this.toString());
        return new FloatMagneticFluxVector(this.data, MagneticFluxUnit.SI);
    }

    /**
     * Return the current vector as a magneticflux vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatMagneticFluxVector; the current vector as a magneticflux vector
     */
    public final FloatMagneticFluxVector asMagneticFlux(final MagneticFluxUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MagneticFluxUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMagneticFluxVector", this.toString());
        FloatMagneticFluxVector result = new FloatMagneticFluxVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a mass vector.
     * @return FloatMassVector; the current vector as a mass vector
     */
    public final FloatMassVector asMass()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMassVector", this.toString());
        return new FloatMassVector(this.data, MassUnit.SI);
    }

    /**
     * Return the current vector as a mass vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatMassVector; the current vector as a mass vector
     */
    public final FloatMassVector asMass(final MassUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MassUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMassVector", this.toString());
        FloatMassVector result = new FloatMassVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a momentum vector.
     * @return FloatMomentumVector; the current vector as a momentum vector
     */
    public final FloatMomentumVector asMomentum()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMomentumVector", this.toString());
        return new FloatMomentumVector(this.data, MomentumUnit.SI);
    }

    /**
     * Return the current vector as a momentum vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatMomentumVector; the current vector as a momentum vector
     */
    public final FloatMomentumVector asMomentum(final MomentumUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(MomentumUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatMomentumVector", this.toString());
        FloatMomentumVector result = new FloatMomentumVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a power vector.
     * @return FloatPowerVector; the current vector as a power vector
     */
    public final FloatPowerVector asPower()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatPowerVector", this.toString());
        return new FloatPowerVector(this.data, PowerUnit.SI);
    }

    /**
     * Return the current vector as a power vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatPowerVector; the current vector as a power vector
     */
    public final FloatPowerVector asPower(final PowerUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PowerUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatPowerVector", this.toString());
        FloatPowerVector result = new FloatPowerVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a pressure vector.
     * @return FloatPressureVector; the current vector as a pressure vector
     */
    public final FloatPressureVector asPressure()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatPressureVector", this.toString());
        return new FloatPressureVector(this.data, PressureUnit.SI);
    }

    /**
     * Return the current vector as a pressure vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatPressureVector; the current vector as a pressure vector
     */
    public final FloatPressureVector asPressure(final PressureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(PressureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatPressureVector", this.toString());
        FloatPressureVector result = new FloatPressureVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a radioactivity vector.
     * @return FloatRadioActivityVector; the current vector as a radioactivity vector
     */
    public final FloatRadioActivityVector asRadioActivity()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatRadioActivityVector", this.toString());
        return new FloatRadioActivityVector(this.data, RadioActivityUnit.SI);
    }

    /**
     * Return the current vector as a radioactivity vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatRadioActivityVector; the current vector as a radioactivity vector
     */
    public final FloatRadioActivityVector asRadioActivity(final RadioActivityUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(RadioActivityUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatRadioActivityVector", this.toString());
        FloatRadioActivityVector result = new FloatRadioActivityVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a solidangle vector.
     * @return FloatSolidAngleVector; the current vector as a solidangle vector
     */
    public final FloatSolidAngleVector asSolidAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatSolidAngleVector", this.toString());
        return new FloatSolidAngleVector(this.data, SolidAngleUnit.SI);
    }

    /**
     * Return the current vector as a solidangle vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatSolidAngleVector; the current vector as a solidangle vector
     */
    public final FloatSolidAngleVector asSolidAngle(final SolidAngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SolidAngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatSolidAngleVector", this.toString());
        FloatSolidAngleVector result = new FloatSolidAngleVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a speed vector.
     * @return FloatSpeedVector; the current vector as a speed vector
     */
    public final FloatSpeedVector asSpeed()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatSpeedVector", this.toString());
        return new FloatSpeedVector(this.data, SpeedUnit.SI);
    }

    /**
     * Return the current vector as a speed vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatSpeedVector; the current vector as a speed vector
     */
    public final FloatSpeedVector asSpeed(final SpeedUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(SpeedUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatSpeedVector", this.toString());
        FloatSpeedVector result = new FloatSpeedVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a torque vector.
     * @return FloatTorqueVector; the current vector as a torque vector
     */
    public final FloatTorqueVector asTorque()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatTorqueVector", this.toString());
        return new FloatTorqueVector(this.data, TorqueUnit.SI);
    }

    /**
     * Return the current vector as a torque vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatTorqueVector; the current vector as a torque vector
     */
    public final FloatTorqueVector asTorque(final TorqueUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TorqueUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatTorqueVector", this.toString());
        FloatTorqueVector result = new FloatTorqueVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a volume vector.
     * @return FloatVolumeVector; the current vector as a volume vector
     */
    public final FloatVolumeVector asVolume()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatVolumeVector", this.toString());
        return new FloatVolumeVector(this.data, VolumeUnit.SI);
    }

    /**
     * Return the current vector as a volume vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatVolumeVector; the current vector as a volume vector
     */
    public final FloatVolumeVector asVolume(final VolumeUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(VolumeUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatVolumeVector", this.toString());
        FloatVolumeVector result = new FloatVolumeVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a angle vector.
     * @return FloatAngleVector; the current vector as a angle vector
     */
    public final FloatAngleVector asAngle()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngleVector", this.toString());
        return new FloatAngleVector(this.data, AngleUnit.SI);
    }

    /**
     * Return the current vector as a angle vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatAngleVector; the current vector as a angle vector
     */
    public final FloatAngleVector asAngle(final AngleUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(AngleUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatAngleVector", this.toString());
        FloatAngleVector result = new FloatAngleVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a length vector.
     * @return FloatLengthVector; the current vector as a length vector
     */
    public final FloatLengthVector asLength()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLengthVector", this.toString());
        return new FloatLengthVector(this.data, LengthUnit.SI);
    }

    /**
     * Return the current vector as a length vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatLengthVector; the current vector as a length vector
     */
    public final FloatLengthVector asLength(final LengthUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(LengthUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatLengthVector", this.toString());
        FloatLengthVector result = new FloatLengthVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a temperature vector.
     * @return FloatTemperatureVector; the current vector as a temperature vector
     */
    public final FloatTemperatureVector asTemperature()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatTemperatureVector", this.toString());
        return new FloatTemperatureVector(this.data, TemperatureUnit.SI);
    }

    /**
     * Return the current vector as a temperature vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatTemperatureVector; the current vector as a temperature vector
     */
    public final FloatTemperatureVector asTemperature(final TemperatureUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(TemperatureUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatTemperatureVector", this.toString());
        FloatTemperatureVector result = new FloatTemperatureVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Return the current vector as a duration vector.
     * @return FloatDurationVector; the current vector as a duration vector
     */
    public final FloatDurationVector asDuration()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDurationVector", this.toString());
        return new FloatDurationVector(this.data, DurationUnit.SI);
    }

    /**
     * Return the current vector as a duration vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return FloatDurationVector; the current vector as a duration vector
     */
    public final FloatDurationVector asDuration(final DurationUnit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(DurationUnit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to FloatDurationVector", this.toString());
        FloatDurationVector result = new FloatDurationVector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
