package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Temperature;
import org.djunits.quantity.TemperatureDifference;
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
public class TemperatureUnitTest extends AbstractLinearUnitTest<Temperature.Unit>
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
        assertEquals("K", Temperature.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Temperature.Unit.K, 1.0, 0.00001, "kelvin", "K");
        checkUnitRatioNameAndAbbreviation(Temperature.Unit.degC, 1.0, 0.000001, "degree Celsius", "\u00B0C");
        checkUnitRatioNameAndAbbreviation(Temperature.Unit.degF, 5.0 / 9.0, 0.00001, "degree Fahrenheit", "\u00B0F");
        // Check two conversions between non-standard units
        assertEquals(9.0 / 5.0, getMultiplicationFactorTo(Temperature.Unit.degC, Temperature.Unit.degF), 0.0001,
                "one DEGREE CELSIUS is 9/5 DEGREE FAHRENHEIT");
        checkUnitRatioNameAndAbbreviation(Temperature.Unit.degR, 5. / 9., 0.0001, "degree Rankine", "\u00B0R");
        checkUnitRatioNameAndAbbreviation(Temperature.Unit.degRe, 0.8, 0.000001, "degree Reaumur", "\u00B0R\u00E9");
    }

    /**
     * Verify that we can create our own temperature unit; i.c. Newton.
     */
    @Test
    public final void createTempteratureUnit()
    {
        Temperature.Unit myTU = Temperature.Unit.K.deriveUnit("N", "Newton", 3.0, UnitSystem.OTHER);
        assertTrue(null != myTU, "Can create a new TempteratureUnit");
        checkUnitRatioNameAndAbbreviation(myTU, 3.0, 0.0001, "Newton", "N");
        Units.unregister(myTU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("K"), TemperatureDifference.ONE.getDisplayUnit().siUnit());
        assertEquals(Temperature.Unit.K, TemperatureDifference.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(TemperatureDifference.ONE, Temperature.Unit.K.ofSi(1.0));

        Temperature.Unit nonlinearUnit = new Temperature.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
