package org.djunits.quantity;

import java.util.Locale;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
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
public class Length extends Quantity<Length, Length.Unit>
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
    private static final long serialVersionUID = 600L;

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
     * Add an (absolute) position to this length, and return a position. The unit of the return value will be the unit of this
     * length, and the reference of the return value will be the reference belonging to the given position.
     * <code>R.add(A)</code> = unit of R and reference value of A.
     * @param position the absolute position to add
     * @return the absolute position plus this length
     */
    public final Position add(final Position position)
    {
        return position.add(this).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Calculate the division of Length and Length, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Length and Length
     */
    public final Dimensionless divide(final Length v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Length and LinearObjectDensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and LinearObjectDensity
     */
    public final Dimensionless multiply(final LinearObjectDensity v)
    {
        return new Dimensionless(this.si() * v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Length and Length, which results in a Area quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and Length
     */
    public final Area multiply(final Length v)
    {
        return new Area(this.si() * v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of Length and LinearObjectDensity, which results in a Area quantity.
     * @param v quantity
     * @return quantity as a division of Length and LinearObjectDensity
     */
    public final Area divide(final LinearObjectDensity v)
    {
        return new Area(this.si() / v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of Length and Area, which results in a LinearObjectDensity quantity.
     * @param v quantity
     * @return quantity as a division of Length and Area
     */
    public final LinearObjectDensity divide(final Area v)
    {
        return new LinearObjectDensity(this.si() / v.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Calculate the multiplication of Length and Area, which results in a Volume quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and Area
     */
    public final Volume multiply(final Area v)
    {
        return new Volume(this.si() * v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Length and Force, which results in a Energy quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and Force
     */
    public final Energy multiply(final Force v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the multiplication of Length and Frequency, which results in a Speed quantity.
     * @param v quantity
     * @return quantity as a multiplication of Length and Frequency
     */
    public final Speed multiply(final Frequency v)
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
    public final Momentum multiply(final FlowMass v)
    {
        return new Momentum(this.si() * v.si(), Momentum.Unit.SI);
    }

    @Override
    public LinearObjectDensity reciprocal()
    {
        return LinearObjectDensity.ofSi(1.0 / this.si());
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
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Length.Unit, Length>
    {
        /** Constant for the foot. */
        public static final double CONST_FT = 0.3048;

        /** Constant for the yard. */
        public static final double CONST_YD = 3.0 * CONST_FT;

        /** Constant for the inch. */
        public static final double CONST_IN = CONST_FT / 12.0;

        /** Constant for the mile. */
        public static final double CONST_MI = 5280.0 * CONST_FT;

        /** Constant for the nautical mile. */
        public static final double CONST_NM = 1852.0;

        /** Constant for the Astronomical Unit = 149,597,870,700 m. */
        public static final double CONST_AU = 149_597_870_700.0;

        /** Constant for the lightyear = 9,460,730,472,580,800 m. */
        public static final double CONST_LY = 9_460_730_472_580_800.0;

        /** Constant for the parsec = AU / tan(1 arcsecond) = AU * 648,000 / PI m. */
        public static final double CONST_PC = 149_597_870_700.0 * 648_000.0 / Math.PI;

        /** The dimensions of the length: m. */
        public static final SIUnit SI_UNIT = SIUnit.of("m");

        /** meter. */
        public static final Length.Unit m = new Length.Unit("m", "meter", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Length.Unit SI = m.generateSiPrefixes(false, false);

        /** decameter. */
        public static final Length.Unit dam = Units.resolve(Length.Unit.class, "dam");

        /** hectometer. */
        public static final Length.Unit hm = Units.resolve(Length.Unit.class, "hm");

        /** kilometer. */
        public static final Length.Unit km = Units.resolve(Length.Unit.class, "km");

        /** decimeter. */
        public static final Length.Unit dm = Units.resolve(Length.Unit.class, "dm");

        /** centimeter. */
        public static final Length.Unit cm = Units.resolve(Length.Unit.class, "cm");

        /** millimeter. */
        public static final Length.Unit mm = Units.resolve(Length.Unit.class, "mm");

        /** micrometer. */
        public static final Length.Unit mum = Units.resolve(Length.Unit.class, "mum");

        /** nanometer. */
        public static final Length.Unit nm = Units.resolve(Length.Unit.class, "nm");

        /** picometer. */
        public static final Length.Unit pm = Units.resolve(Length.Unit.class, "pm");

        /** attometer. */
        public static final Length.Unit am = Units.resolve(Length.Unit.class, "am");

        /** femtometer. */
        public static final Length.Unit fm = Units.resolve(Length.Unit.class, "fm");

        /** foot (international) = 0.3048 m = 1/3 yd = 12 inches. */
        public static final Length.Unit ft =
                new Length.Unit("ft", "ft", "foot", new LinearScale(CONST_FT), UnitSystem.IMPERIAL);

        /** inch (international) = 2.54 cm = 1/36 yd = 1/12 ft. */
        public static final Length.Unit in =
                new Length.Unit("in", "in", "inch", new LinearScale(CONST_IN), UnitSystem.IMPERIAL);

        /** yard (international) = 0.9144 m = 3 ft = 36 in. */
        public static final Length.Unit yd =
                new Length.Unit("yd", "yd", "yard", new LinearScale(CONST_YD), UnitSystem.IMPERIAL);

        /** mile (international) = 5280 ft = 1760 yd. */
        public static final Length.Unit mi =
                new Length.Unit("mi", "mi", "mile", new LinearScale(CONST_MI), UnitSystem.IMPERIAL);

        /** nautical mile (international) = 1852 m. */
        public static final Length.Unit NM = new Length.Unit("NM", "Nautical Mile", CONST_NM, UnitSystem.OTHER);

        /** Astronomical Unit = 149,597,870,700 m. */
        public static final Length.Unit AU = new Length.Unit("AU", "Astronomical Unit", CONST_AU, UnitSystem.OTHER);

        /** Lightyear = 9,460,730,472,580,800 m. */
        public static final Length.Unit ly = new Length.Unit("ly", "lightyear", CONST_LY, UnitSystem.OTHER);

        /** Parsec = AU / tan(1 arcsecond) = AU * 648,000 / PI m. */
        public static final Length.Unit Pc = new Length.Unit("Pc", "Parsec", CONST_PC, UnitSystem.OTHER);

        /** Angstrom = 10^-10 m. */
        public static final Length.Unit A =
                new Length.Unit("A", "\u00C5", "Angstrom", new LinearScale(1.0E-10), UnitSystem.OTHER);

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
        public Length ofSi(final double si)
        {
            return Length.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Length.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
