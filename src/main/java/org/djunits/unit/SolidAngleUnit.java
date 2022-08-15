package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard solid angle unit.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class SolidAngleUnit extends Unit<SolidAngleUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "sr" as the SI signature. */
    public static final Quantity<SolidAngleUnit> BASE = new Quantity<>("SolidAngle", "sr");

    /** The SI unit for solid angle is steradian. */
    public static final SolidAngleUnit SI =
            new SolidAngleUnit().build(new Unit.Builder<SolidAngleUnit>().setQuantity(BASE).setId("sr").setName("steradian")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE));

    /** steradian. */
    public static final SolidAngleUnit STERADIAN = SI;

    /** square degree. */
    public static final SolidAngleUnit SQUARE_DEGREE =
            STERADIAN.deriveLinear((Math.PI / 180.0) * (Math.PI / 180.0), "sq.deg", "square degree");

}
