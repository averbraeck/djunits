package org.djunits.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

import org.djunits.quantity.Quantity;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.base.Scalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.junit.jupiter.api.Test;

/**
 * ValueOfTest.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 */
public class DoubleValueOfTest
{
    /**
     * Test legal "of" arguments.
     * @param scalarClass the scalar class
     * @param value the value to enter
     * @param unitString the unit String to use
     */
    private void legal(final Class<? extends DoubleScalar<?, ?>> scalarClass, final double value, final String unitString)
    {
        try
        {
            Method ofMethod = scalarClass.getDeclaredMethod("of", double.class, String.class);
            ofMethod.invoke(null, value, unitString);
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test legal "valueOf" arguments.
     * @param scalarClass the scalar class
     * @param text the text to parse
     */
    private void legal(final Class<? extends DoubleScalar<?, ?>> scalarClass, final String text)
    {
        try
        {
            Method valueOfMethod = scalarClass.getDeclaredMethod("valueOf", String.class);
            valueOfMethod.invoke(null, text);
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test illegal "of" arguments.
     * @param scalarClass the scalar class
     * @param value the value to enter
     * @param unitString the unit String to use
     */
    private void illegal(final Class<? extends DoubleScalar<?, ?>> scalarClass, final double value, final String unitString)
    {
        try
        {
            Method ofMethod = scalarClass.getDeclaredMethod("of", double.class, String.class);
            ofMethod.invoke(null, value, unitString);
            fail("Should not be able to create " + scalarClass.getSimpleName() + " with unit " + unitString);
        }
        catch (InvocationTargetException exception)
        {
            // ok
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test illegal "valueOf" arguments.
     * @param scalarClass the scalar class
     * @param text the text to parse
     */
    private void illegal(final Class<? extends DoubleScalar<?, ?>> scalarClass, final String text)
    {
        try
        {
            Method valueOfMethod = scalarClass.getDeclaredMethod("valueOf", String.class);
            valueOfMethod.invoke(null, text);
            fail("Should not be able to create " + scalarClass.getSimpleName() + " with text " + text);
        }
        catch (InvocationTargetException exception)
        {
            // ok
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * test the valueOf and the of methods.
     * @throws UnitException on error
     */
    @Test
    public void testSIScalar() throws UnitException
    {
        Locale.setDefault(Locale.US);
        SIUnit m2 = SIUnit.of(new SIDimensions(0, 0, 0, 2, 0, 0, 0, 0, 0));
        assertEquals(SIScalar.ofSI(10.0, m2), SIScalar.of(10.0, "m2"));
        assertEquals(SIScalar.ofSI(10.0, m2), SIScalar.of(10.0, "m^2"));
        assertEquals(SIScalar.ofSI(10.0, m2), SIScalar.valueOf("10.0 m2"));
        // test if the . in 10.0 is not removed...
        assertEquals(SIScalar.ofSI(10.0, m2), SIScalar.valueOf("10.0 m^2"));

        legal(SIScalar.class, 10.0, "");
        assertEquals(SIScalar.ofSI(10.0, SIUnit.DIMLESS), SIScalar.valueOf("10.0"));

        legal(SIScalar.class, 10.0, "m2");
        legal(SIScalar.class, "10.0 m2");
        illegal(SIScalar.class, 10.0, null);
        illegal(SIScalar.class, null);
        illegal(SIScalar.class, "");
        // illegal(SIScalar.class, "1.0.0 m2"); // formatter.parse() does not use the whole number string
        illegal(SIScalar.class, 10.0, "xyz");
        illegal(SIScalar.class, "10.0 xyz");
        try
        {
            SIScalar.valueOf("10.0 xyzuwv");
            fail("valueOf of nonexistent unit should have thrown an IllegalArgumentException");
        }
        catch (IllegalArgumentException iae)
        {
            // Ignore expected exception
        }

        try
        {
            SIScalar.valueOf("xyzuvw");
            fail("valueOf of empty string should have thrown an IllegalArgumentException");
        }
        catch (IllegalArgumentException iae)
        {
            // Ignore expected exception
        }
    }

    /** test the valueOf and the of methods. */
    @Test
    public void testArea()
    {
        Locale.setDefault(Locale.US);
        assertEquals(Area.ofSI(10.0), Area.of(10.0, "m2"));
        assertEquals(Area.ofSI(10.0), Area.of(10.0, "m^2"));
        assertEquals(Area.ofSI(10.0), Area.valueOf("10.0 m2"));
        assertEquals(Area.ofSI(10.0), Area.valueOf("10.0 m^2")); // test if the . in 10.0 is not removed...
        assertEquals(new Area(10.0, AreaUnit.HECTARE), Area.of(10.0, "ha"));
        assertEquals(new Area(10.0, AreaUnit.HECTARE), Area.valueOf("10.0 ha"));

        legal(Area.class, 10.0, "m2");
        legal(Area.class, "10.0 m2");
        illegal(Area.class, 10.0, null);
        illegal(Area.class, 10.0, "");
        illegal(Area.class, null);
        illegal(Area.class, "");
        illegal(Area.class, "1.0.0 m2"); // formatter.pasrse() does not use the whole number string
        illegal(Area.class, 10.0, "xyz");
        illegal(Area.class, "10.0 xyz");
        illegal(Area.class, 10.0, "m5s3");
        illegal(Area.class, "10.0 m5s3");
    }

    /** test the valueOf and the of methods of all units with US locale. */
    @SuppressWarnings("unchecked")
    @Test
    public void testValueOfUS()
    {
        Locale.setDefault(Locale.US);
        // get the interfaces such as org.djunits.value.vdouble.scalar.Time
        for (String scalarName : CLASSNAMES.ALL_LIST)
        {
            String scalarClassName = scalarName;
            Class<? extends DoubleScalar<?, ?>> scalarClass = null;
            // get the implementation of that class
            try
            {
                scalarClass = (Class<? extends DoubleScalar<?, ?>>) Class
                        .forName("org.djunits.value.vdouble.scalar." + scalarClassName);
            }
            catch (ClassNotFoundException exception)
            {
                fail("Class not found for Scalar class " + "org.djunits.value.vdouble.scalar." + scalarClassName);
            }
            testValueUS(scalarClass);
        }
    }

    /**
     * Test scalar valueOf() and of() functions.
     * @param scalarClass the class to test
     */
    @SuppressWarnings("unchecked")
    private void testValueUS(final Class<? extends DoubleScalar<?, ?>> scalarClass)
    {
        try
        {
            Method insMethod = scalarClass.getDeclaredMethod("ofSI", double.class);
            Scalar<?, ?> scalar = (Scalar<?, ?>) insMethod.invoke(null, 10.0);
            Unit<?> unit = scalar.getDisplayUnit();

            Method qMethod = unit.getClass().getMethod("getQuantity");
            Quantity<?> quantity = (Quantity<?>) qMethod.invoke(unit);
            Map<String, Unit<?>> abbreviations = (Map<String, Unit<?>>) quantity.getUnitsByAbbreviation();
            for (String abb : abbreviations.keySet())
            {
                legal(scalarClass, 10.0, abb);
                legal(scalarClass, -10.0, abb);
                legal(scalarClass, -10.0E-10, abb);
                legal(scalarClass, 10.0E10, abb);

                legal(scalarClass, "10" + abb);
                legal(scalarClass, "10 " + abb);
                legal(scalarClass, "10.0" + abb);
                legal(scalarClass, "10.0 " + abb);
                legal(scalarClass, "-10.0" + abb);
                legal(scalarClass, "-10.0 " + abb);
                legal(scalarClass, "0" + abb);
                legal(scalarClass, "0 " + abb);
                legal(scalarClass, "0.0" + abb);
                legal(scalarClass, "0.0 " + abb);
                legal(scalarClass, "-0.0" + abb);
                legal(scalarClass, "-0.0 " + abb);

                legal(scalarClass, "10e1" + abb);
                legal(scalarClass, "10e1 " + abb);
                legal(scalarClass, "10.0e1" + abb);
                legal(scalarClass, "10.0e1 " + abb);
                legal(scalarClass, "-10.0e1" + abb);
                legal(scalarClass, "-10.0e1 " + abb);
                legal(scalarClass, "0e1" + abb);
                legal(scalarClass, "0e1 " + abb);
                legal(scalarClass, "0.0e1" + abb);
                legal(scalarClass, "0.0e1 " + abb);
                legal(scalarClass, "-0.0e1" + abb);
                legal(scalarClass, "-0.0e1 " + abb);

                legal(scalarClass, "10e-1" + abb);
                legal(scalarClass, "10e-1 " + abb);
                legal(scalarClass, "10.0e-1" + abb);
                legal(scalarClass, "10.0e-1 " + abb);
                legal(scalarClass, "-10.0e-1" + abb);
                legal(scalarClass, "-10.0e-1 " + abb);
                legal(scalarClass, "0e-1" + abb);
                legal(scalarClass, "0e-1 " + abb);
                legal(scalarClass, "0.0e-1" + abb);
                legal(scalarClass, "0.0e-1 " + abb);
                legal(scalarClass, "-0.0e-1" + abb);
                legal(scalarClass, "-0.0e-1 " + abb);

                legal(scalarClass, "10E1" + abb);
                legal(scalarClass, "10E1 " + abb);
                legal(scalarClass, "10.0E1" + abb);
                legal(scalarClass, "10.0E1 " + abb);
                legal(scalarClass, "-10.0E1" + abb);
                legal(scalarClass, "-10.0E1 " + abb);
                legal(scalarClass, "0E1" + abb);
                legal(scalarClass, "0E1 " + abb);
                legal(scalarClass, "0.0E1" + abb);
                legal(scalarClass, "0.0E1 " + abb);
                legal(scalarClass, "-0.0E1" + abb);
                legal(scalarClass, "-0.0E1 " + abb);

                legal(scalarClass, "10E-1" + abb);
                legal(scalarClass, "10E-1 " + abb);
                legal(scalarClass, "10.0E-1" + abb);
                legal(scalarClass, "10.0E-1 " + abb);
                legal(scalarClass, "-10.0E-1" + abb);
                legal(scalarClass, "-10.0E-1 " + abb);
                legal(scalarClass, "0E-1" + abb);
                legal(scalarClass, "0E-1 " + abb);
                legal(scalarClass, "0.0E-1" + abb);
                legal(scalarClass, "0.0E-1 " + abb);
                legal(scalarClass, "-0.0E-1" + abb);
                legal(scalarClass, "-0.0E-1 " + abb);
            }

        }
        catch (InvocationTargetException exception)
        {
            // ok
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }

    }

    /** test the valueOf and the of methods of all units with US locale. */
    @SuppressWarnings("unchecked")
    @Test
    public void testValueOfNL()
    {
        Locale.setDefault(Locale.forLanguageTag("NL"));
        // get the interfaces such as org.djunits.value.vdouble.scalar.Time
        for (String scalarName : CLASSNAMES.ALL_LIST)
        {
            String scalarClassName = scalarName;
            Class<? extends DoubleScalar<?, ?>> scalarClass = null;
            // get the implementation of that class
            try
            {
                scalarClass = (Class<? extends DoubleScalar<?, ?>>) Class
                        .forName("org.djunits.value.vdouble.scalar." + scalarClassName);
            }
            catch (ClassNotFoundException exception)
            {
                fail("Class not found for Scalar class " + "org.djunits.value.vdouble.scalar." + scalarClassName);
            }
            testValueNL(scalarClass);
        }
    }

    /**
     * Test scalar valueOf() and of() functions.
     * @param scalarClass the class to test
     */
    @SuppressWarnings("unchecked")
    private void testValueNL(final Class<? extends DoubleScalar<?, ?>> scalarClass)
    {
        try
        {
            Method insMethod = scalarClass.getDeclaredMethod("ofSI", double.class);
            Scalar<?, ?> scalar = (Scalar<?, ?>) insMethod.invoke(null, 10.0);
            Unit<?> unit = scalar.getDisplayUnit();

            Method qMethod = unit.getClass().getMethod("getQuantity");
            Quantity<?> quantity = (Quantity<?>) qMethod.invoke(unit);
            Map<String, Unit<?>> abbreviations = (Map<String, Unit<?>>) quantity.getUnitsByAbbreviation();
            for (String abb : abbreviations.keySet())
            {
                legal(scalarClass, 10.0, abb);
                legal(scalarClass, -10.0, abb);
                legal(scalarClass, -10.0E-10, abb);
                legal(scalarClass, 10.0E10, abb);

                legal(scalarClass, "10" + abb);
                legal(scalarClass, "10 " + abb);
                legal(scalarClass, "10,0" + abb);
                legal(scalarClass, "10,0 " + abb);
                legal(scalarClass, "-10,0" + abb);
                legal(scalarClass, "-10,0 " + abb);
                legal(scalarClass, "0" + abb);
                legal(scalarClass, "0 " + abb);
                legal(scalarClass, "0,0" + abb);
                legal(scalarClass, "0,0 " + abb);
                legal(scalarClass, "-0,0" + abb);
                legal(scalarClass, "-0,0 " + abb);

                legal(scalarClass, "10e1" + abb);
                legal(scalarClass, "10e1 " + abb);
                legal(scalarClass, "10,0e1" + abb);
                legal(scalarClass, "10,0e1 " + abb);
                legal(scalarClass, "-10,0e1" + abb);
                legal(scalarClass, "-10,0e1 " + abb);
                legal(scalarClass, "0e1" + abb);
                legal(scalarClass, "0e1 " + abb);
                legal(scalarClass, "0,0e1" + abb);
                legal(scalarClass, "0,0e1 " + abb);
                legal(scalarClass, "-0,0e1" + abb);
                legal(scalarClass, "-0,0e1 " + abb);

                legal(scalarClass, "10e-1" + abb);
                legal(scalarClass, "10e-1 " + abb);
                legal(scalarClass, "10,0e-1" + abb);
                legal(scalarClass, "10,0e-1 " + abb);
                legal(scalarClass, "-10,0e-1" + abb);
                legal(scalarClass, "-10,0e-1 " + abb);
                legal(scalarClass, "0e-1" + abb);
                legal(scalarClass, "0e-1 " + abb);
                legal(scalarClass, "0,0e-1" + abb);
                legal(scalarClass, "0,0e-1 " + abb);
                legal(scalarClass, "-0,0e-1" + abb);
                legal(scalarClass, "-0,0e-1 " + abb);

                legal(scalarClass, "10E1" + abb);
                legal(scalarClass, "10E1 " + abb);
                legal(scalarClass, "10,0E1" + abb);
                legal(scalarClass, "10,0E1 " + abb);
                legal(scalarClass, "-10,0E1" + abb);
                legal(scalarClass, "-10,0E1 " + abb);
                legal(scalarClass, "0E1" + abb);
                legal(scalarClass, "0E1 " + abb);
                legal(scalarClass, "0,0E1" + abb);
                legal(scalarClass, "0,0E1 " + abb);
                legal(scalarClass, "-0,0E1" + abb);
                legal(scalarClass, "-0,0E1 " + abb);

                legal(scalarClass, "10E-1" + abb);
                legal(scalarClass, "10E-1 " + abb);
                legal(scalarClass, "10,0E-1" + abb);
                legal(scalarClass, "10,0E-1 " + abb);
                legal(scalarClass, "-10,0E-1" + abb);
                legal(scalarClass, "-10,0E-1 " + abb);
                legal(scalarClass, "0E-1" + abb);
                legal(scalarClass, "0E-1 " + abb);
                legal(scalarClass, "0,0E-1" + abb);
                legal(scalarClass, "0,0E-1 " + abb);
                legal(scalarClass, "-0,0E-1" + abb);
                legal(scalarClass, "-0,0E-1 " + abb);
            }

        }
        catch (InvocationTargetException exception)
        {
            // ok
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }

    }
}
