package org.djunits.vecmat.d2;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Reference;
import org.djunits.quantity.def.Quantity;
import org.djunits.vecmat.def.AbsVector;

/**
 * AbsVector2 implements a vector with two real-valued entries representing an absolute quantity. The vector is immutable,
 * except for the display unit, which can be changed. Some of the method that have been defined already for a generic vector can
 * be re-implemented for efficiency.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 * @param <VA> the absolute vector or matrix type
 * @param <VQ> the relative vector or matrix type
 * @param <VAT> the type of the transposed version of the absolute vector
 */
public abstract class AbsVector2<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>,
        VA extends AbsVector2<A, Q, VA, VQ, VAT>, VQ extends Vector2<Q, VQ, ?, ?, ?>, VAT extends AbsVector2<A, Q, VAT, ?, VA>>
        extends AbsVector<A, Q, VA, VQ, VAT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate an AbsVector2.
     * @param relativeVector the vector with values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVector2(final VQ relativeVector, final Reference<?, A, Q> reference)
    {
        super(relativeVector, reference);
    }

    /**
     * Row vector for AbsVector2 with absolute quantities.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static class Row<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
            extends AbsVector2<A, Q, AbsVector2.Row<A, Q>, Vector2.Row<Q>, AbsVector2.Col<A, Q>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new AbsVector2 with absolute quantities, with a display unit and a reference point.
         * @param relativeVector the vector with values relative to the reference point
         * @param reference the reference point for the absolute values
         */
        public Row(final Vector2.Row<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            super(relativeVector, reference);
        }

        @Override
        public AbsVector2.Row<A, Q> instantiate(final Vector2.Row<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            return new AbsVector2.Row<>(relativeVector, reference);
        }

        @Override
        public AbsVector2.Col<A, Q> transpose()
        {
            return new AbsVector2.Col<>(getRelativeVecMat().transpose(), getReference());
        }
    }

    /**
     * Column vector for AbsVector2 with absolute quantities.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static class Col<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
            extends AbsVector2<A, Q, AbsVector2.Col<A, Q>, Vector2.Col<Q>, AbsVector2.Row<A, Q>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new AbsVector2 with absolute quantities, with a display unit and a reference point.
         * @param relativeVector the vector with values relative to the reference point
         * @param reference the reference point for the absolute values
         */
        public Col(final Vector2.Col<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            super(relativeVector, reference);
        }

        @Override
        public AbsVector2.Col<A, Q> instantiate(final Vector2.Col<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            return new AbsVector2.Col<>(relativeVector, reference);
        }

        @Override
        public AbsVector2.Row<A, Q> transpose()
        {
            return new AbsVector2.Row<>(getRelativeVecMat().transpose(), getReference());
        }
    }

}
