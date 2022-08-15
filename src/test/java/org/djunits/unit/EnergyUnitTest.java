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
public class EnergyUnitTest extends AbstractLinearUnitTest<EnergyUnit>
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
        assertEquals("kgm2/s2", EnergyUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(EnergyUnit.JOULE, 1, 0.00000001, "joule", "J");
        checkUnitRatioNameAndAbbreviation(EnergyUnit.FOOT_POUND_FORCE, 1.35581794833, 0.0000005, "foot pound-force", "ft.lbf");
        checkUnitRatioNameAndAbbreviation(EnergyUnit.BTU_ISO, 1054.5, 0.001, "British thermal unit (ISO)", "BTU(ISO)");
        // Check two conversions between non-standard units
        assertEquals("one FOOT POUND FORCE is about 0.0013 BTU ISO", 0.0013,
                getMultiplicationFactorTo(EnergyUnit.FOOT_POUND_FORCE, EnergyUnit.BTU_ISO), 0.0001);
        // Check conversion factor to standard unit for all remaining acceleration units
        checkUnitRatioNameAndAbbreviation(EnergyUnit.INCH_POUND_FORCE, 0.112984829, 0.000000001, "inch pound-force", "in.lbf");
        checkUnitRatioNameAndAbbreviation(EnergyUnit.BTU_IT, 1055.05585262, 0.000001,
                "British thermal unit (International Table)", "BTU(IT)");
        checkUnitRatioNameAndAbbreviation(EnergyUnit.CALORIE_IT, 4.1868, 0.00005, "calorie (International Table)", "cal(IT)");
        checkUnitRatioNameAndAbbreviation(EnergyUnit.KILOCALORIE, 4184, 0.05, "kilocalorie", "kcal");
        checkUnitRatioNameAndAbbreviation(EnergyUnit.KILOWATT_HOUR, 3600000, 0.1, "kilowatt-hour", "kWh");
    }

    /**
     * Verify that we can create our own Energy unit.
     */
    @Test
    public final void createEnergyUnit()
    {
        EnergyUnit myUnit = EnergyUnit.SI.deriveLinear(1.23, "my", "myEnergy", UnitSystem.OTHER);
        assertTrue("Can create a new EnergyUnit", null != myUnit);
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myEnergy", "my");
        EnergyUnit.BASE.unregister(myUnit);
    }

}
