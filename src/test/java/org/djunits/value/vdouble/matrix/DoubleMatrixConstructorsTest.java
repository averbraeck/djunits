package org.djunits.value.vdouble.matrix;

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
import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.djutils.exceptions.Try;
import org.junit.jupiter.api.Test;

/**
 * Test constructors of DoubleMatrix.
 */
public class DoubleMatrixConstructorsTest
{
    /**
     * test double[][] constructors.
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
    public void testDoubleConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
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
            Class<?> scalarClass = Class.forName("org.djunits.value.vdouble.scalar." + scalarName);
            Class<?> vectorClass = Class.forName("org.djunits.value.vdouble.vector." + scalarName + "Vector");
            for (int dataset : new int[] {1, 2})
            {
                double[][] testValues =
                        dataset == 1 ? DOUBLEMATRIX.denseRectArrays(50, 50, true) : DOUBLEMATRIX.sparseRectArrays(50, 50, true);

                int cardinality = 0;
                double zSum = 0.0;
                for (int i = 0; i < testValues.length; i++)
                {
                    for (int j = 0; j < testValues[0].length; j++)
                    {
                        double value = testValues[i][j];
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
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorDUS = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(double[][].class, unitClass, StorageType.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorDU = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(double[][].class, unitClass);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorDS = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(double[][].class, StorageType.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorD = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(double[][].class);

                    // initialize matrices
                    DoubleMatrix<?, ?, ?, ?> vDUS = constructorDUS.newInstance(testValues, standardUnit, storageType);
                    assertEquals(storageType, vDUS.getStorageType(), "StorageType must match");
                    DoubleMatrix<?, ?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                    assertEquals(StorageType.DENSE, vDU.getStorageType(), "StorageType must be DENSE");
                    DoubleMatrix<?, ?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                    assertEquals(storageType, vDS.getStorageType(), "StorageType must match");
                    DoubleMatrix<?, ?, ?, ?> vD = constructorD.newInstance(new Object[] {testValues});
                    assertEquals(StorageType.DENSE, vD.getStorageType(), "StorageType must be DENSE");

                    for (DoubleMatrix<?, ?, ?, ?> doubleMatrix : new DoubleMatrix[] {vDUS, vDU, vDS, vD})
                    {
                        compareValuesWithScale(standardUnit.getScale(), testValues, doubleMatrix.getValuesSI());
                        assertEquals(standardUnit, doubleMatrix.getDisplayUnit(), "Unit must match");
                        assertEquals(cardinality, doubleMatrix.cardinality(), "Cardinality");

                        assertEquals(vectorClass, doubleMatrix.getVectorClass(), "VectorClass must match");
                        assertEquals(scalarClass, doubleMatrix.getScalarClass(), "ScalarClass must match");
                        Method imMethod =
                                doubleMatrix.getClass().getMethod("instantiateMatrix", DoubleMatrixData.class, unitClass);
                        assertEquals(doubleMatrix, imMethod.invoke(doubleMatrix,
                                DoubleMatrixData.instantiate(testValues, IdentityScale.SCALE, storageType), standardUnit),
                                "instantiateMatrix must match");
                        Method ivMethod =
                                doubleMatrix.getClass().getMethod("instantiateVector", DoubleVectorData.class, unitClass);
                        assertEquals(vectorClass,
                                ivMethod.invoke(doubleMatrix, DoubleVectorData.instantiate(new double[] {1, 2, 0, 0, 3, 4},
                                        IdentityScale.SCALE, storageType), standardUnit).getClass(),
                                "instantiateVector class must match");
                        Method isMethod = doubleMatrix.getClass().getMethod("instantiateScalarSI", double.class, unitClass);
                        assertEquals(scalarClass, isMethod.invoke(doubleMatrix, 3.14, standardUnit).getClass(),
                                "instantiateScalarSI class must match");

                        if (doubleMatrix instanceof Relative)
                        {
                            assertEquals(zSum, ((DoubleMatrixRel<?, ?, ?, ?>) doubleMatrix).zSum().getSI(), 0.001, "zSum");
                        }
                        Try.testFail(() -> doubleMatrix.setSI(0, 0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.setInUnit(0, 0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.ceil(), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        DoubleMatrix<?, ?, ?, ?> mutable = doubleMatrix.mutable();
                        assertTrue(mutable.isMutable(), "mutable double matrix is mutable");
                        mutable.setSI(0, 0, 0);
                        mutable.setInUnit(0, 0, 0);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(-1, 0, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(0, -1, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(testValues.length, 0, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(0, testValues.length, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        mutable.setSI(testValues.length - 1, 0, 0);
                        mutable.setSI(0, testValues.length - 1, 0);
                        mutable = doubleMatrix.mutable();
                        mutable.ceil();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.ceil(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "ceil");
                            }
                        }
                        DoubleMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        Try.testFail(() -> immutable.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.floor(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.rint(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.neg(), "double matrix should be immutable", ValueRuntimeException.class);
                        mutable = doubleMatrix.mutable().mutable();
                        mutable.floor();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.floor(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "floor");
                            }
                        }
                        mutable = doubleMatrix.mutable();
                        mutable.rint();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.rint(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "rint");
                            }
                        }
                        mutable = doubleMatrix.mutable();
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
                double[][] testValues =
                        dataset == 1 ? DOUBLEMATRIX.denseRectArrays(50, 50, true) : DOUBLEMATRIX.sparseRectArrays(50, 50, true);
                Class<?> scalarClass = CLASSNAMES.doubleScalarClass(scalarName);
                Object[][] scalarValues = (Object[][]) Array.newInstance(scalarClass, testValues.length, testValues[0].length);
                Class<?> scalarArrayClass = scalarValues.getClass();
                Constructor<DoubleScalar<?, ?>> constructorScalar =
                        (Constructor<DoubleScalar<?, ?>>) scalarClass.getConstructor(double.class, unitClass);

                int cardinality = 0;
                double zSum = 0.0;
                for (int i = 0; i < testValues.length; i++)
                {
                    for (int j = 0; j < testValues[0].length; j++)
                    {
                        double value = testValues[i][j];
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
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorLUS = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(scalarArrayClass, unitClass, StorageType.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorLU = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(scalarArrayClass, unitClass);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorLS = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(scalarArrayClass, StorageType.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorL = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(scalarArrayClass);

                    // initialize matrices
                    DoubleMatrix<?, ?, ?, ?> vLUS = constructorLUS.newInstance(scalarValues, standardUnit, storageType);
                    assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                    DoubleMatrix<?, ?, ?, ?> vLU = constructorLU.newInstance(scalarValues, standardUnit);
                    assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                    DoubleMatrix<?, ?, ?, ?> vLS = constructorLS.newInstance(scalarValues, storageType);
                    assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                    DoubleMatrix<?, ?, ?, ?> vL = constructorL.newInstance(new Object[] {scalarValues});
                    assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");

                    for (DoubleMatrix<?, ?, ?, ?> doubleMatrix : new DoubleMatrix[] {vLUS, vLU, vLS, vL})
                    {
                        compareValuesWithScale(standardUnit.getScale(), testValues, doubleMatrix.getValuesSI());
                        assertEquals(standardUnit, doubleMatrix.getDisplayUnit(), "Unit must match");
                        assertEquals(cardinality, doubleMatrix.cardinality(), "Cardinality");
                        if (doubleMatrix instanceof Relative)
                        {
                            assertEquals(zSum, ((DoubleMatrixRel<?, ?, ?, ?>) doubleMatrix).zSum().getSI(), 0.001, "zSum");
                        }

                        Try.testFail(() -> doubleMatrix.setSI(0, 0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.setInUnit(0, 0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.ceil(), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        DoubleMatrix<?, ?, ?, ?> mutable = doubleMatrix.mutable();
                        assertTrue(mutable.isMutable(), "mutable double matrix is mutable");
                        mutable.setSI(0, 0, 0);
                        mutable.setInUnit(0, 0, 0);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(-1, 0, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(0, -1, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(testValues.length, 0, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(0, testValues.length, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        mutable.setSI(testValues.length - 1, 0, 0);
                        mutable.setSI(0, testValues.length - 1, 0);
                        mutable = doubleMatrix.mutable();
                        mutable.ceil();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.ceil(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "ceil");
                            }
                        }
                        DoubleMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        Try.testFail(() -> immutable.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.floor(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.rint(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.neg(), "double matrix should be immutable", ValueRuntimeException.class);
                        mutable = doubleMatrix.mutable().mutable();
                        mutable.floor();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.floor(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "floor");
                            }
                        }
                        mutable = doubleMatrix.mutable();
                        mutable.rint();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals(Math.rint(testValues[i][j]), mutable.getInUnit(i, j), 0.001, "rint");
                            }
                        }
                        mutable = doubleMatrix.mutable();
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
     * test Map&lt;Integer, Double&gt; constructors.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testMapDoubleConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
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
            Class<?> scalarClass = CLASSNAMES.doubleScalarClass(scalarName);
            int rows = 50;
            int cols = 50;
            for (int dataset : new int[] {1, 2})
            {
                Collection<DoubleSparseValue<?, ?>> testValues =
                        dataset == 1 ? DOUBLEMATRIX.denseRectTuplesAnonymous(rows, cols, scalarClass, standardUnit, true)
                                : DOUBLEMATRIX.sparseRectTuplesAnonymous(rows, cols, scalarClass, standardUnit, true);

                double[][] values = new double[rows][cols];
                int cardinality = 0;
                double zSum = 0.0;
                for (DoubleSparseValue<?, ?> dsValue : testValues)
                {
                    double value = dsValue.getValueSI();
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
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorMUS =
                            (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES.doubleMatrixClass(scalarName)
                                    .getConstructor(Collection.class, unitClass, int.class, int.class, StorageType.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorMU = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(Collection.class, unitClass, int.class, int.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorMS =
                            (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES.doubleMatrixClass(scalarName)
                                    .getConstructor(Collection.class, int.class, int.class, StorageType.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorM = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(Collection.class, int.class, int.class);

                    // initialize matrices
                    DoubleMatrix<?, ?, ?, ?> vMUS =
                            constructorMUS.newInstance(testValues, standardUnit, rows, cols, storageType);
                    assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                    DoubleMatrix<?, ?, ?, ?> vMU = constructorMU.newInstance(testValues, standardUnit, rows, cols);
                    assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                    DoubleMatrix<?, ?, ?, ?> vMS = constructorMS.newInstance(testValues, rows, cols, storageType);
                    assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                    DoubleMatrix<?, ?, ?, ?> vM = constructorM.newInstance(testValues, rows, cols);
                    assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");

                    for (DoubleMatrix<?, ?, ?, ?> doubleMatrix : new DoubleMatrix[] {vMUS, vMU, vMS, vM})
                    {
                        DoubleMatrix<?, ?, ?, ?> original = doubleMatrix.clone();
                        compareValuesWithScale(standardUnit.getScale(), testValues, doubleMatrix.getValuesSI());
                        assertEquals(standardUnit, doubleMatrix.getDisplayUnit(), "Unit must match");
                        assertEquals(cardinality, doubleMatrix.cardinality(), "Cardinality");
                        if (doubleMatrix instanceof Relative)
                        {
                            assertEquals(zSum, ((DoubleMatrixRel<?, ?, ?, ?>) doubleMatrix).zSum().getSI(), 0.001, "zSum");
                        }

                        Try.testFail(() -> doubleMatrix.setSI(0, 0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.setInUnit(0, 0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.ceil(), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        DoubleMatrix<?, ?, ?, ?> mutable = doubleMatrix.mutable();
                        assertTrue(mutable.isMutable(), "mutable double matrix is mutable");
                        mutable.setSI(0, 0, 0);
                        mutable.setInUnit(0, 0, 0);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(-1, 0, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(0, -1, 0),
                                "negative index should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(rows, 0, 0),
                                "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(0, cols, 0),
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
                        DoubleMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        Try.testFail(() -> immutable.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.floor(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.rint(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.neg(), "double matrix should be immutable", ValueRuntimeException.class);
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
                    vMUS = constructorMUS.newInstance(new ArrayList<DoubleSparseValue<?, ?>>(), standardUnit, 0, 0,
                            storageType);
                    assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                    assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMUS.cardinality(), "Cardinality");
                    if (vMUS instanceof Relative)
                    {
                        assertEquals(0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                    }

                    vMU = constructorMU.newInstance(new ArrayList<DoubleSparseValue<?, ?>>(), standardUnit, 0, 0);
                    assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                    assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMU.cardinality(), "Cardinality");
                    if (vMU instanceof Relative)
                    {
                        assertEquals(0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                    }

                    vMS = constructorMS.newInstance(new ArrayList<DoubleSparseValue<?, ?>>(), 0, 0, storageType);
                    assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                    assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMS.cardinality(), "Cardinality");
                    if (vMS instanceof Relative)
                    {
                        assertEquals(0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                    }

                    vM = constructorM.newInstance(new ArrayList<DoubleSparseValue<?, ?>>(), 0, 0);
                    assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                    assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vM.cardinality(), "Cardinality");
                    if (vM instanceof Relative)
                    {
                        assertEquals(0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                    }

                    // test the empty map with a size
                    vMUS = constructorMUS.newInstance(new ArrayList<DoubleSparseValue<?, ?>>(), standardUnit, 10, 10,
                            storageType);
                    assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                    assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMUS.cardinality(), "Cardinality");
                    assertEquals(10, vMUS.rows(), "Rows");
                    assertEquals(10, vMUS.cols(), "Cols");
                    if (vMUS instanceof Relative)
                    {
                        assertEquals(0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                    }

                    vMU = constructorMU.newInstance(new ArrayList<DoubleSparseValue<?, ?>>(), standardUnit, 10, 10);
                    assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                    assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMU.cardinality(), "Cardinality");
                    assertEquals(10, vMUS.rows(), "Rows");
                    assertEquals(10, vMUS.cols(), "Cols");
                    if (vMU instanceof Relative)
                    {
                        assertEquals(0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                    }

                    vMS = constructorMS.newInstance(new ArrayList<DoubleSparseValue<?, ?>>(), 10, 10, storageType);
                    assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                    assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vMS.cardinality(), "Cardinality");
                    assertEquals(10, vMUS.rows(), "Rows");
                    assertEquals(10, vMUS.cols(), "Cols");
                    if (vMS instanceof Relative)
                    {
                        assertEquals(0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                    }

                    vM = constructorM.newInstance(new ArrayList<DoubleSparseValue<?, ?>>(), 10, 10);
                    assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                    assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                    assertEquals(0, vM.cardinality(), "Cardinality");
                    assertEquals(10, vMUS.rows(), "Rows");
                    assertEquals(10, vMUS.cols(), "Cols");
                    if (vM instanceof Relative)
                    {
                        assertEquals(0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                    }
                }
            }
        }
    }

    /**
     * Test the SIMatrix class.
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
                double[][] testValues = dataset == 1 ? DOUBLEMATRIX.denseRectArrays(rows, cols, true)
                        : DOUBLEMATRIX.sparseRectArrays(rows, cols, true);

                int cardinality = 0;
                double zSum = 0.0;
                for (int i = 0; i < testValues.length; i++)
                {
                    for (int j = 0; j < testValues[0].length; j++)
                    {
                        double value = testValues[i][j];
                        if (0.0 != value)
                        {
                            cardinality++;
                            zSum += value;
                        }
                    }
                }

                for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                {
                    SIMatrix siv = new SIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                    final SIMatrix sivf = siv.clone();
                    compareValues(testValues, siv.getValuesSI());
                    assertEquals(storageType, siv.getStorageType(), "StorageType must match");
                    assertEquals(cardinality, siv.cardinality(), "Cardinality");
                    assertEquals(zSum, siv.zSum().getSI(), 0.001, "zSum");
                    assertEquals(SIScalar.class, siv.getScalarClass(), "getScalarClass return SIScalar");
                    Try.testFail(() -> sivf.setSI(0, 0, 0), "double matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> sivf.setInUnit(0, 0, 0), "double matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> sivf.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                    SIMatrix mutable = siv.mutable();
                    assertTrue(siv.equals(siv), "matrix is equal to itself");
                    assertTrue(siv.equals(mutable), "matrix and mutable matrix are considered equal");
                    assertTrue(mutable.equals(siv), "matrix and mutable matrix are considered equal (symmetry)");
                    assertFalse(siv.equals(null), "matrix is not equal to null");
                    assertFalse(siv.equals("hello world"), "matrix is not equal to some other object");
                    mutable.setSI(0, 0, 0);
                    mutable.setInUnit(0, 0, 0);
                    Try.testFail(() -> siv.mutable().setSI(-1, 0, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    Try.testFail(() -> siv.mutable().setSI(0, -1, 0), "negative index should have thrown an exception",
                            IndexOutOfBoundsException.class);
                    Try.testFail(() -> siv.mutable().setSI(rows, 0, 0),
                            "index just above range should have thrown an exception", IndexOutOfBoundsException.class);
                    Try.testFail(() -> siv.mutable().setSI(0, cols, 0),
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
                    DoubleMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double matrix should be immutable", ValueRuntimeException.class);
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
        double[][] testValues = DOUBLEMATRIX.denseRectArrays(30, 20, true);
        AbsoluteTemperature[][] at = new AbsoluteTemperature[30][20];
        for (int r = 0; r < testValues.length; r++)
        {
            for (int c = 0; c < testValues[0].length; c++)
            {
                AbsoluteTemperature value = new AbsoluteTemperature(testValues[r][c], AbsoluteTemperatureUnit.KELVIN);
                at[r][c] = value;
            }
        }

        new AbsoluteTemperatureMatrix(testValues, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        Try.testFail(() -> new AbsoluteTemperatureMatrix((double[][]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(testValues, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(testValues, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(() -> new AbsoluteTemperatureMatrix((AbsoluteTemperature[][]) null, AbsoluteTemperatureUnit.KELVIN,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(at, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(at, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(
                () -> new AbsoluteTemperatureMatrix(
                        (Collection<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>>) null,
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(
                () -> new AbsoluteTemperatureMatrix(
                        (Collection<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>>) null,
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(
                new ArrayList<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>>(), null, 10, 10,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(
                () -> new AbsoluteTemperatureMatrix(
                        new ArrayList<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, 10, 10, null),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(
                () -> new AbsoluteTemperatureMatrix(
                        new ArrayList<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, -1, 10, StorageType.SPARSE),
                "negative rows should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(
                () -> new AbsoluteTemperatureMatrix(
                        new ArrayList<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>>(),
                        AbsoluteTemperatureUnit.KELVIN, 10, -1, StorageType.SPARSE),
                "negative cols should have thrown an exception", ValueRuntimeException.class);
        Collection<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>> list = new ArrayList<>();
        list.add(new DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>(20, 20, 273.15));
        Try.testFail(() -> new AbsoluteTemperatureMatrix(list, AbsoluteTemperatureUnit.KELVIN, 5, 30, StorageType.SPARSE),
                "too small rows should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(list, AbsoluteTemperatureUnit.KELVIN, 30, 5, StorageType.SPARSE),
                "too small cols should have thrown an exception", ValueRuntimeException.class);

        Try.testFail(() -> list.add(new DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>(-20, 20, 273.15)),
                "negative row should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(() -> list.add(new DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>(20, -20, 273.15)),
                "negative col should have thrown an exception", ValueRuntimeException.class);

        Quantity<?> quantity = Quantities.INSTANCE.getQuantity("AbsoluteTemperature" + "Unit");
        new SIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
        Try.testFail(() -> new SIMatrix((double[][]) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SIMatrix(testValues, null, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        Try.testFail(() -> new SIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), null),
                "null pointer should have thrown an exception", NullPointerException.class);
    }

    /**
     * Compare two double arrays.
     * @param reference double[][]; the reference values
     * @param got double[][] the values that should match the reference values
     */
    public void compareValues(final double[][] reference, final double[][] got)
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
     * Compare two double arrays with factor and offset (derived from a scale).
     * @param scale Scale; the scale
     * @param reference double[][]; the reference values
     * @param got double[][] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final double[][] reference, final double[][] got)
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
            assertEquals(reference[i].length, got[i].length, "length of reference[i] must equal length of result[i] ");
            for (int j = 0; j < reference[i].length; j++)
            {
                assertEquals(reference[i][j] * factor + offset, got[i][j], 0.001,
                        "value at index " + i + "," + j + " must match");
            }
        }
    }

    /**
     * Compare two double arrays with factor and offset (derived from a scale).
     * @param scale Scale; the scale
     * @param reference Collection&lt;DoubleSparseValue&gt;; the reference values
     * @param got double[][] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final Collection<DoubleSparseValue<?, ?>> reference,
            final double[][] got)
    {
        if (scale instanceof GradeScale)
        {
            return; // too difficult; for now
        }
        double offset = scale.toStandardUnit(0);
        double factor = scale.toStandardUnit(1) - offset;

        Map<Integer, Map<Integer, Double>> values = new HashMap<>();
        for (DoubleSparseValue<?, ?> value : reference)
        {
            int r = value.getRow();
            assertTrue(r >= 0 && r < got.length);
            int c = value.getColumn();
            assertTrue(c >= 0 && c < got[0].length);
            Map<Integer, Double> rowMap = values.get(r);
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
                double value = values.get(r) == null ? 0.0 : values.get(r).get(c) == null ? 0 : values.get(r).get(c);
                assertEquals(value * factor + offset, got[r][c], 0.001, "value at index " + r + "," + c + " must match");
            }
        }
    }

}
