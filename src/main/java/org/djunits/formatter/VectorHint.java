package org.djunits.formatter;

/**
 * VectorHint stores a number of settings that influence how a vector will be formatted as a String.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VectorHint implements FormatHint
{
    /** Start symbol. */
    private String startSymbol = "[";

    /** End symbol. */
    private String endSymbol = "]";

    /** Separator symbol. */
    private String separatorSymbol = ", ";

    /** Print column vector as row vector. */
    private boolean colAsRow = true;

    /** Prefix to use for a column vector. */
    private String colVectorPrefix = "Col";

    /** Prefix to use for a row vector. */
    private String rowVectorPrefix = "Row";

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        ctx.vectorStartSymbol = this.startSymbol;
        ctx.vectorEndSymbol = this.endSymbol;
        ctx.vectorSeparatorSymbol = this.separatorSymbol;
        ctx.vectorColAsRow = this.colAsRow;
        ctx.vectorColPrefix = this.colVectorPrefix;
        ctx.vectorRowPrefix = this.rowVectorPrefix;
    }

    /**
     * Set the start symbol to use, e.g., "[".
     * @param startSymbol new startSymbol
     * @return VectorHint object for fluent design
     */
    public VectorHint setStartSymbol(final String startSymbol)
    {
        this.startSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use, e.g., "]".
     * @param endSymbol new endSymbol
     * @return VectorHint object for fluent design
     */
    public VectorHint setEndSymbol(final String endSymbol)
    {
        this.endSymbol = endSymbol;
        return this;
    }

    /**
     * Set the separator symbol to use, e.g., ", ".
     * @param separatorSymbol new separatorSymbol
     * @return VectorHint object for fluent design
     */
    public VectorHint setSeparatorSymbol(final String separatorSymbol)
    {
        this.separatorSymbol = separatorSymbol;
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
