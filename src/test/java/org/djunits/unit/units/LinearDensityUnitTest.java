package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.LinearDensity;
import org.djunits.unit.Units;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Peter Knoppers
 */
public class LinearDensityUnitTest extends AbstractLinearUnitTest<LinearDensity.Unit>
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
        assertEquals("kg/m", LinearDensity.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(LinearDensity.Unit.kg_m, 1, 0.000001, "kilogram per meter", "kg/m");
    }

    /**
     * Verify that we can create our own LinearDensity unit.
     */
    @Test
    public final void createLinearDensityUnit()
    {
        LinearDensity.Unit muLDU = LinearDensity.Unit.kg_m.deriveUnit("kgperin", "KgPerInch", 2.54 / 100, UnitSystem.OTHER);
        assertTrue(null != muLDU, "Can create a new LinearDensityUnit");
        checkUnitRatioNameAndAbbreviation(muLDU, 0.0254, 0.000001, "KgPerInch", "kgperin");
        Units.unregister(muLDU);
    }

}
