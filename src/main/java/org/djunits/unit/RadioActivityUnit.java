package org.djunits.unit;

import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;

/**
 * The units of radio activity (decays per unit of time).
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * version May 15, 2014 <br>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class RadioActivityUnit extends Unit<RadioActivityUnit>
{

    /** */
    private static final long serialVersionUID = 20190830;

    /** The base, with "/s" as the SI signature. */
    public static final Quantity<RadioActivityUnit> BASE = new Quantity<>("RadioActivity", "/s");

    /** The SI unit for radio activity is Becquerel. */
    public static final RadioActivityUnit SI = new RadioActivityUnit().build(new Unit.Builder<RadioActivityUnit>()
            .setQuantity(BASE).setId("Bq").setName("becquerel").setUnitSystem(UnitSystem.SI_DERIVED)
            .setSiPrefixes(SIPrefixes.UNIT_POS, 1.0).setScale(IdentityScale.SCALE));

    /** Becquerel. */
    public static final RadioActivityUnit BECQUEREL = SI;

    /** kBq. */
    public static final RadioActivityUnit KILOBECQUEREL = BECQUEREL.deriveLinear(1.0E3, "kBq", "kilobequerel");

    /** MBq. */
    public static final RadioActivityUnit MEGABECQUEREL = BECQUEREL.deriveLinear(1.0E6, "MBq", "megabequerel");

    /** GBq. */
    public static final RadioActivityUnit GIGABECQUEREL = BECQUEREL.deriveLinear(1.0E9, "GBq", "gigabequerel");

    /** TBq. */
    public static final RadioActivityUnit TERABECQUEREL = BECQUEREL.deriveLinear(1.0E12, "TBq", "terabequerel");

    /** PBq. */
    public static final RadioActivityUnit PETABECQUEREL = BECQUEREL.deriveLinear(1.0E15, "PBq", "petabequerel");

    /** Curie. */
    public static final RadioActivityUnit CURIE = BECQUEREL.deriveLinear(3.7E10, "Ci", "curie", UnitSystem.OTHER);

    /** milliCurie. */
    public static final RadioActivityUnit MILLICURIE = CURIE.deriveLinear(1.0E-3, "mCi", "millicurie");

    /** microCurie. */
    public static final RadioActivityUnit MICROCURIE =
            CURIE.deriveLinear(1.0E-6, "muCi", "microcurie", UnitSystem.OTHER, "muCi", "muCi", "\u03BCCi");

    /** nanoCurie. */
    public static final RadioActivityUnit NANOCURIE = CURIE.deriveLinear(1.0E-9, "nCi", "nanocurie");

    /** Rutherford. */
    public static final RadioActivityUnit RUTHERFORD = BECQUEREL.deriveLinear(1.0E6, "Rd", "rutherford", UnitSystem.OTHER);
}
