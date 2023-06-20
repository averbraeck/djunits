package org.djunits.value;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;

/**
 * Absolute values are quantities that are measured from some agreed upon reference point. Absolute types always have an
 * associated Relative type, indicated by the interface AbsWithRel. Values are Absolute when the sum of two values makes no
 * sense, but the difference does (but results in a Relative).
 * <p>
 * Copyright (c) 2015-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <AU> the absolute unit type
 * @param <A> the absolute value type
 * @param <RU> the relative unit type
 * @param <R> the relative value type
 */
public interface Absolute<AU extends AbsoluteLinearUnit<AU, RU>, A extends Absolute<AU, A, RU, R>, RU extends Unit<RU>,
        R extends RelWithAbs<AU, A, RU, R>>
{
    /**
     * Add a Relative value to this Absolute value. A new value is returned due to immutability.
     * @param rel R; R the right operand
     * @return A; the sum of this value and the operand
     */
    A plus(R rel);

    /**
     * Subtract a Relative value from this Absolute value. A new value is returned due to immutability.
     * @param rel R; R the right operand
     * @return A; the subtraction of this value and the operand
     */
    A minus(R rel);

    /**
     * Subtract an Absolute value from this Absolute value, resulting in a Relative value. A new value is returned due to
     * immutability.
     * @param abs A; A the right operand
     * @return R; the subtraction of this value and the operand
     */
    R minus(A abs);
}
