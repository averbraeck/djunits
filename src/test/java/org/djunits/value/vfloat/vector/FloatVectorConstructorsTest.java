package org.djunits.value.vfloat.vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.vector.base.FloatVector;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.djutils.exceptions.Try;
import org.junit.jupiter.api.Test;

/**
 * Test constructors of FloatVector.
 */
public class FloatVectorConstructorsTest
{
    /**
     * test float[] constructors.
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
                Constructor<FloatVector<?, ?, ?>> constructorDUS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(float[].class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorDU = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(float[].class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorDS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(float[].class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorD = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(float[].class);

                // initialize vectors
                FloatVector<?, ?, ?> vDUS = constructorDUS.newInstance(testValues, standardUnit, storageType);
                assertEquals(storageType, vDUS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                assertEquals(StorageType.DENSE, vDU.getStorageType(), "StorageType must be DENSE");
                FloatVector<?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                assertEquals(storageType, vDS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vD = constructorD.newInstance(testValues);
                assertEquals(StorageType.DENSE, vD.getStorageType(), "StorageType must be DENSE");

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vDUS, vDU, vDS, vD})
                {
                    compareValuesWithScale(standardUnit.getScale(), testValues, floatVector.getValuesSI());
                    assertEquals(standardUnit, floatVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, floatVector.cardinality(), "Cardinality");
                    if (floatVector instanceof Relative)
                    {
                        assertEquals(zSum, ((FloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable float vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(testValues.length, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    mutable.setSI(testValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals(Math.ceil(testValues[index]), mutable.getInUnit(index), 0.001, "ceil");
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
                        assertEquals(Math.floor(testValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals(Math.rint(testValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals(-testValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), testValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }
            }
        }
    }

    /**
     * test Scalar[] constructors.
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
                Constructor<FloatVector<?, ?, ?>> constructorLUS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(scalarArrayClass, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorLU = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(scalarArrayClass, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorLS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(scalarArrayClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorL = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(scalarArrayClass);

                // initialize vectors
                FloatVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                FloatVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vL = constructorL.newInstance(new Object[] {testValues});
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatVector.getValuesSI());
                    assertEquals(standardUnit, floatVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, floatVector.cardinality(), "Cardinality");
                    if (floatVector instanceof Relative)
                    {
                        assertEquals(zSum, ((FloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001, "zSum");
                    }
                    assertEquals(scalarClass, floatVector.getScalarClass(), "scalarClass must match");
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
                    assertTrue(mutable.isMutable(), "mutable float vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001, "ceil");
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
                        assertEquals(Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(-floatValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), floatValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }
            }
        }
    }

    /**
     * test List&lt;Float&gt; constructors.
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
                Constructor<FloatVector<?, ?, ?>> constructorLUS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(List.class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorLU = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(List.class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorLS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(List.class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorL =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName).getConstructor(List.class);

                // initialize vectors
                FloatVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                FloatVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatVector.getValuesSI());
                    assertEquals(standardUnit, floatVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, floatVector.cardinality(), "Cardinality");
                    if (floatVector instanceof Relative)
                    {
                        assertEquals(zSum, ((FloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable float vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001, "ceil");
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
                        assertEquals(Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(-floatValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), floatValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }

                // test the empty list
                vLUS = constructorLUS.newInstance(new ArrayList<Float>(), standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vLUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLUS.cardinality(), "Cardinality");
                if (vLUS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001, "zSum");
                }

                vLU = constructorLU.newInstance(new ArrayList<Float>(), standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                assertEquals(standardUnit, vLU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLU.cardinality(), "Cardinality");
                if (vLU instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vLU).zSum().getSI(), 0.001, "zSum");
                }

                vLS = constructorLS.newInstance(new ArrayList<Float>(), storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vLS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLS.cardinality(), "Cardinality");
                if (vLS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vLS).zSum().getSI(), 0.001, "zSum");
                }

                vL = constructorL.newInstance(new ArrayList<Float>());
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");
                assertEquals(standardUnit, vL.getDisplayUnit(), "Unit must match");
                assertEquals(0, vL.cardinality(), "Cardinality");
                if (vL instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vL).zSum().getSI(), 0.001, "zSum");
                }
            }
        }
    }

    /**
     * test List&lt;Scalar&gt; constructors.
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
                Constructor<FloatVector<?, ?, ?>> constructorLUS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(List.class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorLU = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(List.class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorLS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(List.class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorL =
                        (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES.floatVectorClass(scalarName).getConstructor(List.class);

                // initialize vectors
                FloatVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                FloatVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatVector.getValuesSI());
                    assertEquals(standardUnit, floatVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, floatVector.cardinality(), "Cardinality");
                    if (floatVector instanceof Relative)
                    {
                        assertEquals(zSum, ((FloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable float vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001, "ceil");
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
                        assertEquals(Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals(-floatValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), floatValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }

                // test the empty list
                vLUS = constructorLUS.newInstance(new ArrayList<FloatScalar<?, ?>>(), standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vLUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLUS.cardinality(), "Cardinality");
                if (vLUS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001, "zSum");
                }

                vLU = constructorLU.newInstance(new ArrayList<FloatScalar<?, ?>>(), standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                assertEquals(standardUnit, vLU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLU.cardinality(), "Cardinality");
                if (vLU instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vLU).zSum().getSI(), 0.001, "zSum");
                }

                vLS = constructorLS.newInstance(new ArrayList<FloatScalar<?, ?>>(), storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vLS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLS.cardinality(), "Cardinality");
                if (vLS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vLS).zSum().getSI(), 0.001, "zSum");
                }

                vL = constructorL.newInstance(new ArrayList<FloatScalar<?, ?>>());
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");
                assertEquals(standardUnit, vL.getDisplayUnit(), "Unit must match");
                assertEquals(0, vL.cardinality(), "Cardinality");
                if (vL instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vL).zSum().getSI(), 0.001, "zSum");
                }
            }
        }
    }

    /**
     * test Map&lt;Integer, Float&gt; constructors.
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
            Map<Integer, Float> testValues = new TreeMap<>();

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
                Constructor<FloatVector<?, ?, ?>> constructorMUS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(Map.class, int.class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorMU = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(Map.class, int.class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorMS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(Map.class, int.class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorM = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(Map.class, int.class);

                // initialize vectors
                FloatVector<?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                FloatVector<?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, floatVector.getValuesSI());
                    assertEquals(standardUnit, floatVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, floatVector.cardinality(), "Cardinality");
                    assertEquals(size, floatVector.size(), "Size");
                    if (floatVector instanceof Relative)
                    {
                        assertEquals(zSum, ((FloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable float vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001, "ceil");
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
                        assertEquals(Math.floor(allValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.rint(allValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(-allValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), allValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }

                // test the empty map
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Float>(), 0, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMUS.cardinality(), "Cardinality");
                if (vMUS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Float>(), 0, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMU.cardinality(), "Cardinality");
                if (vMU instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Float>(), 0, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMS.cardinality(), "Cardinality");
                if (vMS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Float>(), 0);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                assertEquals(0, vM.cardinality(), "Cardinality");
                if (vM instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Float>(), 10, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMUS.cardinality(), "Cardinality");
                assertEquals(10, vMUS.size(), "Size");
                if (vMUS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Float>(), 10, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMU.cardinality(), "Cardinality");
                assertEquals(10, vMU.size(), "Size");
                if (vMU instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Float>(), 10, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMS.cardinality(), "Cardinality");
                assertEquals(10, vMS.size(), "Size");
                if (vMS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Float>(), 10);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                assertEquals(0, vM.cardinality(), "Cardinality");
                assertEquals(10, vM.size(), "Size");
                if (vM instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                }
            }
        }
    }

    /**
     * test Map&lt;Integer, Scalar&gt; constructors.
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
            Map<Integer, FloatScalar<?, ?>> testValues = new TreeMap<>();
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
                Constructor<FloatVector<?, ?, ?>> constructorMUS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(Map.class, int.class, unitClass, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorMU = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(Map.class, int.class, unitClass);
                Constructor<FloatVector<?, ?, ?>> constructorMS = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(Map.class, int.class, StorageType.class);
                Constructor<FloatVector<?, ?, ?>> constructorM = (Constructor<FloatVector<?, ?, ?>>) CLASSNAMES
                        .floatVectorClass(scalarName).getConstructor(Map.class, int.class);

                // initialize vectors
                FloatVector<?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                FloatVector<?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                FloatVector<?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");

                for (FloatVector<?, ?, ?> floatVector : new FloatVector[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, floatVector.getValuesSI());
                    assertEquals(standardUnit, floatVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, floatVector.cardinality(), "Cardinality");
                    assertEquals(size, floatVector.size(), "Size");
                    if (floatVector instanceof Relative)
                    {
                        assertEquals(zSum, ((FloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> floatVector.setSI(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.setInUnit(0, 0), "float vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatVector.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                    FloatVector<?, ?, ?> mutable = floatVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable float vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    Try.testFail(() -> floatVector.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001, "ceil");
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
                        assertEquals(Math.floor(allValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = floatVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.rint(allValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = floatVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(-allValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatVector.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), allValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }

                // test the empty list
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMUS.cardinality(), "Cardinality");
                if (vMUS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMU.cardinality(), "Cardinality");
                if (vMU instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMS.cardinality(), "Cardinality");
                if (vMS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                }

                vM = constructorM.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                assertEquals(0, vM.cardinality(), "Cardinality");
                if (vM instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMUS.cardinality(), "Cardinality");
                assertEquals(10, vMUS.size(), "Size");
                if (vMUS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMU.cardinality(), "Cardinality");
                assertEquals(10, vMU.size(), "Size");
                if (vMU instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMS.cardinality(), "Cardinality");
                assertEquals(10, vMS.size(), "Size");
                if (vMS instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                }

                vM = constructorM.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                assertEquals(0, vM.cardinality(), "Cardinality");
                assertEquals(10, vM.size(), "Size");
                if (vM instanceof Relative)
                {
                    assertEquals(0.0, ((FloatVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
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
            Map<Integer, Float> map = new TreeMap<>();
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
                assertEquals(storageType, siv.getStorageType(), "StorageType must match");
                assertEquals(cardinality, siv.cardinality(), "Cardinality");
                assertEquals(zSum, siv.zSum().getSI(), 0.001, "zSum");
                assertEquals(FloatSIScalar.class, siv.getScalarClass(), "getScalarClass return FloatSIScalar");
                Try.testFail(() -> sivf.ceil(), "float vector should be immutable", ValueRuntimeException.class);
                FloatSIVector mutable = siv.mutable();
                assertTrue(siv.equals(siv), "vector is equal to itself");
                assertTrue(siv.equals(mutable), "vector and mutable vector are considered equal");
                assertTrue(mutable.equals(siv), "vector and mutable vector are considered equal (symmetry)");
                assertFalse(siv.equals(null), "vector is not equal to null");
                assertFalse(siv.equals("hello world"), "vector is not equal to some other object");
                mutable.ceil();
                assertFalse(siv.equals(mutable), "vector is not equal to ceil of vector");
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals(Math.ceil(testValues[index]), mutable.getInUnit(index), 0.001, "ceil");
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
                    assertEquals(Math.floor(testValues[index]), mutable.getInUnit(index), 0.001, "floor");
                }
                mutable = siv.mutable();
                mutable.abs();
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals(Math.abs(testValues[index]), mutable.getInUnit(index), 0.001, "abs");
                }
                mutable = siv.mutable();
                mutable.rint();
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals(Math.rint(testValues[index]), mutable.getInUnit(index), 0.001, "rint");
                }
                mutable = siv.mutable();
                mutable.neg();
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals(-testValues[index], mutable.getInUnit(index), 0.001, "neg");
                }
                int nextIndex = 0;
                Iterator<?> iterator;
                for (iterator = siv.iterator(); iterator.hasNext();)
                {
                    FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
                    assertEquals(s.getDisplayUnit().getQuantity().getSiDimensions(), quantity.getSiDimensions(),
                            "SIDimensions match");
                    assertEquals(s.getInUnit(), testValues[nextIndex], 0.001, "value of scalar matches");
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
        Map<Integer, FloatAbsoluteTemperature> map = new TreeMap<>();
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
                () -> new FloatAbsoluteTemperatureVector((Map<Integer, FloatAbsoluteTemperature>) null, 10,
                        AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector((Map<Integer, Float>) null, 10, AbsoluteTemperatureUnit.KELVIN,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, 10, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, 10, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, -1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "negative length should have thrown an exception", IllegalArgumentException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureVector(map, 1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "too small length should have thrown an exception", IndexOutOfBoundsException.class);

        map.put(-1, at[0]);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureVector(map, testValues.length, AbsoluteTemperatureUnit.KELVIN,
                        StorageType.SPARSE),
                "too small length should have thrown an exception", IndexOutOfBoundsException.class);

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
        Map<Integer, FloatAbsoluteTemperature> map = new TreeMap<>();
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
     * @param reference the reference values
     * @param got float[] the values that should match the reference values
     */
    public void compareValues(final float[] reference, final float[] got)
    {
        assertEquals(reference.length, got.length, "length of reference must equal length of result ");
        for (int i = 0; i < reference.length; i++)
        {
            assertEquals(reference[i], got[i], 0.001, "value at index " + i + " must match");
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
            assertEquals(reference[i] * factor + offset, got[i], 0.001, "value at index " + i + " must match");
        }
    }

}
