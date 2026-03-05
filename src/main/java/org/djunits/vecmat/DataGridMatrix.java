package org.djunits.vecmat;

import java.lang.reflect.Array;
import java.util.Objects;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.storage.DataGrid;
import org.djunits.vecmat.storage.DenseDoubleData;
import org.djutils.exceptions.Throw;

/**
 * Matrix implements the core functions for a matrix with n x m real-valued entries. The matrix is immutable, except for the
 * display unit, which can be changed. <br>
 * <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the matrix type
 */
public abstract class DataGridMatrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, M extends DataGridMatrix<Q, U, M>>
        extends Matrix<Q, U, DataGridMatrix<Q, U, M>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The data of the matrix, in SI unit. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected final DataGrid<?> dataSi;

    /**
     * Create a new Matrix with a unit and a size.
     * @param dataSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    protected DataGridMatrix(final DataGrid<?> dataSi, final U displayUnit)
    {
        super(displayUnit, dataSi.rows(), dataSi.cols());
        Throw.whenNull(dataSi, "dataSi");
        this.dataSi = dataSi;
    }

    /**
     * Return the internal datagrid object, so we can retrieve data from it.
     * @return the internal datagrid object
     */
    public DataGrid<?> getDataGrid()
    {
        return this.dataSi;
    }

    @Override
    public double[] si()
    {
        return this.dataSi.getDataArray();
    }

    /**
     * Return the (r,c)-value of the matrix in SI or BASE units. Note that this method follows a 1-based (a11, a12, etc.)
     * numbering scheme from matrix calculations, where the internal storage is 0-based.
     * @param row the row, from 1, ..., N
     * @param col the column, from 1, ..., M
     * @return the (r,c)-value of the matrix in SI or BASE units
     */
    @Override
    public double si(final int row, final int col)
    {
        // 1-based operation, where storage is 0-based
        return this.dataSi.get(row - 1, col - 1);
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
        super.setDisplayUnit(newUnit);
        return (M) this;
    }

    /**
     * Return the (r,c)-value of the matrix as a quantity with the correct unit. Note that this method follows a 1-based (a11,
     * a12, etc.) numbering scheme from matrix calculations, where the internal storage is 0-based.
     * @param row the row, from 1, ..., N
     * @param col the column, from 1, ..., M
     * @return the (r,c)-value of the matrix as a quantity with the correct unit
     */
    @Override
    public Q value(final int row, final int col)
    {
        return getDisplayUnit().ofSi(si(row, col)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation creates a {@code Q[][]} using the runtime component type of the first element and fills it in one
     * pass. It avoids intermediate lists and prevents {@code ClassCastException} that can arise from casting {@code Object[][]}
     * to {@code Q[][]}.
     * </p>
     * @return a new 2-D array of quantities with the same shape ({@code rows() x cols()}) as this matrix; each entry
     *         {@code [i-1][j-1]} contains {@code value(i, j)} with the current display unit
     */
    @Override
    @SuppressWarnings("unchecked") // cast from Array.newInstance(...) to Q[][]
    public Q[][] getScalars()
    {
        // Obtain the runtime class of Q by instantiating the first cell.
        final Q[][] out = (Q[][]) Array.newInstance(value(1, 1).getClass(), rows(), cols());
        for (int i = 1; i <= rows(); i++)
        {
            for (int j = 1; j <= cols(); j++)
            {
                out[i - 1][j - 1] = value(i, j);
            }
        }
        return out;

    }

    /**
     * Retrieve a 1-based row from the matrix as a vector. Note that this method follows a 1-based (a11, a12, etc.) numbering
     * scheme from matrix calculations, where the internal storage is 0-based.
     * @param row row of the values to retrieve
     * @return the row as a Vector
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    @Override
    public VectorN.Row<Q, U> getRow(final int row) throws IndexOutOfBoundsException
    {
        double[] data = this.dataSi.getRowArray(row - 1);
        return new VectorN.Row<Q, U>(new DenseDoubleData(data, 1, cols()), getDisplayUnit());
    }

    /**
     * Retrieve a 1-based column from the matrix as a vector. Note that this method follows a 1-based (a11, a12, etc.) numbering
     * scheme from matrix calculations, where the internal storage is 0-based.
     * @param column column of the values to retrieve
     * @return the column as a Vector
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    @Override
    public VectorN.Col<Q, U> getColumn(final int column) throws IndexOutOfBoundsException
    {
        double[] data = this.dataSi.getColArray(column - 1);
        return new VectorN.Col<Q, U>(new DenseDoubleData(data, rows(), 1), getDisplayUnit());
    }

    /**
     * Retrieve the main diagonal of the matrix as a column vector.
     * @return the main diagonal as a Vector
     * @throws IllegalStateException in case the matrix is not square
     */
    @Override
    public VectorN.Col<Q, U> getDiagonal() throws IllegalStateException
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "Matrix is not square");
        double[] data = new double[rows()];
        for (int i = 0; i < rows(); i++)
        {
            data[i] = this.dataSi.get(i, i);
        }
        return new VectorN.Col<Q, U>(new DenseDoubleData(data, rows(), 1), getDisplayUnit());
    }

    /**
     * Retrieve a row from the matrix as an array of scalars.
     * @param row row of the values to retrieve
     * @return the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    @Override
    @SuppressWarnings("unchecked")
    public Q[] getRowScalars(final int row) throws IndexOutOfBoundsException
    {
        Throw.when(row < 1 || row > rows(), IndexOutOfBoundsException.class, "Row %d out of bounds [1..%d]", row, rows());

        // Build a Q[] of length cols() using the runtime class of the first element
        Q first = value(row, 1);
        Q[] out = (Q[]) java.lang.reflect.Array.newInstance(first.getClass(), cols());
        for (int c = 1; c <= cols(); c++)
        {
            out[c - 1] = value(row, c);
        }
        return out;
    }

    /**
     * Retrieve a column from the matrix as an array of scalars.
     * @param column column of the values to retrieve
     * @return the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    @Override
    @SuppressWarnings("unchecked")
    public Q[] getColumnScalars(final int column) throws IndexOutOfBoundsException
    {
        Throw.when(column < 1 || column > cols(), IndexOutOfBoundsException.class, "Column %d out of bounds [1..%d]", column,
                cols());

        Q first = value(1, column);
        Q[] out = (Q[]) java.lang.reflect.Array.newInstance(first.getClass(), rows());
        for (int r = 1; r <= rows(); r++)
        {
            out[r - 1] = value(r, column);
        }
        return out;
    }

    /**
     * Retrieve the main diagonal of the matrix as an array of scalars.
     * @return the main diagonal as a Scalar array
     * @throws IllegalStateException in case the matrix is not square
     */
    @Override
    @SuppressWarnings("unchecked")
    public Q[] getDiagonalScalars() throws IllegalStateException
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "Matrix is not square");

        Q first = value(1, 1);
        Q[] out = (Q[]) java.lang.reflect.Array.newInstance(first.getClass(), rows());
        for (int i = 1; i <= rows(); i++)
        {
            out[i - 1] = value(i, i);
        }
        return out;
    }

    @Override
    public boolean isRelative()
    {
        return value(1, 1).isRelative();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(this.dataSi);
        return result;
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DataGridMatrix<?, ?, ?> other = (DataGridMatrix<?, ?, ?>) obj;
        return Objects.equals(this.dataSi, other.dataSi);
    }

}
