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
 * Luminous intensity is the luminous flux emitted per unit solid angle, measured in candelas (cd).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LuminousIntensity extends Quantity<LuminousIntensity, LuminousIntensity.Unit>
{
    /** Constant with value zero. */
    public static final LuminousIntensity ZERO = LuminousIntensity.ofSi(0.0);

    /** Constant with value one. */
    public static final LuminousIntensity ONE = LuminousIntensity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LuminousIntensity NaN = LuminousIntensity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LuminousIntensity POSITIVE_INFINITY = LuminousIntensity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LuminousIntensity NEGATIVE_INFINITY = LuminousIntensity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final LuminousIntensity POS_MAXVALUE = LuminousIntensity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final LuminousIntensity NEG_MAXVALUE = LuminousIntensity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a LuminousIntensity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public LuminousIntensity(final double value, final LuminousIntensity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a LuminousIntensity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public LuminousIntensity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(LuminousIntensity.Unit.class, abbreviation));
    }

    /**
     * Construct LuminousIntensity quantity.
     * @param value Scalar from which to construct this instance
     */
    public LuminousIntensity(final LuminousIntensity value)
    {
        super(value.si(), LuminousIntensity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a LuminousIntensity instance based on an SI value.
     * @param si the si value
     * @return the LuminousIntensity instance based on an SI value
     */
    public static LuminousIntensity ofSi(final double si)
    {
        return new LuminousIntensity(si, LuminousIntensity.Unit.SI);
    }

    @Override
    public LuminousIntensity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return LuminousIntensity.Unit.SI_UNIT;
    }

    /**
     * Returns a LuminousIntensity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LuminousIntensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LuminousIntensity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a LuminousIntensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LuminousIntensity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of LuminousIntensity and LuminousIntensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of LuminousIntensity and LuminousIntensity
     */
    public final Dimensionless divide(final LuminousIntensity v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of LuminousIntensity and SolidAngle, which results in a LuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of LuminousIntensity and SolidAngle
     */
    public final LuminousFlux multiply(final SolidAngle v)
    {
        return new LuminousFlux(this.si() * v.si(), LuminousFlux.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * LuminousIntensity.Unit encodes the units of luminous flux emitted per unit solid angle.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<LuminousIntensity.Unit, LuminousIntensity>
    {
        /** The dimensions of the luminous flux emitted per unit solid angle: cd. */
        public static final SIUnit SI_UNIT = SIUnit.of("cd");

        /** Candela. */
        public static final LuminousIntensity.Unit CANDELA =
                new LuminousIntensity.Unit("cd", "candela", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final LuminousIntensity.Unit SI = CANDELA.generateSiPrefixes(false, false);

        /**
         * Create a new LuminousIntensity unit.
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

        @Override
        public LuminousIntensity ofSi(final double si)
        {
            return LuminousIntensity.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new LuminousIntensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
