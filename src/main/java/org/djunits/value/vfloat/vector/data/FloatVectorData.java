package org.djunits.value.vfloat.vector.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.IntStream;

import org.djunits.unit.Unit;
import org.djunits.unit.scale.Scale;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.Storage;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatFunction2;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djutils.exceptions.Throw;

/**
 * Stores the data for a FloatVector and carries out basic operations.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public abstract class FloatVectorData extends Storage<FloatVectorData> implements Serializable
{
    /** */
    private static final long serialVersionUID = 1L;

    /** The internal storage of the Vector; can be sparse or dense. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected float[] vectorSI;

    /**
     * Construct a new FloatVectorData object.
     * @param storageType StorageType; the data type.
     */
    FloatVectorData(final StorageType storageType)
    {
        super(storageType);
    }

    /* ============================================================================================ */
    /* ====================================== INSTANTIATION ======================================= */
    /* ============================================================================================ */

    /**
     * Instantiate a FloatVectorData with the right data type.
     * @param values float[]; the (SI) values to store
     * @param scale Scale; the scale of the unit to use for conversion to SI
     * @param storageType StorageType; the data type to use
     * @return FloatVectorData; the FloatVectorData with the right data type
     * @throws NullPointerException when values are null, or storageType is null
     */
    public static FloatVectorData instantiate(final float[] values, final Scale scale, final StorageType storageType)
    {
        Throw.whenNull(values, "FloatVectorData.instantiate: float[] values is null");
        Throw.whenNull(scale, "FloatVectorData.instantiate: scale is null");
        Throw.whenNull(storageType, "FloatVectorData.instantiate: storageType is null");

        float[] valuesSI = new float[values.length];
        IntStream.range(0, values.length).parallel().forEach(i -> valuesSI[i] = (float) scale.toStandardUnit(values[i]));

        if (storageType.equals(StorageType.DENSE))
        {
            return new FloatVectorDataDense(valuesSI);
        }
        else
        {
            return FloatVectorDataSparse.instantiate(valuesSI);
        }
    }

    /**
     * Instantiate a FloatVectorData with the right data type.
     * @param values List&lt;? extends Number&gt;; the values to store, can either be a list of numbers, or a list of
     *            FloatScalars
     * @param scale Scale; the scale of the unit to use for conversion to SI
     * @param storageType StorageType; the data type to use
     * @return FloatVectorData; the FloatVectorData with the right data type
     * @throws NullPointerException when list is null, or storageType is null
     */
    public static FloatVectorData instantiate(final List<? extends Number> values, final Scale scale,
            final StorageType storageType)
    {
        Throw.whenNull(values, "FloatVectorData.instantiate: float[] values is null");
        Throw.whenNull(scale, "FloatVectorData.instantiate: scale is null");
        Throw.whenNull(storageType, "FloatVectorData.instantiate: storageType is null");
        Throw.when(values.parallelStream().filter(d -> d == null).count() > 0, NullPointerException.class,
                "values contains one or more null values");

        float[] valuesSI = new float[values.size()];
        IntStream.range(0, values.size()).parallel()
                .forEach(i -> valuesSI[i] = (float) scale.toStandardUnit(values.get(i).floatValue()));

        if (storageType.equals(StorageType.DENSE))
        {
            return new FloatVectorDataDense(valuesSI);
        }
        else
        {
            return FloatVectorDataSparse.instantiate(valuesSI);
        }
    }

    /**
     * Instantiate a FloatVectorData with the right data type.
     * @param values S[]; the values to store
     * @param storageType StorageType; the data type to use
     * @return FloatVectorData; the FloatVectorData with the right data type
     * @throws NullPointerException when values is null, or storageType is null
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    public static <U extends Unit<U>, S extends FloatScalar<U, S>> FloatVectorData instantiate(final S[] values,
            final StorageType storageType)
    {
        Throw.whenNull(values, "FloatVectorData.instantiate: double[] values is null");
        Throw.whenNull(storageType, "FloatVectorData.instantiate: storageType is null");

        for (S s : values)
        {
            Throw.whenNull(s, "null value in values");
        }

        float[] valuesSI = new float[values.length];
        IntStream.range(0, values.length).parallel().forEach(i -> valuesSI[i] = values[i].getSI());

        if (storageType.equals(StorageType.DENSE))
        {
            return new FloatVectorDataDense(valuesSI);
        }
        else
        {
            return FloatVectorDataSparse.instantiate(valuesSI);
        }
    }

    /**
     * Instantiate a FloatVectorData with the right data type.
     * @param valueMap SortedMap&lt;Integer,? extends Number&gt;; the values to store; either Numbers or FloatScalars
     * @param size int; the size of the vector to pad with 0 after last entry in map
     * @param scale Scale; the scale of the unit to use for conversion to SI
     * @param storageType StorageType; the data type to use
     * @return FloatVectorData; the FloatVectorData with the right data type
     * @throws ValueRuntimeException when length &lt; 0
     * @throws NullPointerException when values is null, or storageType is null
     */
    public static FloatVectorData instantiate(final SortedMap<Integer, ? extends Number> valueMap, final int size,
            final Scale scale, final StorageType storageType) throws ValueRuntimeException
    {
        Throw.whenNull(valueMap, "FloatVectorData.instantiate: values is null");
        Throw.when(size < 0, ValueRuntimeException.class, "size must be >= 0");
        Throw.whenNull(scale, "FloatVectorData.instantiate: scale is null");
        Throw.whenNull(storageType, "FloatVectorData.instantiate: storageType is null");
        for (Integer key : valueMap.keySet())
        {
            Throw.when(key < 0 || key >= size, ValueRuntimeException.class, "Key in values out of range");
        }

        if (storageType.equals(StorageType.DENSE))
        {
            float[] valuesSI = new float[size];
            if (scale.isBaseSIScale())
            {
                valueMap.entrySet().parallelStream().forEach(entry -> valuesSI[entry.getKey()] = entry.getValue().floatValue());
            }
            else
            {
                Arrays.fill(valuesSI, (float) scale.toStandardUnit(0.0));
                valueMap.entrySet().parallelStream().forEach(
                        entry -> valuesSI[entry.getKey()] = (float) scale.toStandardUnit(entry.getValue().floatValue()));
            }
            return new FloatVectorDataDense(valuesSI);
        }

        else // StorageType.SPARSE

        {
            int nonZeroCount;
            if (scale.isBaseSIScale())
            {
                nonZeroCount = (int) valueMap.values().parallelStream().filter(f -> f.floatValue() != 0f).count();
            }
            else
            {
                // Much harder, and the result is unlikely to be very sparse
                nonZeroCount = size - (int) valueMap.values().parallelStream()
                        .filter(d -> scale.toStandardUnit(d.floatValue()) == 0d).count();
            }
            int[] indices = new int[nonZeroCount];
            float[] valuesSI = new float[nonZeroCount];
            if (scale.isBaseSIScale())
            {
                int index = 0;
                for (Integer key : valueMap.keySet())
                {
                    float value = valueMap.get(key).floatValue();
                    if (0.0 != value)
                    {
                        indices[index] = key;
                        valuesSI[index] = value;
                        index++;
                    }
                }
            }
            else
            {
                Arrays.fill(valuesSI, (float) scale.toStandardUnit(0.0));
                int index = 0;
                int lastKey = 0;
                for (Integer key : valueMap.keySet())
                {
                    for (int i = lastKey; i < key; i++)
                    {
                        indices[index++] = i;
                    }
                    lastKey = key;
                    float value = (float) scale.toStandardUnit(valueMap.get(key).floatValue());
                    if (0.0 != value)
                    {
                        indices[index] = key;
                        valuesSI[index] = value;
                        index++;
                    }
                    lastKey = key + 1;
                }
                while (index < indices.length)
                {
                    indices[index++] = lastKey++;
                }
            }
            return new FloatVectorDataSparse(valuesSI, indices, size);
        }
    }

    /* ============================================================================================ */
    /* ==================================== UTILITY FUNCTIONS ===================================== */
    /* ============================================================================================ */

    /**
     * Retrieve the size of the vector.
     * @return int; the size of the vector
     */
    public abstract int size();

    /**
     * Return the densely stored equivalent of this data.
     * @return FloatVectorDataDense; the dense transformation of this data
     */
    public abstract FloatVectorDataDense toDense();

    /**
     * Return the sparsely stored equivalent of this data.
     * @return FloatVectorDataSparse; the sparse transformation of this data
     */
    public abstract FloatVectorDataSparse toSparse();

    /**
     * Retrieve the SI value of one element of this data.
     * @param index int; the index to get the value for
     * @return the value at the index
     */
    public abstract float getSI(int index);

    /**
     * Sets a value at the index in the vector.
     * @param index int; the index to set the value for
     * @param valueSI float; the value at the index
     */
    public abstract void setSI(int index, float valueSI);

    /**
     * Compute and return the sum of all values.
     * @return double; the sum of the values of all cells
     */
    public final float zSum()
    {
        // this does not copy the data. See http://stackoverflow.com/questions/23106093/how-to-get-a-stream-from-a-float
        return (float) IntStream.range(0, this.vectorSI.length).parallel().mapToDouble(i -> this.vectorSI[i]).sum();
    }

    /**
     * Create and return a dense copy of the data.
     * @return float[]; a safe copy of VectorSI
     */
    public abstract float[] getDenseVectorSI();

    /**
     * Check the sizes of this data object and the other data object.
     * @param other FloatVectorData; the other data object
     * @throws ValueRuntimeException if vectors have different lengths
     */
    protected void checkSizes(final FloatVectorData other) throws ValueRuntimeException
    {
        if (this.size() != other.size())
        {
            throw new ValueRuntimeException("Two data objects used in a FloatVector operation do not have the same size");
        }
    }

    /* ============================================================================================ */
    /* ================================== CALCULATION FUNCTIONS =================================== */
    /* ============================================================================================ */

    /**
     * Apply an operation to each cell.
     * @param floatFunction FloatFunction; the operation to apply
     * @return FloatVectorData; this (modified) float vector data object
     */
    public abstract FloatVectorData assign(FloatFunction floatFunction);

    /**
     * Apply a binary operation on a cell by cell basis.
     * @param floatFunction2 FloatFunction2; the binary operation to apply
     * @param right FloatVectorData; the right operand for the binary operation
     * @return DoubleMatrixData; this (modified) float vector data object
     * @throws ValueRuntimeException when the sizes of the vectors do not match
     */
    abstract FloatVectorData assign(FloatFunction2 floatFunction2, FloatVectorData right) throws ValueRuntimeException;

    /**
     * Add two vectors on a cell-by-cell basis. If both vectors are sparse, a sparse vector is returned, otherwise a dense
     * vector is returned.
     * @param right FloatVectorData; the other data object to add
     * @return FloatVectorData; the sum of this data object and the other data object
     * @throws ValueRuntimeException if vectors have different lengths
     */
    public abstract FloatVectorData plus(FloatVectorData right) throws ValueRuntimeException;

    /**
     * Add a vector to this vector on a cell-by-cell basis. The type of vector (sparse, dense) stays the same.
     * @param right FloatVectorData; the other data object to add
     * @return FloatVectorData; this modified float vector data object
     * @throws ValueRuntimeException if vectors have different lengths
     */
    public final FloatVectorData incrementBy(final FloatVectorData right) throws ValueRuntimeException
    {
        return assign(new FloatFunction2()
        {
            @Override
            public float apply(final float leftValue, final float rightValue)
            {
                return leftValue + rightValue;
            }
        }, right);
    }

    /**
     * Subtract two vectors on a cell-by-cell basis. If both vectors are sparse, a sparse vector is returned, otherwise a dense
     * vector is returned.
     * @param right FloatVectorData; the other data object to subtract
     * @return FloatVectorData; the difference of this data object and the other data object
     * @throws ValueRuntimeException if vectors have different lengths
     */
    public abstract FloatVectorData minus(FloatVectorData right) throws ValueRuntimeException;

    /**
     * Subtract a vector from this vector on a cell-by-cell basis. The type of vector (sparse, dense) stays the same.
     * @param right FloatVectorData; the other data object to subtract
     * @return FloatVectorData; this modified float vector data object
     * @throws ValueRuntimeException if vectors have different lengths
     */
    public final FloatVectorData decrementBy(final FloatVectorData right) throws ValueRuntimeException
    {
        return assign(new FloatFunction2()
        {
            @Override
            public float apply(final float leftValue, final float rightValue)
            {
                return leftValue - rightValue;
            }
        }, right);
    }

    /**
     * Multiply two vector on a cell-by-cell basis. If both vectors are dense, a dense vector is returned, otherwise a sparse
     * vector is returned.
     * @param right FloatVectorData; the other data object to multiply with
     * @return FloatVectorData; a new double vector data store holding the result of the multiplications
     * @throws ValueRuntimeException if vectors have different lengths
     */
    public abstract FloatVectorData times(FloatVectorData right) throws ValueRuntimeException;

    /**
     * Multiply a vector with the values of another vector on a cell-by-cell basis. The type of vector (sparse, dense) stays the
     * same.
     * @param right FloatVectorData; the other data object to multiply with
     * @return FloatVectorData; this modified float vector data store
     * @throws ValueRuntimeException if vectors have different lengths
     */
    public final FloatVectorData multiplyBy(final FloatVectorData right) throws ValueRuntimeException
    {
        assign(new FloatFunction2()
        {
            @Override
            public float apply(final float leftValue, final float rightValue)
            {
                return leftValue * rightValue;
            }
        }, right);
        return this;
    }

    /**
     * Divide two vectors on a cell-by-cell basis. If this vector is sparse and <code>right</code> is dense, a sparse vector is
     * returned, otherwise a dense vector is returned.
     * @param right FloatVectorData; the other data object to divide by
     * @return FloatVectorData; the ratios of the values of this data object and the other data object
     * @throws ValueRuntimeException if vectors have different lengths
     */
    public abstract FloatVectorData divide(FloatVectorData right) throws ValueRuntimeException;

    /**
     * Divide the values of a vector by the values of another vector on a cell-by-cell basis. The type of vector (sparse, dense)
     * stays the same.
     * @param right FloatVectorData; the other data object to divide by
     * @return FloatVectorData; this modified float vector data store
     * @throws ValueRuntimeException if vectors have different lengths
     */
    public final FloatVectorData divideBy(final FloatVectorData right) throws ValueRuntimeException
    {
        return assign(new FloatFunction2()
        {
            @Override
            public float apply(final float leftValue, final float rightValue)
            {
                return leftValue / rightValue;
            }
        }, right);
    }

    /* ============================================================================================ */
    /* =============================== EQUALS, HASHCODE, TOSTRING ================================= */
    /* ============================================================================================ */

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.size();
        for (int index = 0; index < this.size(); index++)
        {
            result = 31 * result + Float.floatToIntBits(getSI(index));
        }
        return result;
    }

    /**
     * Compare contents of a dense and a sparse vector.
     * @param dm FloatVectorDataDense; the dense vector
     * @param sm FloatVectorDataSparse; the sparse vector
     * @return boolean; true if the contents are equal
     */
    protected boolean compareDenseVectorWithSparseVector(final FloatVectorDataDense dm, final FloatVectorDataSparse sm)
    {
        for (int index = 0; index < dm.size(); index++)
        {
            if (dm.getSI(index) != sm.getSI(index))
            {
                return false;
            }
        }
        return true;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FloatVectorData))
            return false;
        FloatVectorData other = (FloatVectorData) obj;
        if (this.size() != other.size())
            return false;
        if (other instanceof FloatVectorDataSparse && this instanceof FloatVectorDataDense)
        {
            return compareDenseVectorWithSparseVector((FloatVectorDataDense) this, (FloatVectorDataSparse) other);
        }
        else if (other instanceof FloatVectorDataDense && this instanceof FloatVectorDataSparse)
        {
            return compareDenseVectorWithSparseVector((FloatVectorDataDense) other, (FloatVectorDataSparse) this);
        }
        // Both are dense (both sparse is handled in FloatVectorDataSparse class)
        return Arrays.equals(this.vectorSI, other.vectorSI);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "FloatVectorData [storageType=" + getStorageType() + ", vectorSI=" + Arrays.toString(this.vectorSI) + "]";
    }

}
