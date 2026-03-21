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
 * <li>Statistics: {@code min()}, {@code max()}, {@code mean()}, {@code median()}, {@code mode()}, {@code sum()}</li>
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
    private static VectorN.Col<Length, Length.Unit> col(final double[] inUnit, final Length.Unit unit)
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
    private static VectorN.Row<Length, Length.Unit> row(final double[] inUnit, final Length.Unit unit)
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
        final VectorN.Col<Length, Length.Unit> c = col(new double[] {1, 2, 3}, Length.Unit.km);
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
        final VectorN.Row<Length, Length.Unit> r = row(new double[] {10, 200, 3}, Length.Unit.cm);
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
        VectorN.Col<Length, Length.Unit> c = col(new double[] {1.0, 2.0, 3.0}, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> c.instantiateSi(new double[] {1.0, 2.0}), "Wrong length must throw");
        VectorN.Col<Length, Length.Unit> d = c.instantiateSi(new double[] {10.0, 20.0, 30.0});
        assertArrayEquals(new double[] {10.0, 20.0, 30.0}, d.si(), EPS);
        assertEquals(c.getDisplayUnit(), d.getDisplayUnit());

        double[] newSi = {20, 30, 40};
        VectorN.Col<SIQuantity, SIUnit> csiVector = c.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", csiVector.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, csiVector.si(), EPS, "si array used as-is");
        assertEquals(20.0, csiVector.get(0, 0).si(), EPS);

        VectorN.Col<SIQuantity, SIUnit> csiVectorOf = VectorN.Col.of(new double[] {20.0, 30.0, 40.0}, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", csiVectorOf.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, csiVectorOf.si(), EPS, "si array used as-is");
        assertEquals(20.0, csiVectorOf.get(0, 0).si(), EPS);

        assertThrows(IllegalArgumentException.class, () -> c.instantiateSi(new double[] {1, 2, 3, 4, 5}, SIUnit.of("kgm/s2K")));
    }

    /** Verify instantiateSi(double[]) enforces length=3 and delegates (Row). */
    @Test
    @DisplayName("instantiateSi(double[]) enforces length=3 and delegates (Row)")
    public void testInstantiateSiArrayRow()
    {
        VectorN.Row<Length, Length.Unit> r = row(new double[] {1.0, 2.0, 3.0}, Length.Unit.m);
        assertThrows(IllegalArgumentException.class, () -> r.instantiateSi(new double[] {1.0, 2.0}), "Wrong length must throw");
        VectorN.Row<Length, Length.Unit> s = r.instantiateSi(new double[] {10.0, 20.0, 30.0});
        assertArrayEquals(new double[] {10.0, 20.0, 30.0}, s.si(), EPS);
        assertEquals(r.getDisplayUnit(), s.getDisplayUnit());

        double[] newSi = {20, 30, 40};
        VectorN.Row<SIQuantity, SIUnit> rsiVector = r.instantiateSi(newSi, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", rsiVector.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, rsiVector.si(), EPS, "si array used as-is");
        assertEquals(20.0, rsiVector.get(0, 0).si(), EPS);

        VectorN.Row<SIQuantity, SIUnit> rsiVectorOf = VectorN.Row.of(new double[] {20.0, 30.0, 40.0}, SIUnit.of("kgm/s2K"));
        assertEquals("kgm/s2K", rsiVectorOf.getDisplayUnit().toString(true, false), "display unit retained");
        assertArrayEquals(newSi, rsiVectorOf.si(), EPS, "si array used as-is");
        assertEquals(20.0, rsiVectorOf.get(0, 0).si(), EPS);

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
        var v2 = VectorN.Col.of(q2, Length.Unit.km);
        assertEquals(3000.0, v2.si()[1], 1E-6);

        List<Length> l3 = Arrays.asList(q2);
        var v3 = VectorN.Col.of(l3, Length.Unit.km);
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
        var v2 = VectorN.Row.of(q2, Length.Unit.km);
        assertEquals(3000.0, v2.si()[1], 1E-6);

        List<Length> l3 = Arrays.asList(q2);
        var v3 = VectorN.Row.of(l3, Length.Unit.km);
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
     * Verify {@link VectorN.Row#get(int)} and {@link VectorN.Col#get(int)} with valid and invalid indices; this test ensures
     * the index addressing follows the intended semantics: a row vector reads from {@code (0, index-1)}, a column vector from
     * {@code (index-1, 0)}.
     */
    @Test
    @DisplayName("get(index), addressing semantics (Row & Col)")
    public void testIndexing()
    {
        final VectorN.Row<Length, Length.Unit> r = row(new double[] {1, 2, 3, 4}, Length.Unit.m);
        final VectorN.Col<Length, Length.Unit> c = col(new double[] {5, 6, 7, 8}, Length.Unit.m);

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

        // si(r, c) with valid and invalid indices
        assertEquals(r.si()[0], r.si(0, 0), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> r.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> r.si(4, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> r.si(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> r.si(0, 4));

        // msi(r, c) with valid and invalid indices
        assertEquals(r.si()[0], r.msi(1, 1), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> r.msi(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> r.msi(5, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> r.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> r.msi(1, 5));

        // Col.si(r, c) with valid and invalid indices
        assertEquals(c.si()[0], c.si(0, 0), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> c.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> c.si(4, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> c.si(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> c.si(0, 4));

        // Col.msi(r, c) with valid and invalid indices
        assertEquals(c.si()[0], c.msi(1, 1), EPS);
        assertThrows(IndexOutOfBoundsException.class, () -> c.msi(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> c.msi(5, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> c.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> c.msi(1, 5));
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
        final VectorN.Row<Length, Length.Unit> r = row(new double[] {1, 2, 3}, Length.Unit.km); // SI: 1000, 2000, 3000
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
        final VectorN.Col<Length, Length.Unit> c = col(new double[] {3, 4, 5}, Length.Unit.m);
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
     * Verify that {@link VectorN#setDisplayUnit(org.djunits.unit.UnitInterface)} returns {@code this} for fluent usage and that
     * {@link VectorN#toString()} and {@link VectorN#toString(org.djunits.unit.UnitInterface)} contain correct orientation and
     * unit abbreviation.
     */
    @Test
    @DisplayName("setDisplayUnit() is fluent; toString contains orientation & unit")
    public void testFluentAndToString()
    {
        final VectorN.Row<Length, Length.Unit> r = row(new double[] {1, 2, 3}, Length.Unit.m);
        final VectorN.Row<Length, Length.Unit> ret = r.setDisplayUnit(Length.Unit.km);
        final String s1 = r.toString();
        final String s2 = r.toString(Length.Unit.m);

        assertEquals(r, ret, "fluent");
        assertTrue(s1.startsWith("Row["), "orientation");
        assertTrue(s1.contains("km"), "unit in default toString");
        assertTrue(s2.contains("m"), "unit in toString(withUnit)");

        final VectorN.Col<Length, Length.Unit> c = col(new double[] {1, 2, 3}, Length.Unit.m);
        final VectorN.Col<Length, Length.Unit> retc = c.setDisplayUnit(Length.Unit.km);
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
        final VectorN.Row<Length, Length.Unit> a = row(new double[] {1, -2, 3, -4}, Length.Unit.m);
        final VectorN.Row<Length, Length.Unit> b = row(new double[] {0.5, 3, -1, 2}, Length.Unit.m);
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
        final VectorN.Col<Length, Length.Unit> a = col(new double[] {1, -2, 3, -4}, Length.Unit.m);
        final VectorN.Col<Length, Length.Unit> b = col(new double[] {0.5, 3, -1, 2}, Length.Unit.m);
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
        final VectorN.Row<Length, Length.Unit> r = row(new double[] {1, -2, 3, -4}, Length.Unit.m);
        assertEquals(r.normL2().si(), r.norm().si(), EPS, "norm == normL2");
        assertEquals(10.0, r.normL1().si(), EPS, "L1");
        assertEquals(Math.sqrt(30.0), r.normL2().si(), EPS, "L2");
        assertEquals(Math.cbrt(1.0 + 8.0 + 27.0 + 64.0), r.normLp(3).si(), EPS, "Lp(3)");
        assertEquals(4.0, r.normLinf().si(), EPS, "Linf");

        // Col: [3,4,0,0] → L1=7 ; L2=5 ; Linf=4
        final VectorN.Col<Length, Length.Unit> c = col(new double[] {3, 4, 0, 0}, Length.Unit.m);
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
        final VectorN.Row<Length, Length.Unit> a = row(new double[] {2, 4, 5, 10}, Length.Unit.m);
        final VectorN.Row<Length, Length.Unit> b = row(new double[] {1, 2, 0.5, 4}, Length.Unit.km); // SI: 1000,2000,500,4000

        final VectorN.Row<SIQuantity, SIUnit> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1}, inv.si(), EPS, "invert");
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit invert");

        final VectorN.Row<SIQuantity, SIUnit> mul = a.multiplyEntries(b);
        assertArrayEquals(new double[] {2000.0, 8000.0, 2500.0, 40_000.0}, mul.si(), EPS, "multiply");
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit(), "unit add");

        final VectorN.Row<SIQuantity, SIUnit> div = a.divideEntries(b);
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
        final VectorN.Col<Length, Length.Unit> a = col(new double[] {2, 4, 5, 10}, Length.Unit.m);
        final VectorN.Col<Length, Length.Unit> b = col(new double[] {1, 2, 0.5, 4}, Length.Unit.km);

        final VectorN.Col<SIQuantity, SIUnit> inv = a.invertEntries();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1}, inv.si(), EPS, "invert");
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit invert");

        final VectorN.Col<SIQuantity, SIUnit> mul = a.multiplyEntries(b);
        assertArrayEquals(new double[] {2000.0, 8000.0, 2500.0, 40_000.0}, mul.si(), EPS, "multiply");
        assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit(), "unit add");

        final VectorN.Col<SIQuantity, SIUnit> div = a.divideEntries(b);
        assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025}, div.si(), EPS, "divide");
        assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit(), "unit subtract");
    }

    // =====================================================================================
    // Statistics: min/max/mean/median/mode/sum
    // =====================================================================================

    /**
     * Verify {@code min}, {@code max}, {@code mean}, {@code median}, {@code mode}, and {@code sum} on an unsorted odd-length
     * {@link VectorN.Row} input (to avoid even-median ambiguity).
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/mode/sum (Row)")
    public void testStatisticsRow()
    {
        final VectorN.Row<Length, Length.Unit> r = row(new double[] {1, 4, 2, 5, 3}, Length.Unit.m);
        assertEquals(1.0, r.min().si(), EPS, "min");
        assertEquals(5.0, r.max().si(), EPS, "max");
        assertEquals(3.0, r.mean().si(), EPS, "mean");
        assertEquals(3.0, r.median().si(), EPS, "median");
        assertEquals(5.0, r.mode().si(), EPS, "mode defaults to max");
        assertEquals(15.0, r.sum().si(), EPS, "sum");
    }

    /**
     * Verify {@code min}, {@code max}, {@code mean}, {@code median}, {@code mode}, and {@code sum} on a {@link VectorN.Col}
     * with inputs in kilometers; assertions are made against SI values in meters.
     */
    @Test
    @DisplayName("Statistics: min/max/mean/median/mode/sum (Col)")
    public void testStatisticsCol()
    {
        // km → SI(m): [200, 1000, 500, 1500, 800]
        final VectorN.Col<Length, Length.Unit> c = col(new double[] {0.2, 1.0, 0.5, 1.5, 0.8}, Length.Unit.km);
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
        assertEquals(max, c.mode().si(), EPS, "mode defaults to max");
        assertEquals(sum, c.sum().si(), EPS, "sum");
    }

    // ------------------------------------------------------------------------------------
    // VectorN.Col — getScalars / getRowScalars / getColumnScalars (incl. 1-based)
    // ------------------------------------------------------------------------------------

    /**
     * Verify scalar array extraction helpers on a {@link VectorN.Col} (4x1) and their 1-based variants.
     * <p>
     * Uses kilometers as display unit to verify correct SI conversion.
     */
    @Test
    @DisplayName("VectorN.Col: getScalars helpers (Row/Column) incl. 1-based, lengths, values, and bounds")
    public void testColNScalarExtraction()
    {
        // Column vector: [x; y; z; w] = [5 km; 6 km; 7 km; 8 km] -> SI = [5000; 6000; 7000; 8000] m
        VectorN.Col<Length, Length.Unit> v = col(new double[] {5.0, 6.0, 7.0, 8.0}, Length.Unit.km);

        // 0-based rows -> each row has length 1
        Length[] row0 = v.getRowScalars(0);
        Length[] row3 = v.getRowScalars(3);
        assertEquals(1, row0.length);
        assertEquals(1, row3.length);
        assertEquals(5000.0, row0[0].si(), EPS);
        assertEquals(8000.0, row3[0].si(), EPS);

        // 0-based single column aggregates all rows
        Length[] col0 = v.getColumnScalars(0);
        assertEquals(4, col0.length);
        assertEquals(5000.0, col0[0].si(), EPS);
        assertEquals(8000.0, col0[3].si(), EPS);

        // 1-based variants
        Length[] mRow1 = v.mgetRowScalars(1);
        Length[] mRow4 = v.mgetRowScalars(4);
        assertEquals(1, mRow1.length);
        assertEquals(1, mRow4.length);
        assertEquals(5000.0, mRow1[0].si(), EPS);
        assertEquals(8000.0, mRow4[0].si(), EPS);

        Length[] mCol1 = v.mgetColumnScalars(1);
        assertEquals(4, mCol1.length);
        assertEquals(5000.0, mCol1[0].si(), EPS);
        assertEquals(8000.0, mCol1[3].si(), EPS);

        // OOB (0-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowScalars(4));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnScalars(1));

        // OOB (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowScalars(5));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnScalars(2));
    }

    // ------------------------------------------------------------------------------------
    // VectorN.Col — getRowVector / getColumnVector (incl. 1-based)
    // ------------------------------------------------------------------------------------

    /**
     * Verify vector extraction helpers on a {@link VectorN.Col} (4x1) for both 0-based and 1-based variants.
     * <p>
     * Row vectors returned are {@link Vector1} (1 element). The full column is returned as {@link VectorN.Col}.
     */
    @Test
    @DisplayName("VectorN.Col: getRowVector / getColumnVector incl. 1-based and bounds")
    public void testColNVectorExtractionSpec()
    {
        // Column vector: [5 km; 6 km; 7 km; 8 km] -> SI = [5000; 6000; 7000; 8000] m
        VectorN.Col<Length, Length.Unit> v = col(new double[] {5.0, 6.0, 7.0, 8.0}, Length.Unit.km);

        // 0-based rows -> Vector1
        Vector1<Length, Length.Unit> row0 = v.getRowVector(0);
        Vector1<Length, Length.Unit> row3 = v.getRowVector(3);
        assertEquals(1, row0.size());
        assertEquals(1, row3.size());
        assertEquals(5000.0, row0.get(0).si(), EPS);
        assertEquals(8000.0, row3.get(0).si(), EPS);

        // 0-based column 0 -> full VectorN.Col
        VectorN.Col<Length, Length.Unit> fullCol0 = v.getColumnVector(0);
        assertEquals(4, fullCol0.size());
        assertEquals(5000.0, fullCol0.get(0).si(), EPS);
        assertEquals(8000.0, fullCol0.get(3).si(), EPS);

        // 1-based
        Vector1<Length, Length.Unit> mRow1 = v.mgetRowVector(1);
        Vector1<Length, Length.Unit> mRow4 = v.mgetRowVector(4);
        assertEquals(1, mRow1.size());
        assertEquals(1, mRow4.size());
        assertEquals(5000.0, mRow1.get(0).si(), EPS);
        assertEquals(8000.0, mRow4.get(0).si(), EPS);

        VectorN.Col<Length, Length.Unit> mFullCol1 = v.mgetColumnVector(1);
        assertEquals(4, mFullCol1.size());
        assertEquals(5000.0, mFullCol1.get(0).si(), EPS);
        assertEquals(8000.0, mFullCol1.get(3).si(), EPS);

        // OOB (0-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowVector(4));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnVector(1));

        // OOB (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowVector(5));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnVector(2));
    }

    // ------------------------------------------------------------------------------------
    // VectorN.Col — getRowSi / getColumnSi (incl. 1-based) + si(r,c) / msi(r,c)
    // ------------------------------------------------------------------------------------

    /**
     * Verify SI-array extraction helpers on a {@link VectorN.Col} and direct SI element access using {@code si(row, col)} and
     * {@code msi(row, col)} including bounds.
     */
    @Test
    @DisplayName("VectorN.Col: getRowSi / getColumnSi incl. 1-based + si(r,c)/msi(r,c)")
    public void testColNSiArrayExtractionAndSiAt()
    {
        // Column vector: [5 km; 6 km; 7 km; 8 km] -> SI = [5000; 6000; 7000; 8000] m
        VectorN.Col<Length, Length.Unit> v = col(new double[] {5.0, 6.0, 7.0, 8.0}, Length.Unit.km);

        // 0-based SI arrays
        double[] row0 = v.getRowSi(0);
        double[] row3 = v.getRowSi(3);
        double[] col0 = v.getColumnSi(0);
        assertEquals(1, row0.length);
        assertEquals(1, row3.length);
        assertEquals(4, col0.length);
        assertEquals(5000.0, row0[0], EPS);
        assertEquals(8000.0, row3[0], EPS);
        assertEquals(5000.0, col0[0], EPS);
        assertEquals(8000.0, col0[3], EPS);

        // 1-based SI arrays
        double[] mRow1 = v.mgetRowSi(1);
        double[] mRow4 = v.mgetRowSi(4);
        double[] mCol1 = v.mgetColumnSi(1);
        assertEquals(1, mRow1.length);
        assertEquals(1, mRow4.length);
        assertEquals(4, mCol1.length);
        assertEquals(5000.0, mRow1[0], EPS);
        assertEquals(8000.0, mRow4[0], EPS);
        assertEquals(5000.0, mCol1[0], EPS);
        assertEquals(8000.0, mCol1[3], EPS);

        // si(row, col) 0-based (rows 0..3, only column 0)
        assertEquals(5000.0, v.si(0, 0), EPS);
        assertEquals(8000.0, v.si(3, 0), EPS);

        // msi(row, col) 1-based (rows 1..4, only column 1)
        assertEquals(5000.0, v.msi(1, 1), EPS);
        assertEquals(8000.0, v.msi(4, 1), EPS);

        // OOB (0-based) for arrays
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowSi(4));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnSi(1));

        // OOB (1-based) for arrays
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowSi(5));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnSi(2));

        // OOB for si(row, col) 0-based
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(4, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, 1));

        // OOB for msi(row, col) 1-based
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(5, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 2));
    }

    // ------------------------------------------------------------------------------------
    // VectorN.Row — getScalars / getRowScalars / getColumnScalars (incl. 1-based)
    // ------------------------------------------------------------------------------------

    /**
     * Verify scalar array extraction helpers on a {@link VectorN.Row} (1x4) and their 1-based variants.
     * <p>
     * Uses centimeters as display unit to verify correct SI conversion.
     */
    @Test
    @DisplayName("VectorN.Row: getScalars helpers (Row/Column) incl. 1-based, lengths, values, and bounds")
    public void testRowNScalarExtraction()
    {
        // Row vector: [x, y, z, w] = [3 cm, 4 cm, 5 cm, 6 cm] -> SI = [0.03, 0.04, 0.05, 0.06] m
        VectorN.Row<Length, Length.Unit> v = row(new double[] {3.0, 4.0, 5.0, 6.0}, Length.Unit.cm);

        // 0-based: row 0 -> length 4
        Length[] row0 = v.getRowScalars(0);
        assertEquals(4, row0.length);
        assertEquals(0.03, row0[0].si(), EPS);
        assertEquals(0.06, row0[3].si(), EPS);

        // 0-based: individual columns -> length 1
        Length[] col0 = v.getColumnScalars(0);
        Length[] col3 = v.getColumnScalars(3);
        assertEquals(1, col0.length);
        assertEquals(1, col3.length);
        assertEquals(0.03, col0[0].si(), EPS);
        assertEquals(0.06, col3[0].si(), EPS);

        // 1-based variants
        Length[] mRow1 = v.mgetRowScalars(1);
        Length[] mCol1 = v.mgetColumnScalars(1);
        Length[] mCol4 = v.mgetColumnScalars(4);
        assertEquals(4, mRow1.length);
        assertEquals(1, mCol1.length);
        assertEquals(1, mCol4.length);
        assertEquals(0.03, mRow1[0].si(), EPS);
        assertEquals(0.06, mRow1[3].si(), EPS);
        assertEquals(0.03, mCol1[0].si(), EPS);
        assertEquals(0.06, mCol4[0].si(), EPS);

        // OOB (0-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowScalars(1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnScalars(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnScalars(4));

        // OOB (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowScalars(2));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnScalars(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnScalars(5));
    }

    // ------------------------------------------------------------------------------------
    // VectorN.Row — getRowVector / getColumnVector (incl. 1-based)
    // ------------------------------------------------------------------------------------

    /**
     * Verify vector extraction helpers on a {@link VectorN.Row} (1x4) for both 0-based and 1-based variants.
     * <p>
     * Full row returns {@link VectorN.Row}; each column returns {@link Vector1}.
     */
    @Test
    @DisplayName("VectorN.Row: getRowVector / getColumnVector incl. 1-based and bounds")
    public void testRowNVectorExtractionSpec()
    {
        // Row vector: [3 cm, 4 cm, 5 cm, 6 cm] -> SI = [0.03, 0.04, 0.05, 0.06] m
        VectorN.Row<Length, Length.Unit> v = row(new double[] {3.0, 4.0, 5.0, 6.0}, Length.Unit.cm);

        // 0-based: full row -> VectorN.Row
        VectorN.Row<Length, Length.Unit> fullRow0 = v.getRowVector(0);
        assertEquals(4, fullRow0.size());
        assertEquals(0.03, fullRow0.get(0).si(), EPS);
        assertEquals(0.06, fullRow0.get(3).si(), EPS);

        // 0-based: columns -> Vector1
        Vector1<Length, Length.Unit> c0 = v.getColumnVector(0);
        Vector1<Length, Length.Unit> c3 = v.getColumnVector(3);
        assertEquals(1, c0.size());
        assertEquals(1, c3.size());
        assertEquals(0.03, c0.get(0).si(), EPS);
        assertEquals(0.06, c3.get(0).si(), EPS);

        // 1-based
        VectorN.Row<Length, Length.Unit> mFullRow1 = v.mgetRowVector(1);
        Vector1<Length, Length.Unit> mC1 = v.mgetColumnVector(1);
        Vector1<Length, Length.Unit> mC4 = v.mgetColumnVector(4);
        assertEquals(4, mFullRow1.size());
        assertEquals(1, mC1.size());
        assertEquals(1, mC4.size());
        assertEquals(0.03, mFullRow1.get(0).si(), EPS);
        assertEquals(0.06, mFullRow1.get(3).si(), EPS);
        assertEquals(0.03, mC1.get(0).si(), EPS);
        assertEquals(0.06, mC4.get(0).si(), EPS);

        // OOB (0-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowVector(1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnVector(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnVector(4));

        // OOB (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowVector(2));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnVector(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnVector(5));
    }

    // ------------------------------------------------------------------------------------
    // VectorN.Row — getRowSi / getColumnSi (incl. 1-based) + si(r,c) / msi(r,c)
    // ------------------------------------------------------------------------------------

    /**
     * Verify SI-array extraction helpers on a {@link VectorN.Row} and direct SI element access using {@code si(row, col)} and
     * {@code msi(row, col)} including bounds.
     */
    @Test
    @DisplayName("VectorN.Row: getRowSi / getColumnSi incl. 1-based + si(r,c)/msi(r,c)")
    public void testRowNSiArrayExtractionAndSiAt()
    {
        // Row vector: [3 cm, 4 cm, 5 cm, 6 cm] -> SI = [0.03, 0.04, 0.05, 0.06] m
        VectorN.Row<Length, Length.Unit> v = row(new double[] {3.0, 4.0, 5.0, 6.0}, Length.Unit.cm);

        // 0-based SI arrays
        double[] row0 = v.getRowSi(0);
        double[] col0 = v.getColumnSi(0);
        double[] col3 = v.getColumnSi(3);
        assertEquals(4, row0.length);
        assertEquals(1, col0.length);
        assertEquals(1, col3.length);
        assertEquals(0.03, row0[0], EPS);
        assertEquals(0.06, row0[3], EPS);
        assertEquals(0.03, col0[0], EPS);
        assertEquals(0.06, col3[0], EPS);

        // 1-based SI arrays
        double[] mRow1 = v.mgetRowSi(1);
        double[] mCol1 = v.mgetColumnSi(1);
        double[] mCol4 = v.mgetColumnSi(4);
        assertEquals(4, mRow1.length);
        assertEquals(1, mCol1.length);
        assertEquals(1, mCol4.length);
        assertEquals(0.03, mRow1[0], EPS);
        assertEquals(0.06, mRow1[3], EPS);
        assertEquals(0.03, mCol1[0], EPS);
        assertEquals(0.06, mCol4[0], EPS);

        // si(row, col) 0-based (only row 0, columns 0..3)
        assertEquals(0.03, v.si(0, 0), EPS);
        assertEquals(0.06, v.si(0, 3), EPS);

        // msi(row, col) 1-based (only row 1, columns 1..4)
        assertEquals(0.03, v.msi(1, 1), EPS);
        assertEquals(0.06, v.msi(1, 4), EPS);

        // OOB (0-based) for arrays
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getRowSi(1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnSi(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.getColumnSi(4));

        // OOB (1-based) for arrays
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetRowSi(2));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnSi(0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.mgetColumnSi(5));

        // OOB for si(row, col) 0-based
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.si(0, 4));

        // OOB for msi(row, col) 1-based
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(2, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> v.msi(1, 5));
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
        VectorN.Col<Length, Length.Unit> c1 = col(new double[] {5.5}, Length.Unit.km); // 5.5 km -> 5500 m
        Vector1<Length, Length.Unit> c1As1 = c1.asVector1();
        assertEquals(1, c1As1.size());
        assertEquals(5500.0, c1As1.get(0).si(), EPS);
        assertEquals(Length.Unit.km, c1As1.getDisplayUnit());

        // 2x1 -> asVector2Col (legal)
        VectorN.Col<Length, Length.Unit> c2 = col(new double[] {5.0, 6.0}, Length.Unit.km); // -> [5000, 6000] m
        Vector2.Col<Length, Length.Unit> c2As2 = c2.asVector2Col();
        assertEquals(2, c2As2.size());
        assertEquals(5000.0, c2As2.get(0).si(), EPS);
        assertEquals(6000.0, c2As2.get(1).si(), EPS);
        assertEquals(Length.Unit.km, c2As2.getDisplayUnit());

        // 3x1 -> asVector3Col (legal)
        VectorN.Col<Length, Length.Unit> c3 = col(new double[] {5.0, 6.0, 7.0}, Length.Unit.km); // -> [5000, 6000, 7000] m
        Vector3.Col<Length, Length.Unit> c3As3 = c3.asVector3Col();
        assertEquals(3, c3As3.size());
        assertEquals(5000.0, c3As3.get(0).si(), EPS);
        assertEquals(6000.0, c3As3.get(1).si(), EPS);
        assertEquals(7000.0, c3As3.get(2).si(), EPS);
        assertEquals(Length.Unit.km, c3As3.getDisplayUnit());

        // Illegal shapes for COL: 4x1 should fail for all 3 methods
        VectorN.Col<Length, Length.Unit> c4 = col(new double[] {5.0, 6.0, 7.0, 8.0}, Length.Unit.km);
        assertThrows(IllegalStateException.class, () -> c4.asVector1());
        assertThrows(IllegalStateException.class, () -> c4.asVector2Col());
        assertThrows(IllegalStateException.class, () -> c4.asVector3Col());

        // ----------------------------
        // ROW side (cm): 1x1, 1x2, 1x3
        // ----------------------------

        // 1x1 -> asVector1 (legal)
        VectorN.Row<Length, Length.Unit> r1 = row(new double[] {3.0}, Length.Unit.cm); // 3 cm -> 0.03 m
        Vector1<Length, Length.Unit> r1as1 = r1.asVector1();
        assertEquals(1, r1as1.size());
        assertEquals(0.03, r1as1.get(0).si(), EPS);
        assertEquals(Length.Unit.cm, r1as1.getDisplayUnit());

        // 1x2 -> asVector2Row (legal)
        VectorN.Row<Length, Length.Unit> r2 = row(new double[] {3.0, 4.0}, Length.Unit.cm); // -> [0.03, 0.04] m
        Vector2.Row<Length, Length.Unit> r2As2 = r2.asVector2Row();
        assertEquals(2, r2As2.size());
        assertEquals(0.03, r2As2.get(0).si(), EPS);
        assertEquals(0.04, r2As2.get(1).si(), EPS);
        assertEquals(Length.Unit.cm, r2As2.getDisplayUnit());

        // 1x3 -> asVector3Row (legal)
        VectorN.Row<Length, Length.Unit> r3 = row(new double[] {3.0, 4.0, 5.0}, Length.Unit.cm); // -> [0.03, 0.04, 0.05] m
        Vector3.Row<Length, Length.Unit> r3As3 = r3.asVector3Row();
        assertEquals(3, r3As3.size());
        assertEquals(0.03, r3As3.get(0).si(), EPS);
        assertEquals(0.04, r3As3.get(1).si(), EPS);
        assertEquals(0.05, r3As3.get(2).si(), EPS);
        assertEquals(Length.Unit.cm, r3As3.getDisplayUnit());

        // Illegal shapes for ROW: 1x4 should fail for all 3 methods
        VectorN.Row<Length, Length.Unit> r4 = row(new double[] {3.0, 4.0, 5.0, 6.0}, Length.Unit.cm);
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
        final VectorN.Col<Length, Length.Unit> ac = col(new double[] {1, -2, 3, -4}, Length.Unit.m);
        final VectorN.Col<Length, Length.Unit> ad = col(new double[] {10, -20, 30, -40}, Length.Unit.dm);
        final VectorN.Col<Length, Length.Unit> ae = col(new double[] {1, -2, 3, 4}, Length.Unit.m);
        final VectorN.Row<Length, Length.Unit> ar = row(new double[] {1, -2, 3, -4}, Length.Unit.m);

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
        final VectorN.Col<Length, Length.Unit> ac = col(new double[] {1, -2, 3, -4}, Length.Unit.m);
        final VectorN.Row<Length, Length.Unit> ar = row(new double[] {1, -2, 3, -4}, Length.Unit.m);

        assertEquals(ac, ar.transpose());
        assertEquals(ar, ac.transpose());
    }

    /**
     * Test multiply/divide by scalar and as() method.
     */
    @Test
    public void testMultiplyScalarAs()
    {
        VectorN.Col<Length, Length.Unit> r = col(new double[] {1.0, 2.0, 3.0}, Length.Unit.km);
        var d = Duration.of(2.0, "h");
        VectorN.Col<Speed, Speed.Unit> sr = r.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sr.getDisplayUnit());
        assertEquals(0.5, sr.mget(1).getInUnit(), 1E-6);
        assertEquals(1.0, sr.mget(2).getInUnit(), 1E-6);
        assertEquals(1.5, sr.mget(3).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> r.divideEntries(d).as(Area.Unit.m2));

        VectorN.Row<Length, Length.Unit> c = row(new double[] {1.0, 2.0, 3.0}, Length.Unit.km);
        VectorN.Row<Speed, Speed.Unit> sc = c.divideEntries(d).as(Speed.Unit.km_h);
        assertEquals(Speed.Unit.km_h, sc.getDisplayUnit());
        assertEquals(0.5, sc.get(0).getInUnit(), 1E-6);
        assertEquals(1.0, sc.get(1).getInUnit(), 1E-6);
        assertEquals(1.5, sr.get(2).getInUnit(), 1E-6);
        assertThrows(IllegalArgumentException.class, () -> c.divideEntries(d).as(Area.Unit.m2));
    }

}
