package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Luminous flux is the total perceived power of light emitted by a source, measured in lumens (lm).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LuminousFlux extends Quantity.Relative<LuminousFlux, LuminousFlux.Unit>
{
    /** Constant with value zero. */
    public static final LuminousFlux ZERO = LuminousFlux.ofSi(0.0);

    /** Constant with value one. */
    public static final LuminousFlux ONE = LuminousFlux.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LuminousFlux NaN = LuminousFlux.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LuminousFlux POSITIVE_INFINITY = LuminousFlux.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LuminousFlux NEGATIVE_INFINITY = LuminousFlux.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final LuminousFlux POS_MAXVALUE = LuminousFlux.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final LuminousFlux NEG_MAXVALUE = LuminousFlux.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a LuminousFlux quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public LuminousFlux(final double value, final LuminousFlux.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a LuminousFlux quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public LuminousFlux(final double value, final String abbreviation)
    {
        this(value, Units.resolve(LuminousFlux.Unit.class, abbreviation));
    }

    /**
     * Construct LuminousFlux quantity.
     * @param value Scalar from which to construct this instance
     */
    public LuminousFlux(final LuminousFlux value)
    {
        super(value.si(), LuminousFlux.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a LuminousFlux instance based on an SI value.
     * @param si the si value
     * @return the LuminousFlux instance based on an SI value
     */
    public static LuminousFlux ofSi(final double si)
    {
        return new LuminousFlux(si, LuminousFlux.Unit.SI);
    }

    @Override
    public LuminousFlux instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return LuminousFlux.Unit.SI_UNIT;
    }

    /**
     * Returns a LuminousFlux representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LuminousFlux
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LuminousFlux valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a LuminousFlux based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LuminousFlux of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of LuminousFlux and LuminousFlux, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of LuminousFlux and LuminousFlux
     */
    public final Dimensionless divide(final LuminousFlux v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the division of LuminousFlux and Area, which results in a Illuminance scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and Area
     */
    public final Illuminance divide(final Area v)
    {
        return new Illuminance(this.si() / v.si(), Illuminance.Unit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and Illuminance, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and Illuminance
     */
    public final Area divide(final Illuminance v)
    {
        return new Area(this.si() / v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and LuminousIntensity, which results in a SolidAngle scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and LuminousIntensity
     */
    public final SolidAngle divide(final LuminousIntensity v)
    {
        return new SolidAngle(this.si() / v.si(), SolidAngle.Unit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and SolidAngle, which results in a LuminousIntensity scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and SolidAngle
     */
    public final LuminousIntensity divide(final SolidAngle v)
    {
        return new LuminousIntensity(this.si() / v.si(), LuminousIntensity.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * LuminousFlux.Unit encodes the units of total perceived power of light emitted by a source.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<LuminousFlux.Unit>
    {
        /** The dimensions of luminous flux: cd.sr. */
        public static final SIUnit SI_UNIT = SIUnit.of("cdsr");

        /** Lumen. */
        public static final LuminousFlux.Unit LUMEN = new LuminousFlux.Unit("lm", "lumen", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final LuminousFlux.Unit SI = LUMEN;

        /**
         * Create a new LuminousFlux unit.
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
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
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
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new LuminousFlux.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
