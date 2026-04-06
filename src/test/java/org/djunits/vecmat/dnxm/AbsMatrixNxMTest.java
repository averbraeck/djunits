package org.djunits.vecmat.dnxm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.djunits.vecmat.dn.AbsVectorN;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AbsMatrixNxM}.
 * <p>
 * This class provides exhaustive coverage of AbsMatrixNxM and all inherited functionality from AbsSquareMatrix, AbsMatrix,
 * AbsTable, AbsVectorMatrix and Value.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsMatrixNxMTest
{
    /**
     * Create a standard test matrix.
     * @return a test matrix
     */
    private AbsMatrixNxM<Direction, Angle> northDeg2x3()
    {
        return AbsMatrixNxM.of(new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0}, 2, 3, Angle.Unit.deg,
                Direction.Reference.NORTH);
    }

    // ==================================== Constructor and base methods ====================================

    /**
     * Test constructor and base methods such as rows(), cols(), order().
     */
    @Test
    public void testCtorBase()
    {
        var rm = MatrixNxM.of(new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0}, 2, 3, Angle.Unit.deg);
        var am = new AbsMatrixNxM<>(rm, Direction.Reference.NORTH);

        assertEquals(Angle.Unit.deg, am.getDisplayUnit());
        assertEquals(Direction.Reference.NORTH, am.getReference());

        Direction d0 = new Direction(0.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d90 = new Direction(90.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d180 = new Direction(180.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d270 = new Direction(270.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d45 = new Direction(45.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d135 = new Direction(135.0, Angle.Unit.deg, Direction.Reference.NORTH);

        assertEquals(d0, am.get(0, 0));
        assertEquals(d0, am.mget(1, 1));

        assertEquals(d90, am.get(0, 1));
        assertEquals(d90, am.mget(1, 2));

        assertEquals(d180, am.get(0, 2));
        assertEquals(d180, am.mget(1, 3));

        assertEquals(d270, am.get(1, 0));
        assertEquals(d270, am.mget(2, 1));

        assertEquals(d45, am.get(1, 1));
        assertEquals(d45, am.mget(2, 2));

        assertEquals(d135, am.get(1, 2));
        assertEquals(d135, am.mget(2, 3));

        assertEquals(2, am.rows());
        assertEquals(3, am.cols());

        assertTrue(am.isAbsolute());
        assertFalse(am.isRelative());

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
        var rm = MatrixNxM.of(new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0}, 2, 3, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsMatrixNxM<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsMatrixNxM<>(rm, null));
    }

    /**
     * Test instantiateSi().
     */
    @Test
    public void testInstantiateSi()
    {
        var m = northDeg2x3();
        var m2 = m.instantiateSi(
                new double[] {0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 2.0 * Math.PI, 2.5 * Math.PI, 3.0 * Math.PI},
                Direction.Reference.EAST);

        assertEquals(0.5 * Math.PI, m2.si(0, 0));
        assertEquals(Angle.Unit.deg, m2.getDisplayUnit()); // same as original northDeg matrix
        assertEquals(Direction.Reference.EAST, m2.getReference());

        assertThrows(NullPointerException.class, () -> m.instantiateSi(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> m.instantiateSi(m2.getSiArray(), null));
        assertThrows(IllegalArgumentException.class, () -> m.instantiateSi(new double[] {}, Direction.Reference.EAST));
        assertThrows(IllegalArgumentException.class,
                () -> m.instantiateSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, Direction.Reference.EAST));
    }

    // ==================================== Static factory methods ====================================

    /**
     * Test all of() and ofSi() static factories.
     */
    @SuppressWarnings("checkstyle:methodlength")
    @Test
    public void testStaticFactories()
    {
        Angle[] qa = {new Angle(0, Angle.Unit.deg), new Angle(90, Angle.Unit.deg), new Angle(180, Angle.Unit.deg),
                new Angle(270, Angle.Unit.deg), new Angle(45, Angle.Unit.deg), new Angle(135, Angle.Unit.deg)};
        var adeg = new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0};
        var adeg10 = new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0, 225.0, 30.0, 315.0, 60.0};
        var adegg = new double[][] {{0.0, 90.0, 180.0}, {270.0, 45.0, 135.0}};
        var arad = new double[] {0.0, 0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 0.25 * Math.PI, 0.75 * Math.PI};
        var aradg = new double[][] {{0.0, 0.5 * Math.PI, Math.PI}, {1.5 * Math.PI, 0.25 * Math.PI, 0.75 * Math.PI}};
        var rm = MatrixNxM.of(new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0}, 2, 3, Angle.Unit.deg);
        var am = new AbsMatrixNxM<>(rm, Direction.Reference.NORTH);

        // of(double[], unit, ref)
        AbsMatrixNxM<Direction, Angle> mdu = AbsMatrixNxM.of(adeg, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mdu.getDisplayUnit());

        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.of((double[]) null, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of(adeg, 2, 3, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of(adeg, 2, 3, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new double[] {}, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(adeg10, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(adeg, 0, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(adeg, -1, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(adeg, 2, 0, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(adeg, 2, -1, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsMatrixNxM<Direction, Angle> msi = AbsMatrixNxM.ofSi(arad, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), msi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, msi.getDisplayUnit());

        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.ofSi((double[]) null, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.ofSi(arad, 2, 3, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.ofSi(arad, 2, 3, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(new double[] {}, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(adeg10, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(arad, 0, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(arad, -1, 3, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(arad, 2, 0, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(arad, 2, -1, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(double[][], unit, ref)
        AbsMatrixNxM<Direction, Angle> mdgu = AbsMatrixNxM.of(adegg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mdgu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mdgu.getDisplayUnit());

        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.of((double[][]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of(adegg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of(adegg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new double[][] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new double[][] {{}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new double[][] {{1, 2, 3}, {4, 5}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new double[][] {{}, {4, 5, 6}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new double[][] {{1, 2, 3}, {}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.of(new double[][] {null, {4, 5, 6}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.of(new double[][] {{1, 2, 3}, null, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.of(new double[][] {{1, 2, 3}, {4, 5, 6}, null}, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[][], displayUnit, ref)
        AbsMatrixNxM<Direction, Angle> mgsi = AbsMatrixNxM.ofSi(aradg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mgsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mgsi.getDisplayUnit());

        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.ofSi((double[][]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.ofSi(adegg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.ofSi(adegg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(new double[][] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(new double[][] {{}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(new double[][] {{1, 2, 3}, {4, 5}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(new double[][] {{}, {4, 5, 6}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(new double[][] {{1, 2, 3}, {}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.ofSi(new double[][] {{1, 2, 3}, {4, 5, 6}, {}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.ofSi(new double[][] {null, {4, 5, 6}, {7, 8, 9}},
                Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.ofSi(new double[][] {{1, 2, 3}, null, {7, 8, 9}},
                Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.ofSi(new double[][] {{1, 2, 3}, {4, 5, 6}, null},
                Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsMatrixNxM<Direction, Angle> mqa = AbsMatrixNxM.of(qa, 2, 3, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqa.getDisplayUnit());

        AbsMatrixNxM<Direction, Angle> mqa2 = AbsMatrixNxM
                .of(new Angle[] {Angle.of(0.0, "rad"), qa[1], qa[2], qa[3], qa[4], qa[5]}, 2, 3, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, mqa2.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of((Angle[]) null, 2, 3, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of(qa, 2, 3, null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM.of(new Angle[] {}, 2, 3, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM
                .of(new Angle[] {qa[0], qa[1], qa[2], qa[3], qa[4], qa[5], qa[1], qa[2]}, 2, 3, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM.of(qa, 0, 3, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM.of(qa, -1, 3, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM.of(qa, 2, 0, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM.of(qa, 2, -1, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM.of(qa, 2, 2, Direction.Reference.NORTH));

        // of(Q[][], ref)
        Angle[][] qg = {{qa[0], qa[1], qa[2]}, {qa[3], qa[4], qa[5]}};
        AbsMatrixNxM<Direction, Angle> mqg = AbsMatrixNxM.of(qg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqg.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqg.getDisplayUnit());

        AbsMatrixNxM<Direction, Angle> mqg2 = AbsMatrixNxM
                .of(new Angle[][] {{Angle.of(0.0, "rad"), qa[1], qa[2]}, {qa[3], qa[4], qa[5]}}, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqg2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, mqg2.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of((Angle[][]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of(qg, null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM.of(new Angle[][] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrixNxM.of(new Angle[][] {{}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new Angle[][] {{qa[0], qa[1], qa[2]}, {qa[3], qa[4]}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrixNxM.of(new Angle[][] {{}, {qa[3], qa[4], qa[5]}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.of(new Angle[][] {null, {qa[3], qa[4], qa[5]}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.of(new Angle[][] {{qa[0], qa[1], qa[2]}, {qa[3], qa[4], null}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrixNxM.of(new Angle[][] {{null, qa[1], qa[2]}, {qa[3], qa[4], qa[5]}}, Direction.Reference.NORTH));

        // of(Matrix, ref)
        MatrixNxM<Angle> rel = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Angle.Unit.deg);
        AbsMatrixNxM<Direction, Angle> amrel = AbsMatrixNxM.of(rel, Direction.Reference.NORTH);

        assertEquals(1.0 * Math.PI / 180.0, amrel.si(0, 0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, amrel.si(0, 1), 1E-10);
        assertEquals(3.0 * Math.PI / 180.0, amrel.si(0, 2), 1E-10);
        assertEquals(4.0 * Math.PI / 180.0, amrel.si(1, 0), 1E-10);
        assertEquals(5.0 * Math.PI / 180.0, amrel.si(1, 1), 1E-10);
        assertEquals(6.0 * Math.PI / 180.0, amrel.si(1, 2), 1E-10);
        assertEquals(Angle.Unit.deg, amrel.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of((MatrixNxM<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrixNxM.of(rel, null));
    }

    // ==================================== Scalar & SI array / grid access ====================================

    /**
     * Test scalar and SI array / grid extraction.
     */
    @Test
    public void testScalarAndSiGrids()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        Direction[][] s = m.getScalarGrid();
        double[][] si = m.getSiGrid();

        assertEquals(2, s.length);
        assertEquals(2, si.length);
        assertEquals(3, s[0].length);
        assertEquals(3, si[0].length);

        assertEquals(90.0, s[0][1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(Math.PI, si[0][2], 1e-12);

        Direction[] a = m.getScalarArray();
        double[] asi = m.getSiArray();

        assertEquals(6, a.length);
        assertEquals(6, asi.length);

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
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        double[] row = m.getRowSi(1);
        double[] col = m.getColumnSi(1);

        assertEquals(3, row.length);
        assertEquals(2, col.length);

        assertEquals(1.5 * Math.PI, row[0], 1e-12); // 270 deg
        assertEquals(45.0, Math.toDegrees(row[1]), 1e-12);
        assertEquals(90.0, Math.toDegrees(col[0]), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(3));
    }

    /**
     * Test 0-based scalar row and column access.
     */
    @Test
    public void testGetRowAndColumnScalars()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        Direction[] row = m.getRowScalars(0);
        Direction[] col = m.getColumnScalars(1);

        assertEquals(3, row.length);
        assertEquals(2, col.length);

        assertEquals(0.0, row[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(180.0, row[2].getInUnit(Angle.Unit.deg), 1e-12);

        assertEquals(90.0, col[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, col[1].getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(3));
    }

    /**
     * Test 1-based row and column SI access.
     */
    @Test
    public void testMgetRowAndColumnSi()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        double[] row = m.mgetRowSi(2);
        double[] col = m.mgetColumnSi(2);

        assertEquals(3, row.length);
        assertEquals(2, col.length);

        assertEquals(1.5 * Math.PI, row[0], 1e-12);
        assertEquals(45.0, Math.toDegrees(row[1]), 1e-12);
        assertEquals(45.0, Math.toDegrees(col[1]), 1e-12);
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(4));
    }

    /**
     * Test 1-based scalar row and column access.
     */
    @Test
    public void testMgetRowAndColumnScalars()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        Direction[] row = m.mgetRowScalars(1);
        Direction[] col = m.mgetColumnScalars(2);

        assertEquals(3, row.length);
        assertEquals(2, col.length);

        assertEquals(0.0, row[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(180.0, row[2].getInUnit(Angle.Unit.deg), 1e-12);

        assertEquals(90.0, col[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, col[1].getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(4));
    }

    // ==================================== Row & column vector ====================================

    /**
     * Test 0-based vector row and column access.
     */
    @Test
    public void testGetRowAndColumnVector()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        var row = m.getRowVector(0);
        var col = m.getColumnVector(1);

        assertEquals(3, row.size());
        assertEquals(2, col.size());

        assertEquals(0.0, row.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(180.0, row.get(2).getInUnit(Angle.Unit.deg), 1e-12);

        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, col.get(1).getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(2));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(3));
    }

    /**
     * Test 1-based vector row and column access.
     */
    @Test
    public void testMgetRowAndColumnVector()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        var row = m.mgetRowVector(1);
        var col = m.mgetColumnVector(2);

        assertEquals(3, row.size());
        assertEquals(2, col.size());

        assertEquals(0.0, row.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(180.0, row.get(2).getInUnit(Angle.Unit.deg), 1e-12);

        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, col.get(1).getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(4));
    }

    // ==================================== min, max, median ====================================

    /**
     * Test min(), max() and median().
     */
    @Test
    public void testMinMaxMedian()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        assertEquals(0.0, m.min().si());
        assertEquals(1.5 * Math.PI, m.max().si(), 1E-10); // 315 deg
        assertEquals((90.0 + 135.0) / 2.0, m.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, m.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, m.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, m.median().getDisplayUnit());

        AbsMatrixNxM<Direction, Angle> e =
                AbsMatrixNxM.ofSi(new double[] {1, 1, 1, 1, 1, 1}, 3, 2, Angle.Unit.rad, Direction.Reference.NORTH);
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
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        var mt = m.transpose();
        var mt2 = mt.transpose();

        assertEquals(m, mt2);

        assertEquals(0.0, mt.si(0, 0));
        assertEquals(1.5 * Math.PI, mt.si(0, 1), 1E-10); // from (1,0) = 270 deg

        assertEquals(0.5 * Math.PI, mt.si(1, 0), 1E-10); // from (0,1) = 90 deg
        assertEquals(45.0, mt.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);

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
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();

        AbsMatrixNxM<Direction, Angle> madd = northDeg2x3().add(Angle.of(5.0, "deg"));
        AbsMatrixNxM<Direction, Angle> msub = northDeg2x3().subtract(Angle.of(5.0, "deg"));

        MatrixNxM<Angle> m0 = m.subtract(m);
        assertArrayEquals(new double[] {0, 0, 0, 0, 0, 0}, m0.si());

        assertEquals(5.0, madd.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(95.0, madd.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(185.0, madd.get(0, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(275.0, madd.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(50.0, madd.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(140.0, madd.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(-5.0, msub.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(85.0, msub.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, msub.get(0, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(265.0, msub.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(40.0, msub.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(130.0, msub.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        MatrixNxM<Angle> m123456 = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Angle.Unit.deg);
        madd = m.add(m123456);
        msub = m.subtract(m123456);

        assertEquals(1.0, madd.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(92.0, madd.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(183.0, madd.get(0, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(274.0, madd.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(50.0, madd.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(141.0, madd.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(-1.0, msub.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(88.0, msub.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(177.0, msub.get(0, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(266.0, msub.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(40.0, msub.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(129.0, msub.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> m.add((Angle) null));
        assertThrows(NullPointerException.class, () -> m.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> m.add((MatrixNxM<Angle>) null));
        assertThrows(NullPointerException.class, () -> m.subtract((MatrixNxM<Angle>) null));
        assertThrows(NullPointerException.class, () -> m.subtract((AbsMatrixNxM<Direction, Angle>) null));
    }

    // ==================================== toString ====================================

    /**
     * Test toString().
     */
    @Test
    public void testToString()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();
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
        AbsMatrixNxM<Direction, Angle> mdn =
                AbsMatrixNxM.ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6}, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsMatrixNxM<Direction, Angle> mrn =
                AbsMatrixNxM.ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6}, 2, 3, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsMatrixNxM<Direction, Angle> mde =
                AbsMatrixNxM.ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6}, 2, 3, Angle.Unit.deg, Direction.Reference.EAST);
        AbsMatrixNxM<Direction, Angle> mdt =
                AbsMatrixNxM.ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6}, 3, 2, Angle.Unit.deg, Direction.Reference.NORTH);

        assertTrue(mdn.equals(mdn));
        assertTrue(mdn.equals(mrn));
        assertFalse(mdn.equals(mde));
        assertFalse(mdn.equals(mdt));
        assertFalse(mdn.equals(null));
        assertFalse(mdn.equals(new String("abc")));
        assertFalse(mdn.equals(northDeg2x3()));

        assertEquals(mdn.hashCode(), AbsMatrixNxM
                .ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6}, 2, 3, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertEquals(mdn.hashCode(), mrn.hashCode());
        assertNotEquals(mdn.hashCode(), mde.hashCode());
        assertNotEquals(mdn.hashCode(), northDeg2x3().hashCode());
    }

    // ==================================== Vector access ====================================

    /**
     * Test mgetRowVector and getColumnVector.
     */
    @Test
    public void testVectorAccess()
    {
        AbsMatrixNxM<Direction, Angle> m = northDeg2x3();
        AbsVectorN.Row<Direction, Angle> row = m.mgetRowVector(2);
        AbsVectorN.Col<Direction, Angle> col = m.getColumnVector(1);

        assertEquals(3, row.size());
        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);
    }

}
