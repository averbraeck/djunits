package org.djunits.old.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.old.unit.Unit;
import org.djunits.old.unit.scale.OffsetLinearScale;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 * @param <OU> Make the test specific for this sub class of OffsetUnit
 */
public class AbstractOffsetUnitTest<OU extends Unit<OU>> extends AbstractUnitTest<OU>
{
    /**
     * Verify one length conversion factor to standard unit and the localization of the name and abbreviation.
     * @param ou Unit to check
     * @param expectedRatio expected ratio
     * @param expectedOffset expected offset
     * @param precision precision of verification
     * @param expectedName expected name in the resources
     * @param expectedAbbreviation expected abbreviation in the resources
     */
    protected final void checkUnitRatioOffsetNameAndAbbreviation(final OU ou, final double expectedRatio,
            final double expectedOffset, final double precision, final String expectedName, final String expectedAbbreviation)
    {
        OffsetLinearScale scale = (OffsetLinearScale) ou.getScale();
        assertEquals(expectedOffset, scale.getOffsetToStandardUnit(), precision,
                String.format("zero %s is about %f reference unit", ou.getId(), expectedOffset));
        assertEquals(expectedRatio, scale.getConversionFactorToStandardUnit(), precision,
                String.format("one %s is about %f reference unit", ou.getId(), expectedRatio));
        assertEquals(expectedName, ou.getName(), String.format("Name of %s is %s", ou.getId(), expectedName));
        assertEquals(expectedAbbreviation, ou.getDefaultDisplayAbbreviation(),
                String.format("Abbreviation of %s is %s", ou.getId(), expectedAbbreviation));
    }

    /**
     * @param fromUnit the unit to convert from
     * @param toUnit the unit to convert to
     * @return multiplication factor to convert a value from fromUnit to toUnit
     */
    public final double getMultiplicationFactorTo(final OU fromUnit, final OU toUnit)
    {
        OffsetLinearScale fromScale = (OffsetLinearScale) fromUnit.getScale();
        OffsetLinearScale toScale = (OffsetLinearScale) toUnit.getScale();
        return fromScale.getConversionFactorToStandardUnit() / toScale.getConversionFactorToStandardUnit();
    }

}
