package org.djunits.old.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.old.unit.DurationUnit;
import org.djunits.old.unit.TimeUnit;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vfloat.matrix.base.FloatMatrixRelWithAbs;
import org.djunits.old.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.old.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.old.value.vfloat.scalar.FloatDuration;
import org.djunits.old.value.vfloat.scalar.FloatTime;
import org.djunits.old.value.vfloat.vector.FloatDurationVector;
import org.djunits.old.value.vfloat.vector.FloatTimeVector;
import org.djunits.old.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatDuration Matrix.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatDurationMatrix extends FloatMatrixRelWithAbs<TimeUnit, FloatTime, FloatTimeVector, FloatTimeMatrix,
        DurationUnit, FloatDuration, FloatDurationVector, FloatDurationMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a FloatDurationMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatDurationMatrix(final FloatMatrixData data, final DurationUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatDurationMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDurationMatrix(final float[][] data, final DurationUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatDurationMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatDurationMatrix(final float[][] data, final DurationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDurationMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDurationMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, DurationUnit.SI, storageType);
    }

    /**
     * Construct a FloatDurationMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatDurationMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatDuration[][] */

    /**
     * Construct a FloatDurationMatrix from an array of an array of FloatDuration objects. The FloatDuration values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDurationMatrix(final FloatDuration[][] data, final DurationUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatDurationMatrix from an array of an array of FloatDuration objects. The FloatDuration values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatDurationMatrix(final FloatDuration[][] data, final DurationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDurationMatrix from an array of an array of FloatDuration objects. The FloatDuration values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDurationMatrix(final FloatDuration[][] data, final StorageType storageType)
    {
        this(data, DurationUnit.SI, storageType);
    }

    /**
     * Construct a FloatDurationMatrix from an array of an array of FloatDuration objects. The FloatDuration values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatDurationMatrix(final FloatDuration[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatDurationMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDurationMatrix(final Collection<FloatSparseValue<DurationUnit, FloatDuration>> data,
            final DurationUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatDurationMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatDurationMatrix(final Collection<FloatSparseValue<DurationUnit, FloatDuration>> data,
            final DurationUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatDurationMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatDurationMatrix(final Collection<FloatSparseValue<DurationUnit, FloatDuration>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, DurationUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatDurationMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatDurationMatrix(final Collection<FloatSparseValue<DurationUnit, FloatDuration>> data, final int rows,
            final int cols)
    {
        this(data, DurationUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatDuration> getScalarClass()
    {
        return FloatDuration.class;
    }

    @Override
    public Class<FloatDurationVector> getVectorClass()
    {
        return FloatDurationVector.class;
    }

    @Override
    public FloatDurationMatrix instantiateMatrix(final FloatMatrixData fmd, final DurationUnit displayUnit)
    {
        return new FloatDurationMatrix(fmd, displayUnit);
    }

    @Override
    public FloatDurationVector instantiateVector(final FloatVectorData fvd, final DurationUnit displayUnit)
    {
        return new FloatDurationVector(fvd, displayUnit);
    }

    @Override
    public FloatDuration instantiateScalarSI(final float valueSI, final DurationUnit displayUnit)
    {
        FloatDuration result = FloatDuration.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public FloatTimeMatrix instantiateMatrixAbs(final FloatMatrixData fmd, final TimeUnit displayUnit)
    {
        return new FloatTimeMatrix(fmd, displayUnit);
    }

    @Override
    public FloatTimeVector instantiateVectorAbs(final FloatVectorData fvd, final TimeUnit displayUnit)
    {
        return new FloatTimeVector(fvd, displayUnit);
    }

    @Override
    public FloatTime instantiateScalarAbsSI(final float valueSI, final TimeUnit displayUnit)
    {
        FloatTime result = FloatTime.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
