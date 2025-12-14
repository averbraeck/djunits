package org.djunits.old.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.old.unit.EnergyUnit;
import org.djunits.old.unit.scale.IdentityScale;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vfloat.scalar.FloatEnergy;
import org.djunits.old.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.old.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatEnergyVector, a vector of values with a EnergyUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatEnergyVector extends FloatVectorRel<EnergyUnit, FloatEnergy, FloatEnergyVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatEnergyVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatEnergyVector(final FloatVectorData data, final EnergyUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatEnergyVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEnergyVector(final float[] data, final EnergyUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatEnergyVector from a float[] object. The Float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatEnergyVector(final float[] data, final EnergyUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEnergyVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEnergyVector(final float[] data, final StorageType storageType)
    {
        this(data, EnergyUnit.SI, storageType);
    }

    /**
     * Construct a FloatEnergyVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatEnergyVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatEnergy[] */

    /**
     * Construct a FloatEnergyVector from an array of FloatEnergy objects. The FloatEnergy values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEnergyVector(final FloatEnergy[] data, final EnergyUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatEnergyVector from an array of FloatEnergy objects. The FloatEnergy values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatEnergyVector(final FloatEnergy[] data, final EnergyUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEnergyVector from an array of FloatEnergy objects. The FloatEnergy values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data
     * as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEnergyVector(final FloatEnergy[] data, final StorageType storageType)
    {
        this(data, EnergyUnit.SI, storageType);
    }

    /**
     * Construct a FloatEnergyVector from an array of FloatEnergy objects. The FloatEnergy values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatEnergyVector(final FloatEnergy[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Energy> */

    /**
     * Construct a FloatEnergyVector from a list of Number objects or a list of FloatEnergy objects. Note that the displayUnit
     * has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatEnergy
     * objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are
     * expressed, as well as the unit in which they will be printed. In case the list contains FloatEnergy objects, each
     * FloatEnergy has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Energy&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEnergyVector(final List<? extends Number> data, final EnergyUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatEnergy ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatEnergyVector from a list of Number objects or a list of FloatEnergy objects. Note that the displayUnit
     * has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or FloatEnergy
     * objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are
     * expressed, as well as the unit in which they will be printed. In case the list contains FloatEnergy objects, each
     * FloatEnergy has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Energy&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatEnergyVector(final List<? extends Number> data, final EnergyUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEnergyVector from a list of Number objects or a list of FloatEnergy objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatEnergy objects, they each
     * have their own unit, but will be printed using SI units or base units. The values but will always be internally stored as
     * SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Energy&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEnergyVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, EnergyUnit.SI, storageType);
    }

    /**
     * Construct a FloatEnergyVector from a list of Number objects or a list of FloatEnergy objects. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatEnergy objects, they each
     * have their own unit, but will be printed using SI units or base units. The values but will always be internally stored as
     * SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is
     * DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Energy&gt;
     */
    public FloatEnergyVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatEnergy> */

    /**
     * Construct a FloatEnergyVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatEnergy objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatEnergy objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatEnergy objects, each FloatEnergy has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatEnergy&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEnergyVector(final Map<Integer, ? extends Number> data, final int size, final EnergyUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatEnergy
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatEnergyVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatEnergy objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatEnergy objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatEnergy objects, each FloatEnergy has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we
     * offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatEnergy&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatEnergyVector(final Map<Integer, ? extends Number> data, final int size, final EnergyUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatEnergyVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatEnergy objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatEnergy objects, they each have their
     * own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatEnergy&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatEnergyVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, EnergyUnit.SI, storageType);
    }

    /**
     * Construct a FloatEnergyVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to
     * of FloatEnergy objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the
     * size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatEnergy objects, they each have their
     * own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since
     * we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatEnergy&gt;
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatEnergyVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatEnergy> getScalarClass()
    {
        return FloatEnergy.class;
    }

    @Override
    public FloatEnergyVector instantiateVector(final FloatVectorData fvd, final EnergyUnit displayUnit)
    {
        return new FloatEnergyVector(fvd, displayUnit);
    }

    @Override
    public FloatEnergy instantiateScalarSI(final float valueSI, final EnergyUnit displayUnit)
    {
        FloatEnergy result = FloatEnergy.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
