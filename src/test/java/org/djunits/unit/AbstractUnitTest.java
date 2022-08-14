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
public abstract class AbstractUnitTest<U extends Unit<U>>
{
    /**
     * Verify the localization of the name and abbreviation.
     * @param u Unit to check
     * @param expectedName String; expected name in the resources
     * @param expectedAbbreviation String; expected abbreviation in the resources
     */
    protected final void checkUnitNameAndAbbreviation(final U u, final String expectedName, final String expectedAbbreviation)
    {
        assertEquals(String.format("Name of %s is %s", u.getId(), expectedName), expectedName, u.getName());
        assertEquals(String.format("Abbreviation of %s is %s", u.getId(), expectedAbbreviation), expectedAbbreviation,
                u.getDefaultDisplayAbbreviation());
    }

}
