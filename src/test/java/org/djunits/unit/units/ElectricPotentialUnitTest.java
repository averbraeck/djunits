package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.ElectricCurrent;
import org.djunits.quantity.ElectricPotential;
import org.djunits.quantity.Power;
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
public class ElectricPotentialUnitTest extends AbstractLinearUnitTest<ElectricPotential.Unit>
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
        assertEquals("kgm2/s3A", ElectricPotential.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricPotential.Unit.V, 1, 0.00000001, "volt", "V");
        checkUnitRatioNameAndAbbreviation(ElectricPotential.Unit.mV, 0.001, 0.00000000001, "millivolt", "mV");
        checkUnitRatioNameAndAbbreviation(ElectricPotential.Unit.kV, 1000, 0.005, "kilovolt", "kV");
        // Check two conversions between non-standard units
        assertEquals(1000000, getMultiplicationFactorTo(ElectricPotential.Unit.kV, ElectricPotential.Unit.mV), 0.0001,
                "one KILOVOLT is 1000000 MILLIVOLT");
    }

    /**
     * Verify that we can create our own electrical potential unit.
     */
    @Test
    public final void createElectricPotentialUnit()
    {
        ElectricPotential.Unit myEPU = ElectricPotential.Unit.V.deriveUnit("nanoV", "NanoVolt", 1e-9, UnitSystem.SI_DERIVED);
        assertTrue(null != myEPU, "Can create a new ElectricPotential.Unit");
        checkUnitRatioNameAndAbbreviation(myEPU, 1e-9, 0.1, "NanoVolt", "nanoV");
        Units.unregister(myEPU);

        myEPU = ElectricPotential.Unit.SI.deriveUnit("ft.lbfph/uA", "foot pound-force per hour per microA",
                Power.Unit.ft_lbf_h.getScale().toBaseValue(1.0) / ElectricCurrent.Unit.muA.getScale().toBaseValue(1.0),
                UnitSystem.IMPERIAL);
        assertTrue(null != myEPU, "Can create a new ElectricPotential.Unit");
        checkUnitRatioNameAndAbbreviation(myEPU, 376.6, 0.1, "foot pound-force per hour per microA", "ft.lbfph/uA");
        Units.unregister(myEPU);
    }

}
