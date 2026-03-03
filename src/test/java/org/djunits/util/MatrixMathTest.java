package org.djunits.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;

import org.djunits.vecmat.NonInvertibleMatrixException;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MatrixMath}. The suite aims for 100% line and branch coverage by exercising:
 * <ul>
 * <li>Multiplication (rectangular), including length-mismatch error paths</li>
 * <li>Trace, symmetry and skew-symmetry with default and explicit tolerances</li>
 * <li>Determinant: closed-form (n=1,2,3) and LU-based (n≥4)</li>
 * <li>Inverse: closed-form (n=1,2,3) and LU-based (n≥4), including singular matrix exceptions</li>
 * <li>Adjugate: closed-form (n=1,2,3) and general (n≥4), validated via A·adj(A)=det(A)·I</li>
 * <li>Coverage of the private constructor</li>
 * </ul>
 * <p>
 * <strong>Notes:</strong>
 * <ul>
 * <li>All matrices are provided in row-major layout, matching {@link MatrixMath} contracts.</li>
 * <li>Floating-point comparisons use a tight tolerance; identities from inversion are validated numerically.</li>
 * </ul>
 * </p>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@SuppressWarnings("checkstyle:localvariablename")
public class MatrixMathTest
{
    /** Absolute comparison tolerance for floating-point assertions. */
    private static final double EPS = 1e-12;

    // ---------------------------------------------------------------------
    // Utilities used by the tests
    // ---------------------------------------------------------------------

    /**
     * Row-major index helper (same convention as {@link MatrixMath}).
     * @param n order (columns = n for square; used as stride)
     * @param r zero-based row
     * @param c zero-based column
     * @return row-major index
     */
    private static int idx(final int n, final int r, final int c)
    {
        return r * n + c;
    }

    /**
     * Asserts that the given row-major matrix is (approximately) the identity matrix.
     * @param m the matrix (row-major), must be n*n in length
     * @param n the order
     */
    private static void assertIsIdentity(final double[] m, final int n)
    {
        for (int r = 0; r < n; r++)
        {
            for (int c = 0; c < n; c++)
            {
                double expected = (r == c) ? 1.0 : 0.0;
                assertEquals(expected, m[idx(n, r, c)], EPS, "entry (" + r + "," + c + ") must match identity");
            }
        }
    }

    /**
     * Asserts two row-major matrices are element-wise equal within {@link #EPS}.
     * @param expected expected matrix
     * @param actual actual matrix
     */
    private static void assertMatrixClose(final double[] expected, final double[] actual)
    {
        assertEquals(expected.length, actual.length, "matrix sizes must match");
        for (int i = 0; i < expected.length; i++)
        {
            assertEquals(expected[i], actual[i], EPS, "matrix entry mismatch at index " + i);
        }
    }

    // ---------------------------------------------------------------------
    // Constructor coverage (private)
    // ---------------------------------------------------------------------

    /**
     * Covers the private constructor of {@link MatrixMath} via reflection for complete coverage.
     * @throws Exception if reflective construction fails
     */
    @Test
    void privateConstructorCoverage() throws Exception
    {
        Constructor<MatrixMath> ctor = MatrixMath.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        Object instance = ctor.newInstance();
        assertNotNull(instance, "reflective construction returned null");
    }

    // ---------------------------------------------------------------------
    // Multiplication
    // ---------------------------------------------------------------------

    /**
     * Verifies rectangular matrix multiplication and length-mismatch precondition errors.
     */
    @Test
    void multiplyRectangularAndErrors()
    {
        // A (2x2) · B (2x1) = C (2x1)
        double[] A = {1, 2, 3, 4};
        double[] B = {5, 6};
        double[] C = MatrixMath.multiply(A, B, /* m */2, /* n */2, /* p */1);
        assertMatrixClose(new double[] {17, 39}, C);

        // Another rectangular case: A (1x3) · B (3x2) = C (1x2)
        double[] A2 = {1, -1, 2};
        double[] B2 = {0, 2, 3, -1, 1, 4};
        double[] C2 = MatrixMath.multiply(A2, B2, 1, 3, 2);
        // row 0: [1,-1,2] * B2 => [1*0 + (-1)*3 + 2*1, 1*2 + (-1)*(-1) + 2*4] = [-1, 11]
        assertMatrixClose(new double[] {-1, 11}, C2);

        // Length-mismatch errors for A and B
        assertThrows(IllegalArgumentException.class, () -> MatrixMath.multiply(new double[] {1, 2, 3}, B, 2, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> MatrixMath.multiply(A, new double[] {5}, 2, 2, 1));
    }

    // ---------------------------------------------------------------------
    // Basic invariants: trace, symmetry
    // ---------------------------------------------------------------------

    /**
     * Verifies {@link MatrixMath#trace(double[], int)} on simple matrices.
     */
    @Test
    void traceOfMatrix()
    {
        // 3x3 with diag 1,2,3
        double[] M = {1, 9, 9, 9, 2, 9, 9, 9, 3};
        assertEquals(6.0, MatrixMath.trace(M, 3), EPS);

        // 1x1
        assertEquals(7.0, MatrixMath.trace(new double[] {7.0}, 1), EPS);
    }

    /**
     * Verifies symmetry checks with default and explicit tolerances.
     */
    @Test
    void symmetryChecks()
    {
        // Nearly symmetric with tiny asymmetry below default tol (1e-12)
        double eps = 1e-13;
        double[] S = {1, 2 + eps, 3, 2, 5, 6, 3, 6, 9};
        assertTrue(MatrixMath.isSymmetric(S, 3), "should be symmetric within default tol");

        // Explicit tolerance too tight -> not symmetric
        assertFalse(MatrixMath.isSymmetric(S, 3, 1e-15));

        // Skew-symmetric: off-diagonals are negatives, diagonal ~ 0
        double[] K = {0, 2, -3, -2, eps, 4, // eps ~ 0 within tol on diagonal
                3, -4, 0};
        assertTrue(MatrixMath.isSkewSymmetric(K, 3), "should be skew-symmetric within default tol");

        // Skew-symmetric with explicit very tight tol -> diagonal eps triggers failure
        assertFalse(MatrixMath.isSkewSymmetric(K, 3, 1e-15));

        // Non skew-symmetric: a_ij + a_ji != 0 beyond tol
        double[] NK = {0, 1, 0, -2, 0, 0, 0, 0, 0};
        assertFalse(MatrixMath.isSkewSymmetric(NK, 3));
    }

    // ---------------------------------------------------------------------
    // Determinant
    // ---------------------------------------------------------------------

    /**
     * Verifies determinant for n=1,2,3 (closed-form) and n≥4 (LU path).
     */
    @Test
    void determinantVariants()
    {
        // n = 1
        assertEquals(3.0, MatrixMath.determinant(new double[] {3.0}, 1), EPS);

        // n = 2: | 4 7; 2 6 | = 10
        double[] A2 = {4, 7, 2, 6};
        assertEquals(10.0, MatrixMath.determinant(A2, 2), EPS);

        // n = 3: example with det = 1
        double[] A3 = {1, 2, 3, 0, 1, 4, 5, 6, 0};
        assertEquals(1.0, MatrixMath.determinant(A3, 3), EPS);

        // n = 4 (LU path): diagonal matrix -> product of diagonal
        double[] A4 = {1, 0, 0, 0, 0, -2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4};
        assertEquals(-24.0, MatrixMath.determinant(A4, 4), EPS);
    }

    // ---------------------------------------------------------------------
    // Inverse
    // ---------------------------------------------------------------------

    /**
     * Verifies inverse for n=1,2,3 and n≥4, including singular matrix exceptions.
     * @throws Exception if inversion unexpectedly fails
     */
    @Test
    void inverseVariants() throws Exception
    {
        // n = 1 (invertible)
        double[] one = {2.0};
        assertMatrixClose(new double[] {0.5}, MatrixMath.inverse(one, 1));

        // n = 1 singular
        assertThrows(NonInvertibleMatrixException.class, () -> MatrixMath.inverse(new double[] {0.0}, 1));

        // n = 2 (invertible, check A * inv = I)
        double[] A2 = {4, 7, 2, 6};
        double[] invA2 = MatrixMath.inverse(A2, 2);
        double[] prod2 = MatrixMath.multiply(A2, invA2, 2, 2, 2);
        assertIsIdentity(prod2, 2);

        // n = 2 nearly singular -> triggers tolerance-based singular check
        double tiny = 1e-13; // <= DEFAULT_TOL * max(1, maxAbs) with DEFAULT_TOL = 1e-12
        double[] S2 = {1, 1, 1, 1 + tiny};
        assertThrows(NonInvertibleMatrixException.class, () -> MatrixMath.inverse(S2, 2));

        // n = 3 (invertible, check A * inv = I)
        double[] A3 = {1, 2, 3, 0, 1, 4, 5, 6, 0};
        double[] invA3 = MatrixMath.inverse(A3, 3);
        double[] prod3 = MatrixMath.multiply(A3, invA3, 3, 3, 3);
        assertIsIdentity(prod3, 3);

        // n = 3 singular (two identical rows)
        double[] S3 = {1, 2, 3, 1, 2, 3, 0, 1, 4};
        assertThrows(NonInvertibleMatrixException.class, () -> MatrixMath.inverse(S3, 3));

        // n = 4 (invertible, check A * inv = I) -- general LU path
        double[] A4 = {1, 2, 3, 4, 2, 5, 2, 1, 3, 2, 6, 2, 4, 1, 2, 5};
        double[] invA4 = MatrixMath.inverse(A4, 4);
        double[] prod4 = MatrixMath.multiply(A4, invA4, 4, 4, 4);
        assertIsIdentity(prod4, 4);

        // n = 4 singular (row 4 = row 1 + row 2)
        double[] S4 = {1, 2, 3, 4, 2, 5, 2, 1, 3, 2, 6, 2, 3, 7, 5, 5 // = row1 + row2
        };
        assertThrows(NonInvertibleMatrixException.class, () -> MatrixMath.inverse(S4, 4));
    }

    // ---------------------------------------------------------------------
    // Adjugate
    // ---------------------------------------------------------------------

    /**
     * Verifies adjugate for n=1,2,3 and n≥4 via the identity A·adj(A) = det(A)·I.
     */
    @Test
    void adjugateVariants()
    {
        // n = 1: adj([a]) = [1]
        double[] A1 = {7.0};
        assertMatrixClose(new double[] {1.0}, MatrixMath.adjugate(A1, 1));
        double d1 = MatrixMath.determinant(A1, 1);
        double[] P1 = MatrixMath.multiply(A1, MatrixMath.adjugate(A1, 1), 1, 1, 1);
        assertEquals(d1, P1[0], EPS);

        // n = 2
        double[] A2 = {4, 7, 2, 6};
        double[] adj2 = MatrixMath.adjugate(A2, 2); // [[6,-7],[-2,4]]
        assertMatrixClose(new double[] {6, -7, -2, 4}, adj2);
        double det2 = MatrixMath.determinant(A2, 2);
        double[] P2 = MatrixMath.multiply(A2, adj2, 2, 2, 2);
        // P2 should be det(A2) * I
        assertMatrixClose(new double[] {det2, 0, 0, det2}, P2);

        // n = 3: check A·adj(A) = det(A)·I
        double[] A3 = {1, 2, 3, 0, 1, 4, 5, 6, 0};
        double[] adj3 = MatrixMath.adjugate(A3, 3);
        double det3 = MatrixMath.determinant(A3, 3);
        double[] P3 = MatrixMath.multiply(A3, adj3, 3, 3, 3);
        double[] detI3 = {det3, 0, 0, 0, det3, 0, 0, 0, det3};
        assertMatrixClose(detI3, P3);

        // n = 4 (general path): A·adj(A) = det(A)·I
        double[] A4 = {1, 2, 3, 4, 2, 5, 2, 1, 3, 2, 6, 2, 4, 1, 2, 5};
        double[] adj4 = MatrixMath.adjugate(A4, 4);
        double det4 = MatrixMath.determinant(A4, 4);
        double[] P4 = MatrixMath.multiply(A4, adj4, 4, 4, 4);
        double[] detI4 = {det4, 0, 0, 0, 0, det4, 0, 0, 0, 0, det4, 0, 0, 0, 0, det4};
        assertMatrixClose(detI4, P4);
    }
}
