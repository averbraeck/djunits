package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Solid angle is the two-dimensional angle in three-dimensional space, measured in steradians (sr).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class SolidAngle extends Quantity.Relative<SolidAngle, SolidAngle.Unit>
{
    /** Constant with value zero. */
    public static final SolidAngle ZERO = SolidAngle.ofSi(0.0);

    /** Constant with value one. */
    public static final SolidAngle ONE = SolidAngle.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final SolidAngle NaN = SolidAngle.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final SolidAngle POSITIVE_INFINITY = SolidAngle.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final SolidAngle NEGATIVE_INFINITY = SolidAngle.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final SolidAngle POS_MAXVALUE = SolidAngle.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final SolidAngle NEG_MAXVALUE = SolidAngle.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a SolidAngle quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public SolidAngle(final double value, final SolidAngle.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a SolidAngle quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public SolidAngle(final double value, final String abbreviation)
    {
        this(value, Units.resolve(SolidAngle.Unit.class, abbreviation));
    }

    /**
     * Construct SolidAngle quantity.
     * @param value Scalar from which to construct this instance
     */
    public SolidAngle(final SolidAngle value)
    {
        super(value.si(), SolidAngle.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a SolidAngle instance based on an SI value.
     * @param si the si value
     * @return the SolidAngle instance based on an SI value
     */
    public static SolidAngle ofSi(final double si)
    {
        return new SolidAngle(si, SolidAngle.Unit.SI);
    }

    @Override
    public SolidAngle instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return SolidAngle.Unit.SI_UNIT;
    }

    /**
     * Returns a SolidAngle representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a SolidAngle
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static SolidAngle valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a SolidAngle based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SolidAngle of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of SolidAngle and SolidAngle, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of SolidAngle and SolidAngle
     */
    public final Dimensionless divide(final SolidAngle v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of SolidAngle and LuminousIntensity, which results in a LuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of SolidAngle and LuminousIntensity
     */
    public final LuminousFlux times(final LuminousIntensity v)
    {
        return new LuminousFlux(this.si() * v.si(), LuminousFlux.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * SolidAngle.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<SolidAngle.Unit, SolidAngle>
    {
        /** The dimensions of the solid angle: sr. */
        public static final SIUnit SI_UNIT = SIUnit.of("sr");

        /** Steradian. */
        public static final SolidAngle.Unit STERADIAN = new SolidAngle.Unit("sr", "steradian", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final SolidAngle.Unit SI = STERADIAN;

        /** square degree. */
        public static final SolidAngle.Unit SQUARE_DEGREE =
                STERADIAN.deriveUnit("sq.deg", "square degree", (Math.PI / 180.0) * (Math.PI / 180.0), UnitSystem.SI_DERIVED);

        /**
         * Create a new SolidAngle unit.
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
        public SolidAngle ofSi(final double si)
        {
            return SolidAngle.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new SolidAngle.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
