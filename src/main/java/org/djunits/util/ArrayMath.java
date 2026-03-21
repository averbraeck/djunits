package org.djunits.util;

import org.djutils.exceptions.Throw;

/**
 * Utility methods for element-wise arithmetic on primitive {@code double[]} arrays.
 * <p>
 * This class provides common operations such as addition, scaling (multiplying by a scalar), and the fused AXPY operation
 * ({@code out = a + alpha * b}). Each operation is available in two forms:
 * <ul>
 * <li><b>Functional</b>: returns a newly allocated result array (e.g., {@link #add(double[], double[])}).</li>
 * <li><b>Procedural</b>: writes into a caller-provided output buffer (e.g.,
 * {@link #addInto(double[], double[], double[])}).</li>
 * </ul>
 * <h2>Design &amp; Performance Notes</h2>
 * <ul>
 * <li>Methods operate on primitive arrays to avoid boxing and reduce allocation overhead.</li>
 * <li>Simple indexed {@code for}-loops are used on purpose; modern JVMs can hoist bounds checks, unroll loops, and sometimes
 * auto-vectorize tight loops on hot paths.</li>
 * <li>Procedural variants ({@code ...Into}) allow output reuse to reduce GC pressure.</li>
 * <li>No special-casing for short arrays; on modern CPUs/JITs, well-structured loops scale efficiently.</li>
 * </ul>
 * <h2>Preconditions</h2>
 * <ul>
 * <li>All array parameters must be non-null.</li>
 * <li>Arrays participating in an operation must have identical lengths.</li>
 * </ul>
 * <p>
 * This class is not instantiable. Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the
 * Netherlands. All rights reserved. See for project information
 * <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is distributed under a
 * <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class ArrayMath
{
    /** Not instantiable. */
    private ArrayMath()
    {
    }

    // ---------------------------------------------------------------------
    // Functional (allocating) variants
    // ---------------------------------------------------------------------

    /**
     * Returns a new array equal to the element-wise sum {@code a + b}.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations (bounds-check
     * elimination, loop unrolling, potential auto-vectorization).
     * @param a the left-hand array; must be non-null
     * @param b the right-hand array; must be non-null and the same length as {@code a}
     * @return a newly allocated array where {@code out[i] = a[i] + b[i]} for all indices
     * @throws NullPointerException if {@code a} or {@code b} is null
     * @throws IllegalArgumentException if {@code a.length != b.length}
     */
    public static double[] add(final double[] a, final double[] b)
    {
        Throw.whenNull(a, "a");
        Throw.whenNull(b, "b");
        final int n = a.length;
        if (b.length != n)
        {
            throw new IllegalArgumentException("Length mismatch: a.length=" + n + ", b.length=" + b.length);
        }
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = a[i] + b[i];
        }
        return out;
    }

    /**
     * Returns a new array equal to the element-wise sum with a constant {@code a + c}.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations (bounds-check
     * elimination, loop unrolling, potential auto-vectorization).
     * @param a the left-hand array; must be non-null
     * @param c a constant to add to each element
     * @return a newly allocated array where {@code out[i] = a[i] + c} for all indices
     * @throws NullPointerException if {@code a} is null
     */
    public static double[] add(final double[] a, final double c)
    {
        Throw.whenNull(a, "a");
        final int n = a.length;
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = a[i] + c;
        }
        return out;
    }

    /**
     * Returns a new array equal to the element-wise difference {@code a - b}.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations (bounds-check
     * elimination, loop unrolling, potential auto-vectorization).
     * @param a the left-hand array; must be non-null
     * @param b the right-hand array; must be non-null and the same length as {@code a}
     * @return a newly allocated array where {@code out[i] = a[i] - b[i]} for all indices
     * @throws NullPointerException if {@code a} or {@code b} is null
     * @throws IllegalArgumentException if {@code a.length != b.length}
     */
    public static double[] subtract(final double[] a, final double[] b)
    {
        Throw.whenNull(a, "a");
        Throw.whenNull(b, "b");
        final int n = a.length;
        if (b.length != n)
        {
            throw new IllegalArgumentException("Length mismatch: a.length=" + n + ", b.length=" + b.length);
        }
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = a[i] - b[i];
        }
        return out;
    }

    /**
     * Returns a new array equal to the element-wise scaled vector {@code alpha * a}.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations.
     * @param a the input array; must be non-null
     * @param alpha the scalar multiplier
     * @return a newly allocated array where {@code out[i] = alpha * a[i]} for all indices
     * @throws NullPointerException if {@code a} is null
     */
    public static double[] scaleBy(final double[] a, final double alpha)
    {
        Throw.whenNull(a, "a");
        final int n = a.length;
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = alpha * a[i];
        }
        return out;
    }

    /**
     * Returns a new array with absolute values for each entry {@code abs(a)}.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations.
     * @param a the input array; must be non-null
     * @return a newly allocated array where {@code out[i] = Math.abs(a[i])} for all indices
     * @throws NullPointerException if {@code a} is null
     */
    public static double[] abs(final double[] a)
    {
        Throw.whenNull(a, "a");
        final int n = a.length;
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = Math.abs(a[i]);
        }
        return out;
    }

    /**
     * Returns a new array equal to the fused AXPY operation {@code a + alpha * b}.
     * <p>
     * This performs a single pass over the data to improve cache locality compared to separate scale and add steps.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations.
     * @param a the left-hand array; must be non-null
     * @param b the right-hand array; must be non-null and the same length as {@code a}
     * @param alpha the scalar multiplier for {@code b}
     * @return a newly allocated array where {@code out[i] = a[i] + alpha * b[i]}
     * @throws NullPointerException if {@code a} or {@code b} is null
     * @throws IllegalArgumentException if {@code a.length != b.length}
     */
    public static double[] axpy(final double[] a, final double[] b, final double alpha)
    {
        Throw.whenNull(a, "a");
        Throw.whenNull(b, "b");
        final int n = a.length;
        if (b.length != n)
        {
            throw new IllegalArgumentException("Length mismatch: a.length=" + n + ", b.length=" + b.length);
        }
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = a[i] + alpha * b[i];
        }
        return out;
    }

    /**
     * Returns a new array equal to the element-wise multiplication {@code a * b}.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations (bounds-check
     * elimination, loop unrolling, potential auto-vectorization).
     * @param a the left-hand array; must be non-null
     * @param b the right-hand array; must be non-null and the same length as {@code a}
     * @return a newly allocated array where {@code out[i] = a[i] * b[i]} for all indices
     * @throws NullPointerException if {@code a} or {@code b} is null
     * @throws IllegalArgumentException if {@code a.length != b.length}
     */
    public static double[] multiply(final double[] a, final double[] b)
    {
        Throw.whenNull(a, "a");
        Throw.whenNull(b, "b");
        final int n = a.length;
        if (b.length != n)
        {
            throw new IllegalArgumentException("Length mismatch: a.length=" + n + ", b.length=" + b.length);
        }
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = a[i] * b[i];
        }
        return out;
    }

    /**
     * Returns a new array equal to the element-wise division {@code a / b}.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations (bounds-check
     * elimination, loop unrolling, potential auto-vectorization).
     * @param a the left-hand array; must be non-null
     * @param b the right-hand array; must be non-null and the same length as {@code a}
     * @return a newly allocated array where {@code out[i] = a[i] / b[i]} for all indices
     * @throws NullPointerException if {@code a} or {@code b} is null
     * @throws IllegalArgumentException if {@code a.length != b.length}
     */
    public static double[] divide(final double[] a, final double[] b)
    {
        Throw.whenNull(a, "a");
        Throw.whenNull(b, "b");
        final int n = a.length;
        if (b.length != n)
        {
            throw new IllegalArgumentException("Length mismatch: a.length=" + n + ", b.length=" + b.length);
        }
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = a[i] / b[i];
        }
        return out;
    }

    /**
     * Returns a new array equal to the element-wise reciprocal {@code 1 / a}.
     * <p>
     * <strong>Implementation Note:</strong> Uses a simple indexed {@code for}-loop to enable JIT optimizations (bounds-check
     * elimination, loop unrolling, potential auto-vectorization).
     * @param a the array; must be non-null
     * @return a newly allocated array where {@code out[i] = 1 / a[i]} for all indices
     * @throws NullPointerException if {@code a} is null
     */
    public static double[] reciprocal(final double[] a)
    {
        Throw.whenNull(a, "a");
        final int n = a.length;
        double[] out = new double[n];
        for (int i = 0; i < n; i++)
        {
            out[i] = 1.0 / a[i];
        }
        return out;
    }

    /**
     * Returns the number of non-zero elements in the array.
     * @param a the array; must be non-null
     * @return the number of non-zero elements in the array
     * @throws NullPointerException if {@code a} is null
     */
    public static double nnz(final double[] a)
    {
        Throw.whenNull(a, "a");
        int n = 0;
        for (double d : a)
        {
            n += d == 0.0 ? 0 : 1;
        }
        return n;
    }

    // ---------------------------------------------------------------------
    // Procedural (into) variants — write into caller-provided buffer
    // ---------------------------------------------------------------------

    /**
     * Computes the element-wise sum {@code a + b} and writes the result into {@code out}.
     * <p>
     * <strong>Implementation Note:</strong> Reusing {@code out} avoids allocation and reduces GC pressure in hot paths.
     * @param a the left-hand array; must be non-null
     * @param b the right-hand array; must be non-null and the same length as {@code a}
     * @param out the destination array; must be non-null and the same length as {@code a}
     * @throws NullPointerException if any argument is null
     * @throws IllegalArgumentException if lengths differ among {@code a}, {@code b}, and {@code out}
     */
    public static void addInto(final double[] a, final double[] b, final double[] out)
    {
        Throw.whenNull(a, "a");
        Throw.whenNull(b, "b");
        Throw.whenNull(out, "out");
        final int n = a.length;
        if (b.length != n || out.length != n)
        {
            throw new IllegalArgumentException(
                    "Length mismatch: a.length=" + n + ", b.length=" + b.length + ", out.length=" + out.length);
        }
        for (int i = 0; i < n; i++)
        {
            out[i] = a[i] + b[i];
        }
    }

    /**
     * Computes the element-wise scaling {@code alpha * a} and writes the result into {@code out}.
     * <p>
     * <strong>Implementation Note:</strong> Reusing {@code out} avoids allocation and reduces GC pressure in hot paths.
     * @param a the input array; must be non-null
     * @param alpha the scalar multiplier
     * @param out the destination array; must be non-null and the same length as {@code a}
     * @throws NullPointerException if {@code a} or {@code out} is null
     * @throws IllegalArgumentException if {@code out.length != a.length}
     */
    public static void scaleInto(final double[] a, final double alpha, final double[] out)
    {
        Throw.whenNull(a, "a");
        Throw.whenNull(out, "out");
        final int n = a.length;
        if (out.length != n)
        {
            throw new IllegalArgumentException("Length mismatch: a.length=" + n + ", out.length=" + out.length);
        }
        for (int i = 0; i < n; i++)
        {
            out[i] = alpha * a[i];
        }
    }

    /**
     * Computes the fused AXPY operation {@code out = a + alpha * b} and writes the result into {@code out}.
     * <p>
     * <strong>Implementation Note:</strong> Performs a single pass over the arrays to maximize cache locality.
     * @param a the left-hand array; must be non-null
     * @param b the right-hand array; must be non-null and the same length as {@code a}
     * @param alpha the scalar multiplier for {@code b}
     * @param out the destination array; must be non-null and the same length as {@code a}
     * @throws NullPointerException if any argument is null
     * @throws IllegalArgumentException if lengths differ among {@code a}, {@code b}, and {@code out}
     */
    public static void axpyInto(final double[] a, final double[] b, final double alpha, final double[] out)
    {
        Throw.whenNull(a, "a");
        Throw.whenNull(b, "b");
        Throw.whenNull(out, "out");
        final int n = a.length;
        if (b.length != n || out.length != n)
        {
            throw new IllegalArgumentException(
                    "Length mismatch: a.length=" + n + ", b.length=" + b.length + ", out.length=" + out.length);
        }
        for (int i = 0; i < n; i++)
        {
            out[i] = a[i] + alpha * b[i];
        }
    }
}
