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
public class PositionUnitTest extends AbstractOffsetUnitTest<PositionUnit>
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
        assertEquals("m", PositionUnit.DEFAULT.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioOffsetNameAndAbbreviation(PositionUnit.METER, 1, 0.0, 0.00000001, "meter", "m");
        checkUnitRatioOffsetNameAndAbbreviation(PositionUnit.MILE, 1609.34, 0.0, 0.05, "mile", "mi");
        checkUnitRatioOffsetNameAndAbbreviation(PositionUnit.FOOT, 0.3048, 0.0, 0.001, "foot", "ft");
        // Check two conversions between non-standard units
        assertEquals("one FOOT is 0.3048 METER", 0.3048, getMultiplicationFactorTo(PositionUnit.FOOT, PositionUnit.METER),
                0.0001);
        assertEquals("one LIGHTYEAR is about 9.461E12 KILOMETER", 9.461E12,
                getMultiplicationFactorTo(PositionUnit.LIGHTYEAR, PositionUnit.KILOMETER), 1E9);

        // TODO: other units
    }

    /**
     * Verify that we can create our own PositionUnit.
     */
    @Test
    public final void createPositionUnit()
    {
        PositionUnit myUnit =
                PositionUnit.DEFAULT.deriveLinearOffset(1.23, 0.0, LengthUnit.METER, "my", "myPosition", UnitSystem.OTHER);
        assertTrue("Can create a new PositionUnit", null != myUnit);
        checkUnitRatioOffsetNameAndAbbreviation(myUnit, 1.23, 0.0, 0.0001, "myPosition", "my");
        PositionUnit.BASE.unregister(myUnit);
    }

    /**
     * Verify relative base unit.
     */
    @Test
    public final void testRelative()
    {
        assertEquals(LengthUnit.BASE, PositionUnit.DEFAULT.getRelativeQuantity());
    }

}
