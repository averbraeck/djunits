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
public class DimensionlessUnitTest extends AbstractLinearUnitTest<DimensionlessUnit>
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
        assertEquals("", DimensionlessUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(DimensionlessUnit.SI, 1, 0.000001, "", "");
    }

    /**
     * Verify that we can create our own Dimensionless unit.
     */
    @Test
    public final void createDimensionlessUnit()
    {
        DimensionlessUnit myUnit = DimensionlessUnit.SI.deriveLinear(1.23, "my", "myDimensionless", UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new DimensionlessUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myDimensionless", "my");
        DimensionlessUnit.BASE.unregister(myUnit);
    }

}
