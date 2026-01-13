package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.ElectricCharge;
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
public class ElectricChargeUnitTest extends AbstractLinearUnitTest<ElectricCharge.Unit>
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
        assertEquals("sA", ElectricCharge.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricCharge.Unit.C, 1, 0.00000001, "coulomb", "C");
        checkUnitRatioNameAndAbbreviation(ElectricCharge.Unit.mAh, 3.6, 0.000000005, "milliampere hour", "mAh");
        checkUnitRatioNameAndAbbreviation(ElectricCharge.Unit.F, 96485.3365, 0.005, "faraday", "F");
        // Check two conversions between non-standard units
        assertEquals(0.00003731137, getMultiplicationFactorTo(ElectricCharge.Unit.mAh, ElectricCharge.Unit.F), 0.000000001,
                "one MILLIAMPERE_HOUR is about 0.00003731137 FARADAY");
        // Test the other units
        checkUnitRatioNameAndAbbreviation(ElectricCharge.Unit.e, 1.60217653e-19, 1e-25, "elementary unit of charge", "e");
    }

    /**
     * Verify that we can create our own ElectricCharge unit.
     */
    @Test
    public final void createElectricChargeUnit()
    {
        ElectricCharge.Unit myUnit = ElectricCharge.Unit.SI.deriveUnit("my", "myElectricCharge", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new ElectricChargeUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myElectricCharge", "my");
        Units.unregister(myUnit);
    }

}
