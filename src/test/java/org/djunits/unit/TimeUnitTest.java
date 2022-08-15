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
public class TimeUnitTest extends AbstractOffsetUnitTest<TimeUnit>
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
        assertEquals("s", TimeUnit.DEFAULT.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioOffsetNameAndAbbreviation(TimeUnit.BASE_SECOND, 1, 0.0, 0.00000001, "second", "s");
        checkUnitRatioOffsetNameAndAbbreviation(TimeUnit.BASE_HOUR, 3600, 0.0, 0.0005, "hour", "h");
        checkUnitRatioOffsetNameAndAbbreviation(TimeUnit.BASE_DAY, 86400, 0.0, 0.001, "day", "day");
        // Check two conversions between non-standard units
        assertEquals("one DAY is 24 HOUR", 24, getMultiplicationFactorTo(TimeUnit.BASE_DAY, TimeUnit.BASE_HOUR), 0.0001);
        assertEquals("one HOUR is about 0.0417 DAY", 0.0417, getMultiplicationFactorTo(TimeUnit.BASE_HOUR, TimeUnit.BASE_DAY),
                0.0001);
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioOffsetNameAndAbbreviation(TimeUnit.BASE_MILLISECOND, 0.001, 0.0, 0.00000001, "millisecond", "ms");
        checkUnitRatioOffsetNameAndAbbreviation(TimeUnit.BASE_MINUTE, 60, 0.0, 0.000001, "minute", "min");
        checkUnitRatioOffsetNameAndAbbreviation(TimeUnit.BASE_WEEK, 7 * 86400, 0.0, 0.1, "week", "wk");

        // TODO: shifted units
    }

    /**
     * Verify that we can create our own duration unit.
     */
    @Test
    public final void createTimeUnit()
    {
        TimeUnit myTU = TimeUnit.BASE_SECOND.deriveLinearOffset(14.0 * 86400, 0.0, DurationUnit.SECOND, "fn", "Fortnight",
                UnitSystem.OTHER);
        assertTrue("Can create a new TimeUnit", null != myTU);
        checkUnitRatioOffsetNameAndAbbreviation(myTU, 14 * 86400, 0.0, 1, "Fortnight", "fn");
        TimeUnit.BASE.unregister(myTU);
    }

    /**
     * Verify relative base unit.
     */
    @Test
    public final void testRelative()
    {
        assertEquals(DurationUnit.BASE, TimeUnit.DEFAULT.getRelativeQuantity());
    }

}
