package org.djunits.value;

import static org.junit.Assert.assertEquals;

import org.djunits.unit.AngleUnit;
import org.djunits.value.util.AngleUtil;
import org.djunits.value.vdouble.scalar.Angle;
import org.djunits.value.vfloat.scalar.FloatAngle;
import org.junit.Test;

/**
 * Test the AngleUtil class.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
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
        double[] testValues = { 0, 1, -1, -Math.ulp(0), 0 + Math.ulp(0), Math.PI, 2 * Math.PI + 0.0001, 2 * Math.PI - 0.0001,
                10 * Math.PI + 0.0001, -Math.PI, -2 * Math.PI + 0.0001, -2 * Math.PI - 0.0001 };
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
            assertEquals("normalized angle", expected, AngleUtil.normalize(testValue), 0.001);
            assertEquals("float normalized angle", (float) expected, AngleUtil.normalize((float) testValue), 0.001f);
            assertEquals("normalized Angle", expected, AngleUtil.normalize(new Angle(testValue, AngleUnit.SI)).si, 0.001);
            assertEquals("normalized FloatAngle", expected, AngleUtil.normalize(new FloatAngle(testValue, AngleUnit.SI)).si,
                    0.001);
        }
    }
}
