package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of torque (moment of force).
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class TorqueUnit extends Unit<TorqueUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "kgm2/s2" as the SI signature. */
    public static final Quantity<TorqueUnit> BASE = new Quantity<>("Torque", "kgm2/s2");

    /** The SI unit for torque is Newton meter = kgm2/s2. */
    public static final TorqueUnit SI =
            new TorqueUnit().build(new Unit.Builder<TorqueUnit>().setQuantity(BASE).setId("N.m").setName("Newton meter")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE));

    /** Newton meter. */
    public static final TorqueUnit NEWTON_METER = SI;

    /** meter kilogram-force. */
    public static final TorqueUnit METER_KILOGRAM_FORCE = SI.deriveLinear(factorLF(LengthUnit.METER, ForceUnit.KILOGRAM_FORCE),
            "m.kgf", "meter kilogram-force", UnitSystem.OTHER);

    /** Pound foot. */
    public static final TorqueUnit POUND_FOOT =
            SI.deriveLinear(factorLF(LengthUnit.FOOT, ForceUnit.POUND_FORCE), "lbf.ft", "pound-foot", UnitSystem.IMPERIAL);

    /** Pound inch. */
    public static final TorqueUnit POUND_INCH =
            SI.deriveLinear(factorLF(LengthUnit.INCH, ForceUnit.POUND_FORCE), "lbf.in", "pound-inch", UnitSystem.IMPERIAL);

    /**
     * Determine the conversion factor to the base torque unit, given a length unit and a force unit.
     * @param length LengthUnit; the used length unit, e.g. m
     * @param force ForceUnit; the used force unit, e.g. kgf
     * @return double; the conversion factor from the provided units (e.g. m.kgf) to the standard unit (e.g., Nm or kg.m2/s2)
     */
    private static double factorLF(final LengthUnit length, final ForceUnit force)
    {
        return length.getScale().toStandardUnit(1.0) * force.getScale().toStandardUnit(1.0);
    }

}
