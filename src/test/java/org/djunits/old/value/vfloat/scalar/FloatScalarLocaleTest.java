package org.djunits.old.value.vfloat.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.old.value.vfloat.scalar.FloatDuration;
import org.junit.jupiter.api.Test;

/**
 * DoubleScalarLocaleTest tests a DoubleScalar with localizations. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank"> https://djutils.org</a>. The DJUTILS project is
 * distributed under a three-clause BSD-style license, which can be found at
 * <a href="https://djutils.org/docs/license.html" target="_blank"> https://djutils.org/docs/license.html</a>. <br>
 * @author Alexander Verbraeck
 */
public class FloatScalarLocaleTest
{

    /** Test nl_NL. */
    @Test
    public void testNl()
    {
        Locale saveLocale = Locale.getDefault();
        Locale.setDefault(new Locale("nl", "NL"));
        assertTrue(Locale.getDefault().toString().toLowerCase().startsWith("nl"));
        assertEquals("NL", Locale.getDefault().getCountry());

        var v = FloatDuration.valueOf("0,23s");
        assertEquals(0.23f, v.si, 1E-6);

        var d = FloatDuration.valueOf("0,5 uur");
        assertEquals(1800.0f, d.si, 1E-6);
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

        var v = FloatDuration.valueOf("0.23s");
        assertEquals(0.23f, v.si, 1E-6);

        var d = FloatDuration.valueOf("0.5 hour");
        assertEquals(1800.0f, d.si, 1E-6);
        Locale.setDefault(saveLocale);
    }

}
