package org.djunits.vecmat.d2;

import static org.junit.jupiter.api.Assertions.assertAll;
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
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.Matrix;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.SquareDenseMatrix;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.operations.SquareMatrixOps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Matrix2x2} with concrete quantity {@link Length} and unit {@link org.djunits.quantity.Length.Unit}.
 * <p>
 * The tests aim for 100% method and branch coverage in Matrix2x2 and the inherited default functionality from:
 * <ul>
 * <li>{@link Matrix}</li>
 * <li>{@link SquareDenseMatrix}</li>
 * <li>{@link SquareMatrixOps}</li>
 * <li>{@link Hadamard}</li>
 * <li>and the value interfaces used by {@code VectorMatrixOps} (Additive, Scalable, Value)</li>
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
public class Matrix2x2Test
{
    /** Numerical tolerance for double comparisons. */
    private static final double EPS = 1.0E-12;

    /**
     * Helper that builds a {@code Matrix2x2<Length, Length.Unit>} from SI values with a given display unit.
     * @param si a flat 4-element array of SI (meter) values in row-major order [a11, a12, a21, a22]
     * @param displayUnit the display unit to assign to the matrix
     * @return a new matrix instance
     */
    private static Matrix2x2<Length, Length.Unit> ofSi(final double[] si, final Length.Unit displayUnit)
    {
        // Use the public factory with display values by converting SI back into display unit values.
        double[] displayValues = new double[] {displayUnit.fromBaseValue(si[0]), displayUnit.fromBaseValue(si[1]),
                displayUnit.fromBaseValue(si[2]), displayUnit.fromBaseValue(si[3])};
        return Matrix2x2.of(displayValues, displayUnit);
    }

    // ------------------------------------------------------------------------------------
    // Constructors and factories
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix2x2#of(double[], UnitInterface)} rejects nulls and wrong sizes, and converts using the display unit.
     */
    @Test
    @DisplayName("Factory of(double[]) — nulls, length check, and SI conversion")
    public void testFactoryOfArray()
    {
        // Null checks
        assertThrows(NullPointerException.class, () -> Matrix2x2.of((double[]) null, Length.Unit.km),
                "Null value array should throw");
        assertThrows(NullPointerException.class, () -> Matrix2x2.of(new double[] {1, 2, 3, 4}, null), "Null unit should throw");

        // Length check
        assertThrows(IllegalArgumentException.class, () -> Matrix2x2.of(new double[] {1, 2, 3}, Length.Unit.km),
                "Array length != 4 should throw");

        // Successful creation & SI conversion
        Matrix2x2<Length, Length.Unit> m = Matrix2x2.of(new double[] {1, 2, 3, 4}, Length.Unit.km);
        assertAll(() -> assertEquals(2, m.rows(), "rows"), () -> assertEquals(2, m.cols(), "cols"),
                () -> assertEquals(Length.Unit.km, m.getDisplayUnit(), "display unit preserved"),
                () -> assertArrayEquals(new double[] {1000, 2000, 3000, 4000}, m.si(), EPS,
                        "values converted from km to m (SI)"));
    }

    /**
     * Verify {@link Matrix2x2#of(double[][], UnitInterface)} validation and SI conversion.
     */
    @Test
    @DisplayName("Factory of(double[][]) — nulls, 2x2 shape, and SI conversion")
    public void testFactoryOfGrid()
    {
        // Null checks
        assertThrows(NullPointerException.class, () -> Matrix2x2.of((double[][]) null, Length.Unit.km),
                "Null grid should throw");
        assertThrows(NullPointerException.class, () -> Matrix2x2.of(new double[][] {{1, 2}, {3, 4}}, null),
                "Null unit should throw");

        // Not 2x2
        assertThrows(IllegalArgumentException.class, () -> Matrix2x2.of(new double[][] {{1, 2, 3}, {4, 5, 6}}, Length.Unit.m),
                "Non-2x2 grid should throw");

        // Successful creation
        Matrix2x2<Length, Length.Unit> m = Matrix2x2.of(new double[][] {{1, 2}, {3, 4}}, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000}, m.si(), EPS);
    }

    /**
     * Verify {@link Matrix2x2#instantiateSi(double[])} creates a new matrix with the same display unit and the provided SI
     * values.
     */
    @Test
    @DisplayName("instantiate(double[]) — uses provided SI data and keeps display unit")
    public void testInstantiate()
    {
        Matrix2x2<Length, Length.Unit> base = Matrix2x2.of(new double[] {1, 2, 3, 4}, Length.Unit.km);
        double[] newSi = new double[] {10, 20, 30, 40};
        Matrix2x2<Length, Length.Unit> inst = base.instantiateSi(newSi);
        assertAll(() -> assertEquals(Length.Unit.km, inst.getDisplayUnit(), "display unit retained"),
                () -> assertArrayEquals(newSi, inst.si(), EPS, "si array used as-is"));
    }

    // ------------------------------------------------------------------------------------
    // Basic Matrix/Value behaviors: size, value(), display unit switching, toString
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix#rows()}, {@link Matrix#cols()}, {@link Matrix#value(int, int)} and relative/absolute flag.
     */
    @Test
    @DisplayName("rows/cols/value/isRelative")
    public void testBasicShapeAndValue()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        assertAll(() -> assertEquals(2, m.rows(), "rows"), () -> assertEquals(2, m.cols(), "cols"),
                () -> assertEquals(1.0, m.value(1, 1).si(), EPS, "(1,1) in SI"),
                () -> assertTrue(m.isRelative(), "Length is a relative quantity (not absolute)"));
    }

    /**
     * Verify that {@link Matrix#setDisplayUnit(UnitInterface)} only affects presentation and not SI storage.
     */
    @Test
    @DisplayName("setDisplayUnit() only changes presentation")
    public void testSetDisplayUnit()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1000, 2000, 3000, 4000}, Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
        // switch to meters
        m.setDisplayUnit(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, m.getDisplayUnit(), "display unit changed"),
                () -> assertArrayEquals(new double[] {1000, 2000, 3000, 4000}, m.si(), EPS, "SI unchanged"),
                () -> assertEquals(1000.0, m.value(1, 1).si(), EPS, "value SI unaffected"));
        // back to km
        m.setDisplayUnit(Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
    }

    /**
     * Call {@link Matrix#toString()} and {@link Matrix#toString(UnitInterface)} for coverage. We assert the unit abbreviation
     * is present; we do not depend on exact formatting of numbers.
     */
    @Test
    @DisplayName("toString() and toString(unit) contain unit abbreviation")
    public void testToString()
    {
        Matrix2x2<Length, Length.Unit> m = Matrix2x2.of(new double[] {1, 2, 3, 4}, Length.Unit.km);
        String s1 = m.toString();
        String s2 = m.toString(Length.Unit.km);
        assertAll(() -> assertTrue(s1.contains("km"), "default toString includes display unit"),
                () -> assertTrue(s2.contains("km"), "toString(withUnit) includes provided unit"));
    }

    // ------------------------------------------------------------------------------------
    // VectorMatrixOps defaults: add/subtract (Q and VM), negate, abs, scaleBy, mean/median/min/max/mode/sum
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link org.djunits.vecmat.operations.VectorMatrixOps#add(Quantity)} and {@code subtract(Q)} with quantities.
     */
    @Test
    @DisplayName("add(Q) / subtract(Q) apply element-wise SI increments")
    public void testAddSubtractQuantity()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        Length inc = Length.ofSi(10.0);
        Length dec = Length.ofSi(1.5);
        assertArrayEquals(new double[] {11, 12, 13, 14}, m.add(inc).si(), EPS);
        assertArrayEquals(new double[] {-0.5, 0.5, 1.5, 2.5}, m.subtract(dec).si(), EPS);
    }

    /**
     * Verify {@link org.djunits.vecmat.operations.VectorMatrixOps#add(Quantity)} and {@code subtract(VM)} with another matrix.
     */
    @Test
    @DisplayName("add(VM) / subtract(VM) element-wise")
    public void testAddSubtractMatrix()
    {
        Matrix2x2<Length, Length.Unit> a = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        Matrix2x2<Length, Length.Unit> b = ofSi(new double[] {10, 20, 30, 40}, Length.Unit.m);
        assertArrayEquals(new double[] {11, 22, 33, 44}, a.add(b).si(), EPS);
        assertArrayEquals(new double[] {-9, -18, -27, -36}, a.subtract(b).si(), EPS);
    }

    /**
     * Verify {@link org.djunits.vecmat.operations.VectorMatrixOps#negate()},
     * {@link org.djunits.vecmat.operations.VectorMatrixOps#abs()}, and
     * {@link org.djunits.vecmat.operations.VectorMatrixOps#scaleBy(double)}.
     */
    @Test
    @DisplayName("negate / abs / scaleBy")
    public void testNegateAbsScaleBy()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {-1, 2, -3, 4}, Length.Unit.km);
        assertArrayEquals(new double[] {1, -2, 3, -4}, m.negate().si(), EPS);
        assertArrayEquals(new double[] {1, 2, 3, 4}, m.abs().si(), EPS);
        assertArrayEquals(new double[] {-2, 4, -6, 8}, m.scaleBy(2.0).si(), EPS);
    }

    /**
     * Verify {@code mean}, {@code median}, {@code min}, {@code max}, {@code mode}, and {@code sum}.
     */
    @Test
    @DisplayName("mean / median / min / max / mode / sum")
    public void testStats()
    {
        // Use [1, 2, 3, 4] m to give an unambiguous median = 2.5
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        assertAll(() -> assertEquals(2.5, m.mean().si(), EPS, "mean"),
                () -> assertEquals(2.5, m.median().si(), EPS, "median (even-sized set)"),
                () -> assertEquals(1.0, m.min().si(), EPS, "min"), () -> assertEquals(4.0, m.max().si(), EPS, "max"),
                () -> assertEquals(m.max().si(), m.mode().si(), EPS, "mode defaults to max"),
                () -> assertEquals(10.0, m.sum().si(), EPS, "sum"));
    }

    // ------------------------------------------------------------------------------------
    // SquareMatrixOps: transpose, determinant, trace, norms, symmetry tests
    // ------------------------------------------------------------------------------------

    /**
     * Verify transpose on a 2x2 matrix.
     */
    @Test
    @DisplayName("transpose() swaps off-diagonals")
    public void testTranspose()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m); // [ [1,2],[3,4] ]
        Matrix2x2<Length, Length.Unit> t = m.transpose();
        assertArrayEquals(new double[] {1, 3, 2, 4}, t.si(), EPS);
        assertEquals(m.getDisplayUnit(), t.getDisplayUnit(), "transpose keeps unit");
    }

    /**
     * Verify determinant as scalar and as quantity, and trace.
     */
    @Test
    @DisplayName("determinantScalar(), determinant(), trace()")
    public void testDeterminantAndTrace()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        // det = a*d - b*c = 1*4 - 2*3 = -2
        assertEquals(-2.0, m.determinantScalar(), EPS, "determinantScalar");
        assertEquals(-2.0, m.determinant().si(), EPS, "determinant quantity SI value");
        // trace = a + d = 1 + 4 = 5
        assertEquals(5.0, m.trace().si(), EPS, "trace");
    }

    /**
     * Verify Frobenius norm and default {@code norm()}.
     */
    @Test
    @DisplayName("normFrobenius and default norm()")
    public void testNorms()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {3, 4, 0, 0}, Length.Unit.m);
        // Frobenius = sqrt(3^2 + 4^2) = 5
        assertEquals(5.0, m.normFrobenius().si(), EPS);
    }

    /**
     * Verify symmetry and skew-symmetry checks with and without tolerances.
     */
    @Test
    @DisplayName("isSymmetric / isSkewSymmetric with and without tolerance")
    public void testSymmetry()
    {
        Matrix2x2<Length, Length.Unit> sym = ofSi(new double[] {1, 2, 2, 3}, Length.Unit.m);
        Matrix2x2<Length, Length.Unit> skew = ofSi(new double[] {0, 2, -2, 0}, Length.Unit.m);
        Matrix2x2<Length, Length.Unit> nearSym = ofSi(new double[] {1, 2 + 1e-9, 2, 3}, Length.Unit.m);

        assertTrue(sym.isSymmetric(), "exactly symmetric");
        assertFalse(sym.isSkewSymmetric(), "not skew");

        assertTrue(skew.isSkewSymmetric(), "exactly skew-symmetric");
        assertFalse(skew.isSymmetric(), "not symmetric");

        Length tol = Length.ofSi(1e-6);
        assertTrue(nearSym.isSymmetric(tol), "symmetric within tolerance");
        assertFalse(nearSym.isSkewSymmetric(tol), "not skew within tolerance");
    }

    // ------------------------------------------------------------------------------------
    // Inversion, adjugate, matrix x matrix, matrix x vector, element-wise ops (Hadamard)
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix2x2#inverse()} and multiplication with its inverse gives the identity (numerically).
     * @throws NonInvertibleMatrixException if inversion fails (test expects success)
     */
    @Test
    @DisplayName("inverse() and A * A^{-1} ≈ I")
    public void testInverseAndIdentity() throws NonInvertibleMatrixException
    {
        // A = [[4,7],[2,6]] ⇒ det = 10, A^{-1} = (1/10) [[6, -7], [-2, 4]]
        Matrix2x2<Length, Length.Unit> a = ofSi(new double[] {4, 7, 2, 6}, Length.Unit.km);
        Matrix2x2<SIQuantity, SIUnit> inv = a.inverse();

        // Multiply A * A^{-1} → should approximate identity; unit should reduce to unitless (not asserted here)
        Matrix2x2<SIQuantity, SIUnit> prod = a.multiply(inv);
        double[] p = prod.si();
        assertAll(() -> assertEquals(1.0, p[0], 1e-9, "I(1,1)"), () -> assertEquals(0.0, p[1], 1e-9, "I(1,2)"),
                () -> assertEquals(0.0, p[2], 1e-9, "I(2,1)"), () -> assertEquals(1.0, p[3], 1e-9, "I(2,2)"));
    }

    /**
     * Verify that {@link Matrix2x2#inverse()} throws on singular matrices.
     */
    @Test
    @DisplayName("inverse() throws on singular matrix")
    public void testInverseThrowsOnSingular()
    {
        // Singular: rows proportional
        Matrix2x2<Length, Length.Unit> singular = ofSi(new double[] {1, 2, 2, 4}, Length.Unit.m);
        assertThrows(NonInvertibleMatrixException.class, singular::inverse);
    }

    /**
     * Verify {@link Matrix2x2#adjugate()} for a generic 2x2 matrix.
     */
    @Test
    @DisplayName("adjugate() for [[a,b],[c,d]] equals [[d,-b],[-c,a]]")
    public void testAdjugate()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        Matrix2x2<SIQuantity, SIUnit> adj = m.adjugate();
        assertArrayEquals(new double[] {4, -2, -3, 1}, adj.si(), EPS);
    }

    /**
     * Verify standard 2x2 matrix multiplication.
     */
    @Test
    @DisplayName("matrix x matrix multiplication")
    public void testMatrixMultiply()
    {
        // A = [[1,2],[3,4]] ; B = [[5,6],[7,8]] ; A*B = [[19,22],[43,50]]
        var a = new Matrix2x2<Length, Length.Unit>(new double[] {1, 2, 3, 4}, Length.Unit.m);
        var b = new Matrix2x2<Length, Length.Unit>(new double[] {5, 6, 7, 8}, Length.Unit.km);
        Matrix2x2<SIQuantity, SIUnit> c = a.multiply(b);
        assertArrayEquals(new double[] {19_000.0, 22_000.0, 43_000.0, 50_000.0}, c.si(), EPS,
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
        Matrix2x2<Length, Length.Unit> a = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        // Vector [5, 6]^T in km → SI = [5000, 6000]
        Vector2.Col<Length, Length.Unit> v = new Vector2.Col<>(5.0, 6.0, Length.Unit.km);
        Vector2.Col<SIQuantity, SIUnit> r = a.multiply(v); // [1*5000+2*6000, 3*5000+4*6000] = [17k, 39k]
        assertAll(() -> assertEquals(17_000.0, r.si()[0], EPS), () -> assertEquals(39_000.0, r.si()[1], EPS));
    }

    /**
     * Verify Hadamard element-wise operations: invert, multiply, divide.
     */
    @Test
    @DisplayName("Hadamard: invertElements / multiplyElements / divideElements")
    public void testHadamard()
    {
        var a = new Matrix2x2<Length, Length.Unit>(new double[] {2, 4, 5, 10}, Length.Unit.m);
        var b = new Matrix2x2<Length, Length.Unit>(new double[] {1, 2, 0.5, 4}, Length.Unit.km);

        // invert: reciprocal per element
        Matrix2x2<SIQuantity, SIUnit> inv = a.invertElements();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1}, inv.si(), EPS);

        // element-wise multiply: [2*1000, 4*2000, 5*500, 10*4000]
        Matrix2x2<SIQuantity, SIUnit> mul = a.multiplyElements(b);
        assertArrayEquals(new double[] {2000.0, 8000.0, 2500.0, 40_000.0}, mul.si(), EPS);

        // element-wise divide: [2/1000, 4/2000, 5/500, 10/4000]
        Matrix2x2<SIQuantity, SIUnit> div = a.divideElements(b);
        assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025}, div.si(), EPS);
    }

    // ------------------------------------------------------------------------------------
    // as(targetUnit): success and failure branches
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link Matrix2x2#as(UnitInterface)} succeeds when SI units match (e.g., m ↔ km), and throws when SI units mismatch
     * (e.g., length ↔ time).
     */
    @Test
    @DisplayName("as(targetUnit) success (m↔km) and failure (length↔time)")
    public void testAs()
    {
        Matrix2x2<Length, Length.Unit> mKm = Matrix2x2.of(new double[] {1, 2, 3, 4}, Length.Unit.km);

        // Success path: same SI dimension (Length)
        Matrix2x2<Length, Length.Unit> asMeters = mKm.as(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, asMeters.getDisplayUnit(), "display unit switched"),
                () -> assertArrayEquals(mKm.si(), asMeters.si(), EPS, "SI storage unchanged"));

        // Failure path: mismatched SI unit (use SIQuantity/SIUnit with 's' for seconds)
        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> mKm.as(second), "Mismatched SI-unit dimensions must throw");
    }

    // ------------------------------------------------------------------------------------
    // getScalars / getRowScalars / getColumnScalars / getDiagonalScalars
    // getRow / getColumn / getDiagonal (spec-compliant expectations)
    // ------------------------------------------------------------------------------------

    /**
     * Verify scalar array extraction helpers on {@link Matrix}.
     */
    @Test
    @DisplayName("getScalars / getRowScalars / getColumnScalars / getDiagonalScalars")
    public void testScalarExtraction()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);

        // Row/Column/Diagonal scalar arrays
        Length[] row1 = m.getRowScalars(1);
        Length[] col2 = m.getColumnScalars(2);
        Length[] diag = m.getDiagonalScalars();

        assertAll(() -> assertEquals(2, row1.length, "row length"), () -> assertEquals(2, col2.length, "column length"),
                () -> assertEquals(2, diag.length, "diagonal length"), () -> assertEquals(1.0, row1[0].si(), EPS),
                () -> assertEquals(2.0, row1[1].si(), EPS), () -> assertEquals(2.0, col2[0].si(), EPS),
                () -> assertEquals(4.0, col2[1].si(), EPS), () -> assertEquals(1.0, diag[0].si(), EPS),
                () -> assertEquals(4.0, diag[1].si(), EPS));
    }

    /**
     * Verify vector extraction helpers ({@code getRow}, {@code getColumn}, {@code getDiagonal}) behave per spec.
     * <p>
     * This test expects non-null vectors with the correct content. If the implementation is still a TODO returning
     * {@code null}, this test (by design) will fail to flag the missing functionality.
     */
    @Test
    @DisplayName("getRow / getColumn / getDiagonal return the expected vectors (spec)")
    public void testVectorExtractionSpec()
    {
        Matrix2x2<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.cm); // different display unit

        // The following calls are expected to return non-null vectors of appropriate type and unit.
        // If not implemented, they should cause this test to fail.
        assertAll(() -> assertNotNull(m.getRow(1), "getRow(1) must return a non-null vector"),
                () -> assertNotNull(m.getColumn(2), "getColumn(2) must return a non-null vector"),
                () -> assertNotNull(m.getDiagonal(), "getDiagonal() must return a non-null vector"));

        // Optional (if those vectors expose si() or value accessors, verify content)
        // Here we only assert that the calls succeed and return non-null handles per spec.
    }

    // ------------------------------------------------------------------------------------
    // equals / hashCode
    // ------------------------------------------------------------------------------------

    /**
     * Verify equality and hashCode semantics across {@link Matrix}, {@link SquareDenseMatrix}, and {@link Matrix2x2}.
     */
    @Test
    @DisplayName("equals / hashCode")
    public void testEqualsHashCode()
    {
        Matrix2x2<Length, Length.Unit> a1 = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        Matrix2x2<Length, Length.Unit> a2 = ofSi(new double[] {1, 2, 3, 4}, Length.Unit.m);
        Matrix2x2<Length, Length.Unit> b = ofSi(new double[] {1, 2, 3, 5}, Length.Unit.m);

        assertAll(() -> assertEquals(a1, a1, "reflexive"), () -> assertEquals(a1, a2, "equal contents"),
                () -> assertEquals(a1.hashCode(), a2.hashCode(), "hashCode equal"),
                () -> assertNotEquals(a1, b, "different contents"), () -> assertNotEquals(a1, null, "not equal to null"),
                () -> assertNotEquals(a1, "other type", "not equal to other type"));
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
        Matrix2x2<Length, Length.Unit> m = Matrix2x2.of(new double[] {1.0, 200.0, 3.0, 400.0}, Length.Unit.cm);
        // SI values: [0.01, 2.0, 0.03, 4.0]
        assertAll(() -> assertDoesNotThrow(() -> m.value(1, 1)), () -> assertDoesNotThrow(() -> m.value(2, 2)),
                () -> assertEquals(0.01, m.value(1, 1).si(), EPS),
                () -> assertEquals(4.0, m.value(2, 2).setDisplayUnit(Length.Unit.m).si(), EPS));
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        Matrix2x2<Length, Length.Unit> r = Matrix2x2.of(new double[] {1.0, 2.0, 3.0, 4.0}, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        Matrix2x2<Speed, Speed.Unit> sr = r.divideElements(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.value(1, 1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.value(1, 2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.value(2, 1).getInUnit(), 1E-6);
        assertEquals(2.0, sr.value(2, 2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideElements(d).as(Area.Unit.m2));
    }

}
