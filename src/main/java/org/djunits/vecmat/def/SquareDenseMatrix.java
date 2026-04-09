package org.djunits.vecmat.def;

import java.util.Arrays;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.util.ArrayMath;
import org.djutils.exceptions.Throw;

/**
 * SquareDenseMatrix implements the core functions for a matrix with n x n real-valued entries. The data is stored in a dense
 * double[] field. The matrix is immutable, except for the display unit, which can be changed.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <M> the 'SELF' square dense matrix type
 * @param <SI> the square dense matrix type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic square dense matrix type with generics &lt;?, ?&lt; for Hadamard operations
 */
public abstract class SquareDenseMatrix<Q extends Quantity<Q>, M extends SquareDenseMatrix<Q, M, SI, H>,
        SI extends SquareDenseMatrix<SIQuantity, SI, ?, ?>, H extends SquareDenseMatrix<?, ?, ?, ?>>
        extends SquareMatrix<Q, M, SI, H>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The n x n values in SI-units as a row-major array. */
    private final double[] dataSi;

    /** the order (n in n x n) of the matrix. */
    private final int order;

    /**
     * Create a new SquareDenseMatrix with a unit.
     * @param dataSi the matrix values {a11, a12, ..., a21, a22, ...} expressed in the SI unit
     * @param displayUnit the display unit to use
     * @param order the order of the square matrix (number of rows/columns)
     * @throws IllegalArgumentException when dataSi.length != order * order.
     */
    protected SquareDenseMatrix(final double[] dataSi, final Unit<?, Q> displayUnit, final int order)
    {
        super(displayUnit);
        Throw.when(dataSi.length != order * order, IllegalArgumentException.class,
                "SquareDenseMatrix initialized with %d values instead of %d", dataSi.length, order * order);
        this.dataSi = dataSi.clone();
        this.order = order;
    }

    @Override
    public double[] unsafeSiArray()
    {
        return this.dataSi;
    }

    @Override
    public double[] getSiArray()
    {
        return this.dataSi.clone();
    }

    @Override
    public double si(final int row, final int col)
    {
        checkRow(row);
        checkCol(col);
        return this.dataSi[this.order * row + col];
    }

    @Override
    public int rows()
    {
        return this.order;
    }

    @Override
    public int cols()
    {
        return this.order;
    }

    @Override
    public int nonZeroCount()
    {
        return ArrayMath.nnz(this.dataSi);
    }

    @Override
    public double[] getRowSi(final int row)
    {
        checkRow(row);
        double[] vSi = new double[this.order];
        for (int col = 0; col < this.order; col++)
        {
            vSi[col] = this.dataSi[this.order * row + col];
        }
        return vSi;
    }

    @Override
    public double[] getColumnSi(final int col)
    {
        checkCol(col);
        double[] vSi = new double[this.order];
        for (int row = 0; row < this.order; row++)
        {
            vSi[row] = this.dataSi[this.order * row + col];
        }
        return vSi;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(this.dataSi);
        return result;
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SquareDenseMatrix<?, ?, ?, ?> other = (SquareDenseMatrix<?, ?, ?, ?>) obj;
        return Arrays.equals(this.dataSi, other.dataSi);
    }

}
