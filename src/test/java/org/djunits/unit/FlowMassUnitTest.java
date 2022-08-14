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
public class FlowMassUnitTest extends AbstractLinearUnitTest<FlowMassUnit>
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
        assertEquals("kg/s", FlowMassUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(FlowMassUnit.KILOGRAM_PER_SECOND, 1, 0.000001, "kilogram per second", "kg/s");
        checkUnitRatioNameAndAbbreviation(FlowMassUnit.POUND_PER_SECOND, 0.453592, 0.0001, "pound per second", "lb/s");
        // Check two conversions between non-standard units
        assertEquals("one KILOGRAM PER SECOND is about 2.205 POUND PER SECOND", 2.205,
                getMultiplicationFactorTo(FlowMassUnit.KILOGRAM_PER_SECOND, FlowMassUnit.POUND_PER_SECOND), 0.0005);
        assertEquals("one POUND PER SECOND is about 0.453592 KILOGRAM PER SECOND", 0.453592,
                getMultiplicationFactorTo(FlowMassUnit.POUND_PER_SECOND, FlowMassUnit.KILOGRAM_PER_SECOND), 0.0001);
    }

    /**
     * Verify that we can create our own FlowMass unit.
     */
    @Test
    public final void createFlowMassUnit()
    {
        FlowMassUnit myFMU = FlowMassUnit.KILOGRAM_PER_SECOND.deriveLinear(1234, "wdpu", "WaterDropsPerHour", UnitSystem.OTHER);
        assertTrue("Can create a new FlowMassUnit", null != myFMU);
        checkUnitRatioNameAndAbbreviation(myFMU, 1234, 0.0001, "WaterDropsPerHour", "wdpu");
        FlowMassUnit.BASE.unregister(myFMU);
    }

}
