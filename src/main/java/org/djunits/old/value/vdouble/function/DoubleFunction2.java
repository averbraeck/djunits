package org.djunits.old.value.vdouble.function;

/**
 * DoubleFunction carries out a specific transformation function that takes two operands.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Peter Knoppers
 */
public interface DoubleFunction2
{
    /**
     * Apply a function on two float values.
     * @param leftValue the value of the left operand
     * @param rightValue the value of the right operand
     * @return the result of the operation
     */
    double apply(double leftValue, double rightValue);

}
