package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Dimensionless quantity.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Dimensionless extends Quantity.Relative<Dimensionless, Dimensionless.Unit>
{
    /** Constant with value zero. */
    public static final Dimensionless ZERO = Dimensionless.ofSi(0.0);

    /** Constant with value one. */
    public static final Dimensionless ONE = Dimensionless.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Dimensionless NaN = Dimensionless.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Dimensionless POSITIVE_INFINITY = Dimensionless.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Dimensionless NEGATIVE_INFINITY = Dimensionless.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Dimensionless POS_MAXVALUE = Dimensionless.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Dimensionless NEG_MAXVALUE = Dimensionless.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Dimensionless quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Dimensionless(final double value, final Dimensionless.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Dimensionless quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Dimensionless(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Dimensionless.Unit.class, abbreviation));
    }

    /**
     * Construct Dimensionless quantity.
     * @param value Scalar from which to construct this instance
     */
    public Dimensionless(final Dimensionless value)
    {
        super(value.si(), Dimensionless.Unit.BASE);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Dimensionless instance based on an SI value.
     * @param si the si value
     * @return the Dimensionless instance based on an SI value
     */
    public static Dimensionless ofSi(final double si)
    {
        return new Dimensionless(si, Dimensionless.Unit.BASE);
    }

    @Override
    public Dimensionless instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Dimensionless.Unit.SI_UNIT;
    }

    /**
     * Returns a Dimensionless representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Dimensionless
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Dimensionless valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Dimensionless based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Dimensionless of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Dimensionless and Dimensionless, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Dimensionless and Dimensionless
     */
    public final Dimensionless divide(final Dimensionless v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Dimensionless and another quantity, which results in a new instance of the other
     * quantity. A copy of the parameter v is made, since the display unit of a quantity is mutable.
     * @param v quantity
     * @return quantity as a multiplication of Dimensionless and the given quantity
     * @param <VQ> the variable's quantity type
     * @param <VU> the variable's unit type
     */
    public final <VQ extends Quantity<VQ, VU>, VU extends UnitInterface<VU>> VQ times(final VQ v)
    {
        VQ result = v.instantiate(v.si());
        result.setDisplayUnit(v.getDisplayUnit());
        return result;
    }

    /**
     * Calculate the division of Dimensionless and Length, which results in a LinearDensity scalar.
     * @param v scalar
     * @return scalar as a division of Dimensionless and Length
     */
    public final LinearDensity divide(final Length v)
    {
        return new LinearDensity(this.si() / v.si(), LinearDensity.Unit.SI);
    }

    /**
     * Calculate the division of Dimensionless and LinearDensity, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Dimensionless and LinearDensity
     */
    public final Length divide(final LinearDensity v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the division of Dimensionless and Duration, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of Dimensionless and Duration
     */
    public final Frequency divide(final Duration v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the division of Dimensionless and Frequency, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Dimensionless and Frequency
     */
    public final Duration divide(final Frequency v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of Dimensionless and ElectricalConductance, which results in a ElectricalResistance scalar.
     * @param v scalar
     * @return scalar as a division of Dimensionless and ElectricalConductance
     */
    public final ElectricalResistance divide(final ElectricalConductance v)
    {
        return new ElectricalResistance(this.si() / v.si(), ElectricalResistance.Unit.SI);
    }

    /**
     * Calculate the division of Dimensionless and ElectricalResistance, which results in a ElectricalConductance scalar.
     * @param v scalar
     * @return scalar as a division of Dimensionless and ElectricalResistance
     */
    public final ElectricalConductance divide(final ElectricalResistance v)
    {
        return new ElectricalConductance(this.si() / v.si(), ElectricalConductance.Unit.SI);
    }

    @Override
    public Dimensionless reciprocal()
    {
        return Dimensionless.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Dimensionless.Unit encodes a unit without dimensions, e.g., to encode a constant.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Dimensionless.Unit>
    {
        /** The dimensions of the dimensionless quantity: 1 [rad, sr, kg, m, s, A, K, mol, cd]. */
        public static final SIUnit SI_UNIT = new SIUnit(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0});

        /** The SI or BASE unit. */
        public static final Dimensionless.Unit BASE = new Dimensionless.Unit(" ", " ", 1.0, UnitSystem.OTHER);

        /**
         * Create a new Dimensionless unit.
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
            return BASE;
        }

        @Override
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            return new Dimensionless.Unit(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
        }

    }
}
