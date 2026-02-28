package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.MagneticFlux;
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
public class MagneticFluxUnitTest extends AbstractLinearUnitTest<MagneticFlux.Unit>
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
        assertEquals("kgm2/s2A", MagneticFlux.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(MagneticFlux.Unit.Wb, 1, 0.000001, "weber", "Wb");
    }

    /**
     * Verify that we can create our own MagneticFlux unit.
     */
    @Test
    public final void createMagneticFluxUnit()
    {
        MagneticFlux.Unit myUnit = MagneticFlux.Unit.SI.deriveUnit("my", "myMagneticFlux", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new MagneticFluxUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myMagneticFlux", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("kgm2/s2A"), MagneticFlux.ONE.getDisplayUnit().siUnit());
        assertEquals(MagneticFlux.Unit.Wb, MagneticFlux.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(MagneticFlux.ONE, MagneticFlux.Unit.Wb.ofSi(1.0));

        MagneticFlux.Unit nonlinearUnit = new MagneticFlux.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
