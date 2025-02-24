package org.djunits.value.vfloat.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.junit.jupiter.api.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class FloatDimensionlessMatrixTest
{

    /**
     * Test the methods that are only implemented in DimensionLess matrices.
     */
    @Test
    public void testDimensionLess()
    {
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(12, 23, false);
        // put at least one negative value in the test data
        denseTestData[5][5] = -123f;
        // put a zero value in the test data
        denseTestData[10][10] = 0f;
        FloatDimensionlessMatrix dlm =
                new FloatDimensionlessMatrix(denseTestData, DimensionlessUnit.BASE.getStandardUnit(), StorageType.DENSE);
        verifyDimensionLessMatrix(denseTestData, new FloatFunction()
        {
            @Override
            public float apply(final float value)
            {
                return value;
            }
        }, dlm);
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.ABS, dlm.mutable().abs());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.ACOS, dlm.mutable().acos());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.ASIN, dlm.mutable().asin());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.ATAN, dlm.mutable().atan());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.CBRT, dlm.mutable().cbrt());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.CEIL, dlm.mutable().ceil());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.COS, dlm.mutable().cos());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.COSH, dlm.mutable().cosh());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.EXP, dlm.mutable().exp());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.EXPM1, dlm.mutable().expm1());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.FLOOR, dlm.mutable().floor());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.INV, dlm.mutable().inv());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.LOG, dlm.mutable().log());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.LOG10, dlm.mutable().log10());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.LOG1P, dlm.mutable().log1p());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.NEG, dlm.mutable().neg());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.RINT, dlm.mutable().rint());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.SIGNUM, dlm.mutable().signum());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.SIN, dlm.mutable().sin());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.SINH, dlm.mutable().sinh());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.SQRT, dlm.mutable().sqrt());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.TAN, dlm.mutable().tan());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.TANH, dlm.mutable().tanh());
        verifyDimensionLessMatrix(denseTestData, FloatMathFunctions.POW((float) Math.PI), dlm.mutable().pow(Math.PI));
    }

    /**
     * Verify the contents of a FloatDimensionlessVector.
     * @param reference the values on which the <code>operation</code> needs to be applied to get the values that must
     *            be verified
     * @param operation the operation that converts the <code>reference</code> values to the values that must be
     *            verified
     * @param got the values that must be verified
     */
    public static void verifyDimensionLessMatrix(final float[][] reference, final FloatFunction operation,
            final FloatDimensionlessMatrix got)
    {
        assertEquals(reference.length, got.rows(), "row count matches");
        assertEquals(reference[0].length, got.cols(), "column count matches");
        assertEquals(DimensionlessUnit.BASE.getStandardUnit(), got.getDisplayUnit().getStandardUnit(),
                "unit is DimensionLessUnit");
        for (int row = 0; row < reference.length; row++)
        {
            for (int col = 0; col < reference.length; col++)
            {
                float expect = operation.apply(reference[row][col]);
                double tolerance = Double.isNaN(expect) ? 0.1 : Math.abs(expect / 10000d);
                assertEquals(expect, got.getSI(row, col), tolerance, "value must match");
            }
        }
    }

}
