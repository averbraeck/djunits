package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.MagneticFluxDensity;
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
public class MagneticFluxDensityUnitTest extends AbstractLinearUnitTest<MagneticFluxDensity.Unit>
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
        assertEquals("kg/s2A", MagneticFluxDensity.Unit.SI_UNIT.toString(true, false));
        checkUnitRatioNameAndAbbreviation(MagneticFluxDensity.Unit.T, 1, 0.000001, "tesla", "T");
    }

    /**
     * Verify that we can create our own MagneticFluxDensity unit.
     */
    @Test
    public final void createMagneticFluxDensityUnit()
    {
        MagneticFluxDensity.Unit myUnit =
                MagneticFluxDensity.Unit.SI.deriveUnit("my", "myMagneticFluxDensity", 1.23, UnitSystem.OTHER);
        assertTrue(null != myUnit, "Can create a new MagneticFluxDensityUnit");
        checkUnitRatioNameAndAbbreviation(myUnit, 1.23, 0.0001, "myMagneticFluxDensity", "my");
        Units.unregister(myUnit);
    }

}
