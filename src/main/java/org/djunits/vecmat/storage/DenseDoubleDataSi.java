package org.djunits.vecmat.storage;

import java.util.Arrays;
import java.util.Objects;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djutils.exceptions.Throw;

/**
 * DenseDoubleData implements a dense data grid for N x M matrices or N x 1 or 1 x M vectors with double values.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class DenseDoubleDataSi implements DataGridSi<DenseDoubleDataSi>
{
    /** The data stored in row-major format. */
    private final double[] data;

    /** The number of rows. */
    private final int rows;

    /** the number of columns. */
    private final int cols;

    /**
     * Instantiate a data object with one array in row-major format. Note that NO safe copy of the data is stored.
     * @param data the data in row-major format
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     */
    public DenseDoubleDataSi(final double[] data, final int rows, final int cols)
    {
        Throw.whenNull(data, "data");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        Throw.when(data.length != rows * cols, IllegalArgumentException.class,
                "Data object length != rows * cols, %d != %d * %d", data.length, rows, cols);
        this.data = data;
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Instantiate a data object with a double[rows][cols]. A safe copy of the data is stored.
     * @param data the data in row-major format
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public DenseDoubleDataSi(final double[][] data)
    {
        Throw.whenNull(data, "data");
        Throw.when(data.length == 0, IllegalArgumentException.class, "Number of rows in the data matrix = 0");
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new double[this.rows * this.cols];
        for (int r = 0; r < this.rows; r++)
        {
            Throw.when(data[r].length != this.cols, IllegalArgumentException.class,
                    "Number of columns in row %d (%d) is not equal to number of columns in row 0 (%d)", r, data[r].length,
                    this.cols);
            for (int c = 0; c < this.cols; c++)
                this.data[r * this.cols + c] = data[r][c];
        }
    }

    /**
     * Instantiate a data object with a Q[rows][cols]. A safe copy of the data is stored.
     * @param data the data in row-major format
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> DenseDoubleDataSi(final Q[][] data)
    {
        Throw.whenNull(data, "data");
        Throw.when(data.length == 0, IllegalArgumentException.class, "Number of rows in the data matrix = 0");
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new double[this.rows * this.cols];
        for (int r = 0; r < this.rows; r++)
        {
            Throw.when(data[r].length != this.cols, IllegalArgumentException.class,
                    "Number of columns in row %d (%d) is not equal to number of columns in row 0 (%d)", r, data[r].length,
                    this.cols);
            for (int c = 0; c < this.cols; c++)
                this.data[r * this.cols + c] = data[r][c].si();
        }
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

    /**
     * Check whether the row and column are within bounds.
     * @param row the row number
     * @param col the column number
     * @throws IndexOutOfBoundsException when row &gt; rows() or col &gt; cols() or row &lt; 0 or col &lt; 0
     */
    private void checkRowCol(final int row, final int col) throws IndexOutOfBoundsException
    {
        Throw.when(row < 0 || row >= this.rows, IndexOutOfBoundsException.class, "row %d not in range 0..%d", row, this.rows);
        Throw.when(col < 0 || col >= this.cols, IndexOutOfBoundsException.class, "column %d not in range 0..%d", col,
                this.cols);
    }

    @Override
    public double get(final int row, final int col)
    {
        checkRowCol(row, col);
        return this.data[row * this.cols + col];
    }

    @Override
    public double[] getDataArray()
    {
        return this.data;
    }

    @Override
    public DenseDoubleDataSi copy()
    {
        return new DenseDoubleDataSi(this.data.clone(), rows(), cols());
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public int cardinality()
    {
        int result = 0;
        for (int i = 0; i < this.data.length; i++)
            result += this.data[i] == 0.0 ? 0 : 1;
        return result;
    }

    @Override
    public DenseDoubleDataSi instantiateNew(final double[] newData)
    {
        Throw.when(newData.length != rows() * cols(), IllegalArgumentException.class,
                "Data object length != rows * cols, %d != %d * %d", newData.length, rows(), cols());
        return new DenseDoubleDataSi(newData, rows(), cols());
    }

    @Override
    public DenseDoubleDataSi instantiateNew(final double[] newData, final int newRows, final int newCols)
    {
        Throw.when(newData.length != newRows * newCols, IllegalArgumentException.class,
                "Data object length != rows * cols, %d != %d * %d", newData.length, newRows, newCols);
        return new DenseDoubleDataSi(newData, newRows, newCols);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(this.data);
        result = prime * result + Objects.hash(this.cols, this.rows);
        return result;
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
        {
            if (obj instanceof DataGridSi dg)
                return this.cols == dg.cols()  && this.rows == dg.rows() && Arrays.equals(this.data, dg.getDataArray());
            return false;
        }
        DenseDoubleDataSi other = (DenseDoubleDataSi) obj;
        return this.cols == other.cols && this.rows == other.rows && Arrays.equals(this.data, other.data);
    }

}
