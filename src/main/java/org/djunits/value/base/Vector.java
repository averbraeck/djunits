package org.djunits.value.base;

import java.util.Iterator;

import org.djunits.unit.Unit;
import org.djunits.value.IndexedValue;
import org.djunits.value.storage.Storage;

/**
 * Vector to distinguish a vector from vectors and matrices. A possible way to implement this interface is:
 * 
 * <pre>
 * class LengthVector implements Vector&lt;LengthUnit, Length, LengthVector&gt;
 * </pre>
 * 
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>. <br>
 * @author Alexander Verbraeck
 * @param <U> the unit
 * @param <S> the scalar type belonging to the vector type
 * @param <V> the vector type with the given unit
 * @param <D> the data storage type
 */
public abstract class Vector<U extends Unit<U>, S extends Scalar<U, S>, V extends Vector<U, S, V, D>, D extends Storage<D>>
        extends IndexedValue<U, S, V, D> implements Iterable<S>
{
    /** */
    private static final long serialVersionUID = 20230620L;

    /**
     * Construct a new Vector.
     * @param displayUnit the unit of the new AbstractValue
     */
    public Vector(final U displayUnit)
    {
        super(displayUnit);
    }

    /**
     * Retrieve the size of the vector.
     * @return the size of the vector
     */
    public abstract int size();

    /**
     * Retrieve a value from the vector.
     * @param index the index to retrieve the value at
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public abstract S get(int index) throws IndexOutOfBoundsException;

    /**
     * Return the vector as an array of scalars.
     * @return the vector as an array of scalars
     */
    public abstract S[] getScalars();

    /**
     * Create and return an iterator over the scalars in this vector in proper sequence.
     * @return an iterator over the scalars in this vector in proper sequence
     */
    @Override
    public abstract Iterator<S> iterator();

}
