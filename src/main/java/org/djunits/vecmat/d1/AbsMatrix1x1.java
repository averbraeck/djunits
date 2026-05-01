package org.djunits.vecmat.d1;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsSquareMatrix;
import org.djutils.exceptions.Throw;

/**
 * AbsMatrix1x1 implements a matrix with 1x1 absolute quantities with a reference point. The matrix is immutable, except for the
 * display unit, which can be changed.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsMatrix1x1<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsSquareMatrix<A, Q, AbsMatrix1x1<A, Q>, Matrix1x1<Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsMatrix1x1 with a display unit and a reference point.
     * @param relativeMatrix the matrix values {a11} expressed in the displayUnit
     * @param reference the reference point for the absolute values
     */
    public AbsMatrix1x1(final Matrix1x1<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        super(relativeMatrix, reference);
    }

    @Override
    public AbsMatrix1x1<A, Q> instantiate(final Matrix1x1<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        return new AbsMatrix1x1<>(relativeMatrix, reference);
    }

    @Override
    public AbsVector1<A, Q> getRowVector(final int row)
    {
        return new AbsVector1<>(getRelativeVecMat().getRowVector(row), getReference());
    }

    @Override
    public AbsVector1<A, Q> mgetRowVector(final int mRow)
    {
        return new AbsVector1<>(getRelativeVecMat().mgetRowVector(mRow), getReference());
    }

    @Override
    public AbsVector1<A, Q> getColumnVector(final int col)
    {
        return new AbsVector1<>(getRelativeVecMat().getColumnVector(col), getReference());
    }

    @Override
    public AbsVector1<A, Q> mgetColumnVector(final int mCol)
    {
        return new AbsVector1<>(getRelativeVecMat().mgetColumnVector(mCol), getReference());
    }

    @Override
    public AbsVector1<A, Q> getDiagonalVector()
    {
        return new AbsVector1<>(getRelativeVecMat().getDiagonalVector(), getReference());
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param xInUnit the a11-value expressed in the display unit
     * @param displayUnit the display unit to use
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(
            final double xInUnit, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsMatrix1x1<>(Matrix1x1.of(xInUnit, displayUnit), reference);
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param x the a11-value expressed as a quantity
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(final Q x, final R reference)
    {
        return new AbsMatrix1x1<>(Matrix1x1.of(x), reference);
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param absX the a11-value expressed as an absolute quantity
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(final A absX)
    {
        Throw.whenNull(absX, "absX");
        return new AbsMatrix1x1<>(Matrix1x1.of(absX.getQuantity()), absX.getReference());
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param dataInUnit the a11-value expressed as an array in the display unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(
            final double[] dataInUnit, final Unit<?, Q> unit, final R reference)
    {
        return new AbsMatrix1x1<>(Matrix1x1.of(dataInUnit, unit), reference);
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param dataSi the a11-value expressed as an array in the SI units
     * @param displayUnit the display unit to use
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> ofSi(
            final double[] dataSi, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsMatrix1x1<>(Matrix1x1.ofSi(dataSi, displayUnit), reference);
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param data the matrix values {a11} expressed as an array of quantities
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(final Q[] data, final R reference)
    {
        return new AbsMatrix1x1<>(Matrix1x1.of(data), reference);
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param absData the {a11} value expressed as an array of absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(final A[] absData)
    {
        Throw.whenNull(absData, "absData");
        Throw.when(absData.length != 1, IllegalArgumentException.class, "absData.length != 1");
        return new AbsMatrix1x1<>(Matrix1x1.of(absData[0].getQuantity()), absData[0].getReference());
    }

    /**
     * Create an AbsMatrix1x1 with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the matrix values {{a11}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> ofSi(
            final double[][] gridSi, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsMatrix1x1<>(Matrix1x1.ofSi(gridSi, displayUnit), reference);
    }

    /**
     * Create an AbsMatrix1x1 with a unit, based on a 2-dimensional grid.
     * @param gridInUnit the matrix values {{a11}} expressed in the unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(
            final double[][] gridInUnit, final Unit<?, Q> unit, final R reference)
    {
        return new AbsMatrix1x1<>(Matrix1x1.of(gridInUnit, unit), reference);
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param grid the matrix values {{a11}} expressed as a grid of quantities
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(final Q[][] grid, final R reference)
    {
        return new AbsMatrix1x1<>(Matrix1x1.of(grid), reference);
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param absGrid the a11-value expressed as a grid {{a11}}
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(final A[][] absGrid)
    {
        Throw.whenNull(absGrid, "absGrid");
        Throw.when(absGrid.length != 1, IllegalArgumentException.class, "absGrid.length != 1");
        Throw.whenNull(absGrid[0], "absGrid[0]");
        Throw.when(absGrid[0].length != 1, IllegalArgumentException.class, "absGrid[0].length != 1");
        return new AbsMatrix1x1<>(Matrix1x1.of(absGrid[0][0].getQuantity()), absGrid[0][0].getReference());
    }

    /**
     * Create an AbsMatrix1x1 without needing generics.
     * @param relativeMatrix the relative matrix with values relative to the reference point
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix1x1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix1x1<A, Q> of(final Matrix1x1<Q> relativeMatrix, final R reference)
    {
        return new AbsMatrix1x1<>(relativeMatrix, reference);
    }

}
