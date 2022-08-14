package org.djunits.unit.scale;

import java.io.Serializable;

/**
 * Scales for unit conversion, offers functions to and from SI units. E.g., LinearScale for Length, Area, etc. LinearOffsetScale
 * for Temperature. PercentScale for Angle. LogarithmicScale for Sound, GradeScale for percentual angle.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public interface Scale extends Serializable
{
    /**
     * Convert a value to an SI value using this scale.
     * @param value double; the value to convert
     * @return the corresponding SI value
     */
    double toStandardUnit(double value);

    /**
     * Convert a value from an SI value to a value in the unit that uses this scale.
     * @param value double; the value to convert
     * @return the corresponding value in the given unit
     */
    double fromStandardUnit(double value);

    /**
     * Return whether a scale is a 'standard' scale that would belong to an SI unit. For a linear scale, any scale with
     * conversion factor 1 would be considered standard. For an offset scale, it would be considered standard if the offset is 0
     * and the conversion factor is 1.
     * @return boolean; whether the scale is a 'standard' scale that would belong to an SI unit.
     */
    boolean isBaseSIScale();

}
