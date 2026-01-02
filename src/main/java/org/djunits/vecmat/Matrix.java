package org.djunits.vecmat;

import java.util.Objects;

import org.djunits.formatter.Format;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.vecmat.operations.VectorMatrixOps;
import org.djutils.exceptions.Throw;

/**
 * Matrix implements the core functions for a matrix with n x m real-valued entries. The matrix is immutable, except for the
 * display unit, which can be changed. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the matrix type
 */
public abstract class Matrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, M extends Matrix<Q, U, M>>
        implements VectorMatrixOps<Q, U, M>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The display unit. */
    private U displayUnit;

    /** The number of rows. */
    private final int rows;

    /** The number of columns. */
    private final int cols;

    /**
     * Create a new Matrix with a unit and a size.
     * @param displayUnit the display unit to use
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    protected Matrix(final U displayUnit, final int rows, final int cols)
    {
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(rows <= 0, IllegalArgumentException.class, "Number of rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "Number of columns <= 0");
        this.displayUnit = displayUnit;
        this.rows = rows;
        this.cols = cols;
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
     * @return the matrix for fluent design
     */
    @SuppressWarnings("unchecked")
    @Override
    public M setDisplayUnit(final U newUnit)
    {
        Throw.whenNull(this.displayUnit, "displayUnit");
        this.displayUnit = newUnit;
        return (M) this;
    }

    @Override
    public int rows()
    {
        return this.rows;
    }

    @Override
    public int cols()
    {
        return this.cols;
    }

    /**
     * Return the (r,c)-value of the matrix in SI or BASE units.
     * @param row the row, from 1, ..., N
     * @param col the column, from 1, ..., N
     * @return the (r,c)-value of the matrix in SI or BASE units
     */
    public abstract double si(int row, int col);

    /**
     * Return the (r,c)-value of the matrix as a quantity with the correct unit.
     * @param row the row, from 1, ..., N
     * @param col the column, from 1, ..., N
     * @return the (r,c)-value of the matrix as a quantity with the correct unit
     */
    public Q value(final int row, final int col)
    {
        return this.displayUnit.ofSi(si(row, col)).setDisplayUnit(this.displayUnit);
    }

    @Override
    public boolean isRelative()
    {
        return value(1, 1).isRelative();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.cols, this.rows);
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
        Matrix<?, ?, ?> other = (Matrix<?, ?, ?>) obj;
        return this.cols == other.cols && this.rows == other.rows;
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public String toString(final U withUnit)
    {
        var s = new StringBuilder();
        for (int r = 1; r <= rows(); r++)
        {
            s.append(r == 1 ? "[" : "\n ");
            for (int c = 1; c <= cols(); c++)
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
