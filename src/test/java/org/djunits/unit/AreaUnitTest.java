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
public class AreaUnitTest extends AbstractLinearUnitTest<AreaUnit>
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
        assertEquals("m2", AreaUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(AreaUnit.SQUARE_METER, 1, 0.00000001, "square meter", "m^2");
        checkUnitRatioNameAndAbbreviation(AreaUnit.SQUARE_KILOMETER, 1000000, 0.05, "square kilometer", "km^2");
        checkUnitRatioNameAndAbbreviation(AreaUnit.SQUARE_MILE, 2589990, 2, "square mile", "mi^2");
        // Check two conversions between non-standard units
        assertEquals(640, getMultiplicationFactorTo(AreaUnit.SQUARE_MILE, AreaUnit.ACRE), 0.1, "one SQUARE MILE is 640 ACRE");
        // Check conversion factor to standard unit for all remaining area units
        checkUnitRatioNameAndAbbreviation(AreaUnit.ARE, 100, 0.001, "are", "a");
        checkUnitRatioNameAndAbbreviation(AreaUnit.HECTARE, 10000, 0.01, "hectare", "ha");
        checkUnitRatioNameAndAbbreviation(AreaUnit.SQUARE_FOOT, 0.092903, 0.000001, "square foot", "ft^2");
        checkUnitRatioNameAndAbbreviation(AreaUnit.SQUARE_INCH, 0.00064516, 0.00000001, "square inch", "in^2");
        checkUnitRatioNameAndAbbreviation(AreaUnit.SQUARE_YARD, 0.836127, 0.000001, "square yard", "yd^2");
        checkUnitRatioNameAndAbbreviation(AreaUnit.ACRE, 4046.9, 0.05, "acre", "ac");
    }

    /**
     * Verify that we can create our own Area unit.
     */
    @Test
    public final void createAreaUnit()
    {
        AreaUnit myUnit = AreaUnit.SI.deriveLinear(1.23, "my", "myArea", UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new AreaUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myArea", "my");
        AreaUnit.BASE.unregister(myUnit);
    }

}
