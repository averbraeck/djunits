package org.djunits.formatter;

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
    public static QuantityFormat defaults()
    {
        return new QuantityFormat(QuantityFormatContext.DEFAULT.clone());
    }

    /**
     * Return an instance of QuantityFormat with the DEFAULT values, which can be changed for all subsequent calls.
     * @return an instance of QuantityFormat with the DEFAULT values
     */
    public static QuantityFormat changeDefaults()
    {
        return new QuantityFormat(QuantityFormatContext.DEFAULT);
    }

    /**
     * Reset the default values of QuantityFormat to their original values.
     */
    public static void resetDefaults()
    {
        QuantityFormatContext.DEFAULT = new QuantityFormatContext();
    }

    /**
     * Use closest SI prefix. E.g., turn 20400 m into "20.4 km".
     * @return QuantityFormat object for fluent design
     */
    public QuantityFormat scaleSiPrefixes()
    {
        this.ctx.scaleSiPrefixes = true;
        return this;
    }

    /**
     * Use closest SI prefix. E.g., turn 20400 m into "20.4 km".
     * @param minPrefixPower minimum 10th power to use SI prefixes for
     * @param maxPrefixPower maximum 10th power to use SI prefixes for
     * @return QuantityFormat object for fluent design
     */
    public QuantityFormat scaleSiPrefixes(final int minPrefixPower, final int maxPrefixPower)
    {
        this.ctx.scaleSiPrefixes = true;
        this.ctx.minimumPrefixPower = minPrefixPower;
        this.ctx.maximumPrefixPower = maxPrefixPower;
        return this;
    }

}
