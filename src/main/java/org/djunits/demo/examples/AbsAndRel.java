package org.djunits.demo.examples;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.Temperature;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class AbsAndRel
{
    /** */
    private AbsAndRel()
    {
        //
    }

    /**
     * @param args String[]; args
     */
    public static void main(final String[] args)
    {
        AbsoluteTemperature t = new AbsoluteTemperature(0.0, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
        System.out.println("Temperature t  = " + t + ", si = " + t.getSI());
        System.out.println("t in Kelvin    = " + t.toString(AbsoluteTemperatureUnit.KELVIN));
        System.out.println("t in Celsius   = " + t.toString(AbsoluteTemperatureUnit.DEGREE_CELSIUS));

        // add 32 degrees Fahrenheit - should be 0 Celsius
        System.out.println("\nadd 32 degrees Fahrenheit - should be 0 Celsius");
        Temperature t32 = new Temperature(32.0, TemperatureUnit.DEGREE_FAHRENHEIT);
        AbsoluteTemperature t2 = t.plus(t32);
        System.out.println("Temperature t2 = " + t2);
        System.out.println("t2 in Kelvin   = " + t2.toString(AbsoluteTemperatureUnit.KELVIN));
        System.out.println("t2 in Celsius  = " + t2.toString(AbsoluteTemperatureUnit.DEGREE_CELSIUS));

        // The other way around to add 32 degrees Fahrenheit should also be 0 Celsius
        System.out.println("\nThe other way around to add 32 degrees Fahrenheit should also be 0 Celsius");
        AbsoluteTemperature t3 = t32.plus(t);
        System.out.println("Temperature t3 = " + t3);
        System.out.println("t3 in Kelvin   = " + t3.toString(AbsoluteTemperatureUnit.KELVIN));
        System.out.println("t3 in Celsius  = " + t3.toString(AbsoluteTemperatureUnit.DEGREE_CELSIUS));

        // Subtraction - should yield a difference of 32 degrees Fahrenheit (17.78 K/Celcius)
        System.out.println("\nSubtraction - should yield a difference of 32 degrees Fahrenheit (17.78 K/Celcius)");
        Temperature t4 = t3.minus(t);
        System.out.println("Temperature t4 = " + t4);
        System.out.println("t4 in Kelvin   = " + t4.toString(TemperatureUnit.KELVIN));
        System.out.println("t4 in Celsius  = " + t4.toString(TemperatureUnit.DEGREE_CELSIUS));

    }

}
