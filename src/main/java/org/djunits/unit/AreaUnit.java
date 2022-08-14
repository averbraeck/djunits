package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * AreaUnit defines a number of common units for areas.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class AreaUnit extends Unit<AreaUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "m2" as the SI signature. */
    public static final Quantity<AreaUnit> BASE = new Quantity<>("Area", "m2");

    /** The SI unit for area is m^2. */
    public static final AreaUnit SI =
            new AreaUnit().build(new Unit.Builder<AreaUnit>().setQuantity(BASE).setId("m^2").setName("square meter")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 2.0).setScale(IdentityScale.SCALE));

    /** m^2. */
    public static final AreaUnit SQUARE_METER = SI;

    /** mm^2. */
    public static final AreaUnit SQUARE_MILLIMETER = SQUARE_METER.deriveLinear(1.0E-6, "mm^2", "square millimeter");

    /** cm^2. */
    public static final AreaUnit SQUARE_CENTIMETER = SQUARE_METER.deriveLinear(1.0E-4, "cm^2", "square centimeter");

    /** dm^2. */
    public static final AreaUnit SQUARE_DECIMETER = SQUARE_METER.deriveLinear(1.0E-2, "dm^2", "square decimeter");

    /** dam^2. */
    public static final AreaUnit SQUARE_DECAMETER = SQUARE_METER.deriveLinear(1.0E2, "dam^2", "square decameter");

    /** hm^2. */
    public static final AreaUnit SQUARE_HECTOMETER = SQUARE_METER.deriveLinear(1.0E4, "hm^2", "square hectometer");

    /** km^2. */
    public static final AreaUnit SQUARE_KILOMETER = SQUARE_METER.deriveLinear(1.0E6, "km^2", "square kilometer");

    /** centiare. */
    public static final AreaUnit CENTIARE = SQUARE_METER.deriveLinear(1.0, "ca", "centiare", UnitSystem.OTHER);

    /** are. */
    public static final AreaUnit ARE = CENTIARE.deriveLinear(100.0, "a", "are");

    /** hectare. */
    public static final AreaUnit HECTARE = ARE.deriveLinear(100.0, "ha", "hectare");

    /** mile^2. */
    public static final AreaUnit SQUARE_MILE =
            SQUARE_METER.deriveLinear(sqLength(LengthUnit.MILE), "mi^2", "square mile", UnitSystem.IMPERIAL);

    /** Nautical mile^2. */
    public static final AreaUnit SQUARE_NAUTICAL_MILE =
            SQUARE_METER.deriveLinear(sqLength(LengthUnit.NAUTICAL_MILE), "NM^2", "square Nautical Mile", UnitSystem.OTHER);

    /** ft^2. */
    public static final AreaUnit SQUARE_FOOT =
            SQUARE_METER.deriveLinear(sqLength(LengthUnit.FOOT), "ft^2", "square foot", UnitSystem.IMPERIAL);

    /** in^2. */
    public static final AreaUnit SQUARE_INCH =
            SQUARE_METER.deriveLinear(sqLength(LengthUnit.INCH), "in^2", "square inch", UnitSystem.IMPERIAL);

    /** yd^2. */
    public static final AreaUnit SQUARE_YARD =
            SQUARE_METER.deriveLinear(sqLength(LengthUnit.YARD), "yd^2", "square yard", UnitSystem.IMPERIAL);

    /** acre (international) defined as 1/640 square mile or 4840 square yards. */
    public static final AreaUnit ACRE = SQUARE_MILE.deriveLinear(1.0 / 640.0, "ac", "acre", UnitSystem.IMPERIAL);

    /**
     * Calculate the conversion factor for a "squared" length.
     * @param lu LengthUnit; the LengthUnit to use as the base
     * @return double; the conversion factor
     */
    private static double sqLength(final LengthUnit lu)
    {
        double factor = lu.getScale().toStandardUnit(1.0);
        return factor * factor;
    }

}
