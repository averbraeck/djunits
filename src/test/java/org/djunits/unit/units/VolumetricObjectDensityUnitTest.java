package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.VolumetricObjectDensity;
import org.djunits.unit.Units;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 */
public class VolumetricObjectDensityUnitTest extends AbstractLinearUnitTest<VolumetricObjectDensity.Unit>
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
        assertEquals("1/m3", VolumetricObjectDensity.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(VolumetricObjectDensity.Unit.per_m3, 1, 0.000001, "per cubic meter", "/m3");
    }

    /**
     * Verify that we can create our own LinearDensity unit.
     */
    @Test
    public final void createLinearDensityUnit()
    {
        VolumetricObjectDensity.Unit muLDU =
                VolumetricObjectDensity.Unit.per_m3.deriveUnit("perin3", "PerInch3", 2.54 / 100, UnitSystem.OTHER);
        assertTrue(null != muLDU, "Can create a new VolumetricObjectDensityUnit");
        checkUnitRatioNameAndAbbreviation(muLDU, 0.0254, 0.000001, "PerInch3", "perin3");
        Units.unregister(muLDU);
    }

}
