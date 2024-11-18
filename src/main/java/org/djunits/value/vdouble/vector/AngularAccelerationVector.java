package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.AngularAcceleration;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double AngularAccelerationVector, a vector of values with a AngularAccelerationUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class AngularAccelerationVector
        extends DoubleVectorRel<AngularAccelerationUnit, AngularAcceleration, AngularAccelerationVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an AngularAccelerationVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public AngularAccelerationVector(final DoubleVectorData data, final AngularAccelerationUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an AngularAccelerationVector from a double[] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularAccelerationVector(final double[] data, final AngularAccelerationUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an AngularAccelerationVector from a double[] object. The double values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public AngularAccelerationVector(final double[] data, final AngularAccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AngularAccelerationVector from a double[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularAccelerationVector(final double[] data, final StorageType storageType)
    {
        this(data, AngularAccelerationUnit.SI, storageType);
    }

    /**
     * Construct an AngularAccelerationVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public AngularAccelerationVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH AngularAcceleration[] */

    /**
     * Construct an AngularAccelerationVector from an array of AngularAcceleration objects. The AngularAcceleration values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularAccelerationVector(final AngularAcceleration[] data, final AngularAccelerationUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an AngularAccelerationVector from an array of AngularAcceleration objects. The AngularAcceleration values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public AngularAccelerationVector(final AngularAcceleration[] data, final AngularAccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AngularAccelerationVector from an array of AngularAcceleration objects. The AngularAcceleration values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularAccelerationVector(final AngularAcceleration[] data, final StorageType storageType)
    {
        this(data, AngularAccelerationUnit.SI, storageType);
    }

    /**
     * Construct an AngularAccelerationVector from an array of AngularAcceleration objects. The AngularAcceleration values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public AngularAccelerationVector(final AngularAcceleration[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<AngularAcceleration> */

    /**
     * Construct an AngularAccelerationVector from a list of Number objects or a list of AngularAcceleration objects. Note that
     * the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * AngularAcceleration objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * AngularAcceleration objects, each AngularAcceleration has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularAccelerationVector(final List<? extends Number> data, final AngularAccelerationUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof AngularAcceleration
                        ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an AngularAccelerationVector from a list of Number objects or a list of AngularAcceleration objects. Note that
     * the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * AngularAcceleration objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * AngularAcceleration objects, each AngularAcceleration has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public AngularAccelerationVector(final List<? extends Number> data, final AngularAccelerationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an AngularAccelerationVector from a list of Number objects or a list of AngularAcceleration objects. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * AngularAcceleration objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularAccelerationVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, AngularAccelerationUnit.SI, storageType);
    }

    /**
     * Construct an AngularAccelerationVector from a list of Number objects or a list of AngularAcceleration objects. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * AngularAcceleration objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector
     */
    public AngularAccelerationVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, AngularAcceleration> */

    /**
     * Construct an AngularAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of AngularAcceleration objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or
     * AngularAcceleration objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * AngularAcceleration objects, each AngularAcceleration has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularAccelerationVector(final Map<Integer, ? extends Number> data, final int size,
            final AngularAccelerationUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof AngularAcceleration
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an AngularAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of AngularAcceleration objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or
     * AngularAcceleration objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * AngularAcceleration objects, each AngularAcceleration has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public AngularAccelerationVector(final Map<Integer, ? extends Number> data, final int size,
            final AngularAccelerationUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an AngularAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of AngularAcceleration objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * AngularAcceleration objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public AngularAccelerationVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, AngularAccelerationUnit.SI, storageType);
    }

    /**
     * Construct an AngularAccelerationVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of AngularAcceleration objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * AngularAcceleration objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     */
    public AngularAccelerationVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<AngularAcceleration> getScalarClass()
    {
        return AngularAcceleration.class;
    }

    @Override
    public AngularAccelerationVector instantiateVector(final DoubleVectorData dvd, final AngularAccelerationUnit displayUnit)
    {
        return new AngularAccelerationVector(dvd, displayUnit);
    }

    @Override
    public AngularAcceleration instantiateScalarSI(final double valueSI, final AngularAccelerationUnit displayUnit)
    {
        AngularAcceleration result = AngularAcceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
