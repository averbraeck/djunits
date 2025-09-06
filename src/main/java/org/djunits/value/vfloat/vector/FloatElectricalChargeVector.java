package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatElectricalCharge;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatElectricalChargeVector, a vector of values with a ElectricalChargeUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatElectricalChargeVector
        extends FloatVectorRel<ElectricalChargeUnit, FloatElectricalCharge, FloatElectricalChargeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatElectricalChargeVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatElectricalChargeVector(final FloatVectorData data, final ElectricalChargeUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatElectricalChargeVector from a float[] object. The Float values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalChargeVector(final float[] data, final ElectricalChargeUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalChargeVector from a float[] object. The Float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatElectricalChargeVector(final float[] data, final ElectricalChargeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalChargeVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalChargeVector(final float[] data, final StorageType storageType)
    {
        this(data, ElectricalChargeUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalChargeVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatElectricalChargeVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatElectricalCharge[] */

    /**
     * Construct a FloatElectricalChargeVector from an array of FloatElectricalCharge objects. The FloatElectricalCharge values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalChargeVector(final FloatElectricalCharge[] data, final ElectricalChargeUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalChargeVector from an array of FloatElectricalCharge objects. The FloatElectricalCharge values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatElectricalChargeVector(final FloatElectricalCharge[] data, final ElectricalChargeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalChargeVector from an array of FloatElectricalCharge objects. The FloatElectricalCharge values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalChargeVector(final FloatElectricalCharge[] data, final StorageType storageType)
    {
        this(data, ElectricalChargeUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalChargeVector from an array of FloatElectricalCharge objects. The FloatElectricalCharge values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatElectricalChargeVector(final FloatElectricalCharge[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<ElectricalCharge> */

    /**
     * Construct a FloatElectricalChargeVector from a list of Number objects or a list of FloatElectricalCharge objects. Note
     * that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects)
     * or FloatElectricalCharge objects. In case the list contains Number objects, the displayUnit indicates the unit in which
     * the values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * FloatElectricalCharge objects, each FloatElectricalCharge has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;ElectricalCharge&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalChargeVector(final List<? extends Number> data, final ElectricalChargeUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatElectricalCharge
                        ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatElectricalChargeVector from a list of Number objects or a list of FloatElectricalCharge objects. Note
     * that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects)
     * or FloatElectricalCharge objects. In case the list contains Number objects, the displayUnit indicates the unit in which
     * the values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * FloatElectricalCharge objects, each FloatElectricalCharge has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;ElectricalCharge&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatElectricalChargeVector(final List<? extends Number> data, final ElectricalChargeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalChargeVector from a list of Number objects or a list of FloatElectricalCharge objects. When
     * data contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatElectricalCharge objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;ElectricalCharge&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalChargeVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, ElectricalChargeUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalChargeVector from a list of Number objects or a list of FloatElectricalCharge objects. When
     * data contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatElectricalCharge objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;ElectricalCharge&gt;
     */
    public FloatElectricalChargeVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatElectricalCharge> */

    /**
     * Construct a FloatElectricalChargeVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatElectricalCharge objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatElectricalCharge objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatElectricalCharge objects, each FloatElectricalCharge has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatElectricalCharge&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalChargeVector(final Map<Integer, ? extends Number> data, final int size,
            final ElectricalChargeUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatElectricalCharge
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatElectricalChargeVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatElectricalCharge objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatElectricalCharge objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatElectricalCharge objects, each FloatElectricalCharge has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatElectricalCharge&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatElectricalChargeVector(final Map<Integer, ? extends Number> data, final int size,
            final ElectricalChargeUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatElectricalChargeVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatElectricalCharge objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatElectricalCharge objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatElectricalCharge&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalChargeVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, ElectricalChargeUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalChargeVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatElectricalCharge objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatElectricalCharge objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatElectricalCharge&gt;
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatElectricalChargeVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatElectricalCharge> getScalarClass()
    {
        return FloatElectricalCharge.class;
    }

    @Override
    public FloatElectricalChargeVector instantiateVector(final FloatVectorData fvd, final ElectricalChargeUnit displayUnit)
    {
        return new FloatElectricalChargeVector(fvd, displayUnit);
    }

    @Override
    public FloatElectricalCharge instantiateScalarSI(final float valueSI, final ElectricalChargeUnit displayUnit)
    {
        FloatElectricalCharge result = FloatElectricalCharge.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
