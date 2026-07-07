package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Angular acceleration is the rate of change of angular velocity over time, measured in radians per second squared (rad/s2).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AngularAcceleration extends Quantity<AngularAcceleration>
{
    /** Constant with value zero. */
    public static final AngularAcceleration ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final AngularAcceleration ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AngularAcceleration NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AngularAcceleration POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AngularAcceleration NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final AngularAcceleration POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final AngularAcceleration NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a AngularAcceleration quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public AngularAcceleration(final double value, final AngularAcceleration.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a AngularAcceleration quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public AngularAcceleration(final double valueInUnit, final AngularAcceleration.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a AngularAcceleration instance based on an SI value.
     * @param si the si value
     * @return the AngularAcceleration instance based on an SI value
     */
    public static AngularAcceleration ofSi(final double si)
    {
        return new AngularAcceleration(si, AngularAcceleration.Unit.SI, true);
    }

    /**
     * Instantiate a AngularAcceleration quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the AngularAcceleration instance based on an SI value with the given display unit
     */
    public static AngularAcceleration ofSi(final double siValue, final AngularAcceleration.Unit displayUnit)
    {
        return new AngularAcceleration(siValue, displayUnit, true);
    }

    @Override
    public AngularAcceleration instantiateSi(final double siValue, final UnitInterface<AngularAcceleration> displayUnit)
    {
        return new AngularAcceleration(siValue, (AngularAcceleration.Unit) displayUnit, true);
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
     * Returns a AngularAcceleration based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab AngularAcceleration representation of the value in its unit
     */
    public static AngularAcceleration of(final double valueInUnit, final AngularAcceleration.Unit unit)
    {
        return new AngularAcceleration(valueInUnit, unit);
    }

    /**
     * Returns a AngularAcceleration based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AngularAcceleration of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public AngularAcceleration.Unit getDisplayUnit()
    {
        return (AngularAcceleration.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of AngularAcceleration and AngularAcceleration, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and AngularAcceleration
     */
    public Dimensionless divide(final AngularAcceleration v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of AngularAcceleration and Duration, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of AngularAcceleration and Duration
     */
    public AngularVelocity multiply(final Duration v)
    {
        return new AngularVelocity(this.si() * v.si(), AngularVelocity.Unit.SI);
    }

    /**
     * Calculate the division of AngularAcceleration and Frequency, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and Frequency
     */
    public AngularVelocity divide(final Frequency v)
    {
        return new AngularVelocity(this.si() / v.si(), AngularVelocity.Unit.SI);
    }

    /**
     * Calculate the division of AngularAcceleration and AngularVelocity, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and AngularVelocity
     */
    public Frequency divide(final AngularVelocity v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * AngularAcceleration.Unit encodes the units of angle (radians or degrees per second squared).
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<AngularAcceleration>
    {
        /** The dimensions of AngularAcceleration: rad/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("rad/s2");

        /** radian per second squared. */
        public static final AngularAcceleration.Unit rad_s2 =
                new AngularAcceleration.Unit("rad/s2", "radians per second squared", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final AngularAcceleration.Unit SI = rad_s2;

        /** degree per second squared. */
        public static final AngularAcceleration.Unit deg_s2 = rad_s2.deriveUnit("deg/s2", "\u00b0/s2",
                "degree per second squared", Math.PI / 180.0, UnitSystem.SI_ACCEPTED, null);

        /** arcminute per second squared. */
        public static final AngularAcceleration.Unit arcmin_s2 =
                deg_s2.deriveUnit("arcmin/s2", "'/s2", "arcminute per second squared", 1.0 / 60.0, UnitSystem.OTHER, null);

        /** arcsecond per second squared. */
        public static final AngularAcceleration.Unit arcsec_s2 =
                deg_s2.deriveUnit("arcsec/s2", "\"/s2", "arcsecond per second squared", 1.0 / 3600.0, UnitSystem.OTHER, null);

        /** grad per second squared. */
        public static final AngularAcceleration.Unit grad_s2 = rad_s2.deriveUnit("grad/s2", "grad/s2",
                "gradian per second squared", 2.0 * Math.PI / 400.0, UnitSystem.OTHER, null);

        /** centesimal arcminute per second squared. */
        public static final AngularAcceleration.Unit cdm_s2 = grad_s2.deriveUnit("cdm/s2", "c'/s2",
                "centesimal arcminute per second squared", 1.0 / 100.0, UnitSystem.OTHER, null);

        /** centesimal arcsecond per second squared. */
        public static final AngularAcceleration.Unit cds_s2 = grad_s2.deriveUnit("cds/s2", "c\"/s2",
                "centesimal arcsecond per second squared", 1.0 / 10000.0, UnitSystem.OTHER, null);

        /**
         * Create a new AngularAcceleration unit.
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
        public AngularAcceleration ofSi(final double si, final UnitInterface<AngularAcceleration> displayUnit)
        {
            return new AngularAcceleration(si, (Unit) displayUnit, true);
        }

        @Override
        public AngularAcceleration.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new AngularAcceleration.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
