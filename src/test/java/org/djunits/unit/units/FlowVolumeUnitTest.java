package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.FlowVolume;
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
public class FlowVolumeUnitTest extends AbstractLinearUnitTest<FlowVolume.Unit>
{
    /**
     * Set the locale to "US" so we know what texts should be retrieved from the resources.
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
        assertEquals("m3/s", FlowVolume.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(FlowVolume.Unit.m3_s, 1, 0.000001, "cubic meter per second", "m3/s");
        checkUnitRatioNameAndAbbreviation(FlowVolume.Unit.m3_min, 0.0166667, 0.000001, "cubic meter per minute", "m3/min");
        // Check two conversions between non-standard units
        assertEquals(0.01666667, getMultiplicationFactorTo(FlowVolume.Unit.m3_h, FlowVolume.Unit.m3_min), 0.00001,
                "one CUBIC METER PER HOUR is about 2.205 CUBIC_METER_PER_MINUTE");
        assertEquals(60, getMultiplicationFactorTo(FlowVolume.Unit.m3_min, FlowVolume.Unit.m3_h), 0.0001,
                "one CUBIC METER PER MINUTE is 60 CUBIC_METER_PER_HOUR");
    }

    /**
     * Verify that we can create our own FlowVolume unit.
     */
    @Test
    public final void createFLowVolumeUnit()
    {
        FlowVolume.Unit myFVU = FlowVolume.Unit.m3_h.deriveUnit("tph", "TrucksPerHour", 100.0, UnitSystem.OTHER);
        assertTrue(null != myFVU, "Can create a new FlowMassUnit");
        checkUnitRatioNameAndAbbreviation(myFVU, 100. / 3600, 0.0001, "TrucksPerHour", "tph");
        Units.unregister(myFVU);
    }

}
