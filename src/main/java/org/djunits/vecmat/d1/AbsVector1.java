package org.djunits.vecmat.d1;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;
import org.djunits.vecmat.def.AbsVector;

/**
 * AbsVector1 implements a vector with one real-valued entry representing an absolute quantity. The vector is immutable, except
 * for the display unit, which can be changed. Some of the method that have been defined already for a generic vector have been
 * re-implemented for efficiency.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsVector1<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsVector<A, Q, AbsVector1<A, Q>, Vector1<Q>, AbsVector1<A, Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsVector1 with a display unit and a reference point.
     * @param relativeVector the vector with values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVector1(final Vector1<Q> relativeVector, final AbstractReference<?, A, Q> reference)
    {
        super(relativeVector, reference);
    }

    @Override
    public AbsVector1<A, Q> instantiate(final Vector1<Q> relativeVector, final AbstractReference<?, A, Q> reference)
    {
        return new AbsVector1<>(relativeVector, reference);
    }

    @Override
    public AbsVector1<A, Q> transpose()
    {
        return this;
    }

}
