package org.djunits.vecmat.d3;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;
import org.djunits.vecmat.def.AbsSquareMatrix;

/**
 * AbsMatrix3x3 implements a matrix with 3x3 absolute quantities with a reference point. The matrix is immutable, except for the
 * display unit, which can be changed.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsMatrix3x3<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsSquareMatrix<A, Q, AbsMatrix3x3<A, Q>, Matrix3x3<Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsMatrix3x3 with a display unit and a reference point.
     * @param relativeMatrix the matrix values {a11} expressed in the displayUnit
     * @param reference the reference point for the absolute values
     */
    public AbsMatrix3x3(final Matrix3x3<Q> relativeMatrix, final AbstractReference<?, A, Q> reference)
    {
        super(relativeMatrix, reference);
    }

    @Override
    public AbsMatrix3x3<A, Q> instantiate(final Matrix3x3<Q> relativeMatrix, final AbstractReference<?, A, Q> reference)
    {
        return new AbsMatrix3x3<>(relativeMatrix, reference);
    }

    @Override
    public AbsVector3.Row<A, Q> getRowVector(final int row)
    {
        return new AbsVector3.Row<>(getRelativeVecMat().getRowVector(row), getReference());
    }

    @Override
    public AbsVector3.Row<A, Q> mgetRowVector(final int mRow)
    {
        return new AbsVector3.Row<>(getRelativeVecMat().mgetRowVector(mRow), getReference());
    }

    @Override
    public AbsVector3.Col<A, Q> getColumnVector(final int col)
    {
        return new AbsVector3.Col<>(getRelativeVecMat().getColumnVector(col), getReference());
    }

    @Override
    public AbsVector3.Col<A, Q> mgetColumnVector(final int mCol)
    {
        return new AbsVector3.Col<>(getRelativeVecMat().mgetColumnVector(mCol), getReference());
    }

    @Override
    public AbsVector3.Col<A, Q> getDiagonalVector()
    {
        return new AbsVector3.Col<>(getRelativeVecMat().getDiagonalVector(), getReference());
    }

}
