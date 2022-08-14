package org.djunits.demo.examples;

import java.util.Locale;

import org.djunits.unit.SpeedUnit;
import org.djunits.unit.util.UNITS;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.vector.SpeedVector;
import org.djunits.value.vdouble.vector.base.DoubleVector;

/**
 * This Java code demonstrates multiplication and division using DJUNITS.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @version $Revision: 954 $, $LastChangedDate: 2022-01-10 03:42:57 +0100 (Mon, 10 Jan 2022) $, by $Author: averbraeck $,
 *          initial version 3 sep. 2015 <br>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class MultiplyAndDivide implements UNITS
{
    /** */
    private MultiplyAndDivide()
    {
        // utility constructor.
    }

    /**
     * Create some scalar values to demonstrate conversion from and to related units.
     * @param args String[]; the command line arguments; not used
     * @throws ValueRuntimeException in case of error
     */
    public static void main(final String[] args) throws ValueRuntimeException
    {
        Locale.setDefault(Locale.US); // Ensure that floating point values are printed using a dot (".")
        Speed speed = new Speed(50, KM_PER_HOUR);
        Duration duration = new Duration(0.5, HOUR);
        System.out.println("speed is " + speed); // prints 50.000km/h
        System.out.println("duration is " + duration); // prints 0.500h
        Length distance = speed.times(duration);
        System.out.println("distance is " + distance); // prints 2.500e+04m
        Length finish = new Length(100, KILOMETER);
        Duration timeToFinish = finish.divide(speed);
        System.out.println("at speed " + speed + " it will take " + timeToFinish + " to travel " + finish);
        Speed requiredSpeed = finish.divide(duration);
        System.out.println("speed required to reach finish at " + finish + " in " + duration + " is "
                + requiredSpeed.toString(KM_PER_HOUR));
        Speed speed1 = new Speed(1.2, SpeedUnit.SI);
        Speed speed2 = speed1.times(2.0);
        System.out.println("speed times 2 = " + speed2);
        Speed speed3 = speed1.times(3.0);
        System.out.println("speed times 3 = " + speed3);
        double[] sv = new double[] {1, 2, 3, 4, 5};
        SpeedVector speedVector = DoubleVector.instantiate(sv, SpeedUnit.SI, StorageType.DENSE);
        System.out.println("speed vector = " + speedVector);
    }

}
