package org.djunits.test;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.Position;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.Time;

/**
 * TestAbsolute.java.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class TestAbsolute
{
    /** */
    private TestAbsolute()
    {
    }

    /** */
    public static void testPosition()
    {
        var pRef = new Position.Reference("REF", "ref", Length.ZERO, null);
        var p1 = new Position(Length.of(10.0, "m"), pRef);
        System.out.println(p1);
        var pRef100 = new Position.Reference("REF100", "ref100", Length.of(100.0, "m"), pRef);
        var p2 = new Position(Length.of(10.0, "m"), pRef100);
        System.out.println(p2);
        System.out.println(p2.relativeTo(pRef));
        System.out.println();
    }

    /** */
    public static void testAngle()
    {
        var d1 = new Direction(Angle.of(30.0, "deg"), Direction.Reference.NORTH);
        System.out.println(d1);
        System.out.println(d1.relativeTo(Direction.Reference.EAST));
        System.out.println();
    }

    /** */
    public static void testTime()
    {
        var ms = System.currentTimeMillis();
        var t1 = new Time(Duration.of(1.0 * ms, "ms"), Time.Reference.UNIX);
        System.out.println(t1);
        try
        {
            System.out.println(t1.relativeTo(Time.Reference.GPS));
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    /** */
    public static void testTemperature()
    {
        var t1 = new Temperature(15.0, "degC", Temperature.Reference.CELSIUS);
        System.out.println(t1.relativeTo(Temperature.Reference.KELVIN));
        System.out.println();
    }

    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        testPosition();
        testAngle();
        testTime();
        testTemperature();
    }

}
