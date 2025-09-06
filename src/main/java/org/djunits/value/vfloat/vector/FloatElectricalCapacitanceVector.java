package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatElectricalCapacitance;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatElectricalCapacitanceVector, a vector of values with a ElectricalCapacitanceUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatElectricalCapacitanceVector
        extends FloatVectorRel<ElectricalCapacitanceUnit, FloatElectricalCapacitance, FloatElectricalCapacitanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatElectricalCapacitanceVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatElectricalCapacitanceVector(final FloatVectorData data, final ElectricalCapacitanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatElectricalCapacitanceVector from a float[] object. The Float values are expressed in the displayUnit,
     * and will be printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalCapacitanceVector(final float[] data, final ElectricalCapacitanceUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a float[] object. The Float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatElectricalCapacitanceVector(final float[] data, final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalCapacitanceVector(final float[] data, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a float[] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatElectricalCapacitanceVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatElectricalCapacitance[] */

    /**
     * Construct a FloatElectricalCapacitanceVector from an array of FloatElectricalCapacitance objects. The
     * FloatElectricalCapacitance values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalCapacitanceVector(final FloatElectricalCapacitance[] data,
            final ElectricalCapacitanceUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from an array of FloatElectricalCapacitance objects. The
     * FloatElectricalCapacitance values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatElectricalCapacitanceVector(final FloatElectricalCapacitance[] data,
            final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from an array of FloatElectricalCapacitance objects. The
     * FloatElectricalCapacitance values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalCapacitanceVector(final FloatElectricalCapacitance[] data, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from an array of FloatElectricalCapacitance objects. The
     * FloatElectricalCapacitance values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatElectricalCapacitanceVector(final FloatElectricalCapacitance[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<ElectricalCapacitance> */

    /**
     * Construct a FloatElectricalCapacitanceVector from a list of Number objects or a list of FloatElectricalCapacitance
     * objects. Note that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g.,
     * Float objects) or FloatElectricalCapacitance objects. In case the list contains Number objects, the displayUnit indicates
     * the unit in which the values in the list are expressed, as well as the unit in which they will be printed. In case the
     * list contains FloatElectricalCapacitance objects, each FloatElectricalCapacitance has its own unit, and the displayUnit
     * is just used for printing. The values but will always be internally stored as SI values or base values, and expressed
     * using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;ElectricalCapacitance&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalCapacitanceVector(final List<? extends Number> data, final ElectricalCapacitanceUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatElectricalCapacitance
                        ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a list of Number objects or a list of FloatElectricalCapacitance
     * objects. Note that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g.,
     * Float objects) or FloatElectricalCapacitance objects. In case the list contains Number objects, the displayUnit indicates
     * the unit in which the values in the list are expressed, as well as the unit in which they will be printed. In case the
     * list contains FloatElectricalCapacitance objects, each FloatElectricalCapacitance has its own unit, and the displayUnit
     * is just used for printing. The values but will always be internally stored as SI values or base values, and expressed
     * using the display unit or base unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;ElectricalCapacitance&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatElectricalCapacitanceVector(final List<? extends Number> data, final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a list of Number objects or a list of FloatElectricalCapacitance
     * objects. When data contains numbers such as Float, assume that they are expressed using SI units. When the data consists
     * of FloatElectricalCapacitance objects, they each have their own unit, but will be printed using SI units or base units.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;ElectricalCapacitance&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalCapacitanceVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a list of Number objects or a list of FloatElectricalCapacitance
     * objects. When data contains numbers such as Float, assume that they are expressed using SI units. When the data consists
     * of FloatElectricalCapacitance objects, they each have their own unit, but will be printed using SI units or base units.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;ElectricalCapacitance&gt;
     */
    public FloatElectricalCapacitanceVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatElectricalCapacitance> */

    /**
     * Construct a FloatElectricalCapacitanceVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatElectricalCapacitance objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that
     * the displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatElectricalCapacitance objects. In case the map contains Number objects, the displayUnit indicates the unit in which
     * the values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatElectricalCapacitance objects, each FloatElectricalCapacitance has its own unit, and the displayUnit is just used
     * for printing. The values but will always be internally stored as SI values or base values, and expressed using the
     * display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatElectricalCapacitance&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalCapacitanceVector(final Map<Integer, ? extends Number> data, final int size,
            final ElectricalCapacitanceUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatElectricalCapacitance
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatElectricalCapacitance objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that
     * the displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatElectricalCapacitance objects. In case the map contains Number objects, the displayUnit indicates the unit in which
     * the values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatElectricalCapacitance objects, each FloatElectricalCapacitance has its own unit, and the displayUnit is just used
     * for printing. The values but will always be internally stored as SI values or base values, and expressed using the
     * display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatElectricalCapacitance&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatElectricalCapacitanceVector(final Map<Integer, ? extends Number> data, final int size,
            final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatElectricalCapacitance objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatElectricalCapacitance objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatElectricalCapacitance&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatElectricalCapacitanceVector(final Map<Integer, ? extends Number> data, final int size,
            final StorageType storageType)
    {
        this(data, size, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalCapacitanceVector from a (sparse) map of index values to Number objects or a (sparse) map of
     * index values to of FloatElectricalCapacitance objects. Using index values is particularly useful for sparse vectors. The
     * size parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatElectricalCapacitance objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatElectricalCapacitance&gt;
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatElectricalCapacitanceVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatElectricalCapacitance> getScalarClass()
    {
        return FloatElectricalCapacitance.class;
    }

    @Override
    public FloatElectricalCapacitanceVector instantiateVector(final FloatVectorData fvd,
            final ElectricalCapacitanceUnit displayUnit)
    {
        return new FloatElectricalCapacitanceVector(fvd, displayUnit);
    }

    @Override
    public FloatElectricalCapacitance instantiateScalarSI(final float valueSI, final ElectricalCapacitanceUnit displayUnit)
    {
        FloatElectricalCapacitance result = FloatElectricalCapacitance.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
