package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.SolidAngle;
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
public class SolidAngleUnitTest extends AbstractLinearUnitTest<SolidAngle.Unit>
{
    /**
     * Set the locale to "en" so we know what texts should be retrieved from the resources.
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
        assertEquals("sr", SolidAngle.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(SolidAngle.Unit.sr, 1, 0.0000001, "steradian", "sr");
        checkUnitRatioNameAndAbbreviation(SolidAngle.Unit.sq_deg, 1.0 / 3283, 0.0005, "square degree", "sq.deg");
        // Check two conversions between units
        assertEquals(3283, getMultiplicationFactorTo(SolidAngle.Unit.sr, SolidAngle.Unit.sq_deg), 0.5,
                "one STERADIAN is about 3283 SQUARE_DEGREE");
        assertEquals(0.0003045, getMultiplicationFactorTo(SolidAngle.Unit.sq_deg, SolidAngle.Unit.sr), 0.0000005,
                "one SQUARE_DEGREE is about 0.0003045 STERADIAN");
    }

    /**
     * Verify that we can create our own angle unit.
     */
    @Test
    public final void createSolidAngleUnit()
    {
        SolidAngle.Unit myAPU = SolidAngle.Unit.sr.deriveUnit("pt", "point", 0.19634954085, UnitSystem.OTHER);
        assertTrue(null != myAPU, "Can create a new TimeUnit");
        checkUnitRatioNameAndAbbreviation(myAPU, 0.19634954085, 0.0000001, "point", "pt");
        Units.unregister(myAPU);
    }
}
