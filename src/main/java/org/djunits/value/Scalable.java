package org.djunits.value;

/**
 * Scalable indicates that elements of this type can be scaled by a dimensionless factor, based on their SI-values. Note that
 * not all unit-carrying elements are scalable. An example is an absolute scalar (e.g., a date) that cannot be multiplied by 2.
 * <p>
 * Warning: scaling operations on units with an Offset Scale might lead to unexpected answers. 2 * 10 &deg;C = 293.15 &deg;C
 * and not 20 &deg;C.
 * </p>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <T> the element type
 */
public interface Scalable<T extends Scalable<T>>
{
    /**
     * Scale the element by a dimensionless factor, based on the SI-values. Note that this can lead to unwanted effects for
     * elements with Offset scales. 2 * 10 &deg;C = 293.15 &deg;C and not 20 &deg;C.
     * @param factor the dimensionless scale factor
     * @return a new element with all of its SI-values scaled by the dimensionless factor
     */
    T scale(double factor);
}
