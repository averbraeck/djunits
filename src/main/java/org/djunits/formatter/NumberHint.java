package org.djunits.formatter;

/**
 * NumberHint stores a number of settings for the numeric part of an output string when formatting a quantity, vector, matrix or
 * quantity table.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class NumberHint implements FormatHint
{
    /** Format mode. */
    private FloatFormatMode formatMode = FloatFormatMode.FIXED_WITH_SCI_FALLBACK;

    /** Scientific notation: upper case E or lower case e. */
    private boolean upperE = true;

    /** Number of decimal digits. */
    private int decimals = 3;

    /** Fixed width of the numerical output. */
    private int width = 10;

    /** Use grouping separator (e.g., thousands) or not. */
    private boolean groupingSeparator = false;

    /** Number format string. */
    private String formatString = null;

    @Override
    public void applyTo(final FormatContext ctx)
    {
        if (this.formatString != null)
        {
            ctx.formatMode = FloatFormatMode.FORMAT_STRING;
            ctx.formatString = this.formatString;
        }
        else
        {
            ctx.formatMode = this.formatMode;
            ctx.upperE = this.upperE;
            ctx.decimals = this.decimals;
            ctx.width = this.width;
            ctx.groupingSeparator = this.groupingSeparator;
        }
    }

    /**
     * Apply variable length notation. Ignore width and decimals restrictions.
     * @return the NumberHint instance
     */
    public NumberHint variableLength()
    {
        this.formatMode = FloatFormatMode.VARIABLE_LENGTH;
        return this;
    }

    /**
     * Apply fixed length float-only notation.
     * @return the NumberHint instance
     */
    public NumberHint fixedFloat()
    {
        this.formatMode = FloatFormatMode.FIXED_FLOAT;
        return this;
    }

    /**
     * Apply scientific notation.
     * @return the NumberHint instance
     */
    public NumberHint scientific()
    {
        this.formatMode = FloatFormatMode.SCIENTIFIC_ALWAYS;
        return this;
    }

    /**
     * Apply fixed length float-only notation with scientific notation as a fallback.
     * @return the NumberHint instance
     */
    public NumberHint fixedWithSciFallback()
    {
        this.formatMode = FloatFormatMode.FIXED_WITH_SCI_FALLBACK;
        return this;
    }

    /**
     * Apply fixed length float-only notation with engineering notation as a fallback. Engineering notation uses an e formatter
     * with exponents that are multiples of 3.
     * @return the NumberHint instance
     */
    public NumberHint fixedWithEngFallback()
    {
        this.formatMode = FloatFormatMode.FIXED_WITH_ENG_FALLBACK;
        return this;
    }

    /**
     * Apply engineering notation. Engineering notation uses an e formatter with exponents that are multiples of 3.
     * @return the NumberHint instance
     */
    public NumberHint engineering()
    {
        this.formatMode = FloatFormatMode.ENGINEERING_ALWAYS;
        return this;
    }

    /**
     * Set the width of the numerical output.
     * @param width the width of the numerical output
     * @return the NumberHint instance
     */
    public NumberHint setWidth(final int width)
    {
        this.width = width;
        return this;
    }

    /**
     * Set the number of decimal digits of the numerical output.
     * @param decimals the number of decimal digits of the numerical output
     * @return the NumberHint instance
     */
    public NumberHint setDecimals(final int decimals)
    {
        this.decimals = decimals;
        return this;
    }

    /**
     * Set the scientific exponential character to E or e.
     * @param upper results in E when true, and in e when false
     * @return the NumberHint instance
     */
    public NumberHint upperE(final boolean upper)
    {
        this.upperE = upper;
        return this;
    }

    /**
     * Set the grouping separator (e.g., 1000s separator) or or off.
     * @param on indicates whether the grouping separator is on or not
     * @return the NumberHint instance
     */
    public NumberHint setGroupingSeparator(final boolean on)
    {
        this.groupingSeparator = on;
        return this;
    }

    /** The format mode. */
    public enum FloatFormatMode
    {
        /** Use a format string. */
        FORMAT_STRING,
        /** Use a variable length format -- ignore width and decimals restrictions. */
        VARIABLE_LENGTH,
        /** Always apply an "f" formatter. */
        FIXED_FLOAT,
        /** Try to apply an "f" formatter, use scientific formatting as fallback. */
        FIXED_WITH_SCI_FALLBACK,
        /** Try to apply an "f" formatter, use engineering formatting (exponents are multiples of 3) as fallback. */
        FIXED_WITH_ENG_FALLBACK,
        /** Always apply scientific formatting (e formatter). */
        SCIENTIFIC_ALWAYS,
        /** Always apply engineering formatting (e formatter where exponents are multiples of 3). */
        ENGINEERING_ALWAYS;
    }

}
