package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Length;
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
public class LengthUnitTest extends AbstractLinearUnitTest<Length.Unit>
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
        assertEquals("m", Length.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Length.Unit.m, 1, 0.00000001, "meter", "m");
        checkUnitRatioNameAndAbbreviation(Length.Unit.mi, 1609, 0.5, "mile", "mi");
        checkUnitRatioNameAndAbbreviation(Length.Unit.cm, 0.01, 0.000000001, "centimeter", "cm");
        // Check two conversions between non-standard units
        assertEquals(160900, getMultiplicationFactorTo(Length.Unit.mi, Length.Unit.cm), 50, "one mi is about 160900 cm");
        assertEquals(0.000006215, getMultiplicationFactorTo(Length.Unit.cm, Length.Unit.mi), 0.000000002,
                "one cm is about 0.000006215 mi");
        // Check conversion factor to standard unit for all remaining distance units
        checkUnitRatioNameAndAbbreviation(Length.Unit.mm, 0.001, 0.000000001, "millimeter", "mm");
        checkUnitRatioNameAndAbbreviation(Length.Unit.dm, 0.1, 0.000000001, "decimeter", "dm");
        checkUnitRatioNameAndAbbreviation(Length.Unit.dam, 10, 0.0000001, "decameter", "dam");
        checkUnitRatioNameAndAbbreviation(Length.Unit.hm, 100, 0.000001, "hectometer", "hm");
        checkUnitRatioNameAndAbbreviation(Length.Unit.km, 1000, 0.00001, "kilometer", "km");
        checkUnitRatioNameAndAbbreviation(Length.Unit.ft, 0.3048, 0.000001, "foot", "ft");
        checkUnitRatioNameAndAbbreviation(Length.Unit.in, 0.0254, 0.0000001, "inch", "in");
        checkUnitRatioNameAndAbbreviation(Length.Unit.NM, 1852, 0.5, "Nautical Mile", "NM");
        checkUnitRatioNameAndAbbreviation(Length.Unit.yd, 0.9144, 0.00005, "yard", "yd");
    }

    /**
     * Verify that we can create our own length unit.
     */
    @Test
    public final void createLengthUnit()
    {
        Length.Unit myLU = Length.Unit.m.deriveUnit("fl", "Furlong", 201.16800, UnitSystem.OTHER);
        assertTrue(null != myLU, "Can create a new LengthUnit");
        checkUnitRatioNameAndAbbreviation(myLU, 200, 2, "Furlong", "fl");
        Units.unregister(myLU);
    }
}
