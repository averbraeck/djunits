package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.RadioActivity;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * RadioActivityUnitTest.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class RadioActivityUnitTest extends AbstractLinearUnitTest<RadioActivity.Unit>
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
        assertEquals("1/s", RadioActivity.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(RadioActivity.Unit.Bq, 1, 0.000001, "becquerel", "Bq");
    }

    /**
     * Verify that we can create our own RadioActivity unit.
     */
    @Test
    public final void createRadioActivityUnit()
    {
        RadioActivity.Unit myUnit = RadioActivity.Unit.SI.deriveUnit("my", "myRadioActivity", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new RadioActivityUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myRadioActivity", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("/s"), RadioActivity.ONE.getDisplayUnit().siUnit());
        assertEquals(RadioActivity.Unit.Bq, RadioActivity.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(RadioActivity.ONE, RadioActivity.Unit.Bq.ofSi(1.0));

        RadioActivity.Unit nonlinearUnit = new RadioActivity.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
