package org.djunits.value.vdouble.matrix;

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
import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djutils.exceptions.Try;
import org.junit.Test;

/**
 * Test constructors of DoubleMatrix.
 */
public class DoubleMatrixConstructorsTest
{
    /**
     * test double[][] constructors
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testDoubleConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
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
                        dataset == 1 ? DOUBLEMATRIX.denseRectArrays(50, 50) : DOUBLEMATRIX.sparseRectArrays(50, 50);

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

                    // initialize matrixs
                    DoubleMatrix<?, ?, ?, ?> vDUS = constructorDUS.newInstance(testValues, standardUnit, storageType);
                    assertEquals("StorageType must match", storageType, vDUS.getStorageType());
                    DoubleMatrix<?, ?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vDU.getStorageType());
                    DoubleMatrix<?, ?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                    assertEquals("StorageType must match", storageType, vDS.getStorageType());
                    DoubleMatrix<?, ?, ?, ?> vD = constructorD.newInstance(testValues);
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vD.getStorageType());

                    for (DoubleMatrix<?, ?, ?, ?> doubleMatrix : new DoubleMatrix[] {vDUS, vDU, vDS, vD})
                    {
                        compareValuesWithScale(standardUnit.getScale(), testValues, doubleMatrix.getValuesSI());
                        assertEquals("Unit must match", standardUnit, doubleMatrix.getDisplayUnit());
                        assertEquals("Cardinality", cardinality, doubleMatrix.cardinality());
                        if (doubleMatrix instanceof Relative)
                        {
                            assertEquals("zSum", zSum, ((DoubleMatrixRel<?, ?, ?, ?>) doubleMatrix).zSum().getSI(), 0.001);
                        }

                        Try.testFail(() -> doubleMatrix.setSI(0, 0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.setInUnit(0, 0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.ceil(), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        DoubleMatrix<?, ?, ?, ?> mutable = doubleMatrix.mutable();
                        assertTrue("mutable double matrix is mutable", mutable.isMutable());
                        mutable.setSI(0, 0, 0);
                        mutable.setInUnit(0, 0, 0);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(-1, 0, 0),
                                "negative index should have thrown an exception", ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(0, -1, 0),
                                "negative index should have thrown an exception", ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(testValues.length, 0, 0),
                                "index just above range should have thrown an exception", ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(0, testValues.length, 0),
                                "index just above range should have thrown an exception", ValueRuntimeException.class);
                        mutable.setSI(testValues.length - 1, 0, 0);
                        mutable.setSI(0, testValues.length - 1, 0);
                        mutable.ceil();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals("ceil", Math.ceil(testValues[i][j]), mutable.getInUnit(i, j), 0.001);
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
                                assertEquals("floor", Math.floor(testValues[i][j]), mutable.getInUnit(i, j), 0.001);
                            }
                        }
                        mutable = doubleMatrix.mutable();
                        mutable.rint();
                        for (int i = 0; i < testValues.length; i++)
                        {
                            for (int j = 0; j < testValues[0].length; j++)
                            {
                                assertEquals("rint", Math.rint(testValues[i][j]), mutable.getInUnit(i, j), 0.001);
                            }
                        }
                        mutable = doubleMatrix.mutable();
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
                double[][] testValues =
                        dataset == 1 ? DOUBLEMATRIX.denseRectArrays(50, 50) : DOUBLEMATRIX.sparseRectArrays(50, 50);
                Class<?> scalarClass = CLASSNAMES.doubleScalarClass(scalarName);
                Object[][] testValues = (Object[][]) Array.newInstance(scalarClass, doubleValues.length);
                Class<?> scalarArrayClass = testValues.getClass();
                Constructor<DoubleScalar<?, ?>> constructorScalar =
                        (Constructor<DoubleScalar<?, ?>>) scalarClass.getConstructor(double.class, unitClass);

                int cardinality = 0;
                double zSum = 0.0;
                for (int index = 0; index < doubleValues.length; index++)
                {
                    double value = doubleValues[index];
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
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorLUS = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(scalarArrayClass, unitClass, StorageType.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorLU = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(scalarArrayClass, unitClass);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorLS = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(scalarArrayClass, StorageType.class);
                    Constructor<DoubleMatrix<?, ?, ?, ?>> constructorL = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                            .doubleMatrixClass(scalarName).getConstructor(scalarArrayClass);

                    // initialize matrixs
                    DoubleMatrix<?, ?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                    assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                    DoubleMatrix<?, ?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                    DoubleMatrix<?, ?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                    assertEquals("StorageType must match", storageType, vLS.getStorageType());
                    DoubleMatrix<?, ?, ?, ?> vL = constructorL.newInstance(new Object[][] {testValues});
                    assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                    for (DoubleMatrix<?, ?, ?, ?> doubleMatrix : new DoubleMatrix[] {vLUS, vLU, vLS, vL})
                    {
                        compareValuesWithScale(standardUnit.getScale(), doubleValues, doubleMatrix.getValuesSI());
                        assertEquals("Unit must match", standardUnit, doubleMatrix.getDisplayUnit());
                        assertEquals("Cardinality", cardinality, doubleMatrix.cardinality());
                        if (doubleMatrix instanceof Relative)
                        {
                            assertEquals("zSum", zSum, ((DoubleMatrixRel<?, ?, ?, ?>) doubleMatrix).zSum().getSI(), 0.001);
                        }
                        assertEquals("scalarClass must match", scalarClass, doubleMatrix.getScalarClass());
                        Method instantiateMethod = doubleMatrix.getClass().getDeclaredMethod("instantiateMatrix",
                                DoubleMatrixData.class, unitClass);
                        DoubleMatrix<?, ?, ?, ?> vData = (DoubleMatrix<?, ?, ?, ?>) instantiateMethod.invoke(doubleMatrix,
                                DoubleMatrixData.instantiate(doubleValues, IdentityScale.SCALE, storageType), standardUnit);
                        compareValuesWithScale(standardUnit.getScale(), doubleValues, vData.getValuesSI());

                        Try.testFail(() -> doubleMatrix.setSI(0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.setInUnit(0, 0), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.ceil(), "double matrix should be immutable",
                                ValueRuntimeException.class);
                        DoubleMatrix<?, ?, ?, ?> mutable = doubleMatrix.mutable();
                        assertTrue("mutable double matrix is mutable", mutable.isMutable());
                        mutable.setSI(0, 0);
                        mutable.setInUnit(0, 0);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(-1, 0),
                                "negative index should have thrown an exception", ValueRuntimeException.class);
                        Try.testFail(() -> doubleMatrix.mutable().setSI(doubleValues.length, 0),
                                "index just above range should have thrown an exception", ValueRuntimeException.class);
                        mutable.setSI(doubleValues.length - 1, 0);
                        mutable.ceil();
                        for (int index = 0; index < doubleValues.length; index++)
                        {
                            assertEquals("ceil", Math.ceil(doubleValues[index]), mutable.getInUnit(index), 0.001);
                        }
                        DoubleMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                        Try.testFail(() -> immutable.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.floor(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.rint(), "double matrix should be immutable", ValueRuntimeException.class);
                        Try.testFail(() -> immutable.neg(), "double matrix should be immutable", ValueRuntimeException.class);
                        mutable = doubleMatrix.mutable().mutable();
                        mutable.floor();
                        for (int index = 0; index < doubleValues.length; index++)
                        {
                            assertEquals("floor", Math.floor(doubleValues[index]), mutable.getInUnit(index), 0.001);
                        }
                        mutable = doubleMatrix.mutable();
                        mutable.rint();
                        for (int index = 0; index < doubleValues.length; index++)
                        {
                            assertEquals("rint", Math.rint(doubleValues[index]), mutable.getInUnit(index), 0.001);
                        }
                        mutable = doubleMatrix.mutable();
                        mutable.neg();
                        for (int index = 0; index < doubleValues.length; index++)
                        {
                            assertEquals("neg", -doubleValues[index], mutable.getInUnit(index), 0.001);
                        }
                    }
                }
            }
        }
    }

    /**
     * test Map<Integer, Double> constructors
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
            double[][] doubleValues = new double[][] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            double[][] allValues = new double[][] {0, 123.456d, 0, 0, 234.567d, 0, 0, 0, 0, 0};
            int size = 10;
            SortedMap<Integer, Double> testValues = new TreeMap<>();

            int cardinality = 0;
            double zSum = 0.0;
            for (int index = 0; index < doubleValues.length; index++)
            {
                double value = doubleValues[index];
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
                Constructor<DoubleMatrix<?, ?, ?, ?>> constructorMUS = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .doubleMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, unitClass, StorageType.class);
                Constructor<DoubleMatrix<?, ?, ?, ?>> constructorMU = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .doubleMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, unitClass);
                Constructor<DoubleMatrix<?, ?, ?, ?>> constructorMS = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .doubleMatrixClass(scalarName).getConstructor(SortedMap.class, int.class, StorageType.class);
                Constructor<DoubleMatrix<?, ?, ?, ?>> constructorM = (Constructor<DoubleMatrix<?, ?, ?, ?>>) CLASSNAMES
                        .doubleMatrixClass(scalarName).getConstructor(SortedMap.class, int.class);

                // initialize matrixs
                DoubleMatrix<?, ?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                DoubleMatrix<?, ?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                DoubleMatrix<?, ?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                DoubleMatrix<?, ?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());

                for (DoubleMatrix<?, ?, ?, ?> doubleMatrix : new DoubleMatrix[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, doubleMatrix.getValuesSI());
                    assertEquals("Unit must match", standardUnit, doubleMatrix.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, doubleMatrix.cardinality());
                    assertEquals("Size", size, doubleMatrix.size());
                    if (doubleMatrix instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((DoubleMatrixRel<?, ?, ?, ?>) doubleMatrix).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> doubleMatrix.setSI(0, 0), "double matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleMatrix.setInUnit(0, 0), "double matrix should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleMatrix.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                    DoubleMatrix<?, ?, ?, ?> mutable = doubleMatrix.mutable();
                    assertTrue("mutable double matrix is mutable", mutable.isMutable());
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> doubleMatrix.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleMatrix.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("ceil", Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    DoubleMatrix<?, ?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double matrix should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double matrix should be immutable", ValueRuntimeException.class);
                    mutable = doubleMatrix.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleMatrix.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleMatrix.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("neg", -allValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleMatrix.iterator(); iterator.hasNext();)
                    {
                        DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), allValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty map
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Double>(), 0, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Double>(), 0, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Double>(), 0, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Double>(), 0);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vM).zSum().getSI(), 0.001);
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Double>(), 10, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                assertEquals("Size", 10, vMUS.size());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Double>(), 10, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                assertEquals("Size", 10, vMU.size());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Double>(), 10, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                assertEquals("Size", 10, vMS.size());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Double>(), 10);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                assertEquals("Size", 10, vM.size());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((DoubleMatrixRel<?, ?, ?, ?>) vM).zSum().getSI(), 0.001);
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
            // System.out.println("class name is " + className);
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(className + "Unit");
            // double
            @SuppressWarnings("rawtypes")
            Unit standardUnit = quantity.getStandardUnit();
            double[][] testValues = new double[][] {0, 123.456d, 0, 0, -234.567d, 0};
            int cardinality = 0;
            double zSum = 0;
            List<Double> list = new ArrayList<>();
            SortedMap<Integer, Double> map = new TreeMap<>();
            for (int index = 0; index < testValues.length; index++)
            {
                double value = testValues[index];
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
                SIMatrix siv = new SIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                final SIMatrix sivf = siv;
                compareValues(testValues, siv.getValuesSI());
                assertEquals("StorageType must match", storageType, siv.getStorageType());
                assertEquals("Cardinality", cardinality, siv.cardinality());
                assertEquals("zSum", zSum, siv.zSum().getSI(), 0.001);
                assertEquals("getScalarClass return SIScalar", SIScalar.class, siv.getScalarClass());
                Try.testFail(() -> sivf.ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                SIMatrix mutable = siv.mutable();
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
                Try.testFail(() -> sivf.immutable().abs(), "double matrix should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().ceil(), "double matrix should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().floor(), "double matrix should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().rint(), "double matrix should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().neg(), "double matrix should be immutable", ValueRuntimeException.class);

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
                    DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
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
                siv = new SIMatrix(map, testValues.length, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = new SIMatrix(list, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = SIMatrix.of(testValues, standardUnit.getQuantity().getSiDimensions().toString(true, true, true),
                        storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = SIMatrix.of(list, quantity.getSiDimensions().toString(true, true, true), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = SIMatrix.of(map, quantity.getSiDimensions().toString(true, true, true), testValues.length, storageType);
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
        double[][] testValues = new double[][] {0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0};
        AbsoluteTemperature[][] at = new AbsoluteTemperature[testValues.length];
        List<AbsoluteTemperature> al = new ArrayList<>();
        SortedMap<Integer, AbsoluteTemperature> map = new TreeMap<>();
        for (int i = 0; i < testValues.length; i++)
        {
            AbsoluteTemperature value = new AbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.KELVIN);
            at[i] = value;
            al.add(value);
            if (0.0 != value.si)
            {
                map.put(i, value);
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

        Try.testFail(() -> new AbsoluteTemperatureMatrix((List<AbsoluteTemperature>) null, AbsoluteTemperatureUnit.KELVIN,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(
                () -> new AbsoluteTemperatureMatrix((List<Double>) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(al, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(al, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(
                () -> new AbsoluteTemperatureMatrix((SortedMap<Integer, AbsoluteTemperature>) null, 10,
                        AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix((SortedMap<Integer, Double>) null, 10, AbsoluteTemperatureUnit.KELVIN,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(map, 10, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(map, 10, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(map, -1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "negative length should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(() -> new AbsoluteTemperatureMatrix(map, 1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "too small length should have thrown an exception", ValueRuntimeException.class);

        map.put(-1, at[0]);
        Try.testFail(
                () -> new AbsoluteTemperatureMatrix(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "too small length should have thrown an exception", ValueRuntimeException.class);

        map.remove(-1); // Remove the offending entry
        Quantity<?> quantity = Quantities.INSTANCE.getQuantity("AbsoluteTemperature" + "Unit");
        new SIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
        Try.testFail(() -> new SIMatrix((double[][]) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SIMatrix((List<Double>) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SIMatrix(testValues, null, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        Try.testFail(() -> new SIMatrix(testValues, SIUnit.of(quantity.getSiDimensions()), null),
                "null pointer should have thrown an exception", NullPointerException.class);
    }

    /**
     * Test that parallelized operations work.
     */
    @Test
    public void parallelTest()
    {
        double[][] testValues = new double[4000];
        double[][] testValuesCelsius = new double[4000];
        for (int i = 0; i < testValues.length; i++)
        {
            testValues[i] = i % 3 != 0 ? 0 : (100.0 + i);
            testValuesCelsius[i] = testValues[i] + 273.15;
        }
        List<AbsoluteTemperature> al = new ArrayList<>();
        List<Double> dl = new ArrayList<>();
        SortedMap<Integer, AbsoluteTemperature> map = new TreeMap<>();
        AbsoluteTemperature[][] at = new AbsoluteTemperature[testValues.length];
        for (int i = 0; i < testValues.length; i++)
        {
            AbsoluteTemperature value = new AbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.KELVIN);
            al.add(value);
            dl.add(testValues[i]);
            if (0.0 != value.si)
            {
                map.put(i, value);
            }
            value = new AbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
            at[i] = value;
        }
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            AbsoluteTemperatureMatrix atv =
                    new AbsoluteTemperatureMatrix(testValues, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValuesWithScale(AbsoluteTemperatureUnit.KELVIN.getScale(), testValues, atv.getValuesSI());
            atv = new AbsoluteTemperatureMatrix(al, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new AbsoluteTemperatureMatrix(dl, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new AbsoluteTemperatureMatrix(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new AbsoluteTemperatureMatrix(at, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT));
        }
    }

    /**
     * Compare two double arrays.
     * @param reference double[][]; the reference values
     * @param got double[][] the values that should match the reference values
     */
    public void compareValues(final double[][] reference, final double[][] got)
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
     * Compare two double arrays with factor and offset (derived from a scale).
     * @param scale Scale; the scale
     * @param reference double[][]; the reference values
     * @param got double[][] the values that should match the reference values
     */
    public void compareValuesWithScale(final Scale scale, final double[][] reference, final double[][] got)
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
