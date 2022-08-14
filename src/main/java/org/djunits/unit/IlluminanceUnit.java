package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of illuminance.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class IlluminanceUnit extends Unit<IlluminanceUnit>
{
    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "lux" as the SI signature. */
    public static final Quantity<IlluminanceUnit> BASE = new Quantity<>("Illuminance", "srcd/m2");

    /** The SI unit for amount of illuminance is lux. */
    public static final IlluminanceUnit SI =
            new IlluminanceUnit().build(new Unit.Builder<IlluminanceUnit>().setQuantity(BASE).setId("lx").setName("lux")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Lux. */
    public static final IlluminanceUnit LUX = SI;

    /** mlux. */
    public static final IlluminanceUnit MILLILUX = LUX.deriveLinear(1.0E-3, "mlx", "millilux");

    /** mulux. */
    public static final IlluminanceUnit MICROLUX =
            LUX.deriveLinear(1.0E-6, "mulx", "microlux", UnitSystem.SI_DERIVED, "mulx", "mulx", "\u03BClx");

    /** klux. */
    public static final IlluminanceUnit KILOLUX = LUX.deriveLinear(1.0E3, "klx", "kilolux");

    /** phot. */
    public static final IlluminanceUnit PHOT = KILOLUX.deriveLinear(10.0, "ph", "phot");

    /** nox. */
    public static final IlluminanceUnit NOX = MILLILUX.deriveLinear(1.0, "nx", "nox");

}
