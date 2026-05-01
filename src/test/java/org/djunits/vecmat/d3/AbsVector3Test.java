package org.djunits.vecmat.d3;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AbsVector3}.
 * <p>
 * This class provides exhaustive coverage of AbsVector3.Col and AbsVector3.Row and all inherited functionality from AbsVector,
 * AbsVectorMatrix and Value.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsVector3Test
{
    /**
     * Create a standard test column vector.
     * @return a test column vector
     */
    private AbsVector3.Col<Direction, Angle> northDegCol()
    {
        return AbsVector3.Col.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    /**
     * Create a standard row column vector.
     * @return a test row vector
     */
    private AbsVector3.Row<Direction, Angle> northDegRow()
    {
        return AbsVector3.Row.of(new double[] {180.0, 270.0, 90.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    // ==================================== Constructor and base methods ====================================

    /**
     * Test constructor and base methods such as rows(), cols(), size() for column vector.
     */
    @Test
    public void testCtorBaseCol()
    {
        var rvCol = Vector3.Col.of(180.0, 270.0, 90.0, Angle.Unit.deg);
        var avCol = new AbsVector3.Col<>(rvCol, Direction.Reference.NORTH);
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
        var rvRow = Vector3.Row.of(180.0, 270.0, 90.0, Angle.Unit.deg);
        var avRow = new AbsVector3.Row<>(rvRow, Direction.Reference.NORTH);
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
        var rvCol = Vector3.Col.of(180.0, 270.0, 90.0, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsVector3.Col<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsVector3.Col<>(rvCol, null));

        var rvRow = Vector3.Row.of(180.0, 270.0, 90.0, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsVector3.Row<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsVector3.Row<>(rvRow, null));
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

        var rv = Vector3.Col.of(0.5 * Math.PI, Math.PI, 1.5 * Math.PI, Angle.Unit.rad);
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

        var rv = Vector3.Row.of(0.5 * Math.PI, Math.PI, 1.5 * Math.PI, Angle.Unit.rad);
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
        var adeg5 = new double[] {0.0, 90.0, 180.0, 270.0, 45.0};
        var arad = new double[] {Math.PI, 1.5 * Math.PI, 0.5 * Math.PI};
        var rv = Vector3.Col.of(180.0, 270.0, 90.0, Angle.Unit.deg);
        var av = new AbsVector3.Col<>(rv, Direction.Reference.NORTH);

        // of(double, double, double, unit, ref)
        AbsVector3.Col<Direction, Angle> vddu =
                AbsVector3.Col.of(180.0, 270.0, 90.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vddu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vddu.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(180.0, 270.0, 90.0, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(180.0, 270.0, 90.0, Angle.Unit.deg, null));

        // of(Q, Q, Q, ref)
        AbsVector3.Col<Direction, Angle> vqq = AbsVector3.Col.of(qa[0], qa[1], qa[2], Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqq.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqq.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(qa[0], qa[2], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(null, qa[1], qa[2], Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(qa[0], qa[1], qa[2], null));

        // ofSi(double, double, double, unit, ref)
        AbsVector3.Col<Direction, Angle> vsiu =
                AbsVector3.Col.ofSi(arad[0], arad[1], arad[2], Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsiu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsiu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector3.Col.ofSi(arad[0], arad[1], arad[2], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.ofSi(arad[0], arad[1], arad[2], Angle.Unit.deg, null));

        // of(double[], unit, ref)
        AbsVector3.Col<Direction, Angle> vdu = AbsVector3.Col.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector3.Col.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector3.Col.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Col.of(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsVector3.Col<Direction, Angle> vsi = AbsVector3.Col.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector3.Col.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector3.Col.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector3.Col.ofSi(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsVector3.Col<Direction, Angle> vqa = AbsVector3.Col.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqa.getDisplayUnit());
        AbsVector3.Col<Direction, Angle> vqa2 =
                AbsVector3.Col.of(new Angle[] {Angle.of(Math.PI, "rad"), qa[1], qa[2]}, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Col.of(new Angle[] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector3.Col.of(new Angle[] {qa[0], qa[1], qa[0], qa[1], qa[0]}, Direction.Reference.NORTH));

        // of(Vector, ref)
        Vector3.Col<Angle> rel = Vector3.Col.of(new double[] {1, 2, 3}, Angle.Unit.deg);
        AbsVector3.Col<Direction, Angle> avrel = AbsVector3.Col.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, avrel.si(0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, avrel.si(1), 1E-10);
        assertEquals(3.0 * Math.PI / 180.0, avrel.si(2), 1E-10);
        assertEquals(Angle.Unit.deg, avrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of((Vector3.Col<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(rel, null));
    }

    /**
     * Test all of() and ofSi() static factories for row matrices.
     */
    @Test
    public void testStaticFactoriesRow()
    {
        Angle[] qa = {new Angle(180, Angle.Unit.deg), new Angle(270, Angle.Unit.deg), new Angle(90, Angle.Unit.deg)};
        var adeg = new double[] {180.0, 270.0, 90.0};
        var adeg5 = new double[] {0.0, 90.0, 180.0, 270.0, 45.0};
        var arad = new double[] {Math.PI, 1.5 * Math.PI, 0.5 * Math.PI};
        var rv = Vector3.Row.of(180.0, 270.0, 90.0, Angle.Unit.deg);
        var av = new AbsVector3.Row<>(rv, Direction.Reference.NORTH);

        // of(double, double, double, unit, ref)
        AbsVector3.Row<Direction, Angle> vddu =
                AbsVector3.Row.of(180.0, 270.0, 90.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vddu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vddu.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(180.0, 270.0, 90.0, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(180.0, 270.0, 90.0, Angle.Unit.deg, null));

        // of(Q, Q, Q, ref)
        AbsVector3.Row<Direction, Angle> vqq = AbsVector3.Row.of(qa[0], qa[1], qa[2], Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqq.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqq.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(qa[0], qa[1], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(null, qa[1], qa[2], Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(qa[0], qa[1], qa[2], null));

        // ofSi(double, double, double, unit, ref)
        AbsVector3.Row<Direction, Angle> vsiu =
                AbsVector3.Row.ofSi(arad[0], arad[1], arad[2], Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsiu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsiu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector3.Row.ofSi(arad[0], arad[1], arad[2], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.ofSi(arad[0], arad[1], arad[2], Angle.Unit.deg, null));

        // of(double[], unit, ref)
        AbsVector3.Row<Direction, Angle> vdu = AbsVector3.Row.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector3.Row.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector3.Row.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Row.of(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsVector3.Row<Direction, Angle> vsi = AbsVector3.Row.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector3.Row.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector3.Row.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector3.Row.ofSi(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsVector3.Row<Direction, Angle> vqa = AbsVector3.Row.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqa.getDisplayUnit());
        AbsVector3.Row<Direction, Angle> vqa2 =
                AbsVector3.Row.of(new Angle[] {Angle.of(Math.PI, "rad"), qa[1], qa[2]}, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Row.of(new Angle[] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector3.Row.of(new Angle[] {qa[0], qa[1], qa[0], qa[1], qa[0]}, Direction.Reference.NORTH));

        // of(Vector, ref)
        Vector3.Row<Angle> rel = Vector3.Row.of(new double[] {1, 2, 3}, Angle.Unit.deg);
        AbsVector3.Row<Direction, Angle> avrel = AbsVector3.Row.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, avrel.si(0), 1E-10);
        assertEquals(2.0 * Math.PI / 180.0, avrel.si(1), 1E-10);
        assertEquals(3.0 * Math.PI / 180.0, avrel.si(2), 1E-10);
        assertEquals(Angle.Unit.deg, avrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of((Vector3.Row<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(rel, null));
    }

    /**
     * Test of(A) and of(A[]) static factories for column matrices.
     */
    @Test
    public void testAbsStaticFactoriesCol()
    {
        final var north = Direction.Reference.NORTH;
        Angle[] qa = {new Angle(180, Angle.Unit.deg), new Angle(90, Angle.Unit.deg), new Angle(135, Angle.Unit.deg)};
        Direction[] da = {new Direction(qa[0], north), new Direction(qa[1], north), new Direction(qa[2], north)};
        var siArr = new double[] {qa[0].si(), qa[1].si(), qa[2].si()};
        var dEast = new Direction(qa[0], Direction.Reference.EAST);

        // of(A, A, A, ref)
        AbsVector3.Col<Direction, Angle> va = AbsVector3.Col.of(da[0], da[1], da[2]);
        assertArrayEquals(siArr, va.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, va.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of((Direction) null, da[1], da[2]));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(da[0], (Direction) null, da[2]));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(da[0], da[1], (Direction) null));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Col.of(da[0], dEast, da[2]));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Col.of(da[0], da[1], dEast));

        // of(A[], ref)
        AbsVector3.Col<Direction, Angle> vaa = AbsVector3.Col.of(da);
        assertArrayEquals(siArr, vaa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vaa.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of((Direction[]) null));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(new Direction[] {null, null, null}));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(new Direction[] {null, da[1], da[2]}));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(new Direction[] {da[0], null, da[2]}));
        assertThrows(NullPointerException.class, () -> AbsVector3.Col.of(new Direction[] {da[0], da[1], null}));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Col.of(new Direction[] {}));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Col.of(new Direction[] {da[0], da[1], da[0], da[1]}));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Col.of(new Direction[] {da[0], dEast, da[2]}));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Col.of(new Direction[] {da[0], da[1], dEast}));
    }

    /**
     * Test of(A) and of(A[]) static factories for row matrices.
     */
    @Test
    public void testAbsStaticFactoriesRow()
    {
        final var north = Direction.Reference.NORTH;
        Angle[] qa = {new Angle(180, Angle.Unit.deg), new Angle(90, Angle.Unit.deg), new Angle(135, Angle.Unit.deg)};
        Direction[] da = {new Direction(qa[0], north), new Direction(qa[1], north), new Direction(qa[2], north)};
        var siArr = new double[] {qa[0].si(), qa[1].si(), qa[2].si()};
        var dEast = new Direction(qa[0], Direction.Reference.EAST);

        // of(A, A, A, ref)
        AbsVector3.Row<Direction, Angle> va = AbsVector3.Row.of(da[0], da[1], da[2]);
        assertArrayEquals(siArr, va.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, va.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of((Direction) null, da[1], da[2]));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(da[0], (Direction) null, da[2]));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(da[0], da[1], (Direction) null));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Row.of(da[0], dEast, da[2]));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Row.of(da[0], da[1], dEast));

        // of(A[], ref)
        AbsVector3.Row<Direction, Angle> vaa = AbsVector3.Row.of(da);
        assertArrayEquals(siArr, vaa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vaa.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of((Direction[]) null));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(new Direction[] {null, null, null}));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(new Direction[] {null, da[1], da[2]}));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(new Direction[] {da[0], null, da[2]}));
        assertThrows(NullPointerException.class, () -> AbsVector3.Row.of(new Direction[] {da[0], da[1], null}));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Row.of(new Direction[] {}));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Row.of(new Direction[] {da[0], da[1], da[0], da[1]}));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Row.of(new Direction[] {da[0], dEast, da[2]}));
        assertThrows(IllegalArgumentException.class, () -> AbsVector3.Row.of(new Direction[] {da[0], da[1], dEast}));
    }

    // ==================================== Scalar & SI array access ====================================

    /**
     * Test scalar and SI array extraction for column vectors.
     */
    @Test
    public void testScalarAndSiArrayCol()
    {
        AbsVector3.Col<Direction, Angle> v = northDegCol();
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
        AbsVector3.Row<Direction, Angle> v = northDegRow();
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
        AbsVector3.Col<Direction, Angle> v = northDegCol();
        assertEquals(0.5 * Math.PI, v.min().si(), 1E-10);
        assertEquals(1.5 * Math.PI, v.max().si(), 1E-10);
        assertEquals(180.0, v.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, v.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.median().getDisplayUnit());

        AbsVector3.Col<Direction, Angle> e =
                AbsVector3.Col.ofSi(new double[] {1, 1, 1}, Angle.Unit.rad, Direction.Reference.NORTH);
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
        AbsVector3.Row<Direction, Angle> v = northDegRow();
        assertEquals(0.5 * Math.PI, v.min().si(), 1E-10);
        assertEquals(1.5 * Math.PI, v.max().si(), 1E-10);
        assertEquals(180.0, v.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, v.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.median().getDisplayUnit());

        AbsVector3.Row<Direction, Angle> e =
                AbsVector3.Row.ofSi(new double[] {1, 1, 1}, Angle.Unit.rad, Direction.Reference.NORTH);
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
        AbsVector3.Col<Direction, Angle> v = northDegCol();
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
        AbsVector3.Col<Direction, Angle> v = northDegCol();
        AbsVector3.Col<Direction, Angle> vadd = northDegCol().add(Angle.of(5.0, "deg"));
        AbsVector3.Col<Direction, Angle> vsub = northDegCol().subtract(Angle.of(5.0, "deg"));
        Vector3.Col<Angle> v0 = v.subtract(v);

        assertArrayEquals(new double[] {0, 0, 0}, v0.getSiArray());
        assertEquals(185.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(275.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(95.0, vadd.get(2).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(265.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(85.0, vsub.get(2).getInUnit(Angle.Unit.deg), 1E-10);

        Vector3.Col<Angle> v12 = Vector3.Col.of(new double[] {1, 2, 3}, Angle.Unit.deg);
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
        assertThrows(NullPointerException.class, () -> v.add((Vector3.Col<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Vector3.Col<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((AbsVector3.Col<Direction, Angle>) null));
    }

    /**
     * Test add() and subtract() methods for row vectors.
     */
    @Test
    public void testAddSubtractRow()
    {
        AbsVector3.Row<Direction, Angle> v = northDegRow();
        AbsVector3.Row<Direction, Angle> vadd = northDegRow().add(Angle.of(5.0, "deg"));
        AbsVector3.Row<Direction, Angle> vsub = northDegRow().subtract(Angle.of(5.0, "deg"));
        Vector3.Row<Angle> v0 = v.subtract(v);

        assertArrayEquals(new double[] {0, 0, 0}, v0.getSiArray());
        assertEquals(185.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(275.0, vadd.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(95.0, vadd.get(2).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(265.0, vsub.get(1).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(85.0, vsub.get(2).getInUnit(Angle.Unit.deg), 1E-10);

        Vector3.Row<Angle> v12 = Vector3.Row.of(new double[] {1, 2, 3}, Angle.Unit.deg);
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
        assertThrows(NullPointerException.class, () -> v.add((Vector3.Row<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Vector3.Row<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((AbsVector3.Row<Direction, Angle>) null));
    }

    // ==================================== iterator ====================================

    /**
     * Test iterator for column vectors.
     */
    @Test
    public void testIteratorCol()
    {
        AbsVector3.Col<Direction, Angle> v = northDegCol();
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
        AbsVector3.Row<Direction, Angle> v = northDegRow();
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
        AbsVector3.Col<Direction, Angle> v = northDegCol();
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
        AbsVector3.Row<Direction, Angle> v = northDegRow();
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
        AbsVector3.Col<Direction, Angle> vdn =
                AbsVector3.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVector3.Col<Direction, Angle> vrn =
                AbsVector3.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsVector3.Col<Direction, Angle> vde =
                AbsVector3.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.EAST);
        AbsVector3.Row<Direction, Angle> vdRow =
                AbsVector3.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(vdn.equals(vdn));
        assertTrue(vdn.equals(vrn));
        assertFalse(vdn.equals(vde));
        assertFalse(vdn.equals(vdRow));
        assertFalse(vdn.equals(null));
        assertFalse(vdn.equals(new String("abc")));
        assertFalse(vdn.equals(northDegCol()));
        assertFalse(vdn.equals(northDegRow()));

        assertEquals(vdn.hashCode(),
                AbsVector3.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertNotEquals(vdn.hashCode(),
                AbsVector3.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
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
        AbsVector3.Row<Direction, Angle> vdn =
                AbsVector3.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVector3.Row<Direction, Angle> vrn =
                AbsVector3.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsVector3.Row<Direction, Angle> vde =
                AbsVector3.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.EAST);
        AbsVector3.Col<Direction, Angle> vdCol =
                AbsVector3.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(vdn.equals(vdn));
        assertTrue(vdn.equals(vrn));
        assertFalse(vdn.equals(vde));
        assertFalse(vdn.equals(vdCol));
        assertFalse(vdn.equals(null));
        assertFalse(vdn.equals(new String("abc")));
        assertFalse(vdn.equals(northDegCol()));
        assertFalse(vdn.equals(northDegRow()));

        assertEquals(vdn.hashCode(),
                AbsVector3.Row.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertNotEquals(vdn.hashCode(),
                AbsVector3.Col.ofSi(new double[] {0.1, 0.2, 0.3}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertEquals(vdn.hashCode(), vrn.hashCode());
        assertNotEquals(vdn.hashCode(), vde.hashCode());
        assertNotEquals(vdn.hashCode(), northDegRow().hashCode());
    }

    /**
     * Test AbsVector3.Col as() functions.
     */
    @Test
    @DisplayName("AbsVector3.Col as() functions test")
    public void testAbsVector3ColAsFunctions()
    {
        var v = northDegCol();

        var v2 = v.asAbsVector3Col();
        assertArrayEquals(v.getSiArray(), v2.asAbsVector3Col().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, v2.getDisplayUnit());
        assertEquals(v.getReference(), v2.getReference());
        assertEquals(180.0, v2.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        var mNxM = v.asAbsMatrixNxM();
        assertArrayEquals(v.getSiArray(), mNxM.asAbsVector3Col().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mNxM.getDisplayUnit());
        assertEquals(v.getReference(), mNxM.getReference());
        assertEquals(180.0, mNxM.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        var mQT = v.asAbsQuantityTable();
        assertArrayEquals(v.getSiArray(), mQT.asAbsVector3Col().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mQT.getDisplayUnit());
        assertEquals(v.getReference(), mQT.getReference());
        assertEquals(180.0, mQT.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        var vNcol = v.asAbsVectorNCol();
        assertArrayEquals(v.getSiArray(), vNcol.asAbsVector3Col().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vNcol.getDisplayUnit());
        assertEquals(180.0, vNcol.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(IllegalStateException.class, () -> v.asAbsMatrix1x1());
        assertThrows(IllegalStateException.class, () -> v.asAbsMatrix2x2());
        assertThrows(IllegalStateException.class, () -> v.asAbsMatrix3x3());
        assertThrows(IllegalStateException.class, () -> v.asAbsVector1());
        assertThrows(IllegalStateException.class, () -> v.asAbsVector2Col());
        assertThrows(IllegalStateException.class, () -> v.asAbsVector2Col());
        assertThrows(IllegalStateException.class, () -> v.asAbsVector2Row());
        assertThrows(IllegalStateException.class, () -> v.asAbsVector3Row());
        assertThrows(IllegalStateException.class, () -> v.asAbsVectorNRow());
    }

    /**
     * Test AbsVector3.Row as() functions.
     */
    @Test
    @DisplayName("AbsVector3.Row as() functions test")
    public void testAbsVector3RowAsFunctions()
    {
        var v = northDegRow();

        var v2 = v.asAbsVector3Row();
        assertArrayEquals(v.getSiArray(), v2.asAbsVector3Row().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, v2.getDisplayUnit());
        assertEquals(v.getReference(), v2.getReference());
        assertEquals(180.0, v2.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        var mNxM = v.asAbsMatrixNxM();
        assertArrayEquals(v.getSiArray(), mNxM.asAbsVector3Row().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mNxM.getDisplayUnit());
        assertEquals(v.getReference(), mNxM.getReference());
        assertEquals(180.0, mNxM.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        var mQT = v.asAbsQuantityTable();
        assertArrayEquals(v.getSiArray(), mQT.asAbsVector3Row().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, mQT.getDisplayUnit());
        assertEquals(v.getReference(), mQT.getReference());
        assertEquals(180.0, mQT.get(0, 0).getInUnit(Angle.Unit.deg), 1E-10);

        var vNcol = v.asAbsVectorNRow();
        assertArrayEquals(v.getSiArray(), vNcol.asAbsVector3Row().getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vNcol.getDisplayUnit());
        assertEquals(180.0, vNcol.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(IllegalStateException.class, () -> v.asAbsMatrix1x1());
        assertThrows(IllegalStateException.class, () -> v.asAbsMatrix2x2());
        assertThrows(IllegalStateException.class, () -> v.asAbsMatrix3x3());
        assertThrows(IllegalStateException.class, () -> v.asAbsVector1());
        assertThrows(IllegalStateException.class, () -> v.asAbsVector2Row());
        assertThrows(IllegalStateException.class, () -> v.asAbsVector3Col());
        assertThrows(IllegalStateException.class, () -> v.asAbsVectorNCol());
    }

}
