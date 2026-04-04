package org.djunits.vecmat.table;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Reference;
import org.djunits.quantity.def.Quantity;
import org.djunits.vecmat.def.AbsTable;
import org.djunits.vecmat.dn.AbsVectorN;
import org.djunits.vecmat.dnxm.MatrixNxM;

/**
 * AbsQuantityTable implements a table containing absolute quantities with a reference point. The table is immutable, except for
 * the display unit, which can be changed.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsQuantityTable<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsTable<A, Q, AbsQuantityTable<A, Q>, MatrixNxM<Q>, AbsQuantityTable<A, Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsQuantityTable with a display unit and a reference point.
     * @param relativeMatrix the matrix values {a_ij} expressed in the displayUnit
     * @param reference the reference point for the absolute values
     */
    public AbsQuantityTable(final MatrixNxM<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        super(relativeMatrix, reference);
    }

    @Override
    public AbsQuantityTable<A, Q> instantiate(final MatrixNxM<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        return new AbsQuantityTable<>(relativeMatrix, reference);
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

}
