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
public class ElectricalCurrentUnitTest extends AbstractLinearUnitTest<ElectricalCurrentUnit>
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
        assertEquals("A", ElectricalCurrentUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalCurrentUnit.AMPERE, 1, 0.00000001, "ampere", "A");
        checkUnitRatioNameAndAbbreviation(ElectricalCurrentUnit.MILLIAMPERE, 0.001, 0.000000001, "milliampere", "mA");
        // Check two conversions between two units
        assertEquals("one AMPERE is 1000 MILLI AMPERE", 1000,
                getMultiplicationFactorTo(ElectricalCurrentUnit.AMPERE, ElectricalCurrentUnit.MILLIAMPERE), 0.01);
        assertEquals("one MILLI AMPERE is 0.001 AMPERE", 0.001,
                getMultiplicationFactorTo(ElectricalCurrentUnit.MILLIAMPERE, ElectricalCurrentUnit.AMPERE), 0.0001);
    }

    /**
     * Verify that we can create our own ElectricalCurrent unit.
     */
    @Test
    public final void createElectricalCurrentUnit()
    {
        ElectricalCurrentUnit myUnit =
                ElectricalCurrentUnit.SI.deriveLinear(1.23, "my", "myElectricalCurrent", UnitSystem.OTHER);
        assertTrue("Can create a new ElectricalCurrentUnit", null != myUnit);
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myElectricalCurrent", "my");
        ElectricalCurrentUnit.BASE.unregister(myUnit);
    }

}
