package org.djunits.vecmat.d2;

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
 * Unit tests for {@link Vector2} (both {@link Vector2.Col} and {@link Vector2.Row}) with concrete quantity {@link Length} and
 * unit {@link org.djunits.quantity.Length.Unit}. The tests encode the <em>correct behavior</em> and are intended to fail when
 * the implementation contains defects (e.g., wrong L1 norm, incorrect Hadamard unit composition).
 * <p>
 * Coverage goals:
 * <ul>
 * <li>Constructors, factories, and SI conversion semantics</li>
 * <li>Accessors: x(), y(), xSi(), ySi(), si(), get(index)</li>
 * <li>Iterator and getScalarArray()</li>
 * <li>VectorMatrixOps defaults as exercised/overridden by Vector2</li>
 * <li>Normed: norm(), normL1(), normL2(), normLp(p), normLinf()</li>
 * <li>Orientation &amp; shape: rows(), cols(), isColumnVector()</li>
 * <li>Transposition Row&lt;&rarr;&gt;Col</li>
 * <li>Hadamard operations (invertEntries, multiplyEntries, divideEntries)</li>
 * <li>Linear algebra products: Row⋅Col, Col⋅Row, Row⋅Matrix2x2</li>
 * <li>as(targetUnit) positive and negative branches</li>
 * <li>equals, hashCode, toString</li>
 * </ul>
 * <p>
 * <strong>Important:</strong> Values passed to the {@code Vector2} constructors are interpreted in the provided display unit
 * and converted to SI for internal storage. The tests validate that convention. Copyright (c) 2025-2026 Delft University of
 * Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See for project information
 * <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is distributed under a
 * <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class Vector2Test
{
    /** Numerical tolerance for double comparisons. */
    private static final double EPS = 1.0E-12;

    // -----------------------------------------------------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Create a Length-typed column vector using the given display values and unit.
     * @param xInUnit x value, expressed in the given unit
     * @param yInUnit y value, expressed in the given unit
     * @param unit the display unit
     * @return a Vector2.Col&lt;Length, Length.Unit&gt;
     */
    private static Vector2.Col<Length> col(final double xInUnit, final double yInUnit, final Length.Unit unit)
    {
        return new Vector2.Col<>(xInUnit, yInUnit, unit);
    }

    /**
     * Create a Length-typed row vector using the given display values and unit.
     * @param xInUnit x value, expressed in the given unit
     * @param yInUnit y value, expressed in the given unit
     * @param unit the display unit
     * @return a Vector2.Row&lt;Length, Length.Unit&gt;
     */
    private static Vector2.Row<Length> row(final double xInUnit, final double yInUnit, final Length.Unit unit)
    {
        return new Vector2.Row<>(xInUnit, yInUnit, unit);
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
        // 5 km and 6 km → SI = 5000 m and 6000 m for col
        Vector2.Col<Length> c = col(5.0, 6.0, Length.Unit.km);
        assertArrayEquals(new double[] {5000.0, 6000.0}, c.si(), EPS);
        assertEquals(Length.Unit.km, c.getDisplayUnit(), "display unit preserved");

        // Factory .of for Col
        Vector2.Col<Length> cf = Vector2.Col.of(1.2, 3.4, Length.Unit.m);
        assertArrayEquals(new double[] {1.2, 3.4}, cf.si(), EPS);

        // 5 km and 6 km → SI = 5000 m and 6000 m for row
        Vector2.Row<Length> r = row(5.0, 6.0, Length.Unit.km);
        assertArrayEquals(new double[] {5000.0, 6000.0}, r.si(), EPS);
        assertEquals(Length.Unit.km, r.getDisplayUnit(), "display unit preserved");

        // Factory .of for Row
        Vector2.Row<Length> rf = Vector2.Row.of(10.0, 200.0, Length.Unit.cm);
        assertArrayEquals(new double[] {0.10, 2.0}, rf.si(), EPS);
    }

    /**
     * Verify {@link Vector2#instantiateSi(double[])} delegates to the (xSi,ySi) variant and enforces length=2.
     */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=2 and delegates")
    public void testInstantiateSiArray()
    {
        Vector2.Row<Length> r1 = row(1.0, 2.0, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> r1.instantiateSi(new double[] {1.0}), "Wrong length must throw");
        Vector2.Row<Length> r2 = r1.instantiateSi(new double[] {10.0, 20.0});
        assertArrayEquals(new double[] {10.0, 20.0}, r2.si(), EPS);
        assertEquals(r1.getDisplayUnit(), r2.getDisplayUnit(), "display unit copied by instantiate path");

        Vector2.Col<Length> c1 = col(1.0, 2.0, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> c1.instantiateSi(new double[] {1.0}), "Wrong length must throw");
        Vector2.Col<Length> c2 = c1.instantiateSi(new double[] {10.0, 20.0});
        assertArrayEquals(new double[] {10.0, 20.0}, c2.si(), EPS);
        assertEquals(c1.getDisplayUnit(), c2.getDisplayUnit(), "display unit copied by instantiate path");

        // Row vector
        double[] newSi = {20, 30};
        Vector2.Row<SIQuantity> rsiVector = r1.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", rsiVector.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, rsiVector.si(), EPS, "si array used as-is");
        assertEquals(20.0, rsiVector.get(0).si(), EPS);

        Vector2.Row<SIQuantity> rsiVectorOf = Vector2.Row.of(20.0, 30.0, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", rsiVectorOf.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, rsiVectorOf.si(), EPS, "si array used as-is");
        assertEquals(20.0, rsiVectorOf.get(0).si(), EPS);

        assertThrows(IllegalArgumentException.class,
                () -> r1.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));

        // Column vector
        Vector2.Col<SIQuantity> csiVector = c1.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", csiVector.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, csiVector.si(), EPS, "si array used as-is");
        assertEquals(20.0, csiVector.get(0).si(), EPS);

        Vector2.Col<SIQuantity> csiVectorOf = Vector2.Col.of(20.0, 30.0, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", csiVectorOf.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, csiVectorOf.si(), EPS, "si array used as-is");
        assertEquals(20.0, csiVectorOf.get(0).si(), EPS);

        assertThrows(IllegalArgumentException.class,
                () -> c1.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Accessors, indexing, iterator, scalar array
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify size, x()/y() quantities, xSi()/ySi(), si() copy semantics, and get(index) branches.
     */
    @Test
    @DisplayName("size, x/y (Q), xSi/ySi, si() copy, and get(index)")
    public void testAccessorsAndIndexing()
    {
        Vector2.Row<Length> v = row(0.5, 200.0, Length.Unit.cm); // SI: 0.005, 2.0
        assertEquals(2, v.size(), "size is 2");
        assertEquals(0.005, v.xSi(), EPS);
        assertEquals(2.0, v.ySi(), EPS);
        assertEquals(0.5, v.x().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS, "x() returns Length with display unit");
        assertEquals(200.0, v.y().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS, "y() returns Length with display unit");

        // si() must return a fresh copy (mutating the returned array must not affect the vector)
        double[] siCopy = v.si();
        siCopy[0] = 12345.0;
        assertEquals(0.005, v.xSi(), EPS, "internal storage not affected by external array mutation");

        // get(index) with valid and invalid indices
        assertEquals(v.x().si(), v.get(0).si(), EPS);
        assertEquals(v.y().si(), v.get(1).si(), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(2));

        // mget(index) with valid and invalid indices
        assertEquals(v.x().si(), v.mget(1).si(), EPS);
        assertEquals(v.y().si(), v.mget(2).si(), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.mget(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mget(3));

        // si(index) with valid and invalid indices
        assertEquals(v.x().si(), v.si(0), EPS);
        assertEquals(v.y().si(), v.si(1), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(2));

        // msi(index) with valid and invalid indices
        assertEquals(v.x().si(), v.msi(1), EPS);
        assertEquals(v.y().si(), v.msi(2), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(3));

        Vector2.Col<Length> w = col(0.5, 200.0, Length.Unit.cm); // SI: 0.005, 2.0
        assertEquals(2, w.size(), "size is 2");
        assertEquals(0.005, w.xSi(), EPS);
        assertEquals(2.0, w.ySi(), EPS);
        assertEquals(0.5, w.x().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS, "x() returns Length with display unit");
        assertEquals(200.0, w.y().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS, "y() returns Length with display unit");

        // si() must return a fresh copy (mutating the returned array must not affect the vector)
        double[] siCopyCol = w.si();
        siCopyCol[0] = 12345.0;
        assertEquals(0.005, w.xSi(), EPS, "internal storage not affected by external array mutation");

        // get(index) with valid and invalid indices
        assertEquals(w.x().si(), w.si(0), EPS);
        assertEquals(w.y().si(), w.si(1), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> w.mget(0));
        assertThrows(IndexOutOfBoundsException.class, () -> w.mget(3));
    }

    /**
     * Verify the iterator over Q values yields x then y in the vector's display unit and is consistent with get().
     */
    @Test
    @DisplayName("iterator() yields Q in display unit, in order x,y")
    public void testIterator()
    {
        Vector2.Col<Length> v = col(1.0, 2.0, Length.Unit.km); // SI: 1000, 2000
        Iterator<Length> itCol = v.iterator();
        assertTrue(itCol.hasNext());
        Length firstCol = itCol.next();
        Length secondCol = itCol.next();
        assertFalse(itCol.hasNext());
        assertThrows(NoSuchElementException.class, itCol::next);
        assertEquals(1.0, firstCol.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(2.0, secondCol.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(v.get(0).si(), firstCol.si(), EPS);
        assertEquals(v.get(1).si(), secondCol.si(), EPS);

        Vector2.Row<Length> w = row(1.0, 2.0, Length.Unit.km); // SI: 1000, 2000
        Iterator<Length> itRow = w.iterator();
        assertTrue(itRow.hasNext());
        Length firstRow = itRow.next();
        Length secondRow = itRow.next();
        assertFalse(itRow.hasNext());
        assertThrows(NoSuchElementException.class, itRow::next);
        assertEquals(1.0, firstRow.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(2.0, secondRow.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(w.get(0).si(), firstRow.si(), EPS);
        assertEquals(w.get(1).si(), secondRow.si(), EPS);
    }

    /**
     * Verify {@link Vector#getScalarArray()} returns a Q[] (not Object[]) with the correct order and values.
     */
    @Test
    @DisplayName("getScalarArray() returns Q[] with x,y in order")
    public void testGetScalarArray()
    {
        Vector2.Row<Length> v = row(3.0, 4.0, Length.Unit.m);
        Length[] arrRow = v.getScalarArray();
        assertEquals(2, arrRow.length);
        assertInstanceOf(Length.class, arrRow[0]);
        assertInstanceOf(Length.class, arrRow[1]);
        assertEquals(3.0, arrRow[0].si(), EPS);
        assertEquals(4.0, arrRow[1].si(), EPS);

        Vector2.Col<Length> w = col(3.0, 4.0, Length.Unit.m);
        Length[] arrCol = w.getScalarArray();
        assertEquals(2, arrCol.length);
        assertInstanceOf(Length.class, arrCol[0]);
        assertInstanceOf(Length.class, arrCol[1]);
        assertEquals(3.0, arrCol[0].si(), EPS);
        assertEquals(4.0, arrCol[1].si(), EPS);
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
        Vector2.Row<Length> r = row(1.0, 2.0, Length.Unit.m);
        Vector2.Col<Length> c = col(1.0, 2.0, Length.Unit.m);
        assertEquals(1, r.rows());
        assertEquals(2, r.cols());
        assertFalse(r.isColumnVector());
        assertEquals(2, c.rows());
        assertEquals(1, c.cols());
        assertTrue(c.isColumnVector());

        Vector2.Col<Length> rt = r.transpose();
        Vector2.Row<Length> ct = c.transpose();
        assertArrayEquals(r.si(), rt.si(), EPS, "Row→Col preserves SI");
        assertEquals(r.getDisplayUnit(), rt.getDisplayUnit());
        assertArrayEquals(c.si(), ct.si(), EPS, "Col→Row preserves SI");
        assertEquals(c.getDisplayUnit(), ct.getDisplayUnit());

        assertEquals(2, c.nnz());
        assertEquals(2, r.nnz());
        Vector2.Row<Length> r0 = row(0.0, 0.0, Length.Unit.m);
        Vector2.Col<Length> c0 = col(0.0, 0.0, Length.Unit.m);
        assertEquals(0, r0.nnz());
        assertEquals(0, c0.nnz());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // VectorMatrixOps defaults exercised via Vector2 overrides
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify add(Q), subtract(Q), add(V), subtract(V), negate(), abs(), scaleBy(double) for Row.
     */
    @Test
    @DisplayName("VectorMatrixOps: add/subtract (Q & V), negate, abs, scaleBy, Row")
    public void testBasicAlgebraRow()
    {
        Vector2.Row<Length> a = row(1.0, -2.0, Length.Unit.m); // SI: 1, -2
        Vector2.Row<Length> b = row(0.5, 3.0, Length.Unit.m); // SI: 0.5, 3
        Length inc = Length.of(2.0, "m"); // +2 m

        // V ± V
        assertArrayEquals(new double[] {1.5, 1.0}, a.add(b).si(), EPS);
        assertArrayEquals(new double[] {0.5, -5.0}, a.subtract(b).si(), EPS);

        // ± Q
        assertArrayEquals(new double[] {3.0, 0.0}, a.add(inc).si(), EPS);
        assertArrayEquals(new double[] {-1.0, -4.0}, a.subtract(inc).si(), EPS);

        // negate, abs, scaleBy
        assertArrayEquals(new double[] {-1.0, 2.0}, a.negate().si(), EPS);
        assertArrayEquals(new double[] {1.0, 2.0}, a.abs().si(), EPS);
        assertArrayEquals(new double[] {2.0, -4.0}, a.scaleBy(2.0).si(), EPS);
    }

    /**
     * Verify add(Q), subtract(Q), add(V), subtract(V), negate(), abs(), scaleBy(double) for Col.
     */
    @Test
    @DisplayName("VectorMatrixOps: add/subtract (Q & V), negate, abs, scaleBy, Col")
    public void testBasicAlgebraCol()
    {
        Vector2.Col<Length> a = col(1.0, -2.0, Length.Unit.m); // SI: 1, -2
        Vector2.Col<Length> b = col(0.5, 3.0, Length.Unit.m); // SI: 0.5, 3
        Length inc = Length.of(2.0, "m"); // +2 m

        // V ± V
        assertArrayEquals(new double[] {1.5, 1.0}, a.add(b).si(), EPS);
        assertArrayEquals(new double[] {0.5, -5.0}, a.subtract(b).si(), EPS);

        // ± Q
        assertArrayEquals(new double[] {3.0, 0.0}, a.add(inc).si(), EPS);
        assertArrayEquals(new double[] {-1.0, -4.0}, a.subtract(inc).si(), EPS);

        // negate, abs, scaleBy
        assertArrayEquals(new double[] {-1.0, 2.0}, a.negate().si(), EPS);
        assertArrayEquals(new double[] {1.0, 2.0}, a.abs().si(), EPS);
        assertArrayEquals(new double[] {2.0, -4.0}, a.scaleBy(2.0).si(), EPS);
    }

    /**
     * Verify statistics: min, max, mean, median, sum.
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/sum")
    public void testStatistics()
    {
        Vector2.Col<Length> v = col(1.0, 3.0, Length.Unit.m); // SI: 1, 3
        assertEquals(1.0, v.min().si(), EPS);
        assertEquals(3.0, v.max().si(), EPS);
        assertEquals(2.0, v.mean().si(), EPS);
        assertEquals(2.0, v.median().si(), EPS, "median of 2-point set = mean");
        assertEquals(4.0, v.sum().si(), EPS);

        Vector2.Row<Length> w = row(1.0, 3.0, Length.Unit.m); // SI: 1, 3
        assertEquals(1.0, w.min().si(), EPS);
        assertEquals(3.0, w.max().si(), EPS);
        assertEquals(2.0, w.mean().si(), EPS);
        assertEquals(2.0, w.median().si(), EPS, "median of 2-point set = mean");
        assertEquals(4.0, w.sum().si(), EPS);
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
        Vector2.Row<Length> v = row(3.0, 4.0, Length.Unit.m); // SI: 3, 4
        // L2 = 5; Linf = 4; L1 = 7; Lp(p=3) = (3^3 + 4^3)^(1/3)
        assertEquals(v.normL2().si(), v.norm().si(), EPS, "norm() delegates to normL2()");
        assertEquals(7.0, v.normL1().si(), EPS, "L1 = |3| + |4| = 7");
        assertEquals(5.0, v.normL2().si(), EPS);
        assertEquals(Math.cbrt(27.0 + 64.0), v.normLp(3).si(), EPS);
        assertEquals(4.0, v.normLinf().si(), EPS);

        Vector2.Col<Length> w = col(3.0, 4.0, Length.Unit.m); // SI: 3, 4
        // L2 = 5; Linf = 4; L1 = 7; Lp(p=3) = (3^3 + 4^3)^(1/3)
        assertEquals(w.normL2().si(), w.norm().si(), EPS, "norm() delegates to normL2()");
        assertEquals(7.0, w.normL1().si(), EPS, "L1 = |3| + |4| = 7");
        assertEquals(5.0, w.normL2().si(), EPS);
        assertEquals(Math.cbrt(27.0 + 64.0), w.normLp(3).si(), EPS);
        assertEquals(4.0, w.normLinf().si(), EPS);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Hadamard operations (invert, elementwise multiply/divide)
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify Hadamard element-wise operations and correct unit composition. If the implementation derives the unit from
     * x().siUnit() instead of the vector's display unit, the test will fail (by design) for Column vector.
     */
    @Test
    @DisplayName("Hadamard: invertEntries / multiplyEntries / divideEntries + unit composition, Col")
    public void testHadamardCol()
    {
        Vector2.Col<Length> a = col(2.0, 4.0, Length.Unit.m); // SI: 2, 4
        Vector2.Col<Length> b = col(1.0, 2.0, Length.Unit.km); // SI: 1000, 2000

        // invert
        Vector2.Col<SIQuantity> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5, 0.25}, inv.si(), EPS);
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit should be inverted");

        // elementwise multiply → SI: [2*1000, 4*2000] = [2000, 8000]; unit: m ⊕ km
        Vector2.Col<SIQuantity> mul = a.multiplyEntries(b);
        assertArrayEquals(new double[] {2000.0, 8000.0}, mul.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());

        // elementwise divide → SI: [2/1000, 4/2000] = [0.002, 0.002]; unit: m ⊖ km
        Vector2.Col<SIQuantity> div = a.divideEntries(b);
        assertArrayEquals(new double[] {0.002, 0.002}, div.si(), EPS);
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit());
    }

    /**
     * Verify Hadamard element-wise operations and correct unit composition. If the implementation derives the unit from
     * x().siUnit() instead of the vector's display unit, the test will fail (by design) for Row vector.
     */
    @Test
    @DisplayName("Hadamard: invertEntries / multiplyEntries / divideEntries + unit composition, Row")
    public void testHadamardRow()
    {
        Vector2.Row<Length> a = row(2.0, 4.0, Length.Unit.m); // SI: 2, 4
        Vector2.Row<Length> b = row(1.0, 2.0, Length.Unit.km); // SI: 1000, 2000

        // invert
        Vector2.Row<SIQuantity> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5, 0.25}, inv.si(), EPS);
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit should be inverted");

        // elementwise multiply → SI: [2*1000, 4*2000] = [2000, 8000]; unit: m ⊕ km
        Vector2.Row<SIQuantity> mul = a.multiplyEntries(b);
        assertArrayEquals(new double[] {2000.0, 8000.0}, mul.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());

        // elementwise divide → SI: [2/1000, 4/2000] = [0.002, 0.002]; unit: m ⊖ km
        Vector2.Row<SIQuantity> div = a.divideEntries(b);
        assertArrayEquals(new double[] {0.002, 0.002}, div.si(), EPS);
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Linear algebra: Row·Col → scalar; Col·Row → Matrix2x2; Row·Matrix2x2 → Col
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verify Row⋅Col = scalar with plus-unit composition.
     */
    @Test
    @DisplayName("Row · Col → SIQuantity with unit composition")
    public void testRowDotCol()
    {
        Vector2.Row<Length> r = row(1.0, 2.0, Length.Unit.m); // SI: [1, 2]
        Vector2.Col<Length> c = col(5.0, 6.0, Length.Unit.km); // SI: [5000, 6000]
        SIQuantity dot = r.multiply(c); // 1*5000 + 2*6000 = 17000
        assertEquals(17_000.0, dot.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), dot.siUnit());
    }

    /**
     * Verify Col⋅Row = 2x2 matrix with plus-unit composition.
     */
    @Test
    @DisplayName("Col · Row → Matrix2x2 with unit composition")
    public void testColTimesRow()
    {
        Vector2.Col<Length> c = col(1.0, 2.0, Length.Unit.km); // SI: [1000, 2000]
        Vector2.Row<Length> r = row(3.0, 4.0, Length.Unit.m); // SI: [3, 4]
        Matrix2x2<SIQuantity> m = c.multiply(r);
        // Outer product:
        // [1000*3, 1000*4,
        // 2000*3, 2000*4] = [3000, 4000, 6000, 8000]
        assertArrayEquals(new double[] {3000.0, 4000.0, 6000.0, 8000.0}, m.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.km.siUnit(), Length.Unit.m.siUnit()), m.getDisplayUnit().siUnit());
    }

    /**
     * Verify Row⋅Matrix2x2 = Col with plus-unit composition.
     */
    @Test
    @DisplayName("Row · Matrix2x2 → Col with unit composition")
    public void testRowTimesMatrix()
    {
        Vector2.Row<Length> r = row(1.0, 2.0, Length.Unit.m); // [1,2]
        Matrix2x2<Length> a = Matrix2x2.of(new double[] {5.0, 6.0, 7.0, 8.0}, Length.Unit.km); // B in km
        Vector2.Col<SIQuantity> res = r.multiply(a); // [1,2]*[[5k,6k],[7k,8k]] = [19k, 22k] ⇒ SI [19000,22000]
        assertArrayEquals(new double[] {19_000.0, 22_000.0}, res.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), res.getDisplayUnit());
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
        Vector2.Col<Length> v = col(1.0, 2.0, Length.Unit.km);
        Vector2.Col<Length> asMetersCol = v.as(Length.Unit.m);
        assertEquals(Length.Unit.m, asMetersCol.getDisplayUnit());
        assertArrayEquals(v.si(), asMetersCol.si(), EPS, "SI storage must be unchanged");

        // seconds has different SI dimension; must fail
        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> v.as(second));

        Vector2.Row<Length> w = row(1.0, 2.0, Length.Unit.km);
        Vector2.Row<Length> asMetersRow = w.as(Length.Unit.m);
        assertEquals(Length.Unit.m, asMetersRow.getDisplayUnit());
        assertArrayEquals(w.si(), asMetersRow.si(), EPS, "SI storage must be unchanged");

        // seconds has different SI dimension; must fail
        assertThrows(IllegalArgumentException.class, () -> w.as(second));
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
        Vector2.Row<Length> r1 = row(1000.0, 2000.0, Length.Unit.m);
        Vector2.Row<Length> r2 = row(1.0, 2.0, Length.Unit.km).setDisplayUnit(Length.Unit.m);
        Vector2.Col<Length> c1 = col(1.0, 2.0, Length.Unit.m);

        assertEquals(r1, r1, "reflexive");
        assertEquals(r1.hashCode(), r1.hashCode(), "hash stable");
        assertEquals(r1, r2, "equal SI values despite different original display units");
        assertEquals(r1.hashCode(), r2.hashCode(), "hash equal for equal SI");
        assertNotEquals(r1, c1, "Row not equal to Col (different classes)");
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
        Vector2.Col<Length> c = col(1.0, 2.0, Length.Unit.km);
        String s1C = c.toString();
        String s2C = c.toString(Length.Unit.m);
        assertTrue(s1C.startsWith("Col["), "orientation tag");
        assertTrue(s1C.contains("km"), "contains display unit abbreviation");
        assertTrue(s2C.contains("m"), "toString(withUnit) uses the provided unit");

        Vector2.Row<Length> r = row(1.0, 2.0, Length.Unit.km);
        String s1R = r.toString();
        String s2R = r.toString(Length.Unit.m);
        assertTrue(s1R.startsWith("Row["), "orientation tag");
        assertTrue(s1R.contains("km"), "contains display unit abbreviation");
        assertTrue(s2R.contains("m"), "toString(withUnit) uses the provided unit");
    }

    /**
     * Verify setDisplayUnit returns {@code this} (fluent) and that isRelative aligns with Length semantics.
     */
    @Test
    @DisplayName("setDisplayUnit() fluent and isRelative()")
    public void testFluentAndRelative()
    {
        Vector2.Row<Length> r = row(1.0, 2.0, Length.Unit.km);
        Vector2.Row<Length> returnedR = r.setDisplayUnit(Length.Unit.m);
        assertEquals(Length.Unit.m, r.getDisplayUnit());
        assertEquals(r, returnedR, "fluent returns this");
        assertTrue(r.isRelative(), "Length is a relative quantity");

        Vector2.Col<Length> c = col(1.0, 2.0, Length.Unit.km);
        Vector2.Col<Length> returnedC = c.setDisplayUnit(Length.Unit.m);
        assertEquals(Length.Unit.m, c.getDisplayUnit());
        assertEquals(c, returnedC, "fluent returns this");
        assertTrue(c.isRelative(), "Length is a relative quantity");
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        Vector2.Col<Length> r = col(1.0, 2.0, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        Vector2.Col<Speed> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.mget(2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));

        Vector2.Row<Length> c = row(1.0, 2.0, Length.Unit.km);
        Vector2.Row<Speed> sc = c.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sc.getDisplayUnit());
        assertEquals(0.5, sc.mget(1).getInUnit(), 1E-6);
        assertEquals(1.0, sc.mget(2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> c.divideEntries(d).as(Area.Unit.m2));
    }

    // =================================================================================================
    // COMPLETE FACTORY TESTS FOR Vector2.of(...) AND Vector2.ofSi(...)
    // Coverage goal: ALL overloads, ALL branches, Row vs Col explicit
    // =================================================================================================

    // -------------------------------------------------------------------------------------------------
    // of(double x, double y, Unit unit)
    // -------------------------------------------------------------------------------------------------

    /** Col.of(double,double,unit) – happy path. */
    @Test
    public void testColOfDoubleDoubleUnitHappy()
    {
        Vector2.Col<Length> v = Vector2.Col.of(2.0, 3.0, Length.Unit.km);
        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {2000.0, 3000.0}, v.si(), EPS);
    }

    /** Row.of(double,double,unit) – happy path. */
    @Test
    public void testRowOfDoubleDoubleUnitHappy()
    {
        Vector2.Row<Duration> v = Vector2.Row.of(500.0, 1500.0, Duration.Unit.ms);
        assertEquals(Duration.Unit.ms, v.getDisplayUnit());
        assertArrayEquals(new double[] {0.5, 1.5}, v.si(), EPS);
    }

    /** Col.of(double,double,null) – null unit. */
    @Test
    public void testColOfDoubleDoubleUnitNull()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Col.of(1.0, 2.0, null));
    }

    /** Row.of(double,double,null) – null unit. */
    @Test
    public void testRowOfDoubleDoubleUnitNull()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Row.of(1.0, 2.0, null));
    }

    // -------------------------------------------------------------------------------------------------
    // of(Q x, Q y)
    // -------------------------------------------------------------------------------------------------

    /** Col.of(Q,Q) – happy path. */
    @Test
    public void testColOfQQHappy()
    {
        Length x = new Length(1.0, Length.Unit.km);
        Length y = new Length(200.0, Length.Unit.m);

        Vector2.Col<Length> v = Vector2.Col.of(x, y);

        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {1000.0, 200.0}, v.si(), EPS);
    }

    /** Row.of(Q,Q) – happy path. */
    @Test
    public void testRowOfQQHappy()
    {
        Duration x = new Duration(2.0, Duration.Unit.h);
        Duration y = new Duration(30.0, Duration.Unit.min);

        Vector2.Row<Duration> v = Vector2.Row.of(x, y);

        assertEquals(Duration.Unit.h, v.getDisplayUnit());
        assertArrayEquals(new double[] {7200.0, 1800.0}, v.si(), EPS);
    }

    /** Col.of(Q,Q) – null x. */
    @Test
    public void testColOfQQNullX()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Col.of(null, Length.ofSi(1.0)));
    }

    /** Col.of(Q,Q) – null y. */
    @Test
    public void testColOfQQNullY()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Col.of(Length.ofSi(1.0), null));
    }

    /** Row.of(Q,Q) – null x. */
    @Test
    public void testRowOfQQNullX()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Row.of(null, Duration.ofSi(1.0)));
    }

    /** Row.of(Q,Q) – null y. */
    @Test
    public void testRowOfQQNullY()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Row.of(Duration.ofSi(1.0), null));
    }

    // -------------------------------------------------------------------------------------------------
    // of(double[] dataInUnit, Unit unit)
    // -------------------------------------------------------------------------------------------------

    /** Col.of(double[],unit) – happy path. */
    @Test
    public void testColOfArrayUnitHappy()
    {
        Vector2.Col<Length> v = Vector2.Col.of(new double[] {1.0, 2.0}, Length.Unit.m);
        assertArrayEquals(new double[] {1.0, 2.0}, v.si(), EPS);
    }

    /** Row.of(double[],unit) – happy path. */
    @Test
    public void testRowOfArrayUnitHappy()
    {
        Vector2.Row<Duration> v = Vector2.Row.of(new double[] {1000.0, 2000.0}, Duration.Unit.ms);
        assertArrayEquals(new double[] {1.0, 2.0}, v.si(), EPS);
    }

    /** Col.of(double[],unit) – null array. */
    @Test
    public void testColOfArrayUnitNullArray()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Col.of((double[]) null, Length.Unit.m));
    }

    /** Row.of(double[],unit) – null array. */
    @Test
    public void testRowOfArrayUnitNullArray()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Row.of((double[]) null, Duration.Unit.s));
    }

    /** Col.of(double[],unit) – wrong length. */
    @Test
    public void testColOfArrayUnitWrongLength()
    {
        assertThrows(IllegalArgumentException.class, () -> Vector2.Col.of(new double[] {1.0}, Length.Unit.m));
    }

    /** Row.of(double[],unit) – wrong length. */
    @Test
    public void testRowOfArrayUnitWrongLength()
    {
        assertThrows(IllegalArgumentException.class, () -> Vector2.Row.of(new double[] {1.0, 2.0, 3.0}, Duration.Unit.s));
    }

    // -------------------------------------------------------------------------------------------------
    // ofSi(double[] dataSi, Unit displayUnit)
    // -------------------------------------------------------------------------------------------------

    /** Col.ofSi(double[],unit) – happy path. */
    @Test
    public void testColOfSiArrayUnitHappy()
    {
        Vector2.Col<Length> v = Vector2.Col.ofSi(new double[] {10.0, 20.0}, Length.Unit.cm);
        assertEquals(Length.Unit.cm, v.getDisplayUnit());
        assertArrayEquals(new double[] {10.0, 20.0}, v.si(), EPS);
    }

    /** Row.ofSi(double[],unit) – happy path. */
    @Test
    public void testRowOfSiArrayUnitHappy()
    {
        Vector2.Row<Duration> v = Vector2.Row.ofSi(new double[] {3600.0, 7200.0}, Duration.Unit.s);
        assertEquals(Duration.Unit.s, v.getDisplayUnit());
        assertArrayEquals(new double[] {3600.0, 7200.0}, v.si(), EPS);
    }

    /** Col.ofSi(double[],unit) – wrong length. */
    @Test
    public void testColOfSiArrayWrongLength()
    {
        assertThrows(IllegalArgumentException.class, () -> Vector2.Col.ofSi(new double[] {1.0}, Length.Unit.m));
    }

    /** Row.ofSi(double[],unit) – wrong length. */
    @Test
    public void testRowOfSiArrayWrongLength()
    {
        assertThrows(IllegalArgumentException.class, () -> Vector2.Row.ofSi(new double[] {1.0, 2.0, 3.0}, Duration.Unit.s));
    }

    /** Col.ofSi(double[],null). */
    @Test
    public void testColOfSiArrayNullUnit()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Col.ofSi(new double[] {1.0, 2.0}, null));
    }

    // -------------------------------------------------------------------------------------------------
    // ofSi(double xSi, double ySi, Unit displayUnit)
    // -------------------------------------------------------------------------------------------------

    /** Col.ofSi(double,double,unit) – happy path. */
    @Test
    public void testColOfSiScalarsHappy()
    {
        Vector2.Col<Length> v = Vector2.Col.ofSi(5.0, 6.0, Length.Unit.m);
        assertArrayEquals(new double[] {5.0, 6.0}, v.si(), EPS);
    }

    /** Row.ofSi(double,double,unit) – happy path. */
    @Test
    public void testRowOfSiScalarsHappy()
    {
        Vector2.Row<Duration> v = Vector2.Row.ofSi(1.0, 2.0, Duration.Unit.s);
        assertArrayEquals(new double[] {1.0, 2.0}, v.si(), EPS);
    }

    /** Row.ofSi(double,double,null). */
    @Test
    public void testRowOfSiScalarsNullUnit()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Row.ofSi(1.0, 2.0, null));
    }

    // -------------------------------------------------------------------------------------------------
    // of(Q[] data)
    // -------------------------------------------------------------------------------------------------

    /** Col.of(Q[]) – happy path. */
    @Test
    public void testColOfQuantityArrayHappy()
    {
        Length[] data = {new Length(1.0, Length.Unit.km), new Length(500.0, Length.Unit.m)};

        Vector2.Col<Length> v = Vector2.Col.of(data);

        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {1000.0, 500.0}, v.si(), EPS);
    }

    /** Row.of(Q[]) – happy path. */
    @Test
    public void testRowOfQuantityArrayHappy()
    {
        Duration[] data = {new Duration(1.0, Duration.Unit.h), new Duration(30.0, Duration.Unit.min)};

        Vector2.Row<Duration> v = Vector2.Row.of(data);

        assertEquals(Duration.Unit.h, v.getDisplayUnit());
        assertArrayEquals(new double[] {3600.0, 1800.0}, v.si(), EPS);
    }

    /** Col.of(Q[]) – null array. */
    @Test
    public void testColOfQuantityArrayNull()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Col.of((Length[]) null));
    }

    /** Row.of(Q[]) – null array. */
    @Test
    public void testRowOfQuantityArrayNull()
    {
        assertThrows(NullPointerException.class, () -> Vector2.Row.of((Duration[]) null));
    }

    /** Col.of(Q[]) – wrong length. */
    @Test
    public void testColOfQuantityArrayWrongLength()
    {
        assertThrows(IllegalArgumentException.class, () -> Vector2.Col.of(new Length[] {Length.ofSi(1.0)}));
    }

    /** Row.of(Q[]) – wrong length. */
    @Test
    public void testRowOfQuantityArrayWrongLength()
    {
        assertThrows(IllegalArgumentException.class,
                () -> Vector2.Row.of(new Duration[] {Duration.ofSi(1.0), Duration.ofSi(2.0), Duration.ofSi(3.0)}));
    }

}
