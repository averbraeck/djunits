package org.djunits.old.value.vfloat.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.djunits.old.quantity.Quantities;
import org.djunits.old.quantity.Quantity;
import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.Unit;
import org.djunits.old.unit.util.UNITS;
import org.djunits.old.value.CLASSNAMES;
import org.djunits.old.value.vfloat.scalar.base.FloatScalar;
import org.junit.jupiter.api.Test;

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
            Object[] args = new Object[] {testValue, standardUnit};
            FloatScalar<?, ?> floatScalar = (FloatScalar<?, ?>) constructor.newInstance(args);
            // system.out.println(floatScalar);
            assertEquals(testValue, floatScalar.getSI(), 0.1, "Value must match");
            assertEquals(standardUnit, floatScalar.getDisplayUnit(), "Unit must match");
            constructor = scalarClass.getConstructor(floatScalar.getClass());
            args = new Object[] {floatScalar};
            FloatScalar<?, ?> secondaryFloatScalar = (FloatScalar<?, ?>) constructor.newInstance(args);
            assertEquals(testValue, secondaryFloatScalar.getSI(), 0.1, "Value must match");
            assertEquals(standardUnit, secondaryFloatScalar.getDisplayUnit(), "Unit must match");
        }
    }
}
