package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRelWithAbs;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.vector.DurationVector;
import org.djunits.value.vdouble.vector.TimeVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Duration Matrix.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class DurationMatrix extends
        DoubleMatrixRelWithAbs<TimeUnit, Time, TimeVector, TimeMatrix, DurationUnit, Duration, DurationVector, DurationMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a DurationMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public DurationMatrix(final DoubleMatrixData data, final DurationUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a DurationMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DurationMatrix(final double[][] data, final DurationUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a DurationMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public DurationMatrix(final double[][] data, final DurationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a DurationMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DurationMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, DurationUnit.SI, storageType);
    }

    /**
     * Construct a DurationMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public DurationMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Duration[][] */

    /**
     * Construct a DurationMatrix from an array of an array of Duration objects. The Duration values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DurationMatrix(final Duration[][] data, final DurationUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a DurationMatrix from an array of an array of Duration objects. The Duration values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public DurationMatrix(final Duration[][] data, final DurationUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a DurationMatrix from an array of an array of Duration objects. The Duration values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data
     * as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DurationMatrix(final Duration[][] data, final StorageType storageType)
    {
        this(data, DurationUnit.SI, storageType);
    }

    /**
     * Construct a DurationMatrix from an array of an array of Duration objects. The Duration values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public DurationMatrix(final Duration[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a DurationMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DurationMatrix(final Collection<DoubleSparseValue<DurationUnit, Duration>> data, final DurationUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a DurationMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the storage
     * type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public DurationMatrix(final Collection<DoubleSparseValue<DurationUnit, Duration>> data, final DurationUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a DurationMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DurationMatrix(final Collection<DoubleSparseValue<DurationUnit, Duration>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, DurationUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a DurationMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public DurationMatrix(final Collection<DoubleSparseValue<DurationUnit, Duration>> data, final int rows, final int cols)
    {
        this(data, DurationUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<Duration> getScalarClass()
    {
        return Duration.class;
    }

    @Override
    public Class<DurationVector> getVectorClass()
    {
        return DurationVector.class;
    }

    @Override
    public DurationMatrix instantiateMatrix(final DoubleMatrixData dmd, final DurationUnit displayUnit)
    {
        return new DurationMatrix(dmd, displayUnit);
    }

    @Override
    public DurationVector instantiateVector(final DoubleVectorData dvd, final DurationUnit displayUnit)
    {
        return new DurationVector(dvd, displayUnit);
    }

    @Override
    public Duration instantiateScalarSI(final double valueSI, final DurationUnit displayUnit)
    {
        Duration result = Duration.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public TimeMatrix instantiateMatrixAbs(final DoubleMatrixData dmd, final TimeUnit displayUnit)
    {
        return new TimeMatrix(dmd, displayUnit);
    }

    @Override
    public TimeVector instantiateVectorAbs(final DoubleVectorData dvd, final TimeUnit displayUnit)
    {
        return new TimeVector(dvd, displayUnit);
    }

    @Override
    public Time instantiateScalarAbsSI(final double valueSI, final TimeUnit displayUnit)
    {
        Time result = Time.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
