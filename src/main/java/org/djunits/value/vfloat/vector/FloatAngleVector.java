package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.SortedMap;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatAngle;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.vector.base.FloatVectorRelWithAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Relative FloatAngle Vector.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-17T20:24:57.123282Z")
public class FloatAngleVector extends FloatVectorRelWithAbs<DirectionUnit, FloatDirection, FloatDirectionVector,
    AngleUnit, FloatAngle, FloatAngleVector>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a FloatAngleVector from an internal data object.
     * @param data FloatVectorData; the internal data object for the vector
     * @param displayUnit AngleUnit; the display unit of the vector data
     */
    public FloatAngleVector(final FloatVectorData data, final AngleUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatAngleVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit AngleUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngleVector(final float[] data, final AngleUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatAngleVector from a float[] object. The Float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data float[]; the data for the vector
     * @param displayUnit AngleUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatAngleVector(final float[] data, final AngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAngleVector from a float[] object with SI-unit values.
     * @param data float[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngleVector(final float[] data, final StorageType storageType)
    {
        this(data, AngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngleVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data float[]; the data for the vector, in SI units
     */
    public FloatAngleVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatAngle[] */

    /**
     * Construct a FloatAngleVector from an array of FloatAngle objects. The FloatAngle values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatAngle[]; the data for the vector
     * @param displayUnit AngleUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngleVector(final FloatAngle[] data, final AngleUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatAngleVector from an array of FloatAngle objects. The FloatAngle values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data FloatAngle[]; the data for the vector
     * @param displayUnit AngleUnit; the display unit of the values when printing
     */
    public FloatAngleVector(final FloatAngle[] data, final AngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAngleVector from an array of FloatAngle objects. The FloatAngle values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as
     * an array.
     * @param data FloatAngle[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngleVector(final FloatAngle[] data, final StorageType storageType)
    {
        this(data, AngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngleVector from an array of FloatAngle objects. The FloatAngle values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array.
     * @param data FloatAngle[]; the data for the vector
     */
    public FloatAngleVector(final FloatAngle[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Angle> */

    /**
     * Construct a FloatAngleVector from a list of Number objects or a list of FloatAngle objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatAngle objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains FloatAngle objects, each FloatAngle has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;Angle&gt;; the data for the vector
     * @param displayUnit AngleUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngleVector(final List<? extends Number> data, final AngleUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatAngle ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatAngleVector from a list of Number objects or a list of FloatAngle objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatAngle objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains FloatAngle objects, each FloatAngle has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we
     * offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;Angle&gt;; the data for the vector
     * @param displayUnit AngleUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatAngleVector(final List<? extends Number> data, final AngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAngleVector from a list of Number objects or a list of FloatAngle objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatAngle objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;Angle&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngleVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, AngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngleVector from a list of Number objects or a list of FloatAngle objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatAngle objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE
     * since we offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;Angle&gt;; the data for the vector
     */
    public FloatAngleVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH SortedMap<Integer, Float> or SortedMap<Integer, FloatAngle> */

    /**
     * Construct a FloatAngleVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatAngle objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size
     * of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different
     * meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatAngle objects. In case the map
     * contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the
     * unit in which they will be printed. In case the map contains FloatAngle objects, each FloatAngle has its own unit, and the
     * displayUnit is just used for printing. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Float&gt; or SortedMap&lt;Integer, FloatAngle&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AngleUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngleVector(final SortedMap<Integer, ? extends Number> data, final int size, final AngleUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.get(data.firstKey()) instanceof FloatAngle
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatAngleVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatAngle objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size
     * of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different
     * meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatAngle objects. In case the map
     * contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the
     * unit in which they will be printed. In case the map contains FloatAngle objects, each FloatAngle has its own unit, and the
     * displayUnit is just used for printing. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as
     * a Map.
     * @param data SortedMap&lt;Integer, Float&gt; or SortedMap&lt;Integer, FloatAngle&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AngleUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatAngleVector(final SortedMap<Integer, ? extends Number> data, final int size, final AngleUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatAngleVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatAngle objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size
     * of the vector, since the largest index does not have to be part of the map. When data contains numbers such as Float,
     * assume that they are expressed using SI units. When the data consists of FloatAngle objects, they each have their own
     * unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Float&gt; or SortedMap&lt;Integer, FloatAngle&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAngleVector(final SortedMap<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, AngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngleVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatAngle objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size
     * of the vector, since the largest index does not have to be part of the map. When data contains numbers such as Float,
     * assume that they are expressed using SI units. When the data consists of FloatAngle objects, they each have their own
     * unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we
     * offer the data as a Map.
     * @param data SortedMap&lt;Integer, Float&gt; or SortedMap&lt;Integer, FloatAngle&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public FloatAngleVector(final SortedMap<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<FloatAngle> getScalarClass()
    {
        return FloatAngle.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngleVector instantiateVector(final FloatVectorData fvd, final AngleUnit displayUnit)
    {
        return new FloatAngleVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngle instantiateScalarSI(final float valueSI, final AngleUnit displayUnit)
    {
        FloatAngle result = FloatAngle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDirectionVector instantiateVectorAbs(final FloatVectorData fvd, final DirectionUnit displayUnit)
    {
        return new FloatDirectionVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDirection instantiateScalarAbsSI(final float valueSI, final DirectionUnit displayUnit)
    {
        FloatDirection result = FloatDirection.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}