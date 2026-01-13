package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Force;
import org.djunits.unit.Units;
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
public class ForceUnitTest extends AbstractLinearUnitTest<Force.Unit>
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
        assertEquals("kgm/s2", Force.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(Force.Unit.N, 1, 0.000001, "newton", "N");
        checkUnitRatioNameAndAbbreviation(Force.Unit.dyn, 0.00001, 0.000000001, "dyne", "dyn");
        // Check two conversions between non-standard units
        assertEquals(1.01971621e-6, getMultiplicationFactorTo(Force.Unit.dyn, Force.Unit.kgf), 0.00000000001,
                "one DYNE is about 1.019716e-6 KILOGRAM FORCE");
        assertEquals(980665, getMultiplicationFactorTo(Force.Unit.kgf, Force.Unit.dyn), 0.5,
                "one KILOGRAM FORCE is about 980665 DYNE");
    }

    /**
     * Verify that we can create our own Force unit.
     */
    @Test
    public final void createForceUnit()
    {
        Force.Unit myFU = Force.Unit.kgf.deriveUnit("af", "AntForce", 0.002, UnitSystem.OTHER);
        assertTrue(null != myFU, "Can create a new ForceUnit");
        checkUnitRatioNameAndAbbreviation(myFU, 0.002 * 9.8, 0.0001, "AntForce", "af");
        Units.unregister(myFU);
    }

}
