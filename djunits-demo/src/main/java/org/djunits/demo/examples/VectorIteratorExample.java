package org.djunits.demo.examples;

import java.util.SortedMap;
import java.util.TreeMap;

import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.vector.SpeedVector;
import org.djunits.value.vdouble.vector.TimeVector;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vfloat.scalar.FloatSpeed;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djunits.value.vfloat.vector.FloatSpeedVector;
import org.djunits.value.vfloat.vector.FloatTimeVector;
import org.djunits.value.vfloat.vector.base.FloatVector;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class VectorIteratorExample
{
    /** Utility constructor. */
    private VectorIteratorExample()
    {
        //
    }

    /**
     * @param args String[]; the arguments for the main program, not used
     * @throws ValueRuntimeException on vector error
     */
    public static void main(final String[] args) throws ValueRuntimeException
    {
        showDoubleIterator();
        showFloatIterator();
    }

    /**
     * double iterator example.
     * @throws ValueRuntimeException on error
     */
    private static void showDoubleIterator() throws ValueRuntimeException
    {
        Speed s1 = new Speed(10.0, SpeedUnit.METER_PER_SECOND);
        Speed s2 = new Speed(12.0, SpeedUnit.METER_PER_SECOND);
        Speed s3 = new Speed(8.0, SpeedUnit.METER_PER_SECOND);
        Speed s4 = new Speed(16.0, SpeedUnit.METER_PER_SECOND);
        SpeedVector svd = DoubleVector.instantiate(new Speed[] {s1, s2, s3, s4}, SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        System.out.println("Speed vector (Double, Rel, Dense)");
        for (Speed s : svd.getScalars())
        {
            System.out.println(s);
        }

        SortedMap<Integer, Speed> svsMap = new TreeMap<>();
        svsMap.put(1, s1);
        svsMap.put(3, s2);
        svsMap.put(5, s3);
        svsMap.put(7, s4);
        SpeedVector svs = DoubleVector.instantiateMap(svsMap, 10, SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        System.out.println("\nSpeed vector (Double, Rel, Sparse)");
        for (Speed s : svs.getScalars())
        {
            System.out.println(s);
        }

        Time t1 = new Time(10.0, TimeUnit.BASE_SECOND);
        Time t2 = new Time(12.0, TimeUnit.BASE_SECOND);
        Time t3 = new Time(8.0, TimeUnit.BASE_SECOND);
        Time t4 = new Time(16.0, TimeUnit.BASE_SECOND);
        TimeVector tvd = DoubleVector.instantiate(new Time[] {t1, t2, t3, t4}, TimeUnit.BASE_SECOND, StorageType.DENSE);
        System.out.println("\nTime vector (Double, Abs, Dense)");
        for (Time t : tvd.getScalars())
        {
            System.out.println(t);
        }

        SortedMap<Integer, Time> tvsMap = new TreeMap<>();
        tvsMap.put(1, t1);
        tvsMap.put(3, t2);
        tvsMap.put(5, t3);
        tvsMap.put(7, t4);
        TimeVector tvs = DoubleVector.instantiateMap(tvsMap, 10, TimeUnit.BASE_SECOND, StorageType.SPARSE);
        System.out.println("\nTime vector (Double, Abs, Sparse)");
        for (Time t : tvs.getScalars())
        {
            System.out.println(t);
        }
    }

    /**
     * float iterator example.
     * @throws ValueRuntimeException on error
     */
    private static void showFloatIterator() throws ValueRuntimeException
    {
        FloatSpeed s1 = new FloatSpeed(10.0, SpeedUnit.METER_PER_SECOND);
        FloatSpeed s2 = new FloatSpeed(12.0, SpeedUnit.METER_PER_SECOND);
        FloatSpeed s3 = new FloatSpeed(8.0, SpeedUnit.METER_PER_SECOND);
        FloatSpeed s4 = new FloatSpeed(16.0, SpeedUnit.METER_PER_SECOND);
        FloatSpeedVector svd =
                FloatVector.instantiate(new FloatSpeed[] {s1, s2, s3, s4}, SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        System.out.println("\nFloatSpeed vector (Float, Rel, Dense)");
        for (FloatSpeed s : svd.getScalars())
        {
            System.out.println(s);
        }

        SortedMap<Integer, FloatSpeed> svsMap = new TreeMap<>();
        svsMap.put(1, s1);
        svsMap.put(3, s2);
        svsMap.put(5, s3);
        svsMap.put(7, s4);
        FloatSpeedVector svs = FloatVector.instantiateMap(svsMap, 10, SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        System.out.println("\nSpeed vector (Float, Rel, Sparse)");
        for (FloatSpeed s : svs.getScalars())
        {
            System.out.println(s);
        }

        FloatTime t1 = new FloatTime(10.0f, TimeUnit.BASE_SECOND);
        FloatTime t2 = new FloatTime(12.0f, TimeUnit.BASE_SECOND);
        FloatTime t3 = new FloatTime(8.0f, TimeUnit.BASE_SECOND);
        FloatTime t4 = new FloatTime(16.0f, TimeUnit.BASE_SECOND);
        FloatTimeVector tvd =
                FloatVector.instantiate(new FloatTime[] {t1, t2, t3, t4}, TimeUnit.BASE_SECOND, StorageType.DENSE);
        System.out.println("\nFloatTime vector (Float, Abs, Dense)");
        for (FloatTime t : tvd.getScalars())
        {
            System.out.println(t);
        }

        SortedMap<Integer, FloatTime> tvsMap = new TreeMap<>();
        tvsMap.put(1, t1);
        tvsMap.put(3, t2);
        tvsMap.put(5, t3);
        tvsMap.put(7, t4);
        FloatTimeVector tvs = FloatVector.instantiateMap(tvsMap, 10, TimeUnit.BASE_SECOND, StorageType.SPARSE);
        System.out.println("\nFloatTime vector (Float, Abs, Sparse)");
        for (FloatTime t : tvs.getScalars())
        {
            System.out.println(t);
        }

    }

}
