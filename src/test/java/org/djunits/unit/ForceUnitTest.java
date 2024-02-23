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
public class ForceUnitTest extends AbstractLinearUnitTest<ForceUnit>
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
        assertEquals("kgm/s2", ForceUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(ForceUnit.NEWTON, 1, 0.000001, "newton", "N");
        checkUnitRatioNameAndAbbreviation(ForceUnit.DYNE, 0.00001, 0.000000001, "dyne", "dyn");
        // Check two conversions between non-standard units
        assertEquals(1.01971621e-6, getMultiplicationFactorTo(ForceUnit.DYNE, ForceUnit.KILOGRAM_FORCE), 0.00000000001,
                "one DYNE is about 1.019716e-6 KILOGRAM FORCE");
        assertEquals(980665, getMultiplicationFactorTo(ForceUnit.KILOGRAM_FORCE, ForceUnit.DYNE), 0.5,
                "one KILOGRAM FORCE is about 980665 DYNE");
    }

    /**
     * Verify that we can create our own Force unit.
     */
    @Test
    public final void createForceUnit()
    {
        ForceUnit myFU = ForceUnit.KILOGRAM_FORCE.deriveLinear(0.002, "af", "AntForce", UnitSystem.OTHER);
        assertTrue(null != myFU, "Can create a new ForceUnit");
        checkUnitRatioNameAndAbbreviation(myFU, 0.002 * 9.8, 0.0001, "AntForce", "af");
        ForceUnit.BASE.unregister(myFU);
    }

}
