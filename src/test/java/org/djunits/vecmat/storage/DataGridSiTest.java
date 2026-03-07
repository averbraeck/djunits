package org.djunits.vecmat.storage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * DataGridSiTest tests equals, hashCode, equals(obj, eps) for the different DataGridSi classes.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class DataGridSiTest
{
    /**
     * Test equals() with other DataGridSi instances.
     */
    @Test
    public void testEquals()
    {
        double[] d3x2 = new double[] {1, 2.0, -3.0, 4.0, 5.0, -6.0};
        float[] f3x2 = new float[] {1f, 2.0f, -3.0f, 4.0f, 5.0f, -6.0f};
        int[] ind32 = new int[] {0, 1, 2, 3, 4, 5};

        DenseDoubleDataSi dddd = new DenseDoubleDataSi(d3x2, 3, 2);
        DenseFloatDataSi dfdf = new DenseFloatDataSi(f3x2, 3, 2);
        SparseDoubleDataSi sddd = new SparseDoubleDataSi(d3x2, ind32, 3, 2);
        SparseFloatDataSi sfdf = new SparseFloatDataSi(f3x2, ind32, 3, 2);

        assertAll(() -> assertEquals(dddd, dddd), () -> assertEquals(dddd, dfdf), () -> assertEquals(dddd, sddd),
                () -> assertEquals(dddd, sfdf));
        assertAll(() -> assertEquals(dfdf, dddd), () -> assertEquals(dfdf, dfdf), () -> assertEquals(dfdf, sddd),
                () -> assertEquals(dfdf, sfdf));
        assertAll(() -> assertEquals(sddd, dddd), () -> assertEquals(sddd, dfdf), () -> assertEquals(sddd, sddd),
                () -> assertEquals(sddd, sfdf));
        assertAll(() -> assertEquals(sfdf, dddd), () -> assertEquals(sfdf, dfdf), () -> assertEquals(sfdf, sddd),
                () -> assertEquals(sfdf, sfdf));

        DenseDoubleDataSi dddd2 = new DenseDoubleDataSi(d3x2, 2, 3);
        DenseFloatDataSi dfdf2 = new DenseFloatDataSi(f3x2, 2, 3);
        SparseDoubleDataSi sddd2 = new SparseDoubleDataSi(d3x2, ind32, 2, 3);
        SparseFloatDataSi sfdf2 = new SparseFloatDataSi(f3x2, ind32, 2, 3);

        assertAll(() -> assertNotEquals(dddd, dddd2), () -> assertNotEquals(dddd, dfdf2), () -> assertNotEquals(dddd, sddd2),
                () -> assertNotEquals(dddd, sfdf2));
        assertAll(() -> assertNotEquals(dfdf, dddd2), () -> assertNotEquals(dfdf, dfdf2), () -> assertNotEquals(dfdf, sddd2),
                () -> assertNotEquals(dfdf, sfdf2));
        assertAll(() -> assertNotEquals(sddd, dddd2), () -> assertNotEquals(sddd, dfdf2), () -> assertNotEquals(sddd, sddd2),
                () -> assertNotEquals(sddd, sfdf2));
        assertAll(() -> assertNotEquals(sfdf, dddd2), () -> assertNotEquals(sfdf, dfdf2), () -> assertNotEquals(sfdf, sddd2),
                () -> assertNotEquals(sfdf, sfdf2));

        double[] d2x2 = new double[] {1, 2.0, -3.0, 4.0};
        float[] f2x2 = new float[] {1f, 2.0f, -3.0f, 4.0f};
        int[] ind2x2 = new int[] {0, 1, 2, 3};

        DenseDoubleDataSi dddd22 = new DenseDoubleDataSi(d2x2, 2, 2);
        DenseFloatDataSi dfdf22 = new DenseFloatDataSi(f2x2, 2, 2);
        SparseDoubleDataSi sddd22 = new SparseDoubleDataSi(d2x2, ind2x2, 2, 2);
        SparseFloatDataSi sfdf22 = new SparseFloatDataSi(f2x2, ind2x2, 2, 2);

        assertAll(() -> assertNotEquals(dddd, dddd22), () -> assertNotEquals(dddd, dfdf22), () -> assertNotEquals(dddd, sddd22),
                () -> assertNotEquals(dddd, sfdf22));
        assertAll(() -> assertNotEquals(dfdf, dddd22), () -> assertNotEquals(dfdf, dfdf22), () -> assertNotEquals(dfdf, sddd22),
                () -> assertNotEquals(dfdf, sfdf22));
        assertAll(() -> assertNotEquals(sddd, dddd22), () -> assertNotEquals(sddd, dfdf22), () -> assertNotEquals(sddd, sddd22),
                () -> assertNotEquals(sddd, sfdf22));
        assertAll(() -> assertNotEquals(sfdf, dddd22), () -> assertNotEquals(sfdf, dfdf22), () -> assertNotEquals(sfdf, sddd22),
                () -> assertNotEquals(sfdf, sfdf22));

        assertAll(() -> assertNotEquals(dddd2, dddd22), () -> assertNotEquals(dddd2, dfdf22),
                () -> assertNotEquals(dddd2, sddd22), () -> assertNotEquals(dddd2, sfdf22));
        assertAll(() -> assertNotEquals(dfdf2, dddd22), () -> assertNotEquals(dfdf2, dfdf22),
                () -> assertNotEquals(dfdf2, sddd22), () -> assertNotEquals(dfdf2, sfdf22));
        assertAll(() -> assertNotEquals(sddd2, dddd22), () -> assertNotEquals(sddd2, dfdf22),
                () -> assertNotEquals(sddd2, sddd22), () -> assertNotEquals(sddd2, sfdf22));
        assertAll(() -> assertNotEquals(sfdf2, dddd22), () -> assertNotEquals(sfdf2, dfdf22),
                () -> assertNotEquals(sfdf2, sddd22), () -> assertNotEquals(sfdf2, sfdf22));

        double[] d3x2hard = new double[] {1.0 / 3.0, Math.PI, -Math.E, Math.sin(5.1), 1E7, -1E-10};
        float[] f3x2hard = new float[] {(float) (1.0 / 3.0), (float) Math.PI, (float) (-Math.E), (float) Math.sin(5.1),
                (float) 1E7, (float) -1E-10};

        DenseDoubleDataSi ddddhard = new DenseDoubleDataSi(d3x2hard, 3, 2);
        DenseFloatDataSi dfdfhard = new DenseFloatDataSi(f3x2hard, 3, 2);
        SparseDoubleDataSi sdddhard = new SparseDoubleDataSi(d3x2hard, ind32, 3, 2);
        SparseFloatDataSi sfdfhard = new SparseFloatDataSi(f3x2hard, ind32, 3, 2);

        assertAll(() -> assertNotEquals(dddd, ddddhard), () -> assertNotEquals(dddd, dfdfhard),
                () -> assertNotEquals(dddd, sdddhard), () -> assertNotEquals(dddd, sfdfhard));
        assertAll(() -> assertNotEquals(dfdf, ddddhard), () -> assertNotEquals(dfdf, dfdfhard),
                () -> assertNotEquals(dfdf, sdddhard), () -> assertNotEquals(dfdf, sfdfhard));
        assertAll(() -> assertNotEquals(sddd, ddddhard), () -> assertNotEquals(sddd, dfdfhard),
                () -> assertNotEquals(sddd, sdddhard), () -> assertNotEquals(sddd, sfdfhard));
        assertAll(() -> assertNotEquals(sfdf, ddddhard), () -> assertNotEquals(sfdf, dfdfhard),
                () -> assertNotEquals(sfdf, sdddhard), () -> assertNotEquals(sfdf, sfdfhard));
    }

    /**
     * Test hashCode() with other DataGridSi instances.
     */
    @Test
    public void testHashCode()
    {
        double[] d3x2 = new double[] {1, 2.0, -3.0, 4.0, 5.0, -6.0};
        float[] f3x2 = new float[] {1f, 2.0f, -3.0f, 4.0f, 5.0f, -6.0f};
        int[] ind32 = new int[] {0, 1, 2, 3, 4, 5};

        DenseDoubleDataSi dddd = new DenseDoubleDataSi(d3x2, 3, 2);
        DenseFloatDataSi dfdf = new DenseFloatDataSi(f3x2, 3, 2);
        SparseDoubleDataSi sddd = new SparseDoubleDataSi(d3x2, ind32, 3, 2);
        SparseFloatDataSi sfdf = new SparseFloatDataSi(f3x2, ind32, 3, 2);

        assertAll(() -> assertEquals(dddd.hashCode(), dddd.hashCode()), () -> assertEquals(dddd.hashCode(), dfdf.hashCode()),
                () -> assertEquals(dddd.hashCode(), sddd.hashCode()), () -> assertEquals(dddd.hashCode(), sfdf.hashCode()));
        assertAll(() -> assertEquals(dfdf.hashCode(), dddd.hashCode()), () -> assertEquals(dfdf.hashCode(), dfdf.hashCode()),
                () -> assertEquals(dfdf.hashCode(), sddd.hashCode()), () -> assertEquals(dfdf.hashCode(), sfdf.hashCode()));
        assertAll(() -> assertEquals(sddd.hashCode(), dddd.hashCode()), () -> assertEquals(sddd.hashCode(), dfdf.hashCode()),
                () -> assertEquals(sddd.hashCode(), sddd.hashCode()), () -> assertEquals(sddd.hashCode(), sfdf.hashCode()));
        assertAll(() -> assertEquals(sfdf.hashCode(), dddd.hashCode()), () -> assertEquals(sfdf.hashCode(), dfdf.hashCode()),
                () -> assertEquals(sfdf.hashCode(), sddd.hashCode()), () -> assertEquals(sfdf.hashCode(), sfdf.hashCode()));

        DenseDoubleDataSi dddd2 = new DenseDoubleDataSi(d3x2, 2, 3);
        DenseFloatDataSi dfdf2 = new DenseFloatDataSi(f3x2, 2, 3);
        SparseDoubleDataSi sddd2 = new SparseDoubleDataSi(d3x2, ind32, 2, 3);
        SparseFloatDataSi sfdf2 = new SparseFloatDataSi(f3x2, ind32, 2, 3);

        assertAll(() -> assertNotEquals(dddd, dddd2.hashCode()), () -> assertNotEquals(dddd, dfdf2.hashCode()),
                () -> assertNotEquals(dddd, sddd2.hashCode()), () -> assertNotEquals(dddd, sfdf2.hashCode()));
        assertAll(() -> assertNotEquals(dfdf.hashCode(), dddd2.hashCode()),
                () -> assertNotEquals(dfdf.hashCode(), dfdf2.hashCode()),
                () -> assertNotEquals(dfdf.hashCode(), sddd2.hashCode()),
                () -> assertNotEquals(dfdf.hashCode(), sfdf2.hashCode()));
        assertAll(() -> assertNotEquals(sddd.hashCode(), dddd2.hashCode()),
                () -> assertNotEquals(sddd.hashCode(), dfdf2.hashCode()),
                () -> assertNotEquals(sddd.hashCode(), sddd2.hashCode()),
                () -> assertNotEquals(sddd.hashCode(), sfdf2.hashCode()));
        assertAll(() -> assertNotEquals(sfdf.hashCode(), dddd2.hashCode()), () -> assertNotEquals(sfdf, dfdf2.hashCode()),
                () -> assertNotEquals(sfdf.hashCode(), sddd2.hashCode()),
                () -> assertNotEquals(sfdf.hashCode(), sfdf2.hashCode()));

        double[] d2x2 = new double[] {1, 2.0, -3.0, 4.0};
        float[] f2x2 = new float[] {1f, 2.0f, -3.0f, 4.0f};
        int[] ind2x2 = new int[] {0, 1, 2, 3};

        DenseDoubleDataSi dddd22 = new DenseDoubleDataSi(d2x2, 2, 2);
        DenseFloatDataSi dfdf22 = new DenseFloatDataSi(f2x2, 2, 2);
        SparseDoubleDataSi sddd22 = new SparseDoubleDataSi(d2x2, ind2x2, 2, 2);
        SparseFloatDataSi sfdf22 = new SparseFloatDataSi(f2x2, ind2x2, 2, 2);

        assertAll(() -> assertNotEquals(dddd, dddd22), () -> assertNotEquals(dddd, dfdf22.hashCode()),
                () -> assertNotEquals(dddd, sddd22.hashCode()), () -> assertNotEquals(dddd, sfdf22.hashCode()));
        assertAll(() -> assertNotEquals(dfdf.hashCode(), dddd22.hashCode()),
                () -> assertNotEquals(dfdf.hashCode(), dfdf22.hashCode()),
                () -> assertNotEquals(dfdf.hashCode(), sddd22.hashCode()),
                () -> assertNotEquals(dfdf.hashCode(), sfdf22.hashCode()));
        assertAll(() -> assertNotEquals(sddd.hashCode(), dddd22.hashCode()),
                () -> assertNotEquals(sddd.hashCode(), dfdf22.hashCode()),
                () -> assertNotEquals(sddd.hashCode(), sddd22.hashCode()),
                () -> assertNotEquals(sddd.hashCode(), sfdf22.hashCode()));
        assertAll(() -> assertNotEquals(sfdf.hashCode(), dddd22.hashCode()), () -> assertNotEquals(sfdf, dfdf22.hashCode()),
                () -> assertNotEquals(sfdf.hashCode(), sddd22.hashCode()), () -> assertNotEquals(sfdf, sfdf22.hashCode()));

        assertAll(() -> assertNotEquals(dddd2.hashCode(), dddd22.hashCode()),
                () -> assertNotEquals(dddd2.hashCode(), dfdf22.hashCode()),
                () -> assertNotEquals(dddd2.hashCode(), sddd22.hashCode()),
                () -> assertNotEquals(dddd2.hashCode(), sfdf22.hashCode()));
        assertAll(() -> assertNotEquals(dfdf2.hashCode(), dddd22.hashCode()),
                () -> assertNotEquals(dfdf2.hashCode(), dfdf22.hashCode()),
                () -> assertNotEquals(dfdf2.hashCode(), sddd22.hashCode()),
                () -> assertNotEquals(dfdf2.hashCode(), sfdf22.hashCode()));
        assertAll(() -> assertNotEquals(sddd2.hashCode(), dddd22.hashCode()),
                () -> assertNotEquals(sddd2.hashCode(), dfdf22.hashCode()),
                () -> assertNotEquals(sddd2.hashCode(), sddd22.hashCode()),
                () -> assertNotEquals(sddd2.hashCode(), sfdf22.hashCode()));
        assertAll(() -> assertNotEquals(sfdf2.hashCode(), dddd22.hashCode()),
                () -> assertNotEquals(sfdf2.hashCode(), dfdf22.hashCode()),
                () -> assertNotEquals(sfdf2.hashCode(), sddd22.hashCode()),
                () -> assertNotEquals(sfdf2.hashCode(), sfdf22.hashCode()));
    }

    /**
     * Test equals(obj, eps) with other DataGridSi instances.
     */
    @Test
    public void testEqualsEps()
    {
        double[] d3x2hard = new double[] {1.0 / 3.0, Math.PI, -Math.E, Math.sin(5.1), 1E7, -1E-10};
        float[] f3x2hard = new float[] {(float) (1.0 / 3.0), (float) Math.PI, (float) (-Math.E), (float) Math.sin(5.1),
                (float) 1E7, (float) -1E-10};
        int[] ind32 = new int[] {0, 1, 2, 3, 4, 5};

        DenseDoubleDataSi ddddhard = new DenseDoubleDataSi(d3x2hard, 3, 2);
        DenseFloatDataSi dfdfhard = new DenseFloatDataSi(f3x2hard, 3, 2);
        SparseDoubleDataSi sdddhard = new SparseDoubleDataSi(d3x2hard, ind32, 3, 2);
        SparseFloatDataSi sfdfhard = new SparseFloatDataSi(f3x2hard, ind32, 3, 2);

        double eps = 1E-6;
        assertTrue(ddddhard.equals(ddddhard, eps));
        assertTrue(ddddhard.equals(dfdfhard, eps));
        assertTrue(ddddhard.equals(sdddhard, eps));
        assertTrue(ddddhard.equals(sfdfhard, eps));
        assertTrue(dfdfhard.equals(ddddhard, eps));
        assertTrue(dfdfhard.equals(dfdfhard, eps));
        assertTrue(dfdfhard.equals(sdddhard, eps));
        assertTrue(dfdfhard.equals(sfdfhard, eps));
        assertTrue(sdddhard.equals(ddddhard, eps));
        assertTrue(sdddhard.equals(dfdfhard, eps));
        assertTrue(sdddhard.equals(sdddhard, eps));
        assertTrue(sdddhard.equals(sfdfhard, eps));
        assertTrue(sfdfhard.equals(ddddhard, eps));
        assertTrue(sfdfhard.equals(dfdfhard, eps));
        assertTrue(sfdfhard.equals(sdddhard, eps));
        assertTrue(sfdfhard.equals(sfdfhard, eps));

        double[] d2x2 = new double[] {1.0, 3.0, 4.0, 5.0};
        DenseDoubleDataSi ddd2x2 = new DenseDoubleDataSi(d2x2, 2, 2);
        double[] d3x1 = new double[] {1.0, 3.0, 5.0};
        DenseDoubleDataSi ddd3x1 = new DenseDoubleDataSi(d3x1, 3, 1);
        assertFalse(ddddhard.equals(null, eps));
        assertFalse(ddddhard.equals(ddd2x2, eps));
        assertFalse(ddddhard.equals(ddd3x1, eps));

        assertThrows(IllegalArgumentException.class, () -> ddddhard.equals(ddddhard, -eps));

        double[] d3x2b = new double[] {1.0 / 3.0, Math.PI, -Math.E, Math.sin(5.1), 1E7, 1E-5};
        DenseDoubleDataSi ddd3x2b = new DenseDoubleDataSi(d3x2b, 3, 2);
        assertFalse(ddddhard.equals(ddd3x2b, eps));
    }

    /**
     * Test equals() with other DataGridSi instances with zeroes.
     */
    @Test
    public void testEquals0()
    {
        double[] d3x2 = new double[] {1, 0, 0, 2, 0, 0};
        float[] f3x2 = new float[] {1f, 0, 0, 2f, 0f, 0f};
        double[] d3x2s = new double[] {1, 2};
        float[] f3x2s = new float[] {1f, 2f};
        int[] ind0 = new int[] {0, 3};

        DenseDoubleDataSi dddd = new DenseDoubleDataSi(d3x2, 3, 2);
        DenseFloatDataSi dfdf = new DenseFloatDataSi(f3x2, 3, 2);
        SparseDoubleDataSi sddd = new SparseDoubleDataSi(d3x2s, ind0, 3, 2);
        SparseFloatDataSi sfdf = new SparseFloatDataSi(f3x2s, ind0, 3, 2);

        assertAll(() -> assertEquals(dddd, dddd), () -> assertEquals(dddd, dfdf), () -> assertEquals(dddd, sddd),
                () -> assertEquals(dddd, sfdf));
        assertAll(() -> assertEquals(dfdf, dddd), () -> assertEquals(dfdf, dfdf), () -> assertEquals(dfdf, sddd),
                () -> assertEquals(dfdf, sfdf));
        assertAll(() -> assertEquals(sddd, dddd), () -> assertEquals(sddd, dfdf), () -> assertEquals(sddd, sddd),
                () -> assertEquals(sddd, sfdf));
        assertAll(() -> assertEquals(sfdf, dddd), () -> assertEquals(sfdf, dfdf), () -> assertEquals(sfdf, sddd),
                () -> assertEquals(sfdf, sfdf));

        int[] ind2 = new int[] {0, 2};
        SparseDoubleDataSi sddd2 = new SparseDoubleDataSi(d3x2s, ind2, 3, 2);
        SparseFloatDataSi sfdf2 = new SparseFloatDataSi(f3x2s, ind2, 3, 2);
        assertNotEquals(sddd, sddd2);
        assertNotEquals(sddd, sfdf2);
        assertNotEquals(sfdf, sddd2);
        assertNotEquals(sfdf, sfdf2);
    }

    /**
     * Test get() with wrong bounds.
     */
    @Test
    public void testGetBounds()
    {
        double[] d3x2 = new double[] {1, 0, 0, 2, 0, 0};
        float[] f3x2 = new float[] {1f, 0, 0, 2f, 0f, 0f};
        double[] d3x2s = new double[] {1, 2};
        float[] f3x2s = new float[] {1f, 2f};
        int[] ind0 = new int[] {0, 3};

        DenseDoubleDataSi dddd = new DenseDoubleDataSi(d3x2, 3, 2);
        DenseFloatDataSi dfdf = new DenseFloatDataSi(f3x2, 3, 2);
        SparseDoubleDataSi sddd = new SparseDoubleDataSi(d3x2s, ind0, 3, 2);
        SparseFloatDataSi sfdf = new SparseFloatDataSi(f3x2s, ind0, 3, 2);
        DataGridSi<?>[] dgArray = new DataGridSi[] {dddd, dfdf, sddd, sfdf};

        for (var dg : dgArray)
        {
            assertThrows(IndexOutOfBoundsException.class, () -> dg.get(-1, 1));
            assertThrows(IndexOutOfBoundsException.class, () -> dg.get(1, -1));
            assertThrows(IndexOutOfBoundsException.class, () -> dg.get(7, 1));
            assertThrows(IndexOutOfBoundsException.class, () -> dg.get(1, 7));
        }
    }

    /**
     * Test cardinality() with different types.
     */
    @Test
    public void testCardinality()
    {
        double[] d3x2 = new double[] {1, 0, 0, 2, 0, 0};
        float[] f3x2 = new float[] {1f, 0, 0, 2f, 0f, 0f};
        double[] d3x2s = new double[] {1, 2};
        float[] f3x2s = new float[] {1f, 2f};
        int[] ind0 = new int[] {0, 3};

        DenseDoubleDataSi dddd = new DenseDoubleDataSi(d3x2, 3, 2);
        DenseFloatDataSi dfdf = new DenseFloatDataSi(f3x2, 3, 2);
        SparseDoubleDataSi sddd = new SparseDoubleDataSi(d3x2s, ind0, 3, 2);
        SparseFloatDataSi sfdf = new SparseFloatDataSi(f3x2s, ind0, 3, 2);
        DataGridSi<?>[] dgArray = new DataGridSi[] {dddd, dfdf, sddd, sfdf};

        for (var dg : dgArray)
        {
            assertEquals(2, dg.cardinality());
        }
    }

    /**
     * Test instantiateNew() with a length error for different types.
     */
    @Test
    public void testInstantiateNewError()
    {
        double[] d3x2 = new double[] {1, 0, 0, 2, 0, 0};
        float[] f3x2 = new float[] {1f, 0, 0, 2f, 0f, 0f};
        double[] d3x2s = new double[] {1, 2};
        float[] f3x2s = new float[] {1f, 2f};
        int[] ind0 = new int[] {0, 3};

        DenseDoubleDataSi dddd = new DenseDoubleDataSi(d3x2, 3, 2);
        DenseFloatDataSi dfdf = new DenseFloatDataSi(f3x2, 3, 2);
        SparseDoubleDataSi sddd = new SparseDoubleDataSi(d3x2s, ind0, 3, 2);
        SparseFloatDataSi sfdf = new SparseFloatDataSi(f3x2s, ind0, 3, 2);
        DataGridSi<?>[] dgArray = new DataGridSi[] {dddd, dfdf, sddd, sfdf};

        for (var dg : dgArray)
        {
            assertThrows(IllegalArgumentException.class, () -> dg.instantiateNew(new double[] {0, 1, 2}));
        }
    }

}
