package org.djunits.formatter;

import org.djunits.unit.Unit;

/**
 * UnitHint stores a number of settings for the unit part of an output string when formatting a quantity, vector, matrix or
 * quantity table.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class UnitHint implements FormatHint
{
    /** Display unit to use. */
    private Unit<?, ?> displayUnit = null;

    /** Display unit to use, based on a String representation. */
    private String unitString = null;

    /** Textual representation, e.g., deg instead of degree symbol. */
    private Boolean textual = null;

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        if (this.displayUnit != null)
            ctx.displayUnit = this.displayUnit;
        if (this.unitString != null)
            ctx.unitString = this.unitString;
        if (this.textual != null)
            ctx.textual = this.textual;
    }

    /**
     * Set the display unit to use.
     * @param unit the display unit to use
     * @return UnitHint object for fluent design
     */
    public UnitHint setDisplayUnit(final Unit<?, ?> unit)
    {
        this.displayUnit = unit;
        return this;
    }

    /**
     * Set the display unit to use, based on a String representation.
     * @param unit the String representation of the display unit to use
     * @return UnitHint object for fluent design
     */
    public UnitHint setDisplayUnit(final String unit)
    {
        this.unitString = unit;
        return this;
    }

    /**
     * Set textual mode.
     * @return UnitHint object for fluent design
     */
    public UnitHint textual()
    {
        this.textual = true;
        return this;
    }

    /**
     * Set display mode.
     * @return UnitHint object for fluent design
     */
    public UnitHint display()
    {
        this.textual = false;
        return this;
    }

    /**
     * Set textual mode on or off.
     * @param on whether to turn textual mode on or off
     * @return UnitHint object for fluent design
     */
    public UnitHint textual(final boolean on)
    {
        this.textual = on;
        return this;
    }

    /**
     * Set SI unit mode.
     * @return UnitHint object for fluent design
     */
    public SIHint siUnits()
    {
        return new SIHint();
    }

    /**
     * SIHint stores a number of settings for the SIUnit part of an output string when formatting a quantity, vector, matrix or
     * quantity table.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class SIHint extends UnitHint
    {
        /** Use division symbol, e.g., TRUE: kgm2/s2, FALSE: kgm2s-2. */
        private Boolean siDivisionSymbol = null;

        /** Symbol to use as the prefix for a power, e.g., "^" or "&lt;sup&gt;". */
        private String siPowerPrefix = null;

        /** Symbol to use as the postfix for a power, e.g., "&lt;/sup&gt;". */
        private String siPowerPostfix = null;

        /** Symbol to use for dot separation, e.g., "." to create kg.m2/s2. */
        private String siDotSeparator = null;

        @Override
        @SuppressWarnings("checkstyle:needbraces")
        public void applyTo(final FormatContext ctx)
        {
            super.applyTo(ctx);
            ctx.siUnits = true;
            if (this.siDivisionSymbol != null)
                ctx.siDivisionSymbol = this.siDivisionSymbol;
            if (this.siPowerPrefix != null)
                ctx.siPowerPrefix = this.siPowerPrefix;
            if (this.siPowerPostfix != null)
                ctx.siPowerPostfix = this.siPowerPostfix;
            if (this.siDotSeparator != null)
                ctx.siDotSeparator = this.siDotSeparator;
        }

        /**
         * Set whether the divider symbol is used.
         * @param on true when the divider symbol is used, false when not
         * @return SIHint for fluent design
         */
        public SIHint divider(final boolean on)
        {
            this.siDivisionSymbol = on;
            return this;
        }

        /**
         * Set the symbol to use as the prefix for a power to the given String.
         * @param symbol the string to use for the power prefix
         * @return SIHint for fluent design
         */
        public SIHint powerPrefix(final String symbol)
        {
            this.siPowerPrefix = symbol;
            return this;
        }

        /**
         * Set the symbol to use as the postfix for a power to the given String.
         * @param symbol the string to use for the power postfix
         * @return SIHint for fluent design
         */
        public SIHint powerPostfix(final String symbol)
        {
            this.siPowerPostfix = symbol;
            return this;
        }

        /**
         * Set the dot separator symbol to the given String.
         * @param symbol the string to use for the dot separator symbol
         * @return SIHint for fluent design
         */
        public SIHint dotSeparator(final String symbol)
        {
            this.siDotSeparator = symbol;
            return this;
        }

    }
}
