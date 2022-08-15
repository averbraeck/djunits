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
public class AccelerationUnitTest extends AbstractLinearUnitTest<AccelerationUnit>
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
        assertEquals("m/s2", AccelerationUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.METER_PER_SECOND_2, 1, 0.00000001, "meter per second squared",
                "m/s2");
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.KM_PER_HOUR_2, 1 / 3.6 / 3600, 0.0005, "kilometer per hour squared",
                "km/h2");
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.FOOT_PER_SECOND_2, 0.3048, 0.00001, "foot per second squared",
                "ft/s2");
        // Check two conversions between non-standard units
        assertEquals("one FOOT PER SECOND PER SECOND is ??? KM PER HOUR PER HOUR", 3950.208,
                getMultiplicationFactorTo(AccelerationUnit.FOOT_PER_SECOND_2, AccelerationUnit.KM_PER_HOUR_2), 0.01);
        // Check conversion factor to standard unit for all remaining acceleration units
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.INCH_PER_SECOND_2, 0.0254, 0.0000000001, "inch per second squared",
                "in/s2");
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.MILE_PER_HOUR_2, 0.000124177778, 0.0000000001,
                "mile per hour squared", "mi/h2");
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.MILE_PER_SECOND_2, 1609.344, 0.000001, "mile per second squared",
                "mi/s2");
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.KNOT_PER_SECOND, 0.514444444, 0.000001, "knot per second", "kt/s");
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.MILE_PER_HOUR_PER_SECOND, 0.44704, 0.00000001,
                "mile per hour per second", "mi/h/s");
    }

    /**
     * Check that g is about 9.8 m/s/s.
     */
    @Test
    public final void gravityConstant()
    {
        checkUnitRatioNameAndAbbreviation(AccelerationUnit.STANDARD_GRAVITY, 9.8, 0.02, "standard gravity", "g");
    }

    /**
     * Verify that we can create our own Acceleration unit.
     */
    @Test
    public final void createAccelerationUnit()
    {
        AccelerationUnit myUnit = AccelerationUnit.SI.deriveLinear(1.23, "my", "myAcceleration", UnitSystem.OTHER);
        assertTrue("Can create a new AccelerationUnit", null != myUnit);
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myAcceleration", "my");
        AccelerationUnit.BASE.unregister(myUnit);
    }

}
