package org.djunits.vecmat;

import org.djunits.quantity.Quantity;
import org.djunits.unit.UnitInterface;

/**
 * Normed calculates a norm of an element, expressed as a quantity.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public interface Normed<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>>
{
    /**
     * Return the norm of this element, expressed as a quantity.
     * @return the norm of this element, expressed as a quantity
     */
    Q norm();
}
