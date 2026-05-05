package org.djunits.formatter;

import java.util.Locale;

import org.djunits.vecmat.table.AbsQuantityTable;
import org.djunits.vecmat.table.QuantityTable;

/**
 * TableFormatter formats a table as a String, using the settings of the {@link TableFormatContext}. The
 * {@link TableFormatContext} is filled by setting properties of the {@link TableFormatContext}. Note that there is no guarantee
 * that the format can always be honored. As an example, when the required width is too small to fit the answer, the output will
 * show the correct result, but violate the width setting.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class TableFormatter extends Formatter<TableFormatContext>
{
    /**
     * @param table the table to format
     * @param ctx the format context
     */
    TableFormatter(final QuantityTable<?> table, final TableFormatContext ctx)
    {
        super(table, ctx);
    }

    /**
     * Return the value as a table (or table).
     * @return the value as a table (or table)
     */
    QuantityTable<?> table()
    {
        return (QuantityTable<?>) this.value;
    }

    /**
     * Format a table according to a number of table format settings. Note that this method might not be thread-safe for setting
     * the default Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param table the table to format
     * @param tableFormat the format to apply to the table
     * @return a String with a formatted table, matching the table format settings as closely as possible
     */
    public static String format(final QuantityTable<?> table, final TableFormat tableFormat)
    {
        TableFormatContext ctx = tableFormat.ctx;
        Locale savedLocale = Locale.getDefault();
        try
        {
            savedLocale = saveLocale(ctx.locale);
            return new TableFormatter(table, ctx).format();
        }
        finally
        {
            restoreLocale(savedLocale);
        }
    }

    /**
     * Format an absolute table according to a number of table format settings. Note that this method might not be thread-safe
     * for setting the default Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param absTable the absolute table to format
     * @param tableFormat the format to apply to the table
     * @return a String with a formatted table, matching the table format settings as closely as possible
     */
    public static String format(final AbsQuantityTable<?, ?> absTable, final TableFormat tableFormat)
    {
        TableFormatContext ctx = tableFormat.ctx;
        Locale savedLocale = Locale.getDefault();
        try
        {
            savedLocale = saveLocale(ctx.locale);
            return new TableFormatter(absTable.getRelativeVecMat(), ctx).format()
                    + formatReference(ctx, absTable.getReference());
        }
        finally
        {
            restoreLocale(savedLocale);
        }
    }

    /**
     * Return the table, formatted according to the context settings.
     * @return the formatted table
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Override
    String format()
    {
        formatUnit();
        StringBuilder s = new StringBuilder();
        s.append(this.ctx.tablePrefix);
        for (int r = 0; r < table().rows(); r++)
        {
            if (r == 0)
                s.append(this.ctx.firstRowStartSymbol);
            else if (r == table().rows() - 1)
                s.append(this.ctx.lastRowStartSymbol);
            else
                s.append(this.ctx.middleRowStartSymbol);
            for (int c = 0; c < table().cols(); c++)
            {
                if (c > 0)
                    s.append(this.ctx.colSeparatorSymbol);
                double si = table().si(r, c);
                double value = this.useSi ? si : this.unit.getScale().fromBaseValue(si);
                s.append(formatValue(value));
            }
            if (r == 0)
                s.append(this.ctx.firstRowEndSymbol);
            else if (r == table().rows() - 1)
                s.append(this.ctx.lastRowEndSymbol);
            else
                s.append(this.ctx.middleRowEndSymbol);
        }
        s.append(this.ctx.tablePostfix);
        s.append(this.ctx.unitPrefix);
        s.append(this.unitStr);
        s.append(this.ctx.unitPostfix);
        return s.toString();
    }

}
