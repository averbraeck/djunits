package org.djunits.value.vfloat.matrix.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

import org.djunits.Throw;
import org.djunits.unit.Unit;
import org.djunits.unit.scale.Scale;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.AbstractStorage;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatFunction2;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.scalar.base.FloatScalarInterface;

/**
 * Stores the data for a FloatMatrix and carries out basic operations.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public abstract class FloatMatrixData extends AbstractStorage<FloatMatrixData> implements Serializable
{
    /** */
    private static final long serialVersionUID = 1L;

    /** the internal storage of the Matrix; can be sparse or dense. The data is stored in an array. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected float[] matrixSI;

    /** the number of rows of the matrix. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected int rows;

    /** the number of columns of the matrix. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected int cols;

    /**
     * Construct a new DoubleMatrixData store.
     * @param storageType StorageType; the data type
     */
    public FloatMatrixData(final StorageType storageType)
    {
        super(storageType);
    }

    /* ============================================================================================ */
    /* ====================================== INSTANTIATION ======================================= */
    /* ============================================================================================ */

    /**
     * Instantiate a FloatMatrixData with the right data type. The float array is of the form f[rows][columns] so each value can
     * be found with f[row][column].
     * @param values float[][]; the (SI) values to store
     * @param scale Scale; the scale of the unit to use for conversion to SI
     * @param storageType StorageType; the data type to use
     * @return the FloatMatrixData with the right data type
     * @throws ValueRuntimeException when values is ragged
     * @throws NullPointerException when values are null, or storageType is null
     */
    public static FloatMatrixData instantiate(final float[][] values, final Scale scale, final StorageType storageType)
            throws ValueRuntimeException
    {
        Throw.whenNull(scale, "FloatMatrixData.instantiate: scale is null");
        Throw.whenNull(storageType, "FloatMatrixData.instantiate: storageType is null");
        checkRectangularAndNonNull(values);

        int rows = values.length;
        final int cols = rows == 0 ? 0 : values[0].length;
        if (cols == 0)
        {
            rows = 0;
        }

        switch (storageType)
        {
            case DENSE:
                float[] valuesSI = new float[rows * cols];
                IntStream.range(0, values.length).parallel().forEach(r -> IntStream.range(0, cols)
                        .forEach(c -> valuesSI[r * cols + c] = (float) scale.toStandardUnit(values[r][c])));
                return new FloatMatrixDataDense(valuesSI, rows, cols);

            case SPARSE:
                return FloatMatrixDataSparse.instantiate(values, scale);

            default:
                throw new ValueRuntimeException("Unknown storage type in FloatMatrixData.instantiate: " + storageType);
        }
    }

    /**
     * Instantiate a FloatMatrixData with the right data type.
     * @param values Collection&lt;FloatSparseValue&lt;U, S&gt;&gt;; the (sparse [X, Y, SI]) values to store
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the data type to use
     * @return the FloatMatrixData with the right data type
     * @throws NullPointerException when values are null, or storageType is null
     * @throws ValueRuntimeException when rows &lt; 0 or cols &lt; 0
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>> FloatMatrixData instantiate(
            final Collection<FloatSparseValue<U, S>> values, final int rows, final int cols, final StorageType storageType)
            throws NullPointerException
    {
        Throw.whenNull(values, "FloatMatrixData.instantiate: values is null");
        Throw.whenNull(storageType, "FloatMatrixData.instantiate: storageType is null");
        Throw.when(cols < 0, ValueRuntimeException.class, "cols must be >= 0");
        Throw.when(rows < 0, ValueRuntimeException.class, "rows must be >= 0");
        for (FloatSparseValue<U, S> fsp : values)
        {
            Throw.whenNull(fsp, "null value in values");
            Throw.when(fsp.getRow() < 0 || fsp.getRow() >= rows, ValueRuntimeException.class, "row out of range");
            Throw.when(fsp.getColumn() < 0 || fsp.getColumn() >= cols, ValueRuntimeException.class, "column out of range");
        }

        switch (storageType)
        {
            case DENSE:
                float[] valuesSI = new float[rows * cols];
                values.stream().parallel().forEach(v -> valuesSI[v.getRow() * cols + v.getColumn()] = v.getValueSI());
                return new FloatMatrixDataDense(valuesSI, rows, cols);

            case SPARSE:
                return new FloatMatrixDataSparse(values, rows, cols);

            default:
                throw new ValueRuntimeException("Unknown storage type in FloatMatrixData.instantiate: " + storageType);
        }
    }

    /**
     * Instantiate a FloatMatrixData with the right data type. The FloatScalar array is of the form fs[rows][columns] so each
     * value can be found with fs[row][column].
     * @param values S[][]; the values to store
     * @param storageType StorageType; the data type to use
     * @return the FloatMatrixData with the right data type
     * @throws NullPointerException when values is null, or storageType is null
     * @throws ValueRuntimeException when values is ragged
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>> FloatMatrixData instantiate(final S[][] values,
            final StorageType storageType) throws ValueRuntimeException
    {
        Throw.whenNull(storageType, "FloatMatrixData.instantiate: storageType is null");
        checkRectangularAndNonNull(values);

        int rows = values.length;
        final int cols = rows == 0 ? 0 : values[0].length;
        if (cols == 0)
        {
            rows = 0;
        }

        switch (storageType)
        {
            case DENSE:
                float[] valuesSI = new float[rows * cols];
                IntStream.range(0, rows).parallel()
                        .forEach(r -> IntStream.range(0, cols).forEach(c -> valuesSI[r * cols + c] = values[r][c].getSI()));
                return new FloatMatrixDataDense(valuesSI, rows, cols);

            case SPARSE:
                float[][] matrixSI = new float[rows][cols];
                IntStream.range(0, values.length).parallel()
                        .forEach(r -> IntStream.range(0, cols).forEach(c -> matrixSI[r][c] = values[r][c].getSI()));
                return FloatMatrixDataSparse.instantiate(matrixSI);

            default:
                throw new ValueRuntimeException("Unknown storage type in FloatMatrixData.instantiate: " + storageType);
        }
    }

    /* ============================================================================================ */
    /* ==================================== UTILITY FUNCTIONS ===================================== */
    /* ============================================================================================ */

    /**
     * Retrieve the row count.
     * @return int; the number of rows of the matrix
     */
    public int rows()
    {
        return this.rows;
    }

    /**
     * Retrieve the column count.
     * @return int; the number of columns of the matrix
     */
    public int cols()
    {
        return this.cols;
    }

    /**
     * Return the data of this matrix in dense storage format.
     * @return FloatMatrixDataDense; the dense transformation of this data
     */
    public abstract FloatMatrixDataDense toDense();

    /**
     * Return the data of this matrix in sparse storage format.
     * @return FloatMatrixDataSparse; the sparse transformation of this data
     */
    public abstract FloatMatrixDataSparse toSparse();

    /**
     * Retrieve one value from this data.
     * @param row int; the row number to get the value for
     * @param col int; the column number to get the value for
     * @return the value at the [row, col] point
     */
    public abstract float getSI(int row, int col);

    /**
     * Sets a value at the [row, col] point in the matrix.
     * @param row int; the row number to set the value for
     * @param col int; the column number to set the value for
     * @param valueSI float; the value at the index
     */
    public abstract void setSI(int row, int col, float valueSI);

    /**
     * Compute and return the sum of the values of all cells of this matrix.
     * @return float; the sum of the values of all cells
     */
    public final float zSum()
    {
        // this does not copy the data. See http://stackoverflow.com/questions/23106093/how-to-get-a-stream-from-a-float
        return (float) IntStream.range(0, this.matrixSI.length).parallel().mapToDouble(i -> this.matrixSI[i]).sum();
    }

    /**
     * Create and return a deep copy of the data in dense format. The float array is of the form f[rows][columns] so each value
     * can be found with f[row][column].
     * @return float[][]; a safe, dense copy of matrixSI as a matrix
     */
    public abstract float[][] getDenseMatrixSI();

    /**
     * Create and return a deep copy of the data in dense format. The double array is of the form d[rows][columns] so each value
     * can be found with d[row][column].
     * @return double[][]; a safe, dense copy of matrixSI as a matrix
     */
    public abstract double[][] getDoubleDenseMatrixSI();

    /**
     * Check that a 2D array of float is not null, not empty and not jagged; i.e. all rows have the same length.
     * @param values float[][]; the 2D array to check
     * @return the values in case the method is used in a constructor
     * @throws NullPointerException when <code>values</code> is null
     * @throws ValueRuntimeException when <code>values</code> is jagged
     */
    protected static float[][] checkRectangularAndNonNull(final float[][] values) throws ValueRuntimeException
    {
        Throw.when(null == values, NullPointerException.class, "Cannot create a matrix from a null float[][]");
        for (int row = 0; row < values.length; row++)
        {
            Throw.when(null == values[row], ValueRuntimeException.class,
                    "Cannot create a matrix from float[][] containing null row(s)");
            Throw.when(values[row].length != values[0].length, ValueRuntimeException.class,
                    "Cannot create a matrix from a jagged float[][]");
        }
        return values;
    }

    /**
     * Check that a 2D array of float is not null, not empty and not jagged; i.e. all rows have the same length.
     * @param values S[][]; the 2D array to check
     * @return the values in case the method is used in a constructor
     * @throws NullPointerException when <code>values</code> is null
     * @throws ValueRuntimeException when <code>values</code> is jagged
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    protected static <U extends Unit<U>, S extends FloatScalarInterface<U, S>> S[][] checkRectangularAndNonNull(
            final S[][] values) throws ValueRuntimeException
    {
        Throw.when(null == values, NullPointerException.class, "Cannot create a matrix from a null Scalar[][]");
        for (int row = 0; row < values.length; row++)
        {
            Throw.when(null == values[row], ValueRuntimeException.class,
                    "Cannot create a matrix from Scalar[][] containing null row(s)");
            Throw.when(values[row].length != values[0].length, ValueRuntimeException.class,
                    "Cannot create a matrix from a jagged Scalar[][]");
            for (int col = 0; col < values[row].length; col++)
            {
                Throw.whenNull(values[row][col], "Cannot create a matrix from Scalar[][] containing null(s)");
            }
        }
        return values;
    }

    /**
     * Check the sizes of this data object and the other data object.
     * @param other FloatMatrixData; the other data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    protected void checkSizes(final FloatMatrixData other) throws ValueRuntimeException
    {
        if (this.rows() != other.rows() || this.cols() != other.cols())
        {
            throw new ValueRuntimeException("Two data objects used in a FloatMatrix operation do not have the same size");
        }
    }

    /* ============================================================================================ */
    /* ================================== CALCULATION FUNCTIONS =================================== */
    /* ============================================================================================ */

    /**
     * Apply an operation to each cell.
     * @param doubleFunction FloatFunction; the operation to apply
     * @return FloatMatrixData; this (modified) double vector data object
     */
    public abstract FloatMatrixData assign(FloatFunction doubleFunction);

    /**
     * Apply a binary operation on a cell by cell basis.
     * @param floatFunction FloatFunction2; the binary operation to apply
     * @param right FloatMatrixData; the right operand for the binary operation
     * @return FloatMatrixData; this (modified) double matrix data object
     * @throws ValueRuntimeException when the sizes of the vectors do not match
     */
    abstract FloatMatrixData assign(FloatFunction2 floatFunction, FloatMatrixData right) throws ValueRuntimeException;

    /**
     * Add two matrices on a cell-by-cell basis. If both matrices are sparse, a sparse matrix is returned, otherwise a dense
     * matrix is returned.
     * @param right FloatMatrixData; the other data object to add
     * @return the sum of this data object and the other data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    public abstract FloatMatrixData plus(FloatMatrixData right) throws ValueRuntimeException;

    /**
     * Add a matrix to this matrix on a cell-by-cell basis. The type of matrix (sparse, dense) stays the same.
     * @param right FloatMatrixData; the other data object to add
     * @return FloatMatrixData; this modified float matrix data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    public final FloatMatrixData incrementBy(final FloatMatrixData right) throws ValueRuntimeException
    {
        return assign(new FloatFunction2()
        {
            @Override
            public float apply(final float leftValue, final float rightValue)
            {
                return leftValue + rightValue;
            }
        }, right);
    }

    /**
     * Subtract two matrices on a cell-by-cell basis. If both matrices are sparse, a sparse matrix is returned, otherwise a
     * dense matrix is returned.
     * @param right FloatMatrixData; the other data object to subtract
     * @return the sum of this data object and the other data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    public abstract FloatMatrixData minus(FloatMatrixData right) throws ValueRuntimeException;

    /**
     * Subtract a matrix from this matrix on a cell-by-cell basis. The type of matrix (sparse, dense) stays the same.
     * @param decrement FloatMatrixData; the other data object to subtract
     * @return FloatMatrixData; this modified float matrix data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    public final FloatMatrixData decrementBy(final FloatMatrixData decrement) throws ValueRuntimeException
    {
        return assign(new FloatFunction2()
        {
            @Override
            public float apply(final float leftValue, final float rightValue)
            {
                return leftValue - rightValue;
            }
        }, decrement);
    }

    /**
     * Multiply two matrices on a cell-by-cell basis. If both matrices are dense, a dense matrix is returned, otherwise a sparse
     * matrix is returned.
     * @param right FloatMatrixData; the other data object to multiply with
     * @return FloatMatrixData; a new double matrix data store holding the result of the multiplications
     * @throws ValueRuntimeException if matrices have different sizes
     */
    public abstract FloatMatrixData times(FloatMatrixData right) throws ValueRuntimeException;

    /**
     * Multiply a matrix with the values of another matrix on a cell-by-cell basis. The type of matrix (sparse, dense) stays the
     * same.
     * @param right FloatMatrixData; the other data object to multiply with
     * @return FloatMatrixData; this modified data store
     * @throws ValueRuntimeException if matrices have different sizes
     */
    public final FloatMatrixData multiplyBy(final FloatMatrixData right) throws ValueRuntimeException
    {
        return assign(new FloatFunction2()
        {
            @Override
            public float apply(final float leftValue, final float rightValue)
            {
                return leftValue * rightValue;
            }
        }, right);
    }

    /**
     * Divide two matrices on a cell-by-cell basis. If both matrices are dense, a dense matrix is returned, otherwise a sparse
     * matrix is returned.
     * @param right FloatMatrixData; the other data object to divide by
     * @return the sum of this data object and the other data object
     * @throws ValueRuntimeException if matrices have different sizes
     */
    public abstract FloatMatrixData divide(FloatMatrixData right) throws ValueRuntimeException;

    /**
     * Divide the values of a matrix by the values of another matrix on a cell-by-cell basis. The type of matrix (sparse, dense)
     * stays the same.
     * @param right FloatMatrixData; the other data object to divide by
     * @return FloatMatrixData; this modified data store
     * @throws ValueRuntimeException if matrices have different sizes
     */
    public final FloatMatrixData divideBy(final FloatMatrixData right) throws ValueRuntimeException
    {
        return assign(new FloatFunction2()
        {
            @Override
            public float apply(final float leftValue, final float rightValue)
            {
                return leftValue / rightValue;
            }
        }, right);
    }

    /* ============================================================================================ */
    /* =============================== EQUALS, HASHCODE, TOSTRING ================================= */
    /* ============================================================================================ */

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.rows;
        result = prime * result + this.cols;
        for (int row = 0; row < this.rows; row++)
        {
            for (int col = 0; col < this.cols; col++)
            {
                result = 31 * result + Float.floatToIntBits(getSI(row, col));
            }
        }
        return result;
    }

    /**
     * Compare contents of a dense and a sparse matrix.
     * @param dm FloatMatrixDataDense; the dense matrix
     * @param sm FloatMatrixDataSparse; the sparse matrix
     * @return boolean; true if the contents are equal
     */
    protected boolean compareDenseMatrixWithSparseMatrix(final FloatMatrixDataDense dm, final FloatMatrixDataSparse sm)
    {
        for (int row = 0; row < dm.rows; row++)
        {
            for (int col = 0; col < dm.cols; col++)
            {
                if (dm.getSI(row, col) != sm.getSI(row, col))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:needbraces")
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
        if (other instanceof FloatMatrixDataSparse && this instanceof FloatMatrixDataDense)
        {
            return compareDenseMatrixWithSparseMatrix((FloatMatrixDataDense) this, (FloatMatrixDataSparse) other);
        }
        else if (other instanceof FloatMatrixDataDense && this instanceof FloatMatrixDataSparse)
        {
            return compareDenseMatrixWithSparseMatrix((FloatMatrixDataDense) other, (FloatMatrixDataSparse) this);
        }
        // Both are dense (both sparse is handled in FloatMatrixDataSparse class)
        return Arrays.equals(this.matrixSI, other.matrixSI);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "FloatMatrixData [storageType=" + getStorageType() + ", matrixSI=" + Arrays.toString(this.matrixSI) + "]";
    }

}
