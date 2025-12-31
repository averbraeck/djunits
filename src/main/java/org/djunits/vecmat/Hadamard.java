package org.djunits.vecmat;

/**
 * Hadamard operations are element-wise operations on vectors, matrices or collections of quantities.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <H> the implementing type
 */
public interface Hadamard<H extends Hadamard<H>>
{
    /**
     * Invert the collection on an element-by-element basis.
     * @return a collection with an element-by-element inversion (1/value) of the elements
     */
    H invertElements();

    /**
     * Multiply the elements on an element-by-element basis with those of another collection of the same type (but possibly
     * representing another quantity).
     * @param other the other collection of the same type
     * @return a collection with an element-by-element multiplication of its elements
     */
    H multiplyElements(H other);

    /**
     * Divide the elements of this collection on an element-by-element basis with those of another collection of the same type
     * (but possibly representing another quantity).
     * @param other the other collection of the same type
     * @return a collection with an element-by-element division of this collection's elements and other's elements
     */
    H divideElements(H other);
}
