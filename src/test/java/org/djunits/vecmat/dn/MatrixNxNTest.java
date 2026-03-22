package org.djunits.vecmat.dn;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Area;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.Speed;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.d1.Matrix1x1;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.def.VectorMatrix;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MatrixNxN} (using n=4) with concrete quantity {@link Length} and unit
 * {@link org.djunits.quantity.Length.Unit}.
 * <p>
 * The tests encode intended behavior (display → SI conversion, unit algebra via {@link SIUnit#plus(SIUnit)} /
 * {@link SIUnit#add(SIUnit, SIUnit)}, etc.). They fail if the implementation deviates—by design.
 * <p>
 * <strong>Coverage:</strong>
 * <ul>
 * <li>Factories ({@code of(double[],U)}, {@code of(double[][],U)})</li>
 * <li>{@code instantiateSi(double[])}</li>
 * <li>Matrix basics: {@code rows()}, {@code cols()}, {@code value(r,c)}, {@code setDisplayUnit}, {@code toString}</li>
 * <li>Ops defaults: {@code add/sub (Q & VM)}, {@code negate}, {@code abs}, {@code scaleBy}, stats</li>
 * <li>SquareMatrixOps: {@code transpose}, {@code determinantScalar}, {@code determinant}, {@code trace}, {@code normFrobenius},
 * {@code isSymmetric} (± tol), {@code isSkewSymmetric} (± tol)</li>
 * <li>Inverse/adjugate (success + singular), matrixxmatrix, matrixxvector</li>
 * <li>Hadamard: {@code invertElements}, {@code multiplyElements}, {@code divideElements} (+ unit composition)</li>
 * <li>{@code as(targetUnit)} success/failure</li>
 * <li>Scalar extraction helpers: {@code getScalars}, {@code getRowScalars}, {@code getColumnScalars},
 * {@code getDiagonalScalars}</li>
 * <li>{@code equals/hashCode}</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class MatrixNxNTest
{
    /** Numerical tolerance for double comparisons. */
    private static final double EPS = 1.0E-12;

    /**
     * Build an NxN matrix from SI values with a given display unit.
     * @param si a flat n*n-element SI array in row-major order
     * @param unit display unit
     * @return matrix instance
     */
    private static MatrixNxN<Length, Length.Unit> ofSi4(final double[] si, final Length.Unit unit)
    {
        final double[] inDisplay = new double[si.length];
        for (int i = 0; i < si.length; i++)
        {
            inDisplay[i] = unit.fromBaseValue(si[i]);
        }
        return MatrixNxN.of(inDisplay, unit);
    }

    // ------------------------------------------------------------------------------------
    // Factories / constructors
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link MatrixNxN#of(double[], UnitInterface)} rejects nulls, wrong lengths, and converts to SI.
     */
    @Test
    @DisplayName("of(double[],U): nulls, length, SI conversion")
    public void testFactoryArray()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxN.of((double[]) null, Length.Unit.km));
        assertThrows(NullPointerException.class, () -> MatrixNxN.of(new double[16], null));
        assertThrows(IllegalArgumentException.class, () -> MatrixNxN.of(new double[15], Length.Unit.m));

        // 4x4 in km → SI m
        double[] inKm = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        MatrixNxN<Length, Length.Unit> m = MatrixNxN.of(inKm, Length.Unit.km);
        double[] expectedSi = new double[inKm.length];
        for (int i = 0; i < inKm.length; i++)
        {
            expectedSi[i] = 1000.0 * inKm[i];
        }
        assertArrayEquals(expectedSi, m.si(), EPS);
    }

    /**
     * Verify {@link MatrixNxN#of(double[][], UnitInterface)} checks 4x4 shape and converts to SI.
     */
    @Test
    @DisplayName("of(double[][],U): shape & SI conversion")
    public void testFactoryGrid()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxN.of((double[][]) null, Length.Unit.km));
        assertThrows(NullPointerException.class, () -> MatrixNxN.of(new double[4][4], null));
        assertThrows(IllegalArgumentException.class, () -> MatrixNxN.of(new double[0][0], Length.Unit.km));

        // Not square or wrong shape should throw
        assertThrows(IllegalArgumentException.class, () -> MatrixNxN.of(new double[][] {{1, 2}, {3, 4, 5}}, Length.Unit.m));

        double[][] inKm = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        MatrixNxN<Length, Length.Unit> m = MatrixNxN.of(inKm, Length.Unit.km);
        double[] si = m.si();
        for (double v : si)
        {
            assertTrue(v % 1000.0 == 0.0);
        }
    }

    /**
     * Verify {@link MatrixNxN#instantiateSi(double[])} preserves display unit and adopts SI values.
     */
    @Test
    @DisplayName("instantiateSi(double[]): display unit preserved, SI adopted")
    public void testInstantiateSi()
    {
        MatrixNxN<Length, Length.Unit> base =
                MatrixNxN.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.km);
        double[] newSi = {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        MatrixNxN<Length, Length.Unit> inst = base.instantiateSi(newSi);
        assertEquals(base.getDisplayUnit(), inst.getDisplayUnit());
        assertArrayEquals(newSi, inst.si(), EPS);

        MatrixNxN<SIQuantity, SIUnit> siMatrix = base.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siMatrix.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, siMatrix.si(), EPS, "si array used as-is");
        assertEquals(16.0, siMatrix.get(0, 0).si(), EPS);

        MatrixNxN<SIQuantity, SIUnit> siMatrixOf = MatrixNxN
                .of(new double[][] {{16, 15, 14, 13}, {12, 11, 10, 9}, {8, 7, 6, 5}, {4, 3, 2, 1}}, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siMatrixOf.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, siMatrixOf.si(), EPS, "si array used as-is");
        assertEquals(16.0, siMatrixOf.get(0, 0).si(), EPS);

        assertThrows(IllegalArgumentException.class,
                () -> base.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
        assertThrows(IllegalArgumentException.class,
                () -> MatrixNxN.of(new double[][] {{10.0, 11.0, 12.0, 13.0, 14.0}}, SIUnit.of("kgm/s2K")));
    }

    /**
     * Verify constructor.
     */
    @Test
    @DisplayName("MatrixNxN constructor")
    public void testConstructor()
    {
        var dataSi = new DenseDoubleDataSi(new double[][] {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}});
        assertThrows(IllegalArgumentException.class, () -> new MatrixNxN<>(dataSi, Length.Unit.m));

        var dataSi2x2 = new DenseDoubleDataSi(new double[][] {{1.0, 2.0}, {3.0, 4.0}});
        var mat = new MatrixNxN<>(dataSi2x2, Length.Unit.m);
        assertEquals(4.0, mat.msi(2, 2));
    }

    // ------------------------------------------------------------------------------------
    // Basics: rows/cols/value/isRelative, setDisplayUnit, toString
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link VectorMatrix#rows()}, {@link VectorMatrix#cols()}, {@link VectorMatrix#get(int, int)}, and
     * {@link VectorMatrix#isRelative()}.
     */
    @Test
    @DisplayName("rows/cols/value/isRelative")
    public void testShapeAndValue()
    {
        MatrixNxN<Length, Length.Unit> m =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.m);
        assertEquals(4, m.rows());
        assertEquals(4, m.cols());
        assertEquals(1.0, m.get(0, 0).si(), EPS);
        assertTrue(m.isRelative());

        assertEquals(16, m.nnz());
        MatrixNxN<Length, Length.Unit> m0 = ofSi4(new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, Length.Unit.m);
        assertEquals(0, m0.nnz());
        MatrixNxN<Length, Length.Unit> m6 =
                ofSi4(new double[] {0, 0, 0, 0, -1, -0.0, Double.NaN, Double.POSITIVE_INFINITY, 0.0, 6, 0.0, -8, 90, 0, 0, 0},
                        Length.Unit.m);
        assertEquals(6, m6.nnz());
    }

    /**
     * Verify that {@link VectorMatrix#setDisplayUnit(UnitInterface)} only affects presentation (not SI storage).
     */
    @Test
    @DisplayName("setDisplayUnit() only changes presentation")
    public void testSetDisplayUnit()
    {
        MatrixNxN<Length, Length.Unit> m = ofSi4(new double[] {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000,
                11000, 12000, 13000, 14000, 15000, 16000}, Length.Unit.km);
        m.setDisplayUnit(Length.Unit.m);
        assertEquals(Length.Unit.m, m.getDisplayUnit());
        assertEquals(1000.0, m.get(0, 0).si(), EPS);
        m.setDisplayUnit(Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
    }

    /**
     * Verify {@link VectorMatrix#toString()} and {@link VectorMatrix#toString(UnitInterface)} include unit abbreviation.
     */
    @Test
    @DisplayName("toString()/toString(unit) include unit")
    public void testToString()
    {
        MatrixNxN<Length, Length.Unit> m =
                MatrixNxN.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.km);
        assertTrue(m.toString().contains("km"));
        assertTrue(m.toString(Length.Unit.m).contains("m"));
    }

    // ------------------------------------------------------------------------------------
    // Ops defaults: add/sub (Q & VM), negate/abs/scaleBy, stats
    // ------------------------------------------------------------------------------------

    /**
     * Verify vector/matrix algebra defaults and statistics.
     */
    @Test
    @DisplayName("add/sub (Q & VM), negate/abs/scaleBy, stats")
    public void testAlgebraAndStats()
    {
        MatrixNxN<Length, Length.Unit> a =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.m);
        MatrixNxN<Length, Length.Unit> b =
                ofSi4(new double[] {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, Length.Unit.m);
        Length inc = Length.ofSi(2.0);

        assertArrayEquals(new double[] {17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17}, a.add(b).si(), EPS);
        assertArrayEquals(new double[] {-15, -13, -11, -9, -7, -5, -3, -1, 1, 3, 5, 7, 9, 11, 13, 15}, a.subtract(b).si(), EPS);
        assertArrayEquals(new double[] {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}, a.add(inc).si(), EPS);
        assertArrayEquals(new double[] {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, a.subtract(inc).si(), EPS);

        assertArrayEquals(new double[] {-1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16}, a.negate().si(),
                EPS);
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, a.abs().si(), EPS);
        assertArrayEquals(new double[] {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32}, a.scaleBy(2.0).si(), EPS);

        // Stats on 1..16
        assertEquals(8.5, a.mean().si(), EPS);
        assertEquals(8.5, a.median().si(), EPS);
        assertEquals(1.0, a.min().si(), EPS);
        assertEquals(16.0, a.max().si(), EPS);
        assertEquals(136.0, a.sum().si(), EPS);
    }

    // ------------------------------------------------------------------------------------
    // SquareMatrixOps: transpose, determinant/trace/Frobenius, symmetry
    // ------------------------------------------------------------------------------------

    /**
     * Verify transpose, determinant/trace/normFrobenius, symmetry checks.
     */
    @Test
    @DisplayName("transpose, determinant/trace/Frobenius, symmetry")
    public void testSquareOps()
    {
        MatrixNxN<Length, Length.Unit> m =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.m);

        MatrixNxN<Length, Length.Unit> t = m.transpose();
        assertArrayEquals(new double[] {1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15, 4, 8, 12, 16}, t.si(), EPS);
        assertEquals(m.getDisplayUnit(), t.getDisplayUnit());

        assertEquals(0.0, m.determinantSi(), EPS); // singular
        assertEquals(34.0, m.trace().si(), EPS); // 1+6+11+16

        double sumSqr = 0.0;
        for (int i = 1; i <= 16; i++)
        {
            sumSqr += i * i;
        }
        assertEquals(Math.sqrt(sumSqr), m.normFrobenius().si(), EPS);

        MatrixNxN<Length, Length.Unit> sym =
                ofSi4(new double[] {1, 2, 3, 4, 2, 5, 6, 7, 3, 6, 8, 9, 4, 7, 9, 10}, Length.Unit.m);
        assertTrue(sym.isSymmetric());
        assertFalse(sym.isSkewSymmetric());
    }

    // ------------------------------------------------------------------------------------
    // Inverse/adjugate, matrixxmatrix, matrixxvector
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link MatrixNxN#inverse()} throws on singular and {@link MatrixNxN#adjugate()} shape.
     */
    @Test
    @DisplayName("inverse(): singular throws; adjugate() exists")
    public void testInverseAdjugate()
    {
        MatrixNxN<Length, Length.Unit> singular =
                ofSi4(new double[] {1, 2, 3, 4, 2, 4, 6, 8, 3, 6, 9, 12, 4, 8, 12, 16}, Length.Unit.m);
        assertThrows(NonInvertibleMatrixException.class, singular::inverse);

        MatrixNxN<Length, Length.Unit> diag =
                ofSi4(new double[] {2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4, 0, 0, 0, 0, 5}, Length.Unit.m);
        assertDoesNotThrow(diag::inverse);
        assertEquals(4, diag.adjugate().rows());
    }

    /**
     * Verify matrixxmatrix and matrixxvector multiplication with unit composition.
     */
    @Test
    @DisplayName("matrix x matrix and matrix x vector")
    public void testMultiply()
    {
        MatrixNxN<Length, Length.Unit> a = ofSi4(new double[] {1, 2, 3, 4, 0, 1, 0, 0, 0, 0, 1, 0, 5, 6, 7, 8}, Length.Unit.m);
        MatrixNxN<Length, Length.Unit> b =
                MatrixNxN.of(new double[] {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4}, Length.Unit.km);

        MatrixNxN<SIQuantity, SIUnit> c = a.multiply(b);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), c.getDisplayUnit());

        // A·v (size 4): create v in km → SI [1000,2000,3000,4000]
        VectorN.Col<Length, Length.Unit> v = VectorN.Col.ofSi(new double[] {1000, 2000, 3000, 4000}, Length.Unit.km);
        VectorN.Col<SIQuantity, SIUnit> r = a.multiply(v);
        assertEquals(4, r.size());
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), r.getDisplayUnit());

        // A·v (size 3): should give exception
        VectorN.Col<Length, Length.Unit> v3 = VectorN.Col.ofSi(new double[] {1000, 2000, 3000}, Length.Unit.km);
        assertThrows(IllegalArgumentException.class, () -> a.multiply(v3));
    }

    // ------------------------------------------------------------------------------------
    // Hadamard: invert/multiply/divide
    // ------------------------------------------------------------------------------------

    /**
     * Verify element-wise Hadamard operations and unit composition.
     */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition")
    public void testHadamard()
    {
        MatrixNxN<Length, Length.Unit> a =
                ofSi4(new double[] {2, 4, 5, 10, 20, 40, 8, 16, 32, 64, 3, 6, 12, 24, 48, 96}, Length.Unit.m);
        MatrixNxN<Length, Length.Unit> b =
                MatrixNxN.of(new double[] {1, 2, 0.5, 4, 0.25, 8, 0.125, 0.5, 4, 2, 6, 3, 0.5, 1, 0.25, 0.125}, Length.Unit.km);

        MatrixNxN<SIQuantity, SIUnit> inv = a.invertEntries();
        assertEquals(0.5, inv.si()[0], EPS);

        MatrixNxN<SIQuantity, SIUnit> mul = a.multiplyEntries(b);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());

        MatrixNxN<SIQuantity, SIUnit> div = a.divideEntries(b);
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit());
    }

    // ------------------------------------------------------------------------------------
    // as(targetUnit) and scalar extraction helpers
    // ------------------------------------------------------------------------------------

    /**
     * Verify {@link MatrixNxN#as(UnitInterface)} success (m↔km) and failure (dimension mismatch).
     */
    @Test
    @DisplayName("as(targetUnit) success/failure")
    public void testAs()
    {
        MatrixNxN<Length, Length.Unit> m =
                MatrixNxN.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.km);
        MatrixNxN<Length, Length.Unit> asM = m.as(Length.Unit.m);
        assertEquals(Length.Unit.m, asM.getDisplayUnit());
        assertArrayEquals(m.si(), asM.si(), EPS);
        SIUnit second = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> m.as(second));
    }

    /**
     * Verify scalar extraction helpers: {@code getScalars}, {@code getRowScalars}, {@code getColumnScalars}, and
     * {@code getDiagonalScalars}.
     */
    @Test
    @DisplayName("getScalars / getRowScalars / getColumnScalars / getDiagonalScalars")
    public void testScalarExtraction()
    {
        MatrixNxN<Length, Length.Unit> m =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.m);
        MatrixNxN<Length, Length.Unit> mhm =
                ofSi4(new double[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160}, Length.Unit.hm);

        Length[][] q = m.getScalarGrid();
        assertEquals(4, q.length);
        assertEquals(4, q[0].length);
        assertEquals(1.0, q[0][0].si(), EPS);
        assertEquals(16.0, q[3][3].si(), EPS);
        Length[][] qhm = mhm.getScalarGrid();
        assertEquals(4, qhm.length);
        assertEquals(4, qhm[3].length);
        assertEquals(10.0, qhm[0][0].si(), EPS);
        assertEquals(160.0, qhm[3][3].si(), EPS);
        assertEquals(Length.Unit.hm, qhm[2][2].getDisplayUnit());

        double[][] sigrid = m.getSiGrid();
        double[][] sihm = mhm.getSiGrid();
        assertEquals(4, sigrid.length);
        assertEquals(4, sigrid[0].length);
        assertEquals(4, sigrid[1].length);
        assertEquals(4, sigrid[2].length);
        assertEquals(1.0, sigrid[0][0], EPS);
        assertEquals(6.0, sigrid[1][1], EPS);
        assertEquals(16.0, sigrid[3][3], EPS);
        assertEquals(10.0, sihm[0][0], EPS);
        assertEquals(60.0, sihm[1][1], EPS);
        assertEquals(160.0, sihm[3][3], EPS);

        Length[] row2 = m.getRowScalars(2);
        Length[] col3 = m.getColumnScalars(3);
        Length[] diag = m.getDiagonalScalars();
        assertEquals(4, row2.length);
        assertEquals(9.0, row2[0].si(), EPS);
        assertEquals(12.0, row2[3].si(), EPS);
        assertEquals(4, col3.length);
        assertEquals(4.0, col3[0].si(), EPS);
        assertEquals(16.0, col3[3].si(), EPS);
        assertEquals(4, diag.length);
        assertEquals(1.0, diag[0].si(), EPS);
        assertEquals(16.0, diag[3].si(), EPS);

        Length[] mRow3 = m.mgetRowScalars(3);
        Length[] mCol4 = m.mgetColumnScalars(4);
        assertEquals(4, mRow3.length);
        assertEquals(9.0, mRow3[0].si(), EPS);
        assertEquals(12.0, mRow3[3].si(), EPS);
        assertEquals(4, mCol4.length);
        assertEquals(4.0, mCol4[0].si(), EPS);
        assertEquals(16.0, mCol4[3].si(), EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowScalars(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnScalars(4));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowScalars(5));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnScalars(5));
    }

    /**
     * Verify SI extraction helpers: {@code getRowSi}, {@code getColumnSi}, and {@code getDiagonalSi}.
     */
    @Test
    @DisplayName("getRowSi / getColumnSi / getDiagonalSi")
    public void testSiArrayExtraction()
    {
        MatrixNxN<Length, Length.Unit> m =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.m);

        Length[][] q = m.getScalarGrid();
        assertEquals(4, q.length);
        assertEquals(4, q[0].length);
        assertEquals(1.0, q[0][0].si(), EPS);
        assertEquals(16.0, q[3][3].si(), EPS);

        double[] row2 = m.getRowSi(2);
        double[] col3 = m.getColumnSi(3);
        double[] diag = m.getDiagonalSi();
        assertEquals(4, row2.length);
        assertEquals(9.0, row2[0], EPS);
        assertEquals(12.0, row2[3], EPS);
        assertEquals(4, col3.length);
        assertEquals(4.0, col3[0], EPS);
        assertEquals(16.0, col3[3], EPS);
        assertEquals(4, diag.length);
        assertEquals(1.0, diag[0], EPS);
        assertEquals(16.0, diag[3], EPS);

        double[] mRow3 = m.mgetRowSi(3);
        double[] mCol4 = m.mgetColumnSi(4);
        assertEquals(4, mRow3.length);
        assertEquals(9.0, mRow3[0], EPS);
        assertEquals(12.0, mRow3[3], EPS);
        assertEquals(4, mCol4.length);
        assertEquals(4.0, mCol4[0], EPS);
        assertEquals(16.0, mCol4[3], EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowSi(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnSi(4));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowSi(5));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnSi(5));
    }

    /**
     * Verify scalar extraction helpers: {@code getRowVector}, {@code getColumnVector}, and {@code getDiagonalVector}.
     */
    @Test
    @DisplayName("getRowVector / getColumnVector / getDiagonalVector")
    public void testVectorExtraction()
    {
        MatrixNxN<Length, Length.Unit> m =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.m);

        Length[][] q = m.getScalarGrid();
        assertEquals(4, q.length);
        assertEquals(4, q[0].length);
        assertEquals(1.0, q[0][0].si(), EPS);
        assertEquals(16.0, q[3][3].si(), EPS);

        VectorN.Row<Length, Length.Unit> row2 = m.getRowVector(2);
        VectorN.Col<Length, Length.Unit> col3 = m.getColumnVector(3);
        VectorN.Col<Length, Length.Unit> diag = m.getDiagonalVector();
        assertEquals(4, row2.size());
        assertEquals(9.0, row2.get(0).si(), EPS);
        assertEquals(12.0, row2.get(3).si(), EPS);
        assertEquals(4, col3.size());
        assertEquals(4.0, col3.get(0).si(), EPS);
        assertEquals(16.0, col3.get(3).si(), EPS);
        assertEquals(4, diag.size());
        assertEquals(1.0, diag.get(0).si(), EPS);
        assertEquals(16.0, diag.get(3).si(), EPS);

        VectorN.Row<Length, Length.Unit> mRow3 = m.mgetRowVector(3);
        VectorN.Col<Length, Length.Unit> mCol4 = m.mgetColumnVector(4);
        assertEquals(4, mRow3.size());
        assertEquals(9.0, mRow3.get(0).si(), EPS);
        assertEquals(12.0, mRow3.get(3).si(), EPS);
        assertEquals(4, mCol4.size());
        assertEquals(4.0, mCol4.get(0).si(), EPS);
        assertEquals(16.0, mCol4.get(3).si(), EPS);

        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getRowVector(4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> m.getColumnVector(4));

        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetRowVector(5));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.mgetColumnVector(5));
    }

    /**
     * Verify {@code equals/hashCode} for equal and different data.
     */
    @Test
    @DisplayName("equals / hashCode")
    public void testEqualsHash()
    {
        MatrixNxN<Length, Length.Unit> a1 =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.m);
        MatrixNxN<Length, Length.Unit> a2 =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, Length.Unit.m);
        MatrixNxN<Length, Length.Unit> b =
                ofSi4(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17}, Length.Unit.m);

        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1, b);
        assertNotEquals(a1, null);
        assertNotEquals(a1, "other");
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        MatrixNxN<Length, Length.Unit> r =
                MatrixNxN.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        MatrixNxN<Speed, Speed.Unit> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1, 1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.mget(1, 2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.mget(1, 3).getInUnit(), 1E-6);
        assertEquals(2.0, sr.mget(2, 1).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxN — asMatrix1x1 (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asMatrix1x1()} preserves SI and display unit for a 1x1 matrix, and throws
     * {@link IllegalStateException} for other N (N=2 and N=3).
     * <p>
     * Note: The current implementation appears to have a bug: it checks {@code cols() != 2} instead of {@code cols() != 1},
     * which will make this test fail until fixed.
     */
    @Test
    @DisplayName("MatrixNxN: asMatrix1x1 preserves SI & unit for 1x1; throws for N≠1")
    public void testAsMatrix1x1()
    {
        // Happy path: 1x1 with centimeters (3 cm -> 0.03 m)
        MatrixNxN<Length, Length.Unit> n11cm = ofSi4(new double[] {0.03}, Length.Unit.cm);
        assertEquals(Length.Unit.cm, n11cm.getDisplayUnit());
        assertEquals(1, n11cm.si().length);
        assertEquals(0.03, n11cm.si()[0], EPS);
        assertEquals(0.03, n11cm.si(0, 0), EPS);

        Matrix1x1<Length, Length.Unit> fixed11 = n11cm.asMatrix1x1();
        assertEquals(Length.Unit.cm, fixed11.getDisplayUnit());
        double[] si11 = fixed11.si();
        assertEquals(1, si11.length);
        assertEquals(0.03, si11[0], EPS);
        assertEquals(0.03, fixed11.si(0, 0), EPS);

        // Bad paths: N ≠ 1 (cannot independently vary rows/cols for NxN)
        MatrixNxN<Length, Length.Unit> n22m = ofSi4(new double[] {1.0, 2.0, 3.0, 4.0}, Length.Unit.m);
        MatrixNxN<Length, Length.Unit> n33km =
                ofSi4(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0, 7000.0, 8000.0, 9000.0}, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> n22m.asMatrix1x1());
        assertThrows(IllegalStateException.class, () -> n33km.asMatrix1x1());
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxN — asMatrix2x2 (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asMatrix2x2()} preserves SI and display unit for a 2x2 matrix, and throws
     * {@link IllegalStateException} for other N (tested with N=1 and N=3).
     */
    @Test
    @DisplayName("MatrixNxN: asMatrix2x2 preserves SI & unit for 2x2; throws for N≠2")
    public void testAsMatrix2x2()
    {
        // Happy path: 2x2 with meters
        MatrixNxN<Length, Length.Unit> n22m = ofSi4(new double[] {1.0, 2.0, 3.0, 4.0}, Length.Unit.m);
        assertEquals(Length.Unit.m, n22m.getDisplayUnit());
        assertEquals(4, n22m.si().length);
        assertEquals(1.0, n22m.si(0, 0), EPS);
        assertEquals(2.0, n22m.si(0, 1), EPS);
        assertEquals(3.0, n22m.si(1, 0), EPS);
        assertEquals(4.0, n22m.si(1, 1), EPS);

        Matrix2x2<Length, Length.Unit> fixed22 = n22m.asMatrix2x2();
        assertEquals(Length.Unit.m, fixed22.getDisplayUnit());
        double[] si22 = fixed22.si();
        assertEquals(4, si22.length);
        assertEquals(1.0, si22[0], EPS);
        assertEquals(2.0, si22[1], EPS);
        assertEquals(3.0, si22[2], EPS);
        assertEquals(4.0, si22[3], EPS);
        assertEquals(1.0, fixed22.si(0, 0), EPS);
        assertEquals(4.0, fixed22.si(1, 1), EPS);

        // Bad paths: N ≠ 2
        MatrixNxN<Length, Length.Unit> n11cm = ofSi4(new double[] {0.03}, Length.Unit.cm);
        MatrixNxN<Length, Length.Unit> n33km =
                ofSi4(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0, 7000.0, 8000.0, 9000.0}, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> n11cm.asMatrix2x2());
        assertThrows(IllegalStateException.class, () -> n33km.asMatrix2x2());
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxN — asMatrix3x3 (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asMatrix3x3()} preserves SI and display unit for a 3x3 matrix, and throws
     * {@link IllegalStateException} for other N (tested with N=2 and N=4).
     */
    @Test
    @DisplayName("MatrixNxN: asMatrix3x3 preserves SI & unit for 3x3; throws for N≠3")
    public void testAsMatrix3x3()
    {
        // Happy path: 3x3 with kilometers (SI values are meters)
        MatrixNxN<Length, Length.Unit> n33km =
                ofSi4(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0, 7000.0, 8000.0, 9000.0}, Length.Unit.km);
        assertEquals(Length.Unit.km, n33km.getDisplayUnit());
        assertEquals(9, n33km.si().length);
        assertEquals(1000.0, n33km.si(0, 0), EPS);
        assertEquals(3000.0, n33km.si(0, 2), EPS);
        assertEquals(7000.0, n33km.si(2, 0), EPS);
        assertEquals(9000.0, n33km.si(2, 2), EPS);

        Matrix3x3<Length, Length.Unit> fixed33 = n33km.asMatrix3x3();
        assertEquals(Length.Unit.km, fixed33.getDisplayUnit());
        double[] si33 = fixed33.si();
        assertEquals(9, si33.length);
        assertEquals(1000.0, si33[0], EPS);
        assertEquals(2000.0, si33[1], EPS);
        assertEquals(3000.0, si33[2], EPS);
        assertEquals(4000.0, si33[3], EPS);
        assertEquals(5000.0, si33[4], EPS);
        assertEquals(6000.0, si33[5], EPS);
        assertEquals(7000.0, si33[6], EPS);
        assertEquals(8000.0, si33[7], EPS);
        assertEquals(9000.0, si33[8], EPS);
        assertEquals(1000.0, fixed33.si(0, 0), EPS);
        assertEquals(9000.0, fixed33.si(2, 2), EPS);

        // Bad paths: N ≠ 3
        MatrixNxN<Length, Length.Unit> n22m = ofSi4(new double[] {1.0, 2.0, 3.0, 4.0}, Length.Unit.m);
        MatrixNxN<Length, Length.Unit> n44m =
                ofSi4(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0},
                        Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> n22m.asMatrix3x3());
        assertThrows(IllegalStateException.class, () -> n44m.asMatrix3x3());
    }
}
