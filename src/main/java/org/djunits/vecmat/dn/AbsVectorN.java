package org.djunits.vecmat.dn;

import java.util.List;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsVector;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djutils.exceptions.Throw;

/**
 * AbsVectorN implements a vector with three real-valued entries representing an absolute quantity. The vector is immutable,
 * except for the display unit, which can be changed. Some of the method that have been defined already for a generic vector can
 * be re-implemented for efficiency.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 * @param <VA> the absolute vector or matrix type
 * @param <VQ> the relative vector or matrix type
 * @param <VAT> the type of the transposed version of the absolute vector
 */
public abstract class AbsVectorN<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>,
        VA extends AbsVectorN<A, Q, VA, VQ, VAT>, VQ extends VectorN<Q, VQ, ?, ?, ?>, VAT extends AbsVectorN<A, Q, VAT, ?, VA>>
        extends AbsVector<A, Q, VA, VQ, VAT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate an AbsVectorN.
     * @param relativeVector the vector with values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVectorN(final VQ relativeVector, final Reference<?, A, Q> reference)
    {
        super(relativeVector, reference);
    }

    /**
     * Column vector for AbsVectorN with absolute quantities.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static class Col<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
            extends AbsVectorN<A, Q, AbsVectorN.Col<A, Q>, VectorN.Col<Q>, AbsVectorN.Row<A, Q>>
            implements AbsVector.Col<AbsVectorN.Col<A, Q>, Q>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new AbsVectorN with absolute quantities, with a display unit and a reference point.
         * @param relativeVector the vector with values relative to the reference point
         * @param reference the reference point for the absolute values
         */
        public Col(final VectorN.Col<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            super(relativeVector, reference);
        }

        @Override
        public AbsVectorN.Col<A, Q> instantiate(final VectorN.Col<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            return new AbsVectorN.Col<>(relativeVector, reference).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public AbsVectorN.Row<A, Q> transpose()
        {
            return new AbsVectorN.Row<>(getRelativeVecMat().transpose(), getReference());
        }

        // ------------------------------------------ OF METHODS ------------------------------------------

        /**
         * Create a AbsVectorN.Col without needing generics.
         * @param dataInUnit the vector entries expressed as an array in the unit
         * @param unit the unit of the data, which will also be used as the display unit
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVectorN.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Col<A, Q> of(final double[] dataInUnit, final Unit<?, Q> unit,
                        final R reference)
        {
            return new AbsVectorN.Col<>(VectorN.Col.of(dataInUnit, unit), reference);
        }

        /**
         * Create a AbsVectorN.Col without needing generics.
         * @param dataSi the vector entries expressed as an array in the SI units
         * @param displayUnit the display unit to use
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVectorN.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Col<A, Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit,
                        final R reference)
        {
            return new AbsVectorN.Col<>(VectorN.Col.ofSi(dataSi, displayUnit), reference);
        }

        /**
         * Create a AbsVectorN.Col without needing generics. The display unit will be taken from the first quantity in the
         * array.
         * @param data the vector entries expressed as an array of quantities
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVectorN.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Col<A, Q> of(final Q[] data, final R reference)
        {
            return new AbsVectorN.Col<>(VectorN.Col.of(data), reference);
        }

        /**
         * Create an AbsVectorN.Col without needing generics.
         * @param absData the values expressed as an array of absolute quantities
         * @return a new AbsVectorN.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Col<A, Q> of(final A[] absData)
        {
            Throw.whenNull(absData, "absData");
            Throw.when(absData.length == 0, IllegalArgumentException.class, "absData.length = 0");
            Throw.whenNull(absData[0], "absData[0]");
            var ddd = DenseDoubleDataSi.of(absData, absData.length, 1);
            return new AbsVectorN.Col<A, Q>(VectorN.Col.ofSi(ddd, absData[0].getDisplayUnit()), absData[0].getReference());
        }

        /**
         * Create a new column VectorN with a unit, based on a quantity list that contains data. The display unit will be taken
         * from the first quantity in the list.
         * @param data the data of the vector, expressed as a list of quantities.
         * @param reference the reference point for the absolute quantities
         * @return a new column VectorN with a display unit, based on a quantity list
         * @throws IllegalArgumentException when data size is less than 1
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Col<A, Q> of(final List<Q> data, final R reference)
        {
            return new AbsVectorN.Col<>(VectorN.Col.of(data), reference);
        }

        /**
         * Create an AbsVectorN.Col without needing generics.
         * @param absData the values expressed as a list of absolute quantities
         * @return a new AbsVectorN.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Col<A, Q> of(final List<A> absData)
        {
            Throw.whenNull(absData, "absData");
            Throw.when(absData.size() == 0, IllegalArgumentException.class, "absData.size() = 0");
            var ddd = DenseDoubleDataSi.of(absData, absData.size(), 1);
            return new AbsVectorN.Col<A, Q>(VectorN.Col.ofSi(ddd, absData.get(0).getDisplayUnit()),
                    absData.get(0).getReference());
        }

        /**
         * Create an AbsVectorN.Col without needing generics.
         * @param relativeVector the relative vector
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVectorN.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Col<A, Q> of(final VectorN.Col<Q> relativeVector, final R reference)
        {
            Throw.whenNull(relativeVector, "relativeVector");
            return new AbsVectorN.Col<>(relativeVector, reference);
        }
    }

    /**
     * Row vector for AbsVectorN with absolute quantities.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static class Row<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
            extends AbsVectorN<A, Q, AbsVectorN.Row<A, Q>, VectorN.Row<Q>, AbsVectorN.Col<A, Q>>
            implements AbsVector.Row<AbsVectorN.Row<A, Q>, Q>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new AbsVectorN with absolute quantities, with a display unit and a reference point.
         * @param relativeVector the vector with values relative to the reference point
         * @param reference the reference point for the absolute values
         */
        public Row(final VectorN.Row<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            super(relativeVector, reference);
        }

        @Override
        public AbsVectorN.Row<A, Q> instantiate(final VectorN.Row<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            return new AbsVectorN.Row<>(relativeVector, reference).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public AbsVectorN.Col<A, Q> transpose()
        {
            return new AbsVectorN.Col<>(getRelativeVecMat().transpose(), getReference());
        }

        // ------------------------------------------ OF METHODS ------------------------------------------

        /**
         * Create a AbsVectorN.Row without needing generics.
         * @param dataInUnit the vector entries expressed as an array in the unit
         * @param unit the unit of the data, which will also be used as the display unit
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVectorN.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Row<A, Q> of(final double[] dataInUnit, final Unit<?, Q> unit,
                        final R reference)
        {
            return new AbsVectorN.Row<>(VectorN.Row.of(dataInUnit, unit), reference);
        }

        /**
         * Create a AbsVectorN.Row without needing generics.
         * @param dataSi the vector entries expressed as an array in the SI units
         * @param displayUnit the display unit to use
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVectorN.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Row<A, Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit,
                        final R reference)
        {
            return new AbsVectorN.Row<>(VectorN.Row.ofSi(dataSi, displayUnit), reference);
        }

        /**
         * Create a AbsVectorN.Row without needing generics. The display unit will be taken from the first quantity in the
         * array.
         * @param data the vector entries expressed as an array of quantities
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVectorN.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Row<A, Q> of(final Q[] data, final R reference)
        {
            return new AbsVectorN.Row<>(VectorN.Row.of(data), reference);
        }

        /**
         * Create an AbsVectorN.Row without needing generics.
         * @param absData the values expressed as an array of absolute quantities
         * @return a new AbsVectorN.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Row<A, Q> of(final A[] absData)
        {
            Throw.whenNull(absData, "absData");
            Throw.when(absData.length == 0, IllegalArgumentException.class, "absData.length = 0");
            Throw.whenNull(absData[0], "absData[0]");
            var ddd = DenseDoubleDataSi.of(absData, 1, absData.length);
            return new AbsVectorN.Row<A, Q>(VectorN.Row.ofSi(ddd, absData[0].getDisplayUnit()), absData[0].getReference());
        }

        /**
         * Create a new column VectorN with a unit, based on a quantity list that contains data. The display unit will be taken
         * from the first quantity in the list.
         * @param data the data of the vector, expressed as a list of quantities.
         * @param reference the reference point for the absolute quantities
         * @return a new column VectorN with a display unit, based on a quantity list
         * @throws IllegalArgumentException when data size is less than 1
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Row<A, Q> of(final List<Q> data, final R reference)
        {
            return new AbsVectorN.Row<>(VectorN.Row.of(data), reference);
        }

        /**
         * Create an AbsVectorN.Row without needing generics.
         * @param absData the values expressed as a list of absolute quantities
         * @return a new AbsVectorN.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Row<A, Q> of(final List<A> absData)
        {
            Throw.whenNull(absData, "absData");
            Throw.when(absData.size() == 0, IllegalArgumentException.class, "absData.size() = 0");
            var ddd = DenseDoubleDataSi.of(absData, 1, absData.size());
            return new AbsVectorN.Row<A, Q>(VectorN.Row.ofSi(ddd, absData.get(0).getDisplayUnit()),
                    absData.get(0).getReference());
        }

        /**
         * Create an AbsVectorN.Row without needing generics.
         * @param relativeVector the relative vector
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVectorN.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVectorN.Row<A, Q> of(final VectorN.Row<Q> relativeVector, final R reference)
        {
            Throw.whenNull(relativeVector, "relativeVector");
            return new AbsVectorN.Row<>(relativeVector, reference);
        }
    }

}
