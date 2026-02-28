package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Volume;
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
public class VolumeUnitTest extends AbstractLinearUnitTest<Volume.Unit>
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
        assertEquals("m3", Volume.Unit.SI.siUnit().toString(true, false));
        checkUnitRatioNameAndAbbreviation(Volume.Unit.m3, 1, 0.00000001, "cubic meter", "m3");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.dm3, 0.001, 0.0000000001, "cubic decimeter", "dm3");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.L, 0.001, 0.0000000001, "liter", "L");
        // Check two conversions between non-standard units
        assertEquals(5451776000., getMultiplicationFactorTo(Volume.Unit.mi3, Volume.Unit.yd3), 0.5,
                "one CUBIC MILE is about 5451776000 CUBIC YARD");
        assertEquals(1.83426465e-10, getMultiplicationFactorTo(Volume.Unit.yd3, Volume.Unit.mi3), 0.0000000001,
                "one CUBIC YARD is 1.83426465e-10 CUBIC MILE");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(Volume.Unit.cm3, 0.000001, 0.000000000001, "cubic centimeter", "cm3");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.km3, 1e9, 1, "cubic kilometer", "km3");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.mi3, 4.16818183e9, 1000, "cubic mile", "mi3");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.ft3, 0.0283168, 0.0000001, "cubic foot", "ft3");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.in3, 1.6387e-5, 1e-9, "cubic inch", "in3");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.yd3, 0.764554858, 0.0000001, "cubic yard", "yd3");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.gal_US, 0.0037854, 0.0000001, "gallon (US)", "gal(US)");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.fl_oz_US, 0.000029574, 0.000000001, "fluid ounce (US)", "fl.oz(US)");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.fl_oz_imp, .00002841306, 0.00000000001, "fluid ounce (imp)",
                "fl.oz(imp)");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.pt_US, 0.000473176473, 0.0000000000001, "pint (US)", "pt(US)");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.pt_imp, 0.5 * 0.00113652, 0.00001, "pint (imp)", "pt(imp)");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.qt_US, 0.000946353, 0.0000000001, "quart (US)", "qt(US)");
        checkUnitRatioNameAndAbbreviation(Volume.Unit.qt_imp, 0.00113652, 0.000005, "quart (imp)", "qt(imp)");
    }

    /**
     * Verify that we can create our own power unit.
     */
    @Test
    public final void createVolumeUnit()
    {
        Volume.Unit myVU = Volume.Unit.L.deriveUnit("brl", "Barrel", 119.240471, UnitSystem.OTHER);
        assertTrue(null != myVU, "Can create a new Volume.Unit");
        checkUnitRatioNameAndAbbreviation(myVU, 0.119240471, 0.000001, "Barrel", "brl");
        Units.unregister(myVU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("m3"), Volume.ONE.getDisplayUnit().siUnit());
        assertEquals(Volume.Unit.m3, Volume.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Volume.ONE, Volume.Unit.m3.ofSi(1.0));

        Volume.Unit nonlinearUnit = new Volume.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
