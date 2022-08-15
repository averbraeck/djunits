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
public class ElectricalChargeUnitTest extends AbstractLinearUnitTest<ElectricalChargeUnit>
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
        assertEquals("sA", ElectricalChargeUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalChargeUnit.COULOMB, 1, 0.00000001, "coulomb", "C");
        checkUnitRatioNameAndAbbreviation(ElectricalChargeUnit.MILLIAMPERE_HOUR, 3.6, 0.000000005, "milliampere hour", "mAh");
        checkUnitRatioNameAndAbbreviation(ElectricalChargeUnit.FARADAY, 96485.3365, 0.005, "faraday", "F");
        // Check two conversions between non-standard units
        assertEquals("one MILLIAMPERE_HOUR is about 0.00003731137 FARADAY", 0.00003731137,
                getMultiplicationFactorTo(ElectricalChargeUnit.MILLIAMPERE_HOUR, ElectricalChargeUnit.FARADAY), 0.000000001);
        // Test the other units
        checkUnitRatioNameAndAbbreviation(ElectricalChargeUnit.ATOMIC_UNIT, 1.60217653e-19, 1e-25, "elementary unit of charge",
                "e");
    }

    /**
     * Verify that we can create our own ElectricalCharge unit.
     */
    @Test
    public final void createElectricalChargeUnit()
    {
        ElectricalChargeUnit myUnit = ElectricalChargeUnit.SI.deriveLinear(1.23, "my", "myElectricalCharge", UnitSystem.OTHER);
        assertTrue("Can create a new ElectricalChargeUnit", null != myUnit);
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myElectricalCharge", "my");
        ElectricalChargeUnit.BASE.unregister(myUnit);
    }

}
