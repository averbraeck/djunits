package org.djunits.value.vdouble.matrix.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

import org.djunits.unit.Unit;
import org.djunits.unit.scale.Scale;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.Storage;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleFunction2;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djutils.exceptions.Throw;

/**
 * Stores the data for a DoubleMatrix and carries out basic operations.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public abstract class DoubleMatrixData extends Storage<DoubleMatrixData> implements Serializable
{
    /** */
    private static final long serialVersionUID = 1L;

    /** the internal storage of the Matrix; can be sparse or dense. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected double[] matrixSI;

    /** the number of rows of the matrix. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected int rows;

    /** the number of columns of the matrix. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected int cols;

    /**
     * Construct a new DoubleMatrixData store.
     * @param storageType the data type
     */
    DoubleMatrixData(final StorageType storageType)
    {
        super(storageType);
    }

    /* ============================================================================================ */
    /* ====================================== INSTANTIATION ======================================= */
    /* ============================================================================================ */

    /**
     * Instantiate a DoubleMatrixData with the right data type. The double array is of the form d[rows][columns] so each value
     * can be found with d[row][column].
     * @param values the (SI) values to store
     * @param scale the scale of the unit to use for conversion to SI
     * @param storageType the data type to use
     * @return the DoubleMatrixData with the right data type
     * @throws NullPointerException when values are null, or storageType is null
     * @throws ValueRuntimeException when values is ragged
     */
    public static DoubleMatrixData instantiate(final double[][] values, final Scale scale, final StorageType storageType)
            throws ValueRuntimeException
    {
        Throw.whenNull(scale, "DoubleMatrixData.instantiate: scale is null");
        Throw.whenNull(storageType, "DoubleMatrixData.instantiate: storageType is null");
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
                double[] valuesSI = new double[rows * cols];
                IntStream.range(0, values.length).parallel().forEach(r -> IntStream.range(0, cols)
                        .forEach(c -> valuesSI[r * cols + c] = scale.toStandardUnit(values[r][c])));
                return new DoubleMatrixDataDense(valuesSI, rows, cols);

            case SPARSE:
                return DoubleMatrixDataSparse.instantiate(values, scale);

            default:
                throw new ValueRuntimeException("Unknown storage type in DoubleMatrixData.instantiate: " + storageType);
        }
    }

    /**
     * Instantiate a DoubleMatrixData with the right data type.
     * @param values the (sparse [X, Y, SI]) values to store
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the data type to use
     * @return the DoubleMatrixData with the right data type
     * @throws NullPointerException when values are null, or storageType is null
     * @throws ValueRuntimeException when rows &lt; 0 or cols &lt; 0
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> DoubleMatrixData instantiate(
            final Collection<DoubleSparseValue<U, S>> values, final int rows, final int cols, final StorageType storageType)
            throws ValueRuntimeException
    {
        Throw.whenNull(values, "DoubleMatrixData.instantiate: values is null");
        Throw.whenNull(storageType, "DoubleMatrixData.instantiate: storageType is null");
        Throw.when(cols < 0, ValueRuntimeException.class, "cols must be >= 0");
        Throw.when(rows < 0, ValueRuntimeException.class, "rows must be >= 0");
        for (DoubleSparseValue<U, S> dsp : values)
        {
            Throw.whenNull(dsp, "null value in values");
            Throw.when(dsp.getRow() < 0 || dsp.getRow() >= rows, ValueRuntimeException.class, "row out of range");
            Throw.when(dsp.getColumn() < 0 || dsp.getColumn() >= cols, ValueRuntimeException.class, "column out of range");
        }

        switch (storageType)
        {
            case DENSE:
                double[] valuesSI = new double[rows * cols];
                values.stream().parallel().forEach(v -> valuesSI[v.getRow() * cols + v.getColumn()] = v.getValueSI());
                return new DoubleMatrixDataDense(valuesSI, rows, cols);

            case SPARSE:
                return new DoubleMatrixDataSparse(values, rows, cols);

            default:
                throw new ValueRuntimeException("Unknown storage type in DoubleMatrixData.instantiate: " + storageType);
        }
    }

    /**
     * Instantiate a DoubleMatrixData with the right data type. The double array is of the form d[rows][columns] so each value
     * can be found with d[row][column].
     * @param values the values to store
     * @param storageType the data type to use
     * @return the DoubleMatrixData with the right data type
     * @throws NullPointerException when values is null, or storageType is null
     * @throws ValueRuntimeException when values is ragged
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> DoubleMatrixData instantiate(final S[][] values,
            final StorageType storageType) throws ValueRuntimeException
    {
        Throw.whenNull(storageType, "DoubleMatrixData.instantiate: storageType is null");
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
                double[] valuesSI = new double[rows * cols];
                IntStream.range(0, rows).parallel()
                        .forEach(r -> IntStream.range(0, cols).forEach(c -> valuesSI[r * cols + c] = values[r][c].getSI()));
                return new DoubleMatrixDataDense(valuesSI, rows, cols);

            case SPARSE:
                double[][] matrixSI = new double[rows][cols];
                IntStream.range(0, values.length).parallel()
                        .forEach(r -> IntStream.range(0, cols).forEach(c -> matrixSI[r][c] = values[r][c].getSI()));
                return DoubleMatrixDataSparse.instantiate(matrixSI);

            default:
                throw new ValueRuntimeException("Unknown storage type in DoubleMatrixData.instantiate: " + storageType);
        }
    }

    /* ============================================================================================ */
    /* ==================================== UTILITY FUNCTIONS ===================================== */
    /* ============================================================================================ */

    /**
     * Retrieve the row count.
     * @return the number of rows of the matrix
     */
    public int rows()
    {
        return this.rows;
    }

    /**
     * Retrieve the column count.
     * @return the number of columns of the matrix
     */
    public int cols()
    {
        return this.cols;
    }

    /**
     * Return the data of this matrix in dense storage format.
     * @return the dense transformation of this data
     */
    public abstract DoubleMatrixDataDense toDense();

    /**
     * Return the data of this matrix in sparse storage format.
     * @return the sparse transformation of this data
     */
    public abstract DoubleMatrixDataSparse toSparse();

    /**
     * Retrieve one value from this data.
     * @param row the row number to get the value for
     * @param col the column number to get the value for
     * @return the value at the [row, col] point
     */
    public abstract double getSI(int row, int col);

    /**
     * Sets a value at the [row, col] point in the matrix.
     * @param row the row number to set the value for
     * @param col the column number to set the value for
     * @param valueSI the value at the index
     */
    public abstract void setSI(int row, int col, double valueSI);

    /**
     * Compute and return the sum of the values of all cells of this matrix.
     * @return the sum of the values of all cells
     */
    public final double zSum()
    {
        return Arrays.stream(this.matrixSI).parallel().sum();
    }

    /**
     * Create and return a deep copy of the data in dense format. The double array is of the form d[rows][columns] so each value
     * can be found with d[row][column].
     * @return a safe, dense copy of matrixSI as a matrix
     */
    public abstract double[][] getDenseMatrixSI();

    /**
     * Check that a 2D array of float is not null, not empty and not jagged; i.e. all rows have the same length.
     * @param values the 2D array to check
     * @return the values in case the method is used in a constructor
     * @throws NullPointerException when <code>values</code> is null
     * @throws ValueRuntimeException when <code>values</code> is jagged
     */
    protected static double[][] checkRectangularAndNonNull(final double[][] values) throws ValueRuntimeException
    {
        Throw.when(null == values, NullPointerException.class, "Cannot create a matrix from a null double[][]");
        for (int row = 0; row < values.length; row++)
        {
            Throw.when(null == values[row], ValueRuntimeException.class,
                    "Cannot create a matrix from double[][] containing null row(s)");
            Throw.when(values[row].length != values[0].length, ValueRuntimeException.class,
                    "Cannot create a matrix from a jagged double[][]");
        }
        return values;
    }

    /**
     * Check that a 2D array of float is not null, not empty and not jagged; i.e. all rows have the same length.
     * @param values the 2D array to check
     * @return the values in case the method is used in a constructor
     * @throws NullPointerException when <code>values</code> is null
     * @throws ValueRuntimeException when <code>values</code> is jagged
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    protected static <U extends Unit<U>, S extends DoubleScalar<U, S>> S[][] checkRectangularAndNonNull(
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
     * @param other the other data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    protected void checkSizes(final DoubleMatrixData other) throws ValueRuntimeException
    {
        if (this.rows() != other.rows() || this.cols() != other.cols())
        {
            throw new ValueRuntimeException("Two data objects used in a DoubleMatrix operation do not have the same size");
        }
    }

    /* ============================================================================================ */
    /* ================================== CALCULATION FUNCTIONS =================================== */
    /* ============================================================================================ */

    /**
     * Apply an operation to each cell.
     * @param doubleFunction the operation to apply
     * @return this (modified) double matrix data object
     */
    public abstract DoubleMatrixData assign(DoubleFunction doubleFunction);

    /**
     * Apply a binary operation on a cell by cell basis.
     * @param doubleFunction2 the binary operation to apply
     * @param right the right operand for the binary operation
     * @return this (modified) double matrix data object
     * @throws ValueRuntimeException when the sizes of the matrices do not match
     */
    abstract DoubleMatrixData assign(DoubleFunction2 doubleFunction2, DoubleMatrixData right) throws ValueRuntimeException;

    /**
     * Add two matrices on a cell-by-cell basis. If both matrices are sparse, a sparse matrix is returned, otherwise a dense
     * matrix is returned.
     * @param right the other data object to add
     * @return the sum of this data object and the other data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    public abstract DoubleMatrixData plus(DoubleMatrixData right) throws ValueRuntimeException;

    /**
     * Add a matrix to this matrix on a cell-by-cell basis. The type of matrix (sparse, dense) stays the same.
     * @param right the other data object to add
     * @return this modified double matrix data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    public final DoubleMatrixData incrementBy(final DoubleMatrixData right) throws ValueRuntimeException
    {
        return assign(new DoubleFunction2()
        {
            @Override
            public double apply(final double leftValue, final double rightValue)
            {
                return leftValue + rightValue;
            }
        }, right);
    }

    /**
     * Subtract two matrices on a cell-by-cell basis. If both matrices are sparse, a sparse matrix is returned, otherwise a
     * dense matrix is returned.
     * @param right the other data object to subtract
     * @return the sum of this data object and the other data object
     * @throws ValueRuntimeException if matrices have different lengths
     */
    public abstract DoubleMatrixData minus(DoubleMatrixData right) throws ValueRuntimeException;

    /**
     * Subtract a matrix from this matrix on a cell-by-cell basis. The type of matrix (sparse, dense) stays the same.
     * @param decrement the amount to subtract
     * @return this modified double matrix data object
     * @throws ValueRuntimeException if matrices have different sizes
     */
    public final DoubleMatrixData decrementBy(final DoubleMatrixData decrement) throws ValueRuntimeException
    {
        return assign(new DoubleFunction2()
        {
            @Override
            public double apply(final double leftValue, final double rightValue)
            {
                return leftValue - rightValue;
            }
        }, decrement);
    }

    /**
     * Multiply two matrices on a cell-by-cell basis. If both matrices are dense, a dense matrix is returned, otherwise a sparse
     * matrix is returned.
     * @param right the other data object to multiply with
     * @return a new double matrix data store holding the result of the multiplications
     * @throws ValueRuntimeException if matrices have different sizes
     */
    public abstract DoubleMatrixData times(DoubleMatrixData right) throws ValueRuntimeException;

    /**
     * Multiply a matrix with the values of another matrix on a cell-by-cell basis. The type of matrix (sparse, dense) stays the
     * same.
     * @param right the other data object to multiply with
     * @return this modified data store
     * @throws ValueRuntimeException if matrices have different lengths
     */
    public final DoubleMatrixData multiplyBy(final DoubleMatrixData right) throws ValueRuntimeException
    {
        return assign(new DoubleFunction2()
        {
            @Override
            public double apply(final double leftValue, final double rightValue)
            {
                return leftValue * rightValue;
            }
        }, right);
    }

    /**
     * Divide two matrices on a cell-by-cell basis. If this matrix is sparse and <code>right</code> is dense, a sparse matrix is
     * returned, otherwise a dense matrix is returned.
     * @param right the other data object to divide by
     * @return the ratios of the values of this data object and the other data object
     * @throws ValueRuntimeException if matrices have different sizes
     */
    public abstract DoubleMatrixData divide(DoubleMatrixData right) throws ValueRuntimeException;

    /**
     * Divide the values of a matrix by the values of another matrix on a cell-by-cell basis. The type of matrix (sparse, dense)
     * stays the same.
     * @param right the other data object to divide by
     * @return this modified data store
     * @throws ValueRuntimeException if matrices have different sizes
     */
    public final DoubleMatrixData divideBy(final DoubleMatrixData right) throws ValueRuntimeException
    {
        return assign(new DoubleFunction2()
        {
            @Override
            public double apply(final double leftValue, final double rightValue)
            {
                return leftValue / rightValue;
            }
        }, right);
    }

    /* ============================================================================================ */
    /* =============================== EQUALS, HASHCODE, TOSTRING ================================= */
    /* ============================================================================================ */

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
                long bits = Double.doubleToLongBits(getSI(row, col));
                result = 31 * result + (int) (bits ^ (bits >>> 32));
            }
        }
        return result;
    }

    /**
     * Compare contents of a dense and a sparse matrix.
     * @param dm the dense matrix
     * @param sm the sparse matrix
     * @return true if the contents are equal
     */
    protected boolean compareDenseMatrixWithSparseMatrix(final DoubleMatrixDataDense dm, final DoubleMatrixDataSparse sm)
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

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DoubleMatrixData))
            return false;
        DoubleMatrixData other = (DoubleMatrixData) obj;
        if (this.rows != other.rows)
            return false;
        if (this.cols != other.cols)
            return false;
        if (other instanceof DoubleMatrixDataSparse && this instanceof DoubleMatrixDataDense)
        {
            return compareDenseMatrixWithSparseMatrix((DoubleMatrixDataDense) this, (DoubleMatrixDataSparse) other);
        }
        else if (other instanceof DoubleMatrixDataDense && this instanceof DoubleMatrixDataSparse)
        {
            return compareDenseMatrixWithSparseMatrix((DoubleMatrixDataDense) other, (DoubleMatrixDataSparse) this);
        }
        // Both are dense (both sparse is handled in DoubleMatrixDataSparse class)
        return Arrays.equals(this.matrixSI, other.matrixSI);
    }

}
