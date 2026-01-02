package org.djunits.vecmat;

import java.util.Arrays;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.vecmat.operations.SquareMatrixOps;
import org.djutils.exceptions.Throw;

/**
 * SquareDenseMatrix implements the core functions for a matrix with n x n real-valued entries. The data is stored in a dense
 * double[] field. The matrix is immutable, except for the display unit, which can be changed. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the matrix type
 */
public abstract class SquareDenseMatrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>,
        M extends SquareDenseMatrix<Q, U, M>> extends Matrix<Q, U, M> implements SquareMatrixOps<Q, U, M>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The n x n values in si-units. */
    private final double[] dataSi;

    /**
     * Create a new SquareDenseMatrix with a unit.
     * @param dataSi the matrix values [a11, a12, ..., a21, a22, ...] expressed in SI or BASE units
     * @param displayUnit the display unit to use
     * @param order the order of the square matrix (number of rows/columns)
     */
    protected SquareDenseMatrix(final double[] dataSi, final U displayUnit, final int order)
    {
        super(displayUnit, order, order);
        Throw.when(dataSi.length != order * order, IllegalArgumentException.class,
                "SquareDenseMatrix initialized with %d values instead of %d", dataSi.length, order * order);
        this.dataSi = dataSi.clone(); // safe copy
    }

    @Override
    public int order()
    {
        return rows();
    }

    @Override
    public double[] si()
    {
        return this.dataSi;
    }

    @Override
    public double si(final int r, final int c)
    {
        return this.dataSi[order() * (r - 1) + c - 1];
    }

    @Override
    public boolean isRelative()
    {
        return value(1, 1).isRelative();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(this.dataSi);
        return result;
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        SquareDenseMatrix<?, ?, ?> other = (SquareDenseMatrix<?, ?, ?>) obj;
        return Arrays.equals(this.dataSi, other.dataSi);
    }

}
