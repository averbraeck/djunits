package org.djunits.formatter;

/**
 * QuantityFormat stores a number of settings that influence both the value part and the unit part of an output string when
 * formatting a quantity.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class QuantityFormat implements FormatHint
{
    /** Use closest SI prefix. E.g., turn 20400 m into "20.4 km". */
    private Boolean scaleSiPrefixes = null;

    /** minimum 10th power to use SI prefixes for. */
    private Integer minimumPrefixPower = null;

    /** maximum 10th power to use SI prefixes for. */
    private Integer maximumPrefixPower = null;

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
    }

    /**
     * Use closest SI prefix. E.g., turn 20400 m into "20.4 km".
     * @return UnitFormat object for fluent design
     */
    public static QuantityFormat scaleSiPrefixes()
    {
        var qf = new QuantityFormat();
        qf.scaleSiPrefixes = true;
        return qf;
    }

    /**
     * Use closest SI prefix. E.g., turn 20400 m into "20.4 km".
     * @param minimumPrefixPower minimum 10th power to use SI prefixes for
     * @param maximumPrefixPower maximum 10th power to use SI prefixes for
     * @return UnitFormat object for fluent design
     */
    public static QuantityFormat scaleSiPrefixes(final int minimumPrefixPower, final int maximumPrefixPower)
    {
        var qf = new QuantityFormat();
        qf.scaleSiPrefixes = true;
        qf.minimumPrefixPower = minimumPrefixPower;
        qf.maximumPrefixPower = maximumPrefixPower;
        return qf;
    }

}
