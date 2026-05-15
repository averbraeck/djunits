package org.djunits.formatter;

import org.djunits.quantity.LinearObjectDensity;
import org.djunits.quantity.Mass;

/**
 * QuantityFormat stores the settings that influence both the value part and the unit part of an output string when formatting a
 * quantity.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class QuantityFormat extends Format<QuantityFormat, QuantityFormatContext>
{
    /** The defaults (which can be changed). */
    @SuppressWarnings("checkstyle:staticvariablename")
    private static QuantityFormatContext DEFAULT = new QuantityFormatContext();

    /**
     * Construct a QuantityFormat object with a given context. Note that the context can be an existing context that is being
     * modified or a default context.
     * @param ctx the quantity format context to use
     */
    protected QuantityFormat(final QuantityFormatContext ctx)
    {
        super(ctx);
    }

    /**
     * Return an instance of QuantityFormat, initialized with the default values.
     * @return an instance of QuantityFormat, initialized with the default values
     */
    public static QuantityFormat instance()
    {
        return new QuantityFormat(DEFAULT.clone());
    }

    /**
     * Return an instance of QuantityFormat with the DEFAULT values, which can be changed for all subsequent calls.
     * @return an instance of QuantityFormat with the DEFAULT values
     */
    public static QuantityFormat changeDefaults()
    {
        return new QuantityFormat(DEFAULT);
    }

    /**
     * Reset the default values of QuantityFormat to their original values.
     */
    public static void resetDefaults()
    {
        DEFAULT = new QuantityFormatContext();
    }

    /**
     * Turn on the automatic allocation of the unit to its closest SI prefix. E.g., turn 20400 m into "20.4 km".
     * @return QuantityFormat object for fluent design
     */
    public QuantityFormat setAutoSiPrefix()
    {
        this.ctx.autoSiPrefix = true;
        return this;
    }

    /**
     * Turn on the automatic allocation of the unit to its closest SI prefix, in case the exponent of the 10-power in scientific
     * notation is between minExponent and maxExponent, inclusive. E.g., format 20400 m as "20.4 km" after calling
     * setAutoSiPrefix(-9,9) but format it as 20400 m after calling setAutoSiPrefix(-9,0). Note that the kg for {@link Mass} is
     * associated with an exponent of 3, and the g with an exponent of 0. For the {@link LinearObjectDensity} that is expressed
     * in /m, /km is associated with an exponent of 3 and /mm with an exponent of -3.
     * @param minExponent minimum exponent for the 10-power in scientific notation to use SI prefixes for (inclusive)
     * @param maxExponent maximum exponent for the 10-power in scientific notation to use SI prefixes for (inclusive)
     * @return QuantityFormat object for fluent design
     */
    public QuantityFormat setAutoSiPrefix(final int minExponent, final int maxExponent)
    {
        this.ctx.autoSiPrefix = true;
        this.ctx.autoSiMinExponent = minExponent;
        this.ctx.autoSiMaxExponent = maxExponent;
        return this;
    }

}
