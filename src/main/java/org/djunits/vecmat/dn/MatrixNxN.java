package org.djunits.vecmat.dn;

import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.d1.Matrix1x1;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.def.SquareMatrix;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djutils.exceptions.Throw;

/**
 * MatrixNxN implements a square matrix with NxN real-valued entries. The matrix is immutable, except for the display unit,
 * which can be changed. Internal storage can be float or double, and dense or sparse.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 */
public class MatrixNxN<Q extends Quantity<Q>> extends SquareMatrix<Q, MatrixNxN<Q>, MatrixNxN<SIQuantity>, MatrixNxN<?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The data of the matrix, in SI unit. */
    private final DataGridSi<?> dataGridSi;

    /**
     * Create a new NxN Matrix with a unit, based on a DataGrid storage object that contains SI data.
     * @param dataGridSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    public MatrixNxN(final DataGridSi<?> dataGridSi, final Unit<?, Q> displayUnit)
    {
        super(displayUnit);
        Throw.whenNull(dataGridSi, "dataGridSi");
        Throw.when(dataGridSi.rows() != dataGridSi.cols(), IllegalArgumentException.class,
                "Data for the NxN matrix is not square");
        this.dataGridSi = dataGridSi;
    }

    @Override
    public MatrixNxN<Q> instantiateSi(final double[] siNew)
    {
        return new MatrixNxN<Q>(this.dataGridSi.instantiateNew(siNew), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public double[] si()
    {
        return this.dataGridSi.getDataArray();
    }

    @Override
    public double si(final int row, final int col)
    {
        return this.dataGridSi.get(row, col);
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
     * Return the data grid in SI units.
     * @return the data grid in SI units
     */
    public DataGridSi<?> getDataGrid()
    {
        return this.dataGridSi;
    }

    @Override
    public MatrixNxN<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new MatrixNxN<SIQuantity>(this.dataGridSi.instantiateNew(siNew), siUnit);
    }

    @Override
    public VectorN.Row<Q> getRowVector(final int row)
    {
        checkRow(row);
        return VectorN.Row.ofSi(this.dataGridSi.getRowArray(row), getDisplayUnit());
    }

    @Override
    public VectorN.Row<Q> mgetRowVector(final int mRow)
    {
        mcheckRow(mRow);
        return VectorN.Row.ofSi(this.dataGridSi.getRowArray(mRow - 1), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q> getColumnVector(final int col)
    {
        checkCol(col);
        return VectorN.Col.ofSi(this.dataGridSi.getColArray(col), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q> mgetColumnVector(final int mCol)
    {
        mcheckCol(mCol);
        return VectorN.Col.ofSi(this.dataGridSi.getColArray(mCol - 1), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q> getDiagonalVector() throws IllegalStateException
    {
        final int n = rows();
        final double[] data = new double[n];
        for (int i = 0; i < n; i++)
        {
            data[i] = si(i, i);
        }
        // n x 1 column-shape
        return VectorN.Col.ofSi(data, getDisplayUnit());
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
    public MatrixNxN<SIQuantity> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), order());
        return new MatrixNxN<SIQuantity>(this.dataGridSi.instantiateNew(invData), getDisplayUnit().siUnit().invert());
    }

    @Override
    public MatrixNxN<SIQuantity> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), order());
        return new MatrixNxN<SIQuantity>(this.dataGridSi.instantiateNew(invData), getDisplayUnit().siUnit().pow(order() - 1));
    }

    @Override
    public MatrixNxN<SIQuantity> invertEntries()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new MatrixNxN<SIQuantity>(this.dataGridSi.instantiateNew(ArrayMath.reciprocal(si())), siUnit);
    }

    @Override
    public MatrixNxN<SIQuantity> multiplyEntries(final MatrixNxN<?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity>(this.dataGridSi.instantiateNew(ArrayMath.multiply(si(), other.si())), siUnit);
    }

    @Override
    public MatrixNxN<SIQuantity> divideEntries(final MatrixNxN<?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity>(this.dataGridSi.instantiateNew(ArrayMath.divide(si(), other.si())), siUnit);
    }

    @Override
    public MatrixNxN<SIQuantity> multiplyEntries(final Quantity<?> quantity)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity>(this.dataGridSi.instantiateNew(ArrayMath.scaleBy(si(), quantity.si())), siUnit);
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
        MatrixNxN<?> other = (MatrixNxN<?>) obj;
        return Objects.equals(this.dataGridSi, other.dataGridSi);
    }

    // ------------------------------ MATRIX MULTIPLICATION ----------------------------------

    /**
     * Multiply this matrix with another matrix using matrix multiplication and return the result.
     * <p>
     * The unit of the result is the SI-unit “sum” of this matrix and the other matrix (i.e., {@code U.plus(V)} on the
     * underlying {@link SIUnit}s).
     * @param otherMat the right-hand matrix to multiply with
     * @return the product matrix with the correct SI unit
     */
    public MatrixNxN<SIQuantity> multiply(final MatrixNxN<?> otherMat)
    {
        checkMultiply(otherMat);
        final int n = order();
        final double[] resultData = MatrixMath.multiply(si(), otherMat.si(), n, n, n);
        final SIUnit resultUnit = getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity>(this.dataGridSi.instantiateNew(resultData), resultUnit);
    }

    /**
     * Multiply this matrix with a column vector, resulting in a column vector.
     * <p>
     * The unit of the result is the SI-unit “sum” of this matrix and the vector (i.e., {@code U.plus(V)} on the underlying
     * {@link SIUnit}s).
     * @param otherVec the column vector to multiply with (size {@code N})
     * @return the resulting column vector from the multiplication
     * @throws IllegalArgumentException if the vector size does not equal {@code order()}
     */
    public VectorN.Col<SIQuantity> multiply(final VectorN.Col<?> otherVec)
    {
        checkMultiply(otherVec);
        final int n = order();
        final double[] resultData = MatrixMath.multiply(si(), otherVec.si(), n, n, 1);
        final SIUnit resultUnit = getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit());
        return VectorN.Col.ofSi(resultData, resultUnit);
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Check if the length if the row-major matrix is a square.
     * @param length of the array to check
     * @return square root of the length
     * @throws IllegalArgumentException when length is not a square
     */
    protected static int checkSquare(final int length)
    {
        int n = (int) Math.sqrt(length);
        Throw.when(length != n * n, IllegalArgumentException.class,
                "dataInUnit does not contain a square number of entries (%d)", length);
        return n;
    }

    /**
     * Create a new MatrixNxN with a unit, based on a row-major array with values in the given unit.
     * @param dataInUnit the matrix values {a11, a12, 13, ..., aN1, aN2, ..., aNN} expressed in the unit
     * @param unit the unit of the data, also used as the display unit
     * @param <Q> the quantity type
     * @return a new MatrixNxN with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    public static <Q extends Quantity<Q>> MatrixNxN<Q> of(final double[] dataInUnit, final Unit<?, Q> unit)
    {
        Throw.whenNull(dataInUnit, "dataInUnit");
        int n = checkSquare(dataInUnit.length);
        return new MatrixNxN<Q>(DenseDoubleDataSi.of(dataInUnit, n, n, unit), unit);
    }

    /**
     * Create a MatrixNxN without needing generics, based on a row-major array with SI-values.
     * @param dataSi the matrix values {a11, a12, 13, ..., aN1, aN2, ..., aNN} as an array using SI units
     * @param displayUnit the display unit to use
     * @return a new MatrixNxN with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when dataSi does not contain a square number of values
     */
    public static <Q extends Quantity<Q>> MatrixNxN<Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit)
    {
        Throw.whenNull(dataSi, "dataSi");
        int n = checkSquare(dataSi.length);
        return new MatrixNxN<Q>(DenseDoubleDataSi.ofSi(dataSi, n, n), displayUnit);
    }

    /**
     * Create a MatrixNxN without needing generics, based on a row-major array of quantities. The unit is taken from the first
     * quantity in the array.
     * @param data the matrix values {a11, a12, 13, ..., aN1, aN2, ..., aNN} expressed as an array of quantities
     * @return a new MatrixNxN with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when data does not contain a square number of quantities
     */
    public static <Q extends Quantity<Q>> MatrixNxN<Q> of(final Q[] data)
    {
        Throw.whenNull(data, "data");
        Throw.when(data.length == 0, IllegalArgumentException.class, "data.length = 0");
        int n = checkSquare(data.length);
        return new MatrixNxN<Q>(DenseDoubleDataSi.of(data, n, n), data[0].getDisplayUnit());
    }

    /**
     * Create a new MatrixNxN with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the matrix values {a11, a12, ..., a1N}, ..., {aN1, aN2, ..., aNN}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param <Q> the quantity type
     * @return a new MatrixNxN with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> MatrixNxN<Q> ofSi(final double[][] gridSi, final Unit<?, Q> displayUnit)
    {
        return new MatrixNxN<>(DenseDoubleDataSi.ofSi(gridSi), displayUnit);
    }

    /**
     * Create a new MatrixNxN with a unit, based on a 2-dimensional grid with values in the given unit.
     * @param gridInUnit the matrix values {a11, a12, ..., a1N}, ..., {aN1, aN2, ..., aNN}} expressed in the unit
     * @param unit the unit of the values, also used as the display unit
     * @param <Q> the quantity type
     * @return a new MatrixNxN with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> MatrixNxN<Q> of(final double[][] gridInUnit, final Unit<?, Q> unit)
    {
        return new MatrixNxN<>(DenseDoubleDataSi.of(gridInUnit, unit), unit);
    }

    /**
     * Create a MatrixNxN without needing generics, based on a 2-dimensional grid of quantities. The unit is taken from the
     * first quantity in the grid.
     * @param grid the matrix values {a11, a12, ..., a1N}, ..., {aN1, aN2, ..., aNN}} expressed as a 2-dimensional array of
     *            quantities
     * @return a new MatrixNxN with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of quantities
     */
    public static <Q extends Quantity<Q>> MatrixNxN<Q> of(final Q[][] grid)
    {
        Throw.whenNull(grid, "grid");
        Throw.when(grid.length == 0, IllegalArgumentException.class, "grid.length = 0");
        Throw.whenNull(grid[0], "grid[0] = null");
        Throw.when(grid[0].length == 0, IllegalArgumentException.class, "grid[0].length = 0");
        Throw.whenNull(grid[0][0], "grid[0][0] = null");
        return new MatrixNxN<>(DenseDoubleDataSi.of(grid), grid[0][0].getDisplayUnit());
    }

    // ------------------------------------------ AS METHODS ------------------------------------------

    /**
     * Return the matrix "as" a matrix with a known quantity, using a unit to express the result in.
     * <p>
     * The SI units of this matrix and the target unit must match; otherwise an {@link IllegalArgumentException} is thrown. The
     * returned matrix shares the SI values but has the specified display unit.
     * @param <TQ> target quantity type
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target quantity with the specified display unit
     * @throws IllegalArgumentException when the units do not match
     */
    public <TQ extends Quantity<TQ>> MatrixNxN<TQ> as(final Unit<?, TQ> targetUnit)
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "MatrixNxN.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new MatrixNxN<TQ>(this.dataGridSi.instantiateNew(si()), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
    }

    /**
     * Convert this matrix to a {@link Matrix1x1}. The shape must be 1 x 1.
     * @return a {@code Matrix1x1} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 1 x 1
     */
    public Matrix1x1<Q> asMatrix1x1()
    {
        Throw.when(order() != 1, IllegalStateException.class, "asMatrix1x1() called, but matrix is no 1x1 but %dx%d", rows(),
                cols());
        return Matrix1x1.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this matrix to a {@link Matrix2x2}. The shape must be 2 x 2.
     * @return a {@code Matrix2x2} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 2 x 2
     */
    public Matrix2x2<Q> asMatrix2x2()
    {
        Throw.when(order() != 2, IllegalStateException.class, "asMatrix2x2() called, but matrix is no 2x2 but %dx%d", rows(),
                cols());
        return Matrix2x2.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this matrix to a {@link Matrix3x3}. The shape must be 3 x 3.
     * @return a {@code Matrix3x3} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 3 x 3
     */
    public Matrix3x3<Q> asMatrix3x3()
    {
        Throw.when(order() != 3, IllegalStateException.class, "asMatrix3x3() called, but matrix is no 3x3 but %dx%d", rows(),
                cols());
        return Matrix3x3.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

}
