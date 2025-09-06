package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.TorqueUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.Torque;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double TorqueVector, a vector of values with a TorqueUnit. 
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class TorqueVector extends DoubleVectorRel<TorqueUnit, Torque, TorqueVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an TorqueVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public TorqueVector(final DoubleVectorData data, final TorqueUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an TorqueVector from a double[] object. The double values are expressed in the displayUnit, and will be printed
     * using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public TorqueVector(final double[] data, final TorqueUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an TorqueVector from a double[] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public TorqueVector(final double[] data, final TorqueUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an TorqueVector from a double[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public TorqueVector(final double[] data, final StorageType storageType)
    {
        this(data, TorqueUnit.SI, storageType);
    }

    /**
     * Construct an TorqueVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE since we offer
     * the data as an array.
     * @param data the data for the vector, in SI units
     */
    public TorqueVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Torque[] */

    /**
     * Construct an TorqueVector from an array of Torque objects. The Torque values are each expressed in their own unit, but will be
     * internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public TorqueVector(final Torque[] data, final TorqueUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an TorqueVector from an array of Torque objects. The Torque values are each expressed in their own unit, but will be
     * internally stored as SI values, all expressed in the displayUnit when printing. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public TorqueVector(final Torque[] data, final TorqueUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an TorqueVector from an array of Torque objects. The Torque values are each expressed in their own unit, but will be
     * internally stored as SI values, and expressed using SI units when printing. since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public TorqueVector(final Torque[] data, final StorageType storageType)
    {
        this(data, TorqueUnit.SI, storageType);
    }

    /**
     * Construct an TorqueVector from an array of Torque objects. The Torque values are each expressed in their own unit, but will be
     * internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data the data for the vector
     */
    public TorqueVector(final Torque[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<Torque> */

    /**
     * Construct an TorqueVector from a list of Number objects or a list of Torque objects. Note that the displayUnit has a
     * different meaning depending on whether the list contains Number objects (e.g., Double objects) or Torque objects. In case
     * the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed, as
     * well as the unit in which they will be printed. In case the list contains Torque objects, each Torque has its own unit, and
     * the displayUnit is just used for printing. The values but will always be internally stored as SI values or base values,
     * and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;Torque&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public TorqueVector(final List<? extends Number> data, final TorqueUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof Torque ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an TorqueVector from a list of Number objects or a list of Torque objects. Note that the displayUnit has a
     * different meaning depending on whether the list contains Number objects (e.g., Double objects) or Torque objects. In case
     * the list contains Number objects, the displayUnit indicates the unit in which the values in the list are expressed, as
     * well as the unit in which they will be printed. In case the list contains Torque objects, each Torque has its own unit, and
     * the displayUnit is just used for printing. The values but will always be internally stored as SI values or base values,
     * and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we offer the data
     * as a List.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;Torque&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public TorqueVector(final List<? extends Number> data, final TorqueUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an TorqueVector from a list of Number objects or a list of Torque objects. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of Torque objects, they each have their own
     * unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;Torque&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public TorqueVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, TorqueUnit.SI, storageType);
    }

    /**
     * Construct an TorqueVector from a list of Number objects or a list of Torque objects. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of Torque objects, they each have their own
     * unit, but will be printed using SI units or base units. The values but will always be internally stored as SI values or
     * base values, and expressed using the display unit or base unit when printing. Assume the storage type is DENSE since we
     * offer the data as a List.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;Torque&gt;
     */
    public TorqueVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, Torque> */

    /**
     * Construct an TorqueVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * Torque objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of the
     * vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different meaning
     * depending on whether the map contains Number objects (e.g., Double objects) or Torque objects. In case the map contains
     * Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the unit in
     * which they will be printed. In case the map contains Torque objects, each Torque has its own unit, and the displayUnit is
     * just used for printing. The values but will always be internally stored as SI values or base values, and expressed using
     * the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, Torque&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public TorqueVector(final Map<Integer, ? extends Number> data, final int size, final TorqueUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof Torque
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an TorqueVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * Torque objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of the
     * vector, since the largest index does not have to be part of the map. Note that the displayUnit has a different meaning
     * depending on whether the map contains Number objects (e.g., Double objects) or Torque objects. In case the map contains
     * Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as well as the unit in
     * which they will be printed. In case the map contains Torque objects, each Torque has its own unit, and the displayUnit is
     * just used for printing. The values but will always be internally stored as SI values or base values, and expressed using
     * the display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, Torque&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is
     *            expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public TorqueVector(final Map<Integer, ? extends Number> data, final int size, final TorqueUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an TorqueVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * Torque objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of the
     * vector, since the largest index does not have to be part of the map. When data contains numbers such as Double, assume
     * that they are expressed using SI units. When the data consists of Torque objects, they each have their own unit, but will
     * be printed using SI units or base units. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, Torque&gt;; 
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public TorqueVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, TorqueUnit.SI, storageType);
    }

    /**
     * Construct an TorqueVector from a (sparse) map of index values to Number objects or a (sparse) map of index values to of
     * Torque objects. Using index values is particularly useful for sparse vectors. The size parameter indicates the size of the
     * vector, since the largest index does not have to be part of the map. When data contains numbers such as Double, assume
     * that they are expressed using SI units. When the data consists of Torque objects, they each have their own unit, but will
     * be printed using SI units or base units. The values but will always be internally stored as SI values or base values, and
     * expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as
     * a Map.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, Torque&gt;; 
     * @param size the size off the vector, i.e., the highest index
     */
    public TorqueVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<Torque> getScalarClass()
    {
        return Torque.class;
    }

    @Override
    public TorqueVector instantiateVector(final DoubleVectorData dvd, final TorqueUnit displayUnit)
    {
        return new TorqueVector(dvd, displayUnit);
    }

    @Override
    public Torque instantiateScalarSI(final double valueSI, final TorqueUnit displayUnit)
    {
        Torque result = Torque.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

   
}
