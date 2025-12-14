package org.djunits.old.unit.unitsystem;

/**
 * Other (non-SI, cgs, Imperial, mts, US, ...) system, or to indicate a unit is not belonging to a system.
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 */
public class Other extends UnitSystem
{
    /** */
    private static final long serialVersionUID = 20140606L;

    /**
     * Protected constructor to avoid creating other (false) unit systems.
     * @param abbreviationKey the abbreviation of the unit system, such as SI
     * @param nameKey the name of the unit system, such as SI Base
     */
    protected Other(final String abbreviationKey, final String nameKey)
    {
        super(abbreviationKey, nameKey);
    }

}
