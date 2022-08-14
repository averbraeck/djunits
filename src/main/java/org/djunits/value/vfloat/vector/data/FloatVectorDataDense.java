package org.djunits.value.vfloat.vector.data;

import java.util.stream.IntStream;

import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatFunction2;

/**
 * Stores dense data for a FloatVector and carries out basic operations.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatVectorDataDense extends FloatVectorData
{
    /** */
    private static final long serialVersionUID = 1L;

    /**
     * Create a vector with dense data.
     * @param vectorSI float[]; the data to store
     */
    public FloatVectorDataDense(final float[] vectorSI)
    {
        super(StorageType.DENSE);
        this.vectorSI = new float[vectorSI.length];
        System.arraycopy(vectorSI, 0, this.vectorSI, 0, vectorSI.length);
    }

    /** {@inheritDoc} */
    @Override
    public final int cardinality()
    {
        // this does not copy the data. See http://stackoverflow.com/questions/23106093/how-to-get-a-stream-from-a-float
        return (int) IntStream.range(0, this.vectorSI.length).parallel().mapToDouble(i -> this.vectorSI[i])
                .filter(d -> d != 0.0).count();
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorDataDense assign(final FloatFunction floatFunction)
    {
        IntStream.range(0, size()).parallel().forEach(i -> this.vectorSI[i] = floatFunction.apply(this.vectorSI[i]));
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorDataDense assign(final FloatFunction2 floatFunction2, final FloatVectorData right)
    {
        if (right.isDense())
        {
            IntStream.range(0, size()).parallel()
                    .forEach(i -> this.vectorSI[i] = floatFunction2.apply(this.vectorSI[i], right.vectorSI[i]));
        }
        else
        { // right is sparse
            IntStream.range(0, size()).parallel()
                    .forEach(i -> this.vectorSI[i] = floatFunction2.apply(this.vectorSI[i], right.getSI(i)));
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorDataDense toDense()
    {
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorDataSparse toSparse()
    {
        return FloatVectorDataSparse.instantiate(this.vectorSI);
    }

    /** {@inheritDoc} */
    @Override
    public final int size()
    {
        return this.vectorSI.length;
    }

    /** {@inheritDoc} */
    @Override
    public final float getSI(final int index)
    {
        return this.vectorSI[index];
    }

    /** {@inheritDoc} */
    @Override
    public final void setSI(final int index, final float valueSI)
    {
        this.vectorSI[index] = valueSI;
    }

    /** {@inheritDoc} */
    @Override
    public final float[] getDenseVectorSI()
    {
        return this.vectorSI.clone();
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorDataDense copy()
    {
        float[] vCopy = new float[this.vectorSI.length];
        System.arraycopy(this.vectorSI, 0, vCopy, 0, this.vectorSI.length);
        return new FloatVectorDataDense(vCopy);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorDataDense plus(final FloatVectorData right)
    {
        checkSizes(right);
        float[] out = new float[size()];
        IntStream.range(0, size()).parallel().forEach(i -> out[i] = getSI(i) + right.getSI(i));
        return new FloatVectorDataDense(out);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorDataDense minus(final FloatVectorData right)
    {
        checkSizes(right);
        float[] out = new float[size()];
        IntStream.range(0, size()).parallel().forEach(i -> out[i] = getSI(i) - right.getSI(i));
        return new FloatVectorDataDense(out);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorData times(final FloatVectorData right)
    {
        if (right.isSparse())
        {
            // result shall be sparse
            return right.times(this);
        }
        // Both are dense
        checkSizes(right);
        return this.copy().multiplyBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatVectorData divide(final FloatVectorData right)
    {
        checkSizes(right);
        return this.copy().divideBy(right);
    }

}
