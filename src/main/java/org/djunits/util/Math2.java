package org.djunits.util;

import java.util.Arrays;

/**
 * Math2 contains a few utility methods that are missing in the Math package.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class Math2
{

    /** */
    private Math2()
    {
    }

    /**
     * Return the maximum value within an array of values.
     * @param dn zero or more values
     * @return the maximum value of the arguments
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static double max(final double... dn)
    {
        if (dn == null || dn.length == 0)
            return Double.NaN;

        double max = dn[0];
        for (double d : dn)
        {
            max = Math.max(max, d);
        }
        return max;
    }

    /**
     * Return the minimum value within an array of values.
     * @param dn zero or more values
     * @return the minimum value of the arguments
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static double min(final double... dn)
    {
        if (dn == null || dn.length == 0)
            return Double.NaN;

        double min = dn[0];
        for (double d : dn)
        {
            min = Math.min(min, d);
        }
        return min;
    }

    /**
     * Return the maximum absolute value within an array of values.
     * @param dn zero or more values
     * @return the maximum absolute value of the arguments
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static double maxAbs(final double... dn)
    {
        if (dn == null || dn.length == 0)
            return Double.NaN;

        double max = Math.abs(dn[0]);
        for (double d : dn)
        {
            max = Math.max(max, Math.abs(d));
        }
        return max;
    }

    /**
     * Return the mimimum absolute value within an array of values.
     * @param dn zero or more values
     * @return the minimum absolute value of the arguments
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static double minAbs(final double... dn)
    {
        if (dn == null || dn.length == 0)
            return Double.NaN;

        double min = Math.abs(dn[0]);
        for (double d : dn)
        {
            min = Math.min(min, Math.abs(d));
        }
        return min;
    }

    /**
     * Return the sum of the values of an array of values.
     * @param dn zero or more values
     * @return the sum of the values of the arguments
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static double sum(final double... dn)
    {
        if (dn == null || dn.length == 0)
            return Double.NaN;

        double sum = 0.0;
        for (double d : dn)
            sum += d;
        return sum;
    }

    /**
     * Return the sum of the absolute values of an array of values.
     * @param dn zero or more values
     * @return the sum of the absolute values of the arguments
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static double sumAbs(final double... dn)
    {
        if (dn == null || dn.length == 0)
            return Double.NaN;

        double sum = 0.0;
        for (double d : dn)
            sum += Math.abs(d);
        return sum;
    }

    /**
     * Return the sum of the squares of the values of an array of values.
     * @param dn zero or more values
     * @return the sum of the squares of the values of the arguments
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static double sumSqr(final double... dn)
    {
        if (dn == null || dn.length == 0)
            return Double.NaN;

        double sqrsum = 0.0;
        for (double d : dn)
            sqrsum += d * d;
        return sqrsum;
    }

    /**
     * Computes the median of the provided values, skipping NaN entries.
     * <p>
     * Rules:
     * <ul>
     * <li>NaN values are ignored.</li>
     * <li>If there are 0 valid (non-NaN) values, {@code Double.NaN} is returned.</li>
     * <li>For an odd count, returns the middle value after sorting.</li>
     * <li>For an even count, returns the arithmetic mean of the two middle values.</li>
     * </ul>
     * </p>
     * @param d values to consider; NaNs are skipped
     * @return the median as defined above, or {@code Double.NaN} if no valid values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static double median(final double... d)
    {
        if (d == null || d.length == 0)
            return Double.NaN;

        // Count non-NaN values
        int count = 0;
        for (double v : d)
            if (!Double.isNaN(v))
                count++;
        if (count == 0)
            return Double.NaN;

        // Compact non-NaNs into a new array
        final double[] a = new double[count];
        int idx = 0;
        for (double v : d)
            if (!Double.isNaN(v))
                a[idx++] = v;

        Arrays.sort(a);

        if ((count & 1) == 1)
        {
            double m = a[count >>> 1];
            return m;
        }
        else
        {
            int hiIndex = count >>> 1;
            double lo = a[hiIndex - 1];
            double hi = a[hiIndex];
            double avg = lo + (hi - lo) * 0.5;
            return avg;
        }
    }

    /**
     * Computes base^exp for integers.
     * @param base the integer base
     * @param exp the non-negative exponent
     * @return base raised to the power exp
     */
    public static int pow(final int base, final int exp)
    {
        int b = base;
        int e = exp;
        if (e < 0)
        {
            throw new IllegalArgumentException("Exponent must be non-negative");
        }
        int result = 1;
        while (e > 0)
        {
            if ((e & 1) == 1)
            {
                result *= b;
            }
            b *= b;
            e >>= 1; // divide exponent by 2
        }
        return result;
    }

}
