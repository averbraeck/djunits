package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of electrical conductance.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class ElectricalConductanceUnit extends Unit<ElectricalConductanceUnit>
{
    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "s3A2/kgm2" as the SI signature. */
    public static final Quantity<ElectricalConductanceUnit> BASE = new Quantity<>("ElectricalConductance", "s3A2/kgm2");

    /** The SI unit for electrical conductance is Siemens. */
    public static final ElectricalConductanceUnit SI = new ElectricalConductanceUnit()
            .build(new Unit.Builder<ElectricalConductanceUnit>().setQuantity(BASE).setId("S").setName("siemens")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Siemens. */
    public static final ElectricalConductanceUnit SIEMENS = SI;

    /** mS. */
    public static final ElectricalConductanceUnit MILLISIEMENS = SIEMENS.deriveLinear(1.0E-3, "mS", "millisiemens");

    /** muS. */
    public static final ElectricalConductanceUnit MICROSIEMENS =
            SIEMENS.deriveLinear(1.0E-6, "muS", "microsiemens", UnitSystem.SI_DERIVED, "muS", "muS", "\u03BCS");

    /** nS. */
    public static final ElectricalConductanceUnit NANOSIEMENS = SIEMENS.deriveLinear(1.0E-9, "nS", "nanosiemens");
}
