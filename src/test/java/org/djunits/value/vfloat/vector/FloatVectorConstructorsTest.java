package org.djunits.value.vfloat.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
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
import org.djunits.unit.scale.Scale;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.Relative;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Scalar;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalar;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.base.FloatVector;
import org.djunits.value.vfloat.vector.base.FloatVectorInterface;
import org.junit.Test;

/**
 * Test constructors of FloatVector.
 */
public class FloatVectorConstructorsTest
{
    /**
     * Test the constructors of FloatVector.
     * @throws ClassNotFoundException if that happens uncaught; this test has failed
     * @throws SecurityException if that happens uncaught; this test has failed
     * @throws NoSuchMethodException if that happens uncaught; this test has failed
     * @throws InvocationTargetException if that happens uncaught; this test has failed
     * @throws IllegalArgumentException if that happens uncaught; this test has failed
     * @throws IllegalAccessException if that happens uncaught; this test has failed
     * @throws InstantiationException if that happens uncaught; this test has failed
     */
    @SuppressWarnings("unchecked")
    @Test
    public void instantiatorTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
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
            // float
            @SuppressWarnings("rawtypes")
            Unit standardUnit = quantity.getStandardUnit();
            float[] testValues = new float[] { 0, 123.456f, 0, 0, 234.567f, 0, 0 };
            int cardinality = 0;
            double zSum = 0;
            List<Float> list = new ArrayList<>();
            SortedMap<Integer, Float> map = new TreeMap<>();
            SortedMap<Integer, Float> notQuiteSparseMap = new TreeMap<>();
            for (int index = 0; index < testValues.length; index++)
            {
                float value = testValues[index];
                if (0.0 != value)
                {
                    cardinality++;
                    zSum += value;
                    map.put(index, value);
                    notQuiteSparseMap.put(index, value);
                }
                else if (index % 2 == 0)
                {
                    notQuiteSparseMap.put(index, value);
                }
                list.add(value);
            }
            for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
            {
                FloatVectorInterface<?, ?, ?> floatVector = FloatVector.instantiate(testValues, standardUnit, storageType);
                // System.out.println(floatVector);
                compareValuesWithScale(standardUnit.getScale(), testValues, floatVector.getValuesSI());
                assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                assertEquals("StorageType must match", storageType, floatVector.getStorageType());
                assertEquals("Cardinality", cardinality, floatVector.cardinality());
                if (floatVector instanceof Relative)
                {
                    assertEquals("zSum", zSum, ((AbstractFloatVectorRel<?, ?, ?>) floatVector).zSum().getSI(), 0.001);
                }
                String scalarClassName = "org.djunits.value.vfloat.scalar.Float" + className;
                Class<?> scalarClass = Class.forName(scalarClassName);
                assertEquals("getScalarClass", scalarClass, floatVector.getScalarClass());
                compareValues(testValues, floatVector.getValuesInUnit());
                floatVector = FloatVector.instantiateSI(testValues, standardUnit, storageType);
                compareValuesWithScale(standardUnit.getScale(), testValues, floatVector.getValuesSI());
                assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                assertEquals("StorageType must match", storageType, floatVector.getStorageType());
                assertFalse("float vector is immutable by default", floatVector.isMutable());
                try
                {
                    floatVector.setSI(0, 0);
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    floatVector.setInUnit(0, 0);
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    floatVector.ceil();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                FloatVectorInterface<?, ?, ?> mutable = floatVector.mutable();
                assertTrue("mutable float vector is mutable", mutable.isMutable());
                mutable.setSI(0, 0);
                mutable.setInUnit(0, 0);
                try
                {
                    mutable.setSI(-1, 0);
                    fail("negative index should have thrown an excpetion");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    mutable.setSI(testValues.length, 0);
                    fail("negative index should have thrown an excpetion");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                mutable.setSI(testValues.length - 1, 0);
                mutable.ceil();
                for (int index = 0; index < testValues.length; index++)
                {
                    assertEquals("ceil", Math.ceil(testValues[index]), mutable.getInUnit(index), 0.001);
                }
                FloatVectorInterface<?, ?, ?> immutable = floatVector.immutable();
                try
                {
                    immutable.ceil();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    floatVector.floor();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    floatVector.rint();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    floatVector.neg();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
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
                // This does not compile
                // FloatVectorInterface<?, ?, ?> secondary = FloatVector.instantiateAnonymous(floatVector.getScalars(),
                // standardUnit);
                floatVector = FloatVector.instantiate(list, standardUnit, storageType);
                assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                compareValues(testValues, floatVector.getValuesSI());
                floatVector = FloatVector.instantiate(map, testValues.length, standardUnit, storageType);
                compareValues(testValues, floatVector.getValuesSI());
                floatVector = FloatVector.instantiate(notQuiteSparseMap, testValues.length, standardUnit, storageType);
                compareValues(testValues, floatVector.getValuesSI());
                Scalar<?, ?>[] scalarValues = floatVector.getScalars();
                assertEquals("length of array of scalars", testValues.length, scalarValues.length);
                for (int i = 0; i < testValues.length; i++)
                {
                    Scalar<?, ?> s = scalarValues[i];
                    assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                    assertEquals("value of scalar matches", ((AbstractFloatScalar<?, ?>) s).getSI(), testValues[i], 0.001);
                }
                floatVector = FloatVector.instantiateSI(list, standardUnit, storageType);
                assertEquals("Unit must match", standardUnit, floatVector.getDisplayUnit());
                compareValues(testValues, floatVector.getValuesSI());
            }
        }
    }

    /**
     * Test constructors of array, list, map with the FloatAbsoluteTemperature unit.
     */
    @Test
    public void instantiateListTest()
    {
        float[] testValues = new float[] { 0, 123.456f, 0, -273.15f, -273.15f, 0, -273.15f, 234.567f, 0, 0 };
        int cardinality = 0;
        int offsetCardinality = testValues.length;
        FloatAbsoluteTemperature[] at = new FloatAbsoluteTemperature[testValues.length];
        List<FloatAbsoluteTemperature> al = new ArrayList<>();
        List<Float> afl = new ArrayList<>();
        SortedMap<Integer, FloatAbsoluteTemperature> map = new TreeMap<>();
        SortedMap<Integer, FloatAbsoluteTemperature> notQuiteSparseMap = new TreeMap<>();
        SortedMap<Integer, Float> mapf = new TreeMap<>();
        for (int i = 0; i < testValues.length; i++)
        {
            afl.add(testValues[i]);
            FloatAbsoluteTemperature value = new FloatAbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.KELVIN);
            at[i] = value;
            al.add(value);
            if (0.0 != value.si)
            {
                cardinality++;
                map.put(i, value);
                mapf.put(i, value.si);
                notQuiteSparseMap.put(i, value);
            }
            else if (i % 2 == 0)
            {
                notQuiteSparseMap.put(i, value);
            }
            if (AbsoluteTemperatureUnit.DEGREE_CELSIUS.getScale().toStandardUnit(testValues[i]) == 0)
            {
                offsetCardinality--;
            }
        }
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            for (AbsoluteTemperatureUnit temperatureUnit : new AbsoluteTemperatureUnit[] { AbsoluteTemperatureUnit.KELVIN,
                    AbsoluteTemperatureUnit.DEGREE_CELSIUS })
            {
                float offset = temperatureUnit.equals(AbsoluteTemperatureUnit.KELVIN) ? 0f : 273.15f;

                FloatAbsoluteTemperatureVector atv = FloatVector.instantiate(testValues, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : testValues.length, atv.cardinality());
                atv = FloatVector.instantiate(testValues, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());

                atv = FloatVector.instantiateSI(testValues, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = FloatVector.instantiateSI(testValues, temperatureUnit, storageType, FloatAbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = FloatVector.instantiateSI(afl, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = FloatVector.instantiateSI(afl, temperatureUnit, storageType, FloatAbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = FloatVector.instantiate(afl, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());
                atv = FloatVector.instantiate(afl, temperatureUnit, storageType, FloatAbsoluteTemperatureVector.class);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());

                atv = FloatVector.instantiate(at, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = FloatVector.instantiate(at, temperatureUnit, storageType, FloatAbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = FloatVector.instantiateList(al, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = FloatVector.instantiateList(al, temperatureUnit, storageType, FloatAbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = FloatVector.instantiateMap(map, testValues.length, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = FloatVector.instantiateMap(map, testValues.length, temperatureUnit, storageType,
                        FloatAbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = FloatVector.instantiateMap(notQuiteSparseMap, testValues.length, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = FloatVector.instantiateMap(notQuiteSparseMap, testValues.length, temperatureUnit, storageType,
                        FloatAbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = FloatVector.instantiate(mapf, testValues.length, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());
                atv = FloatVector.instantiate(mapf, testValues.length, temperatureUnit, storageType,
                        FloatAbsoluteTemperatureVector.class);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());

                atv = FloatVector.instantiateSI(mapf, testValues.length, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = FloatVector.instantiateSI(mapf, testValues.length, temperatureUnit, storageType,
                        FloatAbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = FloatVector.instantiate(testValues, temperatureUnit, storageType);
                for (int i = 0; i < testValues.length; i++)
                {
                    assertEquals("getInUnit returns value in specified unit", testValues[i], atv.getInUnit(i, temperatureUnit),
                            0.001);
                }

                try
                {
                    atv.getInUnit(-1, temperatureUnit);
                    fail("negative index should have thrown a ValueRuntimeException");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    atv.getInUnit(testValues.length, temperatureUnit);
                    fail("index above range should have thrown a ValueRuntimeException");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    atv.set(0, al.get(0));
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    atv.set(-1, al.get(0));
                    fail("negative index should have thrown a ValueRuntimeException");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    atv.set(testValues.length, al.get(0));
                    fail("index above range should have thrown a ValueRuntimeException");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    atv.setInUnit(0, 0, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
                    fail("should have been immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                FloatAbsoluteTemperatureVector matv = atv.mutable();
                matv.setInUnit(0, 0, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
                assertEquals("Setting temp in F should have stored equivalent value in K", matv.getSI(0), 255.37, 0.01);
                try
                {
                    matv.setInUnit(-1, 0, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
                    fail("negative index should have thrown an exception");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                matv.setInUnit(testValues.length - 1, 0, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
                try
                {
                    matv.setInUnit(testValues.length, 0, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
                    fail("too large index should have thrown an exception");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                // More complete memory test; somewhat inspired on mats+
                matv = atv.mutable();
                for (int index = 0; index < testValues.length; index++)
                {
                    // read and check; change, read again and check again
                    float v = matv.getSI(index);
                    assertEquals("initial value is returned", testValues[index] + offset, v, 0.001);
                    v += 100;
                    matv.setSI(index, v);
                    v = matv.getSI(index);
                    assertEquals("initial value is returned", testValues[index] + 100 + offset, v, 0.001);
                }
                for (int index = testValues.length; --index >= 0;)
                {
                    // read and check, change and check again
                    float v = matv.getSI(index);
                    assertEquals("initial value is returned", testValues[index] + 100 + offset, v, 0.001);
                    v -= 100;
                    matv.setSI(index, v);
                    v = matv.getSI(index);
                    assertEquals("initial value is returned", testValues[index] + offset, v, 0.001);
                }
                assertEquals("Cardinality should be back to original value", 0 == offset ? cardinality : offsetCardinality,
                        matv.cardinality());
                for (int index = 0; index < testValues.length; index++)
                {
                    // read and check; change, read again and check again
                    float v = matv.getSI(index);
                    assertEquals("initial value is returned", testValues[index] + offset, v, 0.001);
                    v += 100;
                    matv.setSI(index, v);
                    v = matv.getSI(index);
                    assertEquals("initial value is returned", testValues[index] + 100 + offset, v, 0.001);
                }

                // Check that we can set on the mutable version
                matv.set(0, al.get(0));
                try
                {
                    matv.set(-1, al.get(0));
                    fail("negative index should have thrown a ValueRuntimeException");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    matv.set(testValues.length, al.get(0));
                    fail("index above range should have thrown a ValueRuntimeException");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                FloatAbsoluteTemperatureVector dense = atv.toDense();
                assertTrue("Should be dense", dense.isDense());
                assertFalse("Should be not be sparse", dense.isSparse());
                assertEquals("Unit should be same", atv.getDisplayUnit(), dense.getDisplayUnit());
                compareValues(dense.getValuesInUnit(), atv.getValuesInUnit());
                assertFalse("Should be immutable", dense.isMutable());

                FloatAbsoluteTemperatureVector sparse = atv.toSparse();
                assertTrue("Should be sparse", sparse.isSparse());
                assertFalse("Should not be be dense", sparse.isDense());
                assertEquals("Unit should be same", atv.getDisplayUnit(), sparse.getDisplayUnit());
                compareValues(sparse.getValuesInUnit(), atv.getValuesInUnit());
                assertFalse("Should be immutable", sparse.isMutable());

                FloatAbsoluteTemperatureVector copy = atv.clone();
                assertEquals("storagetype should not have changed", atv.getStorageType(), copy.getStorageType());
                assertEquals("unit should be same", atv.getDisplayUnit(), copy.getDisplayUnit());
                compareValues(atv.getValuesInUnit(), copy.getValuesInUnit());
                assertFalse("copy is immutable", copy.isMutable());

                copy = matv.clone();
                assertEquals("storagetype should not have changed", matv.getStorageType(), copy.getStorageType());
                assertEquals("unit should be same", matv.getDisplayUnit(), copy.getDisplayUnit());
                compareValues(matv.getValuesInUnit(), copy.getValuesInUnit());
                assertTrue("copy is immutable", copy.isMutable());
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
    public void siVectorTest() throws ValueRuntimeException, UnitException, ClassNotFoundException
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String className : CLASSNAMES.ALL_NODIM_LIST)
        {
            // system.out.println("class name is " + className);
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(className + "Unit");
            // float
            @SuppressWarnings("rawtypes")
            Unit standardUnit = quantity.getStandardUnit();
            float[] testValues = new float[] { 0, 123.456f, 0, 0, 234.567f, 0 };
            int cardinality = 0;
            double zSum = 0;
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
            for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
            {
                FloatSIVector siv = FloatVector.instantiate(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                // system.out.println(siv);
                compareValues(testValues, siv.getValuesSI());
                // assertEquals("Underlying standardUnit must match", standardUnit, siv.getUnit());
                assertEquals("StorageType must match", storageType, siv.getStorageType());
                assertEquals("Cardinality", cardinality, siv.cardinality());
                assertEquals("zSum", zSum, siv.zSum().getSI(), 0.001);
                assertEquals("getScalarClass return FloatSIScalar", FloatSIScalar.class, siv.getScalarClass());
                try
                {
                    siv.ceil();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
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
                FloatVectorInterface<?, ?, ?> immutable = siv.immutable();
                try
                {
                    immutable.abs();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    immutable.ceil();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    siv.floor();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    siv.rint();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    siv.neg();
                    fail("float vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

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
                siv = FloatSIVector.instantiate(map, testValues.length, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = FloatVector.instantiate(list, SIUnit.of(quantity.getSiDimensions()), storageType);
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
        float[] testValues = new float[] { 0f, 123.456f, 0f, 0f, 234.567f, 0f, 0f };
        FloatAbsoluteTemperature[] at = new FloatAbsoluteTemperature[testValues.length];
        List<FloatAbsoluteTemperature> al = new ArrayList<>();
        SortedMap<Integer, FloatAbsoluteTemperature> map = new TreeMap<>();
        for (int i = 0; i < testValues.length; i++)
        {
            FloatAbsoluteTemperature value = new FloatAbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.KELVIN);
            at[i] = value;
            al.add(value);
            if (0.0f != value.si)
            {
                map.put(i, value);
            }
        }

        FloatVector.instantiate(testValues, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        try
        {
            FloatVector.instantiate((float[]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException vre)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiate(testValues, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiate(testValues, AbsoluteTemperatureUnit.KELVIN, null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        FloatVector.instantiate(at, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        try
        {
            FloatVector.instantiate((FloatAbsoluteTemperature[]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiate(at, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiate(at, AbsoluteTemperatureUnit.KELVIN, null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        FloatVector.instantiateList(al, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        try
        {
            FloatVector.instantiateList((List<FloatAbsoluteTemperature>) null, AbsoluteTemperatureUnit.KELVIN,
                    StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiateList(al, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiateList(al, AbsoluteTemperatureUnit.KELVIN, null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        FloatVector.instantiateMap(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        try
        {
            FloatVector.instantiateMap((SortedMap<Integer, FloatAbsoluteTemperature>) null, testValues.length,
                    AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiateMap(map, testValues.length, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiateMap(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiateMap(map, -1, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("negative length should have thrown an exception");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiateMap(map, 1, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("bad length should have thrown an exception");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }
        map.put(-1, at[0]);
        try
        {
            FloatVector.instantiateMap(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("bad entry in map should have thrown an exception");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }
        map.remove(-1); // Remove the offending entry
        Quantity<?> quantity = Quantities.INSTANCE.getQuantity("AbsoluteTemperature" + "Unit");
        FloatVector.instantiate(testValues, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
        try
        {
            FloatVector.instantiate((float[]) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException vre)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiate(testValues, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        try
        {
            FloatVector.instantiate(testValues, SIUnit.of(quantity.getSiDimensions()), null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
    }

    /**
     * Test that parallelized operations work.
     */
    @Test
    public void parallelTest()
    {
        float[] testValues = new float[4000];
        for (int i = 0; i < testValues.length; i++)
        {
            testValues[i] = i % 3 != 0 ? 0f : (100.0f + i);
        }
        List<FloatAbsoluteTemperature> al = new ArrayList<>();
        List<Float> fl = new ArrayList<>();
        FloatAbsoluteTemperature[] at = new FloatAbsoluteTemperature[testValues.length];
        SortedMap<Integer, FloatAbsoluteTemperature> map = new TreeMap<>();
        for (int i = 0; i < testValues.length; i++)
        {
            FloatAbsoluteTemperature value = new FloatAbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.KELVIN);
            al.add(value);
            fl.add(testValues[i]);
            if (0.0 != value.si)
            {
                map.put(i, value);
            }
            value = new FloatAbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
            at[i] = value;
        }
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            for (AbsoluteTemperatureUnit temperatureUnit : new AbsoluteTemperatureUnit[] { AbsoluteTemperatureUnit.KELVIN,
                    AbsoluteTemperatureUnit.DEGREE_CELSIUS })
            {
                FloatAbsoluteTemperatureVector atv = FloatVector.instantiate(testValues, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                atv = FloatVector.instantiateList(al, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                atv = FloatVector.instantiateSI(fl, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                atv = FloatVector.instantiateMap(map, testValues.length, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                atv = FloatVector.instantiate(at, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT));
            }
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
     * Compare two double arrays with factor and offset (derived from a scale).
     * @param scale Scale; the scale
     * @param reference double[]; the reference values
     * @param got double[] the values that should match the reference values
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
