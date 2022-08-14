package org.djunits.unit.util;

/**
 * Runtime Exceptions in Unit package.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class UnitRuntimeException extends RuntimeException
{

    /** */
    private static final long serialVersionUID = 20190821L;

    /**
     * Construct a new UnitRuntimeException with all default values.
     */
    public UnitRuntimeException()
    {
        // Nothing to do here
    }

    /**
     * Construct a new UnitRuntimeException.
     * @param message String; String
     * @param cause Throwable; Throwable
     * @param enableSuppression boolean;am
     * @param writableStackTrace boolean; boolean
     */
    public UnitRuntimeException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Construct a new UnitRuntimeException with some default values.
     * @param message String; String
     * @param cause Throwable; Throwable
     */
    public UnitRuntimeException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Construct a new UnitRuntimeException with some default values.
     * @param message String; String
     */
    public UnitRuntimeException(final String message)
    {
        super(message);
    }

    /**
     * Construct a new UnitRuntimeException with some default values.
     * @param cause Throwable; Throwable
     */
    public UnitRuntimeException(final Throwable cause)
    {
        super(cause);
    }

}
