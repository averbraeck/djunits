package org.djunits.vecmat.dn;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsSquareMatrix;

/**
 * AbsMatrixNxN implements a matrix with NxN absolute quantities with a reference point. The matrix is immutable, except for the
 * display unit, which can be changed.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsMatrixNxN<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsSquareMatrix<A, Q, AbsMatrixNxN<A, Q>, MatrixNxN<Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsMatrixNxN with a display unit and a reference point.
     * @param relativeMatrix the matrix values {a_ij} expressed in the displayUnit
     * @param reference the reference point for the absolute values
     */
    public AbsMatrixNxN(final MatrixNxN<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        super(relativeMatrix, reference);
    }

    @Override
    public AbsMatrixNxN<A, Q> instantiate(final MatrixNxN<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        return new AbsMatrixNxN<>(relativeMatrix, reference);
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
    public AbsVectorN.Col<A, Q> getDiagonalVector()
    {
        return new AbsVectorN.Col<>(getRelativeVecMat().getDiagonalVector(), getReference());
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create an AbsMatrixNxN without needing generics.
     * @param dataInUnit the matrix values {a11, a12, 13, ..., aN1, aN2, ..., aNN} expressed as an array in the display unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new AbsMatrixNxN with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not a square
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrixNxN<A, Q> of(
            final double[] dataInUnit, final Unit<?, Q> unit, final R reference)
    {
        return new AbsMatrixNxN<>(MatrixNxN.of(dataInUnit, unit), reference);
    }

    /**
     * Create an AbsMatrixNxN without needing generics.
     * @param dataSi the matrix values {a11, a12, 13, ..., aN1, aN2, ..., aNN} expressed as an array in the SI units
     * @param displayUnit the display unit to use
     * @param reference the reference point for the absolute quantities
     * @return a new AbsMatrixNxN with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not a square
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrixNxN<A, Q> ofSi(
            final double[] dataSi, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsMatrixNxN<>(MatrixNxN.of(dataSi, displayUnit), reference);
    }

    /**
     * Create an AbsMatrixNxN without needing generics.
     * @param data the matrix values {a11, a12, 13, ..., aN1, aN2, ..., aNN} expressed as an array of quantities
     * @param reference the reference point for the absolute quantities
     * @return a new AbsMatrixNxN with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the size of the data object is not a square
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrixNxN<A, Q> of(final Q[] data, final R reference)
    {
        return new AbsMatrixNxN<>(MatrixNxN.of(data), reference);
    }

    /**
     * Create an AbsMatrixNxN with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the matrix values {a11, a12, ..., a1N}, ..., {aN1, aN2, ..., aNN}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new AbsMatrixNxN with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the data object is not a square grid
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrixNxN<A, Q> ofSi(
            final double[][] gridSi, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsMatrixNxN<>(MatrixNxN.of(gridSi, displayUnit), reference);
    }

    /**
     * Create an AbsMatrixNxN with a unit, based on a 2-dimensional grid.
     * @param gridInUnit the matrix values {a11, a12, ..., a1N}, ..., {aN1, aN2, ..., aNN}} expressed in the unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new AbsMatrixNxN with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the data object is not a square grid
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrixNxN<A, Q> of(
            final double[][] gridInUnit, final Unit<?, Q> unit, final R reference)
    {
        return new AbsMatrixNxN<>(MatrixNxN.of(gridInUnit, unit), reference);
    }

    /**
     * Create an AbsMatrixNxN without needing generics.
     * @param grid the matrix values {a11, a12, ..., a1N}, ..., {aN1, aN2, ..., aNN}} expressed as an array of quantities
     * @param reference the reference point for the absolute quantities
     * @return a new AbsMatrixNxN with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     * @throws IllegalArgumentException when the data object is not a square grid
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrixNxN<A, Q> of(final Q[][] grid, final R reference)
    {
        return new AbsMatrixNxN<>(MatrixNxN.of(grid), reference);
    }

    /**
     * Create an AbsMatrixNxN without needing generics.
     * @param relativeMatrix the relative matrix with values relative to the reference point
     * @param reference the reference point for the absolute quantities
     * @return a new AbsMatrixNxN with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrixNxN<A, Q> of(final MatrixNxN<Q> relativeMatrix, final R reference)
    {
        return new AbsMatrixNxN<>(relativeMatrix, reference);
    }

}
