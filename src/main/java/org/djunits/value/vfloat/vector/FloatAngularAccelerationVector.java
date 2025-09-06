package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatAngularAcceleration;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatAngularAccelerationVector, a vector of values with a AngularAccelerationUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatAngularAccelerationVector
        extends FloatVectorRel<AngularAccelerationUnit, FloatAngularAcceleration, FloatAngularAccelerationVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatAngularAccelerationVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatAngularAccelerationVector(final FloatVectorData data, final AngularAccelerationUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatAngularAccelerationVector from a float[] object. The Float values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngularAccelerationVector(final float[] data, final AngularAccelerationUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a float[] object. The Float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatAngularAccelerationVector(final float[] data, final AngularAccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngularAccelerationVector(final float[] data, final StorageType storageType)
    {
        this(data, AngularAccelerationUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a float[] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatAngularAccelerationVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatAngularAcceleration[] */

    /**
     * Construct a FloatAngularAccelerationVector from an array of FloatAngularAcceleration objects. The
     * FloatAngularAcceleration values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngularAccelerationVector(final FloatAngularAcceleration[] data, final AngularAccelerationUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatAngularAccelerationVector from an array of FloatAngularAcceleration objects. The
     * FloatAngularAcceleration values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatAngularAccelerationVector(final FloatAngularAcceleration[] data, final AngularAccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAngularAccelerationVector from an array of FloatAngularAcceleration objects. The
     * FloatAngularAcceleration values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngularAccelerationVector(final FloatAngularAcceleration[] data, final StorageType storageType)
    {
        this(data, AngularAccelerationUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngularAccelerationVector from an array of FloatAngularAcceleration objects. The
     * FloatAngularAcceleration values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatAngularAccelerationVector(final FloatAngularAcceleration[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<AngularAcceleration> */

    /**
     * Construct a FloatAngularAccelerationVector from a list of Number objects or a list of FloatAngularAcceleration objects.
     * Note that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float
     * objects) or FloatAngularAcceleration objects. In case the list contains Number objects, the displayUnit indicates the
     * unit in which the values in the list are expressed, as well as the unit in which they will be printed. In case the list
     * contains FloatAngularAcceleration objects, each FloatAngularAcceleration has its own unit, and the displayUnit is just
     * used for printing. The values but will always be internally stored as SI values or base values, and expressed using the
     * display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;AngularAcceleration&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngularAccelerationVector(final List<? extends Number> data, final AngularAccelerationUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatAngularAcceleration
                        ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a list of Number objects or a list of FloatAngularAcceleration objects.
     * Note that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float
     * objects) or FloatAngularAcceleration objects. In case the list contains Number objects, the displayUnit indicates the
     * unit in which the values in the list are expressed, as well as the unit in which they will be printed. In case the list
     * contains FloatAngularAcceleration objects, each FloatAngularAcceleration has its own unit, and the displayUnit is just
     * used for printing. The values but will always be internally stored as SI values or base values, and expressed using the
     * display unit or base unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;AngularAcceleration&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatAngularAccelerationVector(final List<? extends Number> data, final AngularAccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a list of Number objects or a list of FloatAngularAcceleration objects.
     * When data contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatAngularAcceleration objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;AngularAcceleration&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngularAccelerationVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, AngularAccelerationUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a list of Number objects or a list of FloatAngularAcceleration objects.
     * When data contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatAngularAcceleration objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;AngularAcceleration&gt;
     */
    public FloatAngularAccelerationVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatAngularAcceleration> */

    /**
     * Construct a FloatAngularAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatAngularAcceleration objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that
     * the displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatAngularAcceleration objects. In case the map contains Number objects, the displayUnit indicates the unit in which
     * the values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatAngularAcceleration objects, each FloatAngularAcceleration has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatAngularAcceleration&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngularAccelerationVector(final Map<Integer, ? extends Number> data, final int size,
            final AngularAccelerationUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatAngularAcceleration
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatAngularAcceleration objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that
     * the displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatAngularAcceleration objects. In case the map contains Number objects, the displayUnit indicates the unit in which
     * the values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatAngularAcceleration objects, each FloatAngularAcceleration has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatAngularAcceleration&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatAngularAccelerationVector(final Map<Integer, ? extends Number> data, final int size,
            final AngularAccelerationUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatAngularAcceleration objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatAngularAcceleration objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatAngularAcceleration&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngularAccelerationVector(final Map<Integer, ? extends Number> data, final int size,
            final StorageType storageType)
    {
        this(data, size, AngularAccelerationUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngularAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatAngularAcceleration objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatAngularAcceleration objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatAngularAcceleration&gt;
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatAngularAccelerationVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatAngularAcceleration> getScalarClass()
    {
        return FloatAngularAcceleration.class;
    }

    @Override
    public FloatAngularAccelerationVector instantiateVector(final FloatVectorData fvd,
            final AngularAccelerationUnit displayUnit)
    {
        return new FloatAngularAccelerationVector(fvd, displayUnit);
    }

    @Override
    public FloatAngularAcceleration instantiateScalarSI(final float valueSI, final AngularAccelerationUnit displayUnit)
    {
        FloatAngularAcceleration result = FloatAngularAcceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
