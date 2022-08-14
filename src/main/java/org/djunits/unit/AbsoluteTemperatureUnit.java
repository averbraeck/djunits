package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * AbsoluteTemperature units.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class AbsoluteTemperatureUnit extends AbsoluteLinearUnit<AbsoluteTemperatureUnit, TemperatureUnit>
{
    /** */
    private static final long serialVersionUID = 20140605L;

    /** The base, with "K" as the SI signature. */
    public static final Quantity<AbsoluteTemperatureUnit> BASE = new Quantity<>("AbsoluteTemperature", "K");

    /** The default unit for temperature is Kelvin. */
    public static final AbsoluteTemperatureUnit KELVIN = new AbsoluteTemperatureUnit()
            .build(new AbsoluteLinearUnit.Builder<AbsoluteTemperatureUnit, TemperatureUnit>().setQuantity(BASE).setId("K")
                    .setName("Kelvin").setUnitSystem(UnitSystem.SI_BASE).setSiPrefixes(SIPrefixes.UNIT, 1.0)
                    .setRelativeUnit(TemperatureUnit.KELVIN).setScale(new OffsetLinearScale(1.0, 0.0)));

    /** The default unit for temperature is Kelvin. */
    public static final AbsoluteTemperatureUnit DEFAULT = KELVIN;

    /** Degree Celsius. */
    public static final AbsoluteTemperatureUnit DEGREE_CELSIUS =
            KELVIN.deriveLinearOffset(1.0, 273.15, TemperatureUnit.DEGREE_CELSIUS, "dgC", "degree Celsius",
                    UnitSystem.SI_DERIVED, "\u00B0C", "degC", new String[] {"C"});

    /** Degree Fahrenheit. */
    public static final AbsoluteTemperatureUnit DEGREE_FAHRENHEIT =
            KELVIN.deriveLinearOffset(5.0 / 9.0, 459.67, TemperatureUnit.DEGREE_FAHRENHEIT, "dgF", "degree Fahrenheit",
                    UnitSystem.SI_DERIVED, "\u00B0F", "degF", new String[] {"F"});

    /** Degree Rankine. */
    public static final AbsoluteTemperatureUnit DEGREE_RANKINE =
            KELVIN.deriveLinearOffset(5.0 / 9.0, 0.0, TemperatureUnit.DEGREE_RANKINE, "dgR", "degree Rankine",
                    UnitSystem.SI_DERIVED, "\u00B0R", "degR", new String[] {"R"});

    /** Degree Reaumur. */
    public static final AbsoluteTemperatureUnit DEGREE_REAUMUR =
            KELVIN.deriveLinearOffset(4.0 / 5.0, 273.15, TemperatureUnit.DEGREE_REAUMUR, "dgRe", "degree Reaumur",
                    UnitSystem.SI_DERIVED, "\u00B0R\u00E9", "degRe", new String[] {"Re", "R\u00E9"});
}
