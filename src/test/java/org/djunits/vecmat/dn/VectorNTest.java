package org.djunits.vecmat.dn;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.djunits.quantity.Length;
import org.djunits.quantity.SIQuantity;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.storage.DataGrid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive unit tests for {@link VectorN}, validating both {@link VectorN.Row} and {@link VectorN.Col} implementations
 * with concrete quantity {@link Length} and display units from {@link org.djunits.quantity.Length.Unit}.
 * <p>
 * The tests encode the <em>intended</em> functional specification. They will fail when the implementation deviates (e.g.,
 * incorrect addressing in {@code get(index)} or wrong unit composition in Hadamard operations).
 * </p>
 * <p>
 * Coverage scope:
 * </p>
 * <ul>
 * <li>Construction via a {@link DataGrid}-backed SI store</li>
 * <li>Accessors: {@code size()}, {@code rows()}, {@code cols()}, {@code isColumnVector()}, {@code si()},
 * {@code get(index)}</li>
 * <li>Iteration and {@code getScalarArray()} correctness (types, order, unit)</li>
 * <li>Fluent display-unit switching ({@code setDisplayUnit}) and stringification ({@code toString()})</li>
 * <li>Vector algebra: {@code add/subtract(Q)}, {@code add/subtract(V)}, {@code negate()}, {@code abs()},
 * {@code scaleBy(double)}</li>
 * <li>Norms: {@code norm()}, {@code normL1()}, {@code normL2()}, {@code normLp(int)}, {@code normLinf()}</li>
 * <li>Hadamard operations: {@code invertElements()}, {@code multiplyElements(V)}, {@code divideElements(V)} with unit
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
     * Minimal dense {@link DataGrid} implementation suitable for testing.
     * <p>
     * This grid stores values in a row-major contiguous {@code double[]} of length {@code rows * cols}. The implementation
     * conforms to the contract of {@link DataGrid}: indices are 0-based; {@link #getDataArray()} may return the internal array
     * for efficiency; and {@link #instantiateNew(double[])} preserves shape while changing the data contents. The semantics
     * match the uploaded {@code DataGrid} interface.
     * </p>
     */
    private static final class DenseGrid implements DataGrid<DenseGrid>
    {
        /** Number of rows of the grid (must be {@code > 0}). */
        private final int rows;

        /** Number of columns of the grid (must be {@code > 0}). */
        private final int cols;

        /** Row-major dense storage of size {@code rows * cols}; immutable reference, defensive copy in constructor. */
        private final double[] data;

        /**
         * Construct a new dense grid.
         * @param rows the number of rows; must be {@code > 0}
         * @param cols the number of columns; must be {@code > 0}
         * @param data the row-major data array; length must equal {@code rows * cols}
         * @throws IllegalArgumentException if {@code rows <= 0}, {@code cols <= 0}, or the {@code data} length is invalid
         */
        DenseGrid(final int rows, final int cols, final double[] data)
        {
            if (rows <= 0 || cols <= 0)
            {
                throw new IllegalArgumentException("rows/cols must be > 0");
            }
            if (data == null || data.length != rows * cols)
            {
                throw new IllegalArgumentException("data length must equal rows * cols");
            }
            this.rows = rows;
            this.cols = cols;
            this.data = data.clone();
        }

        /**
         * {@inheritDoc}
         * @return the number of rows of the grid
         */
        @Override
        public int rows()
        {
            return this.rows;
        }

        /**
         * {@inheritDoc}
         * @return the number of columns of the grid
         */
        @Override
        public int cols()
        {
            return this.cols;
        }

        /**
         * {@inheritDoc}
         * @param row the 0-based row index to retrieve
         * @param col the 0-based column index to retrieve
         * @return the value at {@code (row, col)} as a {@code double}
         * @throws IndexOutOfBoundsException if {@code row} or {@code col} lies outside the legal range
         */
        @Override
        public double get(final int row, final int col)
        {
            if (row < 0 || row >= this.rows || col < 0 || col >= this.cols)
            {
                throw new IndexOutOfBoundsException("get(" + row + "," + col + ")");
            }
            return this.data[row * this.cols + col];
        }

        /**
         * {@inheritDoc}
         * <p>
         * For this dense helper, the internal array is returned. This is acceptable for testing purposes.
         * </p>
         * @return the internal row-major data array
         */
        @Override
        public double[] getDataArray()
        {
            return this.data;
        }

        /**
         * {@inheritDoc}
         * @return a deep copy of this grid; underlying {@code data} is cloned via constructor
         */
        @Override
        public DenseGrid copy()
        {
            return new DenseGrid(this.rows, this.cols, this.data);
        }

        /**
         * {@inheritDoc}
         * @return the number of non-zero cells in this grid
         */
        @Override
        public int cardinality()
        {
            int c = 0;
            for (double v : this.data)
            {
                if (v != 0.0)
                {
                    c++;
                }
            }
            return c;
        }

        /**
         * {@inheritDoc}
         * @param newData the new row-major data; must have length {@code rows * cols}
         * @return a new {@code DenseGrid} with identical shape and the provided data contents
         * @throws IllegalArgumentException if {@code newData} length is invalid
         */
        @Override
        public DenseGrid instantiateNew(final double[] newData)
        {
            return new DenseGrid(this.rows, this.cols, newData);
        }

        /**
         * {@inheritDoc}
         * @param newData the new row-major data; must have length {@code rows * cols}
         * @return a new {@code DenseGrid} with identical shape and the provided data contents
         * @throws IllegalArgumentException if {@code newData} length is invalid
         */
        @Override
        public DenseGrid instantiateNew(final double[] newData, final int r, final int c)
        {
            return new DenseGrid(r, c, newData);
        }

    }

    /**
     * Helper that constructs a {@link VectorN.Col} of {@link Length} with display-unit inputs.
     * <p>
     * Values are given in {@code unit} and converted to SI for internal storage; the vector's display unit is set to
     * {@code unit}.
     * </p>
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
        return new VectorN.Col<>(new DenseGrid(inUnit.length, 1, si), unit);
    }

    /**
     * Helper that constructs a {@link VectorN.Row} of {@link Length} with display-unit inputs.
     * <p>
     * Values are given in {@code unit} and converted to SI for internal storage; the vector's display unit is set to
     * {@code unit}.
     * </p>
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
        return new VectorN.Row<>(new DenseGrid(1, inUnit.length, si), unit);
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
        assertAll(() -> assertArrayEquals(new double[] {1000.0, 2000.0, 3000.0}, c.si(), EPS, "SI storage"),
                () -> assertEquals(3, c.size(), "size"), () -> assertEquals(3, c.rows(), "rows"),
                () -> assertEquals(1, c.cols(), "cols"), () -> assertTrue(c.isColumnVector(), "isColumnVector"),
                () -> assertEquals(Length.Unit.km, c.getDisplayUnit(), "display unit"));
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
        assertAll(() -> assertArrayEquals(new double[] {0.10, 2.0, 0.03}, r.si(), EPS, "SI storage"),
                () -> assertEquals(3, r.size(), "size"), () -> assertEquals(1, r.rows(), "rows"),
                () -> assertEquals(3, r.cols(), "cols"), () -> assertFalse(r.isColumnVector(), "isColumnVector"),
                () -> assertEquals(Length.Unit.cm, r.getDisplayUnit(), "display unit"));
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
        assertAll(() -> assertEquals(1.0, r.get(1).si(), EPS, "row get(1)"),
                () -> assertEquals(4.0, r.get(4).si(), EPS, "row get(4)"),
                () -> assertEquals(5.0, c.get(1).si(), EPS, "col get(1)"),
                () -> assertEquals(8.0, c.get(4).si(), EPS, "col get(4)"));

        // Invalid indices
        assertThrows(IndexOutOfBoundsException.class, () -> r.get(0), "row get(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> r.get(5), "row get(5) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.get(0), "col get(0) must throw");
        assertThrows(IndexOutOfBoundsException.class, () -> c.get(5), "col get(5) must throw");
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

        assertAll(() -> assertEquals(1.0, a.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS),
                () -> assertEquals(2.0, b.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS),
                () -> assertEquals(3.0, d.setDisplayUnit(Length.Unit.km).si() / 1000.0, EPS));
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
        assertAll(() -> assertEquals(3, arr.length, "length"), () -> assertInstanceOf(Length.class, arr[0], "arr[0] type"),
                () -> assertInstanceOf(Length.class, arr[1], "arr[1] type"),
                () -> assertInstanceOf(Length.class, arr[2], "arr[2] type"), () -> assertEquals(3.0, arr[0].si(), EPS),
                () -> assertEquals(4.0, arr[1].si(), EPS), () -> assertEquals(5.0, arr[2].si(), EPS));
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

        assertAll(() -> assertEquals(r, ret, "fluent"), () -> assertTrue(s1.startsWith("Row["), "orientation"),
                () -> assertTrue(s1.contains("km"), "unit in default toString"),
                () -> assertTrue(s2.contains("m"), "unit in toString(withUnit)"));

        final VectorN.Col<Length, Length.Unit> c = col(new double[] {1, 2, 3}, Length.Unit.m);
        final VectorN.Col<Length, Length.Unit> retc = c.setDisplayUnit(Length.Unit.km);
        final String sc = c.toString();

        assertAll(() -> assertEquals(c, retc, "fluent"), () -> assertTrue(sc.startsWith("Col["), "orientation"),
                () -> assertTrue(sc.contains("km"), "unit in toString"));
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
        assertAll(() -> assertEquals(r.normL2().si(), r.norm().si(), EPS, "norm == normL2"),
                () -> assertEquals(10.0, r.normL1().si(), EPS, "L1"),
                () -> assertEquals(Math.sqrt(30.0), r.normL2().si(), EPS, "L2"),
                () -> assertEquals(Math.cbrt(1.0 + 8.0 + 27.0 + 64.0), r.normLp(3).si(), EPS, "Lp(3)"),
                () -> assertEquals(4.0, r.normLinf().si(), EPS, "Linf"));

        // Col: [3,4,0,0] → L1=7 ; L2=5 ; Linf=4
        final VectorN.Col<Length, Length.Unit> c = col(new double[] {3, 4, 0, 0}, Length.Unit.m);
        assertAll(() -> assertEquals(c.normL2().si(), c.norm().si(), EPS, "norm == normL2"),
                () -> assertEquals(7.0, c.normL1().si(), EPS, "L1"), () -> assertEquals(5.0, c.normL2().si(), EPS, "L2"),
                () -> assertEquals(4.0, c.normLinf().si(), EPS, "Linf"));
    }

    // =====================================================================================
    // Hadamard (element-wise) with unit composition
    // =====================================================================================

    /**
     * Verify element-wise Hadamard operations on a {@link VectorN.Row}: {@code invertElements()}, {@code multiplyElements(V)},
     * {@code divideElements(V)} and validate result-unit composition via {@link SIUnit#add(SIUnit, SIUnit)} and
     * {@link SIUnit#subtract(SIUnit, SIUnit)}.
     */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition (Row)")
    public void testHadamardRow()
    {
        final VectorN.Row<Length, Length.Unit> a = row(new double[] {2, 4, 5, 10}, Length.Unit.m);
        final VectorN.Row<Length, Length.Unit> b = row(new double[] {1, 2, 0.5, 4}, Length.Unit.km); // SI: 1000,2000,500,4000

        final VectorN.Row<SIQuantity, SIUnit> inv = a.invertElements();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1}, inv.si(), EPS, "invert");
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit invert");

        final VectorN.Row<SIQuantity, SIUnit> mul = a.multiplyElements(b);
        assertAll(() -> assertArrayEquals(new double[] {2000.0, 8000.0, 2500.0, 40_000.0}, mul.si(), EPS, "multiply"),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit(),
                        "unit add"));

        final VectorN.Row<SIQuantity, SIUnit> div = a.divideElements(b);
        assertAll(() -> assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025}, div.si(), EPS, "divide"),
                () -> assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit(),
                        "unit subtract"));
    }

    /**
     * Verify element-wise Hadamard operations on a {@link VectorN.Col}: {@code invertElements()}, {@code multiplyElements(V)},
     * {@code divideElements(V)} and validate result-unit composition.
     */
    @Test
    @DisplayName("Hadamard: invert/multiply/divide + unit composition (Col)")
    public void testHadamardCol()
    {
        final VectorN.Col<Length, Length.Unit> a = col(new double[] {2, 4, 5, 10}, Length.Unit.m);
        final VectorN.Col<Length, Length.Unit> b = col(new double[] {1, 2, 0.5, 4}, Length.Unit.km);

        final VectorN.Col<SIQuantity, SIUnit> inv = a.invertElements();
        assertArrayEquals(new double[] {0.5, 0.25, 0.2, 0.1}, inv.si(), EPS, "invert");
        assertEquals(Length.Unit.m.siUnit().invert(), inv.getDisplayUnit(), "unit invert");

        final VectorN.Col<SIQuantity, SIUnit> mul = a.multiplyElements(b);
        assertAll(() -> assertArrayEquals(new double[] {2000.0, 8000.0, 2500.0, 40_000.0}, mul.si(), EPS, "multiply"),
                () -> assertEquals(SIUnit.add(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), mul.getDisplayUnit(),
                        "unit add"));

        final VectorN.Col<SIQuantity, SIUnit> div = a.divideElements(b);
        assertAll(() -> assertArrayEquals(new double[] {0.002, 0.002, 0.01, 0.0025}, div.si(), EPS, "divide"),
                () -> assertEquals(SIUnit.subtract(Length.Unit.m.siUnit(), Length.Unit.km.siUnit()), div.getDisplayUnit(),
                        "unit subtract"));
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
        assertAll(() -> assertEquals(1.0, r.min().si(), EPS, "min"), () -> assertEquals(5.0, r.max().si(), EPS, "max"),
                () -> assertEquals(3.0, r.mean().si(), EPS, "mean"), () -> assertEquals(3.0, r.median().si(), EPS, "median"),
                () -> assertEquals(5.0, r.mode().si(), EPS, "mode defaults to max"),
                () -> assertEquals(15.0, r.sum().si(), EPS, "sum"));
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
}
