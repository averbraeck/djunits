package org.djunits.unit.util;

/**
 * Exceptions in Unit package.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class UnitException extends Exception
{

    /** */
    private static final long serialVersionUID = 20140618L;

    /**
     * Construct a new UnitException with all default values.
     */
    public UnitException()
    {
        // Nothing to do here
    }

    /**
     * Construct a new UnitException.
     * @param message String; String
     * @param cause Throwable; Throwable
     * @param enableSuppression boolean;
     * @param writableStackTrace boolean; boolean
     */
    public UnitException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Construct a new UnitException with some default values.
     * @param message String; String
     * @param cause Throwable; Throwable
     */
    public UnitException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Construct a new UnitException with some default values.
     * @param message String; String
     */
    public UnitException(final String message)
    {
        super(message);
    }

    /**
     * Construct a new UnitException with some default values.
     * @param cause Throwable; Throwable
     */
    public UnitException(final Throwable cause)
    {
        super(cause);
    }

}
