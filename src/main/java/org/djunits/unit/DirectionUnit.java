package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard direction unit. Several conversion factors have been taken from
 * <a href="http://en.wikipedia.org/wiki/Conversion_of_units">http://en.wikipedia.org/wiki/Conversion_of_units</a>.
 * <p>
 * Note that the EAST and NORTH Directions are <b>counter</b>clockwise.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class DirectionUnit extends AbsoluteLinearUnit<DirectionUnit, AngleUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "rad" as the SI signature. */
    public static final Quantity<DirectionUnit> BASE = new Quantity<>("Direction", "rad");

    /** The unit for direction with East as the origin and radians as the displacement. */
    public static final DirectionUnit EAST_RADIAN =
            new DirectionUnit().build(new AbsoluteLinearUnit.Builder<DirectionUnit, AngleUnit>().setQuantity(BASE)
                    .setId("rad(E)").setName("radians (East)").setDefaultDisplayAbbreviation("rad(E)")
                    .setDefaultTextualAbbreviation("rad(E)").setUnitSystem(UnitSystem.OTHER).setSiPrefixes(SIPrefixes.NONE, 1.0)
                    .setScale(new OffsetLinearScale(1.0, 0.0)).setRelativeUnit(AngleUnit.RADIAN));

    /** The default unit for direction is East_Radian. */
    public static final DirectionUnit DEFAULT = EAST_RADIAN;

    /** The unit for direction with East as the origin and degrees as the displacement. */
    public static final DirectionUnit EAST_DEGREE =
            new DirectionUnit().build(new AbsoluteLinearUnit.Builder<DirectionUnit, AngleUnit>().setQuantity(BASE)
                    .setId("deg(E)").setName("degrees (East)").setDefaultDisplayAbbreviation("\u00b0(E)")
                    .setDefaultTextualAbbreviation("deg(E)").setUnitSystem(UnitSystem.OTHER).setSiPrefixes(SIPrefixes.NONE, 1.0)
                    .setScale(new OffsetLinearScale(Math.PI / 180.0, 0.0)).setRelativeUnit(AngleUnit.DEGREE));

    /** The unit for direction with North as the origin and radians as the displacement. */
    public static final DirectionUnit NORTH_RADIAN =
            new DirectionUnit().build(new AbsoluteLinearUnit.Builder<DirectionUnit, AngleUnit>().setQuantity(BASE)
                    .setId("rad(N)").setName("radians (North)").setDefaultDisplayAbbreviation("rad(N)")
                    .setDefaultTextualAbbreviation("rad(N)").setUnitSystem(UnitSystem.OTHER).setSiPrefixes(SIPrefixes.NONE, 1.0)
                    .setScale(new OffsetLinearScale(1.0, Math.PI / 2.0)).setRelativeUnit(AngleUnit.RADIAN));

    /** The unit for direction with North as the origin and degrees as the displacement. */
    public static final DirectionUnit NORTH_DEGREE =
            new DirectionUnit().build(new AbsoluteLinearUnit.Builder<DirectionUnit, AngleUnit>().setQuantity(BASE)
                    .setId("deg(N)").setName("degrees (North)").setDefaultDisplayAbbreviation("\u00b0(N)")
                    .setDefaultTextualAbbreviation("deg(N)").setUnitSystem(UnitSystem.OTHER).setSiPrefixes(SIPrefixes.NONE, 1.0)
                    .setScale(new OffsetLinearScale(Math.PI / 180.0, 90.0)).setRelativeUnit(AngleUnit.DEGREE));

}
