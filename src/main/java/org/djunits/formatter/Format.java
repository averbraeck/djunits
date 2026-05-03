package org.djunits.formatter;

import java.util.Locale;

import org.djunits.formatter.FormatContext.FloatFormatMode;
import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsMatrix;
import org.djunits.vecmat.def.AbsVector;
import org.djunits.vecmat.def.Matrix;
import org.djunits.vecmat.def.Vector;
import org.djunits.vecmat.table.AbsQuantityTable;
import org.djunits.vecmat.table.QuantityTable;

/**
 * Format is the abstract base class for the settings the format. It contains generic methods to influence the format of
 * {@link Quantity}, {@link AbsQuantity}, {@link Vector}, {@link AbsVector}, {@link Matrix}, {@link AbsMatrix},
 * {@link QuantityTable} and {@link AbsQuantityTable}. The main methods to set the format in this class are about the way to
 * format the number, the unit, and the locale. Specific setter methods for quantities, vectors, matrices and tables can be
 * found in the extensions of this class, such as {@link QuantityFormat}.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <F> the {@link Format} type
 * @param <C> the {@link FormatContext} type
 */
@SuppressWarnings({"checkstyle:visibilitymodifier", "checkstyle:hiddenfield"})
abstract class Format<F extends Format<F, C>, C extends FormatContext>
{
    /** The format context. */
    protected final C ctx;

    /**
     * Construct a Format object with a given context. Note that the context can be an existing context that is being modified
     * or a default context.
     * @param ctx the format context to use
     */
    Format(final C ctx)
    {
        this.ctx = ctx;
    }

    /**
     * Return the object for fluent design.
     * @return the object for fluent design
     */
    @SuppressWarnings("unchecked")
    public F self()
    {
        return (F) this;
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////// //
    // ///////////////////////////////////// METHODS TO FORMAT THE VALUE ///////////////////////////////////// //
    // /////////////////////////////////////////////////////////////////////////////////////////////////////// //

    /**
     * Apply variable length notation. Ignore width and decimals restrictions.
     * @return the the object instance for fluent design
     */
    public F variableLength()
    {
        this.ctx.formatMode = FloatFormatMode.VARIABLE_LENGTH;
        return self();
    }

    /**
     * Apply fixed length float-only notation.
     * @return the the object instance for fluent design
     */
    public F fixedFloat()
    {
        this.ctx.formatMode = FloatFormatMode.FIXED_FLOAT;
        return self();
    }

    /**
     * Apply scientific notation.
     * @return the the object instance for fluent design
     */
    public F scientific()
    {
        this.ctx.formatMode = FloatFormatMode.SCIENTIFIC_ALWAYS;
        return self();
    }

    /**
     * Apply fixed length float-only notation with scientific notation as a fallback.
     * @return the the object instance for fluent design
     */
    public F fixedWithSciFallback()
    {
        this.ctx.formatMode = FloatFormatMode.FIXED_WITH_SCI_FALLBACK;
        return self();
    }

    /**
     * Apply fixed length float-only notation with engineering notation as a fallback. Engineering notation uses an e formatter
     * with exponents that are multiples of 3.
     * @return the the object instance for fluent design
     */
    public F fixedWithEngFallback()
    {
        this.ctx.formatMode = FloatFormatMode.FIXED_WITH_ENG_FALLBACK;
        return self();
    }

    /**
     * Apply engineering notation. Engineering notation uses an e formatter with exponents that are multiples of 3.
     * @return the the object instance for fluent design
     */
    public F engineering()
    {
        this.ctx.formatMode = FloatFormatMode.ENGINEERING_ALWAYS;
        return self();
    }

    /**
     * Set the width of the numerical output.
     * @param width the width of the numerical output
     * @return the the object instance for fluent design
     */
    public F setWidth(final int width)
    {
        this.ctx.width = width;
        return self();
    }

    /**
     * Set the number of decimal digits of the numerical output.
     * @param decimals the number of decimal digits of the numerical output
     * @return the the object instance for fluent design
     */
    public F setDecimals(final int decimals)
    {
        this.ctx.decimals = decimals;
        return self();
    }

    /**
     * Set the scientific exponential character to E or e.
     * @param upper results in E when true, and in e when false
     * @return the the object instance for fluent design
     */
    public F upperE(final boolean upper)
    {
        this.ctx.upperE = upper;
        return self();
    }

    /**
     * Set the grouping separator (e.g., 1000s separator) or or off.
     * @param on indicates whether the grouping separator is on or not
     * @return the the object instance for fluent design
     */
    public F setGroupingSeparator(final boolean on)
    {
        this.ctx.groupingSeparator = on;
        return self();
    }

    /**
     * Set the format string to use.
     * @param formatString the format string to use
     * @return the the object instance for fluent design
     */
    public F setFormatString(final String formatString)
    {
        this.ctx.formatString = formatString;
        this.ctx.formatMode = FloatFormatMode.FORMAT_STRING;
        return self();
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////// //
    // ///////////////////////////////////// METHODS TO FORMAT THE UNIT ////////////////////////////////////// //
    // /////////////////////////////////////////////////////////////////////////////////////////////////////// //

    /**
     * Set the prefix separator between value and unit.
     * @param prefix separator string between the value and the unit
     * @return the object instance for fluent design
     */
    public F unitPrefix(final String prefix)
    {
        this.ctx.unitPrefix = prefix;
        return self();
    }

    /**
     * Set the postfix separator after the unit.
     * @param postfix string after the unit
     * @return the object instance for fluent design
     */
    public F unitPostfix(final String postfix)
    {
        this.ctx.unitPostfix = postfix;
        return self();
    }

    /**
     * Set the display unit to use.
     * @param unit the display unit to use
     * @return the object instance for fluent design
     */
    public F setDisplayUnit(final Unit<?, ?> unit)
    {
        this.ctx.displayUnit = unit;
        return self();
    }

    /**
     * Set the display unit to use, based on a String representation.
     * @param unit the String representation of the display unit to use
     * @return the object instance for fluent design
     */
    public F setDisplayUnit(final String unit)
    {
        this.ctx.unitString = unit;
        return self();
    }

    /**
     * Set textual mode.
     * @return the object instance for fluent design
     */
    public F textual()
    {
        this.ctx.textual = true;
        return self();
    }

    /**
     * Set display mode.
     * @return the object instance for fluent design
     */
    public F display()
    {
        this.ctx.textual = false;
        return self();
    }

    /**
     * Set textual mode on or off.
     * @param on whether to turn textual mode on or off
     * @return the object instance for fluent design
     */
    public F textual(final boolean on)
    {
        this.ctx.textual = on;
        return self();
    }

    /**
     * Set SI unit mode.
     * @return the object instance for fluent design
     */
    public F siUnits()
    {
        this.ctx.siUnits = true;
        return self();
    }

    /**
     * Set whether the divider symbol is used. This setting only works when siUnits has been switched on.
     * @param on true when the divider symbol is used, false when not
     * @return the object instance for fluent design
     */
    public F divider(final boolean on)
    {
        this.ctx.siDivisionSymbol = on;
        return self();
    }

    /**
     * Set the symbol to use as the prefix for a power to the given String. This setting only works when siUnits has been
     * switched on.
     * @param symbol the string to use for the power prefix
     * @return the object instance for fluent design
     */
    public F powerPrefix(final String symbol)
    {
        this.ctx.siPowerPrefix = symbol;
        return self();
    }

    /**
     * Set the symbol to use as the postfix for a power to the given String. This setting only works when siUnits has been
     * switched on.
     * @param symbol the string to use for the power postfix
     * @return the object instance for fluent design
     */
    public F powerPostfix(final String symbol)
    {
        this.ctx.siPowerPostfix = symbol;
        return self();
    }

    /**
     * Set the dot separator symbol to the given String. This setting only works when siUnits has been switched on.
     * @param symbol the string to use for the dot separator symbol
     * @return the object instance for fluent design
     */
    public F dotSeparator(final String symbol)
    {
        this.ctx.siDotSeparator = symbol;
        return self();
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////// //
    // //////////////////////////////////// METHODS TO FORMAT THE LOCALE ///////////////////////////////////// //
    // /////////////////////////////////////////////////////////////////////////////////////////////////////// //

    /**
     * Apply locale to formatting.
     * @param locale the locale to use
     * @return the object instance for fluent design
     */
    public F setLocale(final Locale locale)
    {
        this.ctx.locale = locale;
        return self();
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////// //
    // ///////////////////////////////// METHODS TO FORMAT ABSOLUTE VALUES /////////////////////////////////// //
    // /////////////////////////////////////////////////////////////////////////////////////////////////////// //

    /**
     * Print the reference.
     * @return the object instance for fluent design
     */
    public F reference()
    {
        this.ctx.printReference = true;
        return self();
    }

    /**
     * Do not print the reference.
     * @return the object instance for fluent design
     */
    public F noReference()
    {
        this.ctx.printReference = false;
        return self();
    }

    /**
     * Indicate whether to print the reference or not.
     * @param on boolean that indicates whether to print the reference or not
     * @return the object instance for fluent design
     */
    public F reference(final boolean on)
    {
        this.ctx.printReference = on;
        return self();
    }

    /**
     * Set the prefix for the reference, e.g., "(relative to ".
     * @param prefix the prefix for the reference
     * @return the object instance for fluent design
     */
    public F setPrefix(final String prefix)
    {
        this.ctx.referencePrefix = prefix;
        return self();
    }

    /**
     * Set the postfix for the reference, e.g., ")".
     * @param postfix the postfix for the reference
     * @return the object instance for fluent design
     */
    public F setPostfix(final String postfix)
    {
        this.ctx.referencePostfix = postfix;
        return self();
    }

}
