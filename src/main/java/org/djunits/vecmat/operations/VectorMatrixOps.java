package org.djunits.vecmat.operations;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.util.ArrayMath;
import org.djunits.util.Math2;
import org.djunits.value.Additive;
import org.djunits.value.Scalable;
import org.djunits.value.Value;

/**
 * VecMathOps contains a number of standard operations on vectors and matrices of quantities.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <VM> the vector or matrix type
 */
public interface VectorMatrixOps<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, VM extends VectorMatrixOps<Q, U, VM>>
        extends Value<U, VM>, Scalable<VM>, Additive<VM>
{
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
     * Return a row-major array of SI-values for this matrix or vector. Note that this is NOT a safe copy.
     * @return the row-major array of SI-values
     */
    double[] si();

    /**
     * Return a new matrix with the given SI or BASE values.
     * @param siNew the values for the new matrix
     * @return a new matrix with the provided SI or BASE values
     */
    VM instantiate(double[] siNew);

    /**
     * Return the mean value of the elements of the vector or matrix.
     * @return the mean value of the elements of the vector or matrix
     */
    default Q mean()
    {
        return getDisplayUnit().ofSi(sum().si() / si().length).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the minimum value of the elements of the vector or matrix.
     * @return the minimum value of the elements of the vector or matrix
     */
    default Q min()
    {
        return getDisplayUnit().ofSi(Math2.min(si())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the maximum value of the elements of the vector or matrix.
     * @return the maximum value of the elements of the vector or matrix
     */
    default Q max()
    {
        return getDisplayUnit().ofSi(Math2.max(si())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the largest value of the elements of the vector or matrix.
     * @return the largest value of the elements of the vector or matrix
     */
    default Q mode()
    {
        return max();
    }

    /**
     * Return the median value of the elements of the vector or matrix.
     * @return the median value of the elements of the vector or matrix
     */
    default Q median()
    {
        return getDisplayUnit().ofSi(Math2.median(si())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the sum of the values of the elements of the vector or matrix.
     * @return the sum of the values of the elements of the vector or matrix
     */
    default Q sum()
    {
        return getDisplayUnit().ofSi(Math2.sum(si())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the a vector or matrix with entries that contain the sum of the element and the increment.
     * @param increment the quantity by which to increase the values of the vector or matrix
     * @return a vector or matrix with elements that are incremented by the given quantity
     */
    default VM add(final Q increment)
    {
        return instantiate(ArrayMath.add(si(), increment.si()));
    }

    /**
     * Return the a vector or matrix with entries that contain the value minus the decrement.
     * @param decrement the quantity by which to decrease the values of the vector or matrix
     * @return a vector or matrix with elements that are decremented by the given quantity
     */
    default VM subtract(final Q decrement)
    {
        return instantiate(ArrayMath.add(si(), -decrement.si()));
    }

    @Override
    default VM add(final VM other)
    {
        return instantiate(ArrayMath.add(si(), other.si()));
    }

    @Override
    default VM subtract(final VM other)
    {
        return instantiate(ArrayMath.subtract(si(), other.si()));
    }

    @Override
    default VM negate()
    {
        return scaleBy(-1.0);
    }

    @Override
    default VM abs()
    {
        return instantiate(ArrayMath.abs(si()));
    }

    @Override
    default VM scaleBy(final double factor)
    {
        return instantiate(ArrayMath.scaleBy(si(), factor));
    }

}
