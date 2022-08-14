package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard density unit based on mass per volume.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class DensityUnit extends Unit<DensityUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "kg/m3" as the SI signature. */
    public static final Quantity<DensityUnit> BASE = new Quantity<>("Density", "kg/m3");

    /** The SI unit for standard density is kg/m^3. */
    public static final DensityUnit SI = new DensityUnit()
            .build(new Unit.Builder<DensityUnit>().setQuantity(BASE).setId("kg/m^3").setName("kilogram per cubic meter")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE));

    /** kg/m^3. */
    public static final DensityUnit KG_PER_METER_3 = SI;

    /** g/cm^3. */
    public static final DensityUnit GRAM_PER_CENTIMETER_3 =
            KG_PER_METER_3.deriveLinear(1.0E3, "g/cm^3", "gram per cubic centimeter");

}
