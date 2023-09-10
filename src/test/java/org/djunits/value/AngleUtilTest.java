package org.djunits.value;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.unit.AngleUnit;
import org.djunits.value.util.AngleUtil;
import org.djunits.value.vdouble.scalar.Angle;
import org.djunits.value.vfloat.scalar.FloatAngle;
import org.junit.jupiter.api.Test;

/**
 * Test the AngleUtil class.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class AngleUtilTest
{
    /**
     * Test the two functions in the AngleUtil class.
     */
    @Test
    public void angleUtilTest()
    {
        double[] testValues = {0, 1, -1, -Math.ulp(0), 0 + Math.ulp(0), Math.PI, 2 * Math.PI + 0.0001, 2 * Math.PI - 0.0001,
                10 * Math.PI + 0.0001, -Math.PI, -2 * Math.PI + 0.0001, -2 * Math.PI - 0.0001};
        for (double testValue : testValues)
        {
            double expected = testValue;
            while (expected >= 2 * Math.PI)
            {
                expected -= 2 * Math.PI;
            }
            while (expected < 0)
            {
                expected += 2 * Math.PI;
            }
            assertEquals(expected, AngleUtil.normalize(testValue), 0.001, "normalized angle");
            assertEquals((float) expected, AngleUtil.normalize((float) testValue), 0.001f, "float normalized angle");
            assertEquals(expected, AngleUtil.normalize(new Angle(testValue, AngleUnit.SI)).si, 0.001, "normalized Angle");
            assertEquals(expected, AngleUtil.normalize(new FloatAngle(testValue, AngleUnit.SI)).si, 0.001,
                    "normalized FloatAngle");
        }
    }
}
