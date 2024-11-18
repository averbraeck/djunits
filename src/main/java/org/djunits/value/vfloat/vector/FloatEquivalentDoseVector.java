package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatEquivalentDose;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatEquivalentDoseVector, a vector of values with a EquivalentDoseUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatEquivalentDoseVector
        extends FloatVectorRel<EquivalentDoseUnit, FloatEquivalentDose, FloatEquivalentDoseVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatEquivalentDoseVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatEquivalentDoseVector(final FloatVectorData data, final EquivalentDoseUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatEquivalentDoseVector from a float[] object. The Float values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEquivalentDoseVector(final float[] data, final EquivalentDoseUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a float[] object. The Float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatEquivalentDoseVector(final float[] data, final EquivalentDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEquivalentDoseVector(final float[] data, final StorageType storageType)
    {
        this(data, EquivalentDoseUnit.SI, storageType);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatEquivalentDoseVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatEquivalentDose[] */

    /**
     * Construct a FloatEquivalentDoseVector from an array of FloatEquivalentDose objects. The FloatEquivalentDose values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEquivalentDoseVector(final FloatEquivalentDose[] data, final EquivalentDoseUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatEquivalentDoseVector from an array of FloatEquivalentDose objects. The FloatEquivalentDose values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatEquivalentDoseVector(final FloatEquivalentDose[] data, final EquivalentDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEquivalentDoseVector from an array of FloatEquivalentDose objects. The FloatEquivalentDose values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEquivalentDoseVector(final FloatEquivalentDose[] data, final StorageType storageType)
    {
        this(data, EquivalentDoseUnit.SI, storageType);
    }

    /**
     * Construct a FloatEquivalentDoseVector from an array of FloatEquivalentDose objects. The FloatEquivalentDose values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatEquivalentDoseVector(final FloatEquivalentDose[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<EquivalentDose> */

    /**
     * Construct a FloatEquivalentDoseVector from a list of Number objects or a list of FloatEquivalentDose objects. Note that
     * the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or
     * FloatEquivalentDose objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * FloatEquivalentDose objects, each FloatEquivalentDose has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEquivalentDoseVector(final List<? extends Number> data, final EquivalentDoseUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatEquivalentDose
                        ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a list of Number objects or a list of FloatEquivalentDose objects. Note that
     * the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or
     * FloatEquivalentDose objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * FloatEquivalentDose objects, each FloatEquivalentDose has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatEquivalentDoseVector(final List<? extends Number> data, final EquivalentDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a list of Number objects or a list of FloatEquivalentDose objects. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatEquivalentDose objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEquivalentDoseVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, EquivalentDoseUnit.SI, storageType);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a list of Number objects or a list of FloatEquivalentDose objects. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatEquivalentDose objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector
     */
    public FloatEquivalentDoseVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatEquivalentDose> */

    /**
     * Construct a FloatEquivalentDoseVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatEquivalentDose objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatEquivalentDose objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatEquivalentDose objects, each FloatEquivalentDose has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEquivalentDoseVector(final Map<Integer, ? extends Number> data, final int size,
            final EquivalentDoseUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatEquivalentDose
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatEquivalentDose objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or
     * FloatEquivalentDose objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * FloatEquivalentDose objects, each FloatEquivalentDose has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatEquivalentDoseVector(final Map<Integer, ? extends Number> data, final int size,
            final EquivalentDoseUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatEquivalentDose objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatEquivalentDose objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEquivalentDoseVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, EquivalentDoseUnit.SI, storageType);
    }

    /**
     * Construct a FloatEquivalentDoseVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatEquivalentDose objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatEquivalentDose objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatEquivalentDoseVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatEquivalentDose> getScalarClass()
    {
        return FloatEquivalentDose.class;
    }

    @Override
    public FloatEquivalentDoseVector instantiateVector(final FloatVectorData fvd, final EquivalentDoseUnit displayUnit)
    {
        return new FloatEquivalentDoseVector(fvd, displayUnit);
    }

    @Override
    public FloatEquivalentDose instantiateScalarSI(final float valueSI, final EquivalentDoseUnit displayUnit)
    {
        FloatEquivalentDose result = FloatEquivalentDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
