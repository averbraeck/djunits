package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.SpeedUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatSpeed;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatSpeedVector, a vector of values with a SpeedUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatSpeedVector extends FloatVectorRel<SpeedUnit, FloatSpeed, FloatSpeedVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatSpeedVector from an internal data object.
     * @param data FloatVectorData; the internal data object for the vector
     * @param displayUnit SpeedUnit; the display unit of the vector data
     */
    public FloatSpeedVector(final FloatVectorData data, final SpeedUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatSpeedVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit SpeedUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSpeedVector(final float[] data, final SpeedUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatSpeedVector from a float[] object. The Float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data float[]; the data for the vector
     * @param displayUnit SpeedUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatSpeedVector(final float[] data, final SpeedUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatSpeedVector from a float[] object with SI-unit values.
     * @param data float[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSpeedVector(final float[] data, final StorageType storageType)
    {
        this(data, SpeedUnit.SI, storageType);
    }

    /**
     * Construct a FloatSpeedVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data float[]; the data for the vector, in SI units
     */
    public FloatSpeedVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatSpeed[] */

    /**
     * Construct a FloatSpeedVector from an array of FloatSpeed objects. The FloatSpeed values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatSpeed[]; the data for the vector
     * @param displayUnit SpeedUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSpeedVector(final FloatSpeed[] data, final SpeedUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatSpeedVector from an array of FloatSpeed objects. The FloatSpeed values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data FloatSpeed[]; the data for the vector
     * @param displayUnit SpeedUnit; the display unit of the values when printing
     */
    public FloatSpeedVector(final FloatSpeed[] data, final SpeedUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatSpeedVector from an array of FloatSpeed objects. The FloatSpeed values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as
     * an array.
     * @param data FloatSpeed[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSpeedVector(final FloatSpeed[] data, final StorageType storageType)
    {
        this(data, SpeedUnit.SI, storageType);
    }

    /**
     * Construct a FloatSpeedVector from an array of FloatSpeed objects. The FloatSpeed values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array.
     * @param data FloatSpeed[]; the data for the vector
     */
    public FloatSpeedVector(final FloatSpeed[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Speed> */

    /**
     * Construct a FloatSpeedVector from a list of Number objects or a list of FloatSpeed objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatSpeed objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains FloatSpeed objects, each FloatSpeed has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;Speed&gt;; the data for the vector
     * @param displayUnit SpeedUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSpeedVector(final List<? extends Number> data, final SpeedUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatSpeed ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatSpeedVector from a list of Number objects or a list of FloatSpeed objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatSpeed objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains FloatSpeed objects, each FloatSpeed has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we
     * offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;Speed&gt;; the data for the vector
     * @param displayUnit SpeedUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatSpeedVector(final List<? extends Number> data, final SpeedUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatSpeedVector from a list of Number objects or a list of FloatSpeed objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatSpeed objects, they each
     * have their own unit, but will be printed using SI units or base units. The values but will always be internally stored as
     * SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;Speed&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSpeedVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, SpeedUnit.SI, storageType);
    }

    /**
     * Construct a FloatSpeedVector from a list of Number objects or a list of FloatSpeed objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatSpeed objects, they each
     * have their own unit, but will be printed using SI units or base units. The values but will always be internally stored as
     * SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is
     * DENSE since we offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;Speed&gt;; the data for the vector
     */
    public FloatSpeedVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatSpeed> */

    /**
     * Construct a FloatSpeedVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatSpeed objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatSpeed objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatSpeed objects, each FloatSpeed has its own
     * unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSpeed&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit SpeedUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSpeedVector(final Map<Integer, ? extends Number> data, final int size, final SpeedUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatSpeed
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatSpeedVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatSpeed objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatSpeed objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatSpeed objects, each FloatSpeed has its own
     * unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer
     * the data as a Map.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSpeed&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit SpeedUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatSpeedVector(final Map<Integer, ? extends Number> data, final int size, final SpeedUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatSpeedVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatSpeed objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatSpeed objects, they each have their
     * own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSpeed&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatSpeedVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, SpeedUnit.SI, storageType);
    }

    /**
     * Construct a FloatSpeedVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatSpeed objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatSpeed objects, they each have their
     * own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since
     * we offer the data as a Map.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatSpeed&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public FloatSpeedVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatSpeed> getScalarClass()
    {
        return FloatSpeed.class;
    }

    @Override
    public FloatSpeedVector instantiateVector(final FloatVectorData fvd, final SpeedUnit displayUnit)
    {
        return new FloatSpeedVector(fvd, displayUnit);
    }

    @Override
    public FloatSpeed instantiateScalarSI(final float valueSI, final SpeedUnit displayUnit)
    {
        FloatSpeed result = FloatSpeed.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
