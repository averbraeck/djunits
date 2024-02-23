package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.DensityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatDensity;
import org.djunits.value.vfloat.vector.FloatDensityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatDensityMatrix, a matrix of values with a DensityUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatDensityMatrix extends FloatMatrixRel<DensityUnit, FloatDensity, FloatDensityVector, FloatDensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatDensityMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit DensityUnit; the display unit of the matrix data
     */
    public FloatDensityMatrix(final FloatMatrixData data, final DensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatDensityMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit DensityUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDensityMatrix(final float[][] data, final DensityUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatDensityMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit DensityUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatDensityMatrix(final float[][] data, final DensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDensityMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDensityMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, DensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatDensityMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatDensityMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Density[][] */

    /**
     * Construct a FloatDensityMatrix from an array of an array of FloatDensity objects. The FloatDensity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatDensity[][]; the data for the matrix
     * @param displayUnit DensityUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDensityMatrix(final FloatDensity[][] data, final DensityUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatDensityMatrix from an array of an array of FloatDensity objects. The FloatDensity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatDensity[][]; the data for the matrix
     * @param displayUnit DensityUnit; the display unit of the values when printing
     */
    public FloatDensityMatrix(final FloatDensity[][] data, final DensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDensityMatrix from an array of an array of FloatDensity objects. The FloatDensity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data FloatDensity[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDensityMatrix(final FloatDensity[][] data, final StorageType storageType)
    {
        this(data, DensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatDensityMatrix from an array of an array of FloatDensity objects. The FloatDensity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatDensity[][]; the data for the matrix
     */
    public FloatDensityMatrix(final FloatDensity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit DensityUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDensityMatrix(final Collection<FloatSparseValue<DensityUnit, FloatDensity>> data, final DensityUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit DensityUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatDensityMatrix(final Collection<FloatSparseValue<DensityUnit, FloatDensity>> data, final DensityUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDensityMatrix(final Collection<FloatSparseValue<DensityUnit, FloatDensity>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, DensityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatDensityMatrix(final Collection<FloatSparseValue<DensityUnit, FloatDensity>> data, final int rows,
            final int cols)
    {
        this(data, DensityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDensity> getScalarClass()
    {
        return FloatDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDensityVector> getVectorClass()
    {
        return FloatDensityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDensityMatrix instantiateMatrix(final FloatMatrixData fmd, final DensityUnit displayUnit)
    {
        return new FloatDensityMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDensityVector instantiateVector(final FloatVectorData fvd, final DensityUnit displayUnit)
    {
        return new FloatDensityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDensity instantiateScalarSI(final float valueSI, final DensityUnit displayUnit)
    {
        FloatDensity result = FloatDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
