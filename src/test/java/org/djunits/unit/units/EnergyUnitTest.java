package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Energy;
import org.djunits.unit.Units;
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
public class EnergyUnitTest extends AbstractLinearUnitTest<Energy.Unit>
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
        assertEquals("kgm2/s2", Energy.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Energy.Unit.J, 1, 0.00000001, "joule", "J");
        checkUnitRatioNameAndAbbreviation(Energy.Unit.ft_lbf, 1.35581794833, 0.0000005, "foot pound-force", "ft.lbf");
        checkUnitRatioNameAndAbbreviation(Energy.Unit.BTU_ISO, 1054.5, 0.001, "British thermal unit (ISO)", "BTU(ISO)");
        // Check two conversions between non-standard units
        assertEquals(0.0013, getMultiplicationFactorTo(Energy.Unit.ft_lbf, Energy.Unit.BTU_ISO), 0.0001,
                "one FOOT POUND FORCE is about 0.0013 BTU ISO");
        // Check conversion factor to standard unit for all remaining acceleration units
        checkUnitRatioNameAndAbbreviation(Energy.Unit.in_lbf, 0.112984829, 0.000000001, "inch pound-force", "in.lbf");
        checkUnitRatioNameAndAbbreviation(Energy.Unit.BTU_IT, 1055.05585262, 0.000001, "British thermal unit (Int. Table)",
                "BTU(IT)");
        checkUnitRatioNameAndAbbreviation(Energy.Unit.cal_IT, 4.1868, 0.00005, "calorie (Int. Table)", "cal(IT)");
        checkUnitRatioNameAndAbbreviation(Energy.Unit.kcal, 4184, 0.05, "kilocalorie", "kcal");
        checkUnitRatioNameAndAbbreviation(Energy.Unit.kWh, 3600000, 0.1, "kilowatt hour", "kWh");
    }

    /**
     * Verify that we can create our own Energy unit.
     */
    @Test
    public final void createEnergyUnit()
    {
        Energy.Unit myUnit = Energy.Unit.SI.deriveUnit("my", "myEnergy", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new EnergyUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myEnergy", "my");
        Units.unregister(myUnit);
    }

}
