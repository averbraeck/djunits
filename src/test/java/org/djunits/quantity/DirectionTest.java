package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * DirectionTest tests the Direction absolute quantity class and its Reference handling.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class DirectionTest
{
    /**
     * Setup correct locale for test.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, parsing, ofSi, instantiate, and siUnit.
     */
    @Test
    void testBasics()
    {
        // Construct with value + unit + reference
        Direction dEast90 = new Direction(90.0, Angle.Unit.deg, Direction.Reference.EAST);
        assertEquals(Math.PI / 2.0, dEast90.si(), 1E-12);

        // Construct with value + abbreviation + reference
        Direction dEast180 = new Direction(180.0, "deg", Direction.Reference.EAST);
        assertEquals(Math.PI, dEast180.si(), 1E-12);

        // Construct with Angle + reference
        Direction dFromAngle = new Direction(Angle.ofSi(Math.PI / 3.0), Direction.Reference.EAST);
        assertEquals(Math.PI / 3.0, dFromAngle.si(), 1E-12);

        // NORTH reference; 0° NORTH == +90° EAST
        Direction dNorth0 = new Direction(0.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction dEast0 = new Direction(0.0, Angle.Unit.deg, Direction.Reference.EAST);
        assertEquals(Math.PI / 2.0, dNorth0.subtract(dEast0).si(), 1E-12);

        // ofSi
        Direction dSi = Direction.ofSi(Math.PI / 6.0, Direction.Reference.EAST);
        assertEquals(Math.PI / 6.0, dSi.si(), 1E-12);

        // valueOf(text, ref) using "deg"
        Direction p1 = Direction.valueOf("180 deg", Direction.Reference.EAST);
        assertEquals(Math.PI, p1.si(), 1E-12);

        // of(value, unitString, ref)
        // 180° NORTH = 90° (NORTH) + 180° = 270° EAST = 3π/2
        Direction p3 = Direction.of(180.0, "deg", Direction.Reference.NORTH);
        assertEquals(Math.PI, p3.si(), 1E-12);

        // siUnit delegates to Angle SI
        assertEquals("rad", dEast90.siUnit().toString(true, false));
    }

    /**
     * Test arithmetic with proper reference handling and display-unit propagation.
     */
    @Test
    void testOperationsAndDisplayPropagation()
    {
        // A = 30° EAST
        Direction a = new Direction(30.0, Angle.Unit.deg, Direction.Reference.EAST);
        // B = 0° NORTH = +90° EAST
        Direction b = new Direction(0.0, Angle.Unit.deg, Direction.Reference.NORTH);

        // subtract(Direction): result is Angle in A's display unit; angle = A.si - B(relative to A.ref).si
        Angle diff = a.subtract(b);
        assertEquals(30.0 * Math.PI / 180.0 - Math.PI / 2.0, diff.si(), 1E-12); // -60°
        // Display unit of diff should be the same as A's display unit (deg)
        assertEquals(a.getDisplayUnit(), diff.getDisplayUnit());

        // add(Angle): Direction with same reference and display unit
        Direction aPlus15 = a.add(Angle.of(15.0, "deg"));
        assertEquals(45.0 * Math.PI / 180.0, aPlus15.si(), 1E-12);
        assertEquals(a.getDisplayUnit(), aPlus15.getDisplayUnit());
        assertEquals(Direction.Reference.EAST, aPlus15.getReference());

        // subtract(Angle): Direction with same reference and display unit
        Direction aMinus10 = a.subtract(Angle.of(10.0, "deg"));
        assertEquals(20.0 * Math.PI / 180.0, aMinus10.si(), 1E-12);
        assertEquals(a.getDisplayUnit(), aMinus10.getDisplayUnit());
        assertEquals(Direction.Reference.EAST, aMinus10.getReference());
    }

    /**
     * Test the static Reference registry and chained reference offsets.
     */
    @Test
    void testReferencesAddGetAndOffsets()
    {
        // Built-ins are registered
        assertEquals(Direction.Reference.EAST, Direction.Reference.get("EAST"));
        assertEquals(Direction.Reference.NORTH, Direction.Reference.get("NORTH"));
        assertNull(Direction.Reference.get("UNKNOWN_REF"));

        // WEST = 180° relative to EAST (using add(id, name, offset) → relative to EAST by default)
        Direction.Reference.add("WEST", "West = 180 degrees from East", Angle.of(180.0, "deg"));
        var west = Direction.Reference.get("WEST");
        // Check Direction using WEST
        Direction w0 = new Direction(0.0, Angle.Unit.deg, west);
        Direction east0 = new Direction(0.0, Angle.Unit.deg, Direction.Reference.EAST);
        assertEquals(0.0, w0.si(), 1E-12);
        assertEquals(Math.PI, w0.subtract(east0).si());

        // Define NE = NORTH + 45°
        Direction.Reference.add("NE", "North-East: 45 degrees from North", Angle.of(45.0, "deg"), Direction.Reference.NORTH);
        var northEast = Direction.Reference.get("NE");
        // 0° in NE equals NORTH (90°) + 45° = 135° EAST = 3π/4
        Direction north0 = new Direction(0.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction ne0 = new Direction(0.0, Angle.Unit.deg, northEast);
        assertEquals(0.25 * Math.PI, ne0.subtract(north0).si(), 1E-12);

        // Cross-check via subtraction (relativeTo conversion used inside):
        // WEST(0°) minus EAST(0°) should be +π (in A's display unit)
        Angle westMinusEast = w0.subtract(new Direction(0.0, Angle.Unit.deg, Direction.Reference.EAST));
        assertEquals(Math.PI, westMinusEast.si(), 1E-12);
        assertEquals(w0.getDisplayUnit(), westMinusEast.getDisplayUnit());
    }
}
