package org.djunits.formatter;

import java.util.Locale;

import org.djunits.vecmat.def.AbsVector;
import org.djunits.vecmat.def.Vector;

/**
 * VectorFormatter formats a vector as a String, using the settings of the {@link VectorFormatContext}. The
 * {@link VectorFormatContext} is filled by setting flags using the {@link VectorFormat}. Note that there is no guarantee that
 * the format can always be honored. As an example, when the required width is too small to fit the answer, the output will show
 * the correct result, but violate the width hint.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VectorFormatter extends Formatter<VectorFormatContext>
{
    /**
     * @param vector the quantity to format
     * @param ctx the format context
     */
    VectorFormatter(final Vector<?, ?, ?, ?, ?> vector, final VectorFormatContext ctx)
    {
        super(vector, ctx);
    }

    /**
     * Return the value as a vector.
     * @return the value as a vector
     */
    Vector<?, ?, ?, ?, ?> vector()
    {
        return (Vector<?, ?, ?, ?, ?>) this.value;
    }

    /**
     * Format a vector according to a number of FormatHints. Note that this method might not be thread-safe for setting the
     * default Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param vector the vector to format
     * @param vectorFormat the format to apply to the vector
     * @return a String with a formatted vector, matching the FormatHints as closely as possible
     */
    public static String format(final Vector<?, ?, ?, ?, ?> vector, final VectorFormat vectorFormat)
    {
        VectorFormatContext ctx = vectorFormat.ctx;
        Locale savedLocale = Locale.getDefault();
        try
        {
            savedLocale = saveLocale(ctx.locale);
            return new VectorFormatter(vector, ctx).format();
        }
        finally
        {
            restoreLocale(savedLocale);
        }
    }

    /**
     * Format an absolute vector according to a number of FormatHints. Note that this method might not be thread-safe for
     * setting the default Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param absVector the absolute vector to format
     * @param vectorFormat the format to apply to the vector
     * @return a String with a formatted vector, matching the FormatHints as closely as possible
     */
    public static String format(final AbsVector<?, ?, ?, ?, ?> absVector, final VectorFormat vectorFormat)
    {
        VectorFormatContext ctx = vectorFormat.ctx;
        Locale savedLocale = Locale.getDefault();
        try
        {
            savedLocale = saveLocale(ctx.locale);
            return new VectorFormatter(absVector.getRelativeVecMat(), ctx).format()
                    + formatReference(ctx, absVector.getReference());
        }
        finally
        {
            restoreLocale(savedLocale);
        }
    }

    /**
     * Return the vector, formatted according to the context settings.
     * @return the formatted vector
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Override
    String format()
    {
        formatUnit();
        StringBuilder s = new StringBuilder();
        s.append(vector().isRowVector() ? this.ctx.rowVectorPrefix : this.ctx.colVectorPrefix);
        if (vector().isRowVector() || this.ctx.colAsRow)
        {
            s.append(this.ctx.rowStartSymbol);
            boolean first = true;
            for (double si : vector().unsafeSiArray())
            {
                if (!first)
                    s.append(this.ctx.rowSeparatorSymbol);
                first = false;
                double value = this.useSi ? si : this.unit.getScale().fromBaseValue(si);
                s.append(formatValue(value));
            }
            s.append(this.ctx.rowEndSymbol);
        }
        else // format as column vector
        {
            s.append(this.ctx.colStartSymbol);
            boolean first = true;
            for (double si : vector().unsafeSiArray())
            {
                if (!first)
                    s.append(this.ctx.colSeparatorSymbol);
                first = false;
                double value = this.useSi ? si : this.unit.getScale().fromBaseValue(si);
                s.append(formatValue(value));
            }
            s.append(this.ctx.colEndSymbol);
        }
        s.append(this.ctx.unitPrefix);
        s.append(this.unitStr);
        s.append(this.ctx.unitPostfix);
        return s.toString();
    }

}
