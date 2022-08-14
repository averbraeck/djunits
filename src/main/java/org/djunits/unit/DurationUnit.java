package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard duration units.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class DurationUnit extends Unit<DurationUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "s" as the SI signature. */
    public static final Quantity<DurationUnit> BASE = new Quantity<>("Duration", "s");

    /** The SI unit for duration is second. */
    public static final DurationUnit SI = new DurationUnit().build(
            new Unit.Builder<DurationUnit>().setQuantity(BASE).setId("s").setName("second").setUnitSystem(UnitSystem.SI_BASE)
                    .setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE).setAdditionalAbbreviations("sec"));

    /** second. */
    public static final DurationUnit SECOND = SI;

    /** microsecond. */
    public static final DurationUnit MICROSECOND =
            SI.deriveLinear(1.0E-6, "mus", "microsecond", UnitSystem.SI_DERIVED, "\u03BCs", "mus", "\u03BCsec", "musec");

    /** millisecond. */
    public static final DurationUnit MILLISECOND =
            SI.deriveLinear(1.0E-3, "ms", "millisecond", UnitSystem.SI_DERIVED, "ms", "ms", "msec");

    /** minute. */
    public static final DurationUnit MINUTE = SI.deriveLinear(60.0, "min", "minute", UnitSystem.SI_ACCEPTED, "min", "min");

    /** hour. */
    public static final DurationUnit HOUR =
            MINUTE.deriveLinear(60.0, "h", "hour", UnitSystem.SI_ACCEPTED, "h", "h", new String[] {"hr", "hour"});

    /** day. */
    public static final DurationUnit DAY = HOUR.deriveLinear(24.0, "day", "day", UnitSystem.SI_ACCEPTED, "day", "day");

    /** week. */
    public static final DurationUnit WEEK =
            DAY.deriveLinear(7.0, "wk", "week", UnitSystem.OTHER, "wk", "wk", new String[] {"week"});

}
