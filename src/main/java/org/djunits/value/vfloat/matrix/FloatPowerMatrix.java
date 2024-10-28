package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.PowerUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatPower;
import org.djunits.value.vfloat.vector.FloatPowerVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatPowerMatrix, a matrix of values with a PowerUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatPowerMatrix extends FloatMatrixRel<PowerUnit, FloatPower, FloatPowerVector, FloatPowerMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatPowerMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit PowerUnit; the display unit of the matrix data
     */
    public FloatPowerMatrix(final FloatMatrixData data, final PowerUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatPowerMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit PowerUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPowerMatrix(final float[][] data, final PowerUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatPowerMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit PowerUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatPowerMatrix(final float[][] data, final PowerUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatPowerMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPowerMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, PowerUnit.SI, storageType);
    }

    /**
     * Construct a FloatPowerMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatPowerMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Power[][] */

    /**
     * Construct a FloatPowerMatrix from an array of an array of FloatPower objects. The FloatPower values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatPower[][]; the data for the matrix
     * @param displayUnit PowerUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPowerMatrix(final FloatPower[][] data, final PowerUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatPowerMatrix from an array of an array of FloatPower objects. The FloatPower values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatPower[][]; the data for the matrix
     * @param displayUnit PowerUnit; the display unit of the values when printing
     */
    public FloatPowerMatrix(final FloatPower[][] data, final PowerUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatPowerMatrix from an array of an array of FloatPower objects. The FloatPower values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array of an array.
     * @param data FloatPower[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPowerMatrix(final FloatPower[][] data, final StorageType storageType)
    {
        this(data, PowerUnit.SI, storageType);
    }

    /**
     * Construct a FloatPowerMatrix from an array of an array of FloatPower objects. The FloatPower values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatPower[][]; the data for the matrix
     */
    public FloatPowerMatrix(final FloatPower[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatPowerMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit PowerUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPowerMatrix(final Collection<FloatSparseValue<PowerUnit, FloatPower>> data, final PowerUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatPowerMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit PowerUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatPowerMatrix(final Collection<FloatSparseValue<PowerUnit, FloatPower>> data, final PowerUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatPowerMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPowerMatrix(final Collection<FloatSparseValue<PowerUnit, FloatPower>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, PowerUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatPowerMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatPowerMatrix(final Collection<FloatSparseValue<PowerUnit, FloatPower>> data, final int rows, final int cols)
    {
        this(data, PowerUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatPower> getScalarClass()
    {
        return FloatPower.class;
    }

    @Override
    public Class<FloatPowerVector> getVectorClass()
    {
        return FloatPowerVector.class;
    }

    @Override
    public FloatPowerMatrix instantiateMatrix(final FloatMatrixData fmd, final PowerUnit displayUnit)
    {
        return new FloatPowerMatrix(fmd, displayUnit);
    }

    @Override
    public FloatPowerVector instantiateVector(final FloatVectorData fvd, final PowerUnit displayUnit)
    {
        return new FloatPowerVector(fvd, displayUnit);
    }

    @Override
    public FloatPower instantiateScalarSI(final float valueSI, final PowerUnit displayUnit)
    {
        FloatPower result = FloatPower.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
