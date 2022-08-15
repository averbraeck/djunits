package org.djunits.unit.unitsystem;

/**
 * Other (non-SI, cgs, Imperial, mts, US, ...) system, or to indicate a unit is not belonging to a system.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class Other extends UnitSystem
{
    /** */
    private static final long serialVersionUID = 20140606L;

    /**
     * Protected constructor to avoid creating other (false) unit systems.
     * @param abbreviationKey String; the abbreviation of the unit system, such as SI
     * @param nameKey String; the name of the unit system, such as SI Base
     */
    protected Other(final String abbreviationKey, final String nameKey)
    {
        super(abbreviationKey, nameKey);
    }

}
