package org.djunits.unit;

import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djutils.test.ExceptionTest;
import org.junit.jupiter.api.Test;

/**
 * Test the UnitException, the UnitRuntimeException, and the NonInvertibleMatrixException classes.<p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
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
        ExceptionTest.testExceptionClass(UnitRuntimeException.class);
        ExceptionTest.testExceptionClass(NonInvertibleMatrixException.class);
    }

}
