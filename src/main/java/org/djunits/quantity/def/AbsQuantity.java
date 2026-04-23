package org.djunits.quantity.def;

import java.util.Locale;
import java.util.Objects;

import org.djunits.formatter.FormatHint;
import org.djunits.formatter.Formatter;
import org.djunits.formatter.UnitHint;
import org.djunits.unit.Unit;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;
import org.djunits.value.Value;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * AbsQuantity is an abstract class that stores the basic information about a absolute quantity. An absolute quantity wraps a
 * relative Quantity and has a reference point that acts as an origin or zero point.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the relative quantity type
 * @param <R> the reference type to use for the absolute quantity
 */
public abstract class AbsQuantity<A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>>
        implements Value<A, Q>, Comparable<A>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The relative quantity. */
    private final Q quantity;

    /** The reference point. */
    private final R reference;

    /**
     * Instantiate an absolute quantity with a quantity and a reference.
     * @param quantity the relative quantity that indicates the 'distance' to the reference point
     * @param reference the reference point
     */
    public AbsQuantity(final Q quantity, final R reference)
    {
        Throw.whenNull(quantity, "quantity");
        Throw.whenNull(reference, "reference");
        this.quantity = quantity;
        this.reference = reference;
    }

    /**********************************************************************************/
    /******************************* UNIT-RELATED METHODS *****************************/
    /**********************************************************************************/

    @Override
    public Unit<?, Q> getDisplayUnit()
    {
        return this.quantity.getDisplayUnit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public A setDisplayUnit(final Unit<?, Q> newUnit)
    {
        this.quantity.setDisplayUnit(newUnit);
        return (A) this;
    }

    /**
     * Retrieve the relative quantity value in the current display unit.
     * @return the relative quantity value in the current display unit
     */
    public final double getInUnit()
    {
        return getDisplayUnit().getScale().fromBaseValue(si());
    }

    /**
     * Retrieve the relative quantity value converted into some specified unit.
     * @param targetUnit the unit to convert the relative quantity value into
     * @return the value of the relative quantity in the target unit
     */
    public final double getInUnit(final Unit<?, Q> targetUnit)
    {
        return targetUnit.getScale().fromBaseValue(si());
    }

    /**
     * Return the (relative) quantity relative to the reference.
     * @return the (relative) quantity relative to the reference
     */
    public Q getQuantity()
    {
        return this.quantity;
    }

    /**
     * Return the reference point (zero or origin).
     * @return the reference point
     */
    public R getReference()
    {
        return this.reference;
    }

    /**
     * Return the "pretty" and localized name of the quantity.
     * @return the "pretty" and localized name of the quantity
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
        return this.quantity.si();
    }

    /**
     * Instantiate an absolute quantity with a quantity and a reference.
     * @param quantity the relative quantity that indicates the 'distance' to the reference point
     * @param reference the reference point
     * @return the absolute quantity with a quantity and a reference
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public abstract A instantiate(Q quantity, R reference);

    /**********************************************************************************/
    /******************************* COMPARISON METHODS *******************************/
    /**********************************************************************************/

    /**
     * Test if this Quantity is less than another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is less than o; false otherwise
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    public boolean lt(final A other)
    {
        Throw.when(!getReference().equals(other.getReference()), IllegalArgumentException.class,
                "lt operator not applicable to quantities with a different reference: %s <> %s", getReference().getId(),
                other.getReference().getId());
        return si() < other.si();
    }

    /**
     * Test if this Quantity is less than or equal to another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is less than or equal to o; false otherwise
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    public boolean le(final A other)
    {
        Throw.when(!getReference().equals(other.getReference()), IllegalArgumentException.class,
                "le operator not applicable to quantities with a different reference: %s <> %s", getReference().getId(),
                other.getReference().getId());
        return si() <= other.si();
    }

    /**
     * Test if this Quantity is greater than another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is greater than o; false otherwise
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    public boolean gt(final A other)
    {
        Throw.when(!getReference().equals(other.getReference()), IllegalArgumentException.class,
                "gt operator not applicable to quantities with a different reference: %s <> %s", getReference().getId(),
                other.getReference().getId());
        return si() > other.si();
    }

    /**
     * Test if this Quantity is greater than or equal to another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is greater than or equal to o; false otherwise
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    public boolean ge(final A other)
    {
        Throw.when(!getReference().equals(other.getReference()), IllegalArgumentException.class,
                "ge operator not applicable to quantities with a different reference: %s <> %s", getReference().getId(),
                other.getReference().getId());
        return si() >= other.si();
    }

    /**
     * Test if this Quantity is equal to another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is equal to o; false otherwise
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    public boolean eq(final A other)
    {
        return si() == other.si() && getReference().equals(other.getReference());
    }

    /**
     * Test if this Quantity is not equal to another Quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is not equal to o; false otherwise
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    public boolean ne(final A other)
    {
        return si() != other.si() || !getReference().equals(other.getReference());
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

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    @Override
    public final int compareTo(final A other)
    {
        Throw.when(!getReference().equals(other.getReference()), IllegalArgumentException.class,
                "Comparable operator not applicable to quantities with a different reference: %s <> %s", getReference().getId(),
                other.getReference().getId());
        return Double.compare(this.si(), other.si());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.quantity, this.reference);
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
        AbsQuantity<?, ?, ?> other = (AbsQuantity<?, ?, ?>) obj;
        return Objects.equals(this.quantity, other.quantity) && Objects.equals(this.reference, other.reference);
    }

    /**********************************************************************************/
    /********************************** PARSING METHODS *******************************/
    /**********************************************************************************/

    /**
     * Returns an absolute quantity for the textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into the quantity
     * @param example an example instance to deliver
     * @param reference the reference point
     * @return the absolute quantity representation of the value with its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     * @param <A> the absolute quantity type
     * @param <Q> the relative quantity type
     * @param <R> the reference type to use for the absolute quantity
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> A valueOf(final String text, final A example, final R reference)
    {
        Throw.whenNull(example, "Error parsing AbsQuantity: example is null");
        String quantityClass = example.getClass().getSimpleName();
        Throw.whenNull(text, "Error parsing AbsQuantity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing %s: empty text to parse", quantityClass);
        Throw.whenNull(reference, "Error parsing AbsQuantity: reference is null");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            @SuppressWarnings("unchecked")
            Unit<?, Q> unit = (Unit<?, Q>) Units.resolve(example.getDisplayUnit().getClass(), unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity %s", unitString,
                    quantityClass);
            return example.instantiate(example.getQuantity().instantiate(d, unit), reference);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing " + quantityClass + " from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns an absolute quantity based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @param example an absolute example instance to deliver
     * @param reference the reference point
     * @return the absolute quantity representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     * @param <A> the absolute quantity type
     * @param <Q> the relative quantity type
     * @param <R> the reference type to use for the absolute quantity
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> A of(
            final double valueInUnit, final String unitString, final A example, final R reference)
    {
        Throw.whenNull(example, "Error parsing AbsQuantity: example is null");
        String quantityClass = example.getClass().getSimpleName();
        Throw.whenNull(unitString, "Error parsing %s: unitString is null", quantityClass);
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing %s: empty unitString",
                quantityClass);
        Throw.whenNull(reference, "Error parsing AbsQuantity: reference is null");
        @SuppressWarnings("unchecked")
        Unit<?, Q> unit = (Unit<?, Q>) Units.resolve(example.getDisplayUnit().getClass(), unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing %s with unit %s", quantityClass, unitString);
        return example.instantiate(example.getQuantity().instantiate(valueInUnit, unit), reference);
    }

    /**********************************************************************************/
    /*************************** STRING AND FORMATTING METHODS ************************/
    /**********************************************************************************/

    /**
     * Return the quantity relative to another reference point.
     * @param otherReference the reference point to which it has to be defined relatively.
     * @return the absolute quantity relative to the other reference point
     * @throws IllegalArgumentException when there is no translation from the current reference point to the provided reference
     */
    @SuppressWarnings({"unchecked", "checkstyle:needbraces"})
    public A relativeTo(final R otherReference)
    {
        if (getReference().equals(otherReference))
            return (A) this;
        if (getReference().equals(otherReference.getOffsetReference()))
            return instantiate(getQuantity().subtract(otherReference.getOffset()), otherReference);
        var offsetReference = getReference().getOffsetReference();
        Throw.when(offsetReference == null, IllegalArgumentException.class,
                "Reference %s cannot be transformed to a base reference for a transformation", getReference().getId());
        if (offsetReference.equals(otherReference))
            return instantiate(getQuantity().add(getReference().getOffset()), otherReference);
        if (otherReference.getOffsetReference().equals(offsetReference))
            return instantiate(getQuantity().add(getReference().getOffset()).subtract(otherReference.getOffset()),
                    otherReference);
        throw new IllegalArgumentException(String.format("Reference %s cannot be transformed to reference %s",
                getReference().getId(), otherReference.getId()));
    }

    /**
     * Concise description of this value.
     * @return a String with the value, non-verbose, with the unit attached.
     */
    @Override
    public String toString()
    {
        return toString(new FormatHint[] {});
    }

    /**
     * String representation of this value after applying the format hints.
     * @param hints the format hints to apply on the quantity
     * @return a String representation of this quantity, formatted according to the format hints
     */
    public String toString(final FormatHint... hints)
    {
        return Formatter.formatAbsQuantity(this, hints);
    }

    /**
     * String representation of the value expressed in the specified unit.
     * @param targetUnit the unit into which the values are converted for display
     * @return printable string with the value contents expressed in the specified unit
     */
    @Override
    public String toString(final Unit<?, Q> targetUnit)
    {
        return toString(UnitHint.setDisplayUnit(targetUnit));
    }

    /**********************************************************************************/
    /************************************ OPERATIONS **********************************/
    /**********************************************************************************/

    /**
     * Subtract two absolute quantities from each other, resulting in the corresponding relative quantity. The unit of the
     * resulting quantity will be the unit of 'this' absolute quantity. Quantity 'other' will be transformed to the reference
     * point of this absolute quantity. If the reference points of this and other are different, and no transformations between
     * the reference points exist, an exception will be thrown.
     * @param other the absolute quantity to subtract
     * @return the relative quantity as a result of the subtraction
     * @throws IllegalArgumentException when the reference points are unequal and cannot be transformed to each other
     */
    public abstract Q subtract(A other);

    /**
     * Add a relative quantity to this absolute quantity, resulting in a new absolute quantity containing the sum. The new
     * quantity will have the same reference point and unit as this absolute quantity.
     * @param other the relative quantity to add
     * @return the absolute quantity as a result of the addition
     */
    public abstract A add(Q other);

    /**
     * Subtract a relative quantity from this absolute quantity, resulting in a new absolute quantity containing the difference.
     * The new quantity will have the same reference point and unit as this absolute quantity.
     * @param other the relative quantity to subtract
     * @return the absolute quantity as a result of the subtraction
     */
    public abstract A subtract(Q other);

    @Override
    public boolean isRelative()
    {
        return false;
    }

    /**********************************************************************************/
    /********************* STATIC OPERATIONS ON MULTIPLE QUANTITIES *******************/
    /**********************************************************************************/

    /**
     * Interpolate between two absolute quantities. Note that the first quantities does not have to be smaller than the second.
     * @param zero the quantity at a ratio of zero
     * @param one the quantity at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Quantity at the given ratio between 0 and 1
     * @param <A> the absolute quantity type
     * @param <Q> the relative quantity type
     * @param <R> the reference type to use for the absolute quantity
     * @throws IllegalArgumentException when absolute quantities have a different reference point
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> A interpolate(final A zero, final A one, final double ratio)
    {
        Throw.when(!zero.getReference().equals(one.getReference()), IllegalArgumentException.class,
                "inperpolate operation not applicable to quantities with a different reference: %s <> %s",
                zero.getReference().getId(), one.getReference().getId());
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        Q quantity =
                zero.getQuantity().instantiateSi(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio)
                        .setDisplayUnit(zero.getDisplayUnit());
        return zero.instantiate(quantity, zero.getReference());
    }

    /**
     * Return the maximum value of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the maximum value of more than two quantities
     * @param <A> the absolute quantity type
     * @param <Q> the relative quantity type
     * @param <R> the reference type to use for the absolute quantity
     * @throws IllegalArgumentException when absolute quantities have a different reference point
     */
    @SafeVarargs
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> A max(final A quantity1,
            final A... quantities)
    {
        A maxA = quantity1;
        for (A absq : quantities)
        {
            Throw.when(!quantity1.getReference().equals(absq.getReference()), IllegalArgumentException.class,
                    "max operation not applicable to quantities with a different reference: %s <> %s",
                    quantity1.getReference().getId(), absq.getReference().getId());
            if (absq.gt(maxA))
            {
                maxA = absq;
            }
        }
        return maxA;
    }

    /**
     * Return the minimum value of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the minimum value of more than two quantities
     * @param <A> the absolute quantity type
     * @param <Q> the relative quantity type
     * @param <R> the reference type to use for the absolute quantity
     * @throws IllegalArgumentException when absolute quantities have a different reference point
     */
    @SafeVarargs
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> A min(final A quantity1,
            final A... quantities)
    {
        A minA = quantity1;
        for (A absq : quantities)
        {
            Throw.when(!quantity1.getReference().equals(absq.getReference()), IllegalArgumentException.class,
                    "min operation not applicable to quantities with a different reference: %s <> %s",
                    quantity1.getReference().getId(), absq.getReference().getId());
            if (absq.lt(minA))
            {
                minA = absq;
            }
        }
        return minA;
    }

    /**
     * Return the sum of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the sum of the quantities
     * @param <A> the absolute quantity type
     * @param <Q> the relative quantity type
     * @param <R> the reference type to use for the absolute quantity
     * @throws IllegalArgumentException when absolute quantities have a different reference point
     */
    @SafeVarargs
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> A sum(final A quantity1,
            final A... quantities)
    {
        double sum = quantity1.si();
        for (A absq : quantities)
        {
            Throw.when(!quantity1.getReference().equals(absq.getReference()), IllegalArgumentException.class,
                    "sum operation not applicable to quantities with a different reference: %s <> %s",
                    quantity1.getReference().getId(), absq.getReference().getId());
            sum += absq.si();
        }
        return quantity1.instantiate(quantity1.getQuantity().instantiateSi(sum).setDisplayUnit(quantity1.getDisplayUnit()),
                quantity1.getReference());
    }

    /**
     * Return the mean of one or more quantities.
     * @param quantity1 the first quantity
     * @param quantities the other quantities
     * @return the mean of the quantities
     * @param <A> the absolute quantity type
     * @param <Q> the relative quantity type
     * @param <R> the reference type to use for the absolute quantity
     * @throws IllegalArgumentException when absolute quantities have a different reference point
     */
    @SafeVarargs
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> A mean(final A quantity1, final A... quantities)
    {
        // the possible exception is thrown by sum()
        int n = 1 + quantities.length;
        return quantity1.instantiate(sum(quantity1, quantities).getQuantity().divideBy(n), quantity1.getReference());
    }

}
