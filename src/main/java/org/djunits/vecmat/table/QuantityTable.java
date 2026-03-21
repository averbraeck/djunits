package org.djunits.vecmat.table;

import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.d1.Matrix1x1;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.def.VectorMatrix;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djutils.exceptions.Throw;

/**
 * QuantityTable is a two-dimensonal table with quantities. The QuantityTable allows for Hadamard (element-wise) operations, but
 * not for vector/matrix operations. A QuantityTable can be transformed to a MatrixNxM or vice versa.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public class QuantityTable<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>>
        extends VectorMatrix<Q, U, QuantityTable<Q, U>, QuantityTable<SIQuantity, SIUnit>, QuantityTable<?, ?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The data of the table, in SI unit. */
    private final DataGridSi<?> dataSi;

    /**
     * Create a new NxM QuantityTable with a unit, based on a DataGrid storage object. This constructor assumes dataSi stores SI
     * values. Note: NO safe copy is made.
     * @param dataSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     */
    public QuantityTable(final DataGridSi<?> dataSi, final U displayUnit)
    {
        super(displayUnit);
        Throw.whenNull(dataSi, "dataSi");
        this.dataSi = dataSi;
    }

    /**
     * Create a new NxM QuantityTable with a unit, based on a 1-dimensional double array.
     * @param valueArrayInUnit the matrix values {a11, a12, ..., a1M, aN2, ..., aNM} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @param rows the number of rows in the valueArray
     * @param cols the number of columns in the valueArray
     * @return a new NxM QuantityTable with a unit
     * @throws IllegalArgumentException when rows or cols is not positive, or when the number of entries in valueArray is not
     *             equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> QuantityTable<Q, U> of(
            final double[] valueArrayInUnit, final int rows, final int cols, final U displayUnit)
    {
        Throw.whenNull(valueArrayInUnit, "valueArrayInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(rows <= 0, IllegalArgumentException.class, "rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "cols <= 0");
        Throw.when(rows * cols != valueArrayInUnit.length, IllegalArgumentException.class,
                "valueArrayInUnit does not contain the correct number of entries (%d x %d != %d)", rows, cols,
                valueArrayInUnit.length);
        double[] aSi = new double[rows * cols];
        for (int i = 0; i < valueArrayInUnit.length; i++)
            aSi[i] = displayUnit.toBaseValue(valueArrayInUnit[i]);
        return new QuantityTable<Q, U>(new DenseDoubleDataSi(aSi, rows, cols), displayUnit);
    }

    /**
     * Create a new NxM QuantityTable with a unit, based on a 2-dimensional double grid.
     * @param valueGridInUnit the matrix values {{a11, a12, a1M}, ..., {aN1, aN2, aNM}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new NxM QuantityTable with a unit
     * @throws IllegalArgumentException when valueGrid has 0 rows, or when the number of columns for one of the rows is not
     *             equal to the number of columns in another row
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>,
            U extends UnitInterface<U, Q>> QuantityTable<Q, U> of(final double[][] valueGridInUnit, final U displayUnit)
    {
        Throw.whenNull(valueGridInUnit, "valueGridInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        int rows = valueGridInUnit.length;
        Throw.when(rows == 0, IllegalArgumentException.class, "valueGridInUnit has 0 rows");
        int cols = valueGridInUnit[0].length;
        Throw.when(cols == 0, IllegalArgumentException.class, "row 0 in valueGridInUnit has 0 columns");
        double[] aSi = new double[rows * cols];
        for (int r = 0; r < rows; r++)
        {
            Throw.when(valueGridInUnit[r].length != cols, IllegalArgumentException.class,
                    "valueGridInUnit is not a NxM array; row %d has a length of %d, not %d", r, valueGridInUnit[r].length,
                    cols);
            for (int c = 0; c < cols; c++)
                aSi[cols * r + c] = displayUnit.toBaseValue(valueGridInUnit[r][c]);
        }
        return new QuantityTable<Q, U>(new DenseDoubleDataSi(aSi, rows, cols), displayUnit);
    }

    /**
     * Create a new NxM QuantityTable with a unit, based on a 2-dimensional quantity grid.
     * @param quantityGrid the matrix values {{a11, a12, ..., a1M}, {aN2, ..., aNM}}, each with their own unit
     * @param displayUnit the display unit to use for the resulting matrix
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new NxM QuantityTable with a unit
     * @throws IllegalArgumentException when rows or cols is not positive, or when the number of entries in quantityGrid is not
     *             equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> QuantityTable<Q, U> of(final Q[][] quantityGrid,
            final U displayUnit)
    {
        return new QuantityTable<Q, U>(new DenseDoubleDataSi(quantityGrid), displayUnit);
    }

    @Override
    public QuantityTable<Q, U> instantiateSi(final double[] siNew)
    {
        return new QuantityTable<Q, U>(this.dataSi.instantiateNew(siNew), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public QuantityTable<SIQuantity, SIUnit> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new QuantityTable<SIQuantity, SIUnit>(this.dataSi.instantiateNew(siNew), siUnit);
    }

    /**
     * Return the internal datagrid object, so we can retrieve data from it.
     * @return the internal datagrid object
     */
    public DataGridSi<?> getDataGrid()
    {
        return this.dataSi;
    }

    @Override
    public double[] si()
    {
        return this.dataSi.getDataArray();
    }

    @Override
    public double si(final int row, final int col) throws IndexOutOfBoundsException
    {
        checkRow(row);
        checkCol(col);
        return this.dataSi.get(row, col);
    }

    @Override
    public VectorN.Row<Q, U> getRowVector(final int row)
    {
        return VectorN.Row.ofSi(getRowSi(row), getDisplayUnit());
    }

    @Override
    public VectorN.Row<Q, U> mgetRowVector(final int mRow)
    {
        return VectorN.Row.ofSi(mgetRowSi(mRow), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q, U> getColumnVector(final int col)
    {
        return VectorN.Col.ofSi(getColumnSi(col), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q, U> mgetColumnVector(final int mCol)
    {
        return VectorN.Col.ofSi(mgetColumnSi(mCol), getDisplayUnit());
    }

    @Override
    public double[] getRowSi(final int row)
    {
        checkRow(row);
        return this.dataSi.getRowArray(row);
    }

    @Override
    public double[] getColumnSi(final int col)
    {
        checkCol(col);
        return this.dataSi.getColArray(col);
    }

    @Override
    public int rows()
    {
        return this.dataSi.rows();
    }

    @Override
    public int cols()
    {
        return this.dataSi.cols();
    }

    @Override
    public int nonZeroCount()
    {
        return this.dataSi.nonZeroCount();
    }

    // --------------------------------------- AS() FUNCTIONS ---------------------------------

    @Override
    public int hashCode()
    {
        return Objects.hash(this.dataSi);
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
        QuantityTable<?, ?> other = (QuantityTable<?, ?>) obj;
        return Objects.equals(this.dataSi, other.dataSi);
    }

    /**
     * Return the QuantityTable 'as' a QuantityTable with a known quantity, using a unit to express the result in. Throw a
     * Runtime exception when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the quantity table to
     * @return a quantity table typed in the target quantity table class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> QuantityTable<TQ, TU> as(final TU targetUnit)
            throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "QuantityTable.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new QuantityTable<TQ, TU>(this.dataSi.instantiateNew(si()), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
    }

    /**
     * Convert this QuantityTable to a {@link Matrix1x1}. The shape must be 1 x 1.
     * @return a {@code Matrix1x1} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 1 x 1
     */
    public Matrix1x1<Q, U> asMatrix1x1()
    {
        Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class,
                "asMatrix1x1() called, but matrix is no 1x1 but %dx%d", rows(), cols());
        return Matrix1x1.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to a {@link Matrix2x2}. The shape must be 2 x 2.
     * @return a {@code Matrix2x2} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 2 x 2
     */
    public Matrix2x2<Q, U> asMatrix2x2()
    {
        Throw.when(rows() != 2 || cols() != 2, IllegalStateException.class,
                "asMatrix2x2() called, but matrix is no 2x2 but %dx%d", rows(), cols());
        return Matrix2x2.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to a {@link Matrix3x3}. The shape must be 3 x 3.
     * @return a {@code Matrix3x3} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 3 x 3
     */
    public Matrix3x3<Q, U> asMatrix3x3()
    {
        Throw.when(rows() != 3 || cols() != 3, IllegalStateException.class,
                "asMatrix3x3() called, but matrix is no 3x3 but %dx%d", rows(), cols());
        return Matrix3x3.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to a {@link MatrixNxN}. The shape must be square.
     * @return a {@code MatrixNxN} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not square
     */
    public MatrixNxN<Q, U> asMatrixNxN()
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "asMatrixNxN() called, but matrix is no square but %dx%d",
                rows(), cols());
        return new MatrixNxN<Q, U>(new DenseDoubleDataSi(si(), rows(), cols()), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to a 1-element column vector. Shape must be 1 x 1.
     * @return a {@code Vector1} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 1
     */
    public Vector1<Q, U> asVector1()
    {
        Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class, "Matrix is not 1x1");
        final double[] data = si();
        return new Vector1<Q, U>(data[0], getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to a 2-element column vector. Shape must be 2 x 1.
     * @return a {@code Vector2.Col} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 2 x 1
     */
    public Vector2.Col<Q, U> asVector2Col()
    {
        Throw.when(rows() != 2 || cols() != 1, IllegalStateException.class, "Matrix is not 2x1");
        final double[] data = si();
        return new Vector2.Col<Q, U>(data[0], data[1], getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to a 3-element column vector. Shape must be 3 x 1.
     * @return a {@code Vector3.Col} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 3 x 1
     */
    public Vector3.Col<Q, U> asVector3Col()
    {
        Throw.when(rows() != 3 || cols() != 1, IllegalStateException.class, "Matrix is not 3x1");
        final double[] data = si();
        return new Vector3.Col<Q, U>(data[0], data[1], data[2], getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to an N-element column vector. Shape must be N x 1.
     * @return a {@code VectorN.Col} with identical SI data and display unit
     * @throws IllegalStateException if {@code cols() != 1}
     */
    public VectorN.Col<Q, U> asVectorNCol()
    {
        Throw.when(cols() != 1, IllegalStateException.class, "Matrix is not Nx1");
        return VectorN.Col.ofSi(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to a 2-element row vector. Shape must be 1 x 2.
     * @return a {@code Vector2.Row} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 2
     */
    public Vector2.Row<Q, U> asVector2Row()
    {
        Throw.when(rows() != 1 || cols() != 2, IllegalStateException.class, "Matrix is not 1x2");
        final double[] data = si();
        return new Vector2.Row<Q, U>(data[0], data[1], getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to a 3-element row vector. Shape must be 1 x 3.
     * @return a {@code Vector3.Row} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 3
     */
    public Vector3.Row<Q, U> asVector3Row()
    {
        Throw.when(rows() != 1 || cols() != 3, IllegalStateException.class, "Matrix is not 1x3");
        final double[] data = si();
        return new Vector3.Row<Q, U>(data[0], data[1], data[2], getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this QuantityTable to an N-element row vector. Shape must be 1 x N.
     * @return a {@code VectorN.Row} with identical SI data and display unit
     * @throws IllegalStateException if {@code rows() != 1}
     */
    public VectorN.Row<Q, U> asVectorNRow()
    {
        Throw.when(rows() != 1, IllegalStateException.class, "Matrix is not 1xN");
        return VectorN.Row.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

}
