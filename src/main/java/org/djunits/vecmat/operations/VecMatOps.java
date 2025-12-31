package org.djunits.vecmat.operations;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;

/**
 * VecMathOps contains a number of standard operations on vectors and matrices of quantities.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <VM> the vectoror matrix type
 */
public interface VecMatOps<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, VM extends VecMatOps<Q, U, VM>>
{
    /**
     * Return the mean value of the elements of the vector or matrix.
     * @return the mean value of the elements of the vector or matrix
     */
    Q mean();
    
    /**
     * Return the minimum value of the elements of the vector or matrix.
     * @return the minimum value of the elements of the vector or matrix
     */
    Q min();

    /**
     * Return the maximum value of the elements of the vector or matrix.
     * @return the maximum value of the elements of the vector or matrix
     */
    Q max();

    /**
     * Return the largest value of the elements of the vector or matrix.
     * @return the largest value of the elements of the vector or matrix
     */
    Q mode();

    /**
     * Return the median value of the elements of the vector or matrix.
     * @return the median value of the elements of the vector or matrix
     */
    Q median();

    /**
     * Return the sum of the values of the elements of the vector or matrix.
     * @return the sum of the values of the elements of the vector or matrix
     */
    Q sum();

    /**
     * Return the a vector or matrix with entries that contain the sum of the element and the increment.
     * @param increment the quantity by which to increase the values of the vector or matrix
     * @return a vector or matrix with elements that are incremented by the given quantity
     */
    VM plus(Q increment);

    /**
     * Return the a vector or matrix with entries that contain the value minus the decrement.
     * @param decrement the quantity by which to decrease the values of the vector or matrix
     * @return a vector or matrix with elements that are decremented by the given quantity
     */
    VM minus(Q decrement);

}
