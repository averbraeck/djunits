package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of luminous flux.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class LuminousFluxUnit extends Unit<LuminousFluxUnit>
{
    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "lumen" as the SI signature. */
    public static final Quantity<LuminousFluxUnit> BASE = new Quantity<>("LuminousFlux", "srcd");

    /** The SI unit for amount of luminous flux is Lumen. */
    public static final LuminousFluxUnit SI =
            new LuminousFluxUnit().build(new Unit.Builder<LuminousFluxUnit>().setQuantity(BASE).setId("lm").setName("lumen")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Mole. */
    public static final LuminousFluxUnit LUMEN = SI;

}
