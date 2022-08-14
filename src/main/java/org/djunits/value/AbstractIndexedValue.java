package org.djunits.value;

import org.djunits.Throw;
import org.djunits.unit.Unit;
import org.djunits.value.base.Scalar;
import org.djunits.value.storage.AbstractStorage;
import org.djunits.value.storage.StorageType;

/**
 * AbstractIndexedValue.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit type
 * @param <S> the scalar type for the U unit
 * @param <T> the value type for this unit
 * @param <D> the data storage type
 */
public abstract class AbstractIndexedValue<U extends Unit<U>, S extends Scalar<U, S>,
        T extends AbstractIndexedValue<U, S, T, D>, D extends AbstractStorage<D>> extends AbstractValue<U, T>
        implements IndexedValue<U, S, T>, Cloneable
{
    /** */
    private static final long serialVersionUID = 20190927L;

    /** If set, any modification of the data must be preceded by replacing the data with a local copy. */
    private boolean copyOnWrite = false;

    /** helper flag to indicate whether the data is mutable or not. */
    private boolean mutable = false;

    /**
     * Construct a new AbstractIndexedValue.
     * @param unit U; the unit
     */
    protected AbstractIndexedValue(final U unit)
    {
        super(unit);
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

    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
    public final boolean isDense()
    {
        return getData().isDense();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isSparse()
    {
        return getData().isSparse();
    }

    /** {@inheritDoc} */
    @Override
    public final int cardinality()
    {
        return getData().cardinality();
    }

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
