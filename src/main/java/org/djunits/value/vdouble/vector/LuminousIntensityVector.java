package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.LuminousIntensity;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double LuminousIntensityVector, a vector of values with a LuminousIntensityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class LuminousIntensityVector extends DoubleVectorRel<LuminousIntensityUnit, LuminousIntensity, LuminousIntensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an LuminousIntensityVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public LuminousIntensityVector(final DoubleVectorData data, final LuminousIntensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an LuminousIntensityVector from a double[] object. The double values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LuminousIntensityVector(final double[] data, final LuminousIntensityUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an LuminousIntensityVector from a double[] object. The double values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public LuminousIntensityVector(final double[] data, final LuminousIntensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an LuminousIntensityVector from a double[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LuminousIntensityVector(final double[] data, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, storageType);
    }

    /**
     * Construct an LuminousIntensityVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public LuminousIntensityVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH LuminousIntensity[] */

    /**
     * Construct an LuminousIntensityVector from an array of LuminousIntensity objects. The LuminousIntensity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LuminousIntensityVector(final LuminousIntensity[] data, final LuminousIntensityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an LuminousIntensityVector from an array of LuminousIntensity objects. The LuminousIntensity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public LuminousIntensityVector(final LuminousIntensity[] data, final LuminousIntensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an LuminousIntensityVector from an array of LuminousIntensity objects. The LuminousIntensity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LuminousIntensityVector(final LuminousIntensity[] data, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, storageType);
    }

    /**
     * Construct an LuminousIntensityVector from an array of LuminousIntensity objects. The LuminousIntensity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public LuminousIntensityVector(final LuminousIntensity[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<LuminousIntensity> */

    /**
     * Construct an LuminousIntensityVector from a list of Number objects or a list of LuminousIntensity objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * LuminousIntensity objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * LuminousIntensity objects, each LuminousIntensity has its own unit, and the displayUnit is just used for printing. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LuminousIntensityVector(final List<? extends Number> data, final LuminousIntensityUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof LuminousIntensity
                        ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an LuminousIntensityVector from a list of Number objects or a list of LuminousIntensity objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * LuminousIntensity objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * LuminousIntensity objects, each LuminousIntensity has its own unit, and the displayUnit is just used for printing. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public LuminousIntensityVector(final List<? extends Number> data, final LuminousIntensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an LuminousIntensityVector from a list of Number objects or a list of LuminousIntensity objects. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * LuminousIntensity objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LuminousIntensityVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, storageType);
    }

    /**
     * Construct an LuminousIntensityVector from a list of Number objects or a list of LuminousIntensity objects. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * LuminousIntensity objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector
     */
    public LuminousIntensityVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, LuminousIntensity> */

    /**
     * Construct an LuminousIntensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of LuminousIntensity objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit
     * has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or LuminousIntensity
     * objects. In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are
     * expressed, as well as the unit in which they will be printed. In case the map contains LuminousIntensity objects, each
     * LuminousIntensity has its own unit, and the displayUnit is just used for printing. The values but will always be
     * internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LuminousIntensityVector(final Map<Integer, ? extends Number> data, final int size,
            final LuminousIntensityUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof LuminousIntensity
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an LuminousIntensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of LuminousIntensity objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit
     * has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or LuminousIntensity
     * objects. In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are
     * expressed, as well as the unit in which they will be printed. In case the map contains LuminousIntensity objects, each
     * LuminousIntensity has its own unit, and the displayUnit is just used for printing. The values but will always be
     * internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public LuminousIntensityVector(final Map<Integer, ? extends Number> data, final int size,
            final LuminousIntensityUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an LuminousIntensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of LuminousIntensity objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. When data contains numbers
     * such as Double, assume that they are expressed using SI units. When the data consists of LuminousIntensity objects, they
     * each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public LuminousIntensityVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, LuminousIntensityUnit.SI, storageType);
    }

    /**
     * Construct an LuminousIntensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of LuminousIntensity objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. When data contains numbers
     * such as Double, assume that they are expressed using SI units. When the data consists of LuminousIntensity objects, they
     * each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     */
    public LuminousIntensityVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<LuminousIntensity> getScalarClass()
    {
        return LuminousIntensity.class;
    }

    @Override
    public LuminousIntensityVector instantiateVector(final DoubleVectorData dvd, final LuminousIntensityUnit displayUnit)
    {
        return new LuminousIntensityVector(dvd, displayUnit);
    }

    @Override
    public LuminousIntensity instantiateScalarSI(final double valueSI, final LuminousIntensityUnit displayUnit)
    {
        LuminousIntensity result = LuminousIntensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
