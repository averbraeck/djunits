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
 * Linear density is mass per unit length of an object, measured in kilograms per meter (kg/m).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LinearDensity extends Quantity<LinearDensity>
{
    /** Constant with value zero. */
    public static final LinearDensity ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final LinearDensity ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LinearDensity NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LinearDensity POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LinearDensity NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final LinearDensity POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final LinearDensity NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a LinearDensity quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public LinearDensity(final double value, final LinearDensity.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a LinearDensity quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public LinearDensity(final double valueInUnit, final LinearDensity.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a LinearDensity instance based on an SI value.
     * @param si the si value
     * @return the LinearDensity instance based on an SI value
     */
    public static LinearDensity ofSi(final double si)
    {
        return new LinearDensity(si, LinearDensity.Unit.SI, true);
    }

    /**
     * Instantiate a LinearDensity quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the LinearDensity instance based on an SI value with the given display unit
     */
    public static LinearDensity ofSi(final double siValue, final LinearDensity.Unit displayUnit)
    {
        return new LinearDensity(siValue, displayUnit, true);
    }

    @Override
    public LinearDensity instantiateSi(final double siValue, final UnitInterface<LinearDensity> displayUnit)
    {
        return new LinearDensity(siValue, (LinearDensity.Unit) displayUnit, true);
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
     * Returns a LinearDensity based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab LinearDensity representation of the value in its unit
     */
    public static LinearDensity of(final double valueInUnit, final LinearDensity.Unit unit)
    {
        return new LinearDensity(valueInUnit, unit);
    }

    /**
     * Returns a LinearDensity based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LinearDensity of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public LinearDensity.Unit getDisplayUnit()
    {
        return (LinearDensity.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of LinearDensity and LinearDensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of LinearDensity and LinearDensity
     */
    public Dimensionless divide(final LinearDensity v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Divides this linear density by a mass to yield a linear object density.
     * <p>
     * Formula: (kg/m) / kg = 1/m.
     * @param mass the mass divisor; must not be {@code null}.
     * @return the resulting linear object density in SI (1/m).
     * @throws NullPointerException if {@code mass} is {@code null}.
     */
    public LinearObjectDensity divide(final Mass mass)
    {
        return new LinearObjectDensity(this.si() / mass.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Divides this linear density by a linear object density to yield a mass.
     * <p>
     * Formula: (kg/m) / (1/m) = kg.
     * @param lod the linear object density divisor; must not be {@code null}.
     * @return the resulting mass in SI (kg).
     * @throws NullPointerException if {@code lod} is {@code null}.
     */
    public Mass divide(final LinearObjectDensity lod)
    {
        return new Mass(this.si() / lod.si(), Mass.Unit.SI);
    }

    /**
     * Multiplies this linear density by a length to yield a mass.
     * <p>
     * Formula: (kg/m) * m = kg.
     * @param length the length multiplier; must not be {@code null}.
     * @return the resulting mass in SI (kg).
     * @throws NullPointerException if {@code length} is {@code null}.
     */
    public Mass multiply(final Length length)
    {
        return new Mass(this.si() * length.si(), Mass.Unit.SI);
    }

    /**
     * Multiplies this linear density by a speed to yield a mass flow.
     * <p>
     * Formula: (kg/m) * (m/s) = kg/s.
     * @param speed the speed multiplier; must not be {@code null}.
     * @return the resulting mass flow in SI (kg/s).
     * @throws NullPointerException if {@code speed} is {@code null}.
     */
    public FlowMass multiply(final Speed speed)
    {
        return new FlowMass(this.si() * speed.si(), FlowMass.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * LinearDensity.Unit encodes unit for mass per unit length.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<LinearDensity>
    {
        /** The dimensions of linear density: kg/m. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/m");

        /** Kilogram per meter. */
        public static final LinearDensity.Unit kg_m =
                new LinearDensity.Unit("kg/m", "kilogram per meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final LinearDensity.Unit SI = kg_m;

        /**
         * Create a new LinearDensity unit.
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
        public LinearDensity ofSi(final double si, final UnitInterface<LinearDensity> displayUnit)
        {
            return new LinearDensity(si, (Unit) displayUnit, true);
        }

        @Override
        public LinearDensity.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new LinearDensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
