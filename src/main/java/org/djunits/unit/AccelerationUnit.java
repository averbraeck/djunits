package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard acceleration unit based on distance and time.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class AccelerationUnit extends Unit<AccelerationUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "m/s2" as the SI signature. */
    public static final Quantity<AccelerationUnit> BASE = new Quantity<>("Acceleration", "m/s2");

    /** The SI unit for acceleration is m/s^2. */
    public static final AccelerationUnit SI = new AccelerationUnit().build(new Unit.Builder<AccelerationUnit>()
            .setQuantity(BASE).setId("m/s2").setName("meter per second squared").setUnitSystem(UnitSystem.SI_DERIVED)
            .setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE).setAdditionalAbbreviations("m/sec^2"));

    /** m/s2. */
    public static final AccelerationUnit METER_PER_SECOND_2 = SI;

    /** km/h2. */
    public static final AccelerationUnit KM_PER_HOUR_2 = SI.deriveLinear(factorLD("km", "h"), "km/h2",
            "kilometer per hour squared", UnitSystem.SI_ACCEPTED, "km/h2", "km/h2", "km/hr^2", "km/hour^2");

    /** ft/s2. */
    public static final AccelerationUnit FOOT_PER_SECOND_2 = SI.deriveLinear(factorLD("ft", "s"), "ft/s2",
            "foot per second squared", UnitSystem.IMPERIAL, "ft/s2", "ft/s2", "ft/sec^2");

    /** in/s2. */
    public static final AccelerationUnit INCH_PER_SECOND_2 = SI.deriveLinear(factorLD("in", "s"), "in/s2",
            "inch per second squared", UnitSystem.IMPERIAL, "in/s2", "in/s2", "in/sec^2");

    /** mi/h2. */
    public static final AccelerationUnit MILE_PER_HOUR_2 = SI.deriveLinear(factorLD("mi", "h"), "mi/h2",
            "mile per hour squared", UnitSystem.IMPERIAL, "mi/h2", "mi/h2", "mi/hr^2", "mi/hour^2");

    /** mi/s2. */
    public static final AccelerationUnit MILE_PER_SECOND_2 = SI.deriveLinear(factorLD("mi", "s"), "mi/s2",
            "mile per second squared", UnitSystem.IMPERIAL, "mi/s2", "mi/s2", "mi/sec^2");

    /** kt/s. */
    public static final AccelerationUnit KNOT_PER_SECOND =
            SI.deriveLinear(factorSD("kt", "s"), "kt/s", "knot per second", UnitSystem.OTHER, "kt/s", "kt/s", "kt/sec");

    /** mi/h/s. */
    public static final AccelerationUnit MILE_PER_HOUR_PER_SECOND =
            SI.deriveLinear(factorSD("mi/h", "s"), "mi/h/s", "mile per hour per second", UnitSystem.IMPERIAL, "mi/h/s",
                    "mi/h/s", "mi/hr/s", "mi/hour/s", "mi/h/sec", "mi/hr/sec", "mi/hour/sec");

    /** The standard gravity. */
    public static final AccelerationUnit STANDARD_GRAVITY = SI.deriveLinear(9.80665, "g", "standard gravity", UnitSystem.OTHER);

    /** cm/s. */
    public static final AccelerationUnit GAL = SI.deriveLinear(factorLD("cm", "s"), "Gal", "gal", UnitSystem.CGS);

    /**
     * Determine the conversion factor to the base acceleration unit, given a length unit and a duration unit.
     * @param length String; a length unit, e.g. km
     * @param duration String; a duration unit, e.g. h
     * @return double; the conversion factor from the provided units (e.g. km/h2) to the standard unit (m/s2)
     */
    private static double factorLD(final String length, final String duration)
    {
        double l = LengthUnit.BASE.of(length).getScale().toStandardUnit(1.0);
        double d = DurationUnit.BASE.of(duration).getScale().toStandardUnit(1.0);
        return l / (d * d);
    }

    /**
     * Determine the conversion factor to the base acceleration unit, given a speed unit and a duration unit.
     * @param speed String; a speed unit, e.g. km/h
     * @param duration String; a duration unit, e.g. s
     * @return the conversion factor from the provided units (e.g. km/h/s) to the standard unit (m/s2)
     */
    private static double factorSD(final String speed, final String duration)
    {
        return SpeedUnit.BASE.of(speed).getScale().toStandardUnit(1.0)
                / DurationUnit.BASE.of(duration).getScale().toStandardUnit(1.0);
    }

}
