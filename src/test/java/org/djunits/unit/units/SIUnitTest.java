package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.junit.jupiter.api.Test;

/**
 * SIUnitTest tests a few remaining methods of the SIUnit class.<p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class SIUnitTest
{
    // --------------------------------
    // SIUnit does not have SI prefixes
    // --------------------------------

    /**
     * Test SIPrefix methods.
     */
    @Test
    public void testSIPrefix()
    {
        var su = SIUnit.of("kgm/s2");
        assertEquals(su, su.setSiPrefix(SIPrefixes.getSiPrefix("G")));
        assertEquals(su, su.setSiPrefix("G"));
        assertEquals(su, su.setSiPrefixKilo("G"));
        assertEquals(su, su.setSiPrefixPer("G"));
        assertNull(su.getSiPrefix());
    }

    /**
     * Test toString() methods.
     */
    @Test
    public void testToString()
    {
        var su = SIUnit.of("kgm3/s2");

        // divided, separator
        assertEquals("kgm3s-2", su.toString(false, false));
        assertEquals("kgm3/s2", su.toString(true, false));
        assertEquals("kg.m3.s-2", su.toString(false, true));
        assertEquals("kg.m3/s2", su.toString(true, true));
        
        // divided, separator, power
        assertEquals("kgm3s-2", su.toString(false, false, false));
        assertEquals("kgm3/s2", su.toString(true, false, false));
        assertEquals("kg.m3.s-2", su.toString(false, true, false));
        assertEquals("kg.m3/s2", su.toString(true, true, false));
        assertEquals("kgm^3s^-2", su.toString(false, false, true));
        assertEquals("kgm^3/s^2", su.toString(true, false, true));
        assertEquals("kg.m^3.s^-2", su.toString(false, true, true));
        assertEquals("kg.m^3/s^2", su.toString(true, true, true));

        // divided, separator, powerPrefix, powerPostfix
        assertEquals("kg&cdot;m<sup>3</sup>/s<sup>2</sup>", su.toString(true, "&cdot;", "<sup>", "</sup>"));
        assertEquals("kg&cdot;m<sup>3</sup>&cdot;s<sup>-2</sup>", su.toString(false, "&cdot;", "<sup>", "</sup>"));
    }

}
