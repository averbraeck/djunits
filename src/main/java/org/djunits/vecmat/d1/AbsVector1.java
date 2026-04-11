package org.djunits.vecmat.d1;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsVector;
import org.djutils.exceptions.Throw;

/**
 * AbsVector1 implements a vector with one real-valued entry representing an absolute quantity. The vector is immutable, except
 * for the display unit, which can be changed. Some of the method that have been defined already for a generic vector have been
 * re-implemented for efficiency.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsVector1<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsVector<A, Q, AbsVector1<A, Q>, Vector1<Q>, AbsVector1<A, Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsVector1 with a display unit and a reference point.
     * @param relativeVector the vector with values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVector1(final Vector1<Q> relativeVector, final Reference<?, A, Q> reference)
    {
        super(relativeVector, reference);
    }

    @Override
    public AbsVector1<A, Q> instantiate(final Vector1<Q> relativeVector, final Reference<?, A, Q> reference)
    {
        return new AbsVector1<>(relativeVector, reference).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public AbsVector1<A, Q> transpose()
    {
        return this;
    }

    @Override
    public boolean isColumnVector()
    {
        return true;
    }

    @Override
    public boolean isRowVector()
    {
        return true;
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create an AbsVector1 without needing generics.
     * @param xInUnit the x-value expressed in the unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsVector1<A, Q> of(final double xInUnit, final Unit<?, Q> unit, final R reference)
    {
        return new AbsVector1<>(Vector1.of(xInUnit, unit), reference);
    }

    /**
     * Create an AbsVector1 without needing generics.
     * @param x the x-value expressed as a quantity
     * @param reference the reference point for the absolute quantities
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsVector1<A, Q> of(final Q x, final R reference)
    {
        return new AbsVector1<>(Vector1.of(x), reference);
    }

    /**
     * Create an AbsVector1 without needing generics.
     * @param absX the v1-value expressed as an absolute quantity
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsVector1<A, Q> of(final A absX)
    {
        Throw.whenNull(absX, "absX");
        return new AbsVector1<>(Vector1.of(absX.getQuantity()), absX.getReference());
    }

    /**
     * Create an AbsVector1 without needing generics.
     * @param dataInUnit the x-value expressed as an array in the display unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsVector1<A, Q> of(
            final double[] dataInUnit, final Unit<?, Q> unit, final R reference)
    {
        return new AbsVector1<>(Vector1.of(dataInUnit, unit), reference);
    }


    /**
     * Create an AbsVector1 without needing generics.
     * @param xSi the x-value expressed as an array in the SI units
     * @param displayUnit the display unit to use
     * @param reference the reference point for the absolute quantities
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsVector1<A, Q> ofSi(
            final double xSi, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsVector1<>(Vector1.ofSi(xSi, displayUnit), reference);
    }

    /**
     * Create an AbsVector1 without needing generics.
     * @param dataSi the x-value expressed as an array in the SI units
     * @param displayUnit the display unit to use
     * @param reference the reference point for the absolute quantities
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsVector1<A, Q> ofSi(
            final double[] dataSi, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsVector1<>(Vector1.ofSi(dataSi, displayUnit), reference);
    }

    /**
     * Create an AbsVector1 without needing generics.
     * @param data the x-value expressed as an array of quantities
     * @param reference the reference point for the absolute quantities
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsVector1<A, Q> of(final Q[] data, final R reference)
    {
        return new AbsVector1<>(Vector1.of(data), reference);
    }

    /**
     * Create an AbsVector1 without needing generics.
     * @param absData the {x} value expressed as an array of absolute quantities
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsVector1<A, Q> of(final A[] absData)
    {
        Throw.whenNull(absData, "absData");
        Throw.when(absData.length != 1, IllegalArgumentException.class, "absData.length != 1");
        return new AbsVector1<>(Vector1.of(absData[0].getQuantity()), absData[0].getReference());
    }

    /**
     * Create an AbsVector1 without needing generics.
     * @param relativeVector the relative vector
     * @param reference the reference point for the absolute quantities
     * @return a new AbsVector1 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsVector1<A, Q> of(final Vector1<Q> relativeVector, final R reference)
    {
        Throw.whenNull(relativeVector, "relativeVector");
        return new AbsVector1<>(relativeVector, reference);
    }

}
