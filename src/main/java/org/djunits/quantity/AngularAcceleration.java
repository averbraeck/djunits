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
 * Angular acceleration is the rate of change of angular velocity over time, measured in radians per second squared
 * (rad/s2).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AngularAcceleration extends Quantity.Relative<AngularAcceleration, AngularAcceleration.Unit>
{
    /** Constant with value zero. */
    public static final AngularAcceleration ZERO = AngularAcceleration.ofSi(0.0);

    /** Constant with value one. */
    public static final AngularAcceleration ONE = AngularAcceleration.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AngularAcceleration NaN = AngularAcceleration.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AngularAcceleration POSITIVE_INFINITY = AngularAcceleration.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AngularAcceleration NEGATIVE_INFINITY = AngularAcceleration.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final AngularAcceleration POS_MAXVALUE = AngularAcceleration.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final AngularAcceleration NEG_MAXVALUE = AngularAcceleration.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a AngularAcceleration quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public AngularAcceleration(final double value, final AngularAcceleration.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a AngularAcceleration quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public AngularAcceleration(final double value, final String abbreviation)
    {
        this(value, Units.resolve(AngularAcceleration.Unit.class, abbreviation));
    }

    /**
     * Construct AngularAcceleration quantity.
     * @param value Scalar from which to construct this instance
     */
    public AngularAcceleration(final AngularAcceleration value)
    {
        super(value.si(), AngularAcceleration.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a AngularAcceleration instance based on an SI value.
     * @param si the si value
     * @return the AngularAcceleration instance based on an SI value
     */
    public static AngularAcceleration ofSi(final double si)
    {
        return new AngularAcceleration(si, AngularAcceleration.Unit.SI);
    }

    @Override
    public AngularAcceleration instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return AngularAcceleration.Unit.SI_UNIT;
    }

    /**
     * Returns a AngularAcceleration representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AngularAcceleration
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AngularAcceleration valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a AngularAcceleration based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AngularAcceleration of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of AngularAcceleration and AngularAcceleration, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and AngularAcceleration
     */
    public final Dimensionless divide(final AngularAcceleration v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of AngularAcceleration and Duration, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of AngularAcceleration and Duration
     */
    public final AngularVelocity multiply(final Duration v)
    {
        return new AngularVelocity(this.si() * v.si(), AngularVelocity.Unit.SI);
    }

    /**
     * Calculate the division of AngularAcceleration and Frequency, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and Frequency
     */
    public final AngularVelocity divide(final Frequency v)
    {
        return new AngularVelocity(this.si() / v.si(), AngularVelocity.Unit.SI);
    }

    /**
     * Calculate the division of AngularAcceleration and AngularVelocity, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and AngularVelocity
     */
    public final Frequency divide(final AngularVelocity v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * AngularAcceleration.Unit encodes the units of angle (radians or degrees per second squared).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<AngularAcceleration.Unit, AngularAcceleration>
    {
        /** The dimensions of AngularAcceleration: rad/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("rad/s2");

        /** radian per second squared. */
        public static final AngularAcceleration.Unit RADIAN_PER_SECOND_SQUARED =
                new AngularAcceleration.Unit("rad/s2", "radians per second squared", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final AngularAcceleration.Unit SI = RADIAN_PER_SECOND_SQUARED;

        /** degree per second squared. */
        public static final AngularAcceleration.Unit DEGREE_PER_SECOND_SQUARED = RADIAN_PER_SECOND_SQUARED.deriveUnit("deg/s2",
                "\u00b0/s2", "degree per second squared", Math.PI / 180.0, UnitSystem.SI_ACCEPTED);

        /** arcminute per second squared. */
        public static final AngularAcceleration.Unit ARCMINUTE_PER_SECOND_SQUARED = DEGREE_PER_SECOND_SQUARED
                .deriveUnit("arcmin/s2", "'/s2", "arcminute per second squared", 1.0 / 60.0, UnitSystem.OTHER);

        /** arcsecond per second squared. */
        public static final AngularAcceleration.Unit ARCSECOND_PER_SECOND_SQUARED = DEGREE_PER_SECOND_SQUARED
                .deriveUnit("arcsec/s2", "\"/s2", "arcsecond per second squared", 1.0 / 3600.0, UnitSystem.OTHER);

        /** grad per second squared. */
        public static final AngularAcceleration.Unit GRAD_PER_SECOND_SQUARED = RADIAN_PER_SECOND_SQUARED.deriveUnit("grad/s2",
                "gradian per second squared", 2.0 * Math.PI / 400.0, UnitSystem.OTHER);

        /** centesimal arcminute per second squared. */
        public static final AngularAcceleration.Unit CENTESIMAL_ARCMINUTE_PER_SECOND_SQUARED = GRAD_PER_SECOND_SQUARED
                .deriveUnit("cdm/s2", "c'/s2", "centesimal arcminute per second squared", 1.0 / 100.0, UnitSystem.OTHER);

        /** centesimal arcsecond per second squared. */
        public static final AngularAcceleration.Unit CENTESIMAL_ARCSECOND_PER_SECOND_SQUARED = GRAD_PER_SECOND_SQUARED
                .deriveUnit("cds/s2", "c\"/s2", "centesimal arcsecond per second squared", 1.0 / 10000.0, UnitSystem.OTHER);

        /**
         * Create a new AngularAcceleration unit.
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
        public AngularAcceleration ofSi(final double si)
        {
            return AngularAcceleration.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new AngularAcceleration.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
