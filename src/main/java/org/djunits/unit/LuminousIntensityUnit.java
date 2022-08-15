package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of luminous intensity.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class LuminousIntensityUnit extends Unit<LuminousIntensityUnit>
{
    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "cd" as the SI signature. */
    public static final Quantity<LuminousIntensityUnit> BASE = new Quantity<>("LuminousIntensity", "cd");

    /** The SI unit for luminous intensity is cd. */
    public static final LuminousIntensityUnit SI = new LuminousIntensityUnit()
            .build(new Unit.Builder<LuminousIntensityUnit>().setQuantity(BASE).setId("cd").setName("candela")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Candela. */
    public static final LuminousIntensityUnit CANDELA = SI;

}
