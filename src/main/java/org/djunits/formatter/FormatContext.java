package org.djunits.formatter;

import java.util.Locale;

import org.djunits.formatter.NumberHint.FloatFormatMode;
import org.djunits.unit.Unit;

/**
 * FormatContext contains a number of settings for formatting a quantity, vector, matrix or quantity table. Note that this class
 * and its fields are package private. It is not the intention to use or extends the class, since the Formatter class can only
 * deal with the fields as expressed in this class, and not with other added fields. An explicit extension mechanism is present
 * that does not need the FormatContext to expose its inner structure.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
final class FormatContext
{
    // Number formatting from NumberHint, contains default values

    /** Format mode. */
    FloatFormatMode formatMode = FloatFormatMode.FIXED_WITH_SCI_FALLBACK;

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

    // Unit formatting from UnitHint, contains default values

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

    // Quantity formatting from QuantityHint, contains default values

    /** Use closest SI prefix. E.g., turn 20400 m into "20.4 km". */
    boolean scaleSiPrefixes = false;

    /** minimum 10th power to use SI prefixes for. */
    int minimumPrefixPower = -30;

    /** maximum 10th power to use SI prefixes for. */
    int maximumPrefixPower = 32;
    
    /** separator between the value and the unit. */
    String unitSeparator = " ";

    // Absolute quantity formatting from QuantityHint, contains default values
    
    /** Print the reference or not. */
    boolean printReference = false;

    // Locale formatting from LocaleHint

    /** Used locale for the entire output string (number and unit). */
    Locale locale = null;

    // Vector formatting from VectorHint
    
    /** Start symbol. */
    String vectorStartSymbol = "[";

    /** End symbol. */
    String vectorEndSymbol = "]";

    /** Separator symbol. */
    String vectorSeparatorSymbol = ", ";

    /** Print column vector as row vector. */
    boolean vectorColAsRow = true;
    
    /** Prefix to use for a column vector. */
    String vectorColPrefix = "Col";
    
    /** Prefix to use for a row vector. */
    String vectorRowPrefix = "Row";

}
