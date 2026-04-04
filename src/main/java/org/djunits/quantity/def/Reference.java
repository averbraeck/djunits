package org.djunits.quantity.def;

import java.util.Map;

import org.djutils.base.Identifiable;

/**
 * Reference contains information about the reference point or origin / zero point of an absolute quantity.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <R> the reference type itself
 * @param <A> the absolute quantity type for generics instantiation
 * @param <Q> the relative quantity type for the offset
 */
public interface Reference<R extends Reference<R, A, Q>, A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>>
        extends Identifiable
{
    /**
     * Return a safe copy of the static reference map for this Reference subclass.
     * @return a safe copy of the static reference map for this subclass
     */
    Map<String, Reference<?, ?, ?>> getReferenceMap();

    /**
     * Instance-level unregister; removes this reference from the per-class registry. Intended primarily for unit tests to clean
     * up temporary references. Existing objects that hold a direct pointer to this instance continue to work.
     * @return true if this reference was removed from the registry; false if it was not present
     */
    boolean unregister();

    /**
     * Return a strongly typed absolute quantity belonging to this reference.
     * @param quantity the relative quantity that indicates the 'distance' to this reference point
     * @return a strongly typed absolute quantity belonging to this reference
     */
    A instantiate(Q quantity);

    /**
     * Return the offset w.r.t. the offset reference, or zero when the offset is not defined.
     * @return the offset expressed in the relative quantity
     */
    Q getOffset();

    /**
     * Return the offset reference for the offset, or null when the offset reference is not defined.
     * @return the offset reference
     */
    R getOffsetReference();

    /**
     * Return the description of this reference point.
     * @return description of this reference point
     */
    String getName();
}
