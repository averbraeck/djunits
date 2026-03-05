package org.djunits.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Math2}. This suite aims for 100% line and branch coverage by exercising:
 * <ul>
 * <li>Aggregations: {@link Math2#max(double...)}, {@link Math2#min(double...)}, {@link Math2#maxAbs(double...)},
 * {@link Math2#minAbs(double...)}, {@link Math2#sum(double...)}, {@link Math2#sumAbs(double...)},
 * {@link Math2#sumSqr(double...)}</li>
 * <li>{@link Math2#median(double...)} with odd/even counts and {@code NaN}-filtering behavior</li>
 * <li>{@link Math2#pow(int, int)} for various bases/exp and the negative-exponent error branch</li>
 * <li>Coverage of the private constructor</li>
 * </ul>
 * <p>
 * <strong>Conventions:</strong> Floating-point comparisons use tight absolute tolerances; wherever the API specifies
 * {@code Double.NaN} as the sentinel for {@code null}/empty inputs, the tests assert that behavior explicitly.
 * </p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class Math2Test
{
    /** Absolute comparison tolerance for floating-point assertions. */
    private static final double EPS = 1e-12;

    // ---------------------------------------------------------------------
    // Constructor coverage
    // ---------------------------------------------------------------------

    /**
     * Covers the private no-arg constructor of {@link Math2} to attain full coverage of the class.
     * @throws Exception on reflection errors (should not occur in normal environments)
     */
    @Test
    void privateConstructorCoverage() throws Exception
    {
        Constructor<Math2> ctor = Math2.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        Object instance = ctor.newInstance();
        assertNotNull(instance, "Reflection should construct a non-null instance (coverage only)");
    }

    // ---------------------------------------------------------------------
    // max / min
    // ---------------------------------------------------------------------

    /**
     * Verifies {@link Math2#max(double...)} for typical values, extremes, and sentinel behavior.
     */
    @Test
    void maxValues()
    {
        // Normal numbers
        assertEquals(5.0, Math2.max(1.0, -2.0, 5.0, 0.0), EPS);
        // Presence of +Infinity should dominate
        assertEquals(Double.POSITIVE_INFINITY, Math2.max(-1.0, Double.POSITIVE_INFINITY, 3.0));
        // Single element
        assertEquals(-7.0, Math2.max(-7.0), EPS);

        // Sentinel behavior: null / empty -> NaN
        assertTrue(Double.isNaN(Math2.max((double[]) null)));
        assertTrue(Double.isNaN(Math2.max()));
    }

    /**
     * Verifies {@link Math2#min(double...)} for typical values, extremes, and sentinel behavior.
     */
    @Test
    void minValues()
    {
        // Normal numbers
        assertEquals(-3.0, Math2.min(2.0, -3.0, 1.0), EPS);
        // Presence of -Infinity should dominate
        assertEquals(Double.NEGATIVE_INFINITY, Math2.min(10.0, Double.NEGATIVE_INFINITY, 0.0));
        // Single element
        assertEquals(4.0, Math2.min(4.0), EPS);

        // Sentinel behavior: null / empty -> NaN
        assertTrue(Double.isNaN(Math2.min((double[]) null)));
        assertTrue(Double.isNaN(Math2.min()));
    }

    // ---------------------------------------------------------------------
    // maxAbs / minAbs
    // ---------------------------------------------------------------------

    /**
     * Verifies {@link Math2#maxAbs(double...)} with negative/positive values and sentinels.
     */
    @Test
    void maxAbsValues()
    {
        assertEquals(5.0, Math2.maxAbs(-5.0, 3.0, -4.9), EPS);
        assertEquals(0.0, Math2.maxAbs(0.0, -0.0), EPS);
        // Infinity dominates in absolute value
        assertEquals(Double.POSITIVE_INFINITY, Math2.maxAbs(-1.0, Double.NEGATIVE_INFINITY, 2.0));

        assertTrue(Double.isNaN(Math2.maxAbs((double[]) null)));
        assertTrue(Double.isNaN(Math2.maxAbs()));
    }

    /**
     * Verifies {@link Math2#minAbs(double...)} with negative/positive values (including -0.0) and sentinels.
     */
    @Test
    void minAbsValues()
    {
        assertEquals(0.0, Math2.minAbs(0.0, -0.0, 2.0), EPS);
        assertEquals(1.5, Math2.minAbs(-3.0, 1.5, -2.0), EPS);

        assertTrue(Double.isNaN(Math2.minAbs((double[]) null)));
        assertTrue(Double.isNaN(Math2.minAbs()));
    }

    // ---------------------------------------------------------------------
    // sum / sumAbs / sumSqr
    // ---------------------------------------------------------------------

    /**
     * Verifies {@link Math2#sum(double...)} with typical inputs and sentinel behavior.
     */
    @Test
    void sumValues()
    {
        assertEquals(6.0, Math2.sum(1.0, 2.0, 3.0), EPS);
        assertTrue(Double.isNaN(Math2.sum()), "empty -> NaN per contract, not 0.0");
        // per API: null/empty -> NaN; assert both explicitly
        assertTrue(Double.isNaN(Math2.sum((double[]) null)));
        assertTrue(Double.isNaN(Math2.sum()));
    }

    /**
     * Verifies {@link Math2#sumAbs(double...)} produces the sum of absolute values and sentinel behavior.
     */
    @Test
    void sumAbsValues()
    {
        assertEquals(10.0, Math2.sumAbs(-5.0, -3.0, 2.0), EPS);
        assertTrue(Double.isNaN(Math2.sumAbs((double[]) null)));
        assertTrue(Double.isNaN(Math2.sumAbs()));
    }

    /**
     * Verifies {@link Math2#sumSqr(double...)} produces the sum of squares and sentinel behavior.
     */
    @Test
    void sumSqrValues()
    {
        assertEquals(1.0 + 4.0 + 9.0, Math2.sumSqr(1.0, -2.0, 3.0), EPS);
        assertEquals(0.0, Math2.sumSqr(0.0), EPS);
        assertTrue(Double.isNaN(Math2.sumSqr((double[]) null)));
        assertTrue(Double.isNaN(Math2.sumSqr()));
    }

    // ---------------------------------------------------------------------
    // median
    // ---------------------------------------------------------------------

    /**
     * Verifies {@link Math2#median(double...)} across odd/even sizes, NaN filtering, single-value, and all-NaN inputs.
     * <ul>
     * <li>Odd count -> middle element</li>
     * <li>Even count -> average of two middle elements</li>
     * <li>NaNs are ignored; if all values are NaN -> {@code Double.NaN}</li>
     * </ul>
     */
    @Test
    void medianVariants()
    {
        // Odd count, unsorted
        assertEquals(3.0, Math2.median(5.0, 3.0, 1.0), EPS);

        // Even count, unsorted -> average of two middles after sorting
        // Sorted list would be [1, 2, 10, 100] -> average(2,10) = 6
        assertEquals(6.0, Math2.median(10.0, 100.0, 1.0, 2.0), EPS);

        // Mixed with NaNs: NaNs are ignored
        // Valid numbers: [1.0, 2.0, 3.0] -> median = 2.0
        assertEquals(2.0, Math2.median(Double.NaN, 1.0, 2.0, Double.NaN, 3.0), EPS);

        // Single valid value among NaNs
        assertEquals(42.0, Math2.median(Double.NaN, 42.0, Double.NaN), EPS);

        // All NaN -> NaN
        assertTrue(Double.isNaN(Math2.median(Double.NaN, Double.NaN)));

        // Null / empty -> NaN
        assertTrue(Double.isNaN(Math2.median((double[]) null)));
        assertTrue(Double.isNaN(Math2.median()));
    }

    // ---------------------------------------------------------------------
    // pow(int, int)
    // ---------------------------------------------------------------------

    /**
     * Verifies {@link Math2#pow(int, int)} for diverse cases, including the negative-exponent error.
     */
    @Test
    void powVariants()
    {
        // Exponent zero -> 1 for any base
        assertEquals(1, Math2.pow(0, 0));
        assertEquals(1, Math2.pow(7, 0));
        assertEquals(1, Math2.pow(-3, 0));

        // Positive exponents
        assertEquals(8, Math2.pow(2, 3));
        assertEquals(81, Math2.pow(3, 4));
        assertEquals(1_000_000, Math2.pow(10, 6)); // potential overflow is acceptable in int semantics

        // Negative base with odd/even exponent
        assertEquals(-27, Math2.pow(-3, 3)); // odd exponent retains sign
        assertEquals(16, Math2.pow(-2, 4)); // even exponent yields positive

        // Error branch: negative exponent is illegal
        assertThrows(IllegalArgumentException.class, () -> Math2.pow(2, -1));
        assertThrows(IllegalArgumentException.class, () -> Math2.pow(-2, -3));
    }
}
