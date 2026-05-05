package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Angle;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Angle.Unit test code. <p>
 * Copyright (c) 2013-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Peter Knoppers
 */
public class AngleUnitTest extends AbstractLinearUnitTest<Angle.Unit>
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
     * Verify one length conversion factor to standard unit and the localization of the name and abbreviation.
     * @param au Unit to check
     * @param expectedValue expected value of one 'unit to check' in SI units
     * @param precision precision of verification
     * @param expectedName expected name in the resources
     * @param expectedAbbreviation expected abbreviation in the resources
     */
    protected final void checkUnitValueNameAndAbbreviation(final Angle.Unit au, final double expectedValue,
            final double precision, final String expectedName, final String expectedAbbreviation)
    {
        assertEquals("rad", Angle.Unit.SI_UNIT.format(true, false));
        assertEquals(expectedValue, au.getScale().toBaseValue(1.0), precision,
                String.format("one %s is about %f reference unit", au.getId(), expectedValue));
        assertEquals(expectedName, au.getName(), String.format("Name of %s is %s", au.getId(), expectedName));
        assertEquals(expectedAbbreviation, au.getDisplayAbbreviation(),
                String.format("Abbreviation of %s is %s", au.getId(), expectedAbbreviation));
    }

    /**
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        checkUnitValueNameAndAbbreviation(Angle.Unit.deg, 2 * Math.PI / 360, 0.000001, "degree", "\u00b0");
        checkUnitValueNameAndAbbreviation(Angle.Unit.arcmin, 2 * Math.PI / 360 / 60, 0.0001, "arcminute", "\'");
        checkUnitValueNameAndAbbreviation(Angle.Unit.grad, 2 * Math.PI / 400, 0.00001, "gradian", "grad");
        // TODO Check two conversions between non-standard Angle units
        assertEquals(54, getMultiplicationFactorTo(Angle.Unit.grad, Angle.Unit.arcmin), 0.5, "one GRAD is about 54 ARCMINUTE");
        assertEquals(0.0185, getMultiplicationFactorTo(Angle.Unit.arcmin, Angle.Unit.grad), 0.0001,
                "one ARCMINUTE is about 0.0185 GRAD");
        // Check conversion factor to standard unit for all remaining time units
        checkUnitValueNameAndAbbreviation(Angle.Unit.cdm, 0.00015708, 0.0000001, "centesimal arcminute", "c\'");
        checkUnitValueNameAndAbbreviation(Angle.Unit.cds, 1.57079e-6, 0.1, "centesimal arcsecond", "c\"");
        checkUnitValueNameAndAbbreviation(Angle.Unit.percent, 0.0099996667, 0.0001, "percent", "%");
    }

    /**
     * Verify that we can create our own angle unit.
     */
    @Test
    public final void createAngleUnit()
    {
        Angle.Unit myAPU = Angle.Unit.rad.deriveUnit("pt", "point", 0.19634954085, UnitSystem.OTHER);
        assertTrue(null != myAPU, "Can create a new AngleUnit");
        checkUnitValueNameAndAbbreviation(myAPU, 0.19634954085, 0.0000001, "point", "pt");
        Units.unregister(myAPU);
        
        myAPU = new Angle.Unit("pt", "point", 0.19634954085, UnitSystem.OTHER);
        assertTrue(null != myAPU, "Can create a new AngleUnit");
        checkUnitValueNameAndAbbreviation(myAPU, 0.19634954085, 0.0000001, "point", "pt");
        Units.unregister(myAPU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("rad"), Angle.ONE.getDisplayUnit().siUnit());
        assertEquals(Angle.Unit.rad, Angle.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Angle.ONE, Angle.Unit.rad.ofSi(1.0));

        Angle.Unit nonlinearUnit = new Angle.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
