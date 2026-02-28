package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Area;
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
public class AreaUnitTest extends AbstractLinearUnitTest<Area.Unit>
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
        assertEquals("m2", Area.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Area.Unit.m2, 1, 0.00000001, "square meter", "m2");
        checkUnitRatioNameAndAbbreviation(Area.Unit.km2, 1000000, 0.05, "square kilometer", "km2");
        checkUnitRatioNameAndAbbreviation(Area.Unit.mi2, 2589990, 2, "square mile", "mi2");
        // Check two conversions between non-standard units
        assertEquals(640, getMultiplicationFactorTo(Area.Unit.mi2, Area.Unit.ac), 0.1, "one SQUARE MILE is 640 ACRE");
        // Check conversion factor to standard unit for all remaining area units
        checkUnitRatioNameAndAbbreviation(Area.Unit.a, 100, 0.001, "are", "a");
        checkUnitRatioNameAndAbbreviation(Area.Unit.ha, 10000, 0.01, "hectare", "ha");
        checkUnitRatioNameAndAbbreviation(Area.Unit.ft2, 0.092903, 0.000001, "square foot", "ft2");
        checkUnitRatioNameAndAbbreviation(Area.Unit.in2, 0.00064516, 0.00000001, "square inch", "in2");
        checkUnitRatioNameAndAbbreviation(Area.Unit.yd2, 0.836127, 0.000001, "square yard", "yd2");
        checkUnitRatioNameAndAbbreviation(Area.Unit.ac, 4046.9, 0.05, "acre", "ac");
    }

    /**
     * Verify that we can create our own Area unit.
     */
    @Test
    public final void createAreaUnit()
    {
        Area.Unit myUnit = Area.Unit.SI.deriveUnit("my", "myArea", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new AreaUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myArea", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("m2"), Area.ONE.getDisplayUnit().siUnit());
        assertEquals(Area.Unit.m2, Area.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Area.ONE, Area.Unit.m2.ofSi(1.0));

        Area.Unit nonlinearUnit = new Area.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
