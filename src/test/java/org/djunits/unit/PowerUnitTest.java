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
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class PowerUnitTest extends AbstractLinearUnitTest<PowerUnit>
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
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        assertEquals("kgm2/s3", PowerUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(PowerUnit.WATT, 1, 0.00000001, "watt", "W");
        checkUnitRatioNameAndAbbreviation(PowerUnit.FOOT_POUND_FORCE_PER_HOUR, 0.00037661608333, 0.0000000001,
                "foot pound-force per hour", "ft.lbf/h");
        checkUnitRatioNameAndAbbreviation(PowerUnit.FOOT_POUND_FORCE_PER_MINUTE, 0.022596965, 0.000001,
                "foot pound-force per minute", "ft.lbf/min");
        // Check two conversions between non-standard units
        assertEquals(0.01666667,
                getMultiplicationFactorTo(PowerUnit.FOOT_POUND_FORCE_PER_HOUR, PowerUnit.FOOT_POUND_FORCE_PER_MINUTE),
                0.0000001, "one FOOT POUND FORCE PER HOUR is about 0.0166667 FOOT POUND FORCE PER MINUTE");
        assertEquals(60, getMultiplicationFactorTo(PowerUnit.FOOT_POUND_FORCE_PER_MINUTE, PowerUnit.FOOT_POUND_FORCE_PER_HOUR),
                0.000001, "one FOOT POUND FORCE PER MINUTE is 60 FOOT POUND FORCE PER HOUR");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(PowerUnit.KILOWATT, 1000, 0.001, "kilowatt", "kW");
        checkUnitRatioNameAndAbbreviation(PowerUnit.MEGAWATT, 1000000, 1, "megawatt", "MW");
        checkUnitRatioNameAndAbbreviation(PowerUnit.GIGAWATT, 1e9, 1e3, "gigawatt", "GW");
        checkUnitRatioNameAndAbbreviation(PowerUnit.FOOT_POUND_FORCE_PER_SECOND, 1.3558179, 0.000001,
                "foot pound-force per second", "ft.lbf/s");
        checkUnitRatioNameAndAbbreviation(PowerUnit.HORSEPOWER_METRIC, 735.49875, 0.00001, "horsepower (metric)", "hp(M)");
    }

    /**
     * Verify that we can create our own power unit.
     */
    @Test
    public final void createPowerUnitUnit()
    {
        PowerUnit myMU = PowerUnit.WATT.deriveLinear(250, "pnp", "Person", UnitSystem.OTHER);
        assertTrue(null != myMU, "Can create a new PowerUnit");
        checkUnitRatioNameAndAbbreviation(myMU, 250, 1, "Person", "pnp");
        PowerUnit.BASE.unregister(myMU);
    }

}
