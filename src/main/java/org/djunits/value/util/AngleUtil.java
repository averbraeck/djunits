package org.djunits.value.util;

import org.djunits.unit.AngleUnit;
import org.djunits.value.vdouble.scalar.Angle;
import org.djunits.value.vfloat.scalar.FloatAngle;

/**
 * Utilities for Angles, such as normalization between 0 and 2 * PI.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public interface AngleUtil
{
    /**
     * Normalize an angle between 0 and 2 * PI.
     * @param angle double; original angle.
     * @return angle between 0 and 2 * PI.
     */
    static double normalize(final double angle)
    {
        double normalized = angle % (2 * Math.PI);
        if (normalized < 0.0)
        {
            normalized += 2 * Math.PI;
        }
        return normalized;
    }

    /**
     * Normalize an angle between 0 and 2 * PI.
     * @param angle float; original angle.
     * @return angle between 0 and 2 * PI.
     */
    static float normalize(final float angle)
    {
        return (float) normalize((double) angle); // for maximum precision of the float
    }

    /**
     * Normalize an angle between 0 and 2 * PI.
     * @param angle Angle; original angle.
     * @return Angle; a new Angle object with angle between 0 and 2 * PI.
     */
    static Angle normalize(final Angle angle)
    {
        return new Angle(normalize(angle.si), AngleUnit.RADIAN);
    }

    /**
     * Normalize an angle between 0 and 2 * PI.
     * @param angle FloatAngle; original angle.
     * @return angle between 0 and 2 * PI.
     */
    static FloatAngle normalize(final FloatAngle angle)
    {
        return new FloatAngle(normalize(angle.si), AngleUnit.RADIAN);
    }

}
