package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.ElectricCurrent;
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
public class ElectricCurrentUnitTest extends AbstractLinearUnitTest<ElectricCurrent.Unit>
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
        assertEquals("A", ElectricCurrent.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricCurrent.Unit.A, 1, 0.00000001, "ampere", "A");
        checkUnitRatioNameAndAbbreviation(ElectricCurrent.Unit.mA, 0.001, 0.000000001, "milliampere", "mA");
        // Check two conversions between two units
        assertEquals(1000, getMultiplicationFactorTo(ElectricCurrent.Unit.A, ElectricCurrent.Unit.mA), 0.01,
                "one AMPERE is 1000 MILLIAMPERE");
        assertEquals(0.001, getMultiplicationFactorTo(ElectricCurrent.Unit.mA, ElectricCurrent.Unit.A), 0.0001,
                "one MILLI AMPERE is 0.001 AMPERE");
    }

    /**
     * Verify that we can create our own ElectricCurrent unit.
     */
    @Test
    public final void createElectricCurrentUnit()
    {
        ElectricCurrent.Unit myUnit = ElectricCurrent.Unit.SI.deriveUnit("my", "myElectricCurrent", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new ElectricCurrentUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myElectricCurrent", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("A"), ElectricCurrent.ONE.getDisplayUnit().siUnit());
        assertEquals(ElectricCurrent.Unit.A, ElectricCurrent.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(ElectricCurrent.ONE, ElectricCurrent.Unit.A.ofSi(1.0));

        ElectricCurrent.Unit nonlinearUnit = new ElectricCurrent.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
