package org.djunits.vecmat.d3;

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

import org.djunits.quantity.Area;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.Speed;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.d1.Vector1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Vector3} (both {@link Vector3.Col} and {@link Vector3.Row}) with concrete quantity {@link Length} and
 * unit {@link org.djunits.quantity.Length.Unit}. The tests encode the correct behavior and are intended to fail when the
 * implementation contains defects (e.g., wrong factories, wrong rows/cols, etc.).
 * <p>
 * Coverage goals:
 * <ul>
 * <li>Constructors, factories, and display→SI conversion</li>
 * <li>Accessors: x(), y(), z(), xSi(), ySi(), zSi(), si(), get(index)</li>
 * <li>Iterator and getScalarArray()</li>
 * <li>Vector algebra: add/sub (Q &amp; V), negate, abs, scaleBy</li>
 * <li>Normed: norm(), normL1(), normL2(), normLp(p), normLinf()</li>
 * <li>Orientation &amp; shape: rows(), cols(), isColumnVector()</li>
 * <li>Transposition Row&lt;↔&gt;Col</li>
 * <li>Hadamard operations (invertElements, multiplyElements, divideElements) + unit composition</li>
 * <li>Linear algebra: Row⋅Col, Col⋅Row, Row⋅Matrix3x3</li>
 * <li>as(targetUnit) positive and negative branches</li>
 * <li>equals, hashCode, toString, setDisplayUnit, isRelative</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
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
        assertArrayEquals(new double[] {1000.0, 2000.0, 3000.0}, c.si(), EPS);
        assertEquals(Length.Unit.km, c.getDisplayUnit());

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
        assertArrayEquals(new double[] {1000.0, 2000.0, 3000.0}, r.si(), EPS);
        assertEquals(Length.Unit.km, r.getDisplayUnit());

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

        double[] newSi = {20, 30, 40};
        Vector3.Col<SIQuantity, SIUnit> csiVector = c.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", csiVector.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, csiVector.si(), EPS, "si array used as-is");
        assertEquals(20.0, csiVector.get(0, 0).si(), EPS);

        Vector3.Col<SIQuantity, SIUnit> csiVectorOf = Vector3.Col.of(20.0, 30.0, 40.0, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", csiVectorOf.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, csiVectorOf.si(), EPS, "si array used as-is");
        assertEquals(20.0, csiVectorOf.get(0, 0).si(), EPS);
        
        assertThrows(IllegalArgumentException.class,
                () -> c.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
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
        
        double[] newSi = {20, 30, 40};
        Vector3.Row<SIQuantity, SIUnit> rsiVector = r.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", rsiVector.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, rsiVector.si(), EPS, "si array used as-is");
        assertEquals(20.0, rsiVector.get(0, 0).si(), EPS);

        Vector3.Row<SIQuantity, SIUnit> rsiVectorOf = Vector3.Row.of(20.0, 30.0, 40.0, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", rsiVectorOf.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, rsiVectorOf.si(), EPS, "si array used as-is");
        assertEquals(20.0, rsiVectorOf.get(0, 0).si(), EPS);
        
        assertThrows(IllegalArgumentException.class,
                () -> r.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
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
        assertEquals(3, v.size());
        assertEquals(0.005, v.xSi(), EPS);
        assertEquals(2.0, v.ySi(), EPS);
        assertEquals(0.03, v.zSi(), EPS);

        assertEquals(0.005, v.si(0), EPS);
        assertEquals(2.0, v.si(1), EPS);
        assertEquals(0.03, v.si(2), EPS);
        assertEquals(0.5, v.get(0).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);
        assertEquals(200.0, v.get(1).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);
        assertEquals(3.0, v.get(2).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);

        assertEquals(0.005, v.msi(1), EPS);
        assertEquals(2.0, v.msi(2), EPS);
        assertEquals(0.03, v.msi(3), EPS);
        assertEquals(0.5, v.mget(1).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);
        assertEquals(200.0, v.mget(2).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);
        assertEquals(3.0, v.mget(3).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);

        double[] siCopy = v.si();
        siCopy[0] = 12345.0;
        assertEquals(0.005, v.xSi(), EPS, "internal storage not affected");

        assertThrows(IndexOutOfBoundsException.class, () -> v.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.get(3));
    }

    /** Verify size, x/y/z quantities, xSi/ySi/zSi, si(), get(index) (Col). */
    @Test
    @DisplayName("size, x/y/z (Q), xSi/ySi/zSi, si() copy, and get(index) (Col)")
    public void testAccessorsAndIndexingCol()
    {
        Vector3.Col<Length, Length.Unit> v = col(0.5, 200.0, 3.0, Length.Unit.cm); // 0.005, 2.0, 0.03
        assertEquals(3, v.size());
        assertEquals(0.005, v.xSi(), EPS);
        assertEquals(2.0, v.ySi(), EPS);
        assertEquals(0.03, v.zSi(), EPS);

        assertEquals(0.005, v.si(0), EPS);
        assertEquals(2.0, v.si(1), EPS);
        assertEquals(0.03, v.si(2), EPS);
        assertEquals(0.5, v.get(0).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);
        assertEquals(200.0, v.get(1).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);
        assertEquals(3.0, v.get(2).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);

        assertEquals(0.005, v.msi(1), EPS);
        assertEquals(2.0, v.msi(2), EPS);
        assertEquals(0.03, v.msi(3), EPS);
        assertEquals(0.5, v.mget(1).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);
        assertEquals(200.0, v.mget(2).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);
        assertEquals(3.0, v.mget(3).setDisplayUnit(Length.Unit.cm).si() * 100.0, EPS);

        // Row.si(r, c) with valid and invalid indices
        assertEquals(v.x().si(), v.si(0, 0), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, 3));

        // Row.msi(r, c) with valid and invalid indices
        assertEquals(v.x().si(), v.msi(1, 1), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(4, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 4));

        double[] siCopy = v.si();
        siCopy[1] = 12345.0;
        assertEquals(2.0, v.ySi(), EPS, "internal storage not affected");

        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(4));
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
        assertEquals(1.0, rx.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(2.0, ry.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(3.0, rz.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);

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
        assertEquals(3, arrR.length);
        assertInstanceOf(Length.class, arrR[0]);
        assertInstanceOf(Length.class, arrR[1]);
        assertInstanceOf(Length.class, arrR[2]);
        assertEquals(3.0, arrR[0].si(), EPS);
        assertEquals(4.0, arrR[1].si(), EPS);
        assertEquals(5.0, arrR[2].si(), EPS);

        Vector3.Col<Length, Length.Unit> c = col(3.0, 4.0, 5.0, Length.Unit.m);
        Length[] arrC = c.getScalarArray();
        assertEquals(3, arrC.length);
        assertEquals(3.0, arrC[0].si(), EPS);
        assertEquals(4.0, arrC[1].si(), EPS);
        assertEquals(5.0, arrC[2].si(), EPS);
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
        assertEquals(1, r.rows());
        assertEquals(3, r.cols());
        assertFalse(r.isColumnVector());
        assertEquals(3, c.rows());
        assertEquals(1, c.cols());
        assertTrue(c.isColumnVector());

        Vector3.Col<Length, Length.Unit> rt = r.transpose();
        Vector3.Row<Length, Length.Unit> ct = c.transpose();
        assertArrayEquals(r.si(), rt.si(), EPS);
        assertEquals(r.getDisplayUnit(), rt.getDisplayUnit());
        assertArrayEquals(c.si(), ct.si(), EPS);
        assertEquals(c.getDisplayUnit(), ct.getDisplayUnit());
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

        assertEquals(expectedMin, v.min().si(), EPS, "min");
        assertEquals(expectedMax, v.max().si(), EPS, "max");
        assertEquals(expectedMean, v.mean().si(), EPS, "mean");
        assertEquals(expectedMedian, v.median().si(), EPS, "median");
        assertEquals(expectedMode, v.mode().si(), EPS, "mode defaults to max");
        assertEquals(expectedSum, v.sum().si(), EPS, "sum");
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

        assertEquals(expectedMin, v.min().si(), EPS, "min");
        assertEquals(expectedMax, v.max().si(), EPS, "max");
        assertEquals(expectedMean, v.mean().si(), EPS, "mean");
        assertEquals(expectedMedian, v.median().si(), EPS, "median");
        assertEquals(expectedMode, v.mode().si(), EPS, "mode defaults to max");
        assertEquals(expectedSum, v.sum().si(), EPS, "sum");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Normed
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify norm(), normL1(), normL2(), normLp(p), normLinf() (Row &amp; Col). */
    @Test
    @DisplayName("Normed: norm(), normL1(), normL2(), normLp(p), normLinf()")
    public void testNorms()
    {
        Vector3.Row<Length, Length.Unit> r = row(1.0, -2.0, 2.0, Length.Unit.m); // |1|+|−2|+|2|=5; L2 = 3; L∞=2
        assertEquals(r.normL2().si(), r.norm().si(), EPS);
        assertEquals(5.0, r.normL1().si(), EPS);
        assertEquals(3.0, r.normL2().si(), EPS);
        assertEquals(Math.cbrt(1.0 + 8.0 + 8.0), r.normLp(3).si(), EPS);
        assertEquals(2.0, r.normLinf().si(), EPS);

        Vector3.Col<Length, Length.Unit> c = col(3.0, 4.0, 12.0, Length.Unit.m); // L1=19; L2=13; L∞=12
        assertEquals(c.normL2().si(), c.norm().si(), EPS);
        assertEquals(19.0, c.normL1().si(), EPS);
        assertEquals(13.0, c.normL2().si(), EPS);
        assertEquals(Math.cbrt(27.0 + 64.0 + 1728.0), c.normLp(3).si(), EPS);
        assertEquals(12.0, c.normLinf().si(), EPS);
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
        assertArrayEquals(new double[] {2000.0, 8000.0, 24000.0}, mul.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());

        var div = a.divideElements(b);
        assertArrayEquals(new double[] {0.002, 0.002, 0.0026666666666666666}, div.si(), 1e-15);
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit());
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
        assertArrayEquals(new double[] {2000.0, 8000.0, 24000.0}, mul.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());

        var div = a.divideElements(b);
        assertArrayEquals(new double[] {0.002, 0.002, 0.0026666666666666666}, div.si(), 1e-15);
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit());
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
        assertEquals(32_000.0, dot.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), dot.siUnit());
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
        assertArrayEquals(expected, m.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.km.siUnit(), Length.Unit.m.siUnit()), m.getDisplayUnit().siUnit());
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
        assertArrayEquals(expected, prod.si(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), prod.getDisplayUnit());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // as(targetUnit)
    // -----------------------------------------------------------------------------------------------------------------

    /** Verify as(targetUnit) succeeds for m↔km and fails for mismatched SI unit (Row &amp; Col). */
    @Test
    @DisplayName("as(targetUnit) success for m↔km and failure for mismatched SI unit")
    public void testAs()
    {
        Vector3.Col<Length, Length.Unit> c = col(1.0, 2.0, 3.0, Length.Unit.km);
        Vector3.Col<Length, Length.Unit> cm = c.as(Length.Unit.m);
        assertEquals(Length.Unit.m, cm.getDisplayUnit());
        assertArrayEquals(c.si(), cm.si(), EPS);

        Vector3.Row<Length, Length.Unit> r = row(1.0, 2.0, 3.0, Length.Unit.km);
        Vector3.Row<Length, Length.Unit> rm = r.as(Length.Unit.m);
        assertEquals(Length.Unit.m, rm.getDisplayUnit());
        assertArrayEquals(r.si(), rm.si(), EPS);

        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> c.as(second));
        assertThrows(IllegalArgumentException.class, () -> r.as(second));
    }

    // ------------------------------------------------------------------------------------
    // Vector3.Col — getScalars / getRowScalars / getColumnScalars (incl. 1-based)
    // ------------------------------------------------------------------------------------

    /**
     * Verify scalar array extraction helpers on a {@link Vector3.Col} (3x1) and their 1-based variants.
     * <p>
     * Uses kilometers as display unit to verify correct SI conversion.
     */
    @Test
    @DisplayName("Vector3.Col: getScalars helpers (Row/Column) incl. 1-based, lengths, values, and bounds")
    public void testCol3ScalarExtraction()
    {
        // Column vector: [x; y; z] = [5 km; 6 km; 7 km] -> SI = [5000; 6000; 7000] m
        Vector3.Col<Length, Length.Unit> v = col(5.0, 6.0, 7.0, Length.Unit.km);

        // 0-based rows -> each row has length 1
        Length[] row0 = v.getRowScalars(0);
        Length[] row1 = v.getRowScalars(1);
        Length[] row2 = v.getRowScalars(2);
        assertEquals(1, row0.length);
        assertEquals(1, row1.length);
        assertEquals(1, row2.length);
        assertEquals(5000.0, row0[0].si(), EPS);
        assertEquals(6000.0, row1[0].si(), EPS);
        assertEquals(7000.0, row2[0].si(), EPS);

        // 0-based single column aggregates all rows
        Length[] col0 = v.getColumnScalars(0);
        assertEquals(3, col0.length);
        assertEquals(5000.0, col0[0].si(), EPS);
        assertEquals(6000.0, col0[1].si(), EPS);
        assertEquals(7000.0, col0[2].si(), EPS);

        // 1-based variants
        Length[] mRow1 = v.mgetRowScalars(1);
        Length[] mRow2 = v.mgetRowScalars(2);
        Length[] mRow3 = v.mgetRowScalars(3);
        assertEquals(1, mRow1.length);
        assertEquals(1, mRow2.length);
        assertEquals(1, mRow3.length);
        assertEquals(5000.0, mRow1[0].si(), EPS);
        assertEquals(6000.0, mRow2[0].si(), EPS);
        assertEquals(7000.0, mRow3[0].si(), EPS);

        Length[] mCol1 = v.mgetColumnScalars(1);
        assertEquals(3, mCol1.length);
        assertEquals(5000.0, mCol1[0].si(), EPS);
        assertEquals(6000.0, mCol1[1].si(), EPS);
        assertEquals(7000.0, mCol1[2].si(), EPS);

        // OOB (0-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowScalars(3));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnScalars(1));

        // OOB (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowScalars(4));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnScalars(2));
    }

    // ------------------------------------------------------------------------------------
    // Vector3.Col — getRowVector / getColumnVector (incl. 1-based)
    // ------------------------------------------------------------------------------------

    /**
     * Verify vector extraction helpers on a {@link Vector3.Col} (3x1) for both 0-based and 1-based variants.
     * <p>
     * Row vectors returned are {@link Vector1} (1 element). The full column is returned as {@link Vector3.Col}.
     */
    @Test
    @DisplayName("Vector3.Col: getRowVector / getColumnVector incl. 1-based and bounds")
    public void testCol3VectorExtractionSpec()
    {
        // Column vector: [5 km; 6 km; 7 km] -> SI = [5000; 6000; 7000] m
        Vector3.Col<Length, Length.Unit> v = col(5.0, 6.0, 7.0, Length.Unit.km);

        // 0-based rows -> Vector1
        Vector1<Length, Length.Unit> row0 = v.getRowVector(0);
        Vector1<Length, Length.Unit> row1 = v.getRowVector(1);
        Vector1<Length, Length.Unit> row2 = v.getRowVector(2);
        assertEquals(1, row0.size());
        assertEquals(1, row1.size());
        assertEquals(1, row2.size());
        assertEquals(5000.0, row0.get(0).si(), EPS);
        assertEquals(6000.0, row1.get(0).si(), EPS);
        assertEquals(7000.0, row2.get(0).si(), EPS);

        // 0-based column 0 -> full Vector3.Col
        Vector3.Col<Length, Length.Unit> fullCol0 = v.getColumnVector(0);
        assertEquals(3, fullCol0.size());
        assertEquals(5000.0, fullCol0.get(0).si(), EPS);
        assertEquals(6000.0, fullCol0.get(1).si(), EPS);
        assertEquals(7000.0, fullCol0.get(2).si(), EPS);

        // 1-based
        Vector1<Length, Length.Unit> mRow1 = v.mgetRowVector(1);
        Vector1<Length, Length.Unit> mRow2 = v.mgetRowVector(2);
        Vector1<Length, Length.Unit> mRow3 = v.mgetRowVector(3);
        assertEquals(1, mRow1.size());
        assertEquals(1, mRow2.size());
        assertEquals(1, mRow3.size());
        assertEquals(5000.0, mRow1.get(0).si(), EPS);
        assertEquals(6000.0, mRow2.get(0).si(), EPS);
        assertEquals(7000.0, mRow3.get(0).si(), EPS);

        Vector3.Col<Length, Length.Unit> mFullCol1 = v.mgetColumnVector(1);
        assertEquals(3, mFullCol1.size());
        assertEquals(5000.0, mFullCol1.get(0).si(), EPS);
        assertEquals(6000.0, mFullCol1.get(1).si(), EPS);
        assertEquals(7000.0, mFullCol1.get(2).si(), EPS);

        // OOB (0-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowVector(3));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnVector(1));

        // OOB (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowVector(4));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnVector(2));
    }

    // ------------------------------------------------------------------------------------
    // Vector3.Col — getRowSi / getColumnSi (incl. 1-based) + si(r,c) / msi(r,c)
    // ------------------------------------------------------------------------------------

    /**
     * Verify SI-array extraction helpers on a {@link Vector3.Col} and direct SI element access using {@code si(row, col)} and
     * {@code msi(row, col)} including bounds.
     */
    @Test
    @DisplayName("Vector3.Col: getRowSi / getColumnSi incl. 1-based + si(r,c)/msi(r,c)")
    public void testCol3SiArrayExtractionAndSiAt()
    {
        // Column vector: [5 km; 6 km; 7 km] -> SI = [5000; 6000; 7000] m
        Vector3.Col<Length, Length.Unit> v = col(5.0, 6.0, 7.0, Length.Unit.km);

        // 0-based SI arrays
        double[] row0 = v.getRowSi(0);
        double[] row1 = v.getRowSi(1);
        double[] row2 = v.getRowSi(2);
        double[] col0 = v.getColumnSi(0);
        assertEquals(1, row0.length);
        assertEquals(1, row1.length);
        assertEquals(1, row2.length);
        assertEquals(3, col0.length);
        assertEquals(5000.0, row0[0], EPS);
        assertEquals(6000.0, row1[0], EPS);
        assertEquals(7000.0, row2[0], EPS);
        assertEquals(5000.0, col0[0], EPS);
        assertEquals(6000.0, col0[1], EPS);
        assertEquals(7000.0, col0[2], EPS);

        // 1-based SI arrays
        double[] mRow1 = v.mgetRowSi(1);
        double[] mRow2 = v.mgetRowSi(2);
        double[] mRow3 = v.mgetRowSi(3);
        double[] mCol1 = v.mgetColumnSi(1);
        assertEquals(1, mRow1.length);
        assertEquals(1, mRow2.length);
        assertEquals(1, mRow3.length);
        assertEquals(3, mCol1.length);
        assertEquals(5000.0, mRow1[0], EPS);
        assertEquals(6000.0, mRow2[0], EPS);
        assertEquals(7000.0, mRow3[0], EPS);
        assertEquals(5000.0, mCol1[0], EPS);
        assertEquals(6000.0, mCol1[1], EPS);
        assertEquals(7000.0, mCol1[2], EPS);

        // si(row, col) 0-based (rows 0..2, only column 0)
        assertEquals(5000.0, v.si(0, 0), EPS);
        assertEquals(6000.0, v.si(1, 0), EPS);
        assertEquals(7000.0, v.si(2, 0), EPS);

        // msi(row, col) 1-based (rows 1..3, only column 1)
        assertEquals(5000.0, v.msi(1, 1), EPS);
        assertEquals(6000.0, v.msi(2, 1), EPS);
        assertEquals(7000.0, v.msi(3, 1), EPS);

        // OOB (0-based) for arrays
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowSi(3));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnSi(1));

        // OOB (1-based) for arrays
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowSi(4));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnSi(2));

        // OOB for si(row, col) 0-based
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, 1));

        // OOB for msi(row, col) 1-based
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(4, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 2));
    }

    // ------------------------------------------------------------------------------------
    // Vector3.Row — getScalars / getRowScalars / getColumnScalars (incl. 1-based)
    // ------------------------------------------------------------------------------------

    /**
     * Verify scalar array extraction helpers on a {@link Vector3.Row} (1x3) and their 1-based variants.
     * <p>
     * Uses centimeters as display unit to verify correct SI conversion.
     */
    @Test
    @DisplayName("Vector3.Row: getScalars helpers (Row/Column) incl. 1-based, lengths, values, and bounds")
    public void testRow3ScalarExtraction()
    {
        // Row vector: [x, y, z] = [3 cm, 4 cm, 5 cm] -> SI = [0.03, 0.04, 0.05] m
        Vector3.Row<Length, Length.Unit> v = row(3.0, 4.0, 5.0, Length.Unit.cm);

        // 0-based: row 0 -> length 3
        Length[] row0 = v.getRowScalars(0);
        assertEquals(3, row0.length);
        assertEquals(0.03, row0[0].si(), EPS);
        assertEquals(0.04, row0[1].si(), EPS);
        assertEquals(0.05, row0[2].si(), EPS);

        // 0-based: individual columns -> length 1
        Length[] col0 = v.getColumnScalars(0);
        Length[] col1 = v.getColumnScalars(1);
        Length[] col2 = v.getColumnScalars(2);
        assertEquals(1, col0.length);
        assertEquals(1, col1.length);
        assertEquals(1, col2.length);
        assertEquals(0.03, col0[0].si(), EPS);
        assertEquals(0.04, col1[0].si(), EPS);
        assertEquals(0.05, col2[0].si(), EPS);

        // 1-based variants
        Length[] mRow1 = v.mgetRowScalars(1);
        Length[] mCol1 = v.mgetColumnScalars(1);
        Length[] mCol2 = v.mgetColumnScalars(2);
        Length[] mCol3 = v.mgetColumnScalars(3);
        assertEquals(3, mRow1.length);
        assertEquals(1, mCol1.length);
        assertEquals(1, mCol2.length);
        assertEquals(1, mCol3.length);
        assertEquals(0.03, mRow1[0].si(), EPS);
        assertEquals(0.04, mRow1[1].si(), EPS);
        assertEquals(0.05, mRow1[2].si(), EPS);
        assertEquals(0.03, mCol1[0].si(), EPS);
        assertEquals(0.04, mCol2[0].si(), EPS);
        assertEquals(0.05, mCol3[0].si(), EPS);

        // OOB (0-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowScalars(1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnScalars(3));

        // OOB (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowScalars(2));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnScalars(4));
    }

    // ------------------------------------------------------------------------------------
    // Vector3.Row — getRowVector / getColumnVector (incl. 1-based)
    // ------------------------------------------------------------------------------------

    /**
     * Verify vector extraction helpers on a {@link Vector3.Row} (1x3) for both 0-based and 1-based variants.
     * <p>
     * Full row returns {@link Vector3.Row}; each column returns {@link Vector1}.
     */
    @Test
    @DisplayName("Vector3.Row: getRowVector / getColumnVector incl. 1-based and bounds")
    public void testRow3VectorExtractionSpec()
    {
        // Row vector: [3 cm, 4 cm, 5 cm] -> SI = [0.03, 0.04, 0.05] m
        Vector3.Row<Length, Length.Unit> v = row(3.0, 4.0, 5.0, Length.Unit.cm);

        // 0-based: full row -> Vector3.Row
        Vector3.Row<Length, Length.Unit> fullRow0 = v.getRowVector(0);
        assertEquals(3, fullRow0.size());
        assertEquals(0.03, fullRow0.get(0).si(), EPS);
        assertEquals(0.04, fullRow0.get(1).si(), EPS);
        assertEquals(0.05, fullRow0.get(2).si(), EPS);

        // 0-based: columns -> Vector1
        Vector1<Length, Length.Unit> c0 = v.getColumnVector(0);
        Vector1<Length, Length.Unit> c1 = v.getColumnVector(1);
        Vector1<Length, Length.Unit> c2 = v.getColumnVector(2);
        assertEquals(1, c0.size());
        assertEquals(1, c1.size());
        assertEquals(1, c2.size());
        assertEquals(0.03, c0.get(0).si(), EPS);
        assertEquals(0.04, c1.get(0).si(), EPS);
        assertEquals(0.05, c2.get(0).si(), EPS);

        // 1-based
        Vector3.Row<Length, Length.Unit> mFullRow1 = v.mgetRowVector(1);
        Vector1<Length, Length.Unit> mC1 = v.mgetColumnVector(1);
        Vector1<Length, Length.Unit> mC2 = v.mgetColumnVector(2);
        Vector1<Length, Length.Unit> mC3 = v.mgetColumnVector(3);
        assertEquals(3, mFullRow1.size());
        assertEquals(1, mC1.size());
        assertEquals(1, mC2.size());
        assertEquals(1, mC3.size());
        assertEquals(0.03, mFullRow1.get(0).si(), EPS);
        assertEquals(0.04, mFullRow1.get(1).si(), EPS);
        assertEquals(0.05, mFullRow1.get(2).si(), EPS);
        assertEquals(0.03, mC1.get(0).si(), EPS);
        assertEquals(0.04, mC2.get(0).si(), EPS);
        assertEquals(0.05, mC3.get(0).si(), EPS);

        // OOB (0-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowVector(1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnVector(3));

        // OOB (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowVector(2));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnVector(4));
    }

    // ------------------------------------------------------------------------------------
    // Vector3.Row — getRowSi / getColumnSi (incl. 1-based) + si(r,c) / msi(r,c)
    // ------------------------------------------------------------------------------------

    /**
     * Verify SI-array extraction helpers on a {@link Vector3.Row} and direct SI element access using {@code si(row, col)} and
     * {@code msi(row, col)} including bounds.
     */
    @Test
    @DisplayName("Vector3.Row: getRowSi / getColumnSi incl. 1-based + si(r,c)/msi(r,c)")
    public void testRow3SiArrayExtractionAndSiAt()
    {
        // Row vector: [3 cm, 4 cm, 5 cm] -> SI = [0.03, 0.04, 0.05] m
        Vector3.Row<Length, Length.Unit> v = row(3.0, 4.0, 5.0, Length.Unit.cm);

        // 0-based SI arrays
        double[] row0 = v.getRowSi(0);
        double[] col0 = v.getColumnSi(0);
        double[] col1 = v.getColumnSi(1);
        double[] col2 = v.getColumnSi(2);
        assertEquals(3, row0.length);
        assertEquals(1, col0.length);
        assertEquals(1, col1.length);
        assertEquals(1, col2.length);
        assertEquals(0.03, row0[0], EPS);
        assertEquals(0.04, row0[1], EPS);
        assertEquals(0.05, row0[2], EPS);
        assertEquals(0.03, col0[0], EPS);
        assertEquals(0.04, col1[0], EPS);
        assertEquals(0.05, col2[0], EPS);

        // 1-based SI arrays
        double[] mRow1 = v.mgetRowSi(1);
        double[] mCol1 = v.mgetColumnSi(1);
        double[] mCol2 = v.mgetColumnSi(2);
        double[] mCol3 = v.mgetColumnSi(3);
        assertEquals(3, mRow1.length);
        assertEquals(1, mCol1.length);
        assertEquals(1, mCol2.length);
        assertEquals(1, mCol3.length);
        assertEquals(0.03, mRow1[0], EPS);
        assertEquals(0.04, mRow1[1], EPS);
        assertEquals(0.05, mRow1[2], EPS);
        assertEquals(0.03, mCol1[0], EPS);
        assertEquals(0.04, mCol2[0], EPS);
        assertEquals(0.05, mCol3[0], EPS);

        // si(row, col) 0-based (only row 0, columns 0..2)
        assertEquals(0.03, v.si(0, 0), EPS);
        assertEquals(0.04, v.si(0, 1), EPS);
        assertEquals(0.05, v.si(0, 2), EPS);

        // msi(row, col) 1-based (only row 1, columns 1..3)
        assertEquals(0.03, v.msi(1, 1), EPS);
        assertEquals(0.04, v.msi(1, 2), EPS);
        assertEquals(0.05, v.msi(1, 3), EPS);

        // OOB (0-based) for arrays
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowSi(1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnSi(3));

        // OOB (1-based) for arrays
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowSi(2));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnSi(4));

        // OOB for si(row, col) 0-based
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, 3));

        // OOB for msi(row, col) 1-based
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(2, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 4));
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

        assertEquals(r1, r1);
        assertEquals(r1.hashCode(), r1.hashCode());
        assertEquals(r1, r2, "equal SI values despite different original display units");
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotEquals(r1, c1, "Row not equal to Col");
        assertNotEquals(r1, null);
        assertNotEquals(r1, "not a vector");

        String sRow = r1.toString();
        String sCol = c1.toString(Length.Unit.km);
        assertTrue(sRow.startsWith("Row["));
        assertTrue(sCol.startsWith("Col["));
        assertTrue(sCol.contains("km"));

        Vector3.Row<Length, Length.Unit> ret = r1.setDisplayUnit(Length.Unit.km);
        assertEquals(Length.Unit.km, r1.getDisplayUnit());
        assertEquals(r1, ret);
        assertTrue(r1.isRelative(), "Length is a relative quantity");
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        Vector3.Col<Length, Length.Unit> r = col(1.0, 2.0, 3.0, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        Vector3.Col<Speed, Speed.Unit> sr = r.divideElements(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.mget(2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.mget(3).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideElements(d).as(Area.Unit.m2));

        Vector3.Row<Length, Length.Unit> c = row(1.0, 2.0, 3.0, Length.Unit.km);
        Vector3.Row<Speed, Speed.Unit> sc = c.divideElements(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sc.getDisplayUnit());
        assertEquals(0.5, sc.get(0).getInUnit(), 1E-6);
        assertEquals(1.0, sc.get(1).getInUnit(), 1E-6);
        assertEquals(1.5, sr.get(2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> c.divideElements(d).as(Area.Unit.m2));
    }

}
