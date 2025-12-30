package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Torque is a measure of rotational force about an axis, measured in newton meters (Nm).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Torque extends Quantity.Relative<Torque, Torque.Unit>
{
    /** Constant with value zero. */
    public static final Torque ZERO = Torque.ofSi(0.0);

    /** Constant with value one. */
    public static final Torque ONE = Torque.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Torque NaN = Torque.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Torque POSITIVE_INFINITY = Torque.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Torque NEGATIVE_INFINITY = Torque.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Torque POS_MAXVALUE = Torque.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Torque NEG_MAXVALUE = Torque.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Torque quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Torque(final double value, final Torque.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Torque quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Torque(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Torque.Unit.class, abbreviation));
    }

    /**
     * Construct Torque quantity.
     * @param value Scalar from which to construct this instance
     */
    public Torque(final Torque value)
    {
        super(value.si(), Torque.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Torque instance based on an SI value.
     * @param si the si value
     * @return the Torque instance based on an SI value
     */
    public static Torque ofSi(final double si)
    {
        return new Torque(si, Torque.Unit.SI);
    }

    @Override
    public Torque instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Torque.Unit.SI_UNIT;
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
     * Returns a Torque based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Torque of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Torque and Torque, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Torque and Torque
     */
    public final Dimensionless divide(final Torque v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the division of Torque and Force, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Force
     */
    public final Length divide(final Force v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Length, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Length
     */
    public final Force divide(final Length v)
    {
        return new Force(this.si() / v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Torque and LinearObjectDensity, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Torque and LinearObjectDensity
     */
    public final Force times(final LinearObjectDensity v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Duration, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Duration
     */
    public final Power divide(final Duration v)
    {
        return new Power(this.si() / v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Power, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Power
     */
    public final Duration divide(final Power v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the multiplication of Torque and Frequency, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Torque and Frequency
     */
    public final Power times(final Frequency v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Volume, which results in a Pressure scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Volume
     */
    public final Pressure divide(final Volume v)
    {
        return new Pressure(this.si() / v.si(), Pressure.Unit.SI);
    }

    /**
     * Calculate the division of Torque and Pressure, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Torque and Pressure
     */
    public final Volume divide(final Pressure v)
    {
        return new Volume(this.si() / v.si(), Volume.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Torque.Unit encodes the units of rotational force about an axis, measured in newton meters (Nm).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Torque.Unit, Torque>
    {
        /** The dimensions of the absorbed dose: kgm2/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s2");

        /** Newton meter. */
        public static final Torque.Unit NEWTON_METER = new Torque.Unit("Nm", "newton meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Torque.Unit SI = NEWTON_METER;

        /** meter kilogram-force. */
        public static final Torque.Unit METER_KILOGRAM_FORCE =
                SI.deriveUnit("m.kgf", "meter kilogram-force", Acceleration.Unit.CONST_GRAVITY, UnitSystem.OTHER);

        /** Pound foot. */
        public static final Torque.Unit POUND_FOOT = SI.deriveUnit("lbf.ft", "pound-force foot",
                Length.Unit.CONST_FT * Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** Pound inch. */
        public static final Torque.Unit POUND_INCH = SI.deriveUnit("lbf.in", "pound-force inch",
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
        public Torque ofSi(final double si)
        {
            return Torque.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Torque.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
