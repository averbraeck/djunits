package org.djunits.value.vdouble.matrix;

import static org.junit.Assert.assertEquals;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.junit.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DoubleDimensionlessMatrixTest
{

    /**
     * Test the methods that are only implemented in DimensionLess matrices.
     */
    @Test
    public void testDimensionLess()
    {
        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(12, 23, false);
        // put at least one negative value in the test data
        denseTestData[5][5] = -123d;
        // put a zero value in the test data
        denseTestData[10][10] = 0d;
        DimensionlessMatrix dlm =
                new DimensionlessMatrix(denseTestData, DimensionlessUnit.BASE.getStandardUnit(), StorageType.DENSE);
        verifyDimensionLessMatrix(denseTestData, new DoubleFunction()
        {
            @Override
            public double apply(final double value)
            {
                return value;
            }
        }, dlm);
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.ABS, dlm.mutable().abs());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.ACOS, dlm.mutable().acos());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.ASIN, dlm.mutable().asin());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.ATAN, dlm.mutable().atan());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.CBRT, dlm.mutable().cbrt());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.CEIL, dlm.mutable().ceil());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.COS, dlm.mutable().cos());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.COSH, dlm.mutable().cosh());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.EXP, dlm.mutable().exp());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.EXPM1, dlm.mutable().expm1());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.FLOOR, dlm.mutable().floor());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.INV, dlm.mutable().inv());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.LOG, dlm.mutable().log());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.LOG10, dlm.mutable().log10());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.LOG1P, dlm.mutable().log1p());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.NEG, dlm.mutable().neg());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.RINT, dlm.mutable().rint());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.SIGNUM, dlm.mutable().signum());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.SIN, dlm.mutable().sin());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.SINH, dlm.mutable().sinh());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.SQRT, dlm.mutable().sqrt());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.TAN, dlm.mutable().tan());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.TANH, dlm.mutable().tanh());
        verifyDimensionLessMatrix(denseTestData, DoubleMathFunctions.POW((float) Math.PI), dlm.mutable().pow(Math.PI));
    }

    /**
     * Verify the contents of a FloatDimensionlessVector.
     * @param reference double[]; the values on which the <code>operation</code> needs to be applied to get the values that must
     *            be verified
     * @param operation FloatFunction; the operation that converts the <code>reference</code> values to the values that must be
     *            verified
     * @param got DimensionlessMatrix; the values that must be verified
     */
    public static void verifyDimensionLessMatrix(final double[][] reference, final DoubleFunction operation,
            final DimensionlessMatrix got)
    {
        assertEquals("row count matches", reference.length, got.rows());
        assertEquals("column count matches", reference[0].length, got.cols());
        assertEquals("unit is DimensionLessUnit", DimensionlessUnit.BASE.getStandardUnit(),
                got.getDisplayUnit().getStandardUnit());
        for (int row = 0; row < reference.length; row++)
        {
            for (int col = 0; col < reference.length; col++)
            {
                double expect = operation.apply(reference[row][col]);
                double tolerance = Math.abs(expect / 10000d);
                assertEquals("value must match", expect, got.getSI(row, col), tolerance);
            }
        }
    }

}
