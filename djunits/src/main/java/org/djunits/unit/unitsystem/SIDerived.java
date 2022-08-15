package org.djunits.unit.unitsystem;

/**
 * The international System of Units (SI). Derived units such as the J(oule), W(att).
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class SIDerived extends SI
{
    /** */
    private static final long serialVersionUID = 20140606L;

    /**
     * protected constructor to avoid creating other (false) SI unit systems.
     * @param abbreviationKey String; the abbreviation of the unit system, such as SI
     * @param nameKey String; the name of the unit system, such as SI Base
     */
    protected SIDerived(final String abbreviationKey, final String nameKey)
    {
        super(abbreviationKey, nameKey);
    }

}
