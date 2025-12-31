package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Catalytic Activty is the rate of catalytic reaction per unit amount of catalyst, and is expressed in katal (kat), which is
 * mol/s.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class CatalyticActivity extends Quantity<CatalyticActivity, CatalyticActivity.Unit>
{
    /** Constant with value zero. */
    public static final CatalyticActivity ZERO = CatalyticActivity.ofSi(0.0);

    /** Constant with value one. */
    public static final CatalyticActivity ONE = CatalyticActivity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final CatalyticActivity NaN = CatalyticActivity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final CatalyticActivity POSITIVE_INFINITY = CatalyticActivity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final CatalyticActivity NEGATIVE_INFINITY = CatalyticActivity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final CatalyticActivity POS_MAXVALUE = CatalyticActivity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final CatalyticActivity NEG_MAXVALUE = CatalyticActivity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a CatalyticActivity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public CatalyticActivity(final double value, final CatalyticActivity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a CatalyticActivity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public CatalyticActivity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(CatalyticActivity.Unit.class, abbreviation));
    }

    /**
     * Construct CatalyticActivity quantity.
     * @param value Scalar from which to construct this instance
     */
    public CatalyticActivity(final CatalyticActivity value)
    {
        super(value.si(), CatalyticActivity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a CatalyticActivity instance based on an SI value.
     * @param si the si value
     * @return the CatalyticActivity instance based on an SI value
     */
    public static CatalyticActivity ofSi(final double si)
    {
        return new CatalyticActivity(si, CatalyticActivity.Unit.SI);
    }

    @Override
    public CatalyticActivity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return CatalyticActivity.Unit.SI_UNIT;
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
     * Returns a CatalyticActivity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static CatalyticActivity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
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
     * CatalyticActivity.Unit encodes the units of the speed of a chamical reaction, and is expressed in mol/s.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<CatalyticActivity.Unit, CatalyticActivity>
    {
        /** The dimensions of catalytic activity: mol/s [rad, sr, kg, m, s, A, K, mol, cd]. */
        public static final SIUnit SI_UNIT = SIUnit.of("mol/s");

        /** katal. */
        public static final CatalyticActivity.Unit KATAL =
                new CatalyticActivity.Unit("kat", "katal", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final CatalyticActivity.Unit SI = KATAL.generateSiPrefixes(false, false);

        /** mkat. */
        public static final CatalyticActivity.Unit MILLIKATAL = Units.resolve(CatalyticActivity.Unit.class, "mkat");

        /** &#181;kat. */
        public static final CatalyticActivity.Unit MICROKATAL = Units.resolve(CatalyticActivity.Unit.class, "mukat");

        /** nkat. */
        public static final CatalyticActivity.Unit NANOKATAL = Units.resolve(CatalyticActivity.Unit.class, "nkat");

        /**
         * Create a new CatalyticActivity unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, new LinearScale(scaleFactorToBaseUnit), unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
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

        /** {@inheritDoc} */
        @Override
        public CatalyticActivity ofSi(final double si)
        {
            return CatalyticActivity.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new CatalyticActivity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
