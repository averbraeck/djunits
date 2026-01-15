package org.djunits.vecmat.storage;

import java.io.Serializable;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djutils.exceptions.Throw;

/**
 * Double-precision data point for a matrix that can be used for constructing sparse matrices.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @param <Q> the quantity type of the value
 * @param <U> the unit type of the value
 */
public class DoubleSparseValue<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> implements Serializable
{
    /** */
    private static final long serialVersionUID = 20191018L;

    /** the row in the matrix. */
    private final int row;

    /** the column in the matrix. */
    private final int column;

    /** the value of the data point in the matrix. */
    private final double si;

    /**
     * Create a data point for a sparse matrix.
     * @param row the row of the sparse data point in the matrix
     * @param column the column of the sparse data point in the matrix
     * @param value the value in the given unit of the data point in the matrix
     */
    public DoubleSparseValue(final int row, final int column, final Q value)
    {
        this(row, column, checkNull(value).si());
    }

    /**
     * Check for null pointer in constructor.
     * @param value the scalar to check
     * @return the untouched scalar value
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    private static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Q checkNull(final Q value)
    {
        Throw.whenNull(value, "value may not be null");
        return value;
    }

    /**
     * Create a data point for a sparse matrix.
     * @param row the row of the sparse data point in the matrix
     * @param column the column of the sparse data point in the matrix
     * @param valueInUnit the value in the given unit of the data point in the matrix
     * @param unit the unit of the value
     */
    public DoubleSparseValue(final int row, final int column, final double valueInUnit, final U unit)
    {
        this(row, column, unit.toBaseValue(valueInUnit));
    }

    /**
     * Create a data point for a sparse matrix.
     * @param row the row of the sparse data point in the matrix
     * @param column the column of the sparse data point in the matrix
     * @param si the SI value of the data point in the matrix
     */
    public DoubleSparseValue(final int row, final int column, final double si)
    {
        Throw.when(row < 0, IllegalArgumentException.class, "row may not be negative");
        Throw.when(column < 0, IllegalArgumentException.class, "column may not be negative");
        this.row = row;
        this.column = column;
        this.si = si;
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
    public final double si()
    {
        return this.si;
    }

    @Override
    public String toString()
    {
        return "DoubleSparseValue [row=" + this.row + ", column=" + this.column + ", valueSI=" + this.si + "]";
    }

}
