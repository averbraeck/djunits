package org.djunits.formatter;

/**
 * VectorFormat stores the settings that influence both the value part and the unit part of an output string when formatting a
 * vector.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VectorFormat extends Format<VectorFormat, VectorFormatContext>
{
    /**
     * Construct a VectorFormat object with a given context. Note that the context can be an existing context that is being
     * modified or a default context.
     * @param ctx the quantity format context to use
     */
    public VectorFormat(final VectorFormatContext ctx)
    {
        super(ctx);
    }

    /**
     * Return an instance of VectorFormat, initialized with the default values.
     * @return an instance of VectorFormat, initialized with the default values
     */
    public static VectorFormat with()
    {
        return new VectorFormat(VectorFormatContext.DEFAULT.clone());
    }

    /**
     * Return an instance of VectorFormat with the DEFAULT values, which can be changed for all subsequent calls.
     * @return an instance of VectorFormat with the DEFAULT values
     */
    public static VectorFormat changeDefaults()
    {
        return new VectorFormat(VectorFormatContext.DEFAULT);
    }

    /**
     * Set the start symbol to use for a row vector, e.g., "[".
     * @param startSymbol new startSymbol for a row vector
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setRowStartSymbol(final String startSymbol)
    {
        this.ctx.rowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for a row vector, e.g., "]".
     * @param endSymbol new endSymbol for a row vector
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setRowEndSymbol(final String endSymbol)
    {
        this.ctx.rowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the separator symbol to use for a column vector, e.g., ", ".
     * @param separatorSymbol new separatorSymbol for a column vector
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setColSeparatorSymbol(final String separatorSymbol)
    {
        this.ctx.colSeparatorSymbol = separatorSymbol;
        return this;
    }

    /**
     * Set the start symbol to use for a column vector, e.g., "[".
     * @param startSymbol new startSymbol for a column vector
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setColStartSymbol(final String startSymbol)
    {
        this.ctx.colStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for a column vector, e.g., "]".
     * @param endSymbol new endSymbol for a column vector
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setColEndSymbol(final String endSymbol)
    {
        this.ctx.colEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the separator symbol to use for a row vector, e.g., "\n ".
     * @param separatorSymbol new separatorSymbol for a row vector
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setRowSeparatorSymbol(final String separatorSymbol)
    {
        this.ctx.rowSeparatorSymbol = separatorSymbol;
        return this;
    }

    /**
     * Indicate whether column vectors need to be printed as row vectors.
     * @param colAsRow if true, column vectors will be printed as row vectors
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setColAsRow(final boolean colAsRow)
    {
        this.ctx.colAsRow = colAsRow;
        return this;
    }

    /**
     * Set the column vector prefix, e.g., "Col".
     * @param colVectorPrefix new column vector prefix
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setColVectorPrefix(final String colVectorPrefix)
    {
        this.ctx.colVectorPrefix = colVectorPrefix;
        return this;
    }

    /**
     * Set the row vector prefix, e.g., "Row".
     * @param rowVectorPrefix new row vector prefix
     * @return VectorFormat object for fluent design
     */
    public VectorFormat setRowVectorPrefix(final String rowVectorPrefix)
    {
        this.ctx.rowVectorPrefix = rowVectorPrefix;
        return this;
    }

}
