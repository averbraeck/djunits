package org.djunits.formatter;

/**
 * TableFormat stores the settings that influence both the value part and the unit part of an output string when formatting a
 * table.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class TableFormat extends Format<TableFormat, TableFormatContext>
{
    /**
     * Construct a TableFormat object with a given context. Note that the context can be an existing context that is being
     * modified or a default context.
     * @param ctx the quantity format context to use
     */
    public TableFormat(final TableFormatContext ctx)
    {
        super(ctx);
    }

    /**
     * Return an instance of TableFormat, initialized with the default values.
     * @return an instance of TableFormat, initialized with the default values
     */
    public static TableFormat with()
    {
        return new TableFormat(TableFormatContext.DEFAULT.clone());
    }

    /**
     * Return an instance of TableFormat with the DEFAULT values, which can be changed for all subsequent calls.
     * @return an instance of TableFormat with the DEFAULT values
     */
    public static TableFormat changeDefaults()
    {
        return new TableFormat(TableFormatContext.DEFAULT);
    }

    /**
     * Set the start symbol to use for a middle row in a table, e.g., "|".
     * @param startSymbol new startSymbol for a table
     * @return TableFormat object for fluent design
     */
    public TableFormat setMiddleRowStartSymbol(final String startSymbol)
    {
        this.ctx.middleRowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol for a middle row for a table, e.g., "]".
     * @param endSymbol new endSymbol for a table
     * @return TableFormat object for fluent design
     */
    public TableFormat setMiddleRowEndSymbol(final String endSymbol)
    {
        this.ctx.middleRowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the start symbol to use for the first row for a table, e.g., "|".
     * @param startSymbol new start symbol for the first row in a table
     * @return TableFormat object for fluent design
     */
    public TableFormat setFirstRowStartSymbol(final String startSymbol)
    {
        this.ctx.firstRowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for the first row for a table, e.g., "|".
     * @param endSymbol new end symbol for the first row in a table
     * @return TableFormat object for fluent design
     */
    public TableFormat setFirstRowEndSymbol(final String endSymbol)
    {
        this.ctx.firstRowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the start symbol to use for the last row for a table, e.g., "|".
     * @param startSymbol new start symbol for the last row in a table
     * @return TableFormat object for fluent design
     */
    public TableFormat setLastRowStartSymbol(final String startSymbol)
    {
        this.ctx.lastRowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for the last row for a table, e.g., "|".
     * @param endSymbol new end symbol for the last row in a table
     * @return TableFormat object for fluent design
     */
    public TableFormat setLastRowEndSymbol(final String endSymbol)
    {
        this.ctx.lastRowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the separator symbol to use between columns in a row for a table, e.g., " ".
     * @param separatorSymbol new separatorSymbol for a table
     * @return TableFormat object for fluent design
     */
    public TableFormat setColSeparatorSymbol(final String separatorSymbol)
    {
        this.ctx.colSeparatorSymbol = separatorSymbol;
        return this;
    }

    /**
     * Set the table prefix, e.g., "Table\n".
     * @param tablePrefix new table prefix
     * @return TableFormat object for fluent design
     */
    public TableFormat setTablePrefix(final String tablePrefix)
    {
        this.ctx.tablePrefix = tablePrefix;
        return this;
    }

    /**
     * Set the table postfix, e.g., "".
     * @param tablePostfix new table postfix
     * @return TableFormat object for fluent design
     */
    public TableFormat setTablePostfix(final String tablePostfix)
    {
        this.ctx.tablePostfix = tablePostfix;
        return this;
    }

}
