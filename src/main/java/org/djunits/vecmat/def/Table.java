package org.djunits.vecmat.def;

import java.lang.reflect.Array;

import org.djunits.formatter.Format;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djutils.exceptions.Throw;

/**
 * Table contains a number of standard operations on 2-dimensional tables of relative quantities. The Matrix and QuantityTable
 * are examples of classes that extends Table.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <T> the 'SELF' table type
 * @param <SI> the table type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic table type with generics &lt;?, ?&lt; for Hadamard operations
 * @param <TT> the type of the transposed version of the table
 */
public abstract class Table<Q extends Quantity<Q>, T extends Table<Q, T, SI, H, TT>, SI extends Table<SIQuantity, SI, ?, ?, ?>,
        H extends Table<?, ?, ?, ?, ?>, TT extends Table<Q, TT, ?, ?, T>> extends VectorMatrix<Q, T, SI, H, TT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Table with a unit.
     * @param displayUnit the display unit to use
     */
    public Table(final Unit<?, Q> displayUnit)
    {
        super(displayUnit);
    }

    /**
     * Return the si-value at position (row, col), where both row and col are 0-based values.
     * @param row the row (0-based)
     * @param col the column (0-based)
     * @return the si-value at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 0 or larger than number of rows/columns - 1.
     */
    public abstract double si(int row, int col) throws IndexOutOfBoundsException;

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
    public Q get(final int row, final int col) throws IndexOutOfBoundsException
    {
        return getDisplayUnit().ofSi(si(row, col)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the quantity at position (row, col), where both row and col are 1-based values.
     * @param mRow the row (1-based)
     * @param mCol the column (1-based)
     * @return the quantity at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 1 or larger than number of rows/columns.
     */
    public Q mget(final int mRow, final int mCol) throws IndexOutOfBoundsException
    {
        return getDisplayUnit().ofSi(msi(mRow, mCol)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the table or matrix as a 2D array of scalars.
     * @return a new Q[rows()][cols()] array; entry [i][j] contains get(i, j).
     */
    @SuppressWarnings("unchecked") // cast from Array.newInstance(...) to Q[][]
    public Q[][] getScalarGrid()
    {
        // Determine the runtime type of Q using the first cell; constructors guarantee rows, cols >= 0.
        final Q first = get(0, 0);
        final Class<?> qClass = first.getClass();

        // Allocate a Q[rows()][cols()] array and fill it.
        final Q[][] out = (Q[][]) Array.newInstance(qClass, rows(), cols());
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
     * Return the table or matrix as a 2D array of double SI values.
     * @return a new double[rows()][cols()] array; entry [i][j] contains si(i, j).
     */
    public double[][] getSiGrid()
    {
        // Allocate a double[rows()][cols()] array and fill it.
        final double[][] out = (double[][]) Array.newInstance(double.class, rows(), cols());
        for (int i = 0; i < rows(); i++)
        {
            for (int j = 0; j < cols(); j++)
            {
                out[i][j] = si(i, j);
            }
        }
        return out;
    }

    /**
     * Return a quantity row (0-based) from the table or matrix. Note that the specific vector to return can be tightened by the
     * implementing class.
     * @param row the row number to retrieve (0-based)
     * @return a row vector with the data at the given row
     */
    public abstract Vector<Q, ?, ?, ?, ?> getRowVector(int row);

    /**
     * Return a quantity row (1-based) from the table or matrix. Note that the specific vector to return can be tightened by the
     * implementing class.
     * @param mRow the row number to retrieve (1-based)
     * @return a row vector with the data at the given row
     */
    public abstract Vector<Q, ?, ?, ?, ?> mgetRowVector(int mRow);

    /**
     * Return a quantity column (0-based) from the table or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param col the column number to retrieve (0-based)
     * @return a column vector with the data at the given column
     */
    public abstract Vector<Q, ?, ?, ?, ?> getColumnVector(int col);

    /**
     * Return a quantity column (1-based) from the table or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param mCol the column number to retrieve (1-based)
     * @return a column vector with the data at the given column
     */
    public abstract Vector<Q, ?, ?, ?, ?> mgetColumnVector(int mCol);

    /**
     * Return an array with SI-values for the given row (0-based) from the table or matrix.
     * @param row the row number to retrieve (0-based)
     * @return an array with SI-values with the data at the given row
     */
    public abstract double[] getRowSi(int row);

    /**
     * Return an array with SI-values for the given row (1-based) from the table or matrix.
     * @param mRow the row number to retrieve (1-based)
     * @return an array with SI-values with the data at the given row
     */
    public double[] mgetRowSi(final int mRow)
    {
        mcheckRow(mRow);
        return getRowSi(mRow - 1);
    }

    /**
     * Return an array with SI-values for the given column (0-based) from the table or matrix.
     * @param col the column number to retrieve (0-based)
     * @return an array with SI-values with the data at the given column
     */
    public abstract double[] getColumnSi(int col);

    /**
     * Return an array with SI-values for the given column (1-based) from the table or matrix.
     * @param mCol the column number to retrieve (1-based)
     * @return an array with SI-values with the data at the given column
     */
    public double[] mgetColumnSi(final int mCol)
    {
        mcheckCol(mCol);
        return getColumnSi(mCol - 1);
    }

    /**
     * Retrieve a row (0-based) from the table or matrix as an array of scalars.
     * @param row row of the values to retrieve (0-based)
     * @return the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    @SuppressWarnings("unchecked")
    public Q[] getRowScalars(final int row) throws IndexOutOfBoundsException
    {
        checkRow(row);

        // Build a Q[] of length cols() using the runtime class of the first element
        Q first = get(row, 0);
        Q[] out = (Q[]) Array.newInstance(first.getClass(), cols());
        for (int c = 0; c < cols(); c++)
        {
            out[c] = get(row, c);
        }
        return out;
    }

    /**
     * Retrieve a row (1-based) from the table or matrix as an array of scalars.
     * @param mRow row of the values to retrieve (1-based)
     * @return the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    public Q[] mgetRowScalars(final int mRow) throws IndexOutOfBoundsException
    {
        mcheckRow(mRow);
        return getRowScalars(mRow - 1);
    }

    /**
     * Retrieve a column (0-based) from the table or matrix as an array of scalars.
     * @param col column of the values to retrieve (0-based)
     * @return the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    @SuppressWarnings("unchecked")
    public Q[] getColumnScalars(final int col) throws IndexOutOfBoundsException
    {
        checkCol(col);

        Q first = get(0, col);
        Q[] out = (Q[]) Array.newInstance(first.getClass(), rows());
        for (int r = 0; r < rows(); r++)
        {
            out[r] = get(r, col);
        }
        return out;
    }

    /**
     * Retrieve a column (1-based) from the table or matrix as an array of scalars.
     * @param mCol column of the values to retrieve (1-based)
     * @return the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    public Q[] mgetColumnScalars(final int mCol) throws IndexOutOfBoundsException
    {
        mcheckCol(mCol);
        return getColumnScalars(mCol - 1);
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public String toString(final Unit<?, Q> withUnit)
    {
        var s = new StringBuilder();
        for (int r = 0; r < rows(); r++)
        {
            s.append(r == 0 ? "[" : "\n ");
            for (int c = 0; c < cols(); c++)
            {
                if (c > 0)
                    s.append(", ");
                s.append(Format.format(withUnit.fromBaseValue(si(r, c))));
            }
        }
        s.append("] ");
        s.append(withUnit.getDisplayAbbreviation());
        return s.toString();
    }

    @Override
    public String toString()
    {
        return toString(getDisplayUnit());
    }

    // ------------------------------------ HELPER METHODS ------------------------------------

    /**
     * Check if the 0-based row is within bounds.
     * @param row the 0-based row to check
     * @throws IndexOutOfBoundsException when row is out of bounds
     */
    protected void checkRow(final int row)
    {
        Throw.when(row < 0 || row >= rows(), IndexOutOfBoundsException.class, "Row %d out of bounds [0..%d]", row, rows() - 1);
    }

    /**
     * Check if the 0-based column is within bounds.
     * @param col the 0-based column to check
     * @throws IndexOutOfBoundsException when column is out of bounds
     */
    protected void checkCol(final int col)
    {
        Throw.when(col < 0 || col >= cols(), IndexOutOfBoundsException.class, "Column %d out of bounds [0..%d]", col,
                cols() - 1);
    }

    /**
     * Check if the 1-based row is within bounds.
     * @param mRow the 1-based row to check
     * @throws IndexOutOfBoundsException when row is out of bounds
     */
    protected void mcheckRow(final int mRow)
    {
        Throw.when(mRow < 1 || mRow > rows(), IndexOutOfBoundsException.class, "Row %d out of bounds [1..%d]", mRow, rows());
    }

    /**
     * Check if the 1-based column is within bounds.
     * @param mCol the 1-based column to check
     * @throws IndexOutOfBoundsException when column is out of bounds
     */
    protected void mcheckCol(final int mCol)
    {
        Throw.when(mCol < 1 || mCol > cols(), IndexOutOfBoundsException.class, "Column %d out of bounds [1..%d]", mCol, cols());
    }

}
