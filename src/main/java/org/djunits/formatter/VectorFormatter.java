package org.djunits.formatter;

import org.djunits.vecmat.def.Vector;

/**
 * VectorFormatter formats a vector as a String, using the settings of the FormatContext. The FormatContext is filled by
 * applying FormatHints to the FormatContext. The term 'hint' is used explicitly here, since there is no guarantee that the
 * hints can always be satisfied. As an example, when the required width is too small to fit the answer, the output will show
 * the correct result, but violate the width hint. The VectorFormatter uses settings from VectorHint, QuantityHint, UnitHint,
 * NumberHint, and LocaleHint.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VectorFormatter extends Formatter
{
    /**
     * @param vector the quantity to format
     * @param ctx the format context
     */
    VectorFormatter(final Vector<?, ?, ?, ?, ?> vector, final FormatContext ctx)
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
     * Return the vector, formatted according to the context settings.
     * @return the formatted vector
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Override
    String format()
    {
        formatUnit();
        StringBuilder s = new StringBuilder();
        s.append(vector().isRowVector() ? this.ctx.vectorRowPrefix : this.ctx.vectorColPrefix);
        if (vector().isRowVector() || this.ctx.vectorColAsRow)
        {
            s.append(this.ctx.vectorRowStartSymbol);
            boolean first = true;
            for (double si : vector().unsafeSiArray())
            {
                if (!first)
                    s.append(this.ctx.vectorRowSeparatorSymbol);
                first = false;
                double value = this.useSi ? si : this.unit.getScale().fromBaseValue(si);
                s.append(formatValue(value));
            }
            s.append(this.ctx.vectorRowEndSymbol);
        }
        else // format as column vector
        {
            s.append(this.ctx.vectorColStartSymbol);
            boolean first = true;
            for (double si : vector().unsafeSiArray())
            {
                if (!first)
                    s.append(this.ctx.vectorColSeparatorSymbol);
                first = false;
                double value = this.useSi ? si : this.unit.getScale().fromBaseValue(si);
                s.append(formatValue(value));
            }
            s.append(this.ctx.vectorColEndSymbol);
        }
        s.append(this.ctx.unitSeparator);
        s.append(this.unitStr);
        return s.toString();
    }

    /**
     * Format the unit according to the context settings.
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Override
    void formatUnit()
    {
        boolean formatted = checkSiUnits();
        if (!formatted)
            formatted = checkUnitString();
        if (!formatted)
            formatted = checkDisplayUnit();
        if (this.unitStr == null)
            this.unitStr = this.ctx.textual ? this.unit.getTextualAbbreviation() : this.unit.getTextualAbbreviation();
    }

}
