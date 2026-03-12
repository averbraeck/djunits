package org.djunits.vecmat.operations;

import org.djunits.quantity.def.Quantity;

/**
 * Hadamard operations are element-wise operations on vectors, matrices or collections of quantities.
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
     * Invert the vector, matrix or table on an element-by-element basis.
     * @return a vector, matrix or table with an element-by-element inversion (1/value) of the elements
     */
    SI invertElements();

    /**
     * Multiply the elements of this vector, matrix or table on an element-by-element basis with those of another collection of
     * the same type (but possibly representing another quantity).
     * @param other the other collection of the same type
     * @return a vector, matrix or table with an element-by-element multiplication of its elements
     */
    SI multiplyElements(VM other);

    /**
     * Divide the elements of this vector, matrix or table on an element-by-element basis with those of another collection of
     * the same type (but possibly representing another quantity).
     * @param other the other collection of the same type
     * @return a vector, matrix or table with an element-by-element division of this collection's elements and other's elements
     */
    SI divideElements(VM other);

    /**
     * Multiply the elements of this vector, matrix or table by the given quantity.
     * @param quantity the scalar quantity to multiply by
     * @return a vector, matrix or table where the elements have been multiplied by the given quantity
     */
    SI multiplyElements(Quantity<?, ?> quantity);

    /**
     * Divide the elements of this vector, matrix or table by the given quantity.
     * @param quantity the scalar quantity to divide by
     * @return a vector, matrix or table where the elements have been divided by the given quantity
     */
    default SI divideElements(final Quantity<?, ?> quantity)
    {
        return multiplyElements(quantity.reciprocal());
    }

}
