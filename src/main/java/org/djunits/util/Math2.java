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
     * Return the maximum value of 2 or more values.
     * @param d1 value 1
     * @param d2 value 2
     * @param dn zero or more further values
     * @return the maximum value of the arguments
     */
    public static double max(final double d1, final double d2, final double... dn)
    {
        double max = Math.max(d1, d2);
        for (double d : dn)
        {
            max = Math.max(max, d);
        }
        return max;
    }

    /**
     * Return the minimum value of 2 or more values.
     * @param d1 value 1
     * @param d2 value 2
     * @param dn zero or more further values
     * @return the minimum value of the arguments
     */
    public static double min(final double d1, final double d2, final double... dn)
    {
        double min = Math.min(d1, d2);
        for (double d : dn)
        {
            min = Math.min(min, d);
        }
        return min;
    }

    /**
     * Return the absolute maximum value of 2 or more values.
     * @param d1 value 1
     * @param d2 value 2
     * @param dn zero or more further values
     * @return the absolute maximum value of the arguments
     */
    public static double absmax(final double d1, final double d2, final double... dn)
    {
        double max = Math.max(Math.abs(d1), Math.abs(d2));
        for (double d : dn)
        {
            max = Math.max(max, Math.abs(d));
        }
        return max;
    }

    /**
     * Return the absolute minimum value of 2 or more values.
     * @param d1 value 1
     * @param d2 value 2
     * @param dn zero or more further values
     * @return the absolute minimum value of the arguments
     */
    public static double absmin(final double d1, final double d2, final double... dn)
    {
        double min = Math.min(Math.abs(d1), Math.abs(d2));
        for (double d : dn)
        {
            min = Math.min(min, Math.abs(d));
        }
        return min;
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

}
