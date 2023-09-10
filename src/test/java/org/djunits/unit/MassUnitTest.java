package org.djunits.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.unitsystem.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class MassUnitTest extends AbstractLinearUnitTest<MassUnit>
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
        assertEquals("kg", MassUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(MassUnit.KILOGRAM, 1, 0.00000001, "kilogram", "kg");
        checkUnitRatioNameAndAbbreviation(MassUnit.GRAM, 0.001, 0.000000001, "gram", "g");
        checkUnitRatioNameAndAbbreviation(MassUnit.POUND, 0.453592, 0.000001, "pound", "lb");
        // Check two conversions between non-standard units
        assertEquals(16, getMultiplicationFactorTo(MassUnit.POUND, MassUnit.OUNCE), 0.00001, "one POUND is 16 OUNCE");
        assertEquals(0.0625, getMultiplicationFactorTo(MassUnit.OUNCE, MassUnit.POUND), 0.000001, "one OUNCE is 0.0625 POUND");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(MassUnit.OUNCE, 0.0283495, 0.0000001, "ounce", "oz");
        checkUnitRatioNameAndAbbreviation(MassUnit.TON_LONG, 1016.046906, 0.00001, "long ton", "long tn");
        checkUnitRatioNameAndAbbreviation(MassUnit.TON_SHORT, 907.18474, 0.00001, "short ton", "sh tn");
        checkUnitRatioNameAndAbbreviation(MassUnit.TON_METRIC, 1000, 0.001, "metric tonne", "t");
    }

    /**
     * Verify that we can create our own mass unit.
     */
    @Test
    public final void createMassUnit()
    {
        MassUnit myMU = MassUnit.KILOGRAM.deriveLinear(80, "pn", "Person", UnitSystem.OTHER);
        assertTrue(null != myMU, "Can create a new MassUnit");
        checkUnitRatioNameAndAbbreviation(myMU, 80, 1, "Person", "pn");
        MassUnit.BASE.unregister(myMU);
    }

}
