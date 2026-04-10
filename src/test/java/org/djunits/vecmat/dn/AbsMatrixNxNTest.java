package org.djunits.vecmat.dn;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AbsMatrixNxN}.
 * <p>
 * This class provides exhaustive coverage of AbsMatrixNxN and all inherited functionality from AbsSquareMatrix, AbsMatrix,
 * AbsTable, AbsVectorMatrix and Value. The tests intentionally use an NxN matrix with N = 2 to exercise the generic NxN
 * implementation paths while keeping test logic manageable.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsMatrixNxNTest
{
    /**
     * Create a standard NxN NxN test matrix.
     * @return a test matrix
     */
    private AbsMatrixNxN<Direction, Angle> northDeg()
    {
        return AbsMatrixNxN.of(new double[] {0.0, 90.0, 180.0, 270.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    // ==================================== Constructor and base methods ====================================

    /**
     * Test constructor and base methods.
     */
    @Test
    public void testCtorBase()
    {
        MatrixNxN<Angle> rm = MatrixNxN.of(new double[] {0.0, 90.0, 180.0, 270.0}, Angle.Unit.deg);

        AbsMatrixNxN<Direction, Angle> am = new AbsMatrixNxN<>(rm, Direction.Reference.NORTH);

        assertEquals(2, am.rows());
        assertEquals(2, am.cols());
        assertEquals(2, am.order());

        assertEquals(Direction.Reference.NORTH, am.getReference());
        assertEquals(Angle.Unit.deg, am.getDisplayUnit());

        assertTrue(am.isAbsolute());
        assertFalse(am.isRelative());

        Direction d0 = new Direction(0.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d90 = new Direction(90.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d180 = new Direction(180.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d270 = new Direction(270.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertEquals(d0, am.get(0, 0));
        assertEquals(d0, am.mget(1, 1));
        assertEquals(d90, am.get(0, 1));
        assertEquals(d90, am.mget(1, 2));
        assertEquals(d180, am.get(1, 0));
        assertEquals(d180, am.mget(2, 1));
        assertEquals(d270, am.get(1, 1));
        assertEquals(d270, am.mget(2, 2));

        assertEquals(2, am.rows());
        assertEquals(2, am.cols());
        assertEquals(2, am.order());
        assertTrue(am.isAbsolute());
        assertFalse(am.isRelative());
        assertFalse(am.isSymmetric());
        assertFalse(am.isSkewSymmetric());
        assertFalse(am.isSymmetric(Angle.of(1E-6, "deg")));
        assertFalse(am.isSkewSymmetric(Angle.of(1E-6, "deg")));

        am.setDisplayUnit("rad");
        assertEquals(Angle.Unit.rad, am.getDisplayUnit());
        am.setDisplayUnit("deg");
        assertEquals(Angle.Unit.deg, am.getDisplayUnit());
    }

    /**
     * Test constructor exceptions.
     */
    @Test
    public void testCtorExceptions()
    {
        MatrixNxN<Angle> rm = MatrixNxN.of(new double[] {0.0, 90.0, 180.0, 270.0}, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsMatrixNxN<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsMatrixNxN<>(rm, null));
    }

    // ==================================== instantiateSi ====================================

    /**
     * Test instantiateSi().
     */
    @Test
    public void testInstantiateSi()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        AbsMatrixNxN<Direction, Angle> m2 = m.instantiateSi(new double[] {1, 2, 3, 4}, Direction.Reference.EAST);

        assertEquals(Direction.Reference.EAST, m2.getReference());
        assertEquals(1.0, m2.si(0, 0), 1e-12);
        assertEquals(Angle.Unit.deg, m2.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> m.instantiateSi(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> m.instantiateSi(m2.getSiArray(), null));
        assertThrows(IllegalArgumentException.class, () -> m.instantiateSi(new double[] {}, Direction.Reference.EAST));
        assertThrows(IllegalArgumentException.class,
                () -> m.instantiateSi(new double[] {1, 2, 3, 4, 5}, Direction.Reference.EAST));
    }

    // ==================================== Static factory methods ====================================

    /**
     * Test all static of() and ofSi() factories.
     */
    @Test
    public void testStaticFactories()
    {
        Angle[] qa = {new Angle(0, Angle.Unit.deg), new Angle(90, Angle.Unit.deg), new Angle(180, Angle.Unit.deg),
                new Angle(270, Angle.Unit.deg)};
        var adeg = new double[] {0.0, 90.0, 180.0, 270.0};
        var adeg5 = new double[] {0.0, 90.0, 180.0, 270.0, 45.0};
        var adegg = new double[][] {{0.0, 90.0}, {180.0, 270.0}};
        var arad = new double[] {0.0, Math.PI / 2.0, Math.PI, 1.5 * Math.PI};
        var aradg = new double[][] {{0.0, Math.PI / 2.0}, {Math.PI, 1.5 * Math.PI}};
        var rm = MatrixNxN.of(new double[] {0.0, 90.0, 180.0, 270.0}, Angle.Unit.deg);
        var am = new AbsMatrixNxN<>(rm, Direction.Reference.NORTH);

        // of(double[], unit, ref)
        AbsMatrixNxN<Direction, Angle> mdu = AbsMatrixNxN.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxN.of(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsMatrixNxN<Direction, Angle> msi = AbsMatrixNxN.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), msi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, msi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxN.ofSi(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(double[][], unit, ref)
        AbsMatrixNxN<Direction, Angle> mdgu = AbsMatrixNxN.of(adegg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mdgu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mdgu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.of((double[][]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of(adegg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of(adegg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.of(new double[][] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.of(new double[][] {{}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.of(new double[][] {{1, 2}, {3}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.of(new double[][] {{}, {3, 4}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.of(new double[][] {null, {3, 4}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.of(new double[][] {{1, 2}, null}, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[][], displayUnit, ref)
        AbsMatrixNxN<Direction, Angle> mgsi = AbsMatrixNxN.ofSi(aradg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mgsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mgsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.ofSi((double[][]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.ofSi(adegg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.ofSi(adegg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.ofSi(new double[][] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.ofSi(new double[][] {{}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.ofSi(new double[][] {{1, 2}, {3}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.ofSi(new double[][] {{}, {3, 4}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.ofSi(new double[][] {null, {3, 4}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.ofSi(new double[][] {{1, 2}, null}, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsMatrixNxN<Direction, Angle> mqa = AbsMatrixNxN.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqa.getDisplayUnit());
        AbsMatrixNxN<Direction, Angle> mqa2 =
                AbsMatrixNxN.of(new Angle[] {Angle.of(0.0, "rad"), qa[1], qa[2], qa[3]}, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, mqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxN.of(new Angle[] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.of(new Angle[] {qa[0], qa[1], qa[2], qa[3], qa[2]}, Direction.Reference.NORTH));

        // of(Q[][], ref)
        Angle[][] qg = {{qa[0], qa[1]}, {qa[2], qa[3]}};
        AbsMatrixNxN<Direction, Angle> mqg = AbsMatrixNxN.of(qg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqg.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqg.getDisplayUnit());
        AbsMatrixNxN<Direction, Angle> mqg2 =
                AbsMatrixNxN.of(new Angle[][] {{Angle.of(0.0, "rad"), qa[1]}, {qa[2], qa[3]}}, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqg2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, mqg2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of((Angle[][]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of(qg, null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxN.of(new Angle[][] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxN.of(new Angle[][] {{}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.of(new Angle[][] {{qa[0], qa[1]}, {qa[2]}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxN.of(new Angle[][] {{}, {qa[2], qa[3]}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.of(new Angle[][] {null, {qa[2], qa[3]}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.of(new Angle[][] {{qa[0], qa[1]}, {qa[2], null}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxN.of(new Angle[][] {{null, qa[1]}, {qa[2], qa[3]}}, Direction.Reference.NORTH));

        // of(Matrix, ref)
        MatrixNxN<Angle> rel = MatrixNxN.of(new double[] {1, 2, 3, 4}, Angle.Unit.deg);
        AbsMatrixNxN<Direction, Angle> amrel = AbsMatrixNxN.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, amrel.si(0, 0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, amrel.si(0, 1), 1E-10);
        assertEquals(3.0 * Math.PI / 180.0, amrel.si(1, 0), 1E-10);
        assertEquals(4.0 * Math.PI / 180.0, amrel.si(1, 1), 1E-10);
        assertEquals(Angle.Unit.deg, amrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of((MatrixNxN<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxN.of(rel, null));
    }

    // ==================================== Scalar & SI array / grid access ====================================

    /**
     * Test scalar and SI array / grid extraction.
     */
    @Test
    public void testScalarAndSiGrids()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        Direction[][] s = m.getScalarGrid();
        double[][] si = m.getSiGrid();

        assertEquals(2, s.length);
        assertEquals(2, si.length);
        assertEquals(2, s[0].length);
        assertEquals(2, si[0].length);
        assertEquals(90.0, s[0][1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(Math.PI, si[1][0], 1e-12);

        Direction[] a = m.getScalarArray();
        double[] asi = m.getSiArray();
        assertEquals(4, a.length);
        assertEquals(4, asi.length);
        assertEquals(90.0, a[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(Math.PI, asi[2], 1e-12);
    }

    // ==================================== Row & column access (SI and scalar) ====================================

    /**
     * Test 0-based row and column SI access.
     */
    @Test
    public void testGetRowAndColumnSi()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        double[] row = m.getRowSi(1);
        double[] col = m.getColumnSi(1);
        assertEquals(2, row.length);
        assertEquals(2, col.length);

        assertEquals(Math.PI, row[0], 1e-12);
        assertEquals(270.0, Math.toDegrees(col[1]), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(2));
    }

    /**
     * Test 0-based scalar row access.
     */
    @Test
    public void testGetRowAndColumnScalars()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        Direction[] row = m.getRowScalars(0);
        Direction[] col = m.getColumnScalars(1);

        assertEquals(2, row.length);
        assertEquals(2, col.length);
        assertEquals(0.0, row[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, col[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(270.0, col[1].getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(2));
    }

    /**
     * Test 1-based row and column SI access.
     */
    @Test
    public void testMgetRowAndColumnSi()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        double[] row = m.mgetRowSi(2);
        double[] col = m.mgetColumnSi(2);

        assertEquals(2, row.length);
        assertEquals(2, col.length);
        assertEquals(Math.PI, row[0], 1e-12);
        assertEquals(270.0, Math.toDegrees(col[1]), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(3));
    }

    /**
     * Test 1-based scalar row and column access.
     */
    @Test
    public void testMgetRowAndColumnScalars()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        Direction[] row = m.mgetRowScalars(1);
        Direction[] col = m.mgetColumnScalars(2);

        assertEquals(2, row.length);
        assertEquals(2, col.length);
        assertEquals(0.0, row[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, col[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(270.0, col[1].getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(3));
    }

    // ==================================== Diagonal SI and scalar ====================================

    /**
     * Test diagonal SI.
     */
    @Test
    public void testDiagonalSi()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        double[] diag = m.getDiagonalSi();
        assertEquals(2, diag.length);
        assertEquals(0.0, diag[0], 1e-12);
        assertEquals(270.0, Math.toDegrees(diag[1]), 1e-12);
    }

    /**
     * Test diagonal scalar.
     */
    @Test
    public void testDiagonalScalars()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        Direction[] diag = m.getDiagonalScalars();
        assertEquals(2, diag.length);
        assertEquals(0.0, diag[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(270.0, diag[1].getInUnit(Angle.Unit.deg), 1e-12);
    }

    // ==================================== Row & column vector ====================================

    /**
     * Test 0-based vector row and column access.
     */
    @Test
    public void testGetRowAndColumnVector()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        var row = m.getRowVector(0);
        var col = m.getColumnVector(1);

        assertEquals(2, row.size());
        assertEquals(2, col.size());
        assertEquals(0.0, row.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(270.0, col.get(1).getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(2));
    }

    /**
     * Test 1-based vector row and column access.
     */
    @Test
    public void testMgetRowAndColumnVector()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        var row = m.mgetRowVector(1);
        var col = m.mgetColumnVector(2);

        assertEquals(2, row.size());
        assertEquals(2, col.size());
        assertEquals(0.0, row.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(270.0, col.get(1).getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(3));
    }

    // ==================================== Diagonal vector ====================================

    /**
     * Test diagonal vector.
     */
    @Test
    public void testDiagonalVector()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        var diag = m.getDiagonalVector();
        assertEquals(2, diag.size());
        assertEquals(0.0, diag.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(270.0, diag.get(1).getInUnit(Angle.Unit.deg), 1e-12);
    }

    // ==================================== min, max, median ====================================

    /**
     * Test min(), max() and median().
     */
    @Test
    public void testMinMaxMedian()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();
        assertEquals(0.0, m.min().si());
        assertEquals(1.5 * Math.PI, m.max().si(), 1E-10);
        assertEquals(135, m.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, m.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, m.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, m.median().getDisplayUnit());

        AbsMatrixNxN<Direction, Angle> e =
                AbsMatrixNxN.ofSi(new double[] {1, 1, 1, 1}, Angle.Unit.rad, Direction.Reference.NORTH);
        assertEquals(1.0, e.min().si(), 1E-10);
        assertEquals(1.0, e.max().si(), 1E-10);
        assertEquals(1.0, e.median().si(), 1E-10);
    }

    // ==================================== transpose ====================================

    /**
     * Test transpose().
     */
    @Test
    public void testTranspose()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();
        var mt = m.transpose();
        var mt2 = mt.transpose();
        assertEquals(m, mt2);

        assertEquals(0.0, mt.si(0, 0));
        assertEquals(Math.PI, mt.si(0, 1), 1E-10);
        assertEquals(0.5 * Math.PI, mt.si(1, 0), 1E-10);
        assertEquals(270.0, mt.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, mt.getDisplayUnit());
        assertEquals(Angle.Unit.deg, mt2.getDisplayUnit());
    }

    // ==================================== add, subtract ====================================

    /**
     * Test add() and subtract() methods.
     */
    @Test
    public void testAddSubtract()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();
        AbsMatrixNxN<Direction, Angle> madd = northDeg().add(Angle.of(5.0, "deg"));
        AbsMatrixNxN<Direction, Angle> msub = northDeg().subtract(Angle.of(5.0, "deg"));
        MatrixNxN<Angle> m0 = m.subtract(m);

        assertArrayEquals(new double[] {0, 0, 0, 0}, m0.getSiArray());
        assertEquals(5.0, madd.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(95.0, madd.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(185.0, madd.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(275.0, madd.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(-5.0, msub.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(85.0, msub.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, msub.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(265.0, msub.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);

        MatrixNxN<Angle> m1234 = MatrixNxN.of(new double[] {1, 2, 3, 4}, Angle.Unit.deg);
        madd = m.add(m1234);
        msub = m.subtract(m1234);
        assertEquals(1.0, madd.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(92.0, madd.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(183.0, madd.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(274.0, madd.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(-1.0, msub.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(88.0, msub.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(177.0, msub.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(266.0, msub.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> m.add((Angle) null));
        assertThrows(NullPointerException.class, () -> m.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> m.add((MatrixNxN<Angle>) null));
        assertThrows(NullPointerException.class, () -> m.subtract((MatrixNxN<Angle>) null));
        assertThrows(NullPointerException.class, () -> m.subtract((AbsMatrixNxN<Direction, Angle>) null));
    }

    // ==================================== toString ====================================

    /**
     * Test toString().
     */
    @Test
    public void testToString()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();
        String sdeg = m.toString();
        String srad = m.toString(Angle.Unit.rad);
        assertTrue(sdeg.contains(Angle.Unit.deg.getDisplayAbbreviation()));
        assertTrue(srad.contains(Angle.Unit.rad.getDisplayAbbreviation()));
        assertTrue(sdeg.contains("["));
        assertTrue(srad.contains("]"));
    }

    // ==================================== equals, hashCode ====================================

    /**
     * Test equals(), hashCode().
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsHashCode()
    {
        AbsMatrixNxN<Direction, Angle> mdn =
                AbsMatrixNxN.ofSi(new double[] {0.1, 0.2, 0.3, 0.4}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsMatrixNxN<Direction, Angle> mrn =
                AbsMatrixNxN.ofSi(new double[] {0.1, 0.2, 0.3, 0.4}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsMatrixNxN<Direction, Angle> mde =
                AbsMatrixNxN.ofSi(new double[] {0.1, 0.2, 0.3, 0.4}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(mdn.equals(mdn));
        assertTrue(mdn.equals(mrn));
        assertFalse(mdn.equals(mde));
        assertFalse(mdn.equals(null));
        assertFalse(mdn.equals(new String("abc")));
        assertFalse(mdn.equals(northDeg()));

        assertEquals(mdn.hashCode(),
                AbsMatrixNxN.ofSi(new double[] {0.1, 0.2, 0.3, 0.4}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertEquals(mdn.hashCode(), mrn.hashCode());
        assertNotEquals(mdn.hashCode(), mde.hashCode());
        assertNotEquals(mdn.hashCode(), northDeg().hashCode());
    }

    // ==================================== Vector access ====================================

    /**
     * Test mgetRowVector and getColumnVector.
     */
    @Test
    public void testVectorAccess()
    {
        AbsMatrixNxN<Direction, Angle> m = northDeg();

        AbsVectorN.Row<Direction, Angle> row = m.mgetRowVector(2);
        AbsVectorN.Col<Direction, Angle> col = m.getColumnVector(1);

        assertEquals(2, row.size());
        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);
    }

    /*
     * ==================================== Symmetry and skew symmetry ====================================
     */

    /**
     * Test symmetric and skew-symmetric checks.
     */
    @Test
    public void testSymmetry()
    {
        AbsMatrixNxN<Direction, Angle> sym =
                AbsMatrixNxN.of(new double[] {1, 2, 2, 1}, Angle.Unit.deg, Direction.Reference.NORTH);

        assertTrue(sym.isSymmetric());
        assertTrue(sym.isSymmetric(new Angle(1e-9, Angle.Unit.rad)));

        assertFalse(sym.isSkewSymmetric());
        assertFalse(sym.isSkewSymmetric(new Angle(1e-9, Angle.Unit.rad)));

        AbsMatrixNxN<Direction, Angle> m = northDeg();
        assertThrows(NullPointerException.class, () -> m.isSymmetric(null));
        assertThrows(NullPointerException.class, () -> m.isSkewSymmetric(null));
    }

    /**
     * Test as() functions.
     */
    @Test
    @DisplayName("AbsMatrixNxN as() functions test")
    public void testAsFunctions()
    {
        var m4 = northDeg();
        var pi2 = Math.PI / 2.0;

        var mNxN = m4.asAbsMatrixNxN();
        assertArrayEquals(m4.getSiArray(), mNxN.asAbsMatrixNxN().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mNxN.getDisplayUnit());
        assertEquals(pi2, mNxN.si(0, 1), 1E-10);

        var mNxM = m4.asAbsMatrixNxM();
        assertArrayEquals(m4.getSiArray(), mNxM.asAbsMatrixNxN().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mNxM.getDisplayUnit());
        assertEquals(pi2, mNxM.si(0, 1), 1E-10);

        var mQT = m4.asAbsQuantityTable();
        assertArrayEquals(m4.getSiArray(), mQT.asAbsMatrixNxN().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mQT.getDisplayUnit());
        assertEquals(pi2, mQT.si(0, 1), 1E-10);

        assertThrows(IllegalStateException.class, () -> m4.asAbsMatrix1x1());
        assertThrows(IllegalStateException.class, () -> m4.asAbsMatrix3x3());
        assertThrows(IllegalStateException.class, () -> m4.asAbsVector1());
        assertThrows(IllegalStateException.class, () -> m4.asAbsVector2Col());
        assertThrows(IllegalStateException.class, () -> m4.asAbsVector2Row());
        assertThrows(IllegalStateException.class, () -> m4.asAbsVector3Col());
        assertThrows(IllegalStateException.class, () -> m4.asAbsVector3Row());
        assertThrows(IllegalStateException.class, () -> m4.asAbsVectorNCol());
        assertThrows(IllegalStateException.class, () -> m4.asAbsVectorNRow());

        var m1 = AbsMatrixNxN.of(new double[] {90}, Angle.Unit.deg, Direction.Reference.NORTH);
        var m1NxN = m1.asAbsMatrix1x1();
        assertArrayEquals(m1.getSiArray(), m1NxN.asAbsMatrixNxN().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, m1NxN.getDisplayUnit());
        assertEquals(pi2, m1NxN.si(0, 0), 1E-10);

        var v1 = m1.asAbsVector1();
        assertArrayEquals(m1.getSiArray(), v1.asAbsMatrixNxN().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, v1.getDisplayUnit());
        assertEquals(pi2, v1.si(0), 1E-10);

        var m2 = AbsMatrixNxN.of(new double[] {0, 90, 180, 270}, Angle.Unit.deg, Direction.Reference.NORTH);
        var m2NxN = m2.asAbsMatrix2x2();
        assertArrayEquals(m2.getSiArray(), m2NxN.asAbsMatrixNxN().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, m2NxN.getDisplayUnit());
        assertEquals(pi2, m2NxN.si(0, 1), 1E-10);

        var m3 = AbsMatrixNxN.of(new double[] {0, 90, 180, 270, 0, 90, 180, 270, 0}, Angle.Unit.deg,
                Direction.Reference.NORTH);
        var m3NxN = m3.asAbsMatrix3x3();
        assertArrayEquals(m3.getSiArray(), m3NxN.asAbsMatrixNxN().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, m3NxN.getDisplayUnit());
        assertEquals(pi2, m3NxN.si(0, 1), 1E-10);
    }

}
