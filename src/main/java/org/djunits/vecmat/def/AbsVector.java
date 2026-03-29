package org.djunits.vecmat.def;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djutils.exceptions.Throw;

/**
 * AbsVector contains the contract for Vector classes with absolute values.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 * @param <VA> the absolute vector or matrix type
 * @param <VQ> the relative vector or matrix type
 * @param <VAT> the type of the transposed version of the absolute vector
 */
public abstract class AbsVector<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>, VA extends AbsVector<A, Q, VA, VQ, VAT>,
        VQ extends Vector<Q, VQ, ?, ?, ?>, VAT extends AbsVector<A, Q, VAT, ?, VA>> extends AbsVectorMatrix<A, Q, VA, VQ, VAT>
        implements Iterable<A>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new vector of absolute values with a reference point.
     * @param vector the underlying relative vector with SI values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVector(final VQ vector, final AbstractReference<?, A, Q> reference)
    {
        super(vector, reference);
    }

    /**
     * Retrieve the size of the vector.
     * @return the size of the vector
     */
    public int size()
    {
        return getRelativeVecMat().size();
    }

    /**
     * Return whether this vector is a column vector.
     * @return whether this vector is a column vector
     */
    public boolean isColumnVector()
    {
        return getRelativeVecMat().isColumnVector();
    }

    /**
     * Retrieve an si-value from the vector.
     * @param index the index (0-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public double si(final int index) throws IndexOutOfBoundsException
    {
        return getRelativeVecMat().si(index);
    }

    /**
     * Retrieve an si-value from the vector, based on a 1-valued index.
     * @param mIndex the index (1-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public double msi(final int mIndex) throws IndexOutOfBoundsException
    {
        return getRelativeVecMat().msi(mIndex);
    }

    /**
     * Retrieve a value from the vector.
     * @param index the index (0-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public A get(final int index) throws IndexOutOfBoundsException
    {
        return getReference().instantiate(getDisplayUnit().ofSi(si(index))).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Retrieve a value from the vector, based on a 1-valued index.
     * @param mIndex the index (1-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public A mget(final int mIndex) throws IndexOutOfBoundsException
    {
        return getReference().instantiate(getDisplayUnit().ofSi(si(mIndex - 1))).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the vector as an array of scalars.
     * @return the vector as an array of scalars
     */
    @SuppressWarnings("unchecked")
    public A[] getScalarArray()
    {
        // Determine the runtime type of Q using the first cell; constructors guarantee rows, cols >= 0.
        final A first = getReference().instantiate(getDisplayUnit().ofSi(0.0));
        final Class<?> aClass = first.getClass();
        final A[] out = (A[]) Array.newInstance(aClass, size());
        for (int i = 0; i < size(); i++)
        {
            out[i] = get(i);
        }
        return out;

    }

    @Override
    public String toString(final Unit<?, Q> displayUnit)
    {
        return getRelativeVecMat().toString(displayUnit);
    }
    
    /**
     * Create and return an iterator over the scalars in this vector in proper sequence.
     * @return an iterator over the scalars in this vector in proper sequence
     */
    @Override
    public Iterator<A> iterator()
    {
        return new AbsVectorIterator();
    }

    /** The iterator class for elements of an absolute vector. */
    class AbsVectorIterator implements Iterator<A>
    {
        /** The index for iteration. */
        private int index = 0;

        @Override
        public boolean hasNext()
        {
            return this.index < size();
        }

        @Override
        public A next()
        {
            Throw.when(!hasNext(), NoSuchElementException.class, "No more elements in absolute vector");
            return get(this.index++);
        }
    }

}
