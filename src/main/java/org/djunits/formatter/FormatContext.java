package org.djunits.formatter;

import java.util.Locale;

import org.djunits.unit.Unit;

/**
 * FormatContext contains a number of basic settings for formatting a quantity, vector, matrix or quantity table. The settings
 * deal with the number, unit, and locale. Note that this class and its fields are package private. It is not the intention to
 * use or extends the class, since the {@link Format} class can only deal with the fields as expressed in this class, and not
 * with other added fields.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
abstract class FormatContext implements Cloneable
{
    // Number formatting, contains default values

    /** Format mode. */
    FloatFormatMode formatMode = FloatFormatMode.VARIABLE_LENGTH;

    /** Scientific notation: upper case E or lower case e. */
    boolean upperE = true;

    /** Number of decimal digits. */
    int decimals = 3;

    /** Fixed width of the numerical output. */
    int width = 10;

    /** Use grouping separator (e.g., thousands) or not. */
    boolean groupingSeparator = false;

    /** Number format string. */
    String formatString = null;

    // Unit formatting, contains default values

    /** Display unit to use. */
    Unit<?, ?> displayUnit = null;

    /** Display unit to use, based on a String representation. */
    String unitString = null;

    /** Textual representation, e.g., deg instead of degree symbol. */
    boolean textual = false;

    /** SI representation rather than symbol. */
    boolean siUnits = false;

    /** Use division symbol, e.g., TRUE: kgm2/s2, FALSE: kgm2s-2. */
    boolean siDivisionSymbol = true;

    /** Symbol to use as the prefix for a power, e.g., "^" or "&lt;sup&gt;". */
    String siPowerPrefix = "";

    /** Symbol to use as the postfix for a power, e.g., "&lt;/sup&gt;". */
    String siPowerPostfix = "";

    /** Symbol to use for dot separation, e.g., "." to create kg.m2/s2. */
    String siDotSeparator = "";

    // Quantity formatting, contains default values

    /** Use closest SI prefix. E.g., turn 20400 m into "20.4 km". */
    boolean scaleSiPrefixes = false;

    /** minimum 10th power to use SI prefixes for. */
    int minimumPrefixPower = -30;

    /** maximum 10th power to use SI prefixes for. */
    int maximumPrefixPower = 32;

    /** prefix separator between the value and the unit. */
    String unitPrefix = " ";

    /** postfix atring after the unit. */
    String unitPostfix = "";

    // Absolute quantity formatting, contains default values

    /** Print the reference or not. */
    boolean printReference = false;

    /** Prefix to the reference. */
    String referencePrefix = " (";

    /** Postfix to the reference. */
    String referencePostfix = ")";

    // Locale formatting

    /** Used locale for the entire output string (number and unit). */
    Locale locale = null;

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

    @Override
    protected FormatContext clone()
    {
        try
        {
            return (FormatContext) super.clone();
        }
        catch (CloneNotSupportedException exception)
        {
            throw new AssertionError(exception); // cannot happen
        }
    }

}
