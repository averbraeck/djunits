package org.djunits.value.vdouble.vector.data;

import java.util.Arrays;

import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleFunction2;

/**
 * Stores sparse data for a DoubleVector and carries out basic operations.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class DoubleVectorDataSparse extends DoubleVectorData
{
    /** */
    private static final long serialVersionUID = 1L;

    /** The index values of the Vector. */
    private int[] indices;

    /** The length of the vector (padded with 0 after highest index in indices). */
    private final int size;

    /**
     * Create a vector with sparse data.
     * @param vectorSI double[]; the data to store
     * @param indices int[]; the index values of the Vector
     * @param size int; the length of the vector (padded with 0 after highest index in indices)
     */
    public DoubleVectorDataSparse(final double[] vectorSI, final int[] indices, final int size)
    {
        super(StorageType.SPARSE);
        this.vectorSI = vectorSI;
        this.indices = indices;
        this.size = size;
    }

    /** {@inheritDoc} */
    @Override
    public final int cardinality()
    {
        return this.indices.length;
    }

    /** {@inheritDoc} */
    @Override
    public DoubleVectorData assign(final DoubleFunction doubleFunction)
    {
        if (doubleFunction.apply(0d) != 0d)
        {
            // It is most unlikely that the result AND the left and right operands are efficiently stored in Sparse format
            DoubleVectorDataSparse result = toDense().assign(doubleFunction).toSparse();
            this.indices = result.indices;
            this.vectorSI = result.vectorSI;
            return this;
        }
        // The code below relies on the fact that doubleFunction.apply(0d) yields 0d
        int currentSize = size();
        if (currentSize > 16)
        {
            currentSize = 16;
        }
        int[] newIndices = new int[currentSize];
        double[] newValues = new double[currentSize];
        int nonZeroValues = 0;
        int ownIndex = 0;
        while (ownIndex < this.indices.length)
        {
            int index = this.indices[ownIndex];
            double value = doubleFunction.apply(this.vectorSI[ownIndex]);
            ownIndex++;
            if (value != 0d)
            {
                if (nonZeroValues >= currentSize)
                {
                    // increase size of arrays
                    currentSize *= 2;
                    if (currentSize > size())
                    {
                        currentSize = size();
                    }
                    int[] newNewIndices = new int[currentSize];
                    System.arraycopy(newIndices, 0, newNewIndices, 0, newIndices.length);
                    newIndices = newNewIndices;
                    double[] newNewValues = new double[currentSize];
                    System.arraycopy(newValues, 0, newNewValues, 0, newValues.length);
                    newValues = newNewValues;
                }
                newIndices[nonZeroValues] = index;
                newValues[nonZeroValues] = value;
                nonZeroValues++;
            }
        }
        if (nonZeroValues < currentSize)
        {
            // reduce size of arrays
            int[] newNewIndices = new int[nonZeroValues];
            System.arraycopy(newIndices, 0, newNewIndices, 0, nonZeroValues);
            newIndices = newNewIndices;
            double[] newNewValues = new double[nonZeroValues];
            System.arraycopy(newValues, 0, newNewValues, 0, nonZeroValues);
            newValues = newNewValues;
        }
        this.indices = newIndices;
        this.vectorSI = newValues;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataSparse assign(final DoubleFunction2 doubleFunction, final DoubleVectorData right)
    {
        if (doubleFunction.apply(0d, 0d) != 0d)
        {
            // It is most unlikely that the result AND the left and right operands are efficiently stored in Sparse format
            DoubleVectorDataSparse result = toDense().assign(doubleFunction, right).toSparse();
            this.indices = result.indices;
            this.vectorSI = result.vectorSI;
            return this;
        }
        // The code below relies on the fact that doubleFunction.apply(0d, 0d) yields 0d
        checkSizes(right);
        int currentSize = size();
        if (currentSize > 16)
        {
            currentSize = 16;
        }
        int[] newIndices = new int[currentSize];
        double[] newValues = new double[currentSize];
        int nonZeroValues = 0;
        int ownIndex = 0;
        int otherIndex = 0;
        if (right.isSparse())
        { // both are sparse, result must be sparse
            DoubleVectorDataSparse other = (DoubleVectorDataSparse) right;
            while (ownIndex < this.indices.length || otherIndex < other.indices.length)
            {
                double value;
                int index;
                if (ownIndex < this.indices.length && otherIndex < other.indices.length)
                { // neither we nor right has run out of values
                    if (this.indices[ownIndex] == other.indices[otherIndex])
                    {
                        value = doubleFunction.apply(this.vectorSI[ownIndex], other.vectorSI[otherIndex]);
                        index = this.indices[ownIndex];
                        ownIndex++;
                        otherIndex++;
                    }
                    else if (this.indices[ownIndex] < other.indices[otherIndex])
                    {
                        // we have a non-zero; right has a zero
                        value = doubleFunction.apply(this.vectorSI[ownIndex], 0d);
                        index = this.indices[ownIndex];
                        ownIndex++;
                    }
                    else
                    {
                        // we have a zero; right has a non-zero
                        value = doubleFunction.apply(0d, other.vectorSI[otherIndex]);
                        index = other.indices[otherIndex];
                        otherIndex++;
                    }
                }
                else if (ownIndex < this.indices.length)
                { // right has run out of values; we have not
                    value = doubleFunction.apply(this.vectorSI[ownIndex], 0d);
                    index = this.indices[ownIndex];
                    ownIndex++;
                }
                else
                { // we have run out of values; right has not
                    value = doubleFunction.apply(0d, other.vectorSI[otherIndex]);
                    index = other.indices[otherIndex];
                    otherIndex++;
                }
                if (value != 0d)
                {
                    if (nonZeroValues >= currentSize)
                    {
                        // increase size of arrays
                        currentSize *= 2;
                        if (currentSize > size())
                        {
                            currentSize = size();
                        }
                        int[] newNewIndices = new int[currentSize];
                        System.arraycopy(newIndices, 0, newNewIndices, 0, newIndices.length);
                        newIndices = newNewIndices;
                        double[] newNewValues = new double[currentSize];
                        System.arraycopy(newValues, 0, newNewValues, 0, newValues.length);
                        newValues = newNewValues;
                    }
                    newIndices[nonZeroValues] = index;
                    newValues[nonZeroValues] = value;
                    nonZeroValues++;
                }
            }
        }
        else
        { // we are sparse; other is dense, result must be sparse
            DoubleVectorDataDense other = (DoubleVectorDataDense) right;
            while (otherIndex < right.vectorSI.length)
            {
                double value;
                int index = otherIndex;
                if (ownIndex < this.indices.length)
                { // neither we nor right has run out of values
                    if (this.indices[ownIndex] == otherIndex)
                    {
                        value = doubleFunction.apply(this.vectorSI[ownIndex], other.vectorSI[otherIndex]);
                        ownIndex++;
                    }
                    else
                    {
                        // we have a zero; other has a value
                        value = doubleFunction.apply(0d, other.vectorSI[otherIndex]);
                    }
                    otherIndex++;
                }
                else
                { // we have run out of values; right has not
                    value = doubleFunction.apply(0d, other.vectorSI[otherIndex]);
                    otherIndex++;
                }
                if (value != 0d)
                {
                    if (nonZeroValues >= currentSize)
                    {
                        // increase size of arrays
                        currentSize *= 2;
                        if (currentSize > size())
                        {
                            currentSize = size();
                        }
                        int[] newNewIndices = new int[currentSize];
                        System.arraycopy(newIndices, 0, newNewIndices, 0, newIndices.length);
                        newIndices = newNewIndices;
                        double[] newNewValues = new double[currentSize];
                        System.arraycopy(newValues, 0, newNewValues, 0, newValues.length);
                        newValues = newNewValues;
                    }
                    newIndices[nonZeroValues] = index;
                    newValues[nonZeroValues] = value;
                    nonZeroValues++;
                }
            }
        }
        if (nonZeroValues < currentSize)
        {
            // reduce size of arrays
            int[] newNewIndices = new int[nonZeroValues];
            System.arraycopy(newIndices, 0, newNewIndices, 0, nonZeroValues);
            newIndices = newNewIndices;
            double[] newNewValues = new double[nonZeroValues];
            System.arraycopy(newValues, 0, newNewValues, 0, nonZeroValues);
            newValues = newNewValues;
        }
        this.indices = newIndices;
        this.vectorSI = newValues;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataDense toDense()
    {
        double[] denseSI = new double[this.size];
        for (int index = 0; index < this.vectorSI.length; index++)
        {
            denseSI[this.indices[index]] = this.vectorSI[index];
        }
        return new DoubleVectorDataDense(denseSI);
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataSparse toSparse()
    {
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final int size()
    {
        return this.size;
    }

    /** {@inheritDoc} */
    @Override
    public final double getSI(final int index)
    {
        int internalIndex = Arrays.binarySearch(this.indices, index);
        return internalIndex < 0 ? 0.0 : this.vectorSI[internalIndex];
    }

    /** {@inheritDoc} */
    @Override
    public final void setSI(final int index, final double valueSI)
    {
        int internalIndex = Arrays.binarySearch(this.indices, index);
        if (internalIndex >= 0)
        {
            if (valueSI == 0f)
            {
                // remove room
                int[] indicesNew = new int[this.indices.length - 1];
                double[] vectorSINew = new double[this.vectorSI.length - 1];
                System.arraycopy(this.indices, 0, indicesNew, 0, internalIndex);
                System.arraycopy(this.vectorSI, 0, vectorSINew, 0, internalIndex);
                System.arraycopy(this.indices, internalIndex + 1, indicesNew, internalIndex,
                        this.indices.length - internalIndex - 1);
                System.arraycopy(this.vectorSI, internalIndex + 1, vectorSINew, internalIndex,
                        this.indices.length - internalIndex - 1);
                this.indices = indicesNew;
                this.vectorSI = vectorSINew;
            }
            else
            {
                this.vectorSI[internalIndex] = valueSI;
            }
            return;
        }

        // make room. TODO increase size in chunks
        internalIndex = -internalIndex - 1;
        int[] indicesNew = new int[this.indices.length + 1];
        double[] vectorSINew = new double[this.vectorSI.length + 1];
        System.arraycopy(this.indices, 0, indicesNew, 0, internalIndex);
        System.arraycopy(this.vectorSI, 0, vectorSINew, 0, internalIndex);
        // System.out.println("arraycopy1 current size is " + this.indices.length + " srcPos=" + internalIndex +
        // ", new size is "
        // + indicesNew.length + " dstPos=" + (internalIndex + 1) + " length=" + (this.indices.length - internalIndex));
        System.arraycopy(this.indices, internalIndex, indicesNew, internalIndex + 1, this.indices.length - internalIndex);
        System.arraycopy(this.vectorSI, internalIndex, vectorSINew, internalIndex + 1, this.indices.length - internalIndex);
        // System.arraycopy(this.indices, internalIndex, indicesNew, internalIndex - 1, this.indices.length - internalIndex);
        // System.arraycopy(this.vectorSI, internalIndex, vectorSINew, internalIndex - 1, this.indices.length - internalIndex);
        indicesNew[internalIndex] = index;
        vectorSINew[internalIndex] = valueSI;
        this.indices = indicesNew;
        this.vectorSI = vectorSINew;
    }

    /** {@inheritDoc} */
    @Override
    public final double[] getDenseVectorSI()
    {
        return toDense().vectorSI;
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorDataSparse copy()
    {
        double[] vCopy = new double[this.vectorSI.length];
        System.arraycopy(this.vectorSI, 0, vCopy, 0, this.vectorSI.length);
        int[] iCopy = new int[this.indices.length];
        System.arraycopy(this.indices, 0, iCopy, 0, this.indices.length);
        return new DoubleVectorDataSparse(vCopy, iCopy, this.size);
    }

    /**
     * Instantiate a DoubleVectorDataSparse from an array.
     * @param valuesSI double[]; the (SI) values to store
     * @return the DoubleVectorDataSparse
     */
    public static DoubleVectorDataSparse instantiate(final double[] valuesSI)
    {
        // determine number of non-null cells
        int length = (int) Arrays.stream(valuesSI).parallel().filter(d -> d != 0.0).count();
        double[] sparseSI = new double[length];
        int[] indices = new int[length];

        // fill the sparse data structures. Cannot be parallelized because of stateful and sequence-sensitive count
        int count = 0;
        for (int i = 0; i < valuesSI.length; i++)
        {
            if (valuesSI[i] != 0.0)
            {
                sparseSI[count] = valuesSI[i];
                indices[count] = i;
                count++;
            }
        }
        return new DoubleVectorDataSparse(sparseSI, indices, valuesSI.length);
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorData plus(final DoubleVectorData right)
    {
        if (right.isDense())
        {
            DoubleVectorData result = right.plus(this);
            return result;
        }
        checkSizes(right);
        return this.copy().incrementBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorData minus(final DoubleVectorData right)
    {
        return this.copy().decrementBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorData times(final DoubleVectorData right)
    {
        return this.copy().multiplyBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public final DoubleVectorData divide(final DoubleVectorData right) throws ValueRuntimeException
    {
        if (right.isSparse())
        {
            // Sparse divided by sparse makes a dense
            return this.toDense().divideBy(right);
        }
        // Sparse divided by dense makes a sparse
        return this.copy().divideBy(right);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings({"checkstyle:needbraces", "checkstyle:designforextension"})
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DoubleVectorData))
            return false;
        DoubleVectorData other = (DoubleVectorData) obj;
        if (this.size() != other.size())
            return false;
        if (other instanceof DoubleVectorDataDense)
            return super.equals(other);
        if (getClass() != obj.getClass())
            return false;
        // Both are sparse
        if (!Arrays.equals(this.indices, ((DoubleVectorDataSparse) other).indices))
            return false;
        return Arrays.equals(this.vectorSI, ((DoubleVectorDataSparse) other).vectorSI);
    }

}
