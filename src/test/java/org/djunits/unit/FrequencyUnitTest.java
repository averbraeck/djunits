package org.djunits.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.unitsystem.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class FrequencyUnitTest extends AbstractLinearUnitTest<FrequencyUnit>
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
        assertEquals("1/s", FrequencyUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(FrequencyUnit.HERTZ, 1, 0.000001, "hertz", "Hz");
        checkUnitRatioNameAndAbbreviation(FrequencyUnit.KILOHERTZ, 1000, 0.0001, "kilohertz", "kHz");
        // Check two conversions between non-standard units
        assertEquals(0.001, getMultiplicationFactorTo(FrequencyUnit.KILOHERTZ, FrequencyUnit.MEGAHERTZ), 0.00000000001,
                "one KILOHERTZ is 0.001 MEGAHERTZ");
        assertEquals(1000, getMultiplicationFactorTo(FrequencyUnit.MEGAHERTZ, FrequencyUnit.KILOHERTZ), 0.0005,
                "one MEGAHERTZ is 1000 KILOHERTZ");
        checkUnitRatioNameAndAbbreviation(FrequencyUnit.GIGAHERTZ, 1e9, 1e3, "gigahertz", "GHz");
        checkUnitRatioNameAndAbbreviation(FrequencyUnit.TERAHERTZ, 1e12, 1e6, "terahertz", "THz");
    }

    /**
     * Verify that we can create our own Frequency unit.
     */
    @Test
    public final void createFrequencyUnit()
    {
        FrequencyUnit myFU = FrequencyUnit.KILOHERTZ.deriveLinear(0.440, "MA", "MiddleA", UnitSystem.OTHER);
        assertTrue(null != myFU, "Can create a new ForceUnit");
        checkUnitRatioNameAndAbbreviation(myFU, 440, 0.0001, "MiddleA", "MA");
        FrequencyUnit.BASE.unregister(myFU);
    }

}
