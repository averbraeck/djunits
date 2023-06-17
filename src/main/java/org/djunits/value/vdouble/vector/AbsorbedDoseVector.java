package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.SortedMap;

import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.AbsorbedDose;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double AbsorbedDoseVector, a vector of values with a AbsorbedDoseUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-17T20:24:57.123282Z")
public class AbsorbedDoseVector extends AbstractDoubleVectorRel<AbsorbedDoseUnit, AbsorbedDose, AbsorbedDoseVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an AbsorbedDoseVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector
     * @param displayUnit AbsorbedDoseUnit; the display unit of the vector data
     */
    public AbsorbedDoseVector(final DoubleVectorData data, final AbsorbedDoseUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an AbsorbedDoseVector from a double[] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data double[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit AbsorbedDoseUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AbsorbedDoseVector(final double[] data, final AbsorbedDoseUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an AbsorbedDoseVector from a double[] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data double[]; the data for the vector
     * @param displayUnit AbsorbedDoseUnit; the unit of the values in the data array, and display unit when printing
     */
    public AbsorbedDoseVector(final double[] data, final AbsorbedDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AbsorbedDoseVector from a double[] object with SI-unit values.
     * @param data double[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AbsorbedDoseVector(final double[] data, final StorageType storageType)
    {
        this(data, AbsorbedDoseUnit.SI, storageType);
    }

    /**
     * Construct an AbsorbedDoseVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data double[]; the data for the vector, in SI units
     */
    public AbsorbedDoseVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH AbsorbedDose[] */

    /**
     * Construct an AbsorbedDoseVector from an array of AbsorbedDose objects. The AbsorbedDose values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data AbsorbedDose[]; the data for the vector
     * @param displayUnit AbsorbedDoseUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AbsorbedDoseVector(final AbsorbedDose[] data, final AbsorbedDoseUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an AbsorbedDoseVector from an array of AbsorbedDose objects. The AbsorbedDose values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data AbsorbedDose[]; the data for the vector
     * @param displayUnit AbsorbedDoseUnit; the display unit of the values when printing
     */
    public AbsorbedDoseVector(final AbsorbedDose[] data, final AbsorbedDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AbsorbedDoseVector from an array of AbsorbedDose objects. The AbsorbedDose values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array.
     * @param data AbsorbedDose[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AbsorbedDoseVector(final AbsorbedDose[] data, final StorageType storageType)
    {
        this(data, AbsorbedDoseUnit.SI, storageType);
    }

    /**
     * Construct an AbsorbedDoseVector from an array of AbsorbedDose objects. The AbsorbedDose values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data AbsorbedDose[]; the data for the vector
     */
    public AbsorbedDoseVector(final AbsorbedDose[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<AbsorbedDose> */

    /**
     * Construct an AbsorbedDoseVector from a list of Number objects or a list of AbsorbedDose objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * AbsorbedDose objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in
     * the list are expressed, as well as the unit in which they will be printed. In case the list contains AbsorbedDose
     * objects, each AbsorbedDose has its own unit, and the displayUnit is just used for printing. The values but will always be
     * internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;AbsorbedDose&gt;; the data for the vector
     * @param displayUnit AbsorbedDoseUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AbsorbedDoseVector(final List<? extends Number> data, final AbsorbedDoseUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof AbsorbedDose ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an AbsorbedDoseVector from a list of Number objects or a list of AbsorbedDose objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * AbsorbedDose objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values in
     * the list are expressed, as well as the unit in which they will be printed. In case the list contains AbsorbedDose
     * objects, each AbsorbedDose has its own unit, and the displayUnit is just used for printing. The values but will always be
     * internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;AbsorbedDose&gt;; the data for the vector
     * @param displayUnit AbsorbedDoseUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public AbsorbedDoseVector(final List<? extends Number> data, final AbsorbedDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AbsorbedDoseVector from a list of Number objects or a list of AbsorbedDose objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of AbsorbedDose objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;AbsorbedDose&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AbsorbedDoseVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, AbsorbedDoseUnit.SI, storageType);
    }

    /**
     * Construct an AbsorbedDoseVector from a list of Number objects or a list of AbsorbedDose objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of AbsorbedDose objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;AbsorbedDose&gt;; the data for the vector
     */
    public AbsorbedDoseVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH SortedMap<Integer, Double> or SortedMap<Integer, AbsorbedDose> */

    /**
     * Construct an AbsorbedDoseVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of AbsorbedDose objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Double objects) or AbsorbedDose objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains AbsorbedDose objects, each AbsorbedDose has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, AbsorbedDose&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AbsorbedDoseUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AbsorbedDoseVector(final SortedMap<Integer, ? extends Number> data, final int size,
            final AbsorbedDoseUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.get(data.firstKey()) instanceof AbsorbedDose
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an AbsorbedDoseVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of AbsorbedDose objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Double objects) or AbsorbedDose objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains AbsorbedDose objects, each AbsorbedDose has its
     * own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we
     * offer the data as a Map.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, AbsorbedDose&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit AbsorbedDoseUnit; the display unit of the vector data, and the unit of the data points when the data
     *            is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public AbsorbedDoseVector(final SortedMap<Integer, ? extends Number> data, final int size,
            final AbsorbedDoseUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an AbsorbedDoseVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of AbsorbedDose objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of AbsorbedDose objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, AbsorbedDose&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AbsorbedDoseVector(final SortedMap<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, AbsorbedDoseUnit.SI, storageType);
    }

    /**
     * Construct an AbsorbedDoseVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of AbsorbedDose objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of AbsorbedDose objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE
     * since we offer the data as a Map.
     * @param data SortedMap&lt;Integer, Double&gt; or SortedMap&lt;Integer, AbsorbedDose&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public AbsorbedDoseVector(final SortedMap<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<AbsorbedDose> getScalarClass()
    {
        return AbsorbedDose.class;
    }

    /** {@inheritDoc} */
    @Override
    public AbsorbedDoseVector instantiateVector(final DoubleVectorData dvd, final AbsorbedDoseUnit displayUnit)
    {
        return new AbsorbedDoseVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AbsorbedDose instantiateScalarSI(final double valueSI, final AbsorbedDoseUnit displayUnit)
    {
        AbsorbedDose result = AbsorbedDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
