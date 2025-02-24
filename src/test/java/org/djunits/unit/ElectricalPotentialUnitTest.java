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
public class ElectricalPotentialUnitTest extends AbstractLinearUnitTest<ElectricalPotentialUnit>
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
        assertEquals("kgm2/s3A", ElectricalPotentialUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalPotentialUnit.VOLT, 1, 0.00000001, "volt", "V");
        checkUnitRatioNameAndAbbreviation(ElectricalPotentialUnit.MILLIVOLT, 0.001, 0.00000000001, "millivolt", "mV");
        checkUnitRatioNameAndAbbreviation(ElectricalPotentialUnit.KILOVOLT, 1000, 0.005, "kilovolt", "kV");
        // Check two conversions between non-standard units
        assertEquals(1000000, getMultiplicationFactorTo(ElectricalPotentialUnit.KILOVOLT, ElectricalPotentialUnit.MILLIVOLT),
                0.0001, "one KILOVOLT is 1000000 MILLIVOLT");
    }

    /**
     * Verify that we can create our own electrical potential unit.
     */
    @Test
    public final void createElectricalPotentialUnit()
    {
        ElectricalPotentialUnit myEPU = ElectricalPotentialUnit.VOLT.deriveLinear(1e-9, "nanoV", "NanoVolt");
        assertTrue(null != myEPU, "Can create a new ElectricalPotentialUnit");
        checkUnitRatioNameAndAbbreviation(myEPU, 1e-9, 0.1, "NanoVolt", "nanoV");
        ElectricalPotentialUnit.BASE.unregister(myEPU);

        myEPU = ElectricalPotentialUnit.SI.deriveLinear(
                PowerUnit.FOOT_POUND_FORCE_PER_HOUR.getScale().toStandardUnit(1.0)
                        / ElectricalCurrentUnit.MICROAMPERE.getScale().toStandardUnit(1.0),
                "ft.lbfph/uA", "foot pound-force per hour per microA", UnitSystem.IMPERIAL);
        assertTrue(null != myEPU, "Can create a new ElectricalPotentialUnit");
        checkUnitRatioNameAndAbbreviation(myEPU, 376.6, 0.1, "foot pound-force per hour per microA", "ft.lbfph/uA");
        ElectricalPotentialUnit.BASE.unregister(myEPU);
    }

}
