package org.djunits.formatter;

import java.util.Locale;

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
    // Number formatting

    /** Scientific notation. */
    Boolean scientificNotation = null;

    /** Number of decimal digits. */
    Integer decimalDigits = null;

    /** Maximum width of the numerical output. */
    Integer maxWidth = null;

    /** Fixed width of the numerical output. */
    Integer fixedWidth = null;

    // Unit formatting

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

    /** Symbol to use for start of power, e.g., "^" or "&lt;sup&gt;". */
    String siPowerStart = "";

    /** Symbol to use for end of power, e.g., "&lt;/sup&gt;". */
    String siPowerEnd = "";

    /** Symbol to use for dot separation, e.g., "." to create kg.m2/s2. */
    String siDotSeparator = "";

    // Quantity formatting

    /** Use closest SI prefix. E.g., turn 20400 m into "20.4 km". */
    boolean scaleSiPrefixes = false;

    /** minimum 10th power to use SI prefixes for. */
    int minimumPrefixPower = -30;

    /** maximum 10th power to use SI prefixes for. */
    int maximumPrefixPower = 32;

    // Locale formatting

    /** Used locale for the entire output string (number and unit). */
    Locale locale = null;

}
