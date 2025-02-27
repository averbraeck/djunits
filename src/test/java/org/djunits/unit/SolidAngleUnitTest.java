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
public class SolidAngleUnitTest extends AbstractLinearUnitTest<SolidAngleUnit>
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
        assertEquals("sr", SolidAngleUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(SolidAngleUnit.STERADIAN, 1, 0.0000001, "steradian", "sr");
        checkUnitRatioNameAndAbbreviation(SolidAngleUnit.SQUARE_DEGREE, 1.0 / 3283, 0.0005, "square degree", "sq.deg");
        // Check two conversions between units
        assertEquals(3283, getMultiplicationFactorTo(SolidAngleUnit.STERADIAN, SolidAngleUnit.SQUARE_DEGREE), 0.5,
                "one STERADIAN is about 3283 SQUARE_DEGREE");
        assertEquals(0.0003045, getMultiplicationFactorTo(SolidAngleUnit.SQUARE_DEGREE, SolidAngleUnit.STERADIAN), 0.0000005,
                "one SQUARE_DEGREE is about 0.0003045 STERADIAN");
    }

    /**
     * Verify that we can create our own angle unit.
     */
    @Test
    public final void createSolidAngleUnit()
    {
        SolidAngleUnit myAPU = SolidAngleUnit.STERADIAN.deriveLinear(0.19634954085, "pt", "point", UnitSystem.OTHER);
        assertTrue(null != myAPU, "Can create a new TimeUnit");
        checkUnitRatioNameAndAbbreviation(myAPU, 0.19634954085, 0.0000001, "point", "pt");
        SolidAngleUnit.BASE.unregister(myAPU);
    }
}
