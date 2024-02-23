package org.djunits.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.unitsystem.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class ElectricalResistanceUnitTest extends AbstractLinearUnitTest<ElectricalResistanceUnit>
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
        assertEquals("kgm2/s3A2", ElectricalResistanceUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalResistanceUnit.OHM, 1, 0.00000001, "ohm", "\u03A9");
        checkUnitRatioNameAndAbbreviation(ElectricalResistanceUnit.MILLIOHM, 0.001, 0.00000000001, "milliohm", "m\u03A9");
        checkUnitRatioNameAndAbbreviation(ElectricalResistanceUnit.KILOOHM, 1000, 0.005, "kiloohm", "k\u03A9");
        // Check two conversions between non-standard units
        assertEquals(1000000, getMultiplicationFactorTo(ElectricalResistanceUnit.KILOOHM, ElectricalResistanceUnit.MILLIOHM),
                0.0001, "one KILOOHM is 1000000 MILLIOHM");
    }

    /**
     * Verify that we can create our own electrical resistance unit.
     */
    @Test
    public final void createElectricalResistanceUnit()
    {
        ElectricalResistanceUnit myERU = ElectricalResistanceUnit.OHM.deriveLinear(1.0E9, "GigOhm", "GigaOhm");
        assertTrue(null != myERU, "Can create a new ElectricalResistanceUnit");
        checkUnitRatioNameAndAbbreviation(myERU, 1e9, 0.1, "GigaOhm", "GigOhm");
        ElectricalResistanceUnit.BASE.unregister(myERU);

        ElectricalResistanceUnit abOhm =
                ElectricalResistanceUnit.OHM.deriveLinear(
                        ElectricalPotentialUnit.ABVOLT.getScale().toStandardUnit(1.0)
                                / ElectricalCurrentUnit.ABAMPERE.getScale().toStandardUnit(1.0),
                        "abOO", "abOhm(CGS)", UnitSystem.CGS);
        assertTrue(null != abOhm, "Can create Abohm unit");
        checkUnitRatioNameAndAbbreviation(abOhm, 1e-9, 1e-12, "abOhm(CGS)", "abOO");
        ElectricalResistanceUnit.BASE.unregister(myERU);
    }

}
