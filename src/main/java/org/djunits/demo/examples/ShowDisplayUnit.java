package org.djunits.demo.examples;

import org.djunits.unit.LengthUnit;
import org.djunits.value.vdouble.scalar.Length;

/**
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class ShowDisplayUnit
{
    /** */
    private ShowDisplayUnit()
    {
        // Utility class
    }

    /**
     * @param args String[]; args (blank)
     */
    public static void main(final String[] args)
    {
        Length length = new Length(10.00, LengthUnit.KILOMETER);
        System.out.println("original len: " + length);
        System.out.println("original si : " + length.si);
        length.setDisplayUnit(LengthUnit.METER);
        System.out.println("changed len: " + length);
        System.out.println("[not] changed si : " + length.si);
        // not possible: length.setDisplayUnit(MassUnit.KILOGRAM);
    }

}
