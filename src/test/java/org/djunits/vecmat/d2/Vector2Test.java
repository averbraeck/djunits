package org.djunits.vecmat.d2;

import static org.junit.jupiter.api.Assertions.assertAll;
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
import org.djunits.vecmat.operations.VectorOps;
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
 * <li>Hadamard operations (invertElements, multiplyElements, divideElements)</li>
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
    private static Vector2.Col<Length, Length.Unit> col(final double xInUnit, final double yInUnit, final Length.Unit unit)
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
    private static Vector2.Row<Length, Length.Unit> row(final double xInUnit, final double yInUnit, final Length.Unit unit)
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
        Vector2.Col<Length, Length.Unit> c = col(5.0, 6.0, Length.Unit.km);
        assertAll(() -> assertArrayEquals(new double[] {5000.0, 6000.0}, c.si(), EPS),
                () -> assertEquals(Length.Unit.km, c.getDisplayUnit(), "display unit preserved"));

        // Factory .of for Col
        Vector2.Col<Length, Length.Unit> cf = Vector2.Col.of(1.2, 3.4, Length.Unit.m);
        assertArrayEquals(new double[] {1.2, 3.4}, cf.si(), EPS);

        // 5 km and 6 km → SI = 5000 m and 6000 m for row
        Vector2.Row<Length, Length.Unit> r = row(5.0, 6.0, Length.Unit.km);
        assertAll(() -> assertArrayEquals(new double[] {5000.0, 6000.0}, r.si(), EPS),
                () -> assertEquals(Length.Unit.km, r.getDisplayUnit(), "display unit preserved"));

        // Factory .of for Row
        Vector2.Row<Length, Length.Unit> rf = Vector2.Row.of(10.0, 200.0, Length.Unit.cm);
        assertArrayEquals(new double[] {0.10, 2.0}, rf.si(), EPS);
    }

    /**
     * Verify {@link Vector2#instantiateSi(double[])} delegates to the (xSi,ySi) variant and enforces length=2.
     */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=2 and delegates")
    public void testInstantiateSiArray()
    {
        Vector2.Row<Length, Length.Unit> r1 = row(1.0, 2.0, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> r1.instantiateSi(new double[] {1.0}), "Wrong length must throw");
        Vector2.Row<Length, Length.Unit> r2 = r1.instantiateSi(new double[] {10.0, 20.0});
        assertArrayEquals(new double[] {10.0, 20.0}, r2.si(), EPS);
        assertEquals(r1.getDisplayUnit(), r2.getDisplayUnit(), "display unit copied by instantiate path");

        Vector2.Col<Length, Length.Unit> c1 = col(1.0, 2.0, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> c1.instantiateSi(new double[] {1.0}), "Wrong length must throw");
        Vector2.Col<Length, Length.Unit> c2 = c1.instantiateSi(new double[] {10.0, 20.0});
        assertArrayEquals(new double[] {10.0, 20.0}, c2.si(), EPS);
        assertEquals(c1.getDisplayUnit(), c2.getDisplayUnit(), "display unit copied by instantiate path");
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
        Vector2.Row<Length, Length.Unit> v = row(0.5, 200.0, Length.Unit.cm); // SI: 0.005, 2.0
        assertAll(() -> assertEquals(2, v.size(), "size is 2"), () -> assertEquals(0.005, v.xSi(), EPS),
                () -> assertEquals(2.0, v.ySi(), EPS),
                () -> assertEquals(0.5, v.x().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS,
                        "x() returns Length with display unit"),
                () -> assertEquals(200.0, v.y().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS,
                        "y() returns Length with display unit"));

        // si() must return a fresh copy (mutating the returned array must not affect the vector)
        double[] siCopy = v.si();
        siCopy[0] = 12345.0;
        assertEquals(0.005, v.xSi(), EPS, "internal storage not affected by external array mutation");

        // get(index) with valid and invalid indices
        assertEquals(v.x().si(), v.get(1).si(), EPS);
        assertEquals(v.y().si(), v.get(2).si(), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(3));

        Vector2.Col<Length, Length.Unit> w = col(0.5, 200.0, Length.Unit.cm); // SI: 0.005, 2.0
        assertAll(() -> assertEquals(2, w.size(), "size is 2"), () -> assertEquals(0.005, w.xSi(), EPS),
                () -> assertEquals(2.0, w.ySi(), EPS),
                () -> assertEquals(0.5, w.x().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS,
                        "x() returns Length with display unit"),
                () -> assertEquals(200.0, w.y().setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS,
                        "y() returns Length with display unit"));

        // si() must return a fresh copy (mutating the returned array must not affect the vector)
        double[] siCopyCol = w.si();
        siCopyCol[0] = 12345.0;
        assertEquals(0.005, w.xSi(), EPS, "internal storage not affected by external array mutation");

        // get(index) with valid and invalid indices
        assertEquals(w.x().si(), w.get(1).si(), EPS);
        assertEquals(w.y().si(), w.get(2).si(), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> w.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> w.get(3));
    }

    /**
     * Verify the iterator over Q values yields x then y in the vector's display unit and is consistent with get().
     */
    @Test
    @DisplayName("iterator() yields Q in display unit, in order x,y")
    public void testIterator()
    {
        Vector2.Col<Length, Length.Unit> v = col(1.0, 2.0, Length.Unit.km); // SI: 1000, 2000
        Iterator<Length> itCol = v.iterator();
        assertTrue(itCol.hasNext());
        Length firstCol = itCol.next();
        Length secondCol = itCol.next();
        assertFalse(itCol.hasNext());
        assertThrows(NoSuchElementException.class, itCol::next);
        assertAll(() -> assertEquals(1.0, firstCol.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS),
                () -> assertEquals(2.0, secondCol.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS),
                () -> assertEquals(v.get(1).si(), firstCol.si(), EPS), () -> assertEquals(v.get(2).si(), secondCol.si(), EPS));

        Vector2.Row<Length, Length.Unit> w = row(1.0, 2.0, Length.Unit.km); // SI: 1000, 2000
        Iterator<Length> itRow = w.iterator();
        assertTrue(itRow.hasNext());
        Length firstRow = itRow.next();
        Length secondRow = itRow.next();
        assertFalse(itRow.hasNext());
        assertThrows(NoSuchElementException.class, itRow::next);
        assertAll(() -> assertEquals(1.0, firstRow.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS),
                () -> assertEquals(2.0, secondRow.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS),
                () -> assertEquals(w.get(1).si(), firstRow.si(), EPS), () -> assertEquals(w.get(2).si(), secondRow.si(), EPS));
    }

    /**
     * Verify {@link VectorOps#getScalarArray()} returns a Q[] (not Object[]) with the correct order and values.
     */
    @Test
    @DisplayName("getScalarArray() returns Q[] with x,y in order")
    public void testGetScalarArray()
    {
        Vector2.Row<Length, Length.Unit> v = row(3.0, 4.0, Length.Unit.m);
        Length[] arrRow = v.getScalarArray();
        assertAll(() -> assertEquals(2, arrRow.length), () -> assertInstanceOf(Length.class, arrRow[0]),
                () -> assertInstanceOf(Length.class, arrRow[1]), () -> assertEquals(3.0, arrRow[0].si(), EPS),
                () -> assertEquals(4.0, arrRow[1].si(), EPS));

        Vector2.Col<Length, Length.Unit> w = col(3.0, 4.0, Length.Unit.m);
        Length[] arrCol = w.getScalarArray();
        assertAll(() -> assertEquals(2, arrCol.length), () -> assertInstanceOf(Length.class, arrCol[0]),
                () -> assertInstanceOf(Length.class, arrCol[1]), () -> assertEquals(3.0, arrCol[0].si(), EPS),
                () -> assertEquals(4.0, arrCol[1].si(), EPS));
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
        Vector2.Row<Length, Length.Unit> r = row(1.0, 2.0, Length.Unit.m);
        Vector2.Col<Length, Length.Unit> c = col(1.0, 2.0, Length.Unit.m);
        assertAll(() -> assertEquals(1, r.rows()), () -> assertEquals(2, r.cols()), () -> assertFalse(r.isColumnVector()),
                () -> assertEquals(2, c.rows()), () -> assertEquals(1, c.cols()), () -> assertTrue(c.isColumnVector()));

        Vector2.Col<Length, Length.Unit> rt = r.transpose();
        Vector2.Row<Length, Length.Unit> ct = c.transpose();
        assertAll(() -> assertArrayEquals(r.si(), rt.si(), EPS, "Row→Col preserves SI"),
                () -> assertEquals(r.getDisplayUnit(), rt.getDisplayUnit()),
                () -> assertArrayEquals(c.si(), ct.si(), EPS, "Col→Row preserves SI"),
                () -> assertEquals(c.getDisplayUnit(), ct.getDisplayUnit()));
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
        Vector2.Row<Length, Length.Unit> a = row(1.0, -2.0, Length.Unit.m); // SI: 1, -2
        Vector2.Row<Length, Length.Unit> b = row(0.5, 3.0, Length.Unit.m); // SI: 0.5, 3
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
        Vector2.Col<Length, Length.Unit> a = col(1.0, -2.0, Length.Unit.m); // SI: 1, -2
        Vector2.Col<Length, Length.Unit> b = col(0.5, 3.0, Length.Unit.m); // SI: 0.5, 3
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
     * Verify statistics: min, max, mean, median, mode, sum.
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/mode/sum")
    public void testStatistics()
    {
        Vector2.Col<Length, Length.Unit> v = col(1.0, 3.0, Length.Unit.m); // SI: 1, 3
        assertAll(() -> assertEquals(1.0, v.min().si(), EPS), () -> assertEquals(3.0, v.max().si(), EPS),
                () -> assertEquals(2.0, v.mean().si(), EPS),
                () -> assertEquals(2.0, v.median().si(), EPS, "median of 2-point set = mean"),
                () -> assertEquals(3.0, v.mode().si(), EPS, "mode defaults to max"),
                () -> assertEquals(4.0, v.sum().si(), EPS));

        Vector2.Row<Length, Length.Unit> w = row(1.0, 3.0, Length.Unit.m); // SI: 1, 3
        assertAll(() -> assertEquals(1.0, w.min().si(), EPS), () -> assertEquals(3.0, w.max().si(), EPS),
                () -> assertEquals(2.0, w.mean().si(), EPS),
                () -> assertEquals(2.0, w.median().si(), EPS, "median of 2-point set = mean"),
                () -> assertEquals(3.0, w.mode().si(), EPS, "mode defaults to max"),
                () -> assertEquals(4.0, w.sum().si(), EPS));
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
        Vector2.Row<Length, Length.Unit> v = row(3.0, 4.0, Length.Unit.m); // SI: 3, 4
        // L2 = 5; Linf = 4; L1 = 7; Lp(p=3) = (3^3 + 4^3)^(1/3)
        assertAll(() -> assertEquals(v.normL2().si(), v.norm().si(), EPS, "norm() delegates to normL2()"),
                () -> assertEquals(7.0, v.normL1().si(), EPS, "L1 = |3| + |4| = 7"),
                () -> assertEquals(5.0, v.normL2().si(), EPS),
                () -> assertEquals(Math.cbrt(27.0 + 64.0), v.normLp(3).si(), EPS),
                () -> assertEquals(4.0, v.normLinf().si(), EPS));

        Vector2.Col<Length, Length.Unit> w = col(3.0, 4.0, Length.Unit.m); // SI: 3, 4
        // L2 = 5; Linf = 4; L1 = 7; Lp(p=3) = (3^3 + 4^3)^(1/3)
        assertAll(() -> assertEquals(w.normL2().si(), w.norm().si(), EPS, "norm() delegates to normL2()"),
                () -> assertEquals(7.0, w.normL1().si(), EPS, "L1 = |3| + |4| = 7"),
                () -> assertEquals(5.0, w.normL2().si(), EPS),
                () -> assertEquals(Math.cbrt(27.0 + 64.0), w.normLp(3).si(), EPS),
                () -> assertEquals(4.0, w.normLinf().si(), EPS));
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
        Vector2.Col<Length, Length.Unit> a = col(2.0, 4.0, Length.Unit.m); // SI: 2, 4
        Vector2.Col<Length, Length.Unit> b = col(1.0, 2.0, Length.Unit.km); // SI: 1000, 2000

        // invert
        Vector2.Col<SIQuantity, SIUnit> inv = a.invertElements();
        assertArrayEquals(new double[] {0.5, 0.25}, inv.si(), EPS);
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit should be inverted");

        // elementwise multiply → SI: [2*1000, 4*2000] = [2000, 8000]; unit: m ⊕ km
        Vector2.Col<SIQuantity, SIUnit> mul = a.multiplyElements(b);
        assertAll(() -> assertArrayEquals(new double[] {2000.0, 8000.0}, mul.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit()));

        // elementwise divide → SI: [2/1000, 4/2000] = [0.002, 0.002]; unit: m ⊖ km
        Vector2.Col<SIQuantity, SIUnit> div = a.divideElements(b);
        assertAll(() -> assertArrayEquals(new double[] {0.002, 0.002}, div.si(), EPS),
                () -> assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit()));
    }

    /**
     * Verify Hadamard element-wise operations and correct unit composition. If the implementation derives the unit from
     * x().siUnit() instead of the vector's display unit, the test will fail (by design) for Row vector.
     */
    @Test
    @DisplayName("Hadamard: invertElements / multiplyElements / divideElements + unit composition, Row")
    public void testHadamardRow()
    {
        Vector2.Row<Length, Length.Unit> a = row(2.0, 4.0, Length.Unit.m); // SI: 2, 4
        Vector2.Row<Length, Length.Unit> b = row(1.0, 2.0, Length.Unit.km); // SI: 1000, 2000

        // invert
        Vector2.Row<SIQuantity, SIUnit> inv = a.invertElements();
        assertArrayEquals(new double[] {0.5, 0.25}, inv.si(), EPS);
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit should be inverted");

        // elementwise multiply → SI: [2*1000, 4*2000] = [2000, 8000]; unit: m ⊕ km
        Vector2.Row<SIQuantity, SIUnit> mul = a.multiplyElements(b);
        assertAll(() -> assertArrayEquals(new double[] {2000.0, 8000.0}, mul.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit()));

        // elementwise divide → SI: [2/1000, 4/2000] = [0.002, 0.002]; unit: m ⊖ km
        Vector2.Row<SIQuantity, SIUnit> div = a.divideElements(b);
        assertAll(() -> assertArrayEquals(new double[] {0.002, 0.002}, div.si(), EPS),
                () -> assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit()));
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
        Vector2.Row<Length, Length.Unit> r = row(1.0, 2.0, Length.Unit.m); // SI: [1, 2]
        Vector2.Col<Length, Length.Unit> c = col(5.0, 6.0, Length.Unit.km); // SI: [5000, 6000]
        SIQuantity dot = r.multiply(c); // 1*5000 + 2*6000 = 17000
        assertAll(() -> assertEquals(17_000.0, dot.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), dot.siUnit()));
    }

    /**
     * Verify Col⋅Row = 2x2 matrix with plus-unit composition.
     */
    @Test
    @DisplayName("Col · Row → Matrix2x2 with unit composition")
    public void testColTimesRow()
    {
        Vector2.Col<Length, Length.Unit> c = col(1.0, 2.0, Length.Unit.km); // SI: [1000, 2000]
        Vector2.Row<Length, Length.Unit> r = row(3.0, 4.0, Length.Unit.m); // SI: [3, 4]
        Matrix2x2<SIQuantity, SIUnit> m = c.multiply(r);
        // Outer product:
        // [1000*3, 1000*4,
        // 2000*3, 2000*4] = [3000, 4000, 6000, 8000]
        assertAll(() -> assertArrayEquals(new double[] {3000.0, 4000.0, 6000.0, 8000.0}, m.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.km.siUnit(), Length.Unit.m.siUnit()), m.getDisplayUnit().siUnit()));
    }

    /**
     * Verify Row⋅Matrix2x2 = Col with plus-unit composition.
     */
    @Test
    @DisplayName("Row · Matrix2x2 → Col with unit composition")
    public void testRowTimesMatrix()
    {
        Vector2.Row<Length, Length.Unit> r = row(1.0, 2.0, Length.Unit.m); // [1,2]
        Matrix2x2<Length, Length.Unit> a = Matrix2x2.of(new double[] {5.0, 6.0, 7.0, 8.0}, Length.Unit.km); // B in km
        Vector2.Col<SIQuantity, SIUnit> res = r.multiply(a); // [1,2]*[[5k,6k],[7k,8k]] = [19k, 22k] ⇒ SI [19000,22000]
        assertAll(() -> assertArrayEquals(new double[] {19_000.0, 22_000.0}, res.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), res.getDisplayUnit()));
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
        Vector2.Col<Length, Length.Unit> v = col(1.0, 2.0, Length.Unit.km);
        Vector2.Col<Length, Length.Unit> asMetersCol = v.as(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, asMetersCol.getDisplayUnit()),
                () -> assertArrayEquals(v.si(), asMetersCol.si(), EPS, "SI storage must be unchanged"));

        // seconds has different SI dimension; must fail
        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> v.as(second));

        Vector2.Row<Length, Length.Unit> w = row(1.0, 2.0, Length.Unit.km);
        Vector2.Row<Length, Length.Unit> asMetersRow = w.as(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, asMetersRow.getDisplayUnit()),
                () -> assertArrayEquals(w.si(), asMetersRow.si(), EPS, "SI storage must be unchanged"));

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
        Vector2.Row<Length, Length.Unit> r1 = row(1000.0, 2000.0, Length.Unit.m);
        Vector2.Row<Length, Length.Unit> r2 = row(1.0, 2.0, Length.Unit.km).setDisplayUnit(Length.Unit.m);
        Vector2.Col<Length, Length.Unit> c1 = col(1.0, 2.0, Length.Unit.m);

        assertAll(() -> assertEquals(r1, r1, "reflexive"), () -> assertEquals(r1.hashCode(), r1.hashCode(), "hash stable"),
                () -> assertEquals(r1, r2, "equal SI values despite different original display units"),
                () -> assertEquals(r1.hashCode(), r2.hashCode(), "hash equal for equal SI"),
                () -> assertNotEquals(r1, c1, "Row not equal to Col (different classes)"), () -> assertNotEquals(r1, null),
                () -> assertNotEquals(r1, "not a vector"));
    }

    /**
     * Verify toString() and toString(unit) contain orientation tag and unit abbreviation.
     */
    @Test
    @DisplayName("toString() and toString(unit)")
    public void testToString()
    {
        Vector2.Col<Length, Length.Unit> c = col(1.0, 2.0, Length.Unit.km);
        String s1C = c.toString();
        String s2C = c.toString(Length.Unit.m);
        assertAll(() -> assertTrue(s1C.startsWith("Col["), "orientation tag"),
                () -> assertTrue(s1C.contains("km"), "contains display unit abbreviation"),
                () -> assertTrue(s2C.contains("m"), "toString(withUnit) uses the provided unit"));

        Vector2.Row<Length, Length.Unit> r = row(1.0, 2.0, Length.Unit.km);
        String s1R = r.toString();
        String s2R = r.toString(Length.Unit.m);
        assertAll(() -> assertTrue(s1R.startsWith("Row["), "orientation tag"),
                () -> assertTrue(s1R.contains("km"), "contains display unit abbreviation"),
                () -> assertTrue(s2R.contains("m"), "toString(withUnit) uses the provided unit"));
    }

    /**
     * Verify setDisplayUnit returns {@code this} (fluent) and that isRelative aligns with Length semantics.
     */
    @Test
    @DisplayName("setDisplayUnit() fluent and isRelative()")
    public void testFluentAndRelative()
    {
        Vector2.Row<Length, Length.Unit> r = row(1.0, 2.0, Length.Unit.km);
        Vector2.Row<Length, Length.Unit> returnedR = r.setDisplayUnit(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, r.getDisplayUnit()),
                () -> assertEquals(r, returnedR, "fluent returns this"),
                () -> assertTrue(r.isRelative(), "Length is a relative quantity"));

        Vector2.Col<Length, Length.Unit> c = col(1.0, 2.0, Length.Unit.km);
        Vector2.Col<Length, Length.Unit> returnedC = c.setDisplayUnit(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, c.getDisplayUnit()),
                () -> assertEquals(c, returnedC, "fluent returns this"),
                () -> assertTrue(c.isRelative(), "Length is a relative quantity"));
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        Vector2.Col<Length, Length.Unit> r = col(1.0, 2.0, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        Vector2.Col<Speed, Speed.Unit> sr = r.divideElements(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.get(1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.get(2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideElements(d).as(Area.Unit.m2));

        Vector2.Row<Length, Length.Unit> c = row(1.0, 2.0, Length.Unit.km);
        Vector2.Row<Speed, Speed.Unit> sc = c.divideElements(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sc.getDisplayUnit());
        assertEquals(0.5, sc.get(1).getInUnit(), 1E-6);
        assertEquals(1.0, sc.get(2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> c.divideElements(d).as(Area.Unit.m2));
    }
}
