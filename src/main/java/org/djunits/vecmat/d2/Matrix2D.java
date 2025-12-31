package org.djunits.vecmat.d2;

import org.djunits.quantity.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.vecmat.SquareMatrix;
import org.djutils.exceptions.Throw;

/**
 * Matrix2D implements a matrix with 2x2 real-valued entries. The matrix is immutable, except for the display unit, which can be
 * changed. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public class Matrix2D<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends SquareMatrix<Q, U, Matrix2D<Q, U>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Matrix2D with a unit.
     * @param aSi the matrix values [a11, a12, a21, a22] expressed in SI or BASE units
     * @param displayUnit the display unit to use
     */
    protected Matrix2D(final double[] aSi, final U displayUnit)
    {
        super(aSi, displayUnit, 2);
    }

    /**
     * Create a new Matrix2D with a unit, based on a 1-dimensional array.
     * @param valueArray the matrix values {a11, a12, a21, a22} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new Matrix2D with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Matrix2D<Q, U> of(final double[] valueArray,
            final U displayUnit)
    {
        Throw.whenNull(valueArray, "valueArray");
        Throw.whenNull(displayUnit, "displayUnit");
        double[] aSi = new double[valueArray.length];
        for (int i = 0; i < valueArray.length; i++)
            aSi[i] = displayUnit.toBaseValue(valueArray[i]);
        return new Matrix2D<Q, U>(aSi, displayUnit);
    }

    /**
     * Create a new Matrix2D with a unit, based on a 2-dimensional grid.
     * @param valueGrid the matrix values {{a11, a12}, {a21, a22}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new Matrix2D with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Matrix2D<Q, U> of(final double[][] valueGrid,
            final U displayUnit)
    {
        Throw.whenNull(valueGrid, "valueGrid");
        Throw.whenNull(displayUnit, "displayUnit");
        double[] aSi = new double[4];
        Throw.when(valueGrid.length != 2 || valueGrid[0].length != 2 || valueGrid[1].length != 2,
                IllegalArgumentException.class, "valueGrid is not a 2x2 array");
        for (int r = 0; r < valueGrid.length; r++)
            for (int c = 0; c < valueGrid.length; c++)
                aSi[2 * r + c] = displayUnit.toBaseValue(valueGrid[r][c]);
        return new Matrix2D<Q, U>(aSi, displayUnit);
    }

    @Override
    protected Matrix2D<Q, U> instantiate(final double[] aSiNew)
    {
        return new Matrix2D<Q, U>(aSiNew, getDisplayUnit());
    }

}
