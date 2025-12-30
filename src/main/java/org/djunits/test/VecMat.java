package org.djunits.test;

import org.djunits.quantity.Length;
import org.djunits.quantity.Mass;
import org.djunits.vecmat.d2.Vector2D;

/**
 * VecMat.java.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VecMat
{
    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        var v2 = Vector2D.Col.of(10, 20, Length.Unit.METER);
        var v3 = Vector2D.Col.of(20, 30, Length.Unit.FOOT);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v2.plus(v3));
        var v4 = Vector2D.Col.of(10, 20, Mass.Unit.GRAM);
        // This does not compile: System.out.println(v2.plus(v4));
        var v5 = Vector2D.Row.of(10, 20, Mass.Unit.POUND);
        // This does not compile: System.out.println(v4.plus(v5));
        System.out.println(v4.plus(v5.transpose()));
        System.out.format("norm L1 of %s is %s%n", v4, v4.normL1());
        System.out.format("norm L2 of %s is %s%n", v4, v4.normL2());
        System.out.format("norm Lp=2 of %s is %s%n", v4, v4.normLp(2));
        System.out.format("norm Linf of %s is %s%n", v4, v4.normLinf());
    }
}
