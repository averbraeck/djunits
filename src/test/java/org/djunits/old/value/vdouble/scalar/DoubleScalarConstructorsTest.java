package org.djunits.old.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.djunits.old.quantity.Quantities;
import org.djunits.old.quantity.Quantity;
import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.Unit;
import org.djunits.old.unit.scale.IdentityScale;
import org.djunits.old.unit.si.SIPrefixes;
import org.djunits.old.unit.unitsystem.UnitSystem;
import org.djunits.old.unit.util.UNITS;
import org.djunits.old.unit.util.UnitRuntimeException;
import org.djunits.old.value.CLASSNAMES;
import org.djunits.old.value.vdouble.scalar.SIScalar;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.old.value.vfloat.scalar.FloatSIScalar;
import org.junit.jupiter.api.Test;

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
            Object[] args = new Object[] {testValue, standardUnit};
            DoubleScalar<?, ?> doubleScalar = (DoubleScalar<?, ?>) constructor.newInstance(args);
            // system.out.println(doubleScalar);
            assertEquals(testValue, doubleScalar.getSI(), 0.1, "Value must match");
            assertEquals(standardUnit, doubleScalar.getDisplayUnit(), "Unit must match");
            constructor = scalarClass.getConstructor(doubleScalar.getClass());
            args = new Object[] {doubleScalar};
            DoubleScalar<?, ?> secondaryDoubleScalar = (DoubleScalar<?, ?>) constructor.newInstance(args);
            assertEquals(testValue, secondaryDoubleScalar.getSI(), 0.1, "Value must match");
            assertEquals(standardUnit, secondaryDoubleScalar.getDisplayUnit(), "Unit must match");
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
            SIScalar.instantiateAnonymous(123.456, BadUnitClass.SI);
        }
        catch (UnitRuntimeException ure)
        {
            // Ignore expected exception
        }

        try
        {
            FloatSIScalar.instantiateAnonymous(123.456f, BadUnitClass.SI);
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
