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
 * Torque is a measure of rotational force about an axis, measured in newton meters (Nm).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Torque extends Quantity<Torque>
{
    /** Constant with value zero. */
    public static final Torque ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final Torque ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Torque NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Torque POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Torque NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Torque POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Torque NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Torque quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public Torque(final double value, final Torque.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a Torque quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public Torque(final double valueInUnit, final Torque.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a Torque instance based on an SI value.
     * @param si the si value
     * @return the Torque instance based on an SI value
     */
    public static Torque ofSi(final double si)
    {
        return new Torque(si, Torque.Unit.SI, true);
    }

    /**
     * Instantiate a Torque quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the Torque instance based on an SI value with the given display unit
     */
    public static Torque ofSi(final double siValue, final Torque.Unit displayUnit)
    {
        return new Torque(siValue, displayUnit, true);
    }

    @Override
    public Torque instantiateSi(final double siValue, final UnitInterface<Torque> displayUnit)
    {
        return new Torque(siValue, (Torque.Unit) displayUnit, true);
    }

    /**
     * Returns a Torque representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Torque
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Torque valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Torque based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab Torque representation of the value in its unit
     */
    public static Torque of(final double valueInUnit, final Torque.Unit unit)
    {
        return new Torque(valueInUnit, unit);
    }

    /**
     * Returns a Torque based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Torque of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public Torque.Unit getDisplayUnit()
    {
        return (Torque.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of Torque and Torque, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Torque and Torque
     */
    public Dimensionless divide(final Torque v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of Torque and Force, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Force
     */
    public Length divide(final Force v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Length, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Length
     */
    public Force divide(final Length v)
    {
        return new Force(this.si() / v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Torque and LinearObjectDensity, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Torque and LinearObjectDensity
     */
    public Force multiply(final LinearObjectDensity v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Duration, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Duration
     */
    public Power divide(final Duration v)
    {
        return new Power(this.si() / v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Power, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Power
     */
    public Duration divide(final Power v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the multiplication of Torque and Frequency, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Torque and Frequency
     */
    public Power multiply(final Frequency v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Volume, which results in a Pressure scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Volume
     */
    public Pressure divide(final Volume v)
    {
        return new Pressure(this.si() / v.si(), Pressure.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Pressure, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Pressure
     */
    public Volume divide(final Pressure v)
    {
        return new Volume(this.si() / v.si(), Volume.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Torque.Unit encodes the units of rotational force about an axis, measured in newton meters (Nm).
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Torque>
    {
        /** The dimensions of torque: kgm2/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s2");

        /** Newton meter. */
        public static final Torque.Unit Nm = new Torque.Unit("Nm", "newton meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Torque.Unit SI = Nm;

        /** meter kilogram-force. */
        public static final Torque.Unit m_kgf =
                SI.deriveUnit("m.kgf", "meter kilogram-force", Acceleration.Unit.CONST_GRAVITY, UnitSystem.OTHER);

        /** Pound foot. */
        public static final Torque.Unit lbf_ft = SI.deriveUnit("lbf.ft", "pound-force foot",
                Length.Unit.CONST_FT * Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** Pound inch. */
        public static final Torque.Unit lbf_in = SI.deriveUnit("lbf.in", "pound-force inch",
                Length.Unit.CONST_IN * Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /**
         * Create a new Torque unit.
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
        public Torque ofSi(final double si, final UnitInterface<Torque> displayUnit)
        {
            return new Torque(si, (Unit) displayUnit, true);
        }

        @Override
        public Torque.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Torque.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        @Override
        public Torque.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

    }

}
