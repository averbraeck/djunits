package org.djunits.unit.si;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.djunits.unit.Unit;
import org.junit.Test;

/**
 * SIRuntimeExceptionTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class SIRuntimeExceptionTest
{
    /** test the SIRuntimeException. */
    @Test
    public void testException()
    {
        try
        {
            throw new SIRuntimeException();
        }
        catch (SIRuntimeException e)
        {
            assertNull(e.getMessage());
        }
        catch (Exception exception)
        {
            fail("Right exception not thrown");
        }

        try
        {
            throw new SIRuntimeException("abc");
        }
        catch (SIRuntimeException e)
        {
            assertEquals("abc", e.getMessage());
        }
        catch (Exception exception)
        {
            fail("Right exception not thrown");
        }

        try
        {
            throw new SIRuntimeException(new IllegalArgumentException());
        }
        catch (SIRuntimeException e)
        {
            assertTrue(e.getMessage().contains("IllegalArgumentException"));
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
        catch (Exception exception)
        {
            fail("Right exception not thrown");
        }

        try
        {
            throw new SIRuntimeException("abc", new IllegalArgumentException("def"));
        }
        catch (SIRuntimeException e)
        {
            assertEquals("abc", e.getMessage());
            assertTrue(e.getCause() instanceof IllegalArgumentException);
            assertEquals("def", e.getCause().getMessage());
        }
        catch (Exception exception)
        {
            fail("Right exception not thrown");
        }
    }

    /**
     * Test the static getUnit method in the Unit class.
     */
    @Test
    public void testUnitGetUnit()
    {
        try
        {
            Unit.getUnit(null);
            fail("null unit string should have thrown a NullPointerException");
        }
        catch (NullPointerException npe)
        {
            assertTrue("Exception describes the parameter that has the problem", npe.getMessage().contains("unitString"));
        }

        try
        {
            Unit.getUnit("");
            fail("empty string should have thrown a IllegalArgumentException");
        }
        catch (IllegalArgumentException npe)
        {
            assertTrue("Exception describes the parameter that has the problem", npe.getMessage().contains("unitString"));
        }

        try
        {
            Unit.getUnit("m.m.m.m");
            fail("Bad unit string should have thrown an IllegalArgumentException");
        }
        catch (IllegalArgumentException npe)
        {
            // Ignore expected exception
        }

        Unit<?> u = Unit.getUnit("m/s");
        assertTrue("u uses a base si scale", u.getScale().isBaseSIScale());
    }

}
