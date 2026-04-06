package org.djunits.vecmat.d2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AbsVector2}.
 * <p>
 * This class provides exhaustive coverage of AbsVector2.Col and AbsVector2.Row and all inherited functionality from AbsVector,
 * AbsVectorMatrix and Value.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsVector2Test
{
    /**
     * Create a standard test column vector.
     * @return a test column vector
     */
    private AbsVector2.Col<Direction, Angle> northDegCol()
    {
        return AbsVector2.Col.of(new double[] {180.0, 270.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    /**
     * Create a standard row column vector.
     * @return a test row vector
     */
    private AbsVector2.Row<Direction, Angle> northDegRow()
    {
        return AbsVector2.Row.of(new double[] {180.0, 270.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    // ==================================== Constructor and base methods ====================================

    /**
     * Test constructor and base methods such as rows(), cols(), size() for column vector.
     */
    @Test
    public void testCtorBaseCol()
    {
        var rvCol = new Vector2.Col<Angle>(180.0, 270.0, Angle.Unit.deg);
        var avCol = new AbsVector2.Col<>(rvCol, Direction.Reference.NORTH);
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

        assertEquals(2, avCol.rows());
        assertEquals(1, avCol.cols());
        assertEquals(2, avCol.size());
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
        var rvRow = new Vector2.Row<Angle>(180.0, 270.0, Angle.Unit.deg);
        var avRow = new AbsVector2.Row<>(rvRow, Direction.Reference.NORTH);
        assertEquals(Angle.Unit.deg, avRow.getDisplayUnit());
        assertEquals(Direction.Reference.NORTH, avRow.getReference());

        Direction d180 = new Direction(180.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction d270 = new Direction(270.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertEquals(d180, avRow.get(0));
        assertEquals(d180, avRow.mget(1));
        assertEquals(d270, avRow.get(1));
        assertEquals(d270, avRow.mget(2));

        assertEquals(d180.si(), avRow.si(0));
        assertEquals(d180.si(), avRow.msi(1));
        assertEquals(d270.si(), avRow.si(1));
        assertEquals(d270.si(), avRow.msi(2));

        assertEquals(1, avRow.rows());
        assertEquals(2, avRow.cols());
        assertEquals(2, avRow.size());
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
        var rvCol = new Vector2.Col<Angle>(180.0, 270.0, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsVector2.Col<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsVector2.Col<>(rvCol, null));

        var rvRow = new Vector2.Row<Angle>(180.0, 270.0, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsVector2.Row<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsVector2.Row<>(rvRow, null));
    }

    /**
     * Test instantiate() and instantiateSi() for column matrices.
     */
    @Test
    public void testInstantiateCol()
    {
        var vCol = northDegCol();
        var v2 = vCol.instantiateSi(new double[] {0.5 * Math.PI, Math.PI}, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, v2.si(0));
        assertEquals(Angle.Unit.deg, v2.getDisplayUnit()); // same as original northDeg column vector
        assertEquals(Direction.Reference.EAST, v2.getReference());

        var rv = Vector2.Col.of(0.5 * Math.PI, Math.PI, Angle.Unit.rad);
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
        var v2 = vRow.instantiateSi(new double[] {0.5 * Math.PI, Math.PI}, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, v2.si(0));
        assertEquals(Angle.Unit.deg, v2.getDisplayUnit()); // same as original northDeg column vector
        assertEquals(Direction.Reference.EAST, v2.getReference());

        var rv = Vector2.Row.of(0.5 * Math.PI, Math.PI, Angle.Unit.rad);
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
        Angle[] qa = {new Angle(180, Angle.Unit.deg), new Angle(270, Angle.Unit.deg)};
        var adeg = new double[] {180.0, 270.0};
        var adeg5 = new double[] {0.0, 90.0, 180.0, 270.0, 45.0};
        var arad = new double[] {Math.PI, 1.5 * Math.PI};
        var rv = new Vector2.Col<Angle>(180.0, 270.0, Angle.Unit.deg);
        var av = new AbsVector2.Col<>(rv, Direction.Reference.NORTH);

        // of(double, double, unit, ref)
        AbsVector2.Col<Direction, Angle> vddu = AbsVector2.Col.of(180.0, 270.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vddu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vddu.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(180.0, 270.0, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(180.0, 270.0, Angle.Unit.deg, null));

        // of(Q, Q, ref)
        AbsVector2.Col<Direction, Angle> vqq = AbsVector2.Col.of(qa[0], qa[1], Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqq.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqq.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(qa[0], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(null, qa[1], Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(qa[0], qa[1], null));

        // ofSi(double, double, unit, ref)
        AbsVector2.Col<Direction, Angle> vsiu =
                AbsVector2.Col.ofSi(arad[0], arad[1], Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsiu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsiu.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.ofSi(arad[0], arad[1], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.ofSi(arad[0], arad[1], Angle.Unit.deg, null));

        // of(double[], unit, ref)
        AbsVector2.Col<Direction, Angle> vdu = AbsVector2.Col.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector2.Col.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector2.Col.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsVector2.Col.of(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsVector2.Col<Direction, Angle> vsi = AbsVector2.Col.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector2.Col.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector2.Col.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector2.Col.ofSi(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsVector2.Col<Direction, Angle> vqa = AbsVector2.Col.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqa.getDisplayUnit());
        AbsVector2.Col<Direction, Angle> vqa2 =
                AbsVector2.Col.of(new Angle[] {Angle.of(Math.PI, "rad"), qa[1]}, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsVector2.Col.of(new Angle[] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector2.Col.of(new Angle[] {qa[0], qa[1], qa[0], qa[1], qa[0]}, Direction.Reference.NORTH));

        // of(Vector, ref)
        Vector2.Col<Angle> rel = Vector2.Col.of(new double[] {1, 2}, Angle.Unit.deg);
        AbsVector2.Col<Direction, Angle> avrel = AbsVector2.Col.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, avrel.si(0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, avrel.si(1), 1E-10);
        assertEquals(Angle.Unit.deg, avrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of((Vector2.Col<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Col.of(rel, null));
    }

    /**
     * Test all of() and ofSi() static factories for row matrices.
     */
    @Test
    public void testStaticFactoriesRow()
    {
        Angle[] qa = {new Angle(180, Angle.Unit.deg), new Angle(270, Angle.Unit.deg)};
        var adeg = new double[] {180.0, 270.0};
        var adeg5 = new double[] {0.0, 90.0, 180.0, 270.0, 45.0};
        var arad = new double[] {Math.PI, 1.5 * Math.PI};
        var rv = new Vector2.Row<Angle>(180.0, 270.0, Angle.Unit.deg);
        var av = new AbsVector2.Row<>(rv, Direction.Reference.NORTH);

        // of(double, double, unit, ref)
        AbsVector2.Row<Direction, Angle> vddu = AbsVector2.Row.of(180.0, 270.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vddu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vddu.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(180.0, 270.0, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(180.0, 270.0, Angle.Unit.deg, null));

        // of(Q, Q, ref)
        AbsVector2.Row<Direction, Angle> vqq = AbsVector2.Row.of(qa[0], qa[1], Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqq.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqq.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(qa[0], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(null, qa[1], Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(qa[0], qa[1], null));

        // ofSi(double, double, unit, ref)
        AbsVector2.Row<Direction, Angle> vsiu =
                AbsVector2.Row.ofSi(arad[0], arad[1], Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsiu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsiu.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.ofSi(arad[0], arad[1], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.ofSi(arad[0], arad[1], Angle.Unit.deg, null));

        // of(double[], unit, ref)
        AbsVector2.Row<Direction, Angle> vdu = AbsVector2.Row.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector2.Row.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector2.Row.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsVector2.Row.of(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsVector2.Row<Direction, Angle> vsi = AbsVector2.Row.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector2.Row.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector2.Row.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector2.Row.ofSi(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsVector2.Row<Direction, Angle> vqa = AbsVector2.Row.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqa.getDisplayUnit());
        AbsVector2.Row<Direction, Angle> vqa2 =
                AbsVector2.Row.of(new Angle[] {Angle.of(Math.PI, "rad"), qa[1]}, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsVector2.Row.of(new Angle[] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector2.Row.of(new Angle[] {qa[0], qa[1], qa[0], qa[1], qa[0]}, Direction.Reference.NORTH));

        // of(Vector, ref)
        Vector2.Row<Angle> rel = Vector2.Row.of(new double[] {1, 2}, Angle.Unit.deg);
        AbsVector2.Row<Direction, Angle> avrel = AbsVector2.Row.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, avrel.si(0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, avrel.si(1), 1E-10);
        assertEquals(Angle.Unit.deg, avrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of((Vector2.Row<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector2.Row.of(rel, null));
    }

    // ==================================== Scalar & SI array access ====================================

    /**
     * Test scalar and SI array extraction for column vectors.
     */
    @Test
    public void testScalarAndSiArrayCol()
    {
        AbsVector2.Col<Direction, Angle> v = northDegCol();
        Direction[] a = v.getScalarArray();
        double[] asi = v.getSiArray();
        assertEquals(2, a.length);
        assertEquals(2, asi.length);
        assertEquals(270.0, a[1].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(Math.PI, asi[0], 1e-12);
    }

    /**
     * Test scalar and SI array extraction for row vectors.
     */
    @Test
    public void testScalarAndSiArrayRow()
    {
        AbsVector2.Row<Direction, Angle> v = northDegRow();
        Direction[] a = v.getScalarArray();
        double[] asi = v.getSiArray();
        assertEquals(2, a.length);
        assertEquals(2, asi.length);
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
        AbsVector2.Col<Direction, Angle> v = northDegCol();
        assertEquals(Math.PI, v.min().si(), 1E-10);
        assertEquals(1.5 * Math.PI, v.max().si(), 1E-10);
        assertEquals((180.0 + 270.0) / 2.0, v.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, v.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.median().getDisplayUnit());

        AbsVector2.Col<Direction, Angle> e =
                AbsVector2.Col.ofSi(new double[] {1, 1}, Angle.Unit.rad, Direction.Reference.NORTH);
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
        AbsVector2.Row<Direction, Angle> v = northDegRow();
        assertEquals(Math.PI, v.min().si(), 1E-10);
        assertEquals(1.5 * Math.PI, v.max().si(), 1E-10);
        assertEquals((180.0 + 270.0) / 2.0, v.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, v.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.median().getDisplayUnit());

        AbsVector2.Row<Direction, Angle> e =
                AbsVector2.Row.ofSi(new double[] {1, 1}, Angle.Unit.rad, Direction.Reference.NORTH);
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
        AbsVector2.Col<Direction, Angle> v = northDegCol();
        var vt = v.transpose();
        var vt2 = vt.transpose();
        assertEquals(v, vt2);

        assertTrue(v.isColumnVector());
        assertTrue(vt.isRowVector());
        assertTrue(vt2.isColumnVector());

        assertEquals(Math.PI, vt.si(0), 1E-10);
        assertEquals(1.5 * Math.PI, vt.si(1), 1E-10);

        assertEquals(Math.PI, vt2.si(0), 1E-10);
        assertEquals(1.5 * Math.PI, vt2.si(1), 1E-10);

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
        AbsVector2.Col<Direction, Angle> v = northDegCol();
        AbsVector2.Col<Direction, Angle> vadd = northDegCol().add(Angle.of(5.0, "deg"));
        AbsVector2.Col<Direction, Angle> vsub = northDegCol().subtract(Angle.of(5.0, "deg"));
        Vector2.Col<Angle> v0 = v.subtract(v);

        assertArrayEquals(new double[] {0, 0}, v0.si());
        assertEquals(185.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(275.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(265.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);

        Vector2.Col<Angle> v12 = Vector2.Col.of(new double[] {1, 2}, Angle.Unit.deg);
        vadd = v.add(v12);
        vsub = v.subtract(v12);
        assertEquals(181.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(272.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(179.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(268.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> v.add((Angle) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> v.add((Vector2.Col<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Vector2.Col<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((AbsVector2.Col<Direction, Angle>) null));
    }

    /**
     * Test add() and subtract() methods for row vectors.
     */
    @Test
    public void testAddSubtractRow()
    {
        AbsVector2.Row<Direction, Angle> v = northDegRow();
        AbsVector2.Row<Direction, Angle> vadd = northDegRow().add(Angle.of(5.0, "deg"));
        AbsVector2.Row<Direction, Angle> vsub = northDegRow().subtract(Angle.of(5.0, "deg"));
        Vector2.Row<Angle> v0 = v.subtract(v);

        assertArrayEquals(new double[] {0, 0}, v0.si());
        assertEquals(185.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(275.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(265.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);

        Vector2.Row<Angle> v12 = Vector2.Row.of(new double[] {1, 2}, Angle.Unit.deg);
        vadd = v.add(v12);
        vsub = v.subtract(v12);
        assertEquals(181.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(272.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(179.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(268.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> v.add((Angle) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> v.add((Vector2.Row<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Vector2.Row<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((AbsVector2.Row<Direction, Angle>) null));
    }

    // ==================================== iterator ====================================

    /**
     * Test iterator for column vectors.
     */
    @Test
    public void testIteratorCol()
    {
        AbsVector2.Col<Direction, Angle> v = northDegCol();
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
        AbsVector2.Row<Direction, Angle> v = northDegRow();
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
        AbsVector2.Col<Direction, Angle> v = northDegCol();
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
        AbsVector2.Row<Direction, Angle> v = northDegRow();
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
        AbsVector2.Col<Direction, Angle> vdn =
                AbsVector2.Col.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVector2.Col<Direction, Angle> vrn =
                AbsVector2.Col.ofSi(new double[] {0.1, 0.2}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsVector2.Col<Direction, Angle> vde =
                AbsVector2.Col.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.EAST);
        AbsVector2.Row<Direction, Angle> vdRow =
                AbsVector2.Row.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(vdn.equals(vdn));
        assertTrue(vdn.equals(vrn));
        assertFalse(vdn.equals(vde));
        assertFalse(vdn.equals(vdRow));
        assertFalse(vdn.equals(null));
        assertFalse(vdn.equals(new String("abc")));
        assertFalse(vdn.equals(northDegCol()));
        assertFalse(vdn.equals(northDegRow()));

        assertEquals(vdn.hashCode(),
                AbsVector2.Col.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertNotEquals(vdn.hashCode(),
                AbsVector2.Row.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
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
        AbsVector2.Row<Direction, Angle> vdn =
                AbsVector2.Row.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVector2.Row<Direction, Angle> vrn =
                AbsVector2.Row.ofSi(new double[] {0.1, 0.2}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsVector2.Row<Direction, Angle> vde =
                AbsVector2.Row.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.EAST);
        AbsVector2.Col<Direction, Angle> vdCol =
                AbsVector2.Col.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(vdn.equals(vdn));
        assertTrue(vdn.equals(vrn));
        assertFalse(vdn.equals(vde));
        assertFalse(vdn.equals(vdCol));
        assertFalse(vdn.equals(null));
        assertFalse(vdn.equals(new String("abc")));
        assertFalse(vdn.equals(northDegCol()));
        assertFalse(vdn.equals(northDegRow()));

        assertEquals(vdn.hashCode(),
                AbsVector2.Row.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertNotEquals(vdn.hashCode(),
                AbsVector2.Col.ofSi(new double[] {0.1, 0.2}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertEquals(vdn.hashCode(), vrn.hashCode());
        assertNotEquals(vdn.hashCode(), vde.hashCode());
        assertNotEquals(vdn.hashCode(), northDegRow().hashCode());
    }

}
