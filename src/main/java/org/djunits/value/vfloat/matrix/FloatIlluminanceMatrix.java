package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.IlluminanceUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatIlluminance;
import org.djunits.value.vfloat.vector.FloatIlluminanceVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatIlluminanceMatrix, a matrix of values with a IlluminanceUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatIlluminanceMatrix
        extends FloatMatrixRel<IlluminanceUnit, FloatIlluminance, FloatIlluminanceVector, FloatIlluminanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatIlluminanceMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatIlluminanceMatrix(final FloatMatrixData data, final IlluminanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatIlluminanceMatrix from a float[][] object. The float values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatIlluminanceMatrix(final float[][] data, final IlluminanceUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatIlluminanceMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatIlluminanceMatrix(final float[][] data, final IlluminanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatIlluminanceMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatIlluminanceMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, IlluminanceUnit.SI, storageType);
    }

    /**
     * Construct a FloatIlluminanceMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatIlluminanceMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Illuminance[][] */

    /**
     * Construct a FloatIlluminanceMatrix from an array of an array of FloatIlluminance objects. The FloatIlluminance values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatIlluminanceMatrix(final FloatIlluminance[][] data, final IlluminanceUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatIlluminanceMatrix from an array of an array of FloatIlluminance objects. The FloatIlluminance values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatIlluminanceMatrix(final FloatIlluminance[][] data, final IlluminanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatIlluminanceMatrix from an array of an array of FloatIlluminance objects. The FloatIlluminance values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatIlluminanceMatrix(final FloatIlluminance[][] data, final StorageType storageType)
    {
        this(data, IlluminanceUnit.SI, storageType);
    }

    /**
     * Construct a FloatIlluminanceMatrix from an array of an array of FloatIlluminance objects. The FloatIlluminance values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatIlluminanceMatrix(final FloatIlluminance[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatIlluminanceMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatIlluminanceMatrix(final Collection<FloatSparseValue<IlluminanceUnit, FloatIlluminance>> data,
            final IlluminanceUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatIlluminanceMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatIlluminanceMatrix(final Collection<FloatSparseValue<IlluminanceUnit, FloatIlluminance>> data,
            final IlluminanceUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatIlluminanceMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatIlluminanceMatrix(final Collection<FloatSparseValue<IlluminanceUnit, FloatIlluminance>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, IlluminanceUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatIlluminanceMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatIlluminanceMatrix(final Collection<FloatSparseValue<IlluminanceUnit, FloatIlluminance>> data, final int rows,
            final int cols)
    {
        this(data, IlluminanceUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatIlluminance> getScalarClass()
    {
        return FloatIlluminance.class;
    }

    @Override
    public Class<FloatIlluminanceVector> getVectorClass()
    {
        return FloatIlluminanceVector.class;
    }

    @Override
    public FloatIlluminanceMatrix instantiateMatrix(final FloatMatrixData fmd, final IlluminanceUnit displayUnit)
    {
        return new FloatIlluminanceMatrix(fmd, displayUnit);
    }

    @Override
    public FloatIlluminanceVector instantiateVector(final FloatVectorData fvd, final IlluminanceUnit displayUnit)
    {
        return new FloatIlluminanceVector(fvd, displayUnit);
    }

    @Override
    public FloatIlluminance instantiateScalarSI(final float valueSI, final IlluminanceUnit displayUnit)
    {
        FloatIlluminance result = FloatIlluminance.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
