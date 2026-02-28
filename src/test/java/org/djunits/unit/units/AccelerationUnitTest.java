package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Acceleration;
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
public class AccelerationUnitTest extends AbstractLinearUnitTest<Acceleration.Unit>
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
        assertEquals("m/s2", Acceleration.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.m_s2, 1, 0.00000001, "meter per second squared", "m/s2");
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.km_h2, 1 / 3.6 / 3600, 0.0005, "kilometer per hour squared",
                "km/h2");
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.ft_s2, 0.3048, 0.00001, "foot per second squared", "ft/s2");
        // Check two conversions between non-standard units
        assertEquals(3950.208, getMultiplicationFactorTo(Acceleration.Unit.ft_s2, Acceleration.Unit.km_h2), 0.01,
                "one FOOT PER SECOND PER SECOND is ??? KM PER HOUR PER HOUR");
        // Check conversion factor to standard unit for all remaining acceleration units
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.in_s2, 0.0254, 0.0000000001, "inch per second squared", "in/s2");
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.mi_h2, 0.000124177778, 0.0000000001, "mile per hour squared",
                "mi/h2");
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.mi_s2, 1609.344, 0.000001, "mile per second squared", "mi/s2");
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.kt_s, 0.514444444, 0.000001, "knot per second", "kt/s");
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.mi_h_s, 0.44704, 0.00000001, "mile per hour per second", "mi/h/s");
    }

    /**
     * Check that g is about 9.8 m/s/s.
     */
    @Test
    public final void gravityConstant()
    {
        checkUnitRatioNameAndAbbreviation(Acceleration.Unit.g, 9.8, 0.02, "standard gravity", "g");
    }

    /**
     * Verify that we can create our own Acceleration unit.
     */
    @Test
    public final void createAccelerationUnit()
    {
        Acceleration.Unit myUnit = Acceleration.Unit.SI.deriveUnit("my", "myAcceleration", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new AccelerationUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myAcceleration", "my");
        Units.unregister(myUnit);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("m/s2"), Acceleration.ONE.getDisplayUnit().siUnit());
        assertEquals(Acceleration.Unit.m_s2, Acceleration.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(Acceleration.ONE, Acceleration.Unit.m_s2.ofSi(1.0));

        Acceleration.Unit nonlinearUnit = new Acceleration.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
