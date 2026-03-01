package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * AreaTest tests the Area quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class AreaTest
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
     * Test the basic features: constructors, constants, parsing, SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testAreaBasics()
    {
        // Construct with unit
        Area a0 = new Area(0.0, Area.Unit.m2);
        assertEquals(Area.ZERO, a0);
        assertEquals(0.0, Area.ZERO.si());

        Area a1 = new Area(1.0, Area.Unit.m2);
        assertEquals(Area.ONE, a1);
        assertEquals(1.0, Area.ONE.si());

        // Constants sanity
        assertTrue(Double.isNaN(Area.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Area.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Area.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Area.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Area.NEG_MAXVALUE.si());

        // Copy constructor preserves SI (and display unit)
        Area km2 = new Area(1.0, Area.Unit.km2);
        Area copy = new Area(km2);
        assertEquals(km2.si(), copy.si(), 1E-12);

        // Construct with abbreviation string
        Area aKm2 = new Area(2.0, "km2");
        assertEquals(2.0 * 1.0E6, aKm2.si(), 1E-9);

        // "mum2" textual abbreviation vs "μm2" display abbreviation both resolvable
        Area aMum2Text = new Area(1.0, "mum2");
        assertEquals(1.0E-12, aMum2Text.si(), 1E-24);

        // SI multiples and submultiples
        assertEquals(1.0E6, new Area(1.0, Area.Unit.km2).si(), 1E-6);
        assertEquals(1.0E4, new Area(1.0, Area.Unit.hm2).si(), 1E-6);
        assertEquals(1.0E2, new Area(1.0, Area.Unit.dam2).si(), 1E-12);
        assertEquals(1.0E-2, new Area(1.0, Area.Unit.dm2).si(), 1E-12);
        assertEquals(1.0E-4, new Area(1.0, Area.Unit.cm2).si(), 1E-12);
        assertEquals(1.0E-6, new Area(1.0, Area.Unit.mm2).si(), 1E-12);
        assertEquals(1.0E-12, new Area(1.0, Area.Unit.mum2).si(), 1E-24);
        assertEquals(1.0E-18, new Area(1.0, Area.Unit.nm2).si(), 1E-27);
        assertEquals(1.0E-24, new Area(1.0, Area.Unit.pm2).si(), 1E-33);
        assertEquals(1.0E-30, new Area(1.0, Area.Unit.fm2).si(), 1E-39);
        assertEquals(1.0E-36, new Area(1.0, Area.Unit.am2).si(), 1E-45);

        // Metric area units: ca, a, ha
        assertEquals(1.0, new Area(1.0, Area.Unit.ca).si(), 1E-12); // centiare = 1 m^2
        assertEquals(100.0, new Area(1.0, Area.Unit.a).si(), 1E-12); // are = 100 m^2
        assertEquals(10_000.0, new Area(1.0, Area.Unit.ha).si(), 1E-12); // hectare = 10,000 m^2

        // Imperial/other units
        assertEquals(Length.Unit.CONST_MI * Length.Unit.CONST_MI, new Area(1.0, Area.Unit.mi2).si(), 1E-6);
        assertEquals(Length.Unit.CONST_NM * Length.Unit.CONST_NM, new Area(1.0, Area.Unit.NM2).si(), 1E-6);
        assertEquals(Length.Unit.CONST_FT * Length.Unit.CONST_FT, new Area(1.0, Area.Unit.ft2).si(), 1E-12);
        assertEquals(Length.Unit.CONST_IN * Length.Unit.CONST_IN, new Area(1.0, Area.Unit.in2).si(), 1E-12);
        assertEquals(Length.Unit.CONST_YD * Length.Unit.CONST_YD, new Area(1.0, Area.Unit.yd2).si(), 1E-12);

        // Acre: 1/640 square mile
        assertEquals(Length.Unit.CONST_MI * Length.Unit.CONST_MI / 640.0, new Area(1.0, Area.Unit.ac).si(), 1E-6);

        // Parsing valueOf and of(value, unitString)
        Area p1 = Area.valueOf("12 km2");
        assertEquals(12.0 * 1.0E6, p1.si(), 1E-6);

        Area p2 = Area.valueOf("1 ac");
        assertEquals(Length.Unit.CONST_MI * Length.Unit.CONST_MI / 640.0, p2.si(), 1E-6);

        Area p3 = Area.of(3.0, "yd2");
        assertEquals(3.0 * Length.Unit.CONST_YD * Length.Unit.CONST_YD, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, aKm2.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation
        assertEquals("m2", aKm2.siUnit().toString(true, false));

        // ofSi
        Area neg = Area.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic: divide/multiply combinations and reciprocal.
     */
    @Test
    void testAreaOperations()
    {
        // Divide Area by Area -> Dimensionless
        var d1 = Area.ONE.divide(Area.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-12);

        // Area * ArealObjectDensity -> Dimensionless
        var n2 = Area.ofSi(2.0).multiply(ArealObjectDensity.ofSi(3.0));
        assertTrue(n2 instanceof Dimensionless);
        assertEquals(6.0, n2.si(), 1E-12);

        // Area * Length -> Volume
        var v3 = Area.ofSi(2.0).multiply(Length.ofSi(3.0));
        assertTrue(v3 instanceof Volume);
        assertEquals(6.0, v3.si(), 1E-12);

        // Area / LinearObjectDensity -> Volume
        var v4 = Area.ofSi(6.0).divide(LinearObjectDensity.ofSi(2.0));
        assertTrue(v4 instanceof Volume);
        assertEquals(3.0, v4.si(), 1E-12);

        // Area / Volume -> LinearObjectDensity
        var lod5 = Area.ofSi(6.0).divide(Volume.ofSi(2.0));
        assertTrue(lod5 instanceof LinearObjectDensity);
        assertEquals(3.0, lod5.si(), 1E-12);

        // Area / Length -> Length
        var l6 = Area.ofSi(6.0).divide(Length.ofSi(2.0));
        assertTrue(l6 instanceof Length);
        assertEquals(3.0, l6.si(), 1E-12);

        // Area * LinearObjectDensity -> Length
        var l7 = Area.ofSi(2.0).multiply(LinearObjectDensity.ofSi(3.0));
        assertTrue(l7 instanceof Length);
        assertEquals(6.0, l7.si(), 1E-12);

        // Area * Speed -> FlowVolume
        var q8 = Area.ofSi(2.0).multiply(Speed.ofSi(3.0));
        assertTrue(q8 instanceof FlowVolume);
        assertEquals(6.0, q8.si(), 1E-12);

        // Area * Pressure -> Force
        var f9 = Area.ofSi(2.0).multiply(Pressure.ofSi(3.0));
        assertTrue(f9 instanceof Force);
        assertEquals(6.0, f9.si(), 1E-12);

        // Area * Illuminance -> LuminousFlux
        var phi10 = Area.ofSi(2.0).multiply(Illuminance.ofSi(3.0));
        assertTrue(phi10 instanceof LuminousFlux);
        assertEquals(6.0, phi10.si(), 1E-12);

        // reciprocal -> ArealObjectDensity
        var rhoA = Area.ofSi(4.0).reciprocal();
        assertTrue(rhoA instanceof ArealObjectDensity);
        assertEquals(0.25, rhoA.si(), 1E-12);
    }

    /**
     * Test the Area.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Area.Unit.SI_UNIT, Area.Unit.m2.siUnit());
        assertEquals(Area.Unit.SI, Area.Unit.m2.getBaseUnit());

        // Unit.ofSi delegates to Area.ofSi
        Area fromUnit = Area.Unit.m2.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (m2) -> should succeed
        Area.Unit twoM2 = Area.Unit.m2.deriveUnit("m2x2", "m2x2", "two square meters", 2.0, UnitSystem.OTHER);
        Area x = new Area(1.0, twoM2); // 1 * 2 m2 == 2 m2
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Area.Unit nonLinear = new Area.Unit("ga", "ga", "grade-like area (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("ga2", "ga2", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
