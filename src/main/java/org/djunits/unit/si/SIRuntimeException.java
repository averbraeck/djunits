package org.djunits.unit.si;

/**
 * Runtime Exceptions in SI package.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class SIRuntimeException extends RuntimeException
{

    /** */
    private static final long serialVersionUID = 20190821L;

    /**
     * Construct a new SIRuntimeException with all fields set to default values.
     */
    public SIRuntimeException()
    {
        // Nothing to do here
    }

    /**
     * Construct a new SIRuntimeException with specified message and cause.
     * @param message String; String
     * @param cause Throwable; Throwable
     */
    public SIRuntimeException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Construct a new SIRuntimeException with specified message.
     * @param message String; String
     */
    public SIRuntimeException(final String message)
    {
        super(message);
    }

    /**
     * Construct a new SIRuntimeException with specified cause.
     * @param cause Throwable; Throwable
     */
    public SIRuntimeException(final Throwable cause)
    {
        super(cause);
    }

}
