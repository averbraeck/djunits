package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.SortedMap;

import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.AngularVelocity;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double AngularVelocityVector, a vector of values with a AngularVelocityUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-17T20:24:57.123282Z")
public class AngularVelocityVector extends DoubleVectorRel<AngularVelocityUnit, AngularVelocity, AngularVelocityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an AngularVelocityVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector
     * @param displayUnit AngularVelocityUnit; the display unit of the vector data
     */
    public AngularVelocityVector(final DoubleVectorData data, final AngularVelocityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an AngularVelocityVector from a double[] object. The double values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data double[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit AngularVelocityUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularVelocityVector(final double[] data, final AngularVelocityUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an AngularVelocityVector from a double[] object. The double values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array.
     * @param data double[]; the data for the vector
     * @param displayUnit AngularVelocityUnit; the unit of the values in the data array, and display unit when printing
     */
    public AngularVelocityVector(final double[] data, final AngularVelocityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AngularVelocityVector from a double[] object with SI-unit values.
     * @param data double[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularVelocityVector(final double[] data, final StorageType storageType)
    {
        this(data, AngularVelocityUnit.SI, storageType);
    }

    /**
     * Construct an AngularVelocityVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array.
     * @param data double[]; the data for the vector, in SI units
     */
    public AngularVelocityVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH AngularVelocity[] */

    /**
     * Construct an AngularVelocityVector from an array of AngularVelocity objects. The AngularVelocity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data AngularVelocity[]; the data for the vector
     * @param displayUnit AngularVelocityUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularVelocityVector(final AngularVelocity[] data, final AngularVelocityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an AngularVelocityVector from an array of AngularVelocity objects. The AngularVelocity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data AngularVelocity[]; the data for the vector
     * @param displayUnit AngularVelocityUnit; the display unit of the values when printing
     */
    public AngularVelocityVector(final AngularVelocity[] data, final AngularVelocityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AngularVelocityVector from an array of AngularVelocity objects. The AngularVelocity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array.
     * @param data AngularVelocity[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularVelocityVector(final AngularVelocity[] data, final StorageType storageType)
    {
        this(data, AngularVelocityUnit.SI, storageType);
    }

    /**
     * Construct an AngularVelocityVector from an array of AngularVelocity objects. The AngularVelocity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data AngularVelocity[]; the data for the vector
     */
    public AngularVelocityVector(final AngularVelocity[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<AngularVelocity> */

    /**
     * Construct an AngularVelocityVector from a list of Number objects or a list of AngularVelocity objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * AngularVelocity objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains AngularVelocity
     * objects, each AngularVelocity has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;AngularVelocity&gt;; the data for the vector
     * @param displayUnit AngularVelocityUnit; the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularVelocityVector(final List<? extends Number> data, final AngularVelocityUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof AngularVelocity ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an AngularVelocityVector from a list of Number objects or a list of AngularVelocity objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * AngularVelocity objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains AngularVelocity
     * objects, each AngularVelocity has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;AngularVelocity&gt;; the data for the vector
     * @param displayUnit AngularVelocityUnit; the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public AngularVelocityVector(final List<? extends Number> data, final AngularVelocityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AngularVelocityVector from a list of Number objects or a list of AngularVelocity objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of AngularVelocity objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;AngularVelocity&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularVelocityVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, AngularVelocityUnit.SI, storageType);
    }

    /**
     * Construct an AngularVelocityVector from a list of Number objects or a list of AngularVelocity objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of AngularVelocity objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;AngularVelocity&gt;; the data for the vector
     */
    public AngularVelocityVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH SortedMap<Integer, Double> or SortedMap<Integer, AngularVelocity> */

    /**
     * Construct an AngularVelocityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of AngularVelocity objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit
     * has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or AngularVelocity
     * objects. In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are
     * expressed, as well as the unit in which they will be printed. In case the map contains AngularVelocity objects, each
     * AngularVelocity has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, AngularVelocity&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AngularVelocityUnit; the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularVelocityVector(final SortedMap<Integer, ? extends Number> data, final int size,
            final AngularVelocityUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.get(data.firstKey()) instanceof AngularVelocity
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an AngularVelocityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of AngularVelocity objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit
     * has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or AngularVelocity
     * objects. In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are
     * expressed, as well as the unit in which they will be printed. In case the map contains AngularVelocity objects, each
     * AngularVelocity has its own unit, and the displayUnit is just used for printing. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is SPARSE since we offer the data as a Map.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, AngularVelocity&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AngularVelocityUnit; the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public AngularVelocityVector(final SortedMap<Integer, ? extends Number> data, final int size,
            final AngularVelocityUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an AngularVelocityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of AngularVelocity objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. When data contains numbers
     * such as Double, assume that they are expressed using SI units. When the data consists of AngularVelocity objects, they
     * each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, AngularVelocity&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularVelocityVector(final SortedMap<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, AngularVelocityUnit.SI, storageType);
    }

    /**
     * Construct an AngularVelocityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of AngularVelocity objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. When data contains numbers
     * such as Double, assume that they are expressed using SI units. When the data consists of AngularVelocity objects, they
     * each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is SPARSE since we offer the data as a Map.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, AngularVelocity&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public AngularVelocityVector(final SortedMap<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<AngularVelocity> getScalarClass()
    {
        return AngularVelocity.class;
    }

    /** {@inheritDoc} */
    @Override
    public AngularVelocityVector instantiateVector(final DoubleVectorData dvd, final AngularVelocityUnit displayUnit)
    {
        return new AngularVelocityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngularVelocity instantiateScalarSI(final double valueSI, final AngularVelocityUnit displayUnit)
    {
        AngularVelocity result = AngularVelocity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
