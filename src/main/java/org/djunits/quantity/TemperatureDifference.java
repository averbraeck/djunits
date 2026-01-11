package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;

/**
 * TemperatureDifference is a measure of (difference in) thermal state or average kinetic energy of particles, measured in
 * kelvins (K). Note that the TemperatureDifference quantity is relative (it measures a difference between temperatures),
 * whereas the Temperature quantity is absolute.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class TemperatureDifference extends Quantity<TemperatureDifference, Temperature.Unit>
{
    /** Constant with value zero. */
    public static final TemperatureDifference ZERO = TemperatureDifference.ofSi(0.0);

    /** Constant with value one. */
    public static final TemperatureDifference ONE = TemperatureDifference.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final TemperatureDifference NaN = TemperatureDifference.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final TemperatureDifference POSITIVE_INFINITY = TemperatureDifference.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final TemperatureDifference NEGATIVE_INFINITY = TemperatureDifference.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final TemperatureDifference POS_MAXVALUE = TemperatureDifference.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final TemperatureDifference NEG_MAXVALUE = TemperatureDifference.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a TemperatureDifference quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public TemperatureDifference(final double value, final Temperature.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a TemperatureDifference quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public TemperatureDifference(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Temperature.Unit.class, abbreviation));
    }

    /**
     * Construct TemperatureDifference quantity.
     * @param value Scalar from which to construct this instance
     */
    public TemperatureDifference(final TemperatureDifference value)
    {
        super(value.si(), Temperature.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a TemperatureDifference instance based on an SI value.
     * @param si the si value
     * @return the TemperatureDifference instance based on an SI value
     */
    public static TemperatureDifference ofSi(final double si)
    {
        return new TemperatureDifference(si, Temperature.Unit.SI);
    }

    @Override
    public TemperatureDifference instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Temperature.Unit.SI_UNIT;
    }

    /**
     * Returns a TemperatureDifference representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Temperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static TemperatureDifference valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a TemperatureDifference based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static TemperatureDifference of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of TemperatureDifference and TemperatureDifference, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of TemperatureDifference and Temperature
     */
    public final Dimensionless divide(final TemperatureDifference v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Add an absolute temperature to this temperature (difference), and return an absolute temperature. The unit of the return
     * value will be the unit of this (relative) temperature.
     * @param absoluteTemperature the absolute temperature to add
     * @return the absolute temperature plus this temperature difference
     */
    public final Temperature add(final Temperature absoluteTemperature)
    {
        var abstemp = Temperature.ofSi(absoluteTemperature.si() + si());
        abstemp.setDisplayUnit(getDisplayUnit());
        return abstemp;
    }
}
