package org.djunits.value.vfloat.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.djunits.quantity.Quantities;
import org.djunits.quantity.Quantity;
import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.Relative;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.base.FloatVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.djutils.exceptions.Try;
import org.junit.Test;

/**
 * Test constructors of FloatVector.
 */
public class FloatVectorConstructorsTest
{
    /**
     * test float[] constructors
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testFloatConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String scalarName : CLASSNAMES.ALL_NODIM_LIST)
        {
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(scalarName + "Unit");
            Unit<?> standardUnit = quantity.getStandardUnit();
            Class<?> unitClass = standardUnit.getClass();
            float[] testValues = new float[] {0, 123.456f, 0, 0, 234.567f, 0, 0};

            int cardinality = 0;
            float zSum = 0.0f;
            for (int index = 0; index < testValues.length; index++)
            {
                float value = testValues[index];
                if (0.0 != value)
                {
                    cardinality++;
                    zSum += value;
                }
            }

            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                // get the constructors
                Constructor<FloatVector<?, ?, ?>> constructorDUS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(float[].class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorDU =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(float[].class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorDS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(float[].class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorD = (Constructor<
                        FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName).getConstructor(float[].class);

                // initialize vectors
                FloatVector<?, ?, ?> vDUS = constructorDUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vDUS.getStorageType());
                FloatVector<?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vDU.getStorageType());
                FloatVector<?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vDS.getStorageType());
                FloatVector<?, ?, ?> vD = constructorD.newInstance(testValues);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vD.getStorageType());

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vDUS, vDU, vDS, vD})
                {
                    compareValuesWithScale(standardUnit.getScale(), testValues, floatVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatVector.cardinality());
                    if (floatVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractFloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue("mutable float vector is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(testValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(testValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(testValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float vector should be immutable", ValueRuntimeException.class);
                    mutable = floatVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(testValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(testValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals("neg", -testValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        AbstractFloatScalar<?, ?> s = (AbstractFloatScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), testValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }
            }
        }
    }

    /**
     * test Scalar[] constructors
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testScalarConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String scalarName : CLASSNAMES.ALL_NODIM_LIST)
        {
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(scalarName + "Unit");
            Unit<?> standardUnit = quantity.getStandardUnit();
            Class<?> unitClass = standardUnit.getClass();
            float[] floatValues = new float[] {0, 123.456f, 0, 0, 234.567f, 0, 0};
            Class<?> scalarClass = CLASSNAMES.floatScalarClass(scalarName);
            Object[] testValues = (Object[]) Array.newInstance(scalarClass, floatValues.length);
            Class<?> scalarArrayClass = testValues.getClass();
            Constructor<FloatScalar<?, ?>> constructorScalar =
                    (Constructor<FloatScalar<?, ?>>) scalarClass.getConstructor(float.class, unitClass);

            int cardinality = 0;
            float zSum = 0.0f;
            for (int index = 0; index < floatValues.length; index++)
            {
                float value = floatValues[index];
                Array.set(testValues, index, constructorScalar.newInstance(value, standardUnit));
                if (0.0 != value)
                {
                    cardinality++;
                    zSum += value;
                }
            }

            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                // get the constructors
                Constructor<FloatVector<?, ?, ?>> constructorLUS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(scalarArrayClass, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorLU =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(scalarArrayClass, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorLS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(scalarArrayClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorL =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(scalarArrayClass);

                // initialize vectors
                FloatVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                FloatVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                FloatVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                FloatVector<?, ?, ?> vL = constructorL.newInstance(new Object[] {testValues});
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatVector.cardinality());
                    if (floatVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractFloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001);
                    }
                    assertEquals("scalarClass must match", scalarClass, floatVector.getScalarClass());
                    Method instantiateMethod =
                            floatVector.getClass().getDeclaredMethod("instantiateVector", FloatVectorData.class, unitClass);
                    FloatVector<?, ?, ?> vData = (FloatVector<?, ?, ?>) instantiateMethod.invoke(floatVector,
                            FloatVectorData.instantiate(floatValues, IdentityScale.SCALE, storageType), standardUnit);
                    compareValuesWithScale(standardUnit.getScale(), floatValues, vData.getValuesSI());

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue("mutable float vector is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float vector should be immutable", ValueRuntimeException.class);
                    mutable = floatVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("neg", -floatValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        AbstractFloatScalar<?, ?> s = (AbstractFloatScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), floatValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }
            }
        }
    }

    /**
     * test List<Float> constructors
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testListFloatConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String scalarName : CLASSNAMES.ALL_NODIM_LIST)
        {
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(scalarName + "Unit");
            Unit<?> standardUnit = quantity.getStandardUnit();
            Class<?> unitClass = standardUnit.getClass();
            float[] floatValues = new float[] {0, 123.456f, 0, 0, 234.567f, 0, 0};
            List<Float> testValues = new ArrayList<>();

            int cardinality = 0;
            float zSum = 0.0f;
            for (int index = 0; index < floatValues.length; index++)
            {
                float value = floatValues[index];
                testValues.add(value);
                if (0.0 != value)
                {
                    cardinality++;
                    zSum += value;
                }
            }

            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                // get the constructors
                Constructor<FloatVector<?, ?, ?>> constructorLUS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(List.class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorLU =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(List.class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorLS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(List.class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorL = (Constructor<
                        FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName).getConstructor(List.class);

                // initialize vectors
                FloatVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                FloatVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                FloatVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                FloatVector<?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatVector.cardinality());
                    if (floatVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractFloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue("mutable float vector is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float vector should be immutable", ValueRuntimeException.class);
                    mutable = floatVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("neg", -floatValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        AbstractFloatScalar<?, ?> s = (AbstractFloatScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), floatValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty list
                vLUS = constructorLUS.newInstance(new ArrayList<Float>(), standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLUS.cardinality());
                if (vLUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001);
                }

                vLU = constructorLU.newInstance(new ArrayList<Float>(), standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                assertEquals("Unit must match", standardUnit, vLU.getDisplayUnit());
                assertEquals("Cardinality", 0, vLU.cardinality());
                if (vLU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vLU).zSum().getSI(), 0.001);
                }

                vLS = constructorLS.newInstance(new ArrayList<Float>(), storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLS.cardinality());
                if (vLS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vLS).zSum().getSI(), 0.001);
                }

                vL = constructorL.newInstance(new ArrayList<Float>());
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());
                assertEquals("Unit must match", standardUnit, vL.getDisplayUnit());
                assertEquals("Cardinality", 0, vL.cardinality());
                if (vL instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vL).zSum().getSI(), 0.001);
                }
            }
        }
    }

    /**
     * test List<Scalar> constructors
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testListScalarConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String scalarName : CLASSNAMES.ALL_NODIM_LIST)
        {
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(scalarName + "Unit");
            Unit<?> standardUnit = quantity.getStandardUnit();
            Class<?> unitClass = standardUnit.getClass();
            float[] floatValues = new float[] {0, 123.456f, 0, 0, 234.567f, 0, 0};
            List<FloatScalar<?, ?>> testValues = new ArrayList<>();
            Constructor<FloatScalar<?, ?>> constructorScalar = (Constructor<FloatScalar<?, ?>>) CLASSNAMES
                    .floatScalarClass(scalarName).getConstructor(float.class, unitClass);

            int cardinality = 0;
            float zSum = 0.0f;
            for (int index = 0; index < floatValues.length; index++)
            {
                float value = floatValues[index];
                testValues.add(constructorScalar.newInstance(value, standardUnit));
                if (0.0 != value)
                {
                    cardinality++;
                    zSum += value;
                }
            }

            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                // get the constructors
                Constructor<FloatVector<?, ?, ?>> constructorLUS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(List.class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorLU =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(List.class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorLS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(List.class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorL = (Constructor<
                        FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName).getConstructor(List.class);

                // initialize vectors
                FloatVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                FloatVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                FloatVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                FloatVector<?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatVector.cardinality());
                    if (floatVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractFloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue("mutable float vector is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float vector should be immutable", ValueRuntimeException.class);
                    mutable = floatVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("neg", -floatValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        AbstractFloatScalar<?, ?> s = (AbstractFloatScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), floatValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty list
                vLUS = constructorLUS.newInstance(new ArrayList<FloatScalar<?, ?>>(), standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLUS.cardinality());
                if (vLUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001);
                }

                vLU = constructorLU.newInstance(new ArrayList<FloatScalar<?, ?>>(), standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                assertEquals("Unit must match", standardUnit, vLU.getDisplayUnit());
                assertEquals("Cardinality", 0, vLU.cardinality());
                if (vLU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vLU).zSum().getSI(), 0.001);
                }

                vLS = constructorLS.newInstance(new ArrayList<FloatScalar<?, ?>>(), storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLS.cardinality());
                if (vLS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vLS).zSum().getSI(), 0.001);
                }

                vL = constructorL.newInstance(new ArrayList<FloatScalar<?, ?>>());
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());
                assertEquals("Unit must match", standardUnit, vL.getDisplayUnit());
                assertEquals("Cardinality", 0, vL.cardinality());
                if (vL instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vL).zSum().getSI(), 0.001);
                }
            }
        }
    }

    /**
     * test Map<Integer, Float> constructors
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testMapFloatConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String scalarName : CLASSNAMES.ALL_NODIM_LIST)
        {
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(scalarName + "Unit");
            Unit<?> standardUnit = quantity.getStandardUnit();
            Class<?> unitClass = standardUnit.getClass();
            float[] floatValues = new float[] {0, 123.456f, 0, 0, 234.567f, 0, 0};
            float[] allValues = new float[] {0, 123.456f, 0, 0, 234.567f, 0, 0, 0, 0, 0};
            int size = 10;
            SortedMap<Integer, Float> testValues = new TreeMap<>();

            int cardinality = 0;
            float zSum = 0.0f;
            for (int index = 0; index < floatValues.length; index++)
            {
                float value = floatValues[index];
                if (0.0 != value)
                {
                    testValues.put(index, value);
                    cardinality++;
                    zSum += value;
                }
            }

            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                // get the constructors
                Constructor<FloatVector<?, ?, ?>> constructorMUS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorMU =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorMS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorM =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class);

                // initialize vectors
                FloatVector<?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                FloatVector<?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                FloatVector<?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                FloatVector<?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, floatVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatVector.cardinality());
                    assertEquals("Size", size, floatVector.size());
                    if (floatVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractFloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue("mutable float vector is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float vector should be immutable", ValueRuntimeException.class);
                    mutable = floatVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("neg", -allValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        AbstractFloatScalar<?, ?> s = (AbstractFloatScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), allValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty map
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Float>(), 0, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Float>(), 0, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Float>(), 0, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Float>(), 0);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Float>(), 10, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                assertEquals("Size", 10, vMUS.size());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Float>(), 10, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                assertEquals("Size", 10, vMU.size());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Float>(), 10, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                assertEquals("Size", 10, vMS.size());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Float>(), 10);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                assertEquals("Size", 10, vM.size());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }
            }
        }
    }

    /**
     * test SortedMap<Integer, Scalar> constructors
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testMapScalarConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String scalarName : CLASSNAMES.ALL_NODIM_LIST)
        {
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(scalarName + "Unit");
            Unit<?> standardUnit = quantity.getStandardUnit();
            Class<?> unitClass = standardUnit.getClass();
            float[] floatValues = new float[] {0, 123.456f, 0, 0, 234.567f, 0, 0};
            float[] allValues = new float[] {0, 123.456f, 0, 0, 234.567f, 0, 0, 0, 0, 0};
            int size = 10;
            SortedMap<Integer, FloatScalar<?, ?>> testValues = new TreeMap<>();
            Constructor<FloatScalar<?, ?>> constructorScalar = (Constructor<FloatScalar<?, ?>>) CLASSNAMES
                    .floatScalarClass(scalarName).getConstructor(float.class, unitClass);

            int cardinality = 0;
            float zSum = 0.0f;
            for (int index = 0; index < floatValues.length; index++)
            {
                float value = floatValues[index];
                if (0.0 != value)
                {
                    testValues.put(index, constructorScalar.newInstance(value, standardUnit));
                    cardinality++;
                    zSum += value;
                }
            }

            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                // get the constructors
                Constructor<FloatVector<?, ?, ?>> constructorMUS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorMU =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorMS =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorM =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class);

                // initialize vectors
                FloatVector<?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                FloatVector<?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                FloatVector<?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                FloatVector<?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, floatVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatVector.cardinality());
                    assertEquals("Size", size, floatVector.size());
                    if (floatVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractFloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue("mutable float vector is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float vector should be immutable", ValueRuntimeException.class);
                    mutable = floatVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("neg", -allValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        AbstractFloatScalar<?, ?> s = (AbstractFloatScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), allValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty list
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, standardUnit,
                        storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, standardUnit,
                        storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                assertEquals("Size", 10, vMUS.size());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                assertEquals("Size", 10, vMU.size());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                assertEquals("Size", 10, vMS.size());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                assertEquals("Size", 10, vM.size());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractFloatVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }
            }
        }
    }

    /**
     * Test the FloatSIVector class.
     * @throws UnitException if that happens uncaught; this test has failed
     * @throws ValueRuntimeException if that happens uncaught; this test has failed
     * @throws ClassNotFoundException if that happens uncaught; this test has failed
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void siVectorConstructors() throws ValueRuntimeException, UnitException, ClassNotFoundException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String className : CLASSNAMES.ALL_NODIM_LIST)
        {
            // System.out.println("class name is " + className);
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(className + "Unit");
            // float
            @SuppressWarnings("rawtypes")
            Unit standardUnit = quantity.getStandardUnit();
            float[] testValues = new float[] {0, 123.456f, 0, 0, -234.567f, 0};
            int cardinality = 0;
            float zSum = 0;
            List<Float> list = new ArrayList<>();
            SortedMap<Integer, Float> map = new TreeMap<>();
            for (int index = 0; index < testValues.length; index++)
            {
                float value = testValues[index];
                if (0.0 != value)
                {
                    cardinality++;
                    zSum += value;
                    map.put(index, value);
                }
                list.add(value);
            }
            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                FloatSIVector siv = new FloatSIVector(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                final FloatSIVector sivf = siv;
                compareValues(testValues, siv.getValuesSI());
                assertEquals("StorageType must match", storageType, siv.getStorageType());
                assertEquals("Cardinality", cardinality, siv.cardinality());
                assertEquals("zSum", zSum, siv.zSum().getSI(), 0.001);
                assertEquals("getScalarClass return FloatSIScalar", FloatSIScalar.class, siv.getScalarClass());
                Try.testFail(() -> sivf.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                FloatSIVector mutable = siv.mutable();
                assertTrue("vector is equal to itself", siv.equals(siv));
                assertTrue("vector and mutable vector are considered equal", siv.equals(mutable));
                assertTrue("vector and mutable vector are considered equal (symmetry)", mutable.equals(siv));
                assertFalse("vector is not equal to null", siv.equals(null));
                assertFalse("vector is not equal to some other object", siv.equals("hello world"));
                mutable.ceil();
                assertFalse("vector is not equal to ceil of vector", siv.equals(mutable));
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals("ceil", Math.ceil(testValues[index]), mutable.getInUnit(index), 0.001);
                }
                Try.testFail(() -> sivf.immutable().abs(), "float vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().ceil(), "float vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().floor(), "float vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().rint(), "float vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().neg(), "float vector should be immutable", ValueRuntimeException.class);

                mutable = siv.mutable().mutable();
                mutable.floor();
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals("floor", Math.floor(testValues[index]), mutable.getInUnit(index), 0.001);
                }
                mutable = siv.mutable();
                mutable.abs();
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals("abs", Math.abs(testValues[index]), mutable.getInUnit(index), 0.001);
                }
                mutable = siv.mutable();
                mutable.rint();
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals("rint", Math.rint(testValues[index]), mutable.getInUnit(index), 0.001);
                }
                mutable = siv.mutable();
                mutable.neg();
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals("neg", -testValues[index], mutable.getInUnit(index), 0.001);
                }
                int nextIndex = 0;
                Iterator<?> iterator;
                for (iterator = siv.iterator(); iterator.hasNext();)
                {
                    AbstractFloatScalar<?, ?> s = (AbstractFloatScalar<?, ?>) iterator.next();
                    assertEquals("SIDimensions match", s.getDisplayUnit().getQuantity().getSiDimensions(),
                            quantity.getSiDimensions());
                    assertEquals("value of scalar matches", s.getInUnit(), testValues[nextIndex], 0.001);
                    nextIndex++;
                    try
                    {
                        iterator.remove();
                        fail("Removal of elements should not work in this iterator");
                    }
                    catch (RuntimeException rte)
                    {
                        // Ignore expected exception
                    }
                }
                try
                {
                    iterator.next();
                    fail("another call to next should have thrown a NoSuchElementException");
                }
                catch (NoSuchElementException nsee)
                {
                    // Ignore expected exception
                }
                siv = new FloatSIVector(map, testValues.length, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = new FloatSIVector(list, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = FloatSIVector.of(testValues, standardUnit.getQuantity().getSiDimensions().toString(true, true, true),
                        storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = FloatSIVector.of(list, quantity.getSiDimensions().toString(true, true, true), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = FloatSIVector.of(map, quantity.getSiDimensions().toString(true, true, true), testValues.length,
                        storageType);
                compareValues(testValues, siv.getValuesSI());
            }
        }
    }

    /**
     * Test all the exception that the constructors may throw.
     * @throws UnitException when that happens uncaught; this test has failed
     * @throws ValueRuntimeException when that happens uncaught; this test has failed
     */
    @Test
    public void exceptionsTest() throws ValueRuntimeException, UnitException
    {
        float[] testValues = new float[] {0, 123.456f, 0, -273.15f, -273.15f, 0, -273.15f, 234.567f, 0, 0};
        FloatAbsoluteTemperature[] at = new FloatAbsoluteTemperature[testValues.length];
        List<FloatAbsoluteTemperature> al = new ArrayList<>();
        SortedMap<Integer, FloatAbsoluteTemperature> map = new TreeMap<>();
        for (int i = 0; i < testValues.length; i++)
        {
            FloatAbsoluteTemperature value = new FloatAbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.KELVIN);
            at[i] = value;
            al.add(value);
            if (0.0 != value.si)
            {
                map.put(i, value);
            }
        }

        new FloatAbsoluteTemperatureVector(testValues, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureVector((float[]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(testValues, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(testValues, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(() -> new FloatAbsoluteTemperatureVector((FloatAbsoluteTemperature[]) null, AbsoluteTemperatureUnit.KELVIN,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(at, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(at, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(() -> new FloatAbsoluteTemperatureVector((List<FloatAbsoluteTemperature>) null,
                AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureVector((List<Float>) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(al, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(al, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(
                () -> new FloatAbsoluteTemperatureVector((SortedMap<Integer, FloatAbsoluteTemperature>) null, 10,
                        AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector((SortedMap<Integer, Float>) null, 10,
                AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, 10, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, 10, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, -1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "negative length should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, 1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "too small length should have thrown an exception", ValueRuntimeException.class);

        map.put(-1, at[0]);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, testValues.length, AbsoluteTemperatureUnit.KELVIN,
                StorageType.SPARSE), "too small length should have thrown an exception", ValueRuntimeException.class);

        map.remove(-1); // Remove the offending entry
        Quantity<?> quantity = Quantities.INSTANCE.getQuantity("AbsoluteTemperature" + "Unit");
        new FloatSIVector(testValues, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
        Try.testFail(() -> new FloatSIVector((float[]) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatSIVector((List<Float>) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatSIVector(testValues, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatSIVector(testValues, SIUnit.of(quantity.getSiDimensions()), null),
                "null pointer should have thrown an exception", NullPointerException.class);
    }

    /**
     * Test that parallelized operations work.
     */
    @Test
    public void parallelTest()
    {
        float[] testValues = new float[4000];
        float[] testValuesCelsius = new float[4000];
        for (int i = 0; i < testValues.length; i++)
        {
            testValues[i] = i % 3 != 0 ? 0f : (100.0f + i);
            testValuesCelsius[i] = testValues[i] + 273.15f;
        }
        List<FloatAbsoluteTemperature> al = new ArrayList<>();
        List<Float> dl = new ArrayList<>();
        SortedMap<Integer, FloatAbsoluteTemperature> map = new TreeMap<>();
        FloatAbsoluteTemperature[] at = new FloatAbsoluteTemperature[testValues.length];
        for (int i = 0; i < testValues.length; i++)
        {
            FloatAbsoluteTemperature value = new FloatAbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.KELVIN);
            al.add(value);
            dl.add(testValues[i]);
            if (0.0 != value.si)
            {
                map.put(i, value);
            }
            value = new FloatAbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
            at[i] = value;
        }
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            FloatAbsoluteTemperatureVector atv =
                    new FloatAbsoluteTemperatureVector(testValues, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValuesWithScale(AbsoluteTemperatureUnit.KELVIN.getScale(), testValues, atv.getValuesSI());
            atv = new FloatAbsoluteTemperatureVector(al, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new FloatAbsoluteTemperatureVector(dl, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new FloatAbsoluteTemperatureVector(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new FloatAbsoluteTemperatureVector(at, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT));
        }
    }

    /**
     * Compare two float arrays.
     * @param reference float[]; the reference values
     * @param got float[] the values that should match the reference values
     */
    public void compareValues(final float[] reference, final float[] got)
    {
        assertEquals("length of reference must equal length of result ", reference.length, got.length);
        for (int i = 0; i < reference.length; i++)
        {
            assertEquals("value at index " + i + " must match", reference[i], got[i], 0.001);
        }
    }

    /**
     * Compare two float arrays with factor and offset (derived from a scale).
     * @param scale Scale; the scale
     * @param reference float[]; the reference values
     * @param got float[] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final float[] reference, final float[] got)
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
