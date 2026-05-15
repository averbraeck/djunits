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
        checkAutoSiPrefix();
        if (this.unitStr == null)
            this.unitStr = this.ctx.textual ? this.unit.getTextualAbbreviation() : this.unit.getDisplayAbbreviation();
    }

    /**
     * Prepare for automatic SI scaling if it has been turned on, and if it is possible to apply.
     * @return whether automatic SI prefix scaling was applied or not
     * @param <Q> the quantity type
     */
    @SuppressWarnings({"unchecked", "checkstyle:needbraces"})
    private <Q extends Quantity<Q>> boolean checkAutoSiPrefix()
    {
        if (!this.ctx.autoSiPrefix)
            return false;

        Q q = (Q) quantity();

        // Reset to base unit if needed
        if (this.unit.getSiPrefix() == null)
        {
            q.setDisplayUnit(q.getDisplayUnit().getBaseUnit());
            this.unit = q.getDisplayUnit();
        }

        // If, e.g., SIQuantity, do not format as SI unit
        if (this.unit.getSiPrefix() == null)
            return false;

        PrefixType type = this.unit.getSiPrefix().getType();

        double si = q.si();
        if (si == 0.0)
            return false;

        double log10 = Math.log10(Math.abs(si));
        boolean invert = type.equals(PrefixType.PER_UNIT) || type.equals(PrefixType.PER_KILO);
        int exponent = (int) (3 * Math.floor(log10 / 3.0));

        // normalize per type
        String baseId = this.unit.getId();
        switch (type)
        {
            case UNIT:
                break;

            case KILO:
                exponent += 3;
                baseId = baseId.substring(1);
                break;

            case PER_UNIT:
                baseId = "/" + baseId.substring(1);
                break;

            case PER_KILO:
                exponent -= 3;
                baseId = "/" + baseId.substring(2);
                break;

            default:
                return false;
        }

        int lookupExponent = invert ? -exponent : exponent;
        if (lookupExponent < this.ctx.autoSiMinExponent || lookupExponent > this.ctx.autoSiMaxExponent)
            return false;
        SIPrefix prefix = SIPrefixes.FACTORS.getOrDefault(lookupExponent, SIPrefixes.getSiPrefix(""));
        String prefixText = prefix.getDefaultTextualPrefix();
        String key = invert ? "/" + prefixText + baseId.substring(1) : prefixText + baseId;
        this.unit = (Unit<?, Q>) Units.resolve(q.getDisplayUnit().getClass(), key);
        return true;
    }
        
}
