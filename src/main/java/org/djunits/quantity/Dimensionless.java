package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;

/**
 * Dimensionless quantity.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Dimensionless extends Quantity<Dimensionless, Unitless>
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
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Dimensionless quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Dimensionless(final double value, final Unitless unit)
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
        this(value, Units.resolve(Unitless.class, abbreviation));
    }

    /**
     * Construct Dimensionless quantity.
     * @param value Scalar from which to construct this instance
     */
    public Dimensionless(final Dimensionless value)
    {
        super(value.si(), Unitless.BASE);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Dimensionless instance based on an SI value.
     * @param si the si value
     * @return the Dimensionless instance based on an SI value
     */
    public static Dimensionless ofSi(final double si)
    {
        return new Dimensionless(si, Unitless.BASE);
    }

    @Override
    public Dimensionless instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Unitless.SI_UNIT;
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
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of Dimensionless and Length, which results in a LinearObjectDensity scalar.
     * @param v scalar
     * @return scalar as a division of Dimensionless and Length
     */
    public final LinearObjectDensity divide(final Length v)
    {
        return new LinearObjectDensity(this.si() / v.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Calculate the division of Dimensionless and LinearObjectDensity, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Dimensionless and LinearObjectDensity
     */
    public final Length divide(final LinearObjectDensity v)
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

}
