package org.djunits.vecmat;

/**
 * NonInvertibleMatrixException is throws on inverting a singular matrix.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class NonInvertibleMatrixException extends Exception
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a NonInvertibleMatrixException without any explanation.
     */
    public NonInvertibleMatrixException()
    {
    }

    /**
     * Create a NonInvertibleMatrixException with a message.
     * @param message the message to include
     */
    public NonInvertibleMatrixException(final String message)
    {
        super(message);
    }

    /**
     * Create a NonInvertibleMatrixException with a cause.
     * @param cause the cause to include
     */
    public NonInvertibleMatrixException(final Throwable cause)
    {
        super(cause);
    }

    /**
     * Create a NonInvertibleMatrixException with a message and a cause.
     * @param message the message to include
     * @param cause the cause to include
     */
    public NonInvertibleMatrixException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

}
