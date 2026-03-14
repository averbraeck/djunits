package org.djunits.vecmat.def;

import java.util.Iterator;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.operations.Normed;

/**
 * Vector contains the contract for Vector classes. In addition, Vector classes can implement other interfaces as well, such
 * as VectorTransposable.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <V> the vector type
 * @param <SI> the vector type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic vector type with generics &lt;?, ?&lt; for Hadamard operations
 */
public abstract class Vector<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, V extends VectorMatrix<Q, U, V, SI, H>,
        SI extends VectorMatrix<SIQuantity, SIUnit, SI, ?, ?>, H extends VectorMatrix<?, ?, ?, ?, ?>>
        extends VectorMatrix<Q, U, V, SI, H> implements Iterable<Q>, Normed<Q, U>
{
    /** */
    private static final long serialVersionUID = 600L;
    
    /**
     * Create a new Vector with a unit, as an extension of Matrix.
     * @param displayUnit the display unit to use
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
     * Return whether this vector is a column vector.
     * @return whether this vector is a column vector
     */
    public abstract boolean isColumnVector();

    /**
     * Retrieve an si-value from the vector.
     * @param index the index (0-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public abstract double si(int index) throws IndexOutOfBoundsException;

    /**
     * Retrieve an si-value from the vector, based on a 1-valued index.
     * @param mIndex the index (1-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public double msi(final int mIndex) throws IndexOutOfBoundsException
    {
        return si(mIndex - 1);
    }

    /**
     * Retrieve a value from the vector.
     * @param index the index (0-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public Q get(final int index) throws IndexOutOfBoundsException
    {
        return getDisplayUnit().ofSi(si(index)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Retrieve a value from the vector, based on a 1-valued index.
     * @param mIndex the index (1-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public Q mget(final int mIndex) throws IndexOutOfBoundsException
    {
        return getDisplayUnit().ofSi(si(mIndex - 1)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the vector as an array of scalars.
     * @return the vector as an array of scalars
     */
    public abstract Q[] getScalarArray();

    /**
     * Create and return an iterator over the scalars in this vector in proper sequence.
     * @return an iterator over the scalars in this vector in proper sequence
     */
    @Override
    public abstract Iterator<Q> iterator();

}
