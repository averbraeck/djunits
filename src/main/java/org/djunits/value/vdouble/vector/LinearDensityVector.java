package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.LinearDensity;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double LinearDensityVector, a vector of values with a LinearDensityUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class LinearDensityVector extends DoubleVectorRel<LinearDensityUnit, LinearDensity, LinearDensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an LinearDensityVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector
     * @param displayUnit LinearDensityUnit; the display unit of the vector data
     */
    public LinearDensityVector(final DoubleVectorData data, final LinearDensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an LinearDensityVector from a double[] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data double[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit LinearDensityUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LinearDensityVector(final double[] data, final LinearDensityUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an LinearDensityVector from a double[] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data double[]; the data for the vector
     * @param displayUnit LinearDensityUnit; the unit of the values in the data array, and display unit when printing
     */
    public LinearDensityVector(final double[] data, final LinearDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an LinearDensityVector from a double[] object with SI-unit values.
     * @param data double[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LinearDensityVector(final double[] data, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, storageType);
    }

    /**
     * Construct an LinearDensityVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array.
     * @param data double[]; the data for the vector, in SI units
     */
    public LinearDensityVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH LinearDensity[] */

    /**
     * Construct an LinearDensityVector from an array of LinearDensity objects. The LinearDensity values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data LinearDensity[]; the data for the vector
     * @param displayUnit LinearDensityUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LinearDensityVector(final LinearDensity[] data, final LinearDensityUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an LinearDensityVector from an array of LinearDensity objects. The LinearDensity values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data LinearDensity[]; the data for the vector
     * @param displayUnit LinearDensityUnit; the display unit of the values when printing
     */
    public LinearDensityVector(final LinearDensity[] data, final LinearDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an LinearDensityVector from an array of LinearDensity objects. The LinearDensity values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array.
     * @param data LinearDensity[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LinearDensityVector(final LinearDensity[] data, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, storageType);
    }

    /**
     * Construct an LinearDensityVector from an array of LinearDensity objects. The LinearDensity values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data LinearDensity[]; the data for the vector
     */
    public LinearDensityVector(final LinearDensity[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<LinearDensity> */

    /**
     * Construct an LinearDensityVector from a list of Number objects or a list of LinearDensity objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * LinearDensity objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains LinearDensity
     * objects, each LinearDensity has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;LinearDensity&gt;; the data for the vector
     * @param displayUnit LinearDensityUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LinearDensityVector(final List<? extends Number> data, final LinearDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof LinearDensity ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an LinearDensityVector from a list of Number objects or a list of LinearDensity objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * LinearDensity objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains LinearDensity
     * objects, each LinearDensity has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;LinearDensity&gt;; the data for the vector
     * @param displayUnit LinearDensityUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public LinearDensityVector(final List<? extends Number> data, final LinearDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an LinearDensityVector from a list of Number objects or a list of LinearDensity objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of LinearDensity objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;LinearDensity&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LinearDensityVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, storageType);
    }

    /**
     * Construct an LinearDensityVector from a list of Number objects or a list of LinearDensity objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of LinearDensity objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;LinearDensity&gt;; the data for the vector
     */
    public LinearDensityVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, LinearDensity> */

    /**
     * Construct an LinearDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of LinearDensity objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Double objects) or LinearDensity objects.
     * In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed,
     * as well as the unit in which they will be printed. In case the map contains LinearDensity objects, each LinearDensity has
     * its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, LinearDensity&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit LinearDensityUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LinearDensityVector(final Map<Integer, ? extends Number> data, final int size, final LinearDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof LinearDensity
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an LinearDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of LinearDensity objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Double objects) or LinearDensity objects.
     * In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed,
     * as well as the unit in which they will be printed. In case the map contains LinearDensity objects, each LinearDensity has
     * its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since
     * we offer the data as a Map.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, LinearDensity&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit LinearDensityUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public LinearDensityVector(final Map<Integer, ? extends Number> data, final int size, final LinearDensityUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an LinearDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of LinearDensity objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of LinearDensity objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, LinearDensity&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LinearDensityVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, LinearDensityUnit.SI, storageType);
    }

    /**
     * Construct an LinearDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of LinearDensity objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of LinearDensity objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE
     * since we offer the data as a Map.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, LinearDensity&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public LinearDensityVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<LinearDensity> getScalarClass()
    {
        return LinearDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public LinearDensityVector instantiateVector(final DoubleVectorData dvd, final LinearDensityUnit displayUnit)
    {
        return new LinearDensityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public LinearDensity instantiateScalarSI(final double valueSI, final LinearDensityUnit displayUnit)
    {
        LinearDensity result = LinearDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
