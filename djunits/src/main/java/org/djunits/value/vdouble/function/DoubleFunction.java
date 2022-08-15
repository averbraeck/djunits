package org.djunits.value.vdouble.function;

/**
 * DoubleFunction carries out a specific transformation function on a double value.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public interface DoubleFunction
{
    /**
     * Carry out a specific transformation function on a double value.
     * @param value double; the value to transform
     * @return double; the transformed value
     */
    double apply(double value);
}
