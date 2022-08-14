package org.djunits.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.djunits.locale.DefaultLocale;
import org.djunits.unit.unitsystem.UnitSystem;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class SpeedUnitTest extends AbstractLinearUnitTest<SpeedUnit>
{
    /**
     * Set the locale to "en" so we know what texts should be retrieved from the resources.
     */
    @Before
    public final void setup()
    {
        DefaultLocale.setLocale(new Locale("en"));
    }

    /**
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        assertEquals("m/s", SpeedUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(SpeedUnit.METER_PER_SECOND, 1, 0.00000001, "meter per second", "m/s");
        checkUnitRatioNameAndAbbreviation(SpeedUnit.KM_PER_HOUR, 0.277778, 0.000001, "kilometer per hour", "km/h");
        checkUnitRatioNameAndAbbreviation(SpeedUnit.MILE_PER_HOUR, 0.44704, 0.00001, "mile per hour", "mi/h");
        // Check two conversions between non-standard units
        assertEquals("one KM PER HOUR is about 0.621371 MILE PER HOUR", 0.621371,
                getMultiplicationFactorTo(SpeedUnit.KM_PER_HOUR, SpeedUnit.MILE_PER_HOUR), 0.0001);
        assertEquals("one MILE PER HOUR is about 1.60934 KM PER HOUR", 1.60934,
                getMultiplicationFactorTo(SpeedUnit.MILE_PER_HOUR, SpeedUnit.KM_PER_HOUR), 0.0001);
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(SpeedUnit.FOOT_PER_SECOND, 0.3048, 0.0001, "foot per second", "ft/s");
        checkUnitRatioNameAndAbbreviation(SpeedUnit.KNOT, 0.514444, 0.000001, "knot", "kt");
    }

    /**
     * Verify that we can create our own speed unit.
     */
    @Test
    public final void createSpeedUnit()
    {
        SpeedUnit mySU = SpeedUnit.KM_PER_HOUR.deriveLinear(48, "sprtr", "Sprinter", UnitSystem.SI_DERIVED);
        assertTrue("Can create a new PowerUnit", null != mySU);
        checkUnitRatioNameAndAbbreviation(mySU, 13.3333, 0.0001, "Sprinter", "sprtr");
        SpeedUnit.BASE.unregister(mySU);
    }

}
