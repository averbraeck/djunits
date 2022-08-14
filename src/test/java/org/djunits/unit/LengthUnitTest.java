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
public class LengthUnitTest extends AbstractLinearUnitTest<LengthUnit>
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
        assertEquals("m", LengthUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(LengthUnit.METER, 1, 0.00000001, "meter", "m");
        checkUnitRatioNameAndAbbreviation(LengthUnit.MILE, 1609, 0.5, "mile", "mi");
        checkUnitRatioNameAndAbbreviation(LengthUnit.CENTIMETER, 0.01, 0.000000001, "centimeter", "cm");
        // Check two conversions between non-standard units
        assertEquals("one MILE is about 160900 CENTIMETER", 160900,
                getMultiplicationFactorTo(LengthUnit.MILE, LengthUnit.CENTIMETER), 50);
        assertEquals("one CENTIMETER is about 0.000006215 MILE", 0.000006215,
                getMultiplicationFactorTo(LengthUnit.CENTIMETER, LengthUnit.MILE), 0.000000002);
        // Check conversion factor to standard unit for all remaining distance units
        checkUnitRatioNameAndAbbreviation(LengthUnit.MILLIMETER, 0.001, 0.000000001, "millimeter", "mm");
        checkUnitRatioNameAndAbbreviation(LengthUnit.DECIMETER, 0.1, 0.000000001, "decimeter", "dm");
        checkUnitRatioNameAndAbbreviation(LengthUnit.DECAMETER, 10, 0.0000001, "decameter", "dam");
        checkUnitRatioNameAndAbbreviation(LengthUnit.HECTOMETER, 100, 0.000001, "hectometer", "hm");
        checkUnitRatioNameAndAbbreviation(LengthUnit.KILOMETER, 1000, 0.00001, "kilometer", "km");
        checkUnitRatioNameAndAbbreviation(LengthUnit.FOOT, 0.3048, 0.000001, "foot", "ft");
        checkUnitRatioNameAndAbbreviation(LengthUnit.INCH, 0.0254, 0.0000001, "inch", "in");
        checkUnitRatioNameAndAbbreviation(LengthUnit.NAUTICAL_MILE, 1852, 0.5, "nautical mile", "NM");
        checkUnitRatioNameAndAbbreviation(LengthUnit.YARD, 0.9144, 0.00005, "yard", "yd");
    }

    /**
     * Verify that we can create our own length unit.
     */
    @Test
    public final void createLengthUnit()
    {
        LengthUnit myLU = LengthUnit.METER.deriveLinear(201.16800, "fl", "Furlong", UnitSystem.OTHER);
        assertTrue("Can create a new LengthUnit", null != myLU);
        checkUnitRatioNameAndAbbreviation(myLU, 200, 2, "Furlong", "fl");
        LengthUnit.BASE.unregister(myLU);
    }
}
