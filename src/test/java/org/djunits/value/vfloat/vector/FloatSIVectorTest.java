package org.djunits.value.vfloat.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.unit.AbsoluteLinearUnit;
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
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarAbs;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRelWithAbs;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorAbs;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRelWithAbs;
import org.djunits.value.vfloat.vector.base.FloatVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.junit.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class FloatSIVectorTest
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
     * @param <AU> the absolute unit type
     * @param <A> the absolute scalar type
     * @param <AV> the absolute vector type
     * @param <RU> the relative unit type
     * @param <R> the relative scalar type
     * @param <RV> the relative vector type
     */
    @SuppressWarnings("unchecked")
    @Test
    public <AU extends AbsoluteLinearUnit<AU, RU>, A extends AbstractFloatScalarAbs<AU, A, RU, R>,
            AV extends AbstractFloatVectorAbs<AU, A, AV, RU, R, RV>, RU extends Unit<RU>,
            R extends AbstractFloatScalarRelWithAbs<AU, A, RU, R>,
            RV extends AbstractFloatVectorRelWithAbs<AU, A, AV, RU, R, RV>> void testAsAll()
                    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException, ClassNotFoundException, UnitException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        float[] denseTestData = FLOATVECTOR.denseArray(50);
        FloatDimensionlessVector dimlessVector = FloatVector.instantiate(
                FloatVectorData.instantiate(denseTestData, DimensionlessUnit.SI.getScale(), StorageType.DENSE),
                DimensionlessUnit.SI);
        dimlessVector = dimlessVector.mutable().divide(dimlessVector).asDimensionless(); // unit vector
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<RU> quantity = (Quantity<RU>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (RU unit : quantity.getUnitsById().values())
            {
                AbstractFloatVectorRel<RU, R, RV> vector = (AbstractFloatVectorRel<RU, R, RV>) FloatVector
                        .instantiate(FloatVectorData.instantiate(denseTestData, unit.getScale(), StorageType.DENSE), unit);
                AbstractFloatVectorRel<RU, R, RV> sparseVector = vector.toSparse();
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals("Value at index matches", vector.getSI(index), sparseVector.getSI(index), 0.0);
                }

                FloatSIVector mult = vector.times(dimlessVector);
                Method asMethod = FloatSIVector.class.getDeclaredMethod("as" + type);
                AbstractFloatVectorRel<RU, R, RV> asVector = (AbstractFloatVectorRel<RU, R, RV>) asMethod.invoke(mult);
                assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), vector.getSI(index),
                            asVector.getSI(index), vector.getSI(index) / 1000.0);
                }

                Method asMethodDisplayUnit = FloatSIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                AbstractFloatVectorRel<RU, R, RV> asVectorDisplayUnit =
                        (AbstractFloatVectorRel<RU, R, RV>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());

                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), vector.getSI(index),
                            asVectorDisplayUnit.getSI(index), vector.getSI(index) / 1000.0);
                }

                // test exception for wrong 'as'
                FloatSIVector cd4sr2 = FloatSIVector.instantiate(denseTestData, SIUnit.of("cd4/sr2"), StorageType.DENSE);
                try
                {
                    AbstractFloatVectorRel<RU, R, RV> asVectorDim = (AbstractFloatVectorRel<RU, R, RV>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asVectorDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    AbstractFloatVectorRel<RU, R, RV> asVectorDim =
                            (AbstractFloatVectorRel<RU, R, RV>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asVectorDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }
            }
        }
        for (String type : CLASSNAMES.ABS_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<AU> quantity = (Quantity<AU>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (AU unit : quantity.getUnitsById().values())
            {
                AbstractFloatVectorAbs<AU, A, AV, RU, R, RV> vector = (AbstractFloatVectorAbs<AU, A, AV, RU, R, RV>) FloatVector
                        .instantiate(FloatVectorData.instantiate(denseTestData, unit.getScale(), StorageType.DENSE), unit);
                AbstractFloatVectorAbs<?, ?, ?, ?, ?, ?> sparseVector = vector.toSparse();
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals("Value at index matches", vector.getSI(index), sparseVector.getSI(index), 0.0);
                }
                Class<A> scalarClass = vector.getScalarClass();
                assertTrue("Scalar class is correct kind of vfloat.scalar class",
                        scalarClass.getName().equals("org.djunits.value.vfloat.scalar.Float" + type));
                float testValue = 123.45f;
                A scalarAbs = vector.instantiateScalarSI(testValue, unit);
                assertEquals("Scalar has correct value", testValue, scalarAbs.getSI(), 0.001);
                assertEquals("Scalar ahs correct displayUnit", unit, scalarAbs.getDisplayUnit());
                Quantity<RU> relativeQuantity = (Quantity<RU>) Quantities.INSTANCE
                        .getQuantity(CLASSNAMES.REL_WITH_ABS_LIST.get(CLASSNAMES.ABS_LIST.indexOf(type)) + "Unit");
                for (RU relativeUnit : relativeQuantity.getUnitsById().values())
                {
                    R scalarRel = vector.instantiateScalarRelSI(testValue, relativeUnit);
                    assertEquals("display unit of scalarRel matches", relativeUnit, scalarRel.getDisplayUnit());
                    assertEquals("value of scalarRel matches", testValue, scalarRel.getSI(), 0.001);
                }
            }
        }
    }

    /**
     * Test the methods that are only implemented in DimensionLess vectors.
     */
    @Test
    public void testDimensionLess()
    {
        float[] denseTestData = FLOATVECTOR.denseArray(50);
        // put at least one negative value in the test data
        denseTestData[5] = -123f;
        // put a zero value in the test data
        denseTestData[10] = 0f;
        FloatDimensionlessVector dlv =
                FloatVector.instantiate(denseTestData, DimensionlessUnit.BASE.getStandardUnit(), StorageType.DENSE);
        verifyDimensionLessVector(denseTestData, new FloatFunction()
        {
            @Override
            public float apply(final float value)
            {
                return value;
            }
        }, dlv);
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.ABS, dlv.mutable().abs());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.ACOS, dlv.mutable().acos());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.ASIN, dlv.mutable().asin());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.ATAN, dlv.mutable().atan());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.CBRT, dlv.mutable().cbrt());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.CEIL, dlv.mutable().ceil());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.COS, dlv.mutable().cos());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.COSH, dlv.mutable().cosh());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.EXP, dlv.mutable().exp());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.EXPM1, dlv.mutable().expm1());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.FLOOR, dlv.mutable().floor());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.INV, dlv.mutable().inv());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.LOG, dlv.mutable().log());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.LOG10, dlv.mutable().log10());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.LOG1P, dlv.mutable().log1p());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.NEG, dlv.mutable().neg());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.RINT, dlv.mutable().rint());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.SIGNUM, dlv.mutable().signum());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.SIN, dlv.mutable().sin());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.SINH, dlv.mutable().sinh());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.SQRT, dlv.mutable().sqrt());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.TAN, dlv.mutable().tan());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.TANH, dlv.mutable().tanh());
        verifyDimensionLessVector(denseTestData, FloatMathFunctions.POW((float) Math.PI), dlv.mutable().pow(Math.PI));
    }

    /**
     * Verify the contents of a FloatDimensionlessVector.
     * @param reference float[]; the values on which the <code>operation</code> needs to be applied to get the values that must
     *            be verified
     * @param operation FloatFunction; the operation that converts the <code>reference</code> values to the values that must be
     *            verified
     * @param got FloatDimensionlessVector; the values that must be verified
     */
    public static void verifyDimensionLessVector(final float[] reference, final FloatFunction operation,
            final FloatDimensionlessVector got)
    {
        assertEquals("item count matches", reference.length, got.size());
        assertEquals("unit is DimensionLessUnit", DimensionlessUnit.BASE.getStandardUnit(),
                got.getDisplayUnit().getStandardUnit());
        for (int index = 0; index < reference.length; index++)
        {
            float expect = operation.apply(reference[index]);
            float tolerance = Math.abs(expect / 10000f);
            assertEquals("value must match", expect, got.getSI(index), tolerance);
        }
    }

}
