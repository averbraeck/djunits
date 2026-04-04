package org.djunits.vecmat.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.djunits.quantity.Length;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DenseDoubleDataSi}.
 * <p>
 * This test class verifies the functional behavior of {@link DenseDoubleDataSi} and the default methods in the
 * {@link DataGridSi} interface:
 * <ul>
 * <li>Constructors: double[], double[][], and Q[][] (SI values)</li>
 * <li>Accessors: {@code rows()}, {@code cols()}, {@code get(int,int)}, {@code getDataArray()}</li>
 * <li>Default methods in {@link DataGridSi}: {@code getRowArray(int)}, {@code getColArray(int)} including bounds checks</li>
 * <li>Copy and instantiation: {@code copy()}, {@code instantiateNew(double[])}, {@code instantiateNew(double[],int,int)}</li>
 * <li>Cardinality semantics, including 0.0, -0.0, {@code NaN}, and infinities</li>
 * <li>Equality and hash code contracts</li>
 * <li>All branches and all exception paths</li>
 * </ul>
 * <p>
 * Tests assert functional intent; if the implementation is incorrect, these tests will fail by design. <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class DenseDoubleDataSiTest
{
    /**
     * Create a sample dense row-major array for a 2x3 matrix.
     * @return a new double array with values {1, 2, 3, 4, 5, 6}
     */
    private static double[] sample2x3()
    {
        return new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
    }

    /**
     * Verify basic getters {@code rows()}, {@code cols()}, and {@code get(int,int)} on a simple 2x3 matrix.
     */
    @Test
    @DisplayName("rows/cols/get: happy path for 2x3")
    public void testRowsColsAndGet()
    {
        double[] data = sample2x3();
        DenseDoubleDataSi d = new DenseDoubleDataSi(data, 2, 3);

        assertEquals(2, d.rows(), "rows()");
        assertEquals(3, d.cols(), "cols()");
        assertEquals(1.0, d.get(0, 0), 1e-12, "get(0,0)");
        assertEquals(2.0, d.get(0, 1), 1e-12, "get(0,1)");
        assertEquals(3.0, d.get(0, 2), 1e-12, "get(0,2)");
        assertEquals(4.0, d.get(1, 0), 1e-12, "get(1,0)");
        assertEquals(5.0, d.get(1, 1), 1e-12, "get(1,1)");
        assertEquals(6.0, d.get(1, 2), 1e-12, "get(1,2)");
    }

    /**
     * Verify that {@code get(int,int)} throws for all out-of-bounds row/col combinations.
     */
    @Test
    @DisplayName("get: index bounds checking")
    public void testGetOutOfBounds()
    {
        DenseDoubleDataSi d = new DenseDoubleDataSi(sample2x3(), 2, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> d.get(-1, 0), "row < 0 should throw");
        assertThrows(IndexOutOfBoundsException.class, () -> d.get(2, 0), "row >= rows should throw");
        assertThrows(IndexOutOfBoundsException.class, () -> d.get(0, -1), "col < 0 should throw");
        assertThrows(IndexOutOfBoundsException.class, () -> d.get(0, 3), "col >= cols should throw");
    }

    /**
     * Verify that {@link DataGridSi#getRowArray(int)} returns a dense copy with correct values and enforces bounds.
     */
    @Test
    @DisplayName("DataGrid.getRowArray: happy path + bounds")
    public void testGetRowArray()
    {
        DenseDoubleDataSi d = new DenseDoubleDataSi(sample2x3(), 2, 3);
        assertArrayEquals(new double[] {1.0, 2.0, 3.0}, d.getRowArray(0), 1e-12, "row 0");
        assertArrayEquals(new double[] {4.0, 5.0, 6.0}, d.getRowArray(1), 1e-12, "row 1");

        assertThrows(IndexOutOfBoundsException.class, () -> d.getRowArray(-1), "row < 0 should throw");
        assertThrows(IndexOutOfBoundsException.class, () -> d.getRowArray(2), "row >= rows should throw");
    }

    /**
     * Verify that {@link DataGridSi#getColArray(int)} returns a dense copy with correct values and enforces bounds.
     */
    @Test
    @DisplayName("DataGrid.getColArray: happy path + bounds")
    public void testGetColArray()
    {
        DenseDoubleDataSi d = new DenseDoubleDataSi(sample2x3(), 2, 3);
        assertArrayEquals(new double[] {1.0, 4.0}, d.getColArray(0), 1e-12, "col 0");
        assertArrayEquals(new double[] {2.0, 5.0}, d.getColArray(1), 1e-12, "col 1");
        assertArrayEquals(new double[] {3.0, 6.0}, d.getColArray(2), 1e-12, "col 2");

        assertThrows(IndexOutOfBoundsException.class, () -> d.getColArray(-1), "col < 0 should throw");
        assertThrows(IndexOutOfBoundsException.class, () -> d.getColArray(3), "col >= cols should throw");
    }

    /**
     * Verify exposure semantics of {@link DenseDoubleDataSi#getDataArray()} (no safe copy). Modifications through the returned
     * array are reflected in the object, by design.
     */
    @Test
    @DisplayName("getDataArray: exposes internal array (by design)")
    public void testGetDataArrayExposure()
    {
        double[] data = sample2x3();
        DenseDoubleDataSi d = new DenseDoubleDataSi(data, 2, 3);

        // Same reference is returned (no safe copy)
        assertSame(data, d.getDataArray(), "Internal array should be exposed by design");

        // Mutating via getDataArray() must mutate the object state
        double[] exposed = d.getDataArray();
        exposed[0] = 10.0;
        assertEquals(10.0, d.get(0, 0), 1e-12, "mutation through exposed array is visible");
    }

    /**
     * Verify {@link DenseDoubleDataSi#copy()} creates a deep copy (independent backing array).
     */
    @Test
    @DisplayName("copy: deep copy of data and identical geometry")
    public void testCopy()
    {
        DenseDoubleDataSi original = new DenseDoubleDataSi(sample2x3(), 2, 3);
        DenseDoubleDataSi copy = original.copy();

        assertEquals(original.rows(), copy.rows(), "rows identical");
        assertEquals(original.cols(), copy.cols(), "cols identical");
        assertArrayEquals(original.getDataArray(), copy.getDataArray(), 1e-12, "data identical");
        assertNotSame(original.getDataArray(), copy.getDataArray(), "backing arrays must differ");

        // Mutations to the original must not affect the copy
        original.getDataArray()[0] = 99.0;
        assertEquals(99.0, original.get(0, 0), 1e-12, "original mutated");
        assertEquals(1.0, copy.get(0, 0), 1e-12, "copy isolated");
    }

    /**
     * Verify {@link DenseDoubleDataSi#instantiateNew(double[])} returns a new instance with same shape and exposed backing.
     */
    @Test
    @DisplayName("instantiateNew(double[]): shape preserved, backing exposed")
    public void testInstantiateNewSameDims()
    {
        DenseDoubleDataSi d = new DenseDoubleDataSi(sample2x3(), 2, 3);

        double[] other = new double[] {7.0, 8.0, 9.0, 10.0, -0.0, Double.NaN};
        DenseDoubleDataSi d2 = d.instantiateNew(other);

        assertEquals(2, d2.rows(), "rows preserved");
        assertEquals(3, d2.cols(), "cols preserved");
        assertSame(other, d2.getDataArray(), "constructor exposes provided array");
        assertEquals(7.0, d2.get(0, 0), 1e-12);
        assertEquals(8.0, d2.get(0, 1), 1e-12);
        assertEquals(9.0, d2.get(0, 2), 1e-12);
        assertEquals(10.0, d2.get(1, 0), 1e-12);
        assertEquals(-0.0, d2.get(1, 1), 1e-12);
        assertFalse(!Double.isNaN(d2.get(1, 2)), "last element should be NaN");
    }

    /**
     * Verify {@link DenseDoubleDataSi#instantiateNew(double[], int, int)} for new shape and argument validation.
     */
    @Test
    @DisplayName("instantiateNew(double[], r, c): new shape and length checks")
    public void testInstantiateNewNewDims()
    {
        DenseDoubleDataSi d = new DenseDoubleDataSi(sample2x3(), 2, 3);

        // Valid reshape to 3x2 with new content
        double[] data3x2 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        DenseDoubleDataSi reshaped = d.instantiateNew(data3x2, 3, 2);
        assertEquals(3, reshaped.rows());
        assertEquals(2, reshaped.cols());
        assertSame(data3x2, reshaped.getDataArray(), "backing exposed (by design)");
        assertEquals(1.0, reshaped.get(0, 0), 1e-12);
        assertEquals(2.0, reshaped.get(0, 1), 1e-12);
        assertEquals(5.0, reshaped.get(2, 0), 1e-12);
        assertEquals(6.0, reshaped.get(2, 1), 1e-12);

        // Invalid length must throw
        double[] bad = new double[] {1.0, 2.0, 3.0};
        assertThrows(IllegalArgumentException.class, () -> d.instantiateNew(bad, 2, 2),
                "newData.length != newRows * newCols should throw");
    }

    /**
     * Verify {@link DenseDoubleDataSi#nonZeroCount()} semantics: counts non-zero entries (treats +/-0.0 as zero; counts NaN and
     * infinities as non-zero).
     */
    @Test
    @DisplayName("cardinality: zero, -zero, NaN, +/-infinity")
    public void testCardinality()
    {
        double[] payload = new double[] {0.0, -0.0, 1.0, -2.0, Double.NaN, Double.POSITIVE_INFINITY};
        DenseDoubleDataSi d = new DenseDoubleDataSi(payload, 2, 3);

        // 0.0 -> zero, -0.0 -> zero, 1.0 -> non-zero, -2.0 -> non-zero, NaN -> non-zero, +inf -> non-zero
        assertEquals(4, d.nonZeroCount(), "cardinality should count non-zeros incl. NaN and infinities");
    }

    /**
     * Verify equality and hash code contracts.
     */
    @Test
    @DisplayName("equals/hashCode: reflexive, symmetric, and data/shape sensitive")
    public void testEqualsHashCode()
    {
        DenseDoubleDataSi a = new DenseDoubleDataSi(sample2x3(), 2, 3);
        DenseDoubleDataSi b = new DenseDoubleDataSi(sample2x3(), 2, 3);
        DenseDoubleDataSi c = new DenseDoubleDataSi(new double[] {1, 2, 3, 4, 5, 7}, 2, 3);
        DenseDoubleDataSi d3x2 = new DenseDoubleDataSi(sample2x3(), 3, 2);

        // Reflexive
        assertEquals(a, a, "reflexive");

        // Symmetric and value-based
        assertEquals(a, b, "same content and shape -> equals");
        assertEquals(a.hashCode(), b.hashCode(), "equal objects must have equal hash codes");

        // Different data
        assertNotEquals(a, c, "different content -> not equals");

        // Different shape
        assertNotEquals(a, d3x2, "different shape -> not equals");

        // Null and different class
        assertNotEquals(a, null, "not equal to null");
        assertNotEquals(a, "string", "not equal to other type");
    }

    /**
     * Verify constructor with {@code double[]} validates inputs and sets geometry and backing reference as specified (no safe
     * copy).
     */
    @Test
    @DisplayName("ctor(double[], r, c): validation and exposure semantics")
    public void testCtorDoubleArrayValidationAndExposure()
    {
        // Null data -> NullPointerException (from Throw.whenNull)
        assertThrows(NullPointerException.class, () -> new DenseDoubleDataSi((double[]) null, 1, 1));

        // rows <= 0
        assertThrows(IllegalArgumentException.class, () -> new DenseDoubleDataSi(new double[] {1.0}, 0, 1));
        // cols <= 0
        assertThrows(IllegalArgumentException.class, () -> new DenseDoubleDataSi(new double[] {1.0}, 1, 0));
        // length mismatch
        assertThrows(IllegalArgumentException.class, () -> new DenseDoubleDataSi(new double[] {1.0, 2.0}, 1, 1));

        // Happy path
        double[] payload = sample2x3();
        DenseDoubleDataSi d = new DenseDoubleDataSi(payload, 2, 3);
        assertSame(payload, d.getDataArray(), "no safe copy on ctor(double[])");
        assertEquals(2, d.rows());
        assertEquals(3, d.cols());
    }

    /**
     * Verify constructor with {@code double[][]} validates inputs, performs a safe copy, and preserves row-major ordering.
     * Additionally, assert the exact exception type for ragged rows to surface the current format-string bug.
     */
    @Test
    @DisplayName("ctor(double[][]): validation, safe copy, row-major, ragged exact-exception")
    public void testCtor2DArrayValidationCopyingAndOrder()
    {
        // Null -> NPE (from Throw.whenNull)
        assertThrows(NullPointerException.class, () -> DenseDoubleDataSi.ofSi((double[][]) null));

        // Empty outer array -> IAE
        assertThrows(IllegalArgumentException.class, () -> DenseDoubleDataSi.ofSi(new double[][] {}));

        // Ragged array -> should throw exactly IllegalArgumentException (not a subclass)
        double[][] ragged = new double[][] {{1.0, 2.0}, {3.0}};
        Exception raggedEx = assertThrows(IllegalArgumentException.class, () -> DenseDoubleDataSi.ofSi(ragged));
        assertEquals(IllegalArgumentException.class, raggedEx.getClass(),
                "Ragged rows must throw exactly IllegalArgumentException (format bug would cause a different type)");

        // Happy path; verify safe copy and row-major
        double[][] m = new double[][] {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        DenseDoubleDataSi d = DenseDoubleDataSi.ofSi(m);
        assertEquals(2, d.rows());
        assertEquals(3, d.cols());

        // Must be row-major: [1,2,3, 4,5,6]
        assertArrayEquals(sample2x3(), d.getDataArray(), 1e-12, "row-major order");

        // Safe copy: mutate source matrix; DenseDoubleDataSi must remain unchanged
        m[0][0] = 999.0;
        assertEquals(1.0, d.get(0, 0), 1e-12, "safe copy on ctor(double[][])");
    }

    /**
     * Verify constructor with {@code Q[][]} validates inputs, performs a safe copy, and writes SI values; unit conversions must
     * be respected (e.g., km to m, cm to m). Additionally, assert the exact exception type for ragged rows to surface the
     * current format-string bug.
     */
    @Test
    @DisplayName("ctor(Q[][]): validation, safe copy, SI conversion, ragged exact-exception")
    public void testCtorQuantity2DArraySIConversion()
    {
        // Null -> NPE
        assertThrows(NullPointerException.class, () -> DenseDoubleDataSi.of((Length[][]) null));

        // Empty -> IAE
        assertThrows(IllegalArgumentException.class, () -> DenseDoubleDataSi.of(new Length[][] {}));

        // Ragged -> should throw exactly IllegalArgumentException (not a subclass)
        Length[][] ragged = new Length[][] {{new Length(1.0, Length.Unit.m)}, {}};
        Exception raggedEx = assertThrows(IllegalArgumentException.class, () -> DenseDoubleDataSi.of(ragged));
        assertEquals(IllegalArgumentException.class, raggedEx.getClass(),
                "Ragged rows must throw exactly IllegalArgumentException (format bug would cause a different type)");

        // Happy path with mixed units; SI values are meters
        Length[][] l = new Length[][] {{new Length(1.0, Length.Unit.km), new Length(2.0, Length.Unit.m)},
                {new Length(3.0, Length.Unit.cm), new Length(4.0, Length.Unit.mm)}};
        DenseDoubleDataSi d = DenseDoubleDataSi.of(l);

        // Expected SI (meters): [1000.0, 2.0, 0.03, 0.004]
        double[] expected = new double[] {1000.0, 2.0, 0.03, 0.004};
        assertEquals(2, d.rows());
        assertEquals(2, d.cols());
        assertArrayEquals(expected, d.getDataArray(), 1e-12, "SI values must be written in row-major order");

        // Safe copy: mutate source lengths; result must remain unchanged
        l[0][0] = new Length(42.0, Length.Unit.m);
        assertArrayEquals(expected, d.getDataArray(), 1e-12, "safe copy on ctor(Q[][])");
    }

}
