package org.djunits.old.value.vfloat.function;

/**
 * DoubleFunction carries out a specific transformation function on a float value.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 */
public interface FloatFunction
{
    /**
     * Carry out a specific transformation function on a float value.
     * @param value the value to transform
     * @return the transformed value
     */
    float apply(float value);

}
