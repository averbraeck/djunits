package org.djunits.vecmat.operations;

import java.util.Arrays;
import java.util.Iterator;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.util.ArrayMath;
import org.djunits.value.Value;

/**
 * AbsoluteVectorOps contains the contract for AbsoluteVector classes. In addition, AbsoluteVector classes can implement other
 * interfaces as well, such as VectorTransposable.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <R> the reference type
 * @param <QV> the relative vector type
 * @param <AV> the absolute vector type
 */
public interface AbsoluteVector<A extends AbsoluteQuantity<A, Q, U, R>, Q extends Quantity<Q, U>,
        U extends UnitInterface<U, Q>, R extends AbstractReference<R, Q>, QV extends Vector<Q, U, QV>,
        AV extends AbsoluteVector<A, Q, U, R, QV, AV>> extends Iterable<A>, Value<U, AV>
{
    /**
     * Return the relative vector belonging to this absolute vector.
     * @return the relative vector belonging to this absolute vector
     */
    QV getRelativeVector();

    /**
     * Return the reference point.
     * @return the reference point
     */
    R getReference();

    /**
     * Retrieve the size of the vector.
     * @return the size of the vector
     */
    default int size()
    {
        return getRelativeVector().size();
    }

    /**
     * Return the number of rows.
     * @return the number of rows
     */
    int rows();

    /**
     * Return the number of columns.
     * @return the number of columns
     */
    int cols();

    /**
     * Return a row-major array of SI-values for this vector. The SI values are the values in SI units, relative to he
     * reference. Note that this is NOT a safe copy.
     * @return the row-major array of SI-values
     */
    double[] si();

    /**
     * Return a new vector with the given SI or BASE values, relative to the given reference.
     * @param siNew the values for the new vector
     * @param newReference the reference for the new absolute vector
     * @return a new vector with the provided SI or BASE values
     */
    AV instantiateSi(double[] siNew, R newReference);

    /**
     * Return a vector with entries that contain the sum of the element and the increment, relative to the reference.
     * @param increment the quantity by which to increase the values of the vector or matrix
     * @return a vector or matrix with elements that are incremented by the given quantity
     */
    default AV add(final Q increment)
    {
        return instantiateSi(ArrayMath.add(si(), increment.si()), getReference());
    }

    /**
     * Return a vector with entries that contain the value minus the decrement, relative to the reference.
     * @param decrement the quantity by which to decrease the values of the vector or matrix
     * @return a vector or matrix with elements that are decremented by the given quantity
     */
    default AV subtract(final Q decrement)
    {
        return instantiateSi(ArrayMath.add(si(), -decrement.si()), getReference());
    }

    /**
     * Return a vector with entries that contain the sum of the absolute element and the equivalent relative element in the
     * other vector. The result will be relative to the given reference.
     * @param relativeVector the quantity by which to increase the values of the vector
     * @return a vector with elements that are incremented by the given quantity
     */
    default AV add(final QV relativeVector)
    {
        return instantiateSi(ArrayMath.add(si(), relativeVector.si()), getReference());
    }

    /**
     * Return a vector with entries that contain the absolute element minus the equivalent relative element in the other vector.
     * The result will be relative to the given reference.
     * @param relativeVector the quantity by which to decrease the values of the vector
     * @return a vector with elements that are decreased by the given quantity
     */
    default AV subtract(final QV relativeVector)
    {
        return instantiateSi(ArrayMath.subtract(si(), relativeVector.si()), getReference());
    }

    /**
     * Retrieve a value from the vector.
     * @param index the index to retrieve the value at
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    A get(int index) throws IndexOutOfBoundsException;

    /**
     * Return the vector as an array of scalars.
     * @return the vector as an array of scalars
     */
    A[] getScalarArray();

    /**
     * Create and return an iterator over the scalars in this vector in proper sequence.
     * @return an iterator over the scalars in this vector in proper sequence
     */
    @Override
    default Iterator<A> iterator()
    {
        return Arrays.stream(getScalarArray()).iterator();
    }

}
