package org.djunits.value.vfloat.function;

/**
 * FloatFunction carries out a specific transformation function that takes two operands.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public interface FloatFunction2
{
    /**
     * Apply function on two float values.
     * @param leftValue float; the value of the left operand
     * @param rightValue float; the value of the right operand
     * @return float; the result of the operation
     */
    float apply(float leftValue, float rightValue);

}
