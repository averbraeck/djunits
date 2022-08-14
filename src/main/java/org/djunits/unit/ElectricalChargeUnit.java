package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Units for electrical charge.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class ElectricalChargeUnit extends Unit<ElectricalChargeUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "sA" as the SI signature. */
    public static final Quantity<ElectricalChargeUnit> BASE = new Quantity<>("ElectricalCharge", "sA");

    /** The SI unit for electrical charge is Coulomb = A.s. */
    public static final ElectricalChargeUnit SI = new ElectricalChargeUnit()
            .build(new Unit.Builder<ElectricalChargeUnit>().setQuantity(BASE).setId("C").setName("coulomb")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Coulomb = A.s. */
    public static final ElectricalChargeUnit COULOMB = SI;

    /** microCoulomb = muA.s. */
    public static final ElectricalChargeUnit MICROCOULOMB = COULOMB.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** milliCoulomb = mA.s. */
    public static final ElectricalChargeUnit MILLICOULOMB = COULOMB.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** ampere hour. */
    public static final ElectricalChargeUnit AMPERE_HOUR =
            new ElectricalChargeUnit().build(new Unit.Builder<ElectricalChargeUnit>().setQuantity(BASE).setId("Ah")
                    .setName("ampere hour").setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0)
                    .setScale(new LinearScale(3600.0)));

    /** milliampere hour. */
    public static final ElectricalChargeUnit MILLIAMPERE_HOUR = AMPERE_HOUR.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** milliampere second. */
    public static final ElectricalChargeUnit MILLIAMPERE_SECOND =
            AMPERE_HOUR.deriveLinear(1.0 / 3600.0, "mAs", "milliampere second");

    /** kiloampere hour. */
    public static final ElectricalChargeUnit KILOAMPERE_HOUR = AMPERE_HOUR.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** megaampere hour. */
    public static final ElectricalChargeUnit MEGAAMPERE_HOUR = AMPERE_HOUR.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** Faraday. */
    public static final ElectricalChargeUnit FARADAY = COULOMB.deriveLinear(96485.3383, "F", "faraday", UnitSystem.OTHER);

    /** atomic unit of charge. This value is exact since the 2019 redefinition of the SI base units. */
    public static final ElectricalChargeUnit ATOMIC_UNIT =
            COULOMB.deriveLinear(1.602176634E-19, "e", "elementary unit of charge", UnitSystem.SI_ACCEPTED);

    /** statcoulomb (CGS ESU). */
    public static final ElectricalChargeUnit STATCOULOMB =
            COULOMB.deriveLinear(3.335641E-10, "statC", "statcoulomb", UnitSystem.CGS_ESU);

    /** franklin (CGS ESU). */
    public static final ElectricalChargeUnit FRANKLIN = STATCOULOMB.deriveLinear(1.0, "Fr", "franklin");

    /** esu (CGS ESU). */
    public static final ElectricalChargeUnit ESU = STATCOULOMB.deriveLinear(1.0, "esu", "electrostatic unit");

    /** abcoulomb (CGS EMU). */
    public static final ElectricalChargeUnit ABCOULOMB = COULOMB.deriveLinear(10.0, "abC", "abcoulomb", UnitSystem.CGS_EMU);

    /** emu (CGS EMU). */
    public static final ElectricalChargeUnit EMU = ABCOULOMB.deriveLinear(1.0, "emu", "electromagnetic unit");

}
