package org.djunits.value.vdouble.matrix.base;

import java.io.Serializable;

import org.djunits.Throw;
import org.djunits.unit.Unit;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.DoubleScalarInterface;

/**
 * Data point for a matrix that can be used for constructing sparse matrices.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit type of the value
 * @param <S> the scalar type of the value
 */
public class DoubleSparseValue<U extends Unit<U>, S extends DoubleScalarInterface<U, S>> implements Serializable
{
    /** ... */
    private static final long serialVersionUID = 20191018L;

    /** the row in the matrix. */
    private final int row;

    /** the column in the matrix. */
    private final int column;

    /** the value of the data point in the matrix. */
    private final double valueSI;

    /**
     * Create a data point for a sparse matrix.
     * @param row int; the row of the sparse data point in the matrix
     * @param column int; the column of the sparse data point in the matrix
     * @param value S; the value in the given unit of the data point in the matrix
     */
    public DoubleSparseValue(final int row, final int column, final S value)
    {
        this(row, column, checkNull(value).getSI());
    }

    /**
     * Check for null pointer in constructor.
     * @param value S; the scalar to check
     * @return S; the untouched scalar value
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     */
    private static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>> S checkNull(final S value)
    {
        Throw.whenNull(value, "value may not be null");
        return value;
    }

    /**
     * Create a data point for a sparse matrix.
     * @param row int; the row of the sparse data point in the matrix
     * @param column int; the column of the sparse data point in the matrix
     * @param valueInUnit double; the value in the given unit of the data point in the matrix
     * @param unit U; the unit of the value
     */
    public DoubleSparseValue(final int row, final int column, final double valueInUnit, final U unit)
    {
        this(row, column, (float) ValueUtil.expressAsSIUnit(valueInUnit, unit));
    }

    /**
     * Create a data point for a sparse matrix.
     * @param row int; the row of the sparse data point in the matrix
     * @param column int; the column of the sparse data point in the matrix
     * @param valueSI double; the SI value of the data point in the matrix
     */
    public DoubleSparseValue(final int row, final int column, final double valueSI)
    {
        Throw.when(row < 0, ValueRuntimeException.class, "row may not be negative");
        Throw.when(column < 0, ValueRuntimeException.class, "column may not be negative");
        this.row = row;
        this.column = column;
        this.valueSI = valueSI;
    }

    /**
     * @return the row in the matrix
     */
    public final int getRow()
    {
        return this.row;
    }

    /**
     * @return the column in the matrix
     */
    public final int getColumn()
    {
        return this.column;
    }

    /**
     * @return the SI value of the data point in the matrix
     */
    public final double getValueSI()
    {
        return this.valueSI;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "DoubleSparseValue [row=" + this.row + ", column=" + this.column + ", valueSI=" + this.valueSI + "]";
    }

}
