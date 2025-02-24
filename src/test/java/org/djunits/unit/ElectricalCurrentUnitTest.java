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
public class ElectricalCurrentUnitTest extends AbstractLinearUnitTest<ElectricalCurrentUnit>
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
        assertEquals("A", ElectricalCurrentUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalCurrentUnit.AMPERE, 1, 0.00000001, "ampere", "A");
        checkUnitRatioNameAndAbbreviation(ElectricalCurrentUnit.MILLIAMPERE, 0.001, 0.000000001, "milliampere", "mA");
        // Check two conversions between two units
        assertEquals(1000, getMultiplicationFactorTo(ElectricalCurrentUnit.AMPERE, ElectricalCurrentUnit.MILLIAMPERE), 0.01,
                "one AMPERE is 1000 MILLI AMPERE");
        assertEquals(0.001, getMultiplicationFactorTo(ElectricalCurrentUnit.MILLIAMPERE, ElectricalCurrentUnit.AMPERE), 0.0001,
                "one MILLI AMPERE is 0.001 AMPERE");
    }

    /**
     * Verify that we can create our own ElectricalCurrent unit.
     */
    @Test
    public final void createElectricalCurrentUnit()
    {
        ElectricalCurrentUnit myUnit =
                ElectricalCurrentUnit.SI.deriveLinear(1.23, "my", "myElectricalCurrent", UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new ElectricalCurrentUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myElectricalCurrent", "my");
        ElectricalCurrentUnit.BASE.unregister(myUnit);
    }

}
