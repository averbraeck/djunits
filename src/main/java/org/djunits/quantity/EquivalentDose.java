package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Equivalent dose is a measure of radiation exposure accounting for biological effect, expressed in sieverts (Sv).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class EquivalentDose extends Quantity<EquivalentDose>
{
    /** Constant with value zero. */
    public static final EquivalentDose ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final EquivalentDose ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final EquivalentDose NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final EquivalentDose POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final EquivalentDose NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final EquivalentDose POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final EquivalentDose NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a EquivalentDose quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public EquivalentDose(final double value, final EquivalentDose.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a EquivalentDose quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public EquivalentDose(final double valueInUnit, final EquivalentDose.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a EquivalentDose instance based on an SI value.
     * @param si the si value
     * @return the EquivalentDose instance based on an SI value
     */
    public static EquivalentDose ofSi(final double si)
    {
        return new EquivalentDose(si, EquivalentDose.Unit.SI, true);
    }

    /**
     * Instantiate a EquivalentDose quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the EquivalentDose instance based on an SI value with the given display unit
     */
    public static EquivalentDose ofSi(final double siValue, final EquivalentDose.Unit displayUnit)
    {
        return new EquivalentDose(siValue, displayUnit, true);
    }

    @Override
    public EquivalentDose instantiateSi(final double siValue, final UnitInterface<EquivalentDose> displayUnit)
    {
        return new EquivalentDose(siValue, (EquivalentDose.Unit) displayUnit, true);
    }

    /**
     * Returns a EquivalentDose representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a EquivalentDose
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static EquivalentDose valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a EquivalentDose based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab EquivalentDose representation of the value in its unit
     */
    public static EquivalentDose of(final double valueInUnit, final EquivalentDose.Unit unit)
    {
        return new EquivalentDose(valueInUnit, unit);
    }

    /**
     * Returns a EquivalentDose based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static EquivalentDose of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public EquivalentDose.Unit getDisplayUnit()
    {
        return (EquivalentDose.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of EquivalentDose and EquivalentDose, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of EquivalentDose and EquivalentDose
     */
    public Dimensionless divide(final EquivalentDose v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * EquivalentDose.Unit encodes the unit of radiation exposure.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<EquivalentDose>
    {
        /** The dimensions of the equivalent dose: m2/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("m2/s2");

        /** Sievert. */
        public static final EquivalentDose.Unit Sv = new EquivalentDose.Unit("Sv", "Sv", "sievert", IdentityScale.SCALE,
                UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final EquivalentDose.Unit SI = (Unit) Sv.generateSiPrefixes(false, false);

        /** mSv. */
        public static final EquivalentDose.Unit mSv = Units.resolve(EquivalentDose.Unit.class, "mSv");

        /** &#181;Sv. */
        public static final EquivalentDose.Unit muSv = Units.resolve(EquivalentDose.Unit.class, "muSv");

        /** rem. (stands for röntgen equivalent man). */
        public static final EquivalentDose.Unit rem = Sv.deriveUnit("rem", "rem", "rem", 0.01, UnitSystem.CGS, null);

        /**
         * Create a new EquivalentDose unit.
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
        public EquivalentDose ofSi(final double si, final UnitInterface<EquivalentDose> displayUnit)
        {
            return new EquivalentDose(si, (Unit) displayUnit, true);
        }

        @Override
        public EquivalentDose.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

        @Override
        public EquivalentDose.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new EquivalentDose.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
