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
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
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
     * @param data DoubleVectorData; an internal data object
     * @param unit U; the unit
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
     * @param dvd DoubleVectorData; the data used to instantiate the vector
     * @param displayUnit U; the display unit of the vector
     * @return V; a vector of the correct type
     */
    public abstract V instantiateVector(DoubleVectorData dvd, U displayUnit);

    /**
     * Instantiate a new scalar for the class of this vector. This can be used instead of the DoubleScalar.instiantiate()
     * methods in case a vector of this class is known. The method is faster than DoubleScalar.instantiate, and it will also
     * work if the vector and/or scalar are user-defined.
     * @param valueSI double; the SI value of the scalar
     * @param displayUnit U; the unit in which the value will be displayed
     * @return S; a scalar of the correct type, belonging to the vector type
     */
    public abstract S instantiateScalarSI(double valueSI, U displayUnit);

    /** {@inheritDoc} */
    @Override
    protected final DoubleVectorData getData()
    {
        return this.data;
    }

    /** {@inheritDoc} */
    @Override
    protected void setData(final DoubleVectorData data)
    {
        this.data = data;
    }

    /**
     * Create a double[] array filled with the values in the standard SI unit.
     * @return double[]; array of values in the standard SI unit
     */
    public final double[] getValuesSI()
    {
        return getData().getDenseVectorSI();
    }

    /**
     * Create a double[] array filled with the values in the original unit.
     * @return double[]; the values in the original unit
     */
    public final double[] getValuesInUnit()
    {
        return getValuesInUnit(getDisplayUnit());
    }

    /**
     * Create a double[] array filled with the values converted into a specified unit.
     * @param targetUnit U; the unit into which the values are converted for use
     * @return double[]; the values converted into the specified unit
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

    /** {@inheritDoc} */
    @Override
    public final int size()
    {
        return getData().size();
    }

    /**
     * Check that a provided index is valid.
     * @param index int; the value to check
     * @throws ValueRuntimeException when index is invalid
     */
    protected final void checkIndex(final int index) throws ValueRuntimeException
    {
        if (index < 0 || index >= size())
        {
            throw new ValueRuntimeException("index out of range (valid range is 0.." + (size() - 1) + ", got " + index + ")");
        }
    }

    /**
     * Retrieve the value stored at a specified position in the standard SI unit.
     * @param index int; index of the value to retrieve
     * @return double; value at position index in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final double getSI(final int index) throws ValueRuntimeException
    {
        checkIndex(index);
        return getData().getSI(index);
    }

    /** {@inheritDoc} */
    @Override
    public S get(final int index) throws ValueRuntimeException
    {
        return DoubleScalar.instantiateSI(getSI(index), getDisplayUnit());
    }

    /**
     * Retrieve the value stored at a specified position in the original unit.
     * @param index int; index of the value to retrieve
     * @return double; value at position index in the original unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final double getInUnit(final int index) throws ValueRuntimeException
    {
        return ValueUtil.expressAsUnit(getSI(index), getDisplayUnit());
    }

    /**
     * Retrieve the value stored at a specified position converted into a specified unit.
     * @param index int; index of the value to retrieve
     * @param targetUnit U; the unit for the result
     * @return double; value at position index converted into the specified unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final double getInUnit(final int index, final U targetUnit) throws ValueRuntimeException
    {
        return ValueUtil.expressAsUnit(getSI(index), targetUnit);
    }

    /**
     * Set the value, specified in the standard SI unit, at the specified position.
     * @param index int; the index of the value to set
     * @param valueSI double; the value, specified in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final void setSI(final int index, final double valueSI) throws ValueRuntimeException
    {
        checkIndex(index);
        checkCopyOnWrite();
        getData().setSI(index, valueSI);
    }

    /**
     * Set the value, specified in the (current) display unit, at the specified position.
     * @param index int; the index of the value to set
     * @param valueInUnit double; the value, specified in the (current) display unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setInUnit(final int index, final double valueInUnit) throws ValueRuntimeException
    {
        setSI(index, ValueUtil.expressAsSIUnit(valueInUnit, getDisplayUnit()));
    }

    /**
     * Set the value, specified in the <code>valueUnit</code>, at the specified position.
     * @param index int; the index of the value to set
     * @param valueInUnit double; the value, specified in the (current) display unit
     * @param valueUnit U; the unit in which the <code>valueInUnit</code> is expressed
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setInUnit(final int index, final double valueInUnit, final U valueUnit) throws ValueRuntimeException
    {
        setSI(index, ValueUtil.expressAsSIUnit(valueInUnit, valueUnit));
    }

    /**
     * Set the scalar value at the specified position.
     * @param index int; the index of the value to set
     * @param value S; the value to set
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void set(final int index, final S value) throws ValueRuntimeException
    {
        setSI(index, value.si);
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
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
     * @param doubleFunction DoubleFunction; the function to apply
     * @return V; this updated vector
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

    /** {@inheritDoc} */
    @Override
    public final V abs()
    {
        return assign(DoubleMathFunctions.ABS);
    }

    /** {@inheritDoc} */
    @Override
    public final V ceil()
    {
        return assign(DoubleMathFunctions.CEIL);
    }

    /** {@inheritDoc} */
    @Override
    public final V floor()
    {
        return assign(DoubleMathFunctions.FLOOR);
    }

    /** {@inheritDoc} */
    @Override
    public final V neg()
    {
        return assign(DoubleMathFunctions.NEG);
    }

    /** {@inheritDoc} */
    @Override
    public final V rint()
    {
        return assign(DoubleMathFunctions.RINT);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return toString(getDisplayUnit(), false, true);
    }

    /** {@inheritDoc} */
    @Override
    public String toString(final U displayUnit)
    {
        return toString(displayUnit, false, true);
    }

    /** {@inheritDoc} */
    @Override
    public String toString(final boolean verbose, final boolean withUnit)
    {
        return toString(getDisplayUnit(), verbose, withUnit);
    }

    /** {@inheritDoc} */
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
            catch (ValueRuntimeException ve)
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
     * @param other DoubleVector&lt;?, ?, ?&gt;; other DoubleVector
     * @throws NullPointerException when other vector is null
     * @throws ValueRuntimeException when vectors have unequal size
     */
    protected final void checkSize(final DoubleVector<?, ?, ?> other) throws ValueRuntimeException
    {
        Throw.whenNull(other, "Other vector is null");
        Throw.when(size() != other.size(), ValueRuntimeException.class, "The vectors have different sizes: %d != %d", size(),
                other.size());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public int hashCode()
    {
        final int prime = 31;
        int result = getDisplayUnit().getStandardUnit().hashCode();
        result = prime * result + ((this.data == null) ? 0 : this.data.hashCode());
        return result;
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
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

        /** {@inheritDoc} */
        @Override
        public boolean hasNext()
        {
            return this.cursor != size();
        }

        /** {@inheritDoc} */
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
            catch (ValueRuntimeException exception)
            {
                throw new RuntimeException(exception);
            }
        }

        /** {@inheritDoc} */
        @Override
        public void remove()
        {
            throw new RuntimeException("Remove function cannot be applied on fixed-size DJUNITS Vector");
        }

        /** {@inheritDoc} */
        @Override
        public String toString()
        {
            return "Itr [cursor=" + this.cursor + "]";
        }

    }

}
