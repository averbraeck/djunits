package org.djunits.formatter;

import java.util.Locale;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.Units;
import org.djunits.value.Value;

/**
 * Formatter of quantities, vectors and matrices according to formatting hints.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@SuppressWarnings({"checkstyle:needbraces", "checkstyle:visibilitymodifier"})
public abstract class Formatter
{
    /** the format context. */
    final FormatContext ctx;

    /** the value (quantity, vector, matrix) with a display unit. */
    final Value<?, ?> value;

    /** the unit to express the value in. */
    Unit<?, ?> unit;

    /** the formatted unit. */
    String unitStr = null;

    /** using SI value or valueInUnit for the calculated unit. */
    boolean useSi = false;

    /**
     * @param value the value to format
     * @param ctx the format context
     */
    Formatter(final Value<?, ?> value, final FormatContext ctx)
    {
        this.ctx = ctx;
        this.value = value;
        this.unit = value.getDisplayUnit();
    }

    /**
     * Format the value(s) and unit and return the String representation.
     * @return the formatted value and unit
     */
    abstract String format();

    /**
     * Format the unit according to the context settings.
     */
    abstract void formatUnit();

    /**
     * Check if SI formatting needs to be applied.
     * @return whether SI formatting was applied
     */
    boolean checkSiUnits()
    {
        if (!this.ctx.siUnits)
            return false;
        this.unit = this.unit.siUnit();
        this.useSi = true;
        this.unitStr = this.unit.siUnit().toString(this.ctx.siDivisionSymbol, this.ctx.siDotSeparator, this.ctx.siPowerPrefix,
                this.ctx.siPowerPostfix);
        return true;
    }

    /**
     * Apply the unit string if present.
     * @return whether unit string formatting was applied
     */
    boolean checkUnitString()
    {
        if (this.ctx.unitString != null)
        {
            try
            {
                this.unit = Units.resolve(this.unit.getClass(), this.ctx.unitString);
                this.useSi = false;
                return true;
            }
            catch (Exception e)
            {
                // silently ignore
            }
        }
        return false;
    }

    /**
     * Apply the display unit if present.
     * @return whether display unit formatting was applied
     */
    boolean checkDisplayUnit()
    {
        if (this.ctx.displayUnit != null)
        {
            try
            {
                this.unit = this.ctx.displayUnit;
                this.useSi = false;
                return true;
            }
            catch (Exception e)
            {
                // silently ignore
            }
        }
        return false;
    }

    /**
     * Format a quantity according to a number of FormatHints. Note that this method might not be thread-safe for setting the
     * default Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param quantity the quantity to format
     * @param hints the format hints
     * @return a String with a formatted quantity, matching the FormatHints as closely as possible
     */
    public static String formatQuantity(final Quantity<?> quantity, final FormatHint... hints)
    {
        FormatContext ctx = new FormatContext();
        for (var hint : hints)
        {
            hint.applyTo(ctx);
        }

        Locale savedLocale = null;
        try
        {
            if (ctx.locale != null)
            {
                savedLocale = Locale.getDefault();
                Locale.setDefault(ctx.locale);
            }
            return new QuantityFormatter(quantity, ctx).format();
        }
        finally
        {
            if (savedLocale != null)
            {
                Locale.setDefault(savedLocale);
            }
        }
    }

    /**
     * Format an absolute quantity according to a number of FormatHints. Note that this method might not be thread-safe for
     * setting the default Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param absQuantity the absolute quantity to format
     * @param hints the format hints
     * @return a String with a formatted quantity, matching the FormatHints as closely as possible
     */
    public static String formatAbsQuantity(final AbsQuantity<?, ?, ?> absQuantity, final FormatHint... hints)
    {
        FormatContext ctx = new FormatContext();
        for (var hint : hints)
        {
            hint.applyTo(ctx);
        }

        Locale savedLocale = null;
        try
        {
            if (ctx.locale != null)
            {
                savedLocale = Locale.getDefault();
                Locale.setDefault(ctx.locale);
            }
            return new AbsQuantityFormatter(absQuantity, ctx).format();
        }
        finally
        {
            if (savedLocale != null)
            {
                Locale.setDefault(savedLocale);
            }
        }
    }

    /**
     * Format a value according to the context settings.
     * @param val the value to format
     * @return the formatted value according to the context settings
     */
    String formatValue(final double val)
    {
        return switch (this.ctx.formatMode)
        {
            case VARIABLE_LENGTH -> formatVariableLength(val);
            case FIXED_FLOAT -> formatFixedFloat(val);
            case SCIENTIFIC_ALWAYS -> formatScientific(val);
            case ENGINEERING_ALWAYS -> formatEngineering(val);
            case FIXED_WITH_SCI_FALLBACK -> formatFixedSciFallback(val);
            case FIXED_WITH_ENG_FALLBACK -> formatFixedEngFallback(val);
            case FORMAT_STRING -> String.format(this.ctx.formatString, val);
            default -> formatFixedFloat(val);
        };
    }

    /**
     * Format a value with variable length.
     * @param val the value to format
     * @return a formatted value with variable length
     */
    String formatVariableLength(final double val)
    {
        return String.format(this.ctx.upperE ? "%G" : "%g", val);
    }

    /**
     * Format a value with a fixed floating point length and a given number of decimals.
     * @param val the value to format
     * @return a formatted value with a fixed floating point length and a given number of decimals
     */
    String formatFixedFloat(final double val)
    {
        String fmt = "%" + this.ctx.width + "." + this.ctx.decimals + "f";
        return String.format(fmt, this.value);
    }

    /**
     * Format a value using scientific notation with a fixed length and a given number of decimals.
     * @param val the value to format
     * @return a formatted value using scientific notation with a fixed length and a given number of decimals
     */
    String formatScientific(final double val)
    {
        String fmt = "%" + this.ctx.width + "." + this.ctx.decimals + (this.ctx.upperE ? "E" : "e");
        return String.format(fmt, this.value);
    }

    /**
     * Format a value using engineering notation with a fixed length and a given number of decimals.
     * @param val the value to format
     * @return a formatted value using engineering notation with a fixed length and a given number of decimals
     */
    String formatEngineering(final double val)
    {
        if (val == 0.0)
        {
            return formatFixedFloat(0.0);
        }

        double abs = Math.abs(val);
        int exp = (int) Math.floor(Math.log10(abs));
        int engExp = exp - (exp % 3);

        double mantissa = val / Math.pow(10, engExp);

        // Mantissa formatted as fixed
        String mantFmt = "%." + this.ctx.decimals + "f";
        String mant = String.format(mantFmt, mantissa);
        String result = mant + "e" + String.format("%+d", engExp);
        return pad(result, this.ctx.width);
    }

    /**
     * Format a value using fixed length, but when it does not fit, fall back to scientific notation.
     * @param val the value to format
     * @return a formatted value using fixed length, but when it does not fit, fall back to scientific notation.
     */
    String formatFixedSciFallback(final double val)
    {
        String s = formatFixedFloat(val);
        if (s.length() <= this.ctx.width)
            return s;
        return formatScientific(val);
    }

    /**
     * Format a value using fixed length, but when it does not fit, fall back to engineering notation.
     * @param val the value to format
     * @return a formatted value using fixed length, but when it does not fit, fall back to engineering notation.
     */
    String formatFixedEngFallback(final double val)
    {
        String s = formatFixedFloat(val);
        if (s.length() <= this.ctx.width)
            return s;
        return formatEngineering(val);
    }

    /**
     * Pad a string with spaces.
     * @param s the string to pad
     * @param width the width
     * @return a padded string
     */
    private static String pad(final String s, final int width)
    {
        if (s.length() >= width)
            return s;
        return String.format("%" + width + "s", s);
    }

}
