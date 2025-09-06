package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.FlowVolume;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double FlowVolumeVector, a vector of values with a FlowVolumeUnit. 
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FlowVolumeVector extends DoubleVectorRel<FlowVolumeUnit, FlowVolume, FlowVolumeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FlowVolumeVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FlowVolumeVector(final DoubleVectorData data, final FlowVolumeUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an FlowVolumeVector from a double[] object. The double values are expressed in the displayUnit, and will be printed
     * using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FlowVolumeVector(final double[] data, final FlowVolumeUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an FlowVolumeVector from a double[] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FlowVolumeVector(final double[] data, final FlowVolumeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an FlowVolumeVector from a double[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FlowVolumeVector(final double[] data, final StorageType storageType)
    {
        this(data, FlowVolumeUnit.SI, storageType);
    }

    /**
     * Construct an FlowVolumeVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE since we offer
     * the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FlowVolumeVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FlowVolume[] */

    /**
     * Construct an FlowVolumeVector from an array of FlowVolume objects. The FlowVolume values are each expressed in their own unit, but will be
     * internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FlowVolumeVector(final FlowVolume[] data, final FlowVolumeUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an FlowVolumeVector from an array of FlowVolume objects. The FlowVolume values are each expressed in their own unit, but will be
     * internally stored as SI values, all expressed in the displayUnit when printing. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FlowVolumeVector(final FlowVolume[] data, final FlowVolumeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an FlowVolumeVector from an array of FlowVolume objects. The FlowVolume values are each expressed in their own unit, but will be
     * internally stored as SI values, and expressed using SI units when printing. since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FlowVolumeVector(final FlowVolume[] data, final StorageType storageType)
    {
        this(data, FlowVolumeUnit.SI, storageType);
    }

    /**
     * Construct an FlowVolumeVector from an array of FlowVolume objects. The FlowVolume values are each expressed in their own unit, but will be
     * internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data the data for the vector
     */
    public FlowVolumeVector(final FlowVolume[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<FlowVolume> */

    /**
     * Construct an FlowVolumeVector from a list of Number objects or a list of FlowVolume objects. Note that the displayUnit has a
     * different meaning depending on whether the list contains Number objects (e.g., Double objects) or FlowVolume objects. In case
     * the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed, as
     * well as the unit in which they will be printed. In case the list contains FlowVolume objects, each FlowVolume has its own unit, and
     * the displayUnit is just used for printing. The values but will always be internally stored as SI values or base values,
     * and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;FlowVolume&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FlowVolumeVector(final List<? extends Number> data, final FlowVolumeUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FlowVolume ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an FlowVolumeVector from a list of Number objects or a list of FlowVolume objects. Note that the displayUnit has a
     * different meaning depending on whether the list contains Number objects (e.g., Double objects) or FlowVolume objects. In case
     * the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed, as
     * well as the unit in which they will be printed. In case the list contains FlowVolume objects, each FlowVolume has its own unit, and
     * the displayUnit is just used for printing. The values but will always be internally stored as SI values or base values,
     * and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we offer the data
     * as a List.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;FlowVolume&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public FlowVolumeVector(final List<? extends Number> data, final FlowVolumeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an FlowVolumeVector from a list of Number objects or a list of FlowVolume objects. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of FlowVolume objects, they each have their own
     * unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;FlowVolume&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FlowVolumeVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, FlowVolumeUnit.SI, storageType);
    }

    /**
     * Construct an FlowVolumeVector from a list of Number objects or a list of FlowVolume objects. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of FlowVolume objects, they each have their own
     * unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we
     * offer the data as a List.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;FlowVolume&gt;
     */
    public FlowVolumeVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, FlowVolume> */

    /**
     * Construct an FlowVolumeVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * FlowVolume objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of the
     * vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different meaning
     * depending on whether the map contains Number objects (e.g., Double objects) or FlowVolume objects. In case the map contains
     * Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the unit in
     * which they will be printed. In case the map contains FlowVolume objects, each FlowVolume has its own unit, and the displayUnit is
     * just used for printing. The values but will always be internally stored as SI values or base values, and expressed using
     * the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, FlowVolume&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FlowVolumeVector(final Map<Integer, ? extends Number> data, final int size, final FlowVolumeUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FlowVolume
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an FlowVolumeVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * FlowVolume objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of the
     * vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different meaning
     * depending on whether the map contains Number objects (e.g., Double objects) or FlowVolume objects. In case the map contains
     * Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the unit in
     * which they will be printed. In case the map contains FlowVolume objects, each FlowVolume has its own unit, and the displayUnit is
     * just used for printing. The values but will always be internally stored as SI values or base values, and expressed using
     * the display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, FlowVolume&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public FlowVolumeVector(final Map<Integer, ? extends Number> data, final int size, final FlowVolumeUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an FlowVolumeVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * FlowVolume objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of the
     * vector, since the largest index does not have to be part of the map. When data contains numbers such as Double, assume
     * that they are expressed using SI units. When the data consists of FlowVolume objects, they each have their own unit, but will
     * be printed using SI units or base units. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, FlowVolume&gt;; 
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FlowVolumeVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, FlowVolumeUnit.SI, storageType);
    }

    /**
     * Construct an FlowVolumeVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * FlowVolume objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of the
     * vector, since the largest index does not have to be part of the map. When data contains numbers such as Double, assume
     * that they are expressed using SI units. When the data consists of FlowVolume objects, they each have their own unit, but will
     * be printed using SI units or base units. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as
     * a Map.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, FlowVolume&gt;; 
     * @param size the size off the vector, i.e., the highest index
     */
    public FlowVolumeVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FlowVolume> getScalarClass()
    {
        return FlowVolume.class;
    }

    @Override
    public FlowVolumeVector instantiateVector(final DoubleVectorData dvd, final FlowVolumeUnit displayUnit)
    {
        return new FlowVolumeVector(dvd, displayUnit);
    }

    @Override
    public FlowVolume instantiateScalarSI(final double valueSI, final FlowVolumeUnit displayUnit)
    {
        FlowVolume result = FlowVolume.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

   
}
