package org.djunits.value.vdouble.vector;

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
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.djutils.exceptions.Try;
import org.junit.jupiter.api.Test;

/**
 * Test constructors of DoubleVector.
 */
public class DoubleVectorConstructorsTest
{
    /**
     * test double[] constructors.
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
            double[] testValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};

            int cardinality = 0;
            double zSum = 0.0;
            for (int index = 0; index < testValues.length; index++)
            {
                double value = testValues[index];
                if (0.0 != value)
                {
                    cardinality++;
                    zSum += value;
                }
            }

            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                // get the constructors
                Constructor<DoubleVector<?, ?, ?>> constructorDUS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(double[].class, unitClass, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorDU = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(double[].class, unitClass);
                Constructor<DoubleVector<?, ?, ?>> constructorDS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(double[].class, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorD = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(double[].class);

                // initialize vectors
                DoubleVector<?, ?, ?> vDUS = constructorDUS.newInstance(testValues, standardUnit, storageType);
                assertEquals(storageType, vDUS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                assertEquals(StorageType.DENSE, vDU.getStorageType(), "StorageType must be DENSE");
                DoubleVector<?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                assertEquals(storageType, vDS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vD = constructorD.newInstance(testValues);
                assertEquals(StorageType.DENSE, vD.getStorageType(), "StorageType must be DENSE");

                for (DoubleVector<?, ?, ?> doubleVector : new DoubleVector[] {vDUS, vDU, vDS, vD})
                {
                    compareValuesWithScale(standardUnit.getScale(), testValues, doubleVector.getValuesSI());
                    assertEquals(standardUnit, doubleVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, doubleVector.cardinality(), "Cardinality");
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals(zSum, ((DoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVector<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable double vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> doubleVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.mutable().setSI(testValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(testValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals(Math.ceil(testValues[index]), mutable.getInUnit(index), 0.001, "ceil");
                    }
                    DoubleVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals(Math.floor(testValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals(Math.rint(testValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals(-testValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            Class<?> scalarClass = CLASSNAMES.doubleScalarClass(scalarName);
            Object[] testValues = (Object[]) Array.newInstance(scalarClass, doubleValues.length);
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
                Constructor<DoubleVector<?, ?, ?>> constructorLUS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(scalarArrayClass, unitClass, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorLU = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(scalarArrayClass, unitClass);
                Constructor<DoubleVector<?, ?, ?>> constructorLS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(scalarArrayClass, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorL = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(scalarArrayClass);

                // initialize vectors
                DoubleVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                DoubleVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vL = constructorL.newInstance(new Object[] {testValues});
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");

                for (DoubleVector<?, ?, ?> doubleVector : new DoubleVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), doubleValues, doubleVector.getValuesSI());
                    assertEquals(standardUnit, doubleVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, doubleVector.cardinality(), "Cardinality");
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals(zSum, ((DoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001, "zSum");
                    }
                    assertEquals(scalarClass, doubleVector.getScalarClass(), "scalarClass must match");
                    Method instantiateMethod =
                            doubleVector.getClass().getDeclaredMethod("instantiateVector", DoubleVectorData.class, unitClass);
                    DoubleVector<?, ?, ?> vData = (DoubleVector<?, ?, ?>) instantiateMethod.invoke(doubleVector,
                            DoubleVectorData.instantiate(doubleValues, IdentityScale.SCALE, storageType), standardUnit);
                    compareValuesWithScale(standardUnit.getScale(), doubleValues, vData.getValuesSI());

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVector<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable double vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> doubleVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.mutable().setSI(doubleValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(doubleValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.ceil(doubleValues[index]), mutable.getInUnit(index), 0.001, "ceil");
                    }
                    DoubleVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.floor(doubleValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.rint(doubleValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(-doubleValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), doubleValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }
            }
        }
    }

    /**
     * test List&lt;Double&gt; constructors.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws InstantiationException on error
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testListDoubleConstructors() throws NoSuchMethodException, SecurityException, InstantiationException,
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            List<Double> testValues = new ArrayList<>();

            int cardinality = 0;
            double zSum = 0.0;
            for (int index = 0; index < doubleValues.length; index++)
            {
                double value = doubleValues[index];
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
                Constructor<DoubleVector<?, ?, ?>> constructorLUS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(List.class, unitClass, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorLU = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(List.class, unitClass);
                Constructor<DoubleVector<?, ?, ?>> constructorLS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(List.class, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorL = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(List.class);

                // initialize vectors
                DoubleVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                DoubleVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");

                for (DoubleVector<?, ?, ?> doubleVector : new DoubleVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), doubleValues, doubleVector.getValuesSI());
                    assertEquals(standardUnit, doubleVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, doubleVector.cardinality(), "Cardinality");
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals(zSum, ((DoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVector<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable double vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> doubleVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.mutable().setSI(doubleValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(doubleValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.ceil(doubleValues[index]), mutable.getInUnit(index), 0.001, "ceil");
                    }
                    DoubleVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.floor(doubleValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.rint(doubleValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(-doubleValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), doubleValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }

                // test the empty list
                vLUS = constructorLUS.newInstance(new ArrayList<Double>(), standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vLUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLUS.cardinality(), "Cardinality");
                if (vLUS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001, "zSum");
                }

                vLU = constructorLU.newInstance(new ArrayList<Double>(), standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                assertEquals(standardUnit, vLU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLU.cardinality(), "Cardinality");
                if (vLU instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vLU).zSum().getSI(), 0.001, "zSum");
                }

                vLS = constructorLS.newInstance(new ArrayList<Double>(), storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vLS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLS.cardinality(), "Cardinality");
                if (vLS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vLS).zSum().getSI(), 0.001, "zSum");
                }

                vL = constructorL.newInstance(new ArrayList<Double>());
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");
                assertEquals(standardUnit, vL.getDisplayUnit(), "Unit must match");
                assertEquals(0, vL.cardinality(), "Cardinality");
                if (vL instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vL).zSum().getSI(), 0.001, "zSum");
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            List<DoubleScalar<?, ?>> testValues = new ArrayList<>();
            Constructor<DoubleScalar<?, ?>> constructorScalar = (Constructor<DoubleScalar<?, ?>>) CLASSNAMES
                    .doubleScalarClass(scalarName).getConstructor(double.class, unitClass);

            int cardinality = 0;
            double zSum = 0.0;
            for (int index = 0; index < doubleValues.length; index++)
            {
                double value = doubleValues[index];
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
                Constructor<DoubleVector<?, ?, ?>> constructorLUS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(List.class, unitClass, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorLU = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(List.class, unitClass);
                Constructor<DoubleVector<?, ?, ?>> constructorLS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(List.class, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorL = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(List.class);

                // initialize vectors
                DoubleVector<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                DoubleVector<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");

                for (DoubleVector<?, ?, ?> doubleVector : new DoubleVector[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), doubleValues, doubleVector.getValuesSI());
                    assertEquals(standardUnit, doubleVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, doubleVector.cardinality(), "Cardinality");
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals(zSum, ((DoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVector<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable double vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> doubleVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.mutable().setSI(doubleValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(doubleValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.ceil(doubleValues[index]), mutable.getInUnit(index), 0.001, "ceil");
                    }
                    DoubleVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.floor(doubleValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(Math.rint(doubleValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals(-doubleValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), doubleValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }

                // test the empty list
                vLUS = constructorLUS.newInstance(new ArrayList<DoubleScalar<?, ?>>(), standardUnit, storageType);
                assertEquals(storageType, vLUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vLUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLUS.cardinality(), "Cardinality");
                if (vLUS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001, "zSum");
                }

                vLU = constructorLU.newInstance(new ArrayList<DoubleScalar<?, ?>>(), standardUnit);
                assertEquals(StorageType.DENSE, vLU.getStorageType(), "StorageType must be DENSE");
                assertEquals(standardUnit, vLU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLU.cardinality(), "Cardinality");
                if (vLU instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vLU).zSum().getSI(), 0.001, "zSum");
                }

                vLS = constructorLS.newInstance(new ArrayList<DoubleScalar<?, ?>>(), storageType);
                assertEquals(storageType, vLS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vLS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vLS.cardinality(), "Cardinality");
                if (vLS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vLS).zSum().getSI(), 0.001, "zSum");
                }

                vL = constructorL.newInstance(new ArrayList<DoubleScalar<?, ?>>());
                assertEquals(StorageType.DENSE, vL.getStorageType(), "StorageType must be DENSE");
                assertEquals(standardUnit, vL.getDisplayUnit(), "Unit must match");
                assertEquals(0, vL.cardinality(), "Cardinality");
                if (vL instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vL).zSum().getSI(), 0.001, "zSum");
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            double[] allValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0, 0, 0, 0};
            int size = 10;
            Map<Integer, Double> testValues = new TreeMap<>();

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
                Constructor<DoubleVector<?, ?, ?>> constructorMUS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(Map.class, int.class, unitClass, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorMU = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(Map.class, int.class, unitClass);
                Constructor<DoubleVector<?, ?, ?>> constructorMS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(Map.class, int.class, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorM = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(Map.class, int.class);

                // initialize vectors
                DoubleVector<?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                DoubleVector<?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");

                for (DoubleVector<?, ?, ?> doubleVector : new DoubleVector[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, doubleVector.getValuesSI());
                    assertEquals(standardUnit, doubleVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, doubleVector.cardinality(), "Cardinality");
                    assertEquals(size, doubleVector.size(), "Size");
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals(zSum, ((DoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVector<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable double vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> doubleVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001, "ceil");
                    }
                    DoubleVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.floor(allValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.rint(allValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(-allValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), allValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }

                // test the empty map
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Double>(), 0, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMUS.cardinality(), "Cardinality");
                if (vMUS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Double>(), 0, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMU.cardinality(), "Cardinality");
                if (vMU instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Double>(), 0, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMS.cardinality(), "Cardinality");
                if (vMS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Double>(), 0);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                assertEquals(0, vM.cardinality(), "Cardinality");
                if (vM instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Double>(), 10, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMUS.cardinality(), "Cardinality");
                assertEquals(10, vMUS.size(), "Size");
                if (vMUS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Double>(), 10, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMU.cardinality(), "Cardinality");
                assertEquals(10, vMU.size(), "Size");
                if (vMU instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Double>(), 10, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMS.cardinality(), "Cardinality");
                assertEquals(10, vMS.size(), "Size");
                if (vMS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Double>(), 10);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                assertEquals(0, vM.cardinality(), "Cardinality");
                assertEquals(10, vM.size(), "Size");
                if (vM instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            double[] allValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0, 0, 0, 0};
            int size = 10;
            Map<Integer, DoubleScalar<?, ?>> testValues = new TreeMap<>();
            Constructor<DoubleScalar<?, ?>> constructorScalar = (Constructor<DoubleScalar<?, ?>>) CLASSNAMES
                    .doubleScalarClass(scalarName).getConstructor(double.class, unitClass);

            int cardinality = 0;
            double zSum = 0.0;
            for (int index = 0; index < doubleValues.length; index++)
            {
                double value = doubleValues[index];
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
                Constructor<DoubleVector<?, ?, ?>> constructorMUS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(Map.class, int.class, unitClass, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorMU = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(Map.class, int.class, unitClass);
                Constructor<DoubleVector<?, ?, ?>> constructorMS = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(Map.class, int.class, StorageType.class);
                Constructor<DoubleVector<?, ?, ?>> constructorM = (Constructor<DoubleVector<?, ?, ?>>) CLASSNAMES
                        .doubleVectorClass(scalarName).getConstructor(Map.class, int.class);

                // initialize vectors
                DoubleVector<?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                DoubleVector<?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                DoubleVector<?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");

                for (DoubleVector<?, ?, ?> doubleVector : new DoubleVector[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, doubleVector.getValuesSI());
                    assertEquals(standardUnit, doubleVector.getDisplayUnit(), "Unit must match");
                    assertEquals(cardinality, doubleVector.cardinality(), "Cardinality");
                    assertEquals(size, doubleVector.size(), "Size");
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals(zSum, ((DoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001, "zSum");
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVector<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue(mutable.isMutable(), "mutable double vector is mutable");
                    mutable.setSI(0, 0);
                    mutable.setInUnit(0, 0);
                    Try.testFail(() -> doubleVector.mutable().setSI(-1, 0), "negative index should have thrown an exception",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.mutable().setSI(allValues.length, 0),
                            "index just above range should have thrown an exception", ValueRuntimeException.class);
                    mutable.setSI(allValues.length - 1, 0);
                    mutable.ceil();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001, "ceil");
                    }
                    DoubleVector<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.floor(allValues[index]), mutable.getInUnit(index), 0.001, "floor");
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(Math.rint(allValues[index]), mutable.getInUnit(index), 0.001, "rint");
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals(-allValues[index], mutable.getInUnit(index), 0.001, "neg");
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
                        assertEquals(s.getDisplayUnit(), standardUnit, "unit of scalar matches");
                        assertEquals(s.getInUnit(), allValues[nextIndex], 0.001, "value of scalar matches");
                        nextIndex++;
                    }
                }

                // test the empty list
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, DoubleScalar<?, ?>>(), 0, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMUS.cardinality(), "Cardinality");
                if (vMUS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, DoubleScalar<?, ?>>(), 0, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMU.cardinality(), "Cardinality");
                if (vMU instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, DoubleScalar<?, ?>>(), 0, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMS.cardinality(), "Cardinality");
                if (vMS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                }

                vM = constructorM.newInstance(new TreeMap<Integer, DoubleScalar<?, ?>>(), 0);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                assertEquals(0, vM.cardinality(), "Cardinality");
                if (vM instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, DoubleScalar<?, ?>>(), 10, standardUnit, storageType);
                assertEquals(storageType, vMUS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMUS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMUS.cardinality(), "Cardinality");
                assertEquals(10, vMUS.size(), "Size");
                if (vMUS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001, "zSum");
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, DoubleScalar<?, ?>>(), 10, standardUnit);
                assertEquals(StorageType.SPARSE, vMU.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vMU.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMU.cardinality(), "Cardinality");
                assertEquals(10, vMU.size(), "Size");
                if (vMU instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001, "zSum");
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, DoubleScalar<?, ?>>(), 10, storageType);
                assertEquals(storageType, vMS.getStorageType(), "StorageType must match");
                assertEquals(standardUnit, vMS.getDisplayUnit(), "Unit must match");
                assertEquals(0, vMS.cardinality(), "Cardinality");
                assertEquals(10, vMS.size(), "Size");
                if (vMS instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001, "zSum");
                }

                vM = constructorM.newInstance(new TreeMap<Integer, DoubleScalar<?, ?>>(), 10);
                assertEquals(StorageType.SPARSE, vM.getStorageType(), "StorageType must be SPARSE");
                assertEquals(standardUnit, vM.getDisplayUnit(), "Unit must match");
                assertEquals(0, vM.cardinality(), "Cardinality");
                assertEquals(10, vM.size(), "Size");
                if (vM instanceof Relative)
                {
                    assertEquals(0.0, ((DoubleVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001, "zSum");
                }
            }
        }
    }

    /**
     * Test the SIVector class.
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
            // double
            @SuppressWarnings("rawtypes")
            Unit standardUnit = quantity.getStandardUnit();
            double[] testValues = new double[] {0, 123.456d, 0, 0, -234.567d, 0};
            int cardinality = 0;
            double zSum = 0;
            List<Double> list = new ArrayList<>();
            Map<Integer, Double> map = new TreeMap<>();
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
                SIVector siv = new SIVector(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                final SIVector sivf = siv;
                compareValues(testValues, siv.getValuesSI());
                assertEquals(storageType, siv.getStorageType(), "StorageType must match");
                assertEquals(cardinality, siv.cardinality(), "Cardinality");
                assertEquals(zSum, siv.zSum().getSI(), 0.001, "zSum");
                assertEquals(SIScalar.class, siv.getScalarClass(), "getScalarClass return SIScalar");
                Try.testFail(() -> sivf.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                SIVector mutable = siv.mutable();
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
                Try.testFail(() -> sivf.immutable().abs(), "double vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().ceil(), "double vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().floor(), "double vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().rint(), "double vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().neg(), "double vector should be immutable", ValueRuntimeException.class);

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
                    DoubleScalar<?, ?> s = (DoubleScalar<?, ?>) iterator.next();
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
                siv = new SIVector(map, testValues.length, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = new SIVector(list, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = SIVector.of(testValues, standardUnit.getQuantity().getSiDimensions().toString(true, true, true),
                        storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = SIVector.of(list, quantity.getSiDimensions().toString(true, true, true), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = SIVector.of(map, quantity.getSiDimensions().toString(true, true, true), testValues.length, storageType);
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
        double[] testValues = new double[] {0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0};
        AbsoluteTemperature[] at = new AbsoluteTemperature[testValues.length];
        List<AbsoluteTemperature> al = new ArrayList<>();
        Map<Integer, AbsoluteTemperature> map = new TreeMap<>();
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

        new AbsoluteTemperatureVector(testValues, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        Try.testFail(() -> new AbsoluteTemperatureVector((double[]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(testValues, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(testValues, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(() -> new AbsoluteTemperatureVector((AbsoluteTemperature[]) null, AbsoluteTemperatureUnit.KELVIN,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(at, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(at, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(() -> new AbsoluteTemperatureVector((List<AbsoluteTemperature>) null, AbsoluteTemperatureUnit.KELVIN,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(
                () -> new AbsoluteTemperatureVector((List<Double>) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(al, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(al, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);

        Try.testFail(
                () -> new AbsoluteTemperatureVector((Map<Integer, AbsoluteTemperature>) null, 10,
                        AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector((Map<Integer, Double>) null, 10, AbsoluteTemperatureUnit.KELVIN,
                StorageType.DENSE), "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(map, 10, null, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(map, 10, AbsoluteTemperatureUnit.KELVIN, null),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(map, -1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "negative length should have thrown an exception", ValueRuntimeException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector(map, 1, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "too small length should have thrown an exception", ValueRuntimeException.class);

        map.put(-1, at[0]);
        Try.testFail(
                () -> new AbsoluteTemperatureVector(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, StorageType.SPARSE),
                "too small length should have thrown an exception", ValueRuntimeException.class);

        map.remove(-1); // Remove the offending entry
        Quantity<?> quantity = Quantities.INSTANCE.getQuantity("AbsoluteTemperature" + "Unit");
        new SIVector(testValues, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
        Try.testFail(() -> new SIVector((double[]) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SIVector((List<Double>) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SIVector(testValues, null, StorageType.DENSE), "null pointer should have thrown an exception",
                NullPointerException.class);
        Try.testFail(() -> new SIVector(testValues, SIUnit.of(quantity.getSiDimensions()), null),
                "null pointer should have thrown an exception", NullPointerException.class);
    }

    /**
     * Test that parallelized operations work.
     */
    @Test
    public void parallelTest()
    {
        double[] testValues = new double[4000];
        double[] testValuesCelsius = new double[4000];
        for (int i = 0; i < testValues.length; i++)
        {
            testValues[i] = i % 3 != 0 ? 0 : (100.0 + i);
            testValuesCelsius[i] = testValues[i] + 273.15;
        }
        List<AbsoluteTemperature> al = new ArrayList<>();
        List<Double> dl = new ArrayList<>();
        Map<Integer, AbsoluteTemperature> map = new TreeMap<>();
        AbsoluteTemperature[] at = new AbsoluteTemperature[testValues.length];
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
            AbsoluteTemperatureVector atv =
                    new AbsoluteTemperatureVector(testValues, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValuesWithScale(AbsoluteTemperatureUnit.KELVIN.getScale(), testValues, atv.getValuesSI());
            atv = new AbsoluteTemperatureVector(al, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new AbsoluteTemperatureVector(dl, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new AbsoluteTemperatureVector(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesSI());
            atv = new AbsoluteTemperatureVector(at, AbsoluteTemperatureUnit.KELVIN, storageType);
            compareValues(testValues, atv.getValuesInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT));
        }
    }

    /**
     * Compare two double arrays.
     * @param reference double[]; the reference values
     * @param got double[] the values that should match the reference values
     */
    public void compareValues(final double[] reference, final double[] got)
    {
        assertEquals(reference.length, got.length, "length of reference must equal length of result ");
        for (int i = 0; i < reference.length; i++)
        {
            assertEquals(reference[i], got[i], 0.001, "value at index " + i + " must match");
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
