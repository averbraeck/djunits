package org.djunits.formatter;

/**
 * AbsoluteHint stores a number of settings that influence both the value part and the unit part of an output string when
 * formatting an absolute quantity.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsoluteHint extends QuantityHint
{
    /** Print the reference or not. */
    private Boolean printReference = null;

    /** Prefix to the reference. */
    private String prefix = null;

    /** Postfix to the reference. */
    private String postfix = null;

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        super.applyTo(ctx);
        if (this.printReference != null)
            ctx.printReference = this.printReference;
        if (this.prefix != null)
            ctx.referencePrefix = this.prefix;
        if (this.postfix != null)
            ctx.referencePostfix = this.postfix;
    }

    /**
     * Print the reference.
     * @return AbsoluteHint object for fluent design
     */
    public AbsoluteHint reference()
    {
        this.printReference = true;
        return this;
    }

    /**
     * Do not print the reference.
     * @return AbsoluteHint object for fluent design
     */
    public AbsoluteHint noReference()
    {
        this.printReference = false;
        return this;
    }

    /**
     * Indicate whether to print the reference or not.
     * @param on boolean that indicates whether to print the reference or not
     * @return AbsoluteHint object for fluent design
     */
    public AbsoluteHint reference(final boolean on)
    {
        this.printReference = on;
        return this;
    }

    /**
     * Set the prefix for the reference, e.g., "(relative to ".
     * @param prefix the prefix for the reference
     * @return AbsoluteHint object for fluent design
     */
    public AbsoluteHint setPrefix(final String prefix)
    {
        this.prefix = prefix;
        return this;
    }

    /**
     * Set the postfix for the reference, e.g., ")".
     * @param postfix the postfix for the reference
     * @return AbsoluteHint object for fluent design
     */
    public AbsoluteHint setPostfix(final String postfix)
    {
        this.postfix = postfix;
        return this;
    }

}
