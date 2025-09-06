package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.VolumeUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatVolume;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatVolumeVector, a vector of values with a VolumeUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatVolumeVector extends FloatVectorRel<VolumeUnit, FloatVolume, FloatVolumeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatVolumeVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatVolumeVector(final FloatVectorData data, final VolumeUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatVolumeVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatVolumeVector(final float[] data, final VolumeUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatVolumeVector from a float[] object. The Float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatVolumeVector(final float[] data, final VolumeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatVolumeVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatVolumeVector(final float[] data, final StorageType storageType)
    {
        this(data, VolumeUnit.SI, storageType);
    }

    /**
     * Construct a FloatVolumeVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatVolumeVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatVolume[] */

    /**
     * Construct a FloatVolumeVector from an array of FloatVolume objects. The FloatVolume values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatVolumeVector(final FloatVolume[] data, final VolumeUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatVolumeVector from an array of FloatVolume objects. The FloatVolume values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatVolumeVector(final FloatVolume[] data, final VolumeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatVolumeVector from an array of FloatVolume objects. The FloatVolume values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data
     * as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatVolumeVector(final FloatVolume[] data, final StorageType storageType)
    {
        this(data, VolumeUnit.SI, storageType);
    }

    /**
     * Construct a FloatVolumeVector from an array of FloatVolume objects. The FloatVolume values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatVolumeVector(final FloatVolume[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Volume> */

    /**
     * Construct a FloatVolumeVector from a list of Number objects or a list of FloatVolume objects. Note that the displayUnit
     * has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatVolume
     * objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are
     * expressed, as well as the unit in which they will be printed. In case the list contains FloatVolume objects, each
     * FloatVolume has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Volume&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatVolumeVector(final List<? extends Number> data, final VolumeUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatVolume ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatVolumeVector from a list of Number objects or a list of FloatVolume objects. Note that the displayUnit
     * has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatVolume
     * objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are
     * expressed, as well as the unit in which they will be printed. In case the list contains FloatVolume objects, each
     * FloatVolume has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Volume&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatVolumeVector(final List<? extends Number> data, final VolumeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatVolumeVector from a list of Number objects or a list of FloatVolume objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatVolume objects, they each
     * have their own unit, but will be printed using SI units or base units. The values but will always be internally stored as
     * SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Volume&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatVolumeVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, VolumeUnit.SI, storageType);
    }

    /**
     * Construct a FloatVolumeVector from a list of Number objects or a list of FloatVolume objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatVolume objects, they each
     * have their own unit, but will be printed using SI units or base units. The values but will always be internally stored as
     * SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is
     * DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Volume&gt;
     */
    public FloatVolumeVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatVolume> */

    /**
     * Construct a FloatVolumeVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatVolume objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatVolume objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatVolume objects, each FloatVolume has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatVolume&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatVolumeVector(final Map<Integer, ? extends Number> data, final int size, final VolumeUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatVolume
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatVolumeVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatVolume objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatVolume objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatVolume objects, each FloatVolume has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we
     * offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatVolume&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatVolumeVector(final Map<Integer, ? extends Number> data, final int size, final VolumeUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatVolumeVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatVolume objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatVolume objects, they each have their
     * own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatVolume&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatVolumeVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, VolumeUnit.SI, storageType);
    }

    /**
     * Construct a FloatVolumeVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatVolume objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatVolume objects, they each have their
     * own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since
     * we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatVolume&gt;
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatVolumeVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatVolume> getScalarClass()
    {
        return FloatVolume.class;
    }

    @Override
    public FloatVolumeVector instantiateVector(final FloatVectorData fvd, final VolumeUnit displayUnit)
    {
        return new FloatVolumeVector(fvd, displayUnit);
    }

    @Override
    public FloatVolume instantiateScalarSI(final float valueSI, final VolumeUnit displayUnit)
    {
        FloatVolume result = FloatVolume.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
