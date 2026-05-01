package org.djunits.vecmat.table;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsTable;
import org.djunits.vecmat.dn.AbsVectorN;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djutils.exceptions.Throw;

/**
 * AbsQuantityTable implements a table containing absolute quantities with a reference point. The table is immutable, except for
 * the display unit, which can be changed.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsQuantityTable<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsTable<A, Q, AbsQuantityTable<A, Q>, QuantityTable<Q>, AbsQuantityTable<A, Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsQuantityTable with a display unit and a reference point.
     * @param relativeTable the table values {a_ij} expressed in the displayUnit
     * @param reference the reference point for the absolute values
     */
    public AbsQuantityTable(final QuantityTable<Q> relativeTable, final Reference<?, A, Q> reference)
    {
        super(relativeTable, reference);
    }

    @Override
    public AbsQuantityTable<A, Q> instantiate(final QuantityTable<Q> relativeTable, final Reference<?, A, Q> reference)
    {
        return new AbsQuantityTable<>(relativeTable, reference);
    }

    @Override
    public AbsVectorN.Row<A, Q> getRowVector(final int row)
    {
        return new AbsVectorN.Row<>(getRelativeVecMat().getRowVector(row), getReference());
    }

    @Override
    public AbsVectorN.Row<A, Q> mgetRowVector(final int mRow)
    {
        return new AbsVectorN.Row<>(getRelativeVecMat().mgetRowVector(mRow), getReference());
    }

    @Override
    public AbsVectorN.Col<A, Q> getColumnVector(final int col)
    {
        return new AbsVectorN.Col<>(getRelativeVecMat().getColumnVector(col), getReference());
    }

    @Override
    public AbsVectorN.Col<A, Q> mgetColumnVector(final int mCol)
    {
        return new AbsVectorN.Col<>(getRelativeVecMat().mgetColumnVector(mCol), getReference());
    }

    @Override
    public AbsQuantityTable<A, Q> transpose()
    {
        return instantiate(getRelativeVecMat().transpose(), getReference()).setDisplayUnit(getDisplayUnit());
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create a new AbsQuantityTable with a unit, based on a row-major array with values in the given unit.
     * @param dataInUnit the table values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} expressed in the unit
     * @param rows the number of rows
     * @param cols the number of columns
     * @param unit the unit of the data, also used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new AbsQuantityTable with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> of(final double[] dataInUnit, final int rows, final int cols,
                    final Unit<?, Q> unit, final R reference)
    {
        return new AbsQuantityTable<A, Q>(QuantityTable.of(dataInUnit, rows, cols, unit), reference);
    }

    /**
     * Create a AbsQuantityTable without needing generics, based on a row-major array with SI-values.
     * @param dataSi the table values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} as an array using SI units
     * @param rows the number of rows
     * @param cols the number of columns
     * @param displayUnit the display unit to use
     * @param reference the reference point for the absolute quantities
     * @return a new AbsQuantityTable with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> ofSi(final double[] dataSi, final int rows, final int cols,
                    final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsQuantityTable<A, Q>(QuantityTable.ofSi(dataSi, rows, cols, displayUnit), reference);
    }

    /**
     * Create a AbsQuantityTable without needing generics, based on a row-major array of quantities. The unit is taken from the
     * first quantity in the array.
     * @param data the table values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} expressed as an array of quantities
     * @param rows the number of rows
     * @param cols the number of columns
     * @param reference the reference point for the absolute quantities
     * @return a new AbsQuantityTable with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols, or when the number of rows
     *             or columns is not positive
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> of(final Q[] data, final int rows, final int cols,
                    final R reference)
    {
        return new AbsQuantityTable<A, Q>(QuantityTable.of(data, rows, cols), reference);
    }

    /**
     * Create a AbsQuantityTable without needing generics, based on a row-major array of absolute quantities. The unit is
     * taken from the first quantity in the grid. The reference points have to be all the same.
     * @param absData the table values {a11, a12, ..., A1M, ..., aN1, aN2, ..., aNM} expressed as an array of
     *            absolute quantities
     * @param rows the number of rows
     * @param cols the number of columns
     * @return a new AbsQuantityTable with a display unit and reference point
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> of(final A[] absData, final int rows, final int cols)
    {
        Throw.whenNull(absData, "absData");
        var ddd = DenseDoubleDataSi.of(absData, rows, cols); // guarantees that absGrid[0][0] exists
        return new AbsQuantityTable<>(new QuantityTable<Q>(ddd, absData[0].getDisplayUnit()), absData[0].getReference());
    }

    /**
     * Create a new AbsQuantityTable with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the table values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new AbsQuantityTable with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> ofSi(final double[][] gridSi, final Unit<?, Q> displayUnit,
                    final R reference)
    {
        Throw.whenNull(displayUnit, "displayUnit");
        return new AbsQuantityTable<>(QuantityTable.ofSi(gridSi, displayUnit), reference);
    }

    /**
     * Create a new AbsQuantityTable with a unit, based on a 2-dimensional grid with values in the given unit.
     * @param gridInUnit the table values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed in the unit
     * @param unit the unit of the values, also used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new AbsQuantityTable with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> of(final double[][] gridInUnit, final Unit<?, Q> unit,
                    final R reference)
    {
        return new AbsQuantityTable<>(QuantityTable.of(gridInUnit, unit), reference);
    }

    /**
     * Create a AbsQuantityTable without needing generics, based on a 2-dimensional grid of quantities. The unit is taken from
     * the first quantity in the grid.
     * @param grid the table values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed as a 2-dimensional array of
     *            quantities
     * @param reference the reference point for the absolute quantities
     * @return a new AbsQuantityTable with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> of(final Q[][] grid, final R reference)
    {
        return new AbsQuantityTable<>(QuantityTable.of(grid), reference);
    }

    /**
     * Create a AbsQuantityTable without needing generics, based on a 2-dimensional grid of absolute quantities. The unit is
     * taken from the first quantity in the grid. The reference points have to be all the same.
     * @param absGrid the table values {{a11, a12, ..., A1M}, ..., {aN1, aN2, ..., aNM}} expressed as a 2-dimensional array of
     *            absolute quantities
     * @return a new AbsQuantityTable with a display unit and reference point
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> of(final A[][] absGrid)
    {
        Throw.whenNull(absGrid, "absGrid");
        var ddd = DenseDoubleDataSi.of(absGrid); // guarantees that absGrid[0][0] exists
        return new AbsQuantityTable<>(new QuantityTable<Q>(ddd, absGrid[0][0].getDisplayUnit()), absGrid[0][0].getReference());
    }

    /**
     * Create a AbsQuantityTable without needing generics, based on a relative table. The unit is taken from the first quantity
     * in the grid.
     * @param relativeTable the relative table with values relative to the reference point
     * @param reference the reference point for the absolute quantities
     * @return a new AbsQuantityTable with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not equal to rows*cols
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsQuantityTable<A, Q> of(final QuantityTable<Q> relativeTable, final R reference)
    {
        return new AbsQuantityTable<>(relativeTable, reference);
    }

}
