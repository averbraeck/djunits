package org.djunits.unit.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * UnitRuntimeExceptionTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class UnitRuntimeExceptionTest
{
    /** test the UnitRuntimeException. */
    @Test
    public void testException()
    {
        try
        {
            throw new UnitRuntimeException();
        }
        catch (UnitRuntimeException e)
        {
            assertNull(e.getMessage());
        }
        catch (Exception exception)
        {
            fail("Right exception not thrown");
        }

        try
        {
            throw new UnitRuntimeException("abc");
        }
        catch (UnitRuntimeException e)
        {
            assertEquals("abc", e.getMessage());
        }
        catch (Exception exception)
        {
            fail("Right exception not thrown");
        }

        try
        {
            throw new UnitRuntimeException(new IllegalArgumentException());
        }
        catch (UnitRuntimeException e)
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
            throw new UnitRuntimeException("abc", new IllegalArgumentException("def"));
        }
        catch (UnitRuntimeException e)
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
}
