package org.djunits.value.vdouble.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.scalar.base.DoubleScalarAbs;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRelWithAbs;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vdouble.vector.base.DoubleVectorAbs;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.base.DoubleVectorRelWithAbs;
import org.junit.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DoubleSIVectorTest
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
    public <AU extends AbsoluteLinearUnit<AU, RU>, A extends DoubleScalarAbs<AU, A, RU, R>,
            AV extends DoubleVectorAbs<AU, A, AV, RU, R, RV>, RU extends Unit<RU>,
            R extends DoubleScalarRelWithAbs<AU, A, RU, R>,
            RV extends DoubleVectorRelWithAbs<AU, A, AV, RU, R, RV>> void testAsAll()
                    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException, ClassNotFoundException, UnitException, InstantiationException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        double[] denseTestData = DOUBLEVECTOR.denseArray(50);
        DimensionlessVector dimlessVector = new DimensionlessVector(denseTestData, DimensionlessUnit.SI, StorageType.DENSE);
        dimlessVector = dimlessVector.mutable().divide(dimlessVector).asDimensionless(); // unit vector
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class<?> unitClass = Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<RU> quantity = (Quantity<RU>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (RU unit : quantity.getUnitsById().values())
            {
                Constructor<DoubleVectorRel<RU, R, RV>> constructor =
                        (Constructor<DoubleVectorRel<RU, R, RV>>) CLASSNAMES.doubleVectorClass(type)
                                .getConstructor(double[].class, unitClass, StorageType.class);
                DoubleVectorRel<RU, R, RV> vector = constructor.newInstance(denseTestData, unit, StorageType.DENSE);
                DoubleVectorRel<RU, R, RV> sparseVector = vector.toSparse();
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals("Value at index matches", vector.getSI(index), sparseVector.getSI(index), 0.0);
                }

                SIVector mult = vector.times(dimlessVector);
                Method asMethod = SIVector.class.getDeclaredMethod("as" + type);
                DoubleVectorRel<RU, R, RV> asVector = (DoubleVectorRel<RU, R, RV>) asMethod.invoke(mult);
                assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), vector.getSI(index),
                            asVector.getSI(index), vector.getSI(index) / 1000.0);
                }

                Method asMethodDisplayUnit = SIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                DoubleVectorRel<RU, R, RV> asVectorDisplayUnit =
                        (DoubleVectorRel<RU, R, RV>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());

                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), vector.getSI(index),
                            asVectorDisplayUnit.getSI(index), vector.getSI(index) / 1000.0);
                }

                // test exception for wrong 'as'
                SIVector cd4sr2 = new SIVector(denseTestData, SIUnit.of("cd4/sr2"), StorageType.DENSE);
                try
                {
                    DoubleVectorRel<RU, R, RV> asVectorDim =
                            (DoubleVectorRel<RU, R, RV>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asVectorDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    DoubleVectorRel<RU, R, RV> asVectorDim =
                            (DoubleVectorRel<RU, R, RV>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
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
                Constructor<AV> constructor = (Constructor<AV>) CLASSNAMES.doubleVectorClass(type)
                        .getConstructor(double[].class, unitClass, StorageType.class);
                AV vector = constructor.newInstance(denseTestData, unit, StorageType.DENSE);
                AV sparseVector = vector.toSparse();
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals("Value at index matches", vector.getSI(index), sparseVector.getSI(index), 0.0);
                }
                Class<A> scalarClass = vector.getScalarClass();
                assertTrue("Scalar class is correct kind of vdouble.scalar class",
                        scalarClass.getName().equals("org.djunits.value.vdouble.scalar." + type));
                double testValue = 123.45;
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
                // Indirectly test the instantiateVectorRel method (we don't have direct access to a DoubleVectorData object)
                // This was more difficult than it should be...
                RV relVector = vector.minus(vector);
                assertEquals(0.0, relVector.cardinality(), 0.0001);
            }
        }

        // just to see if Position and Length play nice for 'minus'
        PositionVector pv = new PositionVector(denseTestData, PositionUnit.METER);
        LengthVector lv = new LengthVector(denseTestData, LengthUnit.METER);
        PositionVector pdiff = pv.minus(lv);
        assertEquals(0.0, pdiff.cardinality(), 0.0001);
        LengthVector ldiff = pv.minus(pv);
        assertEquals(0.0, ldiff.cardinality(), 0.0001);
    }

    /**
     * Test the methods that are only implemented in DimensionLess vectors.
     */
    @Test
    public void testDimensionLess()
    {
        double[] denseTestData = DOUBLEVECTOR.denseArray(50);
        // put at least one negative value in the test data
        denseTestData[5] = -123d;
        // put a zero value in the test data
        denseTestData[10] = 0d;
        DimensionlessVector dlv =
                new DimensionlessVector(denseTestData, DimensionlessUnit.BASE.getStandardUnit(), StorageType.DENSE);
        verifyDimensionLessVector(denseTestData, new DoubleFunction()
        {
            @Override
            public double apply(final double value)
            {
                return value;
            }
        }, dlv);
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.ABS, dlv.mutable().abs());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.ACOS, dlv.mutable().acos());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.ASIN, dlv.mutable().asin());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.ATAN, dlv.mutable().atan());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.CBRT, dlv.mutable().cbrt());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.CEIL, dlv.mutable().ceil());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.COS, dlv.mutable().cos());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.COSH, dlv.mutable().cosh());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.EXP, dlv.mutable().exp());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.EXPM1, dlv.mutable().expm1());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.FLOOR, dlv.mutable().floor());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.INV, dlv.mutable().inv());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.LOG, dlv.mutable().log());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.LOG10, dlv.mutable().log10());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.LOG1P, dlv.mutable().log1p());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.NEG, dlv.mutable().neg());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.RINT, dlv.mutable().rint());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.SIGNUM, dlv.mutable().signum());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.SIN, dlv.mutable().sin());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.SINH, dlv.mutable().sinh());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.SQRT, dlv.mutable().sqrt());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.TAN, dlv.mutable().tan());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.TANH, dlv.mutable().tanh());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.POW((float) Math.PI), dlv.mutable().pow(Math.PI));
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

        double[] testValues = new double[] {0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0};
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            DimensionlessVector allVector = new DimensionlessVector(new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    DimensionlessUnit.SI.getStandardUnit(), storageType);
            for (String type : CLASSNAMES.REL_LIST)
            {
                Class<?> unitClass = Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                    {
                        Constructor<DoubleVector> constructor = (Constructor<DoubleVector>) CLASSNAMES
                                .doubleVectorClass(type).getConstructor(double[].class, unitClass, StorageType.class);
                        DoubleVectorRel vector = (DoubleVectorRel) constructor.newInstance(testValues, unit, storageType2);
                        SIVector mult = vector.times(allVector);
                        Method asMethod = SIVector.class.getDeclaredMethod("as" + type);
                        DoubleVectorRel<U, ?, ?> asVector = (DoubleVectorRel<U, ?, ?>) asMethod.invoke(mult);
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, mult.getValuesSI());

                        Method asMethodDisplayUnit = SIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                        DoubleVectorRel<U, ?, ?> asVectorDisplayUnit =
                                (DoubleVectorRel<U, ?, ?>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, asVectorDisplayUnit.getValuesSI());

                        // test exception for wrong 'as'
                        SIVector cd4sr2 = new SIVector(testValues, SIUnit.of("cd4/sr2"), storageType2);
                        try
                        {
                            DoubleScalarRel<?, ?> asScalarDim = (DoubleScalarRel<?, ?>) asMethod.invoke(cd4sr2);
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }

                        try
                        {
                            DoubleScalarRel<?, ?> asScalarDim =
                                    (DoubleScalarRel<?, ?>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
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

        double[] testValues = new double[] {0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0};
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            DoubleVectorRel<?, ?, ?> dimless = new DimensionlessVector(new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    DimensionlessUnit.SI.getStandardUnit(), storageType);
            for (String type : CLASSNAMES.REL_WITH_ABS_LIST)
            {
                Class<?> unitClass = Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                    {
                        Constructor<DoubleVectorRel> constructor = (Constructor<DoubleVectorRel>) CLASSNAMES
                                .doubleVectorClass(type).getConstructor(double[].class, unitClass, StorageType.class);
                        DoubleVectorRel vector = constructor.newInstance(testValues, unit, storageType2);
                        SIVector mult = vector.times(dimless);
                        Method asMethod = SIVector.class.getDeclaredMethod("as" + type);
                        DoubleVectorRel<U, ?, ?> asVector = (DoubleVectorRel<U, ?, ?>) asMethod.invoke(mult);
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, mult.getValuesSI());

                        Method asMethodDisplayUnit = SIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                        DoubleVectorRel<U, ?, ?> asVectorDisplayUnit =
                                (DoubleVectorRel<U, ?, ?>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, asVectorDisplayUnit.getValuesSI());

                        // test exception for wrong 'as'
                        SIVector cd4sr2 = new SIVector(testValues, SIUnit.of("cd4/sr2"), storageType2);
                        try
                        {
                            DoubleScalarRel<?, ?> asScalarDim = (DoubleScalarRel<?, ?>) asMethod.invoke(cd4sr2);
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }

                        try
                        {
                            DoubleScalarRel<?, ?> asScalarDim =
                                    (DoubleScalarRel<?, ?>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
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
     * Verify the contents of a FloatDimensionlessVector.
     * @param reference double[]; the values on which the <code>operation</code> needs to be applied to get the values that must
     *            be verified
     * @param operation FloatFunction; the operation that converts the <code>reference</code> values to the values that must be
     *            verified
     * @param got FloatDimensionlessVector; the values that must be verified
     */
    public static void verifyDimensionLessVector(final double[] reference, final DoubleFunction operation,
            final DimensionlessVector got)
    {
        assertEquals("item count matches", reference.length, got.size());
        assertEquals("unit is DimensionLessUnit", DimensionlessUnit.BASE.getStandardUnit(),
                got.getDisplayUnit().getStandardUnit());
        for (int index = 0; index < reference.length; index++)
        {
            double expect = operation.apply(reference[index]);
            double tolerance = Math.abs(expect / 10000d);
            assertEquals("value must match", expect, got.getSI(index), tolerance);
        }
    }

    /**
     * Compare two double arrays with factor and offset (derived from a scale).
     * @param scale Scale; the scale
     * @param reference double[]; the reference values
     * @param got double[] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final double[] reference, final double[] got)
    {
        assertEquals("length of reference must equal length of result ", reference.length, got.length);
        if (scale instanceof GradeScale)
        {
            return; // too difficult; for now
        }
        double offset = scale.toStandardUnit(0);
        double factor = scale.toStandardUnit(1) - offset;
        for (int i = 0; i < reference.length; i++)
        {
            assertEquals("value at index " + i + " must match", reference[i] * factor + offset, got[i], 0.001);
        }
    }
}
