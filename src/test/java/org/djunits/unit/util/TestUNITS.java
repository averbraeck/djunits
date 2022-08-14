package org.djunits.unit.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.junit.Test;

/**
 * TestUNITS.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class TestUNITS
{
    /**
     * Test the UNITS class for completeness and consistency.
     * @throws IllegalAccessException on error
     * @throws IllegalArgumentException on error
     * @throws SecurityException on error
     * @throws NoSuchFieldException on error
     */
    @Test
    public void testUNITS() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
    {
        // See if we can calculate a value for each of the constants
        for (Field constantField : UNITS.class.getDeclaredFields())
        {
            if (constantField.getName().equals("UNIT"))
            {
                continue;
            }
            Unit<?> constant = (Unit<?>) constantField.get(null);
            if (constant == null)
            {
                fail("constant for field " + constantField.getName() + " is null");
            }
            assertEquals("types of fields differ for " + constantField.getName(), constant.getClass(), constantField.getType());
            if (constant.getId().contains("eV"))
            {
                continue;
            }
            if (constant.getQuantity().getStandardUnit().getName().equals("Kelvin")
                    && constant instanceof AbsoluteLinearUnit<?, ?>)
            {
                continue;
            }
            if (constant.getQuantity().getStandardUnit().getName().equals("meter")
                    && constant instanceof AbsoluteLinearUnit<?, ?>)
            {
                continue;
            }
            if (constant.getQuantity().getStandardUnit().getName().equals("second")
                    && constant instanceof AbsoluteLinearUnit<?, ?>)
            {
                continue;
            }
            // find the field with this name in the declaring class
            Field unitField = constant.getClass().getDeclaredField(constantField.getName());
            Unit<?> unitConstant = (Unit<?>) unitField.get(constant.getClass());
            // see if this is the same field with the same content
            assertEquals("fields differ for " + constantField.getName(), constant, unitConstant);
        }
    }
}
