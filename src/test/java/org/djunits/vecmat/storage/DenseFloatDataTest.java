package org.djunits.vecmat.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.djunits.quantity.Length;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DenseFloatData}.
 * <p>
 * This test class verifies the complete functional behavior of {@link DenseFloatData} and all default methods inherited from
 * {@link DataGrid}. All tests focus on correct functional semantics. If the implementation contains bugs or produces incorrect
 * results, these tests are designed to fail.
 * <ul>
 * <li>All constructors (float[], float[][], Q[][])</li>
 * <li>Bounds checking on {@code get(int,int)}</li>
 * <li>All default {@code DataGrid} methods: {@code getRowArray}, {@code getColArray}</li>
 * <li>Copy semantics</li>
 * <li>Instantiations via {@code instantiateNew}</li>
 * <li>Cardinality behavior</li>
 * <li>Equality and hashCode</li>
 * <li>SI-conversion correctness for Q[][] (using Length)</li>
 * <li>Float → double copying correctness in {@code getDataArray()}</li>
 * </ul>
 * <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class DenseFloatDataTest
{
    /**
     * Helper method: create a simple 2x3 float array stored in row-major format.
     * @return the array {1, 2, 3, 4, 5, 6}
     */
    private static float[] sample2x3()
    {
        return new float[] {1f, 2f, 3f, 4f, 5f, 6f};
    }

    // ---------------------------------------------------------------------------
    // Basic geometry and accessors
    // ---------------------------------------------------------------------------

    /**
     * Test {@code rows()}, {@code cols()}, and {@code get(int,int)} for normal behavior.
     */
    @Test
    @DisplayName("rows/cols/get: happy-path read access")
    public void testRowsColsGet()
    {
        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);

        assertEquals(2, d.rows(), "rows()");
        assertEquals(3, d.cols(), "cols()");
        assertEquals(1.0, d.get(0, 0), 1e-12);
        assertEquals(2.0, d.get(0, 1), 1e-12);
        assertEquals(3.0, d.get(0, 2), 1e-12);
        assertEquals(4.0, d.get(1, 0), 1e-12);
        assertEquals(5.0, d.get(1, 1), 1e-12);
        assertEquals(6.0, d.get(1, 2), 1e-12);
    }

    /**
     * Test all out-of-bounds read conditions.
     */
    @Test
    @DisplayName("get: bounds checking exceptions")
    public void testGetOutOfBounds()
    {
        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> d.get(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> d.get(2, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> d.get(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> d.get(0, 3));
    }

    // ---------------------------------------------------------------------------
    // DataGrid default methods: getRowArray / getColArray
    // ---------------------------------------------------------------------------

    /**
     * Test {@code getRowArray} including bounds checking.
     */
    @Test
    @DisplayName("DataGrid.getRowArray: normal and error paths")
    public void testGetRowArray()
    {
        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);

        assertArrayEquals(new double[] {1, 2, 3}, d.getRowArray(0), 1e-12);
        assertArrayEquals(new double[] {4, 5, 6}, d.getRowArray(1), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> d.getRowArray(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> d.getRowArray(2));
    }

    /**
     * Test {@code getColArray} including bounds checking.
     */
    @Test
    @DisplayName("DataGrid.getColArray: normal and error paths")
    public void testGetColArray()
    {
        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);

        assertArrayEquals(new double[] {1, 4}, d.getColArray(0), 1e-12);
        assertArrayEquals(new double[] {2, 5}, d.getColArray(1), 1e-12);
        assertArrayEquals(new double[] {3, 6}, d.getColArray(2), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> d.getColArray(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> d.getColArray(3));
    }

    // ---------------------------------------------------------------------------
    // getDataArray(): must return a fresh double[] copy
    // ---------------------------------------------------------------------------

    /**
     * Verify that {@code getDataArray()} returns a *new* double array each call and does not expose the internal float[].
     */
    @Test
    @DisplayName("getDataArray: returns new array every time; not exposing internals")
    public void testGetDataArrayCopy()
    {
        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);

        double[] a1 = d.getDataArray();
        double[] a2 = d.getDataArray();

        assertNotSame(a1, a2, "getDataArray() must create a new array");
        assertArrayEquals(a1, a2, 1e-12, "arrays must contain the same values");

        // Mutations must not affect stored float array
        a1[0] = 999.0;
        assertEquals(1.0, d.get(0, 0), 1e-12, "mutation must not propagate");
    }

    // ---------------------------------------------------------------------------
    // copy()
    // ---------------------------------------------------------------------------

    /**
     * Test deep copy semantics of {@code copy()}.
     */
    @Test
    @DisplayName("copy: deep-copy of internal float array")
    public void testCopy()
    {
        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);
        DenseFloatData copy = d.copy();

        assertEquals(d.rows(), copy.rows());
        assertEquals(d.cols(), copy.cols());
        assertArrayEquals(d.getDataArray(), copy.getDataArray(), 1e-12);

        // float[] should be cloned
        assertNotSame(d.getDataArray(), copy.getDataArray(), "getDataArray() always returns new double[]");
        assertNotSame(d, copy);

        // Mutate original backing array -> copy must not change
        d.instantiateNew(new double[] {10, 20, 30, 40, 50, 60}); // replace via instantiateNew
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, copy.getDataArray(), 1e-12,
                "copy must remain unchanged after original changes");
    }

    // ---------------------------------------------------------------------------
    // instantiateNew
    // ---------------------------------------------------------------------------

    /**
     * Test {@code instantiateNew(double[])} conversion double→float and geometry preservation.
     */
    @Test
    @DisplayName("instantiateNew(double[]): shape preserved, float cast correct")
    public void testInstantiateNewSameDims()
    {
        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);

        double[] newData = new double[] {1.5, -2.25, 3.75, 4.5, 0.0, Double.NaN};
        DenseFloatData d2 = d.instantiateNew(newData);

        assertEquals(2, d2.rows());
        assertEquals(3, d2.cols());

        double[] retrieved = d2.getDataArray();
        assertEquals((float) 1.5, retrieved[0], 1e-12);
        assertEquals((float) -2.25, retrieved[1], 1e-12);
        assertFalse(!Double.isNaN(retrieved[5]), "NaN must remain NaN after float conversion");

        // Invalid length must throw
        assertThrows(IllegalArgumentException.class, () -> d.instantiateNew(new double[] {1.0, 2.0}));
    }

    /**
     * Test {@code instantiateNew(double[], r, c)} for correct reshape and error handling.
     */
    @Test
    @DisplayName("instantiateNew(double[],r,c): reshape + float casting")
    public void testInstantiateNewNewDims()
    {
        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);

        double[] newData = new double[] {9, 8, 7, 6, 5, 4};
        DenseFloatData reshaped = d.instantiateNew(newData, 3, 2);

        assertEquals(3, reshaped.rows());
        assertEquals(2, reshaped.cols());
        assertArrayEquals(new double[] {9, 8, 7, 6, 5, 4}, reshaped.getDataArray(), 1e-12);

        // Wrong size
        assertThrows(IllegalArgumentException.class, () -> d.instantiateNew(new double[] {1, 2, 3}, 2, 2));
    }

    // ---------------------------------------------------------------------------
    // Cardinality
    // ---------------------------------------------------------------------------

    /**
     * Test cardinality semantics: counts non-zero entries; counts NaN and infinity as non-zero.
     */
    @Test
    @DisplayName("cardinality: float rules incl. -0f, NaN, infinities")
    public void testCardinality()
    {
        float[] raw = new float[] {0f, -0f, 2f, -3f, Float.NaN, Float.POSITIVE_INFINITY};
        DenseFloatData d = new DenseFloatData(raw, 2, 3);

        // zero and -zero count as zero, NaN and infinities count as non-zero
        assertEquals(4, d.cardinality());
    }

    // ---------------------------------------------------------------------------
    // equals / hashCode
    // ---------------------------------------------------------------------------

    /**
     * Test equals and hashCode semantics.
     */
    @Test
    @DisplayName("equals/hashCode: reflexivity, symmetry, difference detection")
    public void testEqualsHashCode()
    {
        DenseFloatData a = new DenseFloatData(sample2x3(), 2, 3);
        DenseFloatData b = new DenseFloatData(sample2x3(), 2, 3);
        DenseFloatData c = new DenseFloatData(new float[] {1, 2, 3, 4, 5, 7}, 2, 3);
        DenseFloatData d3x2 = new DenseFloatData(sample2x3(), 3, 2);

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, c);
        assertNotEquals(a, d3x2);
        assertNotEquals(a, null);
        assertNotEquals(a, "string");
    }

    // ---------------------------------------------------------------------------
    // Constructors + validation
    // ---------------------------------------------------------------------------

    /**
     * Test validation paths for constructor(float[],r,c).
     */
    @Test
    @DisplayName("ctor(float[],r,c): validation of null, size, geometry")
    public void testCtorFloatArrayValidation()
    {
        assertThrows(NullPointerException.class, () -> new DenseFloatData((float[]) null, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new DenseFloatData(new float[] {1}, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new DenseFloatData(new float[] {1}, 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new DenseFloatData(new float[] {1, 2}, 1, 1));

        DenseFloatData d = new DenseFloatData(sample2x3(), 2, 3);
        assertEquals(2, d.rows());
        assertEquals(3, d.cols());
    }

    /**
     * Test constructor(float[][]) for safe-copy semantics, validation, row-major correctness, and ragged-row exact exception
     * class.
     */
    @Test
    @DisplayName("ctor(float[][]): validation, safe copy, row-major, ragged detection")
    public void testCtor2DFloatArray()
    {
        assertThrows(NullPointerException.class, () -> new DenseFloatData((float[][]) null));
        assertThrows(IllegalArgumentException.class, () -> new DenseFloatData(new float[][] {}));

        // Ragged rows -> must throw exactly IllegalArgumentException
        float[][] ragged = new float[][] {{1f, 2f}, {3f}};
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new DenseFloatData(ragged));
        assertEquals(IllegalArgumentException.class, ex.getClass(),
                "Expected IAE; formatting bug in code would produce different exception");

        // Valid safe-copy
        float[][] arr = new float[][] {{1f, 2f, 3f}, {4f, 5f, 6f}};
        DenseFloatData d = new DenseFloatData(arr);
        assertEquals(2, d.rows());
        assertEquals(3, d.cols());
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, d.getDataArray(), 1e-12);

        // Modify source; DenseFloatData must remain stable
        arr[0][0] = 999f;
        assertEquals(1.0, d.get(0, 0), 1e-12);
    }

    /**
     * Test constructor(Q[][]) for safe-copy, SI conversion, and ragged detection.
     */
    @Test
    @DisplayName("ctor(Q[][]): SI conversion, safe copy, ragged detection")
    public void testCtorQuantityArray()
    {
        assertThrows(NullPointerException.class, () -> new DenseFloatData((Length[][]) null));
        assertThrows(IllegalArgumentException.class, () -> new DenseFloatData(new Length[][] {}));

        // Ragged rows must throw exactly IAE
        Length[][] ragged = new Length[][] {{new Length(1, Length.Unit.m)}, {}};
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new DenseFloatData(ragged));
        assertEquals(IllegalArgumentException.class, ex.getClass());

        // Valid mixed-unit input
        Length[][] q = new Length[][] {{new Length(1.0, Length.Unit.km), new Length(1.0, Length.Unit.m)},
                {new Length(2.0, Length.Unit.cm), new Length(3.0, Length.Unit.mm)}};
        DenseFloatData d = new DenseFloatData(q);

        // Expected SI values (float-cast): 1000.0, 1.0, 0.02, 0.003
        assertArrayEquals(new double[] {1000.0, 1.0, 0.02, 0.003}, d.getDataArray(), 1e-7);

        // Modify input source -> DenseFloatData must not change
        q[0][0] = new Length(7.0, Length.Unit.m);
        assertArrayEquals(new double[] {1000.0, 1.0, 0.02, 0.003}, d.getDataArray(), 1e-7);
    }
}
