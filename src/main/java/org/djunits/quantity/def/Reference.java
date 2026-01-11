package org.djunits.quantity.def;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.djutils.base.Identifiable;
import org.djutils.exceptions.Throw;

/**
 * Reference contains information about the reference point or origin / zero point of an absolute quantity.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <R> the reference type itself
 * @param <Q> the relative quantity for the offset
 */
public abstract class Reference<R extends Reference<R, Q>, Q extends Quantity<Q, ?>> implements Identifiable
{
    /** the list of possible reference points to use. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected static Map<String, Reference<?, ?>> referenceMap = new LinkedHashMap<>();

    /** The id. */
    private final String id;

    /** The explanation. */
    private final String name;

    /** The offset w.r.t. the offset reference, is ZERO when offsetReference is null. */
    private final Q offset;

    /** The reference to which the offset is relative, can be null. */
    private final R offsetReference;

    /**
     * Define a new reference point for the absolute quantity.
     * @param id the id
     * @param name the name or explanation
     * @param offset the offset w.r.t. the offsetReference, should be ZERO when offsetReference is null
     * @param offsetReference the reference to which the offset is relative, can be null
     */
    public Reference(final String id, final String name, final Q offset, final R offsetReference)
    {
        Throw.whenNull(id, "id");
        Throw.whenNull(name, "name");
        Throw.whenNull(offset, "offset");
        this.id = id;
        this.name = name;
        this.offset = offset;
        this.offsetReference = offsetReference;
        referenceMap.put(id, this);
    }

    /**
     * Return the offset w.r.t. the offset reference, or null when the offset is not defined.
     * @return the offset expressed in the relative quantity
     */
    public Q getOffset()
    {
        return this.offset;
    }

    /**
     * Return the offset reference for the offset, or null when the offset reference is not defined.
     * @return the offset reference
     */
    public R getOffsetReference()
    {
        return this.offsetReference;
    }

    @Override
    public String getId()
    {
        return this.id;
    }

    /**
     * Return the description of this reference point.
     * @return the description of this reference point
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Return a safe copy of the static reference map.
     * @return a safe copy of the static reference map
     */
    public Map<String, Reference<?, ?>> getReferenceMap()
    {
        return new LinkedHashMap<>(referenceMap);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.name, this.offset, this.offsetReference);
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reference<?, ?> other = (Reference<?, ?>) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name)
                && Objects.equals(this.offset, other.offset) && Objects.equals(this.offsetReference, other.offsetReference);
    }

    @Override
    public String toString()
    {
        return this.id;
    }

}
