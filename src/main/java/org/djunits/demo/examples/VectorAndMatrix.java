package org.djunits.demo.examples;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.LengthMatrix;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.vector.AbsoluteTemperatureVector;
import org.djunits.value.vdouble.vector.DurationVector;
import org.djunits.value.vdouble.vector.TemperatureVector;
import org.djunits.value.vdouble.vector.TimeVector;
import org.djunits.value.vdouble.vector.base.DoubleVector;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class VectorAndMatrix
{

    /** */
    private VectorAndMatrix()
    {
        //
    }

    /**
     * @param args String[]; args
     * @throws ValueRuntimeException on error
     */
    public static void main(final String[] args) throws ValueRuntimeException
    {
        double[] doubleValues = new double[] {0.2, 10.0, 5.7, -100.0, 15.0};
        TimeVector dva = DoubleVector.instantiate(doubleValues, TimeUnit.EPOCH_MINUTE, StorageType.DENSE);
        System.out.println(dva);

        double[] doubleValues2 = new double[] {10, 20.0, 60, 120.0, 300.0};
        DurationVector dvr = DoubleVector.instantiate(doubleValues2, DurationUnit.SECOND, StorageType.DENSE);
        System.out.println(dvr);

        TimeVector dva2 = dva.plus(dvr);
        System.out.println(dva2);
        TimeVector dva3 = dva.minus(dvr);
        System.out.println(dva3);
        TimeVector dva4 = dvr.plus(dva);
        System.out.println(dva4);

        double[] tempValues = new double[] {0.0, -17.77778, -273.15, 100.0};
        AbsoluteTemperatureVector tva =
                DoubleVector.instantiate(tempValues, AbsoluteTemperatureUnit.DEGREE_CELSIUS, StorageType.DENSE);
        System.out.println(tva);

        double[] tempValues2 = new double[] {32.0, 32.0, 459.67 + 32, 212.0 - 32.0};
        TemperatureVector tvr = DoubleVector.instantiate(tempValues2, TemperatureUnit.DEGREE_FAHRENHEIT, StorageType.DENSE);
        System.out.println(tvr);

        AbsoluteTemperatureVector tva2 = tva.plus(tvr);
        System.out.println(tva2);
        AbsoluteTemperatureVector tva3 = tva.minus(tvr);
        System.out.println(tva3);
        // XXX DoubleVector.Abs<TimeUnit, DurationUnit> dva4 = dvr.plus(dva);
        System.out.println();

        DurationVector dv =
                DoubleVector.instantiate(new double[] {1.0, 2.0, 5.0, 10.0}, DurationUnit.MINUTE, StorageType.DENSE);
        Duration d = dv.get(2);
        System.out.println(d);

        double[][] data = new double[1000][1000];
        for (int i = 0; i < 1000; i++)
        {
            for (int j = 0; j < 1000; j++)
            {
                data[i][j] = 9 * i + 2 * j * 0.364;
            }
        }
        LengthMatrix lengthMatrix = new LengthMatrix(
                DoubleMatrixData.instantiate(data, IdentityScale.SCALE, StorageType.DENSE), LengthUnit.CENTIMETER);
    }

}
