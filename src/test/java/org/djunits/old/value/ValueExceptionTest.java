package org.djunits.old.value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.old.value.ValueRuntimeException;
import org.junit.jupiter.api.Test;

/**
 * Test the constructors for ValueException.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @version $Revision: 954 $, $LastChangedDate: 2022-01-10 03:42:57 +0100 (Mon, 10 Jan 2022) $, by $Author: averbraeck $,
 *          initial version 27 sep. 2015 <br>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
public class ValueExceptionTest
{
    /**
     * Test all constructors for ValueException.
     */
    @Test
    public final void valueExceptionTest()
    {
        String message = "MessageString";
        Exception e = new ValueRuntimeException(message);
        assertTrue(null != e, "Exception should not be null");
        assertEquals(message, e.getMessage(), "message should be our message");
        assertEquals(null, e.getCause(), "cause should be null");
        e = new ValueRuntimeException();
        assertTrue(null != e, "Exception should not be null");
        assertEquals(null, e.getCause(), "cause should be null");
        String causeString = "CauseString";
        Throwable cause = new Throwable(causeString);
        e = new ValueRuntimeException(cause);
        assertTrue(null != e, "Exception should not be null");
        assertEquals(cause, e.getCause(), "cause should not be our cause");
        assertEquals(causeString, e.getCause().getMessage(), "cause description should be our cause string");
        e = new ValueRuntimeException(message, cause);
        assertTrue(null != e, "Exception should not be null");
        assertEquals(message, e.getMessage(), "message should be our message");
        assertEquals(cause, e.getCause(), "cause should not be our cause");
        assertEquals(causeString, e.getCause().getMessage(), "cause description should be our cause string");
        for (boolean enableSuppression : new boolean[] {true, false})
        {
            for (boolean writableStackTrace : new boolean[] {true, false})
            {
                e = new ValueRuntimeException(message, cause, enableSuppression, writableStackTrace);
                assertTrue(null != e, "Exception should not be null");
                assertEquals(message, e.getMessage(), "message should be our message");
                assertEquals(cause, e.getCause(), "cause should not be our cause");
                assertEquals(causeString, e.getCause().getMessage(), "cause description should be our cause string");
                // Don't know how to check if suppression is enabled/disabled
                StackTraceElement[] stackTrace = new StackTraceElement[1];
                stackTrace[0] = new StackTraceElement("a", "b", "c", 1234);
                try
                {
                    e.setStackTrace(stackTrace);
                }
                catch (Exception e1)
                {
                    assertTrue(writableStackTrace, "Stack trace should be writable");
                    continue;
                }
                // You wouldn't believe it, but a call to setStackTrace if non-writable is silently ignored
                StackTraceElement[] retrievedStackTrace = e.getStackTrace();
                if (retrievedStackTrace.length > 0)
                {
                    assertTrue(writableStackTrace, "stack trace should be writable");
                }
            }
        }
    }
}
