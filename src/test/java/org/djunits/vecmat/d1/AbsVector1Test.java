package org.djunits.vecmat.d1;

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
 * Tests for {@link AbsVector1}.
 * <p>
 * This class provides exhaustive coverage of AbsVector1 and AbsVector1.Row and all inherited functionality from AbsVector,
 * AbsVectorMatrix and Value.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsVector1Test
{
    /**
     * Create a standard test AbsVector1.
     * @return a test AbsVector1
     */
    private AbsVector1<Direction, Angle> northDeg()
    {
        return AbsVector1.of(new double[] {180.0}, Angle.Unit.deg, Direction.Reference.NORTH);
    }

    // ==================================== Constructor and base methods ====================================

    /**
     * Test constructor and base methods such as rows(), cols(), size() for AbsVector1.
     */
    @Test
    public void testCtorBase()
    {
        var rv = Vector1.of(180.0, Angle.Unit.deg);
        var av = new AbsVector1<>(rv, Direction.Reference.NORTH);
        assertEquals(Angle.Unit.deg, av.getDisplayUnit());
        assertEquals(Direction.Reference.NORTH, av.getReference());

        Direction d180 = new Direction(180.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertEquals(d180, av.get(0));
        assertEquals(d180, av.mget(1));

        assertEquals(d180.si(), av.si(0));
        assertEquals(d180.si(), av.msi(1));

        assertEquals(1, av.rows());
        assertEquals(1, av.cols());
        assertEquals(1, av.size());
        assertTrue(av.isAbsolute());
        assertFalse(av.isRelative());

        av.setDisplayUnit("rad");
        assertEquals(Angle.Unit.rad, av.getDisplayUnit());
        av.setDisplayUnit("deg");
        assertEquals(Angle.Unit.deg, av.getDisplayUnit());
    }

    /**
     * Test exceptions for AbsVector1 constructor.
     */
    @Test
    public void testCtorBaseExceptions()
    {
        var rv = Vector1.of(180.0, Angle.Unit.deg);
        assertThrows(NullPointerException.class, () -> new AbsVector1<>(null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> new AbsVector1<>(rv, null));
    }

    /**
     * Test instantiate() and instantiateSi() for column matrices.
     */
    @Test
    public void testInstantiate()
    {
        var v = northDeg();
        var v2 = v.instantiateSi(new double[] {0.5 * Math.PI}, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, v2.si(0));
        assertEquals(Angle.Unit.deg, v2.getDisplayUnit()); // same as original northDeg AbsVector1
        assertEquals(Direction.Reference.EAST, v2.getReference());

        var rv = Vector1.of(0.5 * Math.PI, Angle.Unit.rad);
        var v3 = v.instantiate(rv, Direction.Reference.EAST);
        assertEquals(0.5 * Math.PI, v3.si(0));
        assertEquals(Angle.Unit.deg, v3.getDisplayUnit()); // same as original northDeg AbsVector1
        assertEquals(Direction.Reference.EAST, v3.getReference());

        assertThrows(NullPointerException.class, () -> v.instantiateSi(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> v.instantiateSi(v2.getSiArray(), null));
        assertThrows(IllegalArgumentException.class, () -> v.instantiateSi(new double[] {}, Direction.Reference.EAST));
        assertThrows(IllegalArgumentException.class,
                () -> v.instantiateSi(new double[] {1, 2, 3, 4, 5}, Direction.Reference.EAST));

        assertThrows(NullPointerException.class, () -> v.instantiate(null, Direction.Reference.EAST));
        assertThrows(NullPointerException.class, () -> v.instantiate(rv, null));
    }

    // ==================================== Static factory methods ====================================

    /**
     * Test all of() and ofSi() static factories for column matrices.
     */
    @Test
    public void testStaticFactories()
    {
        Angle[] qa = {new Angle(180, Angle.Unit.deg)};
        var adeg = new double[] {180.0};
        var adeg5 = new double[] {0.0, 90.0, 180.0, 270.0, 45.0};
        var arad = new double[] {Math.PI};
        var rv = Vector1.of(180.0, Angle.Unit.deg);
        var av = new AbsVector1<>(rv, Direction.Reference.NORTH);

        // of(double, double, unit, ref)
        AbsVector1<Direction, Angle> vddu = AbsVector1.of(180.0, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vddu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vddu.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector1.of(180.0, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.of(180.0, Angle.Unit.deg, null));

        // of(Q, ref)
        AbsVector1<Direction, Angle> vqq = AbsVector1.of(qa[0], Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqq.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqq.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector1.of((Angle) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.of(qa[0], null));

        // ofSi(double, unit, ref)
        AbsVector1<Direction, Angle> vsiu = AbsVector1.ofSi(arad[0], Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsiu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsiu.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector1.ofSi(arad[0], null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.ofSi(arad[0], Angle.Unit.deg, null));

        // of(double[], unit, ref)
        AbsVector1<Direction, Angle> vdu = AbsVector1.of(adeg, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vdu.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vdu.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector1.of((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.of(adeg, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.of(adeg, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector1.of(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsVector1.of(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // ofSi(double[], displayUnit, ref)
        AbsVector1<Direction, Angle> vsi = AbsVector1.ofSi(arad, Angle.Unit.deg, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vsi.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vsi.getDisplayUnit());
        assertThrows(NullPointerException.class,
                () -> AbsVector1.ofSi((double[]) null, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.ofSi(arad, null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.ofSi(arad, Angle.Unit.deg, null));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector1.ofSi(new double[] {}, Angle.Unit.deg, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class, () -> AbsVector1.ofSi(adeg5, Angle.Unit.deg, Direction.Reference.NORTH));

        // of(Q[], ref)
        AbsVector1<Direction, Angle> vqa = AbsVector1.of(qa, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.deg, vqa.getDisplayUnit());
        AbsVector1<Direction, Angle> vqa2 = AbsVector1.of(new Angle[] {Angle.of(Math.PI, "rad")}, Direction.Reference.NORTH);
        assertArrayEquals(av.getSiArray(), vqa2.getSiArray(), 1E-10);
        assertEquals(Angle.Unit.rad, vqa2.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector1.of((Angle[]) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.of(qa, null));
        assertThrows(IllegalArgumentException.class, () -> AbsVector1.of(new Angle[] {}, Direction.Reference.NORTH));
        assertThrows(IllegalArgumentException.class,
                () -> AbsVector1.of(new Angle[] {qa[0], qa[0]}, Direction.Reference.NORTH));

        // of(Vector, ref)
        Vector1<Angle> rel = Vector1.of(new double[] {1}, Angle.Unit.deg);
        AbsVector1<Direction, Angle> avrel = AbsVector1.of(rel, Direction.Reference.NORTH);
        assertEquals(1.0 * Math.PI / 180.0, avrel.si(0), 1E-10);
        assertEquals(Angle.Unit.deg, avrel.getDisplayUnit());
        assertThrows(NullPointerException.class, () -> AbsVector1.of((Vector1<Angle>) null, Direction.Reference.NORTH));
        assertThrows(NullPointerException.class, () -> AbsVector1.of(rel, null));
    }

    // ==================================== Scalar & SI array access ====================================

    /**
     * Test scalar and SI array extraction for AbsVector1.
     */
    @Test
    public void testScalarAndSiArray()
    {
        AbsVector1<Direction, Angle> v = northDeg();
        Direction[] a = v.getScalarArray();
        double[] asi = v.getSiArray();
        assertEquals(1, a.length);
        assertEquals(1, asi.length);
        assertEquals(180.0, a[0].getInUnit(Angle.Unit.deg), 1e-12);
        assertEquals(Math.PI, asi[0], 1e-12);
    }

    // ==================================== min, max, median ====================================

    /**
     * Test min(), max() and median() for AbsVector1.
     */
    @Test
    public void testMinMaxMedian()
    {
        AbsVector1<Direction, Angle> v = northDeg();
        assertEquals(Math.PI, v.min().si(), 1E-10);
        assertEquals(Math.PI, v.max().si(), 1E-10);
        assertEquals(180.0, v.median().getInUnit(Angle.Unit.deg), 1E-10);

        assertEquals(Angle.Unit.deg, v.min().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.max().getDisplayUnit());
        assertEquals(Angle.Unit.deg, v.median().getDisplayUnit());
    }

    // ==================================== transpose ====================================

    /**
     * Test transpose().
     */
    @Test
    public void testTranspose()
    {
        AbsVector1<Direction, Angle> v = northDeg();
        var vt = v.transpose();
        var vt2 = vt.transpose();
        assertEquals(v, vt2);

        assertTrue(v.isColumnVector());
        assertTrue(vt.isRowVector());
        assertTrue(vt2.isColumnVector());

        assertEquals(Math.PI, vt.si(0), 1E-10);
        assertEquals(Math.PI, vt2.si(0), 1E-10);

        assertEquals(Angle.Unit.deg, vt.getDisplayUnit());
        assertEquals(Angle.Unit.deg, vt2.getDisplayUnit());
    }

    // ==================================== add, subtract ====================================

    /**
     * Test add() and subtract() methods for AbsVector1.
     */
    @Test
    public void testAddSubtract()
    {
        AbsVector1<Direction, Angle> v = northDeg();
        AbsVector1<Direction, Angle> vadd = northDeg().add(Angle.of(5.0, "deg"));
        AbsVector1<Direction, Angle> vsub = northDeg().subtract(Angle.of(5.0, "deg"));
        Vector1<Angle> v0 = v.subtract(v);

        assertArrayEquals(new double[] {0}, v0.getSiArray());
        assertEquals(185.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(175.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        Vector1<Angle> v12 = Vector1.of(new double[] {1}, Angle.Unit.deg);
        vadd = v.add(v12);
        vsub = v.subtract(v12);
        assertEquals(181.0, vadd.get(0).getInUnit(Angle.Unit.deg), 1E-10);
        assertEquals(179.0, vsub.get(0).getInUnit(Angle.Unit.deg), 1E-10);

        assertThrows(NullPointerException.class, () -> v.add((Angle) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Angle) null));
        assertThrows(NullPointerException.class, () -> v.add((Vector1<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((Vector1<Angle>) null));
        assertThrows(NullPointerException.class, () -> v.subtract((AbsVector1<Direction, Angle>) null));
    }

    // ==================================== iterator ====================================

    /**
     * Test iterator for AbsVector1.
     */
    @Test
    public void testIterator()
    {
        AbsVector1<Direction, Angle> v = northDeg();
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
     * Test toString() for AbsVector1.
     */
    @Test
    public void testToString()
    {
        AbsVector1<Direction, Angle> v = northDeg();
        String sdeg = v.toString();
        String srad = v.toString(Angle.Unit.rad);
        assertTrue(sdeg.contains(Angle.Unit.deg.getDisplayAbbreviation()));
        assertTrue(srad.contains(Angle.Unit.rad.getDisplayAbbreviation()));
        assertTrue(sdeg.contains("["));
        assertTrue(srad.contains("]"));
        assertTrue(sdeg.contains(""));
        assertTrue(srad.contains(""));
    }

    // ==================================== equals, hashCode ====================================

    /**
     * Test equals(), hashCode() for AbsVector1.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsHashCode()
    {
        AbsVector1<Direction, Angle> vdn = AbsVector1.ofSi(new double[] {0.1}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVector1<Direction, Angle> vrn = AbsVector1.ofSi(new double[] {0.1}, Angle.Unit.rad, Direction.Reference.NORTH);
        AbsVector1<Direction, Angle> vde = AbsVector1.ofSi(new double[] {0.1}, Angle.Unit.deg, Direction.Reference.EAST);

        assertTrue(vdn.equals(vdn));
        assertTrue(vdn.equals(vrn));
        assertFalse(vdn.equals(vde));
        assertFalse(vdn.equals(null));
        assertFalse(vdn.equals(new String("abc")));
        assertFalse(vdn.equals(northDeg()));

        assertEquals(vdn.hashCode(), AbsVector1.ofSi(new double[] {0.1}, Angle.Unit.deg, Direction.Reference.NORTH).hashCode());
        assertEquals(vdn.hashCode(), vrn.hashCode());
        assertNotEquals(vdn.hashCode(), vde.hashCode());
        assertNotEquals(vdn.hashCode(), northDeg().hashCode());
    }

}
