package org.djunits.vecmat;

import java.util.ArrayList;
import java.util.List;
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
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
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
     * @param col the column, from 1, ..., N
     * @return the (r,c)-value of the matrix in SI or BASE units
     */
    @Override
    public double si(final int row, final int col)
    {
        // 1-based operation, where storage is 0-based
        return this.dataSi.get(row - 1, col - 1);
    }

    /**
     * Return the (r,c)-value of the matrix as a quantity with the correct unit. Note that this method follows a 1-based (a11,
     * a12, etc.) numbering scheme from matrix calculations, where the internal storage is 0-based.
     * @param row the row, from 1, ..., N
     * @param col the column, from 1, ..., N
     * @return the (r,c)-value of the matrix as a quantity with the correct unit
     */
    @Override
    public Q value(final int row, final int col)
    {
        return getDisplayUnit().ofSi(si(row, col)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the vector as a 2D-array of scalars.
     * @return the vector as a 2D-array of scalars
     */
    @Override
    @SuppressWarnings("unchecked")
    public Q[][] getScalars()
    {
        List<Q[]> result = new ArrayList<>();
        for (int i = 1; i <= rows(); i++)
        {
            List<Q> row = new ArrayList<>();
            for (int j = 1; j <= cols(); j++)
            {
                row.add(value(i, j));
            }
            result.add((Q[]) row.toArray());
        }
        return (Q[][]) result.toArray();
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
        List<Q> rowScalars = new ArrayList<>();
        for (int j = 1; j <= cols(); j++)
        {
            rowScalars.add(value(row, j));
        }
        return (Q[]) rowScalars.toArray();
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
        List<Q> columnScalars = new ArrayList<>();
        for (int i = 1; i <= cols(); i++)
        {
            columnScalars.add(value(i, column));
        }
        return (Q[]) columnScalars.toArray();
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
        List<Q> diagScalars = new ArrayList<>();
        for (int i = 1; i <= cols(); i++)
        {
            diagScalars.add(value(i, i));
        }
        return (Q[]) diagScalars.toArray();
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
