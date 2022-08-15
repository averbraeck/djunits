package org.djunits;

import static org.junit.Assert.fail;

/**
 * Use the Try class as follows:
 * 
 * <pre>
 * new Try(
 *   public &#64;Override void execute()
 *   {
 *     // code to test that should fail
 *   }).test();
 * </pre>
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public abstract class Try
{
    /** implement the execute method to test failure. */
    public abstract void execute();

    /** call the test method to test failure of the try. */
    public void test()
    {
        test("method should have thrown an exception");
    }

    /**
     * call the test method to test failure of the try.
     * @param message the message to print
     */
    public void test(final String message)
    {
        try
        {
            execute();
            fail(message);
        }
        catch (Exception e)
        {
            // ok!
        }
    }

    /**
     * call the test method to test failure of the try with an expected exception type.
     * @param message the message to print
     * @param expectedExceptionClass the expected exception
     */
    public void test(final String message, final Class<? extends Exception> expectedExceptionClass)
    {
        try
        {
            execute();
            fail(message);
        }
        catch (Exception e)
        {
            if (!expectedExceptionClass.isInstance(e))
            {
                fail(message + ", unexcepted exception: " + e.getClass().getSimpleName());
            }
        }
    }
}
