package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Linear object density counts the number of objects per unit of length, measured in number per meter (/m).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LinearObjectDensity extends Quantity<LinearObjectDensity>
{
    /** Constant with value zero. */
    public static final LinearObjectDensity ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final LinearObjectDensity ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LinearObjectDensity NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LinearObjectDensity POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LinearObjectDensity NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final LinearObjectDensity POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final LinearObjectDensity NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a LinearObjectDensity quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public LinearObjectDensity(final double value, final LinearObjectDensity.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a LinearObjectDensity quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public LinearObjectDensity(final double valueInUnit, final LinearObjectDensity.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a LinearObjectDensity instance based on an SI value.
     * @param si the si value
     * @return the LinearObjectDensity instance based on an SI value
     */
    public static LinearObjectDensity ofSi(final double si)
    {
        return new LinearObjectDensity(si, LinearObjectDensity.Unit.SI, true);
    }

    /**
     * Instantiate a LinearObjectDensity quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the LinearObjectDensity instance based on an SI value with the given display unit
     */
    public static LinearObjectDensity ofSi(final double siValue, final LinearObjectDensity.Unit displayUnit)
    {
        return new LinearObjectDensity(siValue, displayUnit, true);
    }

    @Override
    public LinearObjectDensity instantiateSi(final double siValue, final UnitInterface<LinearObjectDensity> displayUnit)
    {
        return new LinearObjectDensity(siValue, (LinearObjectDensity.Unit) displayUnit, true);
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
     * Returns a LinearObjectDensity based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab LinearObjectDensity representation of the value in its unit
     */
    public static LinearObjectDensity of(final double valueInUnit, final LinearObjectDensity.Unit unit)
    {
        return new LinearObjectDensity(valueInUnit, unit);
    }

    /**
     * Returns a LinearObjectDensity based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LinearObjectDensity of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public LinearObjectDensity.Unit getDisplayUnit()
    {
        return (LinearObjectDensity.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of LinearObjectDensity and LinearObjectDensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of LinearObjectDensity and LinearObjectDensity
     */
    public final Dimensionless divide(final LinearObjectDensity v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of LinearObjectDensity and Length, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearObjectDensity and Length
     */
    public final Dimensionless multiply(final Length v)
    {
        return new Dimensionless(this.si() * v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of LinearObjectDensity and Area, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearObjectDensity and Area
     */
    public final Length multiply(final Area v)
    {
        return new Length(this.si() * v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of LinearObjectDensity and Energy, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearObjectDensity and Energy
     */
    public final Force multiply(final Energy v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of LinearObjectDensity and Speed, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearObjectDensity and Speed
     */
    public final Frequency multiply(final Speed v)
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
     * LinearObjectDensity.Unit encodes the unit for the number of objects per unit of length.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<LinearObjectDensity>
    {
        /** The dimensions of the number of objects per unit of length: per meter (/m). */
        public static final SIUnit SI_UNIT = SIUnit.of("/m");

        /** per meter. */
        public static final LinearObjectDensity.Unit per_m = new LinearObjectDensity.Unit("/m", "/m", "per meter",
                IdentityScale.SCALE, UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefixPer("/"));

        /** The SI or BASE unit. */
        public static final LinearObjectDensity.Unit SI = (Unit) per_m.generateSiPrefixes(false, true);

        /** per millimeter. */
        public static final LinearObjectDensity.Unit per_mm = Units.resolve(LinearObjectDensity.Unit.class, "/mm");

        /** per centimeter. */
        public static final LinearObjectDensity.Unit per_cm = Units.resolve(LinearObjectDensity.Unit.class, "/cm");

        /** per decimeter. */
        public static final LinearObjectDensity.Unit per_dm = Units.resolve(LinearObjectDensity.Unit.class, "/dm");

        /** per decameter. */
        public static final LinearObjectDensity.Unit per_dam = Units.resolve(LinearObjectDensity.Unit.class, "/dam");

        /** per hectometer. */
        public static final LinearObjectDensity.Unit per_hm = Units.resolve(LinearObjectDensity.Unit.class, "/hm");

        /** per kilometer. */
        public static final LinearObjectDensity.Unit per_km = Units.resolve(LinearObjectDensity.Unit.class, "/km");

        /** per inch. */
        public static final LinearObjectDensity.Unit per_in =
                new LinearObjectDensity.Unit("/in", "per inch", 1.0 / Length.Unit.CONST_IN, UnitSystem.IMPERIAL);

        /** per foot. */
        public static final LinearObjectDensity.Unit per_ft =
                new LinearObjectDensity.Unit("/ft", "per foot", 1.0 / Length.Unit.CONST_FT, UnitSystem.IMPERIAL);

        /** per yard. */
        public static final LinearObjectDensity.Unit per_yd =
                new LinearObjectDensity.Unit("/yd", "per yard", 1.0 / Length.Unit.CONST_YD, UnitSystem.IMPERIAL);

        /** per mile. */
        public static final LinearObjectDensity.Unit per_mi =
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
        public LinearObjectDensity ofSi(final double si, final UnitInterface<LinearObjectDensity> displayUnit)
        {
            return new LinearObjectDensity(si, (Unit) displayUnit, true);
        }

        @Override
        public LinearObjectDensity.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new LinearObjectDensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
