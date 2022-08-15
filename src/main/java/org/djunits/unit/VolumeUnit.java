package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * VolumeUnit defines a number of common units for volumes.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class VolumeUnit extends Unit<VolumeUnit>
{
    /** */
    private static final long serialVersionUID = 20140604L;

    /** The base, with "m2" as the SI signature. */
    public static final Quantity<VolumeUnit> BASE = new Quantity<>("Volume", "m3");

    /** The SI unit for area is m^3. */
    public static final VolumeUnit SI =
            new VolumeUnit().build(new Unit.Builder<VolumeUnit>().setQuantity(BASE).setId("m^3").setName("cubic meter")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 3.0).setScale(IdentityScale.SCALE));

    /** m^3. */
    public static final VolumeUnit CUBIC_METER = SI;

    /** mm^3. */
    public static final VolumeUnit CUBIC_MILLIMETER = CUBIC_METER.deriveLinear(1.0E-9, "mm^3", "cubic millimeter");

    /** cm^3. */
    public static final VolumeUnit CUBIC_CENTIMETER = CUBIC_METER.deriveLinear(1.0E-6, "cm^3", "cubic centimeter");

    /** dm^3. */
    public static final VolumeUnit CUBIC_DECIMETER = CUBIC_METER.deriveLinear(1.0E-3, "dm^3", "cubic decimeter");

    /** dam^3. */
    public static final VolumeUnit CUBIC_DECAMETER = CUBIC_METER.deriveLinear(1.0E3, "dam^3", "cubic decameter");

    /** hm^3. */
    public static final VolumeUnit CUBIC_HECTOMETER = CUBIC_METER.deriveLinear(1.0E6, "hm^3", "cubic hectometer");

    /** km^3. */
    public static final VolumeUnit CUBIC_KILOMETER = CUBIC_METER.deriveLinear(1.0E9, "km^3", "cubic kilometer");

    /** mile^3. */
    public static final VolumeUnit CUBIC_MILE =
            CUBIC_METER.deriveLinear(cubedLength(LengthUnit.MILE), "mi^3", "cubic mile", UnitSystem.IMPERIAL);

    /** Nautical mile^3. */
    public static final VolumeUnit CUBIC_NAUTICAL_MILE =
            CUBIC_METER.deriveLinear(cubedLength(LengthUnit.NAUTICAL_MILE), "NM^3", "cubic Nautical Mile", UnitSystem.OTHER);

    /** ft^3. */
    public static final VolumeUnit CUBIC_FOOT =
            CUBIC_METER.deriveLinear(cubedLength(LengthUnit.FOOT), "ft^3", "cubic foot", UnitSystem.IMPERIAL);

    /** in^3. */
    public static final VolumeUnit CUBIC_INCH =
            CUBIC_METER.deriveLinear(cubedLength(LengthUnit.INCH), "in^3", "cubic inch", UnitSystem.IMPERIAL);

    /** yd^3. */
    public static final VolumeUnit CUBIC_YARD =
            CUBIC_METER.deriveLinear(cubedLength(LengthUnit.YARD), "yd^3", "cubic yard", UnitSystem.IMPERIAL);

    /** liter. */
    public static final VolumeUnit LITER = CUBIC_DECIMETER.deriveLinear(1.0, "L", "liter", UnitSystem.SI_ACCEPTED);

    /** gallon (US), fluids. */
    public static final VolumeUnit GALLON_US =
            CUBIC_INCH.deriveLinear(231.0, "gal(US)", "gallon (US)", UnitSystem.US_CUSTOMARY);

    /** gallon (imperial). */
    public static final VolumeUnit GALLON_IMP = LITER.deriveLinear(4.54609, "gal(imp)", "gallon (imp)", UnitSystem.IMPERIAL);

    /** quart (fluid US) = 1/4 US gallon. */
    public static final VolumeUnit QUART_US = GALLON_US.deriveLinear(0.25, "qt(US)", "quart (US)");

    /** quart (imperial) = 1/4 imp gallon. */
    public static final VolumeUnit QUART_IMP = GALLON_IMP.deriveLinear(0.25, "qt(imp)", "quart (imp)");

    /** pint (fluid US) = 1/2 US quart. */
    public static final VolumeUnit PINT_US = QUART_US.deriveLinear(0.5, "pt(US)", "pint (US)");

    /** pint (imperial) = 1/2 imp quart. */
    public static final VolumeUnit PINT_IMP = QUART_IMP.deriveLinear(0.5, "pt(imp)", "pint (imp)");

    /** ounce (fluid US) = 1/16 US pint. */
    public static final VolumeUnit FLUID_OUNCE_US = PINT_US.deriveLinear(1.0 / 16.0, "fl.oz(US)", "fluid ounce (US)");

    /** ounce (fluid imperial) = 1/20 imp pint. */
    public static final VolumeUnit FLUID_OUNCE_IMP = PINT_IMP.deriveLinear(1.0 / 20.0, "fl.oz(imp)", "fluid ounce (imp)");

    /** Cubic lightyear. */
    public static final VolumeUnit CUBIC_LIGHTYEAR =
            CUBIC_METER.deriveLinear(cubedLength(LengthUnit.LIGHTYEAR), "ly^3", "cubic lightyear", UnitSystem.OTHER);

    /** Cubic Parsec. */
    public static final VolumeUnit CUBIC_PARSEC =
            CUBIC_METER.deriveLinear(cubedLength(LengthUnit.PARSEC), "pc^3", "cubic Parsec", UnitSystem.OTHER);

    /**
     * Calculate the conversion factor for a "cubed" length.
     * @param lu LengthUnit; the LengthUnit to use as the base
     * @return double; the conversion factor
     */
    private static double cubedLength(final LengthUnit lu)
    {
        double factor = lu.getScale().toStandardUnit(1.0);
        return factor * factor * factor;
    }

}
