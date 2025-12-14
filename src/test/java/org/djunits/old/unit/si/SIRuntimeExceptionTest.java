package org.djunits.old.unit.si;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.djunits.old.unit.SpeedUnit;
import org.djunits.old.unit.Unit;
import org.djunits.old.unit.si.SIDimensions;
import org.djunits.old.unit.si.SIRuntimeException;
import org.junit.jupiter.api.Test;

/**
 * SIRuntimeExceptionTest.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
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
        assertTrue(Unit.getUnit("").getQuantity().getSiDimensions().equals(SIDimensions.DIMLESS));
        assertTrue(Unit.getUnit("m/s").getQuantity().getSiDimensions()
                .equals(SpeedUnit.METER_PER_SECOND.getQuantity().getSiDimensions()));

        try
        {
            Unit.getUnit(null);
            fail("null unit string should have thrown a NullPointerException");
        }
        catch (NullPointerException npe)
        {
            assertTrue(npe.getMessage().contains("unitString"), "Exception describes the parameter that has the problem");
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
        assertTrue(u.getScale().isBaseSIScale(), "u uses a base si scale");
    }

}
