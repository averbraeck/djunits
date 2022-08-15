package org.djunits.demo.website;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Example from the website to test if the code on the website is correct
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class JerkUnit extends Unit<JerkUnit>
{
    /** */
    private static final long serialVersionUID = 20191003L;

    /** The base quantity, with "m/s3" as the SI signature. */
    public static final Quantity<JerkUnit> BASE = new Quantity<>("Jerk", "m/s3");

    /** The SI unit for jerk is m/s^3. */
    public static final JerkUnit SI =
            new JerkUnit().build(new Unit.Builder<JerkUnit>().setQuantity(BASE).setId("m/s3").setName("meter per second cubed")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.NONE, 1.0).setScale(IdentityScale.SCALE));

    /** m/s3. */
    public static final JerkUnit M_PER_S3 = SI;

    /** cm/s3. */
    public static final JerkUnit CM_PER_S3 = SI.deriveLinear(factorLD("cm", "s"), "cm/s3", "centimeter per second cubed");

    /** mm/s3. */
    public static final JerkUnit MM_PER_S3 = SI.deriveLinear(factorLD("mm", "s"), "mm/s3", "millimeter per second cubed");

    /** ft/s3. */
    public static final JerkUnit FT_PER_S3 = SI.deriveLinear(factorLD("ft", "s"), "ft/s3", "foot per second cubed");

    /** in/s3. */
    public static final JerkUnit IN_PER_S3 = SI.deriveLinear(factorLD("in", "s"), "in/s3", "inch per second cubed");

    /**
     * Determine the conversion factor to the base jerk unit, given a length unit and a duration unit.
     * @param length String; a length unit, e.g. km
     * @param duration String; a duration unit, e.g. h
     * @return double; the conversion factor from the provided units (e.g. km/h3) to the standard unit (m/s3)
     */
    private static double factorLD(final String length, final String duration)
    {
        double l = LengthUnit.BASE.of(length).getScale().toStandardUnit(1.0);
        double d = DurationUnit.BASE.of(duration).getScale().toStandardUnit(1.0);
        return l / (d * d * d);
    }
}
