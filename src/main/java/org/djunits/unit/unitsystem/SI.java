package org.djunits.unit.unitsystem;

/**
 * The international System of Units (SI).
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public abstract class SI extends UnitSystem
{
    /** */
    private static final long serialVersionUID = 20140606L;

    /**
     * Protected constructor to avoid creating other (false) SI unit systems.
     * @param abbreviationKey String; the abbreviation of the unit system, such as SI
     * @param nameKey String; the name of the unit system, such as SI Base
     */
    protected SI(final String abbreviationKey, final String nameKey)
    {
        super(abbreviationKey, nameKey);
    }

}
