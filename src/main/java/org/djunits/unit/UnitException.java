package org.djunits.unit;

/**
 * Exceptions in Unit package.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Peter Knoppers
 */
public class UnitException extends Exception
{
    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Construct a new UnitException with all default values.
     */
    public UnitException()
    {
        // Nothing to do here
    }

    /**
     * Construct a new UnitException.
     * @param message String
     * @param cause Throwable
     * @param enableSuppression boolean
     * @param writableStackTrace boolean
     */
    public UnitException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Construct a new UnitException with some default values.
     * @param message String
     * @param cause Throwable
     */
    public UnitException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Construct a new UnitException with some default values.
     * @param message String
     */
    public UnitException(final String message)
    {
        super(message);
    }

    /**
     * Construct a new UnitException with some default values.
     * @param cause Throwable
     */
    public UnitException(final Throwable cause)
    {
        super(cause);
    }

}
