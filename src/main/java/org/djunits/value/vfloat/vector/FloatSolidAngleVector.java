package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.SolidAngleUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatSolidAngle;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatSolidAngleVector, a vector of values with a SolidAngleUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatSolidAngleVector extends FloatVectorRel<SolidAngleUnit, FloatSolidAngle, FloatSolidAngleVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatSolidAngleVector from an internal data object.
     * @param data FloatVectorData; the internal data object for the vector
     * @param displayUnit SolidAngleUnit; the display unit of the vector data
     */
    public FloatSolidAngleVector(final FloatVectorData data, final SolidAngleUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatSolidAngleVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit SolidAngleUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSolidAngleVector(final float[] data, final SolidAngleUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatSolidAngleVector from a float[] object. The Float values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data float[]; the data for the vector
     * @param displayUnit SolidAngleUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatSolidAngleVector(final float[] data, final SolidAngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatSolidAngleVector from a float[] object with SI-unit values.
     * @param data float[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSolidAngleVector(final float[] data, final StorageType storageType)
    {
        this(data, SolidAngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatSolidAngleVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array.
     * @param data float[]; the data for the vector, in SI units
     */
    public FloatSolidAngleVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatSolidAngle[] */

    /**
     * Construct a FloatSolidAngleVector from an array of FloatSolidAngle objects. The FloatSolidAngle values are each expressed
     * in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatSolidAngle[]; the data for the vector
     * @param displayUnit SolidAngleUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSolidAngleVector(final FloatSolidAngle[] data, final SolidAngleUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatSolidAngleVector from an array of FloatSolidAngle objects. The FloatSolidAngle values are each expressed
     * in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume
     * that the StorageType is DENSE since we offer the data as an array.
     * @param data FloatSolidAngle[]; the data for the vector
     * @param displayUnit SolidAngleUnit; the display unit of the values when printing
     */
    public FloatSolidAngleVector(final FloatSolidAngle[] data, final SolidAngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatSolidAngleVector from an array of FloatSolidAngle objects. The FloatSolidAngle values are each expressed
     * in their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array.
     * @param data FloatSolidAngle[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSolidAngleVector(final FloatSolidAngle[] data, final StorageType storageType)
    {
        this(data, SolidAngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatSolidAngleVector from an array of FloatSolidAngle objects. The FloatSolidAngle values are each expressed
     * in their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data FloatSolidAngle[]; the data for the vector
     */
    public FloatSolidAngleVector(final FloatSolidAngle[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<SolidAngle> */

    /**
     * Construct a FloatSolidAngleVector from a list of Number objects or a list of FloatSolidAngle objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or
     * FloatSolidAngle objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains FloatSolidAngle
     * objects, each FloatSolidAngle has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;SolidAngle&gt;; the data for the vector
     * @param displayUnit SolidAngleUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSolidAngleVector(final List<? extends Number> data, final SolidAngleUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatSolidAngle ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatSolidAngleVector from a list of Number objects or a list of FloatSolidAngle objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or
     * FloatSolidAngle objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains FloatSolidAngle
     * objects, each FloatSolidAngle has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;SolidAngle&gt;; the data for the vector
     * @param displayUnit SolidAngleUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatSolidAngleVector(final List<? extends Number> data, final SolidAngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatSolidAngleVector from a list of Number objects or a list of FloatSolidAngle objects. When data contains
     * numbers such as Float, assume that they are expressed using SI units. When the data consists of FloatSolidAngle objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;SolidAngle&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSolidAngleVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, SolidAngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatSolidAngleVector from a list of Number objects or a list of FloatSolidAngle objects. When data contains
     * numbers such as Float, assume that they are expressed using SI units. When the data consists of FloatSolidAngle objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;SolidAngle&gt;; the data for the vector
     */
    public FloatSolidAngleVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatSolidAngle> */

    /**
     * Construct a FloatSolidAngleVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of FloatSolidAngle objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatSolidAngle objects.
     * In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed,
     * as well as the unit in which they will be printed. In case the map contains FloatSolidAngle objects, each FloatSolidAngle
     * has its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSolidAngle&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit SolidAngleUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSolidAngleVector(final Map<Integer, ? extends Number> data, final int size, final SolidAngleUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatSolidAngle
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatSolidAngleVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of FloatSolidAngle objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatSolidAngle objects.
     * In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed,
     * as well as the unit in which they will be printed. In case the map contains FloatSolidAngle objects, each FloatSolidAngle
     * has its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE
     * since we offer the data as a Map.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSolidAngle&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit SolidAngleUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatSolidAngleVector(final Map<Integer, ? extends Number> data, final int size, final SolidAngleUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatSolidAngleVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of FloatSolidAngle objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatSolidAngle objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSolidAngle&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSolidAngleVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, SolidAngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatSolidAngleVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of FloatSolidAngle objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatSolidAngle objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE
     * since we offer the data as a Map.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSolidAngle&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public FloatSolidAngleVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<FloatSolidAngle> getScalarClass()
    {
        return FloatSolidAngle.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatSolidAngleVector instantiateVector(final FloatVectorData fvd, final SolidAngleUnit displayUnit)
    {
        return new FloatSolidAngleVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSolidAngle instantiateScalarSI(final float valueSI, final SolidAngleUnit displayUnit)
    {
        FloatSolidAngle result = FloatSolidAngle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
