package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.ElectricalConductance;
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
public class ElectricalConductanceUnitTest extends AbstractLinearUnitTest<ElectricalConductance.Unit>
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
        assertEquals("s3A2/kgm2", ElectricalConductance.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalConductance.Unit.S, 1, 0.000001, "siemens", "S");
    }

    /**
     * Verify that we can create our own ElectricalConductance unit.
     */
    @Test
    public final void createElectricalConductanceUnit()
    {
        ElectricalConductance.Unit myUnit =
                ElectricalConductance.Unit.SI.deriveUnit("my", "myElectricalConductance", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new ElectricalConductanceUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myElectricalConductance", "my");
        Units.unregister(myUnit);
    }

}
