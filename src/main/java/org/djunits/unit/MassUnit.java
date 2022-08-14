package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * Standard mass units. Several conversion factors have been taken from
 * <a href="http://en.wikipedia.org/wiki/Conversion_of_units">http://en.wikipedia.org/wiki/Conversion_of_units</a>.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class MassUnit extends Unit<MassUnit>
{
    /** */
    private static final long serialVersionUID = 20140607L;

    /** The base, with "kg" as the SI signature. */
    public static final Quantity<MassUnit> BASE = new Quantity<>("Mass", "kg");

    /** The SI unit for mass is kilogram. */
    public static final MassUnit SI =
            new MassUnit().build(new Unit.Builder<MassUnit>().setQuantity(BASE).setId("kg").setName("kilogram")
                    .setUnitSystem(UnitSystem.SI_DERIVED).setSiPrefixes(SIPrefixes.KILO, 1.0).setScale(IdentityScale.SCALE));

    /** kilogram. */
    public static final MassUnit KILOGRAM = SI;

    /** gram. */
    public static final MassUnit GRAM = KILOGRAM.deriveLinear(1.0E-3, "g", "gram", UnitSystem.SI_BASE);

    /** microgram. */
    public static final MassUnit MICROGRAM = GRAM.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** milligram. */
    public static final MassUnit MILLIGRAM = GRAM.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** pound. */
    public static final MassUnit POUND = KILOGRAM.deriveLinear(0.45359237, "lb", "pound", UnitSystem.IMPERIAL);

    /** pound. */
    public static final MassUnit OUNCE = POUND.deriveLinear(1.0 / 16.0, "oz", "ounce");

    /** long ton = 2240 lb. */
    public static final MassUnit TON_LONG = POUND.deriveLinear(2240.0, "long tn", "long ton");

    /** short ton = 2000 lb. */
    public static final MassUnit TON_SHORT = POUND.deriveLinear(2000.0, "sh tn", "short ton", UnitSystem.US_CUSTOMARY);

    /** metric ton = 1000 kg. */
    public static final MassUnit TON_METRIC = KILOGRAM.deriveLinear(1000.0, "t", "metric tonne", UnitSystem.SI_ACCEPTED);

    /** metric ton = 1000 kg. */
    public static final MassUnit TONNE = KILOGRAM.deriveLinear(1000.0, "t(mts)", "tonne", UnitSystem.MTS);

    /** dalton. */
    public static final MassUnit DALTON = KILOGRAM.deriveLinear(1.6605388628E-27, "Da", "Dalton", UnitSystem.SI_ACCEPTED);

    /** electronvolt = 1.782661907E-36 kg. See http://physics.nist.gov/cuu/Constants/Table/allascii.txt. */
    public static final MassUnit ELECTRONVOLT = new MassUnit().build(
            new Unit.Builder<MassUnit>().setQuantity(BASE).setId("eV").setName("electronvolt").setUnitSystem(UnitSystem.OTHER)
                    .setSiPrefixes(SIPrefixes.UNIT_POS, 1.0).setScale(new LinearScale(1.782661907E-36)));

    /** microelectronvolt. */
    public static final MassUnit MICROELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("mu"), 1.0);

    /** millielectronvolt. */
    public static final MassUnit MILLIELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("m"), 1.0);

    /** kiloelectronvolt. */
    public static final MassUnit KILOELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("k"), 1.0);

    /** megaelectronvolt. */
    public static final MassUnit MEGAELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("M"), 1.0);

    /** gigaelectronvolt. */
    public static final MassUnit GIGAELECTRONVOLT = ELECTRONVOLT.deriveSI(SIPrefixes.getUnit("G"), 1.0);

}
