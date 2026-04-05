package org.djunits.vecmat.table;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Area;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.Speed;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.d1.Matrix1x1;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djunits.vecmat.storage.SparseDoubleDataSi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link QuantityTable}. This test covers all behavior specifically introduced in {@code QuantityTable}.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class QuantityTableTest
{
    /** Numerical tolerance. */
    private static final double EPS = 1.0E-12;

    /**
     * Helper: create a simple 2x2 QuantityTable with Length quantities in meters.
     * @return 2x2 table with values {1,2,3,4} m
     */
    private static QuantityTable<Length> create2x2()
    {
        double[] si = new double[] {1, 2, 3, 4};
        DataGridSi<?> dg = new DenseDoubleDataSi(si, 2, 2);
        return new QuantityTable<>(dg, Length.Unit.m);
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     * Test basic constructor behavior: correct dimensions and display unit.
     */
    @Test
    @DisplayName("ctor: stores datagrid and display unit correctly")
    public void testConstructor()
    {
        QuantityTable<Length> q = create2x2();
        assertEquals(2, q.rows());
        assertEquals(2, q.cols());
        assertEquals(Length.Unit.m, q.getDisplayUnit());
        assertNotNull(q.getDataGrid());
    }

    // ------------------------------------------------------------------------------------
    // Factories / constructors
    // ------------------------------------------------------------------------------------

    /** Verify of(double[], rows, cols, U) rejects nulls/bad sizes and converts display→SI. */
    @Test
    @DisplayName("of(double[],rows,cols,U): nulls/bad sizes/SI convert")
    public void testFactoryArray()
    {
        assertThrows(NullPointerException.class, () -> QuantityTable.of((double[]) null, 3, 2, Length.Unit.km));
        assertThrows(NullPointerException.class, () -> QuantityTable.of(new double[6], 3, 2, null));
        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new double[5], 3, 2, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new double[6], 0, 2, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new double[6], 3, 0, Length.Unit.m));

        // 3x2 in km → SI
        QuantityTable<Length> m = QuantityTable.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000}, m.si(), EPS);
        assertEquals(3, m.rows());
        assertEquals(2, m.cols());
    }

    /** Verify of(double[][],U) checks rectangular shape and converts display→SI. */
    @Test
    @DisplayName("of(double[][],U): rectangular shape & SI convert")
    public void testFactoryGrid()
    {
        assertThrows(NullPointerException.class, () -> QuantityTable.of((double[][]) null, Length.Unit.km));
        assertThrows(NullPointerException.class, () -> QuantityTable.of(new double[][] {{1, 2}, {3, 4}}, null));
        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new double[][] {}, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new double[][] {{}, {1}}, Length.Unit.m));

        // 2x3 in km
        QuantityTable<Length> m = QuantityTable.of(new double[][] {{1, 2, 3}, {4, 5, 6}}, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000}, m.si(), EPS);
        assertEquals(2, m.rows());
        assertEquals(3, m.cols());

        // ragged -> error
        assertThrows(IllegalArgumentException.class,
                () -> QuantityTable.of(new double[][] {{1, 2, 3}, {4, 5}}, Length.Unit.km));
    }

    /** Verify of(Q[][],U) accepts per-cell units via DenseDoubleData and sets display unit. */
    @Test
    @DisplayName("of(Q[][],U): quantity grid accepted")
    public void testFactoryQuantityGrid()
    {
        Length[][] q = new Length[][] {{Length.of(1.0, "km"), Length.of(200.0, "m")}};
        QuantityTable<Length> m = QuantityTable.of(q).setDisplayUnit(Length.Unit.m);
        assertArrayEquals(new double[] {1000.0, 200.0}, m.si(), EPS);
        assertEquals(1, m.rows());
        assertEquals(2, m.cols());
    }

    // ----------------------------------------------------------------------
    // instantiateSi()
    // ----------------------------------------------------------------------

    /**
     * Test instantiateSi(double[]): produces new instance with SI data replaced.
     */
    @Test
    @DisplayName("instantiateSi(): replaces SI data correctly and preserves display unit")
    public void testInstantiateSi()
    {
        QuantityTable<Length> q = create2x2();
        double[] newSi = new double[] {10, 20, 30, 40};

        QuantityTable<Length> q2 = q.instantiateSi(newSi);

        assertNotSame(q, q2);
        assertArrayEquals(newSi, q2.si(), 1e-12);
        assertEquals(q.getDisplayUnit(), q2.getDisplayUnit());
    }

    // ----------------------------------------------------------------------
    // invertElements()
    // ----------------------------------------------------------------------

    /**
     * Test invertElements(): works elementwise, produces SIQuantity output.
     */
    @Test
    @DisplayName("invertElements(): reciprocal of each SI value")
    public void testInvertElements()
    {
        QuantityTable<Length> q = new QuantityTable<>(new DenseDoubleDataSi(new double[] {1, 2, 4, 5}, 2, 2), Length.Unit.m);

        QuantityTable<?> inv = q.invertEntries();
        double[] expected = new double[] {1.0, 1.0 / 2.0, 1.0 / 4.0, 1.0 / 5.0};

        assertArrayEquals(expected, inv.si(), 1e-12);
        assertTrue(inv.getDisplayUnit() instanceof SIUnit, "Display unit after inversion must be SIUnit (inverted)");
    }

    // ----------------------------------------------------------------------
    // multiplyElements()
    // ----------------------------------------------------------------------

    /**
     * Test multiplyElements(): Hadamard product of two tables.
     */
    @Test
    @DisplayName("multiplyElements(): Hadamard multiplication")
    public void testMultiplyElements()
    {
        QuantityTable<Length> a = new QuantityTable<>(new DenseDoubleDataSi(new double[] {1, 2, 3, 4}, 2, 2), Length.Unit.m);

        QuantityTable<Length> b =
                new QuantityTable<>(new DenseDoubleDataSi(new double[] {10, 20, 30, 40}, 2, 2), Length.Unit.m);

        QuantityTable<?> result = a.multiplyEntries(b);

        assertArrayEquals(new double[] {10, 40, 90, 160}, result.si(), 1e-12);
    }

    // ----------------------------------------------------------------------
    // divideElements()
    // ----------------------------------------------------------------------

    /**
     * Test divideElements(): Hadamard division of two tables.
     */
    @Test
    @DisplayName("divideElements(): Hadamard division")
    public void testDivideElements()
    {
        QuantityTable<Length> a =
                new QuantityTable<>(new DenseDoubleDataSi(new double[] {100, 50, 20, 10}, 2, 2), Length.Unit.m);

        QuantityTable<Length> b = new QuantityTable<>(new DenseDoubleDataSi(new double[] {10, 5, 2, 1}, 2, 2), Length.Unit.m);

        QuantityTable<?> result = a.divideEntries(b);

        assertArrayEquals(new double[] {10, 10, 10, 10}, result.si(), 1e-12);
    }

    /** Verify equals/hashCode. */
    @Test
    @DisplayName("equals / hashCode")
    public void testEqualsHash()
    {
        QuantityTable<Length> a = QuantityTable.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        QuantityTable<Length> b = QuantityTable.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        QuantityTable<Length> c = QuantityTable.of(new double[] {1, 2, 3, 4, 5, 7}, 3, 2, Length.Unit.m);
        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
        assertNotEquals(a, null);
        assertNotEquals(a, "other");
    }

    // ----------------------------------------------------------------------
    // as(targetUnit)
    // ----------------------------------------------------------------------

    /**
     * Test as(): converting the table to a different display unit but identical SI unit.
     */
    @Test
    @DisplayName("as(): convert display unit when SI units match")
    public void testAs()
    {
        QuantityTable<Length> q = create2x2();
        QuantityTable<Length> converted = q.as(Length.Unit.km);

        assertNotSame(q, converted);
        assertEquals(Length.Unit.km, converted.getDisplayUnit());
        assertArrayEquals(q.si(), converted.si(), 1e-12);

        // Units NOT matching → error
        assertThrows(IllegalArgumentException.class, () -> q.as(SIUnit.of("s"))); // dimension mismatch: meter vs second
    }

    /**
     * Verify QuantityTable conversions to fixed-size vectors and matrices preserve SI data and display unit, and that shape
     * checks throw IllegalStateException with both row- and column-mismatch branches covered.
     * <p>
     * Uses kilometers for column-shaped tests and centimeters for row-shaped tests to exercise SI conversion.
     */
    @Test
    @DisplayName("QuantityTable: asVector preserve SI and unit; row/col mismatch branches throw")
    public void testQuantityTableAsVectorConversions()
    {
        // ----------------------------
        // asVector1 (requires 1x1)
        // ----------------------------

        QuantityTable<Length> m11cm = QuantityTable.of(new double[] {3}, 1, 1, Length.Unit.cm);
        assertEquals(Length.Unit.cm, m11cm.getDisplayUnit());
        assertEquals(1, m11cm.si().length);
        assertEquals(0.03, m11cm.si()[0], EPS);
        Vector1<Length> v1cm = m11cm.asVector1();
        assertEquals(1, v1cm.size());
        assertEquals(0.03, v1cm.get(0).si(), EPS);
        assertEquals(Length.Unit.cm, v1cm.getDisplayUnit());

        // row mismatch (2x1) and col mismatch (1x2)
        QuantityTable<Length> m21km = QuantityTable.of(new double[] {5.0, 6.0}, 2, 1, Length.Unit.km);
        QuantityTable<Length> m12cm = QuantityTable.of(new double[] {3, 4}, 1, 2, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> m21km.asVector1());
        assertThrows(IllegalStateException.class, () -> m12cm.asVector1());

        // ----------------------------
        // asVector2Col (requires 2x1)
        // ----------------------------

        QuantityTable<Length> m21ok = QuantityTable.of(new double[] {5.0, 6.0}, 2, 1, Length.Unit.km);
        assertEquals(Length.Unit.km, m21ok.getDisplayUnit());
        assertEquals(2, m21ok.si().length);
        assertEquals(5000.0, m21ok.si()[0], EPS);
        assertEquals(6000.0, m21ok.si()[1], EPS);
        Vector2.Col<Length> v2col = m21ok.asVector2Col();
        assertEquals(2, v2col.size());
        assertEquals(5000.0, v2col.get(0).si(), EPS);
        assertEquals(6000.0, v2col.get(1).si(), EPS);
        assertEquals(Length.Unit.km, v2col.getDisplayUnit());

        // row mismatch (1x1) and col mismatch (2x2)
        QuantityTable<Length> m11km = QuantityTable.of(new double[] {7.0}, 1, 1, Length.Unit.km);
        QuantityTable<Length> m22km = QuantityTable.of(new double[] {5, 6, 7, 8}, 2, 2, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m11km.asVector2Col());
        assertThrows(IllegalStateException.class, () -> m22km.asVector2Col());

        // ----------------------------
        // asVector2Row (requires 1x2)
        // ----------------------------
        QuantityTable<Length> m12ok = QuantityTable.of(new double[] {3, 4}, 1, 2, Length.Unit.cm);
        assertEquals(Length.Unit.cm, m12ok.getDisplayUnit());
        assertEquals(2, m12ok.si().length);
        assertEquals(0.03, m12ok.si()[0], EPS);
        assertEquals(0.04, m12ok.si()[1], EPS);
        Vector2.Row<Length> v2row = m12ok.asVector2Row();
        assertEquals(2, v2row.size());
        assertEquals(0.03, v2row.get(0).si(), EPS);
        assertEquals(0.04, v2row.get(1).si(), EPS);
        assertEquals(Length.Unit.cm, v2row.getDisplayUnit());

        // row mismatch (2x2) and col mismatch (1x1)
        QuantityTable<Length> m22cm = QuantityTable.of(new double[] {1, 2, 3, 4}, 2, 2, Length.Unit.cm);
        QuantityTable<Length> m11cm2 = QuantityTable.of(new double[] {0.05}, 1, 1, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> m22cm.asVector2Row());
        assertThrows(IllegalStateException.class, () -> m11cm2.asVector2Row());

        // ----------------------------
        // asVector3Col (requires 3x1)
        // ----------------------------
        QuantityTable<Length> m31ok = QuantityTable.of(new double[] {5, 6, 7}, 3, 1, Length.Unit.km);
        Vector3.Col<Length> v3col = m31ok.asVector3Col();
        assertEquals(3, v3col.size());
        assertEquals(5000.0, v3col.get(0).si(), EPS);
        assertEquals(6000.0, v3col.get(1).si(), EPS);
        assertEquals(7000.0, v3col.get(2).si(), EPS);
        assertEquals(Length.Unit.km, v3col.getDisplayUnit());

        // row mismatch (2x1) and col mismatch (3x2)
        QuantityTable<Length> m21bad = QuantityTable.of(new double[] {5.0, 6.0}, 2, 1, Length.Unit.km);
        QuantityTable<Length> m32bad =
                QuantityTable.of(new double[] {5.0, 0.001, 6.0, 0.002, 7.0, 0.003}, 3, 2, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m21bad.asVector3Col());
        assertThrows(IllegalStateException.class, () -> m32bad.asVector3Col());

        // ----------------------------
        // asVector3Row (requires 1x3)
        // ----------------------------
        QuantityTable<Length> m13ok = QuantityTable.of(new double[] {3, 4, 5}, 1, 3, Length.Unit.cm);
        Vector3.Row<Length> v3row = m13ok.asVector3Row();
        assertEquals(3, v3row.size());
        assertEquals(0.03, v3row.get(0).si(), EPS);
        assertEquals(0.04, v3row.get(1).si(), EPS);
        assertEquals(0.05, v3row.get(2).si(), EPS);
        assertEquals(Length.Unit.cm, v3row.getDisplayUnit());

        // row mismatch (2x3) and col mismatch (1x2)
        QuantityTable<Length> m23bad = QuantityTable.of(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.cm);
        QuantityTable<Length> m12bad = QuantityTable.of(new double[] {7, 8}, 1, 2, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> m23bad.asVector3Row());
        assertThrows(IllegalStateException.class, () -> m12bad.asVector3Row());
    }

    // ------------------------------------------------------------------------------------
    // QuantityTable — asMatrix2x2 / asMatrix3x3
    // ------------------------------------------------------------------------------------

    /**
     * Verify QuantityTable conversions to fixed-size matrices preserve SI data and display unit, and that shape checks throw
     * IllegalStateException for the mismatch branches covered.
     */
    @Test
    @DisplayName("QuantityTable: asMatrix preserve SI and unit")
    public void testQuantityTableAsMatrixConversions()
    {
        // ------------------------------------------------------------------------------------
        // QuantityTable — asMatrix1x1 (happy + bad paths)
        // ------------------------------------------------------------------------------------

        // Happy path: 1x1, using km to verify SI-preservation (5 km -> 5000 m).
        QuantityTable<Length> m1x1km = QuantityTable.of(new double[] {5.0}, 1, 1, Length.Unit.km);
        assertEquals(Length.Unit.km, m1x1km.getDisplayUnit());
        assertEquals(1, m1x1km.si().length);
        assertEquals(5000.0, m1x1km.si()[0], EPS);
        assertEquals(5000.0, m1x1km.si(0, 0), EPS);

        Matrix1x1<Length> fixed1x1 = m1x1km.asMatrix1x1();
        assertEquals(Length.Unit.km, fixed1x1.getDisplayUnit());
        assertEquals(5000.0, fixed1x1.si(0, 0), EPS);
        double[] si11 = fixed1x1.si();
        assertEquals(1, si11.length);
        assertEquals(5000.0, si11[0], EPS);

        // Bad path: row mismatch (2x1).
        QuantityTable<Length> m2x1km = QuantityTable.of(new double[] {5.0, 6.0}, 2, 1, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m2x1km.asMatrix1x1());

        // Bad path: column mismatch (1x2).
        QuantityTable<Length> m1x2km = QuantityTable.of(new double[] {5.0, 6.0}, 1, 2, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m1x2km.asMatrix1x1());

        // ----------------------------
        // asMatrix2x2 (requires 2x2)
        // ----------------------------

        QuantityTable<Length> m22ok = QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0}, 2, 2, Length.Unit.m);
        Matrix2x2<Length> m22fixed = m22ok.asMatrix2x2();
        assertEquals(Length.Unit.m, m22fixed.getDisplayUnit());
        assertEquals(1.0, m22fixed.si(0, 0), EPS);
        assertEquals(2.0, m22fixed.si(0, 1), EPS);
        assertEquals(3.0, m22fixed.si(1, 0), EPS);
        assertEquals(4.0, m22fixed.si(1, 1), EPS);
        double[] m22si = m22fixed.si();
        assertEquals(4, m22si.length);
        assertEquals(1.0, m22si[0], EPS);
        assertEquals(2.0, m22si[1], EPS);
        assertEquals(3.0, m22si[2], EPS);
        assertEquals(4.0, m22si[3], EPS);

        // row mismatch (1x2) and col mismatch (2x3)
        QuantityTable<Length> m12mat = QuantityTable.of(new double[] {9.0, 10.0}, 1, 2, Length.Unit.m);
        QuantityTable<Length> m23mat = QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> m12mat.asMatrix2x2());
        assertThrows(IllegalStateException.class, () -> m23mat.asMatrix2x2());

        // ----------------------------
        // asMatrix3x3 (requires 3x3)
        // ----------------------------

        QuantityTable<Length> m33ok =
                QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}, 3, 3, Length.Unit.m);
        Matrix3x3<Length> m33fixed = m33ok.asMatrix3x3();
        assertEquals(Length.Unit.m, m33fixed.getDisplayUnit());
        assertEquals(1.0, m33fixed.si(0, 0), EPS);
        assertEquals(2.0, m33fixed.si(0, 1), EPS);
        assertEquals(3.0, m33fixed.si(0, 2), EPS);
        assertEquals(4.0, m33fixed.si(1, 0), EPS);
        assertEquals(5.0, m33fixed.si(1, 1), EPS);
        assertEquals(6.0, m33fixed.si(1, 2), EPS);
        assertEquals(7.0, m33fixed.si(2, 0), EPS);
        assertEquals(8.0, m33fixed.si(2, 1), EPS);
        assertEquals(9.0, m33fixed.si(2, 2), EPS);
        double[] m33si = m33fixed.si();
        assertEquals(9, m33si.length);
        assertEquals(1.0, m33si[0], EPS);
        assertEquals(2.0, m33si[1], EPS);
        assertEquals(3.0, m33si[2], EPS);
        assertEquals(4.0, m33si[3], EPS);
        assertEquals(5.0, m33si[4], EPS);
        assertEquals(6.0, m33si[5], EPS);
        assertEquals(7.0, m33si[6], EPS);
        assertEquals(8.0, m33si[7], EPS);
        assertEquals(9.0, m33si[8], EPS);

        // row mismatch (2x3) and col mismatch (3x2)
        QuantityTable<Length> m23badMat = QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        QuantityTable<Length> m32badMat = QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 3, 2, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> m23badMat.asMatrix3x3());
        assertThrows(IllegalStateException.class, () -> m32badMat.asMatrix3x3());
    }

    /**
     * Verify QuantityTable conversions to quantity table preserve SI data and display unit, and that shape checks throw
     * IllegalStateException for the mismatch branches covered.
     */
    @Test
    @DisplayName("QuantityTable: asMatrixNxM preserve SI and unit")
    public void testQuantityTableAsMatrixNxM()
    {
        // ----------------------------
        // asMatrixNxM
        // ----------------------------

        double[] newSi = new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        QuantityTable<Length> q23ok = QuantityTable.of(newSi, 3, 2, Length.Unit.km);
        MatrixNxM<Length> m23fixed = q23ok.asMatrixNxM();
        assertEquals(Length.Unit.km, m23fixed.getDisplayUnit());
        assertEquals(m23fixed.cols(), q23ok.cols());
        assertEquals(m23fixed.rows(), q23ok.rows());
        assertEquals(1000.0, m23fixed.si(0, 0), EPS);
        assertEquals(2000.0, m23fixed.si(0, 1), EPS);
        assertEquals(3000.0, m23fixed.si(1, 0), EPS);
        assertEquals(4000.0, m23fixed.si(1, 1), EPS);
        assertEquals(5000.0, m23fixed.si(2, 0), EPS);
        assertEquals(6000.0, m23fixed.si(2, 1), EPS);
        double[] m23si = m23fixed.si();
        assertEquals(6, m23si.length);
        assertEquals(1000.0 * newSi[0], m23si[0]);
    }

    // ------------------------------------------------------------------------------------
    // QuantityTable — asMatrixNxN (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asMatrixNxN()} preserves SI data and display unit for square matrices (tested with 1x1 and 4x4), and
     * throws {@link IllegalStateException} for non-square shapes (tested with 2x3 and 3x2). SI correctness is checked via both
     * the row-major {@code si()} array and {@code si(row, col)} with 0-based indices.
     */
    @Test
    @DisplayName("QuantityTable: asMatrixNxN preserves SI & unit (square) and throws for non-square shapes")
    public void testAsMatrixNxN()
    {
        // ----------------------------
        // Happy path: 1x1 (using cm)
        // ----------------------------
        QuantityTable<Length> m11cm = QuantityTable.of(new double[] {3}, 1, 1, Length.Unit.cm);
        assertEquals(Length.Unit.cm, m11cm.getDisplayUnit());
        assertEquals(1, m11cm.si().length);
        assertEquals(0.03, m11cm.si()[0], EPS);
        assertEquals(0.03, m11cm.si(0, 0), EPS);

        MatrixNxN<Length> n11 = m11cm.asMatrixNxN();
        assertEquals(Length.Unit.cm, n11.getDisplayUnit());
        double[] si11 = n11.si();
        assertEquals(1, si11.length);
        assertEquals(0.03, si11[0], EPS);
        assertEquals(0.03, n11.si(0, 0), EPS);

        // ----------------------------
        // Happy path: 4x4 (using km)
        // ----------------------------
        double[] si44 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
        QuantityTable<Length> m44km = QuantityTable.of(si44, 4, 4, Length.Unit.km);
        assertEquals(Length.Unit.km, m44km.getDisplayUnit());
        assertEquals(16, m44km.si().length);
        assertEquals(1000.0, m44km.si()[0], EPS);
        assertEquals(16000.0, m44km.si()[15], EPS);
        assertEquals(1000.0, m44km.si(0, 0), EPS);
        assertEquals(4000.0, m44km.si(0, 3), EPS);
        assertEquals(13000.0, m44km.si(3, 0), EPS);
        assertEquals(16000.0, m44km.si(3, 3), EPS);

        MatrixNxN<Length> n44 = m44km.asMatrixNxN();
        assertEquals(Length.Unit.km, n44.getDisplayUnit());

        double[] si44Out = n44.si();
        assertEquals(16, si44Out.length);
        assertEquals(1000.0, si44Out[0], EPS);
        assertEquals(2000.0, si44Out[1], EPS);
        assertEquals(3000.0, si44Out[2], EPS);
        assertEquals(4000.0, si44Out[3], EPS);
        assertEquals(5000.0, si44Out[4], EPS);
        assertEquals(6000.0, si44Out[5], EPS);
        assertEquals(7000.0, si44Out[6], EPS);
        assertEquals(8000.0, si44Out[7], EPS);
        assertEquals(9000.0, si44Out[8], EPS);
        assertEquals(10000.0, si44Out[9], EPS);
        assertEquals(11000.0, si44Out[10], EPS);
        assertEquals(12000.0, si44Out[11], EPS);
        assertEquals(13000.0, si44Out[12], EPS);
        assertEquals(14000.0, si44Out[13], EPS);
        assertEquals(15000.0, si44Out[14], EPS);
        assertEquals(16000.0, si44Out[15], EPS);

        assertEquals(1000.0, n44.si(0, 0), EPS);
        assertEquals(4000.0, n44.si(0, 3), EPS);
        assertEquals(13000.0, n44.si(3, 0), EPS);
        assertEquals(16000.0, n44.si(3, 3), EPS);

        // ----------------------------
        // Bad paths: non-square matrices must throw
        // ----------------------------

        // 2x3 (row-mismatch branch of "rows != cols")
        QuantityTable<Length> m23m = QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> m23m.asMatrixNxN());

        // 3x2 (column-mismatch branch of "rows != cols")
        QuantityTable<Length> m32m = QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 3, 2, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> m32m.asMatrixNxN());
    }

    // ------------------------------------------------------------------------------------
    // QuantityTable — asVectorNCol (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asVectorNCol()}:
     * <ul>
     * <li>Preserves SI data and display unit for {@code N x 1} matrices (tested with N=4 and N=1),</li>
     * <li>Throws {@link IllegalStateException} when the matrix has more than one column (e.g., {@code N x 2}).</li>
     * </ul>
     * SI correctness is validated via both the row-major {@code si()} array and element access on the returned vector.
     */
    @Test
    @DisplayName("QuantityTable: asVectorNCol preserves SI & unit (Nx1) and throws for Nx2")
    public void testAsVectorNCol()
    {
        // Happy path: 4x1 using km (values in SI meters: 5,6,7,8 km -> 5000..8000 m)
        QuantityTable<Length> m41km = QuantityTable.of(new double[] {5.0, 6.0, 7.0, 8.0}, 4, 1, Length.Unit.km);

        assertEquals(Length.Unit.km, m41km.getDisplayUnit());
        assertEquals(4, m41km.si().length);
        assertEquals(5000.0, m41km.si()[0], EPS);
        assertEquals(8000.0, m41km.si()[3], EPS);
        assertEquals(5000.0, m41km.si(0, 0), EPS);
        assertEquals(8000.0, m41km.si(3, 0), EPS);

        VectorN.Col<Length> vCol = m41km.asVectorNCol();
        assertEquals(4, vCol.size());
        assertEquals(Length.Unit.km, vCol.getDisplayUnit());
        assertEquals(5000.0, vCol.get(0).si(), EPS);
        assertEquals(8000.0, vCol.get(3).si(), EPS);

        double[] vColSi = vCol.si();
        assertEquals(4, vColSi.length);
        assertEquals(5000.0, vColSi[0], EPS);
        assertEquals(8000.0, vColSi[3], EPS);

        // Edge happy path: 1x1 also yields a VectorN.Col of length 1
        QuantityTable<Length> m11km = QuantityTable.of(new double[] {1.234}, 1, 1, Length.Unit.km);
        VectorN.Col<Length> vCol1 = m11km.asVectorNCol();
        assertEquals(1, vCol1.size());
        assertEquals(Length.Unit.km, vCol1.getDisplayUnit());
        assertEquals(1234.0, vCol1.get(0).si(), EPS);

        // Bad path: Nx2 must throw (cols() != 1)
        QuantityTable<Length> m42km =
                QuantityTable.of(new double[] {5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0}, 4, 2, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m42km.asVectorNCol());
    }

    // ------------------------------------------------------------------------------------
    // QuantityTable — asVectorNRow (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asVectorNRow()}:
     * <ul>
     * <li>Preserves SI data and display unit for {@code 1 x N} matrices (tested with N=4 and N=1),</li>
     * <li>Throws {@link IllegalStateException} when the matrix has more than one row (e.g., {@code 2 x N}).</li>
     * </ul>
     * SI correctness is validated via both the row-major {@code si()} array and element access on the returned vector.
     */
    @Test
    @DisplayName("QuantityTable: asVectorNRow preserves SI & unit (1xN) and throws for 2xN")
    public void testAsVectorNRow()
    {
        // Happy path: 1x4 using cm (values in SI meters: 3,4,5,6 cm -> 0.03..0.06 m)
        QuantityTable<Length> m14cm = QuantityTable.of(new double[] {3, 4, 5, 6}, 1, 4, Length.Unit.cm);

        assertEquals(Length.Unit.cm, m14cm.getDisplayUnit());
        assertEquals(4, m14cm.si().length);
        assertEquals(0.03, m14cm.si()[0], EPS);
        assertEquals(0.06, m14cm.si()[3], EPS);
        assertEquals(0.03, m14cm.si(0, 0), EPS);
        assertEquals(0.06, m14cm.si(0, 3), EPS);

        VectorN.Row<Length> vRow = m14cm.asVectorNRow();
        assertEquals(4, vRow.size());
        assertEquals(Length.Unit.cm, vRow.getDisplayUnit());
        assertEquals(0.03, vRow.get(0).si(), EPS);
        assertEquals(0.06, vRow.get(3).si(), EPS);

        double[] vRowSi = vRow.si();
        assertEquals(4, vRowSi.length);
        assertEquals(0.03, vRowSi[0], EPS);
        assertEquals(0.06, vRowSi[3], EPS);

        // Edge happy path: 1x1 also yields a VectorN.Row of length 1
        QuantityTable<Length> m11cm = QuantityTable.of(new double[] {0.1}, 1, 1, Length.Unit.cm);
        VectorN.Row<Length> vRow1 = m11cm.asVectorNRow();
        assertEquals(1, vRow1.size());
        assertEquals(Length.Unit.cm, vRow1.getDisplayUnit());
        assertEquals(0.001, vRow1.get(0).si(), EPS);

        // Bad path: 2xN must throw (rows() != 1)
        QuantityTable<Length> m24cm = QuantityTable.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8}, 2, 4, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> m24cm.asVectorNRow());
    }

    /**
     * Verify that all asXXX() methods correctly preserve SI values and correctly assign display units. This test detects subtle
     * SI/display-unit conversion mistakes such as interpreting SI values as display-unit values or performing double
     * conversion.
     */
    @Test
    @DisplayName("asXXX(): SI consistency and correct display-unit conversion")
    public void testAsMethodsUnitConversion()
    {
        // Base test data: a 2x2 table expressed in kilometers, SI values are meters.
        // SI array: {1000, 2000, 3000, 4000} meaning {1,2,3,4} km
        double[] si = new double[] {1000, 2000, 3000, 4000};
        QuantityTable<Length> qtKm = new QuantityTable<>(new DenseDoubleDataSi(si, 2, 2), Length.Unit.km);

        // ----------------------------------------------------------------------
        // 1. asMatrixNxM() must preserve SI and assign displayUnit correctly
        // ----------------------------------------------------------------------
        MatrixNxM<Length> mxm = qtKm.asMatrixNxM();
        assertArrayEquals(si, mxm.si(), 1e-12, "asMatrixNxM(): SI values must be preserved exactly");
        assertEquals(Length.Unit.km, mxm.getDisplayUnit(), "asMatrixNxM(): display unit must match the QuantityTable");

        // ----------------------------------------------------------------------
        // 2. asMatrix2x2() must also preserve SI and display units
        // ----------------------------------------------------------------------
        Matrix2x2<Length> m2 = qtKm.asMatrix2x2();
        assertArrayEquals(si, m2.si(), 1e-12, "asMatrix2x2(): SI values must be preserved exactly");
        assertEquals(Length.Unit.km, m2.getDisplayUnit(), "asMatrix2x2(): display unit must match the QuantityTable");

        // ----------------------------------------------------------------------
        // 3. Create a 3x3 km-table for asMatrix3x3 and asMatrixNxN
        // ----------------------------------------------------------------------
        double[] si9 = new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000};
        QuantityTable<Length> qtKm9 = new QuantityTable<>(new DenseDoubleDataSi(si9, 3, 3), Length.Unit.km);

        Matrix3x3<Length> m3 = qtKm9.asMatrix3x3();
        assertArrayEquals(si9, m3.si(), 1e-12, "asMatrix3x3(): SI values must be preserved for 3x3");
        assertEquals(Length.Unit.km, m3.getDisplayUnit(), "asMatrix3x3(): display unit must match the QuantityTable");

        MatrixNxN<Length> mxn = qtKm9.asMatrixNxN();
        assertArrayEquals(si9, mxn.si(), 1e-12, "asMatrixNxN(): SI values must be preserved for square matrices");
        assertEquals(Length.Unit.km, mxn.getDisplayUnit(), "asMatrixNxN(): display unit must match the QuantityTable");

        // ----------------------------------------------------------------------
        // 4. as(targetUnit): SI values must remain identical, displayUnit replaced
        // ----------------------------------------------------------------------
        QuantityTable<Length> qtKmToM = qtKm.as(Length.Unit.m);

        assertArrayEquals(si, qtKmToM.si(), 1e-12, "as(unit): SI values must not change");
        assertEquals(Length.Unit.m, qtKmToM.getDisplayUnit(), "as(unit): display unit must be replaced but SI preserved");

        // ----------------------------------------------------------------------
        // 5. A negative test: mismatching SI units must throw
        // (Length km → seconds does not match)
        // ----------------------------------------------------------------------
        assertThrows(IllegalArgumentException.class, () -> qtKm.as(org.djunits.unit.si.SIUnit.of("s")),
                "as(): mismatching SI unit must throw IllegalArgumentException");
    }

    // ------------------------------------------------------------------------------------
    // Scalar extraction & equals/hashCode
    // ------------------------------------------------------------------------------------

    /** Verify scalar extraction helpers. */
    @Test
    @DisplayName("getScalars / getRowScalars / getColumnScalars")
    public void testScalarExtraction()
    {
        QuantityTable<Length> m =
                new QuantityTable<>(new DenseDoubleDataSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2), Length.Unit.m);
        QuantityTable<Length> mhm =
                new QuantityTable<>(new SparseDoubleDataSi(new double[] {10, 0, 30, 0, 50, 0}, 3, 2), Length.Unit.hm);
        Length[][] scalars = m.getScalarGrid();
        assertEquals(3, scalars.length);
        assertEquals(2, scalars[0].length);
        assertEquals(1.0, scalars[0][0].si(), EPS);
        assertEquals(6.0, scalars[2][1].si(), EPS);
        Length[][] qhm = mhm.getScalarGrid();
        assertEquals(3, qhm.length);
        assertEquals(2, qhm[1].length);
        assertEquals(10.0, qhm[0][0].si(), EPS);
        assertEquals(0.0, qhm[2][1].si(), EPS);
        assertEquals(Length.Unit.hm, qhm[1][1].getDisplayUnit());

        double[][] sigrid = m.getSiGrid();
        double[][] sihm = mhm.getSiGrid();
        assertEquals(3, sigrid.length);
        assertEquals(2, sigrid[0].length);
        assertEquals(2, sigrid[1].length);
        assertEquals(2, sigrid[2].length);
        assertEquals(1.0, sigrid[0][0], EPS);
        assertEquals(4.0, sigrid[1][1], EPS);
        assertEquals(10.0, sihm[0][0], EPS);
        assertEquals(0.0, sihm[1][1], EPS);
        assertEquals(50.0, sihm[2][0], EPS);
        assertEquals(0.0, sihm[2][1], EPS);

        Length[] row1 = m.getRowScalars(1);
        Length[] col1 = m.getColumnScalars(1);
        assertEquals(2, row1.length);
        assertEquals(3.0, row1[0].si(), EPS);
        assertEquals(4.0, row1[1].si(), EPS);
        assertEquals(3, col1.length);
        assertEquals(2.0, col1[0].si(), EPS);
        assertEquals(6.0, col1[2].si(), EPS);

        Length[] mRow2 = m.mgetRowScalars(2);
        Length[] mCol2 = m.mgetColumnScalars(2);
        assertEquals(2, mRow2.length);
        assertEquals(3.0, mRow2[0].si(), EPS);
        assertEquals(4.0, mRow2[1].si(), EPS);
        assertEquals(3, mCol2.length);
        assertEquals(2.0, mCol2[0].si(), EPS);
        assertEquals(6.0, mCol2[2].si(), EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(2));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(3));
    }

    /** Verify vector extraction helpers. */
    @Test
    @DisplayName("getRowVector / getColumnVector")
    public void testVectorExtraction()
    {
        QuantityTable<Length> m =
                new QuantityTable<>(new DenseDoubleDataSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2), Length.Unit.m);
        Length[][] scalars = m.getScalarGrid();
        assertEquals(3, scalars.length);
        assertEquals(2, scalars[0].length);
        assertEquals(1.0, scalars[0][0].si(), EPS);
        assertEquals(6.0, scalars[2][1].si(), EPS);

        VectorN.Row<Length> row1 = m.getRowVector(1);
        VectorN.Col<Length> col1 = m.getColumnVector(1);
        assertEquals(2, row1.size());
        assertEquals(3.0, row1.get(0).si(), EPS);
        assertEquals(4.0, row1.get(1).si(), EPS);
        assertEquals(3, col1.size());
        assertEquals(2.0, col1.get(0).si(), EPS);
        assertEquals(6.0, col1.get(2).si(), EPS);

        VectorN.Row<Length> mRow2 = m.mgetRowVector(2);
        VectorN.Col<Length> mCol2 = m.mgetColumnVector(2);
        assertEquals(2, mRow2.size());
        assertEquals(3.0, mRow2.get(0).si(), EPS);
        assertEquals(4.0, mRow2.get(1).si(), EPS);
        assertEquals(3, mCol2.size());
        assertEquals(2.0, mCol2.get(0).si(), EPS);
        assertEquals(6.0, mCol2.get(2).si(), EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(2));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(3));
    }

    /** Verify double extraction helpers. */
    @Test
    @DisplayName("getRowSi / getColumnSi")
    public void testSiArrayExtraction()
    {
        QuantityTable<Length> m =
                new QuantityTable<>(new DenseDoubleDataSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2), Length.Unit.m);

        double[] row1 = m.getRowSi(1);
        double[] col1 = m.getColumnSi(1);
        assertEquals(2, row1.length);
        assertEquals(3.0, row1[0], EPS);
        assertEquals(4.0, row1[1], EPS);
        assertEquals(3, col1.length);
        assertEquals(2.0, col1[0], EPS);
        assertEquals(6.0, col1[2], EPS);

        double[] mRow2 = m.mgetRowSi(2);
        double[] mCol2 = m.mgetColumnSi(2);
        assertEquals(2, mRow2.length);
        assertEquals(3.0, mRow2[0], EPS);
        assertEquals(4.0, mRow2[1], EPS);
        assertEquals(3, mCol2.length);
        assertEquals(2.0, mCol2[0], EPS);
        assertEquals(6.0, mCol2[2], EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(2));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(3));
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        QuantityTable<Length> r = QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        QuantityTable<Speed> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1, 1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.mget(1, 2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.mget(1, 3).getInUnit(), 1E-6);
        assertEquals(2.0, sr.mget(2, 1).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));
    }

    /**
     * Verify {@link QuantityTable#nnz()} and {@link QuantityTable#nnz()}.
     */
    @Test
    @DisplayName("test nnz()")
    public void testNnz()
    {
        QuantityTable<Length> qt = QuantityTable.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.km);
        assertEquals(6, qt.nnz());
        assertEquals(6, qt.nonZeroCount());
        assertEquals(6, qt.getDataGrid().nnz());
        assertEquals(6, qt.getDataGrid().nonZeroCount());

        QuantityTable<Length> qt0 = QuantityTable.of(new double[] {0, 0, 0, 0, 0, 0}, 2, 3, Length.Unit.km);
        assertEquals(0, qt0.nnz());
        assertEquals(0, qt0.getDataGrid().nnz());
    }

    // ----------------------------------------------------------------------
    // transpose()
    // ----------------------------------------------------------------------

    /**
     * Verify {@link QuantityTable#transpose()} for a rectangular table. Uses a 2x4 table (rows=2, cols=4) and verifies:
     * <ul>
     * <li>Correct transposition to 4x2</li>
     * <li>Correct row-major data remapping</li>
     * <li>Display unit is preserved</li>
     * <li>Double transpose restores the original table</li>
     * </ul>
     */
    @Test
    @DisplayName("transpose(): rectangular 2x4 → 4x2, data and unit preserved")
    public void testTransposeRectangular()
    {
        /*
         * Original table (2x4), expressed in kilometers: [ 1 2 3 4 ] [ 5 6 7 8 ] SI values are meters: {1000..8000}
         */
        QuantityTable<Length> qt = QuantityTable.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8}, 2, 4, Length.Unit.km);

        // Sanity check original
        assertEquals(2, qt.rows());
        assertEquals(4, qt.cols());
        assertEquals(Length.Unit.km, qt.getDisplayUnit());
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000}, qt.si(), EPS);

        // Transpose: 4x2
        QuantityTable<Length> t = qt.transpose();

        assertEquals(4, t.rows());
        assertEquals(2, t.cols());

        // Display unit must be preserved
        assertEquals(qt.getDisplayUnit(), t.getDisplayUnit());

        /*
         * Expected transposed layout (4x2): [ 1 5 ] [ 2 6 ] [ 3 7 ] [ 4 8 ] Row‑major SI: {1000,5000, 2000,6000, 3000,7000,
         * 4000,8000}
         */
        assertArrayEquals(new double[] {1000, 5000, 2000, 6000, 3000, 7000, 4000, 8000}, t.si(), EPS);

        // Spot‑check element access
        assertEquals(1000.0, t.si(0, 0), EPS);
        assertEquals(5000.0, t.si(0, 1), EPS);
        assertEquals(4000.0, t.si(3, 0), EPS);
        assertEquals(8000.0, t.si(3, 1), EPS);

        // Double transpose should restore original
        QuantityTable<Length> tt = t.transpose();

        assertEquals(qt.rows(), tt.rows());
        assertEquals(qt.cols(), tt.cols());
        assertEquals(qt.getDisplayUnit(), tt.getDisplayUnit());
        assertArrayEquals(qt.si(), tt.si(), EPS);
    }

    // ------------------------------------------------------------------------------------
    // QuantityTable — Static factory methods of() and ofSi()
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link QuantityTable#of(double[], int, int, Unit)} for null handling, size validation, and SI conversion using
     * {@link Length}.
     */
    @Test
    @DisplayName("QuantityTable.of(double[], rows, cols, Unit): nulls, size, SI conversion")
    public void testOfDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> QuantityTable.of((double[]) null, 2, 3, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> QuantityTable.of(new double[6], 2, 3, null));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new double[5], 2, 3, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new double[6], 0, 3, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new double[6], 2, 0, Length.Unit.m));

        double[] inCm = {1, 2, 3, 4, 5, 6};
        QuantityTable<Length> qt = QuantityTable.of(inCm, 2, 3, Length.Unit.cm);

        assertEquals(2, qt.rows());
        assertEquals(3, qt.cols());
        assertArrayEquals(new double[] {0.01, 0.02, 0.03, 0.04, 0.05, 0.06}, qt.si(), EPS);
    }

    /**
     * Verify {@link QuantityTable#ofSi(double[], int, int, Unit)} for null handling, size validation, and display unit
     * preservation.
     */
    @Test
    @DisplayName("QuantityTable.ofSi(double[], rows, cols, Unit): nulls, size, display unit")
    public void testOfSiDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> QuantityTable.ofSi((double[]) null, 2, 2, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> QuantityTable.ofSi(new double[4], 2, 2, null));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.ofSi(new double[3], 2, 2, Length.Unit.m));

        double[] si = {1, 2, 3, 4};
        QuantityTable<Length> qt = QuantityTable.ofSi(si, 2, 2, Length.Unit.km);

        assertEquals(Length.Unit.km, qt.getDisplayUnit());
        assertArrayEquals(si, qt.si(), EPS);
    }

    /**
     * Verify {@link QuantityTable#of(Quantity[], int, int)} for null handling, size validation, and SI conversion.
     */
    @Test
    @DisplayName("QuantityTable.of(Q[], rows, cols): nulls, size, SI conversion")
    public void testOfQuantityArray()
    {
        assertThrows(NullPointerException.class, () -> QuantityTable.of((Length[]) null, 2, 2));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new Length[] {}, 2, 2));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new Length[] {Length.ofSi(1)}, 2, 2));

        Length[] data = {new Length(1, Length.Unit.km), new Length(2, Length.Unit.m), new Length(3, Length.Unit.cm),
                new Length(4, Length.Unit.mm)};

        QuantityTable<Length> qt = QuantityTable.of(data, 2, 2);

        assertEquals(1000.0, qt.si(0, 0), EPS);
        assertEquals(2.0, qt.si(0, 1), EPS);
        assertEquals(0.03, qt.si(1, 0), EPS);
        assertEquals(0.004, qt.si(1, 1), EPS);
    }

    /**
     * Verify {@link QuantityTable#ofSi(double[][], Unit)} for null handling, empty grids, ragged grids, and SI usage.
     */
    @Test
    @DisplayName("QuantityTable.ofSi(double[][], Unit): nulls, empty/ragged grids, SI values")
    public void testOfSiDoubleGrid()
    {
        assertThrows(NullPointerException.class, () -> QuantityTable.ofSi((double[][]) null, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> QuantityTable.ofSi(new double[2][2], null));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.ofSi(new double[][] {}, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.ofSi(new double[][] {{1, 2}, {3}}, Length.Unit.m));

        double[][] gridSi = {{1, 2, 3}, {4, 5, 6}};

        QuantityTable<Length> qt = QuantityTable.ofSi(gridSi, Length.Unit.m);

        assertEquals(2, qt.rows());
        assertEquals(3, qt.cols());
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, qt.si(), EPS);
    }

    /**
     * Verify {@link QuantityTable#of(double[][], Unit)} for SI conversion using {@link Duration}.
     */
    @Test
    @DisplayName("QuantityTable.of(double[][], Unit): rectangular grid & SI conversion (ms/h)")
    public void testOfDoubleGridWithUnit()
    {
        assertThrows(NullPointerException.class, () -> QuantityTable.of((double[][]) null, Duration.Unit.s));

        assertThrows(NullPointerException.class, () -> QuantityTable.of(new double[2][2], null));

        double[][] inHours = {{1, 2}, {3, 4}};

        QuantityTable<Duration> qt = QuantityTable.of(inHours, Duration.Unit.h);

        assertEquals(3600.0, qt.si(0, 0), EPS);
        assertEquals(7200.0, qt.si(0, 1), EPS);
        assertEquals(10_800.0, qt.si(1, 0), EPS);
        assertEquals(14_400.0, qt.si(1, 1), EPS);
    }

    /**
     * Verify {@link QuantityTable#of(Quantity[][])} for null handling, empty grids, and SI conversion.
     */
    @Test
    @DisplayName("QuantityTable.of(Q[][]): nulls, empty grids, SI conversion")
    public void testOfQuantityGrid()
    {
        assertThrows(NullPointerException.class, () -> QuantityTable.of((Duration[][]) null));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new Duration[][] {}));

        assertThrows(NullPointerException.class, () -> QuantityTable.of(new Duration[][] {null}));

        assertThrows(IllegalArgumentException.class, () -> QuantityTable.of(new Duration[][] {{}}));

        Duration[][] grid = {{new Duration(1, Duration.Unit.ms), new Duration(2, Duration.Unit.ms)},
                {new Duration(3, Duration.Unit.ms), new Duration(4, Duration.Unit.ms)}};

        QuantityTable<Duration> qt = QuantityTable.of(grid);

        assertEquals(0.001, qt.si(0, 0), EPS);
        assertEquals(0.002, qt.si(0, 1), EPS);
        assertEquals(0.003, qt.si(1, 0), EPS);
        assertEquals(0.004, qt.si(1, 1), EPS);
    }
}
