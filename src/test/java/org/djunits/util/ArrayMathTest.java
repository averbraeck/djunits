package org.djunits.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ArrayMath}. The suite aims for 100% line and branch coverage by exercising:
 * <ul>
 * <li>All functional (allocating) operations: {@code add}, {@code add(a,c)}, {@code subtract}, {@code scaleBy}, {@code abs},
 * {@code axpy}, {@code multiply}, {@code divide}, {@code reciprocal}</li>
 * <li>All procedural (into-buffer) operations: {@code addInto}, {@code scaleInto}, {@code axpyInto}</li>
 * <li>All error paths: {@link NullPointerException} for {@code null} inputs and {@link IllegalArgumentException} for length
 * mismatches</li>
 * <li>Edge behavior: empty arrays, in-place destinations ({@code out == a} and/or {@code out == b}), division by zero and
 * {@code NaN}, reciprocal of 0 and {@code Infinity}</li>
 * <li>Coverage of the private constructor via reflection</li>
 * </ul>
 * <p>
 * <strong>Design:</strong> Each test documents the scenario and the expected outcome. An {@code assertArrayClose} helper is
 * used with a tight tolerance for floating-point comparisons.
 * </p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ArrayMathTest
{
    /**
     * Absolute comparison tolerance for double arrays.
     */
    private static final double EPS = 1e-12;

    /**
     * Helper that asserts two double arrays are equal within {@link #EPS}.
     * @param expected the expected values
     * @param actual the actual values
     */
    private static void assertArrayClose(final double[] expected, final double[] actual)
    {
        assertArrayEquals(expected, actual, EPS);
    }

    // ------------------------------------------------------------------------------------
    // Constructor coverage
    // ------------------------------------------------------------------------------------

    /**
     * Covers the private no-arg constructor of {@link ArrayMath} to achieve 100% coverage of the class.
     * @throws Exception when reflection fails (should not happen in normal environments)
     */
    @Test
    void constructorIsNotInstantiableButReachableViaReflection() throws Exception
    {
        Constructor<ArrayMath> ctor = ArrayMath.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        Object instance = ctor.newInstance();
        assertNotNull(instance, "Reflection should be able to construct an instance for coverage purposes");
    }

    // ------------------------------------------------------------------------------------
    // Functional (allocating) operations
    // ------------------------------------------------------------------------------------

    /**
     * Verifies {@link ArrayMath#add(double[], double[])} on a happy path and length/Null preconditions. Expected: element-wise
     * sum, new array returned; NPE/IAE on invalid inputs.
     */
    @Test
    void addArraysFunctional()
    {
        double[] a = {1.0, -2.0, 3.5};
        double[] b = {4.0, 5.0, -1.5};
        assertArrayClose(new double[] {5.0, 3.0, 2.0}, ArrayMath.add(a, b));

        // empty arrays
        assertArrayClose(new double[] {}, ArrayMath.add(new double[] {}, new double[] {}));

        // invalids
        assertThrows(NullPointerException.class, () -> ArrayMath.add(null, b));
        assertThrows(NullPointerException.class, () -> ArrayMath.add(a, null));
        assertThrows(IllegalArgumentException.class, () -> ArrayMath.add(new double[] {1}, new double[] {1, 2}));
    }

    /**
     * Verifies {@link ArrayMath#add(double[], double)} adds a constant to each element and returns a new array. Also checks
     * empty array path and null precondition.
     */
    @Test
    void addScalarFunctional()
    {
        double[] a = {1.0, -2.0, 3.0};
        assertArrayClose(new double[] {3.5, 0.5, 5.5}, ArrayMath.add(a, 2.5));
        assertArrayClose(new double[] {}, ArrayMath.add(new double[] {}, 10.0));
        assertThrows(NullPointerException.class, () -> ArrayMath.add((double[]) null, 1.0));
    }

    /**
     * Verifies {@link ArrayMath#subtract(double[], double[])} element-wise difference and preconditions.
     */
    @Test
    void subtractFunctional()
    {
        double[] a = {5.0, 5.0, 5.0};
        double[] b = {1.0, 2.0, 3.0};
        assertArrayClose(new double[] {4.0, 3.0, 2.0}, ArrayMath.subtract(a, b));

        assertThrows(NullPointerException.class, () -> ArrayMath.subtract(null, b));
        assertThrows(NullPointerException.class, () -> ArrayMath.subtract(a, null));
        assertThrows(IllegalArgumentException.class, () -> ArrayMath.subtract(new double[] {1}, new double[] {}));
    }

    /**
     * Verifies {@link ArrayMath#scaleBy(double[], double)} scaling by a scalar and preconditions.
     */
    @Test
    void scaleByFunctional()
    {
        double[] a = {2.0, -3.0, 0.5};
        assertArrayClose(new double[] {-4.0, 6.0, -1.0}, ArrayMath.scaleBy(a, -2.0));
        assertArrayClose(new double[] {}, ArrayMath.scaleBy(new double[] {}, 3.0));
        assertThrows(NullPointerException.class, () -> ArrayMath.scaleBy(null, 1.0));
    }

    /**
     * Verifies {@link ArrayMath#abs(double[])} absolute value, including negative zero and {@code NaN}.
     */
    @Test
    void absFunctional()
    {
        double[] a = {-0.0, -2.5, 3.0, Double.NaN};
        double[] out = ArrayMath.abs(a);
        assertEquals(0.0, out[0], EPS, "abs(-0.0) should be +0.0");
        assertArrayClose(new double[] {0.0, 2.5, 3.0, Double.NaN}, out);
        assertThrows(NullPointerException.class, () -> ArrayMath.abs(null));
    }

    /**
     * Verifies fused AXPY {@link ArrayMath#axpy(double[], double[], double)} and preconditions.
     */
    @Test
    void axpyFunctional()
    {
        double[] a = {1.0, 2.0, 3.0};
        double[] b = {4.0, 5.0, 6.0};
        double alpha = 0.5;
        assertArrayClose(new double[] {1.0 + 0.5 * 4.0, 2.0 + 0.5 * 5.0, 3.0 + 0.5 * 6.0}, ArrayMath.axpy(a, b, alpha));
        assertThrows(NullPointerException.class, () -> ArrayMath.axpy(null, b, 1.0));
        assertThrows(NullPointerException.class, () -> ArrayMath.axpy(a, null, 1.0));
        assertThrows(IllegalArgumentException.class, () -> ArrayMath.axpy(new double[] {1}, new double[] {1, 2}, 1.0));
    }

    /**
     * Verifies {@link ArrayMath#multiply(double[], double[])} element-wise product and preconditions.
     */
    @Test
    void multiplyFunctional()
    {
        double[] a = {2.0, -3.0, 0.5};
        double[] b = {-4.0, -2.0, 10.0};
        assertArrayClose(new double[] {-8.0, 6.0, 5.0}, ArrayMath.multiply(a, b));
        assertThrows(NullPointerException.class, () -> ArrayMath.multiply(null, b));
        assertThrows(NullPointerException.class, () -> ArrayMath.multiply(a, null));
        assertThrows(IllegalArgumentException.class, () -> ArrayMath.multiply(new double[] {1}, new double[] {1, 2}));
    }

    /**
     * Verifies {@link ArrayMath#divide(double[], double[])} element-wise division, including division by zero and NaN.
     */
    @Test
    void divideFunctional()
    {
        double[] a = {1.0, 0.0, 1.0};
        double[] b = {0.0, 0.0, -0.0};
        double[] out = ArrayMath.divide(a, b);
        assertTrue(Double.isInfinite(out[0]) && out[0] > 0, "1/0 should be +Infinity");
        assertTrue(Double.isNaN(out[1]), "0/0 should be NaN");
        assertTrue(Double.isInfinite(out[2]) && out[2] < 0, "1/(-0.0) should be -Infinity");

        assertThrows(NullPointerException.class, () -> ArrayMath.divide(null, b));
        assertThrows(NullPointerException.class, () -> ArrayMath.divide(a, null));
        assertThrows(IllegalArgumentException.class, () -> ArrayMath.divide(new double[] {1, 2}, new double[] {1}));
    }

    /**
     * Verifies {@link ArrayMath#reciprocal(double[])} including reciprocal of 0 and Infinity.
     */
    @Test
    void reciprocalFunctional()
    {
        double[] a = {2.0, -4.0, 0.0, Double.POSITIVE_INFINITY, Double.NaN};
        double[] out = ArrayMath.reciprocal(a);
        assertArrayClose(new double[] {0.5, -0.25, Double.POSITIVE_INFINITY, 0.0, Double.NaN}, out);
        assertThrows(NullPointerException.class, () -> ArrayMath.reciprocal(null));
    }

    // ------------------------------------------------------------------------------------
    // Procedural (into-buffer) operations
    // ------------------------------------------------------------------------------------

    /**
     * Verifies {@link ArrayMath#addInto(double[], double[], double[])}.
     * <ul>
     * <li>Normal usage with distinct output buffer</li>
     * <li>In-place usage with {@code out == a}</li>
     * <li>In-place usage with {@code out == b}</li>
     * <li>All precondition error paths</li>
     * </ul>
     */
    @Test
    void addIntoProcedural()
    {
        double[] a = {1.0, 2.0, 3.0};
        double[] b = {4.0, 5.0, 6.0};
        double[] out = new double[3];

        // distinct output
        ArrayMath.addInto(a, b, out);
        assertArrayClose(new double[] {5.0, 7.0, 9.0}, out);

        // in-place out == a
        double[] aCopy = {1.0, 2.0, 3.0};
        ArrayMath.addInto(aCopy, b, aCopy);
        assertArrayClose(new double[] {5.0, 7.0, 9.0}, aCopy);

        // in-place out == b
        double[] bCopy = {4.0, 5.0, 6.0};
        ArrayMath.addInto(a, bCopy, bCopy);
        assertArrayClose(new double[] {5.0, 7.0, 9.0}, bCopy);

        // preconditions
        assertThrows(NullPointerException.class, () -> ArrayMath.addInto(null, b, out));
        assertThrows(NullPointerException.class, () -> ArrayMath.addInto(a, null, out));
        assertThrows(NullPointerException.class, () -> ArrayMath.addInto(a, b, null));
        assertThrows(IllegalArgumentException.class,
                () -> ArrayMath.addInto(new double[] {1}, new double[] {1, 2}, new double[1]));
        assertThrows(IllegalArgumentException.class,
                () -> ArrayMath.addInto(new double[] {1}, new double[] {1}, new double[2]));
    }

    /**
     * Verifies {@link ArrayMath#scaleInto(double[], double, double[])}.
     * <ul>
     * <li>Normal usage with distinct output buffer</li>
     * <li>In-place usage with {@code out == a}</li>
     * <li>Null and length precondition errors</li>
     * </ul>
     */
    @Test
    void scaleIntoProcedural()
    {
        double[] a = {2.0, -1.0, 4.0};
        double[] out = new double[3];

        // distinct output
        ArrayMath.scaleInto(a, -2.0, out);
        assertArrayClose(new double[] {-4.0, 2.0, -8.0}, out);

        // in-place out == a
        double[] aCopy = {2.0, -1.0, 4.0};
        ArrayMath.scaleInto(aCopy, 0.5, aCopy);
        assertArrayClose(new double[] {1.0, -0.5, 2.0}, aCopy);

        // preconditions
        assertThrows(NullPointerException.class, () -> ArrayMath.scaleInto(null, 1.0, out));
        assertThrows(NullPointerException.class, () -> ArrayMath.scaleInto(a, 1.0, null));
        assertThrows(IllegalArgumentException.class, () -> ArrayMath.scaleInto(new double[] {1, 2}, 1.0, new double[1]));
    }

    /**
     * Verifies {@link ArrayMath#axpyInto(double[], double[], double, double[])}.
     * <ul>
     * <li>Normal usage with distinct output buffer</li>
     * <li>In-place usage with {@code out == a}</li>
     * <li>In-place usage with {@code out == b}</li>
     * <li>All precondition error paths</li>
     * </ul>
     */
    @Test
    void axpyIntoProcedural()
    {
        double[] a = {1.0, 2.0, 3.0};
        double[] b = {4.0, 5.0, 6.0};
        double alpha = 2.0;
        double[] out = new double[3];

        // distinct output
        ArrayMath.axpyInto(a, b, alpha, out);
        assertArrayClose(new double[] {1 + 2 * 4, 2 + 2 * 5, 3 + 2 * 6}, out);

        // in-place out == a
        double[] aCopy = {1.0, 2.0, 3.0};
        ArrayMath.axpyInto(aCopy, b, alpha, aCopy);
        assertArrayClose(new double[] {1 + 2 * 4, 2 + 2 * 5, 3 + 2 * 6}, aCopy);

        // in-place out == b
        double[] bCopy = {4.0, 5.0, 6.0};
        ArrayMath.axpyInto(a, bCopy, alpha, bCopy);
        assertArrayClose(new double[] {1 + 2 * 4, 2 + 2 * 5, 3 + 2 * 6}, bCopy);

        // preconditions
        assertThrows(NullPointerException.class, () -> ArrayMath.axpyInto(null, b, alpha, out));
        assertThrows(NullPointerException.class, () -> ArrayMath.axpyInto(a, null, alpha, out));
        assertThrows(NullPointerException.class, () -> ArrayMath.axpyInto(a, b, alpha, null));
        assertThrows(IllegalArgumentException.class,
                () -> ArrayMath.axpyInto(new double[] {1}, new double[] {1, 2}, 1.0, new double[1]));
        assertThrows(IllegalArgumentException.class,
                () -> ArrayMath.axpyInto(new double[] {1}, new double[] {1}, 1.0, new double[2]));
    }
}
