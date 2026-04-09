package org.djunits.vecmat.dn;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.def.Vector;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djutils.exceptions.Throw;

/**
 * VectorN.java.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <V> the vector type (Row or Col)
 * @param <SI> the vector type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic vector type with generics &lt;?, ?&lt; for Hadamard operations
 * @param <VT> the type of the transposed version of the vector
 */
public abstract class VectorN<Q extends Quantity<Q>, V extends VectorN<Q, V, SI, H, VT>,
        SI extends VectorN<SIQuantity, SI, ?, ?, ?>, H extends VectorN<?, ?, ?, ?, ?>, VT extends VectorN<Q, VT, ?, ?, V>>
        extends Vector<Q, V, SI, H, VT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The data of the matrix, in SI unit. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected final DataGridSi<?> dataSi;

    /**
     * Create a new VectorN with a unit, based on a DataGridSi storage object that contains SI data.
     * @param dataSi the data of the vector, in SI unit.
     * @param displayUnit the display unit to use
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    protected VectorN(final DataGridSi<?> dataSi, final Unit<?, Q> displayUnit)
    {
        super(displayUnit);
        Throw.whenNull(dataSi, "dataSi");
        this.dataSi = dataSi;
    }

    @Override
    public Iterator<Q> iterator()
    {
        final double[] si = this.dataSi.unsafeSiArray();
        final Unit<?, Q> frozenDisplayUnit = getDisplayUnit(); // capture once
        return Arrays.stream(si).mapToObj(v -> frozenDisplayUnit.ofSi(v).setDisplayUnit(frozenDisplayUnit)).iterator();
    }

    @Override
    public Q[] getScalarArray()
    {
        final double[] siArray = this.dataSi.unsafeSiArray();
        final Unit<?, Q> frozenDisplayUnit = getDisplayUnit(); // capture once
        final Q first = frozenDisplayUnit.ofSi(siArray[0]).setDisplayUnit(frozenDisplayUnit);
        final Class<?> qClass = first.getClass();
        @SuppressWarnings("unchecked")
        final Q[] out = (Q[]) Array.newInstance(qClass, siArray.length);
        out[0] = first;
        for (int i = 1; i < siArray.length; i++)
        {
            out[i] = frozenDisplayUnit.ofSi(siArray[i]).setDisplayUnit(frozenDisplayUnit);
        }
        return out;
    }

    @Override
    public Q normL1()
    {
        double n = 0.0;
        for (var d : unsafeSiArray())
        {
            n += Math.abs(d);
        }
        return getDisplayUnit().ofSi(n).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normL2()
    {
        double n = 0.0;
        for (var d : unsafeSiArray())
        {
            n += d * d;
        }
        return getDisplayUnit().ofSi(Math.sqrt(n)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLp(final int p)
    {
        double n = 0.0;
        for (var d : unsafeSiArray())
        {
            n += Math.pow(Math.abs(d), p);
        }
        return getDisplayUnit().ofSi(Math.pow(n, 1.0 / p)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLinf()
    {
        double max = Double.NEGATIVE_INFINITY;
        for (var d : unsafeSiArray())
        {
            max = Math.max(Math.abs(d), max);
        }
        return getDisplayUnit().ofSi(max).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public int rows()
    {
        return this.dataSi.rows();
    }

    @Override
    public int cols()
    {
        return this.dataSi.cols();
    }

    @Override
    public double[] getSiArray()
    {
        return this.dataSi.getSiArray();
    }

    @Override
    public double[] unsafeSiArray()
    {
        return this.dataSi.unsafeSiArray();
    }

    @Override
    public int nonZeroCount()
    {
        return this.dataSi.nonZeroCount();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.dataSi, rows(), cols());
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
        VectorN<?, ?, ?, ?, ?> other = (VectorN<?, ?, ?, ?, ?>) obj;
        return Objects.equals(this.dataSi, other.dataSi) && rows() == other.rows() && cols() == other.cols();
    }

    @Override
    public String toString(final Unit<?, Q> withUnit)
    {
        double[] data = unsafeSiArray();
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
     * which can be changed.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     */
    public static class Col<Q extends Quantity<Q>>
            extends VectorN<Q, Col<Q>, VectorN.Col<SIQuantity>, VectorN.Col<?>, VectorN.Row<Q>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new column VectorN with a unit, based on a DataGridSi storage object that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @throws IllegalArgumentException when the number of rows or columns does not have a positive value or when the vector
         *             is initialized with more than one row
         */
        public Col(final DataGridSi<?> dataSi, final Unit<?, Q> displayUnit)
        {
            super(dataSi, displayUnit);
            Throw.when(dataSi.cols() != 1, IllegalArgumentException.class,
                    "Column vector initialized with more than one column");
        }

        @Override
        public VectorN.Col<Q> instantiateSi(final double[] data)
        {
            return new VectorN.Col<Q>(this.dataSi.instantiateNew(data), getDisplayUnit().getBaseUnit())
                    .setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Col<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
        {
            return new VectorN.Col<SIQuantity>(this.dataSi.instantiateNew(siNew), siUnit);
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
        public double si(final int index) throws IndexOutOfBoundsException
        {
            return this.dataSi.get(index, 0);
        }

        @Override
        public VectorN.Row<Q> transpose()
        {
            var newSi = this.dataSi.instantiateNew(unsafeSiArray(), cols(), rows());
            return new VectorN.Row<Q>(newSi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public VectorN.Col<SIQuantity> invertEntries()
        {
            SIUnit siUnit = getDisplayUnit().siUnit().invert();
            return new VectorN.Col<SIQuantity>(this.dataSi.instantiateNew(ArrayMath.reciprocal(unsafeSiArray())), siUnit);
        }

        @Override
        public VectorN.Col<SIQuantity> multiplyEntries(final VectorN.Col<?> other)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new VectorN.Col<SIQuantity>(
                    this.dataSi.instantiateNew(ArrayMath.multiply(unsafeSiArray(), other.unsafeSiArray())), siUnit);
        }

        @Override
        public VectorN.Col<SIQuantity> divideEntries(final VectorN.Col<?> other)
        {
            SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new VectorN.Col<SIQuantity>(
                    this.dataSi.instantiateNew(ArrayMath.divide(unsafeSiArray(), other.unsafeSiArray())), siUnit);
        }

        @Override
        public VectorN.Col<SIQuantity> multiplyEntries(final Quantity<?> quantity)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
            return new VectorN.Col<SIQuantity>(this.dataSi.instantiateNew(ArrayMath.scaleBy(unsafeSiArray(), quantity.si())),
                    siUnit);
        }

        // ------------------------------------------ OF METHODS ------------------------------------------

        /**
         * Create a new column VectorN with a unit, based on a double[] array that contains data in the given unit.
         * @param dataInUnit the data of the vector, in the given unit.
         * @param unit the unit of the data
         * @return a new column VectorN with a unit, based on a double[] array expressed in the given unit
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Col<Q> of(final double[] dataInUnit, final Unit<?, Q> unit)
        {
            double[] dataSi = new double[dataInUnit.length];
            for (int i = 0; i < dataInUnit.length; i++)
            {
                dataSi[i] = unit.toBaseValue(dataInUnit[i]);
            }
            return ofSi(dataSi, unit);
        }

        /**
         * Create a new column VectorN with a unit, based on a double[] array that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @return a new column VectorN with a unit, based on a double[] array that contains SI data
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Col<Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit)
        {
            return new VectorN.Col<Q>(new DenseDoubleDataSi(dataSi.clone(), dataSi.length, 1), displayUnit.getBaseUnit())
                    .setDisplayUnit(displayUnit);
        }

        /**
         * Create a new column VectorN with a unit, based on a quantity array that contains data. The display unit will be taken
         * from the first quantity in the array.
         * @param data the data of the vector, in the given unit.
         * @return a new column VectorN with a display unit, based on a quantity array
         * @throws IllegalArgumentException when data array length is less than 1
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Col<Q> of(final Q[] data)
        {
            Throw.when(data.length < 1, IllegalArgumentException.class, "data array length < 1");
            double[] dataSi = new double[data.length];
            for (int i = 0; i < data.length; i++)
            {
                dataSi[i] = data[i].si();
            }
            return ofSi(dataSi, data[0].getDisplayUnit());
        }

        /**
         * Create a new column VectorN with a unit, based on a DataGridSi storage object that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @throws IllegalArgumentException when the number of rows or columns does not have a positive value or when the vector
         *             is initialized with more than one row
         * @return a new column VectorN with a unit, based on a DataGridSi storage object that contains SI data
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Col<Q> ofSi(final DataGridSi<?> dataSi, final Unit<?, Q> displayUnit)
        {
            return new VectorN.Col<Q>(dataSi, displayUnit.getBaseUnit()).setDisplayUnit(displayUnit);
        }

        /**
         * Create a new column VectorN with a unit, based on a quantity list that contains data. The display unit will be taken
         * from the first quantity in the list.
         * @param data the data of the vector, in the given unit.
         * @return a new column VectorN with a display unit, based on a quantity list
         * @throws IllegalArgumentException when data size is less than 1
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Col<Q> of(final List<Q> data)
        {
            Throw.when(data.size() < 1, IllegalArgumentException.class, "data.size < 1");
            double[] dataSi = new double[data.size()];
            for (int i = 0; i < data.size(); i++)
            {
                dataSi[i] = data.get(i).si();
            }
            return ofSi(dataSi, data.get(0).getDisplayUnit());
        }

        // ------------------------------------------ AS METHODS ------------------------------------------

        /**
         * Return the vector 'as' a vector with a known quantity, using a unit to express the result in. Throw a Runtime
         * exception when the SI units of this vector and the target vector do not match. The dataSi object containing the
         * vector values is NOT copied.
         * @param targetUnit the unit to convert the vector to
         * @return a quantity typed in the target vector class
         * @throws IllegalArgumentException when the units do not match
         * @param <TQ> target quantity type
         */
        public <TQ extends Quantity<TQ>> VectorN.Col<TQ> as(final Unit<?, TQ> targetUnit) throws IllegalArgumentException
        {
            Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                    "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit,
                    getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
            return new VectorN.Col<TQ>(this.dataSi, targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
        }

        /**
         * Return this matrix as a 1-element column vector. Shape must be 1 x 1.
         * @return a {@code Vector1} with identical SI data and display unit
         * @throws IllegalStateException if shape is not 1 x 1
         */
        public Vector1<Q> asVector1()
        {
            Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class, "Matrix is not 1x1");
            final double[] data = unsafeSiArray();
            return new Vector1<Q>(data[0], getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        /**
         * Return this matrix as a 2-element column vector. Shape must be 2 x 1.
         * @return a {@code Vector2.Col} with identical SI data and display unit
         * @throws IllegalStateException if shape is not 2 x 1
         */
        public Vector2.Col<Q> asVector2Col()
        {
            Throw.when(rows() != 2 || cols() != 1, IllegalStateException.class, "Matrix is not 2x1");
            final double[] data = unsafeSiArray();
            return new Vector2.Col<Q>(data[0], data[1], getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        /**
         * Return this matrix as a 3-element column vector. Shape must be 3 x 1.
         * @return a {@code Vector3.Col} with identical SI data and display unit
         * @throws IllegalStateException if shape is not 3 x 1
         */
        public Vector3.Col<Q> asVector3Col()
        {
            Throw.when(rows() != 3 || cols() != 1, IllegalStateException.class, "Matrix is not 3x1");
            final double[] data = unsafeSiArray();
            return new Vector3.Col<Q>(data[0], data[1], data[2], getDisplayUnit().getBaseUnit())
                    .setDisplayUnit(getDisplayUnit());
        }

    }

    /**
     * VectorN.Row implements a row vector with real-valued entries. The vector is immutable, except for the display unit, which
     * can be changed.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     */
    public static class Row<Q extends Quantity<Q>>
            extends VectorN<Q, Row<Q>, VectorN.Row<SIQuantity>, VectorN.Row<?>, VectorN.Col<Q>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new row VectorN with a unit, based on a DataGridSi storage object that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @throws IllegalArgumentException when the number of rows or columns does not have a positive value or when the vector
         *             is initialized with more than one row
         */
        public Row(final DataGridSi<?> dataSi, final Unit<?, Q> displayUnit)
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
        public VectorN.Row<Q> instantiateSi(final double[] data)
        {
            return new VectorN.Row<>(this.dataSi.instantiateNew(data), getDisplayUnit());
        }

        @Override
        public VectorN.Row<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
        {
            return new VectorN.Row<SIQuantity>(this.dataSi.instantiateNew(siNew), siUnit);
        }

        @Override
        public int size()
        {
            return this.dataSi.cols();
        }

        @Override
        public double si(final int index) throws IndexOutOfBoundsException
        {
            return this.dataSi.get(0, index);
        }

        @Override
        public VectorN.Col<Q> transpose()
        {
            var newSi = this.dataSi.instantiateNew(unsafeSiArray(), cols(), rows());
            return new VectorN.Col<Q>(newSi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public VectorN.Row<SIQuantity> invertEntries()
        {
            SIUnit siUnit = getDisplayUnit().siUnit().invert();
            return new VectorN.Row<SIQuantity>(this.dataSi.instantiateNew(ArrayMath.reciprocal(unsafeSiArray())), siUnit);
        }

        @Override
        public VectorN.Row<SIQuantity> multiplyEntries(final VectorN.Row<?> other)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new VectorN.Row<SIQuantity>(
                    this.dataSi.instantiateNew(ArrayMath.multiply(unsafeSiArray(), other.unsafeSiArray())), siUnit);
        }

        @Override
        public VectorN.Row<SIQuantity> divideEntries(final VectorN.Row<?> other)
        {
            SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new VectorN.Row<SIQuantity>(
                    this.dataSi.instantiateNew(ArrayMath.divide(unsafeSiArray(), other.unsafeSiArray())), siUnit);
        }

        @Override
        public VectorN.Row<SIQuantity> multiplyEntries(final Quantity<?> quantity)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
            return new VectorN.Row<SIQuantity>(this.dataSi.instantiateNew(ArrayMath.scaleBy(unsafeSiArray(), quantity.si())),
                    siUnit);
        }

        // ------------------------------------------ OF METHODS ------------------------------------------

        /**
         * Create a new row VectorN with a unit, based on a double[] array that contains data in the given unit.
         * @param dataInUnit the data of the vector, in the given unit.
         * @param unit the unit of the data
         * @return a new row VectorN with a unit, based on a double[] array expressed in the given unit
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Row<Q> of(final double[] dataInUnit, final Unit<?, Q> unit)
        {
            double[] dataSi = new double[dataInUnit.length];
            for (int i = 0; i < dataInUnit.length; i++)
            {
                dataSi[i] = unit.toBaseValue(dataInUnit[i]);
            }
            return ofSi(dataSi, unit);
        }

        /**
         * Create a new row VectorN with a unit, based on a double[] array that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @return a new row VectorN with a unit, based on a double[] array that contains SI data
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Row<Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit)
        {
            return new VectorN.Row<Q>(new DenseDoubleDataSi(dataSi.clone(), 1, dataSi.length), displayUnit.getBaseUnit())
                    .setDisplayUnit(displayUnit);
        }

        /**
         * Create a new row VectorN with a unit, based on a quantity array that contains data. The display unit will be taken
         * from the first quantity in the array.
         * @param data the data of the vector, in the given unit.
         * @return a new row VectorN with a display unit, based on a quantity array
         * @throws IllegalArgumentException when data array length is less than 1
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Row<Q> of(final Q[] data)
        {
            Throw.when(data.length < 1, IllegalArgumentException.class, "data array length < 1");
            double[] dataSi = new double[data.length];
            for (int i = 0; i < data.length; i++)
            {
                dataSi[i] = data[i].si();
            }
            return ofSi(dataSi, data[0].getDisplayUnit());
        }

        /**
         * Create a new row VectorN with a unit, based on a DataGridSi storage object that contains SI data.
         * @param dataSi the data of the vector, in SI unit.
         * @param displayUnit the display unit to use
         * @throws IllegalArgumentException when the number of rows or rows does not have a positive value or when the vector is
         *             initialized with more than one row
         * @return a new row VectorN with a unit, based on a DataGridSi storage object that contains SI data
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Row<Q> ofSi(final DataGridSi<?> dataSi, final Unit<?, Q> displayUnit)
        {
            return new VectorN.Row<Q>(dataSi, displayUnit.getBaseUnit()).setDisplayUnit(displayUnit);
        }

        /**
         * Create a new row VectorN with a unit, based on a quantity list that contains data. The display unit will be taken
         * from the first quantity in the list.
         * @param data the data of the vector, in the given unit.
         * @return a new row VectorN with a display unit, based on a quantity list
         * @throws IllegalArgumentException when data size is less than 1
         * @param <Q> the quantity type
         */
        public static <Q extends Quantity<Q>> VectorN.Row<Q> of(final List<Q> data)
        {
            Throw.when(data.size() < 1, IllegalArgumentException.class, "data.size < 1");
            double[] dataSi = new double[data.size()];
            for (int i = 0; i < data.size(); i++)
            {
                dataSi[i] = data.get(i).si();
            }
            return ofSi(dataSi, data.get(0).getDisplayUnit());
        }

        // ------------------------------------------ AS METHODS ------------------------------------------

        /**
         * Return the vector 'as' a vector with a known quantity, using a unit to express the result in. Throw a Runtime
         * exception when the SI units of this vector and the target vector do not match. The dataSi object containing the
         * vector values is NOT copied.
         * @param targetUnit the unit to convert the vector to
         * @return a quantity typed in the target vector class
         * @throws IllegalArgumentException when the units do not match
         * @param <TQ> target quantity type
         */
        public <TQ extends Quantity<TQ>> VectorN.Row<TQ> as(final Unit<?, TQ> targetUnit) throws IllegalArgumentException
        {
            Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                    "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit,
                    getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
            return new VectorN.Row<TQ>(this.dataSi, targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
        }

        /**
         * Return this matrix as a 1-element column vector. Shape must be 1 x 1.
         * @return a {@code Vector1} with identical SI data and display unit
         * @throws IllegalStateException if shape is not 1 x 1
         */
        public Vector1<Q> asVector1()
        {
            Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class, "Matrix is not 1x1");
            final double[] data = unsafeSiArray();
            return new Vector1<Q>(data[0], getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        /**
         * Return this matrix as a 2-element row vector. Shape must be 1 x 2.
         * @return a {@code Vector2.Row} with identical SI data and display unit
         * @throws IllegalStateException if shape is not 1 x 2
         */
        public Vector2.Row<Q> asVector2Row()
        {
            Throw.when(rows() != 1 || cols() != 2, IllegalStateException.class, "Matrix is not 1x2");
            final double[] data = unsafeSiArray();
            return new Vector2.Row<Q>(data[0], data[1], getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        /**
         * Return this matrix as a 3-element row vector. Shape must be 1 x 3.
         * @return a {@code Vector3.Row} with identical SI data and display unit
         * @throws IllegalStateException if shape is not 1 x 3
         */
        public Vector3.Row<Q> asVector3Row()
        {
            Throw.when(rows() != 1 || cols() != 3, IllegalStateException.class, "Matrix is not 1x3");
            final double[] data = unsafeSiArray();
            return new Vector3.Row<Q>(data[0], data[1], data[2], getDisplayUnit().getBaseUnit())
                    .setDisplayUnit(getDisplayUnit());
        }

    }

}
