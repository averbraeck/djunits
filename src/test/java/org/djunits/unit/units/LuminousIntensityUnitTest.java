package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.LuminousIntensity;
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
public class LuminousIntensityUnitTest extends AbstractLinearUnitTest<LuminousIntensity.Unit>
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
        assertEquals("cd", LuminousIntensity.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(LuminousIntensity.Unit.cd, 1, 0.000001, "candela", "cd");
    }

    /**
     * Verify that we can create our own LuminousIntensity unit.
     */
    @Test
    public final void createLuminousIntensityUnit()
    {
        LuminousIntensity.Unit myUnit =
                LuminousIntensity.Unit.SI.deriveUnit("my", "myLuminousIntensity", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new LuminousIntensityUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myLuminousIntensity", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("cd"), LuminousIntensity.ONE.getDisplayUnit().siUnit());
        assertEquals(LuminousIntensity.Unit.cd, LuminousIntensity.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(LuminousIntensity.ONE, LuminousIntensity.Unit.cd.ofSi(1.0));

        LuminousIntensity.Unit nonlinearUnit =
                new LuminousIntensity.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
