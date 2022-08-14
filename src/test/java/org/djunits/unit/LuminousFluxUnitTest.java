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
public class LuminousFluxUnitTest extends AbstractLinearUnitTest<LuminousFluxUnit>
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
        assertEquals("srcd", LuminousFluxUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(LuminousFluxUnit.LUMEN, 1, 0.000001, "lumen", "lm");
    }

    /**
     * Verify that we can create our own LuminousFlux unit.
     */
    @Test
    public final void createLuminousFluxUnit()
    {
        LuminousFluxUnit myUnit = LuminousFluxUnit.SI.deriveLinear(1.23, "my", "myLuminousFlux", UnitSystem.OTHER);
        assertTrue("Can create a new LuminousFluxUnit", null != myUnit);
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myLuminousFlux", "my");
        LuminousFluxUnit.BASE.unregister(myUnit);
    }

}
