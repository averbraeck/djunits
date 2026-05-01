package org.djunits.vecmat.d3;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsVector;
import org.djutils.exceptions.Throw;

/**
 * AbsVector3 implements a vector with three real-valued entries representing an absolute quantity. The vector is immutable,
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
public abstract class AbsVector3<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>,
        VA extends AbsVector3<A, Q, VA, VQ, VAT>, VQ extends Vector3<Q, VQ, ?, ?, ?>, VAT extends AbsVector3<A, Q, VAT, ?, VA>>
        extends AbsVector<A, Q, VA, VQ, VAT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate an AbsVector3.
     * @param relativeVector the vector with values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVector3(final VQ relativeVector, final Reference<?, A, Q> reference)
    {
        super(relativeVector, reference);
    }

    /**
     * Column vector for AbsVector3 with absolute quantities.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static class Col<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
            extends AbsVector3<A, Q, AbsVector3.Col<A, Q>, Vector3.Col<Q>, AbsVector3.Row<A, Q>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new AbsVector3 with absolute quantities, with a display unit and a reference point.
         * @param relativeVector the vector with values relative to the reference point
         * @param reference the reference point for the absolute values
         */
        public Col(final Vector3.Col<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            super(relativeVector, reference);
        }

        @Override
        public AbsVector3.Col<A, Q> instantiate(final Vector3.Col<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            return new AbsVector3.Col<>(relativeVector, reference).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public AbsVector3.Row<A, Q> transpose()
        {
            return new AbsVector3.Row<>(getRelativeVecMat().transpose(), getReference());
        }

        // ------------------------------------------ OF METHODS ------------------------------------------

        /**
         * Create a AbsVector3.Col without needing generics.
         * @param xInUnit the x-value expressed in the unit
         * @param yInUnit the y-value expressed in the unit
         * @param zInUnit the z-value expressed in the unit
         * @param unit the unit of the data, which will also be used as the display unit
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> of(final double xInUnit, final double yInUnit,
                        final double zInUnit, final Unit<?, Q> unit, final R reference)
        {
            return new AbsVector3.Col<>(Vector3.Col.of(xInUnit, yInUnit, zInUnit, unit), reference);
        }

        /**
         * Create a AbsVector3.Col without needing generics. The display unit will be taken from the x-quantity.
         * @param x the x-value expressed as a quantity
         * @param y the y-value expressed as a quantity
         * @param z the z-value expressed as a quantity
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> of(final Q x, final Q y, final Q z, final R reference)
        {
            return new AbsVector3.Col<>(Vector3.Col.of(x, y, z), reference);
        }

        /**
         * Create an AbsVector3.Col without needing generics.
         * @param absX the v1-value expressed as an absolute quantity
         * @param absY the v2-value expressed as an absolute quantity
         * @param absZ the v3-value expressed as an absolute quantity
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> of(final A absX, final A absY, final A absZ)
        {
            Throw.whenNull(absX, "absX");
            Throw.whenNull(absY, "absY");
            Throw.whenNull(absZ, "absZ");
            Throw.when(!absX.getReference().equals(absY.getReference()), IllegalArgumentException.class,
                    "absX.reference != absY.reference");
            Throw.when(!absX.getReference().equals(absZ.getReference()), IllegalArgumentException.class,
                    "absX.reference != absZ.reference");
            return new AbsVector3.Col<>(Vector3.Col.of(absX.getQuantity(), absY.getQuantity(), absZ.getQuantity()),
                    absX.getReference());
        }

        /**
         * Create a AbsVector3.Col without needing generics.
         * @param dataInUnit the vector entries expressed as an array in the unit
         * @param unit the unit of the data, which will also be used as the display unit
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> of(final double[] dataInUnit, final Unit<?, Q> unit,
                        final R reference)
        {
            return new AbsVector3.Col<>(Vector3.Col.of(dataInUnit, unit), reference);
        }

        /**
         * Create a AbsVector3.Col without needing generics.
         * @param dataSi the vector entries expressed as an array in the SI units
         * @param displayUnit the display unit to use
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit,
                        final R reference)
        {
            return new AbsVector3.Col<>(Vector3.Col.ofSi(dataSi, displayUnit), reference);
        }

        /**
         * Create a AbsVector3.Col without needing generics.
         * @param xSi the x vector entry expressed in the SI unit
         * @param ySi the y vector entry expressed in the SI unit
         * @param zSi the z vector entry expressed in the SI unit
         * @param displayUnit the display unit to use
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> ofSi(final double xSi, final double ySi, final double zSi,
                        final Unit<?, Q> displayUnit, final R reference)
        {
            return new AbsVector3.Col<>(Vector3.Col.ofSi(xSi, ySi, zSi, displayUnit), reference);
        }

        /**
         * Create a AbsVector3.Col without needing generics. The display unit will be taken from the first quantity in the
         * array.
         * @param data the vector entries expressed as an array of quantities
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> of(final Q[] data, final R reference)
        {
            return new AbsVector3.Col<>(Vector3.Col.of(data), reference);
        }

        /**
         * Create an AbsVector3.Col without needing generics.
         * @param absData the {x, y} value expressed as an array of absolute quantities
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> of(final A[] absData)
        {
            Throw.whenNull(absData, "absData");
            Throw.when(absData.length != 3, IllegalArgumentException.class, "absData.length != 3");
            return AbsVector3.Col.of(absData[0], absData[1], absData[2]);
        }

        /**
         * Create an AbsVector3.Col without needing generics.
         * @param relativeVector the relative vector
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Col with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Col<A, Q> of(final Vector3.Col<Q> relativeVector, final R reference)
        {
            Throw.whenNull(relativeVector, "relativeVector");
            return new AbsVector3.Col<>(relativeVector, reference);
        }
    }

    /**
     * Row vector for AbsVector3 with absolute quantities.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <A> the absolute quantity type
     * @param <Q> the corresponding relative quantity type
     */
    public static class Row<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
            extends AbsVector3<A, Q, AbsVector3.Row<A, Q>, Vector3.Row<Q>, AbsVector3.Col<A, Q>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new AbsVector3 with absolute quantities, with a display unit and a reference point.
         * @param relativeVector the vector with values relative to the reference point
         * @param reference the reference point for the absolute values
         */
        public Row(final Vector3.Row<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            super(relativeVector, reference);
        }

        @Override
        public AbsVector3.Row<A, Q> instantiate(final Vector3.Row<Q> relativeVector, final Reference<?, A, Q> reference)
        {
            return new AbsVector3.Row<>(relativeVector, reference).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public AbsVector3.Col<A, Q> transpose()
        {
            return new AbsVector3.Col<>(getRelativeVecMat().transpose(), getReference());
        }

        // ------------------------------------------ OF METHODS ------------------------------------------

        /**
         * Create a AbsVector3.Row without needing generics.
         * @param xInUnit the x-value expressed in the unit
         * @param yInUnit the y-value expressed in the unit
         * @param zInUnit the z-value expressed in the unit
         * @param unit the unit of the data, which will also be used as the display unit
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> of(final double xInUnit, final double yInUnit,
                        final double zInUnit, final Unit<?, Q> unit, final R reference)
        {
            return new AbsVector3.Row<>(Vector3.Row.of(xInUnit, yInUnit, zInUnit, unit), reference);
        }

        /**
         * Create a AbsVector3.Row without needing generics. The display unit will be taken from the x-quantity.
         * @param x the x-value expressed as a quantity
         * @param y the y-value expressed as a quantity
         * @param z the z-value expressed as a quantity
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> of(final Q x, final Q y, final Q z, final R reference)
        {
            return new AbsVector3.Row<>(Vector3.Row.of(x, y, z), reference);
        }

        /**
         * Create an AbsVector3.Row without needing generics.
         * @param absX the v1-value expressed as an absolute quantity
         * @param absY the v2-value expressed as an absolute quantity
         * @param absZ the v3-value expressed as an absolute quantity
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> of(final A absX, final A absY, final A absZ)
        {
            Throw.whenNull(absX, "absX");
            Throw.whenNull(absY, "absY");
            Throw.whenNull(absZ, "absZ");
            Throw.when(!absX.getReference().equals(absY.getReference()), IllegalArgumentException.class,
                    "absX.reference != absY.reference");
            Throw.when(!absX.getReference().equals(absZ.getReference()), IllegalArgumentException.class,
                    "absX.reference != absZ.reference");
            return new AbsVector3.Row<>(Vector3.Row.of(absX.getQuantity(), absY.getQuantity(), absZ.getQuantity()),
                    absX.getReference());
        }

        /**
         * Create a AbsVector3.Row without needing generics.
         * @param dataInUnit the vector entries expressed as an array in the unit
         * @param unit the unit of the data, which will also be used as the display unit
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> of(final double[] dataInUnit, final Unit<?, Q> unit,
                        final R reference)
        {
            return new AbsVector3.Row<>(Vector3.Row.of(dataInUnit, unit), reference);
        }

        /**
         * Create a AbsVector3.Row without needing generics.
         * @param dataSi the vector entries expressed as an array in the SI units
         * @param displayUnit the display unit to use
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit,
                        final R reference)
        {
            return new AbsVector3.Row<>(Vector3.Row.ofSi(dataSi, displayUnit), reference);
        }

        /**
         * Create a AbsVector3.Row without needing generics.
         * @param xSi the x vector entry expressed in the SI unit
         * @param ySi the y vector entry expressed in the SI unit
         * @param zSi the z vector entry expressed in the SI unit
         * @param displayUnit the display unit to use
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> ofSi(final double xSi, final double ySi, final double zSi,
                        final Unit<?, Q> displayUnit, final R reference)
        {
            return new AbsVector3.Row<>(Vector3.Row.ofSi(xSi, ySi, zSi, displayUnit), reference);
        }

        /**
         * Create a AbsVector3.Row without needing generics. The display unit will be taken from the first quantity in the
         * array.
         * @param data the vector entries expressed as an array of quantities
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> of(final Q[] data, final R reference)
        {
            return new AbsVector3.Row<>(Vector3.Row.of(data), reference);
        }

        /**
         * Create an AbsVector3.Row without needing generics.
         * @param absData the {x, y} value expressed as an array of absolute quantities
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> of(final A[] absData)
        {
            Throw.whenNull(absData, "absData");
            Throw.when(absData.length != 3, IllegalArgumentException.class, "absData.length != 3");
            return AbsVector3.Row.of(absData[0], absData[1], absData[2]);
        }

        /**
         * Create an AbsVector3.Row without needing generics.
         * @param relativeVector the relative vector
         * @param reference the reference point for the absolute quantities
         * @return a new AbsVector3.Row with a unit
         * @param <A> the absolute quantity type
         * @param <Q> the quantity type
         * @param <R> the reference type
         */
        public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
                R extends Reference<R, A, Q>> AbsVector3.Row<A, Q> of(final Vector3.Row<Q> relativeVector, final R reference)
        {
            Throw.whenNull(relativeVector, "relativeVector");
            return new AbsVector3.Row<>(relativeVector, reference);
        }
    }

}
