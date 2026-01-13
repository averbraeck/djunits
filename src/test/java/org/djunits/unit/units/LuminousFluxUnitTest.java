package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.LuminousFlux;
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
public class LuminousFluxUnitTest extends AbstractLinearUnitTest<LuminousFlux.Unit>
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
        assertEquals("srcd", LuminousFlux.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(LuminousFlux.Unit.lm, 1, 0.000001, "lumen", "lm");
    }

    /**
     * Verify that we can create our own LuminousFlux unit.
     */
    @Test
    public final void createLuminousFluxUnit()
    {
        LuminousFlux.Unit myUnit = LuminousFlux.Unit.SI.deriveUnit("my", "myLuminousFlux", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new LuminousFluxUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myLuminousFlux", "my");
        Units.unregister(myUnit);
    }

}
