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
public class CatalyticActivityUnitTest extends AbstractLinearUnitTest<CatalyticActivityUnit>
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
        assertEquals("mol/s", CatalyticActivityUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(CatalyticActivityUnit.KATAL, 1, 0.000001, "katal", "kat");
    }

    /**
     * Verify that we can create our own CatalyticActivity unit.
     */
    @Test
    public final void createCatalyticActivityUnit()
    {
        CatalyticActivityUnit myUnit =
                CatalyticActivityUnit.SI.deriveLinear(1.23, "my", "myCatalyticActivity", UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new CatalyticActivityUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myCatalyticActivity", "my");
        CatalyticActivityUnit.BASE.unregister(myUnit);
    }

}
