package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Areal object density counts the number of objects per unit of area, measured in number per square meter (/m2).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ArealObjectDensity extends Quantity.Relative<ArealObjectDensity, ArealObjectDensity.Unit>
{
    /** Constant with value zero. */
    public static final ArealObjectDensity ZERO = ArealObjectDensity.ofSi(0.0);

    /** Constant with value one. */
    public static final ArealObjectDensity ONE = ArealObjectDensity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ArealObjectDensity NaN = ArealObjectDensity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ArealObjectDensity POSITIVE_INFINITY = ArealObjectDensity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ArealObjectDensity NEGATIVE_INFINITY = ArealObjectDensity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ArealObjectDensity POS_MAXVALUE = ArealObjectDensity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ArealObjectDensity NEG_MAXVALUE = ArealObjectDensity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ArealObjectDensity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public ArealObjectDensity(final double value, final ArealObjectDensity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a ArealObjectDensity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public ArealObjectDensity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(ArealObjectDensity.Unit.class, abbreviation));
    }

    /**
     * Construct ArealObjectDensity quantity.
     * @param value Scalar from which to construct this instance
     */
    public ArealObjectDensity(final ArealObjectDensity value)
    {
        super(value.si(), ArealObjectDensity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a ArealObjectDensity instance based on an SI value.
     * @param si the si value
     * @return the ArealObjectDensity instance based on an SI value
     */
    public static ArealObjectDensity ofSi(final double si)
    {
        return new ArealObjectDensity(si, ArealObjectDensity.Unit.SI);
    }

    @Override
    public ArealObjectDensity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return ArealObjectDensity.Unit.SI_UNIT;
    }

    /**
     * Returns a ArealObjectDensity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ArealObjectDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ArealObjectDensity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a ArealObjectDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ArealObjectDensity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Divides this areal object density by another areal object density to yield a dimensionless ratio.
     * <p>
     * Formula: (1/m²) / (1/m²) = 1.
     * </p>
     * @param other the areal object density divisor; must not be {@code null}.
     * @return the resulting dimensionless ratio in SI (1).
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    public final Dimensionless divide(final ArealObjectDensity other)
    {
        return new Dimensionless(this.si() / other.si(), Unitless.BASE);
    }

    /**
     * Multiplies this areal object density by an area to yield a dimensionless count.
     * <p>
     * Formula: (1/m²) × m² = 1.
     * </p>
     * @param area the area multiplier; must not be {@code null}.
     * @return the resulting dimensionless count in SI (1).
     * @throws NullPointerException if {@code area} is {@code null}.
     */
    public final Dimensionless times(final Area area)
    {
        return new Dimensionless(this.si() * area.si(), Unitless.BASE);
    }

    /**
     * Multiplies this areal object density by a length to yield a linear object density.
     * <p>
     * Formula: (1/m²) × m = 1/m.
     * </p>
     * @param length the length multiplier; must not be {@code null}.
     * @return the resulting linear object density in SI (1/m).
     * @throws NullPointerException if {@code length} is {@code null}.
     */
    public final LinearObjectDensity times(final Length length)
    {
        return new LinearObjectDensity(this.si() * length.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Divides this areal object density by a length to yield a volumetric object density.
     * <p>
     * Formula: (1/m²) / m = 1/m³.
     * </p>
     * @param length the length divisor; must not be {@code null}.
     * @return the resulting volumetric object density in SI (1/m³).
     * @throws NullPointerException if {@code length} is {@code null}.
     */
    public final VolumetricObjectDensity divide(final Length length)
    {
        return new VolumetricObjectDensity(this.si() / length.si(), VolumetricObjectDensity.Unit.SI);
    }

    /**
     * Divides this areal object density by a volumetric object density to yield a length.
     * <p>
     * Formula: (1/m²) / (1/m³) = m.
     * </p>
     * @param vod the volumetric object density divisor; must not be {@code null}.
     * @return the resulting length in SI (m).
     * @throws NullPointerException if {@code vod} is {@code null}.
     */
    public final Length divide(final VolumetricObjectDensity vod)
    {
        return new Length(this.si() / vod.si(), Length.Unit.SI);
    }

    @Override
    public Area reciprocal()
    {
        return Area.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ArealObjectDensity.Unit encodes the unit for number of objects per unit of area.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<ArealObjectDensity.Unit, ArealObjectDensity>
    {
        /** The dimensions of the number of objects per unit of area: per square meter (/m2). */
        public static final SIUnit SI_UNIT = SIUnit.of("/m2");

        /** per meter. */
        public static final ArealObjectDensity.Unit PER_SQUARE_METER =
                new ArealObjectDensity.Unit("/m2", "per square meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final ArealObjectDensity.Unit SI = PER_SQUARE_METER;

        /**
         * Create a new ArealObjectDensity unit.
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
        public ArealObjectDensity ofSi(final double si)
        {
            return ArealObjectDensity.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ArealObjectDensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
