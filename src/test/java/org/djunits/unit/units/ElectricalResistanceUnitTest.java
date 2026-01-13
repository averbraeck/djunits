package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.ElectricCurrent;
import org.djunits.quantity.ElectricPotential;
import org.djunits.quantity.ElectricalResistance;
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
public class ElectricalResistanceUnitTest extends AbstractLinearUnitTest<ElectricalResistance.Unit>
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
        assertEquals("kgm2/s3A2", ElectricalResistance.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalResistance.Unit.ohm, 1, 0.00000001, "ohm", "\u03A9");
        checkUnitRatioNameAndAbbreviation(ElectricalResistance.Unit.mohm, 0.001, 0.00000000001, "milliohm", "m\u03A9");
        checkUnitRatioNameAndAbbreviation(ElectricalResistance.Unit.kohm, 1000, 0.005, "kiloohm", "k\u03A9");
        // Check two conversions between non-standard units
        assertEquals(1000000, getMultiplicationFactorTo(ElectricalResistance.Unit.kohm, ElectricalResistance.Unit.mohm), 0.0001,
                "one KILOOHM is 1000000 MILLIOHM");
    }

    /**
     * Verify that we can create our own electrical resistance unit.
     */
    @Test
    public final void createElectricalResistanceUnit()
    {
        ElectricalResistance.Unit myERU =
                ElectricalResistance.Unit.ohm.deriveUnit("GigOhm", "GigaOhm", 1.0E9, UnitSystem.SI_DERIVED);
        assertTrue(null != myERU, "Can create a new ElectricalResistanceUnit");
        checkUnitRatioNameAndAbbreviation(myERU, 1e9, 0.1, "GigaOhm", "GigOhm");
        Units.unregister(myERU);

        ElectricalResistance.Unit abOhm = ElectricalResistance.Unit.ohm.deriveUnit("abOO", "abOhm(CGS)",
                ElectricPotential.Unit.abV.getScale().toBaseValue(1.0) / ElectricCurrent.Unit.abA.getScale().toBaseValue(1.0),
                UnitSystem.CGS);
        assertTrue(null != abOhm, "Can create Abohm unit");
        checkUnitRatioNameAndAbbreviation(abOhm, 1e-9, 1e-12, "abOhm(CGS)", "abOO");
        Units.unregister(myERU);
    }

}
