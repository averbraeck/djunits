package org.djunits.vecmat.storage;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djutils.exceptions.Throw;

/**
 * SparseDoubleData implements a sparse data grid for N x M matrices or N x 1 or 1 x N vectors with double values. The sparse
 * grid is implemented with an index array that indicates the position of the data values in the dense array. Any index that is
 * missing indicates a data value of 0.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class SparseDoubleData implements DataGrid<SparseDoubleData>
{
    /** The sparse data stored in row-major format, where only non-zero values are stored. */
    private double[] sparseData;

    /** The index array, giving the position in the dense data array of the values in data. */
    private int[] indexes;

    /** The number of rows. */
    private final int rows;

    /** the number of columns. */
    private final int cols;

    /**
     * Instantiate a data object with one array in row-major format. Note that NO safe copy of the data is stored.
     * @param sparseData the sparse data in row-major format
     * @param indexes the indexes with the data coordinates
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     */
    protected SparseDoubleData(final double[] sparseData, final int[] indexes, final int rows, final int cols)
    {
        Throw.whenNull(sparseData, "denseData");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        this.rows = rows;
        this.cols = cols;
        this.sparseData = sparseData;
        this.indexes = indexes;
    }

    /**
     * Instantiate a data object with one array in row-major format. Note that NO safe copy of the data is stored.
     * @param denseData the dense data in row-major format
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     */
    public SparseDoubleData(final double[] denseData, final int rows, final int cols)
    {
        Throw.whenNull(denseData, "denseData");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        this.rows = rows;
        this.cols = cols;
        storeSparse(denseData);
    }

    /**
     * Instantiate a data object with a dense double[rows][cols]. A sparse, safe copy of the data is stored.
     * @param denseData the data in row-major format as a double[][]
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public SparseDoubleData(final double[][] denseData)
    {
        Throw.whenNull(denseData, "denseData");
        Throw.when(denseData.length == 0, IllegalArgumentException.class, "Number of rows in the data matrix = 0");
        this.rows = denseData.length;
        this.cols = denseData[0].length;
        for (int r = 1; r < this.rows; r++)
            Throw.when(denseData[r].length != this.cols, IllegalArgumentException.class,
                    "Number of columns in row %d (%d)is not equal to number of columns in row 0 (%d)", r, denseData[r],
                    this.cols);
        storeSparse(denseData);
    }

    /**
     * Instantiate a data object with one array in row-major format. Note that a safe copy of the data is stored.
     * @param sparseData the sparse data in row-major format
     * @param indexes the indexes with the data coordinates
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> SparseDoubleData(final Q[] sparseData, final int[] indexes,
            final int rows, final int cols)
    {
        Throw.whenNull(sparseData, "denseData");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        this.rows = rows;
        this.cols = cols;
        this.sparseData = new double[sparseData.length];
        for (int i = 0; i < sparseData.length; i++)
            this.sparseData[i] = sparseData[i].si();
        this.indexes = indexes.clone();
    }

    /**
     * Instantiate a data object with one array in row-major format. Note that a safe copy of the data is stored.
     * @param denseData the dense data in row-major format
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> SparseDoubleData(final Q[] denseData, final int rows,
            final int cols)
    {
        Throw.whenNull(denseData, "denseData");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        this.rows = rows;
        this.cols = cols;
        storeSparse(denseData);
    }

    /**
     * Instantiate a data object with a dense double[rows][cols]. A sparse, safe copy of the data is stored.
     * @param denseData the data in row-major format as a double[][]
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> SparseDoubleData(final Q[][] denseData)
    {
        Throw.whenNull(denseData, "denseData");
        Throw.when(denseData.length == 0, IllegalArgumentException.class, "Number of rows in the data matrix = 0");
        this.rows = denseData.length;
        this.cols = denseData[0].length;
        for (int r = 1; r < this.rows; r++)
            Throw.when(denseData[r].length != this.cols, IllegalArgumentException.class,
                    "Number of columns in row %d (%d)is not equal to number of columns in row 0 (%d)", r, denseData[r],
                    this.cols);
        storeSparse(denseData);
    }

    /**
     * Instantiate a data object with an indexed collection of values. Note that a safe copy of the data is stored.
     * @param indexedData the sparse data in an indexed format
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IndexOutOfBoundsException when a row or column index of an element is out of bounds
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> SparseDoubleData(
            final Collection<DoubleSparseValue<Q, U>> indexedData, final int rows, final int cols)
    {
        Throw.whenNull(indexedData, "indexedData");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        this.rows = rows;
        this.cols = cols;
        this.sparseData = new double[indexedData.size()];
        this.indexes = new int[indexedData.size()];
        int index = 0;
        for (var value : indexedData)
        {
            Throw.when(value.getRow() < 0 || value.getRow() >= rows, IndexOutOfBoundsException.class,
                    "Row index for indexed value %s out of bounds", value.toString());
            Throw.when(value.getColumn() < 0 || value.getColumn() >= rows, IndexOutOfBoundsException.class,
                    "Column index for indexed value %s out of bounds", value.toString());
            this.sparseData[index] = value.si();
            this.indexes[index++] = value.getRow() * this.cols + value.getColumn();
        }
    }

    /**
     * Store sparse data[] and indexes[].
     * @param denseData the dense data in row-major format
     */
    @SuppressWarnings("checkstyle:needbraces")
    public void storeSparse(final double[] denseData)
    {
        int nonzero = 0;
        for (int i = 0; i < denseData.length; i++)
            if (denseData[i] != 0.0)
                nonzero++;
        this.sparseData = new double[nonzero];
        this.indexes = new int[nonzero];
        int index = 0;
        for (int i = 0; i < denseData.length; i++)
            if (denseData[i] != 0.0)
            {
                this.sparseData[index] = denseData[i];
                this.indexes[index] = i;
                index++;
            }
    }

    /**
     * Store sparse data[] and indexes[].
     * @param denseData the dense data in row-major format
     */
    @SuppressWarnings("checkstyle:needbraces")
    public void storeSparse(final double[][] denseData)
    {
        int nonzero = 0;
        for (int i = 0; i < denseData.length; i++)
            for (int j = 0; j < denseData[i].length; j++)
                if (denseData[i][j] != 0.0)
                    nonzero++;
        this.sparseData = new double[nonzero];
        this.indexes = new int[nonzero];
        int index = 0;
        for (int i = 0; i < denseData.length; i++)
            for (int j = 0; j < denseData[i].length; j++)
                if (denseData[i][j] != 0.0)
                {
                    this.sparseData[index] = denseData[i][j];
                    this.indexes[index] = i * this.cols + j;
                    index++;
                }
    }

    /**
     * Store sparse data[] and indexes[].
     * @param denseData the dense data in row-major format
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> void storeSparse(final Q[] denseData)
    {
        int nonzero = 0;
        for (int i = 0; i < denseData.length; i++)
            if (denseData[i].ne0())
                nonzero++;
        this.sparseData = new double[nonzero];
        this.indexes = new int[nonzero];
        int index = 0;
        for (int i = 0; i < denseData.length; i++)
            if (denseData[i].ne0())
            {
                this.sparseData[index] = denseData[i].si();
                this.indexes[index] = i;
                index++;
            }
    }

    /**
     * Store sparse data[] and indexes[].
     * @param denseData the dense data in row-major format
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> void storeSparse(final Q[][] denseData)
    {
        int nonzero = 0;
        for (int i = 0; i < denseData.length; i++)
            for (int j = 0; j < denseData[i].length; j++)
                if (denseData[i][j].ne0())
                    nonzero++;
        this.sparseData = new double[nonzero];
        this.indexes = new int[nonzero];
        int index = 0;
        for (int i = 0; i < denseData.length; i++)
            for (int j = 0; j < denseData[i].length; j++)
                if (denseData[i][j].ne0())
                {
                    this.sparseData[index] = denseData[i][j].si();
                    this.indexes[index] = i * this.cols + j;
                    index++;
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
        int index = row * this.cols + col; // zero-based
        final int pos = Arrays.binarySearch(this.indexes, index);
        return (pos >= 0) ? this.sparseData[pos] : 0.0;
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public double[] getDataArray()
    {
        double[] denseData = new double[rows() * cols()];
        for (int i = 0; i < this.sparseData.length; i++)
            denseData[this.indexes[i]] = this.sparseData[i];
        return denseData;
    }

    @Override
    public SparseDoubleData copy()
    {
        return new SparseDoubleData(this.sparseData.clone(), this.indexes.clone(), rows(), cols());
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public int cardinality()
    {
        int result = 0;
        for (int i = 0; i < this.sparseData.length; i++)
            result += this.sparseData[i] == 0.0 ? 0 : 1;
        return result;
    }

    @Override
    public SparseDoubleData instantiate(final double[] denseData)
    {
        Throw.when(denseData.length != rows() * cols(), IllegalArgumentException.class,
                "Data object length != rows * cols, %d != %d * %d", denseData.length, rows(), cols());
        return new SparseDoubleData(denseData, rows(), cols());
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(this.sparseData);
        result = prime * result + Arrays.hashCode(this.indexes);
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
            return false;
        SparseDoubleData other = (SparseDoubleData) obj;
        return this.cols == other.cols && Arrays.equals(this.sparseData, other.sparseData)
                && Arrays.equals(this.indexes, other.indexes) && this.rows == other.rows;
    }

}
