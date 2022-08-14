package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Temperature units.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class TemperatureUnit extends Unit<TemperatureUnit>
{
    /** */
    private static final long serialVersionUID = 20140605L;

    /** The base, with "m2" as the SI signature. */
    public static final Quantity<TemperatureUnit> BASE = new Quantity<>("Temperature", "K");

    /** The SI unit for temperature is Kelvin. */
    public static final TemperatureUnit SI =
            new TemperatureUnit().build(new Unit.Builder<TemperatureUnit>().setQuantity(BASE).setId("K").setName("Kelvin")
                    .setUnitSystem(UnitSystem.SI_BASE).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Kelvin. */
    public static final TemperatureUnit KELVIN = SI;

    /** Degree Celsius. */
    public static final TemperatureUnit DEGREE_CELSIUS =
            KELVIN.deriveLinear(1.0, "dgC", "degree Celcius", UnitSystem.SI_DERIVED, "\u00B0C", "degC", new String[] {"C"});

    /** Degree Fahrenheit. */
    public static final TemperatureUnit DEGREE_FAHRENHEIT = KELVIN.deriveLinear(5.0 / 9.0, "dgF", "degree Fahrenheit",
            UnitSystem.SI_DERIVED, "\u00B0F", "degF", new String[] {"F"});

    /** Degree Rankine. */
    public static final TemperatureUnit DEGREE_RANKINE = KELVIN.deriveLinear(5.0 / 9.0, "dgR", "degree Rankine",
            UnitSystem.SI_DERIVED, "\u00B0R", "degR", new String[] {"R"});

    /** Degree Reaumur. */
    public static final TemperatureUnit DEGREE_REAUMUR = KELVIN.deriveLinear(4.0 / 5.0, "dgRe", "degree Reaumur",
            UnitSystem.SI_DERIVED, "\u00B0R\u00E9", "degRe", new String[] {"Re", "R\u00E9"});

}
