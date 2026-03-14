package org.djunits.vecmat.def;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.Math2;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.NonInvertibleMatrixException;

/**
 * SquareMatrix defines a number of operations that can be applied to square matrixes, such as transpose, invert, and
 * determinant.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the 'SELF' square matrix type
 * @param <SI> the square matrix type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic square matrix type with generics &lt;?, ?&lt; for Hadamard operations
 */
public abstract class SquareMatrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>,
        M extends SquareMatrix<Q, U, M, SI, H>, SI extends SquareMatrix<SIQuantity, SIUnit, SI, ?, ?>,
        H extends SquareMatrix<?, ?, ?, ?, ?>> extends Matrix<Q, U, M, SI, H>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new square matrix with a unit.
     * @param displayUnit the display unit to use
     */
    public SquareMatrix(final U displayUnit)
    {
        super(displayUnit);
    }

    /**
     * Return the order (the number of rows/columns) of this matrix.
     * @return the order (the number of rows/columns) of this matrix
     */
    public int order()
    {
        return rows();
    }

    /**
     * Return the transposed square matrix. A transposed matrix has the same unit as the original one.
     * @return the transposed square matrix
     */
    @SuppressWarnings("checkstyle:needbraces")
    public M transpose()
    {
        double[] data = si();
        double[] newSi = new double[data.length];
        int n = order();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                newSi[n * i + j] = data[n * j + i];
        return instantiateSi(newSi);
    }

    /**
     * Return the determinant of the square matrix as a scalar in SI or BASE units.
     * @return the determinant of the square matrix as a scalar in SI or BASE units
     */
    public double determinantScalar()
    {
        return MatrixMath.determinant(si(), order());
    }

    /**
     * Return the determinant of the square matrix as quantity with unit U^n where n is the order of the matrix.
     * @return the determinant of the square matrix as a quantity
     */
    public SIQuantity determinant()
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
     * Retrieve the main diagonal of the matrix as a column vector.
     * @return the main diagonal as a Vector
     */
    public abstract Vector<Q, U, ?, ?, ?> getDiagonalVector();

    /**
     * Retrieve the main diagonal of the matrix as an array of scalars.
     * @return the main diagonal as a Scalar array
     */
    @SuppressWarnings("unchecked")
    public Q[] getDiagonalScalars()
    {
        List<Q> result = new ArrayList<>();
        for (int i = 0; i < rows(); i++)
        {
            result.add(get(i, i));
        }

        Q sample = result.get(0);
        Q[] array = (Q[]) Array.newInstance(sample.getClass(), result.size());
        return result.toArray(array);
    }

    /**
     * Retrieve the main diagonal of the matrix as an array of doubles with SI-values.
     * @return the main diagonal as a doube array with SI-values
     */
    public double[] getDiagonalSi()
    {
        double[] result = new double[order()];
        for (int i = 0; i < rows(); i++)
        {
            result[i] = si(i, i);
        }
        return result;
    }

    /**
     * Return the inverse of the square matrix, if the matrix is non-singular. The unit of the matrix is U^(-1).
     * @return the inverse of the square matrix, if the matrix is non-singular
     * @throws NonInvertibleMatrixException when the matrix is singular or cannot be inverted
     */
    public abstract SI inverse() throws NonInvertibleMatrixException;

    /**
     * Return the adjugate (classical adjoint) matrix for this matrix, often denoted as adj(M). The unit of adj(M) is U^(n-1)
     * where n is the order of the matrix.
     * @return the adjugate (classical adjoint) matrix
     */
    public abstract SI adjugate();

    /**
     * Return the trace of the matrix (the sum of the diagonal elements). It results in a quantity with the same unit as the
     * original matrix.
     * @return the trace of this matrix
     */
    public Q trace()
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
    public Q normFrobenius()
    {
        return getDisplayUnit().ofSi(Math.sqrt(Math2.sumSqr(si()))).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return whether the matrix is symmetric. Use a public tolerance of 1.0E-12 times the largest absolute si quantity.
     * @return whether the matrix is symmetric
     */
    public boolean isSymmetric()
    {
        return MatrixMath.isSymmetric(si(), order());
    }

    /**
     * Return whether the matrix is symmetric, up to a tolerance.
     * @param tolerance the tolerance, expressed as a quantity
     * @return whether the matrix is symmetric
     */
    public boolean isSymmetric(final Q tolerance)
    {
        return MatrixMath.isSymmetric(si(), order(), tolerance.si());
    }

    /**
     * Return whether the matrix is skew symmetric. Use a public tolerance of 1.0E-12 times the largest absolute si quantity.
     * @return whether the matrix is skew symmetric
     */
    public boolean isSkewSymmetric()
    {
        return MatrixMath.isSkewSymmetric(si(), order());
    }

    /**
     * Return whether the matrix is skew symmetric, up to a tolerance.
     * @param tolerance the tolerance, expressed as a quantity
     * @return whether the matrix is skew symmetric
     */
    public boolean isSkewSymmetric(final Q tolerance)
    {
        return MatrixMath.isSkewSymmetric(si(), order(), tolerance.si());
    }

}
