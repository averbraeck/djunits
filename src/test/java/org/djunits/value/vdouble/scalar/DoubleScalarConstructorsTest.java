package org.djunits.value.vdouble.scalar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.djunits.quantity.Quantities;
import org.djunits.quantity.Quantity;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarInterface;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.junit.Test;

/**
 * Test the scalar constructors.
 */
public class DoubleScalarConstructorsTest implements UNITS
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
            String scalarClassName = "org.djunits.value.vdouble.scalar." + className;
            Class<?> scalarClass = Class.forName(scalarClassName);
            Unit<?> standardUnit = quantity.getStandardUnit();
            Constructor<?> constructor = scalarClass.getConstructor(double.class, standardUnit.getClass());
            double testValue = 123.456d;
            Object[] args = new Object[] { testValue, standardUnit };
            DoubleScalarInterface<?, ?> doubleScalar = (DoubleScalarInterface<?, ?>) constructor.newInstance(args);
            // system.out.println(doubleScalar);
            assertEquals("Value must match", testValue, doubleScalar.getSI(), 0.1);
            assertEquals("Unit must match", standardUnit, doubleScalar.getDisplayUnit());
            constructor = scalarClass.getConstructor(doubleScalar.getClass());
            args = new Object[] { doubleScalar };
            DoubleScalarInterface<?, ?> secondaryDoubleScalar = (DoubleScalarInterface<?, ?>) constructor.newInstance(args);
            assertEquals("Value must match", testValue, secondaryDoubleScalar.getSI(), 0.1);
            assertEquals("Unit must match", standardUnit, secondaryDoubleScalar.getDisplayUnit());
        }
    }

    /**
     * Test that the constructors throw the expected exceptions.
     */
    @Test
    public void constructorExceptionsTest()
    {
        try
        {
            DoubleScalar.instantiateAnonymous(123.456, BadUnitClass.SI);
        }
        catch (UnitRuntimeException ure)
        {
            // Ignore expected exception
        }

        try
        {
            FloatScalar.instantiateAnonymous(123.456f, BadUnitClass.SI);
        }
        catch (UnitRuntimeException ure)
        {
            // Ignore expected exception
        }
    }

}

/**
 * Unit class with name that does not end on "Unit".
 */
class BadUnitClass extends Unit<BadUnitClass>
{

    /** ... */
    private static final long serialVersionUID = 1L;

    /** The base, with "m/s2" as the SI signature. */
    public static final Quantity<BadUnitClass> BASE = new Quantity<>("m/s2", "m/s2");

    /** The SI unit for acceleration is m/s^2. */
    public static final BadUnitClass SI = new BadUnitClass()
            .build(new Unit.Builder<BadUnitClass>().setQuantity(BASE).setId("m/s2").setName("meter per second squared")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE));
}
