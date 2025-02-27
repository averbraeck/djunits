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
 * @author <a href="https://tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class DirectionUnitTest extends AbstractUnitTest<DirectionUnit>
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
     * Verify one conversion factor to standard unit and the localization of the name and abbreviation.
     * @param au Unit to check
     * @param expectedValue expected value of one 'unit to check' in SI units
     * @param precision precision of verification
     * @param expectedName expected name in the resources
     * @param expectedAbbreviation expected abbreviation in the resources
     */
    protected final void checkUnitValueNameAndAbbreviation(final DirectionUnit au, final double expectedValue,
            final double precision, final String expectedName, final String expectedAbbreviation)
    {
        assertEquals("rad", DirectionUnit.DEFAULT.getQuantity().getSiDimensions().toString(true, false));
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
        checkUnitValueNameAndAbbreviation(DirectionUnit.EAST_RADIAN, 1.0, 0.00001, "radians (East)", "rad(E)");
        checkUnitValueNameAndAbbreviation(DirectionUnit.EAST_DEGREE, Math.PI / 180.0, 0.00001, "degrees (East)", "\u00b0(E)");
        checkUnitValueNameAndAbbreviation(DirectionUnit.NORTH_RADIAN, 1.0 + Math.PI / 2.0, 0.00001, "radians (North)",
                "rad(N)");
        checkUnitValueNameAndAbbreviation(DirectionUnit.NORTH_DEGREE, Math.PI / 2.0 + Math.PI / 180.0, 0.00001,
                "degrees (North)", "\u00b0(N)");
    }

    /**
     * Verify that we can create our own direction unit.
     */
    @Test
    public final void createClockwiseDirectionUnit()
    {
        // clockwise degrees from 0 EAST
        DirectionUnit myAPU = DirectionUnit.EAST_RADIAN.deriveLinearOffset(-Math.PI / 180.0, -0.0, AngleUnit.RADIAN, "cDE",
                "clockDegE", UnitSystem.OTHER);
        assertTrue(null != myAPU, "Can create a new DirectionUnit");
        checkUnitValueNameAndAbbreviation(myAPU, -Math.PI / 180.0, 0.0001, "clockDegE", "cDE");
        DirectionUnit.BASE.unregister(myAPU);
    }

    /**
     * Verify that we can create our own direction unit.
     */
    @Test
    public final void createCompassDirectionUnit()
    {
        // clockwise degrees from 0 NORTH (compass)
        DirectionUnit myAPU = DirectionUnit.EAST_RADIAN.deriveLinearOffset(-Math.PI / 180.0, -90.0, AngleUnit.DEGREE, "cDN",
                "compass", UnitSystem.OTHER);
        assertTrue(null != myAPU, "Can create a new DirectionUnit");
        checkUnitValueNameAndAbbreviation(myAPU, Math.PI / 2.0 - Math.PI / 180.0, 0.0001, "compass", "cDN");
        DirectionUnit.BASE.unregister(myAPU);
    }

    /**
     * Verify relative base unit.
     */
    @Test
    public final void testRelative()
    {
        assertEquals(AngleUnit.BASE, DirectionUnit.DEFAULT.getRelativeQuantity());
    }

}
