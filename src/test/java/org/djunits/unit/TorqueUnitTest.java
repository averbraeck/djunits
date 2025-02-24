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
public class TorqueUnitTest extends AbstractLinearUnitTest<TorqueUnit>
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
        assertEquals("kgm2/s2", TorqueUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(TorqueUnit.NEWTON_METER, 1, 0.00000001, "Newton meter", "N.m");
        checkUnitRatioNameAndAbbreviation(TorqueUnit.METER_KILOGRAM_FORCE, 9.80665, 0.000005, "meter kilogram-force", "m.kgf");
        checkUnitRatioNameAndAbbreviation(TorqueUnit.POUND_FOOT, 1.35581794833, 0.0000001, "pound-foot", "lbf.ft");
        // Check two conversions between non-standard units
        assertEquals(12, getMultiplicationFactorTo(TorqueUnit.POUND_FOOT, TorqueUnit.POUND_INCH), 0.0001,
                "one FOOT POUND FORCE is 12 INCH_POUND_FORCE");
        // Check conversion factor to standard unit for all remaining acceleration units
        checkUnitRatioNameAndAbbreviation(TorqueUnit.POUND_INCH, 0.112984829, 0.000000001, "pound-inch", "lbf.in");
    }

    /**
     * Verify that we can create our own Torque unit.
     */
    @Test
    public final void createTorqueUnit()
    {
        TorqueUnit myUnit = TorqueUnit.SI.deriveLinear(1.23, "my", "myTorque", UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new TorqueUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myTorque", "my");
        TorqueUnit.BASE.unregister(myUnit);
    }

}
