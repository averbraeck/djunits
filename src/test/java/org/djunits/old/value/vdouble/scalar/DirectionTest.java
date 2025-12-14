package org.djunits.old.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.djunits.old.unit.DirectionUnit;
import org.djunits.old.value.vdouble.scalar.Direction;
import org.junit.jupiter.api.Test;

/**
 * DirectionTest.java. <br>
 * <p>
 * Copyright (c) 2003-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.<br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 */
public class DirectionTest
{
    /**
     * Check the conversions and counter clockwise direction.
     */
    @Test
    public final void testDirectionDegToRad()
    {
        Direction directionE10 = new Direction(10.0, DirectionUnit.EAST_DEGREE);
        assertEquals(Math.PI * (10.0 / 180.0), directionE10.getSI(), 0.0001, "10 degrees East should be pi*10/180 radians");
        Direction directionN10 = new Direction(10.0, DirectionUnit.NORTH_DEGREE);
        assertEquals(Math.PI * (100.0 / 180.0), directionN10.getSI(), 0.0001, "10 degrees North should be pi*100/180 radians");
    }

    /**
     * Check the conversions and counter clockwise direction.
     */
    @Test
    public final void testDirectionStringConversions()
    {
        Locale.setDefault(Locale.US);

        assertEquals(0.0, Direction.valueOf("0.0 deg(E)").getSI(), 0.0001);
        assertEquals(Math.PI, Direction.valueOf("180 deg(E)").getSI(), 0.0001);
        assertEquals(0.0, Direction.valueOf("0.0deg(E)").getSI(), 0.0001);
        assertEquals(Math.PI, Direction.valueOf("180deg(E)").getSI(), 0.0001);

        assertEquals(0.0, Direction.valueOf("0.0 rad(E)").getSI(), 0.0001);
        assertEquals(Math.PI, Direction.valueOf("3.1415927 rad(E)").getSI(), 0.0001);
        assertEquals(0.0, Direction.valueOf("0.0rad(E)").getSI(), 0.0001);
        assertEquals(Math.PI, Direction.valueOf("3.1415927rad(E)").getSI(), 0.0001);

        assertEquals(0.5 * Math.PI, Direction.valueOf("0.0 deg(N)").getSI(), 0.0001);
        assertEquals(Math.PI, Direction.valueOf("90 deg(N)").getSI(), 0.0001);
        assertEquals(0.5 * Math.PI, Direction.valueOf("0.0deg(N)").getSI(), 0.0001);
        assertEquals(Math.PI, Direction.valueOf("90deg(N)").getSI(), 0.0001);

        assertEquals(0.5 * Math.PI, Direction.valueOf("0.0 rad(N)").getSI(), 0.0001);
        assertEquals(1.5 * Math.PI, Direction.valueOf("3.1415927 rad(N)").getSI(), 0.0001);
        assertEquals(0.5 * Math.PI, Direction.valueOf("0.0rad(N)").getSI(), 0.0001);
        assertEquals(1.5 * Math.PI, Direction.valueOf("3.1415927rad(N)").getSI(), 0.0001);
    }
}
