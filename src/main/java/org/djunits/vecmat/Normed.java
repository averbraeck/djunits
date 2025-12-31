package org.djunits.vecmat;

import org.djunits.quantity.def.Quantity;
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
     * Return the norm of this element, expressed as a quantity. The standard norm is the L2-norm.
     * @return the norm of this element, expressed as a quantity
     */
    default Q norm()
    {
        return normL2();
    }

    /**
     * Return the L1-norm of this element, expressed as a quantity. The L1-norm is |x1| + |x2| + ... + |xn|.
     * @return the L1-norm of this element, expressed as a quantity
     */
    Q normL1();

    /**
     * Return the L2-norm of this element, expressed as a quantity. The L2-norm is sqrt(x1^2 + x2^2 + ... + xn^2).
     * @return the L2-norm of this element, expressed as a quantity
     */
    Q normL2();

    /**
     * Return the Lp-norm of this element, expressed as a quantity. The Ln-norm is (x1^p + x2^p + ... + xn^p)^(1/p).
     * @param p the rank of the norm
     * @return the Lp-norm of this element, expressed as a quantity
     */
    Q normLp(int p);

    /**
     * Return the L&infin;-norm of this element, expressed as a quantity. The L&infin;-norm is max(|x1|, |x2|, ..., |xn|).
     * @return the L&infin;-norm of this element, expressed as a quantity
     */
    Q normLinf();

}
