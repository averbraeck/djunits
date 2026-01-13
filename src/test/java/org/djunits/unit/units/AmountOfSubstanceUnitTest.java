package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.AmountOfSubstance;
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
public class AmountOfSubstanceUnitTest extends AbstractLinearUnitTest<AmountOfSubstance.Unit>
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
        assertEquals("mol", AmountOfSubstance.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(AmountOfSubstance.Unit.mol, 1, 0.000001, "mole", "mol");
    }

    /**
     * Verify that we can create our own AmountOfSubstance unit.
     */
    @Test
    public final void createAmountOfSubstanceUnit()
    {
        AmountOfSubstance.Unit myUnit =
                AmountOfSubstance.Unit.SI.deriveUnit("my", "myAmountOfSubstance", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new AmountOfSubstanceUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myAmountOfSubstance", "my");
        Units.unregister(myUnit);
    }

}
