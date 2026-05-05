package org.djunits.formatter;

import java.util.Locale;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.Units;
import org.djunits.unit.si.PrefixType;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;

/**
 * QuantityFormatter formats a quantity as a String, using the settings of the {@link QuantityFormatContext}. The
 * {@link QuantityFormatContext} is filled by using the setters in the {@link QuantityFormat} class. Note that there is no
 * guarantee that the format settings can always be satisfied. As an example, when the required width is too small to fit the
 * answer, the output will show the correct result, but violate the width format setting.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class QuantityFormatter extends Formatter<QuantityFormatContext>
{
    /**
     * @param quantity the quantity to format
     * @param ctx the quantity format context
     */
    QuantityFormatter(final Quantity<?> quantity, final QuantityFormatContext ctx)
    {
        super(quantity, ctx);
    }

    /**
     * Format a quantity according to a given {@link QuantityFormat}. Note that this method might not be thread-safe for setting
     * the Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param quantity the quantity to format
     * @param quantityFormat the format to apply to the quantity
     * @return a String with a formatted quantity, matching the given format as closely as possible
     */
    public static String format(final Quantity<?> quantity, final QuantityFormat quantityFormat)
    {
        QuantityFormatContext ctx = quantityFormat.ctx;
        Locale savedLocale = Locale.getDefault();
        try
        {
            savedLocale = saveLocale(ctx.locale);
            return new QuantityFormatter(quantity, ctx).format();
        }
        finally
        {
            restoreLocale(savedLocale);
        }
    }

    /**
     * Format an absolute quantity according to a given {@link QuantityFormat}. Note that this method might not be thread-safe
     * for setting the Locale. If another thread changes the Locale while formatting, outcomes could vary.
     * @param absQuantity the absolute quantity to format
     * @param quantityFormat the format to apply to the absolute quantity
     * @return a String with a formatted absolute quantity, matching the given format as closely as possible
     */
    public static String format(final AbsQuantity<?, ?, ?> absQuantity, final QuantityFormat quantityFormat)
    {
        QuantityFormatContext ctx = quantityFormat.ctx;
        Locale savedLocale = Locale.getDefault();
        try
        {
            savedLocale = saveLocale(ctx.locale);
            return new QuantityFormatter(absQuantity.getQuantity(), ctx).format()
                    + formatReference(ctx, absQuantity.getReference());
        }
        finally
        {
            restoreLocale(savedLocale);
        }
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
        double value = this.useSi ? quantity().si : this.unit.getScale().fromIdentityScale(quantity().si());
        return formatValue(value) + this.ctx.unitPrefix + this.unitStr + this.ctx.unitPostfix;
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
            double log10si = Math.log10(si);
            int power = log10si > 0 ? 3 * (int) (Math.log10(si) / 3.0) : 3 * (int) (Math.log10(si) / 3.0 - 1);
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
                        power += 3;
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
                        power -= 3;
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
