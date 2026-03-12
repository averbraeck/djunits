package org.djunits.vecmat.table;

import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.def.VectorMatrix;
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
        Throw.when(row < 1 || row > rows(), IndexOutOfBoundsException.class, "row %d out of bounds (1-%d)", row, rows());
        Throw.when(col < 1 || col > cols(), IndexOutOfBoundsException.class, "col %d out of bounds (1-%d)", col, cols());
        return this.dataSi.get(row - 1, col - 1);
    }

    @Override
    public VectorN.Row<Q, U> getRowVector(final int row)
    {
        return VectorN.Row.ofSi(getRowSi(row), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q, U> getColumnVector(final int col)
    {
        return VectorN.Col.ofSi(getColumnSi(col), getDisplayUnit());
    }

    @Override
    public double[] getRowSi(final int row)
    {
        Throw.when(row < 1 || row > rows(), IndexOutOfBoundsException.class, "row %d out of bounds (1-%d)", row, rows());
        return this.dataSi.getRowArray(row - 1);
    }

    @Override
    public double[] getColumnSi(final int col)
    {
        Throw.when(col < 1 || col > cols(), IndexOutOfBoundsException.class, "col %d out of bounds (1-%d)", col, cols());
        return this.dataSi.getColArray(col - 1);
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
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuantityTable<?, ?> other = (QuantityTable<?, ?>) obj;
        return Objects.equals(this.dataSi, other.dataSi);
    }

    // --------------------------------------- AS() FUNCTIONS ---------------------------------

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

}
