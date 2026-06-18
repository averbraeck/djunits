package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.Unitless;

/**
 * TemperatureDifference is a measure of (difference in) thermal state or average kinetic energy of particles, measured in
 * kelvins (K). Note that the TemperatureDifference quantity is relative (it measures a difference between temperatures),
 * whereas the Temperature quantity is absolute.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class TemperatureDifference extends Quantity<TemperatureDifference>
{
    /** Constant with value zero. */
    public static final TemperatureDifference ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final TemperatureDifference ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final TemperatureDifference NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final TemperatureDifference POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final TemperatureDifference NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final TemperatureDifference POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final TemperatureDifference NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a TemperatureDifference quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public TemperatureDifference(final double value, final Temperature.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a TemperatureDifference quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public TemperatureDifference(final double valueInUnit, final Temperature.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a TemperatureDifference instance based on an SI value.
     * @param si the si value
     * @return the TemperatureDifference instance based on an SI value
     */
    public static TemperatureDifference ofSi(final double si)
    {
        return new TemperatureDifference(si, Temperature.Unit.SI, true);
    }

    /**
     * Instantiate a TemperatureDifference quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the TemperatureDifference instance based on an SI value with the given display unit
     */
    public static TemperatureDifference ofSi(final double siValue, final Temperature.Unit displayUnit)
    {
        return new TemperatureDifference(siValue, displayUnit, true);
    }

    @Override
    public TemperatureDifference instantiateSi(final double siValue, final UnitInterface<TemperatureDifference> displayUnit)
    {
        return new TemperatureDifference(siValue, (Temperature.Unit) displayUnit, true);
    }

    /**
     * Returns a TemperatureDifference representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a TemperatureDifference
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static TemperatureDifference valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a TemperatureDifference based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab TemperatureDifference representation of the value in its unit
     */
    public static TemperatureDifference of(final double valueInUnit, final Temperature.Unit unit)
    {
        return new TemperatureDifference(valueInUnit, unit);
    }

    /**
     * Returns a TemperatureDifference based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static TemperatureDifference of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public Temperature.Unit getDisplayUnit()
    {
        return (Temperature.Unit) super.getDisplayUnit();
    }

    /**
     * Add an absolute temperature to this temperature difference, and return a new absolute temperature. The unit of the return
     * value will be the unit of this temperature difference, and the reference point of the return value will be the reference
     * point of the given temperature. <code>R.add(A)</code> = unit of R and reference value of A.
     * @param absoluteTemperature the absolute temperature to add
     * @return the absolute temperature plus this temperature difference
     */
    public final Temperature add(final Temperature absoluteTemperature)
    {
        return new Temperature(new TemperatureDifference(absoluteTemperature.si() + si(), getDisplayUnit(), true),
                absoluteTemperature.getReference());
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

}
