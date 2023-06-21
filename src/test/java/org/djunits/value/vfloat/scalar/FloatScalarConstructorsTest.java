package org.djunits.value.vfloat.scalar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.djunits.quantity.Quantities;
import org.djunits.quantity.Quantity;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.util.UNITS;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.junit.Test;

/**
 * Test the scalar constructors.
 */
public class FloatScalarConstructorsTest implements UNITS
{

    /**
     * Test all constructors.
     * @throws ClassNotFoundException when that happens uncaught this test has failed
     * @throws SecurityException when that happens uncaught this test has failed
     * @throws NoSuchMethodException when that happens uncaught this test has failed
     * @throws InvocationTargetException when that happens uncaught this test has failed
     * @throws IllegalArgumentException when that happens uncaught this test has failed
     * @throws IllegalAccessException when that happens uncaught this test has failed
     * @throws InstantiationException when that happens uncaught this test has failed
     */
    @Test
    public void constructorsTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String className : CLASSNAMES.ALL_NODIM_LIST)
        {
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(className + "Unit");
            String scalarClassName = "org.djunits.value.vfloat.scalar.Float" + className;
            Class<?> scalarClass = Class.forName(scalarClassName);
            Unit<?> standardUnit = quantity.getStandardUnit();
            Constructor<?> constructor = scalarClass.getConstructor(float.class, standardUnit.getClass());
            float testValue = 123.456f;
            Object[] args = new Object[] { testValue, standardUnit };
            FloatScalar<?, ?> floatScalar = (FloatScalar<?, ?>) constructor.newInstance(args);
            // system.out.println(floatScalar);
            assertEquals("Value must match", testValue, floatScalar.getSI(), 0.1);
            assertEquals("Unit must match", standardUnit, floatScalar.getDisplayUnit());
            constructor = scalarClass.getConstructor(floatScalar.getClass());
            args = new Object[] { floatScalar };
            FloatScalar<?, ?> secondaryFloatScalar = (FloatScalar<?, ?>) constructor.newInstance(args);
            assertEquals("Value must match", testValue, secondaryFloatScalar.getSI(), 0.1);
            assertEquals("Unit must match", standardUnit, secondaryFloatScalar.getDisplayUnit());
        }
    }
}
