package org.djunits.unit.scale;

import java.io.Serializable;

/**
 * Scales for unit conversion, offers functions to and from SI units. E.g., {@link LinearScale} for Length, Area, etc.
 * {@link GradeScale} for percentual angle.
 * <p>
 * Copyright (c) 2013-2026 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
public interface Scale extends Serializable
{
    /**
     * Convert a value expressed in this unit to its base (SI) value on the identity scale.
     * @param value the value expressed in this unit
     * @return the value converted to its SI value
     */
    double toIdentityScale(double value);

    /**
     * Convert a value from a base (SI) value on the identity scale to a value in the unit that uses this scale.
     * @param value the value to convert
     * @return the corresponding value in the given unit
     */
    double fromIdentityScale(double value);

    /**
     * Return whether a scale is an 'identity' scale. For a linear scale, any scale with conversion factor 1 would be considered
     * an identity scale.
     * @return whether the scale is an 'identity' scale
     */
    boolean isIdentityScale();

}
