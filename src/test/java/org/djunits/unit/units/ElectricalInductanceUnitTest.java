package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.ElectricalInductance;
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
public class ElectricalInductanceUnitTest extends AbstractLinearUnitTest<ElectricalInductance.Unit>
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
        assertEquals("kgm2/s2A2", ElectricalInductance.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricalInductance.Unit.H, 1, 0.000001, "henry", "H");
    }

    /**
     * Verify that we can create our own ElectricalInductance unit.
     */
    @Test
    public final void createElectricalInductanceUnit()
    {
        ElectricalInductance.Unit myUnit =
                ElectricalInductance.Unit.SI.deriveUnit("my", "myElectricalInductance", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new ElectricalInductanceUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myElectricalInductance", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("kgm2/s2A2"), ElectricalInductance.ONE.getDisplayUnit().siUnit());
        assertEquals(ElectricalInductance.Unit.H, ElectricalInductance.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(ElectricalInductance.ONE, ElectricalInductance.Unit.H.ofSi(1.0));

        ElectricalInductance.Unit nonlinearUnit =
                new ElectricalInductance.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
