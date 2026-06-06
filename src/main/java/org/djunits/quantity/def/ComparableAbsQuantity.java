package org.djunits.quantity.def;

import org.djutils.exceptions.Throw;

/**
 * ComparableAbsQuantity is an abstract class that stores the basic information about an absolute quantity that is comparable.
 * Note that two directions are not comparable based on the SI values, but, e.g., two temperatures are. All absolute quantities
 * wrap a relative Quantity and have a reference point that acts as an origin or zero point.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the relative quantity type
 * @param <R> the reference type to use for the absolute quantity
 */
public abstract class ComparableAbsQuantity<A extends ComparableAbsQuantity<A, Q, R>, Q extends Quantity<Q>,
        R extends Reference<R, A, Q>> extends AbsQuantity<A, Q, R> implements Comparable<A>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate an absolute quantity with a quantity and a reference.
     * @param quantity the relative quantity that indicates the 'distance' to the reference point
     * @param reference the reference point
     */
    public ComparableAbsQuantity(final Q quantity, final R reference)
    {
        super(quantity, reference);
    }

    /**********************************************************************************/
    /******************************* COMPARISON METHODS *******************************/
    /**********************************************************************************/

    /**
     * Test if this absolute quantity is less than another absolute quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is less than other; false otherwise
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
     * Test if this absolute quantity is less than or equal to another absolute quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is less than or equal to other; false otherwise
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
     * Test if this absolute quantity is greater than another absolute quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is greater than other; false otherwise
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
     * Test if this absolute quantity is greater than or equal to another absolute quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is greater than or equal to other; false otherwise
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
     * Test if this absolute quantity is equal to another absolute quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is equal to other; false otherwise
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    public boolean eq(final A other)
    {
        return si() == other.si() && getReference().equals(other.getReference());
    }

    /**
     * Test if this absolute quantity is not equal to another absolute quantity.
     * @param other the right hand side operand of the comparison
     * @return true if this is not equal to other; false otherwise
     * @throws IllegalArgumentException when the two absolute quantities have a different reference point
     */
    public boolean ne(final A other)
    {
        return si() != other.si() || !getReference().equals(other.getReference());
    }

    /**
     * Test if this absolute quantity is less than 0.0.
     * @return true if this is less than 0.0; false if this is not less than 0.0
     */
    public boolean lt0()
    {
        return si() < 0.0;
    }

    /**
     * Test if this absolute quantity is less than or equal to 0.0.
     * @return true if this is less than or equal to 0.0; false if this is not less than or equal to 0.0
     */
    public boolean le0()
    {
        return si() <= 0.0;
    }

    /**
     * Test if this absolute quantity is greater than 0.0.
     * @return true if this is greater than 0.0; false if this is not greater than 0.0
     */
    public boolean gt0()
    {
        return si() > 0.0;
    }

    /**
     * Test if this absolute quantity is greater than or equal to 0.0.
     * @return true if this is greater than or equal to 0.0; false if this is not greater than or equal to 0.0
     */
    public boolean ge0()
    {
        return si() >= 0.0;
    }

    /**
     * Test if this absolute quantity is equal to 0.0.
     * @return true if this is equal to 0.0; false if this is not equal to 0.0
     */
    public boolean eq0()
    {
        return si() == 0.0;
    }

    /**
     * Test if this absolute quantity is not equal to 0.0.
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
    public static <A extends ComparableAbsQuantity<A, Q, R>, Q extends Quantity<Q>,
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
    public static <A extends ComparableAbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> A max(final A quantity1, final A... quantities)
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
    public static <A extends ComparableAbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> A min(final A quantity1, final A... quantities)
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
    public static <A extends ComparableAbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> A mean(final A quantity1, final A... quantities)
    {
        int n = 1 + quantities.length;
        Q relSum = quantity1.getQuantity();
        for (A absq : quantities)
        {
            Throw.when(!quantity1.getReference().equals(absq.getReference()), IllegalArgumentException.class,
                    "mean operation not applicable to quantities with a different reference: %s <> %s",
                    quantity1.getReference().getId(), absq.getReference().getId());
            relSum = relSum.add(absq.getQuantity());
        }
        return quantity1.instantiate(relSum.divideBy(n), quantity1.getReference());
    }

}
