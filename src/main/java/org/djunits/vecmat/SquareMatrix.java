package org.djunits.vecmat;

import java.util.Arrays;
import java.util.Objects;

import org.djunits.formatter.Format;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.Math2;
import org.djunits.util.MatrixMath;
import org.djunits.value.Additive;
import org.djunits.value.Scalable;
import org.djunits.value.Value;
import org.djunits.vecmat.operations.SquareMatrixOps;
import org.djunits.vecmat.operations.VecMatOps;
import org.djutils.exceptions.Throw;

/**
 * SquareMatrix implements the core functions for a matrix with n x n real-valued entries. The matrix is immutable, except for
 * the display unit, which can be changed. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the matrix type
 */
public abstract class SquareMatrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, M extends SquareMatrix<Q, U, M>>
        implements Value<U, M>, Additive<M>, Scalable<M>, SquareMatrixOps<Q, U, M>, VecMatOps<Q, U, M>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The n x n values in si-units. */
    private final double[] dataSi;

    /** The display unit. */
    private U displayUnit;

    /** The order of the matrix (number of rows/columns). */
    private final int order;

    /**
     * Create a new SquareMatrix with a unit.
     * @param aSi the matrix values [a11, a12, ..., a21, a22, ...] expressed in SI or BASE units
     * @param displayUnit the display unit to use
     * @param order the order of the square matrix (number of rows/columns)
     */
    protected SquareMatrix(final double[] aSi, final U displayUnit, final int order)
    {
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(order <= 0, IllegalArgumentException.class, "Square matrix order should be positive, but was %d", order);
        Throw.when(aSi.length != order * order, IllegalArgumentException.class,
                "SquareMatrix initialized with %d values instead of %d", aSi.length, order * order);
        this.order = order;
        this.dataSi = aSi.clone(); // safe copy
        this.displayUnit = displayUnit;
    }

    /**
     * Return a new matrix with the given SI or BASE values.
     * @param aSiNew the new values to use
     * @return a new matrix with the provided SI or BASE values
     */
    protected abstract M instantiate(double[] aSiNew);

    @Override
    public int order()
    {
        return this.order;
    }

    /**
     * Return the display unit of this matrix.
     * @return the display unit of this matrix
     */
    @Override
    public U getDisplayUnit()
    {
        return this.displayUnit;
    }

    /**
     * Set a new display unit of this matrix.
     * @param newUnit the new display unit of this matrix
     */
    @SuppressWarnings("unchecked")
    @Override
    public M setDisplayUnit(final U newUnit)
    {
        this.displayUnit = newUnit;
        return (M) this;
    }

    /**
     * Return the array of SI-values. Note that this is NOT a safe copy.
     * @return the array of SI-values
     */
    public double[] si()
    {
        return this.dataSi;
    }

    /**
     * Return the (r,c)-value of the matrix in SI or BASE units.
     * @param r the row, from 1, ..., N
     * @param c the column, from 1, ..., N
     * @return the (r,c)-value of the matrix in SI or BASE units
     */
    public double si(final int r, final int c)
    {
        return this.dataSi[order() * (r - 1) + c - 1];
    }

    /**
     * Return the x-value of the matrix as a quantity with the correct unit.
     * @param r the row, from 1, ..., N
     * @param c the column, from 1, ..., N
     * @return the x-value of the matrix as a quantity with the correct unit
     */
    public Q value(final int r, final int c)
    {
        return this.displayUnit.ofSi(si(r, c)).setDisplayUnit(this.displayUnit);
    }

    @Override
    public M scaleBy(final double factor)
    {
        return instantiate(ArrayMath.scale(this.dataSi, factor));
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
    public M negate()
    {
        return scaleBy(-1.0);
    }

    @Override
    public M abs()
    {
        return instantiate(ArrayMath.abs(this.dataSi));
    }

    @Override
    public Q normFrobenius()
    {
        return this.displayUnit.ofSi(Math.sqrt(Math2.sumSqr(this.dataSi))).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q mean()
    {
        return this.displayUnit.ofSi(sum().si() / this.dataSi.length).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q min()
    {
        return this.displayUnit.ofSi(Math2.min(this.dataSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q max()
    {
        return this.displayUnit.ofSi(Math2.max(this.dataSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q mode()
    {
        return max();
    }

    @Override
    public Q median()
    {
        return this.displayUnit.ofSi(Math2.median(this.dataSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q sum()
    {
        return this.displayUnit.ofSi(Math2.sum(this.dataSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public M plus(final Q increment)
    {
        return instantiate(ArrayMath.add(this.dataSi, increment.si()));
    }

    @Override
    public M minus(final Q decrement)
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
        return this.displayUnit.ofSi(MatrixMath.trace(this.dataSi, order()));
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
        int result = 1;
        result = prime * result + Arrays.hashCode(this.dataSi);
        result = prime * result + Objects.hash(this.order);
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
        SquareMatrix<?, ?, ?> other = (SquareMatrix<?, ?, ?>) obj;
        return Arrays.equals(this.dataSi, other.dataSi) && this.order == other.order;
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public String toString(final U withUnit)
    {
        var s = new StringBuilder();
        for (int r = 1; r <= order(); r++)
        {
            s.append(r == 1 ? "[" : "\n ");
            for (int c = 1; c <= order(); c++)
            {
                if (c > 1)
                    s.append(", ");
                s.append(Format.format(withUnit.fromBaseValue(si(r, c))));
            }
        }
        s.append("] ");
        s.append(withUnit.getDisplayAbbreviation());
        return s.toString();
    }

    @Override
    public String toString()
    {
        return toString(getDisplayUnit());
    }

}
