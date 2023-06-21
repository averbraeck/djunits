package org.djunits.value.vfloat.vector.base;

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
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.djutils.exceptions.Throw;

/**
 * The most basic abstract class for the FloatVector.
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
public abstract class FloatVector<U extends Unit<U>, S extends FloatScalar<U, S>,
        V extends FloatVector<U, S, V>> extends Vector<U, S, V, FloatVectorData>
{
    /** */
    private static final long serialVersionUID = 20161015L;

    /** The stored data as an object, can be sparse or dense. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected FloatVectorData data;

    /**
     * Construct a new FloatVector.
     * @param data FloatVectorData; an internal data object
     * @param unit U; the unit
     */
    FloatVector(final FloatVectorData data, final U unit)
    {
        super(unit);
        Throw.whenNull(data, "data cannot be null");
        this.data = data;
    }

    /**
     * Instantiate a new vector of the class of this vector. This can be used instead of the FloatVector.instiantiate() methods
     * in case another vector of this class is known. The method is faster than FloatVector.instantiate, and it will also work
     * if the vector is user-defined.
     * @param fvd FloatVectorData; the data used to instantiate the vector
     * @param displayUnit U; the display unit of the vector
     * @return V; a vector of the correct type
     */
    public abstract V instantiateVector(FloatVectorData fvd, U displayUnit);

    /**
     * Instantiate a new scalar for the class of this vector. This can be used instead of the FloatScalar.instiantiate() methods
     * in case a vector of this class is known. The method is faster than FloatScalar.instantiate, and it will also work if the
     * vector and/or scalar are user-defined.
     * @param valueSI float; the SI value of the scalar
     * @param displayUnit U; the unit in which the value will be displayed
     * @return S; a scalar of the correct type, belonging to the vector type
     */
    public abstract S instantiateScalarSI(float valueSI, U displayUnit);
    
    /** {@inheritDoc} */
    @Override
    protected final FloatVectorData getData()
    {
        return this.data;
    }

    /** {@inheritDoc} */
    @Override
    protected void setData(final FloatVectorData data)
    {
        this.data = data;
    }

    /**
     * Create a float[] array filled with the values in the standard SI unit.
     * @return float[]; array of values in the standard SI unit
     */
    public final float[] getValuesSI()
    {
        return getData().getDenseVectorSI();
    }

    /**
     * Create a float[] array filled with the values in the original unit.
     * @return float[]; the values in the original unit
     */
    public final float[] getValuesInUnit()
    {
        return getValuesInUnit(getDisplayUnit());
    }

    /**
     * Create a float[] array filled with the values converted into a specified unit.
     * @param targetUnit U; the unit into which the values are converted for use
     * @return float[]; the values converted into the specified unit
     */
    public final float[] getValuesInUnit(final U targetUnit)
    {
        float[] values = getValuesSI();
        for (int i = values.length; --i >= 0;)
        {
            values[i] = (float) ValueUtil.expressAsUnit(values[i], targetUnit);
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
     * @return float; value at position index in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final float getSI(final int index) throws ValueRuntimeException
    {
        checkIndex(index);
        return getData().getSI(index);
    }

    /** {@inheritDoc} */
    @Override
    public S get(final int index) throws ValueRuntimeException
    {
        return FloatScalar.instantiateSI(getSI(index), getDisplayUnit());
    }

    /**
     * Retrieve the value stored at a specified position in the original unit.
     * @param index int; index of the value to retrieve
     * @return float; value at position index in the original unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final float getInUnit(final int index) throws ValueRuntimeException
    {
        return (float) ValueUtil.expressAsUnit(getSI(index), getDisplayUnit());
    }

    /**
     * Retrieve the value stored at a specified position converted into a specified unit.
     * @param index int; index of the value to retrieve
     * @param targetUnit U; the unit for the result
     * @return float; value at position index converted into the specified unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final float getInUnit(final int index, final U targetUnit) throws ValueRuntimeException
    {
        return (float) ValueUtil.expressAsUnit(getSI(index), targetUnit);
    }

    /**
     * Set the value, specified in the standard SI unit, at the specified position.
     * @param index int; the index of the value to set
     * @param valueSI float; the value, specified in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public final void setSI(final int index, final float valueSI) throws ValueRuntimeException
    {
        checkIndex(index);
        checkCopyOnWrite();
        getData().setSI(index, valueSI);
    }

    /**
     * Set the value, specified in the (current) display unit, at the specified position.
     * @param index int; the index of the value to set
     * @param valueInUnit float; the value, specified in the (current) display unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setInUnit(final int index, final float valueInUnit) throws ValueRuntimeException
    {
        setSI(index, (float) ValueUtil.expressAsSIUnit(valueInUnit, getDisplayUnit()));
    }

    /**
     * Set the value, specified in the <code>valueUnit</code>, at the specified position.
     * @param index int; the index of the value to set
     * @param valueInUnit float; the value, specified in the (current) display unit
     * @param valueUnit U; the unit in which the <code>valueInUnit</code> is expressed
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setInUnit(final int index, final float valueInUnit, final U valueUnit) throws ValueRuntimeException
    {
        setSI(index, (float) ValueUtil.expressAsSIUnit(valueInUnit, valueUnit));
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
     * @param floatFunction FloatFunction; the function to apply
     * @return V; this updated vector
     */
    @SuppressWarnings("unchecked")
    public final V assign(final FloatFunction floatFunction)
    {
        checkCopyOnWrite();
        this.data.assign(floatFunction);
        return (V) this;
    }

    /** {@inheritDoc} */
    @Override
    public final V abs()
    {
        return assign(FloatMathFunctions.ABS);
    }

    /** {@inheritDoc} */
    @Override
    public final V ceil()
    {
        return assign(FloatMathFunctions.CEIL);
    }

    /** {@inheritDoc} */
    @Override
    public final V floor()
    {
        return assign(FloatMathFunctions.FLOOR);
    }

    /** {@inheritDoc} */
    @Override
    public final V neg()
    {
        return assign(FloatMathFunctions.NEG);
    }

    /** {@inheritDoc} */
    @Override
    public final V rint()
    {
        return assign(FloatMathFunctions.RINT);
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
                float f = (float) ValueUtil.expressAsUnit(getSI(i), displayUnit);
                buf.append(" " + Format.format(f));
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
     * @param other FloatVector&lt;?, ?, ?&gt;; other FloatVector
     * @throws NullPointerException when other vector is null
     * @throws ValueRuntimeException when vectors have unequal size
     */
    protected final void checkSize(final FloatVector<?, ?, ?> other) throws ValueRuntimeException
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
        FloatVector<?, ?, ?> other = (FloatVector<?, ?, ?>) obj;
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
