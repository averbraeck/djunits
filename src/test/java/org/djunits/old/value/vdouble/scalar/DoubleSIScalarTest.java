package org.djunits.old.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.old.quantity.Quantities;
import org.djunits.old.quantity.Quantity;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.ElectricalResistanceUnit;
import org.djunits.old.unit.SIUnit;
import org.djunits.old.unit.SpeedUnit;
import org.djunits.old.unit.Unit;
import org.djunits.old.unit.si.SIDimensions;
import org.djunits.old.unit.util.UNITS;
import org.djunits.old.unit.util.UnitException;
import org.djunits.old.unit.util.UnitRuntimeException;
import org.djunits.old.value.CLASSNAMES;
import org.djunits.old.value.vdouble.scalar.Dimensionless;
import org.djunits.old.value.vdouble.scalar.Duration;
import org.djunits.old.value.vdouble.scalar.ElectricalResistance;
import org.djunits.old.value.vdouble.scalar.Length;
import org.djunits.old.value.vdouble.scalar.SIScalar;
import org.djunits.old.value.vdouble.scalar.Speed;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.junit.jupiter.api.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 */
public class DoubleSIScalarTest
{

    /** test casting to other scalars. */
    @Test
    public void testAsScalar()
    {
        Duration d = Duration.ofSI(10.0);
        Length l = Length.valueOf("50.0 m");
        SIScalar pace = SIScalar.divide(d, l);
        // system.out.println("pace = " + pace);
        assertEquals("s/m", pace.getDisplayUnit().toString(),
                "pace has as unit " + pace.getDisplayUnit().toString() + " instead of s/m");
        assertEquals(0.2, pace.getSI(), 0.00001);
        assertTrue(pace.toString().startsWith("0.200"));
        assertTrue(pace.toString().endsWith("s/m"));

        ElectricalResistance ohm180 = new ElectricalResistance(180, ElectricalResistanceUnit.KILOOHM);
        ElectricalResistance ohm90 = new ElectricalResistance(90, ElectricalResistanceUnit.KILOOHM);
        Speed pace2xAsSpeed = pace.times(ohm180).divide(ohm90).reciprocal().as(SpeedUnit.SI);
        // system.out.println("pace2x = " + pace2xAsSpeed);
        assertEquals("m/s", pace2xAsSpeed.getDisplayUnit().toString());
        assertEquals(2.5, pace2xAsSpeed.getSI(), 0.00001);

        Speed pace2xAsSpeedMih = pace.times(ohm180).divide(ohm90).reciprocal().as(SpeedUnit.MILE_PER_HOUR);
        // system.out.println("pace2xMi/h = " + pace2xAsSpeedMih);
        assertEquals("mi/h", pace2xAsSpeedMih.getDisplayUnit().toString());
        assertEquals(2.5, pace2xAsSpeedMih.getSI(), 0.00001);

        Speed speed = pace.reciprocal().as(SpeedUnit.SI);
        // system.out.println("speed = " + speed);
        assertEquals("m/s", speed.getDisplayUnit().toString());
        assertEquals(5.0, speed.getSI(), 0.00001);
        assertTrue(speed.toString().startsWith("5.000"));
        assertTrue(speed.toString().endsWith("m/s"));
        assertTrue(speed.toString(SpeedUnit.FOOT_PER_HOUR).indexOf("ft/h") > 0,
                "toString with display unit contains display unit");

        Speed speedKmh = pace.reciprocal().as(SpeedUnit.KM_PER_HOUR);
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
            for (String type2 : CLASSNAMES.REL_LIST)
            {
                Quantity<?> quantity2 = Quantities.INSTANCE.getQuantity(type2 + "Unit");
                SIDimensions siDim2 = quantity2.getSiDimensions();
                for (Unit<?> unit2 : quantity2.getUnitsById().values())
                {
                    DoubleScalarRel<?, ?> scalar1 = (DoubleScalarRel<?, ?>) SIScalar.instantiateAnonymous(12.0, unit1);
                    DoubleScalarRel<?, ?> scalar2 = (DoubleScalarRel<?, ?>) SIScalar.instantiateAnonymous(0.5, unit2);
                    SIScalar scalar12a = SIScalar.multiply(scalar1, scalar2);
                    SIScalar scalar12b = scalar1.times(scalar2);
                    SIScalar scalar12c = scalar2.times(scalar1);
                    assertEquals(scalar12a.si, scalar12b.si, scalar12a.si / 10000.0);
                    assertEquals(scalar12a.getDisplayUnit(), scalar12b.getDisplayUnit(), "scalar12a.getUnit(): ["
                            + scalar12a.getDisplayUnit() + "] != scalar12b.getUnit(): [" + scalar12b.getDisplayUnit() + "]");
                    assertEquals(scalar12a.si, scalar12c.si, scalar12a.si / 10000.0);
                    assertEquals(scalar12a.getDisplayUnit(), scalar12c.getDisplayUnit());
                    assertEquals(siDim1.plus(siDim2), scalar12a.getDisplayUnit().getQuantity().getSiDimensions());
                    assertFalse(scalar12a.si == 0.0);
                    assertFalse(Double.isInfinite(scalar12a.si));
                    assertFalse(Double.isNaN(scalar12a.si));
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
            for (String type2 : CLASSNAMES.REL_LIST)
            {
                Quantity<?> quantity2 = Quantities.INSTANCE.getQuantity(type2 + "Unit");
                SIDimensions siDim2 = quantity2.getSiDimensions();
                for (Unit<?> unit2 : quantity2.getUnitsById().values())
                {
                    DoubleScalarRel<?, ?> scalar1 = (DoubleScalarRel<?, ?>) SIScalar.instantiateAnonymous(12.0, unit1);
                    DoubleScalarRel<?, ?> scalar2 = (DoubleScalarRel<?, ?>) SIScalar.instantiateAnonymous(0.5, unit2);
                    SIScalar scalar12a = SIScalar.divide(scalar1, scalar2);
                    SIScalar scalar12b = scalar1.divide(scalar2);
                    SIScalar scalar12c = scalar2.divide(scalar1);
                    assertEquals(scalar12a.si, scalar12b.si, scalar12a.si / 10000.0);
                    assertEquals(scalar12a.getDisplayUnit(), scalar12b.getDisplayUnit(), "scalar12a.getUnit(): ["
                            + scalar12a.getDisplayUnit() + "] != scalar12b.getUnit(): [" + scalar12b.getDisplayUnit() + "]");
                    assertEquals(siDim1.minus(siDim2), scalar12b.getDisplayUnit().getQuantity().getSiDimensions());
                    assertEquals(siDim2.minus(siDim1), scalar12c.getDisplayUnit().getQuantity().getSiDimensions());
                    assertFalse(scalar12a.si == 0.0);
                    assertFalse(Double.isInfinite(scalar12a.si));
                    assertFalse(Double.isNaN(scalar12a.si));
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

        DoubleScalarRel<?, ?> dimless = new Dimensionless(1.0, DimensionlessUnit.SI);
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(type + "Unit");
            for (Unit<?> unit : quantity.getUnitsById().values())
            {
                DoubleScalarRel<?, ?> scalar = (DoubleScalarRel<?, ?>) SIScalar.instantiateAnonymous(12.0, unit);
                SIScalar mult = scalar.times(dimless);
                Method asMethod = SIScalar.class.getDeclaredMethod("as" + type);
                DoubleScalarRel<?, ?> asScalar = (DoubleScalarRel<?, ?>) asMethod.invoke(mult);
                assertEquals(scalar.getDisplayUnit().getStandardUnit(), asScalar.getDisplayUnit());
                assertEquals(scalar.si, asScalar.si, scalar.si / 1000.0);

                Method asMethodDisplayUnit = SIScalar.class.getDeclaredMethod("as" + type, unit.getClass());
                DoubleScalarRel<?, ?> asScalarDisplayUnit =
                        (DoubleScalarRel<?, ?>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                assertEquals(scalar.getDisplayUnit().getStandardUnit(), asScalarDisplayUnit.getDisplayUnit());
                assertEquals(scalar.si, asScalarDisplayUnit.si, scalar.si / 1000.0);

                // test exception for wrong 'as'
                SIScalar cd4sr2 = SIScalar.ofSI(8.0, SIUnit.of("cd4/sr2"));
                try
                {
                    DoubleScalarRel<?, ?> asScalarDim = (DoubleScalarRel<?, ?>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asScalarDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    DoubleScalarRel<?, ?> asScalarDim =
                            (DoubleScalarRel<?, ?>) asMethodDisplayUnit.invoke(cd4sr2, scalar.getDisplayUnit());
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
     * Test the min, max, ofSI, interpolate, comparison methods.
     * @throws UnitException on error
     */
    @Test
    public void testDoubleMethods() throws UnitException
    {
        SIUnit paceUnit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of("s/m"));
        SIScalar pace1 = SIScalar.ofSI(1.0, paceUnit);
        SIScalar pace1a = new SIScalar(pace1);
        assertEquals(pace1, pace1a);
        SIScalar pace2 = new SIScalar(2.0, paceUnit);
        assertEquals(2.0, pace2.si, 0.001);
        SIScalar pace3 = pace1.times(3.0);
        assertEquals(3.0, pace3.si, 0.001);
        SIScalar pace5 = pace1.instantiateRel(5.0, paceUnit);
        assertEquals(5.0, pace5.si, 0.001);
        SIScalar pace7 = SIScalar.ofSI(14.0, paceUnit).divide(2.0);
        assertEquals(7.0, pace7.si, 0.001);
        SIScalar pace4 = SIScalar.interpolate(pace1, pace7, 0.5);
        assertEquals(4.0, pace4.si, 0.001);

        assertTrue(pace1.ne0(), "ne0");
        assertTrue(pace1.ge0(), "ge0");
        assertTrue(pace1.gt0(), "gt0");
        assertFalse(pace1.le0(), "le0");
        assertFalse(pace1.eq0(), "eq0");
        assertFalse(pace1.lt0(), "lt0");

        SIScalar pace0 = pace1.minus(pace1);
        assertEquals(0.0, pace0.si, 0, "0");
        assertFalse(pace0.ne0(), "ne0");
        assertTrue(pace0.ge0(), "ge0");
        assertFalse(pace0.gt0(), "gt0");
        assertTrue(pace0.le0(), "le0");
        assertTrue(pace0.eq0(), "eq0");
        assertFalse(pace0.lt0(), "lt0");

        SIScalar negativePace = pace0.minus(pace1);
        assertTrue(negativePace.ne0(), "ne0");
        assertFalse(negativePace.ge0(), "ge0");
        assertFalse(negativePace.gt0(), "gt0");
        assertTrue(negativePace.le0(), "le0");
        assertFalse(negativePace.eq0(), "eq0");
        assertTrue(negativePace.lt0(), "lt0");

        assertEquals(0, pace1.compareTo(pace1), "compareto same");
        assertEquals(-1, pace0.compareTo(pace1), "compareto bigger");
        assertEquals(1, pace0.compareTo(negativePace), "compareto smaller");

        assertNotEquals(pace1, pace2);
        assertNotEquals(pace1, null);
        assertNotEquals(pace1, new Object());
        assertNotEquals(pace1.hashCode(), pace2.hashCode());
        assertEquals(pace1.hashCode(), pace1a.hashCode());

        assertEquals(pace2, SIScalar.max(pace1, pace2));
        assertEquals(pace3, SIScalar.max(pace1, pace2, pace3));
        assertEquals(pace4, SIScalar.max(pace1, pace2, pace3, pace4));
        assertEquals(pace2, SIScalar.max(pace2, pace1));
        assertEquals(pace3, SIScalar.max(pace3, pace2, pace1));
        assertEquals(pace4, SIScalar.max(pace4, pace3, pace2, pace1));

        assertEquals(pace1, SIScalar.min(pace1, pace2));
        assertEquals(pace1, SIScalar.min(pace1, pace2, pace3));
        assertEquals(pace1, SIScalar.min(pace1, pace2, pace3, pace4));
        assertEquals(pace1, SIScalar.min(pace2, pace1));
        assertEquals(pace1, SIScalar.min(pace3, pace2, pace1));
        assertEquals(pace1, SIScalar.min(pace4, pace3, pace2, pace1));

        Dimensionless dim = pace7.divide(pace2).asDimensionless();
        assertEquals(3.5, dim.si, 0.001);
    }

}
