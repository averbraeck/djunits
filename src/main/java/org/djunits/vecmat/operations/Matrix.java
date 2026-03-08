package org.djunits.vecmat.operations;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;

/**
 * Matrix contains a number of standard operations on matrices of relative quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the vector or matrix type
 */
public interface Matrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, M extends Matrix<Q, U, M>>
        extends VectorMatrixOps<Q, U, M>
{
    /**
     * Return the (r,c)-value of the matrix in SI or BASE units.
     * @param row the row, from 1, ..., N
     * @param col the column, from 1, ..., N
     * @return the (r,c)-value of the matrix in SI or BASE units
     */
    double si(int row, int col);

    /**
     * Return the (r,c)-value of the matrix as a quantity with the correct unit.
     * @param row the row, from 1, ..., N
     * @param col the column, from 1, ..., N
     * @return the (r,c)-value of the matrix as a quantity with the correct unit
     */
    Q get(int row, int col);

    /**
     * Return the matrix as a 2D array of scalars.
     * <p>
     * This implementation allocates a {@code Q[][]} using the runtime class of {@code Q} and fills it directly with
     * {@link #get(int, int)} results. It avoids intermediate {@code List} instances and prevents {@link ClassCastException}
     * caused by casting {@code Object[][]} to {@code Q[][]}.
     * @return a new {@code Q[rows()][cols()]} array; entry {@code [i-1][j-1]} contains {@code value(i, j)}
     */
    Q[][] getScalars();

}
