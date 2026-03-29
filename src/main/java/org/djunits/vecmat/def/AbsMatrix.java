package org.djunits.vecmat.def;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;

/**
 * AbsMatrix contains a number of standard operations on matrices that contain absolute quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the quantity type
 * @param <MA> the absolute matrix type
 * @param <MQ> the relative matrix type
 * @param <MAT> the type of the transposed version of the absolute matrix
 */
public abstract class AbsMatrix<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>,
        MA extends AbsMatrix<A, Q, MA, MQ, MAT>, MQ extends Matrix<Q, MQ, ?, ?, ?>, MAT extends AbsMatrix<A, Q, MAT, ?, MA>>
        extends AbsTable<A, Q, MA, MQ, MAT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new matrix of absolute values with a reference point.
     * @param matrix the underlying relative matrix with SI values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsMatrix(final MQ matrix, final AbstractReference<?, A, Q> reference)
    {
        super(matrix, reference);
    }

}
