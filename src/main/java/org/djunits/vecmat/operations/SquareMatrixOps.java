package org.djunits.vecmat.operations;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.Math2;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.Matrix;
import org.djunits.vecmat.NonInvertibleMatrixException;

/**
 * SquareMatrixOps defines a number of operations that can be applied to square matrixes, such as transpose, invert, and
 * determinant.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the square matrix type
 */
public interface SquareMatrixOps<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, M extends SquareMatrixOps<Q, U, M>>
        extends VectorMatrixOps<Q, U, M>
{
    /**
     * Return the order (the number of rows/columns) of this matrix.
     * @return the order (the number of rows/columns) of this matrix
     */
    default int order()
    {
        return rows();
    }

    /**
     * Return the transposed square matrix. A transposed matrix has the same unit as the original one.
     * @return the transposed square matrix
     */
    @SuppressWarnings("checkstyle:needbraces")
    default M transpose()
    {
        double[] data = si();
        double[] newSi = new double[data.length];
        int n = order();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                newSi[n * i + j] = data[n * j + i];
        return instantiate(newSi);
    }

    /**
     * Return the determinant of the square matrix as a scalar in SI or BASE units.
     * @return the determinant of the square matrix as a scalar in SI or BASE units
     */
    default double determinantScalar()
    {
        return MatrixMath.determinant(si(), order());
    }

    /**
     * Return the determinant of the square matrix as quantity with unit U^n where n is the order of the matrix.
     * @return the determinant of the square matrix as a quantity
     */
    default SIQuantity determinant()
    {
        SIUnit siu = getDisplayUnit().siUnit();
        int[] newDim = new int[SIUnit.NUMBER_DIMENSIONS];
        for (int i = 0; i < SIUnit.NUMBER_DIMENSIONS; i++)
        {
            newDim[i] = order() * (int) siu.siDimensions()[i];
        }
        SIUnit detSIUnit = new SIUnit(newDim);
        return new SIQuantity(determinantScalar(), detSIUnit);
    }

    /**
     * Return the inverse of the square matrix, if the matrix is non-singular. The unit of the matrix is U^(-1).
     * @return the inverse of the square matrix, if the matrix is non-singular
     * @throws NonInvertibleMatrixException when the matrix is singular or cannot be inverted
     */
    Matrix<SIQuantity, SIUnit, ?> inverse() throws NonInvertibleMatrixException;

    /**
     * Return the adjugate (classical adjoint) matrix for this matrix, often denoted as adj(M). The unit of adj(M) is U^(n-1)
     * where n is the order of the matrix.
     * @return the adjugate (classical adjoint) matrix
     */
    Matrix<SIQuantity, SIUnit, ?> adjugate();

    /**
     * Return the trace of the matrix (the sum of the diagonal elements). It results in a quantity with the same unit as the
     * original matrix.
     * @return the trace of this matrix
     */
    default Q trace()
    {
        return getDisplayUnit().ofSi(MatrixMath.trace(si(), order()));
    }

    /**
     * Return the Frobenius norm of the matrix, which is equal to sqrt(trace(A*.A)). It results in a quantity with the same unit
     * as the original matrix. See <a href=
     * "https://en.wikipedia.org/wiki/Matrix_norm#Frobenius_norm">https://en.wikipedia.org/wiki/Matrix_norm#Frobenius_norm</a>
     * for more information.
     * @return the Frobenius norm of this matrix
     */
    default Q normFrobenius()
    {
        return getDisplayUnit().ofSi(Math.sqrt(Math2.sumSqr(si()))).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return whether the matrix is symmetric. Use a default tolerance of 1.0E-12 times the largest absolute si quantity.
     * @return whether the matrix is symmetric
     */
    default boolean isSymmetric()
    {
        return MatrixMath.isSymmetric(si(), order());
    }

    /**
     * Return whether the matrix is symmetric, up to a tolerance.
     * @param tolerance the tolerance, expressed as a quantity
     * @return whether the matrix is symmetric
     */
    default boolean isSymmetric(final Q tolerance)
    {
        return MatrixMath.isSymmetric(si(), order(), tolerance.si());
    }

    /**
     * Return whether the matrix is skew symmetric. Use a default tolerance of 1.0E-12 times the largest absolute si quantity.
     * @return whether the matrix is skew symmetric
     */
    default boolean isSkewSymmetric()
    {
        return MatrixMath.isSkewSymmetric(si(), order());
    }

    /**
     * Return whether the matrix is skew symmetric, up to a tolerance.
     * @param tolerance the tolerance, expressed as a quantity
     * @return whether the matrix is skew symmetric
     */
    default boolean isSkewSymmetric(final Q tolerance)
    {
        return MatrixMath.isSkewSymmetric(si(), order(), tolerance.si());
    }

}
