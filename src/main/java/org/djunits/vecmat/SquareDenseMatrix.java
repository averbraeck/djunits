package org.djunits.vecmat;

import java.util.Arrays;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.Math2;
import org.djunits.util.MatrixMath;
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

    /**
     * Return a new matrix with the given SI or BASE values.
     * @param dataSiNew the values for the new matrix
     * @return a new matrix with the provided SI or BASE values
     */
    protected abstract M instantiate(double[] dataSiNew);

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
    public M scaleBy(final double factor)
    {
        return instantiate(ArrayMath.scaleBy(this.dataSi, factor));
    }

    @Override
    public M add(final M other)
    {
        return instantiate(ArrayMath.add(this.dataSi, other.si()));
    }

    @Override
    public M subtract(final M other)
    {
        return instantiate(ArrayMath.subtract(this.dataSi, other.si()));
    }

    @Override
    public M abs()
    {
        return instantiate(ArrayMath.abs(this.dataSi));
    }

    @Override
    public Q normFrobenius()
    {
        return getDisplayUnit().ofSi(Math.sqrt(Math2.sumSqr(this.dataSi))).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q mean()
    {
        return getDisplayUnit().ofSi(sum().si() / this.dataSi.length).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q min()
    {
        return getDisplayUnit().ofSi(Math2.min(this.dataSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q max()
    {
        return getDisplayUnit().ofSi(Math2.max(this.dataSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q median()
    {
        return getDisplayUnit().ofSi(Math2.median(this.dataSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q sum()
    {
        return getDisplayUnit().ofSi(Math2.sum(this.dataSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public M add(final Q increment)
    {
        return instantiate(ArrayMath.add(this.dataSi, increment.si()));
    }

    @Override
    public M subtract(final Q decrement)
    {
        return instantiate(ArrayMath.add(this.dataSi, -decrement.si()));
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public M transpose()
    {
        double[] newSi = new double[this.dataSi.length];
        int n = order();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                newSi[n * i + j] = this.dataSi[n * j + i];
        return instantiate(newSi);
    }

    @Override
    public SIQuantity determinant()
    {
        SIUnit siu = getDisplayUnit().siUnit();
        int[] newDim = new int[SIUnit.NUMBER_DIMENSIONS];
        for (int i = 0; i < SIUnit.NUMBER_DIMENSIONS; i++)
        {
            newDim[i] = (byte) (order() * (int) siu.siDimensions()[i]);
        }
        SIUnit detSIUnit = new SIUnit(newDim);
        return new SIQuantity(MatrixMath.determinant(this.dataSi, order()), detSIUnit);
    }

    @Override
    public Q trace()
    {
        return getDisplayUnit().ofSi(MatrixMath.trace(this.dataSi, order()));
    }

    @Override
    public boolean isSymmetric()
    {
        return MatrixMath.isSymmetric(this.dataSi, order());
    }

    @Override
    public boolean isSymmetric(final Q tolerance)
    {
        return MatrixMath.isSymmetric(this.dataSi, order(), tolerance.si());
    }

    @Override
    public boolean isSkewSymmetric()
    {
        return MatrixMath.isSkewSymmetric(this.dataSi, order());
    }

    @Override
    public boolean isSkewSymmetric(final Q tolerance)
    {
        return MatrixMath.isSkewSymmetric(this.dataSi, order(), tolerance.si());
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
