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
public class AngleUnitTest extends AbstractLinearUnitTest<AngleUnit>
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
     * Verify one length conversion factor to standard unit and the localization of the name and abbreviation.
     * @param au Unit to check
     * @param expectedValue Double; expected value of one 'unit to check' in SI units
     * @param precision Double; precision of verification
     * @param expectedName String; expected name in the resources
     * @param expectedAbbreviation String; expected abbreviation in the resources
     */
    protected final void checkUnitValueNameAndAbbreviation(final AngleUnit au, final double expectedValue,
            final double precision, final String expectedName, final String expectedAbbreviation)
    {
        assertEquals("rad", AngleUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        assertEquals(expectedValue, au.getScale().toStandardUnit(1.0), precision,
                String.format("one %s is about %f reference unit", au.getId(), expectedValue));
        assertEquals(expectedName, au.getName(), String.format("Name of %s is %s", au.getId(), expectedName));
        assertEquals(expectedAbbreviation, au.getDefaultDisplayAbbreviation(),
                String.format("Abbreviation of %s is %s", au.getId(), expectedAbbreviation));
    }

    /**
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        checkUnitValueNameAndAbbreviation(AngleUnit.DEGREE, 2 * Math.PI / 360, 0.000001, "degree", "\u00b0");
        checkUnitValueNameAndAbbreviation(AngleUnit.ARCMINUTE, 2 * Math.PI / 360 / 60, 0.0001, "arcminute", "\'");
        checkUnitValueNameAndAbbreviation(AngleUnit.GRAD, 2 * Math.PI / 400, 0.00001, "gradian", "grad");
        // TODO Check two conversions between non-standard Angle units
        assertEquals(54, getMultiplicationFactorTo(AngleUnit.GRAD, AngleUnit.ARCMINUTE), 0.5, "one GRAD is about 54 ARCMINUTE");
        assertEquals(0.0185, getMultiplicationFactorTo(AngleUnit.ARCMINUTE, AngleUnit.GRAD), 0.0001,
                "one ARCMINUTE is about 0.0185 GRAD");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitValueNameAndAbbreviation(AngleUnit.CENTESIMAL_ARCMINUTE, 0.00015708, 0.0000001, "centesimal arcminute", "c\'");
        checkUnitValueNameAndAbbreviation(AngleUnit.CENTESIMAL_ARCSECOND, 1.57079e-6, 0.1, "centesimal arcsecond", "c\"");
        checkUnitValueNameAndAbbreviation(AngleUnit.PERCENT, 0.0099996667, 0.0001, "percent", "%");
    }

    /**
     * Verify that we can create our own angle unit.
     */
    @Test
    public final void createAngleUnit()
    {
        AngleUnit myAPU = AngleUnit.RADIAN.deriveLinear(0.19634954085, "pt", "point", UnitSystem.OTHER);
        assertTrue(null != myAPU, "Can create a new AngleUnit");
        checkUnitValueNameAndAbbreviation(myAPU, 0.19634954085, 0.0000001, "point", "pt");
        AngleUnit.BASE.unregister(myAPU);
    }

}
