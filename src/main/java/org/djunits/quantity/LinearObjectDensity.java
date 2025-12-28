package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Linear object density counts the number of objects per unit of length, measured in number per meter (/m).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LinearObjectDensity extends Quantity.Relative<LinearObjectDensity, LinearObjectDensity.Unit>
{
    /** Constant with value zero. */
    public static final LinearObjectDensity ZERO = LinearObjectDensity.ofSi(0.0);

    /** Constant with value one. */
    public static final LinearObjectDensity ONE = LinearObjectDensity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LinearObjectDensity NaN = LinearObjectDensity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LinearObjectDensity POSITIVE_INFINITY = LinearObjectDensity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LinearObjectDensity NEGATIVE_INFINITY = LinearObjectDensity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final LinearObjectDensity POS_MAXVALUE = LinearObjectDensity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final LinearObjectDensity NEG_MAXVALUE = LinearObjectDensity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a LinearObjectDensity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public LinearObjectDensity(final double value, final LinearObjectDensity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a LinearObjectDensity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public LinearObjectDensity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(LinearObjectDensity.Unit.class, abbreviation));
    }

    /**
     * Construct LinearObjectDensity quantity.
     * @param value Scalar from which to construct this instance
     */
    public LinearObjectDensity(final LinearObjectDensity value)
    {
        super(value.si(), LinearObjectDensity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a LinearObjectDensity instance based on an SI value.
     * @param si the si value
     * @return the LinearObjectDensity instance based on an SI value
     */
    public static LinearObjectDensity ofSi(final double si)
    {
        return new LinearObjectDensity(si, LinearObjectDensity.Unit.SI);
    }

    @Override
    public LinearObjectDensity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return LinearObjectDensity.Unit.SI_UNIT;
    }

    /**
     * Returns a LinearObjectDensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LinearObjectDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LinearObjectDensity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a LinearObjectDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LinearObjectDensity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of LinearObjectDensity and LinearObjectDensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of LinearObjectDensity and LinearObjectDensity
     */
    public final Dimensionless divide(final LinearObjectDensity v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of LinearObjectDensity and Length, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearObjectDensity and Length
     */
    public final Dimensionless times(final Length v)
    {
        return new Dimensionless(this.si() * v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of LinearObjectDensity and Area, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearObjectDensity and Area
     */
    public final Length times(final Area v)
    {
        return new Length(this.si() * v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of LinearObjectDensity and Energy, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearObjectDensity and Energy
     */
    public final Force times(final Energy v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of LinearObjectDensity and Speed, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearObjectDensity and Speed
     */
    public final Frequency times(final Speed v)
    {
        return new Frequency(this.si() * v.si(), Frequency.Unit.SI);
    }

    @Override
    public Length reciprocal()
    {
        return Length.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * LinearObjectDensity.Unit encodes the unit for the number of objects per unit of length.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<LinearObjectDensity.Unit>
    {
        /** The dimensions of the number of objects per unit of length: per meter (/m). */
        public static final SIUnit SI_UNIT = SIUnit.of("/m");

        /** per meter. */
        public static final LinearObjectDensity.Unit PER_METER =
                new LinearObjectDensity.Unit("/m", "per meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final LinearObjectDensity.Unit SI = PER_METER.generateSiPrefixes(false, true);

        /** per millimeter. */
        public static final LinearObjectDensity.Unit PER_MILLIMETER = Units.resolve(LinearObjectDensity.Unit.class, "/mm");

        /** per centimeter. */
        public static final LinearObjectDensity.Unit PER_CENTIMETER = Units.resolve(LinearObjectDensity.Unit.class, "/cm");

        /** per decimeter. */
        public static final LinearObjectDensity.Unit PER_DECIMETER = Units.resolve(LinearObjectDensity.Unit.class, "/dm");

        /** per decameter. */
        public static final LinearObjectDensity.Unit PER_DECAMETER = Units.resolve(LinearObjectDensity.Unit.class, "/dam");

        /** per hectometer. */
        public static final LinearObjectDensity.Unit PER_HECTOMETER = Units.resolve(LinearObjectDensity.Unit.class, "/hm");

        /** per kilometer. */
        public static final LinearObjectDensity.Unit PER_KILOIMETER = Units.resolve(LinearObjectDensity.Unit.class, "/km");

        /** per inch. */
        public static final LinearObjectDensity.Unit PER_INCH =
                new LinearObjectDensity.Unit("/in", "per inch", 1.0 / Length.Unit.CONST_IN, UnitSystem.IMPERIAL);

        /** per foot. */
        public static final LinearObjectDensity.Unit PER_FOOT =
                new LinearObjectDensity.Unit("/ft", "per foot", 1.0 / Length.Unit.CONST_FT, UnitSystem.IMPERIAL);

        /** per yard. */
        public static final LinearObjectDensity.Unit PER_YARD =
                new LinearObjectDensity.Unit("/yd", "per yard", 1.0 / Length.Unit.CONST_YD, UnitSystem.IMPERIAL);

        /** per mile. */
        public static final LinearObjectDensity.Unit PER_MILE =
                new LinearObjectDensity.Unit("/mi", "per mile", 1.0 / Length.Unit.CONST_MI, UnitSystem.IMPERIAL);

        /**
         * Create a new LinearObjectDensity unit.
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
         * @param textualAbbreviations the textual abbreviations of the unit, where the first one in the list is the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            super(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
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
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new LinearObjectDensity.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
