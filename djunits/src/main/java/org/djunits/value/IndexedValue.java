package org.djunits.value;

import org.djunits.unit.Unit;
import org.djunits.value.base.Scalar;
import org.djunits.value.storage.StorageType;

/**
 * IndexedValue.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit type
 * @param <S> the scalar type for the U unit
 * @param <T> the value type for this unit
 */
public interface IndexedValue<U extends Unit<U>, S extends Scalar<U, S>, T extends IndexedValue<U, S, T>> extends Value<U, T>
{
    /**
     * Return the StorageType (DENSE, SPARSE, etc.) for the stored vector.
     * @return StorageType; the storage type (DENSE, SPARSE, etc.) for the stored vector
     */
    StorageType getStorageType();

    /**
     * Create and return a dense version of this internal storage. When the data was already dense, the current version is
     * returned and no copy will be made of the data.
     * @return T; a dense version of this internal storage
     */
    T toDense();

    /**
     * Create and return a sparse version of this internal storage. When the data was already sparse, the current version is
     * returned and no copy will be made of the data.
     * @return T; a sparse version of this internal storage
     */
    T toSparse();

    /**
     * Return whether the internal storage type of the indexed value is dense or not.
     * @return boolean; whether the internal storage type of the indexed value is dense or not
     */
    boolean isDense();

    /**
     * Return whether the internal storage type of the indexed value is sparse or not.
     * @return boolean; whether the internal storage type of the indexed value is sparse or not
     */
    boolean isSparse();

    /**
     * Return whether the data is mutable or not.
     * @return boolean; whether the data is mutable or not
     */
    boolean isMutable();

    /**
     * Turn the immutable flag on for this vector.
     * @return T; the vector with a raised immutable flag
     */
    T immutable();

    /**
     * Turn the immutable flag off for this internal storage.
     * @return T; the internal storage with a cleared immutable flag
     */
    T mutable();

    /**
     * Count the number of cells that have a non-zero SI value.
     * @return int; the number of cells having non-zero SI value
     */
    int cardinality();

    /**
     * Return the class of the corresponding scalar.
     * @return Class&lt;S&gt;; the class of the corresponding scalar
     */
    Class<S> getScalarClass();

}
