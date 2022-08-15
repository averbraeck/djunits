package org.djunits.value.vfloat.matrix.data;

import java.util.stream.IntStream;

import org.djunits.Throw;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatFunction2;

/**
 * Stores dense data for a FloatMatrix and carries out basic operations.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatMatrixDataDense extends FloatMatrixData
{
    /** */
    private static final long serialVersionUID = 1L;

    /**
     * Create a matrix with dense data.
     * @param matrixSI float[]; the data to store
     * @param rows int; the number of rows
     * @param cols int; the number of columns
     * @throws ValueRuntimeException in case <code>rows * cols != matrixSI.length</code>
     */
    public FloatMatrixDataDense(final float[] matrixSI, final int rows, final int cols) throws ValueRuntimeException
    {
        super(StorageType.DENSE);
        if (rows * cols != matrixSI.length)
        {
            throw new ValueRuntimeException("FloatMatrixDataDense constructor, rows * cols != matrixSI.length");
        }
        this.matrixSI = new float[matrixSI.length];
        System.arraycopy(matrixSI, 0, this.matrixSI, 0, matrixSI.length);
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Create a matrix with dense data. The float array is of the form d[rows][columns] so each value can be found with
     * f[row][column].
     * @param matrixSI float[][]; the data to store
     * @throws NullPointerException when matrixSI is null
     * @throws ValueRuntimeException in case matrix is ragged
     */
    public FloatMatrixDataDense(final float[][] matrixSI) throws ValueRuntimeException
    {
        super(StorageType.DENSE);
        Throw.whenNull(matrixSI, "DoubleMatrixDataDense constructor, matrixSI == null");
        this.rows = matrixSI.length;
        this.cols = this.rows == 0 ? 0 : matrixSI[0].length;
        this.matrixSI = new float[this.rows * this.cols];
        for (int r = 0; r < this.rows; r++)
        {
            float[] row = matrixSI[r];
            if (row.length != this.cols)
            {
                throw new ValueRuntimeException("FloatMatrixDataDense constructor, ragged matrix");
            }
            System.arraycopy(row, 0, this.matrixSI, r * this.cols, row.length);
        }
    }

    /** {@inheritDoc} */
    @Override
    public final int cardinality()
    {
        // this does not copy the data. See http://stackoverflow.com/questions/23106093/how-to-get-a-stream-from-a-float
        return (int) IntStream.range(0, this.matrixSI.length).parallel().mapToDouble(i -> this.matrixSI[i])
                .filter(d -> d != 0.0).count();
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataDense assign(final FloatFunction floatFunction)
    {
        IntStream.range(0, this.rows() * this.cols()).parallel()
                .forEach(i -> this.matrixSI[i] = floatFunction.apply(this.matrixSI[i]));
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataDense assign(final FloatFunction2 floatFunction, final FloatMatrixData right)
    {
        if (right.isDense())
        {
            FloatMatrixDataDense rightDense = (FloatMatrixDataDense) right;
            IntStream.range(0, this.rows() * this.cols()).parallel()
                    .forEach(i -> this.matrixSI[i] = floatFunction.apply(this.matrixSI[i], rightDense.matrixSI[i]));
        }
        else
        {
            IntStream.range(0, this.rows() * this.cols()).parallel().forEach(
                    i -> this.matrixSI[i] = floatFunction.apply(this.matrixSI[i], right.getSI(i / this.cols, i % this.cols)));
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataDense toDense()
    {
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataSparse toSparse()
    {
        int length = cardinality();
        float[] sparseSI = new float[length];
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
        return new FloatMatrixDataSparse(sparseSI, indices, this.rows, this.cols);
    }

    /** {@inheritDoc} */
    @Override
    public final float getSI(final int row, final int col)
    {
        return this.matrixSI[row * this.cols + col];
    }

    /** {@inheritDoc} */
    @Override
    public final void setSI(final int row, final int col, final float valueSI)
    {
        this.matrixSI[row * this.cols + col] = valueSI;
    }

    /** {@inheritDoc} */
    @Override
    public final float[][] getDenseMatrixSI()
    {
        float[][] matrix = new float[this.rows][];
        for (int r = 0; r < this.rows; r++)
        {
            float[] row = new float[this.cols];
            System.arraycopy(this.matrixSI, r * this.cols, row, 0, row.length);
            matrix[r] = row;
        }
        return matrix;
    }

    /** {@inheritDoc} */
    @Override
    public final double[][] getDoubleDenseMatrixSI()
    {
        double[][] matrix = new double[this.rows][];
        for (int r = 0; r < this.rows; r++)
        {
            double[] row = new double[this.cols];
            int offset = r * this.cols;
            for (int c = 0; c < this.cols; c++)
            {
                row[c] = this.matrixSI[offset++];
            }
            matrix[r] = row;
        }
        return matrix;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataDense copy()
    {
        try
        {
            return new FloatMatrixDataDense(getDenseMatrixSI());
        }
        catch (ValueRuntimeException exception)
        {
            throw new RuntimeException(exception); // should not happen -- original is not ragged...
        }
    }

    /** {@inheritDoc} */
    @Override
    public FloatMatrixData plus(final FloatMatrixData right) throws ValueRuntimeException
    {
        checkSizes(right);
        float[] fm = new float[this.rows * this.cols];
        if (right.isDense())
        {
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols).forEach(
                    c -> fm[r * this.cols + c] = this.matrixSI[r * this.cols + c] + right.matrixSI[r * this.cols + c]));
        }
        else
        { // right is sparse
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols)
                    .forEach(c -> fm[r * this.cols + c] = this.matrixSI[r * this.cols + c] + right.getSI(r, c)));
        }
        return new FloatMatrixDataDense(fm, this.rows, this.cols);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataDense minus(final FloatMatrixData right)
    {
        checkSizes(right);
        float[] fm = new float[this.rows * this.cols];
        if (right.isDense())
        {
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols).forEach(
                    c -> fm[r * this.cols + c] = this.matrixSI[r * this.cols + c] - right.matrixSI[r * this.cols + c]));
        }
        else
        { // right is sparse
            IntStream.range(0, this.rows).parallel().forEach(r -> IntStream.range(0, this.cols)
                    .forEach(c -> fm[r * this.cols + c] = this.matrixSI[r * this.cols + c] - right.getSI(r, c)));
        }
        return new FloatMatrixDataDense(fm, this.rows, this.cols);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMatrixData times(final FloatMatrixData right) throws ValueRuntimeException
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
    public FloatMatrixData divide(final FloatMatrixData right) throws ValueRuntimeException
    {
        checkSizes(right);
        return this.copy().divideBy(right);
    }

}
