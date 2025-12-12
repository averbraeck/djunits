package org.djunits.unit;

import org.djunits.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Dimensionless unit.
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 */
public final class DimensionlessUnit extends Unit<DimensionlessUnit>
{
    /** */
    private static final long serialVersionUID = 20150830L;

    /** The base, with the empty SI signature. */
    public static final Quantity<DimensionlessUnit> BASE = new Quantity<>("Dimensionless", "");

    /** The SI unit for a dimension less unit is "". */
    public static final DimensionlessUnit SI =
            new DimensionlessUnit().build(new Unit.Builder<DimensionlessUnit>().setQuantity(BASE).setId("").setName("")
                    .setUnitSystem(UnitSystem.OTHER).setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE));

}
