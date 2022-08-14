package org.djunits.demo.examples;

import java.util.Locale;

import org.djunits.unit.util.UNITS;
import org.djunits.value.vdouble.scalar.Length;

/**
 * This Java code demonstrates conversions between related unit using DJUNITS.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @version $Revision: 954 $, $LastChangedDate: 2022-01-10 03:42:57 +0100 (Mon, 10 Jan 2022) $, by $Author: averbraeck $,
 *          initial version 3 sep. 2015 <br>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class ConversionToAndFromSI implements UNITS
{
    /** */
    private ConversionToAndFromSI()
    {
        // utility constructor.
    }

    /**
     * Create some scalar values to demonstrate conversion from and to related units.
     * @param args String[]; the command line arguments; not used
     */
    public static void main(final String[] args)
    {
        Locale.setDefault(Locale.US); // Ensure that floating point values are printed using a dot (".")
        Length length = new Length(123, KILOMETER); // Construct a Relative Length
        System.out.println("length is " + length); // prints 123.000km; i.e. uses original unit
        System.out.println("length in METER is " + length.toString(METER)); // prints 1.230+05m
        System.out.println("si value is " + length.si); // prints 123000.0
        Length delta = new Length(250, MILE);
        System.out.println("delta is " + delta); // prints 250.000mi
        System.out.println("length + delta is " + length.plus(delta)); // prints 5.253e+05m
        System.out.println("length + delta in km is " + (length.plus(delta)).toString(KILOMETER)); // prints 525.336km
    }
}
