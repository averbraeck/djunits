package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatTemperature;
import org.djunits.value.vfloat.vector.base.FloatVectorAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Absolute FloatAbsoluteTemperature Vector.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-02T12:25:19.834415900Z")
public class FloatAbsoluteTemperatureVector extends FloatVectorAbs<AbsoluteTemperatureUnit, FloatAbsoluteTemperature,
        FloatAbsoluteTemperatureVector, TemperatureUnit, FloatTemperature, FloatTemperatureVector>
{
    /** */
    private static final long serialVersionUID = 20151003L;

    /**
     * Construct a FloatAbsoluteTemperatureVector from an internal data object.
     * @param data FloatVectorData; the internal data object for the vector
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the vector data
     */
    public FloatAbsoluteTemperatureVector(final FloatVectorData data, final AbsoluteTemperatureUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatAbsoluteTemperatureVector from a float[] object. The Float values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data float[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit AbsoluteTemperatureUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAbsoluteTemperatureVector(final float[] data, final AbsoluteTemperatureUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a float[] object. The Float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data float[]; the data for the vector
     * @param displayUnit AbsoluteTemperatureUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatAbsoluteTemperatureVector(final float[] data, final AbsoluteTemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a float[] object with SI-unit values.
     * @param data float[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAbsoluteTemperatureVector(final float[] data, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.DEFAULT, storageType);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a float[] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array.
     * @param data float[]; the data for the vector, in SI units
     */
    public FloatAbsoluteTemperatureVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatAbsoluteTemperature[] */

    /**
     * Construct a FloatAbsoluteTemperatureVector from an array of FloatAbsoluteTemperature objects. The
     * FloatAbsoluteTemperature values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data FloatAbsoluteTemperature[]; the data for the vector
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAbsoluteTemperatureVector(final FloatAbsoluteTemperature[] data, final AbsoluteTemperatureUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from an array of FloatAbsoluteTemperature objects. The
     * FloatAbsoluteTemperature values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data FloatAbsoluteTemperature[]; the data for the vector
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the values when printing
     */
    public FloatAbsoluteTemperatureVector(final FloatAbsoluteTemperature[] data, final AbsoluteTemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from an array of FloatAbsoluteTemperature objects. The
     * FloatAbsoluteTemperature values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array.
     * @param data FloatAbsoluteTemperature[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAbsoluteTemperatureVector(final FloatAbsoluteTemperature[] data, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.DEFAULT, storageType);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from an array of FloatAbsoluteTemperature objects. The
     * FloatAbsoluteTemperature values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data FloatAbsoluteTemperature[]; the data for the vector
     */
    public FloatAbsoluteTemperatureVector(final FloatAbsoluteTemperature[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<AbsoluteTemperature> */

    /**
     * Construct a FloatAbsoluteTemperatureVector from a list of Number objects or a list of FloatAbsoluteTemperature objects.
     * Note that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float
     * objects) or FloatAbsoluteTemperature objects. In case the list contains Number objects, the displayUnit indicates the
     * unit in which the values in the list are expressed, as well as the unit in which they will be printed. In case the list
     * contains FloatAbsoluteTemperature objects, each FloatAbsoluteTemperature has its own unit, and the displayUnit is just
     * used for printing. The values but will always be internally stored as SI values or base values, and expressed using the
     * display unit or base unit when printing.
     * @param data List&lt;Float&gt; or List&lt;AbsoluteTemperature&gt;; the data for the vector
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAbsoluteTemperatureVector(final List<? extends Number> data, final AbsoluteTemperatureUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatAbsoluteTemperature
                        ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a list of Number objects or a list of FloatAbsoluteTemperature objects.
     * Note that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float
     * objects) or FloatAbsoluteTemperature objects. In case the list contains Number objects, the displayUnit indicates the
     * unit in which the values in the list are expressed, as well as the unit in which they will be printed. In case the list
     * contains FloatAbsoluteTemperature objects, each FloatAbsoluteTemperature has its own unit, and the displayUnit is just
     * used for printing. The values but will always be internally stored as SI values or base values, and expressed using the
     * display unit or base unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;AbsoluteTemperature&gt;; the data for the vector
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatAbsoluteTemperatureVector(final List<? extends Number> data, final AbsoluteTemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a list of Number objects or a list of FloatAbsoluteTemperature objects.
     * When data contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatAbsoluteTemperature objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data List&lt;Float&gt; or List&lt;AbsoluteTemperature&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAbsoluteTemperatureVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.DEFAULT, storageType);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a list of Number objects or a list of FloatAbsoluteTemperature objects.
     * When data contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatAbsoluteTemperature objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Float&gt; or List&lt;AbsoluteTemperature&gt;; the data for the vector
     */
    public FloatAbsoluteTemperatureVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatAbsoluteTemperature> */

    /**
     * Construct a FloatAbsoluteTemperatureVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatAbsoluteTemperature objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that
     * the displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatAbsoluteTemperature objects. In case the map contains Number objects, the displayUnit indicates the unit in which
     * the values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatAbsoluteTemperature objects, each FloatAbsoluteTemperature has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatAbsoluteTemperature&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAbsoluteTemperatureVector(final Map<Integer, ? extends Number> data, final int size,
            final AbsoluteTemperatureUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatAbsoluteTemperature
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatAbsoluteTemperature objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that
     * the displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatAbsoluteTemperature objects. In case the map contains Number objects, the displayUnit indicates the unit in which
     * the values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatAbsoluteTemperature objects, each FloatAbsoluteTemperature has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatAbsoluteTemperature&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatAbsoluteTemperatureVector(final Map<Integer, ? extends Number> data, final int size,
            final AbsoluteTemperatureUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatAbsoluteTemperature objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatAbsoluteTemperature objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatAbsoluteTemperature&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatAbsoluteTemperatureVector(final Map<Integer, ? extends Number> data, final int size,
            final StorageType storageType)
    {
        this(data, size, AbsoluteTemperatureUnit.DEFAULT, storageType);
    }

    /**
     * Construct a FloatAbsoluteTemperatureVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatAbsoluteTemperature objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatAbsoluteTemperature objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatAbsoluteTemperature&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public FloatAbsoluteTemperatureVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<FloatAbsoluteTemperature>

            getScalarClass()
    {
        return FloatAbsoluteTemperature.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsoluteTemperatureVector instantiateVector(final FloatVectorData fvd,
            final AbsoluteTemperatureUnit displayUnit)
    {
        return new FloatAbsoluteTemperatureVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsoluteTemperature instantiateScalarSI(final float valueSI, final AbsoluteTemperatureUnit displayUnit)
    {
        FloatAbsoluteTemperature result = FloatAbsoluteTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatTemperatureVector instantiateVectorRel(final FloatVectorData fvd, final TemperatureUnit displayUnit)
    {
        return new FloatTemperatureVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatTemperature instantiateScalarRelSI(final float valueSI, final TemperatureUnit displayUnit)
    {
        FloatTemperature result = FloatTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
