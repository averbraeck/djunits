package org.djunits.quantity;

import org.djunits.formatter.Format;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.value.Value;

/**
 * Quantity is an abstract class that stores the basic information about a quantity. A physical quantity can be expressed as a
 * value, which is the combination of a numerical value and a unit of measurement. The type of physical quantity is encoded in
 * the class (Length, Speed, Area, etc.) with its associated (base) unit of measurement, whereas the numerical value is stored
 * in the si field. Additionally, each quantity has a displayUnit that gives the preference for the (scaled) display of the
 * quantity, e.g., in a toString() method.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public abstract class Quantity<Q extends Quantity<Q, U>, U extends AbstractUnit<U>> extends Number
        implements Value<Q, U>, Comparable<Q>
{
    /** */
    private static final long serialVersionUID = 500L;

    /** The si value. */
    private final double si;

    /** The display unit. */
    private U displayUnit;

    /**
     * Instantiate a quantity with a value and a display unit.
     * @param value the value expressed in the display unit
     * @param displayUnit the display unit to use
     */
    public Quantity(final double value, final U displayUnit)
    {
        this.si = displayUnit.toBaseValue(value);
        this.displayUnit = displayUnit;
    }

    /**********************************************************************************/
    /******************************* UNIT-RELATED METHODS *****************************/
    /**********************************************************************************/

    @Override
    public U getDisplayUnit()
    {
        return this.displayUnit;
    }

    @SuppressWarnings({"unchecked", "checkstyle:hiddenfield"})
    @Override
    public Q setDisplayUnit(final U displayUnit)
    {
        this.displayUnit = displayUnit;
        return (Q) this;
    }

    /**
     * Retrieve the value in the current display unit.
     * @return the value in the current display unit
     */
    public final double getInUnit()
    {
        return getDisplayUnit().getScale().fromBaseValue(si());
    }

    /**
     * Retrieve the value converted into some specified unit.
     * @param targetUnit the unit to convert the value into
     * @return double
     */
    public final double getInUnit(final U targetUnit)
    {
        return targetUnit.getScale().fromBaseValue(si());
    }

    /**********************************************************************************/
    /******************************** SI-RELATED METHODS ******************************/
    /**********************************************************************************/

    /**
     * Return the SI dimensions of this quantity.
     * @return the SI dimensions of this quantity
     */
    public abstract SIDimensions siDimensions();

    /**
     * Return the SI value of the quantity.
     * @return the SI value of the quantity
     */
    public double si()
    {
        return this.si;
    }

    /**
     * Instantiate a quantity with an SI or base value.
     * @param si the value expressed in the display unit
     * @return a quantity with the given SI-value and base display unit
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public abstract Q ofSi(double si);

    /**********************************************************************************/
    /********************************* NUMBER METHODS *********************************/
    /**********************************************************************************/

    @Override
    public double doubleValue()
    {
        return si();
    }

    @Override
    public int intValue()
    {
        return (int) Math.round(si());
    }

    @Override
    public long longValue()
    {
        return Math.round(si());
    }

    @Override
    public float floatValue()
    {
        return (float) si();
    }

    /**
     * Test if this Quantity is less than another Quantity.
     * @param o the right hand side operand of the comparison
     * @return true if this is less than o; false otherwise
     */
    public boolean lt(final Q o)
    {
        return si() < o.si();
    }

    /**
     * Test if this Quantity is less than or equal to another Quantity.
     * @param o the right hand side operand of the comparison
     * @return true if this is less than or equal to o; false otherwise
     */
    public boolean le(final Q o)
    {
        return si() <= o.si();
    }

    /**
     * Test if this Quantity is greater than another Quantity.
     * @param o the right hand side operand of the comparison
     * @return true if this is greater than o; false otherwise
     */
    public boolean gt(final Q o)
    {
        return si() > o.si();
    }

    /**
     * Test if this Quantity is greater than or equal to another Quantity.
     * @param o the right hand side operand of the comparison
     * @return true if this is greater than or equal to o; false otherwise
     */
    public boolean ge(final Q o)
    {
        return si() >= o.si();
    }

    /**
     * Test if this Quantity is equal to another Quantity.
     * @param o the right hand side operand of the comparison
     * @return true if this is equal to o; false otherwise
     */
    public boolean eq(final Q o)
    {
        return si() == o.si();
    }

    /**
     * Test if this Quantity is not equal to another Quantity.
     * @param o the right hand side operand of the comparison
     * @return true if this is not equal to o; false otherwise
     */
    public boolean ne(final Q o)
    {
        return si() != o.si();
    }

    /**
     * Test if this Quantity is less than 0.0.
     * @return true if this is less than 0.0; false if this is not less than 0.0
     */
    public boolean lt0()
    {
        return si() < 0.0;
    }

    /**
     * Test if this Quantity is less than or equal to 0.0.
     * @return true if this is less than or equal to 0.0; false if this is not less than or equal to 0.0
     */
    public boolean le0()
    {
        return si() <= 0.0;
    }

    /**
     * Test if this Quantity is greater than 0.0.
     * @return true if this is greater than 0.0; false if this is not greater than 0.0
     */
    public boolean gt0()
    {
        return si() > 0.0;
    }

    /**
     * Test if this Quantity is greater than or equal to 0.0.
     * @return true if this is greater than or equal to 0.0; false if this is not greater than or equal to 0.0
     */
    public boolean ge0()
    {
        return si() >= 0.0;
    }

    /**
     * Test if this Quantity is equal to 0.0.
     * @return true if this is equal to 0.0; false if this is not equal to 0.0
     */
    public boolean eq0()
    {
        return si() == 0.0;
    }

    /**
     * Test if this Quantity is not equal to 0.0.
     * @return true if this is not equal to 0.0; false if this is equal to 0.0
     */
    public boolean ne0()
    {
        return si() != 0.0;
    }

    @Override
    public final int compareTo(final Q o)
    {
        return Double.compare(this.si(), o.si());
    }

    /**********************************************************************************/
    /*************************** STRING AND FORMATTING METHODS ************************/
    /**********************************************************************************/

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

    /**
     * Concise description of this value.
     * @return a String with the value, non-verbose, with the unit attached.
     */
    @Override
    public String toString()
    {
        return toString(getDisplayUnit(), false, true);
    }

    /**
     * Somewhat verbose description of this value with the values expressed in the specified unit.
     * @param displayUnit the unit into which the values are converted for display
     * @return printable string with the value contents expressed in the specified unit
     */
    @Override
    @SuppressWarnings("checkstyle:hiddenfield")
    public String toString(final U displayUnit)
    {
        return toString(displayUnit, false, true);
    }

    /**
     * Somewhat verbose description of this value with optional type and unit information.
     * @param verbose if true; include type info; if false; exclude type info
     * @param withUnit if true; include the unit; of false; exclude the unit
     * @return printable string with the value contents
     */
    @Override
    public String toString(final boolean verbose, final boolean withUnit)
    {
        return toString(getDisplayUnit(), verbose, withUnit);
    }

    /**
     * Somewhat verbose description of this value with the values expressed in the specified unit.
     * @param displayUnit the unit into which the values are converted for display
     * @param verbose if true; include type info; if false; exclude type info
     * @param withUnit if true; include the unit; of false; exclude the unit
     * @return printable string with the value contents
     */
    @Override
    @SuppressWarnings("checkstyle:hiddenfield")
    public String toString(final U displayUnit, final boolean verbose, final boolean withUnit)
    {
        StringBuffer buf = new StringBuffer();
        if (verbose)
        {
            buf.append(this instanceof Absolute ? "Abs " : "Rel ");
        }
        double d = getInUnit();
        buf.append(Format.format(d));
        if (withUnit)
        {
            buf.append(" "); // Insert one space as prescribed by SI writing conventions
            buf.append(displayUnit.getDisplayAbbreviation());
        }
        return buf.toString();
    }

    /**
     * Format this DoubleScalar in SI unit using prefixes when possible. If the value is too small or too large, e-notation and
     * the plain SI unit are used.
     * @return formatted value of this DoubleScalar
     */
    public String toStringSIPrefixed()
    {
        return toStringSIPrefixed(-30, 32);
    }

    /**
     * Format this DoubleScalar in SI unit using prefixes when possible and within the specified size range. If the value is too
     * small or too large, e-notation and the plain SI unit are used.
     * @param smallestPower the smallest exponent value that will be written using an SI prefix
     * @param biggestPower the largest exponent value that will be written using an SI prefix
     * @return formatted value of this DoubleScalar
     */
    public String toStringSIPrefixed(final int smallestPower, final int biggestPower)
    {
        // Override this method for weights, nonlinear units and DimensionLess.
        if (!Double.isFinite(this.si))
        {
            return toString(getDisplayUnit().getBaseUnit());
        }
        // PK: I can't think of an easier way to figure out what the exponent will be; rounding of the mantissa to the available
        // width makes this hard; This feels like an expensive way.
        String check = String.format(this.si >= 0 ? "%10.8E" : "%10.7E", this.si);
        int exponent = Integer.parseInt(check.substring(check.indexOf("E") + 1));
        if (exponent < -30 || exponent < smallestPower || exponent > 30 + 2 || exponent > biggestPower)
        {
            // Out of SI prefix range; do not scale.
            return String.format(this.si >= 0 ? "%10.4E" : "%10.3E", this.si) + " " + getDisplayUnit().getBaseUnit().getId();
        }
        Integer roundedExponent = (int) Math.ceil((exponent - 2.0) / 3) * 3;
        String key = SIPrefixes.FACTORS.get(roundedExponent).getDefaultTextualPrefix() + getDisplayUnit().getBaseUnit().getId();
        @SuppressWarnings({"unchecked", "checkstyle:hiddenfield"})
        U displayUnit = (U) Units.resolve(getDisplayUnit().getClass(), key);
        return toString(displayUnit);
    }

    /**
     * Concise textual representation of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @return a String with the value with the default textual representation of the unit attached.
     */
    public String toTextualString()
    {
        return toTextualString(getDisplayUnit());
    }

    /**
     * Concise textual representation of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @param displayUnit the display unit for the value
     * @return a String with the value with the default textual representation of the provided unit attached.
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public String toTextualString(final U displayUnit)
    {
        return format(getInUnit()) + " " + displayUnit.getDefaultTextualAbbreviation();
    }

    /**
     * Concise display description of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @return a String with the value with the default display representation of the unit attached.
     */
    public String toDisplayString()
    {
        return toDisplayString(getDisplayUnit());
    }

    /**
     * Concise display description of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @param displayUnit the display unit for the value
     * @return a String with the value with the default display representation of the provided unit attached.
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public String toDisplayString(final U displayUnit)
    {
        return format(getInUnit()) + " " + displayUnit.getDisplayAbbreviation();
    }

    /******************************************************************************************************/
    /******************************************************************************************************/
    /******************************************* RELATIVE QUANTITY ****************************************/
    /******************************************************************************************************/
    /******************************************************************************************************/

    /**
     * The Relative Quantity, with specific methods for a relative quantity that are not applicable for an absolute
     * quantity.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <R> The relative quantity type
     * @param <U> The unit type
     */
    public abstract static class Relative<R extends Relative<R, U>, U extends AbstractUnit<U>> extends Quantity<R, U>
    {
        /** */
        private static final long serialVersionUID = 500L;

        /**
         * Instantiate a relative quantity with a value and a display unit.
         * @param value the value expressed in the display unit
         * @param displayUnit the display unit to use
         */
        public Relative(final double value, final U displayUnit)
        {
            super(value, displayUnit);
        }

        /**
         * Add a Relative value to this Relative value. A new value is returned due to immutability.
         * @param increment the value to add
         * @return the sum of this value and the operand as a new object
         */
        public R plus(final R increment)
        {
            R result = ofSi(si() + increment.si());
            result.setDisplayUnit(getDisplayUnit());
            return result;
        }

        /**
         * Subtract a Relative value from this Relative value. A new value is returned due to immutability.
         * @param decrement the value to subtract
         * @return the difference of this value and the operand
         */
        R minus(final R decrement)
        {
            R result = ofSi(si() - decrement.si());
            result.setDisplayUnit(getDisplayUnit());
            return result;
        }

        /**
         * Return a new Scalar/Vector/Matrix with absolute value(s).
         * @return a new quantity with absolute value(s)
         */
        public R abs()
        {
            R result = ofSi(Math.abs(si()));
            result.setDisplayUnit(getDisplayUnit());
            return result;
        }

        /**
         * Return a new Scalar/Vector/Matrix with negated value(s).
         * @return a new R with negated value(s)
         */
        public R neg()
        {
            R result = ofSi(-si());
            result.setDisplayUnit(getDisplayUnit());
            return result;
        }

        /**
         * Multiply the quantity's value with a constant.
         * @param constant the multiplier
         * @return a new quantity with its value multiplied by a constant
         */
        public R times(final double constant)
        {
            R result = ofSi(si() * constant);
            result.setDisplayUnit(getDisplayUnit());
            return result;
        }

        /**
         * Divide the quantity's value by a constant.
         * @param constant the divisor
         * @return a new quantity with its value divided by a constant
         */
        public R divide(final double constant)
        {
            R result = ofSi(si() / constant);
            result.setDisplayUnit(getDisplayUnit());
            return result;
        }

    }

    /******************************************************************************************************/
    /******************************************************************************************************/
    /******************************************* ABSOLUTE QUANTITY ****************************************/
    /******************************************************************************************************/
    /******************************************************************************************************/

    /**
     * The Absolute Quantity, with specific methods for an absolute quantity that are not applicable for a relative
     * quantity.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> The absolute quantity type
     * @param <U> The unit type
     */
    public abstract static class Absolute<Q extends Absolute<Q, U>, U extends AbstractUnit<U>> extends Quantity<Q, U>
    {
        /** */
        private static final long serialVersionUID = 500L;

        /**
         * Instantiate a relative quantity with a value and a display unit.
         * @param value the value expressed in the display unit
         * @param displayUnit the display unit to use
         */
        public Absolute(final double value, final U displayUnit)
        {
            super(value, displayUnit);
        }

    }

}
