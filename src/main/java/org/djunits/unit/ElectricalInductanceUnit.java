package org.djunits.unit;

import org.djunits.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of electrical capacitance.
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class ElectricalInductanceUnit extends Unit<ElectricalInductanceUnit>
{

    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "kgm2/s2/A2" as the SI signature. */
    public static final Quantity<ElectricalInductanceUnit> BASE = new Quantity<>("ElectricalInductance", "kgm2/s2A2");

    /** The SI unit for electrical inductance is Henry. */
    public static final ElectricalInductanceUnit SI = new ElectricalInductanceUnit()
            .build(new Unit.Builder<ElectricalInductanceUnit>().setQuantity(BASE).setId("H").setName("henry")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Henry. */
    public static final ElectricalInductanceUnit HENRY = SI;

    /** mH. */
    public static final ElectricalInductanceUnit MILLIHENRY = HENRY.deriveLinear(1.0E-3, "mH", "millihenry");

    /** muH. */
    public static final ElectricalInductanceUnit MICROHENRY =
            HENRY.deriveLinear(1.0E-6, "muH", "microhenry", UnitSystem.SI_DERIVED, "muH", "muH", "\u03BCH");

    /** nH. */
    public static final ElectricalInductanceUnit NANOHENRY = HENRY.deriveLinear(1.0E-9, "nH", "nanohenry");
}
