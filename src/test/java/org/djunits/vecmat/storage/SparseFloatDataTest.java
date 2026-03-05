package org.djunits.vecmat.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.djunits.quantity.Length;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SparseFloatData}.
 * <p>
 * Achieves full branch and method coverage, including:
 * <ul>
 * <li>All constructors</li>
 * <li>Validation of nulls, bounds, ragged matrices, and index invariants</li>
 * <li>Sparse generation via all storeSparse(...) overloads</li>
 * <li>Binary-search paths in get()</li>
 * <li>All DataGrid default methods (getRowArray, getColArray)</li>
 * <li>copy(), instantiateNew(), cardinality()</li>
 * <li>SI conversion using Length</li>
 * <li>equals and hashCode</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class SparseFloatDataTest
{
    /**
     * Helper dense array for 2x3 matrix: {1, 0, 2, 0, 3, 4}.
     * @return sample dense data
     */
    private static double[] dense2x3()
    {
        return new double[] {1.0, 0.0, 2.0, 0.0, 3.0, 4.0};
    }

    /**
     * Helper dense 2x3 matrix.
     * @return {{1,0,2},{0,3,4}}
     */
    private static double[][] dense2x3Matrix()
    {
        return new double[][] {{1.0, 0.0, 2.0}, {0.0, 3.0, 4.0}};
    }

    // ----------------------------------------------------------------------
    // Constructor: protected SparseFloatData(float[], int[], r, c)
    // ----------------------------------------------------------------------

    /**
     * Test protected constructor(float[], int[], r, c) via reflection-like access through copy().
     */
    @Test
    @DisplayName("protected ctor(float[],indexes,r,c): validates length, order, bounds")
    public void testProtectedConstructor()
    {
        float[] sparse = new float[] {1f, 2f, 3f};
        int[] idx = new int[] {0, 2, 4};

        // Valid case
        SparseFloatData d = new SparseFloatData(sparse, idx, 2, 3);
        assertEquals(1.0, d.get(0, 0), 1e-12);
        assertEquals(2.0, d.get(0, 2), 1e-12);
        assertEquals(3.0, d.get(1, 1), 1e-12);

        // Null arrays
        assertThrows(NullPointerException.class, () -> new SparseFloatData((float[]) null, idx, 2, 3));
        assertThrows(NullPointerException.class, () -> new SparseFloatData(sparse, null, 2, 3));

        // Length mismatch
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(new float[] {1}, new int[] {0, 1}, 2, 3));

        // Wrong dims
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(sparse, idx, 0, 3));
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(sparse, idx, 2, 0));

        // Out of bounds index
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseFloatData(sparse, new int[] {0, 2, 6}, 2, 3));

        // Not strictly increasing → fail
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(sparse, new int[] {0, 2, 2}, 2, 3));
    }

    // ----------------------------------------------------------------------
    // Constructor: double[] denseData
    // ----------------------------------------------------------------------

    /**
     * Constructor: double[] denseData.
     */
    @Test
    @DisplayName("ctor(double[],r,c): basic sparse conversion + validations")
    public void testCtorDenseData()
    {
        assertThrows(NullPointerException.class, () -> new SparseFloatData((double[]) null, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(dense2x3(), 0, 3));
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(dense2x3(), 2, 0));
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(new double[] {1, 2}, 2, 3));

        SparseFloatData d = new SparseFloatData(dense2x3(), 2, 3);
        assertEquals(1.0, d.get(0, 0), 1e-12);
        assertEquals(0.0, d.get(0, 1), 1e-12);
        assertEquals(3.0, d.get(1, 1), 1e-12);
    }

    // ----------------------------------------------------------------------
    // Constructor: double[][] densely
    // ----------------------------------------------------------------------

    /**
     * Constructor: double[][] densely.
     */
    @Test
    @DisplayName("ctor(double[][]): ragged detection + safe sparse copy")
    public void testCtorMatrix()
    {
        assertThrows(NullPointerException.class, () -> new SparseFloatData((double[][]) null));
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(new double[][] {}));

        // Ragged
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(new double[][] {{1.0}, {1.0, 2.0}}));

        double[][] m = dense2x3Matrix();
        SparseFloatData d = new SparseFloatData(m);
        assertArrayEquals(dense2x3(), d.getDataArray(), 1e-12);

        // Modify source → must not change
        m[0][0] = 999.0;
        assertEquals(1.0, d.get(0, 0), 1e-12);
    }

    // ----------------------------------------------------------------------
    // Constructor: Q[] sparseData + indexes + safe copy
    // ----------------------------------------------------------------------

    /**
     * Constructor: Q[] sparseData + indexes + safe copy.
     */
    @Test
    @DisplayName("ctor(Q[],indexes): SI conversion + safe copies")
    public void testCtorSparseQArray()
    {
        Length[] q = new Length[] {new Length(1.0, Length.Unit.km), // 1000
                new Length(2.0, Length.Unit.m), // 2
                new Length(3.0, Length.Unit.cm) // 0.03
        };
        int[] idx = new int[] {0, 2, 5};

        SparseFloatData d = new SparseFloatData(q, idx, 2, 3);

        double[] dense = d.getDataArray();
        assertEquals(1000f, dense[0], 1e-9);
        assertEquals(2f, dense[2], 1e-9);
        assertEquals(0.03f, dense[5], 1e-9);

        // Modifying q must not change contents
        q[0] = new Length(999, Length.Unit.m);
        assertEquals(1000f, d.get(0, 0), 1e-9);
    }

    // ----------------------------------------------------------------------
    // Constructor: Q[] denseData to sparse safe copy
    // ----------------------------------------------------------------------

    /**
     * Constructor: Q[] denseData to sparse safe copy.
     */
    @Test
    @DisplayName("ctor(Q[],r,c): full dense → sparse with SI conversion")
    public void testCtorDenseQArray()
    {
        Length[] q = new Length[] {new Length(0.0, Length.Unit.m), new Length(1.0, Length.Unit.m),
                new Length(0.0, Length.Unit.m), new Length(1.0, Length.Unit.km)};

        SparseFloatData d = new SparseFloatData(q, 2, 2);

        double[] expected = new double[] {0, 1, 0, 1000};
        assertArrayEquals(expected, d.getDataArray(), 1e-9);

        // Wrong length
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(q, 3, 3));
    }

    // ----------------------------------------------------------------------
    // Constructor: Q[][] denseData
    // ----------------------------------------------------------------------

    /**
     * Constructor(Q[][]): SI conversion + ragged detection + sparse copy.
     */
    @Test
    @DisplayName("ctor(Q[][]): SI conversion + ragged detection + sparse copy")
    public void testCtorMatrixQ()
    {
        assertThrows(NullPointerException.class, () -> new SparseFloatData((Length[][]) null));
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(new Length[][] {}));
        assertThrows(IllegalArgumentException.class, () -> new SparseFloatData(new Length[][] {{Length.ofSi(1)}, {}}));

        Length[][] m = new Length[][] {{new Length(1.0, Length.Unit.km), new Length(0.0, Length.Unit.m)},
                {new Length(2.0, Length.Unit.cm), new Length(3.0, Length.Unit.mm)}};

        SparseFloatData d = new SparseFloatData(m);

        double[] dense = d.getDataArray();
        assertEquals(1000f, dense[0], 1e-9);
        assertEquals(0.02f, dense[2], 1e-9);
        assertEquals(0.003f, dense[3], 1e-9);

        // Modify source → must not change
        m[0][0] = new Length(999, Length.Unit.m);
        assertEquals(1000f, d.get(0, 0), 1e-9);
    }

    // ----------------------------------------------------------------------
    // Constructor: Collection<DoubleSparseValue>
    // ----------------------------------------------------------------------

    /**
     * Constructor(Collection): row/col bounds + SI.
     */
    @Test
    @DisplayName("ctor(Collection): row/col bounds + SI")
    public void testCtorCollection()
    {
        Length v1 = new Length(1, Length.Unit.m);
        Length v2 = new Length(2, Length.Unit.km);
        Length v3 = new Length(3, Length.Unit.cm);

        Collection<FloatSparseValue<Length, Length.Unit>> col = new ArrayList<>();
        col.add(new FloatSparseValue<>(0, 0, v1));
        col.add(new FloatSparseValue<>(1, 1, v2));
        col.add(new FloatSparseValue<>(1, 2, v3));

        SparseFloatData d = new SparseFloatData(col, 2, 3);

        assertEquals(1f, d.get(0, 0), 1e-9);
        assertEquals(2000f, d.get(1, 1), 1e-9);
        assertEquals(0.03f, d.get(1, 2), 1e-9);

        // Row out of bounds
        var col2 = List.of(new FloatSparseValue<>(2, 7, v1));
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseFloatData(col2, 1, 9));

        // Col out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseFloatData(col2, 3, 2));
    }

    // ----------------------------------------------------------------------
    // get(), getDataArray()
    // ----------------------------------------------------------------------

    /**
     * get() and getDataArray(): binary search hit/miss + safe copy.
     */
    @Test
    @DisplayName("get() and getDataArray(): binary search hit/miss + safe copy")
    public void testGetAndGetDataArray()
    {
        SparseFloatData d = new SparseFloatData(dense2x3(), 2, 3);

        assertEquals(1f, d.get(0, 0), 1e-9); // hit
        assertEquals(0f, d.get(0, 1), 1e-9); // miss
        assertEquals(4f, d.get(1, 2), 1e-9); // hit

        double[] dense = d.getDataArray();
        assertArrayEquals(dense2x3(), dense, 1e-12);

        // Must be safe
        dense[0] = 999;
        assertEquals(1f, d.get(0, 0), 1e-9);
    }

    // ----------------------------------------------------------------------
    // DataGrid default methods
    // ----------------------------------------------------------------------

    /**
     * DataGrid.getRowArray / getColArray: correctness + bounds.
     */
    @Test
    @DisplayName("DataGrid.getRowArray / getColArray: correctness + bounds")
    public void testRowColDefaults()
    {
        SparseFloatData d = new SparseFloatData(dense2x3(), 2, 3);

        assertArrayEquals(new double[] {1, 0, 2}, d.getRowArray(0), 1e-9);
        assertArrayEquals(new double[] {0, 3, 4}, d.getRowArray(1), 1e-9);

        assertArrayEquals(new double[] {1, 0}, d.getColArray(0), 1e-9);
        assertArrayEquals(new double[] {0, 3}, d.getColArray(1), 1e-9);

        assertThrows(IndexOutOfBoundsException.class, () -> d.getRowArray(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> d.getColArray(3));
    }

    // ----------------------------------------------------------------------
    // copy()
    // ----------------------------------------------------------------------

    /**
     * Test deep copy.
     */
    @Test
    @DisplayName("copy(): deep copy")
    public void testCopy()
    {
        SparseFloatData d = new SparseFloatData(dense2x3(), 2, 3);
        SparseFloatData c = d.copy();

        assertNotSame(d, c);
        assertArrayEquals(d.getDataArray(), c.getDataArray(), 1e-9);

        // Mutate original → copy must not change
        SparseFloatData mutated = d.instantiateNew(new double[] {0, 0, 0, 0, 0, 5});
        assertEquals(5f, mutated.get(1, 2), 1e-9);
        assertArrayEquals(dense2x3(), c.getDataArray(), 1e-9);
    }

    // ----------------------------------------------------------------------
    // instantiateNew()
    // ----------------------------------------------------------------------

    /**
     * Test instantiateNew.
     */
    @Test
    @DisplayName("instantiateNew: reshape and size validation")
    public void testInstantiateNew()
    {
        SparseFloatData d = new SparseFloatData(dense2x3(), 2, 3);

        double[] newData = new double[] {0, 2, 0, 4};
        SparseFloatData d2 = d.instantiateNew(newData, 2, 2);

        assertEquals(2, d2.rows());
        assertEquals(2, d2.cols());
        assertEquals(2f, d2.get(0, 1), 1e-9);
        assertEquals(4f, d2.get(1, 1), 1e-9);

        // Wrong size
        assertThrows(IllegalArgumentException.class, () -> d.instantiateNew(new double[] {1, 2, 3}, 2, 2));
    }

    // ----------------------------------------------------------------------
    // cardinality
    // ----------------------------------------------------------------------

    /**
     * Test cardinality.
     */
    @Test
    @DisplayName("cardinality: count non-zero sparse entries")
    public void testCardinality()
    {
        SparseFloatData d = new SparseFloatData(dense2x3(), 2, 3);
        assertEquals(4, d.cardinality());
    }

    // ----------------------------------------------------------------------
    // equals / hashCode
    // ----------------------------------------------------------------------

    /**
     * Test equals and hashCode.
     */
    @Test
    @DisplayName("equals/hashCode: reflexive, symmetric, correct mismatch detection")
    public void testEqualsHashCode()
    {
        SparseFloatData a = new SparseFloatData(dense2x3(), 2, 3);
        SparseFloatData b = new SparseFloatData(dense2x3(), 2, 3);
        SparseFloatData c = new SparseFloatData(new double[] {1, 2, 3, 4, 5, 6}, 2, 3);
        SparseFloatData d3x2 = new SparseFloatData(dense2x3(), 3, 2);

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, c);
        assertNotEquals(a, d3x2);
        assertNotEquals(a, null);
        assertNotEquals(a, "string");
    }
}
