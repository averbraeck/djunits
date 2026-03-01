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
 * LengthTest tests the Length quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class LengthTest
{
    /**
     * Use standard locale for all tests.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, constants, parsing (incl. error branches), SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testLengthBasicsAndParsing()
    {
        // Construct with unit
        Length l0 = new Length(0.0, Length.Unit.m);
        assertEquals(Length.ZERO, l0);
        assertEquals(0.0, Length.ZERO.si(), 1E-12);

        Length l1 = new Length(1.0, Length.Unit.m);
        assertEquals(Length.ONE, l1);
        assertEquals(1.0, Length.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Length.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Length.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Length.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Length.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Length.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Length kilo = new Length(2.0, Length.Unit.km); // 2 km = 2000 m
        Length copy = new Length(kilo);
        assertEquals(kilo.si(), copy.si(), 1E-9);
        assertEquals(kilo.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Length lStr = new Length(1.5, "m");
        assertEquals(1.5, lStr.si(), 1E-12);

        // SI prefixes via resolved units
        assertEquals(1E-18, new Length(1.0, Length.Unit.am).si(), 1E-27);
        assertEquals(1E-15, new Length(1.0, Length.Unit.fm).si(), 1E-24);
        assertEquals(1E-12, new Length(1.0, Length.Unit.pm).si(), 1E-21);
        assertEquals(1E-9, new Length(1.0, Length.Unit.nm).si(), 1E-18);
        assertEquals(1E-6, new Length(1.0, Length.Unit.mum).si(), 1E-15); // micrometer
        assertEquals(1E-3, new Length(1.0, Length.Unit.mm).si(), 1E-12);
        assertEquals(1E-2, new Length(1.0, Length.Unit.cm).si(), 1E-11);
        assertEquals(1E-1, new Length(1.0, Length.Unit.dm).si(), 1E-10);
        assertEquals(1E1, new Length(1.0, Length.Unit.dam).si(), 1E-9);
        assertEquals(1E2, new Length(1.0, Length.Unit.hm).si(), 1E-8);
        assertEquals(1E3, new Length(1.0, Length.Unit.km).si(), 1E-6);

        // Imperial / other special units
        assertEquals(Length.Unit.CONST_FT, new Length(1.0, Length.Unit.ft).si(), 1E-12);
        assertEquals(Length.Unit.CONST_IN, new Length(1.0, Length.Unit.in).si(), 1E-12);
        assertEquals(Length.Unit.CONST_YD, new Length(1.0, Length.Unit.yd).si(), 1E-12);
        assertEquals(Length.Unit.CONST_MI, new Length(1.0, Length.Unit.mi).si(), 1E-9);
        assertEquals(Length.Unit.CONST_NM, new Length(1.0, Length.Unit.NM).si(), 1E-9); // nautical mile

        // Ångström (A) = 1e-10 m
        assertEquals(1E-10, new Length(1.0, Length.Unit.A).si(), 1E-20);

        // Astronomical distances
        assertEquals(Length.Unit.CONST_AU, new Length(1.0, Length.Unit.AU).si(), 1E-3);
        assertEquals(Length.Unit.CONST_LY, new Length(1.0, Length.Unit.ly).si(), 1E3);
        assertEquals(Length.Unit.CONST_PC, new Length(1.0, Length.Unit.pc).si(), 1E9);

        // Quick consistency checks: 12 in = 1 ft; 3 ft = 1 yd; 5280 ft = 1 mi
        double oneFt = new Length(1.0, Length.Unit.ft).si();
        double oneIn = new Length(1.0, Length.Unit.in).si();
        double oneYd = new Length(1.0, Length.Unit.yd).si();
        double oneMi = new Length(1.0, Length.Unit.mi).si();
        assertEquals(oneFt, 12.0 * oneIn, 1E-12);
        assertEquals(oneYd, 3.0 * oneFt, 1E-12);
        assertEquals(oneMi, 5280.0 * oneFt, 1E-6);

        // Parsec relation: Pc / AU = 648000 / pi
        double pcOverAu = Length.Unit.CONST_PC / Length.Unit.CONST_AU;
        assertEquals(648_000.0 / Math.PI, pcOverAu, 1E-9);

        // Parsing valueOf and of(value, unitString)
        Length p1 = Length.valueOf("2 m");
        assertEquals(2.0, p1.si(), 1E-12);

        Length p2 = Length.valueOf("2 km"); // 2000 m
        assertEquals(2000.0, p2.si(), 1E-9);

        Length p3 = Length.of(500.0, "cm"); // 5 m
        assertEquals(5.0, p3.si(), 1E-12);

        // Parsing error branches
        assertThrows(NullPointerException.class, () -> Length.valueOf(null));
        assertThrows(IllegalArgumentException.class, () -> Length.valueOf(""));
        assertThrows(IllegalArgumentException.class, () -> Length.valueOf("abc m")); // bad number
        assertThrows(IllegalArgumentException.class, () -> Length.valueOf("10 blargh")); // unknown unit

        assertThrows(NullPointerException.class, () -> Length.of(1.0, null));
        assertThrows(IllegalArgumentException.class, () -> Length.of(1.0, ""));
        assertThrows(UnitRuntimeException.class, () -> Length.of(1.0, "blargh"));

        // instantiate
        assertEquals(-10.1, lStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation
        assertEquals("m", lStr.siUnit().toString(true, false));

        // ofSi
        Length neg = Length.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic operations (divide/multiply variants) and reciprocal.
     */
    @Test
    void testLengthOperations()
    {
        // Divide by Length -> Dimensionless
        var r1 = Length.ONE.divide(Length.ONE);
        assertTrue(r1 instanceof Dimensionless);
        assertEquals(1.0, r1.si(), 1E-12);

        var r2 = Length.ofSi(1.0).divide(Length.ofSi(2.0));
        assertEquals(0.5, r2.si(), 1E-12);

        // Length * LinearObjectDensity -> Dimensionless
        var dim1 = Length.ofSi(2.0).multiply(LinearObjectDensity.ofSi(3.0));
        assertTrue(dim1 instanceof Dimensionless);
        assertEquals(6.0, dim1.si(), 1E-12);

        // Length * Length -> Area
        var a1 = Length.ofSi(2.0).multiply(Length.ofSi(3.0));
        assertTrue(a1 instanceof Area);
        assertEquals(6.0, a1.si(), 1E-12);

        // Length / LinearObjectDensity -> Area
        var a2 = Length.ofSi(6.0).divide(LinearObjectDensity.ofSi(2.0));
        assertTrue(a2 instanceof Area);
        assertEquals(3.0, a2.si(), 1E-12);

        // Length / Area -> LinearObjectDensity
        var lod = Length.ofSi(6.0).divide(Area.ofSi(2.0));
        assertTrue(lod instanceof LinearObjectDensity);
        assertEquals(3.0, lod.si(), 1E-12);

        // Length * Area -> Volume
        var v = Length.ofSi(2.0).multiply(Area.ofSi(3.0));
        assertTrue(v instanceof Volume);
        assertEquals(6.0, v.si(), 1E-12);

        // Length * Force -> Energy
        var e = Length.ofSi(2.0).multiply(Force.ofSi(3.0));
        assertTrue(e instanceof Energy);
        assertEquals(6.0, e.si(), 1E-12);

        // Length * Frequency -> Speed
        var s1 = Length.ofSi(2.0).multiply(Frequency.ofSi(3.0));
        assertTrue(s1 instanceof Speed);
        assertEquals(6.0, s1.si(), 1E-12);

        // Length / Duration -> Speed
        var s2 = Length.ofSi(6.0).divide(Duration.ofSi(2.0));
        assertTrue(s2 instanceof Speed);
        assertEquals(3.0, s2.si(), 1E-12);

        // Length / Speed -> Duration
        var t = Length.ofSi(6.0).divide(Speed.ofSi(2.0));
        assertTrue(t instanceof Duration);
        assertEquals(3.0, t.si(), 1E-12);

        // Length * FlowMass -> Momentum
        var p = Length.ofSi(2.0).multiply(FlowMass.ofSi(3.0));
        assertTrue(p instanceof Momentum);
        assertEquals(6.0, p.si(), 1E-12);

        // reciprocal -> LinearObjectDensity
        var inv = Length.ofSi(4.0).reciprocal();
        assertTrue(inv instanceof LinearObjectDensity);
        assertEquals(0.25, inv.si(), 1E-12);
    }

    /**
     * Test Length.add(Position): reference propagation, display unit propagation, and SI math.
     */
    @Test
    void testAddWithPosition()
    {
        // Define references: origin and a station 100 m offset from origin
        Position.Reference origin = new Position.Reference("O", "Origin");
        Position.Reference station = new Position.Reference("S", "Station", Length.ofSi(100.0), origin);

        // Position at Station: 20 m (display set to cm to verify later overwriting)
        Position posStation = new Position(20.0, Length.Unit.m, station).setDisplayUnit(Length.Unit.cm);
        assertEquals(20.0, posStation.si(), 1E-12);
        assertEquals(station, posStation.getReference());

        // Length to add: 2 m but expressed in km for display-unit propagation
        Length lenKm = new Length(0.002, Length.Unit.km); // 2 m

        // Add: result should have station reference, display unit of lenKm (km), and SI = 22 m
        Position result = lenKm.add(posStation);
        assertEquals(station, result.getReference(), "Reference should be preserved from Position");
        assertEquals(lenKm.getDisplayUnit(), result.getDisplayUnit(), "Display unit should follow Length");
        assertEquals(22.0, result.si(), 1E-12);

        // Another add: use a length in cm; result should switch display unit to cm
        Length lenCm = new Length(150.0, Length.Unit.cm); // 1.5 m
        Position result2 = lenCm.add(posStation);
        assertEquals(station, result2.getReference());
        assertEquals(21.5, result2.si(), 1E-12);
        assertEquals(Length.Unit.cm, result2.getDisplayUnit(), "Display unit should now be cm (from the Length being added)");
    }

    /**
     * Test Unit behavior: base/si unit, ofSi, deriveUnit (linear), and non-linear branch throwing UnitRuntimeException.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Length.Unit.SI_UNIT, Length.Unit.m.siUnit());
        assertEquals(Length.Unit.SI, Length.Unit.m.getBaseUnit());

        // Unit.ofSi delegates to Length.ofSi
        Length fromUnit = Length.Unit.m.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (m) -> should succeed
        Length.Unit twoM = Length.Unit.m.deriveUnit("2m", "two meter", 2.0, UnitSystem.OTHER);
        Length x = new Length(1.0, twoM); // 1 * 2 m == 2 m
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Length.Unit nonLinear =
                new Length.Unit("gm", "gm", "grade-like meter (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2m", "nonlinear derived", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
