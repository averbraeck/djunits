package org.djunits.demo.examples;

import org.djunits.unit.AngleUnit;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.util.AngleUtil;
import org.djunits.value.vdouble.scalar.Angle;

/**
 * Normalization of angles.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @version $Revision$, $LastChangedDate$, by $Author$, initial version Oct 28, 2015 <br>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class Angles
{
    /** */
    private Angles()
    {
        // utility class
    }

    /**
     * Create some Angle values to demonstrate conversion from and to related units, including the non-linear AngleUnit.PERCENT.
     * @param args String[]; the command line arguments; not used
     * @throws ValueRuntimeException in case of error
     */
    public static void main(final String[] args) throws ValueRuntimeException
    {
        System.out.println("Normalization of an angle adds or subtracts a multiple of 2\u03c0. For linear angle units "
                + "the result is a value between 0 and +2\u03c0.");
        System.out.println("Angles not between -90\u00b0 and +90\u00b0, -\u03c0/2 and +\u03c0/2 cannot properly be "
                + "expressed as percentage.");
        Angle a1 = new Angle(3 * Math.PI, AngleUnit.RADIAN);
        Angle normA1 = AngleUtil.normalize(a1);
        System.out.println("3 * \u03c0: " + a1 + " (" + a1.toString(AngleUnit.DEGREE) + ", " + a1.toString(AngleUnit.PERCENT)
                + ") -> " + normA1 + " (" + normA1.toString(AngleUnit.DEGREE) + ", " + normA1.toString(AngleUnit.PERCENT)
                + ") Note: percentage almost 0");
        Angle a2 = new Angle(-Math.PI, AngleUnit.RADIAN);
        Angle normA2 = AngleUtil.normalize(a2);
        System.out.println("   -\u03c0: " + a2 + " (" + a2.toString(AngleUnit.DEGREE) + ", " + a2.toString(AngleUnit.PERCENT)
                + ") -> " + normA2 + " (" + normA2.toString(AngleUnit.DEGREE) + ", " + normA2.toString(AngleUnit.PERCENT)
                + ") Note: percentage almost 0");
        Angle a3 = new Angle(-45, AngleUnit.DEGREE);
        Angle normA3 = AngleUtil.normalize(a3);
        System.out.println(
                "  -45\u0090: " + a3 + " (" + a3.toString(AngleUnit.RADIAN) + ", " + a3.toString(AngleUnit.PERCENT) + ") -> "
                        + normA3 + " (" + normA3.toString(AngleUnit.RADIAN) + ", " + normA3.toString(AngleUnit.PERCENT) + ")");
        System.out.println("");
        System.out.println("Angles expressed as percentage are always between -inf and +inf (-90\u00b0 and +90\u00b0, "
                + "-\u03c0/2 and +\u03c0/2); even after normalization.");
        Angle a4 = new Angle(-100, AngleUnit.PERCENT);
        Angle normA4 = AngleUtil.normalize(a4);
        System.out.println("-100%: " + a4 + " (" + a4.toString(AngleUnit.DEGREE) + ", " + a4.toString(AngleUnit.RADIAN)
                + ") -> " + normA4 + " (" + normA4.toString(AngleUnit.DEGREE) + ", " + normA4.toString(AngleUnit.RADIAN) + ")");
        Angle a5 = new Angle(100, AngleUnit.PERCENT);
        Angle normA5 = AngleUtil.normalize(a5);
        System.out.println(" 100%: " + a5 + " (" + a5.toString(AngleUnit.DEGREE) + ", " + a5.toString(AngleUnit.RADIAN)
                + ") -> " + normA5 + " (" + normA5.toString(AngleUnit.DEGREE) + ", " + normA5.toString(AngleUnit.RADIAN) + ")");
        Angle a6 = new Angle(1000, AngleUnit.PERCENT);
        Angle normA6 = AngleUtil.normalize(a6);
        System.out.println("1000%: " + a6 + " (" + a6.toString(AngleUnit.DEGREE) + ", " + a6.toString(AngleUnit.RADIAN)
                + ") -> " + normA6 + " (" + normA6.toString(AngleUnit.DEGREE) + ", " + normA6.toString(AngleUnit.RADIAN) + ")");
    }

}
