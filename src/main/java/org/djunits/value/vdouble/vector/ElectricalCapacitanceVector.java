package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.ElectricalCapacitance;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double ElectricalCapacitanceVector, a vector of values with a ElectricalCapacitanceUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class ElectricalCapacitanceVector
        extends DoubleVectorRel<ElectricalCapacitanceUnit, ElectricalCapacitance, ElectricalCapacitanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalCapacitanceVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector
     * @param displayUnit ElectricalCapacitanceUnit; the display unit of the vector data
     */
    public ElectricalCapacitanceVector(final DoubleVectorData data, final ElectricalCapacitanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an ElectricalCapacitanceVector from a double[] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data double[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit ElectricalCapacitanceUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalCapacitanceVector(final double[] data, final ElectricalCapacitanceUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a double[] object. The double values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data double[]; the data for the vector
     * @param displayUnit ElectricalCapacitanceUnit; the unit of the values in the data array, and display unit when printing
     */
    public ElectricalCapacitanceVector(final double[] data, final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a double[] object with SI-unit values.
     * @param data double[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalCapacitanceVector(final double[] data, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data double[]; the data for the vector, in SI units
     */
    public ElectricalCapacitanceVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH ElectricalCapacitance[] */

    /**
     * Construct an ElectricalCapacitanceVector from an array of ElectricalCapacitance objects. The ElectricalCapacitance values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data ElectricalCapacitance[]; the data for the vector
     * @param displayUnit ElectricalCapacitanceUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalCapacitanceVector(final ElectricalCapacitance[] data, final ElectricalCapacitanceUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an ElectricalCapacitanceVector from an array of ElectricalCapacitance objects. The ElectricalCapacitance values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data ElectricalCapacitance[]; the data for the vector
     * @param displayUnit ElectricalCapacitanceUnit; the display unit of the values when printing
     */
    public ElectricalCapacitanceVector(final ElectricalCapacitance[] data, final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an ElectricalCapacitanceVector from an array of ElectricalCapacitance objects. The ElectricalCapacitance values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. since we offer the data as an array.
     * @param data ElectricalCapacitance[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalCapacitanceVector(final ElectricalCapacitance[] data, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct an ElectricalCapacitanceVector from an array of ElectricalCapacitance objects. The ElectricalCapacitance values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data ElectricalCapacitance[]; the data for the vector
     */
    public ElectricalCapacitanceVector(final ElectricalCapacitance[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<ElectricalCapacitance> */

    /**
     * Construct an ElectricalCapacitanceVector from a list of Number objects or a list of ElectricalCapacitance objects. Note
     * that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects)
     * or ElectricalCapacitance objects. In case the list contains Number objects, the displayUnit indicates the unit in which
     * the values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * ElectricalCapacitance objects, each ElectricalCapacitance has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing.
     * @param data List&lt;Double&gt; or List&lt;ElectricalCapacitance&gt;; the data for the vector
     * @param displayUnit ElectricalCapacitanceUnit; the display unit of the vector data, and the unit of the data points when
     *            the data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalCapacitanceVector(final List<? extends Number> data, final ElectricalCapacitanceUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof ElectricalCapacitance
                        ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a list of Number objects or a list of ElectricalCapacitance objects. Note
     * that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects)
     * or ElectricalCapacitance objects. In case the list contains Number objects, the displayUnit indicates the unit in which
     * the values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * ElectricalCapacitance objects, each ElectricalCapacitance has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;ElectricalCapacitance&gt;; the data for the vector
     * @param displayUnit ElectricalCapacitanceUnit; the display unit of the vector data, and the unit of the data points when
     *            the data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public ElectricalCapacitanceVector(final List<? extends Number> data, final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a list of Number objects or a list of ElectricalCapacitance objects. When
     * data contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * ElectricalCapacitance objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data List&lt;Double&gt; or List&lt;ElectricalCapacitance&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalCapacitanceVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a list of Number objects or a list of ElectricalCapacitance objects. When
     * data contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * ElectricalCapacitance objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;ElectricalCapacitance&gt;; the data for the vector
     */
    public ElectricalCapacitanceVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, ElectricalCapacitance> */

    /**
     * Construct an ElectricalCapacitanceVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of ElectricalCapacitance objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or
     * ElectricalCapacitance objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * ElectricalCapacitance objects, each ElectricalCapacitance has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, ElectricalCapacitance&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit ElectricalCapacitanceUnit; the display unit of the vector data, and the unit of the data points when
     *            the data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalCapacitanceVector(final Map<Integer, ? extends Number> data, final int size,
            final ElectricalCapacitanceUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof ElectricalCapacitance
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of ElectricalCapacitance objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or
     * ElectricalCapacitance objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * ElectricalCapacitance objects, each ElectricalCapacitance has its own unit, and the displayUnit is just used for
     * printing. The values but will always be internally stored as SI values or base values, and expressed using the display
     * unit or base unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, ElectricalCapacitance&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit ElectricalCapacitanceUnit; the display unit of the vector data, and the unit of the data points when
     *            the data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public ElectricalCapacitanceVector(final Map<Integer, ? extends Number> data, final int size,
            final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of ElectricalCapacitance objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * ElectricalCapacitance objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, ElectricalCapacitance&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalCapacitanceVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct an ElectricalCapacitanceVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of ElectricalCapacitance objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * ElectricalCapacitance objects, they each have their own unit, but will be printed using SI units or base units. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, ElectricalCapacitance&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public ElectricalCapacitanceVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalCapacitance> getScalarClass()
    {
        return ElectricalCapacitance.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCapacitanceVector instantiateVector(final DoubleVectorData dvd,
            final ElectricalCapacitanceUnit displayUnit)
    {
        return new ElectricalCapacitanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCapacitance instantiateScalarSI(final double valueSI, final ElectricalCapacitanceUnit displayUnit)
    {
        ElectricalCapacitance result = ElectricalCapacitance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
