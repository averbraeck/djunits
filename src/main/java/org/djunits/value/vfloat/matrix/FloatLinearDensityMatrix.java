package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.LinearDensityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatLinearDensity;
import org.djunits.value.vfloat.vector.FloatLinearDensityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatLinearDensityMatrix, a matrix of values with a LinearDensityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T11:42:31.564730700Z")
public class FloatLinearDensityMatrix
        extends FloatMatrixRel<LinearDensityUnit, FloatLinearDensity, FloatLinearDensityVector, FloatLinearDensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatLinearDensityMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatLinearDensityMatrix(final FloatMatrixData data, final LinearDensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatLinearDensityMatrix from a float[][] object. The float values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLinearDensityMatrix(final float[][] data, final LinearDensityUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatLinearDensityMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatLinearDensityMatrix(final float[][] data, final LinearDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatLinearDensityMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLinearDensityMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatLinearDensityMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatLinearDensityMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH LinearDensity[][] */

    /**
     * Construct a FloatLinearDensityMatrix from an array of an array of FloatLinearDensity objects. The FloatLinearDensity
     * values are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit
     * when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLinearDensityMatrix(final FloatLinearDensity[][] data, final LinearDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatLinearDensityMatrix from an array of an array of FloatLinearDensity objects. The FloatLinearDensity
     * values are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit
     * when printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatLinearDensityMatrix(final FloatLinearDensity[][] data, final LinearDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatLinearDensityMatrix from an array of an array of FloatLinearDensity objects. The FloatLinearDensity
     * values are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units
     * when printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLinearDensityMatrix(final FloatLinearDensity[][] data, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatLinearDensityMatrix from an array of an array of FloatLinearDensity objects. The FloatLinearDensity
     * values are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units
     * when printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatLinearDensityMatrix(final FloatLinearDensity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatLinearDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLinearDensityMatrix(final Collection<FloatSparseValue<LinearDensityUnit, FloatLinearDensity>> data,
            final LinearDensityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatLinearDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume
     * the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatLinearDensityMatrix(final Collection<FloatSparseValue<LinearDensityUnit, FloatLinearDensity>> data,
            final LinearDensityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatLinearDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLinearDensityMatrix(final Collection<FloatSparseValue<LinearDensityUnit, FloatLinearDensity>> data,
            final int rows, final int cols, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatLinearDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatLinearDensityMatrix(final Collection<FloatSparseValue<LinearDensityUnit, FloatLinearDensity>> data,
            final int rows, final int cols)
    {
        this(data, LinearDensityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatLinearDensity> getScalarClass()
    {
        return FloatLinearDensity.class;
    }

    @Override
    public Class<FloatLinearDensityVector> getVectorClass()
    {
        return FloatLinearDensityVector.class;
    }

    @Override
    public FloatLinearDensityMatrix instantiateMatrix(final FloatMatrixData fmd, final LinearDensityUnit displayUnit)
    {
        return new FloatLinearDensityMatrix(fmd, displayUnit);
    }

    @Override
    public FloatLinearDensityVector instantiateVector(final FloatVectorData fvd, final LinearDensityUnit displayUnit)
    {
        return new FloatLinearDensityVector(fvd, displayUnit);
    }

    @Override
    public FloatLinearDensity instantiateScalarSI(final float valueSI, final LinearDensityUnit displayUnit)
    {
        FloatLinearDensity result = FloatLinearDensity.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
