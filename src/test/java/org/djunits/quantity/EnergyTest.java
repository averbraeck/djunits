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
 * EnergyTest tests the Energy quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class EnergyTest
{
    /**
     * Setup uniform locale before the tests.
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
    void testEnergyBasics()
    {
        // Construct with unit
        Energy e0 = new Energy(0.0, Energy.Unit.J);
        assertEquals(Energy.ZERO, e0);
        assertEquals(0.0, Energy.ZERO.si(), 1E-12);

        Energy e1 = new Energy(1.0, Energy.Unit.J);
        assertEquals(Energy.ONE, e1);
        assertEquals(1.0, Energy.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Energy.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Energy.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Energy.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Energy.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Energy.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Energy kilo = new Energy(2.0, Energy.Unit.kJ); // 2 kJ = 2000 J
        Energy copy = new Energy(kilo);
        assertEquals(kilo.si(), copy.si(), 1E-9);
        assertEquals(kilo.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Energy eStr = new Energy(1.5, "J");
        assertEquals(1.5, eStr.si(), 1E-12);

        // SI prefixes via generated/resolved units
        assertEquals(1E-6, new Energy(1.0, Energy.Unit.muJ).si(), 1E-15);
        assertEquals(1E-3, new Energy(1.0, Energy.Unit.mJ).si(), 1E-12);
        assertEquals(1E3, new Energy(1.0, Energy.Unit.kJ).si(), 1E-9);
        assertEquals(1E6, new Energy(1.0, Energy.Unit.MJ).si(), 1E-6);
        assertEquals(1E9, new Energy(1.0, Energy.Unit.GJ).si(), 1E-3);
        assertEquals(1E12, new Energy(1.0, Energy.Unit.TJ).si(), 1E3);
        assertEquals(1E15, new Energy(1.0, Energy.Unit.PJ).si(), 1E6);

        // Watt-hour family (1 Wh = 3600 J)
        assertEquals(3600.0, new Energy(1.0, Energy.Unit.WATT_HOUR).si(), 1E-9);
        assertEquals(3.6e-3, new Energy(1.0, Energy.Unit.muWh).si(), 1E-12);
        assertEquals(3.6, new Energy(1.0, Energy.Unit.mWh).si(), 1E-12);
        assertEquals(3.6e6, new Energy(1.0, Energy.Unit.kWh).si(), 1E-3);
        assertEquals(3.6e9, new Energy(1.0, Energy.Unit.MWh).si(), 1E2);
        assertEquals(3.6e12, new Energy(1.0, Energy.Unit.GWh).si(), 1E2);
        assertEquals(3.6e15, new Energy(1.0, Energy.Unit.TWh).si(), 1E6);
        assertEquals(3.6e18, new Energy(1.0, Energy.Unit.PWh).si(), 1E9);

        // Electronvolt family
        assertEquals(1.602176634E-19, new Energy(1.0, Energy.Unit.eV).si(), 1E-30);
        assertEquals(1.602176634E-16, new Energy(1.0, Energy.Unit.keV).si(), 1E-27);
        assertEquals(1.602176634E-13, new Energy(1.0, Energy.Unit.MeV).si(), 1E-24);
        assertEquals(1.602176634E-10, new Energy(1.0, Energy.Unit.GeV).si(), 1E-21);

        // CGS / MTS / Imperial: erg, sthene-meter, BTU, calories
        assertEquals(1.0E-7, new Energy(1.0, Energy.Unit.erg).si(), 1E-18);
        assertEquals(1000.0, new Energy(1.0, Energy.Unit.sn_m).si(), 1E-9);
        assertEquals(1.0545E3, new Energy(1.0, Energy.Unit.BTU_ISO).si(), 1E-6);
        assertEquals(1.05505585262E3, new Energy(1.0, Energy.Unit.BTU_IT).si(), 1E-6);
        assertEquals(4.1868, new Energy(1.0, Energy.Unit.cal_IT).si(), 1E-9);
        assertEquals(4.184, new Energy(1.0, Energy.Unit.cal).si(), 1E-12);
        assertEquals(4184.0, new Energy(1.0, Energy.Unit.kcal).si(), 1E-9);

        // ft·lbf vs in·lbf ratio (1 ft = 12 in)
        double ft = new Energy(1.0, Energy.Unit.ft_lbf).si();
        double inch = new Energy(1.0, Energy.Unit.in_lbf).si();
        assertEquals(12.0, ft / inch, 1E-12);

        // Parsing valueOf and of(value, unitString)
        Energy p1 = Energy.valueOf("2 J");
        assertEquals(2.0, p1.si(), 1E-12);

        Energy p2 = Energy.valueOf("2 kJ"); // 2000 J
        assertEquals(2000.0, p2.si(), 1E-9);

        Energy p3 = Energy.of(500.0, "mJ"); // 0.5 J
        assertEquals(0.5, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, eStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("kgm2/s2", eStr.siUnit().toString(true, false));

        // ofSi
        Energy neg = Energy.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide, multiply) behavior with related quantities.
     */
    @Test
    void testEnergyOperations()
    {
        // Divide by Energy -> Dimensionless
        var d1 = Energy.ONE.divide(Energy.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Energy.ofSi(1.0).divide(Energy.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Energy / Force -> Length
        var l1 = Energy.ofSi(6.0).divide(Force.ofSi(2.0));
        assertTrue(l1 instanceof Length);
        assertEquals(3.0, l1.si(), 1E-12);

        // Energy / Length -> Force
        var f1 = Energy.ofSi(6.0).divide(Length.ofSi(2.0));
        assertTrue(f1 instanceof Force);
        assertEquals(3.0, f1.si(), 1E-12);

        // Energy * LinearObjectDensity -> Force
        var f2 = Energy.ofSi(2.0).multiply(LinearObjectDensity.ofSi(3.0));
        assertTrue(f2 instanceof Force);
        assertEquals(6.0, f2.si(), 1E-12);

        // Energy / Duration -> Power
        var p1 = Energy.ofSi(6.0).divide(Duration.ofSi(2.0));
        assertTrue(p1 instanceof Power);
        assertEquals(3.0, p1.si(), 1E-12);

        // Energy / Power -> Duration
        var t1 = Energy.ofSi(6.0).divide(Power.ofSi(2.0));
        assertTrue(t1 instanceof Duration);
        assertEquals(3.0, t1.si(), 1E-12);

        // Energy / Volume -> Pressure
        var pr1 = Energy.ofSi(6.0).divide(Volume.ofSi(2.0));
        assertTrue(pr1 instanceof Pressure);
        assertEquals(3.0, pr1.si(), 1E-12);

        // Energy / Pressure -> Volume
        var v1 = Energy.ofSi(6.0).divide(Pressure.ofSi(2.0));
        assertTrue(v1 instanceof Volume);
        assertEquals(3.0, v1.si(), 1E-12);

        // Energy * Frequency -> Power
        var p2 = Energy.ofSi(2.0).multiply(Frequency.ofSi(3.0));
        assertTrue(p2 instanceof Power);
        assertEquals(6.0, p2.si(), 1E-12);

        // Energy / Speed -> Momentum
        var m1 = Energy.ofSi(6.0).divide(Speed.ofSi(2.0));
        assertTrue(m1 instanceof Momentum);
        assertEquals(3.0, m1.si(), 1E-12);

        // Energy / Momentum -> Speed
        var s1 = Energy.ofSi(6.0).divide(Momentum.ofSi(2.0));
        assertTrue(s1 instanceof Speed);
        assertEquals(3.0, s1.si(), 1E-12);
    }

    /**
     * Test the Energy.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Energy.Unit.SI_UNIT, Energy.Unit.J.siUnit());
        assertEquals(Energy.Unit.SI, Energy.Unit.J.getBaseUnit());

        // Unit.ofSi delegates to Energy.ofSi
        Energy fromUnit = Energy.Unit.J.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (J) -> should succeed
        Energy.Unit twoJ = Energy.Unit.J.deriveUnit("2J", "2J", "two joule", 2.0, UnitSystem.OTHER);
        Energy x = new Energy(1.0, twoJ); // 1 * 2 J == 2 J
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Energy.Unit nonLinear =
                new Energy.Unit("gJ", "gJ", "grade-like joule (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2J", "g2J", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
