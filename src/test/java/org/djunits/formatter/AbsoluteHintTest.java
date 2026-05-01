package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link AbsoluteHint} settings and their effect on quantity formatting.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsoluteHintTest
{
    /**
     * Test prefix separator between number and reference, and postfix after the reference.
     */
    @Test
    public void testReferencePrefixPostfix()
    {
        Direction n = new Direction(30.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction e = new Direction(30.0, Angle.Unit.deg, Direction.Reference.EAST);

        String s1a = n.toString(new UnitHint().textual(), new AbsoluteHint());
        assertTrue(s1a.contains("30"));
        assertTrue(s1a.endsWith(" deg"));

        String s1b = n.toString(new UnitHint().textual());
        assertTrue(s1b.contains("30"));
        assertTrue(s1b.endsWith(" deg"));

        String s2 = n.toString(new AbsoluteHint().noReference(), new UnitHint().textual());
        assertTrue(s2.contains("30"));
        assertTrue(s2.endsWith(" deg"));

        String s3 = n.toString(new AbsoluteHint().reference(false), new UnitHint().textual());
        assertTrue(s3.contains("30"));
        assertTrue(s3.endsWith(" deg"));

        String s4n = n.toString(new AbsoluteHint().reference(), new UnitHint().textual());
        assertTrue(s4n.contains("30"));
        assertTrue(s4n.contains(" deg"));
        assertTrue(s4n.endsWith(" (NORTH)"));

        String s4e = e.toString(new AbsoluteHint().reference(true), new UnitHint().textual());
        assertTrue(s4e.contains("30"));
        assertTrue(s4e.contains(" deg"));
        assertTrue(s4e.endsWith(" (EAST)"));

        String s5 = e.toString(new AbsoluteHint().reference().setPrefix(" [").setPostfix("]"), new UnitHint().textual());
        assertTrue(s5.contains("30"));
        assertTrue(s5.contains(" deg"));
        assertTrue(s5.endsWith(" [EAST]"));
    }

}
