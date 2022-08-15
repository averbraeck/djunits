package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * According to <a href="https://en.wikipedia.org/wiki/Momentum">Wikipedia</a>: Momentum or linear momentum, or translational
 * momentum is the product of the mass and velocity of an object.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class MomentumUnit extends Unit<MomentumUnit>
{
    /** */
    private static final long serialVersionUID = 20200117L;

    /** The base, with "kgm/s" as the SI signature. */
    public static final Quantity<MomentumUnit> BASE = new Quantity<>("Momentum", "kgm/s");

    /** The SI unit for momentum is kgm/s. */
    public static final MomentumUnit SI = new MomentumUnit().build(new Unit.Builder<MomentumUnit>().setQuantity(BASE)
            .setId("kgm/s").setName("kilogram meter per second").setUnitSystem(UnitSystem.SI_DERIVED)
            .setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE).setAdditionalAbbreviations("kgm/sec"));

    /** kgm/s. */
    public static final MomentumUnit KILOGRAM_METER_PER_SECOND = SI;

}
