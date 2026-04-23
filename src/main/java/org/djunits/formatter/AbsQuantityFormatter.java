package org.djunits.formatter;

import org.djunits.quantity.def.AbsQuantity;

/**
 * AbsQuantityFormatter formats an absolute quantity as a String, using the settings of the FormatContext. The FormatContext is
 * filled by applying FormatHints to the FormatContext. The term 'hint' is used explicitly here, since there is no guarantee
 * that the hints can always be satisfied. As an example, when the required width is too small to fit the answer, the output
 * will show the correct result, but violate the width hint.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsQuantityFormatter extends QuantityFormatter
{
    /** The absolute quantity. */
    private final AbsQuantity<?, ?, ?> absQuantity;

    /**
     * @param absQuantity the absolute quantity to format
     * @param ctx the format context
     */
    AbsQuantityFormatter(final AbsQuantity<?, ?, ?> absQuantity, final FormatContext ctx)
    {
        super(absQuantity.getQuantity(), ctx);
        this.absQuantity = absQuantity;
    }

    /**
     * Return the absolute quantity, formatted according to the context settings.
     * @return the formatted absolute quantity
     */
    @Override
    String format()
    {
        formatUnit();
        double value = this.useSi ? quantity().si : this.unit.getScale().fromBaseValue(quantity().si());
        String reference = this.ctx.printReference ? " (" + this.absQuantity.getReference().getId() + ")" : "";
        return formatValue(value) + this.ctx.unitSeparator + this.unitStr + reference;
    }

}
