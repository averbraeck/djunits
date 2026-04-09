package org.djunits.vecmat.d3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Area;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.Speed;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.def.Matrix;
import org.djunits.vecmat.def.SquareDenseMatrix;
import org.djunits.vecmat.def.SquareMatrix;
import org.djunits.vecmat.def.VectorMatrix;
import org.djunits.vecmat.operations.Hadamard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Matrix3x3} with concrete quantity {@link Length} and unit {@link org.djunits.quantity.Length.Unit}.
 * <p>
 * The tests aim for 100% method and branch coverage of Matrix3x3 and inherited/default behavior from:
 * <ul>
 * <li>{@link VectorMatrix}</li>
 * <li>{@link SquareDenseMatrix}</li>
 * <li>{@link SquareMatrix}</li>
 * <li>{@link Hadamard}</li>
 * <li>and value-related interfaces used by VectorMatrixOps (Additive, Scalable, Value)</li>
 * </ul>
 * <p>
 * Assertions reflect the intended functional specification. If any method is incorrectly implemented, tests will fail—by
 * design.
 * <p>
 * <strong>Conventions used</strong>
 * <ul>
 * <li>Quantities: {@link Length}</li>
 * <li>Units: {@link org.djunits.quantity.Length.Unit#m} (SI base), {@link org.djunits.quantity.Length.Unit#km} (scale 1000),
 * and {@link org.djunits.quantity.Length.Unit#cm}</li>
 * <li>Numerical comparisons on SI arrays use a small epsilon</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class Matrix3x3Test
{
    /** Numerical tolerance for double comparisons. */
    private static final double EPS = 1.0E-12;

    /**
     * Helper that builds a {@code Matrix3x3<Length>} from SI values with a given display unit. Values in {@code si} are
     * interpreted as SI (meters) and the display unit is only for presentation. Internally, conversion happens by passing
     * display values to {@code of(...)}.
     * @param si a flat 9-element array of SI (meter) values in row-major order [a11, a12, a13, a21, a22, a23, a31, a32, a33]
     * @param displayUnit the display unit to assign to the matrix
     * @return a new matrix instance
     */
    private static Matrix3x3<Length> ofSi(final double[] si, final Length.Unit displayUnit)
    {
        double[] displayValues = new double[] {displayUnit.fromBaseValue(si[0]), displayUnit.fromBaseValue(si[1]),
                displayUnit.fromBaseValue(si[2]), displayUnit.fromBaseValue(si[3]), displayUnit.fromBaseValue(si[4]),
                displayUnit.fromBaseValue(si[5]), displayUnit.fromBaseValue(si[6]), displayUnit.fromBaseValue(si[7]),
                displayUnit.fromBaseValue(si[8])};
        return Matrix3x3.of(displayValues, displayUnit);
    }

    // ------------------------------------------------------------------------------------
    // Constructors and factories
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix3x3#of(double[], Unit)} rejects nulls and wrong sizes, and converts using the display unit.
     */
    @Test
    @DisplayName("Factory of(double[]) — nulls, length check, SI conversion")
    public void testFactoryOfArray()
    {
        // Null checks
        assertThrows(NullPointerException.class, () -> Matrix3x3.of((double[]) null, Length.Unit.km),
                "Null array should throw");
        assertThrows(NullPointerException.class, () -> Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, null),
                "Null unit should throw");

        // Length check
        assertThrows(IllegalArgumentException.class, () -> Matrix3x3.of(new double[] {1, 2, 3}, Length.Unit.km),
                "Array length != 9 should throw");

        // Successful creation & intended SI conversion (values are in km → SI m)
        Matrix3x3<Length> m = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.km);
        assertEquals(3, m.rows(), "rows");
        assertEquals(3, m.cols(), "cols");
        assertEquals(Length.Unit.km, m.getDisplayUnit(), "display unit");
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000}, m.getSiArray(), EPS,
                "values in km must be stored in SI (m)");
    }

    /**
     * Verify {@link Matrix3x3#of(double[][], Unit)} validation and SI conversion.
     */
    @Test
    @DisplayName("Factory of(double[][]) — nulls, 3x3 shape, SI conversion")
    public void testFactoryOfGrid()
    {
        assertThrows(NullPointerException.class, () -> Matrix3x3.of((double[][]) null, Length.Unit.km));
        assertThrows(NullPointerException.class, () -> Matrix3x3.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, null));

        // Not 3x3
        assertThrows(IllegalArgumentException.class, () -> Matrix3x3.of(new double[][] {{1, 2, 3}, {4, 5, 6}}, Length.Unit.m));

        // Successful creation — grid in km
        Matrix3x3<Length> m = Matrix3x3.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000}, m.getSiArray(), EPS);
    }

    /**
     * Verify {@link Matrix3x3#instantiateSi(double[])} creates a new matrix with the same display unit and the provided SI.
     */
    @Test
    @DisplayName("instantiateSi(double[]) — uses provided SI and keeps display unit")
    public void testInstantiate()
    {
        Matrix3x3<Length> base = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.km);
        double[] newSi = new double[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
        Matrix3x3<Length> inst = base.instantiateSi(newSi);
        assertEquals(Length.Unit.km, inst.getDisplayUnit());
        assertArrayEquals(newSi, inst.getSiArray(), EPS);

        Matrix3x3<SIQuantity> siMatrix = base.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siMatrix.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, siMatrix.getSiArray(), EPS, "si array used as-is");
        assertEquals(9.0, siMatrix.get(0, 0).si(), EPS);

        Matrix3x3<SIQuantity> siMatrixOf = Matrix3x3.of(new double[][] {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}}, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siMatrixOf.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, siMatrixOf.getSiArray(), EPS, "si array used as-is");
        assertEquals(9.0, siMatrixOf.get(0, 0).si(), EPS);

        assertThrows(IllegalArgumentException.class,
                () -> base.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.of(new double[][] {{10.0, 11.0, 12.0, 13.0, 14.0}}, SIUnit.of("kgm/s2K")));
    }

    // ------------------------------------------------------------------------------------
    // Basic Matrix behaviors: size, value(), display unit switching, toString
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link VectorMatrix#rows()}, {@link VectorMatrix#cols()}, {@link Matrix#get(int, int)}, and relative/absolute
     * flag.
     */
    @Test
    @DisplayName("rows/cols/value/isRelative")
    public void testBasicShapeAndValue()
    {
        Matrix3x3<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        assertEquals(3, m.rows());
        assertEquals(3, m.cols());
        assertEquals(1.0, m.get(0, 0).si(), EPS);
        assertEquals(1.0, m.mget(1, 1).si(), EPS);
        assertEquals(1.0, m.si(0, 0), EPS);
        assertEquals(1.0, m.msi(1, 1), EPS);
        assertTrue(m.isRelative(), "Length is relative (not absolute)");

        assertEquals(9, m.nnz());
        Matrix3x3<Length> m0 = ofSi(new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0}, Length.Unit.m);
        assertEquals(0, m0.nnz());
        Matrix3x3<Length> m6 =
                ofSi(new double[] {-1, -0.0, Double.NaN, Double.POSITIVE_INFINITY, 0.0, 6, 0.0, 8, 9}, Length.Unit.m);
        assertEquals(6, m6.nnz());
    }

    /**
     * Verify that {@link VectorMatrix#setDisplayUnit(Unit)} only affects presentation and not SI storage.
     */
    @Test
    @DisplayName("setDisplayUnit() only changes presentation")
    public void testSetDisplayUnit()
    {
        Matrix3x3<Length> m = ofSi(new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000}, Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
        m.setDisplayUnit(Length.Unit.m);
        assertEquals(Length.Unit.m, m.getDisplayUnit());
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000}, m.getSiArray(), EPS);
        m.setDisplayUnit(Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
    }

    /**
     * Verify {@link VectorMatrix#toString()} and {@link VectorMatrix#toString(Unit)}.
     */
    @Test
    @DisplayName("toString() and toString(unit) contain unit abbreviation")
    public void testToString()
    {
        Matrix3x3<Length> m = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.km);
        String s1 = m.toString();
        String s2 = m.toString(Length.Unit.km);
        assertTrue(s1.contains("km"));
        assertTrue(s2.contains("km"));
    }

    // ------------------------------------------------------------------------------------
    // VectorMatrixOps defaults via Matrix3x3: add/subtract (Q & VM), negate, abs, scaleBy, mean/median/min/max/sum
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@code add(Q)} and {@code subtract(Q)} with quantities.
     */
    @Test
    @DisplayName("add(Q) / subtract(Q) apply element-wise SI increments")
    public void testAddSubtractQuantity()
    {
        Matrix3x3<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        Length inc = Length.ofSi(10.0);
        Length dec = Length.ofSi(1.5);
        assertArrayEquals(new double[] {11, 12, 13, 14, 15, 16, 17, 18, 19}, m.add(inc).getSiArray(), EPS);
        assertArrayEquals(new double[] {-0.5, 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5}, m.subtract(dec).getSiArray(), EPS);
    }

    /**
     * Verify {@code add(VM)} and {@code subtract(VM)} element-wise for matrices.
     */
    @Test
    @DisplayName("add(VM) / subtract(VM) element-wise")
    public void testAddSubtractMatrix()
    {
        Matrix3x3<Length> a = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        Matrix3x3<Length> b = ofSi(new double[] {9, 8, 7, 6, 5, 4, 3, 2, 1}, Length.Unit.m);
        assertArrayEquals(new double[] {10, 10, 10, 10, 10, 10, 10, 10, 10}, a.add(b).getSiArray(), EPS);
        assertArrayEquals(new double[] {-8, -6, -4, -2, 0, 2, 4, 6, 8}, a.subtract(b).getSiArray(), EPS);
    }

    /**
     * Verify {@code negate()}, {@code abs()}, and {@code scaleBy(double)}.
     */
    @Test
    @DisplayName("negate / abs / scaleBy")
    public void testNegateAbsScaleBy()
    {
        Matrix3x3<Length> m = ofSi(new double[] {-1, 2, -3, 4, -5, 6, -7, 8, -9}, Length.Unit.km);
        assertArrayEquals(new double[] {1, -2, 3, -4, 5, -6, 7, -8, 9}, m.negate().getSiArray(), EPS);
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, m.abs().getSiArray(), EPS);
        assertArrayEquals(new double[] {-2, 4, -6, 8, -10, 12, -14, 16, -18}, m.scaleBy(2.0).getSiArray(), EPS);
    }

    /**
     * Verify {@code mean}, {@code median}, {@code min}, {@code max}, and {@code sum}.
     */
    @Test
    @DisplayName("mean / median / min / max / sum")
    public void testStats()
    {
        Matrix3x3<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        assertEquals(5.0, m.mean().si(), EPS, "mean");
        assertEquals(5.0, m.median().si(), EPS, "median of 9 entries");
        assertEquals(1.0, m.min().si(), EPS, "min");
        assertEquals(9.0, m.max().si(), EPS, "max");
        assertEquals(45.0, m.sum().si(), EPS, "sum");
    }

    // ------------------------------------------------------------------------------------
    // SquareMatrixOps: transpose, determinant, trace, norms, symmetry tests
    // ------------------------------------------------------------------------------------

    /**
     * Verify transpose on a 3x3 matrix.
     */
    @Test
    @DisplayName("transpose() transposes correctly")
    public void testTranspose()
    {
        Matrix3x3<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        // Transposed SI (row-major)
        Matrix3x3<Length> t = m.transpose();
        assertArrayEquals(new double[] {1, 4, 7, 2, 5, 8, 3, 6, 9}, t.getSiArray(), EPS);
        assertEquals(m.getDisplayUnit(), t.getDisplayUnit());
    }

    /**
     * Verify determinant as scalar and as quantity, and trace; also test the identity A · adj(A) = det(A) · I.
     */
    @Test
    @DisplayName("determinantScalar(), determinant(), trace(), and A·adj(A)=det(A)·I")
    public void testDeterminantTraceAdjugateIdentity()
    {
        // Well-known 3x3 with det = 1:
        // A = [[1,2,3],[0,1,4],[5,6,0]]
        Matrix3x3<Length> a = ofSi(new double[] {1, 2, 3, 0, 1, 4, 5, 6, 0}, Length.Unit.m);

        assertEquals(1.0, a.determinantSi(), EPS, "det(A)=1");
        assertEquals(1.0, a.determinant().si(), EPS, "det(A) as quantity");
        assertEquals(1.0 + 1.0 + 0.0, a.trace().si(), EPS, "trace(A)=2");

        // Check A * adj(A) = det(A) * I = I
        Matrix3x3<SIQuantity> adj = a.adjugate();
        Matrix3x3<SIQuantity> prod = a.multiply(adj);
        double[] p = prod.getSiArray();
        assertEquals(1.0, p[0], 1e-8);
        assertEquals(0.0, p[1], 1e-8);
        assertEquals(0.0, p[2], 1e-8);
        assertEquals(0.0, p[3], 1e-8);
        assertEquals(1.0, p[4], 1e-8);
        assertEquals(0.0, p[5], 1e-8);
        assertEquals(0.0, p[6], 1e-8);
        assertEquals(0.0, p[7], 1e-8);
        assertEquals(1.0, p[8], 1e-8);
    }

    /**
     * Verify Frobenius norm.
     */
    @Test
    @DisplayName("normFrobenius")
    public void testNormFrobenius()
    {
        Matrix3x3<Length> m = ofSi(new double[] {3, 4, 0, 0, 0, 0, 0, 0, 0}, Length.Unit.m);
        assertEquals(5.0, m.normFrobenius().si(), EPS);
    }

    /**
     * Verify symmetry and skew-symmetry checks with and without tolerances.
     */
    @Test
    @DisplayName("isSymmetric / isSkewSymmetric with and without tolerance")
    public void testSymmetry()
    {
        Matrix3x3<Length> sym = ofSi(new double[] {1, 2, 3, 2, 4, 5, 3, 5, 6}, Length.Unit.m);
        Matrix3x3<Length> skew = ofSi(new double[] {0, 2, 3, -2, 0, 4, -3, -4, 0}, Length.Unit.m);
        Matrix3x3<Length> nearSym = ofSi(new double[] {1, 2 + 1e-9, 3, 2, 4, 5, 3, 5, 6}, Length.Unit.m);

        assertTrue(sym.isSymmetric());
        assertFalse(sym.isSkewSymmetric());

        assertTrue(skew.isSkewSymmetric());
        assertFalse(skew.isSymmetric());

        Length tol = Length.ofSi(1e-6);
        assertTrue(nearSym.isSymmetric(tol));
        assertFalse(nearSym.isSkewSymmetric(tol));
    }

    // ------------------------------------------------------------------------------------
    // Inversion, adjugate, matrix x matrix, matrix x vector, element-wise ops (Hadamard)
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix3x3#inverse()} and multiplication with its inverse gives the identity (numerically).
     * @throws NonInvertibleMatrixException if inversion fails (test expects success)
     */
    @Test
    @DisplayName("inverse() and A · A^{-1} ≈ I")
    public void testInverseAndIdentity() throws NonInvertibleMatrixException
    {
        // Use A with det=1 (from above): inverse exists and has convenient values
        Matrix3x3<Length> a = ofSi(new double[] {1, 2, 3, 0, 1, 4, 5, 6, 0}, Length.Unit.km);
        Matrix3x3<SIQuantity> inv = a.inverse(); // unit U^(-1)
        Matrix3x3<SIQuantity> prod = a.multiply(inv); // ≈ I

        double[] p = prod.getSiArray();
        assertEquals(1.0, p[0], 1e-8);
        assertEquals(0.0, p[1], 1e-8);
        assertEquals(0.0, p[2], 1e-8);
        assertEquals(0.0, p[3], 1e-8);
        assertEquals(1.0, p[4], 1e-8);
        assertEquals(0.0, p[5], 1e-8);
        assertEquals(0.0, p[6], 1e-8);
        assertEquals(0.0, p[7], 1e-8);
        assertEquals(1.0, p[8], 1e-8);
    }

    /**
     * Verify that {@link Matrix3x3#inverse()} throws on singular matrices.
     */
    @Test
    @DisplayName("inverse() throws on singular matrix")
    public void testInverseThrowsOnSingular()
    {
        // Singular: third row = sum of first two rows
        Matrix3x3<Length> singular = ofSi(new double[] {1, 2, 3, 4, 5, 6, 5, 7, 9}, Length.Unit.m);
        assertThrows(NonInvertibleMatrixException.class, singular::inverse);
    }

    /**
     * Verify standard 3x3 matrix multiplication (A·B).
     */
    @Test
    @DisplayName("matrix x matrix multiplication")
    public void testMatrixMultiply()
    {
        // A = [[1,2,3],[4,5,6],[7,8,9]]
        Matrix3x3<Length> a = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        // B in km ⇒ SI is 1000x
        Matrix3x3<Length> b = Matrix3x3.of(new double[] {9, 8, 7, 6, 5, 4, 3, 2, 1}, Length.Unit.km);

        Matrix3x3<SIQuantity> c = a.multiply(b);
        // Compute expected in SI: m x km ⇒ elements in m^2 numerically; A·(1000·B')
        // A·B' = [[30,24,18],[84,69,54],[138,114,90]] then x1000
        assertArrayEquals(new double[] {30_000, 24_000, 18_000, 84_000, 69_000, 54_000, 138_000, 114_000, 90_000},
                c.getSiArray(), EPS);
    }

    /**
     * Verify multiplication with a column vector (A·v).
     */
    @Test
    @DisplayName("matrix x vector (column)")
    public void testMatrixTimesVector()
    {
        // A same as above (in m); v in km → SI [1000, 2000, 3000]
        Matrix3x3<Length> a = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        Vector3.Col<Length> v = Vector3.Col.of(1.0, 2.0, 3.0, Length.Unit.km);

        Vector3.Col<SIQuantity> r = a.multiply(v);
        // r = A·[1000,2000,3000]^T = [14000, 32000, 50000]
        assertArrayEquals(new double[] {14_000, 32_000, 50_000}, r.getSiArray(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), r.getDisplayUnit());
    }

    /**
     * Verify Hadamard element-wise operations: invert, multiply, divide.
     */
    @Test
    @DisplayName("Hadamard: invertElements / multiplyElements / divideElements")
    public void testHadamard()
    {
        Matrix3x3<Length> a = Matrix3x3.of(new double[] {2, 4, 5, 10, 20, 40, 8, 16, 32}, Length.Unit.m);
        Matrix3x3<Length> b = Matrix3x3.of(new double[] {1, 2, 0.5, 4, 0.25, 8, 0.125, 0.5, 4}, Length.Unit.km);

        Matrix3x3<SIQuantity> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1, 0.05, 0.025, 0.125, 0.0625, 0.03125}, inv.getSiArray(), EPS);

        Matrix3x3<SIQuantity> mul = a.multiplyEntries(b);
        // element-wise multiply with km→ SI scaling
        assertArrayEquals(new double[] {2000, 8000, 2500, 40_000, 5_000, 320_000, 1000, 8000, 128_000}, mul.getSiArray(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());

        Matrix3x3<SIQuantity> div = a.divideEntries(b);
        assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025, 0.08, 0.005, 0.064, 0.032, 0.008}, div.getSiArray(), 1e-15);
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit());
    }

    // ------------------------------------------------------------------------------------
    // as(targetUnit): success and failure branches
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix3x3#as(Unit)} succeeds when SI units match (e.g., m ↔ km), and throws when SI units mismatch (e.g.,
     * length ↔ time).
     */
    @Test
    @DisplayName("as(targetUnit) success (m↔km) and failure (length↔time)")
    public void testAs()
    {
        Matrix3x3<Length> mKm = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.km);

        Matrix3x3<Length> asMeters = mKm.as(Length.Unit.m);
        assertEquals(Length.Unit.m, asMeters.getDisplayUnit(), "display unit switched");
        assertArrayEquals(mKm.getSiArray(), asMeters.getSiArray(), EPS, "SI storage unchanged");

        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> mKm.as(second));
    }

    // ------------------------------------------------------------------------------------
    // getScalars / getRowScalars / getColumnScalars / getDiagonalScalars
    // getRow / getColumn / getDiagonal (spec-compliant expectations)
    // ------------------------------------------------------------------------------------

    /**
     * Verify scalar array extraction helpers on {@link VectorMatrix}.
     */
    @Test
    @DisplayName("getScalarGrid / getSiGrid / getRowScalars / getColumnScalars / getDiagonalScalars")
    public void testScalarExtraction()
    {
        Matrix3x3<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);

        Length[][] scalars = m.getScalarGrid();
        double[][] sigrid = m.getSiGrid();
        Length[] row1 = m.getRowScalars(1);
        Length[] col2 = m.getColumnScalars(2);
        Length[] diag = m.getDiagonalScalars();

        assertEquals(3, scalars.length);
        assertEquals(3, scalars[0].length);
        assertEquals(1.0, scalars[0][0].si(), EPS);
        assertEquals(5.0, scalars[1][1].si(), EPS);
        assertEquals(9.0, scalars[2][2].si(), EPS);

        assertEquals(3, sigrid.length);
        assertEquals(3, sigrid[0].length);
        assertEquals(3, sigrid[1].length);
        assertEquals(3, sigrid[2].length);
        assertEquals(1.0, sigrid[0][0], EPS);
        assertEquals(5.0, sigrid[1][1], EPS);
        assertEquals(9.0, sigrid[2][2], EPS);

        assertEquals(3, row1.length);
        assertEquals(4.0, row1[0].si(), EPS);
        assertEquals(6.0, row1[2].si(), EPS);

        assertEquals(3, col2.length);
        assertEquals(3.0, col2[0].si(), EPS);
        assertEquals(9.0, col2[2].si(), EPS);

        assertEquals(3, diag.length);
        assertEquals(1.0, diag[0].si(), EPS);
        assertEquals(5.0, diag[1].si(), EPS);
        assertEquals(9.0, diag[2].si(), EPS);

        Length[] mRow2 = m.mgetRowScalars(2);
        Length[] mCol3 = m.mgetColumnScalars(3);
        assertEquals(3, mRow2.length);
        assertEquals(4.0, mRow2[0].si(), EPS);
        assertEquals(6.0, mRow2[2].si(), EPS);
        assertEquals(3, mCol3.length);
        assertEquals(3.0, mCol3[0].si(), EPS);
        assertEquals(9.0, mCol3[2].si(), EPS);
    }

    /**
     * Verify double array extraction helpers on {@link VectorMatrix}.
     */
    @Test
    @DisplayName("getRowSi / getColumnSi / getDiagonalSi")
    public void testSiArrayExtraction()
    {
        Matrix3x3<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);

        double[] row1 = m.getRowSi(1);
        double[] col2 = m.getColumnSi(2);
        double[] diag = m.getDiagonalSi();

        assertEquals(3, row1.length);
        assertEquals(4.0, row1[0], EPS);
        assertEquals(6.0, row1[2], EPS);
        assertEquals(3, col2.length);
        assertEquals(3.0, col2[0], EPS);
        assertEquals(9.0, col2[2], EPS);
        assertEquals(3, diag.length);
        assertEquals(1.0, diag[0], EPS);
        assertEquals(5.0, diag[1], EPS);
        assertEquals(9.0, diag[2], EPS);

        double[] mRow2 = m.mgetRowSi(2);
        double[] mCol3 = m.mgetColumnSi(3);
        assertEquals(3, mRow2.length);
        assertEquals(4.0, mRow2[0], EPS);
        assertEquals(6.0, mRow2[2], EPS);
        assertEquals(3, mCol3.length);
        assertEquals(3.0, mCol3[0], EPS);
        assertEquals(9.0, mCol3[2], EPS);
    }

    /**
     * Verify vector extraction helpers ({@code getRow}, {@code getColumn}, {@code getDiagonal}) behave per spec: this test
     * expects non-null vectors of appropriate type and unit.
     */
    @Test
    @DisplayName("getRow / getColumn / getDiagonal return expected vectors (spec)")
    public void testVectorExtractionSpec()
    {
        Matrix3x3<Length> m = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);

        assertNotNull(m.getRowVector(1), "getRowVector(1) non-null");
        assertNotNull(m.getColumnVector(2), "getColumnVector(2) non-null");
        assertNotNull(m.getDiagonalVector(), "getDiagonalVector() non-null");

        Vector3.Row<Length> row1 = m.getRowVector(1);
        Vector3.Col<Length> col2 = m.getColumnVector(2);
        Vector3.Col<Length> diag = m.getDiagonalVector();

        assertEquals(3, row1.size());
        assertEquals(4.0, row1.get(0).si(), EPS);
        assertEquals(6.0, row1.get(2).si(), EPS);
        assertEquals(3, col2.size());
        assertEquals(3.0, col2.get(0).si(), EPS);
        assertEquals(9.0, col2.get(2).si(), EPS);
        assertEquals(3, diag.size());
        assertEquals(1.0, diag.get(0).si(), EPS);
        assertEquals(5.0, diag.get(1).si(), EPS);
        assertEquals(9.0, diag.get(2).si(), EPS);

        Vector3.Row<Length> mRow2 = m.mgetRowVector(2);
        Vector3.Col<Length> mCol3 = m.mgetColumnVector(3);
        assertEquals(3, mRow2.size());
        assertEquals(4.0, mRow2.get(0).si(), EPS);
        assertEquals(6.0, mRow2.get(2).si(), EPS);
        assertEquals(3, mCol3.size());
        assertEquals(3.0, mCol3.get(0).si(), EPS);
        assertEquals(9.0, mCol3.get(2).si(), EPS);
    }

    // ------------------------------------------------------------------------------------
    // equals / hashCode
    // ------------------------------------------------------------------------------------

    /**
     * Verify equality and hashCode semantics across {@link VectorMatrix}, {@link SquareDenseMatrix}, and {@link Matrix3x3}.
     */
    @Test
    @DisplayName("equals / hashCode")
    public void testEqualsHashCode()
    {
        Matrix3x3<Length> a1 = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        Matrix3x3<Length> a2 = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.m);
        Matrix3x3<Length> b = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 10}, Length.Unit.m);

        assertEquals(a1, a1, "reflexive");
        assertEquals(a1, a2, "equal contents");
        assertEquals(a1.hashCode(), a2.hashCode(), "hashCode equal");
        assertNotEquals(a1, b, "different contents");
        assertNotEquals(a1, null, "not equal to null");
        assertNotEquals(a1, "other type", "not equal to other type");
    }

    // ------------------------------------------------------------------------------------
    // Defensive: additional boundary/argument tests
    // ------------------------------------------------------------------------------------

    /**
     * Ensure index-related methods do not throw for valid bounds and that value retrieval uses display unit.
     */
    @Test
    @DisplayName("Indexing within bounds and value retrieval")
    public void testIndexingAndValues()
    {
        Matrix3x3<Length> m = Matrix3x3.of(new double[] {1.0, 200.0, 3.0, 400.0, 5.0, 600.0, 7.0, 800.0, 9.0}, Length.Unit.cm);
        // SI values: [0.01, 2.0, 0.03, 4.0, 0.05, 6.0, 0.07, 8.0, 0.09]
        assertDoesNotThrow(() -> m.get(1, 1));
        assertDoesNotThrow(() -> m.get(2, 2));
        assertDoesNotThrow(() -> m.mget(1, 1));
        assertDoesNotThrow(() -> m.mget(2, 2));
        assertDoesNotThrow(() -> m.si(1, 1));
        assertDoesNotThrow(() -> m.si(2, 2));
        assertDoesNotThrow(() -> m.msi(1, 1));
        assertDoesNotThrow(() -> m.msi(2, 2));
        assertEquals(0.01, m.get(0, 0).si(), EPS);
        assertEquals(0.09, m.get(2, 2).setDisplayUnit(Length.Unit.m).si(), EPS);
        assertEquals(0.01, m.mget(1, 1).si(), EPS);
        assertEquals(0.09, m.mget(3, 3).setDisplayUnit(Length.Unit.m).si(), EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.get(-1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(3, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mget(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mget(4, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.msi(0, 2));
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        Matrix3x3<Length> r = Matrix3x3.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        Matrix3x3<Speed> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1, 1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.mget(1, 2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.mget(1, 3).getInUnit(), 1E-6);
        assertEquals(2.0, sr.mget(2, 1).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));
    }

    // ------------------------------------------------------------------------------------
    // Static factory methods: Matrix3x3 of() and ofSi() — exhaustive corner cases
    // ------------------------------------------------------------------------------------

    /**
     * Test {@link Matrix3x3#of(double[], Unit)} for nulls, wrong sizes, and unit conversion.
     */
    @Test
    @DisplayName("Matrix3x3 of(double[], Unit): nulls, size checks, unit conversion")
    public void testMatrix3x3OfDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> Matrix3x3.of((double[]) null, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> Matrix3x3.of(new double[9], null));

        assertThrows(IllegalArgumentException.class, () -> Matrix3x3.of(new double[8], Length.Unit.m));

        Matrix3x3<Length> m = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.cm);

        assertArrayEquals(new double[] {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09}, m.getSiArray(), EPS);
    }

    /**
     * Test {@link Matrix3x3#ofSi(double[], Unit)}.
     */
    @Test
    @DisplayName("Matrix3x3 ofSi(double[], Unit): nulls, size checks")
    public void testMatrix3x3OfSiDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> Matrix3x3.ofSi((double[]) null, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> Matrix3x3.ofSi(new double[9], null));

        assertThrows(IllegalArgumentException.class, () -> Matrix3x3.ofSi(new double[10], Length.Unit.m));

        Matrix3x3<Length> m = Matrix3x3.ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.km);

        assertEquals(Length.Unit.km, m.getDisplayUnit());
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, m.getSiArray(), EPS);
    }

    /**
     * Exhaustive grid validation for {@link Matrix3x3#of(double[][], Unit)}.
     */
    @Test
    @DisplayName("Matrix3x3 of(double[][], Unit): exhaustive grid validation")
    public void testMatrix3x3OfDoubleGridAllCornerCases()
    {
        assertThrows(NullPointerException.class, () -> Matrix3x3.of((double[][]) null, Length.Unit.m));

        // wrong row count
        assertThrows(IllegalArgumentException.class, () -> Matrix3x3.of(new double[][] {{1, 2, 3}}, Length.Unit.m));

        // null rows
        assertThrows(NullPointerException.class,
                () -> Matrix3x3.of(new double[][] {null, {1, 2, 3}, {4, 5, 6}}, Length.Unit.m));

        assertThrows(NullPointerException.class,
                () -> Matrix3x3.of(new double[][] {{1, 2, 3}, null, {4, 5, 6}}, Length.Unit.m));

        // wrong column sizes — each row isolated
        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.of(new double[][] {{1, 2}, {3, 4, 5}, {6, 7, 8}}, Length.Unit.m));

        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.of(new double[][] {{1, 2, 3}, {4, 5}, {6, 7, 8}}, Length.Unit.m));

        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8}}, Length.Unit.m));

        // valid grid
        Matrix3x3<Duration> m = Matrix3x3.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Duration.Unit.h);

        assertArrayEquals(new double[] {3600, 7200, 10800, 14400, 18000, 21600, 25200, 28800, 32400}, m.getSiArray(), EPS);
    }

    /**
     * Exhaustive grid validation for {@link Matrix3x3#ofSi(double[][], Unit)}.
     */
    @Test
    @DisplayName("Matrix3x3 ofSi(double[][], Unit): exhaustive grid validation")
    public void testMatrix3x3OfSiDoubleGridAllCornerCases()
    {
        assertThrows(NullPointerException.class, () -> Matrix3x3.ofSi((double[][]) null, Length.Unit.m));

        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.ofSi(new double[][] {{1, 2, 3}, {4, 5, 6}}, Length.Unit.m));

        assertThrows(NullPointerException.class,
                () -> Matrix3x3.ofSi(new double[][] {{1, 2, 3}, null, {7, 8, 9}}, Length.Unit.m));

        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.ofSi(new double[][] {{1, 2, 3}, {4, 5}, {7, 8, 9}}, Length.Unit.m));

        Matrix3x3<Duration> m = Matrix3x3.ofSi(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Duration.Unit.ms);

        assertEquals(Duration.Unit.ms, m.getDisplayUnit());
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, m.getSiArray(), EPS);
    }

    /**
     * Test {@link Matrix3x3#of(Quantity[])}.
     */
    @Test
    @DisplayName("Matrix3x3 of(Q[]): nulls, size, conversion")
    public void testMatrix3x3OfQuantityArray()
    {
        assertThrows(NullPointerException.class, () -> Matrix3x3.of((Length[]) null));

        assertThrows(NullPointerException.class, () -> Matrix3x3.of(new Length[] {Length.ofSi(1), Length.ofSi(2), null,
                Length.ofSi(4), Length.ofSi(5), Length.ofSi(6), Length.ofSi(7), Length.ofSi(8), Length.ofSi(9)}));

        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.of(new Length[] {Length.ofSi(1), Length.ofSi(2), Length.ofSi(3)}));

        Length[] data = {new Length(1, Length.Unit.km), new Length(2, Length.Unit.km), new Length(3, Length.Unit.km),
                new Length(4, Length.Unit.km), new Length(5, Length.Unit.km), new Length(6, Length.Unit.km),
                new Length(7, Length.Unit.km), new Length(8, Length.Unit.km), new Length(9, Length.Unit.km)};

        Matrix3x3<Length> m = Matrix3x3.of(data);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000}, m.getSiArray(), EPS);
    }

    /**
     * Exhaustive grid validation for {@link Matrix3x3#of(Quantity[][])}.
     */
    @Test
    @DisplayName("Matrix3x3 of(Q[][]): exhaustive grid validation")
    public void testMatrix3x3OfQuantityGridAllCornerCases()
    {
        assertThrows(NullPointerException.class, () -> Matrix3x3.of((Length[][]) null));

        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.of(new Length[][] {{Length.ofSi(1), Length.ofSi(2), Length.ofSi(3)}}));

        assertThrows(NullPointerException.class, () -> Matrix3x3.of(new Length[][] {null,
                {Length.ofSi(4), Length.ofSi(5), Length.ofSi(6)}, {Length.ofSi(7), Length.ofSi(8), Length.ofSi(9)}}));

        assertThrows(IllegalArgumentException.class,
                () -> Matrix3x3.of(new Length[][] {{Length.ofSi(1), Length.ofSi(2), Length.ofSi(3)},
                        {Length.ofSi(4), Length.ofSi(5)}, {Length.ofSi(7), Length.ofSi(8), Length.ofSi(9)}}));

        assertThrows(NullPointerException.class, () -> Matrix3x3.of(new Length[][] {{Length.ofSi(1), null, Length.ofSi(3)},
                {Length.ofSi(4), Length.ofSi(5), Length.ofSi(6)}, {Length.ofSi(7), Length.ofSi(8), Length.ofSi(9)}}));

        Length[][] grid = {{new Length(1, Length.Unit.cm), new Length(2, Length.Unit.cm), new Length(3, Length.Unit.cm)},
                {new Length(4, Length.Unit.cm), new Length(5, Length.Unit.cm), new Length(6, Length.Unit.cm)},
                {new Length(7, Length.Unit.cm), new Length(8, Length.Unit.cm), new Length(9, Length.Unit.cm)}};

        Matrix3x3<Length> m = Matrix3x3.of(grid);
        assertArrayEquals(new double[] {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09}, m.getSiArray(), EPS);
    }

}
