package org.djunits.vecmat.dnxm;

import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.d1.Matrix1x1;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.def.Matrix;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djunits.vecmat.storage.DenseFloatDataSi;
import org.djutils.exceptions.Throw;

/**
 * MatrixNxM implements a matrix with NxM real-valued entries. The matrix is immutable, except for the display unit, which can
 * be changed. Internal storage can be float or double, and dense or sparse. MatrixNxN and VectorN extend from this class.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 */
public class MatrixNxM<Q extends Quantity<Q>> extends Matrix<Q, MatrixNxM<Q>, MatrixNxM<SIQuantity>, MatrixNxM<?>, MatrixNxM<Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The data of the table, in SI unit. */
    private final DataGridSi<?> dataGridSi;

    /**
     * Create a new NxM Matrix with a unit, based on a DataGrid storage object that contains SI data.
     * @param dataGridSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    public MatrixNxM(final DataGridSi<?> dataGridSi, final Unit<?, Q> displayUnit)
    {
        super(displayUnit);
        Throw.whenNull(dataGridSi, "dataGridSi");
        this.dataGridSi = dataGridSi;
    }

    @Override
    public MatrixNxM<Q> instantiateSi(final double[] siNew)
    {
        return new MatrixNxM<Q>(this.dataGridSi.instantiateNew(siNew), getDisplayUnit());
    }

    @Override
    public MatrixNxM<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new MatrixNxM<SIQuantity>(this.dataGridSi.instantiateNew(siNew), siUnit);
    }

    /**
     * Return the internal datagrid object, so we can retrieve data from it.
     * @return the internal datagrid object
     */
    public DataGridSi<?> getDataGrid()
    {
        return this.dataGridSi;
    }

    @Override
    public double[] si()
    {
        return this.dataGridSi.getDataArray();
    }

    @Override
    public double si(final int row, final int col) throws IndexOutOfBoundsException
    {
        checkRow(row);
        checkCol(col);
        return this.dataGridSi.get(row, col);
    }

    @Override
    public VectorN.Row<Q> getRowVector(final int row)
    {
        return VectorN.Row.ofSi(getRowSi(row), getDisplayUnit());
    }

    @Override
    public VectorN.Row<Q> mgetRowVector(final int mRow)
    {
        return VectorN.Row.ofSi(mgetRowSi(mRow), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q> getColumnVector(final int col)
    {
        return VectorN.Col.ofSi(getColumnSi(col), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q> mgetColumnVector(final int mCol)
    {
        return VectorN.Col.ofSi(mgetColumnSi(mCol), getDisplayUnit());
    }

    @Override
    public double[] getRowSi(final int row)
    {
        checkRow(row);
        return this.dataGridSi.getRowArray(row);
    }

    @Override
    public double[] getColumnSi(final int col)
    {
        checkCol(col);
        return this.dataGridSi.getColArray(col);
    }

    @Override
    public int rows()
    {
        return this.dataGridSi.rows();
    }

    @Override
    public int cols()
    {
        return this.dataGridSi.cols();
    }

    @Override
    public int nonZeroCount()
    {
        return this.dataGridSi.nonZeroCount();
    }

    /**
     * Return the transposed matrix. A transposed matrix has the same unit as the original one.
     * @return the transposed matrix
     */
    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public MatrixNxM<Q> transpose()
    {
        double[] data = si();
        double[] newSi = new double[data.length];
        int rows = rows();
        int cols = cols();
        final Unit<?, Q> displayUnit = getDisplayUnit();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                newSi[c * rows + r] = data[r * cols + c];
        return new MatrixNxM<Q>(this.dataGridSi.instantiateNew(newSi, cols, rows), displayUnit);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.dataGridSi);
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MatrixNxM<?> other = (MatrixNxM<?>) obj;
        return Objects.equals(this.dataGridSi, other.dataGridSi);
    }

    // ---------------------------------------------- MULTIPLY() METHODS ----------------------------------------------

    /**
     * Multiply this vector or matrix with a Matrix1x1, resulting in a MatrixNxM. The multiplication is a (Mx1) x (1x1) matrix
     * multiplication resulting in an (Mx1) matrix.
     * @param matrix the matrix to multiply with
     * @return a MatrixNxM of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             matrix or vector to multiply with
     */
    public MatrixNxM<SIQuantity> multiply(final Matrix1x1<?> matrix)
    {
        checkMultiply(matrix);
        double[] result = MatrixMath.multiply(si(), matrix.si(), rows(), cols(), matrix.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(matrix.getDisplayUnit().siUnit());
        return new MatrixNxM<SIQuantity>(new DenseDoubleDataSi(result, rows(), matrix.cols()), siUnit);
    }

    /**
     * Multiply this vector or matrix with a Matrix2x2, resulting in a MatrixNxM. The multiplication is a (Mx2) x (2x2) matrix
     * multiplication resulting in an (Mx2) matrix.
     * @param matrix the matrix to multiply with
     * @return a MatrixNxM of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             matrix or vector to multiply with
     */
    public MatrixNxM<SIQuantity> multiply(final Matrix2x2<?> matrix)
    {
        checkMultiply(matrix);
        double[] result = MatrixMath.multiply(si(), matrix.si(), rows(), cols(), matrix.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(matrix.getDisplayUnit().siUnit());
        return new MatrixNxM<SIQuantity>(new DenseDoubleDataSi(result, rows(), matrix.cols()), siUnit);
    }

    /**
     * Multiply this vector or matrix with a Matrix3x3, resulting in a MatrixNxM. The multiplication is a (Mx3) x (3x3) matrix
     * multiplication resulting in an (Mx3) matrix.
     * @param matrix the matrix to multiply with
     * @return a MatrixNxM of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             matrix or vector to multiply with
     */
    public MatrixNxM<SIQuantity> multiply(final Matrix3x3<?> matrix)
    {
        checkMultiply(matrix);
        double[] result = MatrixMath.multiply(si(), matrix.si(), rows(), cols(), matrix.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(matrix.getDisplayUnit().siUnit());
        return new MatrixNxM<SIQuantity>(new DenseDoubleDataSi(result, rows(), matrix.cols()), siUnit);
    }

    /**
     * Multiply this vector or matrix with a MatrixNxM, resulting in a MatrixNxM. The multiplication is a (NxM) x (MxP) matrix
     * multiplication resulting in an (NxP) matrix.
     * @param matrix the matrix to multiply with
     * @return a MatrixNxM of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             matrix or vector to multiply with
     */
    public MatrixNxM<SIQuantity> multiply(final MatrixNxN<?> matrix)
    {
        checkMultiply(matrix);
        double[] result = MatrixMath.multiply(si(), matrix.si(), rows(), cols(), matrix.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(matrix.getDisplayUnit().siUnit());
        if (matrix.getDataGrid().isDouble())
        {
            return new MatrixNxM<SIQuantity>(new DenseDoubleDataSi(result, rows(), matrix.cols()), siUnit);
        }
        return new MatrixNxM<SIQuantity>(new DenseFloatDataSi(result, rows(), matrix.cols()), siUnit);
    }

    /**
     * Multiply this vector or matrix with a Vector1, resulting in a MatrixNxM. The multiplication is a (Mx1) x (1x1) matrix
     * multiplication resulting in an (Mx1) matrix.
     * @param vector the vector to multiply with
     * @return a MatrixNxM of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             vector to multiply with
     */
    public MatrixNxM<SIQuantity> multiply(final Vector1<?> vector)
    {
        checkMultiply(vector);
        double[] result = MatrixMath.multiply(si(), vector.si(), rows(), cols(), vector.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(vector.getDisplayUnit().siUnit());
        return new MatrixNxM<SIQuantity>(new DenseDoubleDataSi(result, rows(), vector.cols()), siUnit);
    }

    /**
     * Multiply this vector or matrix with a Vector2.Col, resulting in a Vector2.Col. The multiplication is a (Mx2) x (2x1)
     * matrix multiplication resulting in an (Mx1) column vector.
     * @param vector the vector to multiply with
     * @return a VectorN.Col of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             vector to multiply with
     */
    public VectorN.Col<SIQuantity> multiply(final Vector2.Col<?> vector)
    {
        checkMultiply(vector);
        double[] result = MatrixMath.multiply(si(), vector.si(), rows(), cols(), vector.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(vector.getDisplayUnit().siUnit());
        return new VectorN.Col<SIQuantity>(new DenseDoubleDataSi(result, rows(), vector.cols()), siUnit);
    }

    /**
     * Multiply this vector or matrix with a Vector3.Col, resulting in a Vector3.Col. The multiplication is a (Mx3) x (3x1)
     * matrix multiplication resulting in an (Mx1) column vector.
     * @param vector the vector to multiply with
     * @return a VectorN.Col of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             vector to multiply with
     */
    public VectorN.Col<SIQuantity> multiply(final Vector3.Col<?> vector)
    {
        checkMultiply(vector);
        double[] result = MatrixMath.multiply(si(), vector.si(), rows(), cols(), vector.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(vector.getDisplayUnit().siUnit());
        return new VectorN.Col<SIQuantity>(new DenseDoubleDataSi(result, rows(), vector.cols()), siUnit);
    }

    /**
     * Multiply this vector or matrix with a VectorN.Col, resulting in a VectorN.Col. The multiplication is a (MxN) x (Nx1)
     * matrix multiplication resulting in an (Mx1) column vector.
     * @param vector the vector to multiply with
     * @return a VectorN.Col of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             vector to multiply with
     */
    public VectorN.Col<SIQuantity> multiply(final VectorN.Col<?> vector)
    {
        checkMultiply(vector);
        double[] result = MatrixMath.multiply(si(), vector.si(), rows(), cols(), vector.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(vector.getDisplayUnit().siUnit());
        return new VectorN.Col<SIQuantity>(new DenseDoubleDataSi(result, rows(), vector.cols()), siUnit);
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create a new MatrixNxM with a unit, based on a row-major array with values in the given unit.
     * @param dataInUnit the matrix values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} expressed in the unit
     * @param rows the number of rows
     * @param cols the number of columns
     * @param unit the unit of the data, also used as the display unit
     * @param <Q> the quantity type
     * @return a new MatrixNxM with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    public static <Q extends Quantity<Q>> MatrixNxM<Q> of(final double[] dataInUnit, final int rows, final int cols,
            final Unit<?, Q> unit)
    {
        return new MatrixNxM<Q>(DenseDoubleDataSi.of(dataInUnit, rows, cols, unit), unit);
    }

    /**
     * Create a MatrixNxM without needing generics, based on a row-major array with SI-values.
     * @param dataSi the matrix values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} as an array using SI units
     * @param rows the number of rows
     * @param cols the number of columns
     * @param displayUnit the display unit to use
     * @return a new MatrixNxM with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when dataSi does not contain a square number of values
     */
    public static <Q extends Quantity<Q>> MatrixNxM<Q> ofSi(final double[] dataSi, final int rows, final int cols,
            final Unit<?, Q> displayUnit)
    {
        return new MatrixNxM<Q>(DenseDoubleDataSi.ofSi(dataSi, rows, cols), displayUnit);
    }

    /**
     * Create a MatrixNxM without needing generics, based on a row-major array of quantities. The unit is taken from the first
     * quantity in the array.
     * @param data the matrix values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} expressed as an array of quantities
     * @param rows the number of rows
     * @param cols the number of columns
     * @return a new MatrixNxM with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when data does not contain a square number of quantities
     */
    public static <Q extends Quantity<Q>> MatrixNxM<Q> of(final Q[] data, final int rows, final int cols)
    {
        Throw.whenNull(data, "data");
        Throw.when(data.length == 0, IllegalArgumentException.class, "data.length = 0");
        return new MatrixNxM<Q>(DenseDoubleDataSi.of(data, rows, cols), data[0].getDisplayUnit());
    }

    /**
     * Create a new MatrixNxM with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the matrix values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param <Q> the quantity type
     * @return a new MatrixNxM with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> MatrixNxM<Q> ofSi(final double[][] gridSi, final Unit<?, Q> displayUnit)
    {
        return new MatrixNxM<>(DenseDoubleDataSi.ofSi(gridSi), displayUnit);
    }

    /**
     * Create a new MatrixNxM with a unit, based on a 2-dimensional grid with values in the given unit.
     * @param gridInUnit the matrix values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed in the unit
     * @param unit the unit of the values, also used as the display unit
     * @param <Q> the quantity type
     * @return a new MatrixNxM with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> MatrixNxM<Q> of(final double[][] gridInUnit, final Unit<?, Q> unit)
    {
        return new MatrixNxM<>(DenseDoubleDataSi.of(gridInUnit, unit), unit);
    }

    /**
     * Create a MatrixNxM without needing generics, based on a 2-dimensional grid of quantities. The unit is taken from the
     * first quantity in the grid.
     * @param grid the matrix values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed as a 2-dimensional array of
     *            quantities
     * @return a new MatrixNxM with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of quantities
     */
    public static <Q extends Quantity<Q>> MatrixNxM<Q> of(final Q[][] grid)
    {
        Throw.whenNull(grid, "grid");
        Throw.when(grid.length == 0, IllegalArgumentException.class, "grid.length = 0");
        Throw.whenNull(grid[0], "grid[0] = null");
        Throw.when(grid[0].length == 0, IllegalArgumentException.class, "grid[0].length = 0");
        Throw.whenNull(grid[0][0], "grid[0][0] = null");
        return new MatrixNxM<>(DenseDoubleDataSi.of(grid), grid[0][0].getDisplayUnit());
    }

    // ------------------------------------------------- AS() METHODS -------------------------------------------------

    /**
     * Return the matrix 'as' a matrix with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target matrix class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     */
    public <TQ extends Quantity<TQ>> MatrixNxM<TQ> as(final Unit<?, TQ> targetUnit) throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "MatrixNxM.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new MatrixNxM<TQ>(this.dataGridSi.instantiateNew(si()), targetUnit);
    }

    /**
     * Convert this matrix to a {@link Matrix1x1}. The shape must be 1 x 1.
     * @return a {@code Matrix1x1} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 1 x 1
     */
    public Matrix1x1<Q> asMatrix1x1()
    {
        Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class,
                "asMatrix1x1() called, but matrix is no 1x1 but %dx%d", rows(), cols());
        return Matrix1x1.ofSi(si(), getDisplayUnit());
    }

    /**
     * Convert this matrix to a {@link Matrix2x2}. The shape must be 2 x 2.
     * @return a {@code Matrix2x2} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 2 x 2
     */
    public Matrix2x2<Q> asMatrix2x2()
    {
        Throw.when(rows() != 2 || cols() != 2, IllegalStateException.class,
                "asMatrix2x2() called, but matrix is no 2x2 but %dx%d", rows(), cols());
        return Matrix2x2.ofSi(si(), getDisplayUnit());
    }

    /**
     * Convert this matrix to a {@link Matrix3x3}. The shape must be 3 x 3.
     * @return a {@code Matrix3x3} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 3 x 3
     */
    public Matrix3x3<Q> asMatrix3x3()
    {
        Throw.when(rows() != 3 || cols() != 3, IllegalStateException.class,
                "asMatrix3x3() called, but matrix is no 3x3 but %dx%d", rows(), cols());
        return Matrix3x3.ofSi(si(), getDisplayUnit());
    }

    /**
     * Convert this matrix to a {@link MatrixNxN}. The shape must be square.
     * @return a {@code MatrixNxN} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not square
     */
    public MatrixNxN<Q> asMatrixNxN()
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "asMatrixNxN() called, but matrix is no square but %dx%d",
                rows(), cols());
        return new MatrixNxN<Q>(new DenseDoubleDataSi(si(), rows(), cols()), getDisplayUnit());
    }

    /**
     * Return this matrix as a 1-element column vector. Shape must be 1 x 1.
     * @return a {@code Vector1} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 1
     */
    public Vector1<Q> asVector1()
    {
        Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class, "Matrix is not 1x1");
        final double[] dataSi = si();
        return new Vector1<Q>(dataSi[0], getDisplayUnit());
    }

    /**
     * Return this matrix as a 2-element column vector. Shape must be 2 x 1.
     * @return a {@code Vector2.Col} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 2 x 1
     */
    public Vector2.Col<Q> asVector2Col()
    {
        Throw.when(rows() != 2 || cols() != 1, IllegalStateException.class, "Matrix is not 2x1");
        final double[] dataSi = si();
        return new Vector2.Col<Q>(dataSi[0], dataSi[1], getDisplayUnit());
    }

    /**
     * Return this matrix as a 3-element column vector. Shape must be 3 x 1.
     * @return a {@code Vector3.Col} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 3 x 1
     */
    public Vector3.Col<Q> asVector3Col()
    {
        Throw.when(rows() != 3 || cols() != 1, IllegalStateException.class, "Matrix is not 3x1");
        final double[] dataSi = si();
        return new Vector3.Col<Q>(dataSi[0], dataSi[1], dataSi[2], getDisplayUnit());
    }

    /**
     * Return this matrix as an N-element column vector. Shape must be N x 1.
     * @return a {@code VectorN.Col} with identical SI data and display unit
     * @throws IllegalStateException if {@code cols() != 1}
     */
    public VectorN.Col<Q> asVectorNCol()
    {
        Throw.when(cols() != 1, IllegalStateException.class, "Matrix is not Nx1");
        return VectorN.Col.ofSi(si(), getDisplayUnit());
    }

    /**
     * Return this matrix as a 2-element row vector. Shape must be 1 x 2.
     * @return a {@code Vector2.Row} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 2
     */
    public Vector2.Row<Q> asVector2Row()
    {
        Throw.when(rows() != 1 || cols() != 2, IllegalStateException.class, "Matrix is not 1x2");
        final double[] dataSi = si();
        return new Vector2.Row<Q>(dataSi[0], dataSi[1], getDisplayUnit());
    }

    /**
     * Return this matrix as a 3-element row vector. Shape must be 1 x 3.
     * @return a {@code Vector3.Row} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 3
     */
    public Vector3.Row<Q> asVector3Row()
    {
        Throw.when(rows() != 1 || cols() != 3, IllegalStateException.class, "Matrix is not 1x3");
        final double[] dataSi = si();
        return new Vector3.Row<Q>(dataSi[0], dataSi[1], dataSi[2], getDisplayUnit());
    }

    /**
     * Return this matrix as an N-element row vector. Shape must be 1 x N.
     * @return a {@code VectorN.Row} with identical SI data and display unit
     * @throws IllegalStateException if {@code rows() != 1}
     */
    public VectorN.Row<Q> asVectorNRow()
    {
        Throw.when(rows() != 1, IllegalStateException.class, "Matrix is not 1xN");
        return VectorN.Row.ofSi(si(), getDisplayUnit());
    }

}
