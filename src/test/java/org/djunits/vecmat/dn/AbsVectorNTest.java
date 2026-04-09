package org.djunits.vecmat.dn;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AbsVectorN}.
 * <p>
 * This class provides exhaustive coverage of AbsVectorN.Col and AbsVectorN.Row and all inherited functionality from AbsVector,
 * AbsVectorMatrix and Value.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsVectorNTest
{
    /**
     * Create a standard test column vector.
     * @return a test column vector
     */
    private AbsVectorN.Col<Direction, Angle> northDegCol()
    {
        return AbsVectorN.Col.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    /**
     * Create a standard row column vector.
     * @return a test row vector
     */
    private AbsVectorN.Row<Direction, Angle> northDegRow()
    {
        return AbsVectorN.Row.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    // ==================================== Constructor and base methods ====================================

    /**
     * Test constructor and base methods such as rows(), cols(), size() for column vector.
     */
    @Test
    public void testCtorBaseCol()
    {
        var rvCol = VectorN.Col.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg);
        var avCol = new AbsVectorN.Col<>(rvCol, Direction.Reference.NORTH);
        assertEquals(Angle.Unit.deg, avCol.getDisplayUnit());
        assertEquals(Direction.Reference.NORTH, avCol.getReference());

        Direction d180 = new Direction(180.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d270 = new Direction(270.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertEquals(d180, avCol.get(0));
        assertEquals(d180, avCol.mget(1));
        assertEquals(d270, avCol.get(1));
        assertEquals(d270, avCol.mget(2));

        assertEquals(d180.si(), avCol.si(0));
        assertEquals(d180.si(), avCol.msi(1));
        assertEquals(d270.si(), avCol.si(1));
        assertEquals(d270.si(), avCol.msi(2));

        assertEquals(3, avCol.rows());
        assertEquals(1, avCol.cols());
        assertEquals(3, avCol.size());
        assertTrue(avCol.isAbsolute());
        assertFalse(avCol.isRelative());

        avCol.setDisplayUnit("rad");
        assertEquals(Angle.Unit.rad, avCol.getDisplayUnit());
        avCol.setDisplayUnit("deg");
        assertEquals(Angle.Unit.deg, avCol.getDisplayUnit());
    }

    /**
     * Test constructor and base methods such as rows(), cols(), size() for row vector.
     */
    @Test
    public void testCtorBaseRow()
    {
        var rvRow = VectorN.Row.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg);
        var avRow = AbsVectorN.Row.of(rvRow, Direction.Reference.NORTH);
        assertEquals(Angle.Unit.deg, avRow.getDisplayUnit());
        assertEquals(Direction.Reference.NORTH, avRow.getReference());

        Direction d180 = new Direction(180.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d270 = new Direction(270.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d90 = new Direction(90.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertEquals(d180, avRow.get(0));
        assertEquals(d180, avRow.mget(1));
        assertEquals(d270, avRow.get(1));
        assertEquals(d270, avRow.mget(2));
        assertEquals(d90, avRow.get(2));
        assertEquals(d90, avRow.mget(3));

        assertEquals(d180.si(), avRow.si(0));
        assertEquals(d180.si(), avRow.msi(1));
        assertEquals(d270.si(), avRow.si(1));
        assertEquals(d270.si(), avRow.msi(2));
        assertEquals(d90.si(), avRow.si(2));
        assertEquals(d90.si(), avRow.msi(3));

        assertEquals(1, avRow.rows());
        assertEquals(3, avRow.cols());
        assertEquals(3, avRow.size());
        assertTrue(avRow.isAbsolute());
        assertFalse(avRow.isRelative());

        avRow.setDisplayUnit("rad");
        assertEquals(Angle.Unit.rad, avRow.getDisplayUnit());
        avRow.setDisplayUnit("deg");
        assertEquals(Angle.Unit.deg, avRow.getDisplayUnit());
    }

    /**
     * Test exceptions for row and column constructor.
     */
    @Test
    public void testCtorBaseExceptions()
    {
        var rvCol = VectorN.Col.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsVectorN.Col<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsVectorN.Col<>(rvCol, null));

        var rvRow = VectorN.Row.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsVectorN.Row<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsVectorN.Row<>(rvRow, null));
    }

    /**
     * Test instantiate() and instantiateSi() for column matrices.
     */
    @Test
    public void testInstantiateCol()
    {
        var vCol = northDegCol();
        var v2 = vCol.instantiateSi(new double[] {0.5 * Math.PI, Math.PI, 1.5 * Math.PI}, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, v2.si(0));
        assertEquals(Angle.Unit.deg, v2.getDisplayUnit()); // same as original northDeg column vector
        assertEquals(Direction.Reference.EAST, v2.getReference());

        var rv = VectorN.Col.of(new double[] {0.5 * Math.PI, Math.PI, 1.5 * Math.PI}, Angle.Unit.rad);
        var v3 = vCol.instantiate(rv, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, v3.si(0));
        assertEquals(Angle.Unit.deg, v3.getDisplayUnit()); // same as original northDeg column vector
        assertEquals(Direction.Reference.EAST, v3.getReference());

        assertThrows(NullPointerException.class, () -> vCol.instantiateSi(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> vCol.instantiateSi(v2.getSiArray(), null));
        assertThrows(IllegalArgumentException.class, () -> vCol.instantiateSi(new double[] {}, Direction.Reference.EAST));
        assertThrows(IllegalArgumentException.class,
                () -> vCol.instantiateSi(new double[] {1, 2, 3, 4, 5}, Direction.Reference.EAST));

        assertThrows(NullPointerException.class, () -> vCol.instantiate(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> vCol.instantiate(rv, null));
    }

    /**
     * Test instantiate() and instantiateSi() for row matrices.
     */
    @Test
    public void testInstantiateRow()
    {
        var vRow = northDegRow();
        var v2 = vRow.instantiateSi(new double[] {0.5 * Math.PI, Math.PI, 1.5 * Math.PI}, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, v2.si(0));
        assertEquals(Angle.Unit.deg, v2.getDisplayUnit()); // same as original northDeg column vector
        assertEquals(Direction.Reference.EAST, v2.getReference());

        var rv = VectorN.Row.of(new double[] {0.5 * Math.PI, Math.PI, 1.5 * Math.PI}, Angle.Unit.rad);
        var v3 = vRow.instantiate(rv, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, v3.si(0));
        assertEquals(Angle.Unit.deg, v3.getDisplayUnit()); // same as original northDeg column vector
        assertEquals(Direction.Reference.EAST, v3.getReference());

        assertThrows(NullPointerException.class, () -> vRow.instantiateSi(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> vRow.instantiateSi(v2.getSiArray(), null));
        assertThrows(IllegalArgumentException.class, () -> vRow.instantiateSi(new double[] {}, Direction.Reference.EAST));
        assertThrows(IllegalArgumentException.class,
                () -> vRow.instantiateSi(new double[] {1, 2, 3, 4, 5}, Direction.Reference.EAST));

        assertThrows(NullPointerException.class, () -> vRow.instantiate(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> vRow.instantiate(rv, null));
    }

    // ==================================== Static factory methods ====================================

    /**
     * Test all of() and ofSi() static factories for column matrices.
     */
    @Test
    public void testStaticFactoriesCol()
    {
        Angle[] qa = {new Angle(180, Angle.Unit.deg), new Angle(270, Angle.Unit.deg), new Angle(90, Angle.Unit.deg)};
        var adeg = new double[] {180.0, 270.0, 90.0};
        var arad = new double[] {Math.PI, 1.5 * Math.PI, 0.5 * Math.PI};
        var rv = VectorN.Col.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg);
        var av = new AbsVectorN.Col<>(rv, Direction.Reference.NORTH);

        // of(double[], unit, ref)
        AbsVectorN.Col<Direction, Angle> vdu = AbsVectorN.Col.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVectorN.Col.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVectorN.Col.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsVectorN.Col<Direction, Angle> vsi = AbsVectorN.Col.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVectorN.Col.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVectorN.Col.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsVectorN.Col<Direction, Angle> vqa = AbsVectorN.Col.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqa.getDisplayUnit());
        AbsVectorN.Col<Direction, Angle> vqa2 =
                AbsVectorN.Col.of(new Angle[] {Angle.of(Math.PI, "rad"), qa[1], qa[2]}, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsVectorN.Col.of(new Angle[] {}, Direction.Reference.NORTH));

        // of(List<Q>, ref)
        List<Angle> alist = List.of(qa[0], qa[1], qa[2]);
        AbsVectorN.Col<Direction, Angle> vqalist = AbsVectorN.Col.of(alist, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqalist.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqalist.getDisplayUnit());
        AbsVectorN.Col<Direction, Angle> vqalist2 =
                AbsVectorN.Col.of(List.of(Angle.of(Math.PI, "rad"), qa[1], qa[2]), Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqalist2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqalist2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.of((List<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.of(alist, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVectorN.Col.of(new ArrayList<Angle>(), Direction.Reference.NORTH));

        // of(Vector, ref)
        VectorN.Col<Angle> rel = VectorN.Col.of(new double[] {1, 2, 3}, Angle.Unit.deg);
        AbsVectorN.Col<Direction, Angle> avrel = AbsVectorN.Col.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, avrel.si(0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, avrel.si(1), 1E-10);
        assertEquals(3.0 * Math.PI / 180.0, avrel.si(2), 1E-10);
        assertEquals(Angle.Unit.deg, avrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.of((VectorN.Col<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Col.of(rel, null));
    }

    /**
     * Test all of() and ofSi() static factories for row matrices.
     */
    @Test
    public void testStaticFactoriesRow()
    {
        Angle[] qa = {new Angle(180, Angle.Unit.deg), new Angle(270, Angle.Unit.deg), new Angle(90, Angle.Unit.deg)};
        var adeg = new double[] {180.0, 270.0, 90.0};
        var arad = new double[] {Math.PI, 1.5 * Math.PI, 0.5 * Math.PI};
        var rv = VectorN.Row.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg);
        var av = new AbsVectorN.Row<>(rv, Direction.Reference.NORTH);

        // of(double[], unit, ref)
        AbsVectorN.Row<Direction, Angle> vdu = AbsVectorN.Row.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVectorN.Row.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVectorN.Row.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsVectorN.Row<Direction, Angle> vsi = AbsVectorN.Row.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVectorN.Row.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVectorN.Row.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsVectorN.Row<Direction, Angle> vqa = AbsVectorN.Row.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqa.getDisplayUnit());
        AbsVectorN.Row<Direction, Angle> vqa2 =
                AbsVectorN.Row.of(new Angle[] {Angle.of(Math.PI, "rad"), qa[1], qa[2]}, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsVectorN.Row.of(new Angle[] {}, Direction.Reference.NORTH));

        // of(List<Q>, ref)
        List<Angle> alist = List.of(qa[0], qa[1], qa[2]);
        AbsVectorN.Row<Direction, Angle> vqalist = AbsVectorN.Row.of(alist, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqalist.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqalist.getDisplayUnit());
        AbsVectorN.Row<Direction, Angle> vqalist2 =
                AbsVectorN.Row.of(List.of(Angle.of(Math.PI, "rad"), qa[1], qa[2]), Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqalist2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqalist2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.of((List<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.of(alist, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVectorN.Row.of(new ArrayList<Angle>(), Direction.Reference.NORTH));

        // of(Vector, ref)
        VectorN.Row<Angle> rel = VectorN.Row.of(new double[] {1, 2, 3}, Angle.Unit.deg);
        AbsVectorN.Row<Direction, Angle> avrel = AbsVectorN.Row.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, avrel.si(0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, avrel.si(1), 1E-10);
        assertEquals(3.0 * Math.PI / 180.0, avrel.si(2), 1E-10);
        assertEquals(Angle.Unit.deg, avrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.of((VectorN.Row<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVectorN.Row.of(rel, null));
    }

    // ==================================== Scalar & SI array access ====================================

    /**
     * Test scalar and SI array extraction for column vectors.
     */
    @Test
    public void testScalarAndSiArrayCol()
    {
        AbsVectorN.Col<Direction, Angle> v = northDegCol();
        Direction[] a = v.getScalarArray();
        double[] asi = v.getSiArray();
        assertEquals(3, a.length);
        assertEquals(3, asi.length);
        assertEquals(270.0, a[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(Math.PI, asi[0], 1e-12);
    }

    /**
     * Test scalar and SI array extraction for row vectors.
     */
    @Test
    public void testScalarAndSiArrayRow()
    {
        AbsVectorN.Row<Direction, Angle> v = northDegRow();
        Direction[] a = v.getScalarArray();
        double[] asi = v.getSiArray();
        assertEquals(3, a.length);
        assertEquals(3, asi.length);
        assertEquals(270.0, a[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(Math.PI, asi[0], 1e-12);
    }

    // ==================================== min, max, median ====================================

    /**
     * Test min(), max() and median() for column vectors.
     */
    @Test
    public void testMinMaxMedianCol()
    {
        AbsVectorN.Col<Direction, Angle> v = northDegCol();
        assertEquals(0.5 * Math.PI, v.min().si(), 1E-10);
        assertEquals(1.5 * Math.PI, v.max().si(), 1E-10);
        assertEquals(180.0, v.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, v.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.median().getDisplayUnit());

        AbsVectorN.Col<Direction, Angle> e =
                AbsVectorN.Col.ofSi(new double[] {1, 1, 1}, Angle.Unit.rad, Direction.Reference.NORTH);
        assertEquals(1.0, e.min().si(), 1E-10);
        assertEquals(1.0, e.max().si(), 1E-10);
        assertEquals(1.0, e.median().si(), 1E-10);
    }

    /**
     * Test min(), max() and median() for row vectors.
     */
    @Test
    public void testMinMaxMedianRow()
    {
        AbsVectorN.Row<Direction, Angle> v = northDegRow();
        assertEquals(0.5 * Math.PI, v.min().si(), 1E-10);
        assertEquals(1.5 * Math.PI, v.max().si(), 1E-10);
        assertEquals(180.0, v.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, v.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.median().getDisplayUnit());

        AbsVectorN.Row<Direction, Angle> e =
                AbsVectorN.Row.ofSi(new double[] {1, 1, 1}, Angle.Unit.rad, Direction.Reference.NORTH);
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
        AbsVectorN.Col<Direction, Angle> v = northDegCol();
        var vt = v.transpose();
        var vt2 = vt.transpose();
        assertEquals(v, vt2);

        assertTrue(v.isColumnVector());
        assertTrue(vt.isRowVector());
        assertTrue(vt2.isColumnVector());

        assertEquals(Math.PI, vt.si(0), 1E-10);
        assertEquals(1.5 * Math.PI, vt.si(1), 1E-10);
        assertEquals(0.5 * Math.PI, vt.si(2), 1E-10);

        assertEquals(Math.PI, vt2.si(0), 1E-10);
        assertEquals(1.5 * Math.PI, vt2.si(1), 1E-10);
        assertEquals(0.5 * Math.PI, vt2.si(2), 1E-10);

        assertEquals(Angle.Unit.deg, vt.getDisplayUnit());
        assertEquals(Angle.Unit.deg, vt2.getDisplayUnit());
    }

    // ==================================== add, subtract ====================================

    /**
     * Test add() and subtract() methods for column vectors.
     */
    @Test
    public void testAddSubtractCol()
    {
        AbsVectorN.Col<Direction, Angle> v = northDegCol();
        AbsVectorN.Col<Direction, Angle> vadd = northDegCol().add(Angle.of(5.0, "deg"));
        AbsVectorN.Col<Direction, Angle> vsub = northDegCol().subtract(Angle.of(5.0, "deg"));
        VectorN.Col<Angle> v0 = v.subtract(v);

        assertArrayEquals(new double[] {0, 0, 0}, v0.getSiArray());
        assertEquals(185.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(275.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(95.0, vadd.get(2).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(265.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(85.0, vsub.get(2).getInUnit(Angle.Unit.deg), 1E-10);

        VectorN.Col<Angle> v12 = VectorN.Col.of(new double[] {1, 2, 3}, Angle.Unit.deg);
        vadd = v.add(v12);
        vsub = v.subtract(v12);
        assertEquals(181.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(272.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(93.0, vadd.get(2).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(179.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(268.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(87.0, vsub.get(2).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> v.add((Angle) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> v.add((VectorN.Col<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((VectorN.Col<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((AbsVectorN.Col<Direction, Angle>) null));
    }

    /**
     * Test add() and subtract() methods for row vectors.
     */
    @Test
    public void testAddSubtractRow()
    {
        AbsVectorN.Row<Direction, Angle> v = northDegRow();
        AbsVectorN.Row<Direction, Angle> vadd = northDegRow().add(Angle.of(5.0, "deg"));
        AbsVectorN.Row<Direction, Angle> vsub = northDegRow().subtract(Angle.of(5.0, "deg"));
        VectorN.Row<Angle> v0 = v.subtract(v);

        assertArrayEquals(new double[] {0, 0, 0}, v0.getSiArray());
        assertEquals(185.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(275.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(95.0, vadd.get(2).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(265.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(85.0, vsub.get(2).getInUnit(Angle.Unit.deg), 1E-10);

        VectorN.Row<Angle> v12 = VectorN.Row.of(new double[] {1, 2, 3}, Angle.Unit.deg);
        vadd = v.add(v12);
        vsub = v.subtract(v12);
        assertEquals(181.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(272.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(93.0, vadd.get(2).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(179.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(268.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(87.0, vsub.get(2).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> v.add((Angle) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> v.add((VectorN.Row<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((VectorN.Row<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((AbsVectorN.Row<Direction, Angle>) null));
    }

    // ==================================== iterator ====================================

    /**
     * Test iterator for column vectors.
     */
    @Test
    public void testIteratorCol()
    {
        AbsVectorN.Col<Direction, Angle> v = northDegCol();
        int i = 0;
        for (Direction d : v)
        {
            assertEquals(v.get(i++), d);
            assertEquals(Angle.Unit.deg, d.getDisplayUnit());
        }

        Iterator<Direction> vit = v.iterator();
        while (vit.hasNext())
        {
            vit.next();
        }
        assertThrows(NoSuchElementException.class, () -> vit.next());
    }

    /**
     * Test iterator for row vectors.
     */
    @Test
    public void testIteratorRow()
    {
        AbsVectorN.Row<Direction, Angle> v = northDegRow();
        int i = 0;
        for (Direction d : v)
        {
            assertEquals(v.get(i++), d);
            assertEquals(Angle.Unit.deg, d.getDisplayUnit());
        }

        Iterator<Direction> vit = v.iterator();
        while (vit.hasNext())
        {
            vit.next();
        }
        assertThrows(NoSuchElementException.class, () -> vit.next());
    }

    // ==================================== toString ====================================

    /**
     * Test toString() for column vectors.
     */
    @Test
    public void testToStringCol()
    {
        AbsVectorN.Col<Direction, Angle> v = northDegCol();
        String sdeg = v.toString();
        String srad = v.toString(Angle.Unit.rad);
        assertTrue(sdeg.contains(Angle.Unit.deg.getDisplayAbbreviation()));
        assertTrue(srad.contains(Angle.Unit.rad.getDisplayAbbreviation()));
        assertTrue(sdeg.contains("["));
        assertTrue(srad.contains("]"));
        assertTrue(sdeg.contains("Col"));
        assertTrue(srad.contains("Col"));
    }

    /**
     * Test toString() for row vectors.
     */
    @Test
    public void testToStringRow()
    {
        AbsVectorN.Row<Direction, Angle> v = northDegRow();
        String sdeg = v.toString();
        String srad = v.toString(Angle.Unit.rad);
        assertTrue(sdeg.contains(Angle.Unit.deg.getDisplayAbbreviation()));
        assertTrue(srad.contains(Angle.Unit.rad.getDisplayAbbreviation()));
        assertTrue(sdeg.contains("["));
        assertTrue(srad.contains("]"));
        assertTrue(sdeg.contains("Row"));
        assertTrue(srad.contains("Row"));
    }

    // ==================================== equals, hashCode ====================================

    /**
     * Test equals(), hashCode() for column vectors.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsHashCodeCol()
    {
        AbsVectorN.Col<Direction, Angle> vdn =
                AbsVectorN.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVectorN.Col<Direction, Angle> vrn =
                AbsVectorN.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsVectorN.Col<Direction, Angle> vde =
                AbsVectorN.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.EAST);
        AbsVectorN.Row<Direction, Angle> vdRow =
                AbsVectorN.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(vdn.equals(vdn));
        assertTrue(vdn.equals(vrn));
        assertFalse(vdn.equals(vde));
        assertFalse(vdn.equals(vdRow));
        assertFalse(vdn.equals(null));
        assertFalse(vdn.equals(new String("abc")));
        assertFalse(vdn.equals(northDegCol()));
        assertFalse(vdn.equals(northDegRow()));

        assertEquals(vdn.hashCode(),
                AbsVectorN.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertNotEquals(vdn.hashCode(),
                AbsVectorN.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertEquals(vdn.hashCode(), vrn.hashCode());
        assertNotEquals(vdn.hashCode(), vde.hashCode());
        assertNotEquals(vdn.hashCode(), northDegCol().hashCode());
    }

    /**
     * Test equals(), hashCode() for row vectors.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsHashCodeRow()
    {
        AbsVectorN.Row<Direction, Angle> vdn =
                AbsVectorN.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVectorN.Row<Direction, Angle> vrn =
                AbsVectorN.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsVectorN.Row<Direction, Angle> vde =
                AbsVectorN.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.EAST);
        AbsVectorN.Col<Direction, Angle> vdCol =
                AbsVectorN.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(vdn.equals(vdn));
        assertTrue(vdn.equals(vrn));
        assertFalse(vdn.equals(vde));
        assertFalse(vdn.equals(vdCol));
        assertFalse(vdn.equals(null));
        assertFalse(vdn.equals(new String("abc")));
        assertFalse(vdn.equals(northDegCol()));
        assertFalse(vdn.equals(northDegRow()));

        assertEquals(vdn.hashCode(),
                AbsVectorN.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertNotEquals(vdn.hashCode(),
                AbsVectorN.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertEquals(vdn.hashCode(), vrn.hashCode());
        assertNotEquals(vdn.hashCode(), vde.hashCode());
        assertNotEquals(vdn.hashCode(), northDegRow().hashCode());
    }

}
