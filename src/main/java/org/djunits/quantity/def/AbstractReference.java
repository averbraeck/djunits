package org.djunits.quantity.def;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.djutils.exceptions.Throw;

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
public abstract class AbstractReference<R extends AbstractReference<R, A, Q>, A extends AbsQuantity<A, Q, R>,
        Q extends Quantity<Q>> implements Reference<R, A, Q>
{
    /**
     * Master registry: per concrete Reference subclass we keep a map of id to reference. This prevents name collisions between
     * different absolute quantities.
     */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected static final Map<Class<?>, Map<String, Reference<?, ?, ?>>> REFERENCES = new LinkedHashMap<>();

    /** The id. */
    private final String id;

    /** The explanation. */
    private final String name;

    /** The offset w.r.t. the offset reference, is ZERO when offsetReference is null. */
    private final Q offset;

    /** The reference to which the offset is relative, can be null. */
    private final R offsetReference;

    /**
     * Define a new reference point for the absolute quantity. Prevent duplicate registration of the same id within the same
     * Reference subclass.
     * @param id the id
     * @param name the name or explanation
     * @param offset the offset w.r.t. the offsetReference, should be ZERO when offsetReference is null
     * @param offsetReference the reference to which the offset is relative, can be null
     * @throws IllegalArgumentException if an id is already registered for this Reference subclass
     */
    public AbstractReference(final String id, final String name, final Q offset, final R offsetReference)
    {
        Throw.whenNull(id, "id");
        Throw.whenNull(name, "name");
        Throw.whenNull(offset, "offset");

        this.id = id;
        this.name = name;
        this.offset = offset;
        this.offsetReference = offsetReference;

        // Register in the per-class map for THIS concrete Reference subclass.
        final Class<?> refClass = getClass();
        final Map<String, Reference<?, ?, ?>> map = mapFor(refClass);

        Throw.when(map.containsKey(id), IllegalArgumentException.class, "Reference id '%s' already registered for %s", id,
                refClass.getSimpleName());

        map.put(id, this);
    }

    /**
     * Get or create the inner map for a specific Reference subclass.
     * @param referenceClass the reference class to look up
     * @return the existing or new reference map for the the Reference subclass
     */
    protected static Map<String, Reference<?, ?, ?>> mapFor(final Class<?> referenceClass)
    {
        return REFERENCES.computeIfAbsent(referenceClass, k -> new LinkedHashMap<>());
    }

    /**
     * Fetch a reference by class and id. Returns null when not found.
     * @param referenceClass the concrete Reference subclass
     * @param id the id
     * @return the reference instance or null
     * @param <R> the reference subclass type
     */
    @SuppressWarnings("unchecked")
    public static <R extends Reference<R, ?, ?>> R get(final Class<R> referenceClass, final String id)
    {
        final Map<String, Reference<?, ?, ?>> map = REFERENCES.get(referenceClass);
        if (map == null)
        {
            return null;
        }
        return (R) map.get(id);
    }

    /**
     * Check existence of id in a specific Reference subclass.
     * @param referenceClass the reference subclass to check
     * @param id the id to check
     * @return whether the id exists for the Reference subclass
     */
    public static boolean containsId(final Class<?> referenceClass, final String id)
    {
        final Map<String, Reference<?, ?, ?>> map = REFERENCES.get(referenceClass);
        return map != null && map.containsKey(id);
    }

    /**
     * Return a safe copy (snapshot) of the registry for a Reference subclass.
     * @param referenceClass the reference subclass to retrieve
     * @return a safe copy of the reference map
     */
    public static Map<String, Reference<?, ?, ?>> snapshotMap(final Class<?> referenceClass)
    {
        final Map<String, Reference<?, ?, ?>> map = REFERENCES.get(referenceClass);
        return map == null ? Map.of() : new LinkedHashMap<>(map);
    }

    /**
     * Return a safe copy of the static reference map for this Reference subclass.
     * @return a safe copy of the static reference map for this subclass
     */
    @Override
    public Map<String, Reference<?, ?, ?>> getReferenceMap()
    {
        return snapshotMap(getClass());
    }

    /**
     * Instance-level unregister; removes this reference from the per-class registry. Intended primarily for unit tests to clean
     * up temporary references. Existing objects that hold a direct pointer to this instance continue to work.
     * @return true if this reference was removed from the registry; false if it was not present
     */
    @Override
    public boolean unregister()
    {
        final Map<String, Reference<?, ?, ?>> map = mapFor(getClass());
        synchronized (map)
        {
            // remove(key, value): only remove if the map still points at *this* instance
            return map.remove(getId(), this);
        }
    }

    /**
     * Return a strongly typed absolute quantity belonging to this reference.
     * @param quantity the relative quantity that indicates the 'distance' to this reference point 
     * @return a strongly typed absolute quantity belonging to this reference
     */
    @Override
    public abstract A instantiate(Q quantity);
    
    /**
     * Return the offset w.r.t. the offset reference, or zero when the offset is not defined.
     * @return the offset expressed in the relative quantity
     */
    @Override
    public Q getOffset()
    {
        return this.offset;
    }

    /**
     * Return the offset reference for the offset, or null when the offset reference is not defined.
     * @return the offset reference
     */
    @Override
    public R getOffsetReference()
    {
        return this.offsetReference;
    }

    @Override
    public String getId()
    {
        return this.id;
    }

    /** @return description of this reference point */
    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id);
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
        AbstractReference<?, ?, ?> other = (AbstractReference<?, ?, ?>) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString()
    {
        return this.id;
    }
}
