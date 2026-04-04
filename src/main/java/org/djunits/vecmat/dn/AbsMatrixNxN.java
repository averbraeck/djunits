package org.djunits.vecmat.dn;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Reference;
import org.djunits.quantity.def.Quantity;
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

}
