package org.djunits.vecmat.d3;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.unit.si.SIUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Vector3} (both {@link Vector3.Col} and {@link Vector3.Row}) with concrete quantity {@link Length} and
 * unit {@link org.djunits.quantity.Length.Unit}. The tests encode the correct behavior and are intended to fail when the
 * implementation contains defects (e.g., wrong factories, wrong rows/cols, etc.).
 * <p>
 * Coverage goals:
 * </p>
 * <ul>
 * <li>Constructors, factories, and display→SI conversion</li>
 * <li>Accessors: x(), y(), z(), xSi(), ySi(), zSi(), si(), get(index)</li>
 * <li>Iterator and getScalarArray()</li>
 * <li>Vector algebra: add/sub (Q & V), negate, abs, scaleBy</li>
 * <li>Normed: norm(), normL1(), normL2(), normLp(p), normLinf()</li>
 * <li>Orientation &amp; shape: rows(), cols(), isColumnVector()</li>
 * <li>Transposition Row&lt;↔&gt;Col</li>
 * <li>Hadamard operations (invertElements, multiplyElements, divideElements) + unit composition</li>
 * <li>Linear algebra: Row⋅Col, Col⋅Row, Row⋅Matrix3x3</li>
 * <li>as(targetUnit) positive and negative branches</li>
 * <li>equals, hashCode, toString, setDisplayUnit, isRelative</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class Vector3Test
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
     * @param zInUnit z value, expressed in the given unit
     * @param unit the display unit
     * @return a Vector3.Col&lt;Length, Length.Unit&gt;
     */
    private static Vector3.Col<Length, Length.Unit> col(final double xInUnit, final double yInUnit, final double zInUnit,
            final Length.Unit unit)
    {
        return new Vector3.Col<>(xInUnit, yInUnit, zInUnit, unit);
    }

    /**
     * Create a Length-typed column vector using the given display values and unit.
     * @param xInUnit x value, expressed in the given unit
     * @param yInUnit y value, expressed in the given unit
     * @param zInUnit z value, expressed in the given unit
     * @param unit the display unit
     * @return a Vector3.Row&lt;Length, Length.Unit&gt;
     */
    private static Vector3.Row<Length, Length.Unit> row(final double xInUnit, final double yInUnit, final double zInUnit,
            final Length.Unit unit)
    {
        return new Vector3.Row<>(xInUnit, yInUnit, zInUnit, unit);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors, factories, SI conversion
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify that constructor interprets values in display unit and stores SI internally (Col). */
    @Test
    @DisplayName("Constructor conversion: display unit → SI storage (Col)")
    public void testConstructorAndSiStorageCol()
    {
        Vector3.Col<Length, Length.Unit> c = col(1.0, 2.0, 3.0, Length.Unit.km); // SI: 1000, 2000, 3000
        assertAll(() -> assertArrayEquals(new double[] {1000.0, 2000.0, 3000.0}, c.si(), EPS),
                () -> assertEquals(Length.Unit.km, c.getDisplayUnit()));

        // Factory .of for Col should behave the same as constructor (no double conversion!)
        Vector3.Col<Length, Length.Unit> cf = Vector3.Col.of(0.1, 20.0, 3000.0, Length.Unit.cm); // 0.001m, 0.2m, 30m
        assertArrayEquals(new double[] {0.001, 0.2, 30.0}, cf.si(), EPS);
    }

    /** Verify that constructor interprets values in display unit and stores SI internally (Row). */
    @Test
    @DisplayName("Constructor conversion: display unit → SI storage (Row)")
    public void testConstructorAndSiStorageRow()
    {
        Vector3.Row<Length, Length.Unit> r = row(1.0, 2.0, 3.0, Length.Unit.km); // SI: 1000, 2000, 3000
        assertAll(() -> assertArrayEquals(new double[] {1000.0, 2000.0, 3000.0}, r.si(), EPS),
                () -> assertEquals(Length.Unit.km, r.getDisplayUnit()));

        // Factory .of for Row should behave the same as constructor (no double conversion!)
        Vector3.Row<Length, Length.Unit> rf = Vector3.Row.of(0.1, 20.0, 3000.0, Length.Unit.cm); // 0.001m, 0.2m, 30m
        assertArrayEquals(new double[] {0.001, 0.2, 30.0}, rf.si(), EPS);
    }

    /** Verify instantiateSi(double[]) enforces length=3 and delegates (Col). */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=3 and delegates (Col)")
    public void testInstantiateSiArrayCol()
    {
        Vector3.Col<Length, Length.Unit> c = col(1.0, 2.0, 3.0, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> c.instantiateSi(new double[] {1.0, 2.0}), "Wrong length must throw");
        Vector3.Col<Length, Length.Unit> d = c.instantiateSi(new double[] {10.0, 20.0, 30.0});
        assertArrayEquals(new double[] {10.0, 20.0, 30.0}, d.si(), EPS);
        assertEquals(c.getDisplayUnit(), d.getDisplayUnit());
    }

    /** Verify instantiateSi(double[]) enforces length=3 and delegates (Row). */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=3 and delegates (Row)")
    public void testInstantiateSiArrayRow()
    {
        Vector3.Row<Length, Length.Unit> r = row(1.0, 2.0, 3.0, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> r.instantiateSi(new double[] {1.0, 2.0}), "Wrong length must throw");
        Vector3.Row<Length, Length.Unit> s = r.instantiateSi(new double[] {10.0, 20.0, 30.0});
        assertArrayEquals(new double[] {10.0, 20.0, 30.0}, s.si(), EPS);
        assertEquals(r.getDisplayUnit(), s.getDisplayUnit());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Accessors, indexing, iterator, scalar array
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify size, x/y/z quantities, xSi/ySi/zSi, si() copy semantics, and get(index) branches (Row). */
    @Test
    @DisplayName("size, x/y/z (Q), xSi/ySi/zSi, si() copy, and get(index) (Row)")
    public void testAccessorsAndIndexingRow()
    {
        Vector3.Row<Length, Length.Unit> v = row(0.5, 200.0, 3.0, Length.Unit.cm); // 0.005, 2.0, 0.03
        assertAll(() -> assertEquals(3, v.size()), () -> assertEquals(0.005, v.xSi(), EPS),
                () -> assertEquals(2.0, v.ySi(), EPS), () -> assertEquals(0.03, v.zSi(), EPS),
                () -> assertEquals(0.5, v.get(1).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS),
                () -> assertEquals(200.0, v.get(2).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS),
                () -> assertEquals(3.0, v.get(3).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS));

        double[] siCopy = v.si();
        siCopy[0] = 12345.0;
        assertEquals(0.005, v.xSi(), EPS, "internal storage not affected");

        assertThrows(IndexOutOfBoundsException.class, () -> v.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(4));
    }

    /** Verify size, x/y/z quantities, xSi/ySi/zSi, si(), get(index) (Col). */
    @Test
    @DisplayName("size, x/y/z (Q), xSi/ySi/zSi, si() copy, and get(index) (Col)")
    public void testAccessorsAndIndexingCol()
    {
        Vector3.Col<Length, Length.Unit> v = col(0.5, 200.0, 3.0, Length.Unit.cm); // 0.005, 2.0, 0.03
        assertAll(() -> assertEquals(3, v.size()), () -> assertEquals(0.005, v.xSi(), EPS),
                () -> assertEquals(2.0, v.ySi(), EPS), () -> assertEquals(0.03, v.zSi(), EPS),
                () -> assertEquals(0.5, v.get(1).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS),
                () -> assertEquals(200.0, v.get(2).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS),
                () -> assertEquals(3.0, v.get(3).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS));

        double[] siCopy = v.si();
        siCopy[1] = 12345.0;
        assertEquals(2.0, v.ySi(), EPS, "internal storage not affected");

        assertThrows(IndexOutOfBoundsException.class, () -> v.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(4));
    }

    /** Verify iterator order and unit for Row and Col. */
    @Test
    @DisplayName("iterator() yields Q in display unit, in order x,y,z")
    public void testIterator()
    {
        Vector3.Row<Length, Length.Unit> r = row(1.0, 2.0, 3.0, Length.Unit.km); // 1000, 2000, 3000
        Iterator<Length> itR = r.iterator();
        Length rx = itR.next();
        Length ry = itR.next();
        Length rz = itR.next();
        assertFalse(itR.hasNext());
        assertThrows(NoSuchElementException.class, itR::next);
        assertAll(() -> assertEquals(1.0, rx.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS),
                () -> assertEquals(2.0, ry.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS),
                () -> assertEquals(3.0, rz.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS));

        Vector3.Col<Length, Length.Unit> c = col(1.0, 2.0, 3.0, Length.Unit.km);
        Iterator<Length> itC = c.iterator();
        assertDoesNotThrow(itC::next);
        assertDoesNotThrow(itC::next);
        assertDoesNotThrow(itC::next);
        assertFalse(itC.hasNext());
    }

    /** Verify getScalarArray returns a Q[3] with values in order x,y,z (Row and Col). */
    @Test
    @DisplayName("getScalarArray() returns Q[3] with x,y,z in order")
    public void testGetScalarArray()
    {
        Vector3.Row<Length, Length.Unit> r = row(3.0, 4.0, 5.0, Length.Unit.m);
        Length[] arrR = r.getScalarArray();
        assertAll(() -> assertEquals(3, arrR.length), () -> assertInstanceOf(Length.class, arrR[0]),
                () -> assertInstanceOf(Length.class, arrR[1]), () -> assertInstanceOf(Length.class, arrR[2]),
                () -> assertEquals(3.0, arrR[0].si(), EPS), () -> assertEquals(4.0, arrR[1].si(), EPS),
                () -> assertEquals(5.0, arrR[2].si(), EPS));

        Vector3.Col<Length, Length.Unit> c = col(3.0, 4.0, 5.0, Length.Unit.m);
        Length[] arrC = c.getScalarArray();
        assertAll(() -> assertEquals(3, arrC.length), () -> assertEquals(3.0, arrC[0].si(), EPS),
                () -> assertEquals(4.0, arrC[1].si(), EPS), () -> assertEquals(5.0, arrC[2].si(), EPS));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Orientation & shape; transposition
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify rows(), cols(), isColumnVector(), and transpose for both Row and Col. */
    @Test
    @DisplayName("rows/cols/isColumnVector and transpose()")
    public void testShapeAndTranspose()
    {
        Vector3.Row<Length, Length.Unit> r = row(1.0, 2.0, 3.0, Length.Unit.m);
        Vector3.Col<Length, Length.Unit> c = col(1.0, 2.0, 3.0, Length.Unit.m);
        assertAll(() -> assertEquals(1, r.rows()), () -> assertEquals(3, r.cols()), () -> assertFalse(r.isColumnVector()),
                () -> assertEquals(3, c.rows()), () -> assertEquals(1, c.cols()), () -> assertTrue(c.isColumnVector()));

        Vector3.Col<Length, Length.Unit> rt = r.transpose();
        Vector3.Row<Length, Length.Unit> ct = c.transpose();
        assertAll(() -> assertArrayEquals(r.si(), rt.si(), EPS), () -> assertEquals(r.getDisplayUnit(), rt.getDisplayUnit()),
                () -> assertArrayEquals(c.si(), ct.si(), EPS), () -> assertEquals(c.getDisplayUnit(), ct.getDisplayUnit()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Vector algebra: add/sub (Q & V), negate, abs, scaleBy
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify add(Q), subtract(Q), add(V), subtract(V), negate(), abs(), scaleBy() (Row). */
    @Test
    @DisplayName("Vector algebra: add/sub (Q & V), negate, abs, scaleBy (Row)")
    public void testBasicAlgebraRow()
    {
        Vector3.Row<Length, Length.Unit> a = row(1.0, -2.0, 3.0, Length.Unit.m); // 1, -2, 3
        Vector3.Row<Length, Length.Unit> b = row(0.5, 3.0, -1.0, Length.Unit.m); // 0.5, 3, -1
        Length inc = Length.of(2.0, "m");

        assertArrayEquals(new double[] {1.5, 1.0, 2.0}, a.add(b).si(), EPS);
        assertArrayEquals(new double[] {0.5, -5.0, 4.0}, a.subtract(b).si(), EPS);

        assertArrayEquals(new double[] {3.0, 0.0, 5.0}, a.add(inc).si(), EPS);
        assertArrayEquals(new double[] {-1.0, -4.0, 1.0}, a.subtract(inc).si(), EPS);

        assertArrayEquals(new double[] {-1.0, 2.0, -3.0}, a.negate().si(), EPS);
        assertArrayEquals(new double[] {1.0, 2.0, 3.0}, a.abs().si(), EPS);
        assertArrayEquals(new double[] {2.0, -4.0, 6.0}, a.scaleBy(2.0).si(), EPS);
    }

    /** Verify add(Q), subtract(Q), add(V), subtract(V), negate(), abs(), scaleBy() (Col). */
    @Test
    @DisplayName("Vector algebra: add/sub (Q & V), negate, abs, scaleBy (Col)")
    public void testBasicAlgebraCol()
    {
        Vector3.Col<Length, Length.Unit> a = col(1.0, -2.0, 3.0, Length.Unit.m);
        Vector3.Col<Length, Length.Unit> b = col(0.5, 3.0, -1.0, Length.Unit.m);
        Length inc = Length.of(2.0, "m");

        assertArrayEquals(new double[] {1.5, 1.0, 2.0}, a.add(b).si(), EPS);
        assertArrayEquals(new double[] {0.5, -5.0, 4.0}, a.subtract(b).si(), EPS);

        assertArrayEquals(new double[] {3.0, 0.0, 5.0}, a.add(inc).si(), EPS);
        assertArrayEquals(new double[] {-1.0, -4.0, 1.0}, a.subtract(inc).si(), EPS);

        assertArrayEquals(new double[] {-1.0, 2.0, -3.0}, a.negate().si(), EPS);
        assertArrayEquals(new double[] {1.0, 2.0, 3.0}, a.abs().si(), EPS);
        assertArrayEquals(new double[] {2.0, -4.0, 6.0}, a.scaleBy(2.0).si(), EPS);
    }

    /**
     * Verify statistics for a Row vector: min, max, mean, median, mode, and sum.
     * <p>
     * Uses unsorted input to ensure {@code median()} is order-independent. All assertions are checked against SI values, since
     * the vector stores SI internally and {@code Q.si()} returns the SI magnitude.
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/mode/sum (Row)")
    public void testStatisticsRow()
    {
        // Unsorted values in meters: [1, 3, 2] → SI: [1, 3, 2]
        Vector3.Row<Length, Length.Unit> v = row(1.0, 3.0, 2.0, Length.Unit.m);

        // Expected statistics in SI
        double expectedMin = 1.0;
        double expectedMax = 3.0;
        double expectedMean = (1.0 + 3.0 + 2.0) / 3.0; // = 2.0
        double expectedMedian = 2.0; // middle of {1,2,3}
        double expectedMode = expectedMax; // default mode() returns max()
        double expectedSum = 6.0;

        assertAll(() -> assertEquals(expectedMin, v.min().si(), EPS, "min"),
                () -> assertEquals(expectedMax, v.max().si(), EPS, "max"),
                () -> assertEquals(expectedMean, v.mean().si(), EPS, "mean"),
                () -> assertEquals(expectedMedian, v.median().si(), EPS, "median"),
                () -> assertEquals(expectedMode, v.mode().si(), EPS, "mode defaults to max"),
                () -> assertEquals(expectedSum, v.sum().si(), EPS, "sum"));
    }

    /**
     * Verify statistics for a Col vector: min, max, mean, median, mode, and sum.
     * <p>
     * Values are provided in kilometers to exercise unit conversion, but assertions are made on SI values (meters). Input is
     * deliberately unsorted to verify {@code median()}.
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/mode/sum (Col)")
    public void testStatisticsCol()
    {
        // Unsorted values in kilometers: [0.5, 2.0, 1.5] → SI (m): [500, 2000, 1500]
        Vector3.Col<Length, Length.Unit> v = col(0.5, 2.0, 1.5, Length.Unit.km);

        // Expected statistics in SI (meters)
        double s1 = 500.0;
        double s2 = 2000.0;
        double s3 = 1500.0;
        double expectedMin = 500.0;
        double expectedMax = 2000.0;
        double expectedSum = s1 + s2 + s3; // 4000.0
        double expectedMean = expectedSum / 3.0; // 1333.333...
        double expectedMedian = 1500.0; // middle of {500,1500,2000}
        double expectedMode = expectedMax; // default mode() returns max()

        assertAll(() -> assertEquals(expectedMin, v.min().si(), EPS, "min"),
                () -> assertEquals(expectedMax, v.max().si(), EPS, "max"),
                () -> assertEquals(expectedMean, v.mean().si(), EPS, "mean"),
                () -> assertEquals(expectedMedian, v.median().si(), EPS, "median"),
                () -> assertEquals(expectedMode, v.mode().si(), EPS, "mode defaults to max"),
                () -> assertEquals(expectedSum, v.sum().si(), EPS, "sum"));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Normed
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify norm(), normL1(), normL2(), normLp(p), normLinf() (Row & Col). */
    @Test
    @DisplayName("Normed: norm(), normL1(), normL2(), normLp(p), normLinf()")
    public void testNorms()
    {
        Vector3.Row<Length, Length.Unit> r = row(1.0, -2.0, 2.0, Length.Unit.m); // |1|+|−2|+|2|=5; L2 = 3; L∞=2
        assertAll(() -> assertEquals(r.normL2().si(), r.norm().si(), EPS), () -> assertEquals(5.0, r.normL1().si(), EPS),
                () -> assertEquals(3.0, r.normL2().si(), EPS),
                () -> assertEquals(Math.cbrt(1.0 + 8.0 + 8.0), r.normLp(3).si(), EPS),
                () -> assertEquals(2.0, r.normLinf().si(), EPS));

        Vector3.Col<Length, Length.Unit> c = col(3.0, 4.0, 12.0, Length.Unit.m); // L1=19; L2=13; L∞=12
        assertAll(() -> assertEquals(c.normL2().si(), c.norm().si(), EPS), () -> assertEquals(19.0, c.normL1().si(), EPS),
                () -> assertEquals(13.0, c.normL2().si(), EPS),
                () -> assertEquals(Math.cbrt(27.0 + 64.0 + 1728.0), c.normLp(3).si(), EPS),
                () -> assertEquals(12.0, c.normLinf().si(), EPS));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Hadamard
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify Hadamard operations and result-unit composition (Col). */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition (Col)")
    public void testHadamardCol()
    {
        Vector3.Col<Length, Length.Unit> a = col(2.0, 4.0, 8.0, Length.Unit.m); // 2, 4, 8
        Vector3.Col<Length, Length.Unit> b = col(1.0, 2.0, 3.0, Length.Unit.km); // 1000, 2000, 3000

        var inv = a.invertElements();
        assertArrayEquals(new double[] {0.5, 0.25, 0.125}, inv.si(), EPS);
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit());

        var mul = a.multiplyElements(b);
        assertAll(() -> assertArrayEquals(new double[] {2000.0, 8000.0, 24000.0}, mul.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit()));

        var div = a.divideElements(b);
        assertAll(() -> assertArrayEquals(new double[] {0.002, 0.002, 0.0026666666666666666}, div.si(), 1e-15),
                () -> assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit()));
    }

    /** Verify Hadamard operations and result-unit composition (Row). */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition (Row)")
    public void testHadamardRow()
    {
        Vector3.Row<Length, Length.Unit> a = row(2.0, 4.0, 8.0, Length.Unit.m);
        Vector3.Row<Length, Length.Unit> b = row(1.0, 2.0, 3.0, Length.Unit.km);

        var inv = a.invertElements();
        assertArrayEquals(new double[] {0.5, 0.25, 0.125}, inv.si(), EPS);
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit());

        var mul = a.multiplyElements(b);
        assertAll(() -> assertArrayEquals(new double[] {2000.0, 8000.0, 24000.0}, mul.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit()));

        var div = a.divideElements(b);
        assertAll(() -> assertArrayEquals(new double[] {0.002, 0.002, 0.0026666666666666666}, div.si(), 1e-15),
                () -> assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Linear algebra
    // -----------------------------------------------------------------------------------------------------------------

    /** Row·Col → scalar; unit composition with SIUnit.plus. */
    @Test
    @DisplayName("Row · Col → SIQuantity with unit composition")
    public void testRowDotCol()
    {
        Vector3.Row<Length, Length.Unit> r = row(1.0, 2.0, 3.0, Length.Unit.m); // 1,2,3
        Vector3.Col<Length, Length.Unit> c = col(4.0, 5.0, 6.0, Length.Unit.km); // 4k,5k,6k
        SIQuantity dot = r.multiply(c); // 1*4000 + 2*5000 + 3*6000 = 32_000
        assertAll(() -> assertEquals(32_000.0, dot.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), dot.siUnit()));
    }

    /** Col·Row → Matrix3x3 with plus-unit composition. */
    @Test
    @DisplayName("Col · Row → Matrix3x3 with unit composition")
    public void testColTimesRow()
    {
        Vector3.Col<Length, Length.Unit> c = col(1.0, 2.0, 3.0, Length.Unit.km); // 1k,2k,3k
        Vector3.Row<Length, Length.Unit> r = row(7.0, 8.0, 9.0, Length.Unit.m); // 7,8,9
        Matrix3x3<SIQuantity, SIUnit> m = c.multiply(r);
        double[] expected = {1000.0 * 7.0, 1000.0 * 8.0, 1000.0 * 9.0, 2000.0 * 7.0, 2000.0 * 8.0, 2000.0 * 9.0, 3000.0 * 7.0,
                3000.0 * 8.0, 3000.0 * 9.0};
        assertAll(() -> assertArrayEquals(expected, m.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.km.siUnit(), Length.Unit.m.siUnit()), m.getDisplayUnit().siUnit()));
    }

    /** Row·Matrix3x3 → Col with plus-unit composition. */
    @Test
    @DisplayName("Row · Matrix3x3 → Col with unit composition")
    public void testRowTimesMatrix()
    {
        Vector3.Row<Length, Length.Unit> r = row(1.0, 2.0, 3.0, Length.Unit.m); // [1,2,3]
        Matrix3x3<Length, Length.Unit> a =
                Matrix3x3.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}, Length.Unit.km); // each entry in km
        Vector3.Col<SIQuantity, SIUnit> prod = r.multiply(a); // result SI in m*km
        double[] expected = {30_000.0, 36_000.0, 42_000.0};
        assertAll(() -> assertArrayEquals(expected, prod.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), prod.getDisplayUnit()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // as(targetUnit)
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify as(targetUnit) succeeds for m↔km and fails for mismatched SI unit (Row & Col). */
    @Test
    @DisplayName("as(targetUnit) success for m↔km and failure for mismatched SI unit")
    public void testAs()
    {
        Vector3.Col<Length, Length.Unit> c = col(1.0, 2.0, 3.0, Length.Unit.km);
        Vector3.Col<Length, Length.Unit> cm = c.as(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, cm.getDisplayUnit()), () -> assertArrayEquals(c.si(), cm.si(), EPS));

        Vector3.Row<Length, Length.Unit> r = row(1.0, 2.0, 3.0, Length.Unit.km);
        Vector3.Row<Length, Length.Unit> rm = r.as(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, rm.getDisplayUnit()), () -> assertArrayEquals(r.si(), rm.si(), EPS));

        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> c.as(second));
        assertThrows(IllegalArgumentException.class, () -> r.as(second));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // equals / hashCode / toString / setDisplayUnit / isRelative
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify equals/hashCode and type sensitivity; toString contains orientation and unit; setDisplayUnit is fluent. */
    @Test
    @DisplayName("equals/hashCode, toString, setDisplayUnit, isRelative")
    public void testObjectContracts()
    {
        Vector3.Row<Length, Length.Unit> r1 = row(1000.0, 2000.0, 3000.0, Length.Unit.m);
        Vector3.Row<Length, Length.Unit> r2 = row(1.0, 2.0, 3.0, Length.Unit.km).setDisplayUnit(Length.Unit.m);
        Vector3.Col<Length, Length.Unit> c1 = col(1000.0, 2000.0, 3000.0, Length.Unit.m);

        assertAll(() -> assertEquals(r1, r1), () -> assertEquals(r1.hashCode(), r1.hashCode()),
                () -> assertEquals(r1, r2, "equal SI values despite different original display units"),
                () -> assertEquals(r1.hashCode(), r2.hashCode()), () -> assertNotEquals(r1, c1, "Row not equal to Col"),
                () -> assertNotEquals(r1, null), () -> assertNotEquals(r1, "not a vector"));

        String sRow = r1.toString();
        String sCol = c1.toString(Length.Unit.km);
        assertAll(() -> assertTrue(sRow.startsWith("Row[")), () -> assertTrue(sCol.startsWith("Col[")),
                () -> assertTrue(sCol.contains("km")));

        Vector3.Row<Length, Length.Unit> ret = r1.setDisplayUnit(Length.Unit.km);
        assertAll(() -> assertEquals(Length.Unit.km, r1.getDisplayUnit()), () -> assertEquals(r1, ret),
                () -> assertTrue(r1.isRelative(), "Length is a relative quantity"));
    }
}
