package org.djunits.value.vdouble.vector;

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

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.quantity.Quantities;
import org.djunits.unit.quantity.Quantity;
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
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarInterface;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.base.DoubleVectorInterface;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.djutils.exceptions.Try;
import org.junit.Test;

/**
 * Test constructors of DoubleVector.
 */
public class DoubleVectorConstructorsTest
{
    /**
     * test double[] constructors
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
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorDUS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(double[].class, unitClass, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorDU =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(double[].class, unitClass);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorDS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(double[].class, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorD =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(double[].class);

                // initialize vectors
                DoubleVectorInterface<?, ?, ?> vDUS = constructorDUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vDUS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vDU = constructorDU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vDU.getStorageType());
                DoubleVectorInterface<?, ?, ?> vDS = constructorDS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vDS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vD = constructorD.newInstance(testValues);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vD.getStorageType());

                for (DoubleVectorInterface<?, ?, ?> doubleVector : new DoubleVectorInterface[] {vDUS, vDU, vDS, vD})
                {
                    compareValuesWithScale(standardUnit.getScale(), testValues, doubleVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, doubleVector.cardinality());
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractDoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVectorInterface<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue("mutable double vector is mutable", mutable.isMutable());
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
                        assertEquals("ceil", Math.ceil(testValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    DoubleVectorInterface<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(testValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(testValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < testValues.length; index++)
                    {
                        assertEquals("neg", -testValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        AbstractDoubleScalar<?, ?> s = (AbstractDoubleScalar<?, ?>) iterator.next();
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            Class<?> scalarClass = CLASSNAMES.doubleScalarClass(scalarName);
            Object[] testValues = (Object[]) Array.newInstance(scalarClass, doubleValues.length);
            Class<?> scalarArrayClass = testValues.getClass();
            Constructor<DoubleScalarInterface<?, ?>> constructorScalar =
                    (Constructor<DoubleScalarInterface<?, ?>>) scalarClass.getConstructor(double.class, unitClass);

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
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLUS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(scalarArrayClass, unitClass, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLU =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(scalarArrayClass, unitClass);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(scalarArrayClass, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorL =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(scalarArrayClass);

                // initialize vectors
                DoubleVectorInterface<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                DoubleVectorInterface<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vL = constructorL.newInstance(new Object[] {testValues});
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (DoubleVectorInterface<?, ?, ?> doubleVector : new DoubleVectorInterface[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), doubleValues, doubleVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, doubleVector.cardinality());
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractDoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001);
                    }
                    assertEquals("scalarClass must match", scalarClass, doubleVector.getScalarClass());
                    Method instantiateMethod =
                            doubleVector.getClass().getDeclaredMethod("instantiateVector", DoubleVectorData.class, unitClass);
                    DoubleVectorInterface<?, ?, ?> vData =
                            (DoubleVectorInterface<?, ?, ?>) instantiateMethod.invoke(doubleVector,
                                    DoubleVectorData.instantiate(doubleValues, IdentityScale.SCALE, storageType), standardUnit);
                    compareValuesWithScale(standardUnit.getScale(), doubleValues, vData.getValuesSI());

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVectorInterface<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue("mutable double vector is mutable", mutable.isMutable());
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
                        assertEquals("ceil", Math.ceil(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    DoubleVectorInterface<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("neg", -doubleValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        AbstractDoubleScalar<?, ?> s = (AbstractDoubleScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), doubleValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }
            }
        }
    }

    /**
     * test List<Double> constructors
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
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLUS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(List.class, unitClass, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLU =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(List.class, unitClass);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(List.class, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorL = (Constructor<
                        DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName).getConstructor(List.class);

                // initialize vectors
                DoubleVectorInterface<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                DoubleVectorInterface<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (DoubleVectorInterface<?, ?, ?> doubleVector : new DoubleVectorInterface[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), doubleValues, doubleVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, doubleVector.cardinality());
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractDoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVectorInterface<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue("mutable double vector is mutable", mutable.isMutable());
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
                        assertEquals("ceil", Math.ceil(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    DoubleVectorInterface<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("neg", -doubleValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        AbstractDoubleScalar<?, ?> s = (AbstractDoubleScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), doubleValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty list
                vLUS = constructorLUS.newInstance(new ArrayList<Double>(), standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLUS.cardinality());
                if (vLUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001);
                }

                vLU = constructorLU.newInstance(new ArrayList<Double>(), standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                assertEquals("Unit must match", standardUnit, vLU.getDisplayUnit());
                assertEquals("Cardinality", 0, vLU.cardinality());
                if (vLU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vLU).zSum().getSI(), 0.001);
                }

                vLS = constructorLS.newInstance(new ArrayList<Double>(), storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLS.cardinality());
                if (vLS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vLS).zSum().getSI(), 0.001);
                }

                vL = constructorL.newInstance(new ArrayList<Double>());
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());
                assertEquals("Unit must match", standardUnit, vL.getDisplayUnit());
                assertEquals("Cardinality", 0, vL.cardinality());
                if (vL instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vL).zSum().getSI(), 0.001);
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            List<DoubleScalarInterface<?, ?>> testValues = new ArrayList<>();
            Constructor<DoubleScalarInterface<?, ?>> constructorScalar = (Constructor<DoubleScalarInterface<?, ?>>) CLASSNAMES
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
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLUS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(List.class, unitClass, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLU =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(List.class, unitClass);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorLS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(List.class, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorL = (Constructor<
                        DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName).getConstructor(List.class);

                // initialize vectors
                DoubleVectorInterface<?, ?, ?> vLUS = constructorLUS.newInstance(testValues, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vLU = constructorLU.newInstance(testValues, standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                DoubleVectorInterface<?, ?, ?> vLS = constructorLS.newInstance(testValues, storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vL = constructorL.newInstance(testValues);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());

                for (DoubleVectorInterface<?, ?, ?> doubleVector : new DoubleVectorInterface[] {vLUS, vLU, vLS, vL})
                {
                    compareValuesWithScale(standardUnit.getScale(), doubleValues, doubleVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, doubleVector.cardinality());
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractDoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVectorInterface<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue("mutable double vector is mutable", mutable.isMutable());
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
                        assertEquals("ceil", Math.ceil(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    DoubleVectorInterface<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(doubleValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < doubleValues.length; index++)
                    {
                        assertEquals("neg", -doubleValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        AbstractDoubleScalar<?, ?> s = (AbstractDoubleScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), doubleValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty list
                vLUS = constructorLUS.newInstance(new ArrayList<DoubleScalarInterface<?, ?>>(), standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vLUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLUS.cardinality());
                if (vLUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vLUS).zSum().getSI(), 0.001);
                }

                vLU = constructorLU.newInstance(new ArrayList<DoubleScalarInterface<?, ?>>(), standardUnit);
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vLU.getStorageType());
                assertEquals("Unit must match", standardUnit, vLU.getDisplayUnit());
                assertEquals("Cardinality", 0, vLU.cardinality());
                if (vLU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vLU).zSum().getSI(), 0.001);
                }

                vLS = constructorLS.newInstance(new ArrayList<DoubleScalarInterface<?, ?>>(), storageType);
                assertEquals("StorageType must match", storageType, vLS.getStorageType());
                assertEquals("Unit must match", standardUnit, vLS.getDisplayUnit());
                assertEquals("Cardinality", 0, vLS.cardinality());
                if (vLS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vLS).zSum().getSI(), 0.001);
                }

                vL = constructorL.newInstance(new ArrayList<DoubleScalarInterface<?, ?>>());
                assertEquals("StorageType must be DENSE", StorageType.DENSE, vL.getStorageType());
                assertEquals("Unit must match", standardUnit, vL.getDisplayUnit());
                assertEquals("Cardinality", 0, vL.cardinality());
                if (vL instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vL).zSum().getSI(), 0.001);
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            double[] allValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0, 0, 0, 0};
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
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorMUS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, unitClass, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorMU =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, unitClass);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorMS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorM =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class);

                // initialize vectors
                DoubleVectorInterface<?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                DoubleVectorInterface<?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());

                for (DoubleVectorInterface<?, ?, ?> doubleVector : new DoubleVectorInterface[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, doubleVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, doubleVector.cardinality());
                    assertEquals("Size", size, doubleVector.size());
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractDoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVectorInterface<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue("mutable double vector is mutable", mutable.isMutable());
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
                        assertEquals("ceil", Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    DoubleVectorInterface<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("neg", -allValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        AbstractDoubleScalar<?, ?> s = (AbstractDoubleScalar<?, ?>) iterator.next();
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
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Double>(), 0, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Double>(), 0, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Double>(), 0);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, Double>(), 10, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                assertEquals("Size", 10, vMUS.size());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, Double>(), 10, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                assertEquals("Size", 10, vMU.size());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, Double>(), 10, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                assertEquals("Size", 10, vMS.size());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, Double>(), 10);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                assertEquals("Size", 10, vM.size());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
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
            double[] doubleValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0};
            double[] allValues = new double[] {0, 123.456d, 0, 0, 234.567d, 0, 0, 0, 0, 0};
            int size = 10;
            SortedMap<Integer, DoubleScalarInterface<?, ?>> testValues = new TreeMap<>();
            Constructor<DoubleScalarInterface<?, ?>> constructorScalar = (Constructor<DoubleScalarInterface<?, ?>>) CLASSNAMES
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
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorMUS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, unitClass, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorMU =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, unitClass);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorMS =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class, StorageType.class);
                Constructor<DoubleVectorInterface<?, ?, ?>> constructorM =
                        (Constructor<DoubleVectorInterface<?, ?, ?>>) CLASSNAMES.doubleVectorClass(scalarName)
                                .getConstructor(SortedMap.class, int.class);

                // initialize vectors
                DoubleVectorInterface<?, ?, ?> vMUS = constructorMUS.newInstance(testValues, size, standardUnit, storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vMU = constructorMU.newInstance(testValues, size, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                DoubleVectorInterface<?, ?, ?> vMS = constructorMS.newInstance(testValues, size, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                DoubleVectorInterface<?, ?, ?> vM = constructorM.newInstance(testValues, size);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());

                for (DoubleVectorInterface<?, ?, ?> doubleVector : new DoubleVectorInterface[] {vMUS, vMU, vMS, vM})
                {
                    compareValuesWithScale(standardUnit.getScale(), allValues, doubleVector.getValuesSI());
                    assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                    assertEquals("Cardinality", cardinality, doubleVector.cardinality());
                    assertEquals("Size", size, doubleVector.size());
                    if (doubleVector instanceof Relative)
                    {
                        assertEquals("zSum", zSum, ((AbstractDoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001);
                    }

                    Try.testFail(() -> doubleVector.setSI(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.setInUnit(0, 0), "double vector should be immutable",
                            ValueRuntimeException.class);
                    Try.testFail(() -> doubleVector.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    DoubleVectorInterface<?, ?, ?> mutable = doubleVector.mutable();
                    assertTrue("mutable double vector is mutable", mutable.isMutable());
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
                        assertEquals("ceil", Math.ceil(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    DoubleVectorInterface<?, ?, ?> immutable = mutable.immutable();
                    Try.testFail(() -> immutable.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.floor(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.rint(), "double vector should be immutable", ValueRuntimeException.class);
                    Try.testFail(() -> immutable.neg(), "double vector should be immutable", ValueRuntimeException.class);
                    mutable = doubleVector.mutable().mutable();
                    mutable.floor();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("floor", Math.floor(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.rint();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("rint", Math.rint(allValues[index]), mutable.getInUnit(index), 0.001);
                    }
                    mutable = doubleVector.mutable();
                    mutable.neg();
                    for (int index = 0; index < allValues.length; index++)
                    {
                        assertEquals("neg", -allValues[index], mutable.getInUnit(index), 0.001);
                    }
                    int nextIndex = 0;
                    for (Iterator<?> iterator = doubleVector.iterator(); iterator.hasNext();)
                    {
                        AbstractDoubleScalar<?, ?> s = (AbstractDoubleScalar<?, ?>) iterator.next();
                        assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                        assertEquals("value of scalar matches", s.getInUnit(), allValues[nextIndex], 0.001);
                        nextIndex++;
                    }
                }

                // test the empty list
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, DoubleScalarInterface<?, ?>>(), 0, standardUnit,
                        storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, DoubleScalarInterface<?, ?>>(), 0, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, DoubleScalarInterface<?, ?>>(), 0, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, DoubleScalarInterface<?, ?>>(), 0);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
                }

                // test the empty map with a size
                vMUS = constructorMUS.newInstance(new TreeMap<Integer, DoubleScalarInterface<?, ?>>(), 10, standardUnit,
                        storageType);
                assertEquals("StorageType must match", storageType, vMUS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMUS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMUS.cardinality());
                assertEquals("Size", 10, vMUS.size());
                if (vMUS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMUS).zSum().getSI(), 0.001);
                }

                vMU = constructorMU.newInstance(new TreeMap<Integer, DoubleScalarInterface<?, ?>>(), 10, standardUnit);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vMU.getStorageType());
                assertEquals("Unit must match", standardUnit, vMU.getDisplayUnit());
                assertEquals("Cardinality", 0, vMU.cardinality());
                assertEquals("Size", 10, vMU.size());
                if (vMU instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMU).zSum().getSI(), 0.001);
                }

                vMS = constructorMS.newInstance(new TreeMap<Integer, DoubleScalarInterface<?, ?>>(), 10, storageType);
                assertEquals("StorageType must match", storageType, vMS.getStorageType());
                assertEquals("Unit must match", standardUnit, vMS.getDisplayUnit());
                assertEquals("Cardinality", 0, vMS.cardinality());
                assertEquals("Size", 10, vMS.size());
                if (vMS instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vMS).zSum().getSI(), 0.001);
                }

                vM = constructorM.newInstance(new TreeMap<Integer, DoubleScalarInterface<?, ?>>(), 10);
                assertEquals("StorageType must be SPARSE", StorageType.SPARSE, vM.getStorageType());
                assertEquals("Unit must match", standardUnit, vM.getDisplayUnit());
                assertEquals("Cardinality", 0, vM.cardinality());
                assertEquals("Size", 10, vM.size());
                if (vM instanceof Relative)
                {
                    assertEquals("zSum", 0.0, ((AbstractDoubleVectorRel<?, ?, ?>) vM).zSum().getSI(), 0.001);
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
                SIVector siv = new SIVector(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                final SIVector sivf = siv;
                compareValues(testValues, siv.getValuesSI());
                assertEquals("StorageType must match", storageType, siv.getStorageType());
                assertEquals("Cardinality", cardinality, siv.cardinality());
                assertEquals("zSum", zSum, siv.zSum().getSI(), 0.001);
                assertEquals("getScalarClass return SIScalar", SIScalar.class, siv.getScalarClass());
                Try.testFail(() -> sivf.ceil(), "double vector should be immutable", ValueRuntimeException.class);
                SIVector mutable = siv.mutable();
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
                Try.testFail(() -> sivf.immutable().abs(), "double vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().ceil(), "double vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().floor(), "double vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().rint(), "double vector should be immutable", ValueRuntimeException.class);
                Try.testFail(() -> sivf.immutable().neg(), "double vector should be immutable", ValueRuntimeException.class);

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
                    AbstractDoubleScalar<?, ?> s = (AbstractDoubleScalar<?, ?>) iterator.next();
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
                () -> new AbsoluteTemperatureVector((SortedMap<Integer, AbsoluteTemperature>) null, 10,
                        AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE),
                "null pointer should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new AbsoluteTemperatureVector((SortedMap<Integer, Double>) null, 10, AbsoluteTemperatureUnit.KELVIN,
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
        SortedMap<Integer, AbsoluteTemperature> map = new TreeMap<>();
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
            AbsoluteTemperatureVector atv = new AbsoluteTemperatureVector(testValues, AbsoluteTemperatureUnit.KELVIN, storageType);
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
        assertEquals("length of reference must equal length of result ", reference.length, got.length);
        for (int i = 0; i < reference.length; i++)
        {
            assertEquals("value at index " + i + " must match", reference[i], got[i], 0.001);
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
