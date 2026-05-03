package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link QuantityFormat} settings that have an effect on the formatting of absolute quantities.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsQuantityFormatTest
{
    /**
     * Test prefix separator between number and reference, and postfix after the reference.
     */
    @Test
    public void testReferencePrefixPostfix()
    {
        Direction n = new Direction(30.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction e = new Direction(30.0, Angle.Unit.deg, Direction.Reference.EAST);

        String s1a = n.toString(QuantityFormat.defaults().textual());
        assertTrue(s1a.contains("30"));
        assertTrue(s1a.endsWith(" deg"));

        String s1b = n.toString(QuantityFormat.defaults().textual());
        assertTrue(s1b.contains("30"));
        assertTrue(s1b.endsWith(" deg"));

        String s2 = n.toString(QuantityFormat.defaults().noReference().textual());
        assertTrue(s2.contains("30"));
        assertTrue(s2.endsWith(" deg"));

        String s3 = n.toString(QuantityFormat.defaults().reference(false).textual());
        assertTrue(s3.contains("30"));
        assertTrue(s3.endsWith(" deg"));

        String s4n = n.toString(QuantityFormat.defaults().reference().textual());
        assertTrue(s4n.contains("30"));
        assertTrue(s4n.contains(" deg"));
        assertTrue(s4n.endsWith(" (NORTH)"));

        String s4e = e.toString(QuantityFormat.defaults().reference(true).textual());
        assertTrue(s4e.contains("30"));
        assertTrue(s4e.contains(" deg"));
        assertTrue(s4e.endsWith(" (EAST)"));

        String s5 = e.toString(QuantityFormat.defaults().reference().setPrefix(" [").setPostfix("]").textual());
        assertTrue(s5.contains("30"));
        assertTrue(s5.contains(" deg"));
        assertTrue(s5.endsWith(" [EAST]"));
    }

}
