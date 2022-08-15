package org.djunits.value.vdouble.matrix.data;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.djunits.Throw;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleFunction2;

/**
 * Stores dense data for a DoubleMatrix and carries out basic operations.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class DoubleMatrixDataDense extends DoubleMatrixData
{
    /** */
    private static final long serialVersionUID = 1L;

    /**
     * Create a matrix with dense data.
     * @param matrixSI double[]; the data to store
     * @param rows int; the number of rows
     * @param cols int; the number of columns
     * @throws ValueRuntimeException in case <code>rows * cols != matrixSI.length</code>
     */
    public DoubleMatrixDataDense(final double[] matrixSI, final int rows, final int cols) throws ValueRuntimeException
    {
        super(StorageType.DENSE);
        if (rows * cols != matrixSI.length)
        {
            throw new ValueRuntimeException("DoubleMatrixDataDense constructor, rows * cols != matrixSI.length");
        }
        this.matrixSI = new double[matrixSI.length];
        System.arraycopy(matrixSI, 0, this.matrixSI, 0, matrixSI.length);
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Create a matrix with dense data. The double array is of the form d[rows][columns] so each value can be found with
     * d[row][column].
     * @param matrixSI double[][]; the data to store
     * @throws NullPointerException when matrixSI is null
     * @throws ValueRuntimeException in case matrix is ragged
     */
    public DoubleMatrixDataDense(final double[][] matrixSI) throws ValueRuntimeException
    {
        super(StorageType.DENSE);
        Throw.whenNull(matrixSI, "DoubleMatrixDataDense constructor, matrixSI == null");
        this.rows = matrixSI.length;
        this.cols = this.rows == 0 ? 0 : matrixSI[0].length;
        this.matrixSI = new double[this.rows * this.cols];
        for (int r = 0; r < this.rows; r++)
        {
            double[] row = matrixSI[r];
            if (row.length != this.cols)
            {
                throw new ValueRuntimeException("DoubleMatrixDataDense constructor, ragged matrix");
            }
            System.arraycopy(row, 0, this.matrixSI, r * this.cols, row.length);
        }
    }

    /** {@inheritDoc} */
    @Override
    public final int cardinality()
    {
        return (int) Arrays.stream(this.matrixSI).parallel().filter(d -> d != 0.0).count();
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleMatrixDataDense assign(final DoubleFunction doubleFunction)
    {
        IntStream.range(0, this.rows() * this.cols()).parallel()
                .forEach(i -> this.matrixSI[i] = doubleFunction.apply(this.matrixSI[i]));
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleMatrixDataDense assign(final DoubleFunction2 doubleFunction, final DoubleMatrixData right)
    {
        if (right.isDense())
        {
            DoubleMatrixDataDense rightDense = (DoubleMatrixDataDense) right;
            IntStream.range(0, this.rows() * this.cols()).parallel()
                    .forEach(i -> this.matrixSI[i] = doubleFunction.apply(this.matrixSI[i], rightDense.matrixSI[i]));
        }
        else
        {
            IntStream.range(0, this.rows() * this.cols()).parallel().forEach(
                    i -> this.matrixSI[i] = doubleFunction.apply(this.matrixSI[i], right.getSI(i / this.cols, i % this.cols)));
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleMatrixDataDense toDense()
    {
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleMatrixDataSparse toSparse()
    {
        int length = cardinality();
        double[] sparseSI = new double[length];
        long[] indices = new long[length];
        int count = 0;
        for (int r = 0; r < this.rows; r++)
        {
            for (int c = 0; c < this.cols; c++)
            {
                int index = r * this.cols + c;
                if (this.matrixSI[index] != 0.0)
                {
                    sparseSI[count] = this.matrixSI[index];
                    indices[count] = index;
                    count++;
                }
            }
        }
        return new DoubleMatrixDataSparse(sparseSI, indices, this.rows, this.cols);
    }

    /** {@inheritDoc} */
    @Override
    public final double getSI(final int row, final int col)
    {
        return this.matrixSI[row * this.cols + col];
    }

    /** {@inheritDoc} */
    @Override
    public final void setSI(final int row, final int col, final double valueSI)
    {
        this.matrixSI[row * this.cols + col] = valueSI;
    }

    /** {@inheritDoc} */
    @Override
    public final double[][] getDenseMatrixSI()
    {
        double[][] matrix = new double[this.rows][];
        for (int r = 0; r < this.rows; r++)
        {
            double[] row = new double[this.cols];
            System.arraycopy(this.matrixSI, r * this.cols, row, 0, row.length);
            matrix[r] = row;
        }
        return matrix;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleMatrixDataDense copy()
    {
        try
        {
            return new DoubleMatrixDataDense(getDenseMatrixSI());
        }
        catch (ValueRuntimeException exception)
        {
            throw new RuntimeException(exception); // should not happen -- original is not ragged...
        }
    }

    /** {@inheritDoc} */
    @Override
    public DoubleMatrixData plus(final DoubleMatrixData right) throws ValueRuntimeException
    {
        checkSizes(right);
        double[] dm = new double[this.rows * this.cols];
        if (right.isDense())
        {
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols).forEach(
                    c -> dm[r * this.cols + c] = this.matrixSI[r * this.cols + c] + right.matrixSI[r * this.cols + c]));
        }
        else
        { // right is sparse
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols)
                    .forEach(c -> dm[r * this.cols + c] = this.matrixSI[r * this.cols + c] + right.getSI(r, c)));
        }
        return new DoubleMatrixDataDense(dm, this.rows, this.cols);
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleMatrixDataDense minus(final DoubleMatrixData right)
    {
        checkSizes(right);
        double[] dm = new double[this.rows * this.cols];
        if (right.isDense())
        {
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols).forEach(
                    c -> dm[r * this.cols + c] = this.matrixSI[r * this.cols + c] - right.matrixSI[r * this.cols + c]));
        }
        else
        { // right is sparse
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols)
                    .forEach(c -> dm[r * this.cols + c] = this.matrixSI[r * this.cols + c] - right.getSI(r, c)));
        }
        return new DoubleMatrixDataDense(dm, this.rows, this.cols);
    }

    /** {@inheritDoc} */
    @Override
    public DoubleMatrixData times(final DoubleMatrixData right) throws ValueRuntimeException
    {
        if (right.isSparse())
        {
            // result shall be sparse
            return right.times(this);
        }
        // Both are dense
        checkSizes(right);
        return this.copy().multiplyBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public DoubleMatrixData divide(final DoubleMatrixData right) throws ValueRuntimeException
    {
        checkSizes(right);
        double[] dm = new double[this.rows * this.cols];
        if (right.isDense())
        {
            IntStream.range(0, this.rows * this.cols).parallel().forEach(i -> dm[i] = this.matrixSI[i] / right.matrixSI[i]);
        }
        else
        {
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols)
                    .forEach(c -> dm[r * this.cols + c] = this.matrixSI[r * this.cols + c] / right.getSI(r, c)));
        }
        return new DoubleMatrixDataDense(dm, this.rows, this.cols);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "DoubleMatrixDataDense [storageType=" + getStorageType() + ", matrixSI=" + Arrays.toString(this.matrixSI) + "]";
    }

}
