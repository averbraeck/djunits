package org.djunits.vecmat.def;

import java.lang.reflect.Array;

import org.djunits.formatter.FormatHint;
import org.djunits.formatter.Formatter;
import org.djunits.formatter.UnitHint;
import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;

/**
 * AbsTable contains a number of standard operations on 2-dimensional tables that contain absolute quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the quantity type
 * @param <MA> the absolute table type
 * @param <MQ> the relative table type
 * @param <MAT> the type of the transposed version of the absolute table
 */
public abstract class AbsTable<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>, MA extends AbsTable<A, Q, MA, MQ, MAT>,
        MQ extends Table<Q, MQ, ?, ?, ?>, MAT extends AbsTable<A, Q, MAT, ?, MA>> extends AbsVectorMatrix<A, Q, MA, MQ, MAT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new matrix of absolute values with a reference point.
     * @param matrix the underlying relative matrix with SI values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsTable(final MQ matrix, final Reference<?, A, Q> reference)
    {
        super(matrix, reference);
    }

    /**
     * Return the si-value at position (row, col), where both row and col are 0-based values.
     * @param row the row (0-based)
     * @param col the column (0-based)
     * @return the si-value at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 0 or larger than number of rows/columns - 1.
     */
    public double si(final int row, final int col) throws IndexOutOfBoundsException
    {
        return getRelativeVecMat().si(row, col);
    }

    /**
     * Return the si-value at position (row, col), where both row and col are 1-based values.
     * @param mRow the row (1-based)
     * @param mCol the column (1-based)
     * @return the si-value at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 1 or larger than number of rows/columns.
     */
    public double msi(final int mRow, final int mCol) throws IndexOutOfBoundsException
    {
        return si(mRow - 1, mCol - 1);
    }

    /**
     * Return the quantity at position (row, col), where both row and col are 0-based values.
     * @param row the row (0-based)
     * @param col the column (0-based)
     * @return the quantity at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 0 or larger than number of rows/columns - 1.
     */
    public A get(final int row, final int col) throws IndexOutOfBoundsException
    {
        return getReference().instantiate(getDisplayUnit().ofSi(si(row, col))).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the quantity at position (row, col), where both row and col are 1-based values.
     * @param mRow the row (1-based)
     * @param mCol the column (1-based)
     * @return the quantity at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 1 or larger than number of rows/columns.
     */
    public A mget(final int mRow, final int mCol) throws IndexOutOfBoundsException
    {
        return getReference().instantiate(getDisplayUnit().ofSi(msi(mRow, mCol))).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the vector or matrix as a 2D array of scalars.
     * @return a new A[rows()][cols()] array; entry [i][j] contains get(i, j).
     */
    @SuppressWarnings("unchecked") // cast from Array.newInstance(...) to Q[][]
    public A[][] getScalarGrid()
    {
        // Determine the runtime type of Q using the first cell; constructors guarantee rows, cols >= 0.
        final A first = get(0, 0);
        final Class<?> qClass = first.getClass();

        // Allocate a Q[rows()][cols()] array and fill it.
        final A[][] out = (A[][]) Array.newInstance(qClass, rows(), cols());
        for (int i = 0; i < rows(); i++)
        {
            for (int j = 0; j < cols(); j++)
            {
                out[i][j] = get(i, j);
            }
        }
        return out;
    }

    /**
     * Return the vector or matrix as a 2D array of double SI values.
     * @return a new double[rows()][cols()] array; entry [i][j] contains si(i, j).
     */
    public double[][] getSiGrid()
    {
        // Allocate a double[rows()][cols()] array and fill it.
        final double[][] out = (double[][]) Array.newInstance(double.class, rows(), cols());
        for (int r = 0; r < rows(); r++)
        {
            for (int c = 0; c < cols(); c++)
            {
                out[r][c] = si(r, c);
            }
        }
        return out;
    }

    /**
     * Return the vector or matrix as a row-major array of scalars.
     * @return a new A[rows() * cols()] array.
     */
    @SuppressWarnings("unchecked") // cast from Array.newInstance(...) to A[][]
    public A[] getScalarArray()
    {
        // Determine the runtime type of Q using the first cell; constructors guarantee rows, cols >= 0.
        final A first = get(0, 0);
        final Class<?> qClass = first.getClass();
        int cols = cols();

        // Allocate a Q[rows() * cols()] array and fill it.
        final A[] out = (A[]) Array.newInstance(qClass, rows() * cols());
        for (int r = 0; r < rows(); r++)
        {
            for (int c = 0; c < cols; c++)
            {
                out[r * cols + c] = get(r, c);
            }
        }
        return out;
    }

    /**
     * Return the vector or matrix as a row-major array of double SI values.
     * @return a new double[rows() * cols()] array.
     */
    public double[] getSiArray()
    {
        // Allocate a double[rows()][cols()] array and fill it.
        final double[] out = new double[rows() * cols()];
        int cols = cols();
        for (int r = 0; r < rows(); r++)
        {
            for (int c = 0; c < cols; c++)
            {
                out[r * cols + c] = si(r, c);
            }
        }
        return out;
    }

    /**
     * Return a quantity row (0-based) from the vector or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param row the row number to retrieve (0-based)
     * @return a row vector with the data at the given row
     */
    public abstract AbsVector<A, Q, ?, ?, ?> getRowVector(int row);

    /**
     * Return a quantity row (1-based) from the vector or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param mRow the row number to retrieve (1-based)
     * @return a row vector with the data at the given row
     */
    public abstract AbsVector<A, Q, ?, ?, ?> mgetRowVector(int mRow);

    /**
     * Return a quantity column (0-based) from the vector or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param col the column number to retrieve (0-based)
     * @return a column vector with the data at the given column
     */
    public abstract AbsVector<A, Q, ?, ?, ?> getColumnVector(int col);

    /**
     * Return a quantity column (1-based) from the vector or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param mCol the column number to retrieve (1-based)
     * @return a column vector with the data at the given column
     */
    public abstract AbsVector<A, Q, ?, ?, ?> mgetColumnVector(int mCol);

    /**
     * Return an array with SI-values for the given row (0-based) from the vector or matrix.
     * @param row the row number to retrieve (0-based)
     * @return an array with SI-values with the data at the given row
     */
    public double[] getRowSi(final int row)
    {
        return getRelativeVecMat().getRowSi(row);
    }

    /**
     * Return an array with SI-values for the given row (1-based) from the vector or matrix.
     * @param mRow the row number to retrieve (1-based)
     * @return an array with SI-values with the data at the given row
     */
    public double[] mgetRowSi(final int mRow)
    {
        return getRelativeVecMat().mgetRowSi(mRow);
    }

    /**
     * Return an array with SI-values for the given column (0-based) from the vector or matrix.
     * @param col the column number to retrieve (0-based)
     * @return an array with SI-values with the data at the given column
     */
    public double[] getColumnSi(final int col)
    {
        return getRelativeVecMat().getColumnSi(col);
    }

    /**
     * Return an array with SI-values for the given column (1-based) from the vector or matrix.
     * @param mCol the column number to retrieve (1-based)
     * @return an array with SI-values with the data at the given column
     */
    public double[] mgetColumnSi(final int mCol)
    {
        return getRelativeVecMat().mgetColumnSi(mCol);
    }

    /**
     * Retrieve a row (0-based) from the matrix as an array of scalars.
     * @param row row of the values to retrieve (0-based)
     * @return the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    @SuppressWarnings("unchecked")
    public A[] getRowScalars(final int row) throws IndexOutOfBoundsException
    {
        checkRow(row);

        // Build a Q[] of length cols() using the runtime class of the first element
        A first = get(row, 0);
        A[] out = (A[]) Array.newInstance(first.getClass(), cols());
        for (int c = 0; c < cols(); c++)
        {
            out[c] = get(row, c);
        }
        return out;
    }

    /**
     * Retrieve a row (1-based) from the matrix as an array of scalars.
     * @param mRow row of the values to retrieve (1-based)
     * @return the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    public A[] mgetRowScalars(final int mRow) throws IndexOutOfBoundsException
    {
        mcheckRow(mRow);
        return getRowScalars(mRow - 1);
    }

    /**
     * Retrieve a column (0-based) from the matrix as an array of scalars.
     * @param col column of the values to retrieve (0-based)
     * @return the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    @SuppressWarnings("unchecked")
    public A[] getColumnScalars(final int col) throws IndexOutOfBoundsException
    {
        checkCol(col);

        A first = get(0, col);
        A[] out = (A[]) Array.newInstance(first.getClass(), rows());
        for (int r = 0; r < rows(); r++)
        {
            out[r] = get(r, col);
        }
        return out;
    }

    /**
     * Retrieve a column (1-based) from the matrix as an array of scalars.
     * @param mCol column of the values to retrieve (1-based)
     * @return the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    public A[] mgetColumnScalars(final int mCol) throws IndexOutOfBoundsException
    {
        mcheckCol(mCol);
        return getColumnScalars(mCol - 1);
    }

    /**********************************************************************************/
    /*************************** STRING AND FORMATTING METHODS ************************/
    /**********************************************************************************/

    /**
     * Concise description of this absolute matrix or table.
     * @return a String with the absolute matrix or table, with the unit attached.
     */
    @Override
    public String toString()
    {
        return toString(new FormatHint[] {});
    }

    /**
     * String representation of this absolute matrix or table after applying the format hints.
     * @param hints the format hints to apply for the absolute matrix or table
     * @return a String representation of this absolute matrix or table, formatted according to the format hints
     */
    public String toString(final FormatHint... hints)
    {
        return Formatter.formatAbsTable(this, hints);
    }

    /**
     * String representation of this absolute matrix or table, expressed in the specified unit.
     * @param targetUnit the unit into which the values of the absolute matrix or table are converted for display
     * @return printable string with the absolute matrix or table's values expressed in the specified unit
     */
    @Override
    public String toString(final Unit<?, Q> targetUnit)
    {
        return toString(new UnitHint().setDisplayUnit(targetUnit));
    }

}
