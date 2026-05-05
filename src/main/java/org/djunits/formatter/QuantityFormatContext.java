package org.djunits.formatter;

/**
 * QuantityFormatContext contains a number of settings for formatting a quantity. Note that this class and its fields are
 * package private. It is not the intention to use or extends the class, since the {@link QuantityFormat} class can only deal
 * with the fields as expressed in this class, and not with other added fields.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
class QuantityFormatContext extends FormatContext implements Cloneable
{
    // Quantity formatting from QuantityFormat, contains default values

    /** Use closest SI prefix. E.g., turn 20400 m into "20.4 km". */
    boolean scaleSiPrefixes = false;

    /** minimum 10th power to use SI prefixes for. */
    int minimumPrefixPower = -30;

    /** maximum 10th power to use SI prefixes for. */
    int maximumPrefixPower = 32;

    @Override
    protected QuantityFormatContext clone()
    {
        return (QuantityFormatContext) super.clone();
    }

}
