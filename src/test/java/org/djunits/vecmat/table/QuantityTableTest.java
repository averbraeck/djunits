package org.djunits.vecmat.table;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Length;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.storage.DataGrid;
import org.djunits.vecmat.storage.DenseDoubleData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link QuantityTable}. This test suite complements the full coverage of
 * {@link org.djunits.vecmat.DataGridMatrix} by testing all behavior specifically introduced in {@code QuantityTable}. <br>
 * <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class QuantityTableTest
{
    /**
     * Helper: create a simple 2x2 QuantityTable with Length quantities in meters.
     * @return 2x2 table with values {1,2,3,4} m
     */
    private static QuantityTable<Length, Length.Unit> create2x2()
    {
        double[] si = new double[] {1, 2, 3, 4};
        DataGrid<?> dg = new DenseDoubleData(si, 2, 2);
        return new QuantityTable<>(dg, Length.Unit.m);
    }

    /**
     * Helper: create a simple 3x3 QuantityTable.
     * @return 3x3 table with values 1..9 m
     */
    private static QuantityTable<Length, Length.Unit> create3x3()
    {
        double[] si = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        return new QuantityTable<>(new DenseDoubleData(si, 3, 3), Length.Unit.m);
    }

    /**
     * Helper: create a non-square 2x3 QuantityTable.
     * @return table 2x3 with values 1..6 m
     */
    private static QuantityTable<Length, Length.Unit> create2x3()
    {
        double[] si = new double[] {1, 2, 3, 4, 5, 6};
        return new QuantityTable<>(new DenseDoubleData(si, 2, 3), Length.Unit.m);
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
        QuantityTable<Length, Length.Unit> q = create2x2();
        assertEquals(2, q.rows());
        assertEquals(2, q.cols());
        assertEquals(Length.Unit.m, q.getDisplayUnit());
        assertNotNull(q.getDataGrid());
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
        QuantityTable<Length, Length.Unit> q = create2x2();
        double[] newSi = new double[] {10, 20, 30, 40};

        QuantityTable<Length, Length.Unit> q2 = q.instantiateSi(newSi);

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
        QuantityTable<Length, Length.Unit> q =
                new QuantityTable<>(new DenseDoubleData(new double[] {1, 2, 4, 5}, 2, 2), Length.Unit.m);

        QuantityTable<?, ?> inv = q.invertElements();
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
        QuantityTable<Length, Length.Unit> a =
                new QuantityTable<>(new DenseDoubleData(new double[] {1, 2, 3, 4}, 2, 2), Length.Unit.m);

        QuantityTable<Length, Length.Unit> b =
                new QuantityTable<>(new DenseDoubleData(new double[] {10, 20, 30, 40}, 2, 2), Length.Unit.m);

        QuantityTable<?, ?> result = a.multiplyElements(b);

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
        QuantityTable<Length, Length.Unit> a =
                new QuantityTable<>(new DenseDoubleData(new double[] {100, 50, 20, 10}, 2, 2), Length.Unit.m);

        QuantityTable<Length, Length.Unit> b =
                new QuantityTable<>(new DenseDoubleData(new double[] {10, 5, 2, 1}, 2, 2), Length.Unit.m);

        QuantityTable<?, ?> result = a.divideElements(b);

        assertArrayEquals(new double[] {10, 10, 10, 10}, result.si(), 1e-12);
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
        QuantityTable<Length, Length.Unit> q = create2x2();
        QuantityTable<Length, Length.Unit> converted = q.as(Length.Unit.km);

        assertNotSame(q, converted);
        assertEquals(Length.Unit.km, converted.getDisplayUnit());
        assertArrayEquals(q.si(), converted.si(), 1e-12);

        // Units NOT matching → error
        assertThrows(IllegalArgumentException.class, () -> q.as(SIUnit.of("s"))); // dimension mismatch: meter vs second
    }

    // ----------------------------------------------------------------------
    // asMatrix2x2()
    // ----------------------------------------------------------------------

    /**
     * Test asMatrix2x2(): only valid for 2x2 tables.
     */
    @Test
    @DisplayName("asMatrix2x2(): success for 2x2, failure otherwise")
    public void testAsMatrix2x2()
    {
        QuantityTable<Length, Length.Unit> q = create2x2();
        Matrix2x2<Length, Length.Unit> m2 = q.asMatrix2x2();

        assertArrayEquals(q.si(), m2.si(), 1e-12);
        assertEquals(q.getDisplayUnit(), m2.getDisplayUnit());

        // Non-2x2
        QuantityTable<Length, Length.Unit> non2 =
                new QuantityTable<>(new DenseDoubleData(new double[] {1, 2, 3}, 3, 1), Length.Unit.m);
        assertThrows(IllegalStateException.class, non2::asMatrix2x2);
    }

    // ----------------------------------------------------------------------
    // asMatrix3x3()
    // ----------------------------------------------------------------------

    /**
     * Test asMatrix3x3(): only valid for 3x3 tables.
     */
    @Test
    @DisplayName("asMatrix3x3(): success for 3x3, failure otherwise")
    public void testAsMatrix3x3()
    {
        QuantityTable<Length, Length.Unit> q = create3x3();
        Matrix3x3<Length, Length.Unit> m3 = q.asMatrix3x3();
        assertArrayEquals(q.si(), m3.si(), 1e-12);

        QuantityTable<Length, Length.Unit> non3 = create2x2();
        assertThrows(IllegalStateException.class, non3::asMatrix3x3);
    }

    // ----------------------------------------------------------------------
    // asMatrixNxN()
    // ----------------------------------------------------------------------

    /**
     * Test asMatrixNxN(): only valid for square tables.
     */
    @Test
    @DisplayName("asMatrixNxN(): square only")
    public void testAsMatrixNxN()
    {
        QuantityTable<Length, Length.Unit> q = create3x3();
        MatrixNxN<Length, Length.Unit> nxn = q.asMatrixNxN();
        assertArrayEquals(q.si(), nxn.si(), 1e-12);

        assertThrows(IllegalStateException.class, () -> create2x3().asMatrixNxN());
    }

    // ----------------------------------------------------------------------
    // asMatrixNxM()
    // ----------------------------------------------------------------------

    /**
     * Test asMatrixNxM(): always valid.
     */
    @Test
    @DisplayName("asMatrixNxM(): always succeeds")
    public void testAsMatrixNxM()
    {
        QuantityTable<Length, Length.Unit> q = create2x3();
        MatrixNxM<Length, Length.Unit> m = q.asMatrixNxM();

        assertEquals(q.rows(), m.rows());
        assertEquals(q.cols(), m.cols());
        assertArrayEquals(q.si(), m.si(), 1e-12);
        assertEquals(q.getDisplayUnit(), m.getDisplayUnit());
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
        QuantityTable<Length, Length.Unit> qtKm = new QuantityTable<>(new DenseDoubleData(si, 2, 2), Length.Unit.km);

        // ----------------------------------------------------------------------
        // 1. asMatrixNxM() must preserve SI and assign displayUnit correctly
        // ----------------------------------------------------------------------
        MatrixNxM<Length, Length.Unit> mxm = qtKm.asMatrixNxM();
        assertArrayEquals(si, mxm.si(), 1e-12, "asMatrixNxM(): SI values must be preserved exactly");
        assertEquals(Length.Unit.km, mxm.getDisplayUnit(), "asMatrixNxM(): display unit must match the QuantityTable");

        // ----------------------------------------------------------------------
        // 2. asMatrix2x2() must also preserve SI and display units
        // ----------------------------------------------------------------------
        Matrix2x2<Length, Length.Unit> m2 = qtKm.asMatrix2x2();
        assertArrayEquals(si, m2.si(), 1e-12, "asMatrix2x2(): SI values must be preserved exactly");
        assertEquals(Length.Unit.km, m2.getDisplayUnit(), "asMatrix2x2(): display unit must match the QuantityTable");

        // ----------------------------------------------------------------------
        // 3. Create a 3x3 km-table for asMatrix3x3 and asMatrixNxN
        // ----------------------------------------------------------------------
        double[] si9 = new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000};
        QuantityTable<Length, Length.Unit> qtKm9 = new QuantityTable<>(new DenseDoubleData(si9, 3, 3), Length.Unit.km);

        Matrix3x3<Length, Length.Unit> m3 = qtKm9.asMatrix3x3();
        assertArrayEquals(si9, m3.si(), 1e-12, "asMatrix3x3(): SI values must be preserved for 3x3");
        assertEquals(Length.Unit.km, m3.getDisplayUnit(), "asMatrix3x3(): display unit must match the QuantityTable");

        MatrixNxN<Length, Length.Unit> mxn = qtKm9.asMatrixNxN();
        assertArrayEquals(si9, mxn.si(), 1e-12, "asMatrixNxN(): SI values must be preserved for square matrices");
        assertEquals(Length.Unit.km, mxn.getDisplayUnit(), "asMatrixNxN(): display unit must match the QuantityTable");

        // ----------------------------------------------------------------------
        // 4. as(targetUnit): SI values must remain identical, displayUnit replaced
        // ----------------------------------------------------------------------
        QuantityTable<Length, Length.Unit> qtKmToM = qtKm.as(Length.Unit.m);

        assertArrayEquals(si, qtKmToM.si(), 1e-12, "as(unit): SI values must not change");
        assertEquals(Length.Unit.m, qtKmToM.getDisplayUnit(), "as(unit): display unit must be replaced but SI preserved");

        // ----------------------------------------------------------------------
        // 5. A negative test: mismatching SI units must throw
        // (Length km → seconds does not match)
        // ----------------------------------------------------------------------
        assertThrows(IllegalArgumentException.class, () -> qtKm.as(org.djunits.unit.si.SIUnit.of("s")),
                "as(): mismatching SI unit must throw IllegalArgumentException");
    }

}
