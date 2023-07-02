package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.MomentumUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.Momentum;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double MomentumVector, a vector of values with a MomentumUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-02T12:23:58.164358800Z")
public class MomentumVector extends DoubleVectorRel<MomentumUnit, Momentum, MomentumVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an MomentumVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector
     * @param displayUnit MomentumUnit; the display unit of the vector data
     */
    public MomentumVector(final DoubleVectorData data, final MomentumUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an MomentumVector from a double[] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data double[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit MomentumUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MomentumVector(final double[] data, final MomentumUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an MomentumVector from a double[] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data double[]; the data for the vector
     * @param displayUnit MomentumUnit; the unit of the values in the data array, and display unit when printing
     */
    public MomentumVector(final double[] data, final MomentumUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an MomentumVector from a double[] object with SI-unit values.
     * @param data double[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MomentumVector(final double[] data, final StorageType storageType)
    {
        this(data, MomentumUnit.SI, storageType);
    }

    /**
     * Construct an MomentumVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data double[]; the data for the vector, in SI units
     */
    public MomentumVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Momentum[] */

    /**
     * Construct an MomentumVector from an array of Momentum objects. The Momentum values are each expressed in their own unit,
     * but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data Momentum[]; the data for the vector
     * @param displayUnit MomentumUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MomentumVector(final Momentum[] data, final MomentumUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an MomentumVector from an array of Momentum objects. The Momentum values are each expressed in their own unit,
     * but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array.
     * @param data Momentum[]; the data for the vector
     * @param displayUnit MomentumUnit; the display unit of the values when printing
     */
    public MomentumVector(final Momentum[] data, final MomentumUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an MomentumVector from an array of Momentum objects. The Momentum values are each expressed in their own unit,
     * but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as an
     * array.
     * @param data Momentum[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MomentumVector(final Momentum[] data, final StorageType storageType)
    {
        this(data, MomentumUnit.SI, storageType);
    }

    /**
     * Construct an MomentumVector from an array of Momentum objects. The Momentum values are each expressed in their own unit,
     * but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType is
     * DENSE since we offer the data as an array.
     * @param data Momentum[]; the data for the vector
     */
    public MomentumVector(final Momentum[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<Momentum> */

    /**
     * Construct an MomentumVector from a list of Number objects or a list of Momentum objects. Note that the displayUnit has a
     * different meaning depending on whether the list contains Number objects (e.g., Double objects) or Momentum objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains Momentum objects, each Momentum has its own
     * unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;Momentum&gt;; the data for the vector
     * @param displayUnit MomentumUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MomentumVector(final List<? extends Number> data, final MomentumUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof Momentum ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an MomentumVector from a list of Number objects or a list of Momentum objects. Note that the displayUnit has a
     * different meaning depending on whether the list contains Number objects (e.g., Double objects) or Momentum objects. In
     * case the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed,
     * as well as the unit in which they will be printed. In case the list contains Momentum objects, each Momentum has its own
     * unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we offer
     * the data as a List.
     * @param data List&lt;Double&gt; or List&lt;Momentum&gt;; the data for the vector
     * @param displayUnit MomentumUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public MomentumVector(final List<? extends Number> data, final MomentumUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an MomentumVector from a list of Number objects or a list of Momentum objects. When data contains numbers such
     * as Double, assume that they are expressed using SI units. When the data consists of Momentum objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;Momentum&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MomentumVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, MomentumUnit.SI, storageType);
    }

    /**
     * Construct an MomentumVector from a list of Number objects or a list of Momentum objects. When data contains numbers such
     * as Double, assume that they are expressed using SI units. When the data consists of Momentum objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE
     * since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;Momentum&gt;; the data for the vector
     */
    public MomentumVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, Momentum> */

    /**
     * Construct an MomentumVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * Momentum objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of
     * the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different
     * meaning depending on whether the map contains Number objects (e.g., Double objects) or Momentum objects. In case the map
     * contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the
     * unit in which they will be printed. In case the map contains Momentum objects, each Momentum has its own unit, and the
     * displayUnit is just used for printing. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, Momentum&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit MomentumUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MomentumVector(final Map<Integer, ? extends Number> data, final int size, final MomentumUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof Momentum
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an MomentumVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * Momentum objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of
     * the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different
     * meaning depending on whether the map contains Number objects (e.g., Double objects) or Momentum objects. In case the map
     * contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the
     * unit in which they will be printed. In case the map contains Momentum objects, each Momentum has its own unit, and the
     * displayUnit is just used for printing. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as
     * a Map.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, Momentum&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit MomentumUnit; the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public MomentumVector(final Map<Integer, ? extends Number> data, final int size, final MomentumUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an MomentumVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * Momentum objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of
     * the vector, since the largest index does not have to be part of the map. When data contains numbers such as Double,
     * assume that they are expressed using SI units. When the data consists of Momentum objects, they each have their own unit,
     * but will be printed using SI units or base units. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, Momentum&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MomentumVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, MomentumUnit.SI, storageType);
    }

    /**
     * Construct an MomentumVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * Momentum objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of
     * the vector, since the largest index does not have to be part of the map. When data contains numbers such as Double,
     * assume that they are expressed using SI units. When the data consists of Momentum objects, they each have their own unit,
     * but will be printed using SI units or base units. The values but will always be internally stored as SI values or base
     * values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer
     * the data as a Map.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, Momentum&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public MomentumVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<Momentum> getScalarClass()
    {
        return Momentum.class;
    }

    /** {@inheritDoc} */
    @Override
    public MomentumVector instantiateVector(final DoubleVectorData dvd, final MomentumUnit displayUnit)
    {
        return new MomentumVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Momentum instantiateScalarSI(final double valueSI, final MomentumUnit displayUnit)
    {
        Momentum result = Momentum.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
