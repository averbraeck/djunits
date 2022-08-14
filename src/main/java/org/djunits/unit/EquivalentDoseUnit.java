package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of equivalent dose (of ionizing radiation).
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class EquivalentDoseUnit extends Unit<EquivalentDoseUnit>
{

    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "m2/s2" as the SI signature. */
    public static final Quantity<EquivalentDoseUnit> BASE = new Quantity<>("EquivalentDose", "m2/s2");

    /** The SI unit for equivalent dose of ionizing radiation is Sievert. */
    public static final EquivalentDoseUnit SI = new EquivalentDoseUnit()
            .build(new Unit.Builder<EquivalentDoseUnit>().setQuantity(BASE).setId("Sv").setName("sievert")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Sievert. */
    public static final EquivalentDoseUnit SIEVERT = SI;

    /** mSv. */
    public static final EquivalentDoseUnit MILLISIEVERT = SIEVERT.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** &#181;Sv. */
    public static final EquivalentDoseUnit MICROSIEVERT = SIEVERT.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** rem. (stands for röntgen equivalent man). */
    public static final EquivalentDoseUnit REM = SIEVERT.deriveLinear(0.01, "rem", "rem", UnitSystem.CGS);

}
