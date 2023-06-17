package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.SortedMap;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.Acceleration;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double AccelerationVector, a vector of values with a AccelerationUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-17T20:24:57.123282Z")
public class AccelerationVector extends AbstractDoubleVectorRel<AccelerationUnit, Acceleration, AccelerationVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an AccelerationVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector
     * @param displayUnit AccelerationUnit; the display unit of the vector data
     */
    public AccelerationVector(final DoubleVectorData data, final AccelerationUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an AccelerationVector from a double[] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data double[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit AccelerationUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AccelerationVector(final double[] data, final AccelerationUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an AccelerationVector from a double[] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data double[]; the data for the vector
     * @param displayUnit AccelerationUnit; the unit of the values in the data array, and display unit when printing
     */
    public AccelerationVector(final double[] data, final AccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AccelerationVector from a double[] object with SI-unit values.
     * @param data double[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AccelerationVector(final double[] data, final StorageType storageType)
    {
        this(data, AccelerationUnit.SI, storageType);
    }

    /**
     * Construct an AccelerationVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data double[]; the data for the vector, in SI units
     */
    public AccelerationVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Acceleration[] */

    /**
     * Construct an AccelerationVector from an array of Acceleration objects. The Acceleration values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data Acceleration[]; the data for the vector
     * @param displayUnit AccelerationUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AccelerationVector(final Acceleration[] data, final AccelerationUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an AccelerationVector from an array of Acceleration objects. The Acceleration values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data Acceleration[]; the data for the vector
     * @param displayUnit AccelerationUnit; the display unit of the values when printing
     */
    public AccelerationVector(final Acceleration[] data, final AccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AccelerationVector from an array of Acceleration objects. The Acceleration values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array.
     * @param data Acceleration[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AccelerationVector(final Acceleration[] data, final StorageType storageType)
    {
        this(data, AccelerationUnit.SI, storageType);
    }

    /**
     * Construct an AccelerationVector from an array of Acceleration objects. The Acceleration values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data Acceleration[]; the data for the vector
     */
    public AccelerationVector(final Acceleration[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<Acceleration> */

    /**
     * Construct an AccelerationVector from a list of Number objects or a list of Acceleration objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * Acceleration objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in
     * the list are expressed, as well as the unit in which they will be printed. In case the list contains Acceleration
     * objects, each Acceleration has its own unit, and the displayUnit is just used for printing. The values but will always be
     * internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;Acceleration&gt;; the data for the vector
     * @param displayUnit AccelerationUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AccelerationVector(final List<? extends Number> data, final AccelerationUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof Acceleration ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an AccelerationVector from a list of Number objects or a list of Acceleration objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * Acceleration objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in
     * the list are expressed, as well as the unit in which they will be printed. In case the list contains Acceleration
     * objects, each Acceleration has its own unit, and the displayUnit is just used for printing. The values but will always be
     * internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;Acceleration&gt;; the data for the vector
     * @param displayUnit AccelerationUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public AccelerationVector(final List<? extends Number> data, final AccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AccelerationVector from a list of Number objects or a list of Acceleration objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of Acceleration objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;Acceleration&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AccelerationVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, AccelerationUnit.SI, storageType);
    }

    /**
     * Construct an AccelerationVector from a list of Number objects or a list of Acceleration objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of Acceleration objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;Acceleration&gt;; the data for the vector
     */
    public AccelerationVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH SortedMap<Integer, Double> or SortedMap<Integer, Acceleration> */

    /**
     * Construct an AccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of Acceleration objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Double objects) or Acceleration objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains Acceleration objects, each Acceleration has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, Acceleration&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AccelerationUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AccelerationVector(final SortedMap<Integer, ? extends Number> data, final int size,
            final AccelerationUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.get(data.firstKey()) instanceof Acceleration
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an AccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of Acceleration objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Double objects) or Acceleration objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains Acceleration objects, each Acceleration has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we
     * offer the data as a Map.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, Acceleration&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AccelerationUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public AccelerationVector(final SortedMap<Integer, ? extends Number> data, final int size,
            final AccelerationUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an AccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of Acceleration objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of Acceleration objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, Acceleration&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AccelerationVector(final SortedMap<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, AccelerationUnit.SI, storageType);
    }

    /**
     * Construct an AccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of Acceleration objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of Acceleration objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE
     * since we offer the data as a Map.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, Acceleration&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public AccelerationVector(final SortedMap<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<Acceleration> getScalarClass()
    {
        return Acceleration.class;
    }

    /** {@inheritDoc} */
    @Override
    public AccelerationVector instantiateVector(final DoubleVectorData dvd, final AccelerationUnit displayUnit)
    {
        return new AccelerationVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Acceleration instantiateScalarSI(final double valueSI, final AccelerationUnit displayUnit)
    {
        Acceleration result = Acceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
