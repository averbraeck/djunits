package org.djunits.old.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.old.unit.DensityUnit;
import org.djunits.old.unit.scale.IdentityScale;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vfloat.scalar.FloatDensity;
import org.djunits.old.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.old.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatDensityVector, a vector of values with a DensityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatDensityVector extends FloatVectorRel<DensityUnit, FloatDensity, FloatDensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatDensityVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatDensityVector(final FloatVectorData data, final DensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatDensityVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDensityVector(final float[] data, final DensityUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatDensityVector from a float[] object. The Float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatDensityVector(final float[] data, final DensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDensityVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDensityVector(final float[] data, final StorageType storageType)
    {
        this(data, DensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatDensityVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatDensityVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatDensity[] */

    /**
     * Construct a FloatDensityVector from an array of FloatDensity objects. The FloatDensity values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDensityVector(final FloatDensity[] data, final DensityUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatDensityVector from an array of FloatDensity objects. The FloatDensity values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatDensityVector(final FloatDensity[] data, final DensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDensityVector from an array of FloatDensity objects. The FloatDensity values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data
     * as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDensityVector(final FloatDensity[] data, final StorageType storageType)
    {
        this(data, DensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatDensityVector from an array of FloatDensity objects. The FloatDensity values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatDensityVector(final FloatDensity[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Density> */

    /**
     * Construct a FloatDensityVector from a list of Number objects or a list of FloatDensity objects. Note that the displayUnit
     * has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatDensity
     * objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are
     * expressed, as well as the unit in which they will be printed. In case the list contains FloatDensity objects, each
     * FloatDensity has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Density&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDensityVector(final List<? extends Number> data, final DensityUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatDensity ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatDensityVector from a list of Number objects or a list of FloatDensity objects. Note that the displayUnit
     * has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatDensity
     * objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are
     * expressed, as well as the unit in which they will be printed. In case the list contains FloatDensity objects, each
     * FloatDensity has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Density&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatDensityVector(final List<? extends Number> data, final DensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDensityVector from a list of Number objects or a list of FloatDensity objects. When data contains
     * numbers such as Float, assume that they are expressed using SI units. When the data consists of FloatDensity objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Density&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDensityVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, DensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatDensityVector from a list of Number objects or a list of FloatDensity objects. When data contains
     * numbers such as Float, assume that they are expressed using SI units. When the data consists of FloatDensity objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Density&gt;
     */
    public FloatDensityVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatDensity> */

    /**
     * Construct a FloatDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatDensity objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatDensity objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatDensity objects, each FloatDensity has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatDensity&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDensityVector(final Map<Integer, ? extends Number> data, final int size, final DensityUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatDensity
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatDensity objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatDensity objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatDensity objects, each FloatDensity has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we
     * offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatDensity&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatDensityVector(final Map<Integer, ? extends Number> data, final int size, final DensityUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatDensity objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatDensity objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatDensity&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDensityVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, DensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatDensity objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatDensity objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE
     * since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatDensity&gt;
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatDensityVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatDensity> getScalarClass()
    {
        return FloatDensity.class;
    }

    @Override
    public FloatDensityVector instantiateVector(final FloatVectorData fvd, final DensityUnit displayUnit)
    {
        return new FloatDensityVector(fvd, displayUnit);
    }

    @Override
    public FloatDensity instantiateScalarSI(final float valueSI, final DensityUnit displayUnit)
    {
        FloatDensity result = FloatDensity.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
