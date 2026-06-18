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
 * Mass is the amount of matter in an object, measured in kilograms (kg).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Mass extends Quantity<Mass>
{
    /** Constant with value zero. */
    public static final Mass ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final Mass ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Mass NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Mass POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Mass NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Mass POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Mass NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Mass quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public Mass(final double value, final Mass.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a Mass quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public Mass(final double valueInUnit, final Mass.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a Mass instance based on an SI value.
     * @param si the si value
     * @return the Mass instance based on an SI value
     */
    public static Mass ofSi(final double si)
    {
        return new Mass(si, Mass.Unit.SI, true);
    }

    /**
     * Instantiate a Mass quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the Mass instance based on an SI value with the given display unit
     */
    public static Mass ofSi(final double siValue, final Mass.Unit displayUnit)
    {
        return new Mass(siValue, displayUnit, true);
    }

    @Override
    public Mass instantiateSi(final double siValue, final UnitInterface<Mass> displayUnit)
    {
        return new Mass(siValue, (Mass.Unit) displayUnit, true);
    }

    /**
     * Returns a Mass representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Mass
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Mass valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Mass based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab Mass representation of the value in its unit
     */
    public static Mass of(final double valueInUnit, final Mass.Unit unit)
    {
        return new Mass(valueInUnit, unit);
    }

    /**
     * Returns a Mass based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Mass of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public Mass.Unit getDisplayUnit()
    {
        return (Mass.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of Mass and Mass, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Mass and Mass
     */
    public final Dimensionless divide(final Mass v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of Mass and FlowMass, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Mass and FlowMass
     */
    public final Duration divide(final FlowMass v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of Mass and Duration, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Duration
     */
    public final FlowMass divide(final Duration v)
    {
        return new FlowMass(this.si() / v.si(), FlowMass.Unit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Acceleration, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Acceleration
     */
    public final Force multiply(final Acceleration v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Frequency, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Frequency
     */
    public final FlowMass multiply(final Frequency v)
    {
        return new FlowMass(this.si() * v.si(), FlowMass.Unit.SI);
    }

    /**
     * Calculate the division of Mass and Density, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Density
     */
    public final Volume divide(final Density v)
    {
        return new Volume(this.si() / v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the division of Mass and Volume, which results in a Density scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Volume
     */
    public final Density divide(final Volume v)
    {
        return new Density(this.si() / v.si(), Density.Unit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Speed, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Speed
     */
    public final Momentum multiply(final Speed v)
    {
        return new Momentum(this.si() * v.si(), Momentum.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Mass.Unit encodes the unit of the amount of matter in an object.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Mass>
    {
        /** Constant for pound (lb). */
        public static final double CONST_LB = 0.45359237;

        /** Constant for ounce. */
        public static final double CONST_OUNCE = CONST_LB / 16.0;

        /** Constant for short ton. */
        public static final double CONST_TON_SHORT = 2000.0 * CONST_LB;

        /** Constant for long ton. */
        public static final double CONST_TON_LONG = 2240.0 * CONST_LB;

        /** The dimensions of mass: kg. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg");

        /** kilogram. */
        public static final Mass.Unit kg =
                new Mass.Unit("kg", "kg", "kilogram", IdentityScale.SCALE, UnitSystem.SI_BASE, SIPrefixes.getSiPrefixKilo("k"));

        /** The SI or BASE unit. */
        public static final Mass.Unit SI = (Unit) kg.generateSiPrefixes(true, false);

        /** gram. */
        public static final Mass.Unit g = Units.resolve(Mass.Unit.class, "g");

        /** microgram. */
        public static final Mass.Unit mug = Units.resolve(Mass.Unit.class, "mug");

        /** milligram. */
        public static final Mass.Unit mg = Units.resolve(Mass.Unit.class, "mg");

        /** pound. */
        public static final Mass.Unit lb = kg.deriveUnit("lb", "pound", CONST_LB, UnitSystem.IMPERIAL);

        /** ounce. */
        public static final Mass.Unit oz = kg.deriveUnit("oz", "ounce", CONST_OUNCE, UnitSystem.IMPERIAL);

        /** long ton = 2240 lb. */
        public static final Mass.Unit long_tn = kg.deriveUnit("long tn", "long ton", CONST_TON_LONG, UnitSystem.IMPERIAL);

        /** short ton = 2000 lb. */
        public static final Mass.Unit sh_tn = kg.deriveUnit("sh tn", "short ton", CONST_TON_SHORT, UnitSystem.US_CUSTOMARY);

        /** metric ton = 1000 kg. */
        public static final Mass.Unit t = kg.deriveUnit("t", "metric tonne", 1000.0, UnitSystem.SI_ACCEPTED);

        /** metric ton = 1000 kg. */
        public static final Mass.Unit t_mts = kg.deriveUnit("t(mts)", "tonne", 1000.0, UnitSystem.MTS);

        /** Dalton, according to CODATA 2018. */
        public static final Mass.Unit Da = kg.deriveUnit("Da", "Dalton", 1.66053906660E-27, UnitSystem.SI_ACCEPTED);

        /** electronvolt = 1.782661907E-36 kg. See http://physics.nist.gov/cuu/Constants/Table/allascii.txt. */
        public static final Mass.Unit eV = kg.deriveUnit("eV", "electronvolt", 1.782661907E-36, UnitSystem.OTHER);

        /** microelectronvolt. */
        public static final Mass.Unit mueV =
                eV.deriveUnit("mueV", "\u03BCeV", "microelectronvolt", 1E-6, UnitSystem.OTHER, null);

        /** millielectronvolt (note, no dash between milli and electron; the SI style guide forbids spaces or hyphens). */
        public static final Mass.Unit meV = eV.deriveUnit("meV", "millielectronvolt", 1E-3, UnitSystem.OTHER);

        /** kiloelectronvolt. */
        public static final Mass.Unit keV = eV.deriveUnit("keV", "kiloelectronvolt", 1E3, UnitSystem.OTHER);

        /** megaelectronvolt. */
        public static final Mass.Unit MeV = eV.deriveUnit("MeV", "megaelectronvolt", 1E6, UnitSystem.OTHER);

        /** gigaelectronvolt. */
        public static final Mass.Unit GeV = eV.deriveUnit("GeV", "gigaelectronvolt", 1E9, UnitSystem.OTHER);

        /**
         * Create a new Mass unit.
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
        public Mass ofSi(final double si, final UnitInterface<Mass> displayUnit)
        {
            return new Mass(si, (Unit) displayUnit, true);
        }

        @Override
        public Mass.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Mass.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        @Override
        public Mass.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

    }

}
