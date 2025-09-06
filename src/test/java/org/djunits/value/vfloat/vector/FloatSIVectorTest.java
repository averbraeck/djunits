package org.djunits.value.vfloat.vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.quantity.Quantities;
import org.djunits.quantity.Quantity;
import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.scalar.base.FloatScalarAbs;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalarRelWithAbs;
import org.djunits.value.vfloat.vector.base.FloatVectorAbs;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.base.FloatVectorRelWithAbs;
import org.junit.jupiter.api.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
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
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public <AU extends AbsoluteLinearUnit<AU, RU>, A extends FloatScalarAbs<AU, A, RU, R>,
            AV extends FloatVectorAbs<AU, A, AV, RU, R, RV>, RU extends Unit<RU>, R extends FloatScalarRelWithAbs<AU, A, RU, R>,
            RV extends FloatVectorRelWithAbs<AU, A, AV, RU, R, RV>> void testAsAll()
                    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException, ClassNotFoundException, UnitException, InstantiationException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        float[] denseTestData = FLOATVECTOR.denseArray(50);
        FloatDimensionlessVector dimlessVector =
                new FloatDimensionlessVector(denseTestData, DimensionlessUnit.SI, StorageType.DENSE);
        dimlessVector = dimlessVector.mutable().divide(dimlessVector).asDimensionless(); // unit vector
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class<?> unitClass = Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<RU> quantity = (Quantity<RU>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (RU unit : quantity.getUnitsById().values())
            {
                Constructor<FloatVectorRel<RU, R, RV>> constructor = (Constructor<FloatVectorRel<RU, R, RV>>) CLASSNAMES
                        .floatVectorClass(type).getConstructor(float[].class, unitClass, StorageType.class);
                FloatVectorRel<RU, R, RV> vector = constructor.newInstance(denseTestData, unit, StorageType.DENSE);
                FloatVectorRel<RU, R, RV> sparseVector = vector.toSparse();
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(vector.getSI(index), sparseVector.getSI(index), 0.0, "Value at index matches");
                }

                FloatSIVector mult = vector.times(dimlessVector);
                Method asMethod = FloatSIVector.class.getDeclaredMethod("as" + type);
                FloatVectorRel<RU, R, RV> asVector = (FloatVectorRel<RU, R, RV>) asMethod.invoke(mult);
                assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(vector.getSI(index), asVector.getSI(index), vector.getSI(index) / 1000.0,
                            type + ", unit: " + unit.getDefaultTextualAbbreviation());
                }

                Method asMethodDisplayUnit = FloatSIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                FloatVectorRel<RU, R, RV> asVectorDisplayUnit =
                        (FloatVectorRel<RU, R, RV>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());

                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(vector.getSI(index), asVectorDisplayUnit.getSI(index), vector.getSI(index) / 1000.0,
                            type + ", unit: " + unit.getDefaultTextualAbbreviation());
                }

                // test exception for wrong 'as'
                FloatSIVector cd4sr2 = new FloatSIVector(denseTestData, SIUnit.of("cd4/sr2"), StorageType.DENSE);
                try
                {
                    FloatVectorRel<RU, R, RV> asVectorDim = (FloatVectorRel<RU, R, RV>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asVectorDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    FloatVectorRel<RU, R, RV> asVectorDim =
                            (FloatVectorRel<RU, R, RV>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
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
            Class<?> unitClass = Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<AU> quantity = (Quantity<AU>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (AU unit : quantity.getUnitsById().values())
            {
                Constructor<AV> constructor = (Constructor<AV>) CLASSNAMES.floatVectorClass(type).getConstructor(float[].class,
                        unitClass, StorageType.class);
                AV vector = constructor.newInstance(denseTestData, unit, StorageType.DENSE);
                AV sparseVector = vector.toSparse();
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(vector.getSI(index), sparseVector.getSI(index), 0.0, "Value at index matches");
                }
                Class<A> scalarClass = vector.getScalarClass();
                assertTrue(scalarClass.getName().equals("org.djunits.value.vfloat.scalar.Float" + type),
                        "Scalar class is correct kind of vfloat.scalar class");
                float testValue = 123.45f;
                A scalarAbs = vector.instantiateScalarSI(testValue, unit);
                assertEquals(testValue, scalarAbs.getSI(), 0.001, "Scalar has correct value");
                assertEquals(unit, scalarAbs.getDisplayUnit(), "Scalar ahs correct displayUnit");
                Quantity<RU> relativeQuantity = (Quantity<RU>) Quantities.INSTANCE
                        .getQuantity(CLASSNAMES.REL_WITH_ABS_LIST.get(CLASSNAMES.ABS_LIST.indexOf(type)) + "Unit");
                for (RU relativeUnit : relativeQuantity.getUnitsById().values())
                {
                    R scalarRel = vector.instantiateScalarRelSI(testValue, relativeUnit);
                    assertEquals(relativeUnit, scalarRel.getDisplayUnit(), "display unit of scalarRel matches");
                    assertEquals(testValue, scalarRel.getSI(), 0.001, "value of scalarRel matches");
                }
                // Indirectly test the instantiateVectorRel method (we don't have direct access to a FloatVectorData object)
                // This was more difficult than it should be...
                RV relVector = vector.minus(vector);
                assertEquals(0.0, relVector.cardinality(), 0.0001);
            }
        }

        // just to see if Position and Length play nice for 'minus'
        FloatPositionVector pv = new FloatPositionVector(denseTestData, PositionUnit.METER);
        FloatLengthVector lv = new FloatLengthVector(denseTestData, LengthUnit.METER);
        FloatPositionVector pdiff = pv.minus(lv);
        assertEquals(0.0, pdiff.cardinality(), 0.0001);
        FloatLengthVector ldiff = pv.minus(pv);
        assertEquals(0.0, ldiff.cardinality(), 0.0001);
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
                new FloatDimensionlessVector(denseTestData, DimensionlessUnit.BASE.getStandardUnit(), StorageType.DENSE);
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
     * Test most "asXX" methods.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     * @param <U> the unit type
     * @throws InstantiationException on error
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public <U extends Unit<U>> void testAsMost() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, ClassNotFoundException, UnitException, InstantiationException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        float[] testValues = new float[] {0, 123.456f, 0, -273.15f, -273.15f, 0, -273.15f, 234.567f, 0, 0};
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            FloatDimensionlessVector allVector = new FloatDimensionlessVector(new float[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    DimensionlessUnit.SI.getStandardUnit(), storageType);
            for (String type : CLASSNAMES.REL_LIST)
            {
                Class<?> unitClass = Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                    {
                        Constructor<?> constructor =
                                CLASSNAMES.floatVectorClass(type).getConstructor(float[].class, unitClass, StorageType.class);
                        FloatVectorRel vector = (FloatVectorRel) constructor.newInstance(testValues, unit, storageType2);
                        FloatSIVector mult = vector.times(allVector);
                        Method asMethod = FloatSIVector.class.getDeclaredMethod("as" + type);
                        FloatVectorRel<U, ?, ?> asVector = (FloatVectorRel<U, ?, ?>) asMethod.invoke(mult);
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, mult.getValuesSI());

                        Method asMethodDisplayUnit = FloatSIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                        FloatVectorRel<U, ?, ?> asVectorDisplayUnit =
                                (FloatVectorRel<U, ?, ?>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, asVectorDisplayUnit.getValuesSI());

                        // test exception for wrong 'as'
                        FloatSIVector cd4sr2 = new FloatSIVector(testValues, SIUnit.of("cd4/sr2"), storageType2);
                        try
                        {
                            FloatScalarRel<?, ?> asScalarDim = (FloatScalarRel<?, ?>) asMethod.invoke(cd4sr2);
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }

                        try
                        {
                            FloatScalarRel<?, ?> asScalarDim =
                                    (FloatScalarRel<?, ?>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }
                    }
                }
            }
        }
    }

    /**
     * Test the "asXX" methods of the remaining classes.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     * @param <U> the unit type
     * @throws InstantiationException on error
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public <U extends Unit<U>> void testAsRemaining() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, ClassNotFoundException, UnitException, InstantiationException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        float[] testValues = new float[] {0, 123.456f, 0, -273.15f, -273.15f, 0, -273.15f, 234.567f, 0, 0};
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            FloatVectorRel<?, ?, ?> dimless = new FloatDimensionlessVector(new float[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    DimensionlessUnit.SI.getStandardUnit(), storageType);
            for (String type : CLASSNAMES.REL_WITH_ABS_LIST)
            {
                Class<?> unitClass = Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                    {
                        Constructor<?> constructor =
                                CLASSNAMES.floatVectorClass(type).getConstructor(float[].class, unitClass, StorageType.class);
                        FloatVectorRel vector = (FloatVectorRel) constructor.newInstance(testValues, unit, storageType2);
                        FloatSIVector mult = vector.times(dimless);
                        Method asMethod = FloatSIVector.class.getDeclaredMethod("as" + type);
                        FloatVectorRel<U, ?, ?> asVector = (FloatVectorRel<U, ?, ?>) asMethod.invoke(mult);
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, mult.getValuesSI());

                        Method asMethodDisplayUnit = FloatSIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                        FloatVectorRel<U, ?, ?> asVectorDisplayUnit =
                                (FloatVectorRel<U, ?, ?>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, asVectorDisplayUnit.getValuesSI());

                        // test exception for wrong 'as'
                        FloatSIVector cd4sr2 = new FloatSIVector(testValues, SIUnit.of("cd4/sr2"), storageType2);
                        try
                        {
                            FloatScalarRel<?, ?> asScalarDim = (FloatScalarRel<?, ?>) asMethod.invoke(cd4sr2);
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }

                        try
                        {
                            FloatScalarRel<?, ?> asScalarDim =
                                    (FloatScalarRel<?, ?>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }
                    }
                }
            }
        }
    }

    /**
     * Verify the contents of a FloatFloatDimensionlessVector.
     * @param reference the values on which the <code>operation</code> needs to be applied to get the values that must be
     *            verified
     * @param operation the operation that converts the <code>reference</code> values to the values that must be verified
     * @param got the values that must be verified
     */
    public static void verifyDimensionLessVector(final float[] reference, final FloatFunction operation,
            final FloatDimensionlessVector got)
    {
        assertEquals(reference.length, got.size(), "item count matches");
        assertEquals(DimensionlessUnit.BASE.getStandardUnit(), got.getDisplayUnit().getStandardUnit(),
                "unit is DimensionLessUnit");
        for (int index = 0; index < reference.length; index++)
        {
            float expect = operation.apply(reference[index]);
            double tolerance = Double.isNaN(expect) ? 0.1 : Math.abs(expect / 10000d);
            assertEquals(expect, got.getSI(index), tolerance, "value must match");
        }
    }

    /**
     * Compare two float arrays with factor and offset (derived from a scale).
     * @param scale the scale
     * @param reference the reference values
     * @param got float[] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final float[] reference, final float[] got)
    {
        assertEquals(reference.length, got.length, "length of reference must equal length of result ");
        if (scale instanceof GradeScale)
        {
            return; // too difficult; for now
        }
        double offset = scale.toStandardUnit(0);
        double factor = scale.toStandardUnit(1) - offset;
        for (int i = 0; i < reference.length; i++)
        {
            double expected = reference[i] * factor + offset;
            double tolerance = Math.abs(expected / 10000f);
            if (Math.abs(got[i]) < 1E-30f && Math.abs(expected) < 1E-30f)
                continue;
            if (Math.abs(got[i]) > 1E30f && Math.abs(expected) > 1E30f)
                continue;
            assertEquals(expected, got[i], tolerance, "value at index " + i + " must match");
        }
    }
}
