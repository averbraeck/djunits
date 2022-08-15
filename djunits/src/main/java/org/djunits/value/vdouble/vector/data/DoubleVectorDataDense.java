package org.djunits.value.vdouble.vector.data;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleFunction2;

/**
 * Stores dense data for a DoubleVector and carries out basic operations.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class DoubleVectorDataDense extends DoubleVectorData
{
    /** */
    private static final long serialVersionUID = 1L;

    /**
     * Create a vector with dense data.
     * @param vectorSI double[]; the data to store
     */
    public DoubleVectorDataDense(final double[] vectorSI)
    {
        super(StorageType.DENSE);
        this.vectorSI = new double[vectorSI.length];
        System.arraycopy(vectorSI, 0, this.vectorSI, 0, vectorSI.length);
    }

    /** {@inheritDoc} */
    @Override
    public final int cardinality()
    {
        return (int) Arrays.stream(this.vectorSI).parallel().filter(d -> d != 0.0).count();
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataDense assign(final DoubleFunction doubleFunction)
    {
        IntStream.range(0, size()).parallel().forEach(i -> this.vectorSI[i] = doubleFunction.apply(this.vectorSI[i]));
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataDense assign(final DoubleFunction2 doubleFunction2, final DoubleVectorData right)
    {
        if (right.isDense())
        {
            IntStream.range(0, size()).parallel()
                    .forEach(i -> this.vectorSI[i] = doubleFunction2.apply(this.vectorSI[i], right.vectorSI[i]));
        }
        else
        { // right is sparse
            IntStream.range(0, size()).parallel()
                    .forEach(i -> this.vectorSI[i] = doubleFunction2.apply(this.vectorSI[i], right.getSI(i)));
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataDense toDense()
    {
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataSparse toSparse()
    {
        return DoubleVectorDataSparse.instantiate(this.vectorSI);
    }

    /** {@inheritDoc} */
    @Override
    public final int size()
    {
        return this.vectorSI.length;
    }

    /** {@inheritDoc} */
    @Override
    public final double getSI(final int index)
    {
        return this.vectorSI[index];
    }

    /** {@inheritDoc} */
    @Override
    public final void setSI(final int index, final double valueSI)
    {
        this.vectorSI[index] = valueSI;
    }

    /** {@inheritDoc} */
    @Override
    public final double[] getDenseVectorSI()
    {
        return this.vectorSI.clone();
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataDense copy()
    {
        double[] vCopy = new double[this.vectorSI.length];
        System.arraycopy(this.vectorSI, 0, vCopy, 0, this.vectorSI.length);
        return new DoubleVectorDataDense(vCopy);
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataDense plus(final DoubleVectorData right)
    {
        checkSizes(right);
        return new DoubleVectorDataDense(
                IntStream.range(0, size()).parallel().mapToDouble(i -> getSI(i) + right.getSI(i)).toArray());
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataDense minus(final DoubleVectorData right)
    {
        checkSizes(right);
        return new DoubleVectorDataDense(
                IntStream.range(0, size()).parallel().mapToDouble(i -> getSI(i) - right.getSI(i)).toArray());
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorData times(final DoubleVectorData right)
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
    public final DoubleVectorData divide(final DoubleVectorData right)
    {
        checkSizes(right);
        return this.copy().divideBy(right);
    }

}
