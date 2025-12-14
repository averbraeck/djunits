package org.djunits.old.value;

import org.djunits.old.unit.AbsoluteLinearUnit;
import org.djunits.old.unit.Unit;

/**
 * Relative values express differences; a RelWithAbs value represents a relative quantity that has a corresponding absolute
 * quantity. An example is Length that belongs to the absolute type Position. Values are Relative when adding or subtracting two
 * values does make sense and results in a value of that same type.
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
public interface RelWithAbs<AU extends AbsoluteLinearUnit<AU, RU>, A extends Absolute<AU, A, RU, R>, RU extends Unit<RU>,
        R extends RelWithAbs<AU, A, RU, R>> extends Relative<RU, R>
{
    /**
     * Add an Absolute value to this relative value. A new value is returned due to immutability. The unit of the result is the
     * unit of the absolute operand.
     * @param abs A the right operand
     * @return the sum of this value and the operand
     */
    A plus(A abs);

}
