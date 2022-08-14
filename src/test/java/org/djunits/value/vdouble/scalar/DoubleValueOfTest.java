package org.djunits.value.vdouble.scalar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitException;
import org.djunits.value.vdouble.scalar.base.DoubleScalarInterface;
import org.junit.Test;

/**
 * ValueOfTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DoubleValueOfTest
{
    /**
     * Test legal "of" arguments.
     * @param scalarClass the scalar class
     * @param value the value to enter
     * @param unitString the unit String to use
     */
    private void legal(final Class<? extends DoubleScalarInterface<?, ?>> scalarClass, final double value,
            final String unitString)
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
    private void legal(final Class<? extends DoubleScalarInterface<?, ?>> scalarClass, final String text)
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
    private void illegal(final Class<? extends DoubleScalarInterface<?, ?>> scalarClass, final double value,
            final String unitString)
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
    private void illegal(final Class<? extends DoubleScalarInterface<?, ?>> scalarClass, final String text)
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
        SIUnit m2 = SIUnit.of(new SIDimensions(0, 0, 0, 2, 0, 0, 0, 0, 0));
        assertEquals(SIScalar.instantiateSI(10.0, m2), SIScalar.of(10.0, "m2"));
        assertEquals(SIScalar.instantiateSI(10.0, m2), SIScalar.of(10.0, "m^2"));
        assertEquals(SIScalar.instantiateSI(10.0, m2), SIScalar.valueOf("10.0 m2"));
        // test if the . in 10.0 is not removed...
        assertEquals(SIScalar.instantiateSI(10.0, m2), SIScalar.valueOf("10.0 m^2"));

        legal(SIScalar.class, 10.0, "m2");
        legal(SIScalar.class, "10.0 m2");
        illegal(SIScalar.class, 10.0, null);
        illegal(SIScalar.class, 10.0, "");
        illegal(SIScalar.class, null);
        illegal(SIScalar.class, "");
        illegal(SIScalar.class, "1.0.0 m2");
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
        assertEquals(Area.instantiateSI(10.0), Area.of(10.0, "m2"));
        assertEquals(Area.instantiateSI(10.0), Area.of(10.0, "m^2"));
        assertEquals(Area.instantiateSI(10.0), Area.valueOf("10.0 m2"));
        assertEquals(Area.instantiateSI(10.0), Area.valueOf("10.0 m^2")); // test if the . in 10.0 is not removed...
        assertEquals(new Area(10.0, AreaUnit.HECTARE), Area.of(10.0, "ha"));
        assertEquals(new Area(10.0, AreaUnit.HECTARE), Area.valueOf("10.0 ha"));

        legal(Area.class, 10.0, "m2");
        legal(Area.class, "10.0 m2");
        illegal(Area.class, 10.0, null);
        illegal(Area.class, 10.0, "");
        illegal(Area.class, null);
        illegal(Area.class, "");
        illegal(Area.class, "1.0.0 m2");
        illegal(Area.class, 10.0, "xyz");
        illegal(Area.class, "10.0 xyz");
        illegal(Area.class, 10.0, "m5s3");
        illegal(Area.class, "10.0 m5s3");
    }
}
