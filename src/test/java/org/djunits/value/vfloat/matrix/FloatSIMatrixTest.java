package org.djunits.value.vfloat.matrix;

import static org.junit.Assert.assertEquals;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.junit.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class FloatSIMatrixTest
{

    /*-
     * Test all "asXX" methods.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     * @param <U> the unit type
     * @param <S> the scalar type
     * @param <V> the vector type
     * @param <M> the matrix type
     */
    /*- TODO: replace with new constructors
    @SuppressWarnings("unchecked")
    @Test
    public <U extends Unit<U>, S extends FloatScalarRel<U, S>, V extends FloatVectorRel<U, S, V>,
            M extends FloatMatrixRel<U, S, V, M>> void testAsAll()
                    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException, ClassNotFoundException, UnitException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());
    
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(5, 10);
        FloatDimensionlessMatrix dimlessMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, DimensionlessUnit.SI.getScale(), StorageType.DENSE),
                DimensionlessUnit.SI);
        dimlessMatrix = dimlessMatrix.mutable().divide(dimlessMatrix).asDimensionless(); // unit matrix
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (U unit : quantity.getUnitsById().values())
            {
                FloatMatrixRel<U, S, V, M> matrix = (FloatMatrixRel<U, S, V, M>) FloatMatrix
                        .instantiate(FloatMatrixData.instantiate(denseTestData, unit.getScale(), StorageType.DENSE), unit);
                FloatSIMatrix mult = matrix.times(dimlessMatrix);
                Method asMethod = FloatSIMatrix.class.getDeclaredMethod("as" + type);
                FloatMatrixRel<U, S, V, M> asMatrix = (FloatMatrixRel<U, S, V, M>) asMethod.invoke(mult);
                assertEquals(matrix.getDisplayUnit().getStandardUnit(), asMatrix.getDisplayUnit());
                for (int row = 0; row < denseTestData.length; row++)
                {
                    for (int col = 0; col < denseTestData[0].length; col++)
                    {
                        assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), matrix.getSI(row, col),
                                asMatrix.getSI(row, col), matrix.getSI(row, col) / 1000.0);
                    }
                }
    
                Method asMethodDisplayUnit = FloatSIMatrix.class.getDeclaredMethod("as" + type, unit.getClass());
                FloatMatrixRel<U, S, V, M> asMatrixDisplayUnit =
                        (FloatMatrixRel<U, S, V, M>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                assertEquals(matrix.getDisplayUnit().getStandardUnit(), asMatrixDisplayUnit.getDisplayUnit());
    
                for (int row = 0; row < denseTestData.length; row++)
                {
                    for (int col = 0; col < denseTestData[0].length; col++)
                    {
                        assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), matrix.getSI(row, col),
                                asMatrixDisplayUnit.getSI(row, col), matrix.getSI(row, col) / 1000.0);
                    }
                }
    
                // test exception for wrong 'as'
                FloatSIMatrix cd4sr2 = FloatSIMatrix.instantiate(denseTestData, SIUnit.of("cd4/sr2"), StorageType.DENSE);
                try
                {
                    FloatMatrixRel<U, S, V, M> asMatrixDim =
                            (FloatMatrixRel<U, S, V, M>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asMatrixDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }
    
                try
                {
                    FloatMatrixRel<U, S, V, M> asMatrixDim =
                            (FloatMatrixRel<U, S, V, M>) asMethodDisplayUnit.invoke(cd4sr2, matrix.getDisplayUnit());
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asMatrixDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }
                FloatSIMatrix sim = FloatSIMatrix.of(denseTestData, quantity.getSiDimensions().toString(true, true, true),
                        StorageType.DENSE);
                for (int row = 0; row < denseTestData.length; row++)
                {
                    for (int col = 0; col < denseTestData[0].length; col++)
                    {
                        assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), denseTestData[row][col],
                                sim.getInUnit(row, col), 0.001);
                    }
                }
    
            }
        }
    }
    */

    /**
     * Test the methods that are only implemented in DimensionLess matrices.
     */
    @Test
    public void testDimensionLess()
    {
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(12, 23);
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
     * @param reference float[]; the values on which the <code>operation</code> needs to be applied to get the values that must
     *            be verified
     * @param operation FloatFunction; the operation that converts the <code>reference</code> values to the values that must be
     *            verified
     * @param got DimensionlessMatrix; the values that must be verified
     */
    public static void verifyDimensionLessMatrix(final float[][] reference, final FloatFunction operation,
            final FloatDimensionlessMatrix got)
    {
        assertEquals("row count matches", reference.length, got.rows());
        assertEquals("column count matches", reference[0].length, got.cols());
        assertEquals("unit is DimensionLessUnit", DimensionlessUnit.BASE.getStandardUnit(),
                got.getDisplayUnit().getStandardUnit());
        for (int row = 0; row < reference.length; row++)
        {
            for (int col = 0; col < reference.length; col++)
            {
                float expect = operation.apply(reference[row][col]);
                double tolerance = Math.abs(expect / 10000d);
                assertEquals("value must match", expect, got.getSI(row, col), tolerance);
            }
        }
    }

}
