package org.djunits.vecmat.d1;

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
 * Tests for {@link AbsMatrix1x1}.
 * <p>
 * This class provides exhaustive coverage of AbsMatrix1x1 and all inherited functionality from AbsSquareMatrix, AbsMatrix,
 * AbsTable, AbsVectorMatrix and Value.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsMatrix1x1Test
{
    /**
     * Create a standard test matrix.
     * @return a test matrix
     */
    private AbsMatrix1x1<Direction, Angle> northDeg()
    {
        return AbsMatrix1x1.of(new double[] {90.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    // ==================================== Constructor and base methods ====================================

    /**
     * Test constructor and base methods such as rows(), cols(), order().
     */
    @Test
    public void testCtorBase()
    {
        var rm = Matrix1x1.of(new double[] {90.0}, Angle.Unit.deg);
        var am = new AbsMatrix1x1<>(rm, Direction.Reference.NORTH);
        assertEquals(Angle.Unit.deg, am.getDisplayUnit());
        assertEquals(Direction.Reference.NORTH, am.getReference());

        Direction d90 = new Direction(90.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertEquals(d90, am.get(0, 0));

        assertEquals(1, am.rows());
        assertEquals(1, am.cols());
        assertEquals(1, am.order());
        assertTrue(am.isAbsolute());
        assertFalse(am.isRelative());
        assertTrue(am.isSymmetric());
        assertFalse(am.isSkewSymmetric());
        assertTrue(am.isSymmetric(Angle.of(1E-6, "deg")));
        assertFalse(am.isSkewSymmetric(Angle.of(1E-6, "deg")));

        am.setDisplayUnit("rad");
        assertEquals(Angle.Unit.rad, am.getDisplayUnit());
        am.setDisplayUnit("deg");
        assertEquals(Angle.Unit.deg, am.getDisplayUnit());
    }

    /**
     * Test exceptions for constructor.
     */
    @Test
    public void testCtorBaseExceptions()
    {
        var rm = Matrix1x1.of(new double[] {90.0}, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsMatrix1x1<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsMatrix1x1<>(rm, null));
    }

    /**
     * Test instantiateSi().
     */
    @Test
    public void testInstantiateSi()
    {
        var m = northDeg();
        var m2 = m.instantiateSi(new double[] {0.5 * Math.PI}, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, m2.si(0, 0));
        assertEquals(Angle.Unit.deg, m2.getDisplayUnit()); // same as original northDeg matrix
        assertEquals(Direction.Reference.EAST, m2.getReference());

        assertThrows(NullPointerException.class, () -> m.instantiateSi(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> m.instantiateSi(m2.getSiArray(), null));
        assertThrows(IllegalArgumentException.class, () -> m.instantiateSi(new double[] {}, Direction.Reference.EAST));
        assertThrows(IllegalArgumentException.class, () -> m.instantiateSi(new double[] {1, 2}, Direction.Reference.EAST));
    }

    // ==================================== Static factory methods ====================================

    /**
     * Test all of() and ofSi() static factories.
     */
    @Test
    public void testStaticFactories()
    {
        Angle[] qa = {new Angle(90, Angle.Unit.deg)};
        var adeg = new double[] {90.0};
        var adeg2 = new double[] {90.0, 45.0};
        var adegg = new double[][] {{90.0}};
        var arad = new double[] {Math.PI / 2.0};
        var aradg = new double[][] {{Math.PI / 2.0}};
        var rm = Matrix1x1.of(new double[] {90.0}, Angle.Unit.deg);
        var am = new AbsMatrix1x1<>(rm, Direction.Reference.NORTH);

        // of(double, unit, ref)
        AbsMatrix1x1<Direction, Angle> md1u = AbsMatrix1x1.of(90.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), md1u.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, md1u.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(90.0, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(90.0, Angle.Unit.deg, null));

        // of(Q, ref)
        AbsMatrix1x1<Direction, Angle> mq = AbsMatrix1x1.of(qa[0], Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mq.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mq.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of((Angle) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(qa[0], null));

        // of(double[], unit, ref)
        AbsMatrix1x1<Direction, Angle> mdu = AbsMatrix1x1.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsMatrix1x1.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(adeg2, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsMatrix1x1<Direction, Angle> msi = AbsMatrix1x1.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), msi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, msi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsMatrix1x1.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.ofSi(adeg2, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(double[][], unit, ref)
        AbsMatrix1x1<Direction, Angle> mdgu = AbsMatrix1x1.of(adegg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mdgu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mdgu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsMatrix1x1.of((double[][]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(adegg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(adegg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new double[][] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new double[][] {{}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new double[][] {{1, 2}, {3}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new double[][] {{}, {3, 4}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new double[][] {null, {3, 4}}, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[][], displayUnit, ref)
        AbsMatrix1x1<Direction, Angle> mgsi = AbsMatrix1x1.ofSi(aradg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mgsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mgsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsMatrix1x1.ofSi((double[][]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.ofSi(adegg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.ofSi(adegg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.ofSi(new double[][] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.ofSi(new double[][] {{}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.ofSi(new double[][] {{1, 2}, {3}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.ofSi(new double[][] {{}, {3, 4}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrix1x1.ofSi(new double[][] {null}, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsMatrix1x1<Direction, Angle> mqa = AbsMatrix1x1.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqa.getDisplayUnit());
        AbsMatrix1x1<Direction, Angle> mqa2 = AbsMatrix1x1.of(new Angle[] {qa[0]}, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(new Angle[] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new Angle[] {qa[0], qa[0]}, Direction.Reference.NORTH));

        // of(Q[][], ref)
        Angle[][] qg = {{qa[0]}};
        AbsMatrix1x1<Direction, Angle> mqg = AbsMatrix1x1.of(qg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqg.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqg.getDisplayUnit());
        AbsMatrix1x1<Direction, Angle> mqg2 = AbsMatrix1x1.of(new Angle[][] {{qa[0]}}, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqg2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqg2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of((Angle[][]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(qg, null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(new Angle[][] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(new Angle[][] {{}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new Angle[][] {{qa[0], qa[0]}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new Angle[][] {{}, {qa[0], qa[0]}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix1x1.of(new Angle[][] {null, {qa[0], qa[0]}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(new Angle[][] {null}, Direction.Reference.NORTH));

        // of(Matrix, ref)
        Matrix1x1<Angle> rel = Matrix1x1.of(new double[] {1}, Angle.Unit.deg);
        AbsMatrix1x1<Direction, Angle> amrel = AbsMatrix1x1.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, amrel.si(0, 0), 1E-10);
        assertEquals(Angle.Unit.deg, amrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of((Matrix1x1<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(rel, null));
    }

    /**
     * Test the of(A), of(A[]) and of(A[][]) static factories.
     */
    @Test
    public void testAbsStaticFactories()
    {
        Angle[] qa = {new Angle(90, Angle.Unit.deg)};
        var dir = new Direction(qa[0], Direction.Reference.EAST);

        var a1 = AbsMatrix1x1.of(dir);
        assertEquals(Angle.Unit.deg, a1.getDisplayUnit());
        assertEquals(Direction.Reference.EAST, a1.getReference());
        assertEquals(90.0, a1.get(0, 0).getInUnit(), 1E-10);
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of((Direction) null));

        var aa = AbsMatrix1x1.of(new Direction[] {dir});
        assertEquals(Angle.Unit.deg, aa.getDisplayUnit());
        assertEquals(Direction.Reference.EAST, aa.getReference());
        assertEquals(90.0, aa.get(0, 0).getInUnit(), 1E-10);
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of((Direction[]) null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(new Direction[] {}));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(new Direction[] {null}));

        var ag = AbsMatrix1x1.of(new Direction[][] {{dir}});
        assertEquals(Angle.Unit.deg, ag.getDisplayUnit());
        assertEquals(Direction.Reference.EAST, ag.getReference());
        assertEquals(90.0, ag.get(0, 0).getInUnit(), 1E-10);
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of((Direction[][]) null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(new Direction[][] {}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(new Direction[][] {{}}));
        assertThrows(NullPointerException.class, () -> AbsMatrix1x1.of(new Direction[][] {null}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(new Direction[][] {{dir, dir}}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix1x1.of(new Direction[][] {{}, {}}));
    }

    // ==================================== Scalar & SI array / grid access ====================================

    /**
     * Test scalar and SI array / grid extraction.
     */
    @Test
    public void testScalarAndSiGrids()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        Direction[][] s = m.getScalarGrid();
        double[][] si = m.getSiGrid();

        assertEquals(1, s.length);
        assertEquals(1, si.length);
        assertEquals(1, s[0].length);
        assertEquals(1, si[0].length);
        assertEquals(90.0, s[0][0].getInUnit(Angle.Unit.deg), 1e-12);

        Direction[] a = m.getScalarArray();
        double[] asi = m.getSiArray();
        assertEquals(1, a.length);
        assertEquals(1, asi.length);
        assertEquals(90.0, a[0].getInUnit(Angle.Unit.deg), 1e-12);
    }

    // ==================================== Row & column access (SI and scalar) ====================================

    /**
     * Test 0-based row and column SI access.
     */
    @Test
    public void testGetRowAndColumnSi()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        double[] row = m.getRowSi(0);
        double[] col = m.getColumnSi(0);
        assertEquals(1, row.length);
        assertEquals(1, col.length);

        assertEquals(Math.PI / 2.0, row[0], 1e-12);
        assertEquals(90.0, Math.toDegrees(col[0]), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(1));
    }

    /**
     * Test 0-based scalar row access.
     */
    @Test
    public void testGetRowAndColumnScalars()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        Direction[] row = m.getRowScalars(0);
        Direction[] col = m.getColumnScalars(0);

        assertEquals(1, row.length);
        assertEquals(1, col.length);
        assertEquals(90.0, row[0].getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(1));
    }

    /**
     * Test 1-based row and column SI access.
     */
    @Test
    public void testMgetRowAndColumnSi()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        double[] row = m.mgetRowSi(1);
        double[] col = m.mgetColumnSi(1);

        assertEquals(1, row.length);
        assertEquals(1, col.length);
        assertEquals(Math.PI / 2.0, row[0], 1e-12);
        assertEquals(90.0, Math.toDegrees(col[0]), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(2));
    }

    /**
     * Test 1-based scalar row and column access.
     */
    @Test
    public void testMgetRowAndColumnScalars()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        Direction[] row = m.mgetRowScalars(1);
        Direction[] col = m.mgetColumnScalars(1);

        assertEquals(1, row.length);
        assertEquals(1, col.length);
        assertEquals(90.0, row[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, col[0].getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(2));
    }

    // ==================================== Diagonal SI and scalar ====================================

    /**
     * Test diagonal SI.
     */
    @Test
    public void testDiagonalSi()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        double[] diag = m.getDiagonalSi();
        assertEquals(1, diag.length);
        assertEquals(Math.PI / 2.0, diag[0], 1e-12);
    }

    /**
     * Test diagonal scalar.
     */
    @Test
    public void testDiagonalScalars()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        Direction[] diag = m.getDiagonalScalars();
        assertEquals(1, diag.length);
        assertEquals(90.0, diag[0].getInUnit(Angle.Unit.deg), 1e-12);
    }

    // ==================================== Row & column vector ====================================

    /**
     * Test 0-based vector row and column access.
     */
    @Test
    public void testGetRowAndColumnVector()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        var row = m.getRowVector(0);
        var col = m.getColumnVector(0);

        assertEquals(1, row.size());
        assertEquals(1, col.size());
        assertEquals(90.0, row.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(1));
    }

    /**
     * Test 1-based vector row and column access.
     */
    @Test
    public void testMgetRowAndColumnVector()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        var row = m.mgetRowVector(1);
        var col = m.mgetColumnVector(1);

        assertEquals(1, row.size());
        assertEquals(1, col.size());
        assertEquals(90.0, row.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(2));
    }

    // ==================================== Diagonal vector ====================================

    /**
     * Test diagonal vector.
     */
    @Test
    public void testDiagonalVector()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();

        var diag = m.getDiagonalVector();
        assertEquals(1, diag.size());
        assertEquals(90.0, diag.get(0).getInUnit(Angle.Unit.deg), 1e-12);
    }

    // ==================================== min, max, median ====================================

    /**
     * Test min(), max() and median().
     */
    @Test
    public void testMinMaxMedian()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();
        assertEquals(0.5 * Math.PI, m.min().si(), 1E-10);
        assertEquals(0.5 * Math.PI, m.max().si(), 1E-10);
        assertEquals(90.0, m.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, m.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, m.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, m.median().getDisplayUnit());
    }

    // ==================================== transpose ====================================

    /**
     * Test transpose().
     */
    @Test
    public void testTranspose()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();
        var mt = m.transpose();
        var mt2 = mt.transpose();
        assertEquals(m, mt2);
        assertEquals(0.5 * Math.PI, mt.si(0, 0), 1E-10);

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
        AbsMatrix1x1<Direction, Angle> m = northDeg();
        AbsMatrix1x1<Direction, Angle> madd = northDeg().add(Angle.of(5.0, "deg"));
        AbsMatrix1x1<Direction, Angle> msub = northDeg().subtract(Angle.of(5.0, "deg"));
        Matrix1x1<Angle> m0 = m.subtract(m);

        assertArrayEquals(new double[] {0}, m0.getSiArray());
        assertEquals(95.0, madd.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(85.0, msub.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        Matrix1x1<Angle> m1 = Matrix1x1.of(new double[] {1}, Angle.Unit.deg);
        madd = m.add(m1);
        msub = m.subtract(m1);
        assertEquals(91.0, madd.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(89.0, msub.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> m.add((Angle) null));
        assertThrows(NullPointerException.class, () -> m.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> m.add((Matrix1x1<Angle>) null));
        assertThrows(NullPointerException.class, () -> m.subtract((Matrix1x1<Angle>) null));
        assertThrows(NullPointerException.class, () -> m.subtract((AbsMatrix1x1<Direction, Angle>) null));
    }

    // ==================================== toString ====================================

    /**
     * Test toString().
     */
    @Test
    public void testToString()
    {
        AbsMatrix1x1<Direction, Angle> m = northDeg();
        String sdeg = m.toString();
        String srad = m.toString(Angle.Unit.rad);
        assertTrue(sdeg.contains(Angle.Unit.deg.getDisplayAbbreviation()));
        assertTrue(srad.contains(Angle.Unit.rad.getDisplayAbbreviation()));
        assertTrue(sdeg.contains("|"));
        assertTrue(srad.contains("|"));
    }

    // ==================================== equals, hashCode ====================================

    /**
     * Test equals(), hashCode().
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsHashCode()
    {
        AbsMatrix1x1<Direction, Angle> mdn = AbsMatrix1x1.ofSi(new double[] {0.1}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsMatrix1x1<Direction, Angle> mrn = AbsMatrix1x1.ofSi(new double[] {0.1}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsMatrix1x1<Direction, Angle> mde = AbsMatrix1x1.ofSi(new double[] {0.1}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(mdn.equals(mdn));
        assertTrue(mdn.equals(mrn));
        assertFalse(mdn.equals(mde));
        assertFalse(mdn.equals(null));
        assertFalse(mdn.equals(new String("abc")));
        assertFalse(mdn.equals(northDeg()));

        assertEquals(mdn.hashCode(),
                AbsMatrix1x1.ofSi(new double[] {0.1}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertEquals(mdn.hashCode(), mrn.hashCode());
        assertNotEquals(mdn.hashCode(), mde.hashCode());
        assertNotEquals(mdn.hashCode(), northDeg().hashCode());
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
        AbsMatrix1x1<Direction, Angle> sym = AbsMatrix1x1.of(new double[] {2}, Angle.Unit.deg, Direction.Reference.NORTH);

        assertTrue(sym.isSymmetric());
        assertTrue(sym.isSymmetric(new Angle(1e-9, Angle.Unit.rad)));

        assertFalse(sym.isSkewSymmetric());
        assertFalse(sym.isSkewSymmetric(new Angle(1e-9, Angle.Unit.rad)));

        AbsMatrix1x1<Direction, Angle> m = northDeg();
        assertThrows(NullPointerException.class, () -> m.isSymmetric(null));
        assertThrows(NullPointerException.class, () -> m.isSkewSymmetric(null));
    }

    /**
     * Test as() functions.
     */
    @Test
    @DisplayName("Matrix1x1 as() functions test")
    public void testAsFunctions()
    {
        var m = northDeg();

        var m1 = m.asAbsMatrix1x1();
        assertArrayEquals(m.getSiArray(), m1.asAbsMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, m1.getDisplayUnit());
        assertEquals(m.getReference(), m1.getReference());
        assertEquals(90.0, m1.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        var v1 = m.asAbsVector1();
        assertArrayEquals(m.getSiArray(), v1.asAbsMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, v1.getDisplayUnit());
        assertEquals(m.getReference(), v1.getReference());
        assertEquals(90.0, v1.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        var mNxN = m.asAbsMatrixNxN();
        assertArrayEquals(m.getSiArray(), mNxN.asAbsMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mNxN.getDisplayUnit());
        assertEquals(m.getReference(), mNxN.getReference());
        assertEquals(90.0, mNxN.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        var mNxM = m.asAbsMatrixNxM();
        assertArrayEquals(m.getSiArray(), mNxM.asAbsMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mNxM.getDisplayUnit());
        assertEquals(m.getReference(), mNxM.getReference());
        assertEquals(90.0, mNxM.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        var mQT = m.asAbsQuantityTable();
        assertArrayEquals(m.getSiArray(), mQT.asAbsMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mQT.getDisplayUnit());
        assertEquals(m.getReference(), mQT.getReference());
        assertEquals(90.0, mQT.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        var vNcol = m.asAbsVectorNCol();
        assertArrayEquals(m.getSiArray(), vNcol.asAbsMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vNcol.getDisplayUnit());
        assertEquals(90.0, vNcol.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        var vNrow = m.asAbsVectorNRow();
        assertArrayEquals(m.getSiArray(), vNrow.asAbsMatrix1x1().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vNrow.getDisplayUnit());
        assertEquals(90.0, vNrow.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(IllegalStateException.class, () -> m.asAbsMatrix2x2());
        assertThrows(IllegalStateException.class, () -> m.asAbsMatrix3x3());
        assertThrows(IllegalStateException.class, () -> m.asAbsVector2Col());
        assertThrows(IllegalStateException.class, () -> m.asAbsVector2Row());
        assertThrows(IllegalStateException.class, () -> m.asAbsVector3Col());
        assertThrows(IllegalStateException.class, () -> m.asAbsVector3Row());
    }

}
