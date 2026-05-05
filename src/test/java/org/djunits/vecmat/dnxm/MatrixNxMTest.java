package org.djunits.vecmat.dnxm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Area;
import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
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
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djunits.vecmat.storage.DenseFloatDataSi;
import org.djunits.vecmat.storage.SparseDoubleDataSi;
import org.djunits.vecmat.storage.SparseFloatDataSi;
import org.djunits.vecmat.table.QuantityTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MatrixNxM} with concrete quantity {@link Length} and unit {@link org.djunits.quantity.Length.Unit}.
 * <p>
 * Tests cover factories, {@code instantiateSi}, algebra/stats (defaults), Hadamard ops, matrixxmatrix, matrixxvector, “as”
 * conversions to square matrices and vectors, scalar extraction helpers, equals/hashCode, and display-unit behavior. Copyright
 * (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See for
 * project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is distributed
 * under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class MatrixNxMTest
{
    /** Numerical tolerance. */
    private static final double EPS = 1.0E-12;

    /**
     * Helper to construct a MatrixNxM from SI values with a display unit.
     * @param si row-major SI values
     * @param rows number of rows
     * @param cols number of cols
     * @param displayUnit display unit
     * @return matrix
     */
    private static MatrixNxM<Length> ofSi(final double[] si, final int rows, final int cols, final Length.Unit displayUnit)
    {
        return MatrixNxM.ofSi(si, rows, cols, displayUnit);
    }

    // ------------------------------------------------------------------------------------
    // Factories / constructors
    // ------------------------------------------------------------------------------------

    /** Verify of(double[], rows, cols, U) rejects nulls/bad sizes and converts display→SI. */
    @Test
    @DisplayName("of(double[],rows,cols,U): nulls/bad sizes/SI convert")
    public void testFactoryArray()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxM.of((double[]) null, 3, 2, Length.Unit.km));
        assertThrows(NullPointerException.class, () -> MatrixNxM.of(new double[6], 3, 2, null));
        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new double[5], 3, 2, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new double[6], 0, 2, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new double[6], 3, 0, Length.Unit.m));

        // 3x2 in km → SI
        MatrixNxM<Length> m = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000}, m.getSiArray(), EPS);
        assertEquals(3, m.rows());
        assertEquals(2, m.cols());
    }

    /** Verify of(double[][],U) checks rectangular shape and converts display→SI. */
    @Test
    @DisplayName("of(double[][],U): rectangular shape & SI convert")
    public void testFactoryGrid()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxM.of((double[][]) null, Length.Unit.km));
        assertThrows(NullPointerException.class, () -> MatrixNxM.of(new double[][] {{1, 2}, {3, 4}}, null));
        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new double[][] {}, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new double[][] {{}, {1}}, Length.Unit.m));

        // 2x3 in km
        MatrixNxM<Length> m = MatrixNxM.of(new double[][] {{1, 2, 3}, {4, 5, 6}}, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000}, m.getSiArray(), EPS);
        assertEquals(2, m.rows());
        assertEquals(3, m.cols());
    }

    /** Verify of(Q[][],U) accepts per-cell units via DenseDoubleData and sets display unit. */
    @Test
    @DisplayName("of(Q[][],U): quantity grid accepted")
    public void testFactoryQuantityGrid()
    {
        Length[][] q = new Length[][] {{Length.of(1.0, "km"), Length.of(200.0, "m")}};
        MatrixNxM<Length> m = MatrixNxM.of(q).setDisplayUnit(Length.Unit.m);
        assertArrayEquals(new double[] {1000.0, 200.0}, m.getSiArray(), EPS);
        assertEquals(1, m.rows());
        assertEquals(2, m.cols());
    }

    /** Verify instantiateSi preserves display unit and adopts provided SI. */
    @Test
    @DisplayName("instantiateSi: preserve display unit, adopt SI")
    public void testInstantiateSi()
    {
        MatrixNxM<Length> base = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.km);
        double[] newSi = new double[] {6, 5, 4, 3, 2, 1};
        MatrixNxM<Length> inst = base.instantiateSi(newSi);
        assertEquals(base.getDisplayUnit(), inst.getDisplayUnit());
        assertArrayEquals(new double[] {6, 5, 4, 3, 2, 1}, inst.getSiArray(), EPS);

        MatrixNxM<SIQuantity> siMatrix = base.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siMatrix.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, siMatrix.getSiArray(), EPS, "si array used as-is");
        assertEquals(6.0, siMatrix.get(0, 0).si(), EPS);

        MatrixNxM<SIQuantity> siMatrixOf = MatrixNxM.of(new double[][] {{6, 5, 4}, {3, 2, 1}}, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", siMatrixOf.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, siMatrixOf.getSiArray(), EPS, "si array used as-is");
        assertEquals(6.0, siMatrixOf.get(0, 0).si(), EPS);

        // ragged matrix
        assertThrows(IllegalArgumentException.class,
                () -> MatrixNxM.of(new double[][] {{6, 5, 4}, {3, 2}}, SIUnit.of("kgm/s2K")));
    }

    // ------------------------------------------------------------------------------------
    // Basics: rows/cols/value/isRelative, setDisplayUnit, toString
    // ------------------------------------------------------------------------------------

    /** Verify rows/cols/value/isRelative on a 3x2 matrix. */
    @Test
    @DisplayName("rows/cols/value/isRelative")
    public void testShapeAndValue()
    {
        MatrixNxM<Length> a32 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        assertEquals(3, a32.rows());
        assertEquals(2, a32.cols());
        assertEquals(1.0, a32.get(0, 0).si(), EPS);
        assertTrue(a32.isRelative());

        assertEquals(6, a32.nnz());
        MatrixNxM<Length> m0 = ofSi(new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0}, 3, 3, Length.Unit.m);
        assertEquals(0, m0.nnz());
        MatrixNxM<Length> m6 =
                ofSi(new double[] {-1, -0.0, Double.NaN, Double.POSITIVE_INFINITY, 0.0, 6, 0.0, 8, 9}, 3, 3, Length.Unit.m);
        assertEquals(6, m6.nnz());
    }

    /** setDisplayUnit only affects presentation; SI unchanged. */
    @Test
    @DisplayName("setDisplayUnit() only changes presentation")
    public void testSetDisplayUnit()
    {
        MatrixNxM<Length> m = ofSi(new double[] {1000, 2000, 3000, 4000}, 2, 2, Length.Unit.km);
        m.setDisplayUnit(Length.Unit.m);
        assertEquals(Length.Unit.m, m.getDisplayUnit());
        assertEquals(1000.0, m.get(0, 0).si(), EPS);
        m.setDisplayUnit(Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
    }

    /** toString and toString(unit) include unit abbreviation. */
    @Test
    @DisplayName("toString()/toString(unit)")
    public void testToString()
    {
        MatrixNxM<Length> m = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.km);
        assertTrue(m.toString().contains("km"));
        assertTrue(m.format(Length.Unit.m).contains("m"));
    }

    // ------------------------------------------------------------------------------------
    // Algebra/stats defaults and Hadamard
    // ------------------------------------------------------------------------------------

    /** Verify add/sub (Q &amp; VM), negate/abs/scaleBy, stats on 2x3 matrix. */
    @Test
    @DisplayName("add/sub (Q & VM), negate/abs/scaleBy, stats")
    public void testAlgebraAndStats()
    {
        MatrixNxM<Length> a = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.m);
        MatrixNxM<Length> b = ofSi(new double[] {6, 5, 4, 3, 2, 1}, 2, 3, Length.Unit.m);
        Length inc = Length.ofSi(2.0);

        assertArrayEquals(new double[] {7, 7, 7, 7, 7, 7}, a.add(b).getSiArray(), EPS);
        assertArrayEquals(new double[] {-5, -3, -1, 1, 3, 5}, a.subtract(b).getSiArray(), EPS);
        assertArrayEquals(new double[] {3, 4, 5, 6, 7, 8}, a.add(inc).getSiArray(), EPS);
        assertArrayEquals(new double[] {-1, 0, 1, 2, 3, 4}, a.subtract(inc).getSiArray(), EPS);

        assertArrayEquals(new double[] {-1, -2, -3, -4, -5, -6}, a.negate().getSiArray(), EPS);
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, a.abs().getSiArray(), EPS);
        assertArrayEquals(new double[] {2, 4, 6, 8, 10, 12}, a.scaleBy(2.0).getSiArray(), EPS);

        assertArrayEquals(new double[] {2, 4, 6, 8, 10, 12}, a.multiplyElements(Dimensionless.ofSi(2.0)).getSiArray(), EPS);
        assertArrayEquals(new double[] {0.5, 1, 1.5, 2, 2.5, 3}, a.divideElements(Dimensionless.ofSi(2.0)).getSiArray(), EPS);

        assertEquals(3.5, a.mean().si(), EPS);
        assertEquals(3.5, a.median().si(), EPS);
        assertEquals(1.0, a.min().si(), EPS);
        assertEquals(6.0, a.max().si(), EPS);
        assertEquals(21.0, a.sum().si(), EPS);
    }

    /** Verify Hadamard invert/multiply/divide with unit composition. */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition")
    public void testHadamard()
    {
        MatrixNxM<Length> a = ofSi(new double[] {2, 4, 5, 10, 20, 40}, 2, 3, Length.Unit.m);
        MatrixNxM<Length> b = MatrixNxM.of(new double[] {1, 2, 0.5, 4, 0.25, 8}, 2, 3, Length.Unit.km);

        MatrixNxM<SIQuantity> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1, 0.05, 0.025}, inv.getSiArray(), EPS);

        MatrixNxM<SIQuantity> mul = a.multiplyEntries(b);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit());
        assertArrayEquals(new double[] {2000, 8000, 2500, 40_000, 5_000, 320_000}, mul.getSiArray(), EPS);

        MatrixNxM<SIQuantity> div = a.divideEntries(b);
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit());
        assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025, 0.08, 0.005}, div.getSiArray(), EPS);
    }

    // ------------------------------------------------------------------------------------
    // Matrix x matrix (NxMxMxP, Nx2x2x2, Nx3x3x3) and Matrix x vector (2,3,N)
    // ------------------------------------------------------------------------------------

    /** Verify general (NxM)x(MxP) multiplication and unit composition. */
    @Test
    @DisplayName("A(NxM) x B(MxP) → C(NxP)")
    public void testMultiplyGeneral()
    {
        // A(3x2) in m
        MatrixNxM<Length> a = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        // B(2x3) in km (display) ⇒ SI = x1000
        MatrixNxM<Length> b = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.km);

        MatrixNxM<SIQuantity> c = a.multiply(b);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), c.getDisplayUnit());

        // Expected SI: A·(1000·B') = 1000·(A·B')
        // A·B' = [[1*1+2*4, 1*2+2*5, 1*3+2*6], ...] = [[9,12,15],[19,26,33],[29,40,51]]
        assertArrayEquals(new double[] {9000, 12000, 15000, 19000, 26000, 33000, 29000, 40000, 51000}, c.getSiArray(), EPS);
    }

    /** Verify Nx2 x 2x2 and Nx3 x 3x3 multiplication variants. */
    @Test
    @DisplayName("A(Nx2)xB(2x2), A(Nx3)xB(3x3)")
    public void testMultiply2x2And3x3()
    {
        // A(3x2) and 2x2
        MatrixNxM<Length> a32 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        Matrix2x2<Length> b22 = Matrix2x2.of(new double[] {1, 2, 3, 4}, Length.Unit.km);
        MatrixNxM<SIQuantity> r32 = a32.multiply(b22);
        assertArrayEquals(new double[] {7000, 10000, 15000, 22000, 23000, 34000}, r32.getSiArray(), EPS);

        // A(2x3) and 3x3
        MatrixNxM<Length> a23 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.m);
        Matrix3x3<Length> b33 = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.km);
        MatrixNxM<SIQuantity> r23 = a23.multiply(b33);
        // A·B' = [[30,36,42],[66,81,96]] then x1000
        assertArrayEquals(new double[] {30_000, 36_000, 42_000, 66_000, 81_000, 96_000}, r23.getSiArray(), EPS);
    }

    /** Verify A(Nx2)·v2 and A(Nx3)·v3 and A(NxM)·vN return VectorN.Col with correct unit. */
    @Test
    @DisplayName("A·v: size 2, size 3, and size N")
    public void testMultiplyVector()
    {
        MatrixNxM<Length> a32 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        Vector2.Col<Length> v2 = Vector2.Col.of(1.0, 2.0, Length.Unit.km); // SI [1000,2000]
        VectorN.Col<SIQuantity> r2 = a32.multiply(v2);
        assertEquals(3, r2.size());
        assertArrayEquals(new double[] {5000, 11000, 17000}, r2.getSiArray(), EPS);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), r2.getDisplayUnit());

        MatrixNxM<Length> a23 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.m);
        Vector3.Col<Length> v3 = Vector3.Col.of(1.0, 2.0, 3.0, Length.Unit.km); // [1k,2k,3k]
        VectorN.Col<SIQuantity> r3 = a23.multiply(v3);
        assertEquals(2, r3.size());
        assertArrayEquals(new double[] {14000, 32000}, r3.getSiArray(), EPS);

        // General VectorN.Col (size = cols)
        VectorN.Col<Length> vN = VectorN.Col.ofSi(new DenseDoubleDataSi(new double[] {1000, 2000}, 2, 1), Length.Unit.km);
        VectorN.Col<SIQuantity> rN = a32.multiply(vN);
        assertArrayEquals(new double[] {5000, 11000, 17000}, rN.getSiArray(), EPS);
    }

    // ------------------------------------------------------------------------------------
    // as(targetUnit) and “as” matrix/vector conversions
    // ------------------------------------------------------------------------------------

    /** Verify as(targetUnit) success (m↔km) and failure (dimension mismatch). */
    @Test
    @DisplayName("as(targetUnit) success/failure")
    public void testAsTargetUnit()
    {
        MatrixNxM<Length> m = MatrixNxM.of(new double[] {1, 2, 3, 4}, 2, 2, Length.Unit.km);
        MatrixNxM<Length> asM = m.as(Length.Unit.m);
        assertEquals(Length.Unit.m, asM.getDisplayUnit());
        assertArrayEquals(m.getSiArray(), asM.getSiArray(), EPS);
        SIUnit s = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> m.as(s));
    }

    /** Verify as* matrix conversions and shape checks. */
    @Test
    @DisplayName("asMatrix2x2 / asMatrix3x3 / asMatrixNxN")
    public void testAsMatrixConversions()
    {
        MatrixNxM<Length> m22 = ofSi(new double[] {1, 2, 3, 4}, 2, 2, Length.Unit.m);
        Matrix2x2<Length> mm22 = m22.asMatrix2x2();
        assertArrayEquals(new double[] {1.0, 2.0, 3.0, 4.0}, mm22.getSiArray(), EPS);

        MatrixNxM<Length> m33 = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 3, 3, Length.Unit.m);
        Matrix3x3<Length> mm33 = m33.asMatrix3x3();
        assertArrayEquals(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}, mm33.getSiArray(), EPS);

        MatrixNxN<Length> mxn = m33.asMatrixNxN();
        assertEquals(3, mxn.rows());
    }

    /** Verify as* vector conversions for 2/3/N Col and Row, plus shape checks. */
    @Test
    @DisplayName("asVector2/3/N (Col/Row) conversions")
    public void testAsVectorConversions()
    {
        // Col
        MatrixNxM<Length> c21 = ofSi(new double[] {1000, 2000}, 2, 1, Length.Unit.km);
        Vector2.Col<Length> vc2 = c21.asVector2Col();
        assertEquals(2, vc2.size());
        assertArrayEquals(new double[] {1000, 2000}, vc2.getSiArray(), EPS);

        MatrixNxM<Length> c31 = ofSi(new double[] {1, 2, 3}, 3, 1, Length.Unit.m);
        Vector3.Col<Length> vc3 = c31.asVector3Col();
        assertArrayEquals(new double[] {1, 2, 3}, vc3.getSiArray(), EPS);

        VectorN.Col<Length> vcn = c31.asVectorNCol();
        assertEquals(3, vcn.size());

        // Row
        MatrixNxM<Length> r12 = ofSi(new double[] {1000, 2000}, 1, 2, Length.Unit.km);
        Vector2.Row<Length> vr2 = r12.asVector2Row();
        assertArrayEquals(new double[] {1000, 2000}, vr2.getSiArray(), EPS);

        MatrixNxM<Length> r13 = ofSi(new double[] {1, 2, 3}, 1, 3, Length.Unit.m);
        Vector3.Row<Length> vr3 = r13.asVector3Row();
        assertArrayEquals(new double[] {1, 2, 3}, vr3.getSiArray(), EPS);

        VectorN.Row<Length> vrn = r13.asVectorNRow();
        assertEquals(3, vrn.size());

        // Negative shape checks
        MatrixNxM<Length> bad = ofSi(new double[] {1, 2, 3, 4}, 2, 2, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> bad.asVector2Col());
        assertThrows(IllegalStateException.class, () -> bad.asVector3Row());
        assertThrows(IllegalStateException.class, () -> bad.asVectorNRow()); // rows()!=1
    }

    // ------------------------------------------------------------------------------------
    // Scalar extraction & equals/hashCode
    // ------------------------------------------------------------------------------------

    /** Verify scalar extraction helpers. */
    @Test
    @DisplayName("getScalars / getRowScalars / getColumnScalars")
    public void testScalarExtraction()
    {
        MatrixNxM<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        MatrixNxM<Length> mhm = ofSi(new double[] {10, 20, 30, 40, 50, 60}, 3, 2, Length.Unit.hm);
        Length[][] scalars = m.getScalarGrid();
        assertEquals(3, scalars.length);
        assertEquals(2, scalars[0].length);
        assertEquals(1.0, scalars[0][0].si(), EPS);
        assertEquals(6.0, scalars[2][1].si(), EPS);
        Length[][] qhm = mhm.getScalarGrid();
        assertEquals(3, qhm.length);
        assertEquals(2, qhm[1].length);
        assertEquals(10.0, qhm[0][0].si(), EPS);
        assertEquals(60.0, qhm[2][1].si(), EPS);
        assertEquals(Length.Unit.hm, qhm[1][1].getDisplayUnit());

        double[][] sigrid = m.getSiGrid();
        double[][] sihm = mhm.getSiGrid();
        assertEquals(3, sigrid.length);
        assertEquals(2, sigrid[0].length);
        assertEquals(2, sigrid[1].length);
        assertEquals(1.0, sigrid[0][0], EPS);
        assertEquals(4.0, sigrid[1][1], EPS);
        assertEquals(10.0, sihm[0][0], EPS);
        assertEquals(40.0, sihm[1][1], EPS);
        assertEquals(60.0, sihm[2][1], EPS);

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
        MatrixNxM<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
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
        MatrixNxM<Length> m = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);

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

    /** Verify equals/hashCode. */
    @Test
    @DisplayName("equals / hashCode")
    public void testEqualsHash()
    {
        MatrixNxM<Length> a = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        MatrixNxM<Length> b = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        MatrixNxM<Length> c = ofSi(new double[] {1, 2, 3, 4, 5, 7}, 3, 2, Length.Unit.m);
        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
        assertNotEquals(a, null);
        assertNotEquals(a, "other");
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        MatrixNxM<Length> r = MatrixNxM.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 3, 2, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        MatrixNxM<Speed> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1, 1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.mget(1, 2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.mget(2, 1).getInUnit(), 1E-6);
        assertEquals(2.0, sr.mget(2, 2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — asVector1 / asVector{2,3}{Col,Row} / asMatrix2x2 / asMatrix3x3
    // ------------------------------------------------------------------------------------

    /**
     * Verify MatrixNxM conversions to fixed-size vectors and matrices preserve SI data and display unit, and that shape checks
     * throw IllegalStateException with both row- and column-mismatch branches covered.
     * <p>
     * Uses kilometers for column-shaped tests and centimeters for row-shaped tests to exercise SI conversion.
     */
    @Test
    @DisplayName("MatrixNxM: asVector preserve SI and unit; row/col mismatch branches throw")
    public void testMatrixNxMAsVectorConversions()
    {
        // ----------------------------
        // asVector1 (requires 1x1)
        // ----------------------------

        MatrixNxM<Length> m11cm = ofSi(new double[] {0.03}, 1, 1, Length.Unit.cm); // 3 cm -> 0.03 m
        assertEquals(Length.Unit.cm, m11cm.getDisplayUnit());
        assertEquals(1, m11cm.getSiArray().length);
        assertEquals(0.03, m11cm.getSiArray()[0], EPS);
        Vector1<Length> v1cm = m11cm.asVector1();
        assertEquals(1, v1cm.size());
        assertEquals(0.03, v1cm.get(0).si(), EPS);
        assertEquals(Length.Unit.cm, v1cm.getDisplayUnit());

        // row mismatch (2x1) and col mismatch (1x2)
        MatrixNxM<Length> m21km = ofSi(new double[] {5000.0, 6000.0}, 2, 1, Length.Unit.km);
        MatrixNxM<Length> m12cm = ofSi(new double[] {0.03, 0.04}, 1, 2, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> m21km.asVector1());
        assertThrows(IllegalStateException.class, () -> m12cm.asVector1());

        // ----------------------------
        // asVector2Col (requires 2x1)
        // ----------------------------

        MatrixNxM<Length> m21ok = ofSi(new double[] {5000.0, 6000.0}, 2, 1, Length.Unit.km); // [5 km; 6 km]
        assertEquals(Length.Unit.km, m21ok.getDisplayUnit());
        assertEquals(2, m21ok.getSiArray().length);
        assertEquals(5000.0, m21ok.getSiArray()[0], EPS);
        assertEquals(6000.0, m21ok.getSiArray()[1], EPS);
        Vector2.Col<Length> v2col = m21ok.asVector2Col();
        assertEquals(2, v2col.size());
        assertEquals(5000.0, v2col.get(0).si(), EPS);
        assertEquals(6000.0, v2col.get(1).si(), EPS);
        assertEquals(Length.Unit.km, v2col.getDisplayUnit());

        // row mismatch (1x1) and col mismatch (2x2)
        MatrixNxM<Length> m11km = ofSi(new double[] {7000.0}, 1, 1, Length.Unit.km);
        MatrixNxM<Length> m22km = ofSi(new double[] {5000.0, 6000.0, 7000.0, 8000.0}, 2, 2, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m11km.asVector2Col());
        assertThrows(IllegalStateException.class, () -> m22km.asVector2Col());

        // ----------------------------
        // asVector2Row (requires 1x2)
        // ----------------------------
        MatrixNxM<Length> m12ok = ofSi(new double[] {0.03, 0.04}, 1, 2, Length.Unit.cm); // [3 cm, 4 cm]
        assertEquals(Length.Unit.cm, m12ok.getDisplayUnit());
        assertEquals(2, m12ok.getSiArray().length);
        assertEquals(0.03, m12ok.getSiArray()[0], EPS);
        assertEquals(0.04, m12ok.getSiArray()[1], EPS);
        Vector2.Row<Length> v2row = m12ok.asVector2Row();
        assertEquals(2, v2row.size());
        assertEquals(0.03, v2row.get(0).si(), EPS);
        assertEquals(0.04, v2row.get(1).si(), EPS);
        assertEquals(Length.Unit.cm, v2row.getDisplayUnit());

        // row mismatch (2x2) and col mismatch (1x1)
        MatrixNxM<Length> m22cm = ofSi(new double[] {0.01, 0.02, 0.03, 0.04}, 2, 2, Length.Unit.cm);
        MatrixNxM<Length> m11cm2 = ofSi(new double[] {0.05}, 1, 1, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> m22cm.asVector2Row());
        assertThrows(IllegalStateException.class, () -> m11cm2.asVector2Row());

        // ----------------------------
        // asVector3Col (requires 3x1)
        // ----------------------------
        MatrixNxM<Length> m31ok = ofSi(new double[] {5000.0, 6000.0, 7000.0}, 3, 1, Length.Unit.km);
        Vector3.Col<Length> v3col = m31ok.asVector3Col();
        assertEquals(3, v3col.size());
        assertEquals(5000.0, v3col.get(0).si(), EPS);
        assertEquals(6000.0, v3col.get(1).si(), EPS);
        assertEquals(7000.0, v3col.get(2).si(), EPS);
        assertEquals(Length.Unit.km, v3col.getDisplayUnit());

        // row mismatch (2x1) and col mismatch (3x2)
        MatrixNxM<Length> m21bad = ofSi(new double[] {5000.0, 6000.0}, 2, 1, Length.Unit.km);
        MatrixNxM<Length> m32bad = ofSi(new double[] {5000.0, 1.0, 6000.0, 2.0, 7000.0, 3.0}, 3, 2, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m21bad.asVector3Col());
        assertThrows(IllegalStateException.class, () -> m32bad.asVector3Col());

        // ----------------------------
        // asVector3Row (requires 1x3)
        // ----------------------------
        MatrixNxM<Length> m13ok = ofSi(new double[] {0.03, 0.04, 0.05}, 1, 3, Length.Unit.cm);
        Vector3.Row<Length> v3row = m13ok.asVector3Row();
        assertEquals(3, v3row.size());
        assertEquals(0.03, v3row.get(0).si(), EPS);
        assertEquals(0.04, v3row.get(1).si(), EPS);
        assertEquals(0.05, v3row.get(2).si(), EPS);
        assertEquals(Length.Unit.cm, v3row.getDisplayUnit());

        // row mismatch (2x3) and col mismatch (1x2)
        MatrixNxM<Length> m23bad = ofSi(new double[] {0.01, 0.02, 0.03, 0.04, 0.05, 0.06}, 2, 3, Length.Unit.cm);
        MatrixNxM<Length> m12bad = ofSi(new double[] {0.07, 0.08}, 1, 2, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> m23bad.asVector3Row());
        assertThrows(IllegalStateException.class, () -> m12bad.asVector3Row());
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — asMatrix2x2 / asMatrix3x3
    // ------------------------------------------------------------------------------------

    /**
     * Verify MatrixNxM conversions to fixed-size matrices preserve SI data and display unit, and that shape checks throw
     * IllegalStateException for the mismatch branches covered.
     */
    @Test
    @DisplayName("MatrixNxM: asMatrix preserve SI and unit")
    public void testMatrixNxMAsMatrixConversions()
    {
        // ------------------------------------------------------------------------------------
        // MatrixNxM — asMatrix1x1 (happy + bad paths)
        // ------------------------------------------------------------------------------------

        // Happy path: 1x1, using km to verify SI-preservation (5 km -> 5000 m).
        MatrixNxM<Length> m1x1km = ofSi(new double[] {5000.0}, 1, 1, Length.Unit.km);
        assertEquals(Length.Unit.km, m1x1km.getDisplayUnit());
        assertEquals(1, m1x1km.getSiArray().length);
        assertEquals(5000.0, m1x1km.getSiArray()[0], EPS);
        assertEquals(5000.0, m1x1km.si(0, 0), EPS);

        Matrix1x1<Length> fixed1x1 = m1x1km.asMatrix1x1();
        assertEquals(Length.Unit.km, fixed1x1.getDisplayUnit());
        assertEquals(5000.0, fixed1x1.si(0, 0), EPS);
        double[] si11 = fixed1x1.getSiArray();
        assertEquals(1, si11.length);
        assertEquals(5000.0, si11[0], EPS);

        // Bad path: row mismatch (2x1).
        MatrixNxM<Length> m2x1km = ofSi(new double[] {5000.0, 6000.0}, 2, 1, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m2x1km.asMatrix1x1());

        // Bad path: column mismatch (1x2).
        MatrixNxM<Length> m1x2km = ofSi(new double[] {5000.0, 6000.0}, 1, 2, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m1x2km.asMatrix1x1());

        // ----------------------------
        // asMatrix2x2 (requires 2x2)
        // ----------------------------

        MatrixNxM<Length> m22ok = ofSi(new double[] {1.0, 2.0, 3.0, 4.0}, 2, 2, Length.Unit.m);
        Matrix2x2<Length> m22fixed = m22ok.asMatrix2x2();
        assertEquals(Length.Unit.m, m22fixed.getDisplayUnit());
        assertEquals(1.0, m22fixed.si(0, 0), EPS);
        assertEquals(2.0, m22fixed.si(0, 1), EPS);
        assertEquals(3.0, m22fixed.si(1, 0), EPS);
        assertEquals(4.0, m22fixed.si(1, 1), EPS);
        double[] m22si = m22fixed.getSiArray();
        assertEquals(4, m22si.length);
        assertEquals(1.0, m22si[0], EPS);
        assertEquals(2.0, m22si[1], EPS);
        assertEquals(3.0, m22si[2], EPS);
        assertEquals(4.0, m22si[3], EPS);

        // row mismatch (1x2) and col mismatch (2x3)
        MatrixNxM<Length> m12mat = ofSi(new double[] {9.0, 10.0}, 1, 2, Length.Unit.m);
        MatrixNxM<Length> m23mat = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> m12mat.asMatrix2x2());
        assertThrows(IllegalStateException.class, () -> m23mat.asMatrix2x2());

        // ----------------------------
        // asMatrix3x3 (requires 3x3)
        // ----------------------------

        MatrixNxM<Length> m33ok = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}, 3, 3, Length.Unit.m);
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
        double[] m33si = m33fixed.getSiArray();
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
        MatrixNxM<Length> m23badMat = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        MatrixNxM<Length> m32badMat = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 3, 2, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> m23badMat.asMatrix3x3());
        assertThrows(IllegalStateException.class, () -> m32badMat.asMatrix3x3());
    }

    /**
     * Verify MatrixNxM conversions to quantity table preserve SI data and display unit, and that shape checks throw
     * IllegalStateException for the mismatch branches covered.
     */
    @Test
    @DisplayName("MatrixNxM: asQuantityTable preserve SI and unit")
    public void testMatrixNxMAsQuantityTable()
    {
        // ----------------------------
        // asQuantityTable
        // ----------------------------

        double[] newSi = new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        MatrixNxM<Length> m23ok = ofSi(newSi, 3, 2, Length.Unit.km);
        QuantityTable<Length> q23fixed = m23ok.asQuantityTable();
        assertEquals(Length.Unit.km, q23fixed.getDisplayUnit());
        assertEquals(q23fixed.cols(), m23ok.cols());
        assertEquals(q23fixed.rows(), m23ok.rows());
        assertEquals(1.0, q23fixed.si(0, 0), EPS);
        assertEquals(2.0, q23fixed.si(0, 1), EPS);
        assertEquals(3.0, q23fixed.si(1, 0), EPS);
        assertEquals(4.0, q23fixed.si(1, 1), EPS);
        assertEquals(5.0, q23fixed.si(2, 0), EPS);
        assertEquals(6.0, q23fixed.si(2, 1), EPS);
        double[] q23si = q23fixed.getSiArray();
        assertEquals(6, q23si.length);
        assertArrayEquals(newSi, q23si);
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — asMatrixNxN (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asMatrixNxN()} preserves SI data and display unit for square matrices (tested with 1x1 and 4x4), and
     * throws {@link IllegalStateException} for non-square shapes (tested with 2x3 and 3x2). SI correctness is checked via both
     * the row-major {@code getSiArray()} array and {@code si(row, col)} with 0-based indices.
     */
    @Test
    @DisplayName("MatrixNxM: asMatrixNxN preserves SI & unit (square) and throws for non-square shapes")
    public void testAsMatrixNxN()
    {
        // ----------------------------
        // Happy path: 1x1 (using cm)
        // ----------------------------
        MatrixNxM<Length> m11cm = ofSi(new double[] {0.03}, 1, 1, Length.Unit.cm); // 3 cm -> 0.03 m
        assertEquals(Length.Unit.cm, m11cm.getDisplayUnit());
        assertEquals(1, m11cm.getSiArray().length);
        assertEquals(0.03, m11cm.getSiArray()[0], EPS);
        assertEquals(0.03, m11cm.si(0, 0), EPS);

        MatrixNxN<Length> n11 = m11cm.asMatrixNxN();
        assertEquals(Length.Unit.cm, n11.getDisplayUnit());
        double[] si11 = n11.getSiArray();
        assertEquals(1, si11.length);
        assertEquals(0.03, si11[0], EPS);
        assertEquals(0.03, n11.si(0, 0), EPS);

        // ----------------------------
        // Happy path: 4x4 (using km)
        // ----------------------------
        double[] si44 = new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0, 7000.0, 8000.0, 9000.0, 10000.0, 11000.0,
                12000.0, 13000.0, 14000.0, 15000.0, 16000.0}; // these are SI meters; with display unit km they should be shown
                                                              // as km but getSiArray() stays meters
        MatrixNxM<Length> m44km = ofSi(si44, 4, 4, Length.Unit.km);
        assertEquals(Length.Unit.km, m44km.getDisplayUnit());
        assertEquals(16, m44km.getSiArray().length);
        assertEquals(1000.0, m44km.getSiArray()[0], EPS);
        assertEquals(16000.0, m44km.getSiArray()[15], EPS);
        assertEquals(1000.0, m44km.si(0, 0), EPS);
        assertEquals(4000.0, m44km.si(0, 3), EPS);
        assertEquals(13000.0, m44km.si(3, 0), EPS);
        assertEquals(16000.0, m44km.si(3, 3), EPS);

        MatrixNxN<Length> n44 = m44km.asMatrixNxN();
        assertEquals(Length.Unit.km, n44.getDisplayUnit());

        double[] si44Out = n44.getSiArray();
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
        MatrixNxM<Length> m23m = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> m23m.asMatrixNxN());

        // 3x2 (column-mismatch branch of "rows != cols")
        MatrixNxM<Length> m32m = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 3, 2, Length.Unit.m);
        assertThrows(IllegalStateException.class, () -> m32m.asMatrixNxN());
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — asVectorNCol (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asVectorNCol()}:
     * <ul>
     * <li>Preserves SI data and display unit for {@code N x 1} matrices (tested with N=4 and N=1),</li>
     * <li>Throws {@link IllegalStateException} when the matrix has more than one column (e.g., {@code N x 2}).</li>
     * </ul>
     * SI correctness is validated via both the row-major {@code getSiArray()} array and element access on the returned vector.
     */
    @Test
    @DisplayName("MatrixNxM: asVectorNCol preserves SI & unit (Nx1) and throws for Nx2")
    public void testAsVectorNCol()
    {
        // Happy path: 4x1 using km (values in SI meters: 5,6,7,8 km -> 5000..8000 m)
        MatrixNxM<Length> m41km = ofSi(new double[] {5000.0, 6000.0, 7000.0, 8000.0}, 4, 1, Length.Unit.km);

        assertEquals(Length.Unit.km, m41km.getDisplayUnit());
        assertEquals(4, m41km.getSiArray().length);
        assertEquals(5000.0, m41km.getSiArray()[0], EPS);
        assertEquals(8000.0, m41km.getSiArray()[3], EPS);
        assertEquals(5000.0, m41km.si(0, 0), EPS);
        assertEquals(8000.0, m41km.si(3, 0), EPS);

        VectorN.Col<Length> vCol = m41km.asVectorNCol();
        assertEquals(4, vCol.size());
        assertEquals(Length.Unit.km, vCol.getDisplayUnit());
        assertEquals(5000.0, vCol.get(0).si(), EPS);
        assertEquals(8000.0, vCol.get(3).si(), EPS);

        double[] vColSi = vCol.getSiArray();
        assertEquals(4, vColSi.length);
        assertEquals(5000.0, vColSi[0], EPS);
        assertEquals(8000.0, vColSi[3], EPS);

        // Edge happy path: 1x1 also yields a VectorN.Col of length 1
        MatrixNxM<Length> m11km = ofSi(new double[] {1234.0}, 1, 1, Length.Unit.km);
        VectorN.Col<Length> vCol1 = m11km.asVectorNCol();
        assertEquals(1, vCol1.size());
        assertEquals(Length.Unit.km, vCol1.getDisplayUnit());
        assertEquals(1234.0, vCol1.get(0).si(), EPS);

        // Bad path: Nx2 must throw (cols() != 1)
        MatrixNxM<Length> m42km =
                ofSi(new double[] {5000.0, 6000.0, 7000.0, 8000.0, 9000.0, 10000.0, 11000.0, 12000.0}, 4, 2, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> m42km.asVectorNCol());
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — asVectorNRow (happy + bad paths)
    // ------------------------------------------------------------------------------------

    /**
     * Verify that {@code asVectorNRow()}:
     * <ul>
     * <li>Preserves SI data and display unit for {@code 1 x N} matrices (tested with N=4 and N=1),</li>
     * <li>Throws {@link IllegalStateException} when the matrix has more than one row (e.g., {@code 2 x N}).</li>
     * </ul>
     * SI correctness is validated via both the row-major {@code getSiArray()} array and element access on the returned vector.
     */
    @Test
    @DisplayName("MatrixNxM: asVectorNRow preserves SI & unit (1xN) and throws for 2xN")
    public void testAsVectorNRow()
    {
        // Happy path: 1x4 using cm (values in SI meters: 3,4,5,6 cm -> 0.03..0.06 m)
        MatrixNxM<Length> m14cm = ofSi(new double[] {0.03, 0.04, 0.05, 0.06}, 1, 4, Length.Unit.cm);

        assertEquals(Length.Unit.cm, m14cm.getDisplayUnit());
        assertEquals(4, m14cm.getSiArray().length);
        assertEquals(0.03, m14cm.getSiArray()[0], EPS);
        assertEquals(0.06, m14cm.getSiArray()[3], EPS);
        assertEquals(0.03, m14cm.si(0, 0), EPS);
        assertEquals(0.06, m14cm.si(0, 3), EPS);

        VectorN.Row<Length> vRow = m14cm.asVectorNRow();
        assertEquals(4, vRow.size());
        assertEquals(Length.Unit.cm, vRow.getDisplayUnit());
        assertEquals(0.03, vRow.get(0).si(), EPS);
        assertEquals(0.06, vRow.get(3).si(), EPS);

        double[] vRowSi = vRow.getSiArray();
        assertEquals(4, vRowSi.length);
        assertEquals(0.03, vRowSi[0], EPS);
        assertEquals(0.06, vRowSi[3], EPS);

        // Edge happy path: 1x1 also yields a VectorN.Row of length 1
        MatrixNxM<Length> m11cm = ofSi(new double[] {0.001}, 1, 1, Length.Unit.cm);
        VectorN.Row<Length> vRow1 = m11cm.asVectorNRow();
        assertEquals(1, vRow1.size());
        assertEquals(Length.Unit.cm, vRow1.getDisplayUnit());
        assertEquals(0.001, vRow1.get(0).si(), EPS);

        // Bad path: 2xN must throw (rows() != 1)
        MatrixNxM<Length> m24cm = ofSi(new double[] {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08}, 2, 4, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> m24cm.asVectorNRow());
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(Matrix1x1)
    // ------------------------------------------------------------------------------------

    /**
     * (Mx1) x (1x1) -&gt; (Mx1). Verify numeric correctness, unit composition (Length×Length→Area) via as(Area.Unit.*), and bad
     * path when cols(this) != rows(rhs).
     */
    @Test
    @DisplayName("MatrixNxM.multiply(Matrix1x1): happy path and bad dimension mismatch")
    public void testMultiplyMatrix1x1()
    {
        // Left: 3x1 in km -> [1000, 2000, 3000] m
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0, 3000.0}, 3, 1, Length.Unit.km);

        // Right: 1x1 in m -> [4] m
        Matrix1x1<Length> right = Matrix1x1.of(new double[] {4.0}, Length.Unit.m);

        MatrixNxM<SIQuantity> prod = left.multiply(right);
        assertEquals(3, prod.rows());
        assertEquals(1, prod.cols());

        // SI result should be elementwise *4
        double[] si = prod.getSiArray();
        assertEquals(3, si.length);
        assertEquals(4000.0, si[0], EPS);
        assertEquals(8000.0, si[1], EPS);
        assertEquals(12000.0, si[2], EPS);
        assertEquals(4000.0, prod.si(0, 0), EPS);
        assertEquals(12000.0, prod.si(2, 0), EPS);

        // Unit composition check: Length × Length -> Area
        MatrixNxM<Area> areaM2 = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, areaM2.getDisplayUnit());
        assertEquals(4000.0, areaM2.si(0, 0), EPS);
        assertEquals(12000.0, areaM2.si(2, 0), EPS);

        MatrixNxM<Area> areaKm2 = prod.as(Area.Unit.km2);
        assertEquals(Area.Unit.km2, areaKm2.getDisplayUnit());
        assertEquals(4000.0, areaKm2.si(0, 0), EPS); // SI stays meters^2
        assertEquals(12000.0, areaKm2.si(2, 0), EPS);

        // Bad path: left must have cols == 1
        MatrixNxM<Length> badLeft = ofSi(new double[] {1.0, 2.0, 3.0, 4.0}, 2, 2, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> badLeft.multiply(right));
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(Matrix2x2)
    // ------------------------------------------------------------------------------------

    /**
     * (Mx2) x (2x2) -&gt; (Mx2).
     */
    @Test
    @DisplayName("MatrixNxM.multiply(Matrix2x2): happy path and bad dimension mismatch")
    public void testMultiplyMatrix2x2()
    {
        // Left: 2x2, km; choose identity*1000 for easy checking (SI)
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 0.0, 0.0, 1000.0}, 2, 2, Length.Unit.km);

        // Right: 2x2, m
        Matrix2x2<Length> right = Matrix2x2.of(new double[] {2.0, 3.0, 4.0, 5.0}, Length.Unit.m);

        MatrixNxM<SIQuantity> prod = left.multiply(right);
        assertEquals(2, prod.rows());
        assertEquals(2, prod.cols());

        // Result should be [[2000, 3000], [4000, 5000]] (m^2)
        assertEquals(2000.0, prod.si(0, 0), EPS);
        assertEquals(3000.0, prod.si(0, 1), EPS);
        assertEquals(4000.0, prod.si(1, 0), EPS);
        assertEquals(5000.0, prod.si(1, 1), EPS);

        MatrixNxM<Area> area = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, area.getDisplayUnit());
        assertEquals(2000.0, area.si(0, 0), EPS);
        assertEquals(5000.0, area.si(1, 1), EPS);

        // Bad path: left must have cols == 2
        MatrixNxM<Length> badLeft = ofSi(new double[] {1.0, 2.0, 3.0}, 3, 1, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> badLeft.multiply(right));
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(Matrix3x3)
    // ------------------------------------------------------------------------------------

    /**
     * (Mx3) x (3x3) -&gt; (Mx3).
     */
    @Test
    @DisplayName("MatrixNxM.multiply(Matrix3x3): happy path and bad dimension mismatch")
    public void testMultiplyMatrix3x3()
    {
        // Left: 2x3 in km (SI easy)
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0}, 2, 3, Length.Unit.km);

        // Right: 3x3 in m
        Matrix3x3<Length> right = Matrix3x3.of(new double[] {1.0, 0.0, 1.0, 0.0, 2.0, 0.0, 1.0, 0.0, 1.0}, Length.Unit.m);

        MatrixNxM<SIQuantity> prod = left.multiply(right);
        assertEquals(2, prod.rows());
        assertEquals(3, prod.cols());

        // Manual multiplication in SI (m * m)
        // Row0: [1000,2000,3000] * right => [1000+3000, 4000, 1000+3000] = [4000, 4000, 4000]
        assertEquals(4000.0, prod.si(0, 0), EPS);
        assertEquals(4000.0, prod.si(0, 1), EPS);
        assertEquals(4000.0, prod.si(0, 2), EPS);
        // Row1: [4000,5000,6000] * right => [4000+6000, 10000, 4000+6000] = [10000, 10000, 10000]
        assertEquals(10000.0, prod.si(1, 0), EPS);
        assertEquals(10000.0, prod.si(1, 1), EPS);
        assertEquals(10000.0, prod.si(1, 2), EPS);

        MatrixNxM<Area> area = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, area.getDisplayUnit());

        // Bad path: left must have cols == 3
        MatrixNxM<Length> badLeft = ofSi(new double[] {1.0, 2.0, 3.0, 4.0}, 2, 2, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> badLeft.multiply(right));
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(MatrixNxN) covering double/float branches
    // ------------------------------------------------------------------------------------

    /**
     * (NxM) x (MxP) where rhs is NxN: verify both branches of getDataGrid().isDouble() by constructing a double-backed and a
     * float-backed MatrixNxN.
     */
    @Test
    @DisplayName("MatrixNxM.multiply(MatrixNxN): double and float storage branches + bad dimension")
    public void testMultiplyMatrixNxNDoubleAndFloatBranches()
    {
        // Left: 3x2 (km)
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0}, 3, 2, Length.Unit.km);

        // ---- Double-backed NxN (2x2 in m) -> should go through DenseDoubleDataSi branch
        MatrixNxN<Length> rhsDouble = MatrixNxN.of(new double[] {2.0, 1.0, 0.0, 3.0}, Length.Unit.m);

        MatrixNxM<SIQuantity> prodDouble = left.multiply(rhsDouble);
        assertEquals(3, prodDouble.rows());
        assertEquals(2, prodDouble.cols());

        // Compute expected: left(3x2) * rhs(2x2)
        // Row0: [1000,2000] * [[2,1],[0,3]] = [2000+0, 1000+6000] = [2000, 7000]
        assertEquals(2000.0, prodDouble.si(0, 0), EPS);
        assertEquals(7000.0, prodDouble.si(0, 1), EPS);
        // Row1: [3000,4000] -> [6000, 3000+12000=15000]
        assertEquals(6000.0, prodDouble.si(1, 0), EPS);
        assertEquals(15000.0, prodDouble.si(1, 1), EPS);
        // Row2: [5000,6000] -> [10000, 5000+18000=23000]
        assertEquals(10000.0, prodDouble.si(2, 0), EPS);
        assertEquals(23000.0, prodDouble.si(2, 1), EPS);

        // ---- Float-backed NxN (2x2) -> should go through DenseFloatDataSi branch
        // If your API uses a specialized factory, replace with it (e.g., MatrixNxN.of(float[], unit) or ofSi(float[], unit)).
        MatrixNxN<Length> rhsFloat =
                new MatrixNxN<>(new DenseFloatDataSi(new float[] {1.0f, 4.0f, 2.0f, 3.0f}, 2, 2), Length.Unit.m);

        MatrixNxM<SIQuantity> prodFloat = left.multiply(rhsFloat);
        assertEquals(3, prodFloat.rows());
        assertEquals(2, prodFloat.cols());

        // Row0: [1000,2000] * [[1,4],[2,3]] = [1000+4000, 4000+6000] = [5000, 10000]
        assertEquals(5000.0, prodFloat.si(0, 0), EPS);
        assertEquals(10000.0, prodFloat.si(0, 1), EPS);
        // Row1: [3000,4000] -> [3000+8000=11000, 12000+12000=24000]
        assertEquals(11000.0, prodFloat.si(1, 0), EPS);
        assertEquals(24000.0, prodFloat.si(1, 1), EPS);
        // Row2: [5000,6000] -> [5000+12000=17000, 20000+18000=38000]
        assertEquals(17000.0, prodFloat.si(2, 0), EPS);
        assertEquals(38000.0, prodFloat.si(2, 1), EPS);

        // Unit composition sanity via as(Area.Unit.m2)
        MatrixNxM<Area> area = prodDouble.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, area.getDisplayUnit());
        assertEquals(2000.0, area.si(0, 0), EPS);

        // Bad path: mismatch cols(left) vs rows(rhs)
        // cols=3
        MatrixNxM<Length> badLeft = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> badLeft.multiply(rhsDouble)); // rhs is 2x2
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(MatrixNxM) : happy path with RHS double-backed (DenseDoubleDataSi)
    // ------------------------------------------------------------------------------------

    /**
     * Verify (2x3) x (3x2) multiplication with a double-backed RHS matrix:
     * <ul>
     * <li>Numeric correctness via getSiArray() and si(r,c).</li>
     * <li>Unit composition via as(Area.Unit.*).</li>
     * <li>Result shape (2x2).</li>
     * </ul>
     */
    @Test
    @DisplayName("MatrixNxM.multiply(MatrixNxM): 2x3 x 3x2 with double-backed RHS")
    public void testMultiplyNxMWithNxMDoubleRhs()
    {
        // Left: 2x3 (km), SI values in meters for easy checking: [[1000,2000,3000],[4000,5000,6000]]
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0}, 2, 3, Length.Unit.km);

        // Right: 3x2 (m), double-backed storage using DenseDoubleDataSi
        double[] rhsSi = new double[] {7.0, 8.0, 9.0, 10.0, 11.0, 12.0};
        MatrixNxM<Length> right = new MatrixNxM<>(new DenseDoubleDataSi(rhsSi, 3, 2), Length.Unit.m);

        MatrixNxM<SIQuantity> prod = left.multiply(right);
        assertEquals(2, prod.rows());
        assertEquals(2, prod.cols());

        // Expected SI result (m * m): [[58000, 64000], [139000, 154000]]
        assertEquals(58000.0, prod.si(0, 0), EPS);
        assertEquals(64000.0, prod.si(0, 1), EPS);
        assertEquals(139000.0, prod.si(1, 0), EPS);
        assertEquals(154000.0, prod.si(1, 1), EPS);

        double[] si = prod.getSiArray();
        assertEquals(4, si.length);
        assertEquals(58000.0, si[0], EPS);
        assertEquals(64000.0, si[1], EPS);
        assertEquals(139000.0, si[2], EPS);
        assertEquals(154000.0, si[3], EPS);

        // Unit composition: Length × Length -> Area; conversion preserves SI
        MatrixNxM<Area> areaM2 = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, areaM2.getDisplayUnit());
        assertEquals(58000.0, areaM2.si(0, 0), EPS);
        assertEquals(154000.0, areaM2.si(1, 1), EPS);

        MatrixNxM<Area> areaKm2 = prod.as(Area.Unit.km2);
        assertEquals(Area.Unit.km2, areaKm2.getDisplayUnit());
        assertEquals(58000.0, areaKm2.si(0, 0), EPS);
        assertEquals(154000.0, areaKm2.si(1, 1), EPS);
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(MatrixNxM) : happy path with RHS float-backed (DenseFloatDataSi)
    // ------------------------------------------------------------------------------------

    /**
     * Verify (2x3) x (3x2) multiplication with a float-backed RHS matrix to cover the DenseFloatDataSi branch.
     */
    @Test
    @DisplayName("MatrixNxM.multiply(MatrixNxM): 2x3 x 3x2 with float-backed RHS")
    public void testMultiplyNxMWithNxMFloatRhs()
    {
        // Left: 2x3 (km), SI values [[1000,2000,3000],[4000,5000,6000]]
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0}, 2, 3, Length.Unit.km);

        // Right: 3x2 (m), float-backed storage using DenseFloatDataSi
        float[] rhsSi = new float[] {1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        MatrixNxM<Length> right = new MatrixNxM<>(new DenseFloatDataSi(rhsSi, 3, 2), Length.Unit.m);

        MatrixNxM<SIQuantity> prod = left.multiply(right);
        assertEquals(2, prod.rows());
        assertEquals(2, prod.cols());

        // Expected SI result:
        // Row0: [1000,2000,3000] * [[1,0],[0,1],[1,1]] = [1000+0+3000, 0+2000+3000] = [4000, 5000]
        // Row1: [4000,5000,6000] -> [4000+0+6000, 0+5000+6000] = [10000, 11000]
        assertEquals(4000.0, prod.si(0, 0), EPS);
        assertEquals(5000.0, prod.si(0, 1), EPS);
        assertEquals(10000.0, prod.si(1, 0), EPS);
        assertEquals(11000.0, prod.si(1, 1), EPS);

        // Unit composition check via Area
        MatrixNxM<Area> area = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, area.getDisplayUnit());
        assertEquals(4000.0, area.si(0, 0), EPS);
        assertEquals(11000.0, area.si(1, 1), EPS);
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(Vector1)
    // ------------------------------------------------------------------------------------

    /**
     * (Mx1) x (1x1) -&gt; (Mx1) MatrixNxM&lt;SIQuantity, SIUni&gt;.
     */
    @Test
    @DisplayName("MatrixNxM.multiply(Vector1): happy path and bad dimension mismatch")
    public void testMultiplyVector1()
    {
        // Left: 2x1, km -> [1000, 2000] m
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0}, 2, 1, Length.Unit.km);

        // Vector1: [4 m]
        Vector1<Length> v = new Vector1<>(4.0, Length.Unit.m);

        MatrixNxM<SIQuantity> prod = left.multiply(v);
        assertEquals(2, prod.rows());
        assertEquals(1, prod.cols());
        assertEquals(4000.0, prod.si(0, 0), EPS);
        assertEquals(8000.0, prod.si(1, 0), EPS);

        MatrixNxM<Area> area = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, area.getDisplayUnit());

        // Bad path: left must have cols == 1
        MatrixNxM<Length> badLeft = ofSi(new double[] {1.0, 2.0, 3.0, 4.0}, 2, 2, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> badLeft.multiply(v));
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(Vector2.Col)
    // ------------------------------------------------------------------------------------

    /**
     * (Mx2) x (2x1) -&gt; (Mx1) VectorN.Col&lt;SIQuantity, SIUnit&gt;.
     */
    @Test
    @DisplayName("MatrixNxM.multiply(Vector2.Col): happy path and bad dimension mismatch")
    public void testMultiplyVector2Col()
    {
        // Left: 3x2, km
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0}, 3, 2, Length.Unit.km);

        // Right vector: [2 m; 3 m]
        Vector2.Col<Length> v2 = new Vector2.Col<>(2.0, 3.0, Length.Unit.m);

        VectorN.Col<SIQuantity> prod = left.multiply(v2);
        assertEquals(3, prod.size());
        assertEquals(8000.0, prod.get(0).si(), EPS); // 1000*2 + 2000*3
        assertEquals(18000.0, prod.get(1).si(), EPS); // 3000*2 + 4000*3
        assertEquals(28000.0, prod.get(2).si(), EPS); // 5000*2 + 6000*3

        VectorN.Col<Area> area = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, area.getDisplayUnit());

        // Bad path: left must have cols == 2
        MatrixNxM<Length> badLeft = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> badLeft.multiply(v2));
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(Vector3.Col)
    // ------------------------------------------------------------------------------------

    /**
     * (Mx3) x (3x1) -&gt; (Mx1) VectorN.Col&lt;SIQuantity, SIUnit&gt;.
     */
    @Test
    @DisplayName("MatrixNxM.multiply(Vector3.Col): happy path and bad dimension mismatch")
    public void testMultiplyVector3Col()
    {
        // Left: 2x3, km
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0}, 2, 3, Length.Unit.km);

        // Right vector: [1 m; 2 m; 3 m]
        Vector3.Col<Length> v3 = Vector3.Col.of(1.0, 2.0, 3.0, Length.Unit.m);

        VectorN.Col<SIQuantity> prod = left.multiply(v3);
        assertEquals(2, prod.size());
        // Row0: 1000*1 + 2000*2 + 3000*3 = 1000 + 4000 + 9000 = 14000
        assertEquals(14000.0, prod.get(0).si(), EPS);
        // Row1: 4000*1 + 5000*2 + 6000*3 = 4000 + 10000 + 18000 = 32000
        assertEquals(32000.0, prod.get(1).si(), EPS);

        VectorN.Col<Area> area = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, area.getDisplayUnit());

        // Bad path: left must have cols == 3
        MatrixNxM<Length> badLeft = ofSi(new double[] {1.0, 2.0, 3.0, 4.0}, 2, 2, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> badLeft.multiply(v3));
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — multiply(VectorN.Col)
    // ------------------------------------------------------------------------------------

    /**
     * (MxN) x (Nx1) -&gt; (Mx1) VectorN.Col&lt;SIQuantity, SIUnit&gt;.
     */
    @Test
    @DisplayName("MatrixNxM.multiply(VectorN.Col): happy path and bad dimension mismatch")
    public void testMultiplyVectorNCol()
    {
        // Left: 3x2, km
        MatrixNxM<Length> left = ofSi(new double[] {1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0}, 3, 2, Length.Unit.km);

        // Right: VectorN.Col length 2 in m (use SI factory if available)
        VectorN.Col<Length> vN = VectorN.Col.of(new double[] {2.0, 3.0}, Length.Unit.m);

        VectorN.Col<SIQuantity> prod = left.multiply(vN);
        assertEquals(3, prod.size());
        assertEquals(8000.0, prod.get(0).si(), EPS);
        assertEquals(18000.0, prod.get(1).si(), EPS);
        assertEquals(28000.0, prod.get(2).si(), EPS);

        VectorN.Col<Area> area = prod.as(Area.Unit.m2);
        assertEquals(Area.Unit.m2, area.getDisplayUnit());

        // Bad path: mismatch cols(left)=3 with vector length=2
        MatrixNxM<Length> badLeft = ofSi(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 2, 3, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> badLeft.multiply(vN));
    }

    // ------------------------------------------------------------------------------------
    // transpose(): Dense/Sparse × (Double/Float) backings
    // ------------------------------------------------------------------------------------

    /**
     * Verify transpose() for a DenseDoubleDataSi-backed rectangular matrix (2x3 → 3x2): - Row-major mapping:
     * [a11,a12,a13,a21,a22,a23] → [a11,a21,a12,a22,a13,a23]. - Shape swap rows↔cols. - Display unit preserved. - Double
     * transpose returns original SI values.
     */
    @Test
    @DisplayName("transpose(): DenseDoubleDataSi 2x3 → 3x2, unit preserved, T(T(A))=A")
    public void testTransposeDenseDouble()
    {
        // A (2x3) in km (SI values chosen directly for clarity)
        double[] si = {1, 2, 3, 4, 5, 6};
        MatrixNxM<Length> a = new MatrixNxM<>(new DenseDoubleDataSi(si, 2, 3), Length.Unit.km);
        assertEquals(2, a.rows());
        assertEquals(3, a.cols());

        MatrixNxM<Length> t = a.transpose();
        assertEquals(3, t.rows());
        assertEquals(2, t.cols());
        assertEquals(a.getDisplayUnit(), t.getDisplayUnit(), "Display unit must be preserved.");
        assertArrayEquals(new double[] {1, 4, 2, 5, 3, 6}, t.getSiArray(), EPS, "Row-major remapping failed.");

        // Double transpose should restore the original SI ordering
        MatrixNxM<Length> tt = t.transpose();
        assertEquals(a.rows(), tt.rows());
        assertEquals(a.cols(), tt.cols());
        assertArrayEquals(a.getSiArray(), tt.getSiArray(), EPS, "Double transpose must restore original SI data.");
    }

    /**
     * Verify transpose() for a DenseFloatDataSi-backed rectangular matrix (2x3 → 3x2): - Correct element remapping in row-major
     * order. - Shape swap rows↔cols. - Display unit preserved.
     */
    @Test
    @DisplayName("transpose(): DenseFloatDataSi 2x3 → 3x2, unit preserved")
    public void testTransposeDenseFloat()
    {
        // A (2x3) in meters, float-backed storage
        float[] sfi = new float[] {1f, 2f, 3f, 4f, 5f, 6f};
        MatrixNxM<Length> a = new MatrixNxM<>(new DenseFloatDataSi(sfi, 2, 3), Length.Unit.m);
        assertEquals(2, a.rows());
        assertEquals(3, a.cols());

        MatrixNxM<Length> t = a.transpose();
        assertEquals(3, t.rows());
        assertEquals(2, t.cols());
        assertEquals(a.getDisplayUnit(), t.getDisplayUnit(), "Display unit must be preserved.");
        assertArrayEquals(new double[] {1, 4, 2, 5, 3, 6}, t.getSiArray(), EPS, "Row-major remapping failed.");
    }

    /**
     * Verify transpose() for a SparseDoubleDataSi-backed rectangular matrix (2x3 → 3x2) containing zeros: - Correct element
     * remapping in row-major order. - Shape swap rows↔cols. - nnz (non-zero count) preserved (zeros remain zeros after
     * transpose).
     */
    @Test
    @DisplayName("transpose(): SparseDoubleDataSi with zeros, 2x3 → 3x2, nnz preserved")
    public void testTransposeSparseDouble()
    {
        // A (2x3) with explicit zeros to exercise sparsity:
        // [1, 0, 0
        // 4, 0, 6]
        double[] si = {1, 0, 0, 4, 0, 6};
        MatrixNxM<Length> a = new MatrixNxM<>(new SparseDoubleDataSi(si, 2, 3), Length.Unit.m);
        assertEquals(2, a.rows());
        assertEquals(3, a.cols());
        assertEquals(3, a.nnz(), "Three non-zeros expected in source.");

        MatrixNxM<Length> t = a.transpose();
        assertEquals(3, t.rows());
        assertEquals(2, t.cols());
        assertEquals(a.getDisplayUnit(), t.getDisplayUnit(), "Display unit must be preserved.");
        // Transposed layout (3x2):
        // [1, 4
        // 0, 0
        // 0, 6]
        assertArrayEquals(new double[] {1, 4, 0, 0, 0, 6}, t.getSiArray(), EPS, "Row-major remapping failed.");
        assertEquals(3, t.nnz(), "nnz must be preserved after transpose.");
    }

    /**
     * Verify transpose() for a SparseFloatDataSi-backed rectangular matrix (2x3 → 3x2) containing zeros: - Correct element
     * remapping in row-major order. - Shape swap rows↔cols. - nnz preserved (zeros remain zeros).
     */
    @Test
    @DisplayName("transpose(): SparseFloatDataSi with zeros, 2x3 → 3x2, nnz preserved")
    public void testTransposeSparseFloat()
    {
        // A (2x3) with zeros, float-backed storage:
        // [1, 0, 0
        // 4, 0, 6]
        float[] sfi = new float[] {1f, 0f, 0f, 4f, 0f, 6f};
        MatrixNxM<Length> a = new MatrixNxM<>(new SparseFloatDataSi(sfi, 2, 3), Length.Unit.m);
        assertEquals(2, a.rows());
        assertEquals(3, a.cols());
        assertEquals(3, a.nnz(), "Three non-zeros expected in source.");

        MatrixNxM<Length> t = a.transpose();
        assertEquals(3, t.rows());
        assertEquals(2, t.cols());
        assertEquals(a.getDisplayUnit(), t.getDisplayUnit(), "Display unit must be preserved.");
        assertArrayEquals(new double[] {1, 4, 0, 0, 0, 6}, t.getSiArray(), EPS, "Row-major remapping failed.");
        assertEquals(3, t.nnz(), "nnz must be preserved after transpose.");
    }

    // ------------------------------------------------------------------------------------
    // MatrixNxM — Static factory methods of() and ofSi()
    // ------------------------------------------------------------------------------------

    /**
     * Test {@link MatrixNxM#of(double[], int, int, Unit)} for nulls, invalid sizes, and SI conversion using {@link Length}.
     */
    @Test
    @DisplayName("of(double[], rows, cols, Unit): nulls, size checks, SI conversion (cm/km)")
    public void testOfDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxM.of((double[]) null, 2, 3, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> MatrixNxM.of(new double[6], 2, 3, null));

        // wrong array length
        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new double[5], 2, 3, Length.Unit.m));

        // invalid rows / cols
        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new double[6], 0, 3, Length.Unit.m));
        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new double[6], 2, 0, Length.Unit.m));

        double[] inCm = {1, 2, 3, 4, 5, 6};
        MatrixNxM<Length> m = MatrixNxM.of(inCm, 2, 3, Length.Unit.cm);

        assertEquals(2, m.rows());
        assertEquals(3, m.cols());
        assertArrayEquals(new double[] {0.01, 0.02, 0.03, 0.04, 0.05, 0.06}, m.getSiArray(), EPS);
    }

    /**
     * Test {@link MatrixNxM#ofSi(double[], int, int, Unit)} for nulls, size errors, and display-unit handling.
     */
    @Test
    @DisplayName("ofSi(double[], rows, cols, Unit): nulls, size checks, display unit")
    public void testOfSiDoubleArray()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxM.ofSi((double[]) null, 2, 2, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> MatrixNxM.ofSi(new double[4], 2, 2, null));

        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.ofSi(new double[3], 2, 2, Length.Unit.m));

        double[] si = {1, 2, 3, 4};
        MatrixNxM<Length> m = MatrixNxM.ofSi(si, 2, 2, Length.Unit.km);

        assertEquals(Length.Unit.km, m.getDisplayUnit());
        assertArrayEquals(si, m.getSiArray(), EPS);
    }

    /**
     * Test {@link MatrixNxM#of(Quantity[], int, int)} for nulls, size errors, and SI conversion.
     */
    @Test
    @DisplayName("of(Q[], rows, cols): nulls, size checks, SI conversion")
    public void testOfQuantityArray()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxM.of((Length[]) null, 2, 2));

        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new Length[] {}, 2, 2));

        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new Length[] {Length.ofSi(1)}, 2, 2));

        Length[] data = {new Length(1, Length.Unit.km), new Length(2, Length.Unit.m), new Length(3, Length.Unit.cm),
                new Length(4, Length.Unit.mm)};

        MatrixNxM<Length> m = MatrixNxM.of(data, 2, 2);

        assertEquals(1000.0, m.si(0, 0), EPS);
        assertEquals(2.0, m.si(0, 1), EPS);
        assertEquals(0.03, m.si(1, 0), EPS);
        assertEquals(0.004, m.si(1, 1), EPS);
    }

    /**
     * Test {@link MatrixNxM#ofSi(double[][], Unit)} for nulls, empty grids, ragged grids, and SI usage.
     */
    @Test
    @DisplayName("ofSi(double[][], Unit): nulls, empty/ragged grids, SI values")
    public void testOfSiDoubleGrid()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxM.ofSi((double[][]) null, Length.Unit.m));

        assertThrows(NullPointerException.class, () -> MatrixNxM.ofSi(new double[2][2], null));

        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.ofSi(new double[][] {}, Length.Unit.m));

        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.ofSi(new double[][] {{1, 2}, {3}}, Length.Unit.m));

        double[][] gridSi = {{1, 2, 3}, {4, 5, 6}};

        MatrixNxM<Length> m = MatrixNxM.ofSi(gridSi, Length.Unit.m);
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, m.getSiArray(), EPS);
    }

    /**
     * Test {@link MatrixNxM#of(double[][], Unit)} for unit conversion using {@link Duration}.
     */
    @Test
    @DisplayName("of(double[][], Unit): rectangular grid and unit conversion (ms/h)")
    public void testOfDoubleGridWithUnit()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxM.of((double[][]) null, Duration.Unit.s));

        assertThrows(NullPointerException.class, () -> MatrixNxM.of(new double[2][2], null));

        double[][] inHours = {{1, 2}, {3, 4}};

        MatrixNxM<Duration> m = MatrixNxM.of(inHours, Duration.Unit.h);

        assertEquals(3600.0, m.si(0, 0), EPS);
        assertEquals(7200.0, m.si(0, 1), EPS);
        assertEquals(10_800.0, m.si(1, 0), EPS);
        assertEquals(14_400.0, m.si(1, 1), EPS);
    }

    /**
     * Test {@link MatrixNxM#of(Quantity[][])} for nulls, empty grids, and SI conversion.
     */
    @Test
    @DisplayName("of(Q[][]): nulls, empty grid, SI conversion")
    public void testOfQuantityGrid()
    {
        assertThrows(NullPointerException.class, () -> MatrixNxM.of((Duration[][]) null));

        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new Duration[][] {}));

        assertThrows(NullPointerException.class, () -> MatrixNxM.of(new Duration[][] {null}));

        assertThrows(IllegalArgumentException.class, () -> MatrixNxM.of(new Duration[][] {{}}));

        Duration[][] grid = {{new Duration(1, Duration.Unit.ms), new Duration(2, Duration.Unit.ms)},
                {new Duration(3, Duration.Unit.ms), new Duration(4, Duration.Unit.ms)}};

        MatrixNxM<Duration> m = MatrixNxM.of(grid);

        assertEquals(0.001, m.si(0, 0), EPS);
        assertEquals(0.002, m.si(0, 1), EPS);
        assertEquals(0.003, m.si(1, 0), EPS);
        assertEquals(0.004, m.si(1, 1), EPS);
    }

}
