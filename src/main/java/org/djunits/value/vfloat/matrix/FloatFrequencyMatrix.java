package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.FrequencyUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatFrequency;
import org.djunits.value.vfloat.vector.FloatFrequencyVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatFrequencyMatrix, a matrix of values with a FrequencyUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatFrequencyMatrix
        extends FloatMatrixRel<FrequencyUnit, FloatFrequency, FloatFrequencyVector, FloatFrequencyMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatFrequencyMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit FrequencyUnit; the display unit of the matrix data
     */
    public FloatFrequencyMatrix(final FloatMatrixData data, final FrequencyUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatFrequencyMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit FrequencyUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFrequencyMatrix(final float[][] data, final FrequencyUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatFrequencyMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit FrequencyUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatFrequencyMatrix(final float[][] data, final FrequencyUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatFrequencyMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFrequencyMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, FrequencyUnit.SI, storageType);
    }

    /**
     * Construct a FloatFrequencyMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatFrequencyMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Frequency[][] */

    /**
     * Construct a FloatFrequencyMatrix from an array of an array of FloatFrequency objects. The FloatFrequency values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatFrequency[][]; the data for the matrix
     * @param displayUnit FrequencyUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFrequencyMatrix(final FloatFrequency[][] data, final FrequencyUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatFrequencyMatrix from an array of an array of FloatFrequency objects. The FloatFrequency values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatFrequency[][]; the data for the matrix
     * @param displayUnit FrequencyUnit; the display unit of the values when printing
     */
    public FloatFrequencyMatrix(final FloatFrequency[][] data, final FrequencyUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatFrequencyMatrix from an array of an array of FloatFrequency objects. The FloatFrequency values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data FloatFrequency[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFrequencyMatrix(final FloatFrequency[][] data, final StorageType storageType)
    {
        this(data, FrequencyUnit.SI, storageType);
    }

    /**
     * Construct a FloatFrequencyMatrix from an array of an array of FloatFrequency objects. The FloatFrequency values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatFrequency[][]; the data for the matrix
     */
    public FloatFrequencyMatrix(final FloatFrequency[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatFrequencyMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit FrequencyUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFrequencyMatrix(final Collection<FloatSparseValue<FrequencyUnit, FloatFrequency>> data,
            final FrequencyUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatFrequencyMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit FrequencyUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatFrequencyMatrix(final Collection<FloatSparseValue<FrequencyUnit, FloatFrequency>> data,
            final FrequencyUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatFrequencyMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFrequencyMatrix(final Collection<FloatSparseValue<FrequencyUnit, FloatFrequency>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, FrequencyUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatFrequencyMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatFrequencyMatrix(final Collection<FloatSparseValue<FrequencyUnit, FloatFrequency>> data, final int rows,
            final int cols)
    {
        this(data, FrequencyUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFrequency> getScalarClass()
    {
        return FloatFrequency.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFrequencyVector> getVectorClass()
    {
        return FloatFrequencyVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatFrequencyMatrix instantiateMatrix(final FloatMatrixData fmd, final FrequencyUnit displayUnit)
    {
        return new FloatFrequencyMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFrequencyVector instantiateVector(final FloatVectorData fvd, final FrequencyUnit displayUnit)
    {
        return new FloatFrequencyVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFrequency instantiateScalarSI(final float valueSI, final FrequencyUnit displayUnit)
    {
        FloatFrequency result = FloatFrequency.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
