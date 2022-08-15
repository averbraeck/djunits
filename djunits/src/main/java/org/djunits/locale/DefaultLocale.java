package org.djunits.locale;

import java.util.Locale;

/**
 * The default local to use in djunits.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public abstract class DefaultLocale
{
    /** The default locale to use in djunits. */
    private static Locale locale;

    static
    {
        setLocale(new Locale("en"));
    }

    /**
     * @return locale
     */
    public static Locale getLocale()
    {
        return DefaultLocale.locale;
    }

    /**
     * @param locale Locale; set locale
     */
    public static void setLocale(final Locale locale)
    {
        DefaultLocale.locale = locale;
    }

}
