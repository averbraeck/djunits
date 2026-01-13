package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Speed;
import org.djunits.unit.Units;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class SpeedUnitTest extends AbstractLinearUnitTest<Speed.Unit>
{
    /**
     * Set the locale to "en" so we know what texts should be retrieved from the resources.
     */
    @BeforeEach
    public final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        assertEquals("m/s", Speed.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Speed.Unit.m_s, 1, 0.00000001, "meter per second", "m/s");
        checkUnitRatioNameAndAbbreviation(Speed.Unit.km_h, 0.277778, 0.000001, "kilometer per hour", "km/h");
        checkUnitRatioNameAndAbbreviation(Speed.Unit.mi_h, 0.44704, 0.00001, "mile per hour", "mi/h");
        // Check two conversions between non-standard units
        assertEquals(0.621371, getMultiplicationFactorTo(Speed.Unit.km_h, Speed.Unit.mi_h), 0.0001,
                "one KM PER HOUR is about 0.621371 MILE PER HOUR");
        assertEquals(1.60934, getMultiplicationFactorTo(Speed.Unit.mi_h, Speed.Unit.km_h), 0.0001,
                "one MILE PER HOUR is about 1.60934 KM PER HOUR");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(Speed.Unit.ft_s, 0.3048, 0.0001, "foot per second", "ft/s");
        checkUnitRatioNameAndAbbreviation(Speed.Unit.kt, 0.514444, 0.000001, "knot", "kt");
    }

    /**
     * Verify that we can create our own speed unit.
     */
    @Test
    public final void createSpeedUnit()
    {
        Speed.Unit mySU = Speed.Unit.km_h.deriveUnit("sprtr", "Sprinter", 48, UnitSystem.SI_DERIVED);
        assertTrue(null != mySU, "Can create a new PowerUnit");
        checkUnitRatioNameAndAbbreviation(mySU, 13.3333, 0.0001, "Sprinter", "sprtr");
        Units.unregister(mySU);
    }

}
