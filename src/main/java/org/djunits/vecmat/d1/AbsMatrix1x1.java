package org.djunits.vecmat.d1;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Reference;
import org.djunits.quantity.def.Quantity;
import org.djunits.vecmat.def.AbsSquareMatrix;

/**
 * AbsMatrix1x1 implements a matrix with 1x1 absolute quantities with a reference point. The matrix is immutable, except for the
 * display unit, which can be changed.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
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

}
