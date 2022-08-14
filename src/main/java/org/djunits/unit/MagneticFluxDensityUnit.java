package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of magnetic flux density, a.k.a. magnetic induction.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class MagneticFluxDensityUnit extends Unit<MagneticFluxDensityUnit>
{
    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "kg/m2s2A" as the SI signature. */
    public static final Quantity<MagneticFluxDensityUnit> BASE = new Quantity<>("MagneticFluxDensity", "kg/s2A");

    /** The SI unit for magnetic flux density is Tesla. */
    public static final MagneticFluxDensityUnit SI = new MagneticFluxDensityUnit()
            .build(new Unit.Builder<MagneticFluxDensityUnit>().setQuantity(BASE).setId("T").setName("tesla")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Tesla. */
    public static final MagneticFluxDensityUnit TESLA = SI;

    /** mT. */
    public static final MagneticFluxDensityUnit MILLITESLA = TESLA.deriveLinear(1.0E-3, "mT", "millitesla");

    /** muT. */
    public static final MagneticFluxDensityUnit MICROTESLA =
            TESLA.deriveLinear(1.0E-6, "muT", "microtesla", UnitSystem.SI_DERIVED, "muT", "muT", "\u03BCT");

    /** nT. */
    public static final MagneticFluxDensityUnit NANOTESLA = TESLA.deriveLinear(1.0E-9, "nT", "nanotesla");

    /** Gauss. */
    public static final MagneticFluxDensityUnit GAUSS = TESLA.deriveLinear(1.0E-4, "G", "Gauss", UnitSystem.CGS);
}
