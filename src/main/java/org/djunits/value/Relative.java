package org.djunits.value;

import org.djunits.unit.Unit;

/**
 * Relative values express differences. An example is Area or Speed. There is no corresponding absolute type. Values are
 * Relative when adding or subtracting two values does make sense and results in a value of that same type.
 * <p>
 * Copyright (c) 2015-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit type
 * @param <R> the relative value type
 */
public interface Relative<U extends Unit<U>, R extends Relative<U, R>>
{
    /**
     * Add a Relative value to this Relative value. A new value is returned due to immutability.
     * @param increment R; the value to add
     * @return R; the sum of this value and the operand as a new object
     */
    R plus(R increment);

    /**
     * Subtract a Relative value from this Relative value. A new value is returned due to immutability.
     * @param decrement R; the value to subtract
     * @return R; the difference of this value and the operand
     */
    R minus(R decrement);

    /**
     * Returns a new scalar/vector/matrix with value(s) multiplied by a factor.
     * @param multiplier double; the multiplier
     * @return T; a new scalar/vector/matrix
     */
    R times(double multiplier);

    /**
     * Returns a new scalar/vector/matrix with value(s) divided by a factor.
     * @param divisor double; the divisor
     * @return T; the modified T
     */
    R divide(double divisor);

    /**
     * Returns a new scalar/vector/matrix with value(s) multiplied by a factor.
     * @param multiplier float; the multiplier
     * @return T; a new scalar/vector/matrix
     */
    R times(float multiplier);

    /**
     * Returns a new scalar/vector/matrix with value(s) divided by a factor.
     * @param divisor float; the divisor
     * @return T; the modified T
     */
    R divide(float divisor);

}
