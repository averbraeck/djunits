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
public class ElectricalCapacitanceUnitTest extends AbstractLinearUnitTest<ElectricalCapacitanceUnit>
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
        assertEquals("s4A2/kgm2", ElectricalCapacitanceUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalCapacitanceUnit.FARAD, 1, 0.000001, "farad", "F");
        checkUnitRatioNameAndAbbreviation(ElectricalCapacitanceUnit.MICROFARAD, 1E-6, 1E-9, "microfarad", "uF");
    }

    /**
     * Verify that we can create our own ElectricalCapacitance unit.
     */
    @Test
    public final void createElectricalCapacitanceUnit()
    {
        ElectricalCapacitanceUnit myUnit =
                ElectricalCapacitanceUnit.SI.deriveLinear(1.23, "my", "myElectricalCapacitance", UnitSystem.OTHER);
        assertTrue("Can create a new ElectricalCapacitanceUnit", null != myUnit);
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myElectricalCapacitance", "my");
        ElectricalCapacitanceUnit.BASE.unregister(myUnit);
    }

}
