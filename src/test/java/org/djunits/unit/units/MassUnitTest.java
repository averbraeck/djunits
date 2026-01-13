package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Mass;
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
public class MassUnitTest extends AbstractLinearUnitTest<Mass.Unit>
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
        assertEquals("kg", Mass.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Mass.Unit.kg, 1, 0.00000001, "kilogram", "kg");
        checkUnitRatioNameAndAbbreviation(Mass.Unit.g, 0.001, 0.000000001, "gram", "g");
        checkUnitRatioNameAndAbbreviation(Mass.Unit.lb, 0.453592, 0.000001, "pound", "lb");
        // Check two conversions between non-standard units
        assertEquals(16, getMultiplicationFactorTo(Mass.Unit.lb, Mass.Unit.oz), 0.00001, "one lb is 16 OUNCE");
        assertEquals(0.0625, getMultiplicationFactorTo(Mass.Unit.oz, Mass.Unit.lb), 0.000001, "one OUNCE is 0.0625 lb");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(Mass.Unit.oz, 0.0283495, 0.0000001, "ounce", "oz");
        checkUnitRatioNameAndAbbreviation(Mass.Unit.long_tn, 1016.046906, 0.00001, "long ton", "long tn");
        checkUnitRatioNameAndAbbreviation(Mass.Unit.sh_tn, 907.18474, 0.00001, "short ton", "sh tn");
        checkUnitRatioNameAndAbbreviation(Mass.Unit.t, 1000, 0.001, "metric tonne", "t");
    }

    /**
     * Verify that we can create our own mass unit.
     */
    @Test
    public final void createMassUnit()
    {
        Mass.Unit myMU = Mass.Unit.kg.deriveUnit("pn", "Person", 80.0, UnitSystem.OTHER);
        assertTrue(null != myMU, "Can create a new MassUnit");
        checkUnitRatioNameAndAbbreviation(myMU, 80, 1, "Person", "pn");
        Units.unregister(myMU);
    }

}
