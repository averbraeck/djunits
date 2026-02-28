package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Power;
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
public class PowerUnitTest extends AbstractLinearUnitTest<Power.Unit>
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
        assertEquals("kgm2/s3", Power.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Power.Unit.W, 1, 0.00000001, "watt", "W");
        checkUnitRatioNameAndAbbreviation(Power.Unit.ft_lbf_h, 0.00037661608333, 0.0000000001, "foot pound-force per hour",
                "ft.lbf/h");
        checkUnitRatioNameAndAbbreviation(Power.Unit.ft_lbf_min, 0.022596965, 0.000001, "foot pound-force per minute",
                "ft.lbf/min");
        // Check two conversions between non-standard units
        assertEquals(0.01666667, getMultiplicationFactorTo(Power.Unit.ft_lbf_h, Power.Unit.ft_lbf_min), 0.0000001,
                "one FOOT POUND FORCE PER HOUR is about 0.0166667 FOOT POUND FORCE PER MINUTE");
        assertEquals(60, getMultiplicationFactorTo(Power.Unit.ft_lbf_min, Power.Unit.ft_lbf_h), 0.000001,
                "one FOOT POUND FORCE PER MINUTE is 60 FOOT POUND FORCE PER HOUR");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(Power.Unit.kW, 1000, 0.001, "kilowatt", "kW");
        checkUnitRatioNameAndAbbreviation(Power.Unit.MW, 1000000, 1, "megawatt", "MW");
        checkUnitRatioNameAndAbbreviation(Power.Unit.GW, 1e9, 1e3, "gigawatt", "GW");
        checkUnitRatioNameAndAbbreviation(Power.Unit.ft_lbf_s, 1.3558179, 0.000001, "foot pound-force per second", "ft.lbf/s");
        checkUnitRatioNameAndAbbreviation(Power.Unit.hp_M, 735.49875, 0.00001, "horsepower (metric)", "hp(M)");
    }

    /**
     * Verify that we can create our own power unit.
     */
    @Test
    public final void createPowerUnitUnit()
    {
        Power.Unit myMU = Power.Unit.W.deriveUnit("pnp", "Person", 250.0, UnitSystem.OTHER);
        assertTrue(null != myMU, "Can create a new PowerUnit");
        checkUnitRatioNameAndAbbreviation(myMU, 250, 1, "Person", "pnp");
        Units.unregister(myMU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("kgm2/s3"), Power.ONE.getDisplayUnit().siUnit());
        assertEquals(Power.Unit.W, Power.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Power.ONE, Power.Unit.W.ofSi(1.0));

        Power.Unit nonlinearUnit = new Power.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
