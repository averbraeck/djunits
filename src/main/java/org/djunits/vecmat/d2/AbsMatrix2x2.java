package org.djunits.vecmat.d2;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Reference;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsSquareMatrix;

/**
 * AbsMatrix2x2 implements a matrix with 2x2 absolute quantities with a reference point. The matrix is immutable, except for the
 * display unit, which can be changed.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsMatrix2x2<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsSquareMatrix<A, Q, AbsMatrix2x2<A, Q>, Matrix2x2<Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsMatrix2x2 with a display unit and a reference point.
     * @param relativeMatrix the matrix values {a11} expressed in the displayUnit
     * @param reference the reference point for the absolute values
     */
    public AbsMatrix2x2(final Matrix2x2<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        super(relativeMatrix, reference);
    }

    @Override
    public AbsMatrix2x2<A, Q> instantiate(final Matrix2x2<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        return new AbsMatrix2x2<>(relativeMatrix, reference);
    }

    @Override
    public AbsVector2.Row<A, Q> getRowVector(final int row)
    {
        return new AbsVector2.Row<>(getRelativeVecMat().getRowVector(row), getReference());
    }

    @Override
    public AbsVector2.Row<A, Q> mgetRowVector(final int mRow)
    {
        return new AbsVector2.Row<>(getRelativeVecMat().mgetRowVector(mRow), getReference());
    }

    @Override
    public AbsVector2.Col<A, Q> getColumnVector(final int col)
    {
        return new AbsVector2.Col<>(getRelativeVecMat().getColumnVector(col), getReference());
    }

    @Override
    public AbsVector2.Col<A, Q> mgetColumnVector(final int mCol)
    {
        return new AbsVector2.Col<>(getRelativeVecMat().mgetColumnVector(mCol), getReference());
    }

    @Override
    public AbsVector2.Col<A, Q> getDiagonalVector()
    {
        return new AbsVector2.Col<>(getRelativeVecMat().getDiagonalVector(), getReference());
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Return a new 2x2 matrix with the given SI or BASE values for the relative vector or matrix.
     * @param dataSi a row-major array with SI values relative to the reference point
     * @param displayUnit the display unit to use for the matrix
     * @param reference the reference point for the relative SI values
     * @return a new matrix with the provided SI or BASE values
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static <A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>> AbsMatrix2x2<A, Q> ofSi(final double[] dataSi,
            final Unit<?, Q> displayUnit, final Reference<?, A, Q> reference)
    {
        return of(Matrix2x2.ofSi(dataSi, displayUnit), reference);
    }

    /**
     * Return a new 2x2 matrix with the given SI or BASE values for the relative vector or matrix.
     * @param relativeMatrix the underlying relative matrix with SI values relative to the reference point
     * @param reference the reference point for the relative SI values
     * @return a new matrix with the provided SI or BASE values
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static <A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>> AbsMatrix2x2<A, Q> of(
            final Matrix2x2<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        return new AbsMatrix2x2<>(relativeMatrix, reference);
    }
    
    // ------------------------------------------ AS METHODS ------------------------------------------


}
