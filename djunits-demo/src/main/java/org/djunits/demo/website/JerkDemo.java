package org.djunits.demo.website;

import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.vector.base.DoubleVector;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class JerkDemo
{
    /** */
    private JerkDemo()
    {
        // utility class
    }

    /**
     * @param args String[]; args
     * @throws ValueRuntimeException on error
     */
    public static void main(final String[] args) throws ValueRuntimeException
    {
        Jerk jerk1 = new Jerk(1.2, JerkUnit.SI);
        System.out.println("jerk1 = Jerk(1.2, JerkUnit.SI)       : " + jerk1);
        Jerk jerk2 = jerk1.times(2.0);
        System.out.println("jerk2 = jerk1.multiplyBy(2.0)        : " + jerk2);
        Jerk jerk3 = new Jerk(4.0, JerkUnit.IN_PER_S3);
        System.out.println("jerk3 = Jerk(4.0, JerkUnit.IN_PER_S3 : " + jerk3);
        System.out.println("jerk3 expressed in JerkUnit.SI       : " + jerk3.toString(JerkUnit.SI));
        System.out.println("jerk3 expressed in JerkUnit.FT_PER_S3: " + jerk3.toString(JerkUnit.FT_PER_S3));

        System.out.println();

        double[] sv = new double[] {1, 2, 3, 4, 5};
        JerkVector jerkVector = DoubleVector.instantiate(sv, JerkUnit.SI, StorageType.DENSE, JerkVector.class);
        System.out.println("jerkVector: " + jerkVector);
        // FIXME why can't we multiply a JerkVector by a scalar Duration and get an AccelerationVector

        double[][] data = new double[1000][1000];
        for (int i = 0; i < 1000; i++)
        {
            for (int j = 0; j < 1000; j++)
            {
                data[i][j] = 9 * i + 2 * j * 0.364;
            }
        }
        // XXX Is this supposed to fill a JerkMatrix???
    }

}
