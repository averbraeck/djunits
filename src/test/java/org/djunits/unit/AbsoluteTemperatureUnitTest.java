package org.djunits.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.djunits.locale.DefaultLocale;
import org.djunits.unit.unitsystem.UnitSystem;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class AbsoluteTemperatureUnitTest extends AbstractOffsetUnitTest<AbsoluteTemperatureUnit>
{
    /**
     * Set the locale to "en" so we know what texts should be retrieved from the resources.
     */
    @Before
    public final void setup()
    {
        DefaultLocale.setLocale(new Locale("en"));
    }

    /**
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        assertEquals("K", AbsoluteTemperatureUnit.DEFAULT.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioOffsetNameAndAbbreviation(AbsoluteTemperatureUnit.KELVIN, 1, 0, 0.00000001, "Kelvin", "K");
        checkUnitRatioOffsetNameAndAbbreviation(AbsoluteTemperatureUnit.DEGREE_CELSIUS, 1, 273.15, 0.000001, "degree Celsius",
                "\u00B0C");
        checkUnitRatioOffsetNameAndAbbreviation(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT, 5.0 / 9.0, 459.67, 0.00001,
                "degree Fahrenheit", "\u00B0F");
        // Check two conversions between non-standard units
        assertEquals("one DEGREE CELSIUS is 9/5 DEGREE FAHRENHEIT", 9.0 / 5.0,
                getMultiplicationFactorTo(AbsoluteTemperatureUnit.DEGREE_CELSIUS, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT),
                0.0001);
        assertEquals("zero DEGREE CELSIUS is 32 DEGREE FAHRENHEIT", 32, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT.getScale()
                .fromStandardUnit(AbsoluteTemperatureUnit.DEGREE_CELSIUS.getScale().toStandardUnit(0.0)), 0.0001);
        assertEquals("100 DEGREE CELSIUS is 212 DEGREE FAHRENHEIT", 212, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT.getScale()
                .fromStandardUnit(AbsoluteTemperatureUnit.DEGREE_CELSIUS.getScale().toStandardUnit(100.0)), 0.0001);
        assertEquals(
                "zero DEGREE FAHRENHEIT is about -17.7778 DEGREE CELSIUS", -17.7778, AbsoluteTemperatureUnit.DEGREE_CELSIUS
                        .getScale().fromStandardUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT.getScale().toStandardUnit(0.0)),
                0.0001);
        checkUnitRatioOffsetNameAndAbbreviation(AbsoluteTemperatureUnit.DEGREE_RANKINE, 5. / 9., 0, 0.0001, "degree Rankine",
                "\u00B0R");
        checkUnitRatioOffsetNameAndAbbreviation(AbsoluteTemperatureUnit.DEGREE_REAUMUR, 0.8, 273.15, 0.000001, "degree Reaumur",
                "\u00B0R\u00E9");
    }

    /**
     * Verify that we can create our own temperature unit; i.c. Newton.
     */
    @Test
    public final void createAbsoluteTempteratureUnit()
    {
        AbsoluteTemperatureUnit myTU = AbsoluteTemperatureUnit.KELVIN.deriveLinearOffset(3.0, 273.15, TemperatureUnit.KELVIN,
                "N", "Newton", UnitSystem.OTHER);
        assertTrue("Can create a new AbsoluteTempteratureUnit", null != myTU);
        checkUnitRatioOffsetNameAndAbbreviation(myTU, 3, 273.15, 0.0001, "Newton", "N");
        AbsoluteTemperatureUnit.BASE.unregister(myTU);
    }

    /**
     * Verify relative base unit.
     */
    @Test
    public final void testRelative()
    {
        assertEquals(TemperatureUnit.BASE, AbsoluteTemperatureUnit.DEFAULT.getRelativeQuantity());
    }

}
