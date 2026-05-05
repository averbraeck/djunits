package org.djunits.vecmat.table;

import java.util.Objects;

import org.djunits.formatter.TableFormat;
import org.djunits.formatter.TableFormatter;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.def.Table;
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
 */
public class QuantityTable<Q extends Quantity<Q>>
        extends Table<Q, QuantityTable<Q>, QuantityTable<SIQuantity>, QuantityTable<?>, QuantityTable<Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The data of the table, in SI unit. */
    private final DataGridSi<?> dataGridSi;

    /**
     * Create a new NxM QuantityTable with a unit, based on a DataGrid storage object. This constructor assumes dataSi stores SI
     * values. Note: NO safe copy is made.
     * @param dataGridSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     */
    public QuantityTable(final DataGridSi<?> dataGridSi, final Unit<?, Q> displayUnit)
    {
        super(displayUnit);
        Throw.whenNull(dataGridSi, "dataGridSi");
        this.dataGridSi = dataGridSi;
    }

    @Override
    public QuantityTable<Q> instantiateSi(final double[] siNew)
    {
        return new QuantityTable<Q>(this.dataGridSi.instantiateNew(siNew), getDisplayUnit());
    }

    @Override
    public QuantityTable<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new QuantityTable<SIQuantity>(this.dataGridSi.instantiateNew(siNew), siUnit);
    }

    /**
     * Return the internal datagrid object, so we can retrieve data from it.
     * @return the internal datagrid object
     */
    public DataGridSi<?> getDataGrid()
    {
        return this.dataGridSi;
    }

    @Override
    public double[] getSiArray()
    {
        return this.dataGridSi.getSiArray();
    }

    @Override
    public double[] unsafeSiArray()
    {
        return this.dataGridSi.unsafeSiArray();
    }

    @Override
    public double si(final int row, final int col) throws IndexOutOfBoundsException
    {
        checkRow(row);
        checkCol(col);
        return this.dataGridSi.get(row, col);
    }

    @Override
    public VectorN.Row<Q> getRowVector(final int row)
    {
        return VectorN.Row.ofSi(getRowSi(row), getDisplayUnit());
    }

    @Override
    public VectorN.Row<Q> mgetRowVector(final int mRow)
    {
        return VectorN.Row.ofSi(mgetRowSi(mRow), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q> getColumnVector(final int col)
    {
        return VectorN.Col.ofSi(getColumnSi(col), getDisplayUnit());
    }

    @Override
    public VectorN.Col<Q> mgetColumnVector(final int mCol)
    {
        return VectorN.Col.ofSi(mgetColumnSi(mCol), getDisplayUnit());
    }

    @Override
    public double[] getRowSi(final int row)
    {
        checkRow(row);
        return this.dataGridSi.getRowArray(row);
    }

    @Override
    public double[] getColumnSi(final int col)
    {
        checkCol(col);
        return this.dataGridSi.getColArray(col);
    }

    @Override
    public int rows()
    {
        return this.dataGridSi.rows();
    }

    @Override
    public int cols()
    {
        return this.dataGridSi.cols();
    }

    @Override
    public int nonZeroCount()
    {
        return this.dataGridSi.nonZeroCount();
    }

    /**
     * Return the transposed quantity table. A transposed quantity table has the same unit as the original one.
     * @return the transposed quantity table
     */
    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public QuantityTable<Q> transpose()
    {
        double[] data = unsafeSiArray();
        double[] newSi = new double[data.length];
        int rows = rows();
        int cols = cols();
        final Unit<?, Q> displayUnit = getDisplayUnit();
        final Unit<?, Q> baseUnit = displayUnit.getBaseUnit();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                newSi[c * rows + r] = data[r * cols + c];
        return new QuantityTable<Q>(this.dataGridSi.instantiateNew(newSi, cols, rows), baseUnit).setDisplayUnit(displayUnit);
    }

    // --------------------------------------- EQUALS AND HASHCODE ---------------------------------

    @Override
    public int hashCode()
    {
        return Objects.hash(this.dataGridSi);
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
        QuantityTable<?> other = (QuantityTable<?>) obj;
        return Objects.equals(this.dataGridSi, other.dataGridSi);
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create a new QuantityTable with a unit, based on a row-major array with values in the given unit.
     * @param dataInUnit the table values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} expressed in the unit
     * @param rows the number of rows
     * @param cols the number of columns
     * @param unit the unit of the data, also used as the display unit
     * @param <Q> the quantity type
     * @return a new QuantityTable with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    public static <Q extends Quantity<Q>> QuantityTable<Q> of(final double[] dataInUnit, final int rows, final int cols,
            final Unit<?, Q> unit)
    {
        return new QuantityTable<Q>(DenseDoubleDataSi.of(dataInUnit, rows, cols, unit), unit);
    }

    /**
     * Create a QuantityTable without needing generics, based on a row-major array with SI-values.
     * @param dataSi the table values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} as an array using SI units
     * @param rows the number of rows
     * @param cols the number of columns
     * @param displayUnit the display unit to use
     * @return a new QuantityTable with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when dataSi does not contain a square number of values
     */
    public static <Q extends Quantity<Q>> QuantityTable<Q> ofSi(final double[] dataSi, final int rows, final int cols,
            final Unit<?, Q> displayUnit)
    {
        return new QuantityTable<Q>(DenseDoubleDataSi.ofSi(dataSi, rows, cols), displayUnit);
    }

    /**
     * Create a QuantityTable without needing generics, based on a row-major array of quantities. The unit is taken from the
     * first quantity in the array.
     * @param data the table values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} expressed as an array of quantities
     * @param rows the number of rows
     * @param cols the number of columns
     * @return a new QuantityTable with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when data does not contain a square number of quantities
     */
    public static <Q extends Quantity<Q>> QuantityTable<Q> of(final Q[] data, final int rows, final int cols)
    {
        Throw.whenNull(data, "data");
        Throw.when(data.length == 0, IllegalArgumentException.class, "data.length = 0");
        return new QuantityTable<Q>(DenseDoubleDataSi.of(data, rows, cols), data[0].getDisplayUnit());
    }

    /**
     * Create a new QuantityTable with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the table values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param <Q> the quantity type
     * @return a new QuantityTable with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> QuantityTable<Q> ofSi(final double[][] gridSi, final Unit<?, Q> displayUnit)
    {
        return new QuantityTable<>(DenseDoubleDataSi.ofSi(gridSi), displayUnit);
    }

    /**
     * Create a new QuantityTable with a unit, based on a 2-dimensional grid with values in the given unit.
     * @param gridInUnit the table values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed in the unit
     * @param unit the unit of the values, also used as the display unit
     * @param <Q> the quantity type
     * @return a new QuantityTable with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> QuantityTable<Q> of(final double[][] gridInUnit, final Unit<?, Q> unit)
    {
        return new QuantityTable<>(DenseDoubleDataSi.of(gridInUnit, unit), unit);
    }

    /**
     * Create a QuantityTable without needing generics, based on a 2-dimensional grid of quantities. The unit is taken from the
     * first quantity in the grid.
     * @param grid the table values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed as a 2-dimensional array of
     *            quantities
     * @return a new QuantityTable with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when dataInUnit does not contain a square number of quantities
     */
    public static <Q extends Quantity<Q>> QuantityTable<Q> of(final Q[][] grid)
    {
        Throw.whenNull(grid, "grid");
        Throw.when(grid.length == 0, IllegalArgumentException.class, "grid.length = 0");
        Throw.whenNull(grid[0], "grid[0] = null");
        Throw.when(grid[0].length == 0, IllegalArgumentException.class, "grid[0].length = 0");
        Throw.whenNull(grid[0][0], "grid[0][0] = null");
        return new QuantityTable<>(DenseDoubleDataSi.of(grid), grid[0][0].getDisplayUnit());
    }

    // ------------------------------------------------- AS() METHODS -------------------------------------------------

    /**
     * Return the QuantityTable 'as' a QuantityTable with a known quantity, using a unit to express the result in. Throw a
     * Runtime exception when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the quantity table to
     * @return a quantity table typed in the target quantity table class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     */
    public <TQ extends Quantity<TQ>> QuantityTable<TQ> as(final Unit<?, TQ> targetUnit) throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "QuantityTable.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new QuantityTable<TQ>(this.dataGridSi.instantiateNew(unsafeSiArray()), targetUnit);
    }

    // ----------------------------------------- STRING AND FORMATTING METHODS ----------------------------------------

    /**
     * Concise description of this quantity table.
     * @return a String with the quantity table, with the unit attached.
     */
    @Override
    public String format()
    {
        return format(TableFormat.defaults());
    }

    /**
     * String representation of this quantity table after applying the format.
     * @param format the format to apply for the quantity table
     * @return a String representation of this quantity table, formatted according to the given format
     */
    public String format(final TableFormat format)
    {
        return TableFormatter.format(this, format);
    }

    /**
     * String representation of this quantity table, expressed in the specified unit.
     * @param targetUnit the unit into which the values of the quantity table are converted for display
     * @return printable string with the quantity table's values expressed in the specified unit
     */
    @Override
    public String format(final Unit<?, Q> targetUnit)
    {
        return format(TableFormat.defaults().setDisplayUnit(targetUnit));
    }

}
