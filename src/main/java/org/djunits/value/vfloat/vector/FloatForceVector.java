package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.ForceUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatForce;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatForceVector, a vector of values with a ForceUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatForceVector extends FloatVectorRel<ForceUnit, FloatForce, FloatForceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatForceVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatForceVector(final FloatVectorData data, final ForceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatForceVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatForceVector(final float[] data, final ForceUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatForceVector from a float[] object. The Float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatForceVector(final float[] data, final ForceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatForceVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatForceVector(final float[] data, final StorageType storageType)
    {
        this(data, ForceUnit.SI, storageType);
    }

    /**
     * Construct a FloatForceVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatForceVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatForce[] */

    /**
     * Construct a FloatForceVector from an array of FloatForce objects. The FloatForce values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatForceVector(final FloatForce[] data, final ForceUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatForceVector from an array of FloatForce objects. The FloatForce values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatForceVector(final FloatForce[] data, final ForceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatForceVector from an array of FloatForce objects. The FloatForce values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as
     * an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatForceVector(final FloatForce[] data, final StorageType storageType)
    {
        this(data, ForceUnit.SI, storageType);
    }

    /**
     * Construct a FloatForceVector from an array of FloatForce objects. The FloatForce values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatForceVector(final FloatForce[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Force> */

    /**
     * Construct a FloatForceVector from a list of Number objects or a list of FloatForce objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatForce objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains FloatForce objects, each FloatForce has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatForceVector(final List<? extends Number> data, final ForceUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatForce ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatForceVector from a list of Number objects or a list of FloatForce objects. Note that the displayUnit has
     * a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatForce objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains FloatForce objects, each FloatForce has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we
     * offer the data as a List.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatForceVector(final List<? extends Number> data, final ForceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatForceVector from a list of Number objects or a list of FloatForce objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatForce objects, they each
     * have their own unit, but will be printed using SI units or base units. The values but will always be internally stored as
     * SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatForceVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, ForceUnit.SI, storageType);
    }

    /**
     * Construct a FloatForceVector from a list of Number objects or a list of FloatForce objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatForce objects, they each
     * have their own unit, but will be printed using SI units or base units. The values but will always be internally stored as
     * SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is
     * DENSE since we offer the data as a List.
     * @param data the data for the vector
     */
    public FloatForceVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatForce> */

    /**
     * Construct a FloatForceVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatForce objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatForce objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatForce objects, each FloatForce has its own
     * unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatForceVector(final Map<Integer, ? extends Number> data, final int size, final ForceUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatForce
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatForceVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatForce objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatForce objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatForce objects, each FloatForce has its own
     * unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer
     * the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatForceVector(final Map<Integer, ? extends Number> data, final int size, final ForceUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatForceVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatForce objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatForce objects, they each have their
     * own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatForceVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, ForceUnit.SI, storageType);
    }

    /**
     * Construct a FloatForceVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatForce objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatForce objects, they each have their
     * own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since
     * we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatForceVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatForce> getScalarClass()
    {
        return FloatForce.class;
    }

    @Override
    public FloatForceVector instantiateVector(final FloatVectorData fvd, final ForceUnit displayUnit)
    {
        return new FloatForceVector(fvd, displayUnit);
    }

    @Override
    public FloatForce instantiateScalarSI(final float valueSI, final ForceUnit displayUnit)
    {
        FloatForce result = FloatForce.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
