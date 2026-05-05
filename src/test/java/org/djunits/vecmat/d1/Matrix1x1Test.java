package org.djunits.vecmat.d1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.def.SquareDenseMatrix;
import org.djunits.vecmat.def.SquareMatrix;
import org.djunits.vecmat.def.VectorMatrix;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.operations.Hadamard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Matrix1x1} with concrete quantity {@link Length} and unit {@link org.djunits.quantity.Length.Unit}.
 * <p>
 * The tests aim for 100% method and branch coverage in Matrix1x1 and the inherited default functionality from:
 * <ul>
 * <li>{@link VectorMatrix}</li>
 * <li>{@link SquareDenseMatrix}</li>
 * <li>{@link SquareMatrix}</li>
 * <li>{@link Hadamard}</li>
 * <li>and the value interfaces used by {@code MatrixOps} (Additive, Scalable, Value)</li>
 * </ul>
 * <p>
 * The assertions are written to reflect the <em>intended</em> functional specification. If any method in the class hierarchy is
 * incorrectly implemented (including TODOs not implemented), tests will fail, as desired.
 * <p>
 * <strong>Conventions used in these tests</strong>
 * <ul>
 * <li>Quantities: {@link Length}</li>
 * <li>Units under test: {@link org.djunits.quantity.Length.Unit#m} (SI base), {@link org.djunits.quantity.Length.Unit#km}
 * (scale 1000), and occasionally {@link org.djunits.quantity.Length.Unit#cm}</li>
 * <li>Numerical comparisons on SI arrays use a small epsilon</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class Matrix1x1Test
{
    /** Numerical tolerance for double comparisons. */
    private static final double EPS = 1.0E-12;

    /**
     * Helper that builds a {@code Matrix1x1<Length>} from SI values with a given display unit.
     * @param si a flat 4-element array of SI (meter) values in row-major order [a11, a12, a21, a22]
     * @param displayUnit the display unit to assign to the matrix
     * @return a new matrix instance
     */
    private static Matrix1x1<Length> ofSi(final double si, final Length.Unit displayUnit)
    {
        // Use the public factory with display values by converting SI back into display unit values.
        double[] displayValues = new double[] {displayUnit.fromBaseValue(si)};
        return Matrix1x1.of(displayValues, displayUnit);
    }

    // ------------------------------------------------------------------------------------
    // Constructors and factories
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix1x1#of(double[], Unit)} rejects nulls and wrong sizes, and converts using the display unit.
     */
    @Test
    @DisplayName("Factory of(double[]) — nulls, length check, and SI conversion")
    public void testFactoryOfArray()
    {
        // Null checks
        assertThrows(NullPointerException.class, () -> Matrix1x1.of((double[]) null, Length.Unit.km),
                "Null value array should throw");
        assertThrows(NullPointerException.class, () -> Matrix1x1.of(new double[] {1}, null), "Null unit should throw");

        // Length check
        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new double[] {1, 2}, Length.Unit.km),
                "Array length != 1 should throw");
        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new double[] {}, Length.Unit.km),
                "Array length != 1 should throw");

        // Successful creation & SI conversion
        Matrix1x1<Length> m = Matrix1x1.of(new double[] {1}, Length.Unit.km);
        assertEquals(1, m.rows(), "rows");
        assertEquals(1, m.cols(), "cols");
        assertEquals(Length.Unit.km, m.getDisplayUnit(), "display unit preserved");
        assertArrayEquals(new double[] {1000}, m.getSiArray(), EPS, "values converted from km to m (SI)");
    }

    /**
     * Verify {@link Matrix1x1#of(double[][], Unit)} validation and SI conversion.
     */
    @Test
    @DisplayName("Factory of(double[][]) — nulls, 1x1 shape, and SI conversion")
    public void testFactoryOfGrid()
    {
        // Null checks
        assertThrows(NullPointerException.class, () -> Matrix1x1.of((double[][]) null, Length.Unit.km),
                "Null grid should throw");
        assertThrows(NullPointerException.class, () -> Matrix1x1.of(new double[][] {{1}}, null), "Null unit should throw");

        // Not 1x1
        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new double[][] {{1, 2, 3}, {4, 5, 6}}, Length.Unit.m),
                "Non-1x1 grid should throw");

        // Successful creation
        Matrix1x1<Length> m = Matrix1x1.of(new double[][] {{1}}, Length.Unit.km);
        assertArrayEquals(new double[] {1000}, m.getSiArray(), EPS);
    }

    /**
     * Verify {@link Matrix1x1#instantiateSi(double[])} creates a new matrix with the same display unit and the provided SI
     * values.
     */
    @Test
    @DisplayName("instantiate(double[]) — uses provided SI data and keeps display unit")
    public void testInstantiate()
    {
        Matrix1x1<Length> base = Matrix1x1.of(new double[] {1}, Length.Unit.km);
        double[] newSi = new double[] {10};
        Matrix1x1<Length> inst = base.instantiateSi(newSi);
        assertEquals(Length.Unit.km, inst.getDisplayUnit(), "display unit retained");
        assertArrayEquals(newSi, inst.getSiArray(), EPS, "si array used as-is");

        Matrix1x1<SIQuantity> siMatrix = base.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siMatrix.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, siMatrix.getSiArray(), EPS, "si array used as-is");
        assertEquals(10.0, siMatrix.get(0, 0).si(), EPS);

        Matrix1x1<SIQuantity> siMatrixOf = Matrix1x1.of(new double[][] {{10.0}}, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siMatrixOf.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, siMatrixOf.getSiArray(), EPS, "si array used as-is");
        assertEquals(10.0, siMatrixOf.get(0, 0).si(), EPS);

        assertThrows(IllegalArgumentException.class,
                () -> base.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
        assertThrows(IllegalArgumentException.class,
                () -> Matrix1x1.of(new double[][] {{10.0, 11.0, 12.0, 13.0, 14.0}}, SIUnit.of("kgm/s2K")));
    }

    // ------------------------------------------------------------------------------------
    // Basic Matrix/Value behaviors: size, value(), display unit switching, toString
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link VectorMatrix#rows()}, {@link VectorMatrix#cols()}, and relative/absolute flag.
     */
    @Test
    @DisplayName("rows/cols/value/isRelative")
    public void testBasicShapeAndValue()
    {
        Matrix1x1<Length> m = ofSi(1.0, Length.Unit.m);
        assertEquals(1, m.rows(), "rows");
        assertEquals(1, m.cols(), "cols");
        assertEquals(1.0, m.get(0, 0).si(), EPS, "(1,1) in SI");
        assertTrue(m.isRelative(), "Length is a relative quantity (not absolute)");
        assertEquals(1, m.nnz());
        Matrix1x1<Length> m0 = ofSi(0.0, Length.Unit.m);
        assertEquals(0, m0.nnz());
    }

    /**
     * Verify that {@link VectorMatrix#setDisplayUnit(Unit)} only affects presentation and not SI storage.
     */
    @Test
    @DisplayName("setDisplayUnit() only changes presentation")
    public void testSetDisplayUnit()
    {
        Matrix1x1<Length> m = ofSi(1000, Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
        // switch to meters
        m.setDisplayUnit(Length.Unit.m);
        assertEquals(Length.Unit.m, m.getDisplayUnit(), "display unit changed");
        assertArrayEquals(new double[] {1000}, m.getSiArray(), EPS, "SI unchanged");
        assertEquals(1000.0, m.get(0, 0).si(), EPS, "value SI unaffected");
        // back to km
        m.setDisplayUnit(Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
    }

    /**
     * Call {@link VectorMatrix#toString()} and {@link VectorMatrix#format(Unit)} for coverage. We assert the unit
     * abbreviation is present; we do not depend on exact formatting of numbers.
     */
    @Test
    @DisplayName("toString() and toString(unit) contain unit abbreviation")
    public void testToString()
    {
        Matrix1x1<Length> m = Matrix1x1.of(new double[] {1}, Length.Unit.km);
        String s1 = m.toString();
        String s2 = m.format(Length.Unit.km);
        assertTrue(s1.contains("km"), "default toString includes display unit");
        assertTrue(s2.contains("km"), "toString(withUnit) includes provided unit");
    }

    // ------------------------------------------------------------------------------------
    // MatrixOps defaults: add/subtract (Q and VM), negate, abs, scaleBy, mean/median/min/max/sum
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link org.djunits.vecmat.def.Matrix#add(Quantity)} and {@code subtract(Q)} with quantities.
     */
    @Test
    @DisplayName("add(Q) / subtract(Q) apply element-wise SI increments")
    public void testAddSubtractQuantity()
    {
        Matrix1x1<Length> m = ofSi(1.0, Length.Unit.m);
        Length inc = Length.ofSi(10.0);
        Length dec = Length.ofSi(1.5);
        assertArrayEquals(new double[] {11}, m.add(inc).getSiArray(), EPS);
        assertArrayEquals(new double[] {-0.5}, m.subtract(dec).getSiArray(), EPS);
    }

    /**
     * Verify {@link org.djunits.vecmat.def.Matrix#add(Quantity)} and {@code subtract(VM)} with another matrix.
     */
    @Test
    @DisplayName("add(VM) / subtract(VM) element-wise")
    public void testAddSubtractMatrix()
    {
        Matrix1x1<Length> a = ofSi(1.0, Length.Unit.m);
        Matrix1x1<Length> b = ofSi(10.0, Length.Unit.m);
        assertArrayEquals(new double[] {11}, a.add(b).getSiArray(), EPS);
        assertArrayEquals(new double[] {-9}, a.subtract(b).getSiArray(), EPS);
    }

    /**
     * Verify {@link org.djunits.vecmat.def.Matrix#negate()}, {@link org.djunits.vecmat.def.Matrix#abs()}, and
     * {@link org.djunits.vecmat.def.Matrix#scaleBy(double)}.
     */
    @Test
    @DisplayName("negate / abs / scaleBy")
    public void testNegateAbsScaleBy()
    {
        Matrix1x1<Length> m = ofSi(-1.0, Length.Unit.km);
        assertArrayEquals(new double[] {1}, m.negate().getSiArray(), EPS);
        assertArrayEquals(new double[] {1}, m.abs().getSiArray(), EPS);
        assertArrayEquals(new double[] {-2}, m.scaleBy(2.0).getSiArray(), EPS);
    }

    /**
     * Verify {@code mean}, {@code median}, {@code min}, {@code max}, and {@code sum}.
     */
    @Test
    @DisplayName("mean / median / min / max / sum")
    public void testStats()
    {
        Matrix1x1<Length> m = ofSi(2.5, Length.Unit.m);
        assertEquals(2.5, m.mean().si(), EPS, "mean");
        assertEquals(2.5, m.median().si(), EPS, "median (even-sized set)");
        assertEquals(2.5, m.min().si(), EPS, "min");
        assertEquals(2.5, m.max().si(), EPS, "max");
        assertEquals(2.5, m.sum().si(), EPS, "sum");
    }

    // ------------------------------------------------------------------------------------
    // SquareMatrixOps: transpose, determinant, trace, norms, symmetry tests
    // ------------------------------------------------------------------------------------

    /**
     * Verify transpose on a 1x1 matrix.
     */
    @Test
    @DisplayName("transpose() swaps off-diagonals")
    public void testTranspose()
    {
        Matrix1x1<Length> m = ofSi(3.0, Length.Unit.m); // [ [3] ]
        Matrix1x1<Length> t = m.transpose();
        assertArrayEquals(new double[] {3}, t.getSiArray(), EPS);
        assertEquals(m.getDisplayUnit(), t.getDisplayUnit(), "transpose keeps unit");
    }

    /**
     * Verify determinant as scalar and as quantity, and trace.
     */
    @Test
    @DisplayName("determinantScalar(), determinant(), trace()")
    public void testDeterminantAndTrace()
    {
        Matrix1x1<Length> m = ofSi(2.0, Length.Unit.m);
        // det = 2
        assertEquals(2, m.determinantSi(), EPS, "determinantScalar");
        assertEquals(2.0, m.determinant().si(), EPS, "determinant quantity SI value");
        // trace = a = 2
        assertEquals(2.0, m.trace().si(), EPS, "trace");
    }

    /**
     * Verify Frobenius norm and default {@code norm()}.
     */
    @Test
    @DisplayName("normFrobenius and default norm()")
    public void testNorms()
    {
        Matrix1x1<Length> m = ofSi(3.0, Length.Unit.m);
        // Frobenius = sqrt(3^2) = 3
        assertEquals(3.0, m.normFrobenius().si(), EPS);
    }

    /**
     * Verify symmetry and skew-symmetry checks with and without tolerances.
     */
    @Test
    @DisplayName("isSymmetric / isSkewSymmetric with and without tolerance")
    public void testSymmetry()
    {
        Matrix1x1<Length> sym = ofSi(1.0, Length.Unit.m);

        assertTrue(sym.isSymmetric(), "exactly symmetric");
        assertFalse(sym.isSkewSymmetric(), "skew");

        Length tol = Length.ofSi(1e-6);
        assertTrue(sym.isSymmetric(tol), "symmetric within tolerance");
        assertFalse(sym.isSkewSymmetric(tol), "skew within tolerance");
    }

    // ------------------------------------------------------------------------------------
    // Inversion, adjugate, matrix x matrix, matrix x vector, element-wise ops (Hadamard)
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix1x1#inverse()} and multiplication with its inverse gives the identity (numerically).
     * @throws NonInvertibleMatrixException if inversion fails (test expects success)
     */
    @Test
    @DisplayName("inverse() and A * A^{-1} ≈ I")
    public void testInverseAndIdentity() throws NonInvertibleMatrixException
    {
        Matrix1x1<Length> a = ofSi(4.0, Length.Unit.km);
        Matrix1x1<SIQuantity> inv = a.inverse();

        // Multiply A * A^{-1} → should approximate identity; unit should reduce to unitless (not asserted here)
        Matrix1x1<SIQuantity> prod = a.multiply(inv);
        double[] p = prod.getSiArray();
        assertEquals(1.0, p[0], 1e-9, "I(1,1)");

    }

    /**
     * Verify that {@link Matrix1x1#inverse()} throws on singular matrices.
     */
    @Test
    @DisplayName("inverse() throws on singular matrix")
    public void testInverseThrowsOnSingular()
    {
        // Singular: rows proportional
        Matrix1x1<Length> singular = ofSi(0.0, Length.Unit.m);
        assertThrows(NonInvertibleMatrixException.class, singular::inverse);
    }

    /**
     * Verify {@link Matrix1x1#adjugate()} for a generic 1x1 matrix.
     */
    @Test
    @DisplayName("adjugate()")
    public void testAdjugate()
    {
        Matrix1x1<Length> m = ofSi(2.0, Length.Unit.m);
        Matrix1x1<SIQuantity> adj = m.adjugate();
        assertArrayEquals(new double[] {1.0}, adj.getSiArray(), EPS);
    }

    /**
     * Verify standard 1x1 matrix multiplication.
     */
    @Test
    @DisplayName("matrix x matrix multiplication")
    public void testMatrixMultiply()
    {
        var a1 = Matrix1x1.of(new double[] {1}, Length.Unit.m);
        var b1 = Matrix1x1.of(new double[] {5}, Length.Unit.km);
        Matrix1x1<SIQuantity> c = a1.multiply(b1);
        assertArrayEquals(new double[] {5_000.0}, c.getSiArray(), EPS,
                "result in SI reflects both inputs (mxkm→m^2 in SI numerical terms)");
    }

    /**
     * Verify multiplication with a column vector. Assumes a {@code Vector2.Col} with constructor (double v1, double v2, SIUnit
     * unit) is available.
     */
    @Test
    @DisplayName("matrix x vector (column)")
    public void testMatrixTimesVector()
    {
        Matrix1x1<Length> a11 = ofSi(2.0, Length.Unit.m);

        // Vector [5]^T in km → SI = [5000]
        Vector1<Length> v1 = Vector1.of(5.0, Length.Unit.km);
        Vector1<SIQuantity> r1 = a11.multiply(v1); // [2*5000] = [10k]
        assertEquals(10_000.0, r1.getSiArray()[0], EPS);

        // Vector [5, 6]^T in km → SI = [5000, 6000]
        Vector2.Row<Length> v2 = Vector2.Row.of(5.0, 6.0, Length.Unit.km);
        Vector2.Row<SIQuantity> r2 = a11.multiply(v2); // [2*5000, 2*6000] = [10k, 12k]
        assertEquals(10_000.0, r2.getSiArray()[0], EPS);
        assertEquals(12_000.0, r2.getSiArray()[1], EPS);

        // Vector [5, 6, 7]^T in km → SI = [5000, 6000, 7000]
        Vector3.Row<Length> v3 = Vector3.Row.of(5.0, 6.0, 7.0, Length.Unit.km);
        Vector3.Row<SIQuantity> r3 = a11.multiply(v3); // [2*5000, 2*6000, 2*7000] = [10k, 12k, 14k]
        assertEquals(10_000.0, r3.getSiArray()[0], EPS);
        assertEquals(12_000.0, r3.getSiArray()[1], EPS);
        assertEquals(14_000.0, r3.getSiArray()[2], EPS);

        // Vector [5, 6, 7, 8]^T in km → SI = [5000, 6000, 7000, 8000]
        VectorN.Row<Length> vn = VectorN.Row.of(new double[] {5.0, 6.0, 7.0, 8.0}, Length.Unit.km);
        VectorN.Row<SIQuantity> rn = a11.multiply(vn); // [2*5000, 2*6000, 2*7000] = [10k, 12k, 14k, 16k]
        assertEquals(10_000.0, rn.getSiArray()[0], EPS);
        assertEquals(12_000.0, rn.getSiArray()[1], EPS);
        assertEquals(14_000.0, rn.getSiArray()[2], EPS);
        assertEquals(16_000.0, rn.getSiArray()[3], EPS);
    }

    /**
     * Verify Hadamard element-wise operations: invert, multiply, divide.
     */
    @Test
    @DisplayName("Hadamard: invertElements / multiplyElements / divideElements")
    public void testHadamard()
    {
        var a = Matrix1x1.of(new double[] {2}, Length.Unit.m);
        var b = Matrix1x1.of(new double[] {4}, Length.Unit.km);

        // invert: reciprocal per element
        Matrix1x1<SIQuantity> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5}, inv.getSiArray(), EPS);

        // element-wise multiply: [2*4000]
        Matrix1x1<SIQuantity> mul = a.multiplyEntries(b);
        assertArrayEquals(new double[] {8000.0}, mul.getSiArray(), EPS);

        // element-wise divide: [2/4000]
        Matrix1x1<SIQuantity> div = a.divideEntries(b);
        assertArrayEquals(new double[] {0.0005}, div.getSiArray(), EPS);
    }

    // ------------------------------------------------------------------------------------
    // as(targetUnit): success and failure branches
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix1x1#as(Unit)} succeeds when SI units match (e.g., m ↔ km), and throws when SI units mismatch (e.g.,
     * length ↔ time).
     */
    @Test
    @DisplayName("as(targetUnit) success (m↔km) and failure (length↔time)")
    public void testAs()
    {
        Matrix1x1<Length> mKm = Matrix1x1.of(new double[] {1}, Length.Unit.km);

        // Success path: same SI dimension (Length)
        Matrix1x1<Length> asMeters = mKm.as(Length.Unit.m);
        assertEquals(Length.Unit.m, asMeters.getDisplayUnit(), "display unit switched");
        assertArrayEquals(mKm.getSiArray(), asMeters.getSiArray(), EPS, "SI storage unchanged");

        // Failure path: mismatched SI unit (use SIQuantity/SIUnit with 's' for seconds)
        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> mKm.as(second), "Mismatched SI-unit dimensions must throw");
    }

    // ------------------------------------------------------------------------------------
    // getScalars / getRowScalars / getColumnScalars / getDiagonalScalars
    // getRowVector / getColumnVector / getDiagonalVector (spec-compliant expectations)
    // getRowSi / getColumnSi / getDiagonalSi (spec-compliant expectations)
    // ------------------------------------------------------------------------------------

    /**
     * Verify grid extraction helpers on {@link VectorMatrix}.
     */
    @Test
    @DisplayName("getScalarGrid / getSiGrid")
    public void testGridExtraction()
    {
        Matrix1x1<Length> m = ofSi(4000.0, Length.Unit.km); // in SI

        Length[][] scalars = m.getScalarGrid();
        double[][] sigrid = m.getSiGrid();

        assertEquals(1, scalars.length);
        assertEquals(1, scalars[0].length);
        assertEquals(4.0, scalars[0][0].getInUnit(), EPS);
        assertEquals(4000.0, scalars[0][0].si(), EPS);
        assertEquals(Length.Unit.km, scalars[0][0].getDisplayUnit());

        assertEquals(1, sigrid.length);
        assertEquals(1, sigrid[0].length);
        assertEquals(4000.0, sigrid[0][0], EPS);
    }

    /**
     * Verify scalar array extraction helpers on {@link VectorMatrix}.
     */
    @Test
    @DisplayName("getScalars / getRowScalars / getColumnScalars / getDiagonalScalars")
    public void testScalarExtraction()
    {
        Matrix1x1<Length> m = ofSi(2.0, Length.Unit.m);

        // Row/Column/Diagonal scalar arrays
        Length[] row0 = m.getRowScalars(0);
        Length[] col0 = m.getColumnScalars(0);
        Length[] diag = m.getDiagonalScalars();

        assertEquals(1, row0.length, "row length");
        assertEquals(1, col0.length, "column length");
        assertEquals(1, diag.length, "diagonal length");
        assertEquals(2.0, row0[0].si(), EPS);
        assertEquals(2.0, col0[0].si(), EPS);
        assertEquals(2.0, diag[0].si(), EPS);

        // Row/Column/Diagonal scalar arrays
        Length[] mRow1 = m.mgetRowScalars(1);
        Length[] mCol2 = m.mgetColumnScalars(1);

        assertEquals(1, mRow1.length, "row length");
        assertEquals(1, mCol2.length, "column length");
        assertEquals(2.0, mRow1[0].si(), EPS);
        assertEquals(2.0, mCol2[0].si(), EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(1));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(2));
    }

    /**
     * Verify vector extraction helpers ({@code getRow}, {@code getColumn}, {@code getDiagonal}) behave per spec.
     * <p>
     * This test expects non-null vectors with the correct content.
     */
    @Test
    @DisplayName("getRowVector / getColumnVector / getDiagonalVector return the expected vectors (spec)")
    public void testVectorExtractionSpec()
    {
        Matrix1x1<Length> m = ofSi(2.0, Length.Unit.cm); // different display unit

        // Row/Column/Diagonal Vectors
        Vector1<Length> row0 = m.getRowVector(0);
        Vector1<Length> col1 = m.getColumnVector(0);
        Vector1<Length> diag = m.getDiagonalVector();

        assertEquals(1, row0.size(), "row size");
        assertEquals(1, col1.size(), "column size");
        assertEquals(1, diag.size(), "diagonal size");
        assertEquals(2.0, row0.get(0).si(), EPS);
        assertEquals(2.0, col1.get(0).si(), EPS);
        assertEquals(2.0, diag.get(0).si(), EPS);

        // Row/Column/Diagonal scalar arrays
        Vector1<Length> mRow1 = m.mgetRowVector(1);
        Vector1<Length> mCol2 = m.mgetColumnVector(1);

        assertEquals(1, mRow1.size(), "row length");
        assertEquals(1, mCol2.size(), "column length");
        assertEquals(2.0, mRow1.get(0).si(), EPS);
        assertEquals(2.0, mCol2.get(0).si(), EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(1));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(2));
    }

    /**
     * Verify si array extraction helpers on {@link VectorMatrix}.
     */
    @Test
    @DisplayName("getRowSi / getColumnSi / getDiagonalSi")
    public void testSiArrayExtraction()
    {
        Matrix1x1<Length> m = ofSi(2.0, Length.Unit.m);

        // Row/Column/Diagonal scalar arrays
        double[] row0 = m.getRowSi(0);
        double[] col1 = m.getColumnSi(0);
        double[] diag = m.getDiagonalSi();

        assertEquals(1, row0.length, "row length");
        assertEquals(1, col1.length, "column length");
        assertEquals(1, diag.length, "diagonal length");
        assertEquals(2.0, row0[0], EPS);
        assertEquals(2.0, col1[0], EPS);
        assertEquals(2.0, diag[0], EPS);

        // Row/Column/Diagonal scalar arrays
        double[] mRow1 = m.mgetRowSi(1);
        double[] mCol2 = m.mgetColumnSi(1);

        assertEquals(1, mRow1.length, "row length");
        assertEquals(1, mCol2.length, "column length");
        assertEquals(2.0, mRow1[0], EPS);
        assertEquals(2.0, mCol2[0], EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(1));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(2));
    }

    // ------------------------------------------------------------------------------------
    // equals / hashCode
    // ------------------------------------------------------------------------------------

    /**
     * Verify equality and hashCode semantics across {@link VectorMatrix}, {@link SquareDenseMatrix}, and {@link Matrix1x1}.
     */
    @Test
    @DisplayName("equals / hashCode")
    public void testEqualsHashCode()
    {
        Matrix1x1<Length> a1 = ofSi(1.0, Length.Unit.m);
        Matrix1x1<Length> a2 = ofSi(1.0, Length.Unit.m);
        Matrix1x1<Length> b = ofSi(5.0, Length.Unit.m);

        assertEquals(a1, a1, "reflexive");
        assertEquals(a1, a2, "equal contents");
        assertEquals(a1.hashCode(), a2.hashCode(), "hashCode equal");
        assertNotEquals(a1, b, "different contents");
        assertNotEquals(a1, null, "not equal to null");
        assertNotEquals(a1, "other type", "not equal to other type");
    }

    // ------------------------------------------------------------------------------------
    // Defensive: additional boundary/argument tests where applicable
    // ------------------------------------------------------------------------------------

    /**
     * Ensure index-related methods do not throw for valid bounds and that value retrieval uses display unit.
     */
    @Test
    @DisplayName("Indexing within bounds and value retrieval")
    public void testIndexingAndValues()
    {
        Matrix1x1<Length> m = Matrix1x1.of(new double[] {1.0}, Length.Unit.cm);
        // SI values: [0.01]

        assertDoesNotThrow(() -> m.get(0, 0));
        assertEquals(0.01, m.get(0, 0).si(), EPS);
        assertEquals(0.01, m.get(0, 0).setDisplayUnit(Length.Unit.m).si(), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(0, 1));

        assertDoesNotThrow(() -> m.si(0, 0));
        assertEquals(0.01, m.si(0, 0), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(0, 1));

        assertDoesNotThrow(() -> m.mget(1, 1));
        assertEquals(0.01, m.mget(1, 1).si(), EPS);
        assertEquals(0.01, m.mget(1, 1).setDisplayUnit(Length.Unit.m).si(), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> m.mget(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mget(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mget(2, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mget(1, 2));

        assertDoesNotThrow(() -> m.msi(1, 1));
        assertEquals(0.01, m.msi(1, 1), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> m.msi(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.msi(2, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.msi(1, 2));
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        Matrix1x1<Length> r = Matrix1x1.of(new double[] {8.0}, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        Matrix1x1<Speed> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(4.0, sr.mget(1, 1).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));
    }

    // ------------------------------------------------------------------------------------
    // Static factory methods: Matrix1x1 of() and ofSi() — exhaustive corner cases
    // ------------------------------------------------------------------------------------

    /**
     * Test {@link Matrix1x1#of(double, Unit)}.
     */
    @Test
    @DisplayName("Matrix1x1 of(double, Unit): basic creation and conversion")
    public void testMatrix1x1OfScalar()
    {
        Matrix1x1<Length> m = Matrix1x1.of(2.5, Length.Unit.cm);
        assertArrayEquals(new double[] {0.025}, m.getSiArray(), EPS);
    }

    /**
     * Test {@link Matrix1x1#of(double[], Unit)}.
     */
    @Test
    @DisplayName("Matrix1x1 of(double[], Unit): nulls, size, conversion")
    public void testMatrix1x1OfDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> Matrix1x1.of((double[]) null, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new double[] {}, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new double[] {1, 2}, Length.Unit.m));

        Matrix1x1<Length> m = Matrix1x1.of(new double[] {3}, Length.Unit.km);
        assertArrayEquals(new double[] {3000}, m.getSiArray(), EPS);
    }

    /**
     * Test {@link Matrix1x1#ofSi(double[], Unit)}.
     */
    @Test
    @DisplayName("Matrix1x1 ofSi(double[], Unit): nulls, size, display unit")
    public void testMatrix1x1OfSiDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> Matrix1x1.ofSi((double[]) null, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.ofSi(new double[] {1, 2}, Length.Unit.m));

        Matrix1x1<Length> m = Matrix1x1.ofSi(new double[] {5}, Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
        assertArrayEquals(new double[] {5}, m.getSiArray(), EPS);
    }

    /**
     * Exhaustive test of {@link Matrix1x1#of(double[][], Unit)}.
     */
    @Test
    @DisplayName("Matrix1x1 of(double[][], Unit): exhaustive grid validation")
    public void testMatrix1x1OfDoubleGrid()
    {
        assertThrows(NullPointerException.class, () -> Matrix1x1.of((double[][]) null, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new double[][] {}, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> Matrix1x1.of(new double[][] {null}, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new double[][] {{1, 2}}, Length.Unit.m));

        Matrix1x1<Length> m = Matrix1x1.of(new double[][] {{4}}, Length.Unit.cm);
        assertArrayEquals(new double[] {0.04}, m.getSiArray(), EPS);
    }

    /**
     * Exhaustive test of {@link Matrix1x1#ofSi(double[][], Unit)}.
     */
    @Test
    @DisplayName("Matrix1x1 ofSi(double[][], Unit): exhaustive grid validation")
    public void testMatrix1x1OfSiDoubleGrid()
    {
        assertThrows(NullPointerException.class, () -> Matrix1x1.ofSi((double[][]) null, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.ofSi(new double[][] {}, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> Matrix1x1.ofSi(new double[][] {null}, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.ofSi(new double[][] {{1, 2}}, Length.Unit.m));

        Matrix1x1<Duration> m = Matrix1x1.ofSi(new double[][] {{7}}, Duration.Unit.ms);
        assertEquals(Duration.Unit.ms, m.getDisplayUnit());
        assertArrayEquals(new double[] {7}, m.getSiArray(), EPS);
    }

    /**
     * Test {@link Matrix1x1#of(Quantity)}.
     */
    @Test
    @DisplayName("Matrix1x1 of(Q): null and conversion")
    public void testMatrix1x1OfQuantity()
    {
        assertThrows(NullPointerException.class, () -> Matrix1x1.of((Length) null));

        Matrix1x1<Length> m = Matrix1x1.of(new Length(2.0, Length.Unit.cm));
        assertArrayEquals(new double[] {0.02}, m.getSiArray(), EPS);
    }

    /**
     * Test {@link Matrix1x1#of(Quantity[])}.
     */
    @Test
    @DisplayName("Matrix1x1 of(Q[]): nulls, size, conversion")
    public void testMatrix1x1OfQuantityArray()
    {
        assertThrows(NullPointerException.class, () -> Matrix1x1.of((Length[]) null));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new Length[] {}));

        assertThrows(NullPointerException.class, () -> Matrix1x1.of(new Length[] {null}));

        Matrix1x1<Length> m = Matrix1x1.of(new Length[] {new Length(3, Length.Unit.km)});
        assertArrayEquals(new double[] {3000}, m.getSiArray(), EPS);
    }

    /**
     * Exhaustive test of {@link Matrix1x1#of(Quantity[][])}.
     */
    @Test
    @DisplayName("Matrix1x1 of(Q[][]): exhaustive grid validation")
    public void testMatrix1x1OfQuantityGrid()
    {
        assertThrows(NullPointerException.class, () -> Matrix1x1.of((Length[][]) null));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new Length[][] {}));

        assertThrows(NullPointerException.class, () -> Matrix1x1.of(new Length[][] {null}));

        assertThrows(IllegalArgumentException.class, () -> Matrix1x1.of(new Length[][] {{Length.ofSi(1), Length.ofSi(2)}}));

        assertThrows(NullPointerException.class, () -> Matrix1x1.of(new Length[][] {{null}}));

        Matrix1x1<Length> m = Matrix1x1.of(new Length[][] {{new Length(5, Length.Unit.cm)}});
        assertArrayEquals(new double[] {0.05}, m.getSiArray(), EPS);
    }

    /**
     * Test as() functions.
     */
    @Test
    @DisplayName("Matrix1x1 as() functions test")
    public void testAsFunctions()
    {
        var m = ofSi(2000.0, Length.Unit.km);

        var m1 = m.asMatrix1x1();
        assertArrayEquals(m.getSiArray(), m1.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, m1.getDisplayUnit());
        assertEquals(2000.0, m1.si(0, 0), 1E-10);

        var v1 = m.asVector1();
        assertArrayEquals(m.getSiArray(), v1.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, v1.getDisplayUnit());
        assertEquals(2000.0, v1.si(0), 1E-10);
        
        var mNxN = m.asMatrixNxN();
        assertArrayEquals(m.getSiArray(), mNxN.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, mNxN.getDisplayUnit());
        assertEquals(2000.0, mNxN.si(0, 0), 1E-10);
        
        var mNxM = m.asMatrixNxM();
        assertArrayEquals(m.getSiArray(), mNxM.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, mNxM.getDisplayUnit());
        assertEquals(2000.0, mNxM.si(0, 0), 1E-10);
        
        var mQT = m.asQuantityTable();
        assertArrayEquals(m.getSiArray(), mQT.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, mQT.getDisplayUnit());
        assertEquals(2000.0, mQT.si(0, 0), 1E-10);
        
        var vNcol = m.asVectorNCol();
        assertArrayEquals(m.getSiArray(), vNcol.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, vNcol.getDisplayUnit());
        assertEquals(2000.0, vNcol.si(0), 1E-10);
        
        var vNrow = m.asVectorNRow();
        assertArrayEquals(m.getSiArray(), vNrow.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, vNrow.getDisplayUnit());
        assertEquals(2000.0, vNrow.si(0), 1E-10);
        
        assertThrows(IllegalStateException.class, () -> m.asMatrix2x2());
        assertThrows(IllegalStateException.class, () -> m.asMatrix3x3());
        assertThrows(IllegalStateException.class, () -> m.asVector2Col());
        assertThrows(IllegalStateException.class, () -> m.asVector2Row());
        assertThrows(IllegalStateException.class, () -> m.asVector3Col());
        assertThrows(IllegalStateException.class, () -> m.asVector3Row());
    }

}
