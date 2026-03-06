package org.djunits.vecmat.dn;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.vecmat.DataGridMatrix;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.operations.Normed;
import org.djunits.vecmat.operations.VectorOps;
import org.djunits.vecmat.operations.VectorTransposable;
import org.djunits.vecmat.storage.DataGrid;
import org.djutils.exceptions.Throw;

/**
 * VectorN.java.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <V> the vector type (Col or Row)
 */
public abstract class VectorN<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, V extends VectorN<Q, U, V>>
        extends DataGridMatrix<Q, U, VectorN<Q, U, ?>> implements VectorOps<Q, U, V>, Normed<Q, U>
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

    /**
     * Set a new display unit of this vector.
     * @param newUnit the new display unit of this vector
     */
    @SuppressWarnings("unchecked")
    @Override
    public V setDisplayUnit(final U newUnit)
    {
        super.setDisplayUnit(newUnit);
        return (V) this;
    }

    @Override
    public Iterator<Q> iterator()
    {
        final double[] si = this.dataSi.getDataArray(); // should be immutable, otherwise make defensive copy
        final U frozenDisplayUnit = getDisplayUnit(); // capture once
        return Arrays.stream(si).mapToObj(v -> frozenDisplayUnit.ofSi(v).setDisplayUnit(frozenDisplayUnit)).iterator();
    }

    @Override
    public Q[] getScalarArray()
    {
        final double[] si = this.dataSi.getDataArray(); // should be immutable, otherwise make defensive copy
        final U frozenDisplayUnit = getDisplayUnit(); // capture once
        final Q first = frozenDisplayUnit.ofSi(si[0]).setDisplayUnit(frozenDisplayUnit);
        final Class<?> qClass = first.getClass();
        @SuppressWarnings("unchecked")
        final Q[] out = (Q[]) Array.newInstance(qClass, si.length);
        out[0] = first;
        for (int i = 1; i < si.length; i++)
        {
            out[i] = frozenDisplayUnit.ofSi(si[i]).setDisplayUnit(frozenDisplayUnit);
        }
        return out;
    }

    @Override
    public Q normL1()
    {
        double n = 0.0;
        for (var d : si())
        {
            n += Math.abs(d);
        }
        return getDisplayUnit().ofSi(n).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normL2()
    {
        double n = 0.0;
        for (var d : si())
        {
            n += d * d;
        }
        return getDisplayUnit().ofSi(Math.sqrt(n)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLp(final int p)
    {
        double n = 0.0;
        for (var d : si())
        {
            n += Math.pow(Math.abs(d), p);
        }
        return getDisplayUnit().ofSi(Math.pow(n, 1.0 / p)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLinf()
    {
        double max = Double.NEGATIVE_INFINITY;
        for (var d : si())
        {
            max = Math.max(Math.abs(d), max);
        }
        return getDisplayUnit().ofSi(max).setDisplayUnit(getDisplayUnit());
    }

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
     * which can be changed. <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
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
         * @throws IllegalArgumentException when the number of rows or columns does not have a positive value or when the vector
         *             is initialized with more than one row
         */
        public Col(final DataGrid<?> dataSi, final U displayUnit)
        {
            super(dataSi, displayUnit);
            Throw.when(dataSi.cols() != 1, IllegalArgumentException.class,
                    "Column vector initialized with more than one column");
        }

        @Override
        public VectorN.Col<Q, U> instantiateSi(final double[] data)
        {
            return new VectorN.Col<Q, U>(this.dataSi.instantiateNew(data), getDisplayUnit().getBaseUnit())
                    .setDisplayUnit(getDisplayUnit());
        }

        @Override
        public boolean isColumnVector()
        {
            return true;
        }

        @Override
        public int size()
        {
            return this.dataSi.rows();
        }

        @Override
        public Q get(final int index) throws IndexOutOfBoundsException
        {
            return getDisplayUnit().ofSi(this.dataSi.get(index - 1, 0));
        }

        @Override
        public VectorN.Row<Q, U> transpose()
        {
            var newSi = this.dataSi.instantiateNew(this.dataSi.getDataArray().clone(), cols(), rows());
            return new VectorN.Row<Q, U>(newSi, getDisplayUnit());
        }

        @Override
        public VectorN.Col<SIQuantity, SIUnit> invertElements()
        {
            SIUnit siUnit = getDisplayUnit().siUnit().invert();
            return new VectorN.Col<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.reciprocal(si())), siUnit);
        }

        @Override
        public VectorN.Col<SIQuantity, SIUnit> multiplyElements(final VectorN.Col<?, ?> other)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new VectorN.Col<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.multiply(si(), other.si())),
                    siUnit);
        }

        @Override
        public VectorN.Col<SIQuantity, SIUnit> divideElements(final VectorN.Col<?, ?> other)
        {
            SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new VectorN.Col<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.divide(si(), other.si())), siUnit);
        }

    }

    /**
     * VectorN.Row implements a row vector with real-valued entries. The vector is immutable, except for the display unit, which
     * can be changed. <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static class Row<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends VectorN<Q, U, Row<Q, U>>
            implements VectorTransposable<Col<Q, U>>, Hadamard<VectorN.Row<?, ?>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new row VectorN with a unit, based on a DataGrid storage object that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @throws IllegalArgumentException when the number of rows or columns does not have a positive value or when the vector
         *             is initialized with more than one row
         */
        public Row(final DataGrid<?> dataSi, final U displayUnit)
        {
            super(dataSi, displayUnit);
            Throw.when(dataSi.rows() != 1, IllegalArgumentException.class, "Row vector initialized with more than one row");
        }

        @Override
        public boolean isColumnVector()
        {
            return false;
        }

        @Override
        public VectorN.Row<Q, U> instantiateSi(final double[] data)
        {
            return new VectorN.Row<>(this.dataSi.instantiateNew(data), getDisplayUnit());
        }

        @Override
        public int size()
        {
            return this.dataSi.cols();
        }

        @Override
        public Q get(final int index) throws IndexOutOfBoundsException
        {
            return getDisplayUnit().ofSi(this.dataSi.get(0, index - 1));
        }

        @Override
        public VectorN.Col<Q, U> transpose()
        {
            var newSi = this.dataSi.instantiateNew(this.dataSi.getDataArray().clone(), cols(), rows());
            return new VectorN.Col<Q, U>(newSi, getDisplayUnit());
        }

        @Override
        public VectorN.Row<SIQuantity, SIUnit> invertElements()
        {
            SIUnit siUnit = getDisplayUnit().siUnit().invert();
            return new VectorN.Row<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.reciprocal(si())), siUnit);
        }

        @Override
        public VectorN.Row<SIQuantity, SIUnit> multiplyElements(final VectorN.Row<?, ?> other)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new VectorN.Row<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.multiply(si(), other.si())),
                    siUnit);
        }

        @Override
        public VectorN.Row<SIQuantity, SIUnit> divideElements(final VectorN.Row<?, ?> other)
        {
            SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new VectorN.Row<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.divide(si(), other.si())), siUnit);
        }

    }

}
