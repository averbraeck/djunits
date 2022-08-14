package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of catalytic activity.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class CatalyticActivityUnit extends Unit<CatalyticActivityUnit>
{

    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "mol/s" as the SI signature. */
    public static final Quantity<CatalyticActivityUnit> BASE = new Quantity<>("CatalyticActivity", "mol/s");

    /** The SI unit for catalytic activity is Katal. */
    public static final CatalyticActivityUnit SI = new CatalyticActivityUnit()
            .build(new Unit.Builder<CatalyticActivityUnit>().setQuantity(BASE).setId("kat").setName("katal")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** KATAL. */
    public static final CatalyticActivityUnit KATAL = SI;

    /** mkat. */
    public static final CatalyticActivityUnit MILLIKATAL = KATAL.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** &#181;kat. */
    public static final CatalyticActivityUnit MICROKATAL = KATAL.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** nkat. */
    public static final CatalyticActivityUnit NANOKATAL = KATAL.deriveSI(SIPrefixes.getUnit("n"), 1.0);

}
