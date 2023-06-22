package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixAbs;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.vector.DurationVector;
import org.djunits.value.vdouble.vector.TimeVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Time Matrix.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-22T21:38:04.910968700Z")
public class TimeMatrix
        extends DoubleMatrixAbs<TimeUnit, Time, TimeVector, TimeMatrix, DurationUnit, Duration, DurationVector, DurationMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a TimeMatrix from an internal data object.
     * @param data DoubleMatrixData; the internal data object for the matrix
     * @param displayUnit TimeUnit; the display unit of the matrix data
     */
    public TimeMatrix(final DoubleMatrixData data, final TimeUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a TimeMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be printed
     * using the displayUnit.
     * @param data double[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit TimeUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public TimeMatrix(final double[][] data, final TimeUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a TimeMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix
     * @param displayUnit TimeUnit; the unit of the values in the data array, and display unit when printing
     */
    public TimeMatrix(final double[][] data, final TimeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a TimeMatrix from a double[][] object with SI-unit values.
     * @param data double[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public TimeMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, TimeUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a TimeMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we offer
     * the data as an array of an array.
     * @param data double[][]; the data for the matrix, in SI units
     */
    public TimeMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Time[][] */

    /**
     * Construct a TimeMatrix from an array of an array of Time objects. The Time values are each expressed in their own unit,
     * but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data Time[][]; the data for the matrix
     * @param displayUnit TimeUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public TimeMatrix(final Time[][] data, final TimeUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a TimeMatrix from an array of an array of Time objects. The Time values are each expressed in their own unit,
     * but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array of an array.
     * @param data Time[][]; the data for the matrix
     * @param displayUnit TimeUnit; the display unit of the values when printing
     */
    public TimeMatrix(final Time[][] data, final TimeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a TimeMatrix from an array of an array of Time objects. The Time values are each expressed in their own unit,
     * but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as an
     * array of an array.
     * @param data Time[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public TimeMatrix(final Time[][] data, final StorageType storageType)
    {
        this(data, TimeUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a TimeMatrix from an array of an array of Time objects. The Time values are each expressed in their own unit,
     * but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data Time[][]; the data for the matrix
     */
    public TimeMatrix(final Time[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a TimeMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit TimeUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public TimeMatrix(final Collection<DoubleSparseValue<TimeUnit, Time>> data, final TimeUnit displayUnit, final int rows,
            final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a TimeMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the storage
     * type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit TimeUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public TimeMatrix(final Collection<DoubleSparseValue<TimeUnit, Time>> data, final TimeUnit displayUnit, final int rows,
            final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a TimeMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public TimeMatrix(final Collection<DoubleSparseValue<TimeUnit, Time>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, TimeUnit.BASE.getStandardUnit(), rows, cols, storageType);
    }

    /**
     * Construct a TimeMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public TimeMatrix(final Collection<DoubleSparseValue<TimeUnit, Time>> data, final int rows, final int cols)
    {
        this(data, TimeUnit.BASE.getStandardUnit(), rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Time> getScalarClass()
    {
        return Time.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<TimeVector> getVectorClass()
    {
        return TimeVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public TimeMatrix instantiateMatrix(final DoubleMatrixData dmd, final TimeUnit displayUnit)
    {
        return new TimeMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public TimeVector instantiateVector(final DoubleVectorData dvd, final TimeUnit displayUnit)
    {
        return new TimeVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Time instantiateScalarSI(final double valueSI, final TimeUnit displayUnit)
    {
        Time result = Time.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public DurationMatrix instantiateMatrixRel(final DoubleMatrixData dmd, final DurationUnit displayUnit)
    {
        return new DurationMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public DurationVector instantiateVectorRel(final DoubleVectorData dvd, final DurationUnit displayUnit)
    {
        return new DurationVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Duration instantiateScalarRelSI(final double valueSI, final DurationUnit displayUnit)
    {
        Duration result = Duration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
