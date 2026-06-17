package org.djunits.quantity.def;

import java.util.Locale;
import java.util.Objects;

import org.djunits.formatter.QuantityFormat;
import org.djunits.formatter.QuantityFormatter;
import org.djunits.quantity.Direction;
import org.djunits.quantity.Position;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.Time;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;
import org.djunits.value.Value;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * AbsQuantity stores the basic information about a absolute quantity and implements the basic operations that hold for all
 * absolute quantities. An absolute quantity wraps a relative Quantity and has a reference point that acts as an origin or zero
 * point. Note that the absolute quantity {@link Direction} directly extends {@link AbsQuantity} because of its circular scale.
 * The other absolute quantities {@link Position}, {@link Temperature} and {@link Time} extends {@link ComparableAbsQuantity}
 * that extends this class and implements comparators as well.
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
        implements Value<Q>
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
    public UnitInterface<?, Q> getDisplayUnit()
    {
        return this.quantity.getDisplayUnit();
    }

    /**
     * TODO: TEMP: KEEP BRIEFLY.
     * @param newUnit the new unit
     * @return the quantity for fluent design
     */
    @SuppressWarnings("unchecked")
    public A setDisplayUnit(final UnitInterface<?, Q> newUnit)
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
        return getDisplayUnit().getScale().fromIdentityScale(si());
    }

    /**
     * Retrieve the relative quantity value converted into some specified unit.
     * @param targetUnit the unit to convert the relative quantity value into
     * @return the value of the relative quantity in the target unit
     */
    public final double getInUnit(final UnitInterface<?, Q> targetUnit)
    {
        return targetUnit.getScale().fromIdentityScale(si());
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
    /******************************** HASHCODE AND EQUALS *****************************/
    /**********************************************************************************/

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
            UnitInterface<?, Q> unit = (UnitInterface<?, Q>) Units.resolve(example.getDisplayUnit().getClass(), unitString);
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
        UnitInterface<?, Q> unit = (UnitInterface<?, Q>) Units.resolve(example.getDisplayUnit().getClass(), unitString);
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
        if (offsetReference.equals(otherReference.getOffsetReference()))
            return instantiate(getQuantity().add(getReference().getOffset()).subtract(otherReference.getOffset()),
                    otherReference);
        throw new IllegalArgumentException(String.format("Reference %s cannot be transformed to reference %s",
                getReference().getId(), otherReference.getId()));
    }

    /**********************************************************************************/
    /*************************** STRING AND FORMATTING METHODS ************************/
    /**********************************************************************************/

    /**
     * Description of this quantity with default formatting.
     * @return a String with the value of the quantity, with the unit attached.
     */
    @Override
    public String toString()
    {
        return format();
    }

    /**
     * Concise description of this quantity.
     * @return a String with the value of the quantity, with the unit attached.
     */
    @Override
    public String format()
    {
        return format(QuantityFormat.instance());
    }

    /**
     * String representation of this quantity after applying the format.
     * @param format the format to apply for the quantity
     * @return a String representation of this quantity, formatted according to the given format
     */
    public String format(final QuantityFormat format)
    {
        return QuantityFormatter.format(this, format);
    }

    /**
     * String representation of this quantity, expressed in the specified unit.
     * @param targetUnit the unit into which the quantity is converted for display
     * @return printable string with the quantity value expressed in the specified unit
     */
    @Override
    public String format(final UnitInterface<?, Q> targetUnit)
    {
        return format(QuantityFormat.instance().setDisplayUnit(targetUnit));
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

}
