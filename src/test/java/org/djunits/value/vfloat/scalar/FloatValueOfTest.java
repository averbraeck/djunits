package org.djunits.value.vfloat.scalar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitException;
import org.djunits.value.vfloat.scalar.base.FloatScalarInterface;
import org.junit.Test;

/**
 * ValueOfTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class FloatValueOfTest
{
    /**
     * Test legal "of" arguments.
     * @param scalarClass the scalar class
     * @param value the value to enter
     * @param unitString the unit String to use
     */
    private void legal(final Class<? extends FloatScalarInterface<?, ?>> scalarClass, final float value,
            final String unitString)
    {
        try
        {
            Method ofMethod = scalarClass.getDeclaredMethod("of", float.class, String.class);
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
    private void legal(final Class<? extends FloatScalarInterface<?, ?>> scalarClass, final String text)
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
    private void illegal(final Class<? extends FloatScalarInterface<?, ?>> scalarClass, final float value,
            final String unitString)
    {
        try
        {
            Method ofMethod = scalarClass.getDeclaredMethod("of", float.class, String.class);
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
    private void illegal(final Class<? extends FloatScalarInterface<?, ?>> scalarClass, final String text)
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
    public void testFloatSIScalar() throws UnitException
    {
        SIUnit m2 = SIUnit.of(new SIDimensions(0, 0, 0, 2, 0, 0, 0, 0, 0));
        assertEquals(FloatSIScalar.instantiateSI(10.0f, m2), FloatSIScalar.of(10.0f, "m2"));
        assertEquals(FloatSIScalar.instantiateSI(10.0f, m2), FloatSIScalar.of(10.0f, "m^2"));
        assertEquals(FloatSIScalar.instantiateSI(10.0f, m2), FloatSIScalar.valueOf("10.0 m2"));
        // test if the . in 10.0 is not removed...
        assertEquals(FloatSIScalar.instantiateSI(10.0f, m2), FloatSIScalar.valueOf("10.0 m^2"));

        legal(FloatSIScalar.class, 10.0f, "m2");
        legal(FloatSIScalar.class, "10.0 m2");
        illegal(FloatSIScalar.class, 10.0f, null);
        illegal(FloatSIScalar.class, 10.0f, "");
        illegal(FloatSIScalar.class, null);
        illegal(FloatSIScalar.class, "");
        illegal(FloatSIScalar.class, "1.0.0 m2");
        illegal(FloatSIScalar.class, 10.0f, "xyz");
        illegal(FloatSIScalar.class, "10.0 xyz");
        try
        {
            FloatSIScalar.valueOf("10.0 xyzuwv");
            fail("valueOf of nonexistent unit should have thrown an IllegalArgumentException");
        }
        catch (IllegalArgumentException iae)
        {
            // Ignore expected exception
        }
        
        try
        {
            FloatSIScalar.valueOf("xyzuvw");
            fail("valueOf of empty string should have thrown an IllegalArgumentException");
        }
        catch (IllegalArgumentException iae)
        {
            // Ignore expected exception
        }
    }

    /** test the valueOf and the of methods. */
    @Test
    public void testFloatArea()
    {
        assertEquals(FloatArea.instantiateSI(10.0f), FloatArea.of(10.0f, "m2"));
        assertEquals(FloatArea.instantiateSI(10.0f), FloatArea.of(10.0f, "m^2"));
        assertEquals(FloatArea.instantiateSI(10.0f), FloatArea.valueOf("10.0 m2"));
        assertEquals(FloatArea.instantiateSI(10.0f), FloatArea.valueOf("10.0 m^2")); // test if the . in 10.0 is not removed...
        assertEquals(new FloatArea(10.0f, AreaUnit.HECTARE), FloatArea.of(10.0f, "ha"));
        assertEquals(new FloatArea(10.0f, AreaUnit.HECTARE), FloatArea.valueOf("10.0 ha"));

        legal(FloatArea.class, 10.0f, "m2");
        legal(FloatArea.class, "10.0 m2");
        illegal(FloatArea.class, 10.0f, null);
        illegal(FloatArea.class, 10.0f, "");
        illegal(FloatArea.class, null);
        illegal(FloatArea.class, "");
        illegal(FloatArea.class, "1.0.0 m2");
        illegal(FloatArea.class, 10.0f, "xyz");
        illegal(FloatArea.class, "10.0 xyz");
        illegal(FloatArea.class, 10.0f, "m5s3");
        illegal(FloatArea.class, "10.0 m5s3");
    }
}
