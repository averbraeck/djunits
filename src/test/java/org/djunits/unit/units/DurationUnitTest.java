package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Duration;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.si.SIUnit;
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
public class DurationUnitTest extends AbstractLinearUnitTest<Duration.Unit>
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
        assertEquals("s", Duration.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Duration.Unit.s, 1, 0.00000001, "second", "s");
        checkUnitRatioNameAndAbbreviation(Duration.Unit.h, 3600, 0.0005, "hour", "h");
        checkUnitRatioNameAndAbbreviation(Duration.Unit.day, 86400, 0.001, "day", "day");
        // Check two conversions between non-standard units
        assertEquals(24, getMultiplicationFactorTo(Duration.Unit.day, Duration.Unit.h), 0.0001, "one DAY is 24 HOUR");
        assertEquals(0.0417, getMultiplicationFactorTo(Duration.Unit.h, Duration.Unit.day), 0.0001,
                "one HOUR is about 0.0417 DAY");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(Duration.Unit.ms, 0.001, 0.00000001, "millisecond", "ms");
        checkUnitRatioNameAndAbbreviation(Duration.Unit.min, 60, 0.000001, "minute", "min");
        checkUnitRatioNameAndAbbreviation(Duration.Unit.wk, 7 * 86400, 0.1, "week", "wk");
    }

    /**
     * Verify that we can create our own duration unit.
     */
    @Test
    public final void createDurationUnit()
    {
        Duration.Unit myTU = Duration.Unit.s.deriveUnit("fnx", "Fortnight", 14.0 * 86400, UnitSystem.OTHER);
        assertTrue(null != myTU, "Can create a new DurationUnit");
        checkUnitRatioNameAndAbbreviation(myTU, 14 * 86400, 1, "Fortnight", "fnx");
        Units.unregister(myTU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("s"), Duration.ONE.getDisplayUnit().siUnit());
        assertEquals(Duration.Unit.s, Duration.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Duration.ONE, Duration.Unit.s.ofSi(1.0));

        Duration.Unit nonlinearUnit = new Duration.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
