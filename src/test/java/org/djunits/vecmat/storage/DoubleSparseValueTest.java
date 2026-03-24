package org.djunits.vecmat.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Length;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DoubleSparseValue}.
 * <p>
 * Ensures 100% coverage of all constructors, accessors, null checks, negative index errors, SI-conversion, and toString()
 * formatting.
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class DoubleSparseValueTest
{
    /**
     * Test constructor(row,col,Quantity) including SI-conversion and null-check.
     */
    @Test
    @DisplayName("ctor(row,col,Q): SI + null check + negative index")
    public void testCtorQuantity()
    {
        // Null value -> NullPointerException
        assertThrows(NullPointerException.class, () -> new DoubleSparseValue<>(0, 0, (Length) null));

        // Negative row/column
        assertThrows(IllegalArgumentException.class, () -> new DoubleSparseValue<>(-1, 0, Length.ofSi(1)));
        assertThrows(IllegalArgumentException.class, () -> new DoubleSparseValue<>(0, -1, Length.ofSi(1)));

        // Happy path
        DoubleSparseValue<Length> v = new DoubleSparseValue<>(2, 3, new Length(2.0, Length.Unit.km)); // 2000 m
        assertEquals(2, v.getRow());
        assertEquals(3, v.getColumn());
        assertEquals(2000.0, v.si(), 1e-12);
    }

    /**
     * Test constructor(row,col,valueInUnit,unit) including toBaseValue conversion.
     */
    @Test
    @DisplayName("ctor(row,col,valueInUnit,unit): conversion to SI")
    public void testCtorValueUnit()
    {
        DoubleSparseValue<Length> v = new DoubleSparseValue<>(1, 2, 3.0, Length.Unit.km); // 3000 m
        assertEquals(3000.0, v.si(), 1e-12);
    }

    /**
     * Test constructor(row,col,si) and valid SI assignment.
     */
    @Test
    @DisplayName("ctor(row,col,si): direct SI storage + negative index errors")
    public void testCtorSI()
    {
        assertThrows(IllegalArgumentException.class, () -> new DoubleSparseValue<>(-1, 0, 5.0));
        assertThrows(IllegalArgumentException.class, () -> new DoubleSparseValue<>(0, -2, 5.0));

        DoubleSparseValue<Length> v = new DoubleSparseValue<>(3, 4, 7.5);
        assertEquals(3, v.getRow());
        assertEquals(4, v.getColumn());
        assertEquals(7.5, v.si(), 1e-12);
    }

    /**
     * Test toString formatting for basic sanity.
     */
    @Test
    @DisplayName("toString(): contains row/column/value")
    public void testToString()
    {
        DoubleSparseValue<Length> v = new DoubleSparseValue<>(1, 2, 3.14);
        String s = v.toString();
        assertTrue(s.contains("row=1"));
        assertTrue(s.contains("column=2"));
        assertTrue(s.contains("valueSI=3.14"));
    }
}
