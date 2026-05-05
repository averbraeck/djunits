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
 * <li>Accessors: x(), y(), xSi(), ySi(), getSiArray(), get(index)</li>
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
    private static Vector1<Length> vec(final double xInUnit, final Length.Unit unit)
    {
        return Vector1.of(xInUnit, unit);
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
        Vector1<Length> v = vec(5.0, Length.Unit.km);
        assertArrayEquals(new double[] {5000.0}, v.getSiArray(), EPS);
        assertEquals(Length.Unit.km, v.getDisplayUnit(), "display unit preserved");
        assertTrue(v.isRowVector());
        assertTrue(v.isColumnVector());
        assertFalse(v.isAbsolute());
        assertTrue(v.isRelative());

        // Factory .of for vec
        Vector1<Length> cf = Vector1.of(1.2, Length.Unit.m);
        assertArrayEquals(new double[] {1.2}, cf.getSiArray(), EPS);
    }

    /**
     * Verify {@link Vector1#instantiateSi(double[])} delegates to the (xSi) variant and enforces length=1.
     */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=1 and delegates")
    public void testInstantiateSiArray()
    {
        Vector1<Length> v1 = vec(1.0, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> v1.instantiateSi(new double[] {1.0, 2.0}),
                "Wrong length must throw");
        double[] newSi = new double[] {20.0};
        Vector1<Length> r2 = v1.instantiateSi(newSi);
        assertArrayEquals(new double[] {20.0}, r2.getSiArray(), EPS);
        assertEquals(v1.getDisplayUnit(), r2.getDisplayUnit(), "display unit copied by instantiate path");

        Vector1<SIQuantity> siVector = v1.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siVector.getDisplayUnit().siUnit().format(true, false), "display unit retained");
        assertArrayEquals(newSi, siVector.getSiArray(), EPS, "si array used as-is");
        assertEquals(20.0, siVector.get(0).si(), EPS);

        Vector1<SIQuantity> siVectorOf = Vector1.of(20.0, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siVectorOf.getDisplayUnit().siUnit().format(true, false), "display unit retained");
        assertArrayEquals(newSi, siVectorOf.getSiArray(), EPS, "si array used as-is");
        assertEquals(20.0, siVectorOf.get(0).si(), EPS);

        assertThrows(IllegalArgumentException.class,
                () -> v1.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Accessors, indexing, iterator, scalar array
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify size, x() quantities, xSi(), getSiArray() copy semantics, and get(index) branches.
     */
    @Test
    @DisplayName("size, x/y (Q), xSi, getSiArray() copy, and get(index)")
    public void testAccessorsAndIndexing()
    {
        Vector1<Length> v = vec(0.5, Length.Unit.cm); // SI: 0.005
        assertEquals(1, v.size(), "size is 1");
        assertEquals(0.005, v.xSi(), EPS);
        assertEquals(0.5, v.x().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS, "x() returns Length with display unit");

        // getSiArray() must return a fresh copy (mutating the returned array must not affect the vector)
        double[] siCopy = v.getSiArray();
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
        Vector1<Length> v = vec(2.0, Length.Unit.km); // SI: 1000, 2000
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
        Vector1<Length> v = vec(3.0, Length.Unit.m);
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
        Vector1<Length> v = vec(2.0, Length.Unit.cm);
        assertEquals(1, v.rows());
        assertEquals(1, v.cols());
        assertTrue(v.isColumnVector());

        Vector1<Length> vt = v.transpose();
        assertArrayEquals(v.getSiArray(), vt.getSiArray(), EPS, "Transpose preserves SI");
        assertEquals(v.getDisplayUnit(), vt.getDisplayUnit());

        assertEquals(1, v.nnz());
        Vector1<Length> v0 = vec(0.0, Length.Unit.m);
        assertEquals(0, v0.nnz());
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
        Vector1<Length> a = vec(1.0, Length.Unit.m); // SI: 1
        Vector1<Length> b = vec(0.5, Length.Unit.m); // SI: 0.5
        Length inc = Length.of(2.0, "m"); // +2 m

        // V ± V
        assertArrayEquals(new double[] {1.5}, a.add(b).getSiArray(), EPS);
        assertArrayEquals(new double[] {0.5}, a.subtract(b).getSiArray(), EPS);

        // ± Q
        assertArrayEquals(new double[] {3.0}, a.add(inc).getSiArray(), EPS);
        assertArrayEquals(new double[] {-1.0}, a.subtract(inc).getSiArray(), EPS);

        // negate, abs, scaleBy
        assertArrayEquals(new double[] {-1.0}, a.negate().getSiArray(), EPS);
        assertArrayEquals(new double[] {1.0}, a.abs().getSiArray(), EPS);
        assertArrayEquals(new double[] {2.0}, a.scaleBy(2.0).getSiArray(), EPS);
    }

    /**
     * Verify statistics: min, max, mean, median, sum.
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/sum")
    public void testStatistics()
    {
        Vector1<Length> v = vec(3.0, Length.Unit.m);
        assertEquals(3.0, v.min().si(), EPS);
        assertEquals(3.0, v.max().si(), EPS);
        assertEquals(3.0, v.mean().si(), EPS);
        assertEquals(3.0, v.median().si(), EPS, "median of 2-point set = mean");
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
        Vector1<Length> v = vec(-4.0, Length.Unit.m);
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
        Vector1<Length> a = vec(2.0, Length.Unit.m); // SI: 2
        Vector1<Length> b = vec(2.0, Length.Unit.km); // SI: 2000

        // invert
        Vector1<SIQuantity> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5}, inv.getSiArray(), EPS);
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit should be inverted");

        // elementwise multiply → SI: [2*2000] = [4000]; unit: m ⊕ km
        Vector1<SIQuantity> mul = a.multiplyEntries(b);
        assertArrayEquals(new double[] {4000.0}, mul.getSiArray(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());

        // elementwise divide → SI: [2/2000] = [0.001]; unit: m ⊖ km
        Vector1<SIQuantity> div = a.divideEntries(b);
        assertArrayEquals(new double[] {0.001}, div.getSiArray(), EPS);
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
        Vector1<Length> v = vec(1.0, Length.Unit.km);
        Vector1<Length> asMetersCol = v.as(Length.Unit.m);
        assertEquals(Length.Unit.m, asMetersCol.getDisplayUnit());
        assertArrayEquals(v.getSiArray(), asMetersCol.getSiArray(), EPS, "SI storage must be unchanged");

        // seconds has different SI dimension; must fail
        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> v.as(second));
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
        Vector1<Length> r1 = vec(1000.0, Length.Unit.m);
        Vector1<Length> r2 = vec(1.0, Length.Unit.km).setDisplayUnit(Length.Unit.m);
        Vector1<Length> r3 = vec(3000.0, Length.Unit.m);

        assertEquals(r1, r1, "reflexive");
        assertEquals(r1.hashCode(), r1.hashCode(), "hash stable");
        assertEquals(r1, r2, "equal SI values despite different original display units");
        assertEquals(r1.hashCode(), r2.hashCode(), "hash equal for equal SI");
        assertNotEquals(r1, null);
        assertNotEquals(r1, r3);
        assertNotEquals(r1, "not a vector");
    }

    /**
     * Verify toString() and toString(unit) contain orientation tag and unit abbreviation.
     */
    @Test
    @DisplayName("toString() and toString(unit)")
    public void testToString()
    {
        Vector1<Length> c = vec(1.0, Length.Unit.km);
        String s1C = c.toString();
        String s2C = c.format(Length.Unit.m);
        assertTrue(s1C.startsWith("["), "start symbol");
        assertTrue(s1C.endsWith("] km"), "contains display unit abbreviation");
        assertTrue(s2C.endsWith("] m"), "toString(withUnit) uses the provided unit");
    }

    /**
     * Verify setDisplayUnit returns {@code this} (fluent) and that isRelative aligns with Length semantics.
     */
    @Test
    @DisplayName("setDisplayUnit() fluent and isRelative()")
    public void testFluentAndRelative()
    {
        Vector1<Length> r = vec(1.0, Length.Unit.km);
        Vector1<Length> returnedR = r.setDisplayUnit(Length.Unit.m);
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
        Vector1<Length> r = vec(1.0, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        Vector1<Speed> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));
    }

    // =================================================================================================
    // COMPLETE FACTORY TESTS FOR Vector1.of(...) AND Vector1.ofSi(...).
    // Vector1 is a corner case: no Row/Col distinction, but full branch coverage is required.
    // =================================================================================================

    // -------------------------------------------------------------------------------------------------
    // of(double xInUnit, Unit unit).
    // -------------------------------------------------------------------------------------------------

    /** Vector1.of(double, unit) happy path. */
    @Test
    public void testOfDoubleUnitHappy()
    {
        Vector1<Length> v = Vector1.of(2.0, Length.Unit.km);
        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {2000.0}, v.getSiArray(), EPS);
    }

    /** Vector1.of(double, null) must throw. */
    @Test
    public void testOfDoubleUnitNull()
    {
        assertThrows(NullPointerException.class, () -> Vector1.of(1.0, null));
    }

    // -------------------------------------------------------------------------------------------------
    // of(Q data).
    // -------------------------------------------------------------------------------------------------

    /** Vector1.of(Q) happy path. */
    @Test
    public void testOfQuantityHappy()
    {
        Length q = new Length(5.0, Length.Unit.cm);

        Vector1<Length> v = Vector1.of(q);

        assertEquals(Length.Unit.cm, v.getDisplayUnit());
        assertArrayEquals(new double[] {0.05}, v.getSiArray(), EPS);
    }

    /** Vector1.of(Q) with null quantity. */
    @Test
    public void testOfQuantityNull()
    {
        assertThrows(NullPointerException.class, () -> Vector1.of((Length) null));
    }

    // -------------------------------------------------------------------------------------------------
    // of(double[] dataInUnit, Unit unit).
    // -------------------------------------------------------------------------------------------------

    /** Vector1.of(double[], unit) happy path. */
    @Test
    public void testOfArrayUnitHappy()
    {
        Vector1<Length> v = Vector1.of(new double[] {3.0}, Length.Unit.m);

        assertEquals(Length.Unit.m, v.getDisplayUnit());
        assertArrayEquals(new double[] {3.0}, v.getSiArray(), EPS);
    }

    /** Vector1.of(double[], unit) with null array. */
    @Test
    public void testOfArrayUnitNullArray()
    {
        assertThrows(NullPointerException.class, () -> Vector1.of((double[]) null, Length.Unit.m));
    }

    /** Vector1.of(double[], unit) with wrong length. */
    @Test
    public void testOfArrayUnitWrongLength()
    {
        assertThrows(IllegalArgumentException.class, () -> Vector1.of(new double[] {}, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> Vector1.of(new double[] {1.0, 2.0}, Length.Unit.m));
    }

    // -------------------------------------------------------------------------------------------------
    // ofSi(double xSi, Unit displayUnit).
    // -------------------------------------------------------------------------------------------------

    /** Vector1.ofSi(double, unit) happy path. */
    @Test
    public void testOfSiScalarHappy()
    {
        Vector1<Length> v = Vector1.ofSi(7.0, Length.Unit.km);

        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {7.0}, v.getSiArray(), EPS);
    }

    /** Vector1.ofSi(double, null) must throw. */
    @Test
    public void testOfSiScalarNullUnit()
    {
        assertThrows(NullPointerException.class, () -> Vector1.ofSi(1.0, null));
    }

    // -------------------------------------------------------------------------------------------------
    // ofSi(double[] dataSi, Unit displayUnit).
    // -------------------------------------------------------------------------------------------------

    /** Vector1.ofSi(double[], unit) happy path. */
    @Test
    public void testOfSiArrayHappy()
    {
        Vector1<Length> v = Vector1.ofSi(new double[] {9.0}, Length.Unit.m);

        assertEquals(Length.Unit.m, v.getDisplayUnit());
        assertArrayEquals(new double[] {9.0}, v.getSiArray(), EPS);
    }

    /** Vector1.ofSi(double[], unit) with null array. */
    @Test
    public void testOfSiArrayNullArray()
    {
        assertThrows(NullPointerException.class, () -> Vector1.ofSi((double[]) null, Length.Unit.m));
    }

    /** Vector1.ofSi(double[], unit) with wrong length. */
    @Test
    public void testOfSiArrayWrongLength()
    {
        assertThrows(IllegalArgumentException.class, () -> Vector1.ofSi(new double[] {}, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> Vector1.ofSi(new double[] {1.0, 2.0}, Length.Unit.m));
    }

    // -------------------------------------------------------------------------------------------------
    // of(Q[] data).
    // -------------------------------------------------------------------------------------------------

    /** Vector1.of(Q[]) happy path. */
    @Test
    public void testOfQuantityArrayHappy()
    {
        Length[] data = {new Length(4.0, Length.Unit.km)};

        Vector1<Length> v = Vector1.of(data);

        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {4000.0}, v.getSiArray(), EPS);
    }

    /** Vector1.of(Q[]) with null array. */
    @Test
    public void testOfQuantityArrayNull()
    {
        assertThrows(NullPointerException.class, () -> Vector1.of((Length[]) null));
    }

    /** Vector1.of(Q[]) with wrong length. */
    @Test
    public void testOfQuantityArrayWrongLength()
    {
        assertThrows(IllegalArgumentException.class, () -> Vector1.of(new Length[] {}));
        assertThrows(IllegalArgumentException.class, () -> Vector1.of(new Length[] {Length.ofSi(1.0), Length.ofSi(2.0)}));
    }
    
    /**
     * Test as() functions.
     */
    @Test
    @DisplayName("Vector1 as() functions test")
    public void testAsFunctions()
    {
        var v = vec(2.0, Length.Unit.km);

        var m1 = v.asMatrix1x1();
        assertArrayEquals(v.getSiArray(), m1.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, m1.getDisplayUnit());
        assertEquals(2000.0, m1.si(0, 0), 1E-10);

        var v1 = v.asVector1();
        assertArrayEquals(v.getSiArray(), v1.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, v1.getDisplayUnit());
        assertEquals(2000.0, v1.si(0), 1E-10);
        
        var mNxN = v.asMatrixNxN();
        assertArrayEquals(v.getSiArray(), mNxN.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, mNxN.getDisplayUnit());
        assertEquals(2000.0, mNxN.si(0, 0), 1E-10);
        
        var mNxM = v.asMatrixNxM();
        assertArrayEquals(v.getSiArray(), mNxM.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, mNxM.getDisplayUnit());
        assertEquals(2000.0, mNxM.si(0, 0), 1E-10);
        
        var mQT = v.asQuantityTable();
        assertArrayEquals(v.getSiArray(), mQT.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, mQT.getDisplayUnit());
        assertEquals(2000.0, mQT.si(0, 0), 1E-10);
        
        var vNcol = v.asVectorNCol();
        assertArrayEquals(v.getSiArray(), vNcol.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, vNcol.getDisplayUnit());
        assertEquals(2000.0, vNcol.si(0), 1E-10);
        
        var vNrow = v.asVectorNRow();
        assertArrayEquals(v.getSiArray(), vNrow.asMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Length.Unit.km, vNrow.getDisplayUnit());
        assertEquals(2000.0, vNrow.si(0), 1E-10);
        
        assertThrows(IllegalStateException.class, () -> v.asMatrix2x2());
        assertThrows(IllegalStateException.class, () -> v.asMatrix3x3());
        assertThrows(IllegalStateException.class, () -> v.asVector2Col());
        assertThrows(IllegalStateException.class, () -> v.asVector2Row());
        assertThrows(IllegalStateException.class, () -> v.asVector3Col());
        assertThrows(IllegalStateException.class, () -> v.asVector3Row());
    }

}
