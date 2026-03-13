package org.djunits.vecmat.dnxm;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Area;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.Speed;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
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
     * @param unit display unit
     * @return matrix
     */
    private static MatrixNxM<Length, Length.Unit> ofSi(final double[] si, final int rows, final int cols,
            final Length.Unit unit)
    {
        final double[] inUnit = new double[si.length];
        for (int i = 0; i < si.length; i++)
        {
            inUnit[i] = unit.fromBaseValue(si[i]);
        }
        return MatrixNxM.of(inUnit, rows, cols, unit);
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
        MatrixNxM<Length, Length.Unit> m = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000}, m.si(), EPS);
        assertAll(() -> assertEquals(3, m.rows()), () -> assertEquals(2, m.cols()));
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
        MatrixNxM<Length, Length.Unit> m = MatrixNxM.of(new double[][] {{1, 2, 3}, {4, 5, 6}}, Length.Unit.km);
        assertArrayEquals(new double[] {1000, 2000, 3000, 4000, 5000, 6000}, m.si(), EPS);
        assertAll(() -> assertEquals(2, m.rows()), () -> assertEquals(3, m.cols()));
    }

    /** Verify of(Q[][],U) accepts per-cell units via DenseDoubleData and sets display unit. */
    @Test
    @DisplayName("of(Q[][],U): quantity grid accepted")
    public void testFactoryQuantityGrid()
    {
        Length[][] q = new Length[][] {{Length.of(1.0, "km"), Length.of(200.0, "m")}};
        MatrixNxM<Length, Length.Unit> m = MatrixNxM.of(q, Length.Unit.m);
        assertArrayEquals(new double[] {1000.0, 200.0}, m.si(), EPS);
        assertAll(() -> assertEquals(1, m.rows()), () -> assertEquals(2, m.cols()));
    }

    /** Verify instantiateSi preserves display unit and adopts provided SI. */
    @Test
    @DisplayName("instantiateSi: preserve display unit, adopt SI")
    public void testInstantiateSi()
    {
        MatrixNxM<Length, Length.Unit> base = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.km);
        MatrixNxM<Length, Length.Unit> inst = base.instantiateSi(new double[] {6, 5, 4, 3, 2, 1});
        assertAll(() -> assertEquals(base.getDisplayUnit(), inst.getDisplayUnit()),
                () -> assertArrayEquals(new double[] {6, 5, 4, 3, 2, 1}, inst.si(), EPS));
    }

    // ------------------------------------------------------------------------------------
    // Basics: rows/cols/value/isRelative, setDisplayUnit, toString
    // ------------------------------------------------------------------------------------

    /** Verify rows/cols/value/isRelative on a 3x2 matrix. */
    @Test
    @DisplayName("rows/cols/value/isRelative")
    public void testShapeAndValue()
    {
        MatrixNxM<Length, Length.Unit> a32 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        assertAll(() -> assertEquals(3, a32.rows()), () -> assertEquals(2, a32.cols()),
                () -> assertEquals(1.0, a32.get(1, 1).si(), EPS), () -> assertTrue(a32.isRelative()));
    }

    /** setDisplayUnit only affects presentation; SI unchanged. */
    @Test
    @DisplayName("setDisplayUnit() only changes presentation")
    public void testSetDisplayUnit()
    {
        MatrixNxM<Length, Length.Unit> m = ofSi(new double[] {1000, 2000, 3000, 4000}, 2, 2, Length.Unit.km);
        m.setDisplayUnit(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, m.getDisplayUnit()), () -> assertEquals(1000.0, m.get(1, 1).si(), EPS));
        m.setDisplayUnit(Length.Unit.km);
        assertEquals(Length.Unit.km, m.getDisplayUnit());
    }

    /** toString and toString(unit) include unit abbreviation. */
    @Test
    @DisplayName("toString()/toString(unit)")
    public void testToString()
    {
        MatrixNxM<Length, Length.Unit> m = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.km);
        assertAll(() -> assertTrue(m.toString().contains("km")), () -> assertTrue(m.toString(Length.Unit.m).contains("m")));
    }

    // ------------------------------------------------------------------------------------
    // Algebra/stats defaults and Hadamard
    // ------------------------------------------------------------------------------------

    /** Verify add/sub (Q &amp; VM), negate/abs/scaleBy, stats on 2x3 matrix. */
    @Test
    @DisplayName("add/sub (Q & VM), negate/abs/scaleBy, stats")
    public void testAlgebraAndStats()
    {
        MatrixNxM<Length, Length.Unit> a = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.m);
        MatrixNxM<Length, Length.Unit> b = ofSi(new double[] {6, 5, 4, 3, 2, 1}, 2, 3, Length.Unit.m);
        Length inc = Length.ofSi(2.0);

        assertArrayEquals(new double[] {7, 7, 7, 7, 7, 7}, a.add(b).si(), EPS);
        assertArrayEquals(new double[] {-5, -3, -1, 1, 3, 5}, a.subtract(b).si(), EPS);
        assertArrayEquals(new double[] {3, 4, 5, 6, 7, 8}, a.add(inc).si(), EPS);
        assertArrayEquals(new double[] {-1, 0, 1, 2, 3, 4}, a.subtract(inc).si(), EPS);

        assertArrayEquals(new double[] {-1, -2, -3, -4, -5, -6}, a.negate().si(), EPS);
        assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6}, a.abs().si(), EPS);
        assertArrayEquals(new double[] {2, 4, 6, 8, 10, 12}, a.scaleBy(2.0).si(), EPS);

        assertAll(() -> assertEquals(3.5, a.mean().si(), EPS), () -> assertEquals(3.5, a.median().si(), EPS),
                () -> assertEquals(1.0, a.min().si(), EPS), () -> assertEquals(6.0, a.max().si(), EPS),
                () -> assertEquals(a.max().si(), a.mode().si(), EPS), () -> assertEquals(21.0, a.sum().si(), EPS));
    }

    /** Verify Hadamard invert/multiply/divide with unit composition. */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition")
    public void testHadamard()
    {
        MatrixNxM<Length, Length.Unit> a = ofSi(new double[] {2, 4, 5, 10, 20, 40}, 2, 3, Length.Unit.m);
        MatrixNxM<Length, Length.Unit> b = MatrixNxM.of(new double[] {1, 2, 0.5, 4, 0.25, 8}, 2, 3, Length.Unit.km);

        MatrixNxM<SIQuantity, SIUnit> inv = a.invertElements();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1, 0.05, 0.025}, inv.si(), EPS);

        MatrixNxM<SIQuantity, SIUnit> mul = a.multiplyElements(b);
        assertAll(() -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit()),
                () -> assertArrayEquals(new double[] {2000, 8000, 2500, 40_000, 5_000, 320_000}, mul.si(), EPS));

        MatrixNxM<SIQuantity, SIUnit> div = a.divideElements(b);
        assertAll(() -> assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit()),
                () -> assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025, 0.08, 0.005}, div.si(), EPS));
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
        MatrixNxM<Length, Length.Unit> a = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        // B(2x3) in km (display) ⇒ SI = x1000
        MatrixNxM<Length, Length.Unit> b = MatrixNxM.of(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.km);

        MatrixNxM<SIQuantity, SIUnit> c = a.multiply(b);
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), c.getDisplayUnit());

        // Expected SI: A·(1000·B') = 1000·(A·B')
        // A·B' = [[1*1+2*4, 1*2+2*5, 1*3+2*6], ...] = [[9,12,15],[19,26,33],[29,40,51]]
        assertArrayEquals(new double[] {9000, 12000, 15000, 19000, 26000, 33000, 29000, 40000, 51000}, c.si(), EPS);
    }

    /** Verify Nx2 x 2x2 and Nx3 x 3x3 multiplication variants. */
    @Test
    @DisplayName("A(Nx2)xB(2x2), A(Nx3)xB(3x3)")
    public void testMultiply2x2And3x3()
    {
        // A(3x2) and 2x2
        MatrixNxM<Length, Length.Unit> a32 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        Matrix2x2<Length, Length.Unit> b22 = Matrix2x2.of(new double[] {1, 2, 3, 4}, Length.Unit.km);
        MatrixNxM<SIQuantity, SIUnit> r32 = a32.multiply(b22);
        assertArrayEquals(new double[] {7000, 10000, 15000, 22000, 23000, 34000}, r32.si(), EPS);

        // A(2x3) and 3x3
        MatrixNxM<Length, Length.Unit> a23 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.m);
        Matrix3x3<Length, Length.Unit> b33 = Matrix3x3.of(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Length.Unit.km);
        MatrixNxM<SIQuantity, SIUnit> r23 = a23.multiply(b33);
        // A·B' = [[30,36,42],[66,81,96]] then x1000
        assertArrayEquals(new double[] {30_000, 36_000, 42_000, 66_000, 81_000, 96_000}, r23.si(), EPS);
    }

    /** Verify A(Nx2)·v2 and A(Nx3)·v3 and A(NxM)·vN return VectorN.Col with correct unit. */
    @Test
    @DisplayName("A·v: size 2, size 3, and size N")
    public void testMultiplyVector()
    {
        MatrixNxM<Length, Length.Unit> a32 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        Vector2.Col<Length, Length.Unit> v2 = new Vector2.Col<>(1.0, 2.0, Length.Unit.km); // SI [1000,2000]
        VectorN.Col<SIQuantity, SIUnit> r2 = a32.multiply(v2);
        assertAll(() -> assertEquals(3, r2.size()), () -> assertArrayEquals(new double[] {5000, 11000, 17000}, r2.si(), EPS),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), r2.getDisplayUnit()));

        MatrixNxM<Length, Length.Unit> a23 = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 2, 3, Length.Unit.m);
        Vector3.Col<Length, Length.Unit> v3 = new Vector3.Col<>(1.0, 2.0, 3.0, Length.Unit.km); // [1k,2k,3k]
        VectorN.Col<SIQuantity, SIUnit> r3 = a23.multiply(v3);
        assertAll(() -> assertEquals(2, r3.size()), () -> assertArrayEquals(new double[] {14000, 32000}, r3.si(), EPS));

        // General VectorN.Col (size = cols)
        VectorN.Col<Length, Length.Unit> vN =
                VectorN.Col.ofSi(new DenseDoubleDataSi(new double[] {1000, 2000}, 2, 1), Length.Unit.km);
        VectorN.Col<SIQuantity, SIUnit> rN = a32.multiply(vN);
        assertArrayEquals(new double[] {5000, 11000, 17000}, rN.si(), EPS);
    }

    // ------------------------------------------------------------------------------------
    // as(targetUnit) and “as” matrix/vector conversions
    // ------------------------------------------------------------------------------------

    /** Verify as(targetUnit) success (m↔km) and failure (dimension mismatch). */
    @Test
    @DisplayName("as(targetUnit) success/failure")
    public void testAsTargetUnit()
    {
        MatrixNxM<Length, Length.Unit> m = MatrixNxM.of(new double[] {1, 2, 3, 4}, 2, 2, Length.Unit.km);
        MatrixNxM<Length, Length.Unit> asM = m.as(Length.Unit.m);
        assertAll(() -> assertEquals(Length.Unit.m, asM.getDisplayUnit()), () -> assertArrayEquals(m.si(), asM.si(), EPS));
        SIUnit s = SIUnit.of("s");
        assertThrows(IllegalArgumentException.class, () -> m.as(s));
    }

    /** Verify as* matrix conversions and shape checks. */
    @Test
    @DisplayName("asMatrix2x2 / asMatrix3x3 / asMatrixNxN")
    public void testAsMatrixConversions()
    {
        MatrixNxM<Length, Length.Unit> m22 = ofSi(new double[] {1, 2, 3, 4}, 2, 2, Length.Unit.m);
        Matrix2x2<Length, Length.Unit> mm22 = m22.asMatrix2x2();
        assertArrayEquals(new double[] {1.0, 2.0, 3.0, 4.0}, mm22.si(), EPS);

        MatrixNxM<Length, Length.Unit> m33 = ofSi(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 3, 3, Length.Unit.m);
        Matrix3x3<Length, Length.Unit> mm33 = m33.asMatrix3x3();
        assertArrayEquals(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}, mm33.si(), EPS);

        MatrixNxN<Length, Length.Unit> mxn = m33.asMatrixNxN();
        assertEquals(3, mxn.rows());
    }

    /** Verify as* vector conversions for 2/3/N Col and Row, plus shape checks. */
    @Test
    @DisplayName("asVector2/3/N (Col/Row) conversions")
    public void testAsVectorConversions()
    {
        // Col
        MatrixNxM<Length, Length.Unit> c21 = ofSi(new double[] {1000, 2000}, 2, 1, Length.Unit.km);
        Vector2.Col<Length, Length.Unit> vc2 = c21.asVector2Col();
        assertAll(() -> assertEquals(2, vc2.size()), () -> assertArrayEquals(new double[] {1000, 2000}, vc2.si(), EPS));

        MatrixNxM<Length, Length.Unit> c31 = ofSi(new double[] {1, 2, 3}, 3, 1, Length.Unit.m);
        Vector3.Col<Length, Length.Unit> vc3 = c31.asVector3Col();
        assertArrayEquals(new double[] {1, 2, 3}, vc3.si(), EPS);

        VectorN.Col<Length, Length.Unit> vcn = c31.asVectorNCol();
        assertEquals(3, vcn.size());

        // Row
        MatrixNxM<Length, Length.Unit> r12 = ofSi(new double[] {1000, 2000}, 1, 2, Length.Unit.km);
        Vector2.Row<Length, Length.Unit> vr2 = r12.asVector2Row();
        assertArrayEquals(new double[] {1000, 2000}, vr2.si(), EPS);

        MatrixNxM<Length, Length.Unit> r13 = ofSi(new double[] {1, 2, 3}, 1, 3, Length.Unit.m);
        Vector3.Row<Length, Length.Unit> vr3 = r13.asVector3Row();
        assertArrayEquals(new double[] {1, 2, 3}, vr3.si(), EPS);

        VectorN.Row<Length, Length.Unit> vrn = r13.asVectorNRow();
        assertEquals(3, vrn.size());

        // Negative shape checks
        MatrixNxM<Length, Length.Unit> bad = ofSi(new double[] {1, 2, 3, 4}, 2, 2, Length.Unit.m);
        assertAll(() -> assertThrows(IllegalStateException.class, () -> bad.asVector2Col()),
                () -> assertThrows(IllegalStateException.class, () -> bad.asVector3Row()),
                () -> assertThrows(IllegalStateException.class, () -> bad.asVectorNRow()) // rows()!=1
        );
    }

    // ------------------------------------------------------------------------------------
    // Scalar extraction & equals/hashCode
    // ------------------------------------------------------------------------------------

    /** Verify scalar extraction helpers. */
    @Test
    @DisplayName("getScalars / getRowScalars / getColumnScalars / getDiagonalScalars")
    public void testScalarExtraction()
    {
        MatrixNxM<Length, Length.Unit> m = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        Length[][] scalars = m.getScalarGrid();
        assertAll(() -> assertEquals(3, scalars.length), () -> assertEquals(2, scalars[0].length),
                () -> assertEquals(1.0, scalars[0][0].si(), EPS), () -> assertEquals(6.0, scalars[2][1].si(), EPS));

        Length[] row2 = m.getRowScalars(2);
        Length[] col2 = m.getColumnScalars(2);
        assertAll(() -> assertEquals(2, row2.length), () -> assertEquals(3.0, row2[0].si(), EPS),
                () -> assertEquals(4.0, row2[1].si(), EPS), () -> assertEquals(3, col2.length),
                () -> assertEquals(2.0, col2[0].si(), EPS), () -> assertEquals(6.0, col2[2].si(), EPS));
    }

    /** Verify equals/hashCode. */
    @Test
    @DisplayName("equals / hashCode")
    public void testEqualsHash()
    {
        MatrixNxM<Length, Length.Unit> a = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        MatrixNxM<Length, Length.Unit> b = ofSi(new double[] {1, 2, 3, 4, 5, 6}, 3, 2, Length.Unit.m);
        MatrixNxM<Length, Length.Unit> c = ofSi(new double[] {1, 2, 3, 4, 5, 7}, 3, 2, Length.Unit.m);
        assertAll(() -> assertEquals(a, a), () -> assertEquals(a, b), () -> assertEquals(a.hashCode(), b.hashCode()),
                () -> assertNotEquals(a, c), () -> assertNotEquals(a, null), () -> assertNotEquals(a, "other"));
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        MatrixNxM<Length, Length.Unit> r = MatrixNxM.of(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, 3, 2, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        MatrixNxM<Speed, Speed.Unit> sr = r.divideElements(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.get(1, 1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.get(1, 2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.get(2, 1).getInUnit(), 1E-6);
        assertEquals(2.0, sr.get(2, 2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideElements(d).as(Area.Unit.m2));
    }

}
