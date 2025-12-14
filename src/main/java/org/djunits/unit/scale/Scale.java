package org.djunits.unit.scale;

import java.io.Serializable;

/**
 * Scales for unit conversion, offers functions to and from SI units. E.g., LinearScale for Length, Area, etc. LinearOffsetScale
 * for Temperature. PercentScale for Angle. LogarithmicScale for Sound, GradeScale for percentual angle.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
public interface Scale extends Serializable
{
    /**
     * Convert a value expressed in this unit to its base (SI) value.
     * @param value the value expressed in this unit
     * @return the value converted to its SI value
     */
    double toBaseValue(double value);

    /**
     * Convert a value from a base (SI) value to a value in the unit that uses this scale.
     * @param value the value to convert
     * @return the corresponding value in the given unit
     */
    double fromBaseValue(double value);

    /**
     * Return whether a scale is a 'standard' scale. For a linear scale, any scale with conversion factor 1 would be considered
     * standard. For an offset scale, it would be considered standard if the offset is 0 and the conversion factor is 1.
     * @return whether the scale is a 'standard' scale
     */
    boolean isBaseScale();

}
