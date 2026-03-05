package org.djunits.vecmat.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Length;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link FloatSparseValue}.
 * <p>
 * Covers all constructors, SI conversion, null checks, negative index errors, accessors, and basic toString() semantics. The
 * tests validate functional correctness; if the implementation contains errors, the tests must fail.
 * </p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class FloatSparseValueTest
{
    /**
     * Test constructor(row,col,Q) including SI-conversion and null-check.
     */
    @Test
    @DisplayName("ctor(row,col,Q): null check + SI conversion + negative indices")
    public void testCtorQuantity()
    {
        assertThrows(NullPointerException.class, () -> new FloatSparseValue<>(0, 0, (Length) null));

        assertThrows(IllegalArgumentException.class, () -> new FloatSparseValue<>(-1, 0, Length.ofSi(1)));

        assertThrows(IllegalArgumentException.class, () -> new FloatSparseValue<>(0, -1, Length.ofSi(1)));

        FloatSparseValue<Length, Length.Unit> v = new FloatSparseValue<>(2, 3, new Length(2.0, Length.Unit.km)); // 2000 m
        assertEquals(2, v.getRow());
        assertEquals(3, v.getColumn());
        assertEquals(2000f, v.si(), 1e-6);
    }

    /**
     * Test constructor(row,col,valueInUnit,unit) including conversion to SI.
     */
    @Test
    @DisplayName("ctor(row,col,valueInUnit,unit): conversion to SI")
    public void testCtorValueUnit()
    {
        FloatSparseValue<Length, Length.Unit> v = new FloatSparseValue<>(1, 2, 3.0f, Length.Unit.km); // 3000 m
        assertEquals(3000f, v.si(), 1e-6);
    }

    /**
     * Test constructor(row,col,si) and negative index detection.
     */
    @Test
    @DisplayName("ctor(row,col,si): SI direct + negative index checks")
    public void testCtorSI()
    {
        assertThrows(IllegalArgumentException.class, () -> new FloatSparseValue<>(-1, 0, 5f));
        assertThrows(IllegalArgumentException.class, () -> new FloatSparseValue<>(0, -1, 5f));

        FloatSparseValue<Length, Length.Unit> v = new FloatSparseValue<>(3, 4, 7.5f);
        assertEquals(3, v.getRow());
        assertEquals(4, v.getColumn());
        assertEquals(7.5f, v.si(), 1e-6);
    }

    /**
     * Test that toString() contains all relevant fields.
     */
    @Test
    @DisplayName("toString(): contains row/column/value")
    public void testToString()
    {
        FloatSparseValue<Length, Length.Unit> v = new FloatSparseValue<>(1, 2, 3.14f);
        String s = v.toString();
        assertTrue(s.contains("row=1"));
        assertTrue(s.contains("column=2"));
        assertTrue(s.contains("valueSI=3.14"));
    }
}
