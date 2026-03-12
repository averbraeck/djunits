package org.djunits.vecmat.d1;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.def.SquareDenseMatrix;
import org.djutils.exceptions.Throw;

/**
 * Matrix2x2 implements a matrix with 2x2 real-valued entries. The matrix is immutable, except for the display unit, which can
 * be changed.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public class Matrix1x1<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>>
        extends SquareDenseMatrix<Q, U, Matrix1x1<Q, U>, Matrix1x1<SIQuantity, SIUnit>, Matrix1x1<?, ?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Matrix2x2 with a unit.
     * @param arrayInUnit the matrix values {a11, a12, a21, a22} expressed in the displayUnit
     * @param displayUnit the display unit to use
     */
    protected Matrix1x1(final double[] arrayInUnit, final U displayUnit)
    {
        super(arrayInUnit, displayUnit, 2);
    }

    /**
     * Create a new Matrix2x2 with a unit, based on a 1-dimensional array.
     * <p>
     * <strong>Implementation Note:</strong> the condition is also checked by super() but the fail fast approach is used here
     * @param arrayInUnit the matrix values {a11, a12, a21, a22} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new Matrix2x2 with a unit
     * @throws IllegalArgumentException when valueArray does not contain 2x2 = 4 values
     */
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Matrix1x1<Q, U> of(final double[] arrayInUnit,
            final U displayUnit)
    {
        Throw.whenNull(arrayInUnit, "arrayInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(arrayInUnit.length != 4, IllegalArgumentException.class, "Length of array != 4 but %d", arrayInUnit.length);
        return new Matrix1x1<Q, U>(arrayInUnit, displayUnit);
    }

    /**
     * Create a new Matrix2x2 with a unit, based on a 2-dimensional grid.
     * @param gridInUnit the matrix values {{a11, a12}, {a21, a22}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new Matrix2x2 with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Matrix1x1<Q, U> of(final double[][] gridInUnit,
            final U displayUnit)
    {
        Throw.whenNull(gridInUnit, "gridInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(gridInUnit.length != 2 || gridInUnit[0].length != 2 || gridInUnit[1].length != 2,
                IllegalArgumentException.class, "gridInUnit is not a 2x2 array");
        return new Matrix1x1<Q, U>(new double[] {gridInUnit[0][0], gridInUnit[0][1], gridInUnit[1][0], gridInUnit[1][1]},
                displayUnit);
    }

    @Override
    public Matrix1x1<Q, U> instantiateSi(final double[] siNew)
    {
        return new Matrix1x1<Q, U>(siNew, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix1x1<SIQuantity, SIUnit> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new Matrix1x1<SIQuantity, SIUnit>(siNew, siUnit);
    }

    @Override
    public Vector1<Q, U> getRowVector(final int row)
    {
        return new Vector1<>(si(1, 1), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q, U> getColumnVector(final int col)
    {
        return new Vector1<>(si(1, 1), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix1x1<SIQuantity, SIUnit> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), 1);
        return new Matrix1x1<SIQuantity, SIUnit>(invData, getDisplayUnit().siUnit().invert());
    }

    @Override
    public Matrix1x1<SIQuantity, SIUnit> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), 1);
        return new Matrix1x1<SIQuantity, SIUnit>(invData, getDisplayUnit().siUnit().pow(order() - 1));
    }

    @Override
    public Matrix1x1<SIQuantity, SIUnit> invertElements()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new Matrix1x1<SIQuantity, SIUnit>(ArrayMath.reciprocal(si()), siUnit);
    }

    @Override
    public Matrix1x1<SIQuantity, SIUnit> multiplyElements(final Matrix1x1<?, ?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity, SIUnit>(ArrayMath.multiply(si(), other.si()), siUnit);
    }

    @Override
    public Matrix1x1<SIQuantity, SIUnit> divideElements(final Matrix1x1<?, ?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity, SIUnit>(ArrayMath.divide(si(), other.si()), siUnit);
    }

    // ------------------------------ MATRIX MULTIPLICATION AND AS() --------------------------

    /**
     * Multiply this matrix with another matrix using matrix multiplication and return the result.
     * @param otherMat the matrix to multiply with.
     * @return the matrix from the multiplication with the correct unit
     */
    public Matrix1x1<SIQuantity, SIUnit> multiply(final Matrix1x1<?, ?> otherMat)
    {
        double[] resultData = MatrixMath.multiply(si(), otherMat.si(), 2, 2, 2);
        return new Matrix1x1<SIQuantity, SIUnit>(resultData,
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a column vector, resulting in a column vector.
     * @param otherVec the column vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector2.Col<SIQuantity, SIUnit> multiply(final Vector2.Col<?, ?> otherVec)
    {
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 2, 2, 1);
        return new Vector2.Col<SIQuantity, SIUnit>(resultData[0], resultData[1],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    @Override
    public Matrix1x1<SIQuantity, SIUnit> multiplyElements(final Quantity<?, ?> quantity)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity, SIUnit>(ArrayMath.scaleBy(si(), quantity.si()), siUnit);
    }

    /**
     * Return the matrix 'as' a matrix with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target matrix class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Matrix1x1<TQ, TU> as(final TU targetUnit)
            throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "Matrix2x2.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new Matrix1x1<TQ, TU>(si(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
    }

}
