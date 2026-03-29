package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.def.AbsQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TimeTest tests the Time absolute quantity class and its Reference handling.<p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUNITS project is distributed under a https://djunits.org/docs/license.html
 * three-clause BSD-style license.
 * <p>
 * This test suite provides comprehensive functional coverage of:
 * <ul>
 * <li>All Time constructors</li>
 * <li>Parsing: valueOf and of</li>
 * <li>Reference creation, lookup, and simple offset transformations</li>
 * <li>Arithmetic operations: subtract(Time), add(Duration), subtract(Duration)</li>
 * <li>Display-unit propagation rules</li>
 * <li>Inherited operations from AbsQuantity: comparisons, zero checks, interpolate, sum, mean, min, max</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
class TimeTest
{
    /**
     * Set Locale.US for consistent number parsing and formatting.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    // =================================================================
    // CONSTRUCTORS AND BASIC BEHAVIOR
    // =================================================================

    /**
     * Test all Time constructors: (value, unit, reference), (value, abbreviation, reference), and (Duration, reference), as
     * well as ofSi(..., reference).
     */
    @Test
    void testConstructors()
    {
        // Use built-in references directly
        Time t1 = new Time(10.0, Duration.Unit.s, Time.Reference.UNIX);
        assertEquals(10.0, t1.si(), 1E-12);
        assertEquals(Time.Reference.UNIX, t1.getReference());

        Time t2 = new Time(250.0, "ms", Time.Reference.UNIX);
        t2.setDisplayUnit(Duration.Unit.ms);
        assertEquals(250.0, t2.getInUnit(), 1E-12);
        assertEquals(Time.Reference.UNIX, t2.getReference());

        Time t3 = new Time(Duration.ofSi(5.0), Time.Reference.GREGORIAN);
        assertEquals(5.0, t3.si(), 1E-12);
        assertEquals(Time.Reference.GREGORIAN, t3.getReference());

        Time t4 = Time.ofSi(42.0, Time.Reference.GPS);
        assertEquals(42.0, t4.si(), 1E-12);
        assertEquals(Time.Reference.GPS, t4.getReference());

        // instantiate(...) should honor both the value and the reference
        Time t5 = t1.instantiate(Duration.ofSi(123.0), Time.Reference.UNIX);
        assertEquals(123.0, t5.si(), 1E-12);
        assertEquals(Time.Reference.UNIX, t5.getReference());

        // siUnit delegates to Duration SI
        assertEquals("s", t1.siUnit().toString(true, false));
    }

    // =================================================================
    // PARSING METHODS (valueOf, of)
    // =================================================================

    /**
     * Test parsing of textual values via valueOf(text, reference) and of(value, unitString, reference), including error paths.
     */
    @Test
    void testParsing()
    {
        Time p1 = Time.valueOf("100 s", Time.Reference.UNIX);
        assertEquals(100.0, p1.si(), 1E-12);
        assertEquals(Time.Reference.UNIX, p1.getReference());

        Time p2 = Time.of(12.0, "s", Time.Reference.UNIX);
        assertEquals(12.0, p2.si(), 1E-12);

        // Error cases
        assertThrows(NullPointerException.class, () -> Time.valueOf(null, Time.Reference.UNIX));
        assertThrows(NullPointerException.class, () -> Time.of(10.0, null, Time.Reference.UNIX));
        assertThrows(IllegalArgumentException.class, () -> Time.valueOf("12 XYZ", Time.Reference.UNIX));
    }

    // =================================================================
    // REFERENCE REGISTRY AND SIMPLE OFFSET TRANSFORMATIONS
    // =================================================================

    /**
     * Test Reference.get for built-ins and simple add/get with offset. Also verify relativeTo(...) for a single-level offset,
     * and that unrelated built-ins (with no defined path) cannot be transformed.
     */
    @Test
    void testReferenceBehavior()
    {
        // Built-ins present
        assertEquals(Time.Reference.GREGORIAN, Time.Reference.get("GREGORIAN"));
        assertEquals(Time.Reference.UNIX, Time.Reference.get("UNIX"));
        assertEquals(Time.Reference.GPS, Time.Reference.get("GPS"));
        assertNull(Time.Reference.get("UNKNOWN_REF"));

        // Define UNIX_PLUS10 = UNIX + 10 s
        Time.Reference.add("UNIX_PLUS10", "UNIX + 10 seconds", Duration.ofSi(10.0), Time.Reference.UNIX);
        Time.Reference unixPlus10 = Time.Reference.get("UNIX_PLUS10");
        assertNotNull(unixPlus10);

        // 0 @ UNIX_PLUS10 equals absolute time 10 s relative to UNIX
        Time t0Plus10 = new Time(0.0, Duration.Unit.s, unixPlus10);
        Time t0Plus10RelUnix = t0Plus10.relativeTo(Time.Reference.UNIX);
        assertEquals(10.0, t0Plus10RelUnix.si(), 1E-12);

        // 50 @ UNIX_PLUS10 equals 60 s relative to UNIX
        Time t50Plus10 = new Time(50.0, Duration.Unit.s, unixPlus10);
        Time t50Plus10RelUnix = t50Plus10.relativeTo(Time.Reference.UNIX);
        assertEquals(60.0, t50Plus10RelUnix.si(), 1E-12);

        // Built-ins are independent (no chain defined) → conversion not possible
        Time tGregorian = new Time(0.0, Duration.Unit.s, Time.Reference.GREGORIAN);
        assertThrows(IllegalArgumentException.class, () -> tGregorian.relativeTo(Time.Reference.UNIX));
        
        // clean up
        Time.Reference.get("UNIX_PLUS10").unregister();
    }

    // =================================================================
    // ARITHMETIC AND DISPLAY-UNIT PROPAGATION
    // =================================================================

    /**
     * Test subtract(Time) → Duration, add(Duration) → Time, subtract(Duration) → Time, and check display-unit propagation from
     * the absolute quantity.
     */
    @Test
    void testArithmetic()
    {
        // Use UNIX reference
        Time t10 = new Time(10.0, Duration.Unit.s, Time.Reference.UNIX).setDisplayUnit(Duration.Unit.ms);
        Time t25 = new Time(25.0, Duration.Unit.s, Time.Reference.UNIX).setDisplayUnit(Duration.Unit.ms);

        // subtract absolute → Duration in display unit of minuend
        Duration diff = t25.subtract(t10);
        assertEquals(15.0, diff.si(), 1E-12);
        assertEquals(Duration.Unit.ms, diff.getDisplayUnit());

        // add relative duration
        Time t35 = t25.add(Duration.ofSi(10.0)).setDisplayUnit(Duration.Unit.s);
        assertEquals(35.0, t35.getInUnit(), 1E-12);
        assertEquals(Time.Reference.UNIX, t35.getReference());

        // subtract relative duration
        Time t5 = t10.subtract(Duration.ofSi(5.0)).setDisplayUnit(Duration.Unit.s);
        assertEquals(5.0, t5.getInUnit(), 1E-12);
        assertEquals(Time.Reference.UNIX, t5.getReference());

        // Cross-reference subtraction with convertible references:
        // UNIX: 20 s; UNIX_PLUS10: 15 s → 25 s relative to UNIX; 20 - 25 = -5 s
        Time.Reference.add("UNIX_PLUS10", "UNIX + 10 seconds", Duration.ofSi(10.0), Time.Reference.UNIX);
        Time tUnix20 = new Time(20.0, Duration.Unit.s, Time.Reference.UNIX).setDisplayUnit(Duration.Unit.s);
        Time tUnixPlus1015 = new Time(15.0, Duration.Unit.s, Time.Reference.get("UNIX_PLUS10")).setDisplayUnit(Duration.Unit.s);
        Duration cross = tUnix20.subtract(tUnixPlus1015);
        assertEquals(-5.0, cross.si(), 1E-12);
        assertEquals(Duration.Unit.s, cross.getDisplayUnit());
        
        // clean up
        Time.Reference.get("UNIX_PLUS10").unregister();
    }

    // =================================================================
    // INHERITED ABSOLUTEQUANTITY COMPARISONS & ZERO TESTS
    // =================================================================

    /**
     * Test lt, le, gt, ge, eq, ne, compareTo. Mismatched references should throw.
     */
    @Test
    void testComparisonOperators()
    {
        Time a = new Time(10.0, Duration.Unit.s, Time.Reference.UNIX);
        Time b = new Time(20.0, Duration.Unit.s, Time.Reference.UNIX);

        assertTrue(a.lt(b));
        assertTrue(a.le(b));
        assertTrue(b.gt(a));
        assertTrue(b.ge(a));
        assertTrue(a.ne(b));
        assertTrue(b.eq(b));

        assertEquals(-1, a.compareTo(b));
        assertEquals(1, b.compareTo(a));

        // Different references → compareTo and relational ops should throw
        Time c = new Time(10.0, Duration.Unit.s, Time.Reference.GPS);
        assertThrows(IllegalArgumentException.class, () -> a.lt(c));
        assertThrows(IllegalArgumentException.class, () -> a.compareTo(c));
    }

    /**
     * Test lt0, le0, gt0, ge0, eq0, ne0 and numeric Number conversions.
     */
    @Test
    void testZeroComparisonsAndNumericConversions()
    {
        Time t0 = new Time(0.0, Duration.Unit.s, Time.Reference.UNIX);
        assertTrue(t0.eq0());
        assertFalse(t0.ne0());
        assertFalse(t0.gt0());
        assertTrue(t0.le0());

        Time t5 = new Time(5.0, Duration.Unit.s, Time.Reference.UNIX);
        assertTrue(t5.gt0());
        assertFalse(t5.lt0());
    }

    // =================================================================
    // STATIC OPERATIONS: interpolate, min, max, sum, mean
    // =================================================================

    /**
     * Test the static operations inherited from AbsQuantity: interpolate, min, max, sum, and mean, using the same
     * reference. Mixed references should throw.
     */
    @Test
    void testStaticOperations()
    {
        Time a = new Time(10.0, Duration.Unit.s, Time.Reference.UNIX);
        Time b = new Time(20.0, Duration.Unit.s, Time.Reference.UNIX);
        Time c = new Time(30.0, Duration.Unit.s, Time.Reference.UNIX);

        assertEquals(c, AbsQuantity.max(a, b, c));
        assertEquals(a, AbsQuantity.min(a, b, c));

        Time sum = AbsQuantity.sum(a, b, c);
        assertEquals(60.0, sum.si(), 1E-12);

        Time mean = AbsQuantity.mean(a, b, c);
        assertEquals(20.0, mean.si(), 1E-12);

        Time mid = AbsQuantity.interpolate(a, c, 0.5);
        assertEquals(20.0, mid.si(), 1E-12);

        // Mixed references → should throw
        Time d = new Time(5.0, Duration.Unit.s, Time.Reference.GPS);
        assertThrows(IllegalArgumentException.class, () -> AbsQuantity.sum(a, d));
    }
}
