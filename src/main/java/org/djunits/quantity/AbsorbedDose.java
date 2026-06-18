package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Absorbed dose is the energy deposited by ionizing radiation per unit mass, measured in grays (Gy).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsorbedDose extends Quantity<AbsorbedDose>
{
    /** Constant with value zero. */
    public static final AbsorbedDose ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final AbsorbedDose ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AbsorbedDose NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AbsorbedDose POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AbsorbedDose NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final AbsorbedDose POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final AbsorbedDose NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a AbsorbedDose quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public AbsorbedDose(final double value, final AbsorbedDose.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a AbsorbedDose quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public AbsorbedDose(final double valueInUnit, final AbsorbedDose.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a AbsorbedDose instance based on an SI value.
     * @param si the si value
     * @return the AbsorbedDose instance based on an SI value
     */
    public static AbsorbedDose ofSi(final double si)
    {
        return new AbsorbedDose(si, AbsorbedDose.Unit.SI, true);
    }

    /**
     * Instantiate a AbsorbedDose quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the AbsorbedDose instance based on an SI value with the given display unit
     */
    public static AbsorbedDose ofSi(final double siValue, final AbsorbedDose.Unit displayUnit)
    {
        return new AbsorbedDose(siValue, displayUnit, true);
    }

    @Override
    public AbsorbedDose instantiateSi(final double siValue, final UnitInterface<AbsorbedDose> displayUnit)
    {
        return new AbsorbedDose(siValue, (AbsorbedDose.Unit) displayUnit, true);
    }

    /**
     * Returns a AbsorbedDose representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AbsorbedDose
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AbsorbedDose valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a AbsorbedDose based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab AbsorbedDose representation of the value in its unit
     */
    public static AbsorbedDose of(final double valueInUnit, final AbsorbedDose.Unit unit)
    {
        return new AbsorbedDose(valueInUnit, unit);
    }

    /**
     * Returns a AbsorbedDose based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AbsorbedDose of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public AbsorbedDose.Unit getDisplayUnit()
    {
        return (AbsorbedDose.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of AbsorbedDose and AbsorbedDose, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of AbsorbedDose and AbsorbedDose
     */
    public final Dimensionless divide(final AbsorbedDose v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * AbsorbedDose.Unit encodes the units of absorbed dose (of ionizing radiation).
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<AbsorbedDose>
    {
        /** The dimensions of the absorbed dose: m2/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("m2/s2");

        /** Gray. */
        public static final AbsorbedDose.Unit Gy = new AbsorbedDose.Unit("Gy", "gray", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final AbsorbedDose.Unit SI = Gy;

        /** mGy. */
        public static final AbsorbedDose.Unit mGy =
                Gy.deriveUnit("mGy", "mGy", "milligray", 1.0E-3, UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix("m"));

        /** &#181;Gy. */
        public static final AbsorbedDose.Unit muGy =
                Gy.deriveUnit("muGy", "\u03BCGy", "microgray", 1.0E-6, UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix("mu"));

        /** erg/g. */
        public static final AbsorbedDose.Unit erg_g = new AbsorbedDose.Unit("erg/g", "erg per gram", 1.0E-4, UnitSystem.CGS);

        /** rad. */
        public static final AbsorbedDose.Unit rad = new AbsorbedDose.Unit("rad", "rad", 1.0E-2, UnitSystem.CGS);

        /**
         * Create a new AbsorbedDose unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, scaleFactorToBaseUnit, unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert from this unit to the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         * @param siPrefix the SI Prefix of this unit
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem, siPrefix);
        }

        @Override
        public SIUnit siUnit()
        {
            return SI_UNIT;
        }

        @Override
        public Unit getBaseUnit()
        {
            return SI;
        }

        @Override
        public AbsorbedDose ofSi(final double si, final UnitInterface<AbsorbedDose> displayUnit)
        {
            return new AbsorbedDose(si, (Unit) displayUnit, true);
        }

        @Override
        public AbsorbedDose.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new AbsorbedDose.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
