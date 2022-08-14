package org.djunits.value.vdouble.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.quantity.Quantities;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.Relative;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Scalar;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalar;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vdouble.vector.base.DoubleVectorInterface;
import org.junit.Test;

/**
 * Test constructors of DoubleVector.
 */
public class DoubleVectorConstructorsTest
{
    /**
     * Test the constructors of DoubleVector.
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

        for (String scalarName : CLASSNAMES.ALL_NODIM_LIST)
        {
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(scalarName + "Unit");
            // double
            @SuppressWarnings("rawtypes")
            Unit standardUnit = quantity.getStandardUnit();
            double[] testValues = new double[] { 0, 123.456d, 0, 0, 234.567d, 0, 0 };
            int cardinality = 0;
            double zSum = 0;
            List<Double> list = new ArrayList<>();
            SortedMap<Integer, Double> map = new TreeMap<>();
            SortedMap<Integer, Double> notQuiteSparseMap = new TreeMap<>();
            for (int index = 0; index < testValues.length; index++)
            {
                double value = testValues[index];
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
                DoubleVectorInterface<?, ?, ?> doubleVector = DoubleVector.instantiate(testValues, standardUnit, storageType);
                // System.out.println(doubleVector);
                compareValuesWithScale(standardUnit.getScale(), testValues, doubleVector.getValuesSI());
                assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                assertEquals("StorageType must match", storageType, doubleVector.getStorageType());
                assertEquals("Cardinality", cardinality, doubleVector.cardinality());
                if (doubleVector instanceof Relative)
                {
                    assertEquals("zSum", zSum, ((AbstractDoubleVectorRel<?, ?, ?>) doubleVector).zSum().getSI(), 0.001);
                }
                String scalarClassName = "org.djunits.value.vdouble.scalar." + scalarName;
                Class<?> scalarClass = Class.forName(scalarClassName);
                assertEquals("getScalarClass", scalarClass, doubleVector.getScalarClass());
                compareValues(testValues, doubleVector.getValuesInUnit());
                doubleVector = DoubleVector.instantiateSI(testValues, standardUnit, storageType);
                compareValuesWithScale(standardUnit.getScale(), testValues, doubleVector.getValuesSI());
                assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                assertEquals("StorageType must match", storageType, doubleVector.getStorageType());
                assertFalse("double vector is immutable by default", doubleVector.isMutable());
                try
                {
                    doubleVector.setSI(0, 0);
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    doubleVector.setInUnit(0, 0);
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    doubleVector.ceil();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                DoubleVectorInterface<?, ?, ?> mutable = doubleVector.mutable();
                assertTrue("mutable double vector is mutable", mutable.isMutable());
                mutable.setSI(0, 0);
                mutable.setInUnit(0, 0);
                try
                {
                    mutable.setSI(-1, 0);
                    fail("negative index should have thrown an exception");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    mutable.setSI(testValues.length, 0);
                    fail("index just above range should have thrown an exception");
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
                DoubleVectorInterface<?, ?, ?> immutable = doubleVector.immutable();
                try
                {
                    immutable.ceil();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    doubleVector.floor();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    doubleVector.rint();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
                try
                {
                    doubleVector.neg();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
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
                // This does not compile
                // DoubleVectorInterface<?, ?, ?> secondary = DoubleVector.instantiateAnonymous(doubleVector.getScalars(),
                // standardUnit);
                doubleVector = DoubleVector.instantiate(list, standardUnit, storageType);
                assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                compareValuesWithScale(standardUnit.getScale(), testValues, doubleVector.getValuesSI());
                doubleVector = DoubleVector.instantiate(map, testValues.length, standardUnit, storageType);
                compareValuesWithScale(standardUnit.getScale(), testValues, doubleVector.getValuesSI());
                doubleVector = DoubleVector.instantiate(notQuiteSparseMap, testValues.length, standardUnit, storageType);
                compareValuesWithScale(standardUnit.getScale(), testValues, doubleVector.getValuesSI());
                Scalar<?, ?>[] scalarValues = doubleVector.getScalars();
                assertEquals("length of array of scalars", testValues.length, scalarValues.length);
                for (int i = 0; i < testValues.length; i++)
                {
                    Scalar<?, ?> s = scalarValues[i];
                    assertEquals("unit of scalar matches", s.getDisplayUnit(), standardUnit);
                    assertEquals("value of scalar matches", ((AbstractDoubleScalar<?, ?>) s).getSI(), testValues[i], 0.001);
                }
                doubleVector = DoubleVector.instantiateSI(list, standardUnit, storageType);
                assertEquals("Unit must match", standardUnit, doubleVector.getDisplayUnit());
                compareValuesWithScale(standardUnit.getScale(), testValues, doubleVector.getValuesSI());
            }
        }
    }

    /**
     * Test constructors of array, list, map with the AbsoluteTemperature unit.
     */
    @Test
    public void instantiateListTest()
    {
        double[] testValues = new double[] { 0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0 };
        int cardinality = 0;
        int offsetCardinality = testValues.length;
        AbsoluteTemperature[] at = new AbsoluteTemperature[testValues.length];
        List<AbsoluteTemperature> al = new ArrayList<>();
        List<Double> adl = new ArrayList<>();
        SortedMap<Integer, AbsoluteTemperature> map = new TreeMap<>();
        SortedMap<Integer, AbsoluteTemperature> notQuiteSparseMap = new TreeMap<>();
        SortedMap<Integer, Double> mapd = new TreeMap<>();
        for (int i = 0; i < testValues.length; i++)
        {
            adl.add(testValues[i]);
            AbsoluteTemperature value = new AbsoluteTemperature(testValues[i], AbsoluteTemperatureUnit.KELVIN);
            at[i] = value;
            al.add(value);
            if (0.0 != value.si)
            {
                cardinality++;
                map.put(i, value);
                mapd.put(i, value.si);
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
                double offset = temperatureUnit.equals(AbsoluteTemperatureUnit.KELVIN) ? 0 : 273.15;

                AbsoluteTemperatureVector atv = DoubleVector.instantiate(testValues, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());
                atv = DoubleVector.instantiate(testValues, temperatureUnit, storageType, AbsoluteTemperatureVector.class);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());

                atv = DoubleVector.instantiateSI(testValues, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = DoubleVector.instantiateSI(testValues, temperatureUnit, storageType, AbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = DoubleVector.instantiateSI(adl, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = DoubleVector.instantiateSI(adl, temperatureUnit, storageType, AbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = DoubleVector.instantiate(adl, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());
                atv = DoubleVector.instantiate(adl, temperatureUnit, storageType, AbsoluteTemperatureVector.class);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());

                atv = DoubleVector.instantiate(at, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = DoubleVector.instantiate(at, temperatureUnit, storageType, AbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = DoubleVector.instantiateList(al, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = DoubleVector.instantiateList(al, temperatureUnit, storageType, AbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = DoubleVector.instantiateMap(map, testValues.length, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = DoubleVector.instantiateMap(map, testValues.length, temperatureUnit, storageType,
                        AbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = DoubleVector.instantiateMap(notQuiteSparseMap, testValues.length, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = DoubleVector.instantiateMap(notQuiteSparseMap, testValues.length, temperatureUnit, storageType,
                        AbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = DoubleVector.instantiate(mapd, testValues.length, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());
                atv = DoubleVector.instantiate(mapd, testValues.length, temperatureUnit, storageType,
                        AbsoluteTemperatureVector.class);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", offset == 0 ? cardinality : offsetCardinality, atv.cardinality());

                atv = DoubleVector.instantiateSI(mapd, testValues.length, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());
                atv = DoubleVector.instantiateSI(mapd, testValues.length, temperatureUnit, storageType,
                        AbsoluteTemperatureVector.class);
                compareValues(testValues, atv.getValuesSI());
                assertEquals("Unit must match", temperatureUnit, atv.getDisplayUnit());
                assertEquals("cardinality", cardinality, atv.cardinality());

                atv = DoubleVector.instantiate(testValues, temperatureUnit, storageType);
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

                AbsoluteTemperatureVector matv = atv.mutable();
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
                    double v = matv.getSI(index);
                    assertEquals("initial value is returned", testValues[index] + offset, v, 0.001);
                    v += 100;
                    matv.setSI(index, v);
                    v = matv.getSI(index);
                    assertEquals("initial value + 100 is returned", testValues[index] + 100 + offset, v, 0.001);
                }
                for (int index = testValues.length; --index >= 0;)
                {
                    // read and check, change and check again
                    double v = matv.getSI(index);
                    assertEquals("initial value + 100 is returned", testValues[index] + 100 + offset, v, 0.001);
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
                    double v = matv.getSI(index);
                    assertEquals("initial value is returned", testValues[index] + offset, v, 0.001);
                    v += 100;
                    matv.setSI(index, v);
                    v = matv.getSI(index);
                    assertEquals("initial value + 100 is returned", testValues[index] + 100 + offset, v, 0.001);
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

                AbsoluteTemperatureVector dense = atv.toDense();
                assertTrue("Should be dense", dense.isDense());
                assertFalse("Should be not be sparse", dense.isSparse());
                assertEquals("Unit should be same", atv.getDisplayUnit(), dense.getDisplayUnit());
                compareValues(dense.getValuesInUnit(), atv.getValuesInUnit());
                assertFalse("Should be immutable", dense.isMutable());

                AbsoluteTemperatureVector sparse = atv.toSparse();
                assertTrue("Should be sparse", sparse.isSparse());
                assertFalse("Should not be be dense", sparse.isDense());
                assertEquals("Unit should be same", atv.getDisplayUnit(), sparse.getDisplayUnit());
                compareValues(sparse.getValuesInUnit(), atv.getValuesInUnit());
                assertFalse("Should be immutable", sparse.isMutable());

                AbsoluteTemperatureVector copy = atv.clone();
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
     * Test the SIVector class.
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
            // System.out.println("class name is " + className);
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(className + "Unit");
            // double
            @SuppressWarnings("rawtypes")
            Unit standardUnit = quantity.getStandardUnit();
            double[] testValues = new double[] { 0, 123.456d, 0, 0, -234.567d, 0 };
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
            for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
            {
                SIVector siv = SIVector.instantiate(testValues, SIUnit.of(quantity.getSiDimensions()), storageType);
                // system.out.println(siv);
                compareValues(testValues, siv.getValuesSI());
                // assertEquals("Underlying standardUnit must match", standardUnit, siv.getUnit());
                assertEquals("StorageType must match", storageType, siv.getStorageType());
                assertEquals("Cardinality", cardinality, siv.cardinality());
                assertEquals("zSum", zSum, siv.zSum().getSI(), 0.001);
                assertEquals("getScalarClass return SIScalar", SIScalar.class, siv.getScalarClass());
                try
                {
                    siv.ceil();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
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
                DoubleVectorInterface<?, ?, ?> immutable = siv.immutable();
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
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    siv.floor();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    siv.rint();
                    fail("double vector should be immutable");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }

                try
                {
                    siv.neg();
                    fail("double vector should be immutable");
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
                siv = SIVector.instantiate(map, testValues.length, SIUnit.of(quantity.getSiDimensions()), storageType);
                compareValues(testValues, siv.getValuesSI());
                siv = SIVector.instantiate(list, SIUnit.of(quantity.getSiDimensions()), storageType);
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
        double[] testValues = new double[] { 0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0 };
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

        DoubleVector.instantiate(testValues, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        try
        {
            DoubleVector.instantiate((double[]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException vre)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiate(testValues, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiate(testValues, AbsoluteTemperatureUnit.KELVIN, null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        DoubleVector.instantiate(at, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        try
        {
            DoubleVector.instantiate((AbsoluteTemperature[]) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiate(at, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiate(at, AbsoluteTemperatureUnit.KELVIN, null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        DoubleVector.instantiateList(al, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        try
        {
            DoubleVector.instantiateList((List<AbsoluteTemperature>) null, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiateList(al, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiateList(al, AbsoluteTemperatureUnit.KELVIN, null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }
        DoubleVector.instantiateMap(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        try
        {
            DoubleVector.instantiateMap((SortedMap<Integer, AbsoluteTemperature>) null, testValues.length,
                    AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiateMap(map, testValues.length, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiateMap(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, null);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiateMap(map, -1, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("negative length should have thrown an exception");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }

        try
        {
            DoubleVector.instantiateMap(map, 1, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("bad length should have thrown an exception");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }

        map.put(-1, at[0]);
        try
        {
            DoubleVector.instantiateMap(map, testValues.length, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
            fail("bad entry in map should have thrown an exception");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }

        map.remove(-1); // Remove the offending entry
        Quantity<?> quantity = Quantities.INSTANCE.getQuantity("AbsoluteTemperature" + "Unit");
        SIVector.instantiate(testValues, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
        try
        {
            SIVector.instantiate((double[]) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            SIVector.instantiate((List<Double>) null, SIUnit.of(quantity.getSiDimensions()), StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            SIVector.instantiate(testValues, null, StorageType.DENSE);
            fail("null pointer should have thrown an exception");
        }
        catch (NullPointerException npe)
        {
            // Ignore expected exception
        }

        try
        {
            SIVector.instantiate(testValues, SIUnit.of(quantity.getSiDimensions()), null);
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
        double[] testValues = new double[4000];
        for (int i = 0; i < testValues.length; i++)
        {
            testValues[i] = i % 3 != 0 ? 0 : (100.0 + i);
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
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            for (AbsoluteTemperatureUnit temperatureUnit : new AbsoluteTemperatureUnit[] { AbsoluteTemperatureUnit.KELVIN,
                    AbsoluteTemperatureUnit.DEGREE_CELSIUS })
            {
                AbsoluteTemperatureVector atv = DoubleVector.instantiate(testValues, temperatureUnit, storageType);
                compareValuesWithScale(temperatureUnit.getScale(), testValues, atv.getValuesSI());
                atv = DoubleVector.instantiateList(al, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                atv = DoubleVector.instantiateSI(dl, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                atv = DoubleVector.instantiateMap(map, testValues.length, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesSI());
                atv = DoubleVector.instantiate(at, temperatureUnit, storageType);
                compareValues(testValues, atv.getValuesInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT));
            }
        }
    }

    /**
     * Test most "asXX" methods.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     * @param <U> the unit type
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public <U extends Unit<U>> void testAsMost() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, ClassNotFoundException, UnitException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        double[] testValues = new double[] { 0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0 };
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            AbstractDoubleVectorRel allVector =
                    (AbstractDoubleVectorRel) DoubleVector.instantiate(new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                            DimensionlessUnit.SI.getStandardUnit(), storageType);
            for (String type : CLASSNAMES.REL_LIST)
            {
                Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
                    {
                        AbstractDoubleVectorRel vector =
                                (AbstractDoubleVectorRel) DoubleVector.instantiate(testValues, unit, storageType2);
                        SIVector mult = vector.times(allVector);
                        Method asMethod = SIVector.class.getDeclaredMethod("as" + type);
                        AbstractDoubleVectorRel<U, ?, ?> asVector = (AbstractDoubleVectorRel<U, ?, ?>) asMethod.invoke(mult);
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, mult.getValuesSI());

                        Method asMethodDisplayUnit = SIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                        AbstractDoubleVectorRel<U, ?, ?> asVectorDisplayUnit =
                                (AbstractDoubleVectorRel<U, ?, ?>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, asVectorDisplayUnit.getValuesSI());

                        // test exception for wrong 'as'
                        SIVector cd4sr2 = SIVector.instantiate(testValues, SIUnit.of("cd4/sr2"), storageType2);
                        try
                        {
                            AbstractDoubleScalarRel<?, ?> asScalarDim = (AbstractDoubleScalarRel<?, ?>) asMethod.invoke(cd4sr2);
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }

                        try
                        {
                            AbstractDoubleScalarRel<?, ?> asScalarDim =
                                    (AbstractDoubleScalarRel<?, ?>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }
                    }
                }
            }
        }
    }

    /**
     * Test the "asXX" methods of the remaining classes.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     * @param <U> the unit type
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public <U extends Unit<U>> void testAsRemaining() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, ClassNotFoundException, UnitException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        double[] testValues = new double[] { 0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0 };
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            AbstractDoubleVectorRel<?, ?, ?> dimless =
                    (AbstractDoubleVectorRel<?, ?, ?>) DoubleVector.instantiate(new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                            DimensionlessUnit.SI.getStandardUnit(), storageType);
            for (String type : CLASSNAMES.REL_WITH_ABS_LIST)
            {
                Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
                    {
                        AbstractDoubleVectorRel vector =
                                (AbstractDoubleVectorRel) DoubleVector.instantiate(testValues, unit, storageType2);
                        SIVector mult = vector.times(dimless);
                        Method asMethod = SIVector.class.getDeclaredMethod("as" + type);
                        AbstractDoubleVectorRel<U, ?, ?> asVector = (AbstractDoubleVectorRel<U, ?, ?>) asMethod.invoke(mult);
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, mult.getValuesSI());

                        Method asMethodDisplayUnit = SIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                        AbstractDoubleVectorRel<U, ?, ?> asVectorDisplayUnit =
                                (AbstractDoubleVectorRel<U, ?, ?>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());
                        compareValuesWithScale(unit.getScale(), testValues, asVectorDisplayUnit.getValuesSI());

                        // test exception for wrong 'as'
                        SIVector cd4sr2 = SIVector.instantiate(testValues, SIUnit.of("cd4/sr2"), storageType2);
                        try
                        {
                            AbstractDoubleScalarRel<?, ?> asScalarDim = (AbstractDoubleScalarRel<?, ?>) asMethod.invoke(cd4sr2);
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }

                        try
                        {
                            AbstractDoubleScalarRel<?, ?> asScalarDim =
                                    (AbstractDoubleScalarRel<?, ?>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
                            fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in "
                                    + asScalarDim);
                        }
                        catch (InvocationTargetException | UnitRuntimeException e)
                        {
                            // ok
                        }
                    }
                }
            }
        }
    }

    /**
     * Test the <code>as</code> method of the SIVector class.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     * @param <U> the unit type
     */
    @SuppressWarnings("unchecked")
    @Test
    public <U extends Unit<U>> void testAsUnit() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, UnitException
    {
        double[] testValues = new double[] { 0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0 };
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            for (String type : CLASSNAMES.REL_LIST)
            {
                Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] { StorageType.DENSE, storageType })
                    {
                        SIUnit siUnit = SIUnit.of(unit.getQuantity().getSiDimensions());
                        SIVector vector = SIVector.instantiate(testValues, siUnit, storageType2);
                        Method asMethod = SIVector.class.getDeclaredMethod("as", Unit.class);
                        AbstractDoubleVectorRel<U, ?, ?> asVector =
                                (AbstractDoubleVectorRel<U, ?, ?>) asMethod.invoke(vector, siUnit);
                        assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                        siUnit = SIUnit.of(AbsoluteTemperatureUnit.KELVIN.getQuantity().getSiDimensions());
                        compareValues(testValues, asVector.getValuesSI());
                        try
                        {
                            asMethod.invoke(vector, siUnit);
                            fail("as method should not be able to cast to unrelated (absoluteTemperature) unit");
                        }
                        catch (InvocationTargetException ite)
                        {
                            Throwable cause = ite.getCause();
                            assertEquals("cause is UnitRuntimeException", UnitRuntimeException.class, cause.getClass());
                            // Otherwise ignore expected exception
                        }
                    }
                }
            }
        }
    }

    /**
     * Test the plus and similar methods.
     */
    @Test
    public void operationTest()
    {
        double[] testValues = new double[] { 0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0 };
        double[] testValues2 = new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (AbsoluteTemperatureUnit temperatureUnit : new AbsoluteTemperatureUnit[] { AbsoluteTemperatureUnit.KELVIN,
                AbsoluteTemperatureUnit.DEGREE_CELSIUS, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT })
        {
            for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
            {
                AbsoluteTemperatureVector atv = DoubleVector.instantiate(testValues, temperatureUnit, storageType);
                for (TemperatureUnit relativeTemperatureUnit : new TemperatureUnit[] { TemperatureUnit.KELVIN,
                        TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.DEGREE_FAHRENHEIT })
                {
                    for (StorageType storageType2 : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
                    {
                        TemperatureVector rtv = DoubleVector.instantiate(testValues2, relativeTemperatureUnit, storageType2);
                        AbsoluteTemperatureVector sumtv = atv.plus(rtv);
                        compareSum(atv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN),
                                rtv.getValuesInUnit(TemperatureUnit.KELVIN),
                                sumtv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN));
                        AbsoluteTemperatureVector difftv = atv.minus(rtv);
                        compareSum(rtv.getValuesInUnit(TemperatureUnit.KELVIN),
                                difftv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN),
                                atv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN));

                        String s = atv.toString(temperatureUnit);
                        assertTrue("toString returns something sensible", s.startsWith("["));
                        assertTrue("toString returns something sensible", s.endsWith("] " + temperatureUnit.toString()));
                        // System.out.println(atv.toString(true, true));
                        s = atv.toString(true, true);
                        assertTrue("toString includes Immutable", s.contains("Immutable"));
                        assertTrue("toString includes Abs", s.contains("Abs"));
                        assertTrue("toString includes Dense or Sparse", s.contains(atv.isDense() ? "Dense" : "Sparse"));
                        assertTrue("toString returns something sensible", s.endsWith("] " + temperatureUnit.toString()));
                        s = atv.mutable().toString(true, true);
                        assertTrue("toString includes Mutable", s.contains("Mutable"));

                        s = rtv.toString();
                        assertTrue("toString returns something sensible", s.startsWith("["));
                        assertTrue("toString returns something sensible",
                                s.endsWith("] " + relativeTemperatureUnit.toString()));
                        s = rtv.toString(true, true);
                        assertTrue("toString includes Immutable", s.contains("Immutable"));
                        assertTrue("toString includes Rel", s.contains("Rel"));
                        assertTrue("toString includes Dense or Sparse", s.contains(rtv.isDense() ? "Dense" : "Sparse"));
                        assertTrue("toString returns something sensible",
                                s.endsWith("] " + relativeTemperatureUnit.toString()));
                        s = rtv.mutable().toString(true, true);
                        assertTrue("toString includes Mutable", s.contains("Mutable"));

                    }
                }
            }
        }
    }

    /**
     * Check that two arrays and a sum array match.
     * @param left double[]; the left array
     * @param right double[]; the right array
     * @param sum double[]; the sum array
     */
    public void compareSum(final double[] left, final double[] right, final double[] sum)
    {
        assertEquals("length of left must equal length of sum", left.length, sum.length);
        assertEquals("length of right must equal length of sum", right.length, sum.length);
        for (int i = 0; i < sum.length; i++)
        {
            assertEquals("left plus right is sum", left[i] + right[i], sum[i], 0.001);
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
