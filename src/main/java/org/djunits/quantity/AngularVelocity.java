package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Angular velocity is the rate of rotation around an axis, measured in radians per second (rad/s).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AngularVelocity extends Quantity.Relative<AngularVelocity, AngularVelocity.Unit>
{
    /** Constant with value zero. */
    public static final AngularVelocity ZERO = AngularVelocity.ofSi(0.0);

    /** Constant with value one. */
    public static final AngularVelocity ONE = AngularVelocity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AngularVelocity NaN = AngularVelocity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AngularVelocity POSITIVE_INFINITY = AngularVelocity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AngularVelocity NEGATIVE_INFINITY = AngularVelocity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final AngularVelocity POS_MAXVALUE = AngularVelocity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final AngularVelocity NEG_MAXVALUE = AngularVelocity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a AngularVelocity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public AngularVelocity(final double value, final AngularVelocity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a AngularVelocity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public AngularVelocity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(AngularVelocity.Unit.class, abbreviation));
    }

    /**
     * Construct AngularVelocity quantity.
     * @param value Scalar from which to construct this instance
     */
    public AngularVelocity(final AngularVelocity value)
    {
        super(value.si(), AngularVelocity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a AngularVelocity instance based on an SI value.
     * @param si the si value
     * @return the AngularVelocity instance based on an SI value
     */
    public static AngularVelocity ofSi(final double si)
    {
        return new AngularVelocity(si, AngularVelocity.Unit.SI);
    }

    @Override
    public AngularVelocity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return AngularVelocity.Unit.SI_UNIT;
    }

    /**
     * Returns a AngularVelocity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AngularVelocity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AngularVelocity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a AngularVelocity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AngularVelocity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of AngularVelocity and AngularVelocity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of AngularVelocity and AngularVelocity
     */
    public final Dimensionless divide(final AngularVelocity v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * AngularVelocity.Unit encodes the units of angle (radians, degrees).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<AngularVelocity.Unit>
    {
        /** The dimensions of AngularVelocity: rad/s. */
        public static final SIUnit SI_UNIT = SIUnit.of("rad/s");

        /** radian per second. */
        public static final AngularVelocity.Unit RADIAN_PER_SECOND =
                new AngularVelocity.Unit("rad/s", "radians per second", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final AngularVelocity.Unit SI = RADIAN_PER_SECOND;

        /** degree per second. */
        public static final AngularVelocity.Unit DEGREE_PER_SECOND =
                RADIAN_PER_SECOND.deriveUnit("deg/s", "\u00b0/s", "degree per second", Math.PI / 180.0, UnitSystem.SI_ACCEPTED);

        /** arcminute per second. */
        public static final AngularVelocity.Unit ARCMINUTE_PER_SECOND =
                DEGREE_PER_SECOND.deriveUnit("arcmin/s", "'/s", "arcminute per second", 1.0 / 60.0, UnitSystem.OTHER);

        /** arcsecond per second. */
        public static final AngularVelocity.Unit ARCSECOND_PER_SECOND =
                DEGREE_PER_SECOND.deriveUnit("arcsec/s", "\"/s", "arcsecond per second", 1.0 / 3600.0, UnitSystem.OTHER);

        /** grad per second. */
        public static final AngularVelocity.Unit GRAD_PER_SECOND =
                RADIAN_PER_SECOND.deriveUnit("grad/s", "gradian per second", 2.0 * Math.PI / 400.0, UnitSystem.OTHER);

        /** centesimal arcminute per second. */
        public static final AngularVelocity.Unit CENTESIMAL_ARCMINUTE_PER_SECOND =
                GRAD_PER_SECOND.deriveUnit("cdm/s", "c'/s", "centesimal arcminute per second", 1.0 / 100.0, UnitSystem.OTHER);

        /** centesimal arcsecond per second. */
        public static final AngularVelocity.Unit CENTESIMAL_ARCSECOND_PER_SECOND = GRAD_PER_SECOND.deriveUnit("cds/s", "c\"/s",
                "centesimal arcsecond per second", 1.0 / 10000.0, UnitSystem.OTHER);

        /**
         * Create a new AngularVelocity unit.
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
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new AngularVelocity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
