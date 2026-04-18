package org.djunits.formatter;

import org.djunits.unit.Unit;

/**
 * UnitFormat stores a number of settings for the unit part of an output string when formatting a quantity, vector, matrix or
 * quantity table.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class UnitFormat implements FormatHint
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
        if (this.displayUnit != null)
            ctx.unitString = this.unitString;
        if (this.textual != null)
            ctx.textual = this.textual;
    }

    /**
     * Set the display unit to use.
     * @param unit the display unit to use
     * @return UnitFormat object for fluent design
     */
    public static UnitFormat displayUnit(final Unit<?, ?> unit)
    {
        var uf = new UnitFormat();
        uf.displayUnit = unit;
        return uf;
    }

    /**
     * Set the display unit to use, based on a String representation.
     * @param unitString the String representation of the display unit to use
     * @return UnitFormat object for fluent design
     */
    public static UnitFormat displayUnit(final String unitString)
    {
        var uf = new UnitFormat();
        uf.unitString = unitString;
        return uf;
    }

    /**
     * Set textual mode.
     * @return UnitFormat object for fluent design
     */
    public static UnitFormat textual()
    {
        var uf = new UnitFormat();
        uf.textual = true;
        return uf;
    }

    /**
     * Set display mode.
     * @return UnitFormat object for fluent design
     */
    public static UnitFormat display()
    {
        var uf = new UnitFormat();
        uf.textual = false;
        return uf;
    }

    /**
     * Set textual mode on or off.
     * @param on whether to turn textual mode on or off
     * @return UnitFormat object for fluent design
     */
    public static UnitFormat textual(final boolean on)
    {
        var uf = new UnitFormat();
        uf.textual = on;
        return uf;
    }

    /**
     * Set SI unit mode.
     * @return UnitFormat object for fluent design
     */
    public static SIFormat siUnits()
    {
        return new SIFormat();
    }

    /**
     * SIFormat stores a number of settings for the SIUnit part of an output string when formatting a quantity, vector, matrix
     * or quantity table.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class SIFormat implements FormatHint
    {
        /** Use division symbol, e.g., TRUE: kgm2/s2, FALSE: kgm2s-2. */
        private Boolean siDivisionSymbol = null;

        /** Symbol to use for start of power, e.g., "^" or "&lt;sup&gt;". */
        private String siPowerStart = null;

        /** Symbol to use for end of power, e.g., "&lt;/sup&gt;". */
        private String siPowerEnd = null;

        /** Symbol to use for dot separation, e.g., "." to create kg.m2/s2. */
        private String siDotSeparator = null;

        @Override
        @SuppressWarnings("checkstyle:needbraces")
        public void applyTo(final FormatContext ctx)
        {
            ctx.siUnits = true;
            if (this.siDivisionSymbol != null)
                ctx.siDivisionSymbol = this.siDivisionSymbol;
            if (this.siPowerStart != null)
                ctx.siPowerStart = this.siPowerStart;
            if (this.siPowerEnd != null)
                ctx.siPowerEnd = this.siPowerEnd;
            if (this.siDotSeparator != null)
                ctx.siDotSeparator = this.siDotSeparator;
        }

        /**
         * Set whether the divider symbol is used.
         * @param on true when the divider symbol is used, false when not
         * @return SIFormat for fluent design
         */
        public SIFormat divider(final boolean on)
        {
            this.siDivisionSymbol = on;
            return this;
        }

        /**
         * Set the power start symbol to the given String.
         * @param symbol the string to use for the power start symbol
         * @return SIFormat for fluent design
         */
        public SIFormat powerStart(final String symbol)
        {
            this.siPowerStart = symbol;
            return this;
        }

        /**
         * Set the power end symbol to the given String.
         * @param symbol the string to use for the power end symbol
         * @return SIFormat for fluent design
         */
        public SIFormat powerEnd(final String symbol)
        {
            this.siPowerEnd = symbol;
            return this;
        }

        /**
         * Set the dot separator symbol to the given String.
         * @param symbol the string to use for the dot separator symbol
         * @return SIFormat for fluent design
         */
        public SIFormat dotSeparator(final String symbol)
        {
            this.siDotSeparator = symbol;
            return this;
        }

    }
}
