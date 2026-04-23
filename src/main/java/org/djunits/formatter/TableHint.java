package org.djunits.formatter;

/**
 * TableHint stores a number of settings that influence how a table or matrix will be formatted as a String.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class TableHint implements FormatHint
{
    /** Start symbol for first row. */
    private String firstRowStartSymbol = null;

    /** End symbol for first row. */
    private String firstRowEndSymbol = null;

    /** Start symbol for middle row. */
    private String rowStartSymbol = null;

    /** End symbol for middle row. */
    private String rowEndSymbol = null;

    /** Start symbol for last row. */
    private String lastRowStartSymbol = null;

    /** End symbol for last row. */
    private String lastRowEndSymbol = null;

    /** Separator symbol for column within a row. */
    private String colSeparatorSymbol = null;

    /** Prefix string to use for a table or matrix. */
    private String tablePrefix = null;

    /** Postfix string to use for a table or matrix. */
    private String tablePostfix = null;

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        if (this.firstRowStartSymbol != null)
            ctx.tableFirstRowStartSymbol = this.firstRowStartSymbol;
        if (this.firstRowEndSymbol != null)
            ctx.tableFirstRowEndSymbol = this.firstRowEndSymbol;
        if (this.rowStartSymbol != null)
            ctx.tableMiddleRowStartSymbol = this.rowStartSymbol;
        if (this.rowEndSymbol != null)
            ctx.tableMiddleRowEndSymbol = this.rowEndSymbol;
        if (this.lastRowStartSymbol != null)
            ctx.tableLastRowStartSymbol = this.lastRowStartSymbol;
        if (this.lastRowEndSymbol != null)
            ctx.tableLastRowEndSymbol = this.lastRowEndSymbol;
        if (this.colSeparatorSymbol != null)
            ctx.tableColSeparatorSymbol = this.colSeparatorSymbol;
        if (this.tablePrefix != null)
            ctx.tablePrefix = this.tablePrefix;
        if (this.tablePostfix != null)
            ctx.tablePostfix = this.tablePostfix;
    }

    /**
     * Set the start symbol to use for a table or matrix, e.g., "[".
     * @param startSymbol new startSymbol for a table or matrix
     * @return TableHint object for fluent design
     */
    public TableHint setRowStartSymbol(final String startSymbol)
    {
        this.rowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol for a row for a table or matrix, e.g., "]".
     * @param endSymbol new endSymbol for a table or matrix
     * @return TableHint object for fluent design
     */
    public TableHint setRowEndSymbol(final String endSymbol)
    {
        this.rowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the start symbol to use for the first row for a table or matrix, e.g., "|".
     * @param startSymbol new start symbol for the first row in a table or matrix
     * @return TableHint object for fluent design
     */
    public TableHint setFirstRowStartSymbol(final String startSymbol)
    {
        this.firstRowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for the first row for a table or matrix, e.g., "|".
     * @param endSymbol new end symbol for the first row in a table or matrix
     * @return TableHint object for fluent design
     */
    public TableHint setFirstRowEndSymbol(final String endSymbol)
    {
        this.firstRowEndSymbol = endSymbol;
        return this;
    }
    
    /**
     * Set the start symbol to use for the last row for a table or matrix, e.g., "|".
     * @param startSymbol new start symbol for the last row in a table or matrix
     * @return TableHint object for fluent design
     */
    public TableHint setLastRowStartSymbol(final String startSymbol)
    {
        this.lastRowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for the last row for a table or matrix, e.g., "|".
     * @param endSymbol new end symbol for the last row in a table or matrix
     * @return TableHint object for fluent design
     */
    public TableHint setLastRowEndSymbol(final String endSymbol)
    {
        this.lastRowEndSymbol = endSymbol;
        return this;
    }
    
    /**
     * Set the separator symbol to use between columns in a row for a table or matrix, e.g., " ".
     * @param separatorSymbol new separatorSymbol for a table or matrix
     * @return TableHint object for fluent design
     */
    public TableHint setColSeparatorSymbol(final String separatorSymbol)
    {
        this.colSeparatorSymbol = separatorSymbol;
        return this;
    }

    /**
     * Set the table or matrix prefix, e.g., "Matrix\n".
     * @param tablePrefix new table prefix
     * @return TableHint object for fluent design
     */
    public TableHint setTablePrefix(final String tablePrefix)
    {
        this.tablePrefix = tablePrefix;
        return this;
    }

    /**
     * Set the table or matrix postfix, e.g., "".
     * @param tablePostfix new table postfix
     * @return TableHint object for fluent design
     */
    public TableHint setTablePostfix(final String tablePostfix)
    {
        this.tablePostfix = tablePostfix;
        return this;
    }

}
