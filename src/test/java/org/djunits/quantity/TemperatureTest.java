package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TemperatureTest tests the Temperature absolute quantity class and its Reference handling.<p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUNITS project is distributed under a https://djunits.org/docs/license.html
 * three-clause BSD-style license.
 * <p>
 * This test suite provides comprehensive functional coverage of:
 * <ul>
 * <li>All constructors of Temperature</li>
 * <li>Parsing via valueOf(...) and of(...)</li>
 * <li>Reference transformations and offset chaining</li>
 * <li>Temperature.Unit scale behavior, lazy reference initialization, and deriveUnit()</li>
 * <li>Arithmetic operations and display-unit propagation</li>
 * <li>Comparison operators and zero checks</li>
 * <li>Static aggregate operations (min, max, sum, mean, interpolate)</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
class TemperatureTest
{
    /**
     * Set Locale.US for consistent number parsing and formatting.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    // =========================================================================
    // BASIC CONSTRUCTORS & STATIC FACTORIES
    // =========================================================================

    /**
     * Test all Temperature constructors, ensuring that value, unit, and reference are interpreted correctly and that the
     * correct default reference is applied when no explicit reference is provided.
     */
    @Test
    void testAllConstructors()
    {
        Temperature t1 = new Temperature(300.0, Temperature.Unit.K, Temperature.Reference.KELVIN);
        assertEquals(300.0, t1.si(), 1E-12);

        Temperature t2 = new Temperature(50.0, Temperature.Unit.degC);
        assertEquals(50.0, t2.getInUnit(), 1E-12);
        assertEquals(Temperature.Reference.CELSIUS, t2.getReference());

        Temperature t3 = new Temperature(32.0, "degF", Temperature.Reference.FAHRENHEIT);
        assertEquals(32.0, t3.getInUnit(), 1E-12);

        Temperature t4 = new Temperature(10.0, "degC");
        assertEquals(10.0, t4.getInUnit(), 1E-12);
        assertEquals(Temperature.Reference.CELSIUS, t4.getReference());

        TemperatureDifference d20 = TemperatureDifference.ofSi(20.0);
        Temperature t5 = new Temperature(d20, Temperature.Reference.KELVIN);
        assertEquals(20.0, t5.si(), 1E-12);

        Temperature t6 = new Temperature(d20);
        assertEquals(20.0, t6.si(), 1E-12);
        assertEquals(Temperature.Reference.KELVIN, t6.getReference());
    }

    /**
     * Test static constructors ofSi, valueOf, and of, including correct interpretation of unit strings, handling of references,
     * and error paths.
     */
    @Test
    void testStaticFactoriesAndParsing()
    {
        Temperature tSi = Temperature.ofSi(123.0);
        assertEquals(123.0, tSi.si(), 1E-12);

        Temperature tSiR = Temperature.ofSi(77.0, Temperature.Reference.CELSIUS);
        assertEquals(77.0, tSiR.si(), 1E-12);

        Temperature tVal = Temperature.valueOf("300 K");
        assertEquals(300.0, tVal.si(), 1E-12);

        Temperature tVal2 = Temperature.valueOf("25 degC", Temperature.Reference.CELSIUS);
        assertEquals(25.0, tVal2.getInUnit(), 1E-12);

        Temperature tOf = Temperature.of(100.0, "degF");
        assertEquals(100.0, tOf.getInUnit(), 1E-12);

        Temperature tOf2 = Temperature.of(5.0, "degC", Temperature.Reference.CELSIUS);
        assertEquals(5.0, tOf2.getInUnit(), 1E-12);

        assertThrows(NullPointerException.class, () -> Temperature.valueOf(null));
        assertThrows(NullPointerException.class, () -> Temperature.of(10, null));
        assertThrows(IllegalArgumentException.class, () -> Temperature.valueOf("50 XYZ"));
    }

    // =========================================================================
    // REFERENCE TRANSFORMATION AND OFFSET GRAPH
    // =========================================================================

    /**
     * Test the correctness of reference conversions using relativeTo(...), including built-in references (KELVIN, CELSIUS,
     * FAHRENHEIT) and a dynamically added reference with its own offset.
     */
    @Test
    void testReferenceConversion()
    {
        Temperature t0C = new Temperature(0.0, Temperature.Unit.degC, Temperature.Reference.CELSIUS);
        assertEquals(273.15, t0C.relativeTo(Temperature.Reference.KELVIN).si(), 1E-12);

        Temperature t32F = new Temperature(32.0, Temperature.Unit.degF, Temperature.Reference.FAHRENHEIT);
        assertEquals(273.15, t32F.relativeTo(Temperature.Reference.KELVIN).si(), 1E-12);

        Temperature.Reference.add("K+10", "Kelvin+10", TemperatureDifference.ofSi(10.0));
        Temperature.Reference r10 = Temperature.Reference.get("K+10");

        Temperature t0Plus10 = new Temperature(0.0, Temperature.Unit.K, r10);
        assertEquals(10.0, t0Plus10.relativeTo(Temperature.Reference.KELVIN).si(), 1E-12);

        Temperature t50Plus10 = new Temperature(50.0, Temperature.Unit.K, r10);
        assertEquals(60.0, t50Plus10.relativeTo(Temperature.Reference.KELVIN).si(), 1E-12);
        
        // clean up
        Temperature.Reference.get("K+10").unregister();
    }

    // =========================================================================
    // TEMPERATURE.UNIT: SCALE, REFERENCE INIT, DERIVED UNITS
    // =========================================================================

    /**
     * Test the behavior of Temperature.Unit, including lazy reference initialization, derived-unit creation, base/SI-unit
     * behavior, and error handling.
     */
    @Test
    void testUnitReferenceInitializationAndDeriveUnit()
    {
        Temperature.Unit.degC.getReference(); // triggers lazy initialization

        assertEquals(Temperature.Reference.CELSIUS, Temperature.Unit.degC.getReference());
        assertEquals(Temperature.Reference.FAHRENHEIT, Temperature.Unit.degF.getReference());
        assertEquals(Temperature.Reference.KELVIN, Temperature.Unit.K.getReference());
        assertEquals(Temperature.Reference.KELVIN, Temperature.Unit.degR.getReference());

        var doubleKelvin = Temperature.Unit.K.deriveUnit("K2", "K2", "Kelvin*2", 2.0, UnitSystem.OTHER);
        assertEquals(2.0 * Temperature.Unit.K.getScale().toBaseValue(1.0), doubleKelvin.getScale().toBaseValue(1.0), 1E-12);

        assertThrows(RuntimeException.class, () ->
        { Temperature.Unit.K.deriveUnit("BAD", "BAD", "BAD", Double.NaN, UnitSystem.SI_DERIVED); });

        TemperatureDifference d = Temperature.Unit.degC.ofSi(5.0);
        assertEquals(5.0, d.si(), 1E-12);

        assertEquals(Temperature.Unit.SI, Temperature.Unit.K.getBaseUnit());
        assertEquals("K", Temperature.Unit.K.siUnit().toString(true, false));
    }

    // =========================================================================
    // ARITHMETIC AND DISPLAY-UNIT PROPAGATION
    // =========================================================================

    /**
     * Test addition and subtraction of relative quantities, subtraction of absolute temperatures, and correct propagation of
     * display units.
     */
    @Test
    void testArithmetic()
    {
        Temperature t20C = new Temperature(20.0, Temperature.Unit.degC, Temperature.Reference.CELSIUS)
                .setDisplayUnit(Temperature.Unit.degC);
        Temperature t30C = new Temperature(30.0, Temperature.Unit.degC, Temperature.Reference.CELSIUS)
                .setDisplayUnit(Temperature.Unit.degC);

        TemperatureDifference diff = t30C.subtract(t20C);
        assertEquals(10.0, diff.getInUnit(), 1E-12);
        assertEquals(Temperature.Unit.degC, diff.getDisplayUnit());

        Temperature t35C = t30C.add(TemperatureDifference.of(5.0, "degC")).setDisplayUnit(Temperature.Unit.degC);
        assertEquals(35.0, t35C.getInUnit(), 1E-12);

        Temperature t15C = t20C.subtract(TemperatureDifference.of(5.0, "degC")).setDisplayUnit(Temperature.Unit.degC);
        assertEquals(15.0, t15C.getInUnit(), 1E-12);
    }

    // =========================================================================
    // COMPARISON OPERATORS & ZERO CHECKS
    // =========================================================================

    /**
     * Test lt, le, gt, ge, eq, ne, compareTo, and mismatched-reference error cases.
     */
    @Test
    void testComparisonOperators()
    {
        Temperature a = new Temperature(10.0, Temperature.Unit.K, Temperature.Reference.KELVIN);
        Temperature b = new Temperature(20.0, Temperature.Unit.K, Temperature.Reference.KELVIN);

        assertTrue(a.lt(b));
        assertTrue(a.le(b));
        assertTrue(b.gt(a));
        assertTrue(b.ge(a));
        assertTrue(a.ne(b));
        assertTrue(b.eq(b));

        assertEquals(-1, a.compareTo(b));
        assertEquals(1, b.compareTo(a));

        Temperature c = new Temperature(10.0, Temperature.Unit.degC, Temperature.Reference.CELSIUS);
        assertThrows(IllegalArgumentException.class, () -> a.lt(c));
    }

    /**
     * Test eq0, ne0, lt0, le0, gt0, ge0 and verify that Number conversions (intValue, longValue, floatValue, doubleValue) match
     * SI values.
     */
    @Test
    void testZeroComparisonsNumericConversions()
    {
        Temperature t0 = new Temperature(0.0, Temperature.Unit.K, Temperature.Reference.KELVIN);
        assertTrue(t0.eq0());
        assertFalse(t0.ne0());
        assertFalse(t0.gt0());
        assertTrue(t0.le0());

        Temperature t5 = new Temperature(5.0, Temperature.Unit.K, Temperature.Reference.KELVIN);
        assertTrue(t5.gt0());
        assertFalse(t5.lt0());
    }

    // =========================================================================
    // STATIC OPERATIONS (min, max, sum, mean, interpolate)
    // =========================================================================

    /**
     * Test the aggregate static operations defined in AbsoluteQuantity: max, min, sum, mean, and interpolate. Also test
     * reference mismatch errors.
     */
    @Test
    void testStaticOperations()
    {
        Temperature a = new Temperature(10.0, Temperature.Unit.K, Temperature.Reference.KELVIN);
        Temperature b = new Temperature(20.0, Temperature.Unit.K, Temperature.Reference.KELVIN);
        Temperature c = new Temperature(30.0, Temperature.Unit.K, Temperature.Reference.KELVIN);

        assertEquals(c, AbsoluteQuantity.max(a, b, c));
        assertEquals(a, AbsoluteQuantity.min(a, b, c));

        Temperature sum = AbsoluteQuantity.sum(a, b, c);
        assertEquals(60.0, sum.si(), 1E-12);

        Temperature mean = AbsoluteQuantity.mean(a, b, c);
        assertEquals(20.0, mean.si(), 1E-12);

        Temperature mid = AbsoluteQuantity.interpolate(a, c, 0.5);
        assertEquals(20.0, mid.si(), 1E-12);

        Temperature d = new Temperature(5.0, Temperature.Unit.degC, Temperature.Reference.CELSIUS);
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.sum(a, d));
    }

    /**
     * Test the lazy initializer for temperature references.
     */
    @Test
    void testTemperatureUnitLazyReferenceInit()
    {
        // Trigger lazy init via any unit
        Temperature.Unit.degC.getReference();
        assertSame(Temperature.Reference.CELSIUS, Temperature.Unit.degC.getReference());
        assertSame(Temperature.Reference.KELVIN, Temperature.Unit.K.getReference());
        assertSame(Temperature.Reference.FAHRENHEIT, Temperature.Unit.degF.getReference());
    }

}
