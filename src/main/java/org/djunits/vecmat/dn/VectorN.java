package org.djunits.vecmat.dn;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.operations.VectorTransposable;
import org.djunits.vecmat.storage.DataGrid;

/**
 * VectorN.java.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <V> the vector type (Col or Row)
 */
public abstract class VectorN<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, V extends VectorN<Q, U, V>>
        extends MatrixNxM<Q, U>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new VectorN with a unit, based on a DataGrid storage object that contains SI data.
     * @param dataSi the data of the vector, in SI unit.
     * @param displayUnit the display unit to use
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    protected VectorN(final DataGrid<?> dataSi, final U displayUnit)
    {
        super(dataSi, displayUnit);
    }

    /**
     * Return whether this vector is a column vector.
     * @return whether this vector is a column vector
     */
    public abstract boolean isColumnVector();

    @Override
    public String toString(final U withUnit)
    {
        double[] data = si();
        var s = new StringBuilder();
        s.append(isColumnVector() ? "Col" : "Row");
        s.append("[");
        for (int i = 0; i < data.length; i++)
        {
            s.append(i > 0 ? ", " : "");
            s.append(withUnit.fromBaseValue(data[i]));
        }
        s.append("] ");
        s.append(withUnit.getDisplayAbbreviation());
        return s.toString();
    }

    @Override
    public String toString()
    {
        return toString(getDisplayUnit());
    }

    /**
     * VectorN.Col implements a column vector with real-valued entries. The vector is immutable, except for the display unit,
     * which can be changed. <br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static class Col<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends VectorN<Q, U, Col<Q, U>>
            implements VectorTransposable<Row<Q, U>>, Hadamard<VectorN.Col<?, ?>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new column VectorN with a unit, based on a DataGrid storage object that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
         */
        protected Col(final DataGrid<?> dataSi, final U displayUnit)
        {
            // TODO check Nx1
            super(dataSi, displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return true;
        }

        @Override
        public int rows()
        {
            return this.dataSi.rows();
        }

        @Override
        public int cols()
        {
            return 1;
        }

        @Override
        public VectorN.Col<Q, U> instantiate(final double[] data)
        {
            return new VectorN.Col<>(this.dataSi.instantiate(data), getDisplayUnit());
        }

        @Override
        public VectorN.Row<Q, U> transpose()
        {
            return new VectorN.Row<Q, U>(this.dataSi.copy(), getDisplayUnit());
        }

        @Override
        public VectorN.Col<SIQuantity, SIUnit> invertElements()
        {
            // TODO return new VectorN.Col<SIQuantity, SIUnit>(1.0 / xSi(), getDisplayUnit().siUnit().invert());
            return null;
        }

        @Override
        public VectorN.Col<SIQuantity, SIUnit> multiplyElements(final Col<?, ?> other)
        {
            return null;
        }

        @Override
        public VectorN.Col<SIQuantity, SIUnit> divideElements(final Col<?, ?> other)
        {
            return null;
        }

    }

    /**
     * VectorN.Row implements a row vector with real-valued entries. The vector is immutable, except for the display unit, which
     * can be changed. <br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static class Row<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends VectorN<Q, U, Row<Q, U>>
            implements VectorTransposable<Col<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new row VectorN with a unit, based on a DataGrid storage object that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
         */
        protected Row(final DataGrid<?> dataSi, final U displayUnit)
        {
            // TODO check 1xN
            super(dataSi, displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return false;
        }

        @Override
        public int rows()
        {
            return 1;
        }

        @Override
        public int cols()
        {
            return this.dataSi.cols();
        }

        @Override
        public VectorN.Row<Q, U> instantiate(final double[] data)
        {
            return new VectorN.Row<>(this.dataSi.instantiate(data), getDisplayUnit());
        }

        @Override
        public VectorN.Col<Q, U> transpose()
        {
            return new VectorN.Col<Q, U>(this.dataSi.copy(), getDisplayUnit());
        }

    }

}
