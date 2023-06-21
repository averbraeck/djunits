package org.djunits.value;

import org.djunits.unit.Unit;
import org.djunits.value.base.Scalar;
import org.djunits.value.storage.Storage;
import org.djunits.value.storage.StorageType;
import org.djutils.exceptions.Throw;

/**
 * AbstractIndexedValue.java.
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit type
 * @param <S> the scalar type for the U unit
 * @param <T> the value type for this unit
 * @param <D> the data storage type
 */
public abstract class IndexedValue<U extends Unit<U>, S extends Scalar<U, S>,
        T extends IndexedValue<U, S, T, D>, D extends Storage<D>> implements Value<U, T>
{
    /** */
    private static final long serialVersionUID = 20190927L;

    /** The display unit of this AbstractValue. */
    private U displayUnit;

    /** If set, any modification of the data must be preceded by replacing the data with a local copy. */
    private boolean copyOnWrite = false;

    /** helper flag to indicate whether the data is mutable or not. */
    private boolean mutable = false;

    /**
     * Construct a new IndexedValue.
     * @param displayUnit U; the unit of the new AbstractValue
     */
    public IndexedValue(final U displayUnit)
    {
        Throw.whenNull(displayUnit, "display unit cannot be null");
        this.displayUnit = displayUnit;
    }

    /** {@inheritDoc} */
    @Override
    public final U getDisplayUnit()
    {
        return this.displayUnit;
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayUnit(final U newUnit)
    {
        Throw.whenNull(newUnit, "newUnit may not be null");
        this.displayUnit = newUnit;
    }

    /**
     * Retrieve the data object. Method can only be used within package and by subclasses.
     * @return D; the internal data
     */
    protected abstract D getData();

    /**
     * Set the data object. Method can only be used within package and by subclasses.
     * @param data D; the internal data
     */
    protected abstract void setData(D data);

    /**
     * Return the StorageType (DENSE, SPARSE, etc.) for the stored vector.
     * @return StorageType; the storage type (DENSE, SPARSE, etc.) for the stored vector
     */
    public final StorageType getStorageType()
    {
        return getData().getStorageType();
    }

    /**
     * Check the copyOnWrite flag and, if it is set, make a deep copy of the data and clear the flag.
     * @throws ValueRuntimeException if the vector is immutable
     */
    protected final void checkCopyOnWrite()
    {
        Throw.when(!this.mutable, ValueRuntimeException.class, "Immutable Vector cannot be modified");
        if (isCopyOnWrite())
        {
            setData(getData().copy());
            setCopyOnWrite(false);
        }
    }

    /**
     * Retrieve the value of the copyOnWrite flag.
     * @return boolean
     */
    protected final boolean isCopyOnWrite()
    {
        return this.copyOnWrite;
    }

    /**
     * Change the copyOnWrite flag.
     * @param copyOnWrite boolean; the new value for the copyOnWrite flag
     */
    protected final void setCopyOnWrite(final boolean copyOnWrite)
    {
        this.copyOnWrite = copyOnWrite;
    }

    /**
     * Return whether the data is mutable or not.
     * @return boolean; whether the data is mutable or not
     */
    public final boolean isMutable()
    {
        return this.mutable;
    }

    /**
     * Set helper flag to indicate whether the data is mutable or not.
     * @param mutable boolean; helper flag to indicate whether the data is mutable or not
     */
    protected final void setMutable(final boolean mutable)
    {
        this.mutable = mutable;
    }

    /**
     * Turn the immutable flag on for this vector.
     * @return T; the vector with a raised immutable flag
     */
    public final T immutable()
    {
        if (isMutable())
        {
            setCopyOnWrite(true);
        }
        T result = clone();
        result.setCopyOnWrite(false);
        result.setMutable(false);
        return result;
    }

    /**
     * Turn the immutable flag off for this internal storage.
     * @return T; the internal storage with a cleared immutable flag
     */
    public final T mutable()
    {
        if (isMutable())
        {
            setCopyOnWrite(true);
        }
        T result = clone();
        result.setCopyOnWrite(true);
        result.setMutable(true);
        return result;
    }

    /**
     * Return whether the internal storage type of the indexed value is dense or not.
     * @return boolean; whether the internal storage type of the indexed value is dense or not
     */
    public final boolean isDense()
    {
        return getData().isDense();
    }

    /**
     * Return whether the internal storage type of the indexed value is sparse or not.
     * @return boolean; whether the internal storage type of the indexed value is sparse or not
     */
    public final boolean isSparse()
    {
        return getData().isSparse();
    }

    /**
     * Count the number of cells that have a non-zero SI value.
     * @return int; the number of cells having non-zero SI value
     */
    public final int cardinality()
    {
        return getData().cardinality();
    }

    /**
     * Create and return a dense version of this internal storage. When the data was already dense, the current version is
     * returned and no copy will be made of the data.
     * @return T; a dense version of this internal storage
     */
    public abstract T toDense();

    /**
     * Create and return a sparse version of this internal storage. When the data was already sparse, the current version is
     * returned and no copy will be made of the data.
     * @return T; a sparse version of this internal storage
     */
    public abstract T toSparse();

    /**
     * Return the class of the corresponding scalar.
     * @return Class&lt;S&gt;; the class of the corresponding scalar
     */
    public abstract Class<S> getScalarClass();

    /**
     * Multiply all values of this vector by the multiplier. This only works if the vector is mutable.
     * @param multiplier double; the factor by which to multiply all values
     * @return V; this modified vector
     * @throws ValueRuntimeException in case the vector is immutable
     */
    public abstract T multiplyBy(double multiplier);

    /**
     * Divide all values of this vector by the divisor. This only works if the vector is mutable.
     * @param divisor double; the value by which to divide all values
     * @return V; this modified vector
     * @throws ValueRuntimeException in case the vector is immutable
     */
    public abstract T divideBy(double divisor);    
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public T clone()
    {
        try
        {
            T result = (T) super.clone();
            result.setData(getData().copy());
            result.setCopyOnWrite(false);
            return result;
        }
        catch (CloneNotSupportedException exception)
        {
            throw new RuntimeException(exception);
        }
    }

}
