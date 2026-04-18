package org.djunits.formatter;

import java.util.Locale;

/**
 * LocaleFormat stores the settings for the use of a locale when formatting a quantity, vector, matrix or quantity table.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class LocaleFormat implements FormatHint
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
     * @return the LocaleFormat instance
     */
    public static LocaleFormat locale(final Locale locale)
    {
        var lf = new LocaleFormat();
        lf.locale = locale;
        return lf;
    }

}
