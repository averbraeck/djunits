package org.djunits.demo.examples;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.Dimensionless;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.vector.DimensionlessVector;
import org.djunits.value.vdouble.vector.DurationVector;
import org.djunits.value.vdouble.vector.SpeedVector;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vfloat.scalar.FloatAcceleration;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.vector.FloatAccelerationVector;
import org.djunits.value.vfloat.vector.FloatDirectionVector;
import org.djunits.value.vfloat.vector.base.FloatVector;

/**
 * Tests for min and max.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class MinAndMax
{
    /** Utility constructor. */
    private MinAndMax()
    {
        //
    }

    /**
     * @param args String[]; the arguments for the main program, not used
     * @throws ValueRuntimeException on vector error
     */
    public static void main(final String[] args) throws ValueRuntimeException
    {
        Speed s1 = new Speed(10.0, SpeedUnit.METER_PER_SECOND);
        Speed s2 = new Speed(12.0, SpeedUnit.METER_PER_SECOND);
        Speed s3 = new Speed(8.0, SpeedUnit.METER_PER_SECOND);
        Speed s4 = new Speed(16.0, SpeedUnit.METER_PER_SECOND);
        SpeedVector sv = DoubleVector.instantiate(new Speed[] {s1, s2, s3, s4}, SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        System.out.println("min of " + s1 + " and " + s2 + " = " + Speed.min(s1, s2));
        System.out.println("max of " + s1 + " and " + s2 + " = " + Speed.max(s1, s2));
        System.out.println("min of " + sv + " = " + Speed.min(s1, s2, s3, s4));
        System.out.println("max of " + sv + " = " + Speed.max(s1, s2, s3, s4));
        System.out.println();

        FloatAcceleration fa1 = new FloatAcceleration(10.0, AccelerationUnit.METER_PER_SECOND_2);
        FloatAcceleration fa2 = new FloatAcceleration(12.0, AccelerationUnit.METER_PER_SECOND_2);
        FloatAcceleration fa3 = new FloatAcceleration(8.0, AccelerationUnit.METER_PER_SECOND_2);
        FloatAcceleration fa4 = new FloatAcceleration(16.0, AccelerationUnit.METER_PER_SECOND_2);
        FloatAccelerationVector fav = FloatVector.instantiate(new FloatAcceleration[] {fa1, fa2, fa3, fa4},
                AccelerationUnit.METER_PER_SECOND_2, StorageType.DENSE);
        System.out.println("min of " + fa1 + " and " + fa2 + " = " + FloatAcceleration.min(fa1, fa2));
        System.out.println("max of " + fa1 + " and " + fa2 + " = " + FloatAcceleration.max(fa1, fa2));
        System.out.println("min of " + fav + " = " + FloatAcceleration.min(fa1, fa2, fa3, fa4));
        System.out.println("max of " + fav + " = " + FloatAcceleration.max(fa1, fa2, fa3, fa4));
        System.out.println();

        Duration t1 = new Duration(10.0, DurationUnit.MINUTE);
        Duration t2 = new Duration(12.0, DurationUnit.MINUTE);
        Duration t3 = new Duration(8.0, DurationUnit.MINUTE);
        Duration t4 = new Duration(16.0, DurationUnit.MINUTE);
        DurationVector tv = DoubleVector.instantiate(new Duration[] {t1, t2, t3, t4}, DurationUnit.MINUTE, StorageType.DENSE);
        System.out.println("min of " + t1 + " and " + t2 + " = " + Duration.min(t1, t2));
        System.out.println("max of " + t1 + " and " + t2 + " = " + Duration.max(t1, t2));
        System.out.println("min of " + tv + " = " + Duration.min(t1, t2, t3, t4));
        System.out.println("max of " + tv + " = " + Duration.max(t1, t2, t3, t4));
        System.out.println();

        FloatDirection fd1 = new FloatDirection(10.0, DirectionUnit.NORTH_DEGREE);
        FloatDirection fd2 = new FloatDirection(12.0, DirectionUnit.NORTH_DEGREE);
        FloatDirection fd3 = new FloatDirection(8.0, DirectionUnit.NORTH_DEGREE);
        FloatDirection fd4 = new FloatDirection(16.0, DirectionUnit.NORTH_DEGREE);
        FloatDirectionVector fdv = FloatVector.instantiate(new FloatDirection[] {fd1, fd2, fd3, fd4},
                DirectionUnit.NORTH_DEGREE, StorageType.DENSE);
        System.out.println("min of " + fd1 + " and " + fd2 + " = " + FloatDirection.min(fd1, fd2));
        System.out.println("max of " + fd1 + " and " + fd2 + " = " + FloatDirection.max(fd1, fd2));
        System.out.println("min of " + fdv + " = " + FloatDirection.min(fd1, fd2, fd3, fd4));
        System.out.println("max of " + fdv + " = " + FloatDirection.max(fd1, fd2, fd3, fd4));
        System.out.println();

        Dimensionless d1 = new Dimensionless(10.0, DimensionlessUnit.SI);
        Dimensionless d2 = new Dimensionless(12.0, DimensionlessUnit.SI);
        Dimensionless d3 = new Dimensionless(8.0, DimensionlessUnit.SI);
        Dimensionless d4 = new Dimensionless(16.0, DimensionlessUnit.SI);
        DimensionlessVector dv =
                DoubleVector.instantiate(new Dimensionless[] {d1, d2, d3, d4}, DimensionlessUnit.SI, StorageType.DENSE);
        System.out.println("min of " + d1 + " and " + d2 + " = " + Dimensionless.min(d1, d2));
        System.out.println("max of " + d1 + " and " + d2 + " = " + Dimensionless.max(d1, d2));
        System.out.println("min of " + dv + " = " + Dimensionless.min(d1, d2, d3, d4));
        System.out.println("max of " + dv + " = " + Dimensionless.max(d1, d2, d3, d4));
        System.out.println();
    }

}
