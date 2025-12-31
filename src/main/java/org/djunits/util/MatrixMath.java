package org.djunits.util;

import org.djunits.vecmat.NonInvertibleMatrixException;

/**
 * MatrixMath implements a number of methods for linear algebra operations on square matrices, such as LU decomposition,
 * inverse, trace, etc.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@SuppressWarnings({"checkstyle:needbraces", "checkstyle:localvariablename"})
public final class MatrixMath
{
    /** The default tolerance for operations when no tolerance is given. */
    protected static final double DEFAULT_TOL = 1e-12;

    /** */
    private MatrixMath()
    {
    }

    // ---------- Helpers ----------

    /**
     * Return the index in a row-major storage of the matrix: [a11, a12, ..., a21, a22, ...].
     * @param n the order of the square matrix
     * @param r the row to look up (0-based)
     * @param c the column to lok up (0-based)
     * @return the index in the array for row r, column c
     */
    private static int idx(final int n, final int r, final int c)
    {
        return r * n + c;
    }

    // ---------- Basic invariants ----------

    /**
     * Calculate the trace of the matrix.
     * @param aSi the row-major storage of the matrix
     * @param n the order of the matrix
     * @return the trace of the matrix
     */
    public static double trace(final double[] aSi, final int n)
    {
        double t = 0.0;
        for (int i = 0; i < n; i++)
            t += aSi[idx(n, i, i)];
        return t;
    }

    /**
     * Return whether the matrix is symmetric, using a default tolerance.
     * @param aSi the row-major storage of the matrix
     * @param n the order of the matrix
     * @return whether the matrix is symmetric
     */
    public static boolean isSymmetric(final double[] aSi, final int n)
    {
        return isSymmetric(aSi, n, DEFAULT_TOL);
    }

    /**
     * Return whether the matrix is symmetric, within the given tolerance.
     * @param aSi the row-major storage of the matrix
     * @param n the order of the matrix
     * @param tol the tolerance in SI units
     * @return whether the matrix is symmetric
     */
    public static boolean isSymmetric(final double[] aSi, final int n, final double tol)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                double aij = aSi[idx(n, i, j)];
                double aji = aSi[idx(n, j, i)];
                if (Math.abs(aij - aji) > tol)
                    return false;
            }
        }
        return true;
    }

    /**
     * Return whether the matrix is skew-symmetric, using a default tolerance.
     * @param aSi the row-major storage of the matrix
     * @param n the order of the matrix
     * @return whether the matrix is symmetric
     */
    public static boolean isSkewSymmetric(final double[] aSi, final int n)
    {
        return isSkewSymmetric(aSi, n, DEFAULT_TOL);
    }

    /**
     * Return whether the matrix is skew-symmetric, within the given tolerance.
     * @param aSi the row-major storage of the matrix
     * @param n the order of the matrix
     * @param tol the tolerance in SI units
     * @return whether the matrix is symmetric
     */
    public static boolean isSkewSymmetric(final double[] aSi, final int n, final double tol)
    {
        for (int i = 0; i < n; i++)
        {
            if (Math.abs(aSi[idx(n, i, i)]) > tol)
                return false; // diagonal must be ~0
            for (int j = i + 1; j < n; j++)
            {
                double aij = aSi[idx(n, i, j)];
                double aji = aSi[idx(n, j, i)];
                if (Math.abs(aij + aji) > tol)
                    return false; // a_ij = -a_ji
            }
        }
        return true;
    }

    // ---------- LU decomposition with partial pivoting ----------

    /**
     * Helper class for LU decomposition with partial pivoting.
     */
    private static final class LU
    {
        /** combined L (unit diag) and U, row-major. */
        private final double[] lu;

        /** row permutations. */
        private final int[] piv;

        /** the pivot sign, +1 or -1. */
        private final int pivotSign;

        /** scale for tolerance decisions. */
        private final double scale;

        /**
         * Construct an LU instance.
         * @param lu combined L (unit diag) and U, row-major
         * @param piv row permutations
         * @param pivotSign the pivot sign, +1 or -1
         * @param scale scale for tolerance decisions
         */
        LU(final double[] lu, final int[] piv, final int pivotSign, final double scale)
        {
            this.lu = lu;
            this.piv = piv;
            this.pivotSign = pivotSign;
            this.scale = scale;
        }
    }

    /**
     * Decompose.
     * @param a the row-major storage of the matrix
     * @param n the order of the square matrix
     * @return an LU object containing L and U in one array
     */
    private static LU luDecompose(final double[] a, final int n)
    {
        double[] lu = a.clone();
        int[] piv = new int[n];
        for (int i = 0; i < n; i++)
            piv[i] = i;
        int pivotSign = 1;
        double scale = Math2.maxAbs(a);

        for (int k = 0; k < n; k++)
        {
            // Find pivot
            int p = k;
            double max = Math.abs(lu[idx(n, k, k)]);
            for (int i = k + 1; i < n; i++)
            {
                double v = Math.abs(lu[idx(n, i, k)]);
                if (v > max)
                {
                    max = v;
                    p = i;
                }
            }
            // Swap rows if needed
            if (p != k)
            {
                for (int j = 0; j < n; j++)
                {
                    double tmp = lu[idx(n, k, j)];
                    lu[idx(n, k, j)] = lu[idx(n, p, j)];
                    lu[idx(n, p, j)] = tmp;
                }
                int tmpi = piv[k];
                piv[k] = piv[p];
                piv[p] = tmpi;
                pivotSign = -pivotSign;
            }

            double pivot = lu[idx(n, k, k)];
            if (pivot != 0.0)
            {
                // Compute multipliers
                for (int i = k + 1; i < n; i++)
                {
                    lu[idx(n, i, k)] /= pivot;
                }
                // Rank-1 update to the trailing submatrix
                for (int i = k + 1; i < n; i++)
                {
                    double lik = lu[idx(n, i, k)];
                    if (lik == 0.0)
                        continue;
                    for (int j = k + 1; j < n; j++)
                    {
                        lu[idx(n, i, j)] -= lik * lu[idx(n, k, j)];
                    }
                }
            }
            // If pivot == 0, we still continue; this indicates singular/deficient rank.
        }
        return new LU(lu, piv, pivotSign, scale);
    }

    /**
     * Determine whether the matrix is singular, based on the LU decomposition.
     * @param luRes The LU result
     * @param n the order of the matrix
     * @param relTol the relative tolerance
     * @return whether the matrix is singular
     */
    private static boolean isSingularFromLU(final LU luRes, final int n, final double relTol)
    {
        double tol = Math.max(1.0, luRes.scale) * relTol;
        for (int i = 0; i < n; i++)
        {
            if (Math.abs(luRes.lu[idx(n, i, i)]) <= tol)
                return true;
        }
        return false;
    }

    /**
     * Return the determinant, based on the LU decomposition.
     * @param luRes The LU result
     * @param n the order of the matrix
     * @return the determinant of the matrix
     */
    private static double detFromLU(final LU luRes, final int n)
    {
        double det = luRes.pivotSign;
        for (int i = 0; i < n; i++)
        {
            det *= luRes.lu[idx(n, i, i)];
        }
        return det;
    }

    /**
     * Solve LU x = b for one right-hand side vector b (vector solve).
     * @param luRes The LU result
     * @param n the order of the matrix
     * @param b the right-hand side
     */
    private static void luSolveInPlace(final LU luRes, final int n, final double[] b)
    {
        // Apply row permutations to b
        double[] bp = b.clone();
        for (int i = 0; i < n; i++)
        {
            b[i] = bp[luRes.piv[i]];
        }
        // Forward substitution: solve L y = Pb
        for (int i = 0; i < n; i++)
        {
            double sum = b[i];
            for (int j = 0; j < i; j++)
            {
                sum -= luRes.lu[idx(n, i, j)] * b[j];
            }
            b[i] = sum; // L has unit diagonal
        }
        // Back substitution: solve U x = y
        for (int i = n - 1; i >= 0; i--)
        {
            double sum = b[i];
            for (int j = i + 1; j < n; j++)
            {
                sum -= luRes.lu[idx(n, i, j)] * b[j];
            }
            b[i] = sum / luRes.lu[idx(n, i, i)];
        }
    }

    // ---------- Determinant ----------

    /**
     * Calculate the determinant, based on the role of Sarrus. See
     * <a href="https://en.wikipedia.org/wiki/Rule_of_Sarrus">https://en.wikipedia.org/wiki/Rule_of_Sarrus</a>.
     * @param aSi the row-major storage of the matrix
     * @param n the order of the matrix
     * @return the determinant
     */
    public static double determinant(final double[] aSi, final int n)
    {
        if (n == 1)
            return aSi[0];
        if (n == 2)
        {
            return aSi[0] * aSi[3] - aSi[1] * aSi[2];
        }
        if (n == 3)
        {
            // Sarrus
            double a = aSi[0], b = aSi[1], c = aSi[2];
            double d = aSi[3], e = aSi[4], f = aSi[5];
            double g = aSi[6], h = aSi[7], i = aSi[8];
            return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
        }
        LU luRes = luDecompose(aSi, n);
        return detFromLU(luRes, n);
    }

    // ---------- Inverse ----------

    /**
     * Calculate the inverse. Fast methods for n=1, 2, 3. For higher order matrices, the calculation is based on the LU
     * decomposition.
     * @param aSi the row-major storage of the matrix
     * @param n the order of the matrix
     * @return the inverse of the matrix
     * @throws NonInvertibleMatrixException when the matrix cannot be inverted
     */
    public static double[] inverse(final double[] aSi, final int n) throws NonInvertibleMatrixException
    {
        if (n == 1)
        {
            double v = aSi[0];
            if (v == 0.0)
                throw new NonInvertibleMatrixException("Singular 1x1 matrix");
            return new double[] {1.0 / v};
        }
        if (n == 2)
        {
            double a = aSi[0], b = aSi[1], c = aSi[2], d = aSi[3];
            double det = a * d - b * c;
            if (Math.abs(det) <= DEFAULT_TOL * Math.max(1.0, Math2.maxAbs(aSi)))
            {
                throw new NonInvertibleMatrixException("Singular 2x2 matrix");
            }
            double invDet = 1.0 / det;
            double[] inv = new double[] {d * invDet, -b * invDet, -c * invDet, a * invDet};
            return inv;
        }
        if (n == 3)
        {
            // Use adj(A)^T / det(A)
            double a = aSi[0], b = aSi[1], c = aSi[2];
            double d = aSi[3], e = aSi[4], f = aSi[5];
            double g = aSi[6], h = aSi[7], i = aSi[8];

            double A = (e * i - f * h);
            double B = -(d * i - f * g);
            double C = (d * h - e * g);
            double D = -(b * i - c * h);
            double E = (a * i - c * g);
            double F = -(a * h - b * g);
            double G = (b * f - c * e);
            double H = -(a * f - c * d);
            double I = (a * e - b * d);

            double det = a * A + b * B + c * C;
            if (Math.abs(det) <= DEFAULT_TOL * Math.max(1.0, Math2.maxAbs(aSi)))
            {
                throw new NonInvertibleMatrixException("Singular 3x3 matrix");
            }
            double invDet = 1.0 / det;
            // inverse = adj(A)^T / det = cof(A) / det (since we computed cofactors already in place)
            double[] inv = new double[] {A * invDet, D * invDet, G * invDet, B * invDet, E * invDet, H * invDet, C * invDet,
                    F * invDet, I * invDet};
            return inv;
        }

        // General n: LU + solve for identity
        LU luRes = luDecompose(aSi, n);
        if (isSingularFromLU(luRes, n, DEFAULT_TOL))
        {
            throw new NonInvertibleMatrixException("Matrix is singular to working precision");
        }
        double[] inv = new double[n * n];
        double[] e = new double[n]; // RHS basis vector
        for (int col = 0; col < n; col++)
        {
            // e = unit vector
            java.util.Arrays.fill(e, 0.0);
            e[col] = 1.0;
            double[] x = e.clone();
            luSolveInPlace(luRes, n, x);
            // write column to inv (row-major target)
            for (int row = 0; row < n; row++)
            {
                inv[idx(n, row, col)] = x[row];
            }
        }
        return inv;
    }

    // ---------- Adjugate (cofactor transpose) ----------

    /**
     * Calculate the adjugate. Fast methods for n=1, 2, 3.
     * @param aSi the row-major storage of the matrix
     * @param n the order of the matrix
     * @return the adjugate of the matrix
     */
    public static double[] adjugate(final double[] aSi, final int n)
    {
        if (n == 1)
        {
            return new double[] {1.0};
        }
        if (n == 2)
        {
            // adj([a b; c d]) = [ d -b; -c a ]
            double a = aSi[0], b = aSi[1], c = aSi[2], d = aSi[3];
            double[] adj = new double[] {d, -b, -c, a};
            return adj;
        }
        if (n == 3)
        {
            double a = aSi[0], b = aSi[1], c = aSi[2];
            double d = aSi[3], e = aSi[4], f = aSi[5];
            double g = aSi[6], h = aSi[7], i = aSi[8];
            // Cofactor matrix (not transposed yet)
            double C00 = (e * i - f * h);
            double C01 = -(d * i - f * g);
            double C02 = (d * h - e * g);
            double C10 = -(b * i - c * h);
            double C11 = (a * i - c * g);
            double C12 = -(a * h - b * g);
            double C20 = (b * f - c * e);
            double C21 = -(a * f - c * d);
            double C22 = (a * e - b * d);
            // Adjugate = Cofactor^T
            double[] adj = new double[] {C00, C10, C20, C01, C11, C21, C02, C12, C22};
            return adj;
        }

        // General n: build cofactor matrix via minors, then transpose
        int m = n - 1;
        double[] cof = new double[n * n];
        double[] minor = new double[m * m];

        for (int r = 0; r < n; r++)
        {
            for (int c = 0; c < n; c++)
            {
                // Build minor excluding row r and col c
                int p = 0;
                for (int i = 0; i < n; i++)
                {
                    if (i == r)
                        continue;
                    for (int j = 0; j < n; j++)
                    {
                        if (j == c)
                            continue;
                        minor[p++] = aSi[idx(n, i, j)];
                    }
                }
                double detMinor;
                if (m == 1)
                {
                    detMinor = minor[0];
                }
                else if (m == 2)
                {
                    detMinor = minor[0] * minor[3] - minor[1] * minor[2];
                }
                else if (m == 3)
                {
                    double A = minor[0], B = minor[1], C = minor[2];
                    double D = minor[3], E = minor[4], F = minor[5];
                    double G = minor[6], H = minor[7], I = minor[8];
                    detMinor = A * (E * I - F * H) - B * (D * I - F * G) + C * (D * H - E * G);
                }
                else
                {
                    // Use LU for larger minors
                    LU luMinor = luDecompose(minor, m);
                    detMinor = detFromLU(luMinor, m);
                }
                double sign = ((r + c) & 1) == 0 ? 1.0 : -1.0;
                // Store cofactor (not yet transposed)
                cof[idx(n, r, c)] = sign * detMinor;
            }
        }
        // Adjugate = cof^T
        double[] adj = new double[n * n];
        for (int r = 0; r < n; r++)
        {
            for (int c = 0; c < n; c++)
            {
                adj[idx(n, r, c)] = cof[idx(n, c, r)];
            }
        }
        return adj;
    }

}
