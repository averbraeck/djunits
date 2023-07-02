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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
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
                    assertEquals("StorageType must match", storageType, vDUS.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vDU.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                    assertEquals("StorageType must match", storageType, vDS.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vD = constructorD.newInstance(new Object[] {testValues});
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vD.getStorageType());

                    for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vDUS, vDU, vDS, vD})
                    {
                        compareValuesWithScale(standardUnit.getScale(), testValues, floatMatrix.getValuesSI());
                        assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                        assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                        
                        assertEquals("VectorClass must match", vectorClass, floatMatrix.getVectorClass());
                        assertEquals("ScalarClass must match", scalarClass, floatMatrix.getScalarClass());
                        Method imMethod =
                                floatMatrix.getClass().getMethod("instantiateMatrix", FloatMatrixData.class, unitClass);
                        assertEquals("instantiateMatrix must match", floatMatrix, imMethod.invoke(floatMatrix,
                                FloatMatrixData.instantiate(testValues, IdentityScale.SCALE, storageType), standardUnit));
                        Method ivMethod =
                                floatMatrix.getClass().getMethod("instantiateVector", FloatVectorData.class, unitClass);
                        assertEquals("instantiateVector class must match", vectorClass, ivMethod.invoke(floatMatrix,
                                FloatVectorData.instantiate(new float[] {1f, 2f, 0f, 0f, 3f, 4f}, IdentityScale.SCALE, storageType),
                                standardUnit).getClass());
                        Method isMethod = floatMatrix.getClass().getMethod("instantiateScalarSI", float.class, unitClass);
                        assertEquals("instantiateScalarSI class must match", scalarClass,
                                isMethod.invoke(floatMatrix, 3.14f, standardUnit).getClass());

                        if (floatMatrix instanceof Relative)
                        {
                            assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?, ?>) floatMatrix).zSum().getSI(),
                                    zSum * 0.0001);
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
                        mutable.setSI(0, testValues.length - 1, 0);
                        mutable = floatMatrix.mutable();
                        mutable.ceil();
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
                    assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vLU = constructorLU.newInstance(scalarValues, standardUnit);
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vLS = constructorLS.newInstance(scalarValues, storageType);
                    assertEquals("StorageType must match", storageType, vLS.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vL = constructorL.newInstance(new Object[] {scalarValues});
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                    for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vLUS, vLU, vLS, vL})
                    {
                        compareValuesWithScale(standardUnit.getScale(), testValues, floatMatrix.getValuesSI());
                        assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                        assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                        if (floatMatrix instanceof Relative)
                        {
                            assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?, ?>) floatMatrix).zSum().getSI(), zSum * 0.001);
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
                        mutable.setSI(0, testValues.length - 1, 0);
                        mutable = floatMatrix.mutable();
                        mutable.ceil();
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
                    assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vMU = constructorMU.newInstance(testValues, standardUnit, rows, cols);
                    assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vMS = constructorMS.newInstance(testValues, rows, cols, storageType);
                    assertEquals("StorageType must match", storageType, vMS.getStorageType());
                    FloatMatrix<?, ?, ?, ?> vM = constructorM.newInstance(testValues, rows, cols);
                    assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());

                    for (FloatMatrix<?, ?, ?, ?> floatMatrix : new FloatMatrix[] {vMUS, vMU, vMS, vM})
                    {
                        FloatMatrix<?, ?, ?, ?> original = floatMatrix.clone();
                        compareValuesWithScale(standardUnit.getScale(), testValues, floatMatrix.getValuesSI());
                        assertEquals("Unit must match", standardUnit, floatMatrix.getDisplayUnit());
                        assertEquals("Cardinality", cardinality, floatMatrix.cardinality());
                        if (floatMatrix instanceof Relative)
                        {
                            assertEquals("zSum", zSum, ((FloatMatrixRel<?, ?, ?, ?>) floatMatrix).zSum().getSI(),
                                    zSum * 0.0001);
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
                        Try.testFail(() -> floatMatrix.mutable().setSI(rows, 0, 0),
                                "index just above range should have thrown an exception", ValueRuntimeException.class);
                        Try.testFail(() -> floatMatrix.mutable().setSI(0, cols, 0),
                                "index just above range should have thrown an exception", ValueRuntimeException.class);
                        mutable.setSI(cols - 1, 0, 0);
                        mutable.setSI(0, rows - 1, 0);
                        mutable = original.mutable();
                        mutable.ceil();
                        for (int r = 0; r < rows; r++)
                        {
                            for (int c = 0; c < cols; c++)
                            {
                                assertEquals("ceil", Math.ceil(values[r][c]), mutable.getInUnit(r, c), 0.001);
                            }
                        }
                        FloatMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        Try.testFail(() -> immutable.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.floor(), "float matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.rint(), "float matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.neg(), "float matrix should be immutable", ValueRuntimeException.class);
                        mutable = original.mutable();
                        mutable.floor();
                        for (int r = 0; r < rows; r++)
                        {
                            for (int c = 0; c < cols; c++)
                            {
                                assertEquals("floor", Math.floor(values[r][c]), mutable.getInUnit(r, c), 0.001);
                            }
                        }
                        mutable = original.mutable();
                        mutable.rint();
                        for (int r = 0; r < rows; r++)
                        {
                            for (int c = 0; c < cols; c++)
                            {
                                assertEquals("rint", Math.rint(values[r][c]), mutable.getInUnit(r, c), 0.001);
                            }
                        }
                        mutable = original.mutable();
                        mutable.neg();
                        for (int r = 0; r < rows; r++)
                        {
                            for (int c = 0; c < cols; c++)
                            {
                                assertEquals("neg", -values[r][c], mutable.getInUnit(r, c), 0.001);
                            }
                        }
                    }

                    // test the empty map
                    vMUS = constructorMUS.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), standardUnit, 0, 0, storageType);
                    assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                    assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                    assertEquals("Cardinality", 0, vMUS.cardinality());
                    if (vMUS instanceof Relative)
                    {
                        assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                    }

                    vMU = constructorMU.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), standardUnit, 0, 0);
                    assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                    assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                    assertEquals("Cardinality", 0, vMU.cardinality());
                    if (vMU instanceof Relative)
                    {
                        assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMU).zSum().getSI(), 0.001);
                    }

                    vMS = constructorMS.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), 0, 0, storageType);
                    assertEquals("StorageType must match", storageType, vMS.getStorageType());
                    assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                    assertEquals("Cardinality", 0, vMS.cardinality());
                    if (vMS instanceof Relative)
                    {
                        assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMS).zSum().getSI(), 0.001);
                    }

                    vM = constructorM.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), 0, 0);
                    assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                    assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                    assertEquals("Cardinality", 0, vM.cardinality());
                    if (vM instanceof Relative)
                    {
                        assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?, ?>) vM).zSum().getSI(), 0.001);
                    }

                    // test the empty map with a size
                    vMUS = constructorMUS.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), standardUnit, 10, 10,
                            storageType);
                    assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                    assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                    assertEquals("Cardinality", 0, vMUS.cardinality());
                    assertEquals("Rows", 10, vMUS.rows());
                    assertEquals("Cols", 10, vMUS.cols());
                    if (vMUS instanceof Relative)
                    {
                        assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                    }

                    vMU = constructorMU.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), standardUnit, 10, 10);
                    assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                    assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                    assertEquals("Cardinality", 0, vMU.cardinality());
                    assertEquals("Rows", 10, vMUS.rows());
                    assertEquals("Cols", 10, vMUS.cols());
                    if (vMU instanceof Relative)
                    {
                        assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMU).zSum().getSI(), 0.001);
                    }

                    vMS = constructorMS.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), 10, 10, storageType);
                    assertEquals("StorageType must match", storageType, vMS.getStorageType());
                    assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                    assertEquals("Cardinality", 0, vMS.cardinality());
                    assertEquals("Rows", 10, vMUS.rows());
                    assertEquals("Cols", 10, vMUS.cols());
                    if (vMS instanceof Relative)
                    {
                        assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?, ?>) vMS).zSum().getSI(), 0.001);
                    }

                    vM = constructorM.newInstance(new ArrayList<FloatSparseValue<?, ?>>(), 10, 10);
                    assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                    assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                    assertEquals("Cardinality", 0, vM.cardinality());
                    assertEquals("Rows", 10, vMUS.rows());
                    assertEquals("Cols", 10, vMUS.cols());
                    if (vM instanceof Relative)
                    {
                        assertEquals("zSum", 0.0, ((FloatMatrixRel<?, ?, ?, ?>) vM).zSum().getSI(), 0.001);
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
                    assertEquals("StorageType must match", storageType, siv.getStorageType());
                    assertEquals("Cardinality", cardinality, siv.cardinality());
                    assertEquals("zSum", zSum, siv.zSum().getSI(), zSum * 0.0001);
                    assertEquals("getScalarClass return SIScalar", FloatSIScalar.class, siv.getScalarClass());
                    Try.testFail(() -> sivf.setSI(0, 0, 0), "float matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> sivf.setInUnit(0, 0, 0), "float matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> sivf.ceil(), "float matrix should be immutable", ValueRuntimeException.class);
                    FloatSIMatrix mutable = siv.mutable();
                    assertTrue("matrix is equal to itself", siv.equals(siv));
                    assertTrue("matrix and mutable matrix are considered equal", siv.equals(mutable));
                    assertTrue("matrix and mutable matrix are considered equal (symmetry)", mutable.equals(siv));
                    assertFalse("matrix is not equal to null", siv.equals(null));
                    assertFalse("matrix is not equal to some other object", siv.equals("hello world"));
                    mutable.setSI(0, 0, 0);
                    mutable.setInUnit(0, 0, 0);
                    Try.testFail(() -> siv.mutable().setSI(-1, 0, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> siv.mutable().setSI(0, -1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> siv.mutable().setSI(rows, 0, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    Try.testFail(() -> siv.mutable().setSI(0, cols, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(rows - 1, 0, 0);
                    mutable.setSI(0, cols - 1, 0);
                    mutable = sivf.mutable();
                    mutable.ceil();
                    assertFalse("matrix is not equal to ceil of matrix", sivf.equals(mutable));
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
                    mutable = sivf.mutable();
                    mutable.floor();
                    for (int r = 0; r < testValues.length; r++)
                    {
                        for (int c = 0; c < testValues[0].length; c++)
                        {
                            assertEquals("floor", Math.floor(testValues[r][c]), mutable.getInUnit(r, c), 0.001);
                        }
                    }
                    mutable = sivf.mutable();
                    mutable.rint();
                    for (int r = 0; r < testValues.length; r++)
                    {
                        for (int c = 0; c < testValues[0].length; c++)
                        {
                            assertEquals("rint", Math.rint(testValues[r][c]), mutable.getInUnit(r, c), 0.001);
                        }
                    }
                    mutable = sivf.mutable();
                    mutable.neg();
                    for (int r = 0; r < testValues.length; r++)
                    {
                        for (int c = 0; c < testValues[0].length; c++)
                        {
                            assertEquals("neg", -testValues[r][c], mutable.getInUnit(r, c), 0.001);
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

        Try.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        (Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>) null,
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        (Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>) null,
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(
                new ArrayList<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>(), null, 10, 10,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        new ArrayList<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, null),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        new ArrayList<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, -1, 10, StorageType.SPARSE),
                "negative rows should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(
                () -> new FloatAbsoluteTemperatureMatrix(
                        new ArrayList<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, 10, -1, StorageType.SPARSE),
                "negative cols should have thrown an exception", ValueRuntimeException.class);
        Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>> list = new ArrayList<>();
        list.add(new FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>(20, 20, 273.15f));
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(list, AbsoluteTemperatureUnit.KELVIN, 5, 30, StorageType.SPARSE),
                "too small rows should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(() -> new FloatAbsoluteTemperatureMatrix(list, AbsoluteTemperatureUnit.KELVIN, 30, 5, StorageType.SPARSE),
                "too small cols should have thrown an exception", ValueRuntimeException.class);

        Try.testFail(() -> list.add(new FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>(-20, 20, 273.15f)),
                "negative row should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(() -> list.add(new FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>(20, -20, 273.15f)),
                "negative col should have thrown an exception", ValueRuntimeException.class);

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
        float offset = (float) scale.toStandardUnit(0);
        float factor = (float) (scale.toStandardUnit(1) - offset);
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

    /**
     * Compare two float arrays with factor and offset (derived from a scale).
     * @param scale Scale; the scale
     * @param reference Collection&lt;FloatSparseValue&gt;; the reference values
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
                assertEquals("value at index " + r + "," + c + " must match", value * factor + offset, got[r][c], 0.001);
            }
        }
    }

}
