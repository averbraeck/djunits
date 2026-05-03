package org.djunits.formatter;

/**
 * TableFormatContext contains a number of basic settings for formatting a quantity table. Note that this class and its fields
 * are package private. It is not the intention to use or extends the class, since the {@link TableFormat} class can only deal
 * with the fields as expressed in this class, and not with other added fields.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
class TableFormatContext extends FormatContext implements Cloneable
{
    /** The defaults (which can be changed). */
    @SuppressWarnings("checkstyle:staticvariablename")
    static TableFormatContext DEFAULT = new TableFormatContext();

    // Table formatting, contains default values

    /** End symbol for first row. */
    String firstRowStartSymbol = "| ";

    /** End symbol for first row. */
    String firstRowEndSymbol = " |\n";

    /** Start symbol for middle row. */
    String middleRowStartSymbol = "| ";

    /** End symbol for middle row. */
    String middleRowEndSymbol = " |\n";

    /** End symbol for last row. */
    String lastRowStartSymbol = "| ";

    /** End symbol for last row. */
    String lastRowEndSymbol = " |";

    /** Separator symbol for cell within a row. */
    String colSeparatorSymbol = "  ";

    /** Prefix string to use for a quantity table. */
    String tablePrefix = "";

    /** Postfix string to use for a quantity table. */
    String tablePostfix = "";

    @Override
    protected TableFormatContext clone()
    {
        return (TableFormatContext) super.clone();
    }

}
