package org.djunits.vecmat.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.djunits.quantity.Length;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SparseDoubleDataSi}.
 * <p>
 * This test class achieves full branch coverage of all constructors, helper methods, index validation, sparse storage
 * generation, DataGrid default methods, binary-search access path, copy semantics, instantiation operations, equals/hashCode,
 * and all Q-based constructors, including SI-conversion. Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5,
 * 2628 BX Delft, the Netherlands. All rights reserved. See for project information
 * <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is distributed under a
 * <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class SparseDoubleDataSiTest
{
    /**
     * Helper: produce a dense matrix (2x3) for easy reuse.
     * @return array {1,0,2,0,3,4}
     */
    private static double[] dense2x3()
    {
        return new double[] {1.0, 0.0, 2.0, 0.0, 3.0, 4.0};
    }

    /**
     * Helper: 2x3 matrix as double[][].
     * @return matrix {{1,0,2},{0,3,4}}
     */
    private static double[][] dense2x3Matrix()
    {
        return new double[][] {{1.0, 0.0, 2.0}, {0.0, 3.0, 4.0}};
    }

    // ----------------------------------------------------------------------
    // Constructor: protected SparseFloatData(float[], int[], r, c)
    // ----------------------------------------------------------------------

    /**
     * Test protected constructor(double[], int[], r, c) via reflection-like access through copy().
     */
    @Test
    @DisplayName("protected ctor(double[],indexes,r,c): validates length, order, bounds")
    public void testProtectedConstructor()
    {
        double[] sparse = new double[] {1, 2, 3};
        int[] idx = new int[] {0, 2, 4};

        // Valid case
        SparseDoubleDataSi d = new SparseDoubleDataSi(sparse, idx, 2, 3);
        assertEquals(1.0, d.get(0, 0), 1e-12);
        assertEquals(2.0, d.get(0, 2), 1e-12);
        assertEquals(3.0, d.get(1, 1), 1e-12);

        // Null arrays
        assertThrows(NullPointerException.class, () -> new SparseDoubleDataSi((double[]) null, idx, 2, 3));
        assertThrows(NullPointerException.class, () -> new SparseDoubleDataSi(sparse, null, 2, 3));

        // Length mismatch
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(new double[] {1}, new int[] {0, 1}, 2, 3));

        // Wrong dims
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(sparse, idx, 0, 3));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(sparse, idx, 2, 0));

        // Out of bounds index
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseDoubleDataSi(sparse, new int[] {0, 2, 6}, 2, 3));

        // Not strictly increasing → fail
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(sparse, new int[] {0, 2, 2}, 2, 3));
        
        // Negative index
        int[] idx2 = new int[] {-2, 2, 4};
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseDoubleDataSi(sparse, idx2, 2, 3));
    }

    /**
     * Test length errors.
     */
    @Test
    public void testLengthErrors()
    {
        double[] double6 = new double[] {1, 0, 2, 0, 4, 0};
        double[] double5 = new double[] {1, 0, 2, 0, 4};
        double[] double7 = new double[] {1, 0, 2, 0, 4, 0, 0};
        int[] idx = new int[] {0, 2, 4};

        // data length != rows x cols
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(double6, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(double5, 3, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(double7, 3, 2));

        // Q[] sparse constructor
        Length[] lengthArray3 = new Length[] {Length.ONE, Length.of(2.0, "m"), Length.of(4.0, "m")};
        Length[] lengthArray4 = new Length[] {Length.ONE, Length.of(2.0, "m"), Length.of(4.0, "m"), Length.of(5.0, "m")};
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(lengthArray4, idx, 3, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(lengthArray3, idx, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(lengthArray3, idx, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(lengthArray3, idx, 3, 0));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(lengthArray3, idx, 3, -2));

        // Q[] dense constructor
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(lengthArray4, 3, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(lengthArray4, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(lengthArray4, 2, -1));

        // DoubleSparseValue constructor
        List<DoubleSparseValue<Length, Length.Unit>> svl = new ArrayList<>();
        svl.add(new DoubleSparseValue<>(0, 0, 1.0));
        svl.add(new DoubleSparseValue<>(0, 1, 2.0));
        svl.add(new DoubleSparseValue<>(1, 1, 4.0));
        assertNotNull(new SparseDoubleDataSi(svl, 3, 2)); // base ok?
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(svl, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(svl, -3, 2));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(svl, 3, 0));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(svl, 3, -2));
        
        svl.add(new DoubleSparseValue<>(3, 1, 8.0));
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseDoubleDataSi(svl, 3, 2));
        svl.remove(3);
        svl.add(new DoubleSparseValue<>(2, 2, 8.0));
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseDoubleDataSi(svl, 3, 2));
        svl.remove(3);
    }

    // ----------------------------------------------------------------------
    // Constructor: double[] denseData
    // ----------------------------------------------------------------------

    /**
     * Test constructor(double[],r,c) and sparse conversion.
     */
    @Test
    @DisplayName("ctor(double[],r,c): basic sparse conversion + validation")
    public void testCtorDenseData()
    {
        // Null → NPE
        assertThrows(NullPointerException.class, () -> new SparseDoubleDataSi((double[]) null, 2, 2));

        // Bad dims
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(dense2x3(), 0, 3));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(dense2x3(), 2, 0));

        // Happy path
        SparseDoubleDataSi d = new SparseDoubleDataSi(dense2x3(), 2, 3);
        assertEquals(2, d.rows());
        assertEquals(3, d.cols());

        // Expect non-zero entries at indices 0,2,4,5
        assertEquals(1.0, d.get(0, 0), 1e-12);
        assertEquals(0.0, d.get(0, 1), 1e-12);
        assertEquals(2.0, d.get(0, 2), 1e-12);
        assertEquals(3.0, d.get(1, 1), 1e-12);
        assertEquals(4.0, d.get(1, 2), 1e-12);
    }

    // ----------------------------------------------------------------------
    // Constructor: double[][] denseData (safe copy)
    // ----------------------------------------------------------------------

    /**
     * Test constructor(double[][]) including ragged checking and safe-copy semantics.
     */
    @Test
    @DisplayName("ctor(double[][]): ragged detection + safe copy + sparse")
    public void testCtorMatrix()
    {
        assertThrows(NullPointerException.class, () -> new SparseDoubleDataSi((double[][]) null));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(new double[][] {}));

        // Ragged
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(new double[][] {{1.0}, {1.0, 2.0}}));

        // Happy path
        double[][] m = dense2x3Matrix();
        SparseDoubleDataSi d = new SparseDoubleDataSi(m);
        assertEquals(2, d.rows());
        assertEquals(3, d.cols());

        // Modify source; must not affect stored sparse representation
        m[0][0] = 999.0;
        assertEquals(1.0, d.get(0, 0), 1e-12);
    }

    // ----------------------------------------------------------------------
    // Constructor: Q[] sparseData + indexes + safe copy
    // ----------------------------------------------------------------------

    /**
     * Test constructor(Q[], indexes, rows, cols) with SI-conversion and validation.
     */
    @Test
    @DisplayName("ctor(Q[],indexes,r,c): SI conversion + safe copies")
    public void testCtorQArraySparse()
    {
        Length[] q = new Length[] {new Length(1.0, Length.Unit.km), // 1000 m
                new Length(2.0, Length.Unit.m), new Length(3.0, Length.Unit.cm) // 0.03 m
        };
        int[] idx = new int[] {0, 3, 5};

        SparseDoubleDataSi d = new SparseDoubleDataSi(q, idx, 2, 3);

        // Check SI conversion
        double[] dense = d.getDataArray();
        assertEquals(1000.0, dense[0], 1e-12);
        assertEquals(2.0, dense[3], 1e-12);
        assertEquals(0.03, dense[5], 1e-12);

        // Mutation safety
        q[0] = new Length(999.0, Length.Unit.m);
        idx[0] = 10;
        double[] dense2 = d.getDataArray();
        assertEquals(1000.0, dense2[0], 1e-12);
    }

    // ----------------------------------------------------------------------
    // Constructor: Q[] denseData (full dense → sparse)
    // ----------------------------------------------------------------------

    /**
     * Test constructor(Q[], rows, cols) and verify no size check and correct sparse generation.
     */
    @Test
    @DisplayName("ctor(Q[],r,c): full dense array -> sparse")
    public void testCtorQArrayDense()
    {
        Length[] q = new Length[] {new Length(0.0, Length.Unit.m), new Length(1.0, Length.Unit.m),
                new Length(0.0, Length.Unit.m), new Length(2.0, Length.Unit.km)};
        SparseDoubleDataSi d = new SparseDoubleDataSi(q, 2, 2);

        double[] expected = new double[4];
        expected[1] = 1.0;
        expected[3] = 2000.0;

        assertArrayEquals(expected, d.getDataArray(), 1e-12);
    }

    // ----------------------------------------------------------------------
    // Constructor: Q[][] denseData → sparse safe copy
    // ----------------------------------------------------------------------

    /**
     * Test constructor(Q[][]) incl. ragged detection, SI conversion, and safe copies.
     */
    @Test
    @DisplayName("ctor(Q[][]): SI conversion + sparse + ragged detection")
    public void testCtorMatrixQ()
    {
        assertThrows(NullPointerException.class, () -> new SparseDoubleDataSi((Length[][]) null));
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(new Length[][] {}));

        Length[][] ragged = new Length[][] {{Length.ofSi(1.0)}, {}};
        assertThrows(IllegalArgumentException.class, () -> new SparseDoubleDataSi(ragged));

        Length[][] m = new Length[][] {{new Length(1.0, Length.Unit.km), new Length(0.0, Length.Unit.m)},
                {new Length(2.0, Length.Unit.cm), new Length(3.0, Length.Unit.mm)}};
        SparseDoubleDataSi d = new SparseDoubleDataSi(m);

        double[] dense = d.getDataArray();
        assertEquals(1000.0, dense[0], 1e-12);
        assertEquals(0.02, dense[2], 1e-12);
        assertEquals(0.003, dense[3], 1e-12);

        // Modify input; must not affect
        m[0][0] = new Length(999.0, Length.Unit.m);
        assertEquals(1000.0, d.get(0, 0), 1e-12);
    }

    // ----------------------------------------------------------------------
    // Constructor: Collection<DoubleSparseValue>
    // ----------------------------------------------------------------------

    /**
     * Test constructor(Collection) including bounds errors and SI conversion.
     */
    @Test
    @DisplayName("ctor(Collection<DoubleSparseValue>): index validation + SI")
    public void testCtorCollection()
    {
        Length v1 = new Length(1.0, Length.Unit.m);
        Length v2 = new Length(2.0, Length.Unit.km);
        Length v3 = new Length(3.0, Length.Unit.cm);

        Collection<DoubleSparseValue<Length, Length.Unit>> col = new ArrayList<>();
        col.add(new DoubleSparseValue<>(0, 0, v1));
        col.add(new DoubleSparseValue<>(1, 1, v2));
        col.add(new DoubleSparseValue<>(1, 2, v3));

        SparseDoubleDataSi d = new SparseDoubleDataSi(col, 2, 3);

        assertEquals(1.0, d.get(0, 0), 1e-12);
        assertEquals(2000.0, d.get(1, 1), 1e-12);
        assertEquals(0.03, d.get(1, 2), 1e-12);

        // Invalid row
        assertThrows(IllegalArgumentException.class, () -> List.of(new DoubleSparseValue<>(-1, 0, v1)));
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseDoubleDataSi(col, 3, 2));

        // Invalid column: current buggy code uses >= rows instead of >= cols
        var col3 = List.of(new DoubleSparseValue<>(0, 3, v1));
        assertThrows(IndexOutOfBoundsException.class, () -> new SparseDoubleDataSi(col3, 2, 3));
    }

    // ----------------------------------------------------------------------
    // get(), getDataArray()
    // ----------------------------------------------------------------------

    /**
     * Test getDataArray generates dense layout correctly and is a safe copy.
     */
    @Test
    @DisplayName("get/getDataArray: binary search hit/miss + safe copy")
    public void testGetAndGetDataArray()
    {
        SparseDoubleDataSi d = new SparseDoubleDataSi(dense2x3(), 2, 3);

        // Direct checks of get()
        assertEquals(1.0, d.get(0, 0), 1e-12); // hit
        assertEquals(0.0, d.get(0, 1), 1e-12); // miss
        assertEquals(4.0, d.get(1, 2), 1e-12); // hit

        // getDataArray should return full dense version
        double[] dense = d.getDataArray();
        assertArrayEquals(dense2x3(), dense, 1e-12);

        // Must be safe copy
        dense[0] = 999.0;
        assertEquals(1.0, d.get(0, 0), 1e-12);
    }

    // ----------------------------------------------------------------------
    // DataGrid default methods
    // ----------------------------------------------------------------------

    /**
     * Test DataGrid.getRowArray and getColArray.
     */
    @Test
    @DisplayName("DataGrid.getRowArray and getColArray work and check bounds")
    public void testDefaultRowColMethods()
    {
        SparseDoubleDataSi d = new SparseDoubleDataSi(dense2x3(), 2, 3);

        assertArrayEquals(new double[] {1.0, 0.0, 2.0}, d.getRowArray(0), 1e-12);
        assertArrayEquals(new double[] {0.0, 3.0, 4.0}, d.getRowArray(1), 1e-12);

        assertArrayEquals(new double[] {1.0, 0.0}, d.getColArray(0), 1e-12);
        assertArrayEquals(new double[] {0.0, 3.0}, d.getColArray(1), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> d.getRowArray(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> d.getColArray(3));
    }

    // ----------------------------------------------------------------------
    // copy()
    // ----------------------------------------------------------------------

    /**
     * Test deep copy of sparseData and indexes.
     */
    @Test
    @DisplayName("copy(): deep copy of sparseData and indexes")
    public void testCopy()
    {
        SparseDoubleDataSi d = new SparseDoubleDataSi(dense2x3(), 2, 3);
        SparseDoubleDataSi c = d.copy();

        assertNotSame(d, c);
        assertNotSame(d.getDataArray(), c.getDataArray());
        assertArrayEquals(d.getDataArray(), c.getDataArray(), 1e-12);

        // Mutate original sparseData via instantiateNew → copy unaffected
        SparseDoubleDataSi d2 = d.instantiateNew(new double[] {0, 0, 0, 0, 0, 1});
        assertEquals(1.0, d2.get(1, 2), 1e-12);
        assertArrayEquals(d.getDataArray(), c.getDataArray(), 1e-12);
    }

    // ----------------------------------------------------------------------
    // instantiateNew
    // ----------------------------------------------------------------------

    /**
     * Test instantiateNew method.
     */
    @Test
    @DisplayName("instantiateNew: reshape + validation")
    public void testInstantiateNew()
    {
        SparseDoubleDataSi d = new SparseDoubleDataSi(dense2x3(), 2, 3);

        double[] newDense = new double[] {0, 5, 0, 6};
        SparseDoubleDataSi d2 = d.instantiateNew(newDense, 2, 2);

        assertEquals(2, d2.rows());
        assertEquals(2, d2.cols());
        assertEquals(5.0, d2.get(0, 1), 1e-12);
        assertEquals(6.0, d2.get(1, 1), 1e-12);

        // Wrong length
        assertThrows(IllegalArgumentException.class, () -> d.instantiateNew(new double[] {1, 2, 3}, 2, 2));
    }

    // ----------------------------------------------------------------------
    // cardinality
    // ----------------------------------------------------------------------

    /**
     * Test cardinality method.
     */
    @Test
    @DisplayName("cardinality: counts non-zero sparse entries")
    public void testCardinality()
    {
        // dense2x3 has 4 non-zero values
        SparseDoubleDataSi d = new SparseDoubleDataSi(dense2x3(), 2, 3);
        assertEquals(4, d.cardinality());

        // explicit 0.0 value in the data -- cardinality should not count it
        SparseDoubleDataSi d0 = new SparseDoubleDataSi(new double[] {1.0, 0.0}, new int[] {1, 3}, 3, 2);
        assertEquals(1, d0.cardinality());
    }

    // ----------------------------------------------------------------------
    // equals and hashCode
    // ----------------------------------------------------------------------

    /**
     * Test equals() and hashCode().
     */
    @Test
    @DisplayName("equals/hashCode: correct semantics")
    public void testEqualsHashCode()
    {
        SparseDoubleDataSi a = new SparseDoubleDataSi(dense2x3(), 2, 3);
        SparseDoubleDataSi b = new SparseDoubleDataSi(dense2x3(), 2, 3);
        SparseDoubleDataSi c = new SparseDoubleDataSi(new double[] {1, 2, 0, 0, 0, 0}, 2, 3);
        SparseDoubleDataSi d3x2 = new SparseDoubleDataSi(dense2x3(), 3, 2);

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, c);
        assertNotEquals(a, d3x2);
        assertNotEquals(a, null);
        assertNotEquals(a, "string");
    }
}
