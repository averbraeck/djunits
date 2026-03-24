package org.djunits.vecmat.d1;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.def.SquareDenseMatrix;
import org.djunits.vecmat.dn.VectorN;
import org.djutils.exceptions.Throw;

/**
 * Matrix1x1 implements a matrix with 1x1 real-valued entries. The matrix is immutable, except for the display unit, which can
 * be changed.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 */
public class Matrix1x1<Q extends Quantity<Q>> extends SquareDenseMatrix<Q, Matrix1x1<Q>, Matrix1x1<SIQuantity>, Matrix1x1<?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Matrix1x1 with a unit.
     * @param arrayInUnit the matrix values {a11} expressed in the displayUnit
     * @param displayUnit the display unit to use
     */
    protected Matrix1x1(final double[] arrayInUnit, final UnitInterface<?, Q> displayUnit)
    {
        super(arrayInUnit, displayUnit, 1);
    }

    /**
     * Create a new Matrix1x1 with a unit, based on a 1-dimensional array.
     * <p>
     * <strong>Implementation Note:</strong> the condition is also checked by super() but the fail fast approach is used here
     * @param arrayInUnit the matrix values {a11, a12, a21, a22} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @return a new Matrix1x1 with a unit
     * @throws IllegalArgumentException when valueArray does not contain 1x1 = 1 value
     */
    public static <Q extends Quantity<Q>> Matrix1x1<Q> of(final double[] arrayInUnit, final UnitInterface<?, Q> displayUnit)
    {
        Throw.whenNull(arrayInUnit, "arrayInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(arrayInUnit.length != 1, IllegalArgumentException.class, "Length of array != 1 but %d", arrayInUnit.length);
        return new Matrix1x1<Q>(arrayInUnit, displayUnit);
    }

    /**
     * Create a new Matrix1x1 with a unit, based on a 2-dimensional grid.
     * @param gridInUnit the matrix values {{a11}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @return a new Matrix1x1 with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> Matrix1x1<Q> of(final double[][] gridInUnit, final UnitInterface<?, Q> displayUnit)
    {
        Throw.whenNull(gridInUnit, "gridInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(gridInUnit.length != 1 || gridInUnit[0].length != 1, IllegalArgumentException.class,
                "gridInUnit is not a 1x1 array");
        return new Matrix1x1<Q>(new double[] {gridInUnit[0][0]}, displayUnit);
    }

    @Override
    public Matrix1x1<Q> instantiateSi(final double[] siNew)
    {
        return new Matrix1x1<Q>(siNew, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix1x1<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new Matrix1x1<SIQuantity>(siNew, siUnit);
    }

    @Override
    public Vector1<Q> getRowVector(final int row)
    {
        checkRow(row);
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> mgetRowVector(final int mRow)
    {
        mcheckRow(mRow);
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> getColumnVector(final int col)
    {
        checkCol(col);
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> mgetColumnVector(final int mCol)
    {
        mcheckCol(mCol);
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> getDiagonalVector() throws IllegalStateException
    {
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix1x1<SIQuantity> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), 1);
        return new Matrix1x1<SIQuantity>(invData, getDisplayUnit().siUnit().invert());
    }

    @Override
    public Matrix1x1<SIQuantity> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), 1);
        return new Matrix1x1<SIQuantity>(invData, getDisplayUnit().siUnit().pow(order() - 1));
    }

    @Override
    public Matrix1x1<SIQuantity> invertEntries()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new Matrix1x1<SIQuantity>(ArrayMath.reciprocal(si()), siUnit);
    }

    @Override
    public Matrix1x1<SIQuantity> multiplyEntries(final Matrix1x1<?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity>(ArrayMath.multiply(si(), other.si()), siUnit);
    }

    @Override
    public Matrix1x1<SIQuantity> divideEntries(final Matrix1x1<?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity>(ArrayMath.divide(si(), other.si()), siUnit);
    }

    // ------------------------------ MATRIX MULTIPLICATION AND AS() --------------------------

    /**
     * Multiply this matrix with another matrix using matrix multiplication and return the result.
     * @param otherMat the matrix to multiply with.
     * @return the matrix from the multiplication with the correct unit
     */
    public Matrix1x1<SIQuantity> multiply(final Matrix1x1<?> otherMat)
    {
        checkMultiply(otherMat);
        double[] resultData = MatrixMath.multiply(si(), otherMat.si(), 1, 1, 1);
        return new Matrix1x1<SIQuantity>(resultData, getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a column vector, resulting in a column vector.
     * @param otherVec the column vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector1<SIQuantity> multiply(final Vector1<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 1, 1);
        return new Vector1<SIQuantity>(resultData[0],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a row vector, resulting in a row vector.
     * @param otherVec the row vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector2.Row<SIQuantity> multiply(final Vector2.Row<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 1, 2);
        return new Vector2.Row<SIQuantity>(resultData[0], resultData[1],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a row vector, resulting in a row vector.
     * @param otherVec the row vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector3.Row<SIQuantity> multiply(final Vector3.Row<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 1, 3);
        return new Vector3.Row<SIQuantity>(resultData[0], resultData[1], resultData[2],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a row vector, resulting in a row vector.
     * @param otherVec the row vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public VectorN.Row<SIQuantity> multiply(final VectorN.Row<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 1, otherVec.cols());
        return VectorN.Row.of(resultData, getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    @Override
    public Matrix1x1<SIQuantity> multiplyEntries(final Quantity<?> quantity)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity>(ArrayMath.scaleBy(si(), quantity.si()), siUnit);
    }

    /**
     * Return the matrix 'as' a matrix with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target matrix class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     */
    public <TQ extends Quantity<TQ>> Matrix1x1<TQ> as(final UnitInterface<?, TQ> targetUnit)
            throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "Matrix1x1.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new Matrix1x1<TQ>(si(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
    }

}
