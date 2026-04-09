package org.djunits.vecmat.storage;

import java.util.Arrays;
import java.util.Objects;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djutils.exceptions.Throw;

/**
 * DenseDoubleData implements a dense data grid for N x M matrices or N x 1 or 1 x M vectors with double values. DenseDoubleData
 * always stores a safe copy of the data when using the of() or ofSi() methods.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class DenseDoubleDataSi implements DataGridSi<DenseDoubleDataSi>
{
    /** The data stored in row-major format. */
    private final double[] dataSi;

    /** The number of rows. */
    private final int rows;

    /** the number of columns. */
    private final int cols;

    /**
     * Instantiate a data object with one array in row-major format. NO safe copy of the data is stored. The constructor is very
     * useful to store data after a calculation that already made a new safe copy.
     * @param dataSi the data with SI-values in row-major format
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     */
    public DenseDoubleDataSi(final double[] dataSi, final int rows, final int cols)
    {
        Throw.whenNull(dataSi, "dataSi");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        Throw.when(dataSi.length != rows * cols, IllegalArgumentException.class,
                "Data object length != rows * cols, %d != %d * %d", dataSi.length, rows, cols);
        this.dataSi = dataSi;
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Instantiate a data object with one array in row-major format. A safe copy of the data is stored.
     * @param dataSi the data with SI-values in row-major format
     * @param rows the number of rows
     * @param cols the number of columns
     * @return a dense double data object with SI values for vectors, matrices and tables
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     */
    public static DenseDoubleDataSi ofSi(final double[] dataSi, final int rows, final int cols)
    {
        return new DenseDoubleDataSi(dataSi.clone(), rows, cols);
    }

    /**
     * Instantiate a data object based on a row-major double[] array in a given unit. A safe copy of the data is stored.
     * @param dataInUnit the data in row-major format, expressed in the given unit
     * @param rows the number of rows
     * @param cols the number of columns
     * @param unit the unit of the data
     * @return a dense double data object with SI values for vectors, matrices and tables
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> DenseDoubleDataSi of(final double[] dataInUnit, final int rows, final int cols,
            final Unit<?, Q> unit)
    {
        Throw.whenNull(dataInUnit, "dataInUnit");
        Throw.whenNull(unit, "unit");
        double[] dataSi = new double[dataInUnit.length];
        for (int i = 0; i < dataInUnit.length; i++)
        {
            dataSi[i] = unit.toBaseValue(dataInUnit[i]);
        }
        return new DenseDoubleDataSi(dataSi, rows, cols);
    }

    /**
     * Instantiate a data object based on a row-major Q[] array. A safe copy of the data is stored.
     * @param data the quantity data in row-major format
     * @param rows the number of rows
     * @param cols the number of columns
     * @return a dense double data object with SI values for vectors, matrices and tables
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> DenseDoubleDataSi of(final Q[] data, final int rows, final int cols)
    {
        Throw.whenNull(data, "data");
        Throw.when(data.length != rows * cols, IllegalArgumentException.class, "Q[] length != rows * cols");
        double[] dataSi = new double[data.length];
        for (int i = 0; i < data.length; i++)
        {
            Throw.whenNull(data[i], "data[%d] = null", i);
            dataSi[i] = data[i].si();
        }
        return new DenseDoubleDataSi(dataSi, rows, cols);
    }

    /**
     * Instantiate a data object with a double[rows][cols]. A safe copy of the data is stored.
     * @param gridSi the data as a double[][] array in row-major format, with SI-values
     * @return a dense double data object with SI values for vectors, matrices and tables
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    public static DenseDoubleDataSi ofSi(final double[][] gridSi)
    {
        Throw.whenNull(gridSi, "gridSi");
        Throw.when(gridSi.length == 0, IllegalArgumentException.class, "Number of rows in the data matrix = 0");
        int rows = gridSi.length;
        Throw.whenNull(gridSi[0], "gridSi[0] = null");
        int cols = gridSi[0].length;
        double[] dataSi = new double[rows * cols];
        for (int r = 0; r < rows; r++)
        {
            Throw.whenNull(gridSi[r], "gridSi[%d] = null", r);
            Throw.when(gridSi[r].length != cols, IllegalArgumentException.class,
                    "Number of columns in row %d (%d) is not equal to number of columns in row 0 (%d)", r, gridSi[r].length,
                    cols);
            for (int c = 0; c < cols; c++)
            {
                dataSi[r * cols + c] = gridSi[r][c];
            }
        }
        return new DenseDoubleDataSi(dataSi, rows, cols);
    }

    /**
     * Instantiate a data object based on a row x column double[][] array in a given unit. A safe copy of the data is stored.
     * @param gridInUnit the data as a double[][] array in row-major format, expressed in the given unit
     * @param unit the unit of the data
     * @return a dense double data object with SI values for vectors, matrices and tables
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> DenseDoubleDataSi of(final double[][] gridInUnit, final Unit<?, Q> unit)
    {
        Throw.whenNull(gridInUnit, "gridInUnit");
        Throw.whenNull(unit, "unit");
        Throw.when(gridInUnit.length == 0, IllegalArgumentException.class, "Number of rows in the data matrix = 0");
        int rows = gridInUnit.length;
        Throw.whenNull(gridInUnit[0], "gridInUnit[0] = null");
        int cols = gridInUnit[0].length;
        double[] dataSi = new double[rows * cols];
        for (int r = 0; r < rows; r++)
        {
            Throw.whenNull(gridInUnit[r], "gridInUnit[%d] = null", r);
            Throw.when(gridInUnit[r].length != cols, IllegalArgumentException.class,
                    "Number of columns in row %d (%d) is not equal to number of columns in row 0 (%d)", r, gridInUnit[r].length,
                    cols);
            for (int c = 0; c < cols; c++)
            {
                dataSi[r * cols + c] = unit.toBaseValue(gridInUnit[r][c]);
            }
        }
        return new DenseDoubleDataSi(dataSi, rows, cols);
    }

    /**
     * Instantiate a data object with a Q[rows][cols]. A safe copy of the data is stored.
     * @param grid the quantities as a [][] array in row-major format
     * @return a dense double data object with SI values for vectors, matrices and tables
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> DenseDoubleDataSi of(final Q[][] grid)
    {
        Throw.whenNull(grid, "grid");
        Throw.when(grid.length == 0, IllegalArgumentException.class, "Number of rows in the data matrix = 0");
        int rows = grid.length;
        Throw.whenNull(grid[0], "grid[0] = null");
        int cols = grid[0].length;
        double[] dataSi = new double[rows * cols];
        for (int r = 0; r < rows; r++)
        {
            Throw.whenNull(grid[r], "grid[%d] = null", r);
            Throw.when(grid[r].length != cols, IllegalArgumentException.class,
                    "Number of columns in row %d (%d) is not equal to number of columns in row 0 (%d)", r, grid[r].length,
                    cols);
            for (int c = 0; c < cols; c++)
            {
                Throw.whenNull(grid[r][c], "grid[%d][%d] = null", r, c);
                dataSi[r * cols + c] = grid[r][c].si();
            }
        }
        return new DenseDoubleDataSi(dataSi, rows, cols);
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
    public boolean isDense()
    {
        return true;
    }

    @Override
    public boolean isDouble()
    {
        return true;
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
        return this.dataSi[row * this.cols + col];
    }

    @Override
    public double[] unsafeSiArray()
    {
        return this.dataSi;
    }

    @Override
    public double[] getSiArray()
    {
        return this.dataSi.clone();
    }

    @Override
    public DenseDoubleDataSi copy()
    {
        return new DenseDoubleDataSi(this.dataSi.clone(), rows(), cols());
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public int nonZeroCount()
    {
        int result = 0;
        for (int i = 0; i < this.dataSi.length; i++)
            result += this.dataSi[i] == 0.0 ? 0 : 1;
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
        result = prime * result + Arrays.hashCode(this.dataSi);
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
                return this.cols == dg.cols() && this.rows == dg.rows() && Arrays.equals(this.dataSi, dg.unsafeSiArray());
            return false;
        }
        DenseDoubleDataSi other = (DenseDoubleDataSi) obj;
        return this.cols == other.cols && this.rows == other.rows && Arrays.equals(this.dataSi, other.dataSi);
    }

}
