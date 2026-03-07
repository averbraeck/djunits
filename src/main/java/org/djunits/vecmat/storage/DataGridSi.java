package org.djunits.vecmat.storage;

import org.djutils.exceptions.Throw;

/**
 * DataGrid is an interface identifies the methods for storing and changing data for an N x M grid. Vectors are stored as a grid
 * with dimensions N x 1 or 1 x N. The DataGrid <i>communicates</i> as if it is a dense grid of double values, but underneath,
 * storage can be, e.g., sparse and/or float.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <D> The datagrid type
 */
public interface DataGridSi<D extends DataGridSi<D>>
{
    /**
     * Return the number of rows in the grid.
     * @return the number of rows in the grid
     */
    int rows();

    /**
     * Return the number of columns in the grid.
     * @return the number of columns in the grid
     */
    int cols();

    /**
     * Return element (row, col) from the grid as a double value, independent of the fact whether it is stored as a double. Note
     * that row and col are 0-based for fast calculations.
     * @param row the row number (0-based)
     * @param col the column number (0-based)
     * @return element (row, col) from the grid as a double value
     * @throws IndexOutOfBoundsException when row &gt; rows() or col &gt; cols() or row &lt; 0 or col &lt; 0
     */
    double get(int row, int col);

    /**
     * Return the data in row-major format. When the data is available in the correct format, NO safe copy is made.
     * @return the data in row-major format
     */
    double[] getDataArray();

    /**
     * Return a deep copy of the DataGrid object.
     * @return a deep copy of the DataGrid object
     */
    D copy();

    /**
     * Compute and return the number of non-zero cells in this indexed value. Counts entries that are not exactly 0.0 (including
     * -0.0 as zero). NaN and infinite values are counted as non-zero.
     * @return the number of non-zero cells
     */
    int cardinality();

    /**
     * Instantiate a new version of the DataGrid object with the given data and the same number of rows and columns.
     * @param data the data in row-major format
     * @return a new version of the DataGrid object with the given data, same number of rows and columns
     */
    D instantiateNew(double[] data);

    /**
     * Instantiate a new version of the DataGrid object with the given data and the given number of rows and columns.
     * @param data the data in row-major format
     * @param newRows the new number of rows
     * @param newCols the new number of columms
     * @return a new version of the DataGrid object with the given data, new number of rows and columns
     */
    D instantiateNew(double[] data, int newRows, int newCols);

    /**
     * Get the data of a row as a (dense) double array.
     * @param row the row number (0-based)
     * @return a double[] of length cols() with all the row values
     * @throws IndexOutOfBoundsException when row &gt; rows() or col &lt; 0
     */
    default double[] getRowArray(final int row)
    {
        Throw.when(row < 0 || row >= rows(), IndexOutOfBoundsException.class, "row %d not in range 0..%d", row, rows() - 1);
        double[] result = new double[cols()];
        for (int c = 0; c < cols(); c++)
        {
            result[c] = get(row, c);
        }
        return result;
    }

    /**
     * Get the data of a column as a (dense) double array.
     * @param col the column number (0-based)
     * @return a double[] of length rows() with all the column values
     * @throws IndexOutOfBoundsException when col &gt; cols() or col &lt; 0
     */
    default double[] getColArray(final int col)
    {
        Throw.when(col < 0 || col >= cols(), IndexOutOfBoundsException.class, "column %d not in range 0..%d", col, cols() - 1);
        double[] result = new double[rows()];
        for (int r = 0; r < rows(); r++)
        {
            result[r] = get(r, col);
        }
        return result;
    }

    /**
     * Test if two datagrids are equal to a maximum absolute error epsilon. The number of rows and columns is also compared.
     * @param other the other datagrid to compare to
     * @param epsilon the maximum absolute error for a value
     * @return whether two datagrid arrays are equal to a maximum absolute error epsilon
     */
    @SuppressWarnings("checkstyle:needbraces")
    default boolean equals(final DataGridSi<?> other, final double epsilon)
    {
        Throw.when(epsilon < 0, IllegalArgumentException.class, "epsilon should be >= 0");
        if (other == null)
            return false;
        if (rows() != other.rows() || cols() != other.cols())
            return false;
        double[] da1 = getDataArray();
        double[] da2 = other.getDataArray();
        for (int i = 0; i < da1.length; i++)
        {
            if (Math.abs(da1[i] - da2[i]) > epsilon)
                return false;
        }
        return true;
    }

}
