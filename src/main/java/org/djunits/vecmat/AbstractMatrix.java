package org.djunits.vecmat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.djunits.formatter.Format;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.operations.Matrix;
import org.djutils.exceptions.Throw;

/**
 * Matrix implements the core functions for a matrix with n x m real-valued entries. The matrix is immutable, except for the
 * display unit, which can be changed.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the matrix type
 */
public abstract class AbstractMatrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, M extends AbstractMatrix<Q, U, M>>
        implements Matrix<Q, U, M>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The display unit. */
    private U displayUnit;

    /** The number of rows. */
    private final int rows;

    /** The number of columns. */
    private final int cols;

    /**
     * Create a new Matrix with a unit and a size.
     * @param displayUnit the display unit to use
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    protected AbstractMatrix(final U displayUnit, final int rows, final int cols)
    {
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        this.displayUnit = displayUnit;
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Return the display unit of this matrix.
     * @return the display unit of this matrix
     */
    @Override
    public U getDisplayUnit()
    {
        return this.displayUnit;
    }

    /**
     * Set a new display unit of this matrix.
     * @param newUnit the new display unit of this matrix
     * @return the matrix for fluent design
     */
    @SuppressWarnings("unchecked")
    @Override
    public M setDisplayUnit(final U newUnit)
    {
        Throw.whenNull(this.displayUnit, "displayUnit");
        this.displayUnit = newUnit;
        return (M) this;
    }

    @Override
    public int rows()
    {
        return this.rows;
    }

    @Override
    public int cols()
    {
        return this.cols;
    }

    @Override
    public abstract double si(int row, int col);

    @Override
    public Q get(final int row, final int col)
    {
        return this.displayUnit.ofSi(si(row, col)).setDisplayUnit(this.displayUnit);
    }

    @Override
    @SuppressWarnings("unchecked") // cast from Array.newInstance(...) to Q[][]
    public Q[][] getScalars()
    {
        // Determine the runtime type of Q using the first cell; constructors guarantee rows, cols >= 1.
        final Q first = get(1, 1);
        final Class<?> qClass = first.getClass();

        // Allocate a Q[rows()][cols()] array and fill it.
        final Q[][] out = (Q[][]) Array.newInstance(qClass, rows(), cols());
        for (int i = 1; i <= rows(); i++)
        {
            for (int j = 1; j <= cols(); j++)
            {
                out[i - 1][j - 1] = get(i, j);
            }
        }
        return out;
    }

    /**
     * Retrieve a row from the matrix as a vector.
     * @param row row of the values to retrieve
     * @return the row as a Vector
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    public VectorN.Row<Q, U> getRow(final int row) throws IndexOutOfBoundsException
    {
        if (row < 1 || row > rows())
        {
            throw new IndexOutOfBoundsException("Row " + row + " out of bounds [1.." + rows() + "]");
        }
        final double[] data = new double[cols()];
        for (int c = 1; c <= cols(); c++)
        {
            data[c - 1] = si(row, c);
        }
        // 1 x cols() row-shape
        return VectorN.Row.of(data, getDisplayUnit());
    }

    /**
     * Retrieve a column from the matrix as a vector.
     * @param column column of the values to retrieve
     * @return the column as a Vector
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    public VectorN.Col<Q, U> getColumn(final int column) throws IndexOutOfBoundsException
    {
        if (column < 1 || column > cols())
        {
            throw new IndexOutOfBoundsException("Column " + column + " out of bounds [1.." + cols() + "]");
        }
        final double[] data = new double[rows()];
        for (int r = 1; r <= rows(); r++)
        {
            data[r - 1] = si(r, column);
        }
        // rows() x 1 column-shape
        return VectorN.Col.ofSi(data, getDisplayUnit());
    }

    /**
     * Retrieve the main diagonal of the matrix as a column vector.
     * @return the main diagonal as a Vector
     * @throws IllegalStateException in case the matrix is not square
     */
    public VectorN.Col<Q, U> getDiagonal() throws IllegalStateException
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "Matrix is not square");
        final int n = rows();
        final double[] data = new double[n];
        for (int i = 1; i <= n; i++)
        {
            data[i - 1] = si(i, i);
        }
        // n x 1 column-shape
        return VectorN.Col.ofSi(data, getDisplayUnit());
    }

    /**
     * Retrieve a row from the matrix as an array of scalars.
     * @param row row of the values to retrieve
     * @return the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    @SuppressWarnings("unchecked")
    public Q[] getRowScalars(final int row) throws IndexOutOfBoundsException
    {
        List<Q> result = new ArrayList<>();
        for (int j = 1; j <= cols(); j++)
        {
            result.add(get(row, j));
        }

        // Use the runtime type of the first element
        Q sample = result.get(0);
        Q[] array = (Q[]) Array.newInstance(sample.getClass(), result.size());
        return result.toArray(array);
    }

    /**
     * Retrieve a column from the matrix as an array of scalars.
     * @param column column of the values to retrieve
     * @return the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    @SuppressWarnings("unchecked")
    public Q[] getColumnScalars(final int column) throws IndexOutOfBoundsException
    {
        List<Q> result = new ArrayList<>();
        for (int i = 1; i <= rows(); i++)
        {
            result.add(get(i, column));
        }

        Q sample = result.get(0);
        Q[] array = (Q[]) Array.newInstance(sample.getClass(), result.size());
        return result.toArray(array);
    }

    /**
     * Retrieve the main diagonal of the matrix as an array of scalars.
     * @return the main diagonal as a Scalar array
     * @throws IllegalStateException in case the matrix is not square
     */
    @SuppressWarnings("unchecked")
    public Q[] getDiagonalScalars() throws IllegalStateException
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "Matrix is not square");

        List<Q> result = new ArrayList<>();
        for (int i = 1; i <= rows(); i++)
        {
            result.add(get(i, i));
        }

        Q sample = result.get(0);
        Q[] array = (Q[]) Array.newInstance(sample.getClass(), result.size());
        return result.toArray(array);
    }

    @Override
    public boolean isRelative()
    {
        return get(1, 1).isRelative();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.cols, this.rows);
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
        AbstractMatrix<?, ?, ?> other = (AbstractMatrix<?, ?, ?>) obj;
        return this.cols == other.cols && this.rows == other.rows;
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public String toString(final U withUnit)
    {
        var s = new StringBuilder();
        for (int r = 1; r <= rows(); r++)
        {
            s.append(r == 1 ? "[" : "\n ");
            for (int c = 1; c <= cols(); c++)
            {
                if (c > 1)
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

}
