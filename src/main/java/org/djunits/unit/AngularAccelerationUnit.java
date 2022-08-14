package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard angular acceleration unit. See
 * <a href="https://en.wikipedia.org/wiki/Angular_acceleration">https://en.wikipedia.org/wiki/Angular_acceleration</a>.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class AngularAccelerationUnit extends Unit<AngularAccelerationUnit>
{
    /** */
    private static final long serialVersionUID = 20200117L;

    /** The base, with "rad/s2" as the SI signature. */
    public static final Quantity<AngularAccelerationUnit> BASE = new Quantity<>("AngularAcceleration", "rad/s2");

    /** The SI unit for angular velocity is radian per second squared. */
    public static final AngularAccelerationUnit SI =
            new AngularAccelerationUnit().build(new Unit.Builder<AngularAccelerationUnit>().setQuantity(BASE).setId("rad/s2")
                    .setName("radians per second squared").setUnitSystem(UnitSystem.SI_DERIVED)
                    .setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE).setAdditionalAbbreviations("rad/sec2"));

    /** radian per second squared. */
    public static final AngularAccelerationUnit RADIAN_PER_SECOND_SQUARED = SI;

    /** degree per second squared. */
    public static final AngularAccelerationUnit DEGREE_PER_SECOND_SQUARED =
            RADIAN_PER_SECOND_SQUARED.deriveLinear(Math.PI / 180.0, "deg/s2", "degree per second", UnitSystem.SI_ACCEPTED,
                    "\u00b0/s2", "deg/s2", "dg/s2", "dg/sec2", "deg/sec2");

    /** arcminute per second squared. */
    public static final AngularAccelerationUnit ARCMINUTE_PER_SECOND_SQUARED = DEGREE_PER_SECOND_SQUARED.deriveLinear(
            1.0 / 60.0, "arcmin/s2", "arcminute per second squared", UnitSystem.SI_ACCEPTED, "'/s2", "'/sec2", "arcmin/sec2");

    /** arcsecond per second squared. */
    public static final AngularAccelerationUnit ARCSECOND_PER_SECOND_SQUARED =
            DEGREE_PER_SECOND_SQUARED.deriveLinear(1.0 / 3600.0, "arcsec/s2", "arcsecond per second squared",
                    UnitSystem.SI_ACCEPTED, "\"/s2", "\"/sec2", new String[] {"arcsec/sec2"});

    /** grad per second squared. */
    public static final AngularAccelerationUnit GRAD_PER_SECOND_SQUARED = RADIAN_PER_SECOND_SQUARED
            .deriveLinear(2.0 * Math.PI / 400.0, "grad/s2", "gradian per second squared", UnitSystem.OTHER);

    /** centesimal arcminute per second squared. */
    public static final AngularAccelerationUnit CENTESIMAL_ARCMINUTE_SQUARED = GRAD_PER_SECOND_SQUARED.deriveLinear(1.0 / 100.0,
            "cdm/s2", "centesimal arcminute per second squared", UnitSystem.OTHER, "c'/s2", "c'/sec2");

    /** centesimal arcsecond per second squared. */
    public static final AngularAccelerationUnit CENTESIMAL_ARCSECOND_PER_SECOND_SQUARED = GRAD_PER_SECOND_SQUARED.deriveLinear(
            1.0 / 10000.0, "cds/s2", "centesimal arcsecond per second squared", UnitSystem.OTHER, "c\"/s2", "c\"/sec2");

}
