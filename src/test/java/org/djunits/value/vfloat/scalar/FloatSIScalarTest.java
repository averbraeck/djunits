package org.djunits.value.vfloat.scalar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.quantity.Quantities;
import org.djunits.quantity.Quantity;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.junit.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class FloatSIScalarTest
{

    /** test casting to other scalars. */
    @Test
    public void testAsScalar()
    {
        FloatDuration d = FloatDuration.instantiateSI(10.0f);
        FloatLength l = FloatLength.valueOf("50.0 m");
        FloatSIScalar pace = FloatScalar.divide(d, l);
        // system.out.println("pace = " + pace);
        assertEquals("pace has as unit " + pace.getDisplayUnit().toString() + " instead of s/m", "s/m",
                pace.getDisplayUnit().toString());
        assertEquals(0.2, pace.getSI(), 0.00001);
        assertTrue(pace.toString().startsWith("0.200"));
        assertTrue(pace.toString().endsWith("s/m"));

        FloatElectricalResistance ohm180 = new FloatElectricalResistance(180, ElectricalResistanceUnit.KILOOHM);
        FloatElectricalResistance ohm90 = new FloatElectricalResistance(90, ElectricalResistanceUnit.KILOOHM);
        FloatSpeed pace2xAsSpeed = pace.times(ohm180).divide(ohm90).reciprocal().as(SpeedUnit.SI);
        // system.out.println("pace2x = " + pace2xAsSpeed);
        assertEquals("m/s", pace2xAsSpeed.getDisplayUnit().toString());
        assertEquals(2.5, pace2xAsSpeed.getSI(), 0.00001);

        FloatSpeed pace2xAsSpeedMih = pace.times(ohm180).divide(ohm90).reciprocal().as(SpeedUnit.MILE_PER_HOUR);
        // system.out.println("pace2xMi/h = " + pace2xAsSpeedMih);
        assertEquals("mi/h", pace2xAsSpeedMih.getDisplayUnit().toString());
        assertEquals(2.5, pace2xAsSpeedMih.getSI(), 0.00001);

        FloatSpeed speed = pace.reciprocal().as(SpeedUnit.SI);
        // system.out.println("speed = " + speed);
        assertEquals("m/s", speed.getDisplayUnit().toString());
        assertEquals(5.0, speed.getSI(), 0.00001);
        assertTrue(speed.toString().startsWith("5.000"));
        assertTrue(speed.toString().endsWith("m/s"));
        assertTrue("toString with display unit contains display unit",
                speed.toString(SpeedUnit.FOOT_PER_HOUR).indexOf("ft/h") > 0);

        FloatSpeed speedKmh = pace.reciprocal().as(SpeedUnit.KM_PER_HOUR);
        // system.out.println("speedKm/h = " + speedKmh);
        assertEquals("km/h", speedKmh.getDisplayUnit().toString());
        assertEquals(5.0, speedKmh.getSI(), 0.00001);
        assertTrue(speedKmh.toString().startsWith("18.000"));
        assertTrue(speedKmh.toString().endsWith("km/h"));

        try
        {
            ohm180.times(ohm90).asSpeed();
            fail("Translating Ohms to Speed should have failed");
        }
        catch (Exception e)
        {
            // ok!
        }

        try
        {
            ohm180.times(ohm90).as(SpeedUnit.SI);
            fail("Translating Ohms to Speed should have failed");
        }
        catch (Exception e)
        {
            // ok!
        }
    }

    /**
     * Multiply a value for every unit with every value for every unit and test the SI dimensions.
     * @throws ClassNotFoundException on error finding unit
     */
    @Test
    public void testMultiplyAll() throws ClassNotFoundException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        for (String type1 : CLASSNAMES.REL_LIST)
        {
            Quantity<?> quantity1 = Quantities.INSTANCE.getQuantity(type1 + "Unit");
            // system.out.println("multiply: " + type1 + "Unit");
            SIDimensions siDim1 = quantity1.getSiDimensions();
            Unit<?> unit1 = quantity1.getUnitsById().values().iterator().next();
            // FLOAT -- PREVENT UNDERFLOW / OVERFLOW
            if (unit1.getScale().toStandardUnit(1.0) > 1.0E12 || 1.0 / unit1.getScale().toStandardUnit(1.0) > 1.0E12)
            {
                continue;
            }
            for (String type2 : CLASSNAMES.REL_LIST)
            {
                Quantity<?> quantity2 = Quantities.INSTANCE.getQuantity(type2 + "Unit");
                SIDimensions siDim2 = quantity2.getSiDimensions();
                for (Unit<?> unit2 : quantity2.getUnitsById().values())
                {
                    // FLOAT -- PREVENT UNDERFLOW / OVERFLOW
                    if (unit2.getScale().toStandardUnit(1.0) > 1.0E12 || 1.0 / unit2.getScale().toStandardUnit(1.0) > 1.0E12)
                    {
                        continue;
                    }
                    AbstractFloatScalarRel<?, ?> scalar1 =
                            (AbstractFloatScalarRel<?, ?>) FloatScalar.instantiateAnonymous(12.0f, unit1);
                    AbstractFloatScalarRel<?, ?> scalar2 =
                            (AbstractFloatScalarRel<?, ?>) FloatScalar.instantiateAnonymous(0.5f, unit2);
                    FloatSIScalar scalar12a = FloatScalar.multiply(scalar1, scalar2);
                    FloatSIScalar scalar12b = scalar1.times(scalar2);
                    FloatSIScalar scalar12c = scalar2.times(scalar1);
                    assertEquals(scalar12a.si, scalar12b.si, scalar12a.si / 10000.0);
                    assertEquals("scalar12a.getUnit(): [" + scalar12a.getDisplayUnit() + "] != scalar12b.getUnit(): ["
                            + scalar12b.getDisplayUnit() + "]", scalar12a.getDisplayUnit(), scalar12b.getDisplayUnit());
                    assertEquals(scalar12a.si, scalar12c.si, scalar12a.si / 10000.0);
                    assertEquals(scalar12a.getDisplayUnit(), scalar12c.getDisplayUnit());
                    assertEquals(siDim1.plus(siDim2), scalar12a.getDisplayUnit().getQuantity().getSiDimensions());
                    assertFalse(scalar12a.si == 0.0);
                    assertFalse(Float.isInfinite(scalar12a.si));
                    assertFalse(Float.isNaN(scalar12a.si));
                }
            }
        }
    }

    /**
     * Divide a value for every unit by every value for every unit and test the SI dimensions.
     * @throws ClassNotFoundException on error finding unit
     */
    @Test
    public void testDivideAll() throws ClassNotFoundException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        for (String type1 : CLASSNAMES.REL_LIST)
        {
            Quantity<?> quantity1 = Quantities.INSTANCE.getQuantity(type1 + "Unit");
            // system.out.println("divide: " + type1 + "Unit");
            SIDimensions siDim1 = quantity1.getSiDimensions();
            Unit<?> unit1 = quantity1.getUnitsById().values().iterator().next();
            // FLOAT -- PREVENT UNDERFLOW / OVERFLOW
            if (unit1.getScale().toStandardUnit(1.0) > 1.0E12 || 1.0 / unit1.getScale().toStandardUnit(1.0) > 1.0E12)
            {
                continue;
            }
            for (String type2 : CLASSNAMES.REL_LIST)
            {
                Quantity<?> quantity2 = Quantities.INSTANCE.getQuantity(type2 + "Unit");
                SIDimensions siDim2 = quantity2.getSiDimensions();
                for (Unit<?> unit2 : quantity2.getUnitsById().values())
                {
                    // FLOAT -- PREVENT UNDERFLOW / OVERFLOW
                    if (unit2.getScale().toStandardUnit(1.0) > 1.0E12 || 1.0 / unit2.getScale().toStandardUnit(1.0) > 1.0E12)
                    {
                        continue;
                    }
                    AbstractFloatScalarRel<?, ?> scalar1 =
                            (AbstractFloatScalarRel<?, ?>) FloatScalar.instantiateAnonymous(12.0f, unit1);
                    AbstractFloatScalarRel<?, ?> scalar2 =
                            (AbstractFloatScalarRel<?, ?>) FloatScalar.instantiateAnonymous(0.5f, unit2);
                    FloatSIScalar scalar12a = FloatScalar.divide(scalar1, scalar2);
                    FloatSIScalar scalar12b = scalar1.divide(scalar2);
                    FloatSIScalar scalar12c = scalar2.divide(scalar1);
                    assertEquals(scalar12a.si, scalar12b.si, scalar12a.si / 10000.0);
                    assertEquals("scalar12a.getUnit(): [" + scalar12a.getDisplayUnit() + "] != scalar12b.getUnit(): ["
                            + scalar12b.getDisplayUnit() + "]", scalar12a.getDisplayUnit(), scalar12b.getDisplayUnit());
                    assertEquals(siDim1.minus(siDim2), scalar12b.getDisplayUnit().getQuantity().getSiDimensions());
                    assertEquals(siDim2.minus(siDim1), scalar12c.getDisplayUnit().getQuantity().getSiDimensions());
                    assertFalse(scalar12a.si == 0.0);
                    assertFalse(Float.isInfinite(scalar12a.si));
                    assertFalse(Float.isNaN(scalar12a.si));
                }
            }
        }
    }

    /**
     * Test all "asXX" methods.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     */
    @Test
    public void testAsAll() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, ClassNotFoundException, UnitException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        AbstractFloatScalarRel<?, ?> dimless = new FloatDimensionless(1.0f, DimensionlessUnit.SI);
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(type + "Unit");
            for (Unit<?> unit : quantity.getUnitsById().values())
            {
                AbstractFloatScalarRel<?, ?> scalar =
                        (AbstractFloatScalarRel<?, ?>) FloatScalar.instantiateAnonymous(12.0f, unit);
                FloatSIScalar mult = scalar.times(dimless);
                Method asMethod = FloatSIScalar.class.getDeclaredMethod("as" + type);
                AbstractFloatScalarRel<?, ?> asScalar = (AbstractFloatScalarRel<?, ?>) asMethod.invoke(mult);
                assertEquals(scalar.getDisplayUnit().getStandardUnit(), asScalar.getDisplayUnit());
                assertEquals(scalar.si, asScalar.si, scalar.si / 1000.0);

                Method asMethodDisplayUnit = FloatSIScalar.class.getDeclaredMethod("as" + type, unit.getClass());
                AbstractFloatScalarRel<?, ?> asScalarDisplayUnit =
                        (AbstractFloatScalarRel<?, ?>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                assertEquals(scalar.getDisplayUnit().getStandardUnit(), asScalarDisplayUnit.getDisplayUnit());
                assertEquals(scalar.si, asScalarDisplayUnit.si, scalar.si / 1000.0);

                // test exception for wrong 'as'
                FloatSIScalar cd4sr2 = FloatSIScalar.instantiateSI(8.0f, SIUnit.of("cd4/sr2"));
                try
                {
                    AbstractFloatScalarRel<?, ?> asScalarDim = (AbstractFloatScalarRel<?, ?>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asScalarDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    AbstractFloatScalarRel<?, ?> asScalarDim =
                            (AbstractFloatScalarRel<?, ?>) asMethodDisplayUnit.invoke(cd4sr2, scalar.getDisplayUnit());
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asScalarDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }
            }
        }
    }

    /**
     * Test the min, max, instantiateSI, interpolate, comparison methods.
     * @throws UnitException on error
     */
    @Test
    public void testFloatMethods() throws UnitException
    {
        SIUnit paceUnit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of("s/m"));
        FloatSIScalar pace1 = FloatSIScalar.instantiateSI(1.0f, paceUnit);
        FloatSIScalar pace1a = new FloatSIScalar(pace1);
        assertEquals(pace1, pace1a);
        FloatSIScalar pace2 = new FloatSIScalar(2.0f, paceUnit);
        assertEquals(2.0, pace2.si, 0.001);
        FloatSIScalar pace3 = pace1.times(3.0f);
        assertEquals(3.0, pace3.si, 0.001);
        FloatSIScalar pace5 = pace1.instantiateRel(5.0f, paceUnit);
        assertEquals(5.0, pace5.si, 0.001);
        FloatSIScalar pace7 = FloatSIScalar.instantiateSI(14.0f, paceUnit).divide(2.0f);
        assertEquals(7.0, pace7.si, 0.001);
        FloatSIScalar pace4 = FloatSIScalar.interpolate(pace1, pace7, 0.5f);
        assertEquals(4.0, pace4.si, 0.001);

        assertTrue("ne0", pace1.ne0());
        assertTrue("ge0", pace1.ge0());
        assertTrue("gt0", pace1.gt0());
        assertFalse("le0", pace1.le0());
        assertFalse("eq0", pace1.eq0());
        assertFalse("lt0", pace1.lt0());

        FloatSIScalar pace0 = pace1.minus(pace1);
        assertEquals("0", 0.0, pace0.si, 0);
        assertFalse("ne0", pace0.ne0());
        assertTrue("ge0", pace0.ge0());
        assertFalse("gt0", pace0.gt0());
        assertTrue("le0", pace0.le0());
        assertTrue("eq0", pace0.eq0());
        assertFalse("lt0", pace0.lt0());

        FloatSIScalar negativePace = pace0.minus(pace1);
        assertTrue("ne0", negativePace.ne0());
        assertFalse("ge0", negativePace.ge0());
        assertFalse("gt0", negativePace.gt0());
        assertTrue("le0", negativePace.le0());
        assertFalse("eq0", negativePace.eq0());
        assertTrue("lt0", negativePace.lt0());

        assertEquals("compareto same", 0, pace1.compareTo(pace1));
        assertEquals("compareto bigger", -1, pace0.compareTo(pace1));
        assertEquals("compareto smaller", 1, pace0.compareTo(negativePace));

        assertNotEquals(pace1, pace2);
        assertNotEquals(pace1, null);
        assertNotEquals(pace1, new Object());
        assertNotEquals(pace1.hashCode(), pace2.hashCode());
        assertEquals(pace1.hashCode(), pace1a.hashCode());

        assertEquals(pace2, FloatSIScalar.max(pace1, pace2));
        assertEquals(pace3, FloatSIScalar.max(pace1, pace2, pace3));
        assertEquals(pace4, FloatSIScalar.max(pace1, pace2, pace3, pace4));
        assertEquals(pace2, FloatSIScalar.max(pace2, pace1));
        assertEquals(pace3, FloatSIScalar.max(pace3, pace2, pace1));
        assertEquals(pace4, FloatSIScalar.max(pace4, pace3, pace2, pace1));

        assertEquals(pace1, FloatSIScalar.min(pace1, pace2));
        assertEquals(pace1, FloatSIScalar.min(pace1, pace2, pace3));
        assertEquals(pace1, FloatSIScalar.min(pace1, pace2, pace3, pace4));
        assertEquals(pace1, FloatSIScalar.min(pace2, pace1));
        assertEquals(pace1, FloatSIScalar.min(pace3, pace2, pace1));
        assertEquals(pace1, FloatSIScalar.min(pace4, pace3, pace2, pace1));

        FloatDimensionless dim = pace7.divide(pace2).asDimensionless();
        assertEquals(3.5, dim.si, 0.001);
    }

}
