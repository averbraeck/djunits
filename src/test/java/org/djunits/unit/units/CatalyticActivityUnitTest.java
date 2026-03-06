package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.CatalyticActivity;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * CatalyticActivity.Unit test. <p>
 * Copyright (c) 2013-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Peter Knoppers
 */
public class CatalyticActivityUnitTest extends AbstractLinearUnitTest<CatalyticActivity.Unit>
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
        assertEquals("mol/s", CatalyticActivity.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(CatalyticActivity.Unit.kat, 1, 0.000001, "katal", "kat");
    }

    /**
     * Verify that we can create our own CatalyticActivity unit.
     */
    @Test
    public final void createCatalyticActivityUnit()
    {
        CatalyticActivity.Unit myUnit =
                CatalyticActivity.Unit.SI.deriveUnit("my", "myCatalyticActivity", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new CatalyticActivityUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myCatalyticActivity", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("mol/s"), CatalyticActivity.ONE.getDisplayUnit().siUnit());
        assertEquals(CatalyticActivity.Unit.kat, CatalyticActivity.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(CatalyticActivity.ONE, CatalyticActivity.Unit.kat.ofSi(1.0));

        CatalyticActivity.Unit nonlinearUnit =
                new CatalyticActivity.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
