package org.djunits.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.LinkedHashSet;

import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;
import org.junit.Test;

/**
 * UnitTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class UnitTest
{
    /** Test the units. */
    @Test
    public void unitTest()
    {
        assertEquals(LengthUnit.SI, LengthUnit.METER);
        assertNotEquals(LengthUnit.CENTIMETER, LengthUnit.DECIMETER);
        assertNotEquals(null, LengthUnit.SI);
        assertNotEquals(LengthUnit.METER, LengthUnit.DECIMETER);
        assertNotEquals(LengthUnit.METER, DurationUnit.SECOND);
        assertEquals(0.3048, LengthUnit.FOOT.getScale().toStandardUnit(1.0), 1.0E-6);
        assertEquals(DurationUnit.MINUTE, DurationUnit.BASE.of("min"));
        assertEquals(DurationUnit.HOUR, DurationUnit.BASE.of("h"));
        assertEquals(DurationUnit.HOUR, DurationUnit.BASE.of("hr"));
        assertEquals(DurationUnit.HOUR, DurationUnit.BASE.of("hour"));

        assertEquals(1.0, SpeedUnit.METER_PER_SECOND.getScale().toStandardUnit(1.0), 1.0E-6);
        assertEquals(0.277777777, SpeedUnit.KM_PER_HOUR.getScale().toStandardUnit(1.0), 1.0E-6);
        assertEquals(0.0000771604938, AccelerationUnit.KM_PER_HOUR_2.getScale().toStandardUnit(1.0), 1.0E-12);

        // these two should be similar except for the abbreviations
        LengthUnit xx1 = new LengthUnit().build(new Unit.Builder<LengthUnit>().setQuantity(LengthUnit.METER.getQuantity())
                .setId("xx1Id").setName("xxName1").setDefaultDisplayAbbreviation("xx1").setDefaultTextualAbbreviation("xx11t")
                .setUnitSystem(UnitSystem.OTHER).setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(new LinearScale(1.0E-13)));
        LengthUnit xx2 = LengthUnit.METER.deriveLinear(1.0E-13, "xx2Id", "xxName2", UnitSystem.OTHER, "xx2", "xx22t");
        assertEquals(xx1.getQuantity(), xx2.getQuantity());
        assertEquals(xx1.getScale(), xx2.getScale());
        assertEquals(xx1.getUnitSystem(), xx2.getUnitSystem());
        assertEquals("xx1Id", xx1.getId());
        assertEquals("xx2Id", xx2.getId());
        assertEquals("xxName1", xx1.getName());
        assertEquals("xxName2", xx2.getName());
        assertEquals("xx11t", xx1.getDefaultTextualAbbreviation());
        assertEquals("xx22t", xx2.getDefaultTextualAbbreviation());
        assertEquals(new LinkedHashSet<String>(Arrays.asList("xx1", "xx11t")), xx1.getAbbreviations());
        assertEquals(new LinkedHashSet<String>(Arrays.asList("xx2", "xx22t")), xx2.getAbbreviations());
        assertNotEquals(xx1, xx2);
        LengthUnit.BASE.unregister(xx1);
        LengthUnit.BASE.unregister(xx2);
    }

    /** hashCode() and equals(). */
    @Test
    public void unitHashCodeEqualsTest()
    {
        LengthUnit m13a = LengthUnit.METER.deriveLinear(1.0E13, "m13", "10^13 m", UnitSystem.SI_DERIVED);
        LengthUnit.BASE.unregister(m13a);
        LengthUnit m13b = LengthUnit.METER.deriveLinear(1.0E13, "m13", "10^13 m", UnitSystem.SI_DERIVED);
        LengthUnit.BASE.unregister(m13b);
        assertEquals(m13a, m13b);
        assertEquals(m13a.hashCode(), m13b.hashCode());

        LengthUnit ym = LengthUnit.BASE.of("ym");
        LengthUnit ym2 = LengthUnit.METER.deriveSI(SIPrefixes.UNIT_PREFIXES.get("y"), 1.0, false);
        LengthUnit.BASE.unregister(ym2);
        assertNotEquals(ym, ym2); // automatically generated = t/f
        assertNotEquals(ym.hashCode(), ym2.hashCode());
        LengthUnit ym3 = LengthUnit.METER.deriveSI(SIPrefixes.UNIT_PREFIXES.get("y"), 1.0, true);
        assertEquals(ym, ym3); // automatically generated = t/t
        assertEquals(ym.hashCode(), ym3.hashCode());
        // automatically generated ym stays in registry for further tests.
    }
}
