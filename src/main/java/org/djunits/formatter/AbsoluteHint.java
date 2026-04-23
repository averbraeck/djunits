package org.djunits.formatter;

/**
 * AbsQuantityHint stores a number of settings that influence both the value part and the unit part of an output string when
 * formatting an absolute quantity.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsoluteHint extends QuantityHint
{
    /** Print the reference or not. */
    private boolean printReference = false;

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        super.applyTo(ctx);
        ctx.printReference = this.printReference;
    }

    /**
     * Print the reference.
     * @return AbsQuantityHint object for fluent design
     */
    public AbsoluteHint reference()
    {
        this.printReference = true;
        return this;
    }

    /**
     * Do not print the reference.
     * @return AbsQuantityHint object for fluent design
     */
    public AbsoluteHint noReference()
    {
        this.printReference = false;
        return this;
    }

    /**
     * Indicate whether to print the reference or not.
     * @param on boolean that indicates whether to print the reference or not
     * @return AbsQuantityHint object for fluent design
     */
    public AbsoluteHint reference(final boolean on)
    {
        this.printReference = on;
        return this;
    }

}
