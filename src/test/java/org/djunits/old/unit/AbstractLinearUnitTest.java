package org.djunits.old.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.old.unit.Unit;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 * @param <U> Make the test specific for this sub class of Unit
 */
public abstract class AbstractLinearUnitTest<U extends Unit<U>> extends AbstractUnitTest<U>
{
    /**
     * Verify one length conversion factor to standard unit and the localization of the name and abbreviation.
     * @param u Unit to check
     * @param expectedRatio expected ratio
     * @param precision precision of verification
     * @param expectedName expected name in the resources
     * @param expectedAbbreviation expected abbreviation in the resources
     */
    protected final void checkUnitRatioNameAndAbbreviation(final U u, final double expectedRatio, final double precision,
            final String expectedName, final String expectedAbbreviation)
    {
        assertEquals(expectedRatio, u.getScale().toStandardUnit(1.0), precision,
                String.format("one %s is about %f reference unit", u.getId(), expectedRatio));
        assertEquals(expectedName, u.getName(), String.format("Name of %s is %s", u.getId(), expectedName));
        assertEquals(expectedAbbreviation, u.getDefaultDisplayAbbreviation(),
                String.format("Abbreviation of %s is %s", u.getId(), expectedAbbreviation));
    }

    /**
     * @param fromUnit the unit to convert from
     * @param toUnit the unit to convert to
     * @return multiplication factor to convert a value from fromUnit to toUnit
     */
    public final double getMultiplicationFactorTo(final U fromUnit, final U toUnit)
    {
        return fromUnit.getScale().toStandardUnit(1.0) / toUnit.getScale().toStandardUnit(1.0);
    }

}
