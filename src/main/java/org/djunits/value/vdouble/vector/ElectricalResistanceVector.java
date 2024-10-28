package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.ElectricalResistance;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double ElectricalResistanceVector, a vector of values with a ElectricalResistanceUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class ElectricalResistanceVector
        extends DoubleVectorRel<ElectricalResistanceUnit, ElectricalResistance, ElectricalResistanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalResistanceVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector
     * @param displayUnit ElectricalResistanceUnit; the display unit of the vector data
     */
    public ElectricalResistanceVector(final DoubleVectorData data, final ElectricalResistanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an ElectricalResistanceVector from a double[] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data double[]; the data for the vector, expressed in the displayUnit
     * @param displayUnit ElectricalResistanceUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalResistanceVector(final double[] data, final ElectricalResistanceUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an ElectricalResistanceVector from a double[] object. The double values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data double[]; the data for the vector
     * @param displayUnit ElectricalResistanceUnit; the unit of the values in the data array, and display unit when printing
     */
    public ElectricalResistanceVector(final double[] data, final ElectricalResistanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an ElectricalResistanceVector from a double[] object with SI-unit values.
     * @param data double[]; the data for the vector, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalResistanceVector(final double[] data, final StorageType storageType)
    {
        this(data, ElectricalResistanceUnit.SI, storageType);
    }

    /**
     * Construct an ElectricalResistanceVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data double[]; the data for the vector, in SI units
     */
    public ElectricalResistanceVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH ElectricalResistance[] */

    /**
     * Construct an ElectricalResistanceVector from an array of ElectricalResistance objects. The ElectricalResistance values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data ElectricalResistance[]; the data for the vector
     * @param displayUnit ElectricalResistanceUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalResistanceVector(final ElectricalResistance[] data, final ElectricalResistanceUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an ElectricalResistanceVector from an array of ElectricalResistance objects. The ElectricalResistance values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data ElectricalResistance[]; the data for the vector
     * @param displayUnit ElectricalResistanceUnit; the display unit of the values when printing
     */
    public ElectricalResistanceVector(final ElectricalResistance[] data, final ElectricalResistanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an ElectricalResistanceVector from an array of ElectricalResistance objects. The ElectricalResistance values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. since we offer the data as an array.
     * @param data ElectricalResistance[]; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalResistanceVector(final ElectricalResistance[] data, final StorageType storageType)
    {
        this(data, ElectricalResistanceUnit.SI, storageType);
    }

    /**
     * Construct an ElectricalResistanceVector from an array of ElectricalResistance objects. The ElectricalResistance values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data ElectricalResistance[]; the data for the vector
     */
    public ElectricalResistanceVector(final ElectricalResistance[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<ElectricalResistance> */

    /**
     * Construct an ElectricalResistanceVector from a list of Number objects or a list of ElectricalResistance objects. Note
     * that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects)
     * or ElectricalResistance objects. In case the list contains Number objects, the displayUnit indicates the unit in which
     * the values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * ElectricalResistance objects, each ElectricalResistance has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data List&lt;Double&gt; or List&lt;ElectricalResistance&gt;; the data for the vector
     * @param displayUnit ElectricalResistanceUnit; the display unit of the vector data, and the unit of the data points when
     *            the data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalResistanceVector(final List<? extends Number> data, final ElectricalResistanceUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof ElectricalResistance
                        ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an ElectricalResistanceVector from a list of Number objects or a list of ElectricalResistance objects. Note
     * that the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects)
     * or ElectricalResistance objects. In case the list contains Number objects, the displayUnit indicates the unit in which
     * the values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * ElectricalResistance objects, each ElectricalResistance has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;ElectricalResistance&gt;; the data for the vector
     * @param displayUnit ElectricalResistanceUnit; the display unit of the vector data, and the unit of the data points when
     *            the data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public ElectricalResistanceVector(final List<? extends Number> data, final ElectricalResistanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an ElectricalResistanceVector from a list of Number objects or a list of ElectricalResistance objects. When
     * data contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * ElectricalResistance objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data List&lt;Double&gt; or List&lt;ElectricalResistance&gt;; the data for the vector
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalResistanceVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, ElectricalResistanceUnit.SI, storageType);
    }

    /**
     * Construct an ElectricalResistanceVector from a list of Number objects or a list of ElectricalResistance objects. When
     * data contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * ElectricalResistance objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data List&lt;Double&gt; or List&lt;ElectricalResistance&gt;; the data for the vector
     */
    public ElectricalResistanceVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, ElectricalResistance> */

    /**
     * Construct an ElectricalResistanceVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of ElectricalResistance objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or
     * ElectricalResistance objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * ElectricalResistance objects, each ElectricalResistance has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, ElectricalResistance&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit ElectricalResistanceUnit; the display unit of the vector data, and the unit of the data points when
     *            the data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalResistanceVector(final Map<Integer, ? extends Number> data, final int size,
            final ElectricalResistanceUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof ElectricalResistance
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an ElectricalResistanceVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of ElectricalResistance objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or
     * ElectricalResistance objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * ElectricalResistance objects, each ElectricalResistance has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, ElectricalResistance&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param displayUnit ElectricalResistanceUnit; the display unit of the vector data, and the unit of the data points when
     *            the data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public ElectricalResistanceVector(final Map<Integer, ? extends Number> data, final int size,
            final ElectricalResistanceUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an ElectricalResistanceVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of ElectricalResistance objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * ElectricalResistance objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, ElectricalResistance&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public ElectricalResistanceVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, ElectricalResistanceUnit.SI, storageType);
    }

    /**
     * Construct an ElectricalResistanceVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of ElectricalResistance objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * ElectricalResistance objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data Map&lt;Integer, Double&gt; or Map&lt;Integer, ElectricalResistance&gt;; the data for the vector
     * @param size int; the size off the vector, i.e., the highest index
     */
    public ElectricalResistanceVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<ElectricalResistance> getScalarClass()
    {
        return ElectricalResistance.class;
    }

    @Override
    public ElectricalResistanceVector instantiateVector(final DoubleVectorData dvd, final ElectricalResistanceUnit displayUnit)
    {
        return new ElectricalResistanceVector(dvd, displayUnit);
    }

    @Override
    public ElectricalResistance instantiateScalarSI(final double valueSI, final ElectricalResistanceUnit displayUnit)
    {
        ElectricalResistance result = ElectricalResistance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
