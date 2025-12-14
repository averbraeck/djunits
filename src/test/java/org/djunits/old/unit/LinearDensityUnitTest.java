package org.djunits.old.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.old.unit.LinearDensityUnit;
import org.djunits.old.unit.unitsystem.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Peter Knoppers
 */
public class LinearDensityUnitTest extends AbstractLinearUnitTest<LinearDensityUnit>
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
        assertEquals("1/m", LinearDensityUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(LinearDensityUnit.PER_METER, 1, 0.000001, "per meter", "/m");
        checkUnitRatioNameAndAbbreviation(LinearDensityUnit.PER_KILOMETER, 0.001, 0.0000001, "per kilometer", "/km");
        checkUnitRatioNameAndAbbreviation(LinearDensityUnit.PER_MILLIMETER, 1000, 0.01, "per millimeter", "/mm");
        // Check two conversions between non-standard units
        assertEquals(1000000, getMultiplicationFactorTo(LinearDensityUnit.PER_MILLIMETER, LinearDensityUnit.PER_KILOMETER), 0.1,
                "one per millimeter is 1000000 per kilometer");
        assertEquals(0.000001, getMultiplicationFactorTo(LinearDensityUnit.PER_KILOMETER, LinearDensityUnit.PER_MILLIMETER),
                0.00000005, "one per kilometer is 0.000001 per millimeter");
    }

    /**
     * Verify that we can create our own LinearDensity unit.
     */
    @Test
    public final void createLinearDensityUnit()
    {
        LinearDensityUnit muLDU = LinearDensityUnit.PER_METER.deriveLinear(2.54 / 100, "perin", "PerInch", UnitSystem.OTHER);
        assertTrue(null != muLDU, "Can create a new LinearDensityUnit");
        checkUnitRatioNameAndAbbreviation(muLDU, 0.0254, 0.000001, "PerInch", "perin");
        LinearDensityUnit.BASE.unregister(muLDU);
    }

}
