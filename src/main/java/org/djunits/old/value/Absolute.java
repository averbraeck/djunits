package org.djunits.old.value;

import org.djunits.old.unit.AbsoluteLinearUnit;
import org.djunits.old.unit.Unit;

/**
 * Absolute values are quantities that are measured from some agreed upon reference point. Absolute types always have an
 * associated Relative type, indicated by the interface AbsWithRel. Values are Absolute when the sum of two values makes no
 * sense, but the difference does (but results in a Relative).
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
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
     * @param rel R the right operand
     * @return the sum of this value and the operand
     */
    A plus(R rel);

    /**
     * Subtract a Relative value from this Absolute value. A new value is returned due to immutability.
     * @param rel R the right operand
     * @return the subtraction of this value and the operand
     */
    A minus(R rel);

    /**
     * Subtract an Absolute value from this Absolute value, resulting in a Relative value. A new value is returned due to
     * immutability.
     * @param abs A the right operand
     * @return the subtraction of this value and the operand
     */
    R minus(A abs);
}
