package org.djunits.unit.si;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.Dimensionless;
import org.djunits.value.vdouble.scalar.Energy;
import org.djunits.value.vdouble.scalar.Mass;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatDimensionless;
import org.djunits.value.vfloat.scalar.FloatEnergy;
import org.djunits.value.vfloat.scalar.FloatMass;
import org.junit.jupiter.api.Test;

/**
 * testje.java. <br>
 * <br>
 * Copyright (c) 2020-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a> <br>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class SIPrefixFormatterTest
{
    /**
     * Test the double scalar SI Prefix formatter.
     */
    @Test
    public void testDoubleSIPrefixFormatter()
    {
        checks(0.0);
        for (int power = -30; power <= 30; power++)
        {
            checks(1.234 * Math.pow(10, power));
        }
        checks(-99);
        checks(-100);
        checks(99);
        checks(100);
    }

    /**
     * Perform all checks using the specified exponent value.
     * @param value double; the value
     */
    public static void checks(final double value)
    {
        for (boolean sign : new boolean[] {false, true})
        {
            checkMass(value, sign);
            checkEnergy(value, sign);
            checkTemperature(value, sign);
            checkDimensionless(value, sign);
        }
    }

    /**
     * Check the formatting for Mass.
     * @param value double; the value
     * @param sign boolean; if true; invert the value
     */
    public static void checkMass(final double value, final boolean sign)
    {
        String in = String.format("%e kg", value);
        Mass m = Mass.valueOf(in);
        String out = m.toStringSIPrefixed(-7, 5);
        Mass m2 = Mass.valueOf(out);
        double precision = sign ? 0.005 : 0.0005; // Precision is less when a leading minus sign must be accommodated
        assertEquals(m.si, m2.si, Math.abs(m.si * precision), "output scanned back matches in");
        int pos = out.indexOf(" ");
        assertEquals(10, pos, "output value part is 10 characters long");
        FloatMass fm = FloatMass.valueOf(in);
        out = fm.toStringSIPrefixed(-7, 5);
        FloatMass fm2 = FloatMass.valueOf(out);
        assertEquals(fm.si, fm2.si, Math.abs(fm.si * precision), "output scanned back matches in");
        pos = out.indexOf(" ");
        assertEquals(10, pos, "output value part is 10 characters long");
    }

    /**
     * Check the formatting for Energy.
     * @param value double; the value
     * @param sign boolean; if true; invert the value
     */
    public static void checkEnergy(final double value, final boolean sign)
    {
        String in = String.format("%e eV", value);
        Energy e = Energy.valueOf(in);
        String out = e.toStringSIPrefixed(-7, 5);
        Energy e2 = Energy.valueOf(out);
        double precision = sign ? 0.005 : 0.0005; // Precision is less when a leading minus sign must be accommodated
        assertEquals(e.si, e2.si, Math.abs(e.si * precision), "output scanned back matches in");
        int pos = out.indexOf(" ");
        assertEquals(10, pos, "output value part is 10 characters long");
        FloatEnergy fe = FloatEnergy.valueOf(in);
        out = fe.toStringSIPrefixed(-7, 5);
        FloatEnergy fe2 = FloatEnergy.valueOf(out);
        assertEquals(fe.si, fe2.si, Math.abs(fe.si * precision), "output scanned back matches in");
        pos = out.indexOf(" ");
        assertEquals(10, pos, "output value part is 10 characters long");
    }

    /**
     * Check the formatting for Temperature.
     * @param value double; the value
     * @param sign boolean; if true; invert the value
     */
    public static void checkTemperature(final double value, final boolean sign)
    {
        String in = String.format("%e \u00B0C", value);
        AbsoluteTemperature t = AbsoluteTemperature.valueOf(in);
        String out = t.toStringSIPrefixed(-7, 5);
        // System.out.println(in + " gets parsed into " + p + " si field is " + p.si + " and then outputs as " + out);
        AbsoluteTemperature t2 = AbsoluteTemperature.valueOf(out);
        double precision = sign ? 0.005 : 0.0005; // Precision is less when a leading minus sign must be accommodated
        assertEquals(t.si, t2.si, Math.abs(t.si * precision), "output scanned back matches in");
        int pos = out.indexOf(" ");
        assertEquals(10, pos, "output value part is 10 characters long");
        FloatAbsoluteTemperature ft = FloatAbsoluteTemperature.valueOf(in);
        out = ft.toStringSIPrefixed(-7, 5);
        FloatAbsoluteTemperature ft2 = FloatAbsoluteTemperature.valueOf(out);
        assertEquals(ft.si, ft2.si, Math.abs(ft.si * precision), "output scanned back matches in");
        pos = out.indexOf(" ");
        assertEquals(10, pos, "output value part is 10 characters long");
    }

    /**
     * Check the formatting for DimensionLess.
     * @param value double; the value
     * @param sign boolean; if true; invert the value
     */
    public static void checkDimensionless(final double value, final boolean sign)
    {
        String in = String.format("%e", value);
        Dimensionless d = Dimensionless.valueOf(in);
        String out = d.toStringSIPrefixed(-7, 5);
        Dimensionless d2 = Dimensionless.valueOf(out);
        double precision = sign ? 0.005 : 0.0005; // Precision is less when a leading minus sign must be accommodated
        assertEquals(d.si, d2.si, Math.abs(d.si * precision), "output scanned back matches in");
        int pos = out.indexOf(" ");
        assertEquals(10, pos, "output value part is 10 characters long");
        FloatDimensionless fd = FloatDimensionless.valueOf(in);
        out = fd.toStringSIPrefixed(-7, 5);
        FloatDimensionless fd2 = FloatDimensionless.valueOf(out);
        assertEquals(fd.si, fd2.si, Math.abs(fd.si * precision), "output scanned back matches in");
        pos = out.indexOf(" ");
        assertEquals(10, pos, "output value part is 10 characters long");
    }

}
