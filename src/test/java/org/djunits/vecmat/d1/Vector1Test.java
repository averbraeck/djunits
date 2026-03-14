package org.djunits.vecmat.d1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.djunits.quantity.Area;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.Speed;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.def.Vector;
import org.djunits.vecmat.def.VectorMatrix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Vector1} (both {@link Vector1} and {@link Vector1}) with concrete quantity {@link Length} and unit
 * {@link org.djunits.quantity.Length.Unit}. The tests encode the <em>correct behavior</em> and are intended to fail when the
 * implementation contains defects (e.g., wrong L1 norm, incorrect Hadamard unit composition).
 * <p>
 * Coverage goals:
 * <ul>
 * <li>Constructors, factories, and SI conversion semantics</li>
 * <li>Accessors: x(), y(), xSi(), ySi(), si(), get(index)</li>
 * <li>Iterator and getScalarArray()</li>
 * <li>VectorMatrixOps defaults as exercised/overridden by Vector1</li>
 * <li>Normed: norm(), normL1(), normL2(), normLp(p), normLinf()</li>
 * <li>Orientation &amp; shape: rows(), cols(), isColumnVector()</li>
 * <li>Transposition Row&lt;&rarr;&gt;Col</li>
 * <li>Hadamard operations (invertElements, multiplyElements, divideElements)</li>
 * <li>Linear algebra products: Row⋅Col, Col⋅Row, Row⋅Matrix2x2</li>
 * <li>as(targetUnit) positive and negative branches</li>
 * <li>equals, hashCode, toString</li>
 * </ul>
 * <p>
 * <strong>Important:</strong> Values passed to the {@code Vector1} constructors are interpreted in the provided display unit
 * and converted to SI for internal storage. The tests validate that convention. Copyright (c) 2025-2026 Delft University of
 * Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See for project information
 * <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is distributed under a
 * <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class Vector1Test
{
    /** Numerical tolerance for double comparisons. */
    private static final double EPS = 1.0E-12;

    // -----------------------------------------------------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Create a Length-typed column / row vector using the given display values and unit.
     * @param xInUnit x value, expressed in the given unit
     * @param unit the display unit
     * @return a Vector1&lt;Length, Length.Unit&gt;
     */
    private static Vector1<Length, Length.Unit> vec(final double xInUnit, final Length.Unit unit)
    {
        return new Vector1<>(xInUnit, unit);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors, factories, and SI conversion
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify that constructor interprets values in the provided display unit and converts to SI storage.
     */
    @Test
    @DisplayName("Constructor conversion: display unit → SI storage")
    public void testConstructorAndSiStorage()
    {
        // 5 km → SI = 5000 m for vec
        Vector1<Length, Length.Unit> v = vec(5.0, Length.Unit.km);
        assertArrayEquals(new double[] {5000.0}, v.si(), EPS);
        assertEquals(Length.Unit.km, v.getDisplayUnit(), "display unit preserved");

        // Factory .of for vec
        Vector1<Length, Length.Unit> cf = Vector1.of(1.2, Length.Unit.m);
        assertArrayEquals(new double[] {1.2}, cf.si(), EPS);
    }

    /**
     * Verify {@link Vector1#instantiateSi(double[])} delegates to the (xSi) variant and enforces length=1.
     */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=1 and delegates")
    public void testInstantiateSiArray()
    {
        Vector1<Length, Length.Unit> v1 = vec(1.0, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> v1.instantiateSi(new double[] {1.0, 2.0}),
                "Wrong length must throw");
        Vector1<Length, Length.Unit> r2 = v1.instantiateSi(new double[] {20.0});
        assertArrayEquals(new double[] {20.0}, r2.si(), EPS);
        assertEquals(v1.getDisplayUnit(), r2.getDisplayUnit(), "display unit copied by instantiate path");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Accessors, indexing, iterator, scalar array
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify size, x() quantities, xSi(), si() copy semantics, and get(index) branches.
     */
    @Test
    @DisplayName("size, x/y (Q), xSi, si() copy, and get(index)")
    public void testAccessorsAndIndexing()
    {
        Vector1<Length, Length.Unit> v = vec(0.5, Length.Unit.cm); // SI: 0.005
        assertEquals(1, v.size(), "size is 1");
        assertEquals(0.005, v.xSi(), EPS);
        assertEquals(0.5, v.x().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS, "x() returns Length with display unit");

        // si() must return a fresh copy (mutating the returned array must not affect the vector)
        double[] siCopy = v.si();
        siCopy[0] = 12345.0;
        assertEquals(0.005, v.xSi(), EPS, "internal storage not affected by external array mutation");

        // get(index) with valid and invalid indices
        assertEquals(v.x().si(), v.get(0).si(), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(1));

        // mget(index) with valid and invalid indices
        assertEquals(v.x().si(), v.mget(1).si(), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.mget(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mget(2));

        // si(index) with valid and invalid indices
        assertEquals(v.x().si(), v.si(0), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(1));

        // msi(index) with valid and invalid indices
        assertEquals(v.x().si(), v.msi(1), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(2));
    }

    /**
     * Verify the iterator over Q values yields x in the vector's display unit and is consistent with get().
     */
    @Test
    @DisplayName("iterator() yields Q in display unit, in order x")
    public void testIterator()
    {
        Vector1<Length, Length.Unit> v = vec(2.0, Length.Unit.km); // SI: 1000, 2000
        Iterator<Length> itCol = v.iterator();
        assertTrue(itCol.hasNext());
        Length firstCol = itCol.next();
        assertFalse(itCol.hasNext());
        assertThrows(NoSuchElementException.class, itCol::next);
        assertEquals(2.0, firstCol.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(v.get(0).si(), firstCol.si(), EPS);
    }

    /**
     * Verify {@link Vector#getScalarArray()} returns a Q[] (not Object[]) with the correct order and values.
     */
    @Test
    @DisplayName("getScalarArray() returns Q[] with x")
    public void testGetScalarArray()
    {
        Vector1<Length, Length.Unit> v = vec(3.0, Length.Unit.m);
        Length[] arrRow = v.getScalarArray();
        assertEquals(1, arrRow.length);
        assertInstanceOf(Length.class, arrRow[0]);
        assertEquals(3.0, arrRow[0].si(), EPS);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Orientation & shape; transposition
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify rows(), cols(), and isColumnVector() for both Row and Col; verify transpose preserves SI values and unit.
     */
    @Test
    @DisplayName("rows/cols/isColumnVector and transpose()")
    public void testShapeAndTranspose()
    {
        Vector1<Length, Length.Unit> v = vec(2.0, Length.Unit.m);
        assertEquals(1, v.rows());
        assertEquals(1, v.cols());
        assertTrue(v.isColumnVector());

        Vector1<Length, Length.Unit> vt = v.transpose();
        assertArrayEquals(v.si(), vt.si(), EPS, "Transpose preserves SI");
        assertEquals(v.getDisplayUnit(), vt.getDisplayUnit());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // VectorMatrixOps defaults exercised via Vector1 overrides
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify add(Q), subtract(Q), add(V), subtract(V), negate(), abs(), scaleBy(double) for Vector1.
     */
    @Test
    @DisplayName("VectorMatrixOps: add/subtract (Q & V), negate, abs, scaleBy")
    public void testBasicAlgebraRow()
    {
        Vector1<Length, Length.Unit> a = vec(1.0, Length.Unit.m); // SI: 1
        Vector1<Length, Length.Unit> b = vec(0.5, Length.Unit.m); // SI: 0.5
        Length inc = Length.of(2.0, "m"); // +2 m

        // V ± V
        assertArrayEquals(new double[] {1.5}, a.add(b).si(), EPS);
        assertArrayEquals(new double[] {0.5}, a.subtract(b).si(), EPS);

        // ± Q
        assertArrayEquals(new double[] {3.0}, a.add(inc).si(), EPS);
        assertArrayEquals(new double[] {-1.0}, a.subtract(inc).si(), EPS);

        // negate, abs, scaleBy
        assertArrayEquals(new double[] {-1.0}, a.negate().si(), EPS);
        assertArrayEquals(new double[] {1.0}, a.abs().si(), EPS);
        assertArrayEquals(new double[] {2.0}, a.scaleBy(2.0).si(), EPS);
    }

    /**
     * Verify statistics: min, max, mean, median, mode, sum.
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/mode/sum")
    public void testStatistics()
    {
        Vector1<Length, Length.Unit> v = vec(3.0, Length.Unit.m);
        assertEquals(3.0, v.min().si(), EPS);
        assertEquals(3.0, v.max().si(), EPS);
        assertEquals(3.0, v.mean().si(), EPS);
        assertEquals(3.0, v.median().si(), EPS, "median of 2-point set = mean");
        assertEquals(3.0, v.mode().si(), EPS, "mode defaults to max");
        assertEquals(3.0, v.sum().si(), EPS);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Normed
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify {@code norm()} equals {@code normL2()} and norms compute correct quantities.
     * <p>
     * <strong>Note:</strong> This test assumes correct L1 definition: |x| + |y|. If the implementation divides by 2, this test
     * will fail (as intended).
     */
    @Test
    @DisplayName("Normed: norm(), normL1(), normL2(), normLp(p), normLinf()")
    public void testNorms()
    {
        Vector1<Length, Length.Unit> v = vec(-4.0, Length.Unit.m);
        assertEquals(v.normL2().si(), v.norm().si(), EPS, "norm() delegates to normL2()");
        assertEquals(4.0, v.normL1().si(), EPS, "L1 = |4| = 4");
        assertEquals(4.0, v.normL2().si(), EPS);
        assertEquals(4.0, v.normLp(3).si(), EPS);
        assertEquals(4.0, v.normLinf().si(), EPS);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Hadamard operations (invert, elementwise multiply/divide)
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify Hadamard element-wise operations and correct unit composition. If the implementation derives the unit from
     * x().siUnit() instead of the vector's display unit, the test will fail (by design) for Column vector.
     */
    @Test
    @DisplayName("Hadamard: invertElements / multiplyElements / divideElements + unit composition, Col")
    public void testHadamardCol()
    {
        Vector1<Length, Length.Unit> a = vec(2.0, Length.Unit.m); // SI: 2
        Vector1<Length, Length.Unit> b = vec(2.0, Length.Unit.km); // SI: 2000

        // invert
        Vector1<SIQuantity, SIUnit> inv = a.invertElements();
        assertArrayEquals(new double[] {0.5}, inv.si(), EPS);
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit should be inverted");

        // elementwise multiply → SI: [2*2000] = [4000]; unit: m ⊕ km
        Vector1<SIQuantity, SIUnit> mul = a.multiplyElements(b);
        assertArrayEquals(new double[] {4000.0}, mul.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());

        // elementwise divide → SI: [2/2000] = [0.001]; unit: m ⊖ km
        Vector1<SIQuantity, SIUnit> div = a.divideElements(b);
        assertArrayEquals(new double[] {0.001}, div.si(), EPS);
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // as(targetUnit): success (same SI dimension) and failure (mismatched)
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify as(targetUnit) succeeds for a unit with identical SI dimension and fails for mismatched dimension.
     */
    @Test
    @DisplayName("as(targetUnit) success for m↔km and failure for mismatched SI unit")
    public void testAs()
    {
        Vector1<Length, Length.Unit> v = vec(1.0, Length.Unit.km);
        Vector1<Length, Length.Unit> asMetersCol = v.as(Length.Unit.m);
        assertEquals(Length.Unit.m, asMetersCol.getDisplayUnit());
        assertArrayEquals(v.si(), asMetersCol.si(), EPS, "SI storage must be unchanged");

        // seconds has different SI dimension; must fail
        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> v.as(second));
    }

    // ------------------------------------------------------------------------------------
    // getScalars / getRowScalars / getColumnScalars / getDiagonalScalars
    // getRowVector / getColumnVector / getDiagonalVector (spec-compliant expectations)
    // getRowSi / getColumnSi / getDiagonalSi (spec-compliant expectations)
    // ------------------------------------------------------------------------------------

    /**
     * Verify scalar array extraction helpers on {@link VectorMatrix}.
     */
    @Test
    @DisplayName("getScalars / getRowScalars / getColumnScalars / getDiagonalScalars")
    public void testScalarExtraction()
    {
        Vector1<Length, Length.Unit> m = vec(2.0, Length.Unit.m);

        // Row/Column/Diagonal scalar arrays
        Length[] row0 = m.getRowScalars(0);
        Length[] col0 = m.getColumnScalars(0);

        assertEquals(1, row0.length, "row length");
        assertEquals(1, col0.length, "column length");
        assertEquals(2.0, row0[0].si(), EPS);
        assertEquals(2.0, col0[0].si(), EPS);

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
        Vector1<Length, Length.Unit> m = vec(2.0, Length.Unit.cm); // different display unit

        // Row/Column/Diagonal Vectors
        Vector1<Length, Length.Unit> row0 = m.getRowVector(0);
        Vector1<Length, Length.Unit> col1 = m.getColumnVector(0);

        assertEquals(1, row0.size(), "row size");
        assertEquals(1, col1.size(), "column size");
        assertEquals(0.02, row0.get(0).si(), EPS);
        assertEquals(0.02, col1.get(0).si(), EPS);

        // Row/Column/Diagonal scalar arrays
        Vector1<Length, Length.Unit> mRow1 = m.mgetRowVector(1);
        Vector1<Length, Length.Unit> mCol2 = m.mgetColumnVector(1);

        assertEquals(1, mRow1.size(), "row length");
        assertEquals(1, mCol2.size(), "column length");
        assertEquals(0.02, mRow1.get(0).si(), EPS);
        assertEquals(0.02, mCol2.get(0).si(), EPS);

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
        Vector1<Length, Length.Unit> m = vec(2.0, Length.Unit.m);

        // Row/Column/Diagonal scalar arrays
        double[] row0 = m.getRowSi(0);
        double[] col1 = m.getColumnSi(0);

        assertEquals(1, row0.length, "row length");
        assertEquals(1, col1.length, "column length");
        assertEquals(2.0, row0[0], EPS);
        assertEquals(2.0, col1[0], EPS);

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

    // -----------------------------------------------------------------------------------------------------------------
    // equals / hashCode / toString / setDisplayUnit / isRelative
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify equals/hashCode and type-sensitive equality (Row vs Col), and that display unit changes do not affect equals.
     */
    @Test
    @DisplayName("equals/hashCode and type sensitivity")
    public void testEqualsHashCode()
    {
        Vector1<Length, Length.Unit> r1 = vec(1000.0, Length.Unit.m);
        Vector1<Length, Length.Unit> r2 = vec(1.0, Length.Unit.km).setDisplayUnit(Length.Unit.m);

        assertEquals(r1, r1, "reflexive");
        assertEquals(r1.hashCode(), r1.hashCode(), "hash stable");
        assertEquals(r1, r2, "equal SI values despite different original display units");
        assertEquals(r1.hashCode(), r2.hashCode(), "hash equal for equal SI");
        assertNotEquals(r1, null);
        assertNotEquals(r1, "not a vector");
    }

    /**
     * Verify toString() and toString(unit) contain orientation tag and unit abbreviation.
     */
    @Test
    @DisplayName("toString() and toString(unit)")
    public void testToString()
    {
        Vector1<Length, Length.Unit> c = vec(1.0, Length.Unit.km);
        String s1C = c.toString();
        String s2C = c.toString(Length.Unit.m);
        assertTrue(s1C.startsWith("["), "orientation tag");
        assertTrue(s1C.contains("km"), "contains display unit abbreviation");
        assertTrue(s2C.contains("m"), "toString(withUnit) uses the provided unit");
    }

    /**
     * Verify setDisplayUnit returns {@code this} (fluent) and that isRelative aligns with Length semantics.
     */
    @Test
    @DisplayName("setDisplayUnit() fluent and isRelative()")
    public void testFluentAndRelative()
    {
        Vector1<Length, Length.Unit> r = vec(1.0, Length.Unit.km);
        Vector1<Length, Length.Unit> returnedR = r.setDisplayUnit(Length.Unit.m);
        assertEquals(Length.Unit.m, r.getDisplayUnit());
        assertEquals(r, returnedR, "fluent returns this");
        assertTrue(r.isRelative(), "Length is a relative quantity");
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        Vector1<Length, Length.Unit> r = vec(1.0, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        Vector1<Speed, Speed.Unit> sr = r.divideElements(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideElements(d).as(Area.Unit.m2));
    }
}
