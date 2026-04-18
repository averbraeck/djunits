package org.djunits.formatter;

/**
 * NumberFormat stores a number of settings for the numeric part of an output string when formatting a quantity, vector, matrix
 * or quantity table.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class NumberFormat implements FormatHint
{
    /** Scientific notation. */
    private Boolean scientificNotation = null;

    /** Number of decimal digits. */
    private Integer decimalDigits = null;

    /** Maximum width of the numerical output. */
    private Integer maxWidth = null;

    /** Fixed width of the numerical output. */
    private Integer fixedWidth = null;

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        if (this.scientificNotation != null)
            ctx.scientificNotation = this.scientificNotation;
        if (this.decimalDigits != null)
            ctx.decimalDigits = this.decimalDigits;
        if (this.maxWidth != null)
            ctx.maxWidth = this.maxWidth;
        if (this.fixedWidth != null)
            ctx.fixedWidth = this.fixedWidth;
    }

    /**
     * Apply scientific notation.
     * @return the NumberFormat instance
     */
    public static NumberFormat scientific()
    {
        var nf = new NumberFormat();
        nf.scientificNotation = true;
        return nf;
    }

    /**
     * Apply scientific or non-scientific notation.
     * @param on whether scientific notation is on or not
     * @return the NumberFormat instance
     */
    public static NumberFormat scientific(final boolean on)
    {
        var nf = new NumberFormat();
        nf.scientificNotation = on;
        return nf;
    }

    /**
     * Set the maximum width of the numerical output.
     * @param width the maximum width of the numerical output
     * @return the NumberFormat instance
     */
    public static NumberFormat maxWidth(final int width)
    {
        var nf = new NumberFormat();
        nf.maxWidth = width;
        return nf;
    }

    /**
     * Set the fixed width of the numerical output.
     * @param width the fixed width of the numerical output
     * @return the NumberFormat instance
     */
    public static NumberFormat fixedWidth(final int width)
    {
        var nf = new NumberFormat();
        nf.fixedWidth = width;
        return nf;
    }

    /**
     * Set the number of decimal digits of the numerical output.
     * @param digits the number of decimal digits of the numerical output
     * @return the NumberFormat instance
     */
    public static NumberFormat decimals(final int digits)
    {
        var nf = new NumberFormat();
        nf.decimalDigits = digits;
        return nf;
    }

}
