package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Pressure;
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
public class PressureUnitTest extends AbstractLinearUnitTest<Pressure.Unit>
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
        assertEquals("kg/ms2", Pressure.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.Pa, 1, 0.00000001, "pascal", "Pa");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.atm, 101325, 0.5, "atmosphere (standard)", "atm");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.at, 98066.5, 0.1, "atmosphere (technical)", "at");
        // Check two conversions between non-standard units
        assertEquals(1 / 0.9678, getMultiplicationFactorTo(Pressure.Unit.atm, Pressure.Unit.at), 0.0001,
                "one ATMOSPHERE STANDARD is about 1.03327 ATMOSPHERE TECHNICAL");
        assertEquals(0.9678, getMultiplicationFactorTo(Pressure.Unit.at, Pressure.Unit.atm), 0.0001,
                "one ATMOSPHERE TECHNICAL is 0.9678 ATMOSPHERE STANDARD");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.hPa, 100, 0.0001, "hectopascal", "hPa");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.kPa, 1000, 0.001, "kilopascal", "kPa");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.bar, 100000, 0.01, "bar", "bar");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.mbar, 100, 0.000001, "millibar", "mbar");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.cmHg, 1333.22368, 0.001, "centimeter mercury", "cmHg");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.mmHg, 133.322368, 0.001, "millimeter mercury", "mmHg");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.ftHg, 40636.66, 0.01, "foot mercury", "ftHg");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.inHg, 3386, 0.5, "inch mercury", "inHg");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.kgf_mm2, 9806650, 0.5, "kilogram-force per square millimeter",
                "kgf/mm2");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.lbf_ft2, 47.880259, 0.000001, "pound-force per square foot", "lbf/ft2");
        checkUnitRatioNameAndAbbreviation(Pressure.Unit.lbf_in2, 6894.75729, 0.00001, "pound-force per square inch", "lbf/in2");
    }

    /**
     * Verify that we can create our own pressure unit.
     */
    @Test
    public final void createPressureUnit()
    {
        Pressure.Unit myPU = Pressure.Unit.mmHg.deriveUnit("hhhp", "HealthyHumanHeart", 106.0, UnitSystem.OTHER);
        assertTrue(null != myPU, "Can create a new PowerUnit");
        checkUnitRatioNameAndAbbreviation(myPU, 14132.1711, 0.01, "HealthyHumanHeart", "hhhp");
        Units.unregister(myPU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("kg/ms2"), Pressure.ONE.getDisplayUnit().siUnit());
        assertEquals(Pressure.Unit.Pa, Pressure.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Pressure.ONE, Pressure.Unit.Pa.ofSi(1.0));

        Pressure.Unit nonlinearUnit = new Pressure.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
