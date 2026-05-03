package org.djunits.formatter;

import java.util.Locale;

import org.djunits.vecmat.def.AbsMatrix;
import org.djunits.vecmat.def.Matrix;

/**
 * MatrixFormatter formats a matrix as a String, using the settings of the {@link MatrixFormatContext}. The
 * {@link MatrixFormatContext} is filled by setting properties of the {@link MatrixFormatContext}. Note that there is no
 * guarantee that the format can always be honored. As an example, when the required width is too small to fit the answer, the
 * output will show the correct result, but violate the width setting.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class MatrixFormatter extends Formatter<MatrixFormatContext>
{
    /**
     * @param matrix the matrix to format
     * @param ctx the format context
     */
    MatrixFormatter(final Matrix<?, ?, ?, ?, ?> matrix, final MatrixFormatContext ctx)
    {
        super(matrix, ctx);
    }

    /**
     * Return the value as a matrix (or matrix).
     * @return the value as a matrix (or matrix)
     */
    Matrix<?, ?, ?, ?, ?> matrix()
    {
        return (Matrix<?, ?, ?, ?, ?>) this.value;
    }

    /**
     * Format a matrix according to a number of FormatHints. Note that this method might not be thread-safe for setting the
     * default Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param matrix the matrix to format
     * @param matrixFormat the format to apply to the matrix
     * @return a String with a formatted matrix, matching the FormatHints as closely as possible
     */
    public static String format(final Matrix<?, ?, ?, ?, ?> matrix, final MatrixFormat matrixFormat)
    {
        MatrixFormatContext ctx = matrixFormat.ctx;
        Locale savedLocale = Locale.getDefault();
        try
        {
            savedLocale = saveLocale(ctx.locale);
            return new MatrixFormatter(matrix, ctx).format();
        }
        finally
        {
            restoreLocale(savedLocale);
        }
    }

    /**
     * Format an absolute matrix according to a number of FormatHints. Note that this method might not be thread-safe for
     * setting the default Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param absMatrix the absolute matrix to format
     * @param matrixFormat the format to apply to the matrix
     * @return a String with a formatted matrix, matching the FormatHints as closely as possible
     */
    public static String format(final AbsMatrix<?, ?, ?, ?, ?> absMatrix, final MatrixFormat matrixFormat)
    {
        MatrixFormatContext ctx = matrixFormat.ctx;
        Locale savedLocale = Locale.getDefault();
        try
        {
            savedLocale = saveLocale(ctx.locale);
            return new MatrixFormatter(absMatrix.getRelativeVecMat(), ctx).format()
                    + formatReference(ctx, absMatrix.getReference());
        }
        finally
        {
            restoreLocale(savedLocale);
        }
    }

    /**
     * Return the matrix, formatted according to the context settings.
     * @return the formatted matrix
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Override
    String format()
    {
        formatUnit();
        StringBuilder s = new StringBuilder();
        s.append(this.ctx.matrixPrefix);
        for (int r = 0; r < matrix().rows(); r++)
        {
            if (r == 0)
                s.append(this.ctx.firstRowStartSymbol);
            else if (r == matrix().rows() - 1)
                s.append(this.ctx.lastRowStartSymbol);
            else
                s.append(this.ctx.middleRowStartSymbol);
            for (int c = 0; c < matrix().cols(); c++)
            {
                if (c > 0)
                    s.append(this.ctx.colSeparatorSymbol);
                double si = matrix().si(r, c);
                double value = this.useSi ? si : this.unit.getScale().fromBaseValue(si);
                s.append(formatValue(value));
            }
            if (r == 0)
                s.append(this.ctx.firstRowEndSymbol);
            else if (r == matrix().rows() - 1)
                s.append(this.ctx.lastRowEndSymbol);
            else
                s.append(this.ctx.middleRowEndSymbol);
        }
        s.append(this.ctx.matrixPostfix);
        s.append(this.ctx.unitPrefix);
        s.append(this.unitStr);
        return s.toString();
    }

}
