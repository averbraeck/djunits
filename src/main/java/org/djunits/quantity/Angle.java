package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Angle is the measure of rotation between two intersecting lines, expressed in radians (rad) or degrees.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Angle extends Quantity<Angle, Angle.Unit>
{
    /** Constant with value zero radians. */
    public static final Angle ZERO = Angle.ofSi(0.0);

    /** Constant with value pi radians. */
    public static final Angle PI = Angle.ofSi(Math.PI);

    /** Constant with value pi/2 radians. */
    public static final Angle HALF_PI = Angle.ofSi(Math.PI / 2.0);

    /** Constant with value 2 pi radians. */
    public static final Angle TWO_PI = Angle.ofSi(Math.PI * 2.0);

    /** Constant with value tau radians. */
    public static final Angle TAU = Angle.ofSi(Math.PI * 2.0);

    /** Constant with value one radian. */
    public static final Angle ONE = Angle.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Angle NaN = Angle.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Angle POSITIVE_INFINITY = Angle.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Angle NEGATIVE_INFINITY = Angle.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Angle POS_MAXVALUE = Angle.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Angle NEG_MAXVALUE = Angle.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Angle quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Angle(final double value, final Angle.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Angle quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Angle(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Angle.Unit.class, abbreviation));
    }

    /**
     * Construct Angle quantity.
     * @param value Scalar from which to construct this instance
     */
    public Angle(final Angle value)
    {
        super(value.si(), Angle.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Angle instance based on an SI value.
     * @param si the si value
     * @return the Angle instance based on an SI value
     */
    public static Angle ofSi(final double si)
    {
        return new Angle(si, Angle.Unit.SI);
    }

    @Override
    public Angle instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Angle.Unit.SI_UNIT;
    }

    /**
     * Returns a Angle representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Angle
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Angle valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Angle based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Angle of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Angle and Angle, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Angle and Angle
     */
    public final Dimensionless divide(final Angle v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Angle and Frequency, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of Angle and Frequency
     */
    public final AngularVelocity multiply(final Frequency v)
    {
        return new AngularVelocity(this.si() * v.si(), AngularVelocity.Unit.SI);
    }

    /**
     * Calculate the division of Angle and Duration, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a division of Angle and Duration
     */
    public final AngularVelocity divide(final Duration v)
    {
        return new AngularVelocity(this.si() / v.si(), AngularVelocity.Unit.SI);
    }

    /**
     * Calculate the division of Angle and AngularVelocity, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Angle and AngularVelocity
     */
    public final Duration divide(final AngularVelocity v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Normalize an angle between 0 and 2 * PI.
     * @param angle original angle.
     * @return angle between 0 and 2 * PI.
     */
    public static double normalize(final double angle)
    {
        double normalized = angle % (2 * Math.PI);
        if (normalized < 0.0)
        {
            normalized += 2 * Math.PI;
        }
        return normalized;
    }

    /**
     * Normalize an angle between 0 and 2 * PI.
     * @param angle original angle.
     * @return a new Angle object with angle between 0 and 2 * PI.
     */
    public static Angle normalize(final Angle angle)
    {
        return new Angle(normalize(angle.si()), Angle.Unit.rad);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Angle.Unit encodes the units of angle (radians, degrees).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Angle.Unit, Angle>
    {
        /** The dimensions of Angle: rad. */
        public static final SIUnit SI_UNIT = SIUnit.of("rad");

        /** radian. */
        public static final Angle.Unit rad =
                new Angle.Unit("rad", "rad", "radian", IdentityScale.SCALE, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Angle.Unit SI = rad;

        /** percent (non-linear, 100% is 45 degrees; 90 degrees is infinite). */
        public static final Angle.Unit percent = new Angle.Unit("%", "%", "percent", new GradeScale(0.01), UnitSystem.OTHER);

        /** degree. */
        public static final Angle.Unit deg =
                rad.deriveUnit("deg", "\u00b0", "degree", Math.PI / 180.0, UnitSystem.SI_ACCEPTED);

        /** arcminute. */
        public static final Angle.Unit arcmin = deg.deriveUnit("arcmin", "'", "arcminute", 1.0 / 60.0, UnitSystem.OTHER);

        /** arcsecond. */
        public static final Angle.Unit arcsec =
                deg.deriveUnit("arcsec", "\"", "arcsecond", 1.0 / 3600.0, UnitSystem.OTHER);

        /** grad. */
        public static final Angle.Unit grad = rad.deriveUnit("grad", "gradian", 2.0 * Math.PI / 400.0, UnitSystem.OTHER);

        /** centesimal arcminute. */
        public static final Angle.Unit cdm =
                grad.deriveUnit("cdm", "c'", "centesimal arcminute", 1.0 / 100.0, UnitSystem.OTHER);

        /** centesimal arcsecond. */
        public static final Angle.Unit cds =
                grad.deriveUnit("cds", "c\"", "centesimal arcsecond", 1.0 / 10000.0, UnitSystem.OTHER);

        /**
         * Create a new Angle unit.
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
        public Angle ofSi(final double si)
        {
            return Angle.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Angle.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
