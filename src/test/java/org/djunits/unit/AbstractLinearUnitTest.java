package org.djunits.unit;

import static org.junit.Assert.assertEquals;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 * @param <U> Make the test specific for this sub class of Unit
 */
public abstract class AbstractLinearUnitTest<U extends Unit<U>> extends AbstractUnitTest<U>
{
    /**
     * Verify one length conversion factor to standard unit and the localization of the name and abbreviation.
     * @param u Unit to check
     * @param expectedRatio Double; expected ratio
     * @param precision Double; precision of verification
     * @param expectedName String; expected name in the resources
     * @param expectedAbbreviation String; expected abbreviation in the resources
     */
    protected final void checkUnitRatioNameAndAbbreviation(final U u, final double expectedRatio, final double precision,
            final String expectedName, final String expectedAbbreviation)
    {
        assertEquals(String.format("one %s is about %f reference unit", u.getId(), expectedRatio), expectedRatio,
                u.getScale().toStandardUnit(1.0), precision);
        assertEquals(String.format("Name of %s is %s", u.getId(), expectedName), expectedName, u.getName());
        assertEquals(String.format("Abbreviation of %s is %s", u.getId(), expectedAbbreviation), expectedAbbreviation,
                u.getDefaultDisplayAbbreviation());
    }

    /**
     * @param fromUnit U; the unit to convert from
     * @param toUnit U; the unit to convert to
     * @return multiplication factor to convert a value from fromUnit to toUnit
     */
    public final double getMultiplicationFactorTo(final U fromUnit, final U toUnit)
    {
        return fromUnit.getScale().toStandardUnit(1.0) / toUnit.getScale().toStandardUnit(1.0);
    }

}
