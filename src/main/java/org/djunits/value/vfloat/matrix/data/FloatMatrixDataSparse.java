package org.djunits.value.vfloat.matrix.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.djunits.Throw;
import org.djunits.unit.Unit;
import org.djunits.unit.scale.Scale;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatFunction2;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.scalar.base.FloatScalarInterface;

/**
 * Stores sparse data for a FloatMatrix and carries out basic operations. The index in the sparse matrix data is calculated as
 * <code>r * columns + c</code>, where r is the row number, cols is the total number of columns, and c is the column number.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatMatrixDataSparse extends FloatMatrixData
{
    /** */
    private static final long serialVersionUID = 1L;

    /** the index values of the Matrix. */
    private long[] indices;

    /**
     * Create a matrix with sparse data.
     * @param matrixSI float[]; the data to store
     * @param indices long[]; the index values of the Matrix, with &lt;tt&gt;index = row * cols + col&lt;/tt&gt;
     * @param rows int; the number of rows
     * @param cols int; the number of columns
     */
    public FloatMatrixDataSparse(final float[] matrixSI, final long[] indices, final int rows, final int cols)
    {
        super(StorageType.SPARSE);
        this.matrixSI = matrixSI;
        this.indices = indices;
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Create a matrix with sparse data.
     * @param dataSI Collection&lt;FloatSparseValue&lt;U, S&gt;&gt;; the sparse [X, Y, SI] values to store
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @throws NullPointerException when storageType is null or dataSI is null
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    public <U extends Unit<U>, S extends FloatScalarInterface<U, S>> FloatMatrixDataSparse(
            final Collection<FloatSparseValue<U, S>> dataSI, final int rows, final int cols) throws NullPointerException
    {
        super(StorageType.SPARSE);
        Throw.whenNull(dataSI, "matrixSI is null");

        int length = (int) dataSI.stream().parallel().filter(d -> d.getValueSI() != 0.0).count();
        this.rows = rows;
        this.cols = cols;
        this.matrixSI = new float[length];
        this.indices = new long[length];
        int index = 0;
        for (FloatSparseValue<U, S> data : dataSI)
        {
            if (data.getValueSI() != 0.0)
            {
                this.indices[index] = data.getRow() * this.cols + data.getColumn();
                this.matrixSI[index] = data.getValueSI();
                index++;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public final int cardinality()
    {
        return this.indices.length;
    }

    /**
     * Fill the sparse data structures matrixSI[] and indices[]. Note: output vectors have to be initialized at the right size.
     * Cannot be parallelized because of stateful and sequence-sensitive count.
     * @param data float[][]; the input data
     * @param matrixSI float[]; the matrix data to write
     * @param indices long[]; the indices to write
     * @throws ValueRuntimeException in case matrix is ragged
     */
    @SuppressWarnings("checkstyle:finalparameters")
    private static void fill(final float[][] data, float[] matrixSI, long[] indices) throws ValueRuntimeException
    {
        int rows = data.length;
        int cols = rows == 0 ? 0 : data[0].length;
        if (cols == 0)
        {
            rows = 0;
        }
        int count = 0;
        for (int r = 0; r < rows; r++)
        {
            float[] row = data[r];
            // Row length check has been done by checkRectangularAndNonEmpty
            for (int c = 0; c < cols; c++)
            {
                int index = r * cols + c;
                if (row[c] != 0.0)
                {
                    matrixSI[count] = row[c];
                    indices[count] = index;
                    count++;
                }
            }
        }
    }

    /**
     * Fill the sparse data structures matrixSI[] and indices[]. Note: output vectors have to be initialized at the right size.
     * Cannot be parallelized because of stateful and sequence-sensitive count.
     * @param data float[][]; the input data
     * @param matrixSI float[]; the matrix data to write
     * @param indices long[]; the indices to write
     * @param scale Scale; Scale, the scale that will convert the data to SI
     * @throws ValueRuntimeException in case matrix is ragged
     */
    @SuppressWarnings("checkstyle:finalparameters")
    private static void fill(final float[][] data, float[] matrixSI, long[] indices, final Scale scale)
            throws ValueRuntimeException
    {
        int rows = data.length;
        int cols = rows == 0 ? 0 : data[0].length;
        if (cols == 0)
        {
            rows = 0;
        }
        int count = 0;
        for (int r = 0; r < rows; r++)
        {
            float[] row = data[r];
            // Row length check has been done by checkRectangularAndNonEmpty
            for (int c = 0; c < cols; c++)
            {
                int index = r * cols + c;
                float value = (float) scale.toStandardUnit(row[c]);
                if (value != 0.0)
                {
                    matrixSI[count] = value;
                    indices[count] = index;
                    count++;
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public FloatMatrixData assign(final FloatFunction floatFunction)
    {
        if (floatFunction.apply(0f) != 0f)
        {
            // It is most unlikely that the result AND the left and right operands are efficiently stored in Sparse format
            FloatMatrixDataSparse result = toDense().assign(floatFunction).toSparse();
            this.indices = result.indices;
            this.matrixSI = result.matrixSI;
            return this;
        }
        // The code below relies on the fact that floatFunction.apply(0f) yields 0f
        int currentSize = rows() * cols();
        if (currentSize > 16)
        {
            currentSize = 16;
        }
        long[] newIndices = new long[currentSize];
        float[] newValues = new float[currentSize];
        int nonZeroValues = 0;
        int ownIndex = 0;
        while (ownIndex < this.indices.length)
        {
            long index = this.indices[ownIndex];
            float value = floatFunction.apply(this.matrixSI[ownIndex]);
            ownIndex++;
            if (value != 0f)
            {
                if (nonZeroValues >= currentSize)
                {
                    // increase size of arrays
                    currentSize *= 2;
                    if (currentSize > rows() * cols())
                    {
                        currentSize = rows() * cols();
                    }
                    long[] newNewIndices = new long[currentSize];
                    System.arraycopy(newIndices, 0, newNewIndices, 0, newIndices.length);
                    newIndices = newNewIndices;
                    float[] newNewValues = new float[currentSize];
                    System.arraycopy(newValues, 0, newNewValues, 0, newValues.length);
                    newValues = newNewValues;
                }
                newIndices[nonZeroValues] = index;
                newValues[nonZeroValues] = value;
                nonZeroValues++;
            }
        }
        if (nonZeroValues < currentSize)
        {
            // reduce size of arrays
            long[] newNewIndices = new long[nonZeroValues];
            System.arraycopy(newIndices, 0, newNewIndices, 0, nonZeroValues);
            newIndices = newNewIndices;
            float[] newNewValues = new float[nonZeroValues];
            System.arraycopy(newValues, 0, newNewValues, 0, nonZeroValues);
            newValues = newNewValues;
        }
        this.indices = newIndices;
        this.matrixSI = newValues;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataSparse assign(final FloatFunction2 floatFunction, final FloatMatrixData right)
    {
        checkSizes(right);
        int currentSize = rows() * cols();
        if (currentSize > 16)
        {
            currentSize = 16;
        }
        long[] newIndices = new long[currentSize];
        float[] newValues = new float[currentSize];
        int nonZeroValues = 0;
        int ownIndex = 0;
        int otherIndex = 0;
        if (right.isSparse())
        { // both are sparse, result must be sparse
            FloatMatrixDataSparse other = (FloatMatrixDataSparse) right;
            while (ownIndex < this.indices.length || otherIndex < other.indices.length)
            {
                float value;
                long index;
                if (ownIndex < this.indices.length && otherIndex < other.indices.length)
                { // neither we nor right has run out of values
                    if (this.indices[ownIndex] == other.indices[otherIndex])
                    {
                        value = floatFunction.apply(this.matrixSI[ownIndex], other.matrixSI[otherIndex]);
                        index = this.indices[ownIndex];
                        ownIndex++;
                        otherIndex++;
                    }
                    else if (this.indices[ownIndex] < other.indices[otherIndex])
                    {
                        // we have a non-zero; right has a zero
                        value = floatFunction.apply(this.matrixSI[ownIndex], 0.0f);
                        index = this.indices[ownIndex];
                        ownIndex++;
                    }
                    else
                    {
                        // we have a zero; right has a non-zero
                        value = floatFunction.apply(0.0f, other.matrixSI[otherIndex]);
                        index = other.indices[otherIndex];
                        otherIndex++;
                    }
                }
                else if (ownIndex < this.indices.length)
                { // right has run out of values; we have not
                    value = floatFunction.apply(this.matrixSI[ownIndex], 0f);
                    index = this.indices[ownIndex];
                    ownIndex++;
                }
                else
                { // we have run out of values; right has not
                    value = floatFunction.apply(0.0f, other.matrixSI[otherIndex]);
                    index = other.indices[otherIndex];
                    otherIndex++;
                }
                if (value != 0f)
                {
                    if (nonZeroValues >= currentSize)
                    {
                        // increase size of arrays
                        currentSize *= 2;
                        if (currentSize > rows() * cols())
                        {
                            currentSize = rows() * cols();
                        }
                        long[] newNewIndices = new long[currentSize];
                        System.arraycopy(newIndices, 0, newNewIndices, 0, newIndices.length);
                        newIndices = newNewIndices;
                        float[] newNewValues = new float[currentSize];
                        System.arraycopy(newValues, 0, newNewValues, 0, newValues.length);
                        newValues = newNewValues;
                    }
                    newIndices[nonZeroValues] = index;
                    newValues[nonZeroValues] = value;
                    nonZeroValues++;
                }
            }
        }
        else
        { // we are sparse; other is dense, result must be sparse
            FloatMatrixDataDense other = (FloatMatrixDataDense) right;
            while (otherIndex < right.matrixSI.length)
            {
                float value;
                int index = otherIndex;
                if (ownIndex < this.indices.length)
                { // neither we nor right has run out of values
                    if (this.indices[ownIndex] == otherIndex)
                    {
                        value = floatFunction.apply(this.matrixSI[ownIndex], other.matrixSI[otherIndex]);
                        ownIndex++;
                    }
                    else
                    {
                        // we have a zero; other has a value
                        value = floatFunction.apply(0.0f, other.matrixSI[otherIndex]);
                    }
                    otherIndex++;
                }
                else
                { // we have run out of values; right has not
                    value = floatFunction.apply(0.0f, other.matrixSI[otherIndex]);
                    otherIndex++;
                }
                if (value != 0f)
                {
                    if (nonZeroValues >= currentSize)
                    {
                        // increase size of arrays
                        currentSize *= 2;
                        if (currentSize > rows() * cols())
                        {
                            currentSize = rows() * cols();
                        }
                        long[] newNewIndices = new long[currentSize];
                        System.arraycopy(newIndices, 0, newNewIndices, 0, newIndices.length);
                        newIndices = newNewIndices;
                        float[] newNewValues = new float[currentSize];
                        System.arraycopy(newValues, 0, newNewValues, 0, newValues.length);
                        newValues = newNewValues;
                    }
                    newIndices[nonZeroValues] = index;
                    newValues[nonZeroValues] = value;
                    nonZeroValues++;
                }
            }
        }
        if (nonZeroValues < currentSize)
        {
            // reduce size of arrays
            long[] newNewIndices = new long[nonZeroValues];
            System.arraycopy(newIndices, 0, newNewIndices, 0, nonZeroValues);
            newIndices = newNewIndices;
            float[] newNewValues = new float[nonZeroValues];
            System.arraycopy(newValues, 0, newNewValues, 0, nonZeroValues);
            newValues = newNewValues;
        }
        this.indices = newIndices;
        this.matrixSI = newValues;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataDense toDense()
    {
        float[] denseSI = new float[this.rows * this.cols];
        for (int index = 0; index < this.matrixSI.length; index++)
        {
            denseSI[(int) this.indices[index]] = this.matrixSI[index];
        }
        try
        {
            return new FloatMatrixDataDense(denseSI, this.rows, this.cols);
        }
        catch (ValueRuntimeException exception)
        {
            throw new RuntimeException(exception); // cannot happen -- denseSI has the right size
        }
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataSparse toSparse()
    {
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final float getSI(final int row, final int col)
    {
        long index = row * this.cols + col;
        int internalIndex = Arrays.binarySearch(this.indices, index);
        return internalIndex < 0 ? 0.0f : this.matrixSI[internalIndex];
    }

    /** {@inheritDoc} */
    @Override
    public final void setSI(final int row, final int col, final float valueSI)
    {
        long index = row * this.cols + col;
        int internalIndex = Arrays.binarySearch(this.indices, index);
        if (internalIndex >= 0)
        {
            this.matrixSI[internalIndex] = valueSI;
            return;
        }

        // make room. TODO increase size in chunks
        internalIndex = -internalIndex - 1;
        long[] indicesNew = new long[this.indices.length + 1];
        float[] matrixSINew = new float[this.matrixSI.length + 1];
        System.arraycopy(this.indices, 0, indicesNew, 0, internalIndex);
        System.arraycopy(this.matrixSI, 0, matrixSINew, 0, internalIndex);
        System.arraycopy(this.indices, internalIndex, indicesNew, internalIndex + 1, this.indices.length - internalIndex);
        System.arraycopy(this.matrixSI, internalIndex, matrixSINew, internalIndex + 1, this.indices.length - internalIndex);
        indicesNew[internalIndex] = index;
        matrixSINew[internalIndex] = valueSI;
        this.indices = indicesNew;
        this.matrixSI = matrixSINew;
    }

    /** {@inheritDoc} */
    @Override
    public final float[][] getDenseMatrixSI()
    {
        return toDense().getDenseMatrixSI();
    }

    /** {@inheritDoc} */
    @Override
    public final double[][] getDoubleDenseMatrixSI()
    {
        return toDense().getDoubleDenseMatrixSI();
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixDataSparse copy()
    {
        float[] vCopy = new float[this.matrixSI.length];
        System.arraycopy(this.matrixSI, 0, vCopy, 0, this.matrixSI.length);
        long[] iCopy = new long[this.indices.length];
        System.arraycopy(this.indices, 0, iCopy, 0, this.indices.length);
        return new FloatMatrixDataSparse(vCopy, iCopy, this.rows, this.cols);
    }

    /**
     * Instantiate a FloatMatrixDataSparse from an array.
     * @param valuesSI float[][]; the (SI) values to store
     * @return the FloatMatrixDataSparse
     * @throws ValueRuntimeException in case matrix is ragged
     */
    public static FloatMatrixDataSparse instantiate(final float[][] valuesSI) throws ValueRuntimeException
    {
        checkRectangularAndNonNull(valuesSI);
        int length = nonZero(valuesSI);
        int rows = valuesSI.length;
        final int cols = rows == 0 ? 0 : valuesSI[0].length;
        if (cols == 0)
        {
            rows = 0;
        }
        float[] sparseSI = new float[length];
        long[] indices = new long[length];
        fill(valuesSI, sparseSI, indices);
        return new FloatMatrixDataSparse(sparseSI, indices, rows, cols);
    }

    /**
     * Instantiate a FloatMatrixDataSparse from an array.
     * @param values float[][]; the values to store
     * @param scale Scale; the scale that will convert values to SI
     * @return the DoubleMatrixDataSparse
     * @throws ValueRuntimeException in case matrix is ragged
     */
    public static FloatMatrixDataSparse instantiate(final float[][] values, final Scale scale) throws ValueRuntimeException
    {
        checkRectangularAndNonNull(values);
        int length = nonZero(values);
        int rows = values.length;
        final int cols = rows == 0 ? 0 : values[0].length;
        if (cols == 0)
        {
            rows = 0;
        }
        float[] sparseSI = new float[length];
        long[] indices = new long[length];
        fill(values, sparseSI, indices, scale);
        return new FloatMatrixDataSparse(sparseSI, indices, rows, cols);
    }

    /**
     * Calculate the number of non-zero values in a float[][] matrix.
     * @param valuesSI float[][]; the float[][] matrix
     * @return the number of non-zero values in the float[][] matrix
     */
    private static int nonZero(final float[][] valuesSI)
    {
        // determine number of non-null cells
        AtomicInteger atomicLength = new AtomicInteger(0);
        IntStream.range(0, valuesSI.length).parallel().forEach(r -> IntStream.range(0, valuesSI[0].length).forEach(c ->
        {
            if (valuesSI[r][c] != 0.0f)
            {
                atomicLength.incrementAndGet();
            }
        }));

        return atomicLength.get();
    }

    /** {@inheritDoc} */
    @Override
    public FloatMatrixData plus(final FloatMatrixData right) throws ValueRuntimeException
    {
        if (right.isDense())
        {
            return right.copy().incrementBy(this);
        }
        return this.copy().incrementBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMatrixData minus(final FloatMatrixData right)
    {
        if (right.isDense())
        {
            return this.toDense().decrementBy(right);
        }
        return this.copy().decrementBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMatrixData times(final FloatMatrixData right) throws ValueRuntimeException
    {
        return this.copy().multiplyBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMatrixData divide(final FloatMatrixData right) throws ValueRuntimeException
    {
        if (right.isSparse())
        {
            // Sparse divided by sparse makes a dense
            return this.toDense().divide(right);
        }
        // Sparse divided by dense makes a sparse
        return this.copy().divideBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings({"checkstyle:needbraces", "checkstyle:designforextension"})
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FloatMatrixData))
            return false;
        FloatMatrixData other = (FloatMatrixData) obj;
        if (this.rows != other.rows)
            return false;
        if (this.cols != other.cols)
            return false;
        if (other instanceof FloatMatrixDataDense)
            return super.equals(other);
        if (getClass() != obj.getClass())
            return false;
        // Both are sparse
        if (!Arrays.equals(this.indices, ((FloatMatrixDataSparse) other).indices))
            return false;
        return Arrays.equals(this.matrixSI, ((FloatMatrixDataSparse) other).matrixSI);
    }

}
