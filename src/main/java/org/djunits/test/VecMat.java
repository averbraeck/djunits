package org.djunits.test;

import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.quantity.Mass;
import org.djunits.quantity.Speed;
import org.djunits.vecmat.d2.Matrix2D;
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
    /** */
    private static void as1()
    {
        var l1 = Length.of(1.0, "km");
        var m1 = Mass.of(1.0, "kg");
        System.out.println(l1 + " * " + m1 + " = " + l1.multiply(m1));
        System.out.println(l1 + " / " + m1 + " = " + l1.divide(m1));
        var q = Duration.of(1.0, "h").divide(l1).reciprocal();
        var speed = q.as(Speed.Unit.METER_PER_SECOND);
        System.out.format("Speed = %s, class = %s%n", speed, speed.getClass().getSimpleName());        
    }

    /** */
    private static void as2()
    {
        var l1 = Length.of(1.0, "km");
        var q = Duration.of(1.0, "h").divide(l1);
        // var speed = q.as(Speed.Unit.METER_PER_SECOND);
        var speed = q.reciprocal().as(Speed.Unit.METER_PER_SECOND);
        System.out.format("Speed = %s, class = %s%n", speed, speed.getClass().getSimpleName());
    }

    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        as1();
        as2();
       
        var v2 = Vector2D.Col.of(10, 20, Length.Unit.METER);
        var v3 = Vector2D.Col.of(20, 30, Length.Unit.FOOT);
        System.out.println("\n" + v2);
        System.out.println(v3);
        System.out.println(v2.add(v3));
        var v4 = Vector2D.Col.of(10, 20, Mass.Unit.GRAM);
        // This does not compile: System.out.println(v2.plus(v4));
        var v5 = Vector2D.Row.of(10, 20, Mass.Unit.POUND);
        // This does not compile: System.out.println(v4.plus(v5));
        System.out.println(v4.add(v5.transpose()));
        System.out.format("%nnorm L1 of %s is %s%n", v4, v4.normL1());
        System.out.format("norm L2 of %s is %s%n", v4, v4.normL2());
        System.out.format("norm Lp=2 of %s is %s%n", v4, v4.normLp(2));
        System.out.format("norm Linf of %s is %s%n", v4, v4.normLinf());

        System.out.println("\n\nMatrices");
        var mat = Matrix2D.of(new double[][] {{1.0, 2.0}, {5.0, 4.0}}, Duration.Unit.SECOND);
        System.out.println("matrix:\n" + mat);
        System.out.println("\nmatrix + matrix:\n" + mat.add(mat));
        System.out.println("\nmatrix + 1 day:\n" + mat.plus(Duration.of(1.0, "day")));
        System.out.println("\ndeterminant: " + mat.determinant());
        try
        {
            System.out.println("\ninverse:\n" + mat.inverse());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        System.out.println("\ntrace: " + mat.trace());
        System.out.println("\nadjugate:\n" + mat.adjugate());
    }
}
