package org.djunits.vecmat.d3;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.def.SquareDenseMatrix;
import org.djutils.exceptions.Throw;

/**
 * Matrix3x3 implements a matrix with 3x3 real-valued entries. The matrix is immutable, except for the display unit, which can
 * be changed.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 */
public class Matrix3x3<Q extends Quantity<Q>>
        extends SquareDenseMatrix<Q, Matrix3x3<Q>, Matrix3x3<SIQuantity>, Matrix3x3<?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Matrix3x3 with a unit.
     * @param arrayInUnit the matrix values [a11, a12, a13, a21, a22, a23, a31, a32, a33] expressed in the display unit
     * @param displayUnit the display unit to use
     */
    protected Matrix3x3(final double[] arrayInUnit, final UnitInterface<?, Q> displayUnit)
    {
        super(arrayInUnit, displayUnit, 3);
    }

    /**
     * Create a new Matrix3x3 with a unit, based on a 1-dimensional array.
     * <p>
     * <strong>Implementation Note:</strong> the condition is also checked by super() but the fail fast approach is used here
     * @param arrayInUnit the matrix values {a11, a12, 13, ..., a31, a32, a33} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type

     * @return a new Matrix3x3 with a unit
     * @throws IllegalArgumentException when valueArray does not contain 3x3 = 9 values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> Matrix3x3<Q> of(final double[] arrayInUnit,
            final UnitInterface<?, Q> displayUnit)
    {
        Throw.whenNull(arrayInUnit, "arrayInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(arrayInUnit.length != 9, IllegalArgumentException.class, "Length of array != 9 but %d", arrayInUnit.length);
        return new Matrix3x3<Q>(arrayInUnit, displayUnit);
    }

    /**
     * Create a new Matrix3x3 with a unit, based on a 2-dimensional grid.
     * @param gridInUnit the matrix values {{a11, a12, a13}, ..., {a31, a32, a33}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type

     * @return a new Matrix3x3 with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> Matrix3x3<Q> of(final double[][] gridInUnit,
            final UnitInterface<?, Q> displayUnit)
    {
        Throw.whenNull(gridInUnit, "gridInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        double[] aInUnit = new double[9];
        Throw.when(
                gridInUnit.length != 3 || gridInUnit[0].length != 3 || gridInUnit[1].length != 3 || gridInUnit[2].length != 3,
                IllegalArgumentException.class, "gridInUnit is not a 3x3 array");
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                aInUnit[3 * r + c] = gridInUnit[r][c];
        return new Matrix3x3<Q>(aInUnit, displayUnit);
    }

    @Override
    public Matrix3x3<Q> instantiateSi(final double[] siNew)
    {
        return new Matrix3x3<Q>(siNew, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix3x3<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new Matrix3x3<SIQuantity>(siNew, siUnit);
    }

    @Override
    public Vector3.Row<Q> getRowVector(final int row)
    {
        checkRow(row);
        return new Vector3.Row<Q>(si(row, 0), si(row, 1), si(row, 2), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector3.Row<Q> mgetRowVector(final int mRow)
    {
        mcheckRow(mRow);
        return new Vector3.Row<Q>(msi(mRow, 1), msi(mRow, 2), msi(mRow, 3), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector3.Col<Q> getColumnVector(final int col)
    {
        checkCol(col);
        return new Vector3.Col<Q>(si(0, col), si(1, col), si(2, col), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector3.Col<Q> mgetColumnVector(final int mCol)
    {
        mcheckCol(mCol);
        return new Vector3.Col<Q>(msi(1, mCol), msi(2, mCol), msi(3, mCol), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector3.Col<Q> getDiagonalVector() throws IllegalStateException
    {
        return new Vector3.Col<Q>(si(0, 0), si(1, 1), si(2, 2), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix3x3<SIQuantity> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), 3);
        return new Matrix3x3<SIQuantity>(invData, getDisplayUnit().siUnit().invert());
    }

    @Override
    public Matrix3x3<SIQuantity> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), 3);
        return new Matrix3x3<SIQuantity>(invData, getDisplayUnit().siUnit().pow(order() - 1));
    }

    @Override
    public Matrix3x3<SIQuantity> invertEntries()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new Matrix3x3<SIQuantity>(ArrayMath.reciprocal(si()), siUnit);
    }

    @Override
    public Matrix3x3<SIQuantity> multiplyEntries(final Matrix3x3<?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix3x3<SIQuantity>(ArrayMath.multiply(si(), other.si()), siUnit);
    }

    @Override
    public Matrix3x3<SIQuantity> divideEntries(final Matrix3x3<?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix3x3<SIQuantity>(ArrayMath.divide(si(), other.si()), siUnit);
    }

    // ------------------------------ MATRIX MULTIPLICATION AND AS() --------------------------

    /**
     * Multiply this matrix with another matrix using matrix multiplication and return the result.
     * @param otherMat the matrix to multiply with.
     * @return the matrix from the multiplication with the correct unit
     */
    public Matrix3x3<SIQuantity> multiply(final Matrix3x3<?> otherMat)
    {
        checkMultiply(otherMat);
        double[] resultData = MatrixMath.multiply(si(), otherMat.si(), 3, 3, 3);
        return new Matrix3x3<SIQuantity>(resultData,
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a column vector, resulting in a column vector.
     * @param otherVec the column vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector3.Col<SIQuantity> multiply(final Vector3.Col<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 3, 3, 1);
        return new Vector3.Col<SIQuantity>(resultData[0], resultData[1], resultData[2],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    @Override
    public Matrix3x3<SIQuantity> multiplyEntries(final Quantity<?> quantity)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
        return new Matrix3x3<SIQuantity>(ArrayMath.scaleBy(si(), quantity.si()), siUnit);
    }

    /**
     * Return the matrix 'as' a matrix with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target matrix class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     */
    public <TQ extends Quantity<TQ>> Matrix3x3<TQ> as(final UnitInterface<?, TQ> targetUnit)
            throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "Matrix3x3.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new Matrix3x3<TQ>(si(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
    }

}
