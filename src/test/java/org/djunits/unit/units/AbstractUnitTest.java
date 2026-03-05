package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.unit.UnitInterface;

/**
 * Test for AbstractUnit. <br>
 * <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 * @param <U> Make the test specific for this sub class of Unit
 */
public abstract class AbstractUnitTest<U extends UnitInterface<U, ?>>
{
    /**
     * Verify the localization of the name and abbreviation.
     * @param u Unit to check
     * @param expectedName expected name in the resources
     * @param expectedAbbreviation expected abbreviation in the resources
     */
    protected final void checkUnitNameAndAbbreviation(final U u, final String expectedName, final String expectedAbbreviation)
    {
        assertEquals(expectedName, u.getName(), String.format("Name of %s is %s", u.getId(), expectedName));
        assertEquals(expectedAbbreviation, u.getDisplayAbbreviation(),
                String.format("Abbreviation of %s is %s", u.getId(), expectedAbbreviation));
    }

}
