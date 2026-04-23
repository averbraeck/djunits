package org.djunits.formatter;

/**
 * QuantityHint stores a number of settings that influence both the value part and the unit part of an output string when
 * formatting a quantity.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class QuantityHint implements FormatHint
{
    /** Use closest SI prefix. E.g., turn 20400 m into "20.4 km". */
    private Boolean scaleSiPrefixes = null;

    /** minimum 10th power to use SI prefixes for. */
    private Integer minimumPrefixPower = null;

    /** maximum 10th power to use SI prefixes for. */
    private Integer maximumPrefixPower = null;

    /** separator between the value and the unit. */
    private String unitSeparator = null;

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        if (this.scaleSiPrefixes != null)
            ctx.scaleSiPrefixes = this.scaleSiPrefixes;
        if (this.minimumPrefixPower != null)
            ctx.minimumPrefixPower = this.minimumPrefixPower;
        if (this.maximumPrefixPower != null)
            ctx.maximumPrefixPower = this.maximumPrefixPower;
        if (this.unitSeparator != null)
            ctx.unitSeparator = this.unitSeparator;
    }

    /**
     * Use closest SI prefix. E.g., turn 20400 m into "20.4 km".
     * @return QuantityHint object for fluent design
     */
    public QuantityHint scaleSiPrefixes()
    {
        this.scaleSiPrefixes = true;
        return this;
    }

    /**
     * Use closest SI prefix. E.g., turn 20400 m into "20.4 km".
     * @param minPrefixPower minimum 10th power to use SI prefixes for
     * @param maxPrefixPower maximum 10th power to use SI prefixes for
     * @return QuantityHint object for fluent design
     */
    public QuantityHint scaleSiPrefixes(final int minPrefixPower, final int maxPrefixPower)
    {
        this.scaleSiPrefixes = true;
        this.minimumPrefixPower = minPrefixPower;
        this.maximumPrefixPower = maxPrefixPower;
        return this;
    }

    /**
     * Use closest SI prefix. E.g., turn 20400 m into "20.4 km".
     * @param separator separator string between the value and the unit
     * @return QuantityHint object for fluent design
     */
    public QuantityHint unitSeparator(final String separator)
    {
        this.unitSeparator = separator;
        return this;
    }

}
