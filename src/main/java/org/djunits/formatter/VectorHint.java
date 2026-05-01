package org.djunits.formatter;

/**
 * VectorHint stores a number of settings that influence how a vector will be formatted as a String.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VectorHint implements FormatHint
{
    /** Start symbol for row. */
    private String rowStartSymbol = null;

    /** End symbol for row. */
    private String rowEndSymbol = null;

    /** Separator symbol for row. */
    private String rowSeparatorSymbol = null;

    /** Start symbol for column. */
    private String colStartSymbol = null;

    /** End symbol for column. */
    private String colEndSymbol = null;

    /** Separator symbol for column. */
    private String colSeparatorSymbol = null;

    /** Print column vector as row vector. */
    private Boolean colAsRow = null;

    /** Prefix to use for a column vector. */
    private String colVectorPrefix = null;

    /** Prefix to use for a row vector. */
    private String rowVectorPrefix = null;

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        if (this.rowStartSymbol != null)
            ctx.vectorRowStartSymbol = this.rowStartSymbol;
        if (this.rowEndSymbol != null)
            ctx.vectorRowEndSymbol = this.rowEndSymbol;
        if (this.rowSeparatorSymbol != null)
            ctx.vectorRowSeparatorSymbol = this.rowSeparatorSymbol;
        if (this.colStartSymbol != null)
            ctx.vectorColStartSymbol = this.colStartSymbol;
        if (this.colEndSymbol != null)
            ctx.vectorColEndSymbol = this.colEndSymbol;
        if (this.colSeparatorSymbol != null)
            ctx.vectorColSeparatorSymbol = this.colSeparatorSymbol;
        if (this.colAsRow != null)
            ctx.vectorColAsRow = this.colAsRow;
        if (this.colVectorPrefix != null)
            ctx.vectorColPrefix = this.colVectorPrefix;
        if (this.rowVectorPrefix != null)
            ctx.vectorRowPrefix = this.rowVectorPrefix;
    }

    /**
     * Set the start symbol to use for a row vector, e.g., "[".
     * @param startSymbol new startSymbol for a row vector
     * @return VectorHint object for fluent design
     */
    public VectorHint setRowStartSymbol(final String startSymbol)
    {
        this.rowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for a row vector, e.g., "]".
     * @param endSymbol new endSymbol for a row vector
     * @return VectorHint object for fluent design
     */
    public VectorHint setRowEndSymbol(final String endSymbol)
    {
        this.rowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the separator symbol to use for a column vector, e.g., ", ".
     * @param separatorSymbol new separatorSymbol for a column vector
     * @return VectorHint object for fluent design
     */
    public VectorHint setColSeparatorSymbol(final String separatorSymbol)
    {
        this.colSeparatorSymbol = separatorSymbol;
        return this;
    }

    /**
     * Set the start symbol to use for a column vector, e.g., "[".
     * @param startSymbol new startSymbol for a column vector
     * @return VectorHint object for fluent design
     */
    public VectorHint setColStartSymbol(final String startSymbol)
    {
        this.colStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for a column vector, e.g., "]".
     * @param endSymbol new endSymbol for a column vector
     * @return VectorHint object for fluent design
     */
    public VectorHint setColEndSymbol(final String endSymbol)
    {
        this.colEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the separator symbol to use for a row vector, e.g., "\n ".
     * @param separatorSymbol new separatorSymbol for a row vector
     * @return VectorHint object for fluent design
     */
    public VectorHint setRowSeparatorSymbol(final String separatorSymbol)
    {
        this.rowSeparatorSymbol = separatorSymbol;
        return this;
    }

    /**
     * Indicate whether column vectors need to be printed as row vectors.
     * @param colAsRow if true, column vectors will be printed as row vectors
     * @return VectorHint object for fluent design
     */
    public VectorHint setColAsRow(final boolean colAsRow)
    {
        this.colAsRow = colAsRow;
        return this;
    }

    /**
     * Set the column vector prefix, e.g., "Col".
     * @param colVectorPrefix new colPrefix
     * @return VectorHint object for fluent design
     */
    public VectorHint setColVectorPrefix(final String colVectorPrefix)
    {
        this.colVectorPrefix = colVectorPrefix;
        return this;
    }

    /**
     * Set the row vector prefix, e.g., "Col".
     * @param rowVectorPrefix set rowPrefix
     * @return VectorHint object for fluent design
     */
    public VectorHint setRowVectorPrefix(final String rowVectorPrefix)
    {
        this.rowVectorPrefix = rowVectorPrefix;
        return this;
    }

}
