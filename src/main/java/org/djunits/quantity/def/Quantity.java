package org.djunits.quantity.def;

import java.util.Locale;
import java.util.Objects;

import org.djunits.formatter.Format;
import org.djunits.quantity.SIQuantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.value.Additive;
import org.djunits.value.Scalable;
import org.djunits.value.Value;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

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
public abstract class Quantity<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends Number
        implements Value<U, Q>, Comparable<Q>, Additive<Q>, Scalable<Q>
{
    /** */
    private static final long serialVersionUID = 600L;

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
        Throw.whenNull(displayUnit, "displayUnit");
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

    @SuppressWarnings("unchecked")
    @Override
    public Q setDisplayUnit(final U newUnit)
    {
        this.displayUnit = newUnit;
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
     * @return the double value of this quantity expressed in the target unit
     */
    public final double getInUnit(final U targetUnit)
    {
        return targetUnit.getScale().fromBaseValue(si());
    }

    /**
     * Return the "pretty" name of the quantity.
     * @return the "pretty" name of the quantity
     */
    public String getName()
    {
        String name = Units.localizedQuantityName(Locale.getDefault(), getClass().getSimpleName());
        final StringBuilder sb = new StringBuilder(name.length() + 8);
        sb.append(name.charAt(0)); // keep first character exactly as-is
        for (int i = 1; i < name.length(); i++)
        {
            final char c = name.charAt(i);
            if (Character.isUpperCase(c))
            {
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ')
                {
                    sb.append(' ');
                }
                sb.append(Character.toLowerCase(c));
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**********************************************************************************/
    /******************************** SI-RELATED METHODS ******************************/
    /**********************************************************************************/

    /**
     * Return the SI unit of this quantity.
     * @return the SI unit of this quantity
     */
    public SIUnit siUnit()
    {
        return getDisplayUnit().siUnit();
    }

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
     * @param siValue the value expressed in the base (SI) unit
     * @return a quantity with the given SI-value and base (SI) unit
     */
    public abstract Q instantiate(double siValue);

    /**
     * Instantiate a quantity with a value and a unit.
     * @param value the double value, experessed in the unit
     * @param unit the unit
     * @return a quantity with the given value and display unit
     */
    public Q instantiate(final double value, final U unit)
    {
        return instantiate(unit.toBaseValue(value)).setDisplayUnit(unit);
    }

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
     * @param other the right hand side operand of the comparison
     * @return true if this is less than o; false otherwise
     */
    public boolean lt(final Q other)
    {
        return si() < other.si();
    }

    /**
     * Test if this Quantity is less than or equal to another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is less than or equal to o; false otherwise
     */
    public boolean le(final Q other)
    {
        return si() <= other.si();
    }

    /**
     * Test if this Quantity is greater than another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is greater than o; false otherwise
     */
    public boolean gt(final Q other)
    {
        return si() > other.si();
    }

    /**
     * Test if this Quantity is greater than or equal to another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is greater than or equal to o; false otherwise
     */
    public boolean ge(final Q other)
    {
        return si() >= other.si();
    }

    /**
     * Test if this Quantity is equal to another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is equal to o; false otherwise
     */
    public boolean eq(final Q other)
    {
        return si() == other.si();
    }

    /**
     * Test if this Quantity is not equal to another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is not equal to o; false otherwise
     */
    public boolean ne(final Q other)
    {
        return si() != other.si();
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
    public final int compareTo(final Q other)
    {
        return Double.compare(this.si(), other.si());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.displayUnit, this.si);
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quantity<?, ?> other = (Quantity<?, ?>) obj;
        return Objects.equals(this.displayUnit, other.displayUnit)
                && Double.doubleToLongBits(this.si) == Double.doubleToLongBits(other.si);
    }

    /**********************************************************************************/
    /********************************** PARSING METHODS *******************************/
    /**********************************************************************************/

    /**
     * Returns a quantity for the textual representation of a value with a unit. The String representation that can be parsed is
     * the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text the textual representation to parse into the quantity
     * @param example an example instance to deliver
     * @return the quantity representation of the value with its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Q valueOf(final String text, final Q example)
    {
        Throw.whenNull(example, "Error parsing Quantity: example is null");
        String quantityClass = example.getClass().getSimpleName();
        Throw.whenNull(text, "Error parsing Quantity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing %s: empty text to parse", quantityClass);
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            @SuppressWarnings("unchecked")
            U unit = (U) Units.resolve(example.getDisplayUnit().getClass(), unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity %s", unitString,
                    quantityClass);
            return example.instantiate(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing " + quantityClass + " from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a quantity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @param example an example instance to deliver
     * @return the quantity representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Q of(final double value, final String unitString,
            final Q example)
    {
        Throw.whenNull(example, "Error parsing Quantity: example is null");
        String quantityClass = example.getClass().getSimpleName();
        Throw.whenNull(unitString, "Error parsing %s: unitString is null", quantityClass);
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing %s: empty unitString",
                quantityClass);
        @SuppressWarnings("unchecked")
        U unit = (U) Units.resolve(example.getDisplayUnit().getClass(), unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing %s with unit %s", quantityClass, unitString);
        return example.instantiate(value, unit);
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
    @SuppressWarnings("checkstyle:hiddenfield")
    public String toString(final U displayUnit, final boolean verbose, final boolean withUnit)
    {
        StringBuffer buf = new StringBuffer();
        if (verbose)
        {
            buf.append("Rel ");
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
        return format(getInUnit()) + " " + displayUnit.getTextualAbbreviation();
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

    /**********************************************************************************/
    /********************* STATIC OPERATIONS ON MULTIPLE QUANTITIES *******************/
    /**********************************************************************************/

    /**
     * Interpolate between two quantities. Note that the first quantities does not have to be smaller than the second.
     * @param zero the quantity at a ratio of zero
     * @param one the quantity at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Quantity at the given ratio between 0 and 1
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Q interpolate(final Q zero, final Q one,
            final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return zero.instantiate(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio)
                .setDisplayUnit(zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the maximum value of the quantities
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SafeVarargs
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Q max(final Q quantity1, final Q... quantities)
    {
        Q maxQ = quantity1;
        for (Q quantity : quantities)
        {
            if (quantity.gt(maxQ))
            {
                maxQ = quantity;
            }
        }
        return maxQ;
    }

    /**
     * Return the minimum value of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the minimum value of more than two quantities
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SafeVarargs
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Q min(final Q quantity1, final Q... quantities)
    {
        Q minQ = quantity1;
        for (Q quantity : quantities)
        {
            if (quantity.lt(minQ))
            {
                minQ = quantity;
            }
        }
        return minQ;
    }

    /**
     * Return the sum of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the sum of the quantities
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SafeVarargs
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Q sum(final Q quantity1, final Q... quantities)
    {
        double sum = quantity1.si();
        for (Q quantity : quantities)
        {
            sum += quantity.si();
        }
        return quantity1.instantiate(sum).setDisplayUnit(quantity1.getDisplayUnit());
    }

    /**
     * Return the product of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the product of the quantities
     */
    @SafeVarargs
    public static SIQuantity product(final Quantity<?, ?> quantity1, final Quantity<?, ?>... quantities)
    {
        double product = quantity1.si();
        SIUnit unit = quantity1.siUnit();
        for (var quantity : quantities)
        {
            product *= quantity.si();
            unit = unit.plus(quantity.siUnit());
        }
        return new SIQuantity(product, unit);
    }

    /**
     * Return the mean of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the mean of the quantities
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    @SafeVarargs
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Q mean(final Q quantity1, final Q... quantities)
    {
        int n = 1 + quantities.length;
        return sum(quantity1, quantities).divideBy(n);
    }

    /***********************************************************************************/
    /********************************* RELATIVE METHODS ********************************/
    /***********************************************************************************/

    @Override
    public Q add(final Q increment)
    {
        return instantiate(si() + increment.si()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q subtract(final Q decrement)
    {
        return instantiate(si() - decrement.si()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q abs()
    {
        return instantiate(Math.abs(si())).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q negate()
    {
        return instantiate(-si()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q scaleBy(final double factor)
    {
        return instantiate(si() * factor).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Multiply this quantity with another quantity, and return a SIQuantity as the result.
     * @param quantity the quantity to multiply with
     * @return the multiplication of this quantity and the given quantity
     */
    public SIQuantity multiply(final Quantity<?, ?> quantity)
    {
        SIUnit siUnit = SIUnit.add(siUnit(), quantity.siUnit());
        return new SIQuantity(si() * quantity.si(), siUnit);
    }

    /**
     * Divide this quantity by another quantity, and return a SIQuantity as the result.
     * @param quantity the quantity to divide by
     * @return the division of this quantity and the given quantity
     */
    public SIQuantity divide(final Quantity<?, ?> quantity)
    {
        SIUnit siUnit = SIUnit.subtract(siUnit(), quantity.siUnit());
        return new SIQuantity(si() / quantity.si(), siUnit);
    }

    /**
     * Return the reciprocal of this quantity (1/q).
     * @return the reciprocal of this quantity, with the correct SI units
     */
    public Quantity<?, ?> reciprocal()
    {
        return new SIQuantity(1.0 / si(), this.siUnit().invert());
    }

    /**
     * Return the quantity 'as' a known quantity, using a unit to express the result in. Throw a Runtime exception when the SI
     * units of this quantity and the target quantity do not match.
     * @param targetUnit the unit to convert the quantity to
     * @return a quantity typed in the target quantity class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> TQ as(final TU targetUnit)
            throws IllegalArgumentException
    {
        Throw.when(!siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit, siUnit().getDisplayAbbreviation(),
                targetUnit.siUnit().getDisplayAbbreviation());
        return targetUnit.ofSi(si()).setDisplayUnit(targetUnit);
    }

    @Override
    public boolean isRelative()
    {
        return true;
    }

}
