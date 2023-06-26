package org.djunits.value.vfloat.matrix;

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
import org.djunits.value.vfloat.matrix.base.FloatMatrix;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djutils.exceptions.Try;
import org.junit.Test;

/**
 * Test constructors of FloatMatrix.
 */
public class FloatMatrixConstructorsTest
{
    /**
     * test float[][] constructors
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
            for (int dataset : new int[] {1, 2})
            {
                float[][] testValues =
                        dataset == 1 ? FLOATMATRIX.denseRectArrays(50, 50) : FLOATMATRIX.sparseRectArrays(50, 50);

                int cardinality = 0;
                float zSum = 0.0f;
                for (int i = 0; i < testValues.length; i++)
                {
                    for (int j = 0; j < testValues[0].length; j++)
                    {
                        float value = testValues[i][j];
                        if (0.0 != value)
                        {
                            cardinality++;
                            zSum += value;
                        }
                    }
                }

                for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                {
                    // get the constructors
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorDUS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(float[][].class, unitClass, StorageType.class);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorDU = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(float[][].class, unitClass);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorDS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(float[][].class, StorageType.class);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorD = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(float[][].class);

                    // initialize matrixs
                    FloatMatrix<?, ?, ?, ?> vDUS = constructorDUS.newInstance(testValues, standardUnit, storageType);
                    assertEquals("StorageType must match", storageType, vDUS.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vDU.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                    assertEquals("StorageType must match", storageType, vDS.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vD = constructorD.newInstance(testValues);
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vD.getStorageType());

                    for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vDUS, vDU, vDS, vD})
                    {
                        compareValuesWithScale(standardUnit.getScale(), testValues, floatMatrix.getValuesSI());
                        assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                        assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                        if (floatMatrix instanceof Relative)
                        {
                            assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?, ?>) floatMatrix).zSum().getSI(), 0.001);
                        }

                        Try.testFail(() -> floatMatrix.setSI(0, 0, 0), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> floatMatrix.setInUnit(0, 0, 0), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                        FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                        assertTrue("mutable float matrix is mutable", mutable.isMutable());
                        mutable.setSI(0, 0, 0);
                        mutable.setInUnit(0, 0, 0);
                        Try.testFail(() -> floatMatrix.mutable().setSI(-1, 0, 0),
                                "negative index should have thrown an exception", ValueRuntimeException.class);
                        Try.testFail(() -> floatMatrix.mutable().setSI(0, -1, 0),
                                "negative index should have thrown an exception", ValueRuntimeException.class);
                        Try.testFail(() -> floatMatrix.mutable().setSI(testValues.length, 0, 0),
                                "index just above range should have thrown an exception", ValueRuntimeException.class);
                        Try.testFail(() -> floatMatrix.mutable().setSI(0, testValues.length, 0),
                                "index just above range should have thrown an exception", ValueRuntimeException.class);
                        mutable.setSI(testValues.length - 1, 0, 0);
                        mutable.ceil();
                        mutable = floatMatrix.mutable();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals("ceil", Math.ceil(testValues[i][j]), mutable.getInUnit(i, j), 0.001);
                            }
                        }
                        FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        Try.testFail(() -> immutable.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.floor(), "float matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.rint(), "float matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.neg(), "float matrix should be immutable", ValueRuntimeException.class);
                        mutable = floatMatrix.mutable().mutable();
                        mutable.floor();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals("floor", Math.floor(testValues[i][j]), mutable.getInUnit(i, j), 0.001);
                            }
                        }
                        mutable = floatMatrix.mutable();
                        mutable.rint();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals("rint", Math.rint(testValues[i][j]), mutable.getInUnit(i, j), 0.001);
                            }
                        }
                        mutable = floatMatrix.mutable();
                        mutable.neg();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals("neg", -testValues[i][j], mutable.getInUnit(i, j), 0.001);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * test Scalar[][] constructors
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
            float[][] floatValues = new float[][] {0, 123.456f, 0, 0, 234.567f, 0, 0};
            Class<?> scalarClass = CLASSNAMES.floatScalarClass(scalarName);
            Object[][] testValues = (Object[][]) Array.newInstance(scalarClass, floatValues.length);
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
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLUS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(scalarArrayClass, unitClass, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLU = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(scalarArrayClass, unitClass);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(scalarArrayClass, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorL = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(scalarArrayClass);

                // initialize matrixs
                FloatMatrix<?, ?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                FloatMatrix<?, ?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vL = constructorL.newInstance(new Object[][] {testValues});
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatMatrix.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                    if (floatMatrix instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?>) floatMatrix).zSum().getSI(), 0.001);
                    }
                    assertEquals("scalarClass must match", scalarClass, floatMatrix.getScalarClass());
                    Method instantiateMethod =
                            floatMatrix.getClass().getDeclaredMethod("instantiateMatrix", FloatMatrixData.class, unitClass);
                    FloatMatrix<?, ?, ?, ?> vData = (FloatMatrix<?, ?, ?, ?>) instantiateMethod.invoke(floatMatrix,
                            FloatMatrixData.instantiate(floatValues, IdentityScale.SCALE, storageType), standardUnit);
                    compareValuesWithScale(standardUnit.getScale(), floatValues, vData.getValuesSI());

                    Try.testFail(() -> floatMatrix.setSI(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.setInUnit(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                    assertTrue("mutable float matrix is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatMatrix.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float matrix should be immutable", ValueRuntimeException.class);
                    mutable = floatMatrix.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("neg", -floatValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatMatrix.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
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
            float[][] floatValues = new float[][] {0, 123.456f, 0, 0, 234.567f, 0, 0};
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
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLUS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(List.class, unitClass, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLU = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(List.class, unitClass);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(List.class, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorL = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(List.class);

                // initialize matrixs
                FloatMatrix<?, ?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                FloatMatrix<?, ?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatMatrix.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                    if (floatMatrix instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?>) floatMatrix).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatMatrix.setSI(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.setInUnit(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                    assertTrue("mutable float matrix is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatMatrix.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float matrix should be immutable", ValueRuntimeException.class);
                    mutable = floatMatrix.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("neg", -floatValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatMatrix.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
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
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001);
                }

                vLU = constructorLU.newInstance(new ArrayList<Float>(), standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                assertEquals("Unit must match", standardUnit, vLU.getDisplayUnit());
                assertEquals("Cardinality", 0, vLU.cardinality());
                if (vLU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vLU).zSum().getSI(), 0.001);
                }

                vLS = constructorLS.newInstance(new ArrayList<Float>(), storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLS.cardinality());
                if (vLS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vLS).zSum().getSI(), 0.001);
                }

                vL = constructorL.newInstance(new ArrayList<Float>());
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());
                assertEquals("Unit must match", standardUnit, vL.getDisplayUnit());
                assertEquals("Cardinality", 0, vL.cardinality());
                if (vL instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vL).zSum().getSI(), 0.001);
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
            float[][] floatValues = new float[][] {0, 123.456f, 0, 0, 234.567f, 0, 0};
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
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLUS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(List.class, unitClass, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLU = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(List.class, unitClass);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorLS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(List.class, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorL = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(List.class);

                // initialize matrixs
                FloatMatrix<?, ?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                FloatMatrix<?, ?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), floatValues, floatMatrix.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                    if (floatMatrix instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?>) floatMatrix).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatMatrix.setSI(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.setInUnit(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                    assertTrue("mutable float matrix is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatMatrix.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.mutable().setSI(floatValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(floatValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float matrix should be immutable", ValueRuntimeException.class);
                    mutable = floatMatrix.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.rint();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(floatValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.neg();
                    for (int index = 0; index < floatValues.length; index++)
                    {
                        assertEquals("neg", -floatValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatMatrix.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
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
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001);
                }

                vLU = constructorLU.newInstance(new ArrayList<FloatScalar<?, ?>>(), standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                assertEquals("Unit must match", standardUnit, vLU.getDisplayUnit());
                assertEquals("Cardinality", 0, vLU.cardinality());
                if (vLU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vLU).zSum().getSI(), 0.001);
                }

                vLS = constructorLS.newInstance(new ArrayList<FloatScalar<?, ?>>(), storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLS.cardinality());
                if (vLS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vLS).zSum().getSI(), 0.001);
                }

                vL = constructorL.newInstance(new ArrayList<FloatScalar<?, ?>>());
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());
                assertEquals("Unit must match", standardUnit, vL.getDisplayUnit());
                assertEquals("Cardinality", 0, vL.cardinality());
                if (vL instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vL).zSum().getSI(), 0.001);
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
            float[][] floatValues = new float[][] {0, 123.456f, 0, 0, 234.567f, 0, 0};
            float[][] allValues = new float[][] {0, 123.456f, 0, 0, 234.567f, 0, 0, 0, 0, 0};
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
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorMUS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, unitClass, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorMU = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, unitClass);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorMS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorM = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(SortedMap.class, int.class);

                // initialize matrixs
                FloatMatrix<?, ?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                FloatMatrix<?, ?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());

                for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, floatMatrix.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                    assertEquals("Size", size, floatMatrix.size());
                    if (floatMatrix instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?>) floatMatrix).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatMatrix.setSI(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.setInUnit(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                    assertTrue("mutable float matrix is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatMatrix.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float matrix should be immutable", ValueRuntimeException.class);
                    mutable = floatMatrix.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("neg", -allValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatMatrix.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
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
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Float>(), 0, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Float>(), 0, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Float>(), 0);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Float>(), 10, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                assertEquals("Size", 10, vMUS.size());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Float>(), 10, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                assertEquals("Size", 10, vMU.size());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Float>(), 10, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                assertEquals("Size", 10, vMS.size());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Float>(), 10);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                assertEquals("Size", 10, vM.size());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
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
            float[][] floatValues = new float[][] {0, 123.456f, 0, 0, 234.567f, 0, 0};
            float[][] allValues = new float[][] {0, 123.456f, 0, 0, 234.567f, 0, 0, 0, 0, 0};
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
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorMUS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, unitClass, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorMU = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, unitClass);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorMS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, StorageType.class);
                Constructor<FloatMatrix<?, ?, ?, ?>> constructorM = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .floatMatrixClass(scalarName).getConstructor(SortedMap.class, int.class);

                // initialize matrixs
                FloatMatrix<?, ?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                FloatMatrix<?, ?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                FloatMatrix<?, ?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());

                for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, floatMatrix.getValuesSI());
                    assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                    assertEquals("Size", size, floatMatrix.size());
                    if (floatMatrix instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?>) floatMatrix).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> floatMatrix.setSI(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.setInUnit(0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                    assertTrue("mutable float matrix is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> floatMatrix.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> floatMatrix.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "float matrix should be immutable", ValueRuntimeException.class);
                    mutable = floatMatrix.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = floatMatrix.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("neg", -allValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = floatMatrix.iterator(); iterator.hasNext();)
                    {
                        FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), allValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty list
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 0);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                assertEquals("Size", 10, vMUS.size());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                assertEquals("Size", 10, vMU.size());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                assertEquals("Size", 10, vMS.size());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, FloatScalar<?, ?>>(), 10);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                assertEquals("Size", 10, vM.size());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }
            }
        }
    }

    /**
     * Test the FloatSIMatrix class.
     * @throws UnitException if that happens uncaught; this test has failed
     * @throws ValueRuntimeException if that happens uncaught; this test has failed
     * @throws ClassNotFoundException if that happens uncaught; this test has failed
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void siMatrixConstructors() throws ValueRuntimeException, UnitException, ClassNotFoundException
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
            float[][] testValues = new float[][] {0, 123.456f, 0, 0, -234.567f, 0};
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
                FloatSIMatrix siv = new FloatSIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                final FloatSIMatrix sivf = siv;
                compareValues(testValues, siv.getValuesSI());
                assertEquals("StorageType must match", storageType, siv.getStorageType());
                assertEquals("Cardinality", cardinality, siv.cardinality());
                assertEquals("zSum", zSum, siv.zSum().getSI(), 0.001);
                assertEquals("getScalarClass return FloatSIScalar", FloatSIScalar.class, siv.getScalarClass());
                Try.testFail(() -> sivf.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                FloatSIMatrix mutable = siv.mutable();
                assertTrue("matrix is equal to itself", siv.equals(siv));
                assertTrue("matrix and mutable matrix are considered equal", siv.equals(mutable));
                assertTrue("matrix and mutable matrix are considered equal (symmetry)", mutable.equals(siv));
                assertFalse("matrix is not equal to null", siv.equals(null));
                assertFalse("matrix is not equal to some other object", siv.equals("hello world"));
                mutable.ceil();
                assertFalse("matrix is not equal to ceil of matrix", siv.equals(mutable));
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals("ceil", Math.ceil(testValues[index]), mutable.getInUnit(index), 0.001);
                }
                Try.testFail(() -> sivf.immutable().abs(), "float matrix should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().floor(), "float matrix should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().rint(), "float matrix should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().neg(), "float matrix should be immutable", ValueRuntimeException.class);

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
                    FloatScalar<?, ?> s = (FloatScalar<?, ?>) iterator.next();
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
                siv = new FloatSIMatrix(map, testValues.length, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = new FloatSIMatrix(list, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = FloatSIMatrix.of(testValues, standardUnit.getQuantity().getSiDimensions().toString(true, true, true),
                        storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = FloatSIMatrix.of(list, quantity.getSiDimensions().toString(true, true, true), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = FloatSIMatrix.of(map, quantity.getSiDimensions().toString(true, true, true), testValues.length,
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
        float[][] testValues = new float[][] {0, 123.456f, 0, -273.15f, -273.15f, 0, -273.15f, 234.567f, 0, 0};
        FloatAbsoluteTemperature[][] at = new FloatAbsoluteTemperature[testValues.length];
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

        new FloatAbsoluteTemperatureMatrix(testValues, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureMatrix((float[][]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(testValues, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(testValues, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix((FloatAbsoluteTemperature[][]) null,
                AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(at, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(at, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix((List<FloatAbsoluteTemperature>) null,
                AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureMatrix((List<Float>) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(al, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(al, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(
                () -> new FloatAbsoluteTemperatureMatrix((SortedMap<Integer, FloatAbsoluteTemperature>) null, 10,
                        AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix((SortedMap<Integer, Float>) null, 10,
                AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(map, 10, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(map, 10, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(map, -1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "negative length should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(map, 1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "too small length should have thrown an exception", ValueRuntimeException.class);

        map.put(-1, at[0]);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(map, testValues.length, AbsoluteTemperatureUnit.KELVIN,
                StorageType.SPARSE), "too small length should have thrown an exception", ValueRuntimeException.class);

        map.remove(-1); // Remove the offending entry
        Quantity<?> quantity = Quantities.INSTANCE.getQuantity("AbsoluteTemperature" + "Unit");
        new FloatSIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
        Try.testFail(() -> new FloatSIMatrix((float[][]) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatSIMatrix(testValues, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatSIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), null),
                "null pointer should have thrown an exception", NullPointerException.class);
    }

    /**
     * Test that parallelized operations work.
     */
    @Test
    public void parallelTest()
    {
        float[][] testValues = new float[4000];
        float[][] testValuesCelsius = new float[4000];
        for (int i = 0; i < testValues.length; i++)
        {
            testValues[i] = i % 3 != 0 ? 0f : (100.0f + i);
            testValuesCelsius[i] = testValues[i] + 273.15f;
        }
        List<FloatAbsoluteTemperature> al = new ArrayList<>();
        List<Float> dl = new ArrayList<>();
        SortedMap<Integer, FloatAbsoluteTemperature> map = new TreeMap<>();
        FloatAbsoluteTemperature[][] at = new FloatAbsoluteTemperature[testValues.length];
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
            FloatAbsoluteTemperatureMatrix atv =
                    new FloatAbsoluteTemperatureMatrix(testValues, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValuesWithScale(AbsoluteTemperatureUnit.KELVIN.getScale(), testValues, atv.getValuesSI());
            atv = new FloatAbsoluteTemperatureMatrix(al, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new FloatAbsoluteTemperatureMatrix(dl, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new FloatAbsoluteTemperatureMatrix(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new FloatAbsoluteTemperatureMatrix(at, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT));
        }
    }

    /**
     * Compare two float arrays.
     * @param reference float[][]; the reference values
     * @param got float[][] the values that should match the reference values
     */
    public void compareValues(final float[][] reference, final float[][] got)
    {
        assertEquals("length of reference must equal length of result ", reference.length, got.length);
        for (int i = 0; i < reference.length; i++)
        {
            assertEquals("length of reference[i] must equal length of result[i] ", reference[i].length, got[i].length);
            for (int j = 0; j < reference[i].length; j++)
            {
                assertEquals("value at index " + i + "," + j + " must match", reference[i][j], got[i][j], 0.001);
            }
        }
    }

    /**
     * Compare two float arrays with factor and offset (derived from a scale).
     * @param scale Scale; the scale
     * @param reference float[][]; the reference values
     * @param got float[][] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final float[][] reference, final float[][] got)
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
            assertEquals("length of reference[i] must equal length of result[i] ", reference[i].length, got[i].length);
            for (int j = 0; j < reference[i].length; j++)
            {
                assertEquals("value at index " + i + "," + j + " must match", reference[i][j] * factor + offset, got[i][j],
                        0.001);
            }
        }
    }

}
