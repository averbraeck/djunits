package org.djunits.old.value.vfloat.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.djunits.old.quantity.Quantities;
import org.djunits.old.quantity.Quantity;
import org.djunits.old.unit.AbsoluteTemperatureUnit;
import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.SIUnit;
import org.djunits.old.unit.Unit;
import org.djunits.old.unit.scale.GradeScale;
import org.djunits.old.unit.scale.IdentityScale;
import org.djunits.old.unit.scale.Scale;
import org.djunits.old.unit.util.UNITS;
import org.djunits.old.unit.util.UnitException;
import org.djunits.old.value.CLASSNAMES;
import org.djunits.old.value.Relative;
import org.djunits.old.value.ValueRuntimeException;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vfloat.matrix.FloatAbsoluteTemperatureMatrix;
import org.djunits.old.value.vfloat.matrix.FloatSIMatrix;
import org.djunits.old.value.vfloat.matrix.base.FloatMatrix;
import org.djunits.old.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.old.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.old.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.old.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.old.value.vfloat.scalar.FloatSIScalar;
import org.djunits.old.value.vfloat.scalar.base.FloatScalar;
import org.djunits.old.value.vfloat.vector.data.FloatVectorData;
import org.djutils.test.UnitTest;
import org.junit.jupiter.api.Test;

/**
 * Test constructors of FloatMatrix.
 */
public class FloatMatrixConstructorsTest
{
    /**
     * test float[][] constructors.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     * @throws ClassNotFoundException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testFloatConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException
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
            Class<?> scalarClass = Class.forName("org.djunits.value.vfloat.scalar.Float" + scalarName);
            Class<?> vectorClass = Class.forName("org.djunits.value.vfloat.vector.Float" + scalarName + "Vector");
            for (int dataset : new int[] {1, 2})
            {
                float[][] testValues =
                        dataset == 1 ? FLOATMATRIX.denseRectArrays(50, 50, true) : FLOATMATRIX.sparseRectArrays(50, 50, true);

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

                    // initialize matrices
                    FloatMatrix<?, ?, ?, ?> vDUS = constructorDUS.newInstance(testValues, standardUnit, storageType);
                    assertEquals(storageType, vDUS.getStorageType(), "StorageType must match");
                    FloatMatrix<?, ?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                    assertEquals(StorageType.DENSE, vDU.getStorageType(), "StorageType must be DENSE");
                    FloatMatrix<?, ?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                    assertEquals(storageType, vDS.getStorageType(), "StorageType must match");
                    FloatMatrix<?, ?, ?, ?> vD = constructorD.newInstance(new Object[] {testValues});
                    assertEquals(StorageType.DENSE, vD.getStorageType(), "StorageType must be DENSE");

                    for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vDUS, vDU, vDS, vD})
                    {
                        compareValuesWithScale(standardUnit.getScale(), testValues, floatMatrix.getValuesSI());
                        assertEquals(standardUnit, floatMatrix.getDisplayUnit(), "Unit must match");
                        assertEquals(cardinality, floatMatrix.cardinality(), "Cardinality");

                        assertEquals(vectorClass, floatMatrix.getVectorClass(), "VectorClass must match");
                        assertEquals(scalarClass, floatMatrix.getScalarClass(), "ScalarClass must match");
                        Method imMethod =
                                floatMatrix.getClass().getMethod("instantiateMatrix", FloatMatrixData.class, unitClass);
                        assertEquals(floatMatrix, imMethod.invoke(floatMatrix,
                                FloatMatrixData.instantiate(testValues, IdentityScale.SCALE, storageType), standardUnit),
                                "instantiateMatrix must match");
                        Method ivMethod =
                                floatMatrix.getClass().getMethod("instantiateVector", FloatVectorData.class, unitClass);
                        assertEquals(vectorClass,
                                ivMethod.invoke(floatMatrix, FloatVectorData.instantiate(new float[] {1f, 2f, 0f, 0f, 3f, 4f},
                                        IdentityScale.SCALE, storageType), standardUnit).getClass(),
                                "instantiateVector class must match");
                        Method isMethod = floatMatrix.getClass().getMethod("instantiateScalarSI", float.class, unitClass);
                        assertEquals(scalarClass, isMethod.invoke(floatMatrix, 3.14f, standardUnit).getClass(),
                                "instantiateScalarSI class must match");

                        if (floatMatrix instanceof Relative)
                        {
                            assertEquals(zSum, ((FloatMatrixRel<?, ?, ?, ?>) floatMatrix).zSum().getSI(), zSum * 0.0001,
                                    "zSum");
                        }
                        UnitTest.testFail(() -> floatMatrix.setSI(0, 0, 0), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> floatMatrix.setInUnit(0, 0, 0), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                        assertTrue(mutable.isMutable(), "mutable float matrix is mutable");
                        mutable.setSI(0, 0, 0);
                        mutable.setInUnit(0, 0, 0);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(-1, 0, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(0, -1, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(testValues.length, 0, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(0, testValues.length, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        mutable.setSI(testValues.length - 1, 0, 0);
                        mutable.setSI(0, testValues.length - 1, 0);
                        mutable = floatMatrix.mutable();
                        mutable.ceil();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.ceil(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "ceil");
                            }
                        }
                        FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        UnitTest.testFail(() -> immutable.ceil(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.floor(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.rint(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.neg(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        mutable = floatMatrix.mutable().mutable();
                        mutable.floor();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.floor(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "floor");
                            }
                        }
                        mutable = floatMatrix.mutable();
                        mutable.rint();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.rint(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "rint");
                            }
                        }
                        mutable = floatMatrix.mutable();
                        mutable.neg();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(-testValues[i][j], mutable.getInUnit(i, j), 0.001, "neg");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * test Scalar[][] constructors.
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
            for (int dataset : new int[] {1, 2})
            {
                float[][] testValues =
                        dataset == 1 ? FLOATMATRIX.denseRectArrays(50, 50, true) : FLOATMATRIX.sparseRectArrays(50, 50, true);
                Class<?> scalarClass = CLASSNAMES.floatScalarClass(scalarName);
                Object[][] scalarValues = (Object[][]) Array.newInstance(scalarClass, testValues.length, testValues[0].length);
                Class<?> scalarArrayClass = scalarValues.getClass();
                Constructor<FloatScalar<?, ?>> constructorScalar =
                        (Constructor<FloatScalar<?, ?>>) scalarClass.getConstructor(float.class, unitClass);

                int cardinality = 0;
                float zSum = 0.0f;
                for (int i = 0; i < testValues.length; i++)
                {
                    for (int j = 0; j < testValues[0].length; j++)
                    {
                        float value = testValues[i][j];
                        scalarValues[i][j] = constructorScalar.newInstance(value, standardUnit);
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
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorLUS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(scalarArrayClass, unitClass, StorageType.class);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorLU = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(scalarArrayClass, unitClass);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorLS = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(scalarArrayClass, StorageType.class);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorL = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(scalarArrayClass);

                    // initialize matrices
                    FloatMatrix<?, ?, ?, ?> vLUS = constructorLUS.newInstance(scalarValues, standardUnit, storageType);
                    assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                    FloatMatrix<?, ?, ?, ?> vLU = constructorLU.newInstance(scalarValues, standardUnit);
                    assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                    FloatMatrix<?, ?, ?, ?> vLS = constructorLS.newInstance(scalarValues, storageType);
                    assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                    FloatMatrix<?, ?, ?, ?> vL = constructorL.newInstance(new Object[] {scalarValues});
                    assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");

                    for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vLUS, vLU, vLS, vL})
                    {
                        compareValuesWithScale(standardUnit.getScale(), testValues, floatMatrix.getValuesSI());
                        assertEquals(standardUnit, floatMatrix.getDisplayUnit(), "Unit must match");
                        assertEquals(cardinality, floatMatrix.cardinality(), "Cardinality");
                        if (floatMatrix instanceof Relative)
                        {
                            assertEquals(zSum, ((FloatMatrixRel<?, ?, ?, ?>) floatMatrix).zSum().getSI(), zSum * 0.001, "zSum");
                        }

                        UnitTest.testFail(() -> floatMatrix.setSI(0, 0, 0), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> floatMatrix.setInUnit(0, 0, 0), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                        assertTrue(mutable.isMutable(), "mutable float matrix is mutable");
                        mutable.setSI(0, 0, 0);
                        mutable.setInUnit(0, 0, 0);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(-1, 0, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(0, -1, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(testValues.length, 0, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(0, testValues.length, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        mutable.setSI(testValues.length - 1, 0, 0);
                        mutable.setSI(0, testValues.length - 1, 0);
                        mutable = floatMatrix.mutable();
                        mutable.ceil();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.ceil(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "ceil");
                            }
                        }
                        FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        UnitTest.testFail(() -> immutable.ceil(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.floor(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.rint(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.neg(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        mutable = floatMatrix.mutable().mutable();
                        mutable.floor();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.floor(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "floor");
                            }
                        }
                        mutable = floatMatrix.mutable();
                        mutable.rint();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.rint(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "rint");
                            }
                        }
                        mutable = floatMatrix.mutable();
                        mutable.neg();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(-testValues[i][j], mutable.getInUnit(i, j), 0.001, "neg");
                            }
                        }
                    }
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
            Class<?> scalarClass = CLASSNAMES.floatScalarClass(scalarName);
            int rows = 50;
            int cols = 50;
            for (int dataset : new int[] {1, 2})
            {
                Collection<FloatSparseValue<?, ?>> testValues =
                        dataset == 1 ? FLOATMATRIX.denseRectTuplesAnonymous(rows, cols, scalarClass, standardUnit, true)
                                : FLOATMATRIX.sparseRectTuplesAnonymous(rows, cols, scalarClass, standardUnit, true);

                float[][] values = new float[rows][cols];
                int cardinality = 0;
                float zSum = 0.0f;
                for (FloatSparseValue<?, ?> dsValue : testValues)
                {
                    float value = dsValue.getValueSI();
                    values[dsValue.getRow()][dsValue.getColumn()] = value;
                    if (0.0 != value)
                    {
                        cardinality++;
                        zSum += value;
                    }
                }

                for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                {
                    // get the constructors
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorMUS =
                            (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES.floatMatrixClass(scalarName)
                                    .getConstructor(Collection.class, unitClass, int.class, int.class, StorageType.class);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorMU = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(Collection.class, unitClass, int.class, int.class);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorMS =
                            (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES.floatMatrixClass(scalarName)
                                    .getConstructor(Collection.class, int.class, int.class, StorageType.class);
                    Constructor<FloatMatrix<?, ?, ?, ?>> constructorM = (Constructor<FloatMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .floatMatrixClass(scalarName).getConstructor(Collection.class, int.class, int.class);

                    // initialize matrices
                    FloatMatrix<?, ?, ?, ?> vMUS =
                            constructorMUS.newInstance(testValues, standardUnit, rows, cols, storageType);
                    assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                    FloatMatrix<?, ?, ?, ?> vMU = constructorMU.newInstance(testValues, standardUnit, rows, cols);
                    assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                    FloatMatrix<?, ?, ?, ?> vMS = constructorMS.newInstance(testValues, rows, cols, storageType);
                    assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                    FloatMatrix<?, ?, ?, ?> vM = constructorM.newInstance(testValues, rows, cols);
                    assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");

                    for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vMUS, vMU, vMS, vM})
                    {
                        FloatMatrix<?, ?, ?, ?> original = floatMatrix.clone();
                        compareValuesWithScale(standardUnit.getScale(), testValues, floatMatrix.getValuesSI());
                        assertEquals(standardUnit, floatMatrix.getDisplayUnit(), "Unit must match");
                        assertEquals(cardinality, floatMatrix.cardinality(), "Cardinality");
                        if (floatMatrix instanceof Relative)
                        {
                            assertEquals(zSum, ((FloatMatrixRel<?, ?, ?, ?>) floatMatrix).zSum().getSI(), zSum * 0.0001,
                                    "zSum");
                        }

                        UnitTest.testFail(() -> floatMatrix.setSI(0, 0, 0), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> floatMatrix.setInUnit(0, 0, 0), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> floatMatrix.ceil(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        FloatMatrix<?, ?, ?, ?> mutable = floatMatrix.mutable();
                        assertTrue(mutable.isMutable(), "mutable float matrix is mutable");
                        mutable.setSI(0, 0, 0);
                        mutable.setInUnit(0, 0, 0);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(-1, 0, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(0, -1, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(rows, 0, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        UnitTest.testFail(() -> floatMatrix.mutable().setSI(0, cols, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        mutable.setSI(cols - 1, 0, 0);
                        mutable.setSI(0, rows - 1, 0);
                        mutable = original.mutable();
                        mutable.ceil();
                        for (int r = 0; r < rows; r++)
                        {
                            for (int c = 0; c < cols; c++)
                            {
                                assertEquals(Math.ceil(values[r][c]), mutable.getInUnit(r, c), 0.001, "ceil");
                            }
                        }
                        FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        UnitTest.testFail(() -> immutable.ceil(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.floor(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.rint(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        UnitTest.testFail(() -> immutable.neg(), "float matrix should be immutable",
                                ValueRuntimeException.class);
                        mutable = original.mutable();
                        mutable.floor();
                        for (int r = 0; r < rows; r++)
                        {
                            for (int c = 0; c < cols; c++)
                            {
                                assertEquals(Math.floor(values[r][c]), mutable.getInUnit(r, c), 0.001, "floor");
                            }
                        }
                        mutable = original.mutable();
                        mutable.rint();
                        for (int r = 0; r < rows; r++)
                        {
                            for (int c = 0; c < cols; c++)
                            {
                                assertEquals(Math.rint(values[r][c]), mutable.getInUnit(r, c), 0.001, "rint");
                            }
                        }
                        mutable = original.mutable();
                        mutable.neg();
                        for (int r = 0; r < rows; r++)
                        {
                            for (int c = 0; c < cols; c++)
                            {
                                assertEquals(-values[r][c], mutable.getInUnit(r, c), 0.001, "neg");
                            }
                        }
                    }

                    // test the empty map
                    vMUS = constructorMUS.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), standardUnit, 0, 0, storageType);
                    assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                    assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMUS.cardinality(), "Cardinality");
                    if (vMUS instanceof Relative)
                    {
                        assertEquals(0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                    }

                    vMU = constructorMU.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), standardUnit, 0, 0);
                    assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                    assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMU.cardinality(), "Cardinality");
                    if (vMU instanceof Relative)
                    {
                        assertEquals(0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                    }

                    vMS = constructorMS.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), 0, 0, storageType);
                    assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                    assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMS.cardinality(), "Cardinality");
                    if (vMS instanceof Relative)
                    {
                        assertEquals(0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                    }

                    vM = constructorM.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), 0, 0);
                    assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                    assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vM.cardinality(), "Cardinality");
                    if (vM instanceof Relative)
                    {
                        assertEquals(0.0, ((FloatMatrixRel<?, ?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                    }

                    // test the empty map with a size
                    vMUS = constructorMUS.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), standardUnit, 10, 10,
                            storageType);
                    assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                    assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMUS.cardinality(), "Cardinality");
                    assertEquals(10, vMUS.rows(), "Rows");
                    assertEquals(10, vMUS.cols(), "Cols");
                    if (vMUS instanceof Relative)
                    {
                        assertEquals(0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                    }

                    vMU = constructorMU.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), standardUnit, 10, 10);
                    assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                    assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMU.cardinality(), "Cardinality");
                    assertEquals(10, vMUS.rows(), "Rows");
                    assertEquals(10, vMUS.cols(), "Cols");
                    if (vMU instanceof Relative)
                    {
                        assertEquals(0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                    }

                    vMS = constructorMS.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), 10, 10, storageType);
                    assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                    assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMS.cardinality(), "Cardinality");
                    assertEquals(10, vMUS.rows(), "Rows");
                    assertEquals(10, vMUS.cols(), "Cols");
                    if (vMS instanceof Relative)
                    {
                        assertEquals(0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                    }

                    vM = constructorM.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), 10, 10);
                    assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                    assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vM.cardinality(), "Cardinality");
                    assertEquals(10, vMUS.rows(), "Rows");
                    assertEquals(10, vMUS.cols(), "Cols");
                    if (vM instanceof Relative)
                    {
                        assertEquals(0.0, ((FloatMatrixRel<?, ?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                    }
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
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(className + "Unit");
            for (int dataset : new int[] {1, 2})
            {
                int rows = 20;
                int cols = 50;
                float[][] testValues = dataset == 1 ? FLOATMATRIX.denseRectArrays(rows, cols, true)
                        : FLOATMATRIX.sparseRectArrays(rows, cols, true);

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
                    FloatSIMatrix siv = new FloatSIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                    final FloatSIMatrix sivf = siv.clone();
                    compareValues(testValues, siv.getValuesSI());
                    assertEquals(storageType, siv.getStorageType(), "StorageType must match");
                    assertEquals(cardinality, siv.cardinality(), "Cardinality");
                    assertEquals(zSum, siv.zSum().getSI(), zSum * 0.0001, "zSum");
                    assertEquals(FloatSIScalar.class, siv.getScalarClass(), "getScalarClass return SIScalar");
                    UnitTest.testFail(() -> sivf.setSI(0, 0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    UnitTest.testFail(() -> sivf.setInUnit(0, 0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    UnitTest.testFail(() -> sivf.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    FloatSIMatrix mutable = siv.mutable();
                    assertTrue(siv.equals(siv), "matrix is equal to itself");
                    assertTrue(siv.equals(mutable), "matrix and mutable matrix are considered equal");
                    assertTrue(mutable.equals(siv), "matrix and mutable matrix are considered equal (symmetry)");
                    assertFalse(siv.equals(null), "matrix is not equal to null");
                    assertFalse(siv.equals("hello world"), "matrix is not equal to some other object");
                    mutable.setSI(0, 0, 0);
                    mutable.setInUnit(0, 0, 0);
                    UnitTest.testFail(() -> siv.mutable().setSI(-1, 0, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    UnitTest.testFail(() -> siv.mutable().setSI(0, -1, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    UnitTest.testFail(() -> siv.mutable().setSI(rows, 0, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    UnitTest.testFail(() -> siv.mutable().setSI(0, cols, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    mutable.setSI(rows - 1, 0, 0);
                    mutable.setSI(0, cols - 1, 0);
                    mutable = sivf.mutable();
                    mutable.ceil();
                    assertFalse(sivf.equals(mutable), "matrix is not equal to ceil of matrix");
                    for (int i = 0; i < testValues.length; i++)
                    {
                        for (int j = 0; j < testValues[0].length; j++)
                        {
                            assertEquals(Math.ceil(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "ceil");
                        }
                    }
                    FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                    UnitTest.testFail(() -> immutable.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    UnitTest.testFail(() -> immutable.floor(), "float matrix should be immutable", ValueRuntimeException.class);
                    UnitTest.testFail(() -> immutable.rint(), "float matrix should be immutable", ValueRuntimeException.class);
                    UnitTest.testFail(() -> immutable.neg(), "float matrix should be immutable", ValueRuntimeException.class);
                    mutable = sivf.mutable();
                    mutable.floor();
                    for (int r = 0; r < testValues.length; r++)
                    {
                        for (int c = 0; c < testValues[0].length; c++)
                        {
                            assertEquals(Math.floor(testValues[r][c]), mutable.getInUnit(r, c), 0.001, "floor");
                        }
                    }
                    mutable = sivf.mutable();
                    mutable.rint();
                    for (int r = 0; r < testValues.length; r++)
                    {
                        for (int c = 0; c < testValues[0].length; c++)
                        {
                            assertEquals(Math.rint(testValues[r][c]), mutable.getInUnit(r, c), 0.001, "rint");
                        }
                    }
                    mutable = sivf.mutable();
                    mutable.neg();
                    for (int r = 0; r < testValues.length; r++)
                    {
                        for (int c = 0; c < testValues[0].length; c++)
                        {
                            assertEquals(-testValues[r][c], mutable.getInUnit(r, c), 0.001, "neg");
                        }
                    }
                }
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
        float[][] testValues = FLOATMATRIX.denseRectArrays(30, 20, true);
        FloatAbsoluteTemperature[][] at = new FloatAbsoluteTemperature[30][20];
        for (int r = 0; r < testValues.length; r++)
        {
            for (int c = 0; c < testValues[0].length; c++)
            {
                FloatAbsoluteTemperature value = new FloatAbsoluteTemperature(testValues[r][c], AbsoluteTemperatureUnit.KELVIN);
                at[r][c] = value;
            }
        }

        new FloatAbsoluteTemperatureMatrix(testValues, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        UnitTest.testFail(
                () -> new FloatAbsoluteTemperatureMatrix((float[][]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(() -> new FloatAbsoluteTemperatureMatrix(testValues, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(() -> new FloatAbsoluteTemperatureMatrix(testValues, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        UnitTest.testFail(() -> new FloatAbsoluteTemperatureMatrix((FloatAbsoluteTemperature[][]) null,
                AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        UnitTest.testFail(() -> new FloatAbsoluteTemperatureMatrix(at, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(() -> new FloatAbsoluteTemperatureMatrix(at, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        UnitTest.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        (Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>) null,
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        (Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>) null,
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(() -> new FloatAbsoluteTemperatureMatrix(
                new ArrayList<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>(), null, 10, 10,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        new ArrayList<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, null),
                "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        new ArrayList<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, -1, 10, StorageType.SPARSE),
                "negative rows should have thrown an exception", ValueRuntimeException.class);
        UnitTest.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        new ArrayList<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, 10, -1, StorageType.SPARSE),
                "negative cols should have thrown an exception", ValueRuntimeException.class);
        Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>> list = new ArrayList<>();
        list.add(new FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>(20, 20, 273.15f));
        UnitTest.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(list, AbsoluteTemperatureUnit.KELVIN, 5, 30, StorageType.SPARSE),
                "too small rows should have thrown an exception", ValueRuntimeException.class);
        UnitTest.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(list, AbsoluteTemperatureUnit.KELVIN, 30, 5, StorageType.SPARSE),
                "too small cols should have thrown an exception", ValueRuntimeException.class);

        UnitTest.testFail(
                () -> list.add(new FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>(-20, 20, 273.15f)),
                "negative row should have thrown an exception", ValueRuntimeException.class);
        UnitTest.testFail(
                () -> list.add(new FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>(20, -20, 273.15f)),
                "negative col should have thrown an exception", ValueRuntimeException.class);

        Quantity<?> quantity = Quantities.INSTANCE.getQuantity("AbsoluteTemperature" + "Unit");
        new FloatSIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
        UnitTest.testFail(() -> new FloatSIMatrix((float[][]) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(() -> new FloatSIMatrix(testValues, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        UnitTest.testFail(() -> new FloatSIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), null),
                "null pointer should have thrown an exception", NullPointerException.class);
    }

    /**
     * Compare two float arrays.
     * @param reference the reference values
     * @param got float[][] the values that should match the reference values
     */
    public void compareValues(final float[][] reference, final float[][] got)
    {
        assertEquals(reference.length, got.length, "length of reference must equal length of result ");
        for (int i = 0; i < reference.length; i++)
        {
            assertEquals(reference[i].length, got[i].length, "length of reference[i] must equal length of result[i] ");
            for (int j = 0; j < reference[i].length; j++)
            {
                assertEquals(reference[i][j], got[i][j], 0.001, "value at index " + i + "," + j + " must match");
            }
        }
    }

    /**
     * Compare two float arrays with factor and offset (derived from a scale).
     * @param scale the scale
     * @param reference the reference values
     * @param got float[][] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final float[][] reference, final float[][] got)
    {
        assertEquals(reference.length, got.length, "length of reference must equal length of result ");
        if (scale instanceof GradeScale)
        {
            return; // too difficult; for now
        }
        float offset = (float) scale.toStandardUnit(0);
        float factor = (float) (scale.toStandardUnit(1) - offset);
        for (int i = 0; i < reference.length; i++)
        {
            assertEquals(reference[i].length, got[i].length, "length of reference[i] must equal length of result[i] ");
            for (int j = 0; j < reference[i].length; j++)
            {
                assertEquals(reference[i][j] * factor + offset, got[i][j], 0.001,
                        "value at index " + i + "," + j + " must match");
            }
        }
    }

    /**
     * Compare two float arrays with factor and offset (derived from a scale).
     * @param scale the scale
     * @param reference the reference values
     * @param got float[][] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final Collection<FloatSparseValue<?, ?>> reference,
            final float[][] got)
    {
        if (scale instanceof GradeScale)
        {
            return; // too difficult; for now
        }
        float offset = (float) scale.toStandardUnit(0);
        float factor = (float) (scale.toStandardUnit(1) - offset);

        Map<Integer, Map<Integer, Float>> values = new HashMap<>();
        for (FloatSparseValue<?, ?> value : reference)
        {
            int r = value.getRow();
            assertTrue(r >= 0 && r < got.length);
            int c = value.getColumn();
            assertTrue(c >= 0 && c < got[0].length);
            Map<Integer, Float> rowMap = values.get(r);
            if (rowMap == null)
            {
                rowMap = new HashMap<>();
                values.put(r, rowMap);
            }
            rowMap.put(c, value.getValueSI());
        }

        for (int r = 0; r < got.length; r++)
        {
            for (int c = 0; c < got[r].length; c++)
            {
                float value = values.get(r) == null ? 0.0f : values.get(r).get(c) == null ? 0 : values.get(r).get(c);
                assertEquals(value * factor + offset, got[r][c], 0.001, "value at index " + r + "," + c + " must match");
            }
        }
    }

}
