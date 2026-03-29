package org.djunits.vecmat.dn;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;
import org.djunits.vecmat.def.AbsVector;

/**
 * AbsVectorN implements a vector with three real-valued entries representing an absolute quantity. The vector is immutable,
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
public abstract class AbsVectorN<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>,
        VA extends AbsVectorN<A, Q, VA, VQ, VAT>, VQ extends VectorN<Q, VQ, ?, ?, ?>, VAT extends AbsVectorN<A, Q, VAT, ?, VA>>
        extends AbsVector<A, Q, VA, VQ, VAT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate an AbsVectorN.
     * @param relativeVector the vector with values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVectorN(final VQ relativeVector, final AbstractReference<?, A, Q> reference)
    {
        super(relativeVector, reference);
    }

    /**
     * Row vector for AbsVectorN with absolute quantities.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static class Row<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
            extends AbsVectorN<A, Q, AbsVectorN.Row<A, Q>, VectorN.Row<Q>, AbsVectorN.Col<A, Q>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new AbsVectorN with absolute quantities, with a display unit and a reference point.
         * @param relativeVector the vector with values relative to the reference point
         * @param reference the reference point for the absolute values
         */
        public Row(final VectorN.Row<Q> relativeVector, final AbstractReference<?, A, Q> reference)
        {
            super(relativeVector, reference);
        }

        @Override
        public AbsVectorN.Row<A, Q> instantiate(final VectorN.Row<Q> relativeVector,
                final AbstractReference<?, A, Q> reference)
        {
            return new AbsVectorN.Row<>(relativeVector, reference);
        }

        @Override
        public AbsVectorN.Col<A, Q> transpose()
        {
            return new AbsVectorN.Col<>(getRelativeVecMat().transpose(), getReference());
        }
    }

    /**
     * Column vector for AbsVectorN with absolute quantities.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static class Col<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
            extends AbsVectorN<A, Q, AbsVectorN.Col<A, Q>, VectorN.Col<Q>, AbsVectorN.Row<A, Q>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new AbsVectorN with absolute quantities, with a display unit and a reference point.
         * @param relativeVector the vector with values relative to the reference point
         * @param reference the reference point for the absolute values
         */
        public Col(final VectorN.Col<Q> relativeVector, final AbstractReference<?, A, Q> reference)
        {
            super(relativeVector, reference);
        }

        @Override
        public AbsVectorN.Col<A, Q> instantiate(final VectorN.Col<Q> relativeVector,
                final AbstractReference<?, A, Q> reference)
        {
            return new AbsVectorN.Col<>(relativeVector, reference);
        }

        @Override
        public AbsVectorN.Row<A, Q> transpose()
        {
            return new AbsVectorN.Row<>(getRelativeVecMat().transpose(), getReference());
        }
    }

}
