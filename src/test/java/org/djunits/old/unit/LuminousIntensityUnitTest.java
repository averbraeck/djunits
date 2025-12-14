package org.djunits.old.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.old.unit.LuminousIntensityUnit;
import org.djunits.old.unit.unitsystem.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class LuminousIntensityUnitTest extends AbstractLinearUnitTest<LuminousIntensityUnit>
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
        assertEquals("cd", LuminousIntensityUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(LuminousIntensityUnit.CANDELA, 1, 0.000001, "candela", "cd");
    }

    /**
     * Verify that we can create our own LuminousIntensity unit.
     */
    @Test
    public final void createLuminousIntensityUnit()
    {
        LuminousIntensityUnit myUnit =
                LuminousIntensityUnit.SI.deriveLinear(1.23, "my", "myLuminousIntensity", UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new LuminousIntensityUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myLuminousIntensity", "my");
        LuminousIntensityUnit.BASE.unregister(myUnit);
    }

}
