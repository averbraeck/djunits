package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.LinearObjectDensity;
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
 * @author Alexander Verbraeck
 */
public class LinearObjectDensityUnitTest extends AbstractLinearUnitTest<LinearObjectDensity.Unit>
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
        assertEquals("1/m", LinearObjectDensity.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(LinearObjectDensity.Unit.per_m, 1, 0.000001, "per meter", "/m");
        checkUnitRatioNameAndAbbreviation(LinearObjectDensity.Unit.per_km, 0.001, 0.0000001, "per kilometer", "/km");
        checkUnitRatioNameAndAbbreviation(LinearObjectDensity.Unit.per_mm, 1000, 0.01, "per millimeter", "/mm");
        // Check two conversions between non-standard units
        assertEquals(1000000, getMultiplicationFactorTo(LinearObjectDensity.Unit.per_mm, LinearObjectDensity.Unit.per_km), 0.1,
                "one per millimeter is 1000000 per kilometer");
        assertEquals(0.000001, getMultiplicationFactorTo(LinearObjectDensity.Unit.per_km, LinearObjectDensity.Unit.per_mm),
                0.00000005, "one per kilometer is 0.000001 per millimeter");
    }

    /**
     * Verify that we can create our own LinearDensity unit.
     */
    @Test
    public final void createLinearDensityUnit()
    {
        LinearObjectDensity.Unit muLDU =
                LinearObjectDensity.Unit.per_m.deriveUnit("perin", "PerInch", 2.54 / 100, UnitSystem.OTHER);
        assertTrue(null != muLDU, "Can create a new LinearObjectDensityUnit");
        checkUnitRatioNameAndAbbreviation(muLDU, 0.0254, 0.000001, "PerInch", "perin");
        Units.unregister(muLDU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("/m"), LinearObjectDensity.ONE.getDisplayUnit().siUnit());
        assertEquals(LinearObjectDensity.Unit.per_m, LinearObjectDensity.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(LinearObjectDensity.ONE, LinearObjectDensity.Unit.per_m.ofSi(1.0));

        LinearObjectDensity.Unit nonlinearUnit =
                new LinearObjectDensity.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
