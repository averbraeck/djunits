package org.djunits.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.djunits.locale.DefaultLocale;
import org.djunits.unit.unitsystem.UnitSystem;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class VolumeUnitTest extends AbstractLinearUnitTest<VolumeUnit>
{
    /**
     * Set the locale to "en" so we know what texts should be retrieved from the resources.
     */
    @Before
    public final void setup()
    {
        DefaultLocale.setLocale(new Locale("en"));
    }

    /**
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        assertEquals("m3", VolumeUnit.SI.getQuantity().getSiDimensions().toString(true, false));
        checkUnitRatioNameAndAbbreviation(VolumeUnit.CUBIC_METER, 1, 0.00000001, "cubic meter", "m^3");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.CUBIC_DECIMETER, 0.001, 0.0000000001, "cubic decimeter", "dm^3");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.LITER, 0.001, 0.0000000001, "liter", "L");
        // Check two conversions between non-standard units
        assertEquals("one CUBIC MILE is about 5451776000 CUBIC YARD", 5451776000.,
                getMultiplicationFactorTo(VolumeUnit.CUBIC_MILE, VolumeUnit.CUBIC_YARD), 0.5);
        assertEquals("one CUBIC YARD is 1.83426465e-10 CUBIC MILE", 1.83426465e-10,
                getMultiplicationFactorTo(VolumeUnit.CUBIC_YARD, VolumeUnit.CUBIC_MILE), 0.0000000001);
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(VolumeUnit.CUBIC_CENTIMETER, 0.000001, 0.000000000001, "cubic centimeter", "cm^3");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.CUBIC_KILOMETER, 1e9, 1, "cubic kilometer", "km^3");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.CUBIC_MILE, 4.16818183e9, 1000, "cubic mile", "mi^3");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.CUBIC_FOOT, 0.0283168, 0.0000001, "cubic foot", "ft^3");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.CUBIC_INCH, 1.6387e-5, 1e-9, "cubic inch", "in^3");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.CUBIC_YARD, 0.764554858, 0.0000001, "cubic yard", "yd^3");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.GALLON_US, 0.0037854, 0.0000001, "gallon (US)", "gal(US)");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.FLUID_OUNCE_US, 0.000029574, 0.000000001, "fluid ounce (US)", "fl.oz(US)");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.FLUID_OUNCE_IMP, .00002841306, 0.00000000001, "fluid ounce (imp)",
                "fl.oz(imp)");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.PINT_US, 0.000473176473, 0.0000000000001, "pint (US)", "pt(US)");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.PINT_IMP, 0.5 * 0.00113652, 0.00001, "pint (imp)", "pt(imp)");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.QUART_US, 0.000946353, 0.0000000001, "quart (US)", "qt(US)");
        checkUnitRatioNameAndAbbreviation(VolumeUnit.QUART_IMP, 0.00113652, 0.000005, "quart (imp)", "qt(imp)");
    }

    /**
     * Verify that we can create our own power unit.
     */
    @Test
    public final void createVolumeUnit()
    {
        VolumeUnit myVU = VolumeUnit.LITER.deriveLinear(119.240471, "brl", "Barrel", UnitSystem.OTHER);
        assertTrue("Can create a new VolumeUnit", null != myVU);
        checkUnitRatioNameAndAbbreviation(myVU, 0.119240471, 0.000001, "Barrel", "brl");
        VolumeUnit.BASE.unregister(myVU);
    }

}
