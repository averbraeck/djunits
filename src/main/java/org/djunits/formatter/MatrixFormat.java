package org.djunits.formatter;

/**
 * MatrixFormat stores the settings that influence both the value part and the unit part of an output string when formatting a
 * matrix.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class MatrixFormat extends Format<MatrixFormat, MatrixFormatContext>
{
    /**
     * Construct a MatrixFormat object with a given context. Note that the context can be an existing context that is being
     * modified or a default context.
     * @param ctx the quantity format context to use
     */
    protected MatrixFormat(final MatrixFormatContext ctx)
    {
        super(ctx);
    }

    /**
     * Return an instance of MatrixFormat, initialized with the default values.
     * @return an instance of MatrixFormat, initialized with the default values
     */
    public static MatrixFormat defaults()
    {
        return new MatrixFormat(MatrixFormatContext.DEFAULT.clone());
    }

    /**
     * Return an instance of MatrixFormat with the DEFAULT values, which can be changed for all subsequent calls.
     * @return an instance of MatrixFormat with the DEFAULT values
     */
    public static MatrixFormat changeDefaults()
    {
        return new MatrixFormat(MatrixFormatContext.DEFAULT);
    }

    /**
     * Set the start symbol to use for a middle row in a matrix, e.g., "|".
     * @param startSymbol new startSymbol for a matrix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setMiddleRowStartSymbol(final String startSymbol)
    {
        this.ctx.middleRowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol for a middle row for a matrix, e.g., "]".
     * @param endSymbol new endSymbol for a matrix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setMiddleRowEndSymbol(final String endSymbol)
    {
        this.ctx.middleRowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the start symbol to use for the first row for a matrix, e.g., "|".
     * @param startSymbol new start symbol for the first row in a matrix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setFirstRowStartSymbol(final String startSymbol)
    {
        this.ctx.firstRowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for the first row for a matrix, e.g., "|".
     * @param endSymbol new end symbol for the first row in a matrix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setFirstRowEndSymbol(final String endSymbol)
    {
        this.ctx.firstRowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the start symbol to use for the last row for a matrix, e.g., "|".
     * @param startSymbol new start symbol for the last row in a matrix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setLastRowStartSymbol(final String startSymbol)
    {
        this.ctx.lastRowStartSymbol = startSymbol;
        return this;
    }

    /**
     * Set the end symbol to use for the last row for a matrix, e.g., "|".
     * @param endSymbol new end symbol for the last row in a matrix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setLastRowEndSymbol(final String endSymbol)
    {
        this.ctx.lastRowEndSymbol = endSymbol;
        return this;
    }

    /**
     * Set the separator symbol to use between columns in a row for a matrix, e.g., " ".
     * @param separatorSymbol new separatorSymbol for a matrix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setColSeparatorSymbol(final String separatorSymbol)
    {
        this.ctx.colSeparatorSymbol = separatorSymbol;
        return this;
    }

    /**
     * Set the matrix prefix, e.g., "Matrix\n".
     * @param matrixPrefix new table prefix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setMatrixPrefix(final String matrixPrefix)
    {
        this.ctx.matrixPrefix = matrixPrefix;
        return this;
    }

    /**
     * Set the matrix postfix, e.g., "".
     * @param matrixPostfix new table postfix
     * @return MatrixFormat object for fluent design
     */
    public MatrixFormat setMatrixPostfix(final String matrixPostfix)
    {
        this.ctx.matrixPostfix = matrixPostfix;
        return this;
    }

}
