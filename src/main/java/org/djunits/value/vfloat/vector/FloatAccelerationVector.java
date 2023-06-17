package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.SortedMap;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatAcceleration;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatAccelerationVector, a vector of values with a AccelerationUnit. 
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-17T20:24:57.123282Z")
public class FloatAccelerationVector extends AbstractFloatVectorRel<AccelerationUnit, FloatAcceleration, FloatAccelerationVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatAccelerationVector from an internal data object.
     * @param data FloatVectorData; the internal data object for the vector
     * @param displayUnit AccelerationUnit; the display unit of the vector data
     */
    public FloatAccelerationVector(final FloatVectorData data, final AccelerationUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatAccelerationVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit AccelerationUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAccelerationVector(final float[] data, final AccelerationUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatAccelerationVector from a float[] object. The Float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data float[]; the data for the vector
     * @param displayUnit AccelerationUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatAccelerationVector(final float[] data, final AccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAccelerationVector from a float[] object with SI-unit values.
     * @param data float[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAccelerationVector(final float[] data, final StorageType storageType)
    {
        this(data, AccelerationUnit.SI, storageType);
    }

    /**
     * Construct a FloatAccelerationVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data float[]; the data for the vector, in SI units
     */
    public FloatAccelerationVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatAcceleration[] */

    /**
     * Construct a FloatAccelerationVector from an array of FloatAcceleration objects. The FloatAcceleration values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatAcceleration[]; the data for the vector
     * @param displayUnit AccelerationUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAccelerationVector(final FloatAcceleration[] data, final AccelerationUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatAccelerationVector from an array of FloatAcceleration objects. The FloatAcceleration values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data FloatAcceleration[]; the data for the vector
     * @param displayUnit AccelerationUnit; the display unit of the values when printing
     */
    public FloatAccelerationVector(final FloatAcceleration[] data, final AccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAccelerationVector from an array of FloatAcceleration objects. The FloatAcceleration values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as
     * an array.
     * @param data FloatAcceleration[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAccelerationVector(final FloatAcceleration[] data, final StorageType storageType)
    {
        this(data, AccelerationUnit.SI, storageType);
    }

    /**
     * Construct a FloatAccelerationVector from an array of FloatAcceleration objects. The FloatAcceleration values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array.
     * @param data FloatAcceleration[]; the data for the vector
     */
    public FloatAccelerationVector(final FloatAcceleration[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Acceleration> */

    /**
     * Construct a FloatAccelerationVector from a list of Number objects or a list of FloatAcceleration objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatAcceleration objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains FloatAcceleration objects, each FloatAcceleration has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;Acceleration&gt;; the data for the vector
     * @param displayUnit AccelerationUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAccelerationVector(final List<? extends Number> data, final AccelerationUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatAcceleration ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatAccelerationVector from a list of Number objects or a list of FloatAcceleration objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatAcceleration objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains FloatAcceleration objects, each FloatAcceleration has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we
     * offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;Acceleration&gt;; the data for the vector
     * @param displayUnit AccelerationUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatAccelerationVector(final List<? extends Number> data, final AccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAccelerationVector from a list of Number objects or a list of FloatAcceleration objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatAcceleration objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;Acceleration&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAccelerationVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, AccelerationUnit.SI, storageType);
    }

    /**
     * Construct a FloatAccelerationVector from a list of Number objects or a list of FloatAcceleration objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatAcceleration objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE
     * since we offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;Acceleration&gt;; the data for the vector
     */
    public FloatAccelerationVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH SortedMap<Integer, Float> or SortedMap<Integer, FloatAcceleration> */

    /**
     * Construct a FloatAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatAcceleration objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size
     * of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different
     * meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatAcceleration objects. In case the map
     * contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the
     * unit in which they will be printed. In case the map contains FloatAcceleration objects, each FloatAcceleration has its own unit, and the
     * displayUnit is just used for printing. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Float&gt; or SortedMap&lt;Integer, FloatAcceleration&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AccelerationUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAccelerationVector(final SortedMap<Integer, ? extends Number> data, final int size, final AccelerationUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.get(data.firstKey()) instanceof FloatAcceleration
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatAcceleration objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size
     * of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different
     * meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatAcceleration objects. In case the map
     * contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the
     * unit in which they will be printed. In case the map contains FloatAcceleration objects, each FloatAcceleration has its own unit, and the
     * displayUnit is just used for printing. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as
     * a Map.
     * @param data SortedMap&lt;Integer, Float&gt; or SortedMap&lt;Integer, FloatAcceleration&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AccelerationUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatAccelerationVector(final SortedMap<Integer, ? extends Number> data, final int size, final AccelerationUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatAcceleration objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size
     * of the vector, since the largest index does not have to be part of the map. When data contains numbers such as Float,
     * assume that they are expressed using SI units. When the data consists of FloatAcceleration objects, they each have their own
     * unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Float&gt; or SortedMap&lt;Integer, FloatAcceleration&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAccelerationVector(final SortedMap<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, AccelerationUnit.SI, storageType);
    }

    /**
     * Construct a FloatAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatAcceleration objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size
     * of the vector, since the largest index does not have to be part of the map. When data contains numbers such as Float,
     * assume that they are expressed using SI units. When the data consists of FloatAcceleration objects, they each have their own
     * unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we
     * offer the data as a Map.
     * @param data SortedMap&lt;Integer, Float&gt; or SortedMap&lt;Integer, FloatAcceleration&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public FloatAccelerationVector(final SortedMap<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<FloatAcceleration> getScalarClass()
    {
        return FloatAcceleration.class;
    }
        
    /** {@inheritDoc} */
    @Override
    public FloatAccelerationVector instantiateVector(final FloatVectorData fvd, final AccelerationUnit displayUnit)
    {
        return new FloatAccelerationVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAcceleration instantiateScalarSI(final float valueSI, final AccelerationUnit displayUnit)
    {
        FloatAcceleration result = FloatAcceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

   
}

