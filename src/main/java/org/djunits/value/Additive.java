package org.djunits.value;

/**
 * Additive indicates that elements of the same type can be added, subtracted and negated, based on the SI-values. Note that not
 * all unit-carrying elements are additive. An example is an absolute scalar (e.g., a date) that cannot be added to another
 * absolute scalar.
 * <p>
 * Warning: additive operations on units with an Offset Scale might lead to unexpected answers. 10 &deg;C + 5 &deg;C = 288.15
 * &deg;C and not 15 &deg;C.
 * </p>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <T> the element type
 */
public interface Additive<T extends Additive<T>>
{
    /**
     * Add an element to this element, based on the SI-values. Note that this can lead to unwanted effects for elements with
     * Offset scales: 10 &deg;C + 5 &deg;C = 288.15 &deg;C and not 15 &deg;C.
     * @param other the element to add to this element
     * @return the sum of the SI-values of this element and the SI-values of the other element
     */
    T add(T other);

    /**
     * Subtract an element from this element, based on the SI-values.
     * @param other the element to subtract from this element
     * @return a new element with the SI-values of this element minus the SI-values of the other element
     */
    T subtract(T other);

    /**
     * Negate this element, based on the SI-values. Note that this can lead to unwanted effects for elements with Offset scales.
     * A temperature in Celsius that is negated, will have its Kelvin temperatures negated.
     * @return a new element with all of its SI-values negated
     */
    T negate();

    /**
     * Make the SI-values of this element absolute. A new element wil be returned. Note that this can lead to unwanted effects
     * for elements with Offset scales. A temperature of -10 degrees Celsius will have its Kelvin temperatures negated.
     * @return a new element with the absolute value for all of its SI-values
     */
    T abs();

}
