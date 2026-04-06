package org.djunits.vecmat.dn;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.djunits.quantity.Area;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.Speed;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive unit tests for {@link VectorN}, validating both {@link VectorN.Row} and {@link VectorN.Col} implementations
 * with concrete quantity {@link Length} and display units from {@link org.djunits.quantity.Length.Unit}.
 * <p>
 * The tests encode the <em>intended</em> functional specification. They will fail when the implementation deviates (e.g.,
 * incorrect addressing in {@code get(index)} or wrong unit composition in Hadamard operations).
 * <p>
 * Coverage scope:
 * <ul>
 * <li>Construction via a {@link DataGridSi}-backed SI store</li>
 * <li>Accessors: {@code size()}, {@code rows()}, {@code cols()}, {@code isColumnVector()}, {@code si()},
 * {@code get(index)}</li>
 * <li>Iteration and {@code getScalarArray()} correctness (types, order, unit)</li>
 * <li>Fluent display-unit switching ({@code setDisplayUnit}) and stringification ({@code toString()})</li>
 * <li>Vector algebra: {@code add/subtract(Q)}, {@code add/subtract(V)}, {@code negate()}, {@code abs()},
 * {@code scaleBy(double)}</li>
 * <li>Norms: {@code norm()}, {@code normL1()}, {@code normL2()}, {@code normLp(int)}, {@code normLinf()}</li>
 * <li>Hadamard operations: {@code invertEntries()}, {@code multiplyEntries(V)}, {@code divideEntries(V)} with unit
 * composition</li>
 * <li>Statistics: {@code min()}, {@code max()}, {@code mean()}, {@code median()}, {@code sum()}</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public final class VectorNTest
{
    /** Numerical tolerance for assertions on double values. */
    private static final double EPS = 1.0E-12;

    // =====================================================================================
    // Local helpers and a minimal dense DataGrid implementation for testing
    // =====================================================================================

    /**
     * Helper that constructs a {@link VectorN.Col} of {@link Length} with display-unit inputs.
     * <p>
     * Values are given in {@code unit} and converted to SI for internal storage; the vector's display unit is set to
     * {@code unit}.
     * @param inUnit the values expressed in the given display unit; never {@code null}
     * @param unit the display unit to use; never {@code null}
     * @return a newly constructed {@link VectorN.Col} with SI storage and the specified display unit
     */
    private static VectorN.Col<Length> col(final double[] inUnit, final Length.Unit unit)
    {
        final double[] si = new double[inUnit.length];
        for (int i = 0; i < inUnit.length; i++)
        {
            si[i] = unit.toBaseValue(inUnit[i]);
        }
        return VectorN.Col.ofSi(si, unit);
    }

    /**
     * Helper that constructs a {@link VectorN.Row} of {@link Length} with display-unit inputs.
     * <p>
     * Values are given in {@code unit} and converted to SI for internal storage; the vector's display unit is set to
     * {@code unit}.
     * @param inUnit the values expressed in the given display unit; never {@code null}
     * @param unit the display unit to use; never {@code null}
     * @return a newly constructed {@link VectorN.Row} with SI storage and the specified display unit
     */
    private static VectorN.Row<Length> row(final double[] inUnit, final Length.Unit unit)
    {
        final double[] si = new double[inUnit.length];
        for (int i = 0; i < inUnit.length; i++)
        {
            si[i] = unit.toBaseValue(inUnit[i]);
        }
        return VectorN.Row.ofSi(si, unit);
    }

    // =====================================================================================
    // Construction & shape
    // =====================================================================================

    /**
     * Verify that a {@link VectorN.Col} constructed from display values stores SI internally and reports correct shape
     * properties (rows, cols, size, orientation).
     */
    @Test
    @DisplayName("Constructor SI storage & shape (Col)")
    public void testConstructorAndShapeCol()
    {
        final VectorN.Col<Length> c = col(new double[] {1, 2, 3}, Length.Unit.km);
        assertArrayEquals(new double[] {1000.0, 2000.0, 3000.0}, c.si(), EPS, "SI storage");
        assertEquals(3, c.size(), "size");
        assertEquals(3, c.rows(), "rows");
        assertEquals(1, c.cols(), "cols");
        assertTrue(c.isColumnVector(), "isColumnVector");
        assertEquals(Length.Unit.km, c.getDisplayUnit(), "display unit");

        var ddd = new DenseDoubleDataSi(new double[] {1, 2, 3, 4}, 2, 2);
        assertThrows(IllegalArgumentException.class, () -> new VectorN.Col<>(ddd, Length.Unit.km));
    }

    /**
     * Verify that a {@link VectorN.Row} constructed from display values stores SI internally and reports correct shape
     * properties (rows, cols, size, orientation).
     */
    @Test
    @DisplayName("Constructor SI storage & shape (Row)")
    public void testConstructorAndShapeRow()
    {
        final VectorN.Row<Length> r = row(new double[] {10, 200, 3}, Length.Unit.cm);
        assertArrayEquals(new double[] {0.10, 2.0, 0.03}, r.si(), EPS, "SI storage");
        assertEquals(3, r.size(), "size");
        assertEquals(1, r.rows(), "rows");
        assertEquals(3, r.cols(), "cols");
        assertFalse(r.isColumnVector(), "isColumnVector");
        assertEquals(Length.Unit.cm, r.getDisplayUnit(), "display unit");

        var ddd = new DenseDoubleDataSi(new double[] {1, 2, 3, 4}, 2, 2);
        assertThrows(IllegalArgumentException.class, () -> new VectorN.Row<>(ddd, Length.Unit.km));
    }

    /** Verify instantiateSi(double[]) enforces length=3 and delegates (Col). */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=3 and delegates (Col)")
    public void testInstantiateSiArrayCol()
    {
        VectorN.Col<Length> c = col(new double[] {1.0, 2.0, 3.0}, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> c.instantiateSi(new double[] {1.0, 2.0}), "Wrong length must throw");
        VectorN.Col<Length> d = c.instantiateSi(new double[] {10.0, 20.0, 30.0});
        assertArrayEquals(new double[] {10.0, 20.0, 30.0}, d.si(), EPS);
        assertEquals(c.getDisplayUnit(), d.getDisplayUnit());

        double[] newSi = {20, 30, 40};
        VectorN.Col<SIQuantity> csiVector = c.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", csiVector.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, csiVector.si(), EPS, "si array used as-is");
        assertEquals(20.0, csiVector.get(0).si(), EPS);

        VectorN.Col<SIQuantity> csiVectorOf = VectorN.Col.of(new double[] {20.0, 30.0, 40.0}, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", csiVectorOf.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, csiVectorOf.si(), EPS, "si array used as-is");
        assertEquals(20.0, csiVectorOf.get(0).si(), EPS);

        assertThrows(IllegalArgumentException.class, () -> c.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
    }

    /** Verify instantiateSi(double[]) enforces length=3 and delegates (Row). */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=3 and delegates (Row)")
    public void testInstantiateSiArrayRow()
    {
        VectorN.Row<Length> r = row(new double[] {1.0, 2.0, 3.0}, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> r.instantiateSi(new double[] {1.0, 2.0}), "Wrong length must throw");
        VectorN.Row<Length> s = r.instantiateSi(new double[] {10.0, 20.0, 30.0});
        assertArrayEquals(new double[] {10.0, 20.0, 30.0}, s.si(), EPS);
        assertEquals(r.getDisplayUnit(), s.getDisplayUnit());

        double[] newSi = {20, 30, 40};
        VectorN.Row<SIQuantity> rsiVector = r.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", rsiVector.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, rsiVector.si(), EPS, "si array used as-is");
        assertEquals(20.0, rsiVector.get(0).si(), EPS);

        VectorN.Row<SIQuantity> rsiVectorOf = VectorN.Row.of(new double[] {20.0, 30.0, 40.0}, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", rsiVectorOf.getDisplayUnit().siUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, rsiVectorOf.si(), EPS, "si array used as-is");
        assertEquals(20.0, rsiVectorOf.get(0).si(), EPS);

        assertThrows(IllegalArgumentException.class, () -> r.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
    }

    /**
     * Test the of() methods for Col.
     */
    @Test
    public void testOfCol()
    {
        var v1 = VectorN.Col.of(new double[] {1, 3, 5}, Length.Unit.km);
        assertEquals(3000.0, v1.si()[1], 1E-6);

        var q2 = new Length[] {Length.of(1.0, "km"), Length.of(3.0, "km"), Length.of(5.0, "km")};
        var v2 = VectorN.Col.of(q2);
        assertEquals(3000.0, v2.si()[1], 1E-6);

        List<Length> l3 = Arrays.asList(q2);
        var v3 = VectorN.Col.of(l3);
        assertEquals(3000.0, v3.si()[1], 1E-6);

        var dsi = new double[] {1, 2, 3, 4};
        var ddd = new DenseDoubleDataSi(dsi, 4, 1);
        var v4 = VectorN.Col.ofSi(ddd, Length.Unit.km);
        assertEquals(2.0, v4.si()[1], 1E-6);

        var v5 = VectorN.Col.ofSi(dsi, Length.Unit.km);
        assertEquals(2.0, v5.si()[1], 1E-6);
    }

    /**
     * Test the of() methods for Row.
     */
    @Test
    public void testOfRow()
    {
        var v1 = VectorN.Row.of(new double[] {1, 3, 5}, Length.Unit.km);
        assertEquals(3000.0, v1.si()[1], 1E-6);

        var q2 = new Length[] {Length.of(1.0, "km"), Length.of(3.0, "km"), Length.of(5.0, "km")};
        var v2 = VectorN.Row.of(q2);
        assertEquals(3000.0, v2.si()[1], 1E-6);

        List<Length> l3 = Arrays.asList(q2);
        var v3 = VectorN.Row.of(l3);
        assertEquals(3000.0, v3.si()[1], 1E-6);

        var dsi = new double[] {1, 2, 3, 4};
        var ddd = new DenseDoubleDataSi(dsi, 1, 4);
        var v4 = VectorN.Row.ofSi(ddd, Length.Unit.km);
        assertEquals(2.0, v4.si()[1], 1E-6);

        var v5 = VectorN.Row.ofSi(dsi, Length.Unit.km);
        assertEquals(2.0, v5.si()[1], 1E-6);
    }

    // =====================================================================================
    // Accessors & indexing
    // =====================================================================================

    /**
     * Verify {@link VectorN.Row#nnz()} and {@link VectorN.Col#nnz()}.
     */
    @Test
    @DisplayName("test nnz()")
    public void testNnz()
    {
        final VectorN.Row<Length> r = row(new double[] {1, 2, 3, 4}, Length.Unit.m);
        final VectorN.Col<Length> c = col(new double[] {5, 6, 7, 8}, Length.Unit.m);
        assertEquals(4, c.nnz());
        assertEquals(4, r.nnz());
        assertEquals(4, c.dataSi.nnz());
        assertEquals(4, r.dataSi.nnz());

        VectorN.Row<Length> r0 = row(new double[] {0.0, 0.0, 0.0}, Length.Unit.m);
        VectorN.Col<Length> c0 = col(new double[] {0.0, 0.0, 0.0}, Length.Unit.m);
        assertEquals(0, r0.nnz());
        assertEquals(0, c0.nnz());
        assertEquals(0, c0.dataSi.nnz());
        assertEquals(0, r0.dataSi.nnz());
    }

    /**
     * Verify {@link VectorN.Row#get(int)} and {@link VectorN.Col#get(int)} with valid and invalid indices; this test ensures
     * the index addressing follows the intended semantics: a row vector reads from {@code (0, index-1)}, a column vector from
     * {@code (index-1, 0)}.
     */
    @Test
    @DisplayName("get(index), addressing semantics (Row & Col)")
    public void testIndexing()
    {
        final VectorN.Row<Length> r = row(new double[] {1, 2, 3, 4}, Length.Unit.m);
        final VectorN.Col<Length> c = col(new double[] {5, 6, 7, 8}, Length.Unit.m);

        // Valid indices
        assertEquals(1.0, r.get(0).si(), EPS, "row get(0)");
        assertEquals(4.0, r.get(3).si(), EPS, "row get(3)");
        assertEquals(5.0, c.get(0).si(), EPS, "col get(0)");
        assertEquals(8.0, c.get(3).si(), EPS, "col get(3)");

        assertEquals(1.0, r.mget(1).si(), EPS, "row mget(1)");
        assertEquals(4.0, r.mget(4).si(), EPS, "row mget(4)");
        assertEquals(5.0, c.mget(1).si(), EPS, "col mget(1)");
        assertEquals(8.0, c.mget(4).si(), EPS, "col mget(4)");

        assertEquals(1.0, r.si(0), EPS, "row si(0)");
        assertEquals(4.0, r.si(3), EPS, "row si(3)");
        assertEquals(5.0, c.si(0), EPS, "col si(0)");
        assertEquals(8.0, c.si(3), EPS, "col si(3)");

        assertEquals(1.0, r.msi(1), EPS, "row msi(1)");
        assertEquals(4.0, r.msi(4), EPS, "row msi(4)");
        assertEquals(5.0, c.msi(1), EPS, "col msi(1)");
        assertEquals(8.0, c.msi(4), EPS, "col msi(4)");

        // Invalid indices
        assertThrows(IndexOutOfBoundsException.class, () -> r.get(-1), "row get(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> r.get(4), "row get(4) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.get(-1), "col get(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.get(4), "col get(4) must throw");

        assertThrows(IndexOutOfBoundsException.class, () -> r.si(-1), "row si(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> r.si(4), "row si(4) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.si(-1), "col si(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.si(4), "col si(4) must throw");

        assertThrows(IndexOutOfBoundsException.class, () -> r.mget(0), "row mget(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> r.mget(5), "row mget(5) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.mget(0), "col mget(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.mget(5), "col mget(5) must throw");

        assertThrows(IndexOutOfBoundsException.class, () -> r.msi(0), "row msi(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> r.msi(5), "row msi(5) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.msi(0), "col msi(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.msi(5), "col msi(5) must throw");
    }

    // =====================================================================================
    // Iterator & scalar array extraction
    // =====================================================================================

    /**
     * Verify that the {@link Iterable} interface is implemented correctly: the iterator produces Q values in natural order and
     * throws {@link NoSuchElementException} at the end.
     */
    @Test
    @DisplayName("iterator() yields Q in display unit, in natural order")
    public void testIterator()
    {
        final VectorN.Row<Length> r = row(new double[] {1, 2, 3}, Length.Unit.km); // SI: 1000, 2000, 3000
        final Iterator<Length> it = r.iterator();

        final Length a = it.next();
        final Length b = it.next();
        final Length d = it.next();

        assertFalse(it.hasNext(), "no more elements");
        assertThrows(NoSuchElementException.class, it::next, "must throw at end");

        assertEquals(1.0, a.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(2.0, b.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
        assertEquals(3.0, d.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS);
    }

    /**
     * Verify that {@link VectorN#getScalarArray()} returns a {@code Q[]} of correct length and component types, with entries in
     * the expected x..n order.
     */
    @Test
    @DisplayName("getScalarArray() returns Q[] with correct order and types")
    public void testGetScalarArray()
    {
        final VectorN.Col<Length> c = col(new double[] {3, 4, 5}, Length.Unit.m);
        final Length[] arr = c.getScalarArray();
        assertEquals(3, arr.length, "length");
        assertInstanceOf(Length.class, arr[0], "arr[0] type");
        assertInstanceOf(Length.class, arr[1], "arr[1] type");
        assertInstanceOf(Length.class, arr[2], "arr[2] type");
        assertEquals(3.0, arr[0].si(), EPS);
        assertEquals(4.0, arr[1].si(), EPS);
        assertEquals(5.0, arr[2].si(), EPS);
    }

    // =====================================================================================
    // Display-unit switching & stringification
    // =====================================================================================

    /**
     * Verify that {@link VectorN#setDisplayUnit(org.djunits.unit.Unit)} returns {@code this} for fluent usage and that
     * {@link VectorN#toString()} and {@link VectorN#toString(org.djunits.unit.Unit)} contain correct orientation and unit
     * abbreviation.
     */
    @Test
    @DisplayName("setDisplayUnit() is fluent; toString contains orientation & unit")
    public void testFluentAndToString()
    {
        final VectorN.Row<Length> r = row(new double[] {1, 2, 3}, Length.Unit.m);
        final VectorN.Row<Length> ret = r.setDisplayUnit(Length.Unit.km);
        final String s1 = r.toString();
        final String s2 = r.toString(Length.Unit.m);

        assertEquals(r, ret, "fluent");
        assertTrue(s1.startsWith("Row["), "orientation");
        assertTrue(s1.contains("km"), "unit in default toString");
        assertTrue(s2.contains("m"), "unit in toString(withUnit)");

        final VectorN.Col<Length> c = col(new double[] {1, 2, 3}, Length.Unit.m);
        final VectorN.Col<Length> retc = c.setDisplayUnit(Length.Unit.km);
        final String sc = c.toString();

        assertEquals(c, retc, "fluent");
        assertTrue(sc.startsWith("Col["), "orientation");
        assertTrue(sc.contains("km"), "unit in toString");
    }

    // =====================================================================================
    // Vector algebra: add/sub (Q & V), negate, abs, scaleBy
    // =====================================================================================

    /**
     * Verify vector algebra operations for {@link VectorN.Row}: element-wise {@code add/subtract(V)}, {@code add/subtract(Q)},
     * {@code negate()}, {@code abs()}, and {@code scaleBy(double)}.
     */
    @Test
    @DisplayName("Vector algebra (Row)")
    public void testAlgebraRow()
    {
        final VectorN.Row<Length> a = row(new double[] {1, -2, 3, -4}, Length.Unit.m);
        final VectorN.Row<Length> b = row(new double[] {0.5, 3, -1, 2}, Length.Unit.m);
        final Length inc = Length.of(2.0, "m");

        assertArrayEquals(new double[] {1.5, 1.0, 2.0, -2.0}, a.add(b).si(), EPS, "a+b");
        assertArrayEquals(new double[] {0.5, -5.0, 4.0, -6.0}, a.subtract(b).si(), EPS, "a-b");
        assertArrayEquals(new double[] {3.0, 0.0, 5.0, -2.0}, a.add(inc).si(), EPS, "a+Q");
        assertArrayEquals(new double[] {-1.0, -4.0, 1.0, -6.0}, a.subtract(inc).si(), EPS, "a-Q");
        assertArrayEquals(new double[] {-1.0, 2.0, -3.0, 4.0}, a.negate().si(), EPS, "negate");
        assertArrayEquals(new double[] {1.0, 2.0, 3.0, 4.0}, a.abs().si(), EPS, "abs");
        assertArrayEquals(new double[] {2.0, -4.0, 6.0, -8.0}, a.scaleBy(2.0).si(), EPS, "scaleBy");
    }

    /**
     * Verify vector algebra operations for {@link VectorN.Col}: element-wise {@code add/subtract(V)}, {@code add/subtract(Q)},
     * {@code negate()}, {@code abs()}, and {@code scaleBy(double)}.
     */
    @Test
    @DisplayName("Vector algebra (Col)")
    public void testAlgebraCol()
    {
        final VectorN.Col<Length> a = col(new double[] {1, -2, 3, -4}, Length.Unit.m);
        final VectorN.Col<Length> b = col(new double[] {0.5, 3, -1, 2}, Length.Unit.m);
        final Length inc = Length.of(2.0, "m");

        assertArrayEquals(new double[] {1.5, 1.0, 2.0, -2.0}, a.add(b).si(), EPS, "a+b");
        assertArrayEquals(new double[] {0.5, -5.0, 4.0, -6.0}, a.subtract(b).si(), EPS, "a-b");
        assertArrayEquals(new double[] {3.0, 0.0, 5.0, -2.0}, a.add(inc).si(), EPS, "a+Q");
        assertArrayEquals(new double[] {-1.0, -4.0, 1.0, -6.0}, a.subtract(inc).si(), EPS, "a-Q");
        assertArrayEquals(new double[] {-1.0, 2.0, -3.0, 4.0}, a.negate().si(), EPS, "negate");
        assertArrayEquals(new double[] {1.0, 2.0, 3.0, 4.0}, a.abs().si(), EPS, "abs");
        assertArrayEquals(new double[] {2.0, -4.0, 6.0, -8.0}, a.scaleBy(2.0).si(), EPS, "scaleBy");
    }

    // =====================================================================================
    // Norms
    // =====================================================================================

    /**
     * Verify {@code norm()}, {@code normL1()}, {@code normL2()}, {@code normLp(int)}, and {@code normLinf()} for both Row and
     * Col vectors; {@code norm()} is expected to equal {@code normL2()} per the {@code Normed} default.
     */
    @Test
    @DisplayName("Normed: norm(), normL1(), normL2(), normLp(p), normLinf()")
    public void testNorms()
    {
        // Row: |1|+|−2|+|3|+|−4|=10 ; L2 = sqrt(30) ; Lp(3)=(1+8+27+64)^(1/3) ; Linf=4
        final VectorN.Row<Length> r = row(new double[] {1, -2, 3, -4}, Length.Unit.m);
        assertEquals(r.normL2().si(), r.norm().si(), EPS, "norm == normL2");
        assertEquals(10.0, r.normL1().si(), EPS, "L1");
        assertEquals(Math.sqrt(30.0), r.normL2().si(), EPS, "L2");
        assertEquals(Math.cbrt(1.0 + 8.0 + 27.0 + 64.0), r.normLp(3).si(), EPS, "Lp(3)");
        assertEquals(4.0, r.normLinf().si(), EPS, "Linf");

        // Col: [3,4,0,0] → L1=7 ; L2=5 ; Linf=4
        final VectorN.Col<Length> c = col(new double[] {3, 4, 0, 0}, Length.Unit.m);
        assertEquals(c.normL2().si(), c.norm().si(), EPS, "norm == normL2");
        assertEquals(7.0, c.normL1().si(), EPS, "L1");
        assertEquals(5.0, c.normL2().si(), EPS, "L2");
        assertEquals(4.0, c.normLinf().si(), EPS, "Linf");
    }

    // =====================================================================================
    // Hadamard (element-wise) with unit composition
    // =====================================================================================

    /**
     * Verify element-wise Hadamard operations on a {@link VectorN.Row}: {@code invertEntries()}, {@code multiplyEntries(V)},
     * {@code divideEntries(V)} and validate result-unit composition via {@link SIUnit#add(SIUnit, SIUnit)} and
     * {@link SIUnit#subtract(SIUnit, SIUnit)}.
     */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition (Row)")
    public void testHadamardRow()
    {
        final VectorN.Row<Length> a = row(new double[] {2, 4, 5, 10}, Length.Unit.m);
        final VectorN.Row<Length> b = row(new double[] {1, 2, 0.5, 4}, Length.Unit.km); // SI: 1000,2000,500,4000

        final VectorN.Row<SIQuantity> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1}, inv.si(), EPS, "invert");
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit invert");

        final VectorN.Row<SIQuantity> mul = a.multiplyEntries(b);
        assertArrayEquals(new double[] {2000.0, 8000.0, 2500.0, 40_000.0}, mul.si(), EPS, "multiply");
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit(), "unit add");

        final VectorN.Row<SIQuantity> div = a.divideEntries(b);
        assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025}, div.si(), EPS, "divide");
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit(), "unit subtract");
    }

    /**
     * Verify element-wise Hadamard operations on a {@link VectorN.Col}: {@code invertEntries()}, {@code multiplyEntries(V)},
     * {@code divideEntries(V)} and validate result-unit composition.
     */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition (Col)")
    public void testHadamardCol()
    {
        final VectorN.Col<Length> a = col(new double[] {2, 4, 5, 10}, Length.Unit.m);
        final VectorN.Col<Length> b = col(new double[] {1, 2, 0.5, 4}, Length.Unit.km);

        final VectorN.Col<SIQuantity> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1}, inv.si(), EPS, "invert");
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit invert");

        final VectorN.Col<SIQuantity> mul = a.multiplyEntries(b);
        assertArrayEquals(new double[] {2000.0, 8000.0, 2500.0, 40_000.0}, mul.si(), EPS, "multiply");
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit(), "unit add");

        final VectorN.Col<SIQuantity> div = a.divideEntries(b);
        assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025}, div.si(), EPS, "divide");
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit(), "unit subtract");
    }

    // =====================================================================================
    // Statistics: min/max/mean/median/sum
    // =====================================================================================

    /**
     * Verify {@code min}, {@code max}, {@code mean}, {@code median}, and {@code sum} on an unsorted odd-length
     * {@link VectorN.Row} input (to avoid even-median ambiguity).
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/sum (Row)")
    public void testStatisticsRow()
    {
        final VectorN.Row<Length> r = row(new double[] {1, 4, 2, 5, 3}, Length.Unit.m);
        assertEquals(1.0, r.min().si(), EPS, "min");
        assertEquals(5.0, r.max().si(), EPS, "max");
        assertEquals(3.0, r.mean().si(), EPS, "mean");
        assertEquals(3.0, r.median().si(), EPS, "median");
        assertEquals(15.0, r.sum().si(), EPS, "sum");
    }

    /**
     * Verify {@code min}, {@code max}, {@code mean}, {@code median}, and {@code sum} on a {@link VectorN.Col} with inputs in
     * kilometers; assertions are made against SI values in meters.
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/sum (Col)")
    public void testStatisticsCol()
    {
        // km → SI(m): [200, 1000, 500, 1500, 800]
        final VectorN.Col<Length> c = col(new double[] {0.2, 1.0, 0.5, 1.5, 0.8}, Length.Unit.km);
        final double[] si = new double[] {200.0, 1000.0, 500.0, 1500.0, 800.0};

        double sum = 0.0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double v : si)
        {
            sum += v;
            min = Math.min(min, v);
            max = Math.max(max, v);
        }
        final double mean = sum / si.length; // 800
        final double median = 800.0; // sorted: [200, 500, 800, 1000, 1500]

        assertEquals(min, c.min().si(), EPS, "min");
        assertEquals(max, c.max().si(), EPS, "max");
        assertEquals(mean, c.mean().si(), EPS, "mean");
        assertEquals(median, c.median().si(), EPS, "median");
        assertEquals(sum, c.sum().si(), EPS, "sum");
    }

    // ------------------------------------------------------------------------------------
    // VectorN.Row & VectorN.Col — asVector1 / asVector2Col|Row / asVector3Col|Row
    // ------------------------------------------------------------------------------------

    /**
     * Verify that VectorN.{@code Col} and VectorN.{@code Row} convert to Vector1/Vector2/Vector3 variants with preserved SI
     * data and display unit; and that illegal shapes throw {@link IllegalStateException}.
     * <p>
     * Uses kilometers for column vectors and centimeters for row vectors to ensure SI conversion is correct.
     */
    @Test
    @DisplayName("VectorN.{Row,Col}: asVector1 / asVector2* / asVector3* preserve SI & unit; illegal shapes throw")
    public void testVectorNasVectorConversionsRowAndCol()
    {
        // ----------------------------
        // COL side (km): 1x1, 2x1, 3x1
        // ----------------------------

        // 1x1 -> asVector1 (legal)
        VectorN.Col<Length> c1 = col(new double[] {5.5}, Length.Unit.km); // 5.5 km -> 5500 m
        Vector1<Length> c1As1 = c1.asVector1();
        assertEquals(1, c1As1.size());
        assertEquals(5500.0, c1As1.get(0).si(), EPS);
        assertEquals(Length.Unit.km, c1As1.getDisplayUnit());

        // 2x1 -> asVector2Col (legal)
        VectorN.Col<Length> c2 = col(new double[] {5.0, 6.0}, Length.Unit.km); // -> [5000, 6000] m
        Vector2.Col<Length> c2As2 = c2.asVector2Col();
        assertEquals(2, c2As2.size());
        assertEquals(5000.0, c2As2.get(0).si(), EPS);
        assertEquals(6000.0, c2As2.get(1).si(), EPS);
        assertEquals(Length.Unit.km, c2As2.getDisplayUnit());

        // 3x1 -> asVector3Col (legal)
        VectorN.Col<Length> c3 = col(new double[] {5.0, 6.0, 7.0}, Length.Unit.km); // -> [5000, 6000, 7000] m
        Vector3.Col<Length> c3As3 = c3.asVector3Col();
        assertEquals(3, c3As3.size());
        assertEquals(5000.0, c3As3.get(0).si(), EPS);
        assertEquals(6000.0, c3As3.get(1).si(), EPS);
        assertEquals(7000.0, c3As3.get(2).si(), EPS);
        assertEquals(Length.Unit.km, c3As3.getDisplayUnit());

        // Illegal shapes for COL: 4x1 should fail for all 3 methods
        VectorN.Col<Length> c4 = col(new double[] {5.0, 6.0, 7.0, 8.0}, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> c4.asVector1());
        assertThrows(IllegalStateException.class, () -> c4.asVector2Col());
        assertThrows(IllegalStateException.class, () -> c4.asVector3Col());

        // ----------------------------
        // ROW side (cm): 1x1, 1x2, 1x3
        // ----------------------------

        // 1x1 -> asVector1 (legal)
        VectorN.Row<Length> r1 = row(new double[] {3.0}, Length.Unit.cm); // 3 cm -> 0.03 m
        Vector1<Length> r1as1 = r1.asVector1();
        assertEquals(1, r1as1.size());
        assertEquals(0.03, r1as1.get(0).si(), EPS);
        assertEquals(Length.Unit.cm, r1as1.getDisplayUnit());

        // 1x2 -> asVector2Row (legal)
        VectorN.Row<Length> r2 = row(new double[] {3.0, 4.0}, Length.Unit.cm); // -> [0.03, 0.04] m
        Vector2.Row<Length> r2As2 = r2.asVector2Row();
        assertEquals(2, r2As2.size());
        assertEquals(0.03, r2As2.get(0).si(), EPS);
        assertEquals(0.04, r2As2.get(1).si(), EPS);
        assertEquals(Length.Unit.cm, r2As2.getDisplayUnit());

        // 1x3 -> asVector3Row (legal)
        VectorN.Row<Length> r3 = row(new double[] {3.0, 4.0, 5.0}, Length.Unit.cm); // -> [0.03, 0.04, 0.05] m
        Vector3.Row<Length> r3As3 = r3.asVector3Row();
        assertEquals(3, r3As3.size());
        assertEquals(0.03, r3As3.get(0).si(), EPS);
        assertEquals(0.04, r3As3.get(1).si(), EPS);
        assertEquals(0.05, r3As3.get(2).si(), EPS);
        assertEquals(Length.Unit.cm, r3As3.getDisplayUnit());

        // Illegal shapes for ROW: 1x4 should fail for all 3 methods
        VectorN.Row<Length> r4 = row(new double[] {3.0, 4.0, 5.0, 6.0}, Length.Unit.cm);
        assertThrows(IllegalStateException.class, () -> r4.asVector1());
        assertThrows(IllegalStateException.class, () -> r4.asVector2Row());
        assertThrows(IllegalStateException.class, () -> r4.asVector3Row());
    }

    /**
     * Verify equals() and hashCode() methods.
     */
    @Test
    @DisplayName("Equals and hashCode")
    public void testEqualsHashCode()
    {
        final VectorN.Col<Length> ac = col(new double[] {1, -2, 3, -4}, Length.Unit.m);
        final VectorN.Col<Length> ad = col(new double[] {10, -20, 30, -40}, Length.Unit.dm);
        final VectorN.Col<Length> ae = col(new double[] {1, -2, 3, 4}, Length.Unit.m);
        final VectorN.Row<Length> ar = row(new double[] {1, -2, 3, -4}, Length.Unit.m);

        assertEquals(ac, ac);
        assertEquals(ac, ad);
        assertNotEquals(ac, ar);
        assertNotEquals(ac, ae);
        assertNotEquals(ac, null);
        assertNotEquals(ac, "");

        assertEquals(ac.hashCode(), ad.hashCode());
        assertNotEquals(ac.hashCode(), ae.hashCode());
    }

    /**
     * Verify transpose() method.
     */
    @Test
    @DisplayName("Transpose")
    public void testTranspose()
    {
        final VectorN.Col<Length> c = col(new double[] {1, -2, 3, -4}, Length.Unit.km);
        final VectorN.Row<Length> r = row(new double[] {1, -2, 3, -4}, Length.Unit.km);

        var rt = r.transpose();
        var ct = c.transpose();
        
        assertEquals(c, rt);
        assertEquals(r, ct);
        
        var rt2 = rt.transpose();
        var ct2 = ct.transpose();
        assertEquals(r, rt2);
        assertEquals(c, ct2);
        
        assertArrayEquals(r.si(), rt.si(), EPS, "Row to Col preserves SI");
        assertEquals(r.getDisplayUnit(), rt.getDisplayUnit());
        assertArrayEquals(c.si(), ct.si(), EPS, "Col to Row preserves SI");
        assertEquals(c.getDisplayUnit(), ct.getDisplayUnit());
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        VectorN.Col<Length> r = col(new double[] {1.0, 2.0, 3.0}, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        VectorN.Col<Speed> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.mget(2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.mget(3).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));

        VectorN.Row<Length> c = row(new double[] {1.0, 2.0, 3.0}, Length.Unit.km);
        VectorN.Row<Speed> sc = c.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sc.getDisplayUnit());
        assertEquals(0.5, sc.get(0).getInUnit(), 1E-6);
        assertEquals(1.0, sc.get(1).getInUnit(), 1E-6);
        assertEquals(1.5, sr.get(2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> c.divideEntries(d).as(Area.Unit.m2));
    }

    // =================================================================================================
    // COMPLETE FACTORY TESTS FOR VectorN.of(...) AND VectorN.ofSi(...).
    // All overloads, all branches, Row and Col symmetric.
    // =================================================================================================

    // -------------------------------------------------------------------------------------------------
    // of(double[] dataInUnit, Unit unit).
    // -------------------------------------------------------------------------------------------------

    /** Col.of(double[], unit) happy path. */
    @Test
    public void testColOfArrayUnitHappy()
    {
        VectorN.Col<Length> v = VectorN.Col.of(new double[] {1.0, 2.0, 3.0}, Length.Unit.km);
        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {1000.0, 2000.0, 3000.0}, v.si(), EPS);
    }

    /** Row.of(double[], unit) happy path. */
    @Test
    public void testRowOfArrayUnitHappy()
    {
        VectorN.Row<Length> v = VectorN.Row.of(new double[] {10.0, 20.0}, Length.Unit.cm);
        assertEquals(Length.Unit.cm, v.getDisplayUnit());
        assertArrayEquals(new double[] {0.10, 0.20}, v.si(), EPS);
    }

    /** Col.of(double[], unit) with empty array. */
    @Test
    public void testColOfArrayUnitEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> VectorN.Col.of(new double[] {}, Length.Unit.m));
    }

    /** Row.of(double[], unit) with empty array. */
    @Test
    public void testRowOfArrayUnitEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> VectorN.Row.of(new double[] {}, Length.Unit.m));
    }

    /** Col.of(double[], unit) with null array. */
    @Test
    public void testColOfArrayUnitNullArray()
    {
        assertThrows(NullPointerException.class, () -> VectorN.Col.of((double[]) null, Length.Unit.m));
    }

    /** Row.of(double[], unit) with null array. */
    @Test
    public void testRowOfArrayUnitNullArray()
    {
        assertThrows(NullPointerException.class, () -> VectorN.Row.of((double[]) null, Length.Unit.m));
    }

    // -------------------------------------------------------------------------------------------------
    // ofSi(double[] dataSi, Unit displayUnit).
    // -------------------------------------------------------------------------------------------------

    /** Col.ofSi(double[], unit) happy path. */
    @Test
    public void testColOfSiArrayHappy()
    {
        VectorN.Col<Length> v = VectorN.Col.ofSi(new double[] {5.0, 6.0}, Length.Unit.m);
        assertEquals(Length.Unit.m, v.getDisplayUnit());
        assertArrayEquals(new double[] {5.0, 6.0}, v.si(), EPS);
    }

    /** Row.ofSi(double[], unit) happy path. */
    @Test
    public void testRowOfSiArrayHappy()
    {
        VectorN.Row<Length> v = VectorN.Row.ofSi(new double[] {7.0, 8.0, 9.0}, Length.Unit.km);
        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {7.0, 8.0, 9.0}, v.si(), EPS);
    }

    /** Col.ofSi(double[], unit) with null unit. */
    @Test
    public void testColOfSiArrayNullUnit()
    {
        assertThrows(NullPointerException.class, () -> VectorN.Col.ofSi(new double[] {1.0}, null));
    }

    /** Row.ofSi(double[], unit) with null array. */
    @Test
    public void testRowOfSiArrayNullArray()
    {
        assertThrows(NullPointerException.class, () -> VectorN.Row.ofSi((double[]) null, Length.Unit.m));
    }

    // -------------------------------------------------------------------------------------------------
    // of(Q[] data).
    // -------------------------------------------------------------------------------------------------

    /** Col.of(Q[]) happy path. */
    @Test
    public void testColOfQuantityArrayHappy()
    {
        Length[] data = {new Length(1.0, Length.Unit.km), new Length(500.0, Length.Unit.m)};

        VectorN.Col<Length> v = VectorN.Col.of(data);

        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {1000.0, 500.0}, v.si(), EPS);
    }

    /** Row.of(Q[]) happy path. */
    @Test
    public void testRowOfQuantityArrayHappy()
    {
        Length[] data = {new Length(2.0, Length.Unit.m), new Length(3.0, Length.Unit.m), new Length(4.0, Length.Unit.m)};

        VectorN.Row<Length> v = VectorN.Row.of(data);

        assertEquals(Length.Unit.m, v.getDisplayUnit());
        assertArrayEquals(new double[] {2.0, 3.0, 4.0}, v.si(), EPS);
    }

    /** Col.of(Q[]) with empty array. */
    @Test
    public void testColOfQuantityArrayEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> VectorN.Col.of(new Length[] {}));
    }

    /** Row.of(Q[]) with empty array. */
    @Test
    public void testRowOfQuantityArrayEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> VectorN.Row.of(new Length[] {}));
    }

    /** Col.of(Q[]) with null array. */
    @Test
    public void testColOfQuantityArrayNull()
    {
        assertThrows(NullPointerException.class, () -> VectorN.Col.of((Length[]) null));
    }

    /** Row.of(Q[]) with null array. */
    @Test
    public void testRowOfQuantityArrayNull()
    {
        assertThrows(NullPointerException.class, () -> VectorN.Row.of((Length[]) null));
    }

    // -------------------------------------------------------------------------------------------------
    // of(List<Q> data).
    // -------------------------------------------------------------------------------------------------

    /** Col.of(List) happy path. */
    @Test
    public void testColOfQuantityListHappy()
    {
        List<Length> list = List.of(new Length(1.0, Length.Unit.km), new Length(2.0, Length.Unit.km));

        VectorN.Col<Length> v = VectorN.Col.of(list);

        assertEquals(Length.Unit.km, v.getDisplayUnit());
        assertArrayEquals(new double[] {1000.0, 2000.0}, v.si(), EPS);
    }

    /** Row.of(List) happy path. */
    @Test
    public void testRowOfQuantityListHappy()
    {
        List<Length> list = List.of(new Length(10.0, Length.Unit.cm), new Length(20.0, Length.Unit.cm));

        VectorN.Row<Length> v = VectorN.Row.of(list);

        assertEquals(Length.Unit.cm, v.getDisplayUnit());
        assertArrayEquals(new double[] {0.10, 0.20}, v.si(), EPS);
    }

    /** Col.of(List) with empty list. */
    @Test
    public void testColOfQuantityListEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> VectorN.Col.of(List.of()));
    }

    /** Row.of(List) with empty list. */
    @Test
    public void testRowOfQuantityListEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> VectorN.Row.of(List.of()));
    }

    /** Col.of(List) with null element. */
    @Test
    public void testColOfQuantityListWithNullElement()
    {
        List<Length> list = Arrays.asList(Length.ofSi(1.0), null);

        assertThrows(NullPointerException.class, () -> VectorN.Col.of(list));
    }

    /** Row.of(List) with null element. */
    @Test
    public void testRowOfQuantityListWithNullElement()
    {
        List<Length> list = Arrays.asList(Length.ofSi(1.0), null);

        assertThrows(NullPointerException.class, () -> VectorN.Row.of(list));
    }

    // -------------------------------------------------------------------------------------------------
    // ofSi(DataGridSi dataSi, Unit displayUnit).
    // -------------------------------------------------------------------------------------------------

    /** Col.ofSi(DataGridSi, unit) happy path. */
    @Test
    public void testColOfSiDataGridHappy()
    {
        var d = new DenseDoubleDataSi(new double[] {1.0, 2.0, 3.0}, 3, 1);
        VectorN.Col<Length> v = VectorN.Col.ofSi(d, Length.Unit.m);

        assertEquals(Length.Unit.m, v.getDisplayUnit());
        assertArrayEquals(new double[] {1.0, 2.0, 3.0}, v.si(), EPS);
    }

    /** Row.ofSi(DataGridSi, unit) happy path. */
    @Test
    public void testRowOfSiDataGridHappy()
    {
        var d = new DenseDoubleDataSi(new double[] {4.0, 5.0}, 1, 2);
        VectorN.Row<Length> v = VectorN.Row.ofSi(d, Length.Unit.m);

        assertEquals(Length.Unit.m, v.getDisplayUnit());
        assertArrayEquals(new double[] {4.0, 5.0}, v.si(), EPS);
    }

    /** Col.ofSi(DataGridSi, unit) with wrong shape. */
    @Test
    public void testColOfSiDataGridWrongShape()
    {
        var d = new DenseDoubleDataSi(new double[] {1.0, 2.0, 3.0, 4.0}, 2, 2);
        assertThrows(IllegalArgumentException.class, () -> VectorN.Col.ofSi(d, Length.Unit.m));
    }

    /** Row.ofSi(DataGridSi, unit) with wrong shape. */
    @Test
    public void testRowOfSiDataGridWrongShape()
    {
        var d = new DenseDoubleDataSi(new double[] {1.0, 2.0, 3.0, 4.0}, 2, 2);
        assertThrows(IllegalArgumentException.class, () -> VectorN.Row.ofSi(d, Length.Unit.m));
    }
}
