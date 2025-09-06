package org.djunits.value.base;

import org.djunits.unit.Unit;
import org.djunits.value.Value;
import org.djutils.exceptions.Throw;

/**
 * Scalar to distinguish a scalar from vectors and matrices.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit
 * @param <S> the scalar type with the given unit
 */
public abstract class Scalar<U extends Unit<U>, S extends Scalar<U, S>> extends Number implements Value<U, S>, Comparable<S>
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /** The display unit of this AbstractScalar. */
    private U displayUnit;

    /**
     * Construct a new Scalar.
     * @param displayUnit the unit of the new AbstractScalar
     */
    protected Scalar(final U displayUnit)
    {
        Throw.whenNull(displayUnit, "display unit cannot be null");
        this.displayUnit = displayUnit;
    }

    @Override
    public final U getDisplayUnit()
    {
        return this.displayUnit;
    }

    @SuppressWarnings("unchecked")
    @Override
    public S setDisplayUnit(final U newUnit)
    {
        Throw.whenNull(newUnit, "newUnit may not be null");
        this.displayUnit = newUnit;
        return (S) this;
    }

    // No hashcode or equals -- has to be implemented on a deeper level

    /**
     * Test if this DoubleScalar is less than another DoubleScalar.
     * @param o the right hand side operand of the comparison
     * @return true if this is less than o; false otherwise
     */
    public abstract boolean lt(S o);

    /**
     * Test if this DoubleScalar is less than or equal to another DoubleScalar.
     * @param o the right hand side operand of the comparison
     * @return true if this is less than or equal to o; false otherwise
     */
    public abstract boolean le(S o);

    /**
     * Test if this DoubleScalar is greater than another DoubleScalar.
     * @param o the right hand side operand of the comparison
     * @return true if this is greater than o; false otherwise
     */
    public abstract boolean gt(S o);

    /**
     * Test if this DoubleScalar is greater than or equal to another DoubleScalar.
     * @param o the right hand side operand of the comparison
     * @return true if this is greater than or equal to o; false otherwise
     */
    public abstract boolean ge(S o);

    /**
     * Test if this DoubleScalar is equal to another DoubleScalar.
     * @param o the right hand side operand of the comparison
     * @return true if this is equal to o; false otherwise
     */
    public abstract boolean eq(S o);

    /**
     * Test if this DoubleScalar is not equal to another DoubleScalar.
     * @param o the right hand side operand of the comparison
     * @return true if this is not equal to o; false otherwise
     */
    public abstract boolean ne(S o);

    /**
     * Test if this DoubleScalar is less than 0.0.
     * @return true if this is less than 0.0; false if this is not less than 0.0
     */
    public abstract boolean lt0();

    /**
     * Test if this DoubleScalar is less than or equal to 0.0.
     * @return true if this is less than or equal to 0.0; false if this is not less than or equal to 0.0
     */
    public abstract boolean le0();

    /**
     * Test if this DoubleScalar is greater than 0.0.
     * @return true if this is greater than 0.0; false if this is not greater than 0.0
     */
    public abstract boolean gt0();

    /**
     * Test if this DoubleScalar is greater than or equal to 0.0.
     * @return true if this is greater than or equal to 0.0; false if this is not greater than or equal to 0.0
     */
    public abstract boolean ge0();

    /**
     * Test if this DoubleScalar is equal to 0.0.
     * @return true if this is equal to 0.0; false if this is not equal to 0.0
     */
    public abstract boolean eq0();

    /**
     * Test if this DoubleScalar is not equal to 0.0.
     * @return true if this is not equal to 0.0; false if this is equal to 0.0
     */
    public abstract boolean ne0();

    /**
     * Concise textual representation of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @return a String with the value with the default textual representation of the unit attached.
     */
    public abstract String toTextualString();

    /**
     * Concise textual representation of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @param withDisplayUnit the display unit for the value
     * @return a String with the value with the default textual representation of the provided unit attached.
     */
    public abstract String toTextualString(U withDisplayUnit);

    /**
     * Concise display description of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @return a String with the value with the default display representation of the unit attached.
     */
    public abstract String toDisplayString();

    /**
     * Concise display description of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @param withDisplayUnit the display unit for the value
     * @return a String with the value with the default display representation of the provided unit attached.
     */
    public abstract String toDisplayString(U withDisplayUnit);

    /**
     * Format a string according to the current locale and the standard (minimized) format, such as "3.14" or "300.0".
     * @param d the number to format
     * @return the formatted number using the current Locale
     */
    public String format(final double d)
    {
        if (d < 1E-5 || d > 1E5)
        {
            return format(d, "%E");
        }
        return format(d, "%f");
    }

    /**
     * Format a string according to the current locale and the provided format string.
     * @param d the number to format
     * @param format the formatting string to use for the number
     * @return the formatted number using the current Locale and the format string
     */
    public String format(final double d, final String format)
    {
        String s = String.format(format, d);
        if (s.contains("e") || s.contains("E"))
        {
            return s;
        }
        while (s.endsWith("0") && s.length() > 2)
        {
            s = s.substring(0, s.length() - 1);
        }
        String last = s.substring(s.length() - 1);
        if (!"01234567890".contains(last))
        {
            s += "0";
        }
        return s;
    }

}
