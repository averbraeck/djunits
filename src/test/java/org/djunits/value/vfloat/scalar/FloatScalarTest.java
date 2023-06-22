package org.djunits.value.vfloat.scalar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.quantity.Quantities;
import org.djunits.quantity.Quantity;
import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarAbs;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.junit.Test;

/**
 * Test the FloatScalar class.
 * <p>
 * This file was generated by the djunits value test classes generator, 26 jun, 2015
 * <p>
 * Copyright (c) 2015-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatScalarTest
{
    /**
     * Check that the value in a FloatScalar matches the expected value.
     * @param ds FloatScalar&lt;?&gt;; the FloatScalar to match
     * @param reference float; the reference value
     * @param precision float; the maximum allowed error
     * @param u Unit&lt;?&gt;; the expected type
     * @param expectAbsolute boolean; if true; ds should be Absolute; if false; ds should be Relative
     */
    private static void checkContentsAndType(final FloatScalar<?, ?> ds, final float reference, final float precision,
            final Unit<?> u, final boolean expectAbsolute)
    {
        assertTrue("FloatScalar should not be null", null != ds);
        assertEquals("Value should match", reference, ds.getInUnit(), precision);
        assertEquals("Unit should be " + u.toString(), u, ds.getDisplayUnit());
        assertTrue("Should be " + (expectAbsolute ? "Absolute" : "Relative"),
                expectAbsolute ? ds.isAbsolute() : ds.isRelative());
    }

    /**
     * Test that the toString method returns something sensible.
     */
    @Test
    public final void toStringAbsTest()
    {
        AbsoluteTemperatureUnit tempUnit = AbsoluteTemperatureUnit.KELVIN;
        float value = 38.0f;
        FloatAbsoluteTemperature ds = new FloatAbsoluteTemperature(value, tempUnit);
        String result = ds.toString(true, true);
        assertTrue("toString result contains \"Abs \"", result.contains("Abs "));
        assertTrue("toString result contains \"K\"", result.contains("K"));
    }

    /**
     * Test constructor, verify the various fields in the constructed objects, test conversions to related units.
     */
    @Test
    public final void basicsAbsTest()
    {
        AbsoluteTemperatureUnit tempUnit = AbsoluteTemperatureUnit.DEGREE_CELSIUS;
        float value = 38.0f;
        FloatAbsoluteTemperature temperatureDS = new FloatAbsoluteTemperature(value, tempUnit);
        checkContentsAndType(temperatureDS, value, 0.001f, tempUnit, true);
        assertEquals("Value in SI is equivalent in Kelvin", 311.15f, temperatureDS.getSI(), 0.05);
        assertEquals("Value in Fahrenheit", 100.4f, temperatureDS.getInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT), 0.1);
        float out = temperatureDS.getInUnit();
        assertEquals("Value should match", value, out, 0.001);
        FloatAbsoluteTemperature mds = new FloatAbsoluteTemperature(value, tempUnit);
        checkContentsAndType(mds, value, 0.001f, tempUnit, true);
        mds = new FloatAbsoluteTemperature(-200f, tempUnit);
        assertEquals("-200 Celsius roughly equivalent to 73 Kelvin", 73.0d, mds.getSI(), 1.0d);
        mds = new FloatAbsoluteTemperature(temperatureDS);
        checkContentsAndType(mds, value, 0.001f, tempUnit, true);
        FloatAbsoluteTemperature temperature2DS = new FloatAbsoluteTemperature(temperatureDS);
        assertTrue("temperature2DS should be equal to temperatureDS", temperature2DS.equals(temperatureDS));
        assertTrue("Value is Absolute", temperatureDS.isAbsolute());
        assertFalse("Value is not Relative", temperatureDS.isRelative());
        temperatureDS = new FloatAbsoluteTemperature(value, AbsoluteTemperatureUnit.KELVIN);
        checkContentsAndType(temperatureDS, value, 0.001f, AbsoluteTemperatureUnit.KELVIN, true);
        out = temperatureDS.getSI();
        assertEquals("Value should match", value, out, 0.001);
    }

    /**
     * Test all "of" methods.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     */
    @Test
    public void testAllOf() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, ClassNotFoundException, UnitException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        float testValue = -123.456f;
        for (String type : CLASSNAMES.ALL_LIST)
        {
            String className = "org.djunits.value.vfloat.scalar.Float" + type;
            Class<?> scalarClass = Class.forName(className);
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(type + "Unit");
            for (Unit<?> unit : quantity.getUnitsById().values())
            {
                Method ofMethod = scalarClass.getDeclaredMethod("of", float.class, String.class);
                for (String unitAbbreviation : unit.getDefaultAbbreviations())
                {
                    FloatScalar<?, ?> scalar = (FloatScalar<?, ?>) ofMethod.invoke(null, testValue, unitAbbreviation);
                    assertEquals("unit was not parsed correctly", scalar.getDisplayUnit().getId(), unit.getId());
                    if (Float.isFinite(scalar.getInUnit()) && scalar.getInUnit() != 0.0f && scalar.getInUnit() != -0.0f
                            && (!unitAbbreviation.contains("(Y")))
                    {
                        assertEquals("value was not parsed correctly", testValue, scalar.getInUnit(), 0.5);
                    }
                }
                try
                {
                    ofMethod.invoke(null, testValue, "abcQRS123");
                }
                catch (InvocationTargetException ite)
                {
                    assertTrue("Exception is an IllegalArgumentException",
                            ite.getCause().toString().startsWith("java.lang.IllegalArgumentException"));
                }

                try
                {
                    ofMethod.invoke(null, testValue, "");
                }
                catch (InvocationTargetException ite)
                {
                    assertTrue("Exception is an IllegalArgumentException",
                            ite.getCause().toString().startsWith("java.lang.IllegalArgumentException"));
                }
            }
        }
    }

    /**
     * Test the plus method of absolutes.
     * @throws ClassNotFoundException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InstantiationException on error
     */
    @Test
    public final void testAbsPlus() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());
        assertEquals("type lists have same size", CLASSNAMES.REL_WITH_ABS_LIST.size(), CLASSNAMES.ABS_LIST.size());

        for (int typeIndex = 0; typeIndex < CLASSNAMES.REL_WITH_ABS_LIST.size(); typeIndex++)
        {
            String relativeType = CLASSNAMES.REL_WITH_ABS_LIST.get(typeIndex);
            String absoluteType = CLASSNAMES.ABS_LIST.get(typeIndex);
            // This relies on the REL_WITH_ABS_LIST and ABS_LIST to be maintained "in sync".
            Class<?> scalarClass = Class.forName("org.djunits.value.vfloat.scalar.Float" + relativeType);
            float relValue = 123.456f;
            for (Unit<?> relativeUnit : Quantities.INSTANCE.getQuantity(relativeType + "Unit").getUnitsById().values())
            {
                Constructor<?> relConstructor = scalarClass.getConstructor(float.class, relativeUnit.getClass());
                FloatScalarRel<?, ?> relScalar =
                        (FloatScalarRel<?, ?>) relConstructor.newInstance(relValue, relativeUnit);
                float absValue = 234.567f;
                Quantity<?> quantity = Quantities.INSTANCE.getQuantity(absoluteType + "Unit");
                for (Unit<?> absoluteUnit : quantity.getUnitsById().values())
                {
                    // Create an abs
                    Method instantiateAbsMethod =
                            scalarClass.getDeclaredMethod("instantiateAbs", float.class, absoluteUnit.getClass());
                    FloatScalarAbs<?, ?, ?, ?> absScalar =
                            (FloatScalarAbs<?, ?, ?, ?>) instantiateAbsMethod.invoke(relScalar, absValue, absoluteUnit);
                    // method "plus" cannot be found with getMethod() for absScalar.getClass().
                    Method plusMethod = scalarClass.getMethod("plus", absScalar.getClass().getSuperclass());
                    FloatScalarAbs<?, ?, ?, ?> sum =
                            (FloatScalarAbs<?, ?, ?, ?>) plusMethod.invoke(relScalar, absScalar);
                    // system.out.println("rel=" + relScalar + ", abs=" + absScalar + ", sum=" + sum);
                    assertEquals("sum in SI equals sum of SI values", absScalar.getSI() + relScalar.getSI(), sum.getSI(),
                            sum.getSI() / 1e7);
                }
            }
        }
    }

    /**
     * Test the equals method.
     */
    @Test
    public final void equalsAbsTest()
    {
        LengthUnit lengthUnit = LengthUnit.METER;
        PositionUnit positionUnit = PositionUnit.DEFAULT;
        float value = 38.0f;
        FloatPosition ds = new FloatPosition(value, positionUnit);
        assertTrue("Equal to itself", ds.equals(ds));
        assertFalse("Not equal to null", ds.equals(null));
        assertFalse("Not equal to some other kind of object; e.g. a String", ds.equals(new String("abc")));
        FloatLength dsCounterPart = new FloatLength(value, lengthUnit);
        assertFalse("Not equal if one Absolute and other Relative", ds.equals(dsCounterPart));
        FloatAbsoluteTemperature dsWrongBaseUnit = new FloatAbsoluteTemperature(value, AbsoluteTemperatureUnit.KELVIN);
        assertEquals("The underlying SI values are the same", ds.getSI(), dsWrongBaseUnit.getSI(), 0.0001);
        assertFalse("Not equals because the standard SI unit differs", ds.equals(dsWrongBaseUnit));
        FloatPosition dsCompatibleUnit = new FloatPosition(38000.0f, PositionUnit.MILLIMETER);
        assertFalse("Units are different", ds.getDisplayUnit().equals(dsCompatibleUnit.getDisplayUnit()));
        assertTrue("equals returns true", ds.equals(dsCompatibleUnit));
        FloatPosition dsDifferentValue = new FloatPosition(123.456f, PositionUnit.MILLIMETER);
        assertFalse("Different value makes equals return false", ds.equals(dsDifferentValue));
    }

    /**
     * Test the relational operations.
     */
    @Test
    public final void relOpAbsTest()
    {
        FloatPosition base = new FloatPosition(123, PositionUnit.KILOMETER);
        FloatPosition same = new FloatPosition(123000, PositionUnit.METER);
        FloatPosition smaller = new FloatPosition(122999, PositionUnit.METER);
        FloatPosition larger = new FloatPosition(123001, PositionUnit.METER);
        assertFalse("123km < 123000m", base.lt(same));
        assertTrue("123km <= 123000m", base.le(same));
        assertTrue("123km >= 123000m", base.ge(same));
        assertFalse("NOT 123km > 123000m", base.gt(same));
        assertTrue("123km == 123000m", base.eq(same));
        assertFalse("NOT 123km != 123000m", base.ne(same));
        assertTrue("123km < 123001m", base.lt(larger));
        assertTrue("123km > 122999m", base.gt(smaller));
        assertTrue("123km >= 123000m", base.ge(same));
        assertFalse("NOT 123km > 123000m", base.gt(same));
        assertFalse("NOT 123km < 123000m", base.lt(same));
        assertTrue("123km <= 123000m", base.le(same));
        assertTrue("123km != 123001m", base.ne(larger));
        assertFalse("NOT 123km == 123001m", base.eq(larger));
        assertTrue("123km != 122999m", base.ne(smaller));
        assertFalse("NOT 123km == 122999m", base.eq(smaller));
        assertFalse("NOT 123km >= 123001m", base.ge(larger));
        assertFalse("NOT 123km <= 122999m", base.le(smaller));
    }

    /**
     * Test the Math functions.
     */
    @Test
    public final void mathFunctionsTestAbsTest()
    {
        float[] seedValues = {-10f, -2f, -1f, -0.5f, -0.1f, 0f, 0.1f, 0.5f, 1f, 2f, 10f};
        for (float seedValue : seedValues)
        {
            float input = seedValue;
            FloatLength floatLength;
            floatLength = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "ceil", floatLength.ceil(), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return (float) Math.ceil(d);
                }
            });
            floatLength = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "floor", floatLength.floor(), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return (float) Math.floor(d);
                }
            });
            floatLength = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "rint", floatLength.rint(), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return (float) Math.rint(d);
                }
            });
            floatLength = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "neg", floatLength.neg(), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return -d;
                }
            });
            floatLength = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "times 3", floatLength.times(3f), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return 3 * d;
                }
            });
            floatLength = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "divide 7", floatLength.divide(7), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return d / 7;
                }
            });
        }
    }

    /**
     * Test plus(FloatScalarAbs, FloatScalarRel).
     */
    @Test
    public final void binaryplusOfAbsAndRelTest()
    {
        float leftValue = 123.4f;
        float rightValue = 234.5f;
        FloatPosition left = new FloatPosition(leftValue, PositionUnit.MILE);
        FloatLength right = new FloatLength(rightValue, LengthUnit.MILE);
        FloatPosition result = FloatScalar.plus(left, right);
        assertEquals("value of element should be SI plus of contributing elements", left.getSI() + right.getSI(),
                result.getSI(), 0.001);
        // Reverse parameters
        result = FloatScalar.plus(right, left);
        assertEquals("value of element should be SI plus of contributing elements", left.getSI() + right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test minus(FloatScalarAbs, FloatScalarRel).
     */
    @Test
    public final void binaryminusOfAbsAndRelTest()
    {
        float leftValue = 123.4f;
        float rightValue = 234.5f;
        FloatPosition left = new FloatPosition(leftValue, PositionUnit.MILE);
        FloatLength right = new FloatLength(rightValue, LengthUnit.MILE);
        FloatPosition result = FloatScalar.minus(left, right);
        assertEquals("value of element should be SI minus of contributing elements", left.getSI() - right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test minus(DoubleScalarAbs, DoubleScalarRel).
     */
    @Test
    public final void binaryminusOfAbsAndAbsTest()
    {
        float leftValue = 123.4f;
        float rightValue = 234.5f;
        FloatPosition left = new FloatPosition(leftValue, PositionUnit.MILE);
        FloatPosition right = new FloatPosition(rightValue, PositionUnit.MILE);
        FloatLength result = FloatScalar.minus(left, right);
        assertEquals("value of element should be SI minus of contributing elements", left.getSI() - right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test the max and min methods.
     */
    @Test
    public void maxAndMinTest()
    {
        float lowest = -123.45f;
        float middle = -23.5f;
        float highest = 45.67f;

        FloatPosition lowestPosition = new FloatPosition(lowest, PositionUnit.FOOT);
        FloatPosition middlePosition = new FloatPosition(middle, PositionUnit.FOOT);
        FloatPosition highestPosition = new FloatPosition(highest, PositionUnit.FOOT);

        FloatPosition max = FloatScalar.max(lowestPosition, highestPosition);
        assertEquals("max returns highest", highestPosition, max);
        // Reverse arguments
        max = FloatScalar.max(highestPosition, lowestPosition);
        assertEquals("max returns highest", highestPosition, max);
        // Three arguments
        max = FloatScalar.max(lowestPosition, middlePosition, highestPosition);
        assertEquals("max returns highest", highestPosition, max);
        max = FloatScalar.max(highestPosition, lowestPosition, middlePosition);
        assertEquals("max returns highest", highestPosition, max);
        max = FloatScalar.max(lowestPosition, highestPosition, middlePosition);
        assertEquals("max returns highest", highestPosition, max);
        // Lots of arguments
        max = FloatScalar.max(highestPosition, lowestPosition, highestPosition, middlePosition, middlePosition);
        assertEquals("max returns highest", highestPosition, max);

        FloatPosition min = FloatScalar.min(lowestPosition, highestPosition);
        assertEquals("min returns lowest", lowestPosition, min);
        // Reverse arguments
        min = FloatScalar.min(highestPosition, lowestPosition);
        assertEquals("min returns highest", lowestPosition, min);
        // Three arguments
        min = FloatScalar.min(lowestPosition, middlePosition, highestPosition);
        assertEquals("min returns lowest", lowestPosition, min);
        min = FloatScalar.min(highestPosition, lowestPosition, middlePosition);
        assertEquals("min returns lowest", lowestPosition, min);
        min = FloatScalar.min(highestPosition, middlePosition, lowestPosition);
        assertEquals("min returns lowest", lowestPosition, min);
        // Lots of arguments
        min = FloatScalar.min(highestPosition, lowestPosition, highestPosition, middlePosition, middlePosition);
        assertEquals("min returns lowest", lowestPosition, min);
    }

    /**
     * Test that the toString method returns something sensible.
     */
    @Test
    public final void toStringRelTest()
    {
        TemperatureUnit tempUnit = TemperatureUnit.KELVIN;
        float value = 38.0f;
        FloatTemperature ds = new FloatTemperature(value, tempUnit);
        String result = ds.toString(true, true);
        assertTrue("toString result contains \"Rel \"", result.contains("Rel "));
        assertTrue("toString result contains \"K\"", result.contains("K"));
    }

    /**
     * Test constructor, verify the various fields in the constructed objects, test conversions to related units.
     */
    @Test
    public final void basicsRelTest()
    {
        TemperatureUnit tempUnit = TemperatureUnit.DEGREE_CELSIUS;
        float value = 38.0f;
        FloatTemperature temperatureDS = new FloatTemperature(value, tempUnit);
        checkContentsAndType(temperatureDS, value, 0.001f, tempUnit, false);
        assertEquals("Value in SI is equivalent in Kelvin", 38.0, temperatureDS.getSI(), 0.05);
        assertEquals("Value in Fahrenheit", 38.0 * 9.0 / 5.0, temperatureDS.getInUnit(TemperatureUnit.DEGREE_FAHRENHEIT), 0.1);
        float out = temperatureDS.getInUnit();
        assertEquals("Value should match", value, out, 0.001);
        FloatTemperature mds = new FloatTemperature(value, tempUnit);
        checkContentsAndType(mds, value, 0.001f, tempUnit, false);
        FloatTemperature temperature2DS = new FloatTemperature(temperatureDS);
        assertTrue("temperature2DS should be equal to temperatureDS", temperature2DS.equals(temperatureDS));
        assertTrue("Value is Relative", temperatureDS.isRelative());
        assertFalse("Value is not Absolute", temperatureDS.isAbsolute());
        temperatureDS = new FloatTemperature(value, TemperatureUnit.KELVIN);
        checkContentsAndType(temperatureDS, value, 0.001f, TemperatureUnit.KELVIN, false);
        out = temperatureDS.getSI();
        assertEquals("Value should match", value, out, 0.001);
    }

    /**
     * Test the equals method.
     */
    @Test
    public final void equalsRelTest()
    {
        LengthUnit lengthUnit = LengthUnit.METER;
        PositionUnit positionUnit = PositionUnit.DEFAULT;
        float value = 38.0f;
        FloatLength ds = new FloatLength(value, lengthUnit);
        assertTrue("Equal to itself", ds.equals(ds));
        assertFalse("Not equal to null", ds.equals(null));
        assertFalse("Not equal to some other kind of object; e.g. a String", ds.equals(new String("abc")));
        FloatPosition dsCounterPart = new FloatPosition(value, positionUnit);
        assertFalse("Not equal if one Absolute and other Relative", ds.equals(dsCounterPart));
        FloatTemperature dsWrongBaseUnit = new FloatTemperature(value, TemperatureUnit.KELVIN);
        assertEquals("The underlying SI values are the same", ds.getSI(), dsWrongBaseUnit.getSI(), 0.0001);
        assertFalse("Not equals because the standard SI unit differs", ds.equals(dsWrongBaseUnit));
        FloatLength dsCompatibleUnit = new FloatLength(38000.0, LengthUnit.MILLIMETER);
        assertFalse("Units are different", ds.getDisplayUnit().equals(dsCompatibleUnit.getDisplayUnit()));
        assertTrue("equals returns true", ds.equals(dsCompatibleUnit));
        FloatLength dsDifferentValue = new FloatLength(123.456, LengthUnit.MILLIMETER);
        assertFalse("Different value makes equals return false", ds.equals(dsDifferentValue));
    }

    /**
     * Test the relational operations.
     */
    @Test
    public final void relOpRelTest()
    {
        FloatLength base = new FloatLength(123, LengthUnit.KILOMETER);
        FloatLength same = new FloatLength(123000, LengthUnit.METER);
        FloatLength smaller = new FloatLength(122999, LengthUnit.METER);
        FloatLength larger = new FloatLength(123001, LengthUnit.METER);
        assertFalse("123km < 123000m", base.lt(same));
        assertTrue("123km <= 123000m", base.le(same));
        assertTrue("123km >= 123000m", base.ge(same));
        assertFalse("NOT 123km > 123000m", base.gt(same));
        assertTrue("123km == 123000m", base.eq(same));
        assertFalse("NOT 123km != 123000m", base.ne(same));
        assertTrue("123km < 123001m", base.lt(larger));
        assertTrue("123km > 122999m", base.gt(smaller));
        assertTrue("123km >= 123000m", base.ge(same));
        assertFalse("NOT 123km > 123000m", base.gt(same));
        assertFalse("NOT 123km < 123000m", base.lt(same));
        assertTrue("123km <= 123000m", base.le(same));
        assertTrue("123km != 123001m", base.ne(larger));
        assertFalse("NOT 123km == 123001m", base.eq(larger));
        assertTrue("123km != 122999m", base.ne(smaller));
        assertFalse("NOT 123km == 122999m", base.eq(smaller));
        assertFalse("NOT 123km >= 123001m", base.ge(larger));
        assertFalse("NOT 123km <= 122999m", base.le(smaller));
    }

    /**
     * Test the Math functions.
     */
    @Test
    public final void mathFunctionsTestRelTest()
    {
        float[] seedValues = {-10f, -2f, -1f, -0.5f, -0.1f, 0f, 0.1f, 0.5f, 1f, 2f, 10f};
        for (float seedValue : seedValues)
        {
            float input = seedValue;
            FloatLength ds;
            ds = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "abs", ds.abs(), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return Math.abs(d);
                }
            });
            ds = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "ceil", ds.ceil(), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return (float) Math.ceil(d);
                }
            });
            ds = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "floor", ds.floor(), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return (float) Math.floor(d);
                }
            });
            ds = new FloatLength(input, LengthUnit.METER);
            MathTester.tester(input, "rint", ds.rint(), 0.001f, new FloatToFloat()
            {
                @Override
                public float function(final float d)
                {
                    return (float) Math.rint(d);
                }
            });
        }
    }

    /**
     * Test plus(FloatScalarRel, FloatScalarRel).
     */
    @Test
    public final void binaryplusOfRelAndRelTest()
    {
        float leftValue = 123.4f;
        float rightValue = 234.5f;
        FloatLength left = new FloatLength(leftValue, LengthUnit.MILE);
        FloatLength right = new FloatLength(rightValue, LengthUnit.MILE);
        FloatLength result = FloatScalar.plus(left, right);
        assertEquals("value of element should be SI plus of contributing elements", left.getSI() + right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test minus(FloatScalarRel, FloatScalarRel).
     */
    @Test
    public final void binaryminusOfRelAndRelTest()
    {
        float leftValue = 123.4f;
        float rightValue = 234.5f;
        FloatLength left = new FloatLength(leftValue, LengthUnit.MILE);
        FloatLength right = new FloatLength(rightValue, LengthUnit.MILE);
        FloatLength result = FloatScalar.minus(left, right);
        assertEquals("value of element should be SI minus of contributing elements", left.getSI() - right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test multiply(FloatScalarRel, FloatScalarRel).
     */
    @Test
    public final void binarymultiplyOfRelAndRelTest()
    {
        float leftValue = 123.4f;
        float rightValue = 234.5f;
        FloatLength left = new FloatLength(leftValue, LengthUnit.MILE);
        FloatLength right = new FloatLength(rightValue, LengthUnit.MILE);
        FloatSIScalar result = FloatScalar.multiply(left, right);
        assertEquals("value of element should be SI multiply of contributing elements", left.getSI() * right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test divide(FloatScalarRel, FloatScalarRel).
     */
    @Test
    public final void binarydivideOfRelAndRelTest()
    {
        float leftValue = 123.4f;
        float rightValue = 234.5f;
        FloatLength left = new FloatLength(leftValue, LengthUnit.MILE);
        FloatLength right = new FloatLength(rightValue, LengthUnit.MILE);
        FloatSIScalar result = FloatScalar.divide(left, right);
        assertEquals("value of element should be SI divide of contributing elements", left.getSI() / right.getSI(),
                result.getSI(), 0.001);
    }

    /** */
    interface FloatToFloat
    {
        /**
         * @param d float; value
         * @return float value
         */
        float function(float d);
    }

    /** */
    abstract static class MathTester
    {
        /**
         * Test a math function.
         * @param inputValue float; unprocessed value
         * @param operation String; description of method that is being tested
         * @param actualResult FloatScalar&lt;?&gt;; the actual result of the operation
         * @param precision float; expected accuracy
         * @param function FloatToFloat; encapsulated function that converts one inputValue to an outputValue
         */
        public static void tester(final float inputValue, final String operation, final FloatScalar<?, ?> actualResult,
                final float precision, final FloatToFloat function)
        {
            float expectedResult = function.function(inputValue);
            float got = actualResult.getSI();
            String description = String.format("%s(%f->%f should be equal to %f with precision %f", operation, inputValue,
                    expectedResult, got, precision);
            // System.out.println(description);
            assertEquals(description, expectedResult, got, precision);
        }

    }

}
