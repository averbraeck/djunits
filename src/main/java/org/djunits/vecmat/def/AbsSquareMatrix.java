package org.djunits.vecmat.def;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;

/**
 * AbsSquareMatrix defines a number of operations that can be applied to square matrixes with absolute quantity values.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the quantity type
 * @param <MA> the absolute matrix type
 * @param <MQ> the relative matrix type
 */
public abstract class AbsSquareMatrix<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>,
        MA extends AbsSquareMatrix<A, Q, MA, MQ>, MQ extends SquareMatrix<Q, MQ, ?, ?>> extends AbsMatrix<A, Q, MA, MQ, MA>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new square matrix of absolute values with a reference point.
     * @param matrix the underlying relative square matrix with SI values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsSquareMatrix(final MQ matrix, final Reference<?, A, Q> reference)
    {
        super(matrix, reference);
    }

    /**
     * Return the order (the number of rows/columns) of this matrix.
     * @return the order (the number of rows/columns) of this matrix
     */
    public int order()
    {
        return rows();
    }

    @Override
    public MA transpose()
    {
        return instantiateSi(getRelativeVecMat().transpose().unsafeSiArray(), getReference()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Retrieve the main diagonal of the matrix as a column vector.
     * @return the main diagonal as a Vector
     */
    public abstract AbsVector<A, Q, ?, ?, ?> getDiagonalVector();

    /**
     * Retrieve the main diagonal of the matrix as an array of scalars.
     * @return the main diagonal as a Scalar array
     */
    @SuppressWarnings("unchecked")
    public A[] getDiagonalScalars()
    {
        List<A> result = new ArrayList<>();
        for (int i = 0; i < rows(); i++)
        {
            result.add(get(i, i));
        }
        A sample = result.get(0);
        A[] array = (A[]) Array.newInstance(sample.getClass(), result.size());
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
     * Return whether the matrix is symmetric. Use a public tolerance of 1.0E-12 times the largest absolute si quantity.
     * @return whether the matrix is symmetric
     */
    public boolean isSymmetric()
    {
        return getRelativeVecMat().isSymmetric();
    }

    /**
     * Return whether the matrix is symmetric, up to a tolerance.
     * @param tolerance the tolerance, expressed as a quantity
     * @return whether the matrix is symmetric
     */
    public boolean isSymmetric(final Q tolerance)
    {
        return getRelativeVecMat().isSymmetric(tolerance);
    }

    /**
     * Return whether the matrix is skew symmetric. Use a public tolerance of 1.0E-12 times the largest absolute si quantity.
     * @return whether the matrix is skew symmetric
     */
    public boolean isSkewSymmetric()
    {
        return getRelativeVecMat().isSkewSymmetric();
    }

    /**
     * Return whether the matrix is skew symmetric, up to a tolerance.
     * @param tolerance the tolerance, expressed as a quantity
     * @return whether the matrix is skew symmetric
     */
    public boolean isSkewSymmetric(final Q tolerance)
    {
        return getRelativeVecMat().isSkewSymmetric(tolerance);
    }

}
