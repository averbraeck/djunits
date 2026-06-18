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
 * Area is a measure of a two-dimensional surface, expressed in square meters (m2).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Area extends Quantity<Area>
{
    /** Constant with value zero. */
    public static final Area ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final Area ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Area NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Area POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Area NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Area POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Area NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Area quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public Area(final double value, final Area.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a Area quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public Area(final double valueInUnit, final Area.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a Area instance based on an SI value.
     * @param si the si value
     * @return the Area instance based on an SI value
     */
    public static Area ofSi(final double si)
    {
        return new Area(si, Area.Unit.SI, true);
    }

    /**
     * Instantiate a Area quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the Area instance based on an SI value with the given display unit
     */
    public static Area ofSi(final double siValue, final Area.Unit displayUnit)
    {
        return new Area(siValue, displayUnit, true);
    }

    @Override
    public Area instantiateSi(final double siValue, final UnitInterface<Area> displayUnit)
    {
        return new Area(siValue, (Area.Unit) displayUnit, true);
    }

    /**
     * Returns a Area representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Area
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Area valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Area based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab Area representation of the value in its unit
     */
    public static Area of(final double valueInUnit, final Area.Unit unit)
    {
        return new Area(valueInUnit, unit);
    }

    /**
     * Returns a Area based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Area of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public Area.Unit getDisplayUnit()
    {
        return (Area.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of Area and Area, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Area and Area
     */
    public final Dimensionless divide(final Area v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Area and ArealObjectDensity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and ArealObjectDensity
     */
    public final Dimensionless multiply(final ArealObjectDensity v)
    {
        return new Dimensionless(this.si() * v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Area and Length, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and Length
     */
    public final Volume multiply(final Length v)
    {
        return new Volume(this.si() * v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the division of Area and LinearObjectDensity, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Area and LinearObjectDensity
     */
    public final Volume divide(final LinearObjectDensity v)
    {
        return new Volume(this.si() / v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the division of Area and Volume, which results in a LinearObjectDensity scalar.
     * @param v scalar
     * @return scalar as a division of Area and Volume
     */
    public final LinearObjectDensity divide(final Volume v)
    {
        return new LinearObjectDensity(this.si() / v.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Calculate the division of Area and Length, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Area and Length
     */
    public final Length divide(final Length v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Area and LinearObjectDensity, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and LinearObjectDensity
     */
    public final Length multiply(final LinearObjectDensity v)
    {
        return new Length(this.si() * v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Area and Speed, which results in a FlowVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and Speed
     */
    public final FlowVolume multiply(final Speed v)
    {
        return new FlowVolume(this.si() * v.si(), FlowVolume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Area and Pressure, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and Pressure
     */
    public final Force multiply(final Pressure v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Area and Illuminance, which results in a LuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and Illuminance
     */
    public final LuminousFlux multiply(final Illuminance v)
    {
        return new LuminousFlux(this.si() * v.si(), LuminousFlux.Unit.SI);
    }

    @Override
    public ArealObjectDensity reciprocal()
    {
        return ArealObjectDensity.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Area.Unit encodes the area unit (length x length).
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Area>
    {
        /** The dimensions of Area: m2. */
        public static final SIUnit SI_UNIT = SIUnit.of("m2");

        /** Square meter. */
        public static final Area.Unit m2 = new Area.Unit("m2", "square meter", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Area.Unit SI = m2;

        /** Square kilometer. */
        public static final Area.Unit km2 = m2.deriveUnit("km2", "square kilometer", 1.0E6, UnitSystem.SI_BASE);

        /** Square hectometer. */
        public static final Area.Unit hm2 = m2.deriveUnit("hm2", "square hectometer", 1.0E4, UnitSystem.SI_BASE);

        /** Square decameter. */
        public static final Area.Unit dam2 = m2.deriveUnit("dam2", "square decameter", 1.0E2, UnitSystem.SI_BASE);

        /** Square decimeter. */
        public static final Area.Unit dm2 = m2.deriveUnit("dm2", "square decimeter", 1.0E-2, UnitSystem.SI_BASE);

        /** Square centimeter. */
        public static final Area.Unit cm2 = m2.deriveUnit("cm2", "square centimeter", 1.0E-4, UnitSystem.SI_BASE);

        /** Square millimeter. */
        public static final Area.Unit mm2 = m2.deriveUnit("mm2", "square millimeter", 1.0E-6, UnitSystem.SI_BASE);

        /** Square micrometer. */
        public static final Area.Unit mum2 =
                m2.deriveUnit("mum2", "\u03BCm2", "square micrometer", 1.0E-12, UnitSystem.SI_BASE, null);

        /** Square nanometer. */
        public static final Area.Unit nm2 = m2.deriveUnit("nm2", "square nanometer", 1.0E-18, UnitSystem.SI_BASE);

        /** Square picometer. */
        public static final Area.Unit pm2 = m2.deriveUnit("pm2", "square picometer", 1.0E-24, UnitSystem.SI_BASE);

        /** Square femtometer. */
        public static final Area.Unit fm2 = m2.deriveUnit("fm2", "square femtometer", 1.0E-30, UnitSystem.SI_BASE);

        /** Square attometer. */
        public static final Area.Unit am2 = m2.deriveUnit("am2", "square attometer", 1.0E-36, UnitSystem.SI_BASE);

        /** centiare. */
        public static final Area.Unit ca = new Area.Unit("ca", "centiare", 1.0, UnitSystem.OTHER);

        /** are. */
        public static final Area.Unit a = new Area.Unit("a", "are", 100.0, UnitSystem.OTHER);

        /** hectare. */
        public static final Area.Unit ha = new Area.Unit("ha", "hectare", 100.0 * 100.0, UnitSystem.OTHER);

        /** mile2. */
        public static final Area.Unit mi2 =
                new Area.Unit("mi2", "square mile", Length.Unit.CONST_MI * Length.Unit.CONST_MI, UnitSystem.IMPERIAL);

        /** Nautical mile2. */
        public static final Area.Unit NM2 =
                new Area.Unit("NM2", "square nautical mile", Length.Unit.CONST_NM * Length.Unit.CONST_NM, UnitSystem.OTHER);

        /** ft2. */
        public static final Area.Unit ft2 =
                new Area.Unit("ft2", "square foot", Length.Unit.CONST_FT * Length.Unit.CONST_FT, UnitSystem.IMPERIAL);

        /** in2. */
        public static final Area.Unit in2 =
                new Area.Unit("in2", "square inch", Length.Unit.CONST_IN * Length.Unit.CONST_IN, UnitSystem.IMPERIAL);

        /** yd2. */
        public static final Area.Unit yd2 =
                new Area.Unit("yd2", "square yard", Length.Unit.CONST_YD * Length.Unit.CONST_YD, UnitSystem.IMPERIAL);

        /** acre (international) defined as 1/640 square mile or 4840 square yards. */
        public static final Area.Unit ac =
                new Area.Unit("ac", "acre", Length.Unit.CONST_MI * Length.Unit.CONST_MI / 640.0, UnitSystem.IMPERIAL);

        /**
         * Create a new Area unit.
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
        public Area ofSi(final double si, final UnitInterface<Area> displayUnit)
        {
            return new Area(si, (Unit) displayUnit, true);
        }

        @Override
        public Area.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Area.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        @Override
        public Area.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

    }

}
