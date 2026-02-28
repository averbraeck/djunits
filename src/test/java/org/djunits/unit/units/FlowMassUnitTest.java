package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.FlowMass;
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
public class FlowMassUnitTest extends AbstractLinearUnitTest<FlowMass.Unit>
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
        assertEquals("kg/s", FlowMass.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(FlowMass.Unit.kg_s, 1, 0.000001, "kilogram per second", "kg/s");
        checkUnitRatioNameAndAbbreviation(FlowMass.Unit.lb_s, 0.453592, 0.0001, "pound per second", "lb/s");
        // Check two conversions between non-standard units
        assertEquals(2.205, getMultiplicationFactorTo(FlowMass.Unit.kg_s, FlowMass.Unit.lb_s), 0.0005,
                "one KILOGRAM PER SECOND is about 2.205 POUND PER SECOND");
        assertEquals(0.453592, getMultiplicationFactorTo(FlowMass.Unit.lb_s, FlowMass.Unit.kg_s), 0.0001,
                "one POUND PER SECOND is about 0.453592 KILOGRAM PER SECOND");
    }

    /**
     * Verify that we can create our own FlowMass unit.
     */
    @Test
    public final void createFlowMassUnit()
    {
        FlowMass.Unit myFMU = FlowMass.Unit.kg_s.deriveUnit("wdpu", "WaterDropsPerHour", 1234.0, UnitSystem.OTHER);
        assertTrue(null != myFMU, "Can create a new FlowMassUnit");
        checkUnitRatioNameAndAbbreviation(myFMU, 1234, 0.0001, "WaterDropsPerHour", "wdpu");
        Units.unregister(myFMU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("kg/s"), FlowMass.ONE.getDisplayUnit().siUnit());
        assertEquals(FlowMass.Unit.kg_s, FlowMass.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(FlowMass.ONE, FlowMass.Unit.kg_s.ofSi(1.0));

        FlowMass.Unit nonlinearUnit = new FlowMass.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
