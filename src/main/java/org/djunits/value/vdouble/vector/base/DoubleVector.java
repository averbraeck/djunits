package org.djunits.value.vdouble.vector.base;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Vector;
import org.djunits.value.formatter.Format;
import org.djunits.value.storage.StorageType;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.djunits.value.vdouble.vector.data.DoubleVectorDataDense;
import org.djunits.value.vdouble.vector.data.DoubleVectorDataSparse;
import org.djutils.exceptions.Throw;

/**
 * The most basic abstract class for the DoubleVector.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit
 * @param <S> the scalar with unit U
 * @param <V> the generic vector type
 */
public abstract class DoubleVector<U extends Unit<U>, S extends DoubleScalar<U, S>, V extends DoubleVector<U, S, V>>
        extends Vector<U, S, V, DoubleVectorData>
{
    /** */
    private static final long serialVersionUID = 20161015L;

    /** The stored data as an object, can be sparse or dense. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected DoubleVectorData data;

    /**
     * Construct a new DoubleVector.
     * @param data an internal data object
     * @param unit the unit
     */
    DoubleVector(final DoubleVectorData data, final U unit)
    {
        super(unit);
        Throw.whenNull(data, "data cannot be null");
        this.data = data;
    }

    /**
     * Instantiate a new vector of the class of this vector. This can be used instead of the DoubleVector.instiantiate() methods
     * in case another vector of this class is known. The method is faster than DoubleVector.instantiate, and it will also work
     * if the vector is user-defined.
     * @param dvd the data used to instantiate the vector
     * @param displayUnit the display unit of the vector
     * @return a vector of the correct type
     */
    public abstract V instantiateVector(DoubleVectorData dvd, U displayUnit);

    /**
     * Instantiate a new scalar for the class of this vector. This can be used instead of the DoubleScalar.instiantiate()
     * methods in case a vector of this class is known. The method is faster than DoubleScalar.instantiate, and it will also
     * work if the vector and/or scalar are user-defined.
     * @param valueSI the SI value of the scalar
     * @param displayUnit the unit in which the value will be displayed
     * @return a scalar of the correct type, belonging to the vector type
     */
    public abstract S instantiateScalarSI(double valueSI, U displayUnit);

    @Override
    protected final DoubleVectorData getData()
    {
        return this.data;
    }

    @Override
    protected void setData(final DoubleVectorData data)
    {
        this.data = data;
    }

    /**
     * Create a double[] array filled with the values in the standard SI unit.
     * @return array of values in the standard SI unit
     */
    public final double[] getValuesSI()
    {
        return getData().getDenseVectorSI();
    }

    /**
     * Create a double[] array filled with the values in the original unit.
     * @return the values in the original unit
     */
    public final double[] getValuesInUnit()
    {
        return getValuesInUnit(getDisplayUnit());
    }

    /**
     * Create a double[] array filled with the values converted into a specified unit.
     * @param targetUnit the unit into which the values are converted for use
     * @return the values converted into the specified unit
     */
    public final double[] getValuesInUnit(final U targetUnit)
    {
        double[] values = getValuesSI();
        for (int i = values.length; --i >= 0;)
        {
            values[i] = ValueUtil.expressAsUnit(values[i], targetUnit);
        }
        return values;
    }

    @Override
    public final int size()
    {
        return getData().size();
    }

    /**
     * Check that a provided index is valid.
     * @param index the value to check
     * @throws IndexOutOfBoundsException when index is invalid
     */
    protected final void checkIndex(final int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException(
                    "index out of range (valid range is 0.." + (size() - 1) + ", got " + index + ")");
        }
    }

    /**
     * Retrieve the value stored at a specified position in the standard SI unit.
     * @param index index of the value to retrieve
     * @return value at position index in the standard SI unit
     * @throws IndexOutOfBoundsException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final double getSI(final int index) throws IndexOutOfBoundsException
    {
        checkIndex(index);
        return getData().getSI(index);
    }

    @Override
    public S get(final int index) throws IndexOutOfBoundsException
    {
        return DoubleScalar.instantiateSI(getSI(index), getDisplayUnit());
    }

    /**
     * Retrieve the value stored at a specified position in the original unit.
     * @param index index of the value to retrieve
     * @return value at position index in the original unit
     * @throws IndexOutOfBoundsException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final double getInUnit(final int index) throws IndexOutOfBoundsException
    {
        return ValueUtil.expressAsUnit(getSI(index), getDisplayUnit());
    }

    /**
     * Retrieve the value stored at a specified position converted into a specified unit.
     * @param index index of the value to retrieve
     * @param targetUnit the unit for the result
     * @return value at position index converted into the specified unit
     * @throws IndexOutOfBoundsException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final double getInUnit(final int index, final U targetUnit) throws IndexOutOfBoundsException
    {
        return ValueUtil.expressAsUnit(getSI(index), targetUnit);
    }

    /**
     * Set the value, specified in the standard SI unit, at the specified position.
     * @param index the index of the value to set
     * @param valueSI the value, specified in the standard SI unit
     * @throws IndexOutOfBoundsException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final void setSI(final int index, final double valueSI) throws IndexOutOfBoundsException
    {
        checkIndex(index);
        checkCopyOnWrite();
        getData().setSI(index, valueSI);
    }

    /**
     * Set the value, specified in the (current) display unit, at the specified position.
     * @param index the index of the value to set
     * @param valueInUnit the value, specified in the (current) display unit
     * @throws IndexOutOfBoundsException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setInUnit(final int index, final double valueInUnit) throws IndexOutOfBoundsException
    {
        setSI(index, ValueUtil.expressAsSIUnit(valueInUnit, getDisplayUnit()));
    }

    /**
     * Set the value, specified in the <code>valueUnit</code>, at the specified position.
     * @param index the index of the value to set
     * @param valueInUnit the value, specified in the (current) display unit
     * @param valueUnit the unit in which the <code>valueInUnit</code> is expressed
     * @throws IndexOutOfBoundsException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setInUnit(final int index, final double valueInUnit, final U valueUnit) throws IndexOutOfBoundsException
    {
        setSI(index, ValueUtil.expressAsSIUnit(valueInUnit, valueUnit));
    }

    /**
     * Set the scalar value at the specified position.
     * @param index the index of the value to set
     * @param value the value to set
     * @throws IndexOutOfBoundsException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void set(final int index, final S value) throws IndexOutOfBoundsException
    {
        setSI(index, value.si);
    }

    @SuppressWarnings("unchecked")
    @Override
    public S[] getScalars()
    {
        S[] array = (S[]) Array.newInstance(getScalarClass(), size());
        for (int i = 0; i < size(); i++)
        {
            array[i] = get(i);
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V toSparse()
    {
        V result;
        if (getStorageType().equals(StorageType.SPARSE))
        {
            result = (V) this;
            result.setDisplayUnit(getDisplayUnit());
        }
        else
        {
            result = instantiateVector(getData().toSparse(), getDisplayUnit());
        }
        result.setDisplayUnit(getDisplayUnit());
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V toDense()
    {
        V result;
        if (getStorageType().equals(StorageType.DENSE))
        {
            result = (V) this;
            result.setDisplayUnit(getDisplayUnit());
        }
        else
        {
            result = instantiateVector(getData().toDense(), getDisplayUnit());
        }
        return result;
    }

    /**
     * Execute a function on a cell by cell basis. Note: May be expensive when used on sparse data.
     * @param doubleFunction the function to apply
     * @return this updated vector
     */
    @SuppressWarnings("unchecked")
    public final V assign(final DoubleFunction doubleFunction)
    {
        checkCopyOnWrite();
        if (getData() instanceof DoubleVectorDataDense)
        {
            ((DoubleVectorDataDense) getData()).assign(doubleFunction);
        }
        else
        {
            this.data = ((DoubleVectorDataSparse) getData()).toDense().assign(doubleFunction).toSparse();
        }
        return (V) this;
    }

    @Override
    public final V abs()
    {
        return assign(DoubleMathFunctions.ABS);
    }

    @Override
    public final V ceil()
    {
        return assign(DoubleMathFunctions.CEIL);
    }

    @Override
    public final V floor()
    {
        return assign(DoubleMathFunctions.FLOOR);
    }

    @Override
    public final V neg()
    {
        return assign(DoubleMathFunctions.NEG);
    }

    @Override
    public final V rint()
    {
        return assign(DoubleMathFunctions.RINT);
    }

    @Override
    public String toString()
    {
        return toString(getDisplayUnit(), false, true);
    }

    @Override
    public String toString(final U displayUnit)
    {
        return toString(displayUnit, false, true);
    }

    @Override
    public String toString(final boolean verbose, final boolean withUnit)
    {
        return toString(getDisplayUnit(), verbose, withUnit);
    }

    @Override
    public String toString(final U displayUnit, final boolean verbose, final boolean withUnit)
    {
        StringBuffer buf = new StringBuffer();
        if (verbose)
        {
            String ar = this instanceof Absolute ? "Abs " : "Rel ";
            String ds = getData().isDense() ? "Dense  " : getData().isSparse() ? "Sparse " : "?????? ";
            if (isMutable())
            {
                buf.append("Mutable   " + ar + ds);
            }
            else
            {
                buf.append("Immutable " + ar + ds);
            }
        }
        buf.append("[");
        for (int i = 0; i < size(); i++)
        {
            try
            {
                double d = ValueUtil.expressAsUnit(getSI(i), displayUnit);
                buf.append(" " + Format.format(d));
            }
            catch (IndexOutOfBoundsException ve)
            {
                buf.append(" " + "********************".substring(0, Format.DEFAULTSIZE));
            }
        }
        buf.append("]");
        if (withUnit)
        {
            buf.append(" " + displayUnit.getLocalizedDisplayAbbreviation());
        }
        return buf.toString();
    }

    /**
     * Centralized size equality check.
     * @param other other DoubleVector
     * @throws NullPointerException when other vector is null
     * @throws ValueRuntimeException when vectors have unequal size
     */
    protected final void checkSize(final DoubleVector<?, ?, ?> other) throws ValueRuntimeException
    {
        Throw.whenNull(other, "Other vector is null");
        Throw.when(size() != other.size(), ValueRuntimeException.class, "The vectors have different sizes: %d != %d", size(),
                other.size());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public int hashCode()
    {
        final int prime = 31;
        int result = getDisplayUnit().getStandardUnit().hashCode();
        result = prime * result + ((this.data == null) ? 0 : this.data.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings({"checkstyle:designforextension", "checkstyle:needbraces"})
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DoubleVector<?, ?, ?> other = (DoubleVector<?, ?, ?>) obj;
        if (!getDisplayUnit().getStandardUnit().equals(other.getDisplayUnit().getStandardUnit()))
            return false;
        if (this.data == null)
        {
            if (other.data != null)
                return false;
        }
        else if (!this.data.equals(other.data))
            return false;
        return true;
    }

    /* ============================================================================================ */
    /* =============================== ITERATOR METHODS AND CLASS ================================= */
    /* ============================================================================================ */

    @Override
    public Iterator<S> iterator()
    {
        return new Itr();
    }

    /**
     * The iterator class is loosely based in AbstractList.Itr. It does not throw a ConcurrentModificationException, because the
     * size of the vector does not change. Normal (non-mutable) vectors cannot change their size, nor their content. The only
     * thing for the MutableVector that can change is its content, not its length.
     */
    protected class Itr implements Iterator<S>, Serializable
    {
        /** ... */
        private static final long serialVersionUID = 20191018L;

        /** index of next element to return. */
        private int cursor = 0;

        @Override
        public boolean hasNext()
        {
            return this.cursor != size();
        }

        @Override
        public S next()
        {
            if (this.cursor >= size())
            {
                throw new NoSuchElementException();
            }
            try
            {
                int i = this.cursor;
                S next = get(i);
                this.cursor = i + 1;
                return next;
            }
            catch (IndexOutOfBoundsException exception)
            {
                throw new RuntimeException(exception);
            }
        }

        @Override
        public void remove()
        {
            throw new RuntimeException("Remove function cannot be applied on fixed-size DJUNITS Vector");
        }

        @Override
        public String toString()
        {
            return "Itr [cursor=" + this.cursor + "]";
        }

    }

}
