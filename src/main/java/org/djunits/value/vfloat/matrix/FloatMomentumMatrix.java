package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.MomentumUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatMomentum;
import org.djunits.value.vfloat.vector.FloatMomentumVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatMomentumMatrix, a matrix of values with a MomentumUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-22T21:38:04.910968700Z")
public class FloatMomentumMatrix extends FloatMatrixRel<MomentumUnit, FloatMomentum, FloatMomentumVector, FloatMomentumMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatMomentumMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit MomentumUnit; the display unit of the matrix data
     */
    public FloatMomentumMatrix(final FloatMatrixData data, final MomentumUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatMomentumMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit MomentumUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMomentumMatrix(final float[][] data, final MomentumUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatMomentumMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit MomentumUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatMomentumMatrix(final float[][] data, final MomentumUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatMomentumMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMomentumMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, MomentumUnit.SI, storageType);
    }

    /**
     * Construct a FloatMomentumMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatMomentumMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Momentum[][] */

    /**
     * Construct a FloatMomentumMatrix from an array of an array of FloatMomentum objects. The FloatMomentum values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatMomentum[][]; the data for the matrix
     * @param displayUnit MomentumUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMomentumMatrix(final FloatMomentum[][] data, final MomentumUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatMomentumMatrix from an array of an array of FloatMomentum objects. The FloatMomentum values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatMomentum[][]; the data for the matrix
     * @param displayUnit MomentumUnit; the display unit of the values when printing
     */
    public FloatMomentumMatrix(final FloatMomentum[][] data, final MomentumUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatMomentumMatrix from an array of an array of FloatMomentum objects. The FloatMomentum values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data FloatMomentum[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMomentumMatrix(final FloatMomentum[][] data, final StorageType storageType)
    {
        this(data, MomentumUnit.SI, storageType);
    }

    /**
     * Construct a FloatMomentumMatrix from an array of an array of FloatMomentum objects. The FloatMomentum values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatMomentum[][]; the data for the matrix
     */
    public FloatMomentumMatrix(final FloatMomentum[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatMomentumMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit MomentumUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMomentumMatrix(final Collection<FloatSparseValue<MomentumUnit, FloatMomentum>> data,
            final MomentumUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatMomentumMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit MomentumUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatMomentumMatrix(final Collection<FloatSparseValue<MomentumUnit, FloatMomentum>> data,
            final MomentumUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatMomentumMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMomentumMatrix(final Collection<FloatSparseValue<MomentumUnit, FloatMomentum>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, MomentumUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatMomentumMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatMomentumMatrix(final Collection<FloatSparseValue<MomentumUnit, FloatMomentum>> data, final int rows,
            final int cols)
    {
        this(data, MomentumUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMomentum> getScalarClass()
    {
        return FloatMomentum.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMomentumVector> getVectorClass()
    {
        return FloatMomentumVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatMomentumMatrix instantiateMatrix(final FloatMatrixData fmd, final MomentumUnit displayUnit)
    {
        return new FloatMomentumMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMomentumVector instantiateVector(final FloatVectorData fvd, final MomentumUnit displayUnit)
    {
        return new FloatMomentumVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMomentum instantiateScalarSI(final float valueSI, final MomentumUnit displayUnit)
    {
        FloatMomentum result = FloatMomentum.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
