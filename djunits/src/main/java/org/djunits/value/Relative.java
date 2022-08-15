package org.djunits.value;

import org.djunits.unit.Unit;

/**
 * Relative values express differences. <br>
 * Values are Relative when adding or subtracting two values does make sense and results in a value of that same type.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit type
 * @param <T> the value type for this unit
 */
public interface Relative<U extends Unit<U>, T extends Value<U, T>>
{
    /**
     * Returns a new scalar/vector/matrix with value(s) multiplied by a factor.
     * @param multiplier double; the multiplier
     * @return T; a new scalar/vector/matrix
     */
    T times(double multiplier);

    /**
     * Returns a new scalar/vector/matrix with value(s) divided by a factor.
     * @param divisor double; the divisor
     * @return T; the modified T
     */
    T divide(double divisor);

    /**
     * Returns a new scalar/vector/matrix with value(s) multiplied by a factor.
     * @param multiplier float; the multiplier
     * @return T; a new scalar/vector/matrix
     */
    T times(float multiplier);

    /**
     * Returns a new scalar/vector/matrix with value(s) divided by a factor.
     * @param divisor float; the divisor
     * @return T; the modified T
     */
    T divide(float divisor);

}
