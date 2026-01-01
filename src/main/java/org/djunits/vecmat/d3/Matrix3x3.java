package org.djunits.vecmat.d3;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.SquareMatrix;
import org.djutils.exceptions.Throw;

/**
 * Matrix3D implements a matrix with 3x3 real-valued entries. The matrix is immutable, except for the display unit, which can be
 * changed. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public class Matrix3x3<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends SquareMatrix<Q, U, Matrix3x3<Q, U>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Matrix3D with a unit.
     * @param aSi the matrix values [a11, a12, a13, a21, a22, a23, a31, a32, a33] expressed in SI or BASE units
     * @param displayUnit the display unit to use
     */
    protected Matrix3x3(final double[] aSi, final U displayUnit)
    {
        super(aSi, displayUnit, 3);
    }

    /**
     * Create a new Matrix3D with a unit, based on a 1-dimensional array.
     * @param valueArray the matrix values {a11, a12, 13, ..., a31, a32, a33} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new Matrix3D with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Matrix3x3<Q, U> of(final double[] valueArray,
            final U displayUnit)
    {
        Throw.whenNull(valueArray, "valueArray");
        Throw.whenNull(displayUnit, "displayUnit");
        double[] aSi = new double[valueArray.length];
        for (int i = 0; i < valueArray.length; i++)
            aSi[i] = displayUnit.toBaseValue(valueArray[i]);
        return new Matrix3x3<Q, U>(aSi, displayUnit);
    }

    /**
     * Create a new Matrix3D with a unit, based on a 2-dimensional grid.
     * @param valueGrid the matrix values {{a11, a12, a13}, ..., {a31, a32, a33}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new Matrix3D with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Matrix3x3<Q, U> of(final double[][] valueGrid,
            final U displayUnit)
    {
        Throw.whenNull(valueGrid, "valueGrid");
        Throw.whenNull(displayUnit, "displayUnit");
        double[] aSi = new double[4];
        Throw.when(valueGrid.length != 3 || valueGrid[0].length != 3 || valueGrid[1].length != 3 || valueGrid[2].length != 3,
                IllegalArgumentException.class, "valueGrid is not a 3x3 array");
        for (int r = 0; r < valueGrid.length; r++)
            for (int c = 0; c < valueGrid.length; c++)
                aSi[3 * r + c] = displayUnit.toBaseValue(valueGrid[r][c]);
        return new Matrix3x3<Q, U>(aSi, displayUnit);
    }

    @Override
    protected Matrix3x3<Q, U> instantiate(final double[] aSiNew)
    {
        return new Matrix3x3<Q, U>(aSiNew, getDisplayUnit());
    }

    @Override
    public Matrix3x3<SIQuantity, SIUnit> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), 3);
        return new Matrix3x3<SIQuantity, SIUnit>(invData, getDisplayUnit().siUnit().invert());
    }

    @Override
    public Matrix3x3<SIQuantity, SIUnit> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), 3);
        return new Matrix3x3<SIQuantity, SIUnit>(invData, getDisplayUnit().siUnit().pow(order() - 1));
    }

    /**
     * Multiply this matrix with another matrix using matrix multiplication and return the result.
     * @param otherMat the matrix to multiply with.
     * @return the matrix from the multiplication with the correct unit
     */
    public Matrix3x3<SIQuantity, SIUnit> multiply(final Matrix3x3<?, ?> otherMat)
    {
        double[] resultData = MatrixMath.multiply(si(), otherMat.si(), 3, 3, 3);
        return new Matrix3x3<SIQuantity, SIUnit>(resultData, getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a column vector, resulting in a column vector.
     * @param otherVec the column vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector3.Col<SIQuantity, SIUnit> multiply(final Vector3.Col<?, ?> otherVec)
    {
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 3, 3, 1);
        return new Vector3.Col<SIQuantity, SIUnit>(resultData[0], resultData[1], resultData[2],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    /**
     * Return the matrix 'as' a matrix with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the matrix to
     * @return a quantity typed in the target matrix class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Matrix3x3<TQ, TU> as(final TU targetUnit)
            throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new Matrix3x3<TQ, TU>(si(), targetUnit);
    }

}
