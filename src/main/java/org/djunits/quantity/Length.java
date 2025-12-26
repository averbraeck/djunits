package org.djunits.quantity;

import java.util.List;
import java.util.Locale;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * Length is the measure of distance between two points, expressed in meters (m).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Length extends Quantity.Relative<Length, Length.Unit>
{
    /** Constant with value zero. */
    public static final Length ZERO = Length.ofSi(0.0);

    /** Constant with value one. */
    public static final Length ONE = Length.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Length NaN = Length.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Length POSITIVE_INFINITY = Length.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Length NEGATIVE_INFINITY = Length.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Length POS_MAXVALUE = Length.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Length NEG_MAXVALUE = Length.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Length quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Length(final double value, final Length.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Length quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Length(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Length.Unit.class, abbreviation));
    }

    /**
     * Construct Length quantity.
     * @param value Scalar from which to construct this instance
     */
    public Length(final Length value)
    {
        super(value.si(), Length.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Length instance based on an SI value.
     * @param si the si value
     * @return the Length instance based on an SI value
     */
    public static Length ofSi(final double si)
    {
        return new Length(si, Length.Unit.SI);
    }

    @Override
    public Length instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Length.Unit.SI_UNIT;
    }

    /**
     * Returns a Length representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Length
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Length valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Length: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Length: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            Length.Unit unit = Units.resolve(Length.Unit.class, unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Length", unitString);
            return new Length(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Length from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Length based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Length of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Length: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Length: empty unitString");
        Length.Unit unit = Units.resolve(Length.Unit.class, unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Length with unit %s", unitString);
        return new Length(value, unit);
    }

    /**
     * Calculate the division of Length and Length, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Length and Length
     */
    public final Dimensionless divide(final Length v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Length and LinearDensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and LinearDensity
     */
    public final Dimensionless times(final LinearDensity v)
    {
        return new Dimensionless(this.si() * v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Length and Length, which results in a Area quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and Length
     */
    public final Area times(final Length v)
    {
        return new Area(this.si() * v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of Length and LinearDensity, which results in a Area quantity.
     * @param v quantity
     * @return quantity as a division of Length and LinearDensity
     */
    public final Area divide(final LinearDensity v)
    {
        return new Area(this.si() / v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of Length and Area, which results in a LinearDensity quantity.
     * @param v quantity
     * @return quantity as a division of Length and Area
     */
    public final LinearDensity divide(final Area v)
    {
        return new LinearDensity(this.si() / v.si(), LinearDensity.Unit.SI);
    }

    /**
     * Calculate the multiplication of Length and Area, which results in a Volume quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and Area
     */
    public final Volume times(final Area v)
    {
        return new Volume(this.si() * v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Length and Force, which results in a Energy quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and Force
     */
    public final Energy times(final Force v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the multiplication of Length and Frequency, which results in a Speed quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and Frequency
     */
    public final Speed times(final Frequency v)
    {
        return new Speed(this.si() * v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the division of Length and Duration, which results in a Speed quantity.
     * @param v quantity
     * @return quantity as a division of Length and Duration
     */
    public final Speed divide(final Duration v)
    {
        return new Speed(this.si() / v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the division of Length and Speed, which results in a Duration quantity.
     * @param v quantity
     * @return quantity as a division of Length and Speed
     */
    public final Duration divide(final Speed v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the multiplication of Length and FlowMass, which results in a Momentum quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and FlowMass
     */
    public final Momentum times(final FlowMass v)
    {
        return new Momentum(this.si() * v.si(), Momentum.Unit.SI);
    }

    @Override
    public LinearDensity reciprocal()
    {
        return LinearDensity.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Length.Unit encodes the length unit.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Length.Unit>
    {
        /** The dimensions of the length: m. */
        public static final SIUnit SI_UNIT = SIUnit.of("m");

        /** meter. */
        public static final Length.Unit METER = new Length.Unit("m", "meter", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Length.Unit SI = METER.generateSiPrefixes(false, false);

        /** decameter. */
        public static final Length.Unit DECAMETER = Units.resolve(Length.Unit.class, "dam");

        /** hectometer. */
        public static final Length.Unit HECTOMETER = Units.resolve(Length.Unit.class, "hm");

        /** kilometer. */
        public static final Length.Unit KILOMETER = Units.resolve(Length.Unit.class, "km");

        /** decimeter. */
        public static final Length.Unit DECIMETER = Units.resolve(Length.Unit.class, "dm");

        /** centimeter. */
        public static final Length.Unit CENTIMETER = Units.resolve(Length.Unit.class, "cm");

        /** millimeter. */
        public static final Length.Unit MILLIMETER = Units.resolve(Length.Unit.class, "mm");

        /** micrometer. */
        public static final Length.Unit MICROMETER = Units.resolve(Length.Unit.class, "mum");

        /** nanometer. */
        public static final Length.Unit NANOMETER = Units.resolve(Length.Unit.class, "nm");

        /** picometer. */
        public static final Length.Unit PICOMETER = Units.resolve(Length.Unit.class, "pm");

        /** attometer. */
        public static final Length.Unit ATTOMETER = Units.resolve(Length.Unit.class, "am");

        /** femtometer. */
        public static final Length.Unit FEMTOMETER = Units.resolve(Length.Unit.class, "fm");

        /** foot (international) = 0.3048 m = 1/3 yd = 12 inches. */
        public static final Length.Unit FOOT =
                new Length.Unit(List.of("ft", "foot", "'"), "ft", "foot", new LinearScale(Units.CONST_FT), UnitSystem.IMPERIAL);

        /** inch (international) = 2.54 cm = 1/36 yd = 1/12 ft. */
        public static final Length.Unit INCH = new Length.Unit(List.of("in", "inch", "\""), "in", "inch",
                new LinearScale(Units.CONST_IN), UnitSystem.IMPERIAL);

        /** yard (international) = 0.9144 m = 3 ft = 36 in. */
        public static final Length.Unit YARD =
                new Length.Unit(List.of("yd", "yard"), "yd", "yard", new LinearScale(Units.CONST_YD), UnitSystem.IMPERIAL);

        /** mile (international) = 5280 ft = 1760 yd. */
        public static final Length.Unit MILE =
                new Length.Unit(List.of("mi", "mile"), "mi", "mile", new LinearScale(Units.CONST_MI), UnitSystem.IMPERIAL);

        /** nautical mile (international) = 1852 m. */
        public static final Length.Unit NAUTICAL_MILE =
                new Length.Unit("NM", "Nautical Mile", Units.CONST_NM, UnitSystem.OTHER);

        /** Astronomical Unit = 149,597,870,700 m. */
        public static final Length.Unit ASTRONOMICAL_UNIT =
                new Length.Unit("AU", "Astronomical Unit", 149_597_870_700.0, UnitSystem.OTHER);

        /** Lightyear = 9,460,730,472,580,800 m. */
        public static final Length.Unit LIGHTYEAR =
                new Length.Unit("ly", "lightyear", 9_460_730_472_580_800.0, UnitSystem.OTHER);

        /** Parsec = 648,000 / PI ly. */
        public static final Length.Unit PARSEC =
                new Length.Unit("Pc", "Parsec", 9_460_730_472_580_800.0 * 648_000.0 / Math.PI, UnitSystem.OTHER);

        /** Angstrom = 10^-10 m. */
        public static final Length.Unit ANGSTROM =
                new Length.Unit(List.of("A", "\u212B"), "\u212B", "Angstrom", new LinearScale(1.0E-10), UnitSystem.OTHER);

        /**
         * Create a new length unit.
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
                return new Length.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
