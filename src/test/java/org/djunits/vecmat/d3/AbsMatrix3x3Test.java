package org.djunits.vecmat.d3;

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
 * Tests for {@link AbsMatrix3x3}.
 * <p>
 * This class provides exhaustive coverage of AbsMatrix3x3 and all inherited functionality from AbsSquareMatrix, AbsMatrix,
 * AbsTable, AbsVectorMatrix and Value.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsMatrix3x3Test
{
    /**
     * Create a standard test matrix.
     * @return a test matrix
     */
    private AbsMatrix3x3<Direction, Angle> northDeg()
    {
        return AbsMatrix3x3.of(new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0, 225.0, 30.0, 315.0}, Angle.Unit.deg,
                Direction.Reference.NORTH);
    }

    // ==================================== Constructor and base methods ====================================

    /**
     * Test constructor and base methods such as rows(), cols(), order().
     */
    @Test
    public void testCtorBase()
    {
        var rm = Matrix3x3.of(new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0, 225.0, 30.0, 315.0}, Angle.Unit.deg);
        var am = new AbsMatrix3x3<>(rm, Direction.Reference.NORTH);

        assertEquals(Angle.Unit.deg, am.getDisplayUnit());
        assertEquals(Direction.Reference.NORTH, am.getReference());

        Direction d0 = new Direction(0.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d90 = new Direction(90.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d180 = new Direction(180.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d270 = new Direction(270.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d45 = new Direction(45.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d135 = new Direction(135.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d225 = new Direction(225.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d30 = new Direction(30.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d315 = new Direction(315.0, Angle.Unit.deg, Direction.Reference.NORTH);

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

        assertEquals(d225, am.get(2, 0));
        assertEquals(d225, am.mget(3, 1));

        assertEquals(d30, am.get(2, 1));
        assertEquals(d30, am.mget(3, 2));

        assertEquals(d315, am.get(2, 2));
        assertEquals(d315, am.mget(3, 3));

        assertEquals(3, am.rows());
        assertEquals(3, am.cols());
        assertEquals(3, am.order());

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
     * Test exceptions for constructor.
     */
    @Test
    public void testCtorBaseExceptions()
    {
        var rm = Matrix3x3.of(new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0, 225.0, 30.0, 315.0}, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsMatrix3x3<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsMatrix3x3<>(rm, null));
    }

    /**
     * Test instantiateSi().
     */
    @Test
    public void testInstantiateSi()
    {
        var m = northDeg();
        var m2 = m.instantiateSi(new double[] {0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 2.0 * Math.PI, 2.5 * Math.PI,
                3.0 * Math.PI, 3.5 * Math.PI, 4.0 * Math.PI, 4.5 * Math.PI}, Direction.Reference.EAST);

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
                new Angle(270, Angle.Unit.deg), new Angle(45, Angle.Unit.deg), new Angle(135, Angle.Unit.deg),
                new Angle(225, Angle.Unit.deg), new Angle(30, Angle.Unit.deg), new Angle(315, Angle.Unit.deg)};
        var adeg = new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0, 225.0, 30.0, 315.0};
        var adeg10 = new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0, 225.0, 30.0, 315.0, 60.0};
        var adegg = new double[][] {{0.0, 90.0, 180.0}, {270.0, 45.0, 135.0}, {225.0, 30.0, 315.0}};
        var arad = new double[] {0.0, 0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 0.25 * Math.PI, 0.75 * Math.PI, 1.25 * Math.PI,
                (1.0 / 6.0) * Math.PI, 1.75 * Math.PI};
        var aradg = new double[][] {{0.0, 0.5 * Math.PI, Math.PI}, {1.5 * Math.PI, 0.25 * Math.PI, 0.75 * Math.PI},
                {1.25 * Math.PI, (1.0 / 6.0) * Math.PI, 1.75 * Math.PI}};
        var rm = Matrix3x3.of(new double[] {0.0, 90.0, 180.0, 270.0, 45.0, 135.0, 225.0, 30.0, 315.0}, Angle.Unit.deg);
        var am = new AbsMatrix3x3<>(rm, Direction.Reference.NORTH);

        // of(double[], unit, ref)
        AbsMatrix3x3<Direction, Angle> mdu = AbsMatrix3x3.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mdu.getDisplayUnit());

        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(adeg10, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsMatrix3x3<Direction, Angle> msi = AbsMatrix3x3.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), msi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, msi.getDisplayUnit());

        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.ofSi(adeg10, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(double[][], unit, ref)
        AbsMatrix3x3<Direction, Angle> mdgu = AbsMatrix3x3.of(adegg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mdgu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mdgu.getDisplayUnit());

        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.of((double[][]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(adegg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(adegg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.of(new double[][] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.of(new double[][] {{}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.of(new double[][] {{1, 2, 3}, {4, 5}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.of(new double[][] {{}, {4, 5, 6}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.of(new double[][] {{1, 2, 3}, {}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.of(new double[][] {null, {4, 5, 6}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.of(new double[][] {{1, 2, 3}, null, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.of(new double[][] {{1, 2, 3}, {4, 5, 6}, null}, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[][], displayUnit, ref)
        AbsMatrix3x3<Direction, Angle> mgsi = AbsMatrix3x3.ofSi(aradg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mgsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mgsi.getDisplayUnit());

        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.ofSi((double[][]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.ofSi(adegg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.ofSi(adegg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.ofSi(new double[][] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.ofSi(new double[][] {{}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.ofSi(new double[][] {{1, 2, 3}, {4, 5}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.ofSi(new double[][] {{}, {4, 5, 6}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.ofSi(new double[][] {{1, 2, 3}, {}, {7, 8, 9}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.ofSi(new double[][] {{1, 2, 3}, {4, 5, 6}, {}}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.ofSi(new double[][] {null, {4, 5, 6}, {7, 8, 9}},
                Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.ofSi(new double[][] {{1, 2, 3}, null, {7, 8, 9}},
                Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.ofSi(new double[][] {{1, 2, 3}, {4, 5, 6}, null},
                Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsMatrix3x3<Direction, Angle> mqa = AbsMatrix3x3.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqa.getDisplayUnit());

        AbsMatrix3x3<Direction, Angle> mqa2 =
                AbsMatrix3x3.of(new Angle[] {Angle.of(0.0, "rad"), qa[1], qa[2], qa[3], qa[4], qa[5], qa[6], qa[7], qa[8]},
                        Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, mqa2.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Angle[] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsMatrix3x3.of(new Angle[] {qa[0], qa[1], qa[2], qa[3], qa[4], qa[5], qa[6], qa[7], qa[8], qa[2]},
                        Direction.Reference.NORTH));

        // of(Q[][], ref)
        Angle[][] qg = {{qa[0], qa[1], qa[2]}, {qa[3], qa[4], qa[5]}, {qa[6], qa[7], qa[8]}};
        AbsMatrix3x3<Direction, Angle> mqg = AbsMatrix3x3.of(qg, Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqg.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mqg.getDisplayUnit());

        AbsMatrix3x3<Direction,
                Angle> mqg2 = AbsMatrix3x3.of(
                        new Angle[][] {{Angle.of(0.0, "rad"), qa[1], qa[2]}, {qa[3], qa[4], qa[5]}, {qa[6], qa[7], qa[8]}},
                        Direction.Reference.NORTH);
        assertArrayEquals(am.getSiArray(), mqg2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, mqg2.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of((Angle[][]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(qg, null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Angle[][] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Angle[][] {{}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3
                .of(new Angle[][] {{qa[0], qa[1], qa[2]}, {qa[3], qa[4]}, {qa[6], qa[7], qa[8]}}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3
                .of(new Angle[][] {{}, {qa[3], qa[4], qa[5]}, {qa[6], qa[7], qa[8]}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3
                .of(new Angle[][] {null, {qa[3], qa[4], qa[5]}, {qa[6], qa[7], qa[8]}}, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.of(new Angle[][] {{qa[0], qa[1], qa[2]}, {qa[3], qa[4], null}, {qa[6], qa[7], qa[8]}},
                        Direction.Reference.NORTH));
        assertThrows(NullPointerException.class,
                () -> AbsMatrix3x3.of(new Angle[][] {{null, qa[1], qa[2]}, {qa[3], qa[4], qa[5]}, {qa[6], qa[7], qa[8]}},
                        Direction.Reference.NORTH));

        // of(Matrix, ref)
        Matrix3x3<Angle> rel = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Angle.Unit.deg);
        AbsMatrix3x3<Direction, Angle> amrel = AbsMatrix3x3.of(rel, Direction.Reference.NORTH);

        assertEquals(1.0 * Math.PI / 180.0, amrel.si(0, 0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, amrel.si(0, 1), 1E-10);
        assertEquals(3.0 * Math.PI / 180.0, amrel.si(0, 2), 1E-10);
        assertEquals(4.0 * Math.PI / 180.0, amrel.si(1, 0), 1E-10);
        assertEquals(5.0 * Math.PI / 180.0, amrel.si(1, 1), 1E-10);
        assertEquals(6.0 * Math.PI / 180.0, amrel.si(1, 2), 1E-10);
        assertEquals(7.0 * Math.PI / 180.0, amrel.si(2, 0), 1E-10);
        assertEquals(8.0 * Math.PI / 180.0, amrel.si(2, 1), 1E-10);
        assertEquals(9.0 * Math.PI / 180.0, amrel.si(2, 2), 1E-10);

        assertEquals(Angle.Unit.deg, amrel.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of((Matrix3x3<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(rel, null));
    }

    /**
     * Test the of(A[]) and of(A[][]) static factories.
     */
    @Test
    public void testAbsStaticFactories()
    {
        Angle[] qa = {new Angle(0, Angle.Unit.deg), new Angle(90, Angle.Unit.deg), new Angle(180, Angle.Unit.deg),
                new Angle(270, Angle.Unit.deg), new Angle(45, Angle.Unit.deg), new Angle(135, Angle.Unit.deg),
                new Angle(225, Angle.Unit.deg), new Angle(30, Angle.Unit.deg), new Angle(315, Angle.Unit.deg)};
        Direction[] arr = new Direction[9];
        for (int i = 0; i < 9; i++)
        {
            arr[i] = new Direction(qa[i], Direction.Reference.EAST);
        }
        Direction[][] grid = new Direction[3][3];
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                grid[r][c] = new Direction(qa[r * 3 + c], Direction.Reference.EAST);
            }
        }

        var aa = AbsMatrix3x3.of(arr);
        assertEquals(Angle.Unit.deg, aa.getDisplayUnit());
        assertEquals(Direction.Reference.EAST, aa.getReference());
        assertEquals(90.0, aa.get(0, 1).getInUnit(), 1E-10);
        assertEquals(270.0, aa.get(1, 0).getInUnit(), 1E-10);
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of((Direction[]) null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Direction[] {}));
        var arrRef = arr.clone();
        arrRef[2] = new Direction(qa[2], Direction.Reference.NORTH);
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(arrRef));
        arrRef[2] = null;
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(arrRef));

        var ag = AbsMatrix3x3.of(grid);
        assertEquals(Angle.Unit.deg, ag.getDisplayUnit());
        assertEquals(Direction.Reference.EAST, ag.getReference());
        assertEquals(90.0, ag.get(0, 1).getInUnit(), 1E-10);
        assertEquals(270.0, ag.get(1, 0).getInUnit(), 1E-10);
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of((Direction[][]) null));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Direction[][] {}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Direction[][] {{}}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3
                .of(new Direction[][] {{arr[1], arr[2]}, {arr[1], arr[2], arr[3]}, {arr[1], arr[2], arr[3]}}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3
                .of(new Direction[][] {{arr[1], arr[2], arr[3]}, {arr[1], arr[2], arr[3]}, {arr[1], arr[2]}}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Direction[][] {{arr[1], arr[2]}, {arr[3]}}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Direction[][] {arr, arr}));
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(new Direction[][] {{}, {}}));
        var gridRef = grid.clone();
        gridRef[1][0] = new Direction(grid[1][0].getQuantity(), Direction.Reference.NORTH);
        assertThrows(IllegalArgumentException.class, () -> AbsMatrix3x3.of(gridRef));
        gridRef[1][0] = null;
        assertThrows(NullPointerException.class, () -> AbsMatrix3x3.of(gridRef));
    }

    // ==================================== Scalar & SI array / grid access ====================================

    /**
     * Test scalar and SI array / grid extraction.
     */
    @Test
    public void testScalarAndSiGrids()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        Direction[][] s = m.getScalarGrid();
        double[][] si = m.getSiGrid();

        assertEquals(3, s.length);
        assertEquals(3, si.length);
        assertEquals(3, s[0].length);
        assertEquals(3, si[0].length);

        assertEquals(90.0, s[0][1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(Math.PI, si[0][2], 1e-12);

        Direction[] a = m.getScalarArray();
        double[] asi = m.getSiArray();

        assertEquals(9, a.length);
        assertEquals(9, asi.length);

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
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        double[] row = m.getRowSi(1);
        double[] col = m.getColumnSi(1);

        assertEquals(3, row.length);
        assertEquals(3, col.length);

        assertEquals(1.5 * Math.PI, row[0], 1e-12); // 270 deg
        assertEquals(45.0, Math.toDegrees(row[1]), 1e-12);
        assertEquals(90.0, Math.toDegrees(col[0]), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(3));
    }

    /**
     * Test 0-based scalar row and column access.
     */
    @Test
    public void testGetRowAndColumnScalars()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        Direction[] row = m.getRowScalars(0);
        Direction[] col = m.getColumnScalars(1);

        assertEquals(3, row.length);
        assertEquals(3, col.length);

        assertEquals(0.0, row[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(180.0, row[2].getInUnit(Angle.Unit.deg), 1e-12);

        assertEquals(90.0, col[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, col[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(30.0, col[2].getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(3));
    }

    /**
     * Test 1-based row and column SI access.
     */
    @Test
    public void testMgetRowAndColumnSi()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        double[] row = m.mgetRowSi(2);
        double[] col = m.mgetColumnSi(2);

        assertEquals(3, row.length);
        assertEquals(3, col.length);

        assertEquals(1.5 * Math.PI, row[0], 1e-12);
        assertEquals(45.0, Math.toDegrees(row[1]), 1e-12);
        assertEquals(30.0, Math.toDegrees(col[2]), 1e-12);
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(4));
    }

    /**
     * Test 1-based scalar row and column access.
     */
    @Test
    public void testMgetRowAndColumnScalars()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        Direction[] row = m.mgetRowScalars(1);
        Direction[] col = m.mgetColumnScalars(2);

        assertEquals(3, row.length);
        assertEquals(3, col.length);

        assertEquals(0.0, row[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(180.0, row[2].getInUnit(Angle.Unit.deg), 1e-12);

        assertEquals(90.0, col[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, col[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(30.0, col[2].getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(4));
    }

    // ==================================== Diagonal SI and scalar ====================================

    /**
     * Test diagonal SI.
     */
    @Test
    public void testDiagonalSi()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();
        double[] diag = m.getDiagonalSi();

        assertEquals(3, diag.length);
        assertEquals(0.0, diag[0], 1e-12);
        assertEquals(45.0, Math.toDegrees(diag[1]), 1e-12);
        assertEquals(315.0, Math.toDegrees(diag[2]), 1e-12);
    }

    /**
     * Test diagonal scalar.
     */
    @Test
    public void testDiagonalScalars()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();
        Direction[] diag = m.getDiagonalScalars();

        assertEquals(3, diag.length);
        assertEquals(0.0, diag[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, diag[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(315.0, diag[2].getInUnit(Angle.Unit.deg), 1e-12);
    }

    // ==================================== Row & column vector ====================================

    /**
     * Test 0-based vector row and column access.
     */
    @Test
    public void testGetRowAndColumnVector()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        var row = m.getRowVector(0);
        var col = m.getColumnVector(1);

        assertEquals(3, row.size());
        assertEquals(3, col.size());

        assertEquals(0.0, row.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(180.0, row.get(2).getInUnit(Angle.Unit.deg), 1e-12);

        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, col.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(30.0, col.get(2).getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(3));
    }

    /**
     * Test 1-based vector row and column access.
     */
    @Test
    public void testMgetRowAndColumnVector()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        var row = m.mgetRowVector(1);
        var col = m.mgetColumnVector(2);

        assertEquals(3, row.size());
        assertEquals(3, col.size());

        assertEquals(0.0, row.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(90.0, row.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(180.0, row.get(2).getInUnit(Angle.Unit.deg), 1e-12);

        assertEquals(90.0, col.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, col.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(30.0, col.get(2).getInUnit(Angle.Unit.deg), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(4));
    }

    // ==================================== Diagonal vector ====================================

    /**
     * Test diagonal vector.
     */
    @Test
    public void testDiagonalVector()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();
        var diag = m.getDiagonalVector();

        assertEquals(3, diag.size());
        assertEquals(0.0, diag.get(0).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(45.0, diag.get(1).getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(315.0, diag.get(2).getInUnit(Angle.Unit.deg), 1e-12);
    }

    // ==================================== min, max, median ====================================

    /**
     * Test min(), max() and median().
     */
    @Test
    public void testMinMaxMedian()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        assertEquals(0.0, m.min().si());
        assertEquals(1.75 * Math.PI, m.max().si(), 1E-10); // 315 deg
        assertEquals(135.0, m.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, m.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, m.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, m.median().getDisplayUnit());

        AbsMatrix3x3<Direction, Angle> e =
                AbsMatrix3x3.ofSi(new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1}, Angle.Unit.rad, Direction.Reference.NORTH);
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
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        var mt = m.transpose();
        var mt2 = mt.transpose();

        assertEquals(m, mt2);

        assertEquals(0.0, mt.si(0, 0));
        assertEquals(1.5 * Math.PI, mt.si(0, 1), 1E-10); // from (1,0) = 270 deg
        assertEquals(1.25 * Math.PI, mt.si(0, 2), 1E-10); // from (2,0) = 225 deg

        assertEquals(0.5 * Math.PI, mt.si(1, 0), 1E-10); // from (0,1) = 90 deg
        assertEquals(45.0, mt.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(30.0, mt.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Math.PI, mt.si(2, 0), 1E-10); // from (0,2) = 180 deg
        assertEquals(0.75 * Math.PI, mt.si(2, 1), 1E-10); // from (1,2) = 135 deg
        assertEquals(315.0, mt.get(2, 2).getInUnit(Angle.Unit.deg), 1E-10);

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
        AbsMatrix3x3<Direction, Angle> m = northDeg();

        AbsMatrix3x3<Direction, Angle> madd = northDeg().add(Angle.of(5.0, "deg"));
        AbsMatrix3x3<Direction, Angle> msub = northDeg().subtract(Angle.of(5.0, "deg"));

        Matrix3x3<Angle> m0 = m.subtract(m);
        assertArrayEquals(new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0}, m0.getSiArray());

        assertEquals(5.0, madd.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(95.0, madd.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(185.0, madd.get(0, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(275.0, madd.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(50.0, madd.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(140.0, madd.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(230.0, madd.get(2, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(35.0, madd.get(2, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(320.0, madd.get(2, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(-5.0, msub.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(85.0, msub.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, msub.get(0, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(265.0, msub.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(40.0, msub.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(130.0, msub.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(220.0, msub.get(2, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(25.0, msub.get(2, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(310.0, msub.get(2, 2).getInUnit(Angle.Unit.deg), 1E-10);

        Matrix3x3<Angle> m123456789 = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Angle.Unit.deg);
        madd = m.add(m123456789);
        msub = m.subtract(m123456789);

        assertEquals(1.0, madd.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(92.0, madd.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(183.0, madd.get(0, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(274.0, madd.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(50.0, madd.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(141.0, madd.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(232.0, madd.get(2, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(38.0, madd.get(2, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(324.0, madd.get(2, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(-1.0, msub.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(88.0, msub.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(177.0, msub.get(0, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(266.0, msub.get(1, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(40.0, msub.get(1, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(129.0, msub.get(1, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(218.0, msub.get(2, 0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(22.0, msub.get(2, 1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(306.0, msub.get(2, 2).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> m.add((Angle) null));
        assertThrows(NullPointerException.class, () -> m.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> m.add((Matrix3x3<Angle>) null));
        assertThrows(NullPointerException.class, () -> m.subtract((Matrix3x3<Angle>) null));
        assertThrows(NullPointerException.class, () -> m.subtract((AbsMatrix3x3<Direction, Angle>) null));

        Matrix3x3<Angle> msubq = m.subtract(Direction.of(5.0, "deg", Direction.Reference.NORTH));
        assertEquals(85.0, msubq.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);
    }

    // ==================================== toString ====================================

    /**
     * Test toString().
     */
    @Test
    public void testToString()
    {
        AbsMatrix3x3<Direction, Angle> m = northDeg();
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
        AbsMatrix3x3<Direction, Angle> mdn = AbsMatrix3x3.ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9},
                Angle.Unit.deg, Direction.Reference.NORTH);
        AbsMatrix3x3<Direction, Angle> mrn = AbsMatrix3x3.ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9},
                Angle.Unit.rad, Direction.Reference.NORTH);
        AbsMatrix3x3<Direction, Angle> mde = AbsMatrix3x3.ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9},
                Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(mdn.equals(mdn));
        assertTrue(mdn.equals(mrn));
        assertFalse(mdn.equals(mde));
        assertFalse(mdn.equals(null));
        assertFalse(mdn.equals(new String("abc")));
        assertFalse(mdn.equals(northDeg()));

        assertEquals(mdn.hashCode(), AbsMatrix3x3
                .ofSi(new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9}, Angle.Unit.deg, Direction.Reference.NORTH)
                .hashCode());
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
        AbsMatrix3x3<Direction, Angle> m = northDeg();
        AbsVector3.Row<Direction, Angle> row = m.mgetRowVector(2);
        AbsVector3.Col<Direction, Angle> col = m.getColumnVector(1);

        assertEquals(3, row.size());
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
        AbsMatrix3x3<Direction, Angle> sym =
                AbsMatrix3x3.of(new double[] {1, 2, 3, 2, 4, 5, 3, 5, 6}, Angle.Unit.deg, Direction.Reference.NORTH);

        assertTrue(sym.isSymmetric());
        assertTrue(sym.isSymmetric(new Angle(1e-9, Angle.Unit.rad)));

        assertFalse(sym.isSkewSymmetric());
        assertFalse(sym.isSkewSymmetric(new Angle(1e-9, Angle.Unit.rad)));

        AbsMatrix3x3<Direction, Angle> m = northDeg();
        assertThrows(NullPointerException.class, () -> m.isSymmetric(null));
        assertThrows(NullPointerException.class, () -> m.isSkewSymmetric(null));
    }

    /**
     * Test as() functions.
     */
    @Test
    @DisplayName("Matrix3x3 as() functions test")
    public void testAsFunctions()
    {
        var m = northDeg();

        var m2 = m.asAbsMatrix3x3();
        assertArrayEquals(m.getSiArray(), m2.asAbsMatrix3x3().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, m2.getDisplayUnit());
        assertEquals(m.getReference(), m2.getReference());
        assertEquals(90.0, m2.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);

        var mNxN = m.asAbsMatrixNxN();
        assertArrayEquals(m.getSiArray(), mNxN.asAbsMatrix3x3().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mNxN.getDisplayUnit());
        assertEquals(m.getReference(), mNxN.getReference());
        assertEquals(90.0, mNxN.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);

        var mNxM = m.asAbsMatrixNxM();
        assertArrayEquals(m.getSiArray(), mNxM.asAbsMatrix3x3().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mNxM.getDisplayUnit());
        assertEquals(m.getReference(), mNxM.getReference());
        assertEquals(90.0, mNxM.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);

        var mQT = m.asAbsQuantityTable();
        assertArrayEquals(m.getSiArray(), mQT.asAbsMatrix3x3().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mQT.getDisplayUnit());
        assertEquals(m.getReference(), mQT.getReference());
        assertEquals(90.0, mQT.get(0, 1).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(IllegalStateException.class, () -> m.asAbsMatrix1x1());
        assertThrows(IllegalStateException.class, () -> m.asAbsMatrix2x2());
        assertThrows(IllegalStateException.class, () -> m.asAbsVector2Col());
        assertThrows(IllegalStateException.class, () -> m.asAbsVector2Row());
        assertThrows(IllegalStateException.class, () -> m.asAbsVector3Col());
        assertThrows(IllegalStateException.class, () -> m.asAbsVector3Row());
        assertThrows(IllegalStateException.class, () -> m.asAbsVectorNCol());
        assertThrows(IllegalStateException.class, () -> m.asAbsVectorNRow());
    }

}
