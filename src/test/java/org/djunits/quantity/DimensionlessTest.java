package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * DimensionlessTest tests the Dimensionless quantity class and the Unitless unit.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class DimensionlessTest
{
    /**
     * Set the locale to "US" so we know what texts should be retrieved from the resources.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test the basic features: constructors, constants, parsing, instantiate, siUnit, and ofSi.
     */
    @Test
    void testDimensionlessBasics()
    {
        // Construct with unit
        Dimensionless n0 = new Dimensionless(0.0, Unitless.BASE);
        assertEquals(Dimensionless.ZERO, n0);
        assertEquals(0.0, Dimensionless.ZERO.si());

        Dimensionless n1 = new Dimensionless(1.0, Unitless.BASE);
        assertEquals(Dimensionless.ONE, n1);
        assertEquals(1.0, Dimensionless.ONE.si());

        // Constants sanity
        assertTrue(Double.isNaN(Dimensionless.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Dimensionless.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Dimensionless.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Dimensionless.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Dimensionless.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Dimensionless three = new Dimensionless(3.0, Unitless.BASE);
        Dimensionless copy = new Dimensionless(three);
        assertEquals(three.si(), copy.si(), 1E-12);

        // Construct with abbreviation string; Unitless.BASE uses " " (space)
        Dimensionless fromAbbrev = new Dimensionless(2.5, " ");
        assertEquals(2.5, fromAbbrev.si(), 1E-12);

        // Parsing valueOf and of(value, unitString) — use a space as the unit id/display
        Dimensionless p1 = Dimensionless.valueOf("4"); // "4" + space unit; spaces are allowed
        assertEquals(4.0, p1.si(), 1E-12);

        Dimensionless p2 = Dimensionless.of(5.0, " ");
        assertEquals(5.0, p2.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, three.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimensionless -> "" with all zero exponents)
        assertEquals("", three.siUnit().toString(true, false));

        // ofSi
        Dimensionless neg = Dimensionless.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic operations and reciprocal for Dimensionless.
     */
    @Test
    void testDimensionlessOperations()
    {
        // Divide by Dimensionless -> Dimensionless
        var d1 = Dimensionless.ONE.divide(Dimensionless.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-12);

        // / Length -> LinearObjectDensity
        var lod = Dimensionless.ofSi(6.0).divide(Length.ofSi(2.0));
        assertTrue(lod instanceof LinearObjectDensity);
        assertEquals(3.0, lod.si(), 1E-12);

        // / LinearObjectDensity -> Length
        var len = Dimensionless.ofSi(6.0).divide(LinearObjectDensity.ofSi(2.0));
        assertTrue(len instanceof Length);
        assertEquals(3.0, len.si(), 1E-12);

        // / Duration -> Frequency
        var freq = Dimensionless.ofSi(6.0).divide(Duration.ofSi(2.0));
        assertTrue(freq instanceof Frequency);
        assertEquals(3.0, freq.si(), 1E-12);

        // / Frequency -> Duration
        var dur = Dimensionless.ofSi(6.0).divide(Frequency.ofSi(2.0));
        assertTrue(dur instanceof Duration);
        assertEquals(3.0, dur.si(), 1E-12);

        // / ElectricalConductance -> ElectricalResistance
        var r = Dimensionless.ofSi(6.0).divide(ElectricalConductance.ofSi(2.0));
        assertTrue(r instanceof ElectricalResistance);
        assertEquals(3.0, r.si(), 1E-12);

        // / ElectricalResistance -> ElectricalConductance
        var g = Dimensionless.ofSi(6.0).divide(ElectricalResistance.ofSi(2.0));
        assertTrue(g instanceof ElectricalConductance);
        assertEquals(3.0, g.si(), 1E-12);

        // reciprocal -> Dimensionless
        var recip = Dimensionless.ofSi(4.0).reciprocal();
        assertTrue(recip instanceof Dimensionless);
        assertEquals(0.25, recip.si(), 1E-12);
    }

    /**
     * Test the Unitless behavior: siUnit, base unit, ofSi, deriveUnit linear success, and exception branch for non-linear
     * scale.
     */
    @Test
    void testUnitlessBehavior()
    {
        // siUnit and base unit links
        assertEquals(Unitless.SI_UNIT, Unitless.BASE.siUnit());
        assertEquals(Unitless.BASE, Unitless.BASE.getBaseUnit());

        // ofSi delegates to Dimensionless.ofSi
        Dimensionless fromUnit = Unitless.BASE.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (BASE) -> should succeed
        Unitless twice = Unitless.BASE.deriveUnit(" 2x", " 2x", "twice unitless", 2.0, UnitSystem.OTHER);
        Dimensionless x = new Dimensionless(1.0, twice); // 1 * 2 == 2
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Unitless nonLinear = new Unitless("gradeless", "gradeless", "gradeless", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("gradeless2", "gradeless2", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
