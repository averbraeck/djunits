package org.djunits.demo.examples;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Energy;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Mass;
import org.djunits.value.vdouble.scalar.Speed;

/** demo for conversions. */
public final class ExamplesSpeedConversions
{
    /** */
    private ExamplesSpeedConversions()
    {
        // utility constructor.
    }

    /**
     * @param args String[]; args
     */
    public static void main(final String[] args)
    {
        example1();
        System.out.println();
        example2();
    }

    /** example 1. */
    public static void example1()
    {
        Speed speed1 = new Speed(30, SpeedUnit.MILE_PER_HOUR);
        System.out.println("speed1:     " + speed1);
        Speed speed2 = new Speed(10, SpeedUnit.METER_PER_SECOND);
        System.out.println("speed2:     " + speed2);
        Speed diff = speed1.minus(speed2);
        System.out.println("difference: " + diff); // Default display unit will be SI unit for speed
        diff.setDisplayUnit(SpeedUnit.MILE_PER_HOUR); // Change default display unit; internal SI value is unaltered
        System.out.println("difference: " + diff);
        System.out.println("difference: " + diff.getInUnit(SpeedUnit.KNOT) + " kt"); // Works, but not mistake-safe
        System.out.println("difference: " + diff.toString(SpeedUnit.KNOT)); // Safer
        System.out.println("difference: " + diff.si + " m/s (si)"); // Programmer must be really sure that SI-unit is m/s
        System.out.println("difference: " + diff.getSI() + " m/s (si)"); // Same as previous
        System.out.println("difference: " + diff.toString(SpeedUnit.SI) + " (si)"); // Safer
        System.out.println("difference: " + diff.toString(SpeedUnit.KM_PER_HOUR));
    }

    /** example 2. */
    public static void example2()
    {
        Speed speed = new Speed(12, SpeedUnit.KM_PER_HOUR);
        Length length = new Length(4, LengthUnit.MILE);
        Duration howLongOK = length.divide(speed); // Good
        System.out.println(howLongOK);
        // Duration howLongWrong = speed.divide(length); // Does not compile; result would be a frequency
        // Speed other = speed.minus(length); // Does not compile; subtracting a length from a speed make no sense
        // Acceleration acceleration = speed.times(speed).asAcceleration(); // Throws a UnitRuntimeException
        Energy kineticEnergy = speed.times(speed).times(new Mass(3, MassUnit.KILOGRAM).times(0.5)).asEnergy(); // OK
        System.out.println(kineticEnergy);
    }

}
