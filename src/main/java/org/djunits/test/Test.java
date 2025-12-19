package org.djunits.test;

import org.djunits.quantity.Length;
import org.djunits.unit.Units;

/**
 * Test.java.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class Test
{
    /** */
    private Test()
    {
    }

    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        Length length = new Length(5.0, Units.m);
        System.out.println(length);

        Length l2 = new Length(5.0, Units.km);
        System.out.println(l2.toDisplayString());
        System.out.println("SI = " + l2.si() + " [" + l2.getDisplayUnit().getBaseUnit().getId() + "]");
        
        Length l3 = new Length(10.0, "Qm");
        System.out.println(l3);
        System.out.println(l3.si());
        
        Length l4 = new Length(Math.PI, "mum");
        System.out.println(l4);
        System.out.println(l4.plus(length));
        System.out.println(length.plus(l4));
    }

}
