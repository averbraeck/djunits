package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of energy.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class EnergyUnit extends Unit<EnergyUnit>
{
    /** */
    private static final long serialVersionUID = 20140604L;

    /** The base, with "kgm2/s2" as the SI signature. */
    public static final Quantity<EnergyUnit> BASE = new Quantity<>("Energy", "kgm2/s2");

    /** The SI unit for energy is Joule (J) = kgm2/s2. */
    public static final EnergyUnit SI =
            new EnergyUnit().build(new Unit.Builder<EnergyUnit>().setQuantity(BASE).setId("J").setName("joule")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Joule. */
    public static final EnergyUnit JOULE = SI;

    /** microjoule. */
    public static final EnergyUnit MICROJOULE = JOULE.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** millijoule. */
    public static final EnergyUnit MILLIJOULE = JOULE.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** kilojoule. */
    public static final EnergyUnit KILOJOULE = JOULE.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** megajoule. */
    public static final EnergyUnit MEGAJOULE = JOULE.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** gigajoule. */
    public static final EnergyUnit GIGAJOULE = JOULE.deriveSI(SIPrefixes.getUnit("G"), 1.0);

    /** terajoule. */
    public static final EnergyUnit TERAJOULE = JOULE.deriveSI(SIPrefixes.getUnit("T"), 1.0);

    /** petajoule. */
    public static final EnergyUnit PETAJOULE = JOULE.deriveSI(SIPrefixes.getUnit("P"), 1.0);

    /** foot-pound force. */
    public static final EnergyUnit FOOT_POUND_FORCE = JOULE.deriveLinear(factorFL(ForceUnit.POUND_FORCE, LengthUnit.FOOT),
            "ft.lbf", "foot pound-force", UnitSystem.IMPERIAL);

    /** inch-pound force. */
    public static final EnergyUnit INCH_POUND_FORCE = JOULE.deriveLinear(factorFL(ForceUnit.POUND_FORCE, LengthUnit.INCH),
            "in.lbf", "inch pound-force", UnitSystem.IMPERIAL);

    /** British thermal unit (ISO). */
    public static final EnergyUnit BTU_ISO =
            JOULE.deriveLinear(1.0545E3, "BTU(ISO)", "British thermal unit (ISO)", UnitSystem.IMPERIAL);

    /** British thermal unit (International Table). */
    public static final EnergyUnit BTU_IT =
            JOULE.deriveLinear(1.05505585262E3, "BTU(IT)", "British thermal unit (International Table)", UnitSystem.IMPERIAL);

    /** calorie (International Table). */
    public static final EnergyUnit CALORIE_IT =
            JOULE.deriveLinear(4.1868, "cal(IT)", "calorie (International Table)", UnitSystem.IMPERIAL);

    /** calorie. */
    public static final EnergyUnit CALORIE = JOULE.deriveLinear(4.184, "cal", "calorie", UnitSystem.OTHER);

    /** kilocalorie. */
    public static final EnergyUnit KILOCALORIE = CALORIE.deriveLinear(1000.0, "kcal", "kilocalorie");

    /** watt-hour. */
    public static final EnergyUnit WATT_HOUR = new EnergyUnit().build(new Unit.Builder<EnergyUnit>().setQuantity(BASE)
            .setId("Wh").setName("watt-hour").setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0)
            .setScale(new LinearScale(3600.0)));

    /** microwatt-hour. */
    public static final EnergyUnit MICROWATT_HOUR = WATT_HOUR.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** milliwatt-hour. */
    public static final EnergyUnit MILLIWATT_HOUR = WATT_HOUR.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** kilowatt-hour. */
    public static final EnergyUnit KILOWATT_HOUR = WATT_HOUR.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** megawatt-hour. */
    public static final EnergyUnit MEGAWATT_HOUR = WATT_HOUR.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** gigawatt-hour. */
    public static final EnergyUnit GIGAWATT_HOUR = WATT_HOUR.deriveSI(SIPrefixes.getUnit("G"), 1.0);

    /** terawatt-hour. */
    public static final EnergyUnit TERAWATT_HOUR = WATT_HOUR.deriveSI(SIPrefixes.getUnit("T"), 1.0);

    /** petawatt-hour. */
    public static final EnergyUnit PETAWATT_HOUR = WATT_HOUR.deriveSI(SIPrefixes.getUnit("P"), 1.0);

    /** electronvolt. */
    public static final EnergyUnit ELECTRONVOLT = new EnergyUnit().build(new Unit.Builder<EnergyUnit>().setQuantity(BASE)
            .setId("eV").setName("electronvolt").setUnitSystem(UnitSystem.SI_ACCEPTED).setSiPrefixes(SIPrefixes.UNIT, 1.0)
            .setScale(new LinearScale(1.602176634E-19)));

    /** micro-electronvolt. */
    public static final EnergyUnit MICROELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** milli-electronvolt. */
    public static final EnergyUnit MILLIELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** kilo-electronvolt. */
    public static final EnergyUnit KILOELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** mega-electronvolt. */
    public static final EnergyUnit MEGAELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** giga-electronvolt. */
    public static final EnergyUnit GIGAELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("G"), 1.0);

    /** sthene-meter (mts). */
    public static final EnergyUnit STHENE_METER = JOULE.deriveLinear(1000.0, "sn.m", "sthene meter", UnitSystem.MTS);

    /** erg (cgs). */
    public static final EnergyUnit ERG = JOULE.deriveLinear(1.0E-7, "erg", "erg", UnitSystem.CGS);

    /**
     * Determine the conversion factor to the base energy unit, given a force unit and a length unit.
     * @param force ForceUnit; the used force unit, e.g. lbf
     * @param distance LengthUnit; the used length unit, e.g. ft
     * @return double; the conversion factor from the provided units (e.g. ft.lbf) to the standard unit (e.g., J)
     */
    private static double factorFL(final ForceUnit force, final LengthUnit distance)
    {
        return force.getScale().toStandardUnit(1.0) * distance.getScale().toStandardUnit(1.0);
    }

}
