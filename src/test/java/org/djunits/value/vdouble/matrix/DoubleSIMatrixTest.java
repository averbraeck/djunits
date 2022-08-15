package org.djunits.value.vdouble.matrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.quantity.Quantities;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.junit.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DoubleSIMatrixTest
{

    /**
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
    @SuppressWarnings("unchecked")
    @Test
    public <U extends Unit<U>, S extends AbstractDoubleScalarRel<U, S>, V extends AbstractDoubleVectorRel<U, S, V>,
            M extends AbstractDoubleMatrixRel<U, S, V, M>> void testAsAll()
                    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException, ClassNotFoundException, UnitException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(5, 10);
        DimensionlessMatrix dimlessMatrix = DoubleMatrix.instantiate(
                DoubleMatrixData.instantiate(denseTestData, DimensionlessUnit.SI.getScale(), StorageType.DENSE),
                DimensionlessUnit.SI);
        dimlessMatrix = dimlessMatrix.mutable().divide(dimlessMatrix).asDimensionless(); // unit matrix
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (U unit : quantity.getUnitsById().values())
            {
                AbstractDoubleMatrixRel<U, S, V, M> matrix = (AbstractDoubleMatrixRel<U, S, V, M>) DoubleMatrix
                        .instantiate(DoubleMatrixData.instantiate(denseTestData, unit.getScale(), StorageType.DENSE), unit);
                SIMatrix mult = matrix.times(dimlessMatrix);
                Method asMethod = SIMatrix.class.getDeclaredMethod("as" + type);
                AbstractDoubleMatrixRel<U, S, V, M> asMatrix = (AbstractDoubleMatrixRel<U, S, V, M>) asMethod.invoke(mult);
                assertEquals(matrix.getDisplayUnit().getStandardUnit(), asMatrix.getDisplayUnit());
                for (int row = 0; row < denseTestData.length; row++)
                {
                    for (int col = 0; col < denseTestData[0].length; col++)
                    {
                        assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), matrix.getSI(row, col),
                                asMatrix.getSI(row, col), matrix.getSI(row, col) / 1000.0);
                    }
                }

                Method asMethodDisplayUnit = SIMatrix.class.getDeclaredMethod("as" + type, unit.getClass());
                AbstractDoubleMatrixRel<U, S, V, M> asMatrixDisplayUnit =
                        (AbstractDoubleMatrixRel<U, S, V, M>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
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
                SIMatrix cd4sr2 = SIMatrix.instantiate(denseTestData, SIUnit.of("cd4/sr2"), StorageType.DENSE);
                try
                {
                    AbstractDoubleMatrixRel<U, S, V, M> asMatrixDim =
                            (AbstractDoubleMatrixRel<U, S, V, M>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asMatrixDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    AbstractDoubleMatrixRel<U, S, V, M> asMatrixDim =
                            (AbstractDoubleMatrixRel<U, S, V, M>) asMethodDisplayUnit.invoke(cd4sr2, matrix.getDisplayUnit());
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asMatrixDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }
                SIMatrix sim =
                        SIMatrix.of(denseTestData, quantity.getSiDimensions().toString(true, true, true), StorageType.DENSE);
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

    /**
     * Test the methods that are only implemented in DimensionLess matrices.
     */
    @Test
    public void testDimensionLess()
    {
        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(12, 23);
        // put at least one negative value in the test data
        denseTestData[5][5] = -123d;
        // put a zero value in the test data
        denseTestData[10][10] = 0d;
        DimensionlessMatrix dlm =
                DoubleMatrix.instantiate(denseTestData, DimensionlessUnit.BASE.getStandardUnit(), StorageType.DENSE);
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
