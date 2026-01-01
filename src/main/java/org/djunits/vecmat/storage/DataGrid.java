package org.djunits.vecmat.storage;

import org.djutils.exceptions.Throw;

/**
 * DataGrid is an interface identifies the methods for storing and changing data for an N x M grid. Vectors are stored as a grid
 * wirh dimensions N x 1 or 1 x N. The DataGrid <i>communicates</i> as if it is a dense grid of double values, but underneath,
 * storage can be sparse and float.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public interface DataGrid
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
     * Return element (row, col) from the grid as a double value in SI units, independent of the fact whether it is stored as a
     * double. Note that row and col are 0-based for fast calculations.
     * @param row the row number (0-based)
     * @param col the column number (0-based)
     * @return element (row, col) from the grid as a double value
     * @throws IndexOutOfBoundsException when row &gt; rows() or col &gt; cols() or row &lt; 0 or col &lt; 0
     */
    double getSi(int row, int col);

    /**
     * Set element (r, c) in the grid to a new value. It is provided as a double, but may be stored in a different format. Note
     * that row and col are 0-based for fast calculations.
     * @param row the row number (0-based)
     * @param col the column number (0-based)
     * @param si the new value of element (row, col) in SI-units
     * @throws IndexOutOfBoundsException when row &gt; rows() or col &gt; cols() or row &lt; 0 or col &lt; 0
     */
    void setSi(int row, int col, double si);

    /**
     * Add the provided value to element (row, col) in the grid. It is provided as a double, but may be stored in a different
     * format. Note that row and col are 0-based for fast calculations.
     * @param row the row number (0-based)
     * @param col the column number (0-based)
     * @param si the SI-value to add to element (row, col)
     * @throws IndexOutOfBoundsException when row &gt; rows() or col &gt; cols() or row &lt; 0 or col &lt; 0
     */
    void addSi(int row, int col, double si);

    /**
     * Return the data in row-major format. When the data is available in the correct format, no safe copy is made.
     * @return the data in row-major format
     */
    double[] getDataSi();
    
    /**
     * Get the data of a row as a (dense) double array.
     * @param row the row number (0-based)
     * @return a double[] of length cols() with all the row values
     * @throws IndexOutOfBoundsException when row &gt; rows() or col &lt; 0
     */
    default double[] getRowSi(final int row)
    {
        Throw.when(row < 0 || row >= rows(), IndexOutOfBoundsException.class, "row %d not in range 0..%d", row, rows());
        double[] result = new double[cols()];
        for (int c = 0; c < cols(); c++)
        {
            result[c] = getSi(row, c);
        }
        return result;
    }

    /**
     * Get the data of a column as a (dense) double array.
     * @param col the column number (0-based)
     * @return a double[] of length rows() with all the column values
     * @throws IndexOutOfBoundsException when col &gt; cols() or col &lt; 0
     */
    default double[] getColSi(final int col)
    {
        Throw.when(col < 0 || col >= cols(), IndexOutOfBoundsException.class, "column %d not in range 0..%d", col, cols());
        double[] result = new double[rows()];
        for (int r = 0; r < rows(); r++)
        {
            result[r] = getSi(r, col);
        }
        return result;
    }

}
