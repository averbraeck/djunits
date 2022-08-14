package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of power.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class PowerUnit extends Unit<PowerUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "kgm2/s3" as the SI signature. */
    public static final Quantity<PowerUnit> BASE = new Quantity<>("Power", "kgm2/s3");

    /** The SI unit for power is watt. */
    public static final PowerUnit SI =
            new PowerUnit().build(new Unit.Builder<PowerUnit>().setQuantity(BASE).setId("W").setName("watt")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** watt. */
    public static final PowerUnit WATT = SI;

    /** microwatt. */
    public static final PowerUnit MICROWATT = WATT.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** milliwatt. */
    public static final PowerUnit MILLIWATT = WATT.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** kiloawatt. */
    public static final PowerUnit KILOWATT = WATT.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** megawatt. */
    public static final PowerUnit MEGAWATT = WATT.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** gigawatt. */
    public static final PowerUnit GIGAWATT = WATT.deriveSI(SIPrefixes.getUnit("G"), 1.0);

    /** terawatt. */
    public static final PowerUnit TERAWATT = WATT.deriveSI(SIPrefixes.getUnit("T"), 1.0);

    /** petawatt. */
    public static final PowerUnit PETAWATT = WATT.deriveSI(SIPrefixes.getUnit("P"), 1.0);

    /** foot-pound-force per hour. */
    public static final PowerUnit FOOT_POUND_FORCE_PER_HOUR =
            SI.deriveLinear(factorED(EnergyUnit.FOOT_POUND_FORCE, DurationUnit.HOUR), "ft.lbf/h", "foot pound-force per hour",
                    UnitSystem.IMPERIAL);

    /** foot-pound-force per minute. */
    public static final PowerUnit FOOT_POUND_FORCE_PER_MINUTE =
            SI.deriveLinear(factorED(EnergyUnit.FOOT_POUND_FORCE, DurationUnit.MINUTE), "ft.lbf/min",
                    "foot pound-force per minute", UnitSystem.IMPERIAL);

    /** foot-pound-force per second. */
    public static final PowerUnit FOOT_POUND_FORCE_PER_SECOND =
            SI.deriveLinear(factorED(EnergyUnit.FOOT_POUND_FORCE, DurationUnit.SECOND), "ft.lbf/s",
                    "foot pound-force per second", UnitSystem.IMPERIAL);

    /** horsepower (metric). */
    public static final PowerUnit HORSEPOWER_METRIC =
            WATT.deriveLinear(735.49875, "hp(M)", "horsepower (metric)", UnitSystem.OTHER);

    /** sthene-meter per second. */
    public static final PowerUnit STHENE_METER_PER_SECOND = SI.deriveLinear(
            factorED(EnergyUnit.STHENE_METER, DurationUnit.SECOND), "sn.m/s", "sthene-meter per second", UnitSystem.MTS);

    /** erg per second. */
    public static final PowerUnit ERG_PER_SECOND =
            SI.deriveLinear(factorED(EnergyUnit.ERG, DurationUnit.SECOND), "erg/s", "erg per second", UnitSystem.CGS);

    /**
     * Determine the conversion factor to the base power unit, given an energy unit and a duration unit.
     * @param energy EnergyUnit; the used energy unit, e.g. erg
     * @param duration DurationUnit; the used duration unit, e.g. s
     * @return double; the conversion factor from the provided units (e.g. erg/s) to the standard unit (e.g., W)
     */
    private static double factorED(final EnergyUnit energy, final DurationUnit duration)
    {
        return energy.getScale().toStandardUnit(1.0) / duration.getScale().toStandardUnit(1.0);
    }

}
