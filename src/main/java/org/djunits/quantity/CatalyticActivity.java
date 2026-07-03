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
 * Catalytic Activty is the rate of catalytic reaction per unit amount of catalyst, and is expressed in katal (kat), which is
 * mol/s.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class CatalyticActivity extends Quantity<CatalyticActivity>
{
    /** Constant with value zero. */
    public static final CatalyticActivity ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final CatalyticActivity ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final CatalyticActivity NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final CatalyticActivity POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final CatalyticActivity NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final CatalyticActivity POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final CatalyticActivity NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a CatalyticActivity quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public CatalyticActivity(final double value, final CatalyticActivity.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a CatalyticActivity quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public CatalyticActivity(final double valueInUnit, final CatalyticActivity.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a CatalyticActivity instance based on an SI value.
     * @param si the si value
     * @return the CatalyticActivity instance based on an SI value
     */
    public static CatalyticActivity ofSi(final double si)
    {
        return new CatalyticActivity(si, CatalyticActivity.Unit.SI, true);
    }

    /**
     * Instantiate a CatalyticActivity quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the CatalyticActivity instance based on an SI value with the given display unit
     */
    public static CatalyticActivity ofSi(final double siValue, final CatalyticActivity.Unit displayUnit)
    {
        return new CatalyticActivity(siValue, displayUnit, true);
    }

    @Override
    public CatalyticActivity instantiateSi(final double siValue, final UnitInterface<CatalyticActivity> displayUnit)
    {
        return new CatalyticActivity(siValue, (CatalyticActivity.Unit) displayUnit, true);
    }

    /**
     * Returns a CatalyticActivity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a CatalyticActivity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static CatalyticActivity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a CatalyticActivity based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab CatalyticActivity representation of the value in its unit
     */
    public static CatalyticActivity of(final double valueInUnit, final CatalyticActivity.Unit unit)
    {
        return new CatalyticActivity(valueInUnit, unit);
    }

    /**
     * Returns a CatalyticActivity based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static CatalyticActivity of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public CatalyticActivity.Unit getDisplayUnit()
    {
        return (CatalyticActivity.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of CatalyticActivity and CatalyticActivity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of CatalyticActivity and CatalyticActivity
     */
    public final Dimensionless divide(final CatalyticActivity v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of CatalyticActivity and Duration, which results in a AmountOfSubstance scalar.
     * @param v scalar
     * @return scalar as a multiplication of CatalyticActivity and Duration
     */
    public final AmountOfSubstance multiply(final Duration v)
    {
        return new AmountOfSubstance(this.si() * v.si(), AmountOfSubstance.Unit.SI);
    }

    /**
     * Calculate the division of CatalyticActivity and AmountOfSubstance, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of CatalyticActivity and AmountOfSubstance
     */
    public final Frequency divide(final AmountOfSubstance v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the division of CatalyticActivity and Frequency, which results in a AmountOfSubstance scalar.
     * @param v scalar
     * @return scalar as a division of CatalyticActivity and Frequency
     */
    public final AmountOfSubstance divide(final Frequency v)
    {
        return new AmountOfSubstance(this.si() / v.si(), AmountOfSubstance.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * CatalyticActivity.Unit encodes the units of the speed of a chamical reaction, and is expressed in mol/s.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<CatalyticActivity>
    {
        /** The dimensions of catalytic activity: mol/s [rad, sr, kg, m, s, A, K, mol, cd]. */
        public static final SIUnit SI_UNIT = SIUnit.of("mol/s");

        /** katal. */
        public static final CatalyticActivity.Unit kat = new CatalyticActivity.Unit("kat", "kat", "katal", IdentityScale.SCALE,
                UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final CatalyticActivity.Unit SI = (Unit) kat.generateSiPrefixes(false, false);

        /** mkat. */
        public static final CatalyticActivity.Unit mkat = Units.resolve(CatalyticActivity.Unit.class, "mkat");

        /** &#181;kat. */
        public static final CatalyticActivity.Unit mukat = Units.resolve(CatalyticActivity.Unit.class, "mukat");

        /** nkat. */
        public static final CatalyticActivity.Unit nkat = Units.resolve(CatalyticActivity.Unit.class, "nkat");

        /**
         * Create a new CatalyticActivity unit.
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
        public CatalyticActivity ofSi(final double si, final UnitInterface<CatalyticActivity> displayUnit)
        {
            return new CatalyticActivity(si, (Unit) displayUnit, true);
        }

        @Override
        public CatalyticActivity.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

        @Override
        public CatalyticActivity.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new CatalyticActivity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
