package org.djunits.unit;

import static org.junit.Assert.assertEquals;

import org.djunits.unit.scale.OffsetLinearScale;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 * @param <OU> Make the test specific for this sub class of OffsetUnit
 */
public class AbstractOffsetUnitTest<OU extends Unit<OU>> extends AbstractUnitTest<OU>
{
    /**
     * Verify one length conversion factor to standard unit and the localization of the name and abbreviation.
     * @param ou Unit to check
     * @param expectedRatio Double; expected ratio
     * @param expectedOffset Double; expected offset
     * @param precision Double; precision of verification
     * @param expectedName String; expected name in the resources
     * @param expectedAbbreviation String; expected abbreviation in the resources
     */
    protected final void checkUnitRatioOffsetNameAndAbbreviation(final OU ou, final double expectedRatio,
            final double expectedOffset, final double precision, final String expectedName, final String expectedAbbreviation)
    {
        OffsetLinearScale scale = (OffsetLinearScale) ou.getScale();
        assertEquals(String.format("zero %s is about %f reference unit", ou.getId(), expectedOffset), expectedOffset,
                scale.getOffsetToStandardUnit(), precision);
        assertEquals(String.format("one %s is about %f reference unit", ou.getId(), expectedRatio), expectedRatio,
                scale.getConversionFactorToStandardUnit(), precision);
        assertEquals(String.format("Name of %s is %s", ou.getId(), expectedName), expectedName, ou.getName());
        assertEquals(String.format("Abbreviation of %s is %s", ou.getId(), expectedAbbreviation), expectedAbbreviation,
                ou.getDefaultDisplayAbbreviation());
    }

    /**
     * @param fromUnit U; the unit to convert from
     * @param toUnit U; the unit to convert to
     * @return multiplication factor to convert a value from fromUnit to toUnit
     */
    public final double getMultiplicationFactorTo(final OU fromUnit, final OU toUnit)
    {
        OffsetLinearScale fromScale = (OffsetLinearScale) fromUnit.getScale();
        OffsetLinearScale toScale = (OffsetLinearScale) toUnit.getScale();
        return fromScale.getConversionFactorToStandardUnit() / toScale.getConversionFactorToStandardUnit();
    }

}
