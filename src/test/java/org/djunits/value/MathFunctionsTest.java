package org.djunits.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.junit.Test;

/**
 * Test the math functions.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class MathFunctionsTest
{
    /**
     * Test the math functions.
     */
    @Test
    public void mathFunctionsTest()
    {
        double[] testValues = { -100, -10, -1, -0.1, 0, 0.1, 1, 10, 100, -Math.PI, Math.PI };
        for (double testValue : testValues)
        {
            // System.out.println("Testing math functions with test value " + testValue);
            check("acos", DoubleMathFunctions.ACOS, FloatMathFunctions.ACOS, testValue, Math.acos(testValue));
            check("asin", DoubleMathFunctions.ASIN, FloatMathFunctions.ASIN, testValue, Math.asin(testValue));
            check("atan", DoubleMathFunctions.ATAN, FloatMathFunctions.ATAN, testValue, Math.atan(testValue));
            check("cbrt", DoubleMathFunctions.CBRT, FloatMathFunctions.CBRT, testValue, Math.cbrt(testValue));
            check("cos", DoubleMathFunctions.COS, FloatMathFunctions.COS, testValue, Math.cos(testValue));
            check("cosh", DoubleMathFunctions.COSH, FloatMathFunctions.COSH, testValue, Math.cosh(testValue));
            check("exp", DoubleMathFunctions.EXP, FloatMathFunctions.EXP, testValue, Math.exp(testValue));
            check("expm1", DoubleMathFunctions.EXPM1, FloatMathFunctions.EXPM1, testValue, Math.expm1(testValue));
            check("inv", DoubleMathFunctions.INV, FloatMathFunctions.INV, testValue, 1 / testValue);
            check("log", DoubleMathFunctions.LOG, FloatMathFunctions.LOG, testValue, Math.log(testValue));
            check("log10", DoubleMathFunctions.LOG10, FloatMathFunctions.LOG10, testValue, Math.log10(testValue));
            check("log1p", DoubleMathFunctions.LOG1P, FloatMathFunctions.LOG1P, testValue, Math.log1p(testValue));
            check("tanh", DoubleMathFunctions.TANH, FloatMathFunctions.TANH, testValue, Math.tanh(testValue));
            check("pow", DoubleMathFunctions.POW(Math.PI), FloatMathFunctions.POW((float) Math.PI), testValue,
                    Math.pow(testValue, Math.PI));
            check("pow", DoubleMathFunctions.POW(-Math.PI), FloatMathFunctions.POW((float) -Math.PI), testValue,
                    Math.pow(testValue, -Math.PI));
            check("signum", DoubleMathFunctions.SIGNUM, FloatMathFunctions.SIGNUM, testValue, Math.signum(testValue));
            check("sin", DoubleMathFunctions.SIN, FloatMathFunctions.SIN, testValue, Math.sin(testValue));
            check("sinh", DoubleMathFunctions.SINH, FloatMathFunctions.SINH, testValue, Math.sinh(testValue));
            check("sqrt", DoubleMathFunctions.SQRT, FloatMathFunctions.SQRT, testValue, Math.sqrt(testValue));
            check("tan", DoubleMathFunctions.TAN, FloatMathFunctions.TAN, testValue, Math.tan(testValue));
            check("tanh", DoubleMathFunctions.TANH, FloatMathFunctions.TANH, testValue, Math.tanh(testValue));
        }
    }

    /**
     * Check the result of a double precision Math function.
     * @param functionName String; name of the function
     * @param df DoubleFunction; the double function to apply
     * @param ff FloatFunction; the float functio to apply
     * @param testValue double; the value to apply
     * @param expectedResult double; the expected result value
     */
    public static void check(final String functionName, final DoubleFunction df, final FloatFunction ff, final double testValue,
            final double expectedResult)
    {
        double got = df.apply(testValue);
        if (Double.isNaN(expectedResult))
        {
            assertTrue(Double.isNaN(got));
        }
        else if (Double.isFinite(expectedResult))
        {
            double margin = Math.abs(expectedResult / 100000);
            assertEquals("double function " + functionName, expectedResult, got, margin);
        }
        float gotF = ff.apply((float) testValue);
        if (Double.isNaN(expectedResult))
        {
            assertTrue(Float.isNaN(gotF));
        }
        else if (Double.isFinite(expectedResult))
        {
            float margin = Math.abs((float) expectedResult / 10000);
            if (expectedResult != 0 && Math.abs(expectedResult) < 0.000001)
            {
                margin = 0.0000001f;
            }
            assertEquals("float function " + functionName, (float) expectedResult, gotF, margin);
        }
    }

}
