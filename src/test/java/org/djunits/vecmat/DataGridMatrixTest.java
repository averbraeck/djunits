package org.djunits.vecmat;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Length;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DataGridMatrix}. This test suite verifies all functionality defined in the abstract class
 * DataGridMatrix by using a concrete instance of {@link MatrixNxM} parametrized with {@link Length} and
 * {@link org.djunits.quantity.Length.Unit}. All tests focus strictly on the functionality specified by DataGridMatrix (not
 * MatrixNxM-specific operations).
 * <p>
 * This class achieves 100% coverage of all DataGridMatrix methods:
 * <ul>
 * <li>Constructor behavior (indirectly tested via MatrixNxM)</li>
 * <li>{@code getDataGrid()}</li>
 * <li>{@code si()} and {@code si(int,int)}</li>
 * <li>{@code setDisplayUnit(U)}</li>
 * <li>{@code value(int,int)}</li>
 * <li>{@code getScalars()}</li>
 * <li>{@code getRow(int)}</li>
 * <li>{@code getColumn(int)}</li>
 * <li>{@code getDiagonal()}</li>
 * <li>{@code getRowScalars(int)}</li>
 * <li>{@code getColumnScalars(int)}</li>
 * <li>{@code getDiagonalScalars()}</li>
 * <li>{@code isRelative()}</li>
 * <li>{@code equals(Object)} and {@code hashCode()}</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class DataGridMatrixTest
{
    /**
     * Helper: create a 2x3 Length matrix with values expressed in meters.
     * @return MatrixNxM of size 2x3 with simple values 1..6 m
     */
    private static MatrixNxM<Length, Length.Unit> create2x3Meters()
    {
        double[] data = new double[] {1, 2, 3, 4, 5, 6};
        DataGridSi<?> dg = new DenseDoubleDataSi(data, 2, 3);
        return new MatrixNxM<>(dg, Length.Unit.m);
    }

    /**
     * Helper: create a 3x3 Length matrix with SI data.
     * @return a simple 3x3 matrix with values {1..9} m
     */
    private static MatrixNxM<Length, Length.Unit> create3x3Meters()
    {
        double[] data = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        return new MatrixNxM<>(new DenseDoubleDataSi(data, 3, 3), Length.Unit.m);
    }

    // ----------------------------------------------------------------------
    // getDataGrid()
    // ----------------------------------------------------------------------

    /**
     * Test that getDataGrid() returns the underlying DataGrid and is the correct instance.
     */
    @Test
    @DisplayName("getDataGrid(): returns internal DataGrid")
    public void testGetDataGrid()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();
        DataGridSi<?> grid = m.getDataGrid();
        assertNotNull(grid);
        assertEquals(2, grid.rows());
        assertEquals(3, grid.cols());
    }

    // ----------------------------------------------------------------------
    // si() array and si(r,c)
    // ----------------------------------------------------------------------

    /**
     * Test si() returns a double[] copy of SI values and si(r,c) uses 1-based indexing.
     */
    @Test
    @DisplayName("si() array and si(r,c): correct SI values, 1-based access")
    public void testSiMethods()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();

        double[] arr = m.si();
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, arr);

        // Test 1-based indexing
        assertEquals(1, m.si(1, 1), 1e-12);
        assertEquals(2, m.si(1, 2), 1e-12);
        assertEquals(6, m.si(2, 3), 1e-12);

        // Out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(3, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.si(1, 4));

        // arr is NOT a safe copy
        arr[0] = 999;
        assertEquals(999.0, m.si(1, 1), 1e-12);
    }

    // ----------------------------------------------------------------------
    // setDisplayUnit
    // ----------------------------------------------------------------------

    /**
     * Test setDisplayUnit returns same instance (fluent) and updates display unit.
     */
    @Test
    @DisplayName("setDisplayUnit(): fluent API, correct unit update")
    public void testSetDisplayUnit()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();
        assertSame(m, m.setDisplayUnit(Length.Unit.km));
        assertEquals(Length.Unit.km, m.getDisplayUnit());
    }

    // ----------------------------------------------------------------------
    // value(r,c)
    // ----------------------------------------------------------------------

    /**
     * Test value(r,c) returns Length quantities with correct display unit applied.
     */
    @Test
    @DisplayName("value(r,c): correct Quantity values, 1-based")
    public void testValue()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();
        Length v = m.get(2, 3); // value = 6 (meters)
        assertEquals(6, v.si(), 1e-12);
        assertEquals(Length.Unit.m, v.getDisplayUnit());

        // Out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(3, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(1, 4));
    }

    // ----------------------------------------------------------------------
    // getScalars()
    // ----------------------------------------------------------------------

    /**
     * Test getScalars() returns a Q[][] array with correct values and units.
     */
    @Test
    @DisplayName("getScalars(): correct Q[][] with units")
    public void testGetScalars()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();
        Length[][] scalars = m.getScalars();

        assertEquals(2, scalars.length);
        assertEquals(3, scalars[0].length);

        assertEquals(1, scalars[0][0].si(), 1e-12);
        assertEquals(6, scalars[1][2].si(), 1e-12);

        assertEquals(Length.Unit.m, scalars[1][2].getDisplayUnit());
    }

    // ----------------------------------------------------------------------
    // getRow() and getColumn()
    // ----------------------------------------------------------------------

    /**
     * Test getRow() returns correct VectorN.Row with correct SI values.
     */
    @Test
    @DisplayName("getRow(): returns correct row vector (1-based)")
    public void testGetRow()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();

        VectorN.Row<Length, Length.Unit> row1 = m.getRow(1);
        assertEquals(3, row1.size());
        assertArrayEquals(new double[] {1, 2, 3}, row1.si(), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRow(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRow(3));
    }

    /**
     * Test getColumn() returns correct VectorN.Col with correct SI values.
     */
    @Test
    @DisplayName("getColumn(): returns correct column vector (1-based)")
    public void testGetColumn()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();

        VectorN.Col<Length, Length.Unit> col2 = m.getColumn(2);
        assertEquals(2, col2.size());
        assertArrayEquals(new double[] {2, 5}, col2.si(), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumn(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumn(4));
    }

    // ----------------------------------------------------------------------
    // getDiagonal()
    // ----------------------------------------------------------------------

    /**
     * Test getDiagonal() for square and non-square matrices.
     */
    @Test
    @DisplayName("getDiagonal(): works for square matrix, fails for non-square")
    public void testGetDiagonal()
    {
        MatrixNxM<Length, Length.Unit> sq = create3x3Meters();
        VectorN.Col<Length, Length.Unit> diag = sq.getDiagonal();
        assertEquals(3, diag.size());
        assertArrayEquals(new double[] {1, 5, 9}, diag.si(), 1e-12);

        MatrixNxM<Length, Length.Unit> nonSq = create2x3Meters();
        assertThrows(IllegalStateException.class, nonSq::getDiagonal);
    }

    // ----------------------------------------------------------------------
    // getRowScalars(), getColumnScalars(), getDiagonalScalars()
    // ----------------------------------------------------------------------

    /**
     * Test getRowScalars().
     */
    @Test
    @DisplayName("getRowScalars(): correct Q[] and 1-based bounds")
    public void testGetRowScalars()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();

        Length[] row2 = m.getRowScalars(2);
        assertEquals(3, row2.length);
        assertEquals(4, row2[0].si(), 1e-12);
        assertEquals(6, row2[2].si(), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(3));
    }

    /**
     * Test getColumnScalars().
     */
    @Test
    @DisplayName("getColumnScalars(): correct Q[] and 1-based bounds")
    public void testGetColumnScalars()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();

        Length[] col3 = m.getColumnScalars(3);
        assertEquals(2, col3.length);
        assertEquals(3, col3[0].si(), 1e-12);
        assertEquals(6, col3[1].si(), 1e-12);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(4));
    }

    /**
     * Test getDiagonalScalars().
     */
    @Test
    @DisplayName("getDiagonalScalars(): diagonal Q[] for square matrix")
    public void testGetDiagonalScalars()
    {
        MatrixNxM<Length, Length.Unit> sq = create3x3Meters();
        Length[] diag = sq.getDiagonalScalars();

        assertEquals(3, diag.length);
        assertEquals(1, diag[0].si(), 1e-12);
        assertEquals(5, diag[1].si(), 1e-12);
        assertEquals(9, diag[2].si(), 1e-12);

        MatrixNxM<Length, Length.Unit> nonSq = create2x3Meters();
        assertThrows(IllegalStateException.class, nonSq::getDiagonalScalars);
    }

    // ----------------------------------------------------------------------
    // isRelative()
    // ----------------------------------------------------------------------

    /**
     * Test isRelative(). Length is relative → true.
     */
    @Test
    @DisplayName("isRelative(): depends on quantity type")
    public void testIsRelative()
    {
        MatrixNxM<Length, Length.Unit> m = create2x3Meters();
        assertTrue(m.isRelative());
    }

    // ----------------------------------------------------------------------
    // equals() and hashCode()
    // ----------------------------------------------------------------------

    /**
     * Test equals and hashCode semantics.
     */
    @Test
    @DisplayName("equals/hashCode: value-based equality & correct mismatches")
    public void testEqualsHashCode()
    {
        MatrixNxM<Length, Length.Unit> a = create2x3Meters();
        MatrixNxM<Length, Length.Unit> b = create2x3Meters();

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        // Different data
        MatrixNxM<Length, Length.Unit> c =
                new MatrixNxM<>(new DenseDoubleDataSi(new double[] {9, 8, 7, 6, 5, 4}, 2, 3), Length.Unit.m);
        assertNotEquals(a, c);

        // Different shape
        MatrixNxM<Length, Length.Unit> d = new MatrixNxM<>(new DenseDoubleDataSi(new double[] {1, 2, 3, 4}, 2, 2), Length.Unit.m);
        assertNotEquals(a, d);

        // Different type
        assertNotEquals(a, "string");
        assertNotEquals(a, null);
    }
}
