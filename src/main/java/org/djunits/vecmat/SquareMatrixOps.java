package org.djunits.vecmat;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;

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
{
    /**
     * Return the order (the number of rows/columns) of this matrix.
     * @return the order (the number of rows/columns) of this matrix
     */
    int order();

    /**
     * Return the transposed square matrix. A transposed matrix has the same unit as the original one.
     * @return the transposed square matrix
     */
    M transpose();

    /**
     * Return the determinant of the square matrix as a scalar in SI or BASE units.
     * @return the determinant of the square matrix as a scalar in SI or BASE units
     */
    default double determinantScalar()
    {
        return determinant().si();
    }

    /**
     * Return the determinant of the square matrix as quantity with unit U^n where n is the order of the matrix.
     * @return the determinant of the square matrix as a quantity
     * @param <QN> the quantity type of the result
     */
    <QN extends Quantity<QN, ?>> QN determinant();

    /**
     * Return the inverse of the square matrix, if the matrix is non-singular.
     * @return the inverse of the square matrix, if the matrix is non-singular
     * @throws NonInvertibleMatrixException when the matrix is singular
     */
    M inverse() throws NonInvertibleMatrixException;

    /**
     * Return the trace of the matrix (the sum of the diagonal elements). It results in a quantity with the same unit as the
     * original matrix.
     * @return the trace of this matrix
     */
    Q trace();

    /**
     * Return the Frobenius norm of the matrix, which is equal to sqrt(trace(A*.A)). It results in a quantity with the same unit
     * as the original matrix. See <a href=
     * "https://en.wikipedia.org/wiki/Matrix_norm#Frobenius_norm">https://en.wikipedia.org/wiki/Matrix_norm#Frobenius_norm</a>
     * for more information.
     * @return the Frobenius norm of this matrix
     */
    Q normFrobenius();

    /**
     * Return the adjugate (classical adjoint) matrix for this matrix, often denoted as adj(M).
     * @return the adjugate (classical adjoint) matrix
     */
    M adjugate();

    /**
     * Return whether the matrix is symmetric. Use a default tolerance of 1.0E-12 times the largest absolute si quantity.
     * @return whether the matrix is symmetric
     */
    boolean isSymmetric();

    /**
     * Return whether the matrix is symmetric, up to a tolerance.
     * @param tolerance the tolerance, expressed as a quantity
     * @return whether the matrix is symmetric
     */
    boolean isSymmetric(Q tolerance);

    /**
     * Return whether the matrix is skew symmetric. Use a default tolerance of 1.0E-12 times the largest absolute si quantity.
     * @return whether the matrix is skew symmetric
     */
    boolean isSkewSymmetric();

    /**
     * Return whether the matrix is skew symmetric, up to a tolerance.
     * @param tolerance the tolerance, expressed as a quantity
     * @return whether the matrix is skew symmetric
     */
    boolean isSkewSymmetric(Q tolerance);
}
