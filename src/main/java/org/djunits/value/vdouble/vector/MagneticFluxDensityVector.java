package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.MagneticFluxDensity;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double MagneticFluxDensityVector, a vector of values with a MagneticFluxDensityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class MagneticFluxDensityVector
        extends DoubleVectorRel<MagneticFluxDensityUnit, MagneticFluxDensity, MagneticFluxDensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an MagneticFluxDensityVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public MagneticFluxDensityVector(final DoubleVectorData data, final MagneticFluxDensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an MagneticFluxDensityVector from a double[] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MagneticFluxDensityVector(final double[] data, final MagneticFluxDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an MagneticFluxDensityVector from a double[] object. The double values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public MagneticFluxDensityVector(final double[] data, final MagneticFluxDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an MagneticFluxDensityVector from a double[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MagneticFluxDensityVector(final double[] data, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, storageType);
    }

    /**
     * Construct an MagneticFluxDensityVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public MagneticFluxDensityVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH MagneticFluxDensity[] */

    /**
     * Construct an MagneticFluxDensityVector from an array of MagneticFluxDensity objects. The MagneticFluxDensity values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MagneticFluxDensityVector(final MagneticFluxDensity[] data, final MagneticFluxDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an MagneticFluxDensityVector from an array of MagneticFluxDensity objects. The MagneticFluxDensity values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public MagneticFluxDensityVector(final MagneticFluxDensity[] data, final MagneticFluxDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an MagneticFluxDensityVector from an array of MagneticFluxDensity objects. The MagneticFluxDensity values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MagneticFluxDensityVector(final MagneticFluxDensity[] data, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, storageType);
    }

    /**
     * Construct an MagneticFluxDensityVector from an array of MagneticFluxDensity objects. The MagneticFluxDensity values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public MagneticFluxDensityVector(final MagneticFluxDensity[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<MagneticFluxDensity> */

    /**
     * Construct an MagneticFluxDensityVector from a list of Number objects or a list of MagneticFluxDensity objects. Note that
     * the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * MagneticFluxDensity objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * MagneticFluxDensity objects, each MagneticFluxDensity has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MagneticFluxDensityVector(final List<? extends Number> data, final MagneticFluxDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof MagneticFluxDensity
                        ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an MagneticFluxDensityVector from a list of Number objects or a list of MagneticFluxDensity objects. Note that
     * the displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * MagneticFluxDensity objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * MagneticFluxDensity objects, each MagneticFluxDensity has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public MagneticFluxDensityVector(final List<? extends Number> data, final MagneticFluxDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an MagneticFluxDensityVector from a list of Number objects or a list of MagneticFluxDensity objects. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * MagneticFluxDensity objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MagneticFluxDensityVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, storageType);
    }

    /**
     * Construct an MagneticFluxDensityVector from a list of Number objects or a list of MagneticFluxDensity objects. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * MagneticFluxDensity objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector
     */
    public MagneticFluxDensityVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, MagneticFluxDensity> */

    /**
     * Construct an MagneticFluxDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of MagneticFluxDensity objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or
     * MagneticFluxDensity objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * MagneticFluxDensity objects, each MagneticFluxDensity has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MagneticFluxDensityVector(final Map<Integer, ? extends Number> data, final int size,
            final MagneticFluxDensityUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof MagneticFluxDensity
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an MagneticFluxDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of MagneticFluxDensity objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. Note that the
     * displayUnit has a different meaning depending on whether the map contains Number objects (e.g., Double objects) or
     * MagneticFluxDensity objects. In case the map contains Number objects, the displayUnit indicates the unit in which the
     * values in the map are expressed, as well as the unit in which they will be printed. In case the map contains
     * MagneticFluxDensity objects, each MagneticFluxDensity has its own unit, and the displayUnit is just used for printing.
     * The values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the
     *            data is expressed as List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public MagneticFluxDensityVector(final Map<Integer, ? extends Number> data, final int size,
            final MagneticFluxDensityUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an MagneticFluxDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of MagneticFluxDensity objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * MagneticFluxDensity objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public MagneticFluxDensityVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, MagneticFluxDensityUnit.SI, storageType);
    }

    /**
     * Construct an MagneticFluxDensityVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of MagneticFluxDensity objects. Using index values is particularly useful for sparse vectors. The size
     * parameter indicates the size of the vector, since the largest index does not have to be part of the map. When data
     * contains numbers such as Double, assume that they are expressed using SI units. When the data consists of
     * MagneticFluxDensity objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector
     * @param size the size off the vector, i.e., the highest index
     */
    public MagneticFluxDensityVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<MagneticFluxDensity> getScalarClass()
    {
        return MagneticFluxDensity.class;
    }

    @Override
    public MagneticFluxDensityVector instantiateVector(final DoubleVectorData dvd, final MagneticFluxDensityUnit displayUnit)
    {
        return new MagneticFluxDensityVector(dvd, displayUnit);
    }

    @Override
    public MagneticFluxDensity instantiateScalarSI(final double valueSI, final MagneticFluxDensityUnit displayUnit)
    {
        MagneticFluxDensity result = MagneticFluxDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
