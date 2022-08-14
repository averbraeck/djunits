package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of absorbed dose (of ionizing radiation).
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class AbsorbedDoseUnit extends Unit<AbsorbedDoseUnit>
{
    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "m2/s2" as the SI signature. */
    public static final Quantity<AbsorbedDoseUnit> BASE = new Quantity<>("AbsorbedDose", "m2/s2");

    /** The SI unit for absorbed dose of ionizing radiation is Gray. */
    public static final AbsorbedDoseUnit SI =
            new AbsorbedDoseUnit().build(new Unit.Builder<AbsorbedDoseUnit>().setQuantity(BASE).setId("Gy").setName("gray")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Gray. */
    public static final AbsorbedDoseUnit GRAY = SI;

    /** mGy. */
    public static final AbsorbedDoseUnit MILLIGRAY = GRAY.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** &#181;Gy. */
    public static final AbsorbedDoseUnit MICROGRAY = GRAY.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** erg/g. */
    public static final AbsorbedDoseUnit ERG_PER_GRAM = GRAY.deriveLinear(1.0E-4, "erg/g", "erg per gram", UnitSystem.CGS);

    /** rad. */
    public static final AbsorbedDoseUnit RAD = GRAY.deriveLinear(0.01, "rad", "rad", UnitSystem.CGS);
}
