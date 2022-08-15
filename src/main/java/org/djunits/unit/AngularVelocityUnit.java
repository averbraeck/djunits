package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard angular velocity unit. See
 * <a href="https://en.wikipedia.org/wiki/Angular_velocity">https://en.wikipedia.org/wiki/Angular_velocity</a>.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class AngularVelocityUnit extends Unit<AngularVelocityUnit>
{
    /** */
    private static final long serialVersionUID = 20200117L;

    /** The base, with "rad/s" as the SI signature. */
    public static final Quantity<AngularVelocityUnit> BASE = new Quantity<>("AngularVelocity", "rad/s");

    /** The SI unit for angular velocity is radian per second. */
    public static final AngularVelocityUnit SI = new AngularVelocityUnit().build(new Unit.Builder<AngularVelocityUnit>()
            .setQuantity(BASE).setId("rad/s").setName("radians per second").setUnitSystem(UnitSystem.SI_DERIVED)
            .setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE).setAdditionalAbbreviations("rad/sec"));

    /** radian per second. */
    public static final AngularVelocityUnit RADIAN_PER_SECOND = SI;

    /** degree per second. */
    public static final AngularVelocityUnit DEGREE_PER_SECOND = RADIAN_PER_SECOND.deriveLinear(Math.PI / 180.0, "deg/s",
            "degree per second", UnitSystem.SI_ACCEPTED, "\u00b0/s", "deg/s", "dg/s", "dg/sec", "deg/sec");

    /** arcminute per second. */
    public static final AngularVelocityUnit ARCMINUTE_PER_SECOND = DEGREE_PER_SECOND.deriveLinear(1.0 / 60.0, "arcmin/s",
            "arcminute per second", UnitSystem.SI_ACCEPTED, "'/s", "'/sec", "arcmin/sec");

    /** arcsecond per second. */
    public static final AngularVelocityUnit ARCSECOND_PER_SECOND = DEGREE_PER_SECOND.deriveLinear(1.0 / 3600.0, "arcsec/s",
            "arcsecond per second", UnitSystem.SI_ACCEPTED, "\"/s", "\"/sec", new String[] {"arcsec/sec"});

    /** grad per second. */
    public static final AngularVelocityUnit GRAD_PER_SECOND =
            RADIAN_PER_SECOND.deriveLinear(2.0 * Math.PI / 400.0, "grad/s", "gradian per second", UnitSystem.OTHER);

    /** centesimal arcminute per second. */
    public static final AngularVelocityUnit CENTESIMAL_ARCMINUTE_PER_SECOND = GRAD_PER_SECOND.deriveLinear(1.0 / 100.0, "cdm/s",
            "centesimal arcminute per second", UnitSystem.OTHER, "c'/s", "c'/sec");

    /** centesimal arcsecond per second. */
    public static final AngularVelocityUnit CENTESIMAL_ARCSECOND_PER_SECOND = GRAD_PER_SECOND.deriveLinear(1.0 / 10000.0,
            "cds/s", "centesimal arcsecond per second", UnitSystem.OTHER, "c\"/s", "c\"/sec");

}
