package org.djunits.unit;

import org.djutils.test.ExceptionTest;
import org.junit.jupiter.api.Test;

/**
 * Test the UnitException and the UnitRuntimeException.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class UnitExceptionsTest
{

    /**
     * Test the UnitException and the UnitRuntimeException using the djutils-test project.
     */
    @Test
    public void testUnitExceptions()
    {
        ExceptionTest.testExceptionClass(UnitException.class);
        ExceptionTest.testExceptionClass(UnitRuntimeException.class);
    }

}
