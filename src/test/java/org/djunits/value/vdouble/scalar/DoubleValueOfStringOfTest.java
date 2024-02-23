package org.djunits.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.Unit;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.base.Scalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.junit.jupiter.api.Test;

/**
 * Test the DoubleScalar and FloatScalar classes for the valueOf and toString methods.
 * <p>
 * This file was generated by the djunits value test classes generator, 26 jun, 2015
 * <p>
 * Copyright (c) 2015-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class DoubleValueOfStringOfTest
{
    /**
     * Test the Duration class for the valueOf and toString methods.
     */
    @Test
    public final void durationValueOfTest()
    {
        Duration duration = new Duration(10.0, DurationUnit.MINUTE);
        assertEquals("10.0000000 min", duration.toString());
        assertEquals(duration, Duration.valueOf(duration.toString()));
    }

    /**
     * Test the DoubleScalar and FloatScalar classes for the valueOf and toString methods.
     */
    @Test
    public final void valueOfDoubleTest()
    {
        for (String className : CLASSNAMES.ALL_NODIM_LIST)
        {
            // get the class
            Class<?> scalarClass = null;
            String classPath = "org.djunits.value.vdouble.scalar." + className;
            try
            {
                scalarClass = Class.forName(classPath);
            }
            catch (ClassNotFoundException exception)
            {
                fail("Class not found for Scalar class " + classPath);
            }

            // create a value so we can obtain info
            Method instantiateSIMethod = null;
            try
            {
                instantiateSIMethod = scalarClass.getMethod("instantiateSI", double.class);
            }
            catch (NoSuchMethodException | SecurityException exception)
            {
                fail("Method instantiateSI not found for Scalar class " + classPath);
            }
            Scalar<?, ?> scalarSI = null;
            try
            {
                scalarSI = (Scalar<?, ?>) instantiateSIMethod.invoke(scalarClass, Double.valueOf(10.0));
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception)
            {
                fail("Method instantiateSI failed for Scalar class " + classPath);
            }

            // get the unit
            Unit<?> unitSI = scalarSI.getDisplayUnit();

            // get the constructor
            Constructor<?> constructScalar = null;
            try
            {
                constructScalar = scalarClass.getConstructor(double.class, unitSI.getClass());
            }
            catch (NoSuchMethodException | SecurityException exception)
            {
                fail("Constructor for unit " + unitSI.getClass().getName() + " not found for Scalar class " + classPath);
            }

            // loop over all the unit types
            for (Unit<?> unit : unitSI.getQuantity().getUnitsById().values())
            {
                DoubleScalar<?, ?> scalar = null;
                try
                {
                    scalar = (DoubleScalar<?, ?>) constructScalar.newInstance(Double.valueOf(1.0), unit);
                    assertEquals(1.0, scalar.getInUnit(), 0.01);
                }
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                        | InstantiationException exception)
                {
                    fail("Construct with unit " + unit.getClass().getName() + " failed for Scalar class " + classPath);
                }
            }
        }
    }
}
