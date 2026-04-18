package org.djunits.formatter;

import java.util.Locale;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.Units;
import org.djunits.unit.si.PrefixType;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;

/**
 * Formatter of quantities, vectors and matrices according to formatting hints.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@SuppressWarnings("checkstyle:needbraces")
public final class Formatter
{
    /** the quantity. */
    private final Quantity<?> quantity;

    /** the format context. */
    private final FormatContext ctx;

    /** the value as a double, expressed in the unit. */
    private double value;

    /** the unit to express the value in. */
    private Unit<?, ?> unit;

    /** the formatted unit. */
    private String unitStr;

    /** the formatted value. */
    private String valueStr;

    /**
     * @param quantity the quantity to fromat
     * @param ctx the format context
     */
    private Formatter(final Quantity<?> quantity, final FormatContext ctx)
    {
        this.quantity = quantity;
        this.ctx = ctx;
        this.value = this.quantity.getInUnit();
        this.unit = this.quantity.getDisplayUnit();
    }

    /**
     * Return the quantity, formatted according to the context settings.
     * @return the formatted quantity
     */
    String format()
    {
        formatUnit();
        formatValue();
        return this.valueStr + " " + this.unitStr;
    }

    /**
     * Format the unit according to the context settings.
     */
    private void formatUnit()
    {
        boolean formatted = checkSiUnits();
        if (!formatted)
            formatted = checkUnitString();
        if (!formatted)
            formatted = checkDisplayUnit();
        checkScaleSiPrefixes();
        if (this.unitStr == null)
            this.unitStr = this.ctx.textual ? this.unit.getTextualAbbreviation() : this.unit.getTextualAbbreviation();
    }

    /**
     * Format the value according to the context settings.
     */
    private void formatValue()
    {
        this.valueStr = format(this.value);
    }

    /**
     * Check if SI formatting needs to be applied.
     * @return whether SI formatting was applied
     */
    private boolean checkSiUnits()
    {
        if (!this.ctx.siUnits)
            return false;
        this.unit = this.unit.siUnit();
        this.value = this.quantity.si();
        this.unitStr = this.unit.siUnit().toString(this.ctx.siDivisionSymbol, this.ctx.siDotSeparator, this.ctx.siPowerStart,
                this.ctx.siPowerEnd);
        return true;
    }

    /**
     * Apply the unit string if present.
     * @return whether unit string formatting was applied
     */
    @SuppressWarnings("unchecked")
    private boolean checkUnitString()
    {
        if (this.ctx.unitString != null)
        {
            try
            {
                var newUnit = Units.resolve(this.unit.getClass(), this.ctx.unitString);
                this.value = this.quantity.getInUnit(newUnit);
                this.unit = newUnit; // change unit only after successful getInUnit()
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
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SuppressWarnings("unchecked")
    private <Q extends Quantity<Q>, U extends Unit<U, Q>> boolean checkDisplayUnit()
    {
        if (this.ctx.displayUnit != null)
        {
            try
            {
                var newUnit = this.ctx.displayUnit;
                this.value = ((Q) this.quantity).getInUnit((U) newUnit);
                this.unit = newUnit; // change unit only after successful getInUnit()
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
     * @param <Q> the quantity type
     */
    @SuppressWarnings("unchecked")
    private <Q extends Quantity<Q>> boolean checkScaleSiPrefixes()
    {
        Q q = (Q) this.quantity;
        if (this.ctx.scaleSiPrefixes && this.unit.getSiPrefix() != null)
        {
            PrefixType type = this.unit.getSiPrefix().getType();
            double si = q.si();
            int power = 3 * (int) (Math.log10(si) / 3.0);
            if (power >= this.ctx.minimumPrefixPower && power <= this.ctx.maximumPrefixPower)
            {
                switch (type)
                {
                    case UNIT:
                    {
                        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(power, SIPrefixes.getSiPrefix(""));
                        String key = prefix.getDefaultTextualPrefix() + q.getDisplayUnit().getBaseUnit().getId();
                        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
                        this.value = si / prefix.getFactor();
                        return true;
                    }
                    case KILO:
                    {
                        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(power - 3, SIPrefixes.getSiPrefix(""));
                        String key = prefix.getDefaultTextualPrefix() + q.getDisplayUnit().getBaseUnit().getId();
                        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
                        this.value = si / prefix.getFactor();
                        return true;
                    }
                    case PER_UNIT:
                    {
                        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(-power, SIPrefixes.getSiPrefix(""));
                        String key = prefix.getDefaultTextualPrefix() + q.getDisplayUnit().getBaseUnit().getId();
                        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
                        this.value = si * prefix.getFactor();
                        return true;
                    }
                    case PER_KILO:
                    {
                        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(-power + 3, SIPrefixes.getSiPrefix(""));
                        String key = prefix.getDefaultTextualPrefix() + q.getDisplayUnit().getBaseUnit().getId();
                        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
                        this.value = si * prefix.getFactor();
                        return true;
                    }
                    default:
                        // silently ignore
                }
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
            return new Formatter(quantity, ctx).format();
        }
        finally
        {
            if (savedLocale != null)
            {
                Locale.setDefault(savedLocale);
            }
        }
    }

    /** Default number of fraction digits. */
    public static final int DEFAULTPRECISION = 3;

    /**
     * Build a format string.
     * @param width the number of characters in the result
     * @param precision the number of fractional digits in the result
     * @param converter the format conversion specifier
     * @return suitable for formatting a float or double
     */
    static String formatString(final int width, final int precision, final String converter)
    {
        return String.format("%%%d.%d%s", width, precision, converter);
    }

    /**
     * Format a floating point value.
     * @param value the value to format
     * @param width the number of characters in the result
     * @param precision the number of fractional digits in the result
     * @return the formatted floating point value
     */
    public static String format(final double value, final int width, final int precision)
    {
        if (0 == value || Math.abs(value) > 0.01 && Math.abs(value) < 9999.0)
        {
            return String.format(formatString(width, precision, "f"), value);
        }
        return String.format(formatString(width, precision, "e"), value);
    }

    /**
     * Format a floating point value.
     * @param value the value to format
     * @param width the number of characters in the result
     * @return the formatted floating point value
     */
    public static String format(final double value, final int width)
    {
        return format(value, width, Formatter.DEFAULTPRECISION);
    }

    /**
     * Format a floating point value.
     * @param value the value to format
     * @return the formatted floating point value
     */
    public static String format(final double value)
    {
        return format(value, Format.DEFAULTSIZE, Formatter.DEFAULTPRECISION);
    }
}
