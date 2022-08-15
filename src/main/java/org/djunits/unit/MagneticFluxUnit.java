package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of magnetic flux.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class MagneticFluxUnit extends Unit<MagneticFluxUnit>
{
    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "kgm2/s2A" as the SI signature. */
    public static final Quantity<MagneticFluxUnit> BASE = new Quantity<>("MagneticFlux", "kgm2/s2A");

    /** The SI unit for magnetic flux is Weber. */
    public static final MagneticFluxUnit SI =
            new MagneticFluxUnit().build(new Unit.Builder<MagneticFluxUnit>().setQuantity(BASE).setId("Wb").setName("weber")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Weber. */
    public static final MagneticFluxUnit WEBER = SI;

    /** mWb. */
    public static final MagneticFluxUnit MILLIWEBER = WEBER.deriveLinear(1.0E-3, "mWb", "milliweber");

    /** muWb. */
    public static final MagneticFluxUnit MICROWEBER =
            WEBER.deriveLinear(1.0E-6, "muWb", "microweber", UnitSystem.SI_DERIVED, "muWb", "muWb", "\u03BCWb");

    /** nWb. */
    public static final MagneticFluxUnit NANOWEBER = WEBER.deriveLinear(1.0E-9, "nWb", "nanoweber");

    /** Maxwell. */
    public static final MagneticFluxUnit MAXWELL = WEBER.deriveLinear(1.0E-8, "Mx", "Maxwell", UnitSystem.CGS);

}
