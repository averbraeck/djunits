package org.djunits.formatter;

import org.djunits.vecmat.def.Table;

/**
 * TableFormatter formats a table or matrix as a String, using the settings of the FormatContext. The FormatContext is filled by
 * applying FormatHints to the FormatContext. The term 'hint' is used explicitly here, since there is no guarantee that the
 * hints can always be satisfied. As an example, when the required width is too small to fit the answer, the output will show
 * the correct result, but violate the width hint. The TableFormatter uses settings from TableHint, QuantityHint, UnitHint,
 * NumberHint, and LocaleHint.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class TableFormatter extends Formatter
{
    /**
     * @param table the table or matrix to format
     * @param ctx the format context
     */
    TableFormatter(final Table<?, ?, ?, ?, ?> table, final FormatContext ctx)
    {
        super(table, ctx);
    }

    /**
     * Return the value as a table (or matrix).
     * @return the value as a table (or matrix)
     */
    Table<?, ?, ?, ?, ?> table()
    {
        return (Table<?, ?, ?, ?, ?>) this.value;
    }

    /**
     * Return the table or matrix, formatted according to the context settings.
     * @return the formatted table or matrix
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
                s.append(this.ctx.tableFirstRowStartSymbol);
            else if (r == table().rows() - 1)
                s.append(this.ctx.tableLastRowStartSymbol);
            else
                s.append(this.ctx.tableMiddleRowStartSymbol);
            for (int c = 0; c < table().cols(); c++)
            {
                if (c > 0)
                    s.append(this.ctx.tableColSeparatorSymbol);
                double si = table().si(r, c);
                double value = this.useSi ? si : this.unit.getScale().fromBaseValue(si);
                s.append(formatValue(value));
            }
            if (r == 0)
                s.append(this.ctx.tableFirstRowEndSymbol);
            else if (r == table().rows() - 1)
                s.append(this.ctx.tableLastRowEndSymbol);
            else
                s.append(this.ctx.tableMiddleRowEndSymbol);
        }
        s.append(this.ctx.tablePostfix);
        s.append(this.ctx.unitPrefix);
        s.append(this.unitStr);
        return s.toString();
    }

}
