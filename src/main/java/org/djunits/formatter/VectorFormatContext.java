package org.djunits.formatter;

/**
 * VectorFormatContext contains a number of settings for formatting a vector. Note that this class and its fields are package
 * private. It is not the intention to use or extends the class, since the {@link VectorFormat} class can only deal with the
 * fields as expressed in this class, and not with other added fields.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
class VectorFormatContext extends FormatContext implements Cloneable
{
    // Vector formatting, contains default values

    /** Start symbol for vector. */
    String startSymbol;

    /** End symbol for vector. */
    String endSymbol;

    /** Separator symbol between values. */
    String separatorSymbol = ", ";

    /** Prefix to use for the entire vector. */
    String vectorPrefix;

    @Override
    protected VectorFormatContext clone()
    {
        return (VectorFormatContext) super.clone();
    }

}
