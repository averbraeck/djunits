package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.unit.UnitInterface;

/**
 * Test for AbstractLinearUnit. <br>
 * <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <U> Make the test specific for this sub class of Unit
 */
public abstract class AbstractLinearUnitTest<U extends UnitInterface<U, ?>> extends AbstractUnitTest<U>
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
        assertEquals(expectedRatio, u.getScale().toBaseValue(1.0), precision,
                String.format("one %s is about %f reference unit", u.getId(), expectedRatio));
        assertEquals(expectedName, u.getName(), String.format("Name of %s is %s", u.getId(), expectedName));
        assertEquals(expectedAbbreviation, u.getDisplayAbbreviation(),
                String.format("Abbreviation of %s is %s", u.getId(), expectedAbbreviation));
    }

    /**
     * @param fromUnit the unit to convert from
     * @param toUnit the unit to convert to
     * @return multiplication factor to convert a value from fromUnit to toUnit
     */
    public final double getMultiplicationFactorTo(final U fromUnit, final U toUnit)
    {
        return fromUnit.getScale().toBaseValue(1.0) / toUnit.getScale().toBaseValue(1.0);
    }

}
