package org.djunits.formatter;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.Units;
import org.djunits.unit.si.PrefixType;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;

/**
 * QuantityFormatter formats a quantity as a String, using the settings of the FormatContext. The FormatContext is filled by
 * applying FormatHints to the FormatContext. The term 'hint' is used explicitly here, since there is no guarantee that the
 * hints can always be satisfied. As an example, when the required width is too small to fit the answer, the output will show
 * the correct result, but violate the width hint.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class QuantityFormatter extends Formatter
{
    /**
     * @param quantity the quantity to format
     * @param ctx the format context
     */
    QuantityFormatter(final Quantity<?> quantity, final FormatContext ctx)
    {
        super(quantity, ctx);
    }

    /**
     * Return the value as a quantity.
     * @return the value as a quantity
     */
    Quantity<?> quantity()
    {
        return (Quantity<?>) this.value;
    }

    /**
     * Return the quantity, formatted according to the context settings.
     * @return the formatted quantity
     */
    @Override
    String format()
    {
        formatUnit();
        double value = this.useSi ? quantity().si : this.unit.getScale().fromBaseValue(quantity().si());
        return formatValue(value) + this.ctx.unitSeparator + this.unitStr;
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
        checkScaleSiPrefixes();
        if (this.unitStr == null)
            this.unitStr = this.ctx.textual ? this.unit.getTextualAbbreviation() : this.unit.getDisplayAbbreviation();
    }

    /**
     * Apply the display unit if present.
     * @return whether display unit formatting was applied
     * @param <Q> the quantity type
     */
    @SuppressWarnings("unchecked")
    private <Q extends Quantity<Q>> boolean checkScaleSiPrefixes()
    {
        Q q = (Q) quantity();
        if (this.ctx.scaleSiPrefixes && this.unit.getSiPrefix() != null)
        {
            PrefixType type = this.unit.getSiPrefix().getType();
            double si = q.si();
            int power = 3 * (int) (Math.log10(si) / 3.0);
            if (power >= this.ctx.minimumPrefixPower && power <= this.ctx.maximumPrefixPower)
            {
                switch (type)
                {
                    case UNIT:
                    {
                        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(power, SIPrefixes.getSiPrefix(""));
                        String key = prefix.getDefaultTextualPrefix() + q.getDisplayUnit().getBaseUnit().getId();
                        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
                        return true;
                    }
                    case KILO:
                    {
                        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(power, SIPrefixes.getSiPrefix(""));
                        String key = prefix.getDefaultTextualPrefix() + q.getDisplayUnit().getBaseUnit().getId().substring(1);
                        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
                        return true;
                    }
                    case PER_UNIT:
                    {
                        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(-power, SIPrefixes.getSiPrefix(""));
                        String key =
                                "/" + prefix.getDefaultTextualPrefix() + q.getDisplayUnit().getBaseUnit().getId().substring(1);
                        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
                        return true;
                    }
                    case PER_KILO:
                    {
                        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(-power, SIPrefixes.getSiPrefix(""));
                        String key =
                                "/" + prefix.getDefaultTextualPrefix() + q.getDisplayUnit().getBaseUnit().getId().substring(2);
                        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
                        return true;
                    }
                    default:
                        // silently ignore
                }
            }
        }
        return false;
    }

}
