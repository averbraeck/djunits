package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Torque;
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
public class TorqueUnitTest extends AbstractLinearUnitTest<Torque.Unit>
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
        assertEquals("kgm2/s2", Torque.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Torque.Unit.Nm, 1, 0.00000001, "newton meter", "Nm");
        checkUnitRatioNameAndAbbreviation(Torque.Unit.m_kgf, 9.80665, 0.000005, "meter kilogram-force", "m.kgf");
        checkUnitRatioNameAndAbbreviation(Torque.Unit.lbf_ft, 1.35581794833, 0.0000001, "pound-force foot", "lbf.ft");
        // Check two conversions between non-standard units
        assertEquals(12, getMultiplicationFactorTo(Torque.Unit.lbf_ft, Torque.Unit.lbf_in), 0.0001,
                "one FOOT POUND FORCE is 12 INCH_POUND_FORCE");
        // Check conversion factor to standard unit for all remaining acceleration units
        checkUnitRatioNameAndAbbreviation(Torque.Unit.lbf_in, 0.112984829, 0.000000001, "pound-force inch", "lbf.in");
    }

    /**
     * Verify that we can create our own Torque unit.
     */
    @Test
    public final void createTorqueUnit()
    {
        Torque.Unit myUnit = Torque.Unit.SI.deriveUnit("my", "myTorque", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new TorqueUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myTorque", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("kgm2/s2"), Torque.ONE.getDisplayUnit().siUnit());
        assertEquals(Torque.Unit.Nm, Torque.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Torque.ONE, Torque.Unit.Nm.ofSi(1.0));

        Torque.Unit nonlinearUnit = new Torque.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
