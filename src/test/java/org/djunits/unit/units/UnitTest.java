package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.djunits.quantity.Acceleration;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.Speed;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.Test;

/**
 * UnitTest.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 */
public class UnitTest
{
    /** Test the units. */
    @Test
    public void unitTest()
    {
        assertEquals(Length.Unit.SI, Length.Unit.m);
        assertNotEquals(Length.Unit.cm, Length.Unit.dm);
        assertNotEquals(null, Length.Unit.SI);
        assertNotEquals(Length.Unit.m, Length.Unit.dm);
        assertNotEquals(Length.Unit.m, Duration.Unit.s);
        assertEquals(0.3048, Length.Unit.ft.getScale().toBaseValue(1.0), 1.0E-6);
        assertEquals(Duration.Unit.min, Units.resolve(Duration.Unit.class, "min"));
        assertEquals(Duration.Unit.h, Units.resolve(Duration.Unit.class, "h"));

        assertEquals(1.0, Speed.Unit.m_s.getScale().toBaseValue(1.0), 1.0E-6);
        assertEquals(0.277777777, Speed.Unit.km_h.getScale().toBaseValue(1.0), 1.0E-6);
        assertEquals(0.0000771604938, Acceleration.Unit.km_h2.getScale().toBaseValue(1.0), 1.0E-12);

        // these two should be similar except for the abbreviations
        Length.Unit xx1 = new Length.Unit("xx1Id", "xx11t", "xxName1", new LinearScale(1.0E-13), UnitSystem.OTHER);
        Length.Unit xx2 = Length.Unit.m.deriveUnit("xx2Id", "xx22t", "xxName2", 1.0E-13, UnitSystem.OTHER);
        assertEquals(xx1.getScale(), xx2.getScale());
        assertEquals(xx1.getUnitSystem(), xx2.getUnitSystem());
        assertEquals("xx1Id", xx1.getId());
        assertEquals("xx2Id", xx2.getId());
        assertEquals("xxName1", xx1.getName());
        assertEquals("xxName2", xx2.getName());
        assertEquals("xx11t", xx1.getDisplayAbbreviation());
        assertEquals("xx22t", xx2.getDisplayAbbreviation());
        assertNotEquals(xx1, xx2);
        Units.unregister(xx1);
        Units.unregister(xx2);
    }

    /** hashCode() and equals(). */
    @Test
    public void unitHashCodeEqualsTest()
    {
        Length.Unit m13a = Length.Unit.m.deriveUnit("m13", "10^13 m", 1.0E13, UnitSystem.SI_DERIVED);
        Units.unregister(m13a);
        Length.Unit m13b = Length.Unit.m.deriveUnit("m13", "10^13 m", 1.0E13, UnitSystem.SI_DERIVED);
        Units.unregister(m13b);
        assertEquals(m13a, m13b);
        assertEquals(m13a.hashCode(), m13b.hashCode());
    }
}
