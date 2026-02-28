package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.AbsorbedDose;
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
public class AbsorbedDoseUnitTest extends AbstractLinearUnitTest<AbsorbedDose.Unit>
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
        assertEquals("m2/s2", AbsorbedDose.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(AbsorbedDose.Unit.Gy, 1, 0.000001, "gray", "Gy");
    }

    /**
     * Verify that we can create our own AbsorbedDose unit.
     */
    @Test
    public final void createAbsorbedDoseUnit()
    {
        AbsorbedDose.Unit myUnit = AbsorbedDose.Unit.SI.deriveUnit("my", "myAbsorbedDose", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new AbsorbedDoseUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myAbsorbedDose", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("m2/s2"), AbsorbedDose.ONE.getDisplayUnit().siUnit());
        assertEquals(AbsorbedDose.Unit.Gy, AbsorbedDose.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(AbsorbedDose.ONE, AbsorbedDose.Unit.Gy.ofSi(1.0));
        
        AbsorbedDose.Unit nonlinearUnit = new AbsorbedDose.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }
    
}
