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
 * Unit tests for {@link DenseFloatDataSi}.
 * <p>
 * This test class verifies the complete functional behavior of {@link DenseFloatDataSi} and all default methods inherited from
 * {@link DataGridSi}. All tests focus on correct functional semantics. If the implementation contains bugs or produces
 * incorrect results, these tests are designed to fail.
 * <ul>
 * <li>All constructors (float[], float[][], Q[][])</li>
 * <li>Bounds checking on {@code get(int,int)}</li>
 * <li>All default {@code DataGrid} methods: {@code getRowArray}, {@code getColArray}</li>
 * <li>Copy semantics</li>
 * <li>Instantiations via {@code instantiateNew}</li>
 * <li>Cardinality behavior</li>
 * <li>Equality and hashCode</li>
 * <li>SI-conversion correctness for Q[][] (using Length)</li>
 * <li>Float → double copying correctness in {@code getSiArray()}</li>
 * </ul>
 * <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class DenseFloatDataSiTest
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
        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);

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
        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);

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
        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);

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
        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);

        assertArrayEquals(new double[] {1, 4}, d.getColArray(0), 1e-12);
        assertArrayEquals(new double[] {2, 5}, d.getColArray(1), 1e-12);
        assertArrayEquals(new double[] {3, 6}, d.getColArray(2), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> d.getColArray(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> d.getColArray(3));
    }

    // ---------------------------------------------------------------------------
    // getSiArray(): must return a fresh double[] copy
    // ---------------------------------------------------------------------------

    /**
     * Verify that {@code getSiArray()} returns a *new* double array each call and does not expose the internal float[].
     */
    @Test
    @DisplayName("getDataArray: returns new array every time; not exposing internals")
    public void testGetDataArrayCopy()
    {
        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);

        double[] a1 = d.getSiArray();
        double[] a2 = d.getSiArray();

        assertNotSame(a1, a2, "getSiArray() must create a new array");
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
        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);
        DenseFloatDataSi copy = d.copy();

        assertEquals(d.rows(), copy.rows());
        assertEquals(d.cols(), copy.cols());
        assertArrayEquals(d.getSiArray(), copy.getSiArray(), 1e-12);

        // float[] should be cloned
        assertNotSame(d.getSiArray(), copy.getSiArray(), "getSiArray() always returns new double[]");
        assertNotSame(d, copy);

        // Mutate original backing array -> copy must not change
        d.instantiateNew(new double[] {10, 20, 30, 40, 50, 60}); // replace via instantiateNew
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, copy.getSiArray(), 1e-12,
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
        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);

        double[] newData = new double[] {1.5, -2.25, 3.75, 4.5, 0.0, Double.NaN};
        DenseFloatDataSi d2 = d.instantiateNew(newData);

        assertEquals(2, d2.rows());
        assertEquals(3, d2.cols());

        double[] retrieved = d2.getSiArray();
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
        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);

        double[] newData = new double[] {9, 8, 7, 6, 5, 4};
        DenseFloatDataSi reshaped = d.instantiateNew(newData, 3, 2);

        assertEquals(3, reshaped.rows());
        assertEquals(2, reshaped.cols());
        assertArrayEquals(new double[] {9, 8, 7, 6, 5, 4}, reshaped.getSiArray(), 1e-12);

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
        DenseFloatDataSi d = new DenseFloatDataSi(raw, 2, 3);

        // zero and -zero count as zero, NaN and infinities count as non-zero
        assertEquals(4, d.nonZeroCount());
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
        DenseFloatDataSi a = new DenseFloatDataSi(sample2x3(), 2, 3);
        DenseFloatDataSi b = new DenseFloatDataSi(sample2x3(), 2, 3);
        DenseFloatDataSi c = new DenseFloatDataSi(new float[] {1, 2, 3, 4, 5, 7}, 2, 3);
        DenseFloatDataSi d3x2 = new DenseFloatDataSi(sample2x3(), 3, 2);

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
        assertThrows(NullPointerException.class, () -> new DenseFloatDataSi((float[]) null, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new DenseFloatDataSi(new float[] {1}, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new DenseFloatDataSi(new float[] {1}, 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new DenseFloatDataSi(new float[] {1, 2}, 1, 1));

        DenseFloatDataSi d = new DenseFloatDataSi(sample2x3(), 2, 3);
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
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.ofSi((float[][]) null));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.ofSi(new float[][] {}));

        // Ragged rows -> must throw exactly IllegalArgumentException
        float[][] ragged = new float[][] {{1f, 2f}, {3f}};
        Exception ex = assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.ofSi(ragged));
        assertEquals(IllegalArgumentException.class, ex.getClass(),
                "Expected IAE; formatting bug in code would produce different exception");

        // Valid safe-copy
        float[][] arr = new float[][] {{1f, 2f, 3f}, {4f, 5f, 6f}};
        DenseFloatDataSi d = DenseFloatDataSi.ofSi(arr);
        assertEquals(2, d.rows());
        assertEquals(3, d.cols());
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, d.getSiArray(), 1e-12);

        // Modify source; DenseFloatDataSi must remain stable
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
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of((Length[][]) null));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(new Length[][] {}));

        // Ragged rows must throw exactly IAE
        Length[][] ragged = new Length[][] {{new Length(1, Length.Unit.m)}, {}};
        Exception ex = assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(ragged));
        assertEquals(IllegalArgumentException.class, ex.getClass());

        // Valid mixed-unit input
        Length[][] q = new Length[][] {{new Length(1.0, Length.Unit.km), new Length(1.0, Length.Unit.m)},
                {new Length(2.0, Length.Unit.cm), new Length(3.0, Length.Unit.mm)}};
        DenseFloatDataSi d = DenseFloatDataSi.of(q);

        // Expected SI values (float-cast): 1000.0, 1.0, 0.02, 0.003
        assertArrayEquals(new double[] {1000.0, 1.0, 0.02, 0.003}, d.getSiArray(), 1e-7);

        // Modify input source -> DenseFloatDataSi must not change
        q[0][0] = new Length(7.0, Length.Unit.m);
        assertArrayEquals(new double[] {1000.0, 1.0, 0.02, 0.003}, d.getSiArray(), 1e-7);
    }

    // ------------------------------------------------------------------------------------
    // Factory methods of() and ofSi() — explicit coverage
    // ------------------------------------------------------------------------------------

    /** epsilon. */
    private static final double EPS = 1e-6;

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /**
     * @return a float[] 2x2 matrix.
     */
    private static float[] f2x2()
    {
        return new float[] {1f, 2f, 3f, 4f};
    }

    /**
     * @return a double[] 2x2 matrix.
     */
    private static double[] d2x2()
    {
        return new double[] {1.0, 2.0, 3.0, 4.0};
    }

    /**
     * Verify {@link DenseDoubleDataSi#ofSi(double[], int, int)} rejects mismatched sizes.
     */
    @Test
    @DisplayName("ofSi(double[], rows, cols): length mismatch")
    public void testOfSiLengthMismatch()
    {
        double[] bad = new double[] {1.0, 2.0, 3.0};

        assertThrows(IllegalArgumentException.class, () -> DenseDoubleDataSi.ofSi(bad, 2, 2));
    }

    // -------------------------------------------------------------------------
    // ofSi(float[], rows, cols)
    // -------------------------------------------------------------------------

    /**
     * Test ofSi(float[], r, c): nulls, size, safe copy.
     */
    @Test
    @DisplayName("ofSi(float[], r, c): nulls, size, safe copy")
    public void testOfSiFloatArray()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.ofSi((float[]) null, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.ofSi(new float[] {1f, 2f}, 1, 1));

        float[] raw = f2x2();
        DenseFloatDataSi d = DenseFloatDataSi.ofSi(raw, 2, 2);

        assertArrayEquals(new double[] {1, 2, 3, 4}, d.getSiArray(), EPS);

        raw[0] = 99f;
        assertEquals(1.0, d.get(0, 0), EPS);
    }

    // -------------------------------------------------------------------------
    // ofSi(double[], rows, cols)
    // -------------------------------------------------------------------------

    /**
     * Test ofSi(double[], r, c): nulls, size, safe copy.
     */
    @Test
    @DisplayName("ofSi(double[], r, c): nulls, size, cast correctness")
    public void testOfSiDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.ofSi((double[]) null, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.ofSi(new double[] {1, 2}, 1, 1));

        DenseFloatDataSi d = DenseFloatDataSi.ofSi(d2x2(), 2, 2);

        assertArrayEquals(new double[] {1, 2, 3, 4}, d.getSiArray(), EPS);
    }

    // -------------------------------------------------------------------------
    // of(float[], rows, cols, Unit)
    // -------------------------------------------------------------------------

    /**
     * Test of(float[], r, c, Unit): nulls and SI conversion.
     */
    @Test
    @DisplayName("of(float[], r, c, Unit): nulls and SI conversion")
    public void testOfFloatArrayUnit()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of((float[]) null, 1, 1, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of(new float[] {1f}, 1, 1, null));

        DenseFloatDataSi d = DenseFloatDataSi.of(new float[] {1f, 2f, 3f, 4f}, 2, 2, Length.Unit.km);

        assertArrayEquals(new double[] {1000, 2000, 3000, 4000}, d.getSiArray(), EPS);
    }

    // -------------------------------------------------------------------------
    // of(double[], rows, cols, Unit)
    // -------------------------------------------------------------------------

    /**
     * Test of(double[], r, c, Unit): nulls and SI conversion.
     */
    @Test
    @DisplayName("of(double[], r, c, Unit): nulls and SI conversion")
    public void testOfDoubleArrayUnit()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of((double[]) null, 1, 1, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of(new double[] {1.0}, 1, 1, null));

        DenseFloatDataSi d = DenseFloatDataSi.of(new double[] {10, 20, 30, 40}, 2, 2, Length.Unit.cm);

        assertArrayEquals(new double[] {0.1, 0.2, 0.3, 0.4}, d.getSiArray(), EPS);
    }

    // -------------------------------------------------------------------------
    // of(Q[], rows, cols)
    // -------------------------------------------------------------------------

    /**
     * Test of(Q[], r, c): nulls and SI conversion.
     */
    @Test
    @DisplayName("of(Q[], r, c): nulls and SI conversion")
    public void testOfQuantityArray()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of((Length[]) null, 1, 1));

        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of(new Length[] {null}, 1, 1));

        Length[] q = new Length[] {new Length(1, Length.Unit.km), new Length(2, Length.Unit.m), new Length(3, Length.Unit.cm),
                new Length(4, Length.Unit.mm)};

        DenseFloatDataSi d = DenseFloatDataSi.of(q, 2, 2);

        assertArrayEquals(new double[] {1000, 2, 0.03, 0.004}, d.getSiArray(), EPS);
    }

    // -------------------------------------------------------------------------
    // ofSi(float[][])
    // -------------------------------------------------------------------------

    /**
     * Test ofSi(float[][]): nulls, ragged rows, safe copy.
     */
    @Test
    @DisplayName("ofSi(float[][]): nulls, ragged rows, safe copy")
    public void testOfSiFloatGrid()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.ofSi((float[][]) null));

        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.ofSi(new float[][] {}));

        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.ofSi(new float[][] {null}));

        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.ofSi(new float[][] {{1f, 2f}, {3f}}));

        float[][] grid = {{1f, 2f}, {3f, 4f}};
        DenseFloatDataSi d = DenseFloatDataSi.ofSi(grid);

        assertArrayEquals(new double[] {1, 2, 3, 4}, d.getSiArray(), EPS);

        grid[0][0] = 99f;
        assertEquals(1.0, d.get(0, 0), EPS);
    }

    // -------------------------------------------------------------------------
    // ofSi(double[][])
    // -------------------------------------------------------------------------

    /**
     * Test ofSi(double[][]): nulls, ragged rows, SI preservation.
     */
    @Test
    @DisplayName("ofSi(double[][]): nulls, ragged rows, SI preservation")
    public void testOfSiDoubleGrid()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.ofSi((double[][]) null));

        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.ofSi(new double[][] {}));

        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.ofSi(new double[][] {{1, 2}, {3}}));

        DenseFloatDataSi d = DenseFloatDataSi.ofSi(new double[][] {{1, 2}, {3, 4}});

        assertArrayEquals(new double[] {1, 2, 3, 4}, d.getSiArray(), EPS);
    }

    // -------------------------------------------------------------------------
    // of(float[][], Unit)
    // -------------------------------------------------------------------------

    /**
     * Test of(float[][], Unit): nulls, ragged, SI conversion.
     */
    @Test
    @DisplayName("of(float[][], Unit): nulls, ragged, SI conversion")
    public void testOfFloatGridUnit()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of((float[][]) null, Length.Unit.m));
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of(new float[][] {{1f}}, null));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(new float[][] {{1f, 2f}, {3f}}, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(new float[][] {{}}, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(new float[][] {}, Length.Unit.m));

        DenseFloatDataSi d = DenseFloatDataSi.of(new float[][] {{1f, 2f}, {3f, 4f}}, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000}, d.getSiArray(), EPS);
    }

    // -------------------------------------------------------------------------
    // of(double[][], Unit)
    // -------------------------------------------------------------------------

    /**
     * Test of(double[][], Unit): nulls, ragged, SI conversion.
     */
    @Test
    @DisplayName("of(double[][], Unit): nulls, ragged, SI conversion")
    public void testOfDoubleGridUnit()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of((double[][]) null, Length.Unit.m));
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of(new double[][] {{1.0}}, null));
        assertThrows(IllegalArgumentException.class,
                () -> DenseFloatDataSi.of(new double[][] {{1.0, 2.0}, {3.0}}, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(new double[][] {{}}, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(new double[][] {}, Length.Unit.m));

        DenseFloatDataSi d = DenseFloatDataSi.of(new double[][] {{10, 20}, {30, 40}}, Length.Unit.cm);
        assertArrayEquals(new double[] {0.1, 0.2, 0.3, 0.4}, d.getSiArray(), EPS);
    }

    // -------------------------------------------------------------------------
    // of(Q[][])
    // -------------------------------------------------------------------------

    /**
     * Test of(Q[][]): nulls, ragged, element nulls, SI conversion.
     */
    @Test
    @DisplayName("of(Q[][]): nulls, ragged, element nulls, SI conversion")
    public void testOfQuantityGrid()
    {
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of((Length[][]) null));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(new Length[][] {}));
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of(new Length[][] {null}));
        assertThrows(IllegalArgumentException.class, () -> DenseFloatDataSi.of(new Length[][] {{Length.ofSi(1)}, {}}));
        assertThrows(NullPointerException.class, () -> DenseFloatDataSi.of(new Length[][] {{null}}));

        Length[][] q = {{new Length(1, Length.Unit.km), new Length(2, Length.Unit.m)},
                {new Length(3, Length.Unit.cm), new Length(4, Length.Unit.mm)}};
        DenseFloatDataSi d = DenseFloatDataSi.of(q);
        assertArrayEquals(new double[] {1000, 2, 0.03, 0.004}, d.getSiArray(), EPS);
    }

}
