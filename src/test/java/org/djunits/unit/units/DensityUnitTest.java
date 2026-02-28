package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Density;
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
public class DensityUnitTest extends AbstractLinearUnitTest<Density.Unit>
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
        assertEquals("kg/m3", Density.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Density.Unit.kg_m3, 1, 0.00000001, "kilogram per cubic meter", "kg/m3");
        checkUnitRatioNameAndAbbreviation(Density.Unit.g_cm3, 1000, 0.0001, "gram per cubic centimeter", "g/cm3");
        // Check two conversions between two units
        assertEquals(0.001, getMultiplicationFactorTo(Density.Unit.kg_m3, Density.Unit.g_cm3), 0.000000001,
                "one KG PER CUBIC METER is 0.0001 GRAM PER CUBIC CENTIMETER");
    }

    /**
     * Verify that we can create our own density unit.
     */
    @Test
    public final void createDensityUnit()
    {
        Density.Unit myDU = Density.Unit.kg_m3.deriveUnit("SPCF", "DensityUnit", 515.317882, UnitSystem.SI_DERIVED);
        assertTrue(null != myDU, "Can create a new DensityUnit");
        checkUnitRatioNameAndAbbreviation(myDU, 515.3, 0.1, "DensityUnit", "SPCF");
        Units.unregister(myDU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("kg/m3"), Density.ONE.getDisplayUnit().siUnit());
        assertEquals(Density.Unit.kg_m3, Density.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Density.ONE, Density.Unit.kg_m3.ofSi(1.0));

        Density.Unit nonlinearUnit = new Density.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
