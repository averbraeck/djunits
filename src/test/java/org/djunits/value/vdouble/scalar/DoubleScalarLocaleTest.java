package org.djunits.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * DoubleScalarLocaleTest tests a DoubleScalar with localizations. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank"> https://djutils.org</a>. The DJUTILS project is
 * distributed under a three-clause BSD-style license, which can be found at
 * <a href="https://djutils.org/docs/license.html" target="_blank"> https://djutils.org/docs/license.html</a>. <br>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class DoubleScalarLocaleTest
{

    /** Test nl_NL. */
    @Test
    public void testNl()
    {
        Locale saveLocale = Locale.getDefault();
        Locale.setDefault(new Locale("nl", "NL"));
        assertTrue(Locale.getDefault().toString().toLowerCase().startsWith("nl"));
        assertEquals("NL", Locale.getDefault().getCountry());

        var v = Duration.valueOf("0,23s");
        assertEquals(0.23, v.si, 1E-6);

        var d = Duration.valueOf("0,5 uur");
        assertEquals(1800.0, d.si, 1E-6);
        Locale.setDefault(saveLocale);
    }

    /** Test en_US. */
    @Test
    public void testEn()
    {
        Locale saveLocale = Locale.getDefault();
        Locale.setDefault(new Locale("en", "US"));
        assertTrue(Locale.getDefault().toString().toLowerCase().startsWith("en"));
        assertEquals("US", Locale.getDefault().getCountry());

        var v = Duration.valueOf("0.23s");
        assertEquals(0.23, v.si, 1E-6);

        var d = Duration.valueOf("0.5 hour");
        assertEquals(1800.0, d.si, 1E-6);
        Locale.setDefault(saveLocale);
    }

}
