package org.djunits.value.vfloat.scalar;

import static org.junit.Assert.assertEquals;

import org.djunits.unit.DirectionUnit;
import org.junit.Test;

/**
 * FloatDirectionTest.java. <br>
 * <p>
 * Copyright (c) 2003-2022 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.<br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FloatDirectionTest
{
    /**
     * Check the conversions and counter clockwise direction.
     */
    @Test
    public final void testFloatDirectionDegToRad()
    {
        FloatDirection directionE10 = new FloatDirection(10.0f, DirectionUnit.EAST_DEGREE);
        assertEquals("10 degrees East should be pi*10/180 radians", Math.PI * (10.0 / 180.0), directionE10.getSI(), 0.0001);
        FloatDirection directionN10 = new FloatDirection(10.0f, DirectionUnit.NORTH_DEGREE);
        assertEquals("10 degrees North should be pi*100/180 radians", Math.PI * (100.0 / 180.0), directionN10.getSI(), 0.0001);
    }

    /**
     * Check the conversions and counter clockwise direction.
     */
    @Test
    public final void testFloatDirectionStringConversions()
    {
        assertEquals(0.0, FloatDirection.valueOf("0.0 deg(E)").getSI(), 0.0001);
        assertEquals(Math.PI, FloatDirection.valueOf("180 deg(E)").getSI(), 0.0001);
        assertEquals(0.0, FloatDirection.valueOf("0.0deg(E)").getSI(), 0.0001);
        assertEquals(Math.PI, FloatDirection.valueOf("180deg(E)").getSI(), 0.0001);

        assertEquals(0.0, FloatDirection.valueOf("0.0 rad(E)").getSI(), 0.0001);
        assertEquals(Math.PI, FloatDirection.valueOf("3.1415927 rad(E)").getSI(), 0.0001);
        assertEquals(0.0, FloatDirection.valueOf("0.0rad(E)").getSI(), 0.0001);
        assertEquals(Math.PI, FloatDirection.valueOf("3.1415927rad(E)").getSI(), 0.0001);

        assertEquals(0.5 * Math.PI, FloatDirection.valueOf("0.0 deg(N)").getSI(), 0.0001);
        assertEquals(Math.PI, FloatDirection.valueOf("90 deg(N)").getSI(), 0.0001);
        assertEquals(0.5 * Math.PI, FloatDirection.valueOf("0.0deg(N)").getSI(), 0.0001);
        assertEquals(Math.PI, FloatDirection.valueOf("90deg(N)").getSI(), 0.0001);

        assertEquals(0.5 * Math.PI, FloatDirection.valueOf("0.0 rad(N)").getSI(), 0.0001);
        assertEquals(1.5 * Math.PI, FloatDirection.valueOf("3.1415927 rad(N)").getSI(), 0.0001);
        assertEquals(0.5 * Math.PI, FloatDirection.valueOf("0.0rad(N)").getSI(), 0.0001);
        assertEquals(1.5 * Math.PI, FloatDirection.valueOf("3.1415927rad(N)").getSI(), 0.0001);
    }
}
