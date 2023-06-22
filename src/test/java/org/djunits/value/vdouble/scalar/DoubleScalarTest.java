package org.djunits.value.vdouble.scalar;

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
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarAbs;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRelWithAbs;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatPosition;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.junit.Test;

/**
 * Test the DoubleScalar class.
 * <p>
 * This file was generated by the djunits value test classes generator, 26 jun, 2015
 * <p>
 * Copyright (c) 2015-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class DoubleScalarTest
{
    /**
     * Check that the value in a DoubleScalarmatches the expected value.
     * @param ds DoubleScalar&lt;?&gt;; the DoubleScalar to match
     * @param reference double; the reference value
     * @param precision double; the maximum allowed error
     * @param u Unit&lt;?&gt;; the expected type
     * @param expectAbsolute boolean; if true; ds should be Absolute; if false; ds should be Relative
     */
    private static void checkContentsAndType(final DoubleScalar<?, ?> ds, final double reference,
            final double precision, final Unit<?> u, final boolean expectAbsolute)
    {
        assertTrue("DoubleScalar should not be null", null != ds);
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
        double value = 38.0;
        AbsoluteTemperature ds = new AbsoluteTemperature(value, tempUnit);
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
        double value = 38.0;
        AbsoluteTemperature temperatureDS = new AbsoluteTemperature(value, tempUnit);
        checkContentsAndType(temperatureDS, value, 0.001, tempUnit, true);
        assertEquals("Value in SI is equivalent in Kelvin", 311.15, temperatureDS.getSI(), 0.05);
        assertEquals("Value in Fahrenheit", 100.4, temperatureDS.getInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT), 0.1);
        double out = temperatureDS.getInUnit();
        assertEquals("Value should match", value, out, 0.001);
        AbsoluteTemperature mds = new AbsoluteTemperature(value, tempUnit);
        checkContentsAndType(mds, value, 0.001, tempUnit, true);
        mds = new AbsoluteTemperature(-200, tempUnit);
        assertEquals("-200 Celsius roughly equivalent to 73 Kelvin", 73.0d, mds.getSI(), 1.0d);
        mds = new AbsoluteTemperature(temperatureDS);
        checkContentsAndType(mds, value, 0.001, tempUnit, true);
        AbsoluteTemperature temperature2DS = new AbsoluteTemperature(temperatureDS);
        assertTrue("temperature2DS should be equal to temperatureDS", temperature2DS.equals(temperatureDS));
        assertTrue("Value is Absolute", temperatureDS.isAbsolute());
        assertFalse("Value is not Relative", temperatureDS.isRelative());
        temperatureDS = new AbsoluteTemperature(value, AbsoluteTemperatureUnit.KELVIN);
        checkContentsAndType(temperatureDS, value, 0.001, AbsoluteTemperatureUnit.KELVIN, true);
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

        double testValue = -123.456;
        for (String type : CLASSNAMES.ALL_LIST)
        {
            String className = "org.djunits.value.vdouble.scalar." + type;
            Class<?> scalarClass = Class.forName(className);
            Quantity<?> quantity = Quantities.INSTANCE.getQuantity(type + "Unit");
            for (Unit<?> unit : quantity.getUnitsById().values())
            {
                Method ofMethod = scalarClass.getDeclaredMethod("of", double.class, String.class);
                for (String unitAbbreviation : unit.getDefaultAbbreviations())
                {
                    DoubleScalar<?, ?> scalar =
                            (DoubleScalar<?, ?>) ofMethod.invoke(null, testValue, unitAbbreviation);
                    assertEquals("unit was parsed correctly", scalar.getDisplayUnit().getId(), unit.getId());
                    assertEquals("value was parsed correctly", scalar.getInUnit(), testValue, 0.0001);
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
            @SuppressWarnings("unchecked")
            Class<? extends DoubleScalarRelWithAbs<?, ?, ?, ?>> scalarClass =
                    (Class<? extends DoubleScalarRelWithAbs<?, ?, ?, ?>>) Class
                            .forName("org.djunits.value.vdouble.scalar." + relativeType);
            double relValue = 123.456;
            for (Unit<?> relativeUnit : Quantities.INSTANCE.getQuantity(relativeType + "Unit").getUnitsById().values())
            {
                Constructor<?> relConstructor = scalarClass.getConstructor(double.class, relativeUnit.getClass());
                DoubleScalarRel<?, ?> relScalar =
                        (DoubleScalarRel<?, ?>) relConstructor.newInstance(relValue, relativeUnit);
                double absValue = 234.567;
                Quantity<?> quantity = Quantities.INSTANCE.getQuantity(absoluteType + "Unit");
                for (Unit<?> absoluteUnit : quantity.getUnitsById().values())
                {
                    // Create an abs
                    Method instantiateAbsMethod =
                            scalarClass.getDeclaredMethod("instantiateAbs", double.class, absoluteUnit.getClass());
                    DoubleScalarAbs<?, ?, ?, ?> absScalar = (DoubleScalarAbs<?, ?, ?, ?>) instantiateAbsMethod
                            .invoke(relScalar, absValue, absoluteUnit);
                    // method "plus" cannot be found with getMethod() for absScalar.getClass().
                    Method plusMethod = scalarClass.getMethod("plus", absScalar.getClass().getSuperclass());
                    DoubleScalarAbs<?, ?, ?, ?> sum =
                            (DoubleScalarAbs<?, ?, ?, ?>) plusMethod.invoke(relScalar, absScalar);
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
        double value = 38.0;
        Position ds = new Position(value, positionUnit);
        assertTrue("Equal to itself", ds.equals(ds));
        assertFalse("Not equal to null", ds.equals(null));
        assertFalse("Not equal to some other kind of object; e.g. a String", ds.equals(new String("abc")));
        Length dsCounterPart = new Length(value, lengthUnit);
        assertFalse("Not equal if one Absolute and other Relative", ds.equals(dsCounterPart));
        AbsoluteTemperature dsWrongBaseUnit = new AbsoluteTemperature(value, AbsoluteTemperatureUnit.KELVIN);
        assertEquals("The underlying SI values are the same", ds.getSI(), dsWrongBaseUnit.getSI(), 0.0001);
        assertFalse("Not equals because the standard SI unit differs", ds.equals(dsWrongBaseUnit));
        Position dsCompatibleUnit = new Position(38000.0, PositionUnit.MILLIMETER);
        assertFalse("Units are different", ds.getDisplayUnit().equals(dsCompatibleUnit.getDisplayUnit()));
        assertTrue("equals returns true", ds.equals(dsCompatibleUnit));
        Position dsDifferentValue = new Position(123.456, PositionUnit.MILLIMETER);
        assertFalse("Different value makes equals return false", ds.equals(dsDifferentValue));
    }

    /**
     * Test the relational operations.
     */
    @Test
    public final void relOpAbsTest()
    {
        Position base = new Position(123, PositionUnit.KILOMETER);
        Position same = new Position(123000, PositionUnit.METER);
        Position smaller = new Position(122999, PositionUnit.METER);
        Position larger = new Position(123001, PositionUnit.METER);
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
        double[] seedValues = { -10, -2, -1, -0.5, -0.1, 0, 0.1, 0.5, 1, 2, 10 };
        for (double seedValue : seedValues)
        {
            double input = seedValue;
            Position ds;
            ds = new Position(input, PositionUnit.DEFAULT);
            MathTester.tester(input, "ceil", ds.ceil(), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return Math.ceil(d);
                }
            });
            ds = new Position(input, PositionUnit.DEFAULT);
            MathTester.tester(input, "floor", ds.floor(), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return Math.floor(d);
                }
            });
            ds = new Position(input, PositionUnit.DEFAULT);
            MathTester.tester(input, "rint", ds.rint(), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return Math.rint(d);
                }
            });
        }
    }

    /**
     * Test plus(DoubleScalarAbs, DoubleScalarRel).
     */
    @Test
    public final void binaryplusOfAbsAndRelTest()
    {
        double leftValue = 123.4;
        double rightValue = 234.5;
        Position left = new Position(leftValue, PositionUnit.MILE);
        Length right = new Length(rightValue, LengthUnit.MILE);
        Position result = DoubleScalar.plus(left, right);
        assertEquals("value of element should be SI plus of contributing elements", left.getSI() + right.getSI(),
                result.getSI(), 0.001);
        // Reverse parameters
        result = DoubleScalar.plus(right, left);
        assertEquals("value of element should be SI plus of contributing elements", left.getSI() + right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test minus(DoubleScalarAbs, DoubleScalarRel).
     */
    @Test
    public final void binaryminusOfAbsAndRelTest()
    {
        double leftValue = 123.4;
        double rightValue = 234.5;
        Position left = new Position(leftValue, PositionUnit.MILE);
        Length right = new Length(rightValue, LengthUnit.MILE);
        Position result = DoubleScalar.minus(left, right);
        assertEquals("value of element should be SI minus of contributing elements", left.getSI() - right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test minus(DoubleScalarAbs, DoubleScalarRel).
     */
    @Test
    public final void binaryminusOfAbsAndAbsTest()
    {
        double leftValue = 123.4;
        double rightValue = 234.5;
        Position left = new Position(leftValue, PositionUnit.MILE);
        Position right = new Position(rightValue, PositionUnit.MILE);
        Length result = DoubleScalar.minus(left, right);
        assertEquals("value of element should be SI minus of contributing elements", left.getSI() - right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test the interpolate methods. Also does the FloatXXX versions.
     */
    @Test
    public final void interpolateTest()
    {
        double[] ratios = new double[] { 0.0, 1.0, 0.5, 0.1, -7.2, 8.04 };
        double zeroValue = 123.4;
        double oneValue = 234.5;
        Position zeroPosition = new Position(zeroValue, PositionUnit.MILE);
        Position onePosition = new Position(oneValue, PositionUnit.MILE);
        Length zeroLength = new Length(zeroValue, LengthUnit.INCH);
        Length oneLength = new Length(oneValue, LengthUnit.INCH);
        FloatPosition floatZeroPosition = new FloatPosition(zeroValue, PositionUnit.MILE);
        FloatPosition floatOnePosition = new FloatPosition(oneValue, PositionUnit.MILE);
        FloatLength floatZeroLength = new FloatLength(zeroValue, LengthUnit.INCH);
        FloatLength floatOneLength = new FloatLength(oneValue, LengthUnit.INCH);
        for (double ratio : ratios)
        {
            double expected;
            // Figure out the expected value in a way that is very different from the actual implementation
            if (ratio == 0)
            {
                expected = zeroValue;
            }
            else if (ratio == 1)
            {
                expected = oneValue;
            }
            else
            {
                expected = zeroValue + (oneValue - zeroValue) * ratio;
            }
            Position interpolated = DoubleScalar.interpolate(zeroPosition, onePosition, ratio);
            assertEquals("interpoated value matches ratio", expected, interpolated.getInUnit(), 0.001);
            FloatPosition floatInterpolated = FloatScalar.interpolate(floatZeroPosition, floatOnePosition, (float) ratio);
            assertEquals("interpoated value matches ratio", (float) expected, floatInterpolated.getInUnit(), 0.01f);
            Length interpolatedLength = DoubleScalar.interpolate(zeroLength, oneLength, ratio);
            assertEquals("interpoated value matches ratio", expected, interpolatedLength.getInUnit(), 0.001);
            FloatLength floatInterpolatedLength = FloatScalar.interpolate(floatZeroLength, floatOneLength, (float) ratio);
            assertEquals("interpoated value matches ratio", (float) expected, floatInterpolatedLength.getInUnit(), 0.01f);
        }
    }

    /**
     * Test the max and min methods.
     */
    @Test
    public void maxAndMinTest()
    {
        double lowest = -123.45;
        double middle = -23.5;
        double highest = 45.67;

        Position lowestPosition = new Position(lowest, PositionUnit.FOOT);
        Position middlePosition = new Position(middle, PositionUnit.FOOT);
        Position highestPosition = new Position(highest, PositionUnit.FOOT);

        Position max = DoubleScalar.max(lowestPosition, highestPosition);
        assertEquals("max returns highest", highestPosition, max);
        // Reverse arguments
        max = DoubleScalar.max(highestPosition, lowestPosition);
        assertEquals("max returns highest", highestPosition, max);
        // Three arguments
        max = DoubleScalar.max(lowestPosition, middlePosition, highestPosition);
        assertEquals("max returns highest", highestPosition, max);
        max = DoubleScalar.max(highestPosition, lowestPosition, middlePosition);
        assertEquals("max returns highest", highestPosition, max);
        max = DoubleScalar.max(lowestPosition, highestPosition, middlePosition);
        assertEquals("max returns highest", highestPosition, max);
        // Lots of arguments
        max = DoubleScalar.max(highestPosition, lowestPosition, highestPosition, middlePosition, middlePosition);
        assertEquals("max returns highest", highestPosition, max);

        Position min = DoubleScalar.min(lowestPosition, highestPosition);
        assertEquals("min returns lowest", lowestPosition, min);
        // Reverse arguments
        min = DoubleScalar.min(highestPosition, lowestPosition);
        assertEquals("min returns highest", lowestPosition, min);
        // Three arguments
        min = DoubleScalar.min(lowestPosition, middlePosition, highestPosition);
        assertEquals("min returns lowest", lowestPosition, min);
        min = DoubleScalar.min(highestPosition, lowestPosition, middlePosition);
        assertEquals("min returns lowest", lowestPosition, min);
        min = DoubleScalar.min(highestPosition, middlePosition, lowestPosition);
        assertEquals("min returns lowest", lowestPosition, min);
        // Lots of arguments
        min = DoubleScalar.min(highestPosition, lowestPosition, highestPosition, middlePosition, middlePosition);
        assertEquals("min returns lowest", lowestPosition, min);
    }

    /**
     * Test that the toString method returns something sensible.
     */
    @Test
    public final void toStringRelTest()
    {
        TemperatureUnit tempUnit = TemperatureUnit.KELVIN;
        double value = 38.0;
        Temperature ds = new Temperature(value, tempUnit);
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
        double value = 38.0;
        Temperature temperatureDS = new Temperature(value, tempUnit);
        checkContentsAndType(temperatureDS, value, 0.001, tempUnit, false);
        assertEquals("Value in SI is equivalent in Kelvin", 38.0, temperatureDS.getSI(), 0.05);
        assertEquals("Value in Fahrenheit", 38.0 * 9.0 / 5.0, temperatureDS.getInUnit(TemperatureUnit.DEGREE_FAHRENHEIT), 0.1);
        double out = temperatureDS.getInUnit();
        assertEquals("Value should match", value, out, 0.001);
        Temperature mds = new Temperature(value, tempUnit);
        checkContentsAndType(mds, value, 0.001, tempUnit, false);
        Temperature temperature2DS = new Temperature(temperatureDS);
        assertTrue("temperature2DS should be equal to temperatureDS", temperature2DS.equals(temperatureDS));
        assertTrue("Value is Relative", temperatureDS.isRelative());
        assertFalse("Value is not Absolute", temperatureDS.isAbsolute());
        temperatureDS = new Temperature(value, TemperatureUnit.KELVIN);
        checkContentsAndType(temperatureDS, value, 0.001, TemperatureUnit.KELVIN, false);
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
        double value = 38.0;
        Length ds = new Length(value, lengthUnit);
        assertTrue("Equal to itself", ds.equals(ds));
        assertFalse("Not equal to null", ds.equals(null));
        assertFalse("Not equal to some other kind of object; e.g. a String", ds.equals(new String("abc")));
        Position dsCounterPart = new Position(value, positionUnit);
        assertFalse("Not equal if one Absolute and other Relative", ds.equals(dsCounterPart));
        Temperature dsWrongBaseUnit = new Temperature(value, TemperatureUnit.KELVIN);
        assertEquals("The underlying SI values are the same", ds.getSI(), dsWrongBaseUnit.getSI(), 0.0001);
        assertFalse("Not equals because the standard SI unit differs", ds.equals(dsWrongBaseUnit));
        Length dsCompatibleUnit = new Length(38000.0, LengthUnit.MILLIMETER);
        assertFalse("Units are different", ds.getDisplayUnit().equals(dsCompatibleUnit.getDisplayUnit()));
        assertTrue("equals returns true", ds.equals(dsCompatibleUnit));
        Length dsDifferentValue = new Length(123.456, LengthUnit.MILLIMETER);
        assertFalse("Different value makes equals return false", ds.equals(dsDifferentValue));
    }

    /**
     * Test the relational operations.
     */
    @Test
    public final void relOpRelTest()
    {
        Length base = new Length(123, LengthUnit.KILOMETER);
        Length same = new Length(123000, LengthUnit.METER);
        Length smaller = new Length(122999, LengthUnit.METER);
        Length larger = new Length(123001, LengthUnit.METER);
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
        double[] seedValues = { -10, -2, -1, -0.5, -0.1, 0, 0.1, 0.5, 1, 2, 10 };
        for (double seedValue : seedValues)
        {
            double input = seedValue;
            Length length;
            length = new Length(input, LengthUnit.METER);
            MathTester.tester(input, "abs", length.abs(), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return Math.abs(d);
                }
            });
            length = new Length(input, LengthUnit.METER);
            MathTester.tester(input, "ceil", length.ceil(), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return Math.ceil(d);
                }
            });
            length = new Length(input, LengthUnit.METER);
            MathTester.tester(input, "floor", length.floor(), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return Math.floor(d);
                }
            });
            length = new Length(input, LengthUnit.METER);
            MathTester.tester(input, "rint", length.rint(), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return Math.rint(d);
                }
            });
            length = new Length(input, LengthUnit.METER);
            MathTester.tester(input, "neg", length.neg(), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return -d;
                }
            });
            length = new Length(input, LengthUnit.METER);
            MathTester.tester(input, "times 3", length.times(3), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return d * 3;
                }
            });
            length = new Length(input, LengthUnit.METER);
            MathTester.tester(input, "divide 7", length.divide(7), 0.001, new DoubleToDouble()
            {
                @Override
                public double function(final double d)
                {
                    return d / 7;
                }
            });
        }
    }

    /**
     * Test plus(DoubleScalarRel, DoubleScalarRel).
     */
    @Test
    public final void binaryplusOfRelAndRelTest()
    {
        double leftValue = 123.4;
        double rightValue = 234.5;
        Length left = new Length(leftValue, LengthUnit.MILE);
        Length right = new Length(rightValue, LengthUnit.MILE);
        Length result = DoubleScalar.plus(left, right);
        assertEquals("value of element should be SI plus of contributing elements", left.getSI() + right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test minus(DoubleScalarRel, DoubleScalarRel).
     */
    @Test
    public final void binaryminusOfRelAndRelTest()
    {
        double leftValue = 123.4;
        double rightValue = 234.5;
        Length left = new Length(leftValue, LengthUnit.MILE);
        Length right = new Length(rightValue, LengthUnit.MILE);
        Length result = DoubleScalar.minus(left, right);
        assertEquals("value of element should be SI minus of contributing elements", left.getSI() - right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test multiply(DoubleScalarRel, DoubleScalarRel).
     */
    @Test
    public final void binarymultiplyOfRelAndRelTest()
    {
        double leftValue = 123.4;
        double rightValue = 234.5;
        Length left = new Length(leftValue, LengthUnit.MILE);
        Length right = new Length(rightValue, LengthUnit.MILE);
        SIScalar result = DoubleScalar.multiply(left, right);
        assertEquals("value of element should be SI multiply of contributing elements", left.getSI() * right.getSI(),
                result.getSI(), 0.001);
    }

    /**
     * Test divide(DoubleScalarRel, DoubleScalarRel).
     */
    @Test
    public final void binarydivideOfRelAndRelTest()
    {
        double leftValue = 123.4;
        double rightValue = 234.5;
        Length left = new Length(leftValue, LengthUnit.MILE);
        Length right = new Length(rightValue, LengthUnit.MILE);
        SIScalar result = DoubleScalar.divide(left, right);
        assertEquals("value of element should be SI divide of contributing elements", left.getSI() / right.getSI(),
                result.getSI(), 0.001);
    }

    /** */
    interface DoubleToDouble
    {
        /**
         * @param d double; value
         * @return double value
         */
        double function(double d);
    }

    /** */
    abstract static class MathTester
    {
        /**
         * Test a math function.
         * @param inputValue double; unprocessed value
         * @param operation String; description of method that is being tested
         * @param actualResult DoubleScalar&lt;?&gt;; the actual result of the operation
         * @param precision double; expected accuracy
         * @param function DoubleToDouble; encapsulated function that converts one inputValue to an outputValue
         */
        public static void tester(final double inputValue, final String operation,
                final DoubleScalar<?, ?> actualResult, final double precision, final DoubleToDouble function)
        {
            double expectedResult = function.function(inputValue);
            double got = actualResult.getSI();
            String description = String.format("%s(%f->%f should be equal to %f with precision %f", operation, inputValue,
                    expectedResult, got, precision);
            // System.out.println(description);
            assertEquals(description, expectedResult, got, precision);
        }

    }

}
