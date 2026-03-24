package org.djunits.vecmat.operations;

import org.djunits.quantity.def.Quantity;

/**
 * Hadamard operations are entry-wise operations on vectors, matrices or collections of quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <VM> the implementing vector or matrix type with generics &lt;?, ?&gt; to, e.g., multiply by
 * @param <SI> the implementing vector or matrix type with generics &lt;SIQuantity, SIUnit&gt;
 */
public interface Hadamard<VM, SI>
{
    /**
     * Invert the vector, matrix or table on an entry-by-entry basis.
     * @return a new vector, matrix or table with an entry-by-entry inversion (1/value) of the entries
     */
    SI invertEntries();

    /**
     * Multiply the entries of this vector, matrix or table on an entry-by-entry basis with those of another collection of
     * the same type (but possibly representing another quantity).
     * @param other the other collection of the same type
     * @return a new vector, matrix or table with an entry-by-entry multiplication of its entries
     */
    SI multiplyEntries(VM other);

    /**
     * Divide the entries of this vector, matrix or table on an entry-by-entry basis with those of another collection of
     * the same type (but possibly representing another quantity).
     * @param other the other collection of the same type
     * @return a new vector, matrix or table with an entry-by-entry division of this collection's entries and other's entries
     */
    SI divideEntries(VM other);

    /**
     * Multiply the entries of this vector, matrix or table by the given quantity.
     * @param quantity the scalar quantity to multiply by
     * @return a new vector, matrix or table where the entries have been multiplied by the given quantity
     */
    SI multiplyEntries(Quantity<?> quantity);

    /**
     * Divide the entries of this vector, matrix or table by the given quantity.
     * @param quantity the scalar quantity to divide by
     * @return a new vector, matrix or table where the entries have been divided by the given quantity
     */
    default SI divideEntries(final Quantity<?> quantity)
    {
        return multiplyEntries(quantity.reciprocal());
    }

}
