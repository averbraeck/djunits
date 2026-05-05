package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.junit.jupiter.api.Test;

/**
 * SIUnitTest tests a few remaining methods of the SIUnit class.<p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
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
        assertEquals("kgm3s-2", su.format(false, false));
        assertEquals("kgm3/s2", su.format(true, false));
        assertEquals("kg.m3.s-2", su.format(false, true));
        assertEquals("kg.m3/s2", su.format(true, true));
        
        // divided, separator, power
        assertEquals("kgm3s-2", su.format(false, false, false));
        assertEquals("kgm3/s2", su.format(true, false, false));
        assertEquals("kg.m3.s-2", su.format(false, true, false));
        assertEquals("kg.m3/s2", su.format(true, true, false));
        assertEquals("kgm^3s^-2", su.format(false, false, true));
        assertEquals("kgm^3/s^2", su.format(true, false, true));
        assertEquals("kg.m^3.s^-2", su.format(false, true, true));
        assertEquals("kg.m^3/s^2", su.format(true, true, true));

        // divided, separator, powerPrefix, powerPostfix
        assertEquals("kg&cdot;m<sup>3</sup>/s<sup>2</sup>", su.format(true, "&cdot;", "<sup>", "</sup>"));
        assertEquals("kg&cdot;m<sup>3</sup>&cdot;s<sup>-2</sup>", su.format(false, "&cdot;", "<sup>", "</sup>"));
    }

}
