package org.djunits.old.unit;

import org.djunits.old.quantity.Quantity;
import org.djunits.old.unit.scale.IdentityScale;
import org.djunits.old.unit.si.SIPrefixes;
import org.djunits.old.unit.unitsystem.UnitSystem;

/**
 * The units of electrical capacitance.
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * version May 15, 2014 <br>
 * @author Peter Knoppers
 */
public class ElectricalCapacitanceUnit extends Unit<ElectricalCapacitanceUnit>
{

    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "s3A2/kgm2" as the SI signature. */
    public static final Quantity<ElectricalCapacitanceUnit> BASE = new Quantity<>("ElectricalCapacitance", "s4A2/kgm2");

    /** The SI unit for electrical capacitance is Farad. */
    public static final ElectricalCapacitanceUnit SI = new ElectricalCapacitanceUnit()
            .build(new Unit.Builder<ElectricalCapacitanceUnit>().setQuantity(BASE).setId("F").setName("farad")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Farad. */
    public static final ElectricalCapacitanceUnit FARAD = SI;

    /** mF. */
    public static final ElectricalCapacitanceUnit MILLIFARAD = FARAD.deriveLinear(1.0E-3, "mF", "millifarad");

    /** uF. */
    public static final ElectricalCapacitanceUnit MICROFARAD =
            FARAD.deriveLinear(1.0E-6, "muF", "microfarad", UnitSystem.SI_DERIVED, "uF", "uF", "\u03BCF", "muF");

    /** nF. */
    public static final ElectricalCapacitanceUnit NANOFARAD = FARAD.deriveLinear(1.0E-9, "nF", "nanofarad");

    /** pF. */
    public static final ElectricalCapacitanceUnit PICOFARAD = FARAD.deriveLinear(1.0E-12, "pF", "picofarad");

}
