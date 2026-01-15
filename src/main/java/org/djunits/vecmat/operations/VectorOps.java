package org.djunits.vecmat.operations;

import java.util.Iterator;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;

/**
 * VectorOps contains the contract for Vector classes. In addition, Vector classes can implement other interfaces as well, such
 * as VectorMatrixOps and VectorTransposable.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <V> the vector or matrix type
 */
public interface VectorOps<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, V extends VectorOps<Q, U, V>>
        extends Iterable<Q>
{
    /**
     * Retrieve the size of the vector.
     * @return the size of the vector
     */
    int size();

    /**
     * Retrieve a value from the vector.
     * @param index the index to retrieve the value at
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    Q get(int index) throws IndexOutOfBoundsException;

    /**
     * Return the vector as an array of scalars.
     * @return the vector as an array of scalars
     */
    Q[] getScalarArray();

    /**
     * Create and return an iterator over the scalars in this vector in proper sequence.
     * @return an iterator over the scalars in this vector in proper sequence
     */
    @Override
    Iterator<Q> iterator();

}
