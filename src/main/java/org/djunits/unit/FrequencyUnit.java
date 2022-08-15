package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard frequency unit based on time.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FrequencyUnit extends Unit<FrequencyUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The SI unit for frequency is Hertz or 1/s. */
    public static final Quantity<FrequencyUnit> BASE = new Quantity<>("Frequency", "/s");

    /** The SI unit for frequency is Hertz. */
    public static final FrequencyUnit SI =
            new FrequencyUnit().build(new Unit.Builder<FrequencyUnit>().setQuantity(BASE).setId("Hz").setName("hertz")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Hertz. */
    public static final FrequencyUnit HERTZ = SI;

    /** kiloHertz. */
    public static final FrequencyUnit KILOHERTZ = HERTZ.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** megaHertz. */
    public static final FrequencyUnit MEGAHERTZ = HERTZ.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** gigaHertz. */
    public static final FrequencyUnit GIGAHERTZ = HERTZ.deriveSI(SIPrefixes.getUnit("G"), 1.0);

    /** teraHertz. */
    public static final FrequencyUnit TERAHERTZ = HERTZ.deriveSI(SIPrefixes.getUnit("T"), 1.0);

    /** Revolutions per minute = 1/60 Hz. */
    public static final FrequencyUnit RPM = HERTZ.deriveLinear(1.0 / 60.0, "rpm", "revolutions per minute", UnitSystem.OTHER);

    /** 1/s and all derived units. */
    public static final FrequencyUnit PER_SECOND = new FrequencyUnit().build(new Unit.Builder<FrequencyUnit>().setQuantity(BASE)
            .setId("/s").setName("per second").setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.PER_UNIT, 1.0)
            .setScale(IdentityScale.SCALE).setAdditionalAbbreviations("/s", "1/s", "/sec", "1/sec"));

    /** 1/microsecond. */
    public static final FrequencyUnit PER_MICROSECOND = PER_SECOND.deriveLinear(1.0E6, "/mus", "per microsecond",
            UnitSystem.SI_DERIVED, "/\u03BCs", "/mus", "1/mus", "1/\u03BCs", "/musec", "1/musec", "/\u03BCsec", "1/\u03BCsec");

    /** 1/millisecond. */
    public static final FrequencyUnit PER_MILLISECOND = PER_SECOND.deriveLinear(1.0E3, "/ms", "per millisecond",
            UnitSystem.SI_DERIVED, "/ms", "/ms", "1/ms", "/msec", "1/msec");

    /** 1/min. */
    public static final FrequencyUnit PER_MINUTE =
            PER_SECOND.deriveLinear(1.0 / 60.0, "/min", "per minute", UnitSystem.SI_ACCEPTED, "/min", "1/min");

    /** 1/hour. */
    public static final FrequencyUnit PER_HOUR = PER_SECOND.deriveLinear(1.0 / 3600.0, "/h", "per hour", UnitSystem.SI_ACCEPTED,
            "/h", "/h", "1/h", "/hr", "1/hr", "/hour", "1/hour");

    /** 1/day. */
    public static final FrequencyUnit PER_DAY =
            PER_HOUR.deriveLinear(1.0 / 24.0, "/day", "per day", UnitSystem.SI_ACCEPTED, "/day", "/day", "1/day");

    /** 1/week. */
    public static final FrequencyUnit PER_WEEK =
            PER_DAY.deriveLinear(1.0 / 7.0, "/wk", "per week", UnitSystem.OTHER, "/wk", "/wk", "1/wk", "/week", "1/week");

}
