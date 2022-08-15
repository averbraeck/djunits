package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of pressure.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class PressureUnit extends Unit<PressureUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "kg/ms2" as the SI signature. */
    public static final Quantity<PressureUnit> BASE = new Quantity<>("Pressure", "kg/ms2");

    /** The SI unit for pressure is Pascal = kgm/s2. */
    public static final PressureUnit SI =
            new PressureUnit().build(new Unit.Builder<PressureUnit>().setQuantity(BASE).setId("Pa").setName("pascal")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.UNIT, 1.0).setScale(IdentityScale.SCALE));

    /** Pascal. */
    public static final PressureUnit PASCAL = SI;

    /** hectoPascal. */
    public static final PressureUnit HECTOPASCAL = PASCAL.deriveLinear(100.0, "hPa", "hectopascal");

    /** kiloPascal. */
    public static final PressureUnit KILOPASCAL = PASCAL.deriveLinear(1000.0, "kPa", "kilopascal");

    /** standard atmosphere. */
    public static final PressureUnit ATMOSPHERE_STANDARD =
            PASCAL.deriveLinear(101325.0, "atm", "atmosphere (standard)", UnitSystem.OTHER);

    /** torr. */
    public static final PressureUnit TORR = ATMOSPHERE_STANDARD.deriveLinear(1.0 / 760.0, "torr", "Torr");

    /** technical atmosphere. */
    public static final PressureUnit ATMOSPHERE_TECHNICAL = SI.deriveLinear(
            factorFA(ForceUnit.KILOGRAM_FORCE, AreaUnit.SQUARE_CENTIMETER), "at", "atmosphere (technical)", UnitSystem.OTHER);

    /** barye. */
    public static final PressureUnit BARYE =
            SI.deriveLinear(factorFA(ForceUnit.DYNE, AreaUnit.SQUARE_CENTIMETER), "Ba", "barye", UnitSystem.CGS);

    /** bar. */
    public static final PressureUnit BAR = SI.deriveLinear(1.0E5, "bar", "bar", UnitSystem.OTHER);

    /** millibar. */
    public static final PressureUnit MILLIBAR = BAR.deriveLinear(1.0E-3, "mbar", "millibar");

    /** cm Hg. */
    public static final PressureUnit CENTIMETER_MERCURY =
            PASCAL.deriveLinear(1333.224, "cmHg", "centimeter mercury", UnitSystem.OTHER);

    /** mm Hg. */
    public static final PressureUnit MILLIMETER_MERCURY =
            PASCAL.deriveLinear(133.3224, "mmHg", "millimeter mercury", UnitSystem.OTHER);

    /** foot Hg. */
    public static final PressureUnit FOOT_MERCURY =
            PASCAL.deriveLinear(40.63666E3, "ftHg", "foot mercury", UnitSystem.IMPERIAL);

    /** inch Hg. */
    public static final PressureUnit INCH_MERCURY =
            PASCAL.deriveLinear(3.386389E3, "inHg", "inch mercury", UnitSystem.IMPERIAL);

    /** kilogram-force per square millimeter. */
    public static final PressureUnit KGF_PER_SQUARE_MM =
            SI.deriveLinear(factorFA(ForceUnit.KILOGRAM_FORCE, AreaUnit.SQUARE_MILLIMETER), "kgf/mm^2",
                    "kilogram-force per square millimeter", UnitSystem.OTHER);

    /** pound per square foot. */
    public static final PressureUnit POUND_PER_SQUARE_FOOT =
            SI.deriveLinear(factorFA(ForceUnit.POUND_FORCE, AreaUnit.SQUARE_FOOT), "lbf/ft^2", "pound-force per square foot",
                    UnitSystem.IMPERIAL);

    /** pound per square inch. */
    public static final PressureUnit POUND_PER_SQUARE_INCH =
            SI.deriveLinear(factorFA(ForceUnit.POUND_FORCE, AreaUnit.SQUARE_INCH), "lbf/in^2", "pound-force per square inch",
                    UnitSystem.IMPERIAL);

    /** pieze. */
    public static final PressureUnit PIEZE =
            SI.deriveLinear(factorFA(ForceUnit.STHENE, AreaUnit.SQUARE_METER), "pz", "pi\u00E8ze", UnitSystem.MTS);

    /**
     * Determine the conversion factor to the base pressure unit, given a force unit and an area unit.
     * @param force ForceUnit; the used force unit, e.g. kgf
     * @param area AreaUnit; the used area unit, e.g. mm2
     * @return double; the conversion factor from the provided units (e.g. kgf/mm2) to the standard unit (Pa)
     */
    private static double factorFA(final ForceUnit force, final AreaUnit area)
    {
        return force.getScale().toStandardUnit(1.0) / area.getScale().toStandardUnit(1.0);
    }

}
