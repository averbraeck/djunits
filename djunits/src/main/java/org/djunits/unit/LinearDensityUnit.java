package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Objects per unit of distance.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class LinearDensityUnit extends Unit<LinearDensityUnit>
{
    /** */
    private static final long serialVersionUID = 20141111L;

    /** The base, with "/m" as the SI signature. */
    public static final Quantity<LinearDensityUnit> BASE = new Quantity<>("LinearDensity", "m-1");

    /** The SI unit for objects per unit of distance is 1/meter. */
    public static final LinearDensityUnit SI = new LinearDensityUnit().build(new Unit.Builder<LinearDensityUnit>()
            .setQuantity(BASE).setId("/m").setName("per meter").setUnitSystem(UnitSystem.SI_DERIVED)
            .setSiPrefixes(SIPrefixes.PER_UNIT, 1.0).setScale(IdentityScale.SCALE).setAdditionalAbbreviations("/m", "1/m"));

    /** 1/meter. */
    public static final LinearDensityUnit PER_METER = SI;

    /** 1/&#181;m. */
    public static final LinearDensityUnit PER_MICROMETER = PER_METER.deriveLinear(1.0E6, "/mum", "per micrometer",
            UnitSystem.SI_DERIVED, "/\u03BCm", "/mum", "1/\u03BCm", "1/mum");

    /** 1/millimeter. */
    public static final LinearDensityUnit PER_MILLIMETER =
            PER_METER.deriveLinear(1.0E3, "/mm", "per millimeter", UnitSystem.SI_DERIVED, "/mm", "1/mm");

    /** 1/centimeter. */
    public static final LinearDensityUnit PER_CENTIMETER =
            PER_METER.deriveLinear(100.0, "/cm", "per centimeter", UnitSystem.SI_DERIVED, "/cm", "1/cm");

    /** 1/decimeter. */
    public static final LinearDensityUnit PER_DECIMETER =
            PER_METER.deriveLinear(10.0, "/dm", "per decimeter", UnitSystem.SI_DERIVED, "/dm", "1/dm");

    /** 1/decameter. */
    public static final LinearDensityUnit PER_DECAMETER =
            PER_METER.deriveLinear(0.1, "/dam", "per decameter", UnitSystem.SI_DERIVED, "/dam", "1/dam");

    /** 1/hectometer. */
    public static final LinearDensityUnit PER_HECTOMETER =
            PER_METER.deriveLinear(0.01, "/hm", "per hectometer", UnitSystem.SI_DERIVED, "/hm", "1/hm");

    /** 1/kilometer. */
    public static final LinearDensityUnit PER_KILOMETER =
            PER_METER.deriveLinear(0.001, "/km", "per kilometer", UnitSystem.SI_DERIVED, "/km", "1/km");

    /** 1/foot (international) = 0.3048 m = 1/3 yd = 12 inches. */
    public static final LinearDensityUnit PER_FOOT =
            PER_METER.deriveLinear(1.0 / 0.3048, "/ft", "per foot", UnitSystem.IMPERIAL, "/ft", "1/ft");

    /** 1/inch (international) = 2.54 cm = 1/36 yd = 1/12 ft. */
    public static final LinearDensityUnit PER_INCH =
            PER_FOOT.deriveLinear(12.0, "/in", "per inch", UnitSystem.IMPERIAL, "/in", "1/in");

    /** 1/yard (international) = 0.9144 m = 3 ft = 36 in. */
    public static final LinearDensityUnit PER_YARD =
            PER_FOOT.deriveLinear(1.0 / 3.0, "/yd", "per yard", UnitSystem.IMPERIAL, "/yd", "1/yd");

    /** 1/mile (international) = 5280 ft = 1760 yd. */
    public static final LinearDensityUnit PER_MILE =
            PER_FOOT.deriveLinear(1.0 / 5280.0, "/mi", "per mile", UnitSystem.IMPERIAL, "/mi", "1/mi");

    /** 1/nautical mile (international) = 1852 m. */
    public static final LinearDensityUnit PER_NAUTICAL_MILE =
            PER_METER.deriveLinear(1.0 / 1852.0, "/NM", "per Nautical Mile", UnitSystem.OTHER, "/NM", "1/NM");

    /** 1/Astronomical Unit. */
    public static final LinearDensityUnit PER_ASTRONOMICAL_UNIT =
            PER_METER.deriveLinear(1.0 / 149597870700.0, "/AU", "per Astronomical Unit", UnitSystem.OTHER, "/AU", "1/AU");

    /** 1/Lightyear. */
    public static final LinearDensityUnit PER_LIGHTYEAR =
            PER_METER.deriveLinear(1.0 / 9460730472580800.0, "/ly", "per lightyear", UnitSystem.OTHER, "/ly", "1/ly");

    /** 1/Parsec. */
    public static final LinearDensityUnit PER_PARSEC =
            PER_LIGHTYEAR.deriveLinear(Math.PI / 648000.0, "/pc", "per parsec", UnitSystem.OTHER, "/pc", "1/pc");

    /** 1/Angstrom. */
    public static final LinearDensityUnit PER_ANGSTROM =
            PER_METER.deriveLinear(1.0E-10, "/A", "per Angstrom", UnitSystem.OTHER, "/\u212B", "/A", "1/\u212B", "1/A");

}
