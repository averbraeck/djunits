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
        System.out.println("POSITION");
        System.out.println("========");

        var pRef = new Position.Reference("REF", "ref", Length.ZERO, null);
        var p1 = new Position(Length.of(10.0, "m"), pRef);
        System.out.println(p1);
        var pRef100 = new Position.Reference("REF100", "ref100", Length.of(100.0, "m"), pRef);
        var p2 = new Position(Length.of(10.0, "m"), pRef100);
        System.out.println(p2 + " = " + p2.relativeTo(pRef));
        System.out.println(p1.toDisplayString(Length.Unit.A));
    }

    /** */
    public static void testDirection()
    {
        System.out.println("DIRECTION");
        System.out.println("=========");

        var d1 = new Direction(Angle.of(30.0, "deg"), Direction.Reference.NORTH);
        System.out.println(d1 + " = " + d1.relativeTo(Direction.Reference.EAST));

        var d2 = new Direction(Angle.of(30.0, "deg"), Direction.Reference.EAST);
        System.out.println(d2 + " = " + d2.relativeTo(Direction.Reference.NORTH));

        var d3 = new Direction(Angle.of(30.0, "deg"), Direction.Reference.NORTH);
        System.out.println(d3 + " + " + d3.relativeTo(Direction.Reference.NORTH));

        var d4 = new Direction(Angle.of(30.0, "deg"), Direction.Reference.EAST);
        System.out.println(d4 + " + " + d4.relativeTo(Direction.Reference.EAST));
        System.out.println();
    }

    /** */
    public static void testTime()
    {
        System.out.println("TIME");
        System.out.println("====");

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
        System.out.println("TEMPERATURE");
        System.out.println("===========");

        var t1 = new Temperature(15.0, "degC", Temperature.Reference.CELSIUS);
        System.out.println(t1 + " = " + t1.relativeTo(Temperature.Reference.KELVIN));
        System.out.println(t1 + " = " + t1.relativeTo(Temperature.Reference.KELVIN).getQuantity().as(Temperature.Unit.K));
        System.out.println();

        var t2 = new Temperature(80.0, "degF", Temperature.Reference.FAHRENHEIT);
        System.out.println(t2 + " = " + t2.relativeTo(Temperature.Reference.KELVIN));
        System.out.println();

        var t3 = new Temperature(32.0, "degF", Temperature.Reference.FAHRENHEIT);
        System.out.println(t3 + " = " + t3.relativeTo(Temperature.Reference.CELSIUS).getQuantity().as(Temperature.Unit.degC));
        System.out.println();
    }

    /** */
    public static void testTemperature2()
    {
        System.out.println("TEMPERATURE2");
        System.out.println("============");

        var t1 = new Temperature(15.0, "degC");
        System.out.println(t1 + " = " + t1.relativeTo(Temperature.Reference.KELVIN));
        System.out.println(t1 + " = " + t1.relativeTo(Temperature.Reference.KELVIN).toDisplayString(Temperature.Unit.K));
        System.out.println();

        var t2 = new Temperature(80.0, Temperature.Unit.degF, Temperature.Reference.FAHRENHEIT);
        System.out.println(t2 + " = " + t2.relativeTo(Temperature.Reference.KELVIN));
        System.out.println(t2 + " = " + t2.relativeTo(Temperature.Reference.CELSIUS).toDisplayString(Temperature.Unit.degC));
        System.out.println();

        var t3 = new Temperature(32.0, "degF", Temperature.Reference.FAHRENHEIT);
        System.out.println(t3 + " = " + t3.relativeTo(Temperature.Reference.CELSIUS).toDisplayString(Temperature.Unit.degC));
        System.out.println();
    }

    /** */
    public static void testSubtractAA()
    {
        System.out.println("ABS - ABS");
        System.out.println("=========");

        var t1 = new Temperature(15.0, "degC");
        var t2 = new Temperature(80.0, "degF");
        System.out.println(t2 + " - " + t1 + " = " + t2.subtract(t1));
        System.out.println(t2 + " - " + t1 + " = " + t2.subtract(t1).setDisplayUnit("degC"));
        var t3 = t2.relativeTo(Temperature.Reference.KELVIN);
        System.out.println(t3 + " - " + t1 + " = " + t3.subtract(t1).setDisplayUnit("degC"));
        System.out.println(t3 + " - " + t1 + " = " + t3.subtract(t1).setDisplayUnit("K"));
        var t4 = t2.relativeTo(Temperature.Reference.KELVIN).setDisplayUnit("K");
        System.out.println(t4 + " - " + t1 + " = " + t4.subtract(t1).setDisplayUnit("degC"));
        System.out.println(t4 + " - " + t1 + " = " + t4.subtract(t1).setDisplayUnit("K"));
        System.out.println(t1 + " - " + t4 + " = " + t1.subtract(t4).setDisplayUnit("K"));
        System.out.println();
    }

    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        testPosition();
        testDirection();
        testTime();
        testTemperature();
        testTemperature2();
        testSubtractAA();
    }

}
