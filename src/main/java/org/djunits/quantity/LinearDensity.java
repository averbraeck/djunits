package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Linear density is mass per unit length of an object, measured in kilograms per meter (kg/m).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LinearDensity extends Quantity.Relative<LinearDensity, LinearDensity.Unit>
{
    /** Constant with value zero. */
    public static final LinearDensity ZERO = LinearDensity.ofSi(0.0);

    /** Constant with value one. */
    public static final LinearDensity ONE = LinearDensity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LinearDensity NaN = LinearDensity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LinearDensity POSITIVE_INFINITY = LinearDensity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LinearDensity NEGATIVE_INFINITY = LinearDensity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final LinearDensity POS_MAXVALUE = LinearDensity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final LinearDensity NEG_MAXVALUE = LinearDensity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a LinearDensity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public LinearDensity(final double value, final LinearDensity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a LinearDensity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public LinearDensity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(LinearDensity.Unit.class, abbreviation));
    }

    /**
     * Construct LinearDensity quantity.
     * @param value Scalar from which to construct this instance
     */
    public LinearDensity(final LinearDensity value)
    {
        super(value.si(), LinearDensity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a LinearDensity instance based on an SI value.
     * @param si the si value
     * @return the LinearDensity instance based on an SI value
     */
    public static LinearDensity ofSi(final double si)
    {
        return new LinearDensity(si, LinearDensity.Unit.SI);
    }

    @Override
    public LinearDensity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return LinearDensity.Unit.SI_UNIT;
    }

    /**
     * Returns a LinearDensity representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LinearDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LinearDensity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a LinearDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LinearDensity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of LinearDensity and LinearDensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of LinearDensity and LinearDensity
     */
    public final Dimensionless divide(final LinearDensity v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Divides this linear density by a mass to yield a linear object density.
     * <p>
     * Formula: (kg/m) / kg = 1/m.
     * </p>
     * @param mass the mass divisor; must not be {@code null}.
     * @return the resulting linear object density in SI (1/m).
     * @throws NullPointerException if {@code mass} is {@code null}.
     */
    public final LinearObjectDensity divide(final Mass mass)
    {
        return new LinearObjectDensity(this.si() / mass.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Divides this linear density by a linear object density to yield a mass.
     * <p>
     * Formula: (kg/m) / (1/m) = kg.
     * </p>
     * @param lod the linear object density divisor; must not be {@code null}.
     * @return the resulting mass in SI (kg).
     * @throws NullPointerException if {@code lod} is {@code null}.
     */
    public final Mass divide(final LinearObjectDensity lod)
    {
        return new Mass(this.si() / lod.si(), Mass.Unit.SI);
    }

    /**
     * Multiplies this linear density by a length to yield a mass.
     * <p>
     * Formula: (kg/m) * m = kg.
     * </p>
     * @param length the length multiplier; must not be {@code null}.
     * @return the resulting mass in SI (kg).
     * @throws NullPointerException if {@code length} is {@code null}.
     */
    public final Mass times(final Length length)
    {
        return new Mass(this.si() * length.si(), Mass.Unit.SI);
    }

    /**
     * Multiplies this linear density by a speed to yield a mass flow.
     * <p>
     * Formula: (kg/m) * (m/s) = kg/s.
     * </p>
     * @param speed the speed multiplier; must not be {@code null}.
     * @return the resulting mass flow in SI (kg/s).
     * @throws NullPointerException if {@code speed} is {@code null}.
     */
    public final FlowMass times(final Speed speed)
    {
        return new FlowMass(this.si() * speed.si(), FlowMass.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * LinearDensity.Unit encodes unit for mass per unit length.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<LinearDensity.Unit, LinearDensity>
    {
        /** The dimensions of linear density: kg/m. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/m");

        /** Kilogram per meter. */
        public static final LinearDensity.Unit KILOGRAM_PER_METER =
                new LinearDensity.Unit("kg/m", "kilogram per meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final LinearDensity.Unit SI = KILOGRAM_PER_METER;

        /**
         * Create a new LinearDensity unit.
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
        public LinearDensity ofSi(final double si)
        {
            return LinearDensity.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new LinearDensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
