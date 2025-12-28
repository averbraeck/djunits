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
 * Volume is the amount of three-dimensional space occupied by matter, measured in cubic meters (m3). <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Volume extends Quantity.Relative<Volume, Volume.Unit>
{
    /** Constant with value zero. */
    public static final Volume ZERO = Volume.ofSi(0.0);

    /** Constant with value one. */
    public static final Volume ONE = Volume.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Volume NaN = Volume.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Volume POSITIVE_INFINITY = Volume.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Volume NEGATIVE_INFINITY = Volume.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Volume POS_MAXVALUE = Volume.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Volume NEG_MAXVALUE = Volume.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Volume quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Volume(final double value, final Volume.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Volume quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Volume(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Volume.Unit.class, abbreviation));
    }

    /**
     * Construct Volume quantity.
     * @param value Scalar from which to construct this instance
     */
    public Volume(final Volume value)
    {
        super(value.si(), Volume.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Volume instance based on an SI value.
     * @param si the si value
     * @return the Volume instance based on an SI value
     */
    public static Volume ofSi(final double si)
    {
        return new Volume(si, Volume.Unit.SI);
    }

    @Override
    public Volume instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Volume.Unit.SI_UNIT;
    }

    /**
     * Returns a Volume representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Volume
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Volume valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Volume: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Volume: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            Volume.Unit unit = Units.resolve(Volume.Unit.class, unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Volume", unitString);
            return new Volume(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Volume from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Volume based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Volume of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Volume: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Volume: empty unitString");
        Volume.Unit unit = Units.resolve(Volume.Unit.class, unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Volume with unit %s", unitString);
        return new Volume(value, unit);
    }

    /**
     * Calculate the division of Volume and Volume, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Volume and Volume
     */
    public final Dimensionless divide(final Volume v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Volume and Density, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Volume and Density
     */
    public final Mass times(final Density v)
    {
        return new Mass(this.si() * v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the multiplication of Volume and Pressure, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Volume and Pressure
     */
    public final Energy times(final Pressure v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the division of Volume and Length, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of Volume and Length
     */
    public final Area divide(final Length v)
    {
        return new Area(this.si() / v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of Volume and Area, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Volume and Area
     */
    public final Length divide(final Area v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Volume and LinearObjectDensity, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a multiplication of Volume and LinearObjectDensity
     */
    public final Area times(final LinearObjectDensity v)
    {
        return new Area(this.si() * v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of Volume and Duration, which results in a FlowVolume scalar.
     * @param v scalar
     * @return scalar as a division of Volume and Duration
     */
    public final FlowVolume divide(final Duration v)
    {
        return new FlowVolume(this.si() / v.si(), FlowVolume.Unit.SI);
    }

    /**
     * Calculate the division of Volume and FlowVolume, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Volume and FlowVolume
     */
    public final Duration divide(final FlowVolume v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Volume.Unit encodes the area unit (length x length).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Volume.Unit>
    {
        /** Constant for the cubic inch. */
        public static final double CONST_CUBIC_INCH = cubed(Length.Unit.CONST_IN);

        /** Constant for the cubic foot. */
        public static final double CONST_CUBIC_FOOT = cubed(Length.Unit.CONST_FT);

        /** Constant for the cubic yard. */
        public static final double CONST_CUBIC_YARD = cubed(Length.Unit.CONST_YD);

        /** Constant for the imperial gallon. */
        public static final double CONST_GALLON_IMP = 4.54609E-3;

        /** Constant for imperial fluid ounce. */
        public static final double CONST_OZ_IMP = CONST_GALLON_IMP / 160.0;

        /** Constant for US gallon. */
        public static final double CONST_GALLON_US = 231.0 * CONST_CUBIC_INCH;

        /** Constant for US fluid ounce. */
        public static final double CONST_OZ_US = CONST_GALLON_US / 128.0;

        /** The dimensions of Volume: m3. */
        public static final SIUnit SI_UNIT = SIUnit.of("m3");

        /** Cubic meter. */
        public static final Volume.Unit CUBIC_METER = new Volume.Unit("m3", "cubic meter", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Volume.Unit SI = CUBIC_METER;

        /** mm^3. */
        public static final Volume.Unit CUBIC_MILLIMETER =
                CUBIC_METER.deriveUnit("mm3", "cubic millimeter", 1.0E-9, UnitSystem.SI_BASE);

        /** cm^3. */
        public static final Volume.Unit CUBIC_CENTIMETER =
                CUBIC_METER.deriveUnit("cm3", "cubic centimeter", 1.0E-6, UnitSystem.SI_BASE);

        /** dm^3. */
        public static final Volume.Unit CUBIC_DECIMETER =
                CUBIC_METER.deriveUnit("dm3", "cubic decimeter", 1.0E-3, UnitSystem.SI_BASE);

        /** dam^3. */
        public static final Volume.Unit CUBIC_DECAMETER =
                CUBIC_METER.deriveUnit("dam3", "cubic decameter", 1.0E3, UnitSystem.SI_BASE);

        /** hm^3. */
        public static final Volume.Unit CUBIC_HECTOMETER =
                CUBIC_METER.deriveUnit("hm3", "cubic hectometer", 1.0E6, UnitSystem.SI_BASE);

        /** km^3. */
        public static final Volume.Unit CUBIC_KILOMETER =
                CUBIC_METER.deriveUnit("km3", "cubic kilometer", 1.0E9, UnitSystem.SI_BASE);

        /** in^3. */
        public static final Volume.Unit CUBIC_INCH =
                CUBIC_METER.deriveUnit("in3", "cubic inch", CONST_CUBIC_INCH, UnitSystem.IMPERIAL);

        /** ft^3. */
        public static final Volume.Unit CUBIC_FOOT =
                CUBIC_METER.deriveUnit("ft3", "cubic foot", CONST_CUBIC_FOOT, UnitSystem.IMPERIAL);

        /** yd^3. */
        public static final Volume.Unit CUBIC_YARD =
                CUBIC_METER.deriveUnit("yd3", "cubic yard", CONST_CUBIC_YARD, UnitSystem.IMPERIAL);

        /** mile^3. */
        public static final Volume.Unit CUBIC_MILE =
                CUBIC_METER.deriveUnit("mi3", "cubic mile", cubed(Length.Unit.CONST_MI), UnitSystem.IMPERIAL);

        /** Nautical mile^3. */
        public static final Volume.Unit CUBIC_NAUTICAL_MILE =
                CUBIC_METER.deriveUnit("NM3", "cubic Nautical Mile", cubed(Length.Unit.CONST_NM), UnitSystem.OTHER);

        /** liter. */
        public static final Volume.Unit LITER = CUBIC_DECIMETER.deriveUnit("L", "liter", 1.0, UnitSystem.SI_ACCEPTED);

        /** gallon (US), fluids. */
        public static final Volume.Unit GALLON_US =
                CUBIC_METER.deriveUnit("gal(US)", "gallon (US)", CONST_GALLON_US, UnitSystem.US_CUSTOMARY);

        /** gallon (imperial). */
        public static final Volume.Unit GALLON_IMP =
                LITER.deriveUnit("gal(imp)", "gallon (imp)", CONST_GALLON_IMP, UnitSystem.IMPERIAL);

        /** quart (fluid US) = 1/4 US gallon. */
        public static final Volume.Unit QUART_US = GALLON_US.deriveUnit("qt(US)", "quart (US)", 0.25, UnitSystem.US_CUSTOMARY);

        /** quart (imperial) = 1/4 imp gallon. */
        public static final Volume.Unit QUART_IMP = GALLON_IMP.deriveUnit("qt(imp)", "quart (imp)", 0.25, UnitSystem.IMPERIAL);

        /** pint (fluid US) = 1/2 US quart. */
        public static final Volume.Unit PINT_US = QUART_US.deriveUnit("pt(US)", "pint (US)", 0.5, UnitSystem.US_CUSTOMARY);

        /** pint (imperial) = 1/2 imp quart. */
        public static final Volume.Unit PINT_IMP = QUART_IMP.deriveUnit("pt(imp)", "pint (imp)", 0.5, UnitSystem.IMPERIAL);

        /** ounce (fluid US) = 1/16 US pint. */
        public static final Volume.Unit FLUID_OUNCE_US =
                CUBIC_METER.deriveUnit("fl.oz(US)", "fluid ounce (US)", CONST_OZ_US, UnitSystem.US_CUSTOMARY);

        /** ounce (fluid imperial) = 1/20 imp pint. */
        public static final Volume.Unit FLUID_OUNCE_IMP =
                CUBIC_METER.deriveUnit("fl.oz(imp)", "fluid ounce (imp)", CONST_OZ_IMP, UnitSystem.IMPERIAL);

        /** Cubic lightyear. */
        public static final Volume.Unit CUBIC_LIGHTYEAR =
                CUBIC_METER.deriveUnit("ly3", "cubic lightyear", cubed(Length.Unit.CONST_LY), UnitSystem.OTHER);

        /** Cubic Parsec. */
        public static final Volume.Unit CUBIC_PARSEC =
                CUBIC_METER.deriveUnit("Pc3", "cubic Parsec", cubed(Length.Unit.CONST_PC), UnitSystem.OTHER);

        /**
         * Create a new Volume unit.
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
                return new Volume.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        /**
         * Return the cubed value of the argument.
         * @param x the value to cube
         * @return x^3
         */
        private static double cubed(final double x)
        {
            return x * x * x;
        }
    }
}
