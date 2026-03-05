package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Illuminance;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Illuminance.Unit test. <br>
 * <br>
 * Copyright (c) 2013-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Peter Knoppers
 */
public class IlluminanceUnitTest extends AbstractLinearUnitTest<Illuminance.Unit>
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
        assertEquals("srcd/m2", Illuminance.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Illuminance.Unit.lx, 1, 0.000001, "lux", "lx");
    }

    /**
     * Verify that we can create our own Illuminance unit.
     */
    @Test
    public final void createIlluminanceUnit()
    {
        Illuminance.Unit myUnit = Illuminance.Unit.SI.deriveUnit("my", "myIlluminance", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new IlluminanceUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myIlluminance", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("srcd/m2"), Illuminance.ONE.getDisplayUnit().siUnit());
        assertEquals(Illuminance.Unit.lx, Illuminance.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Illuminance.ONE, Illuminance.Unit.lx.ofSi(1.0));

        Illuminance.Unit nonlinearUnit = new Illuminance.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
