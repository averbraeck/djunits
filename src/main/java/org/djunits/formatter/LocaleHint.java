package org.djunits.formatter;

import java.util.Locale;

/**
 * LocaleHint stores the settings for the use of a locale when formatting a quantity, vector, matrix or quantity table.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class LocaleHint implements FormatHint
{
    /** Locale to use. */
    private Locale locale = null;

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public void applyTo(final FormatContext ctx)
    {
        if (this.locale != null)
            ctx.locale = this.locale;
    }

    /**
     * Apply locale to formatting.
     * @param locale the locale to use
     * @return the LocaleHint instance
     */
    public LocaleHint setLocale(final Locale locale)
    {
        this.locale = locale;
        return this;
    }

}
