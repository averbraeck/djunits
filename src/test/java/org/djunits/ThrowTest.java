package org.djunits;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * The JUNIT Test for <code>Throw</code> class.
 * <p>
 * Copyright (c) 2002-2022 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank"> https://djunits.org</a>. The DJUNITS project is
 * distributed under a three-clause BSD-style license, which can be found at
 * <a href="https://djunits.org/docs/license.html" target="_blank"> https://djunits.org/docs/license.html</a>.
 * </p>
 * @author <a href="https://www.linkedin.com/in/peterhmjacobs">Peter Jacobs </a>
 */
public class ThrowTest
{
    /** */
    @Test
    public void testThrow()
    {
        Object object = new Object();
        Object objectNull = null;
        int i = 10;
        Double d = Double.valueOf(20.0);
        String s = "argument";
        int hex = 26; // 1A

        //
        // Throw.whenNull(...)
        //

        String message = "Throw error has occurred. Correct";
        try
        {
            Throw.whenNull(objectNull, message);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message, e.getMessage().endsWith(message));
        }

        String message1arg = "Throw error has occurred for %s. Correct";
        String message1 = "Throw error has occurred for argument. Correct";
        try
        {
            Throw.whenNull(objectNull, message1arg, s);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message1, e.getMessage().endsWith(message1));
        }

        String message2arg = "Throw error %d has occurred for %s. Correct";
        String message2 = "Throw error 10 has occurred for argument. Correct";
        try
        {
            Throw.whenNull(objectNull, message2arg, i, s);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message2, e.getMessage().endsWith(message2));
        }

        String message3arg = "Throw error %4.1f has occurred for %s, value %d. Correct";
        String message3 = "Throw error 20.0 has occurred for argument, value 10. Correct";
        try
        {
            Throw.whenNull(objectNull, message3arg, d, s, i);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message3, e.getMessage().endsWith(message3));
        }

        String message4arg = "Throw error %4.1f has occurred for %s, hex=%h, value %d. Correct";
        String message4 = "Throw error 20.0 has occurred for argument, hex=1a, value 10. Correct";
        try
        {
            Throw.whenNull(objectNull, message4arg, d, s, hex, i);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message4, e.getMessage().endsWith(message4));
        }

        // this should be okay
        Throw.whenNull(object, "object is not null -- this should not be triggered");
        Throw.whenNull(object, message1arg, s);
        Throw.whenNull(object, message2arg, i, s);
        Throw.whenNull(object, message3arg, d, s, i);
        Throw.whenNull(object, message4arg, d, s, hex, i);

        //
        // Throw.when(...)
        //

        try
        {
            Throw.when(i == 10, Exception.class, message);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message, e.getMessage().endsWith(message));
        }

        try
        {
            Throw.when(i == 10, Exception.class, message1arg, s);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message1, e.getMessage().endsWith(message1));
        }

        try
        {
            Throw.when(i == 10, Exception.class, message2arg, i, s);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message2, e.getMessage().endsWith(message2));
        }

        try
        {
            Throw.when(i == 10, Exception.class, message3arg, d, s, i);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message3, e.getMessage().endsWith(message3));
        }

        try
        {
            Throw.when(i == 10, Exception.class, message4arg, d, s, hex, i);
        }
        catch (Exception e)
        {
            assertTrue(e.getMessage() + " / " + message4, e.getMessage().endsWith(message4));
        }

        // this should be okay
        Throw.when(false, RuntimeException.class, "object is not null -- this should not be triggered");
        Throw.when(false, RuntimeException.class, message1arg, s);
        Throw.when(false, RuntimeException.class, message2arg, i, s);
        Throw.when(false, RuntimeException.class, message3arg, d, s, i);
        Throw.when(false, RuntimeException.class, message4arg, d, s, hex, i);

        assertEquals(d, Throw.when(d, false, RuntimeException.class, "object is not null -- this should not be triggered"));
        assertEquals(d, Throw.when(d, false, RuntimeException.class, message1arg, s));
        assertEquals(d, Throw.when(d, false, RuntimeException.class, message2arg, i, s));
        assertEquals(d, Throw.when(d, false, RuntimeException.class, message3arg, d, s, i));
        assertEquals(d, Throw.when(d, false, RuntimeException.class, message4arg, d, s, hex, i));

    }

}
