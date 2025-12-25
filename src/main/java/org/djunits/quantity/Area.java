package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Area as a length x length. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Area extends Quantity.Relative<Area, Area.Unit>
{
    /** Constant with value zero. */
    public static final Area ZERO = Area.ofSi(0.0);

    /** Constant with value one. */
    public static final Area ONE = Area.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Area NaN = Area.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Area POSITIVE_INFINITY = Area.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Area NEGATIVE_INFINITY = Area.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Area POS_MAXVALUE = Area.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Area NEG_MAXVALUE = Area.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Area quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Area(final double value, final Area.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Area quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Area(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Area.Unit.class, abbreviation));
    }

    /**
     * Construct Area quantity.
     * @param value Scalar from which to construct this instance
     */
    public Area(final Area value)
    {
        super(value.si(), Area.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Area instance based on an SI value.
     * @param si the si value
     * @return the Area instance based on an SI value
     */
    public static Area ofSi(final double si)
    {
        return new Area(si, Area.Unit.SI);
    }

    @Override
    public Area instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Area.Unit.SI_UNIT;
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
     * Returns a Area based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Area of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Area and Area, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Area and Area
     */
    public final Dimensionless divide(final Area v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Area and Length, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and Length
     */
    public final Volume times(final Length v)
    {
        return new Volume(this.si() * v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the division of Area and LinearDensity, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Area and LinearDensity
     */
    public final Volume divide(final LinearDensity v)
    {
        return new Volume(this.si() / v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the division of Area and Volume, which results in a LinearDensity scalar.
     * @param v scalar
     * @return scalar as a division of Area and Volume
     */
    public final LinearDensity divide(final Volume v)
    {
        return new LinearDensity(this.si() / v.si(), LinearDensity.Unit.SI);
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
     * Calculate the multiplication of Area and LinearDensity, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and LinearDensity
     */
    public final Length times(final LinearDensity v)
    {
        return new Length(this.si() * v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Area and Speed, which results in a FlowVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and Speed
     */
    public final FlowVolume times(final Speed v)
    {
        return new FlowVolume(this.si() * v.si(), FlowVolume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Area and Pressure, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and Pressure
     */
    public final Force times(final Pressure v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Area and Illuminance, which results in a LuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of Area and Illuminance
     */
    public final LuminousFlux times(final Illuminance v)
    {
        return new LuminousFlux(this.si() * v.si(), LuminousFlux.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Area.Unit encodes the area unit (length x length).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Area.Unit>
    {
        /** The dimensions of Area: m2. */
        public static final SIUnit SI_UNIT = SIUnit.of("m2");

        /** Square meter. */
        public static final Area.Unit M2 = new Area.Unit("m^2", "square meter", 1.0, UnitSystem.SI_BASE);

        /** Square meter. */
        public static final Area.Unit SQUARE_METER = M2;

        /** The SI or BASE unit. */
        public static final Area.Unit SI = M2.generateSiPrefixes(false, false);

        /** Square decameter. */
        public static final Area.Unit DAM2 = Units.resolve(Area.Unit.class, "dam^2");

        /** Square hectometer. */
        public static final Area.Unit HM2 = Units.resolve(Area.Unit.class, "hm^2");

        /** Square kilometer. */
        public static final Area.Unit KM2 = Units.resolve(Area.Unit.class, "km^2");

        /** Square kilometer. */
        public static final Area.Unit SQUARE_KM = KM2;

        /** Square decimeter. */
        public static final Area.Unit DM2 = Units.resolve(Area.Unit.class, "dm^2");

        /** Square decimeter. */
        public static final Area.Unit SQUARE_DM = DM2;

        /** Square centimeter. */
        public static final Area.Unit CM2 = Units.resolve(Area.Unit.class, "cm^2");

        /** Square centimeter. */
        public static final Area.Unit SQUARE_CM = CM2;

        /** Square millimeter. */
        public static final Area.Unit MM2 = Units.resolve(Area.Unit.class, "mm^2");

        /** Square millimeter. */
        public static final Area.Unit SQUARE_MM = MM2;

        /** Square micrometer. */
        public static final Area.Unit MUM2 = Units.resolve(Area.Unit.class, "mum^2");

        /** Square nanometer. */
        public static final Area.Unit NM2 = Units.resolve(Area.Unit.class, "nm^2");

        /** Square picometer. */
        public static final Area.Unit PM2 = Units.resolve(Area.Unit.class, "pm^2");

        /** Square attometer. */
        public static final Area.Unit AM2 = Units.resolve(Area.Unit.class, "am^2");

        /** Square femtometer. */
        public static final Area.Unit FM2 = Units.resolve(Area.Unit.class, "fm^2");

        /** centiare. */
        public static final Area.Unit CENTIARE = new Area.Unit("ca", "centiare", 1.0, UnitSystem.OTHER);

        /** are. */
        public static final Area.Unit ARE = new Area.Unit("a", "are", 100.0, UnitSystem.OTHER);

        /** hectare. */
        public static final Area.Unit HECTARE = new Area.Unit("ha", "hectare", 100.0 * 100.0, UnitSystem.OTHER);

        /** mile^2. */
        public static final Area.Unit SQUARE_MILE =
                new Area.Unit("mi^2", "square mile", Units.const_mi * Units.const_mi, UnitSystem.IMPERIAL);

        /** Nautical mile^2. */
        public static final Area.Unit SQUARE_NM =
                new Area.Unit("NM^2", "square nautical mile", Units.const_NM * Units.const_NM, UnitSystem.OTHER);

        /** ft^2. */
        public static final Area.Unit SQUARE_FOOT =
                new Area.Unit("ft^2", "square foot", Units.const_ft * Units.const_ft, UnitSystem.IMPERIAL);

        /** in^2. */
        public static final Area.Unit SQUARE_INCH =
                new Area.Unit("in^2", "square inch", Units.const_in * Units.const_in, UnitSystem.IMPERIAL);

        /** yd^2. */
        public static final Area.Unit SQUARE_YARD =
                new Area.Unit("yd^2", "square yard", Units.const_yd * Units.const_yd, UnitSystem.IMPERIAL);

        /** acre (international) defined as 1/640 square mile or 4840 square yards. */
        public static final Area.Unit ACRE =
                new Area.Unit("ac", "acre", Units.const_mi * Units.const_mi / 640.0, UnitSystem.IMPERIAL);

        /**
         * Create a new Area unit.
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
         * @param textualAbbreviations the textual abbreviations of the unit, where the first one in the list is the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            super(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
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
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Area.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
