package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * PositionTest tests the Position absolute quantity class and its Reference handling.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * <p>
 * This test suite provides comprehensive functional coverage of:
 * <ul>
 * <li>All Position constructors</li>
 * <li>Parsing: valueOf and of</li>
 * <li>Reference creation, lookup, and simple offset chaining</li>
 * <li>Arithmetic operations: subtract(Position), add(Length), subtract(Length)</li>
 * <li>Display-unit propagation rules</li>
 * <li>Inherited operations from AbsoluteQuantity: comparisons, zero checks, interpolate, sum, mean, min, max</li>
 * </ul>
 * </p>
 * The intent is to fully test the semantics of Position as an absolute quantity relative to Length, without testing the Length
 * class itself (Length has its own unit tests).
 * @author Alexander Verbraeck
 */
class PositionTest
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
     * Test all Position constructors: (value, unit, reference), (value, abbreviation, reference), and (Length, reference).
     */
    @Test
    void testConstructors()
    {
        Position.Reference.add("ORIGIN", "Origin");
        Position.Reference origin = Position.Reference.get("ORIGIN");
        assertNotNull(origin);

        // (value, unit, reference)
        Position p1 = new Position(10.0, Length.Unit.m, origin);
        assertEquals(10.0, p1.si(), 1E-12);
        assertEquals(origin, p1.getReference());

        // (value, abbreviation, reference)
        Position p2 = new Position(25.0, "m", origin);
        assertEquals(25.0, p2.getInUnit(), 1E-12);

        // (Length, reference)
        Position p3 = new Position(Length.ofSi(5.0), origin);
        assertEquals(5.0, p3.si(), 1E-12);
        
        // clean up
        Position.Reference.get("ORIGIN").unregister();
    }

    // =================================================================
    // PARSING METHODS (valueOf, of)
    // =================================================================

    /**
     * Test parsing of textual values via valueOf(text, ref) and of(value, unitString, ref).
     */
    @Test
    void testParsing()
    {
        Position.Reference.add("REF", "Reference point");
        Position.Reference ref = Position.Reference.get("REF");

        Position p1 = Position.valueOf("100 m", ref);
        assertEquals(100.0, p1.getInUnit(), 1E-12);

        Position p2 = Position.of(12.0, "m", ref);
        assertEquals(12.0, p2.getInUnit(), 1E-12);

        assertThrows(NullPointerException.class, () -> Position.valueOf(null, ref));
        assertThrows(NullPointerException.class, () -> Position.of(10.0, null, ref));
        assertThrows(IllegalArgumentException.class, () -> Position.valueOf("12 XYZ", ref));
        
        // clean up
        Position.Reference.get("REF").unregister();
    }

    // =================================================================
    // REFERENCE HANDLING AND SIMPLE OFFSET CONVERSION
    // =================================================================

    /**
     * Test that Position.Reference.add and get work, and test simple reference offset transformations with relativeTo.
     */
    @Test
    void testReferenceBehavior()
    {
        // Define base reference
        Position.Reference.add("A", "Reference A");
        Position.Reference a = Position.Reference.get("A");
        assertNotNull(a);

        // Define B offset +10 m from A
        Position.Reference.add("B", "Reference B", Length.ofSi(10.0), a);
        Position.Reference b = Position.Reference.get("B");
        assertNotNull(b);

        // Position(0 @ B) should equal absolute position 10 m relative to A
        Position pB0 = new Position(0.0, Length.Unit.m, b);
        Position pRelA = pB0.relativeTo(a);
        assertEquals(10.0, pRelA.si(), 1E-12);

        // Position(5 @ B) => 15 m relative to A
        Position pB5 = new Position(5.0, Length.Unit.m, b);
        Position pB5RelA = pB5.relativeTo(a);
        assertEquals(15.0, pB5RelA.si(), 1E-12);
        
        // clean up
        Position.Reference.get("A").unregister();
        Position.Reference.get("B").unregister();
    }

    // =================================================================
    // ARITHMETIC AND DISPLAY-UNIT PROPAGATION
    // =================================================================

    /**
     * Test subtract(Position) → Length, add(Length) → Position, subtract(Length) → Position, and check that display units
     * propagate correctly from the absolute quantity.
     */
    @Test
    void testArithmetic()
    {
        Position.Reference.add("R", "Ref R");
        Position.Reference r = Position.Reference.get("R");

        Position p10 = new Position(10.0, Length.Unit.m, r).setDisplayUnit(Length.Unit.cm);
        Position p25 = new Position(25.0, Length.Unit.m, r).setDisplayUnit(Length.Unit.cm);

        // subtract absolute → Length using display unit of minuend
        Length diff = p25.subtract(p10);
        assertEquals(15.0, diff.si(), 1E-12);
        assertEquals(Length.Unit.cm, diff.getDisplayUnit());

        // add relative length
        Position p35 = p25.add(Length.ofSi(10.0)).setDisplayUnit(Length.Unit.m);
        assertEquals(35.0, p35.getInUnit(), 1E-12);
        assertEquals(r, p35.getReference());

        // subtract relative length
        Position p5 = p10.subtract(Length.ofSi(5.0)).setDisplayUnit(Length.Unit.m);
        assertEquals(5.0, p5.getInUnit(), 1E-12);
        assertEquals(r, p5.getReference());
        
        // clean up
        Position.Reference.get("R").unregister();
    }

    // =================================================================
    // INHERITED ABSOLUTEQUANTITY COMPARISONS & ZERO TESTS
    // =================================================================

    /**
     * Test lt, le, gt, ge, eq, ne, compareTo, including mismatched-reference errors.
     */
    @Test
    void testComparisonOperators()
    {
        Position.Reference.add("R1", "Ref 1");
        Position.Reference r1 = Position.Reference.get("R1");

        Position.Reference.add("R2", "Ref 2");
        Position.Reference r2 = Position.Reference.get("R2");

        Position a = new Position(10.0, Length.Unit.m, r1);
        Position b = new Position(20.0, Length.Unit.m, r1);

        assertTrue(a.lt(b));
        assertTrue(a.le(b));
        assertTrue(b.gt(a));
        assertTrue(b.ge(a));
        assertTrue(a.ne(b));
        assertTrue(b.eq(b));

        assertEquals(-1, a.compareTo(b));
        assertEquals(1, b.compareTo(a));

        // mismatched reference → exception
        Position c = new Position(10.0, Length.Unit.m, r2);
        assertThrows(IllegalArgumentException.class, () -> a.lt(c));
        
        // clean up
        Position.Reference.get("R1").unregister();
        Position.Reference.get("R2").unregister();
    }

    /**
     * Test lt0, le0, gt0, ge0, eq0, ne0 and numeric Number conversions.
     */
    @Test
    void testZeroComparisonsAndNumericConversions()
    {
        Position.Reference.add("RZ", "ZeroRef");
        Position.Reference rz = Position.Reference.get("RZ");

        Position p0 = new Position(0.0, Length.Unit.m, rz);
        assertTrue(p0.eq0());
        assertFalse(p0.ne0());
        assertFalse(p0.gt0());
        assertTrue(p0.le0());

        Position p5 = new Position(5.0, Length.Unit.m, rz);
        assertTrue(p5.gt0());
        assertFalse(p5.lt0());

        assertEquals(5, p5.intValue());
        assertEquals(5L, p5.longValue());
        assertEquals(5.0f, p5.floatValue(), 1E-12);
        assertEquals(5.0, p5.doubleValue(), 1E-12);
        
        // clean up
        Position.Reference.get("RZ").unregister();
    }

    // =================================================================
    // STATIC OPERATIONS: interpolate, min, max, sum, mean
    // =================================================================

    /**
     * Test the static operations inherited from AbsoluteQuantity: interpolate, min, max, sum, and mean.
     */
    @Test
    void testStaticOperations()
    {
        Position.Reference.add("RS", "Ref S");
        Position.Reference rs = Position.Reference.get("RS");

        Position a = new Position(10.0, Length.Unit.m, rs);
        Position b = new Position(20.0, Length.Unit.m, rs);
        Position c = new Position(30.0, Length.Unit.m, rs);

        assertEquals(c, AbsoluteQuantity.max(a, b, c));
        assertEquals(a, AbsoluteQuantity.min(a, b, c));

        Position sum = AbsoluteQuantity.sum(a, b, c);
        assertEquals(60.0, sum.si(), 1E-12);

        Position mean = AbsoluteQuantity.mean(a, b, c);
        assertEquals(20.0, mean.si(), 1E-12);

        Position mid = AbsoluteQuantity.interpolate(a, c, 0.5);
        assertEquals(20.0, mid.si(), 1E-12);

        // mismatched references in static ops must throw
        Position.Reference.add("RS2", "Ref S2");
        Position d = new Position(5.0, Length.Unit.m, Position.Reference.get("RS2"));
        assertThrows(IllegalArgumentException.class, () -> AbsoluteQuantity.sum(a, d));
        
        // clean up
        Position.Reference.get("RS").unregister();
        Position.Reference.get("RS2").unregister();
    }
}
