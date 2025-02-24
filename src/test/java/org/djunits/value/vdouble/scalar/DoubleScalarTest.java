package org.djunits.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.junit.jupiter.api.Test;

/**
 * Test the DoubleScalar class.
 * <p>
 * This file was generated by the djunits value test classes generator, 26 jun, 2015
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class DoubleScalarTest
{
    /**
     * Check that the value in a DoubleScalarmatches the expected value.
     * @param ds the DoubleScalar to match
     * @param reference the reference value
     * @param precision the maximum allowed error
     * @param u the expected type
     * @param expectAbsolute if true; ds should be Absolute; if false; ds should be Relative
     */
    private static void checkContentsAndType(final DoubleScalar<?, ?> ds, final double reference, final double precision,
            final Unit<?> u, final boolean expectAbsolute)
    {
        assertTrue(null != ds, "DoubleScalar should not be null");
        assertEquals(reference, ds.getInUnit(), precision, "Value should match");
        assertEquals(u, ds.getDisplayUnit(), "Unit should be " + u.toString());
        assertTrue(expectAbsolute ? ds.isAbsolute() : ds.isRelative(),
                "Should be " + (expectAbsolute ? "Absolute" : "Relative"));
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
        assertTrue(result.contains("Abs "), "toString result contains \"Abs \"");
        assertTrue(result.contains("K"), "toString result contains \"K\"");
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
        assertEquals(311.15, temperatureDS.getSI(), 0.05, "Value in SI is equivalent in Kelvin");
        assertEquals(100.4, temperatureDS.getInUnit(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT), 0.1, "Value in Fahrenheit");
        double out = temperatureDS.getInUnit();
        assertEquals(value, out, 0.001, "Value should match");
        AbsoluteTemperature mds = new AbsoluteTemperature(value, tempUnit);
        checkContentsAndType(mds, value, 0.001, tempUnit, true);
        mds = new AbsoluteTemperature(-200, tempUnit);
        assertEquals(73.0d, mds.getSI(), 1.0d, "-200 Celsius roughly equivalent to 73 Kelvin");
        mds = new AbsoluteTemperature(temperatureDS);
        checkContentsAndType(mds, value, 0.001, tempUnit, true);
        AbsoluteTemperature temperature2DS = new AbsoluteTemperature(temperatureDS);
        assertTrue(temperature2DS.equals(temperatureDS), "temperature2DS should be equal to temperatureDS");
        assertTrue(temperatureDS.isAbsolute(), "Value is Absolute");
        assertFalse(temperatureDS.isRelative(), "Value is not Relative");
        temperatureDS = new AbsoluteTemperature(value, AbsoluteTemperatureUnit.KELVIN);
        checkContentsAndType(temperatureDS, value, 0.001, AbsoluteTemperatureUnit.KELVIN, true);
        out = temperatureDS.getSI();
        assertEquals(value, out, 0.001, "Value should match");
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
                    DoubleScalar<?, ?> scalar = (DoubleScalar<?, ?>) ofMethod.invoke(null, testValue, unitAbbreviation);
                    assertEquals(scalar.getDisplayUnit().getId(), unit.getId(), "unit was parsed correctly");
                    assertEquals(scalar.getInUnit(), testValue, 0.0001, "value was parsed correctly");
                }
                try
                {
                    ofMethod.invoke(null, testValue, "abcQRS123");
                }
                catch (InvocationTargetException ite)
                {
                    assertTrue(ite.getCause().toString().startsWith("java.lang.IllegalArgumentException"),
                            "Exception is an IllegalArgumentException");
                }

                try
                {
                    ofMethod.invoke(null, testValue, "");
                }
                catch (InvocationTargetException ite)
                {
                    assertTrue(ite.getCause().toString().startsWith("java.lang.IllegalArgumentException"),
                            "Exception is an IllegalArgumentException");
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
        assertEquals(CLASSNAMES.REL_WITH_ABS_LIST.size(), CLASSNAMES.ABS_LIST.size(), "type lists have same size");

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
                DoubleScalarRel<?, ?> relScalar = (DoubleScalarRel<?, ?>) relConstructor.newInstance(relValue, relativeUnit);
                double absValue = 234.567;
                Quantity<?> quantity = Quantities.INSTANCE.getQuantity(absoluteType + "Unit");
                for (Unit<?> absoluteUnit : quantity.getUnitsById().values())
                {
                    // Create an abs
                    Method instantiateAbsMethod =
                            scalarClass.getDeclaredMethod("instantiateAbs", double.class, absoluteUnit.getClass());
                    DoubleScalarAbs<?, ?, ?, ?> absScalar =
                            (DoubleScalarAbs<?, ?, ?, ?>) instantiateAbsMethod.invoke(relScalar, absValue, absoluteUnit);
                    // method "plus" cannot be found with getMethod() for absScalar.getClass().
                    Method plusMethod = scalarClass.getMethod("plus", absScalar.getClass().getSuperclass());
                    DoubleScalarAbs<?, ?, ?, ?> sum = (DoubleScalarAbs<?, ?, ?, ?>) plusMethod.invoke(relScalar, absScalar);
                    // system.out.println("rel=" + relScalar + ", abs=" + absScalar + ", sum=" + sum);
                    assertEquals(absScalar.getSI() + relScalar.getSI(), sum.getSI(), Math.abs(sum.getSI() / 1e7),
                            "sum in SI equals sum of SI values");
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
        assertTrue(ds.equals(ds), "Equal to itself");
        assertFalse(ds.equals(null), "Not equal to null");
        assertFalse(ds.equals(new String("abc")), "Not equal to some other kind of object; e.g. a String");
        Length dsCounterPart = new Length(value, lengthUnit);
        assertFalse(ds.equals(dsCounterPart), "Not equal if one Absolute and other Relative");
        AbsoluteTemperature dsWrongBaseUnit = new AbsoluteTemperature(value, AbsoluteTemperatureUnit.KELVIN);
        assertEquals(ds.getSI(), dsWrongBaseUnit.getSI(), 0.0001, "The underlying SI values are the same");
        assertFalse(ds.equals(dsWrongBaseUnit), "Not equals because the standard SI unit differs");
        Position dsCompatibleUnit = new Position(38000.0, PositionUnit.MILLIMETER);
        assertFalse(ds.getDisplayUnit().equals(dsCompatibleUnit.getDisplayUnit()), "Units are different");
        assertTrue(ds.equals(dsCompatibleUnit), "equals returns true");
        Position dsDifferentValue = new Position(123.456, PositionUnit.MILLIMETER);
        assertFalse(ds.equals(dsDifferentValue), "Different value makes equals return false");
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
        assertFalse(base.lt(same), "123km < 123000m");
        assertTrue(base.le(same), "123km <= 123000m");
        assertTrue(base.ge(same), "123km >= 123000m");
        assertFalse(base.gt(same), "NOT 123km > 123000m");
        assertTrue(base.eq(same), "123km == 123000m");
        assertFalse(base.ne(same), "NOT 123km != 123000m");
        assertTrue(base.lt(larger), "123km < 123001m");
        assertTrue(base.gt(smaller), "123km > 122999m");
        assertTrue(base.ge(same), "123km >= 123000m");
        assertFalse(base.gt(same), "NOT 123km > 123000m");
        assertFalse(base.lt(same), "NOT 123km < 123000m");
        assertTrue(base.le(same), "123km <= 123000m");
        assertTrue(base.ne(larger), "123km != 123001m");
        assertFalse(base.eq(larger), "NOT 123km == 123001m");
        assertTrue(base.ne(smaller), "123km != 122999m");
        assertFalse(base.eq(smaller), "NOT 123km == 122999m");
        assertFalse(base.ge(larger), "NOT 123km >= 123001m");
        assertFalse(base.le(smaller), "NOT 123km <= 122999m");
    }

    /**
     * Test the Math functions.
     */
    @Test
    public final void mathFunctionsTestAbsTest()
    {
        double[] seedValues = {-10, -2, -1, -0.5, -0.1, 0, 0.1, 0.5, 1, 2, 10};
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
        assertEquals(left.getSI() + right.getSI(), result.getSI(), 0.001,
                "value of element should be SI plus of contributing elements");
        // Reverse parameters
        result = DoubleScalar.plus(right, left);
        assertEquals(left.getSI() + right.getSI(), result.getSI(), 0.001,
                "value of element should be SI plus of contributing elements");
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
        assertEquals(left.getSI() - right.getSI(), result.getSI(), 0.001,
                "value of element should be SI minus of contributing elements");
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
        assertEquals(left.getSI() - right.getSI(), result.getSI(), 0.001,
                "value of element should be SI minus of contributing elements");
    }

    /**
     * Test the interpolate methods. Also does the FloatXXX versions.
     */
    @Test
    public final void interpolateTest()
    {
        double[] ratios = new double[] {0.0, 1.0, 0.5, 0.1, -7.2, 8.04};
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
            assertEquals(expected, interpolated.getInUnit(), 0.001, "interpoated value matches ratio");
            FloatPosition floatInterpolated = FloatScalar.interpolate(floatZeroPosition, floatOnePosition, (float) ratio);
            assertEquals((float) expected, floatInterpolated.getInUnit(), 0.01f, "interpoated value matches ratio");
            Length interpolatedLength = DoubleScalar.interpolate(zeroLength, oneLength, ratio);
            assertEquals(expected, interpolatedLength.getInUnit(), 0.001, "interpoated value matches ratio");
            FloatLength floatInterpolatedLength = FloatScalar.interpolate(floatZeroLength, floatOneLength, (float) ratio);
            assertEquals((float) expected, floatInterpolatedLength.getInUnit(), 0.01f, "interpoated value matches ratio");
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
        assertEquals(highestPosition, max, "max returns highest");
        // Reverse arguments
        max = DoubleScalar.max(highestPosition, lowestPosition);
        assertEquals(highestPosition, max, "max returns highest");
        // Three arguments
        max = DoubleScalar.max(lowestPosition, middlePosition, highestPosition);
        assertEquals(highestPosition, max, "max returns highest");
        max = DoubleScalar.max(highestPosition, lowestPosition, middlePosition);
        assertEquals(highestPosition, max, "max returns highest");
        max = DoubleScalar.max(lowestPosition, highestPosition, middlePosition);
        assertEquals(highestPosition, max, "max returns highest");
        // Lots of arguments
        max = DoubleScalar.max(highestPosition, lowestPosition, highestPosition, middlePosition, middlePosition);
        assertEquals(highestPosition, max, "max returns highest");

        Position min = DoubleScalar.min(lowestPosition, highestPosition);
        assertEquals(lowestPosition, min, "min returns lowest");
        // Reverse arguments
        min = DoubleScalar.min(highestPosition, lowestPosition);
        assertEquals(lowestPosition, min, "min returns highest");
        // Three arguments
        min = DoubleScalar.min(lowestPosition, middlePosition, highestPosition);
        assertEquals(lowestPosition, min, "min returns lowest");
        min = DoubleScalar.min(highestPosition, lowestPosition, middlePosition);
        assertEquals(lowestPosition, min, "min returns lowest");
        min = DoubleScalar.min(highestPosition, middlePosition, lowestPosition);
        assertEquals(lowestPosition, min, "min returns lowest");
        // Lots of arguments
        min = DoubleScalar.min(highestPosition, lowestPosition, highestPosition, middlePosition, middlePosition);
        assertEquals(lowestPosition, min, "min returns lowest");
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
        assertTrue(result.contains("Rel "), "toString result contains \"Rel \"");
        assertTrue(result.contains("K"), "toString result contains \"K\"");
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
        assertEquals(38.0, temperatureDS.getSI(), 0.05, "Value in SI is equivalent in Kelvin");
        assertEquals(38.0 * 9.0 / 5.0, temperatureDS.getInUnit(TemperatureUnit.DEGREE_FAHRENHEIT), 0.1, "Value in Fahrenheit");
        double out = temperatureDS.getInUnit();
        assertEquals(value, out, 0.001, "Value should match");
        Temperature mds = new Temperature(value, tempUnit);
        checkContentsAndType(mds, value, 0.001, tempUnit, false);
        Temperature temperature2DS = new Temperature(temperatureDS);
        assertTrue(temperature2DS.equals(temperatureDS), "temperature2DS should be equal to temperatureDS");
        assertTrue(temperatureDS.isRelative(), "Value is Relative");
        assertFalse(temperatureDS.isAbsolute(), "Value is not Absolute");
        temperatureDS = new Temperature(value, TemperatureUnit.KELVIN);
        checkContentsAndType(temperatureDS, value, 0.001, TemperatureUnit.KELVIN, false);
        out = temperatureDS.getSI();
        assertEquals(value, out, 0.001, "Value should match");
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
        assertTrue(ds.equals(ds), "Equal to itself");
        assertFalse(ds.equals(null), "Not equal to null");
        assertFalse(ds.equals(new String("abc")), "Not equal to some other kind of object; e.g. a String");
        Position dsCounterPart = new Position(value, positionUnit);
        assertFalse(ds.equals(dsCounterPart), "Not equal if one Absolute and other Relative");
        Temperature dsWrongBaseUnit = new Temperature(value, TemperatureUnit.KELVIN);
        assertEquals(ds.getSI(), dsWrongBaseUnit.getSI(), 0.0001, "The underlying SI values are the same");
        assertFalse(ds.equals(dsWrongBaseUnit), "Not equals because the standard SI unit differs");
        Length dsCompatibleUnit = new Length(38000.0, LengthUnit.MILLIMETER);
        assertFalse(ds.getDisplayUnit().equals(dsCompatibleUnit.getDisplayUnit()), "Units are different");
        assertTrue(ds.equals(dsCompatibleUnit), "equals returns true");
        Length dsDifferentValue = new Length(123.456, LengthUnit.MILLIMETER);
        assertFalse(ds.equals(dsDifferentValue), "Different value makes equals return false");
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
        assertFalse(base.lt(same), "123km < 123000m");
        assertTrue(base.le(same), "123km <= 123000m");
        assertTrue(base.ge(same), "123km >= 123000m");
        assertFalse(base.gt(same), "NOT 123km > 123000m");
        assertTrue(base.eq(same), "123km == 123000m");
        assertFalse(base.ne(same), "NOT 123km != 123000m");
        assertTrue(base.lt(larger), "123km < 123001m");
        assertTrue(base.gt(smaller), "123km > 122999m");
        assertTrue(base.ge(same), "123km >= 123000m");
        assertFalse(base.gt(same), "NOT 123km > 123000m");
        assertFalse(base.lt(same), "NOT 123km < 123000m");
        assertTrue(base.le(same), "123km <= 123000m");
        assertTrue(base.ne(larger), "123km != 123001m");
        assertFalse(base.eq(larger), "NOT 123km == 123001m");
        assertTrue(base.ne(smaller), "123km != 122999m");
        assertFalse(base.eq(smaller), "NOT 123km == 122999m");
        assertFalse(base.ge(larger), "NOT 123km >= 123001m");
        assertFalse(base.le(smaller), "NOT 123km <= 122999m");
    }

    /**
     * Test the Math functions.
     */
    @Test
    public final void mathFunctionsTestRelTest()
    {
        double[] seedValues = {-10, -2, -1, -0.5, -0.1, 0, 0.1, 0.5, 1, 2, 10};
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
        assertEquals(left.getSI() + right.getSI(), result.getSI(), 0.001,
                "value of element should be SI plus of contributing elements");
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
        assertEquals(left.getSI() - right.getSI(), result.getSI(), 0.001,
                "value of element should be SI minus of contributing elements");
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
        assertEquals(left.getSI() * right.getSI(), result.getSI(), 0.001,
                "value of element should be SI multiply of contributing elements");
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
        assertEquals(left.getSI() / right.getSI(), result.getSI(), 0.001,
                "value of element should be SI divide of contributing elements");
    }

    /** */
    interface DoubleToDouble
    {
        /**
         * @param d value
         * @return double value
         */
        double function(double d);
    }

    /** */
    abstract static class MathTester
    {
        /**
         * Test a math function.
         * @param inputValue unprocessed value
         * @param operation description of method that is being tested
         * @param actualResult the actual result of the operation
         * @param precision expected accuracy
         * @param function encapsulated function that converts one inputValue to an outputValue
         */
        public static void tester(final double inputValue, final String operation, final DoubleScalar<?, ?> actualResult,
                final double precision, final DoubleToDouble function)
        {
            double expectedResult = function.function(inputValue);
            double got = actualResult.getSI();
            String description = String.format("%s(%f->%f should be equal to %f with precision %f", operation, inputValue,
                    expectedResult, got, precision);
            // System.out.println(description);
            assertEquals(expectedResult, got, precision, description);
        }

    }

}
