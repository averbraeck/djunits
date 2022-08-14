package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard units for electrical current.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class ElectricalCurrentUnit extends Unit<ElectricalCurrentUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "A" as the SI signature. */
    public static final Quantity<ElectricalCurrentUnit> BASE = new Quantity<>("ElectricalCurrent", "A");

    /** The SI unit for electrical current is Ampere. */
    public static final ElectricalCurrentUnit SI = new ElectricalCurrentUnit()
            .build(new Unit.Builder<ElectricalCurrentUnit>().setQuantity(BASE).setId("A").setName("ampere")
                    .setUnitSystem(UnitSystem.SI_BASE).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Ampere. */
    public static final ElectricalCurrentUnit AMPERE = SI;

    /** microampere. */
    public static final ElectricalCurrentUnit MICROAMPERE = AMPERE.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** milliampere. */
    public static final ElectricalCurrentUnit MILLIAMPERE = AMPERE.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** kiloampere. */
    public static final ElectricalCurrentUnit KILOAMPERE = AMPERE.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** megaampere. */
    public static final ElectricalCurrentUnit MEGAAMPERE = AMPERE.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** statampere (GCS ESU). */
    public static final ElectricalCurrentUnit STATAMPERE =
            AMPERE.deriveLinear(3.335641E-10, "statA", "statampere", UnitSystem.CGS_ESU);

    /** abampere (GCS EMU). */
    public static final ElectricalCurrentUnit ABAMPERE = AMPERE.deriveLinear(10.0, "abA", "abampere", UnitSystem.CGS_EMU);

}
