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

    /** Default number of fraction digits. */
    public static final int DEFAULTPRECISION = 3;

    /** Default size. */
    public static final int DEFAULTSIZE = 10;

    /** Format constructor for mantissa plus exponent notation. */
    private static String exponentFormat = "%%%d.%dE";

    /** Format constructor for mantissa-only notation. */
    private static final String FLOATFORMAT = "%%%d.%df";

    /**
     * Minimum value to display in non-scientific / non-engineering notation. The value 0.0001 ensures that we switch to
     * mantissa + exponent notation around the point were we're about to lose precision due to underflow. This is the point
     * where the width of the zeros before the first non-zero digit starts to exceed the width of a E + exponent value string.
     */
    private static final double EFORMATLIMIT = 0.0001;

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
        return format(value, DEFAULTSIZE, DEFAULTPRECISION);
    }
    
    /**
     * Switch to/from upper case E for exponent indicator. The default is to use upper case.
     * @param upper if true; an upper case E will be used; if false; a lower case e will be used
     */
    public static void setUpperCaseFormat(final boolean upper)
    {
        exponentFormat = upper ? "%%%d.%dE" : "%%%d.%de";
    }

    /**
     * Format a double in Engineering format using <code>DEFAULTSIZE</code> room.
     * @param val the value to format
     * @return the formatted value
     */
    public static String formatEngineering(final double val)
    {
        return formatEngineering(val, DEFAULTSIZE);
    }

    /**
     * Format a double in Engineering format.
     * @param val the value to format
     * @param room the width in characters of the result (minimum value is 10; values below this limit will be treated as 10)
     * @return the formatted value
     */
    public static String formatEngineering(final double val, final int room)
    {
        if (room < 10)
        {
            return format(val, 10); // The EngineeringFormatter needs at least 10 positions
        }
        if (Double.isNaN(val) || 0d == val || Double.isInfinite(val))
        {
            String format = String.format(FLOATFORMAT, room, room);
            return padd(String.format(format, val), room);
        }
        double absVal = Math.abs(val);
        int roomForSignAndFraction = val > 0 ? 2 : 3;
        // Floating point values should show at least one digit after the radix symbol (dot)
        // max is the maximum value to display in non-scientific / non-engineering notation
        double max = Math.pow(10, room - roomForSignAndFraction);
        if (absVal < max - 0.5 && absVal > EFORMATLIMIT)
        {
            // Express as floating point number.
            String format = String.format(FLOATFORMAT, room, room - 2);
            String result = String.format(format, val);
            int length = result.length();
            if (length > room)
            {
                format = String.format(FLOATFORMAT, room, room - 2 + room - length);
                result = String.format(format, val);
            }
            return result;
        }
        // Express in scientific notation using at least 2 digits for the exponent.
        int roomForSignRadixAndExponent = val > 0 ? 6 : 7;
        String format = String.format(exponentFormat, room, room - roomForSignRadixAndExponent);
        String result = String.format(format, val);
        int length = result.length();
        if (length > room) // 3-digit exponent?
        {
            format = String.format(exponentFormat, room, room - length + room - roomForSignRadixAndExponent);
            result = String.format(format, val);
        }
        result = convertToEngineering(result);
        if (result.length() < room) // Exponent 100, or 101 was reduced to 99 which is one digit shorter
        {
            format = String.format(exponentFormat, room, 1 + room - length + room - roomForSignRadixAndExponent);
            result = convertToEngineering(String.format(format, val));
        }
        return result;
    }

    /**
     * Make the exponent of a floating point value a multiple of 3. Assumes that the first dot or comma is the radix symbol and
     * 'e' or 'E' is used at the exponent symbol.
     * @param in String representation of a floating point value
     * @return The engineering formatted value
     */
    public static String convertToEngineering(final String in)
    {
        int positionOfE = in.indexOf("E");
        if (positionOfE < 0)
        {
            positionOfE = in.indexOf("e");
            if (positionOfE < 0)
            {
                return in;
            }
        }
        StringBuilder result = new StringBuilder();
        int exponent = Integer.parseInt(in.substring(positionOfE + 1));
        if (0 == exponent % 3)
        {
            return in;
        }

        String radix = null;
        int pos;
        for (pos = 0; pos < positionOfE; pos++)
        {
            String symbol = in.substring(pos, pos + 1);
            Character c = symbol.charAt(0);
            if (c != '.' && c != ',' || null != radix)
            {
                result.append(symbol);
            }
            else
            {
                radix = symbol;
                break;
            }
        }
        if (null == radix)
        {
            return in; // No radix symbol encountered
        }
        pos++;
        // Shift the radix to the right
        while (0 != exponent % 3)
        {
            String symbol = in.substring(pos, pos + 1);
            result.append(symbol);
            pos++;
            exponent--;
        }
        result.append(radix);
        for (; pos < positionOfE; pos++)
        {
            result.append(in.substring(pos, pos + 1));
        }
        result.append(in.substring(positionOfE, positionOfE + 1));
        String exponentString = String.format("%+03d", exponent);
        result.append(exponentString);
        return result.toString();
    }

    /**
     * Extend a String with spaces, or trim it to reach a specified length.
     * @param in input string
     * @param width length of the result
     * @return the extended or trimmed input string
     */
    public static String padd(final String in, final int width)
    {
        String format = String.format("%%%d.%ds", width, width);
        return String.format(format, in);
    }

}
