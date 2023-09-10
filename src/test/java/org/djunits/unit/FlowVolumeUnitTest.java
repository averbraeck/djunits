package org.djunits.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.unitsystem.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class FlowVolumeUnitTest extends AbstractLinearUnitTest<FlowVolumeUnit>
{
    /**
     * Set the locale to "en" so we know what texts should be retrieved from the resources.
     */
    @BeforeEach
    public final void setup()
    {
        Locale.setDefault(new Locale("en"));
    }

    /**
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        assertEquals("m3/s", FlowVolumeUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(FlowVolumeUnit.CUBIC_METER_PER_SECOND, 1, 0.000001, "cubic meter per second",
                "m^3/s");
        checkUnitRatioNameAndAbbreviation(FlowVolumeUnit.CUBIC_METER_PER_MINUTE, 0.0166667, 0.000001, "cubic meter per minute",
                "m^3/min");
        // Check two conversions between non-standard units
        assertEquals(0.01666667,
                getMultiplicationFactorTo(FlowVolumeUnit.CUBIC_METER_PER_HOUR, FlowVolumeUnit.CUBIC_METER_PER_MINUTE), 0.00001,
                "one CUBIC METER PER HOUR is about 2.205 CUBIC_METER_PER_MINUTED");
        assertEquals(60, getMultiplicationFactorTo(FlowVolumeUnit.CUBIC_METER_PER_MINUTE, FlowVolumeUnit.CUBIC_METER_PER_HOUR),
                0.0001, "one CUBIC METER PER MINUTE is 60 CUBIC_METER_PER_HOUR");
    }

    /**
     * Verify that we can create our own FlowVolume unit.
     */
    @Test
    public final void createFLowVolumeUnit()
    {
        FlowVolumeUnit myFVU = FlowVolumeUnit.CUBIC_METER_PER_HOUR.deriveLinear(100, "tph", "TrucksPerHour", UnitSystem.OTHER);
        assertTrue(null != myFVU, "Can create a new FlowMassUnit");
        checkUnitRatioNameAndAbbreviation(myFVU, 100. / 3600, 0.0001, "TrucksPerHour", "tph");
        FlowVolumeUnit.BASE.unregister(myFVU);
    }

}
