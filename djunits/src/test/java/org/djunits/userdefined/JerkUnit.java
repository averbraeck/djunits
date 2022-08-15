package org.djunits.userdefined;

import org.djunits.unit.Unit;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * JerkUnit.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class JerkUnit extends Unit<JerkUnit>
{
    /** */
    private static final long serialVersionUID = 1L;

    /** The base, with "m/s3" as the SI signature. */
    public static final Quantity<JerkUnit> BASE = new Quantity<>("Jerk", "m/s3");

    /** The SI unit for area is m/s3. */
    public static final JerkUnit SI =
            new JerkUnit().build(new Unit.Builder<JerkUnit>().setQuantity(BASE).setId("m/s3").setName("meter per second cubed")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** m/s3. */
    public static final JerkUnit JERK = SI;
}
