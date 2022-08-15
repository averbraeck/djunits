package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of electrical resistance.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class ElectricalResistanceUnit extends Unit<ElectricalResistanceUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "kgm2/s3A2" as the SI signature. */
    public static final Quantity<ElectricalResistanceUnit> BASE = new Quantity<>("ElectricalResistance", "kgm2/s3A2");

    /** The SI unit for electrical resistance is Ohm. */
    public static final ElectricalResistanceUnit SI = new ElectricalResistanceUnit()
            .build(new Unit.Builder<ElectricalResistanceUnit>().setQuantity(BASE).setId("ohm").setName("ohm")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE)
                    .setDefaultDisplayAbbreviation("\u03A9").setDefaultTextualAbbreviation("ohm"));

    /** Ohm. */
    public static final ElectricalResistanceUnit OHM = SI;

    /** micro-ohm. */
    public static final ElectricalResistanceUnit MICROOHM = OHM.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** milli-ohm. */
    public static final ElectricalResistanceUnit MILLIOHM = OHM.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** kilo-ohm. */
    public static final ElectricalResistanceUnit KILOOHM = OHM.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** mega-ohm. */
    public static final ElectricalResistanceUnit MEGAOHM = OHM.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** giga-ohm. */
    public static final ElectricalResistanceUnit GIGAOHM = OHM.deriveSI(SIPrefixes.getUnit("G"), 1.0);

    /** ab-ohm. */
    public static final ElectricalResistanceUnit ABOHM =
            OHM.deriveLinear(1.0E-9, "abohm", "abohm", UnitSystem.CGS_EMU, "ab\u03A9", "abohm");

    /** stat-ohm. */
    public static final ElectricalResistanceUnit STATOHM =
            OHM.deriveLinear(8.987551787E11, "stohm", "statohm", UnitSystem.CGS_ESU, "st\u03A9", "stohm");

}
