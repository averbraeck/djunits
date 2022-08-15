package org.djunits.value.vfloat.function;

/**
 * DoubleFunction carries out a specific transformation function on a float value.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public interface FloatFunction
{
    /**
     * Carry out a specific transformation function on a float value.
     * @param value float; the value to transform
     * @return float; the transformed value
     */
    float apply(float value);

}
